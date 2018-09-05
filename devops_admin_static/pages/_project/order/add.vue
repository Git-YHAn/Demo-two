<template>
<div>
  <portlet title="创建发布工单" :showSearchBar="false">
    <el-form ref="form" :model="orderInfo" :rules="rules" label-width="80px" label-position="top">
      <div class="row">
        <div class="col-xs-12">
          <div class="portlet box grey-steel">
            <div class="portlet-title">
              <div class="caption">工单信息</div>
            </div>
            <div class="portlet-body">
              <el-col :span="24">
                <el-form-item label="工单标题" prop='orderTitle'>
                  <el-input v-model="orderInfo.orderTitle" placeholder="请输入工单标题"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="发布信息" prop='orderDetails'>
                  <no-ssr>
                    <tinymce id="orderDetailsEditor" v-model="orderInfo.orderDetails"></tinymce>
                  </no-ssr>
                </el-form-item>
              </el-col>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="portlet box grey-steel">
            <div class="portlet-title">
              <div class="caption">任务列表</div>
            </div>
            <div class="portlet-body">
              <table class="dao-table slat">
                <thead>
                  <tr>
                    <th class="name">
                      <el-checkbox v-model="isSelectAll"></el-checkbox> 应用名称</th>
                    <th class="version">版本编号</th>
                    <th class="deploy-type">并行发布</th>
                    <th class="operation">操作</th>
                  </tr>
                </thead>
                <tbody>
                    <template v-for="row in apps">
                      <tr :key="row.appEnvId+'#1'" :class="{'selected': row.isSelected}" @click="toggleRowSelected(row)">
                        <td class="name">{{row.appName}}</td>
                        <td class="version">
                          <el-select v-model="row.depAppVerId" style="width:100%;" popper-class="version-dropdown" placeholder="请选择" @change="changeVersion(row)">
                            <el-option v-for="item in row.versions" :key="item.depAppVerId" :label="item.versionCode" :value="item.depAppVerId">
                              <div>
                                {{ item.versionCode }}
                                <p><a href="javascrip:;" :title="item.description">{{item.description}}</a></p>
                              </div>
                            </el-option>
                          </el-select>
                          <div class="item-minor">
                            <span><a href="javascrip:;" :title="row.versionDesc">{{row.versionDesc}}</a></span>
                          </div>
                        </td>
                        <td class="deploy-type"><el-checkbox v-model="row.deployType" :true-label="1" :false-label="0"></el-checkbox></td>
                        <td class="operation">
                          <el-button @click.stop="selectServerAppEnvId=row.appEnvId">选择发布服务器</el-button>
                          <server-form :orderDetail="row" :visible="selectServerAppEnvId===row.appEnvId" @reset="selectServerAppEnvId=null" @save="changeServer"></server-form>
                        </td>
                      </tr>
                      <tr :key="row.appEnvId+'#2'" class="slat2" :class="{'selected': row.isSelected}"  >
                        <td colspan="4" ><span class="arrow-key badge badge-info badge-roundless" v-for="item in row.selectedServers"> {{item.sshAddress}} </span></td>
                      </tr>
                  </template>
                </tbody>
              </table>
              <div class="row">
                <div class="col-xs-12" style="text-align:right;padding-top: 20px;">
                  <el-button v-if="selectedApps.length>0" type="primary" @click="saveOrder()">保存发布工单</el-button>
                  <el-button v-else type="primary" disabled>请选择要发布的应用</el-button>
                  <el-button @click="$router.back()">取消</el-button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-form>
  </portlet>
