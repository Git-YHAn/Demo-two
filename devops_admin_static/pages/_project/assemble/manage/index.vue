<template>
<div>
  <portlet title="应用列表">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item label="应用名称">
          <el-input v-model="appName"  clearable placeholder="请输入应用名称" @keyup.enter.native="selectAppEnvs"></el-input>
        </el-form-item>
        <el-form-item >
          <!-- <input type="text" style="display: none;" placeholder="阻止回车键提交表单"> -->
          <el-button  type="primary" @click="selectAppEnvs()" style="margin-left:40px">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <el-table :data="appEnvDatas" border style="margin-left:5px" row-key='appEnvId' show-overflow-tooltip>
      <!-- <el-table-column prop="envName" label="环境名称" min-width="100px"></el-table-column> -->
      <!-- <el-table-column prop="proName" label="项目名称" min-width="100px"></el-table-column> -->
      <el-table-column prop="appName" label="应用名称" min-width="70px"></el-table-column>
      <el-table-column prop="appTypeName" label="应用类型" min-width="70px"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" min-width="70px"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" min-width="70px"></el-table-column>
      <el-table-column label="操作" min-width="100px" show-overflow-tooltip>
        <template slot-scope="scope">
            <el-button v-auth="'detail'" type="text" @click="detail(scope.row)">详情</el-button>
            <el-button v-auth="'log'" type="text" @click="logsList(scope.row)">日志下载</el-button>
            <el-button v-auth="'edit'"  type="text" @click="edit(scope.row)">
              <el-badge :is-dot='scope.row.deployAppGitUrl === ""' class="item">编辑</el-badge>
            </el-button>
            <el-button v-auth="'deploy'"  type="text" @click="deploy(scope.row)" >服务器配置</el-button>
            <!-- <el-button type="text" @click="restart(scope.row)" >重启</el-button> -->
            <el-button type="text" @click="uploadApp(scope.row)" :disabled="scope.row.uploadStatus === 1">应用上传</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="uploadStatus" label="上传状态" min-width="70px">
        <template slot-scope="scope">
          <span v-if="scope.row.uploadStatus===0" style="color:green;">待上传</span>
          <span v-else-if="scope.row.uploadStatus===1" style="color:blue;">上传中</span>
          <span v-else-if="scope.row.uploadStatus===2" style="color:green;">上传完成</span>
          <span v-else-if="scope.row.uploadStatus===-1" style="color:red;">上传失败</span>
        </template>
      </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </template>
  </portlet>
  <detail-form :obj="selectRow" :handler="handler" @reset="resetHandler"></detail-form>
  <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
  <deploy-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler" :servers="servers"></deploy-form>
  <restart-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler" :servers="servers"></restart-form>
  <upload-form :obj="selectRow" :handler="handler" @reset="reloadPage"></upload-form>
</div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import detailForm from './detail.vue'
import editForm from './edit.vue'
import deployForm from './deploy-server.vue'
import restartForm from './restart.vue'
import uploadForm from './upload.vue'

export default {
  components: { detailForm, editForm, deployForm, restartForm, uploadForm },
  data() {
    return {
      apps: [],
      appName: '',
      servers: [],
      appEnvDatas: [],
      handler: '',
      selectRow: {},
      page: {pageNum: 1, pageSize: 30},
      intervalId: null
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
    ...mapActions('action', ['searchEnvs', 'searchPros', 'searchAppEnvs', 'searchAppsByPro', 'searchUsableServers', 'getAppEnvServer']),
    reloadPage() {
      this.page.pageNum = 1
      this.resetHandler()
      this.selectAppEnvs()
    },
    resetHandler() {
      this.handler = ''
    },
    detail(row) {
      this.handler = 'DETAIL'
      this.selectRow = row
    },
    edit(row) {
      this.handler = 'EDIT'
      this.selectRow = row
    },
    deploy(row) {
      this.handler = 'DEPLOY_SERVER'
      this.selectRow = row
    },
    restart(row) {
      this.handler = 'RESTART'
      this.selectRow = row
    },
    uploadApp(row) { // 应用上传
      this.handler = 'UPLOAD'
      this.selectRow = row
    },
    logsList(row) { // 日志查询
      window.open(`/common/logdown?instanceId=${row.appEnvId}`)
    },
    async changePro(proId) {
      this.appMessage = []
      if (proId != null && proId !== '') {
        let response = await this.searchAppsByPro(proId)
        if (response.code === '1') {
          this.apps = response.data
        }
      }
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.selectAppEnvs(size, 1)
    },
    setPageNo: function (num) {
      this.page.pageNum = num
      this.selectAppEnvs(undefined, num)
    },
    async selectAppEnvs() {
      let response = await this.searchAppEnvs({
        'envId': this.envId,
        'proId': this.proId,
        'appName': this.appName,
        'pageSize': this.page.pageSize,
        'pageNum': this.page.pageNum
      })
      if (response.code === '1') {
        this.appEnvDatas = response.data.results
        this.page = response.data.meta
      }
    },
    async queryServers() {
      let response = await this.searchUsableServers()
      this.servers = response.data
    }
  },
  created() {
    // 轮询页面,查询应用列表
    this.intervalId = setInterval(this.selectAppEnvs, 3000)
  },
  destroyed() {
    clearInterval(this.intervalId)
  },
  async mounted() {
    this.selectAppEnvs()
    this.queryServers()
  }
}
</script>
