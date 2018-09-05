<template>
  <div>
    <portlet title="发布版本" v-if="handler === ''">
      <template slot="search-bar">
        <el-form :inline="true" v-nosubmit>
          <el-form-item label="应用名称">
            <el-input v-model="appName" clearable placeholder="请输入应用名称"
                      @keyup.enter.native="searchReleaseVersionEvent"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button v-auth="'search'" type="primary" @click="searchReleaseVersionEvent">查询</el-button>
            <el-button v-auth="'added'" type="primary" @click="added()">新增</el-button>
          </el-form-item>
        </el-form>
      </template>
      <el-table :data="versions" border v-if="handler === ''">
        <el-table-column prop="envName" label="环境名称" min-width="100px"></el-table-column>
        <el-table-column prop="proName" label="项目名称" min-width="100px"></el-table-column>
        <el-table-column prop="appName" label="应用名称" min-width="100px"></el-table-column>
        <el-table-column prop="versionCode" label="发布版本编码" min-width="80px;">
          <template slot-scope="scope">
            <el-button type="text" @click="changeRecord('release', scope.row)">
              <el-popover
                placement="top-start"
                title="发布版本信息"
                trigger="hover"
                :open-delay="parseInt('200')"
              >
                <span slot=""><pre>{{scope.row.description + '\n【应用版本信息】\n' + scope.row.appVersionInfo + '\n【配置版本信息】\n' + scope.row.configVersionInfo}}</pre></span>
                <span slot="reference">{{scope.row.versionCode}}</span>
              </el-popover>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column prop="appVersionCode" label="应用版本编码" min-width="80px;">
          <template slot-scope="scope">
            <el-button type="text" @click="changeRecord('app', scope.row)">
              <el-popover
                placement="top-start"
                title="应用版本信息"
                trigger="hover"
                :open-delay="parseInt('200')"
              >
                <span slot=""><pre>{{scope.row.appVersionInfo}}</pre></span>
                <span slot="reference">{{scope.row.appVersionCode}}</span>
              </el-popover>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column prop="configVersionCode" label="配置版本编码" min-width="80px;">
          <template slot-scope="scope">
            <el-button type="text" @click="changeRecord('config', scope.row)">
              <el-popover
                placement="top-start"
                title="配置版本信息"
                trigger="hover"
                :open-delay="parseInt('200')"
              >
                <span slot=""><pre>{{scope.row.configVersionInfo}}</pre></span>
                <span slot="reference">{{scope.row.configVersionCode}}</span>
              </el-popover>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" min-width="100px"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间" min-width="100px"></el-table-column>
        <el-table-column prop="operateUserName" label="操作员" min-width="70px"></el-table-column>
        <el-table-column prop="used" label="使用情况" min-width="50px">
          <template slot-scope="scope">
            <span v-if="scope.row.used===0" style="color:green;">未使用</span>
            <span v-else-if="scope.row.used===1" style="color:red;">已使用</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="120px">
          <template slot-scope="scope">
            <el-button type="text" @click="versionInfo(scope.row)">版本信息</el-button>
            <el-button type="text" @click="changeRecord('release', scope.row)">更改记录</el-button>
          </template>
        </el-table-column>
      </el-table>

      <template slot="footer">
        <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize"
                    @pagenum="setPageNum"></pagination>
      </template>
    </portlet>
    <added-form v-if="handler === 'ADDED'" :obj="selectRow" @reset="resetHandler"></added-form>
    <info-form :obj="selectRow" :handler="handler" @reset="resetHandler"></info-form>
    <change-form :obj="selectRow" :handler="handler" @reset="resetHandler"></change-form>
  </div>

</template>
<script>
  import {mapActions, mapGetters} from 'vuex'
  import infoForm from './info'
  import addedForm from './added.vue'
  import changeForm from './change'

  export default {
    components: {
      infoForm,
      addedForm,
      changeForm
    },
    data() {
      return {
        selectRow: {},
        handler: '',
        versions: [],
        appName: null,
        versionNotUsedVo: {},
        page: {pageNum: 1, pageSize: 30}
      }
    },
    computed: {
      ...mapGetters('project', ['currentEnvironment', 'currentProject']),
      proId: function() {
        return this.currentProject.proId
      },
      envId: function() {
        return this.currentEnvironment.envId
      }
    },
    methods: {
      ...mapActions('action', ['searchReleaseVersion']),
      reloadPage() {
        this.page.pageNum = 1
        this.resetHandler()
        this.searchReleaseVersionEvent()
      },
      resetHandler() {
        this.handler = ''
      },
      versionInfo(val) {
        this.handler = 'INFO'
        this.selectRow = val
      },
      added() {
        this.handler = 'ADDED'
        this.selectRow = {'proId': this.proId, 'envId': this.envId}
      },
      async changeRecord(type, val) {
        let urls
        if (type === 'release') {
          let index0 = val.deployAppGitUrl.lastIndexOf('.')
          let url0 = val.deployAppGitUrl.substring(0, index0)
          urls = url0 + '/compare/' + val.tagUrl + '...' + val.versionCode
        } else if (type === 'app') {
          let index1 = val.appGitUrl.lastIndexOf('.')
          let url1 = val.appGitUrl.substring(0, index1)
          urls = url1 + '/compare/' + val.appTagUrl + '...' + val.appVersionCode
        } else if (type === 'config') {
          let index2 = val.configGitUrl.lastIndexOf('.')
          let url2 = val.configGitUrl.substring(0, index2)
          urls = url2 + '/compare/' + val.configTagUrl + '...' + val.configVersionCode
        }
        window.open(urls)
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.searchReleaseVersionEvent()
      },
      setPageNum: function(num) {
        this.page.pageNum = num
        this.searchReleaseVersionEvent()
      },
      async searchReleaseVersionEvent() {
        let response = await this.searchReleaseVersion(
          Object.assign(this.page, {proId: this.proId, envId: this.envId, appName: this.appName}))
        if (~~response.code === 1) {
          this.versions = response.data.results
          this.page = response.data.meta
          this.versionNotUsedVo = response.data.versionNotUsedVo
        }
      }
    },
    async created() {
      this.searchReleaseVersionEvent()
    }
  }
</script>