</div>
</template>
<script>
import serverForm from './server.vue'
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex'
export default {
  components: {
    serverForm
  },
  data: function () {
    return {
      selectServerAppEnvId: null,
      isSelectAll: false,
      orderInfo: {
        orderTitle: '',
        orderDetails: '',
        deployOrderInfos: []
      },
      apps: [],
      rules: {
        orderTitle: [{ required: true, message: '请输入工单标题', trigger: 'blur' }],
        orderDetails: [{ required: true, message: '请输入发布信息', trigger: 'blur' }]
      }
    }
  },
  watch: {
    isSelectAll: function (value) {
      this.apps.forEach(item => {
        item.isSelected = value
      })
    }
  },
  computed: {
    ...mapState('project', {
      tempOrderInfo: 'orderInfo'
    }),
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function () {
      return this.currentProject.proId
    },
    envId: function () {
      return this.currentEnvironment.envId
    },
    selectedApps: function () {
      return this.apps.filter(item => {
        return item.isSelected
      })
    }
  },
  methods: {
    ...mapActions('action', ['queryApps', 'saveOrderInfo']),
    ...mapMutations('project', ['SET_ORDER_INFO']),
    deleteItem(row) {
      let idx = this.apps.indexOf(row)
      this.apps.splice(idx, 1)
    },
    toggleRowSelected(row) {
      row.isSelected = !row.isSelected
      this.$set(this.apps, this.apps.indexOf(row), row)
    },
    changeVersion(row) {
      let depAppVerId = row.depAppVerId
      row.versions.forEach(item => {
        if (depAppVerId === item.depAppVerId) {
          row.versionDesc = item.description
        }
      })
    },
    changeServer(orderDetail) {
      this.apps.forEach(item => {
        if (item.appEnvId === orderDetail.appEnvId) {
          item = orderDetail
        }
      })
      this.selectServerAppEnvId = null
    },
    async queryAllApps() {
      let response = await this.queryApps({ 'proId': this.proId, 'envId': this.envId })
      if (~~response.code === 1) {
        this.apps = response.data
        this.apps.forEach(item => {
          item.selectedServers = item.servers
          if (item.versions.length > 0) {
            this.$set(item, 'depAppVerId', item.versions[0].depAppVerId)
            this.$set(item, 'versionDesc', item.versions[0].description)
          }
        })
      }
    },
    saveOrder() {
      this.$refs['form'].validate(async (valid, object) => {
        if (valid) {
          this.orderInfo.proId = this.proId
          this.orderInfo.envId = this.envId
          this.orderInfo.deployOrderInfos = []
          this.selectedApps.forEach(item => {
            if (!valid) {
              return
            }
            if (!item.depAppVerId) {
              this.$msg.error('应用【' + item.appName + '】未选择发布版本')
              valid = false
              return
            }
            if (item.selectedServers.length === 0) {
              this.$msg.error('应用【' + item.appName + '】未选择服务器')
              valid = false
              return
            }
            item.selectedServers.forEach(server => {
              this.orderInfo.deployOrderInfos.push({
                'appEnvId': item.appEnvId,
                'appName': item.appName,
                'serverId': server.assetsId,
                'serverName': server.assetsName,
                'serverIp': server.sshAddress,
                'depAppVerId': item.depAppVerId,
                'deployType': item.deployType
              })
            })
          })
          if (valid) {
            let response = await this.saveOrderInfo(this.orderInfo)
            if (~~response.code === 1) {
              this.$router.push(`detail/${response.data}`)
            } else {
              this.$msg.error(response.msg)
            }
          }
        } else {
          let msg = ''
          for (let key in object) {
            let validArr = object[key]
            if (validArr.length > 0) {
              msg = validArr[0].message
              this.$msg.error(msg)
              return false
            }
          }
          return false
        }
      })
    }
  },
  async mounted() {
    await this.queryAllApps()
    if (this.tempOrderInfo && this.tempOrderInfo.deployOrderInfos) {
      this.orderInfo.orderTitle = this.tempOrderInfo.orderTitle
      this.orderInfo.orderDetails = this.tempOrderInfo.orderDetails
      this.orderInfo.deployOrderInfos = this.tempOrderInfo.deployOrderInfos
      this.SET_ORDER_INFO(null)
      let deployOrderInfos = this.orderInfo.deployOrderInfos
      this.apps.forEach(item => {
        let flag = false
        let serverIds = []
        deployOrderInfos.forEach(info => {
          if (info.appEnvId === item.appEnvId) {
            flag = true
            serverIds.push(info.serverId)
            if (!item.isSelected) {
              item.isSelected = true
              item.deployType = info.deployType
              if (info.version && info.version.versionId) {
                item.depAppVerId = info.version.versionId
              }
            }
          }
        })
        if (flag) {
          item.selectedServers = item.servers.filter(server => {
            return serverIds.indexOf(server.assetsId) > -1
          })
          this.$set(this.apps, this.apps.indexOf(item), item)
        }
      })
    }
  }
}
</script>
<style scoped>
.dao-table.slat {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  color: #9ba3af;
  border: 1px solid #e4e7ed;
  border-radius: 3px;
  text-align: left;
  margin: 0;
  width: 100%;
  overflow: visible;
}

