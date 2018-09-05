<template>
<div>
  <portlet :showTitleBar="false">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item label="应用名称">
          <el-input v-model="searchAppName" clearable placeholder="请输入应用名称" @keyup.enter.native="searchEvent"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchEvent()">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template slot="title-bar-tools">
      <el-button type="plain" @click="createPublishOrder()" :disabled="selectedApps.length===0"><i class="glyphicon glyphicon-plus"></i> 创建发布工单</el-button>
    </template>
    <el-table :data="apps" row-key='appEnvId' ref="appListTable" show-overflow-tooltip @select="appSelectEvent" @select-all="appSelectAllEvent" @selection-change="appChangeEvent" @expand-change="changeAppId" :expand-row-keys="expands">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table :data="props.row.orderInfoVoList" :ref="'appDetailTable#'+props.row.appId" row-key='detailId'  :select-on-indeterminate="false" show-overflow-tooltip @selection-change="instanceDetailChangeEvent($event, props.row)">
            <el-table-column type="selection" width="50" align="center" :reserve-selection="true" > </el-table-column>
            <el-table-column label="服务器名称" prop="serverName" align="center" min-width="30px" show-overflow-tooltip></el-table-column>
            <el-table-column label="当前版本" align="center" min-width="35px" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-tooltip class="item" effect="dark" placement="right-start">
                  <template slot="content">
                    {{scope.row.versionDesc}}
                  </template>
                  <span>{{scope.row.versionCode}}</span>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="最近发布状态" align="center" min-width="100px" show-overflow-tooltip>
              <template slot-scope="scope">
                <div class="progress inline-label-progress" :class="{'progress-striped': scope.row.publishStatus>0  && scope.row.publishStatus<500}" >
                  <div class="progress-bar " :class="getStatusBarClass(50, scope.row.publishStatus)" style="width: 20%">目录检测</div>
                  <div class="progress-bar " :class="getStatusBarClass(100, scope.row.publishStatus)" style="width: 20%">代码检出</div>
                  <div class="progress-bar " :class="getStatusBarClass(150, scope.row.publishStatus)" style="width: 20%">版本检测</div>
                  <div class="progress-bar " :class="getStatusBarClass(200, scope.row.publishStatus)" style="width: 20%">服务启动</div>
                  <div class="progress-bar " :class="getStatusBarClass(250, scope.row.publishStatus)" style="width: 20%">服务检测</div>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="运行状态" align="center" min-width="30px" show-overflow-tooltip>
              <template slot-scope="scope">
                <div class="progress inline-label-progress" >
                    <div v-if="scope.row.runStatus===1" class="progress-bar progress-bar-info" style="width: 100%">发布中</div>
                    <div v-else-if="scope.row.runStatus===-1" class="progress-bar progress-bar-default" style="width: 100%">已停止</div>
                    <div v-else class="progress-bar progress-bar-success" style="width: 100%">运行中</div>
                </div>
              </template>
            </el-table-column>
            <el-table-column min-width="50px" show-overflow-tooltip>
              <template slot-scope="scope">
                  <el-dropdown split-button plain type="text" placement="bottom-start" trigger="click" @click='getLogMustInfo(props.row, scope.row)'>
                      运行日志
                      <el-dropdown-menu class="cst-dropdown" slot="dropdown">
                        <el-dropdown-item @click.native="showOrderDetailLogs(scope.row)">发布日志</el-dropdown-item>
                        <el-dropdown-item @click.native="logsList(props.row.appEnvId,scope.row.detailId)">日志下载</el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                    <!-- <el-button v-auth="'detail'" type="text" @click="detail(scope.row)">详情</el-button> -->
                </template>
            </el-table-column>
          </el-table>
    </template>
    </el-table-column>
    <el-table-column type="selection" width="50" align="center" :reserve-selection="true"> </el-table-column>
    <el-table-column label="应用名称" min-width="50px" align="center" prop="appName"></el-table-column>
    <el-table-column label="当前版本" min-width="50px" show-overflow-tooltip>
        <template slot-scope="scope">
          <div v-for="item in scope.row.versions">
            <el-tooltip class="item" effect="dark" placement="right-start">
              <template slot="content">
                {{item.versionDesc}}
              </template>
              <span>{{item.versionCode}}&nbsp;<span class="font-grey-salsa">{{item.num}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></span>
            </el-tooltip>
          </div>
        </template>
      </el-table-column>
    <el-table-column label="发布状态" min-width="50px" show-overflow-tooltip>
      <template slot-scope="scope">
          <div><i class="glyphicon glyphicon-stop font-green-jungle"></i><span class="font-grey-salsa">发布成功&nbsp;{{scope.row.publishSuccessNum}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></div>
          <div><i class="glyphicon glyphicon-stop font-blue-steel"></i><span class="font-grey-salsa">发布中&nbsp;{{scope.row.publishingNum}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></div>
          <div><i class="glyphicon glyphicon-stop font-red-thunderbird"></i><span class="font-grey-salsa">发布失败&nbsp;{{scope.row.publishErrorNum}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></div>
      </template>
    </el-table-column>
    <el-table-column label="运行状态" min-width="50px" show-overflow-tooltip>
      <template slot-scope="scope">
          <div><i class="glyphicon glyphicon-stop font-green-jungle"></i><span class="font-grey-salsa">运行中&nbsp;{{scope.row.runningNum}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></div>
          <div><i class="glyphicon glyphicon-stop font-grey-salt"></i><span class="font-grey-salsa">已停止&nbsp;{{scope.row.stopingNum}}/{{scope.row.orderInfoVoList&&scope.row.orderInfoVoList.length||0}}</span></div>
      </template>
    </el-table-column>
    <el-table-column min-width="50px" show-overflow-tooltip>
        <template slot-scope="scope">
            <el-dropdown split-button plain placement="bottom-start" trigger="click">
                <a href="javascript:;" @click="showRunLog(scope.row)">运行日志</a>
                <el-dropdown-menu class="cst-dropdown" slot="dropdown" >
                  <!-- <el-dropdown-item :disabled="scope.row.runStatus===1 || !scope.row.selectedDetails || scope.row.selectedDetails.length===0" @click.native="createPublishOrder(scope.row)">发布</el-dropdown-item> -->
                  <el-dropdown-item :disabled="isPublishing(scope.row)" @click.native="rebootInstance(scope.row, 1)">并行重启</el-dropdown-item>
                  <el-dropdown-item :disabled="isPublishing(scope.row)" @click.native="rebootInstance(scope.row, 0)">顺序重启</el-dropdown-item>
                  <el-dropdown-item :disabled="scope.row.appTypeId === 400" @click.native="stopInstance(scope.row, 1)">停止</el-dropdown-item>
                  <el-dropdown-item divided @click.native="logsList(scope.row.appEnvId)">日志下载</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <!-- <el-button v-auth="'detail'" type="text" @click="detail(scope.row)">详情</el-button> -->
          </template>
      </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
    </template>
  </portlet>
</div>
</template>
<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'
export default {
  data: function () {
    return {
      interval: null,
      searchAppName: '',
      apps: [],
      selectedApps: [],
      page: { pageNum: 1, pageSize: 30 },
      expands: [] // 展开行的数组,这里为appEnvId数组
    }
  },
  computed: {
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function () {
      return this.currentProject.proId
    },
    envId: function () {
      return this.currentEnvironment.envId
    }
  },
  methods: {
    ...mapActions('action', ['queryAppInstance', 'getAppEnvServer', 'restartApp', 'stopApp']),
    ...mapMutations('project', ['SET_ORDER_INFO']),
    getStatusBarClass(statusCode, currentCode) {
      if (Math.abs(currentCode) < statusCode) {
        return 'progress-bar-wait'
      } else if (currentCode === -statusCode) {
        return 'progress-bar-danger'
      } else if (currentCode === statusCode) {
        return 'progress-bar-info active'
      } else {
        return 'progress-bar-success'
      }
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.searchAppInstance()
    },
    setPageNum: function (num) {
      this.page.pageNum = num
      this.searchAppInstance()
    },
    searchEvent() {
      this.page.pageNum = 1
      this.searchAppInstance()
    },
    async searchAppInstance() {
      let response = await this.queryAppInstance({ 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum, 'envId': this.envId, 'proId': this.proId, 'appName': this.searchAppName })
      if (~~response.code === 1) {
        this.apps = response.data.results.map(item => {
          this.apps.forEach(app => {
            if (app.appEnvId === item.appEnvId) {
              item.selectedDetails = app.selectedDetails
            }
          })
          return item
        })
        this.page = response.data.meta
        this.expands = JSON.parse(JSON.stringify(this.expands))
      }
    },
    showOrderDetailLogs(row) {
      window.open(`/${this.currentProject.proCode}/order/publish_log?id=${row.detailId}`)
    },
    instanceDetailChangeEvent(selections, row) {
      row.selectedDetails = selections.map(item => {
        item.appEnvId = row.appEnvId
        item.serverId = item.serverId
        return item
      })
      this.$refs['appListTable'].toggleRowSelection(row, selections.length > 0)
    },
    changeAppId(row, expandRows) {
      this.expands = expandRows.map(item => {
        return item.appEnvId
      })
    },
    appChangeEvent(selections, row) {
      this.selectedApps = selections
    },
    appSelectEvent(selection, row) {
      if (selection.indexOf(row) > -1) {
        this.$refs['appListTable'].toggleRowExpansion(row, true)
      }
      this.$nextTick(() => {
        let tb = this.$refs['appDetailTable#' + row.appId]
        if (selection.indexOf(row) > -1) {
          if (row.orderInfoVoList) {
            row.orderInfoVoList.forEach(detail => {
              tb.toggleRowSelection(detail, true)
            })
          }
        } else {
          if (tb) {
            tb.clearSelection()
          }
        }
      })
    },
    appSelectAllEvent(selection) {
      if (selection.length > 0) {
        this.apps.forEach(row => {
          this.$refs['appListTable'].toggleRowExpansion(row, true)
          this.$nextTick(() => {
            let tb = this.$refs['appDetailTable#' + row.appId]
            if (row.orderInfoVoList) {
              row.orderInfoVoList.forEach(detail => {
                tb.toggleRowSelection(detail, true)
              })
            }
          })
        })
      } else {
        this.$nextTick(() => {
          this.apps.forEach(row => {
            let tb = this.$refs['appDetailTable#' + row.appId]
            tb.clearSelection()
            this.$refs['appListTable'].toggleRowExpansion(row, false)
          })
        })
      }
    },
    createPublishOrder(row) {
      let orderinfoDetails = []
      if (row) {
        orderinfoDetails = row.selectedDetails
      } else {
        this.selectedApps.forEach(item => {
          orderinfoDetails = orderinfoDetails.concat(item.selectedDetails)
        })
      }
      let orderInfo = { 'deployOrderInfos': orderinfoDetails }
      this.SET_ORDER_INFO(orderInfo)
      this.$router.push(`${this.currentProject.proCode}/order/add`)
    },
    async showRunLog(row) {
      var serverIds = []
      row.orderInfoVoList.forEach(item => {
        serverIds.push(item.serverId)
      })
      if (serverIds.length > 0) {
        window.open(`/common/logs?app_id=${row.appId}&server_ids=${serverIds}`)
      } else {
        this.$msg.error('日志查询错误：没有服务器正运行' + row.appName + '应用')
      }
    },
    getLogMustInfo(appInfo, serverInfo) {
      var serverId = []
      serverId.push(serverInfo.serverId)
      let logInfo = { 'appId': appInfo.appId, 'orderInfoVoList': [{ 'serverId': serverId }] }
      this.showRunLog(logInfo)
    },
    isPublishing(row) {
      if (row.appTypeId === 400) {
        return true
      }
      let isPublishing = false
      if (row.selectedDetails && row.selectedDetails.length > 0) {
        row.selectedDetails.forEach(item => {
          if (item.runStatus === 1) {
            isPublishing = true
          }
        })
      }
      return isPublishing
    },
    async rebootInstance(row, deployType) {
      if (this.isPublishing(row)) {
        this.$msg.error('应用正在发布中！')
      }
      let appEnvId = row.appEnvId
      let serverIds = []
      this.$nextTick(async () => {
        if (row.selectedDetails && row.selectedDetails.length > 0) {
          serverIds = row.selectedDetails.map(item => {
            return item.serverId
          })
        } else {
          this.$msg.error('未选择要重启的应用！')
          return
        }
        this.$confirm('是否确定重启应用?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let response = await this.restartApp({ appEnvId, serverIds, deployType })
          if (~~response.code === 1) {
            window.open(`/common/logs?app_id=${row.appId}&server_ids=${serverIds}`)
          } else {
            this.$msg.error(response.msg)
          }
        })
      })
    },
    stopInstance(row, deployType) {
      let appEnvId = row.appEnvId
      let serverIds = []
      this.$nextTick(async () => {
        if (row.selectedDetails && row.selectedDetails.length > 0) {
          serverIds = row.selectedDetails.map(item => {
            return item.serverId
          })
        } else {
          this.$msg.error('未选择要停止的应用！')
          return
        }
        this.$confirm('是否确定停止应用?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let response = await this.stopApp({ appEnvId, serverIds, deployType })
          if (~~response.code === 1) {
            this.$msg.success('正在停止应用！')
          } else {
            this.$msg.error(response.msg)
          }
        })
      })
    },
    logsList(instanceId, detailId) { // 日志查询
      window.open(`/common/logdown?instanceId=${instanceId}${detailId ? ('&detailId=' + detailId) : ''}`)
    }
  },
  mounted() {
    this.searchEvent()
    this.inverval = setInterval(() => {
      this.searchAppInstance()
    }, 5000)
  },
  destroyed() {
    clearInterval(this.inverval)
  }
}
</script>
