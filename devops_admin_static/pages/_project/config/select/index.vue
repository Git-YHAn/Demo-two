<template>
  <portlet title="查看配置" :showSearchBar="false">
    <el-tabs :value="currentAppId" @tab-click="appChangeEvent">
      <el-tab-pane v-for="item in apps" :key="item.appId" :label="item.appName" :name="item.appId|str" class="fh">
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
      <el-main class="codewrapper file-content">
        <no-ssr placeholder="Loading...">
          <codemirror class="code-container fh" ref="codemirror" :merge="isCompare" v-model="code" :options="codeOptions" @changes="isChange = true">
          </codemirror>
        </no-ssr>
      </el-main>
    </el-container>

    <div slot="footer" v-show="showFiles" style="margin-top: 15px;margin-bottom: -15px;">
      <el-button class="el-icon-edit" style="font-size:22px" @click='refreshto'> 点击修改{{this.appName}}的配置</el-button>
    </div>
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
const modeMap = {
  'js': 'text/javascript',
  'xml': 'application/xml',
  'properties': 'text/x-properties'
}

export default {
  data() {
    return {
      apps: [],
      files: [],
      appName: '',
      currentAppId: null,
      currentFilePath: '',
      code: ``,
      isCompare: false,
      isChange: false,
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
    envCode: function() {
      return this.currentEnvironment.envCode
    },
    currentApp: function() {
      return this.apps.filter((item) => {
        return item.appId === parseInt(this.currentAppId)
      })[0]
    },
    showFiles: function() {
      return this.currentApp
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
    ...mapActions('action', ['queryDeployApps', 'queryBranchFiles', 'getFileContent', 'getOriginFileContent']),
    async appChangeEvent(tab) {
      this.resetApp()
      this.currentAppId = tab.name
      this.appName = tab.label
      if (this.currentApp) {
        this.loadBranchFiles('master')
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
      this.resetFile()
      this.currentFilePath = path
      let response = await this.getFileContent(Object.assign(this.currentBranch, {
        filePath: path
      }))
      if (~~response.code === 1) {
        let suffix = path.split('.').splice(-1)[0]
        if (suffix) {
          this.$set(this.codeOptions, 'mode', modeMap[suffix] || 'application/xml')
          this.$set(this.codeOptions, 'readOnly', true)
        }
        this.code = Base64.decode(response.data)
        this.$nextTick(() => {
          this.isChange = false
        })
      }
    },
    refreshto() {
      window.location.href = './edit?env_code=' + this.envCode + '&currentAppId=' + this.currentAppId
    },
    resetApp() {
      this.currentAppId = null
      this.resetBranch()
    },
    resetBranch() {
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
    }
  },
  async mounted() {},
  async created() {
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
</style>
