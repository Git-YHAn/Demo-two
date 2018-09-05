<template>
  <portlet title="修改配置" :showSearchBar="false">
    <el-tabs  :value="currentAppId" @tab-click="appChangeEvent">
      <el-tab-pane v-for="item in apps" :key="item.appId" :label="item.appName" :name="item.appId|str" class="fh">
        <el-select v-model="item.currentBranch" @change="changeBranchEvent" class="fw" placeholder="请选择分支">
          <el-option v-for="branch in item.branches" :key="branch" :value="branch">
            <span style="float: left">{{ branch }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px" @click.prevent="dropBranchEvent(branch)">删除</span>
          </el-option>
          <el-option value="">新建分支</el-option>
        </el-select>
      </el-tab-pane>
    </el-tabs>
    <el-container class="file-view" style="height:calc(100vh - 350px)" v-show="showFiles">
      <el-aside class="file-menu" width="auto" style="min-width: 300px" v-loading="files.length==0">
        <el-menu ref="fileMenu" @select="fileChangeEvent" :default-active="currentFilePath">
          <el-menu-item v-for="item in files" :key="item.fileUrl" :index="item.fileUrl">
            <span slot="title">{{item.configFileName}}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main class="codewrapper file-content" v-loading="codeLoading">
        <no-ssr placeholder="Loading...">
          <codemirror class="code-container fh" ref="codemirror" :merge="isCompare" v-model="code" :options="codeOptions" @changes="isChange = true">
          </codemirror>
        </no-ssr>
      </el-main>
    </el-container>
    <div slot="footer" style="text-align:right;padding-top:10px;margin-top: 15px;margin-bottom: -15px;" v-show="showFiles">
      <el-button type="primary" @click="showFileDiffEvent">变更记录</el-button>
      <el-button type="primary" @click="compareEvent">{{isCompare?'取消对比':'对比'}}</el-button>
      <el-button type="primary" @click="addFileEvent">新增</el-button>
      <el-button type="primary" :disabled="!isChange" @click="saveFileEvent">保存</el-button>
      <el-button type="danger" :disabled="!currentFilePath" @click="deleteFileEvent">删除</el-button>
      <el-button type="primary" :disabled="!isSave" @click="pushBranchEvent">提交审核</el-button>
    </div>
    <diff-form :handler="handler" :obj="currentBranch" @reset="resetHandler"></diff-form>
    <add-form :handler="handler" :obj="currentBranch" @reset="resetHandler" @save="reloadBranch" @showPushBranchButton="showPushBranchButton"></add-form>
    <commit-form :handler="handler" :obj="currentBranch" @reset="resetHandler" @save="commitBranch"></commit-form>
  </portlet>
</template>
<script>
import {
  Base64
} from 'js-base64'
import {
  mapActions,
  mapGetters
} from 'vuex'
import diffForm from './diff.vue'
import addForm from './add.vue'
import commitForm from './commit.vue'

const modeMap = {
  'js': 'text/javascript',
  'xml': 'application/xml',
  'properties': 'text/x-properties'
}

export default {
  components: {
    diffForm,
    addForm,
    commitForm
  },
  data() {
    return {
      apps: [],
      files: [],
      currentAppId: null,
      currentFilePath: '',
      code: ``,
      isCompare: false,
      isChange: false,
      isSave: false,
      codeLoading: false,
      handler: '',
      codeOptions: {
        connect: 'align',
        orig: ''
      }
    }
  },
  filters: {
    str: function(value) {
      return value.toString()
    }
  },
  computed: {
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function() {
      return this.currentProject.proId
    },
    envId: function() {
      return this.currentEnvironment.envId
    },
    currentApp: function() {
      return this.apps.filter((item) => {
        return item.appId === parseInt(this.currentAppId)
      })[0]
    },
    showFiles: function() {
      return this.currentApp && this.currentApp.currentBranch
    },
    currentBranch: function() {
      return {
        'envId': this.envId,
        'proId': this.proId,
        'appId': this.currentAppId,
        'branchName': this.currentApp && this.currentApp.currentBranch
      }
    }
  },
  methods: {
    ...mapActions('action', [
      'queryDeployApps', 'queryAppBranches', 'checkNewBranch',
      'queryBranchFiles', 'getFileContent', 'dropBranch', 'getOriginFileContent',
      'saveFileContent', 'deleteFile', 'pushBranch'
    ]),
    resetHandler() {
      this.handler = ''
    },
    addFileEvent() {
      this.handler = 'ADD'
    },
    reloadBranch() {
      this.resetHandler()
      this.resetBranch()
      this.loadBranchFiles(this.currentApp.currentBranch)
    },
    commitBranch() {
      this.resetHandler()
      this.resetBranch()
      if (this.currentApp && this.currentApp.currentBranch) {
        this.currentApp.currentBranch = ''
      }
      this.queryAppBranchesEvent()
    },
    pushBranchEvent() {
      if (this.isChange) {
        this.saveFileEvent()
      }
      this.handler = 'COMMIT'
    },
    async appChangeEvent(tab) {
      this.resetApp()
      this.currentAppId = tab.name
      await this.queryAppBranchesEvent()
      if (this.currentApp && this.currentApp.currentBranch) {
        this.changeBranchEvent(this.currentApp.currentBranch)
      }
    },
    async changeBranchEvent(val) {
      this.resetBranch()
      if (val === '') {
        await this.checkNewBranchEvent()
        this.loadBranchFiles(this.currentApp.currentBranch)
      } else if (val) {
        this.loadBranchFiles(val)
      }
    },
    async loadBranchFiles(branchName) {
      let response = await this.queryBranchFiles({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId,
        branchName
      })
      if (~~response.code === 1) {
        this.files = response.data
      }
    },
    async fileChangeEvent(path) {
      let confirm = await this.saveFilePromotion()
      if (confirm) {
        this.resetFile()
        this.currentFilePath = path
        let response = await this.getFileContent(Object.assign(this.currentBranch, {
          filePath: path
        }))
        if (~~response.code === 1) {
          let suffix = path.split('.').splice(-1)[0]
          if (suffix) {
            this.$set(this.codeOptions, 'mode', modeMap[suffix] || 'application/xml')
          }
          this.code = Base64.decode(response.data)
          this.$nextTick(() => {
            this.isChange = false
          })
        }
      }
    },
    async checkNewBranchEvent() {
      let response = await this.checkNewBranch({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId
      })
      if (~~response.code === 1) {
        this.$msg.success('新建分支成功！')
        this.resetBranch()
        await this.queryAppBranchesEvent()
        this.$set(this.currentApp, 'currentBranch', response.data)
      } else {
        this.$msg.error('新建分支失败：', response.msg)
      }
    },
    async dropBranchEvent(branchName) {
      let response = await this.dropBranch({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId,
        branchName
      })
      if (~~response.code === 1) {
        this.currentApp.currentBranch = null
        this.resetBranch()
        this.queryAppBranchesEvent()
        this.$msg.success(`删除分支${branchName}成功!`)
      } else {
        this.$msg.error(`删除分支${branchName}失败!`)
      }
    },
    async deleteFileEvent() {
      let result = await this.$confirm(`确认删除${this.currentFilePath.split('\\').splice(-1)}?`, {
        type: 'warning'
      }).then(async () => {
        return true
      }).catch(() => {
        return false
      })
      if (result) {
        let response = await this.deleteFile({
          'appId': this.currentAppId,
          'proId': this.proId,
          'envId': this.envId,
          'filePath': this.currentFilePath,
          'branchName': this.currentApp.currentBranch
        })
        if (~~response.code === 1) {
          this.reloadBranch()
          this.$msg.success('删除文件成功！')
          this.isChange = true
          this.isSave = true
        } else {
          this.$msg.error('删除文件失败：', response.msg)
        }
      }
    },
    async saveFilePromotion() {
      let result = true
      if (this.isChange) {
        result = await this.$confirm('当前文件有变动，是否放弃修改?', {
          type: 'warning'
        }).then(async () => {
          return true
        }).catch(() => {
          this.$refs.fileMenu.activeIndex = this.currentFilePath
          return false
        })
      }
      return result
    },
    async saveFileEvent() {
      let response = await this.saveFileContent({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId,
        'filePath': this.currentFilePath,
        'branchName': this.currentApp.currentBranch,
        'fileContent': Base64.encode(this.code)
      })
      if (~~response.code === 1) {
        this.$msg.success('保存成功！')
        this.isChange = false
        this.isSave = true
        return true
      } else {
        this.$msg.success('保存失败：', response.msg)
        return false
      }
    },
    // async pushBranchEvent() {
    //
    //   this.$prompt('请输入提交信息', '提交审核', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消'
    //   }).then(async ({
    //     value
    //   }) => {
    //     // let response = await this.pushBranch({ 'appId': this.currentAppId, 'proId': this.proId, 'envId': this.envId, 'branchName': this.currentApp.currentBranch, 'commitMessage': value })
    //     // if (~~response.code === 1) {
    //     //   this.resetBranch()
    //     //   if (this.currentApp && this.currentApp.currentBranch) {
    //     //     this.currentApp.currentBranch = ''
    //     //   }
    //     //   this.queryAppBranchesEvent()
    //     //   this.$msg.success('提交审核成功!')
    //     // } else {
    //     //   this.$msg.error('提交审核失败:', response.msg)
    //     // }
    //   }).catch(() => {
    //     this.$msg.warning('已取消提交')
    //   })
    //   // let response = await this.pushBranch({ 'appId': this.currentAppId, 'proId': this.proId, 'envId': this.envId, 'branchName': this.currentApp.currentBranch })
    //   // if (~~response.code === 1) {
    //   //   this.resetBranch()
    //   //   if (this.currentApp && this.currentApp.currentBranch) {
    //   //     this.currentApp.currentBranch = ''
    //   //   }
    //   //   this.queryAppBranchesEvent()
    //   //   this.$msg.success('提交审核成功！')
    //   // } else {
    //   //   this.$msg.error('提交审核失败:', response.msg)
    //   // }
    // },
    showFileDiffEvent() {
      this.handler = 'DIFF'
    },
    resetApp() {
      this.currentAppId = null
      this.resetBranch()
    },
    resetBranch() {
      this.isSave = false
      this.files = []
      this.resetFile()
    },
    resetFile() {
      this.code = ''
      this.isCompare = false
      this.currentFilePath = ''
      this.$nextTick(() => {
        this.isChange = false
      })
    },
    async queryAppBranchesEvent() {
      let response = await this.queryAppBranches({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId
      })
      if (~~response.code === 1 && this.currentApp) {
        this.$set(this.currentApp, 'branches', response.data)
      }
    },
    async compareEvent() {
      await this.loadOriginFileContent()
      this.isCompare = !this.isCompare
    },
    async loadOriginFileContent() {
      let response = await this.getOriginFileContent({
        'appId': this.currentAppId,
        'proId': this.proId,
        'envId': this.envId,
        'filePath': this.currentFilePath,
        'branchName': this.currentApp.currentBranch
      })
      if (~~response.code === 1) {
        this.$set(this.codeOptions, 'orig', Base64.decode(response.data))
      }
    },
    showPushBranchButton() {
      this.isSave = true
    }
  },
  async mounted() {},
  async created() {
    if (Object.keys(this.$route.query).length > 0) {
      this.currentAppId = this.$route.query.currentAppId
    }
    let response = await this.queryDeployApps({
      'proId': this.proId,
      'envId': this.envId
    })
    if (~~response.code === 1) {
      this.apps = response.data
    }
  }
}
</script>
<style scoped="scoped">
.file-view {
  /* display: table; */
  height: 100%;
}

.file-menu {
  display: table-cell;
  height: 100%;
  max-width: 500px;
  overflow: auto;
}

.file-content {
  display: table-cell;
  height: 100%;
  overflow: auto;
}

.panel {
  height: calc(100% - 120px);
  margin-top: 10px;
}
</style>