.dao-table thead {
  background: #f1f3f6;
}

.dao-table thead tr {
  display: flex;
  background: #f1f3f6;
  height: 40px;
  line-height: 40px;
  border-bottom: 1px solid #e4e7ed;
}

.dao-table.slat th {
  display: block;
  flex: 1 1 auto;
  flex-basis: auto !important;
  width: 0;
  white-space: nowrap;
  margin-right: 30px;
  font-weight: 400;
}

.dao-table.slat th:first-child,
.dao-table.slat td:first-child {
  margin-left: 20px;
}

.dao-table.slat tbody {
  position: relative;
  display: block;
  overflow-y: auto;
  border-radius: 1px;
  background: linear-gradient(#fff 30%, hsla(0, 0%, 100%, 0)), linear-gradient(hsla(0, 0%, 100%, 0), #fff 70%) 0 100%, radial-gradient(farthest-side at 50% 0, rgba(0, 0, 0, .2), transparent), radial-gradient(farthest-side at 50% 100%, rgba(0, 0, 0, .2), transparent) 0 100%;
  background-repeat: no-repeat;
  background-color: #fff;
  background-size: 100% 40px, 100% 40px, 100% 14px, 100% 14px;
  background-attachment: local, local, scroll, scroll;
}

.dao-table.slat tbody tr {
  height: 82px;
  transform: translateZ(0);
}

.dao-table.slat tr {
  display: flex;
  border-bottom: 1px solid #f1f3f6;
}

.dao-table.slat tbody tr:not(.slat2):hover+.slat2 {
  border-color: #e7f1ff;
  background: rgba(85, 155, 255, .1);
}

.dao-table.slat tbody tr.selected {
  border-image: 1 linear-gradient(90deg, rgba(56, 144, 255, .8) 0, rgba(56, 144, 255, .8) 6px, #deecff 0, #deecff);
  border-width: 0;
  border-bottom-width: 1px;
  background: rgba(75, 165, 255, .1) !important;
  box-shadow: inset 10px 0 0 -5px rgba(56, 144, 255, .8);
}

.dao-table td {
  position: relative;
  margin: auto;
  line-height: normal;
  overflow: visible;
}

.dao-table.slat td,
.dao-table.slat th {
  display: block;
  text-align: center;
  flex: 1 1 auto;
  flex-basis: auto !important;
  width: 0;
  white-space: nowrap;
}

.dao-table.slat td.name,
.dao-table.slat th.name {
  text-align: left;
  width: 200px;
}

.dao-table.slat td.version,
.dao-table.slat th.version {
  text-align: left;
  width: 400px;
}

.dao-table.slat td.deploy-type,
.dao-table.slat th.deploy-type {
  text-align: center;
  width: 100px;
}

.dao-table.slat td.operation,
.dao-table.slat th.operation {
  width: 180px;
}

.dao-table.slat tbody tr:last-child {
  border-bottom: none !important;
}

.dao-table.slat td {
  margin-right: 30px;
  color: #595f69;
}

.dao-table.slat .slat2 {
  min-height: 18px;
  height: auto;
  border-bottom: 1px solid #e4e7ed;
  background: rgba(155, 175, 205, .1);
  padding-left: 20px;
}

.dao-table.slat .slat2 td {
  min-height: 18px;
  margin-left: 0;
  margin-top: 9px;
  margin-bottom: 9px;
  white-space: normal;
  display: flex;
  line-height: 18px;
  flex-wrap: wrap;
}

.dao-table.slat td {
  margin-right: 30px;
  color: #595f69;
}

.dao-table.slat span {
  vertical-align: middle;
}

.dao-table.slat .item-minor {
  text-overflow: ellipsis;
  font-size: 13px;
  line-height: 13px;
  height: 16px;
  margin-top: 6px;
  overflow: hidden;
}

.dao-table.slat .item-minor a {
  color: #9ba3af;
  text-decoration: none;
}

.version-dropdown .el-select-dropdown__item {
  height: auto;
  line-height: auto;
}
.version-dropdown li.el-select-dropdown__item p {
  max-width: 500px;
  overflow: hidden;
  text-overflow: ellipsis;
  margin: 0;
}

.version-dropdown li.el-select-dropdown__item p>a {
  font-size: 13px;
  line-height: 13px;
  height: 16px;
  margin-top: 4px;
  color: #9ba3af;
  text-decoration: none;
}
</style>
