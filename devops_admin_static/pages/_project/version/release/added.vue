<template lang="html">
  <portlet :showTitle="false" :showPageBar="false" :showSearchBar="false">
    <el-table :data="apps" ref="apps" @selection-change="selectAppEvent" border>
      <el-table-column type="selection" width="50px" align="center" :selectable="isSelectable"></el-table-column>
      <el-table-column prop="appName" label="应用名称" min-width="20%"></el-table-column>
      <el-table-column label="应用版本" min-width="30%">
        <template slot-scope="scope">
          <el-select v-model="scope.row.appVerId" placeholder="请选择应用版本">
            <el-option v-for="appVersion in scope.row.applicationVersions" :key="appVersion.appVerId"
                       :label="appVersion.versionCode" :value="appVersion.appVerId">
              <el-tooltip effect="light" :content="appVersion.description" placement="right">
                <span>{{appVersion.versionCode}}</span>
              </el-tooltip>
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="配置版本" min-width="30%">
        <template slot-scope="scope">
          <el-select v-model="scope.row.configVerId" placeholder="请选择配置版本" v-if="scope.row.appTypeId !== 400">
            <el-option v-for="configVersion in scope.row.configVersions" :key="configVersion.configVerId"
                       :label="configVersion.versionCode" :value="configVersion.configVerId">
              <el-tooltip effect="light" :content="configVersion.description" placement="right">
                <span>{{configVersion.versionCode}}</span>
              </el-tooltip>
            </el-option>
          </el-select>
          <span v-if="scope.row.appTypeId === 400">无</span>
        </template>
      </el-table-column>
      <el-table-column prop="publishVersionCode" label="当前版本" min-width="20%">
        <template slot-scope="scope">
          <el-button type="text" @click="changeRecord(scope.row)">
            <el-popover
              placement="top-start"
              title="发布版本信息"
              trigger="hover"
              :open-delay="parseInt('200')"
            >
              <span slot=""><pre>{{scope.row.latestVo!=null?scope.row.latestVo.description + '\n【应用版本信息】\n' + scope.row.latestVo.appVersionInfo + '\n【配置版本信息】\n' + scope.row.latestVo.configVersionInfo:''}}</pre></span>
              <span slot="reference">{{scope.row.publishVersionCode}}</span>
            </el-popover>
          </el-button>
        </template>
      </el-table-column>
      <el-table-column v-if='false' prop="appDepVerHisId" label="状态Id" min-width="20%" display></el-table-column>
      <el-table-column prop="productionStatus" label="状态" min-width="30%" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-tooltip v-if="scope.row.productionStatus === 100" content="正在制作中" placement="top-start">
            <div class="warning"></div>
          </el-tooltip>
          <el-tooltip v-if="scope.row.productionStatus === 200" placement="top-start">
            <div slot="content">制作成功
              <el-button type="text" @click='perform(scope.row)'>OK</el-button>
            </div>
            <div style="width: 100%; display: inline-block;">
              <el-progress :show-text="false" :stroke-width="16" :percentage="100" status="success"></el-progress>
            </div>
          </el-tooltip>
          <el-tooltip v-if="scope.row.productionStatus === 300" placement="top-start">
            <div slot="content">制作失败
              <el-button type="text" @click='reassembly(scope.row)'>重新制作</el-button>
              <el-button type="text" @click='perform(scope.row)'>OK</el-button>
            </div>
            <div style="width: 100%; display: inline-block;">
              <el-progress :show-text="false" :stroke-width="16" :percentage="100" status="exception"></el-progress>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <el-footer style="position: relative; height: 35px; text-align: right; padding: 10px 20px;">
      <el-button type="primary" @click="exitIndex">返回</el-button>
      <el-button type="primary" :loading='loading' @click="showDialogVisible" :disabled="selectApps.length > 0 ? false : true">制定</el-button>
    </el-footer>

    <el-dialog title="版本信息" :visible.sync="dialogVisible" show-close>
      <el-main class="codewrapper file-content">
        <codemirror class="code-container fh" ref="codemirror" v-model="code" :options="codeOptions">
        </codemirror>
      </el-main>
      <div slot="footer">
        <el-button type="primary" @click="save" :disabled="this.code.replace(/(^\s*)|(\s*$)/g, '') !== ''?false:true">开始制定</el-button>
      </div>
    </el-dialog>
  </portlet>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      obj: Object
    },
    data() {
      return {
        apps: [],
        code: '',
        selectApps: [],
        appDepVerHis: [],
        selectAll: [],
        handler: '',
        interval: null,
        text: '',
        dialogVisible: false,
        loading: false,
        codeOptions: {
          lineNumbers: false,
          connect: 'align',
          orig: ''
        }
      }
    },
    watch: {
      handler: function(val) {
        if (val === 'ING') {
          this.interval = setInterval(async () => {
            this.pollingMakingStatus()
          }, 1000)
        } else {
          clearInterval(this.interval)
        }
      }
    },
    methods: {
      ...mapActions('action', ['queryPublishEnactApps', 'saveReleaseVersion', 'queryMakingVersion', 'confirmInfo']),
      async queryAppsEvent() {
        let response = await this.queryPublishEnactApps({
          proId: this.obj.proId,
          envId: this.obj.envId
        })
        if (~~response.code === 1) {
          this.apps = response.data
        }
      },
      // 版本对比
      async changeRecord(val) {
        let index = val.deployGitUrl.lastIndexOf('.')
        let url = val.deployGitUrl.substring(0, index)
        window.open(url + '/compare/' + val.latestVo.tagUrl + '...' + val.publishVersionCode)
      },
      async queryAppDepVerHis() {
        let res = await this.queryPublishEnactApps({
          proId: this.obj.proId,
          envId: this.obj.envId
        })
        if (~~res.code === 1) {
          this.apps = res.data
        }
        let response = await this.queryMakingVersion({
          proId: this.obj.proId,
          envId: this.obj.envId
        })
        this.apps.forEach(item => {
          response.data.forEach(items => {
            if (item.appId === items.appId) {
              this.$set(item, 'productionStatus', items.productionStatus)
              this.$set(item, 'appDepVerHisId', items.depVerHisId)
            }
          })
        })
        return response
      },
      async pollingMakingStatus() {
        let response = await this.queryMakingVersion({
          proId: this.obj.proId,
          envId: this.obj.envId
        })
        this.appDepVerHis = response.data
        if (this.appDepVerHis.length > 0) {
          this.changeStatus()
          let num = 0
          this.appDepVerHis.forEach(item => {
            if (item.productionStatus === 100) {
              num++
            }
          })
          if (num > 0) {
            this.handler = 'ING'
          } else {
            this.handler = ''
          }
        }
      },
      changeStatus() {
        this.apps.forEach(item => {
          this.appDepVerHis.forEach(items => {
            if (item.appId === items.appId) {
              this.$set(item, 'productionStatus', items.productionStatus)
              this.$set(item, 'appDepVerHisId', items.depVerHisId)
            }
          })
        })
      },
      isSelectable(row) {
        return !(row.productionStatus && row.productionStatus !== 0)
      },
      selectAppEvent(selection) {
        this.selectApps = selection
      },
      exitIndex() {
        this.$emit('reset')
      },
      show() {
        return !(this.$refs.info && this.$refs.info.value)
      },
      reassembly(row) {
        this.appDepVerHis.forEach(item => {
          if (item.appId === row.appId) {
            row.description = item.description
          }
        })
        this.confirmInfo(row.appDepVerHisId)
        this.saveReleaseVersion(row)
        this.$refs.apps.toggleRowSelection(row, false)
        this.handler = 'ING'
      },
      async perform(row) {
        let response = await this.confirmInfo(row.appDepVerHisId)
        if (~~response.code === 1) {
          this.pollingMakingStatus()
        }
      },
      save() {
        if (this.selectApps && this.selectApps.length > 0) {
          this.selectApps.forEach(async item => {
            item.description = this.code
            this.saveReleaseVersion(item)
          })
          this.selectApps.forEach(item => {
            this.$refs.apps.toggleRowSelection(item, false)
          })
          this.handler = 'ING'
          this.dialogVisible = false
          this.code = ''
        } else {
          this.$msg.warning('请选择需要制定版本的应用')
        }
      },
      showDialogVisible() {
        let selectValidates = true
        let length = this.selectApps.length
        for (let index = 0; index < length; index++) {
          let item = this.selectApps[index]
          if (!item.appVerId) {
            selectValidates = false
            this.$msg.error('【' + item.appName + '】应用版本不能为空!')
            break
          }
          if (!item.configVerId && item.appTypeId !== 400) {
            selectValidates = false
            this.$msg.error('【' + item.appName + '】配置版本不能为空!')
            break
          }
        }
        this.dialogVisible = selectValidates
      }
    },
    async created() {
      this.queryAppsEvent()
      this.queryAppDepVerHis()
      this.pollingMakingStatus()
    },
    beforeDestroy() {
      clearInterval(this.interval)
    }
  }
</script>
<style>
  .warning {
    background-color: #DBDCDD;
    border: 1px solid #DBDCDD;
    border-radius: 10px;
    background-size: 3em 3em;
    background-image: linear-gradient(-45deg, transparent 0em, transparent 0.8em, #F7F9FB 0.9em, #F7F9FB 2.1em, transparent 2.1em, transparent 2.9em, #F7F9FB 3.1em);
    -webkit-animation: warning-animation 750ms infinite linear;
    -moz-animation: warning-animation 750ms infinite linear;
    animation: warning-animation 750ms infinite linear;
  }

  @-webkit-keyframes warning-animation {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: 3em 0;
    }
  }

  @-moz-keyframes warning-animation {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: 3em 0;
    }
  }

  @keyframes warning-animation {
    0% {
      background-position: 0 0;
    }
    100% {
      background-position: 3em 0;
    }
  }

  .file-content {
    height: 500px;
    overflow: auto;
  }

  .warning:before {
    content: '';
    width: 300px;
    border-radius: 10px;
  }

  .warning {
    height: 16px;
  }
</style>
