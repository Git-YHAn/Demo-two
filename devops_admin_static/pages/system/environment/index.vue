<template>
<div>
  <el-form :inline="true" onsubmit="return false;">
    <el-form-item label="环境名称" >
      <el-input placeholder="环境名称" v-model="keyWords" clearable @keyup.enter.native="queryEnvs"></el-input>
    </el-form-item>
    <el-form-item >
      <el-button v-auth="'search'" type="primary" @click="queryEnvs">查询</el-button>
    </el-form-item >
    <el-form-item >
      <el-button v-auth="'add'" type="primary" @click="add()">新增</el-button>
    </el-form-item >
  </el-form>
  <el-table :data="envs" border :expand-row-keys="expands" @expand-change="queryProsByAppEnv" row-key='envId'>
    <el-table-column type="expand">
      <template slot-scope="props">
             <el-table label-position="left" inline :data="pros" :expand-row-keys="expandss"  @expand-change="queryAppByAppEnv" row-key='proId' >
                <el-table-column type="expand">
                  <template slot-scope="props">
                    <el-table label-position="left" inline :data="apps" >
                      <el-table-column prop="appName" label="应用名称"  min-width="100px"> </el-table-column>
                      <el-table-column prop="appCode" label="应用编码" min-width="100px"> </el-table-column>
                      <el-table-column prop="appType" label="运行环境" min-width="100px" :formatter='getTypeStr'> </el-table-column>
                      <el-table-column prop="createTime" label="创建时间" min-width="100px"> </el-table-column>
                      <el-table-column prop="updateTime" label="更新时间" min-width="100px"> </el-table-column>
                      <el-table-column prop="description" label="应用描述" min-width="200px" show-overflow-tooltip> </el-table-column>
                    </el-table>
                 </template>
    </el-table-column>
    <el-table-column prop="proName" label="项目名称" min-width="100px" style="color:#ccc"> </el-table-column>
    <el-table-column prop="proCode" label="项目编码" min-width="100px" style="text-align:center"> </el-table-column>
    <el-table-column prop="createTime" label="创建时间" min-width="100px" style="text-align:center"> </el-table-column>
    <el-table-column prop="updateTime" label="更新时间" min-width="100px" style="text-align:center"> </el-table-column>
    <el-table-column prop="description" label="项目描述" min-width="200px" style="text-align:center" show-overflow-tooltip> </el-table-column>
  </el-table>
  </template>
  </el-table-column>
  <el-table-column prop="envName" label="环境名称" width="120px" style="text-align:center"> </el-table-column>
  <el-table-column prop="envCode" label="环境编码" width="120px" show-overflow-tooltip> </el-table-column>
  <el-table-column prop="priority" label="优先级" width="120px"> </el-table-column>
  <el-table-column prop="createTime" label="创建时间" width="150px"> </el-table-column>
  <el-table-column prop="updateTime" label="更新时间" width="150px"> </el-table-column>
  <el-table-column prop="description" label="环境描述" min-width="180px" show-overflow-tooltip> </el-table-column>
  <el-table-column label="操作" min-width="100px" show-overflow-tooltip>
    <template slot-scope="scope">
            <el-button v-auth="'edit'" type="text" @click="edit(scope.row)">编辑</el-button>
            <el-button v-auth="'config'" type="text" @click="config(scope.row)">配置</el-button>
            <el-button v-auth="'delete'" type="text"  @click="del(scope.row)">删除</el-button>
          </template>
  </el-table-column>
  </el-table>
  <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
  <add-form :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
  <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
  <config-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></config-form>
</div>
</template>
<script>
import { mapActions } from 'vuex'
import addForm from './add.vue'
import editForm from './edit.vue'
import configForm from './config.vue'
export default {
  components: { addForm, editForm, configForm },
  data() {
    return {
      keyWords: '',
      envs: [],
      pros: [],
      apps: [],
      expands: [],
      expandss: [],
      envId: '',
      handler: '',
      selectRow: {},
      page: { pageNum: 1, pageSize: 30 }
    }
  },
  methods: {
    ...mapActions('action', ['searchEnvsByPage', 'searchProsByAppEnv', 'searchAppsByAppEnv', 'deleteEnv']),
    getTypeStr(row) {
      if (row.appType === 0) {
        return 'tomcat'
      } else if (row.appType === 1) {
        return 'jdk7'
      } else if (row.appType === 2) {
        return 'jdk8'
      }
    },
    reloadPage() {
      this.page.pageNum = 1
      this.resetHandler()
      this.queryEnvs()
    },
    resetHandler() {
      this.handler = ''
    },
    add() {
      this.handler = 'ADD'
    },
    config(row) {
      this.handler = 'CONFIG'
      this.selectRow = row
    },
    edit(row) {
      this.handler = 'EDIT'
      this.selectRow = row
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.queryEnvs(size, 1)
    },
    setPageNo: function (num) {
      this.page.pageNum = num
      this.queryEnvs(undefined, num)
    },
    async queryEnvs() {
      let response = await this.searchEnvsByPage({ 'keyWords': this.keyWords, 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum })
      if (response.code === '1') {
        this.envs = response.data.results
        this.page = response.data.meta
      }
      return response
    },
    async queryProsByAppEnv(keyWords) {
      this.expandss = []
      if (this.expands && this.expands.length > 0) {
        if (this.expands[0] === keyWords.envId) {
          this.expands = []
          return
        }
      }
      let response = await this.searchProsByAppEnv(keyWords.envId)
      if (response.code === '1') {
        this.pros = response.data
        this.envId = keyWords.envId
      }
      this.expands = []
      this.expands.push(keyWords.envId)
    },
    async queryAppByAppEnv(keyWords) {
      if (this.expandss && this.expandss.length > 0) {
        if (this.expandss[0] === keyWords.proId) {
          this.expandss = []
          return
        }
      }
      let response = await this.searchAppsByAppEnv({ 'envId': this.envId, 'proId': keyWords.proId })
      if (response.code === '1') {
        this.apps = response.data
      }
      this.expandss = []
      this.expandss.push(keyWords.proId)
    },
    async del(row) {
      if (row.envId) {
        this.$confirm('是否删除?', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(async () => {
          let response = await this.deleteEnv(row.envId)
          if (response.code === '1') {
            this.$msg.success('删除成功！')
            let res = await this.queryEnvs()
            this.jobs = res.data.results
          } else {
            this.$msg.error('删除失败：', response.msg)
          }
        })
      }
    }
  },
  async created() {
    this.queryEnvs()
  }
}
</script>
