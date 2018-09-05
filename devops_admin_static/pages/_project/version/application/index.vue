<template>
  <portlet title="应用版本">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item label="应用名称">
          <el-input v-model="appName" clearable placeholder="请输入应用名称"
                    @keyup.enter.native="searchAppVersionEvent"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button v-auth="'search'" type="primary" @click="searchAppVersionEvent()">查询</el-button>
          <el-button v-auth="'add'" type="primary" @click="add()">新增</el-button>
        </el-form-item>
      </el-form>
    </template>
    <el-table :data="appVersions" border>
      <el-table-column prop="proName" label="项目名称" min-width="120px"></el-table-column>
      <el-table-column prop="appName" label="应用名称" min-width="120px"></el-table-column>
      <el-table-column prop="versionCode" label="应用版本编码" min-width="80px;">
        <template slot-scope="scope">
          <el-button type="text" @click="changeRecord(scope.row)">
            <el-popover
              placement="top-start"
              title="应用版本信息"
              trigger="hover"
              :open-delay="parseInt('200')"
            >
              <span slot=""><pre>{{scope.row.description}}</pre></span>
              <span slot="reference">{{scope.row.versionCode}}</span>
            </el-popover>
          </el-button>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" min-width="110px"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" min-width="110px"></el-table-column>
      <el-table-column prop="operateUserName" label="操作员" min-width="70px"></el-table-column>
      <el-table-column label="操作" min-width="120px">
        <template slot-scope="scope">
          <el-button type="text" @click="info(scope.row)">版本信息</el-button>
          <el-button type="text" @click="changeRecord(scope.row)">更改记录</el-button>
        </template>
      </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize"
                  @pagenum="setPageNum"></pagination>
    </template>
    <info-form :obj="selectRow" :handler="handler" @reset="resetHandler"></info-form>
    <add-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
  </portlet>
</template>
<script>
  import {mapActions, mapGetters} from 'vuex'
  import infoForm from './info.vue'
  import addForm from './add.vue'

  export default {
    components: {
      addForm,
      infoForm
    },
    data() {
      return {
        appName: '',
        appVersions: [],
        selectRow: {},
        handler: '',
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
      ...mapActions('action', ['searchAppVersions', 'searchAppsByPro', 'deleteProject']),
      reloadPage() {
        this.page.pageNum = 1
        this.resetHandler()
        this.searchAppVersionEvent()
      },
      resetHandler() {
        this.handler = ''
      },
      // 版本信息
      async info(val) {
        this.handler = 'INFO'
        this.selectRow = val
      },
      // 更改记录
      async changeRecord(val) {
        let index = val.appGitUrl.lastIndexOf('.')
        let url = val.appGitUrl.substring(0, index)
        window.open(url + '/compare/' + val.tagUrl + '...' + val.versionCode)
      },
      add() {
        this.handler = 'ADD'
        this.selectRow = {'proId': this.proId}
      },
      async searchAppVersionEvent() {
        let response = await this.searchAppVersions({
          pageSize: this.page.pageSize,
          pageNum: this.page.pageNum,
          appName: this.appName,
          proId: this.proId,
          envId: this.envId
        })
        if (~~response.code === 1) {
          this.appVersions = response.data.results
          this.page = response.data.meta
          this.versionNotUsedVo = response.data.versionNotUsedVo
        }
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.searchAppVersionEvent()
      },
      setPageNum: function(num) {
        this.page.pageNum = num
        this.searchAppVersionEvent()
      }
    },
    async created() {
      this.searchAppVersionEvent()
    }
  }
</script>
