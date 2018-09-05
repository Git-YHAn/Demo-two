<template>
<div>
  <portlet title="审核历史">
    <template slot="search-bar">
      <el-form :inline="true" >
        <el-form-item label="应用名称">
          <el-select v-model="appId" clearable placeholder="请选择应用" @change="setPageNum(1)">
            <el-option v-for="app in apps" :key="app.appId" :label="app.appName" :value="app.appId"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </template>
    <el-table ref="dataTable" :data="records" border highlight-current-row :cell-style="{'text-aligin': 'center'}" @expand-change="showDiffList">
      <el-table-column type="expand">
        <template slot-scope="props">
            <el-table v-if="props.row.diffs" v-loading="props.row.loading" :data="props.row.diffs" border>
              <el-table-column prop="diffType" label="变更方式" min-width="80px" align="center">
                <template slot-scope="scope">
                  <span v-if="scope.row.diffType === 'ADD'" style="color: green;">{{scope.row.diffType}}</span>
                  <span v-else-if="scope.row.diffType === 'DELETE'" style="color: red;">{{scope.row.diffType}}</span>
                  <span v-else style="color: blue;">{{scope.row.diffType}}</span>
                </template>
              </el-table-column>
              <el-table-column prop="diffFile" label="文件路径" show-overflow-tooltip min-width="300px"></el-table-column>
              <el-table-column label="操作" min-width="50px">
                <template slot-scope="scope">
                  <el-button v-if="scope.row.diffType !== 'DELETE'" type="text"  @click="showDiff(scope.row)">变更内容</el-button>
                </template>
              </el-table-column>
            </el-table>
            <span v-else>{{props.row.msg}}</span>
          </template>
    </el-table-column>
    <!-- <el-table-column prop="recordId" label="ID" min-width="50px" align="center">
    </el-table-column> -->
    <el-table-column type="index" label="序号" min-width="50px" align="center">
    </el-table-column>
    <el-table-column prop="envName" label="环境名称" min-width="60px" align="center">
    </el-table-column>
    <el-table-column prop="proName" label="项目名称" min-width="60px" align="center">
    </el-table-column>
    <el-table-column prop="appName" label="应用名称" min-width="80px" align="center">
    </el-table-column>
    <el-table-column prop="commitDate" label="提交时间" min-width="100px" align="center">
    </el-table-column>
    <el-table-column prop="editorName" label="提交人员" min-width="80px" align="center">
      <template slot-scope="scope">{{scope.row.editorName||'--'}}</template>
    </el-table-column>
    <el-table-column prop="auditDate" label="审核时间" min-width="100px" align="center">
      <template slot-scope="scope">{{scope.row.auditDate||'--'}}</template>
    </el-table-column>
    <el-table-column prop="auditorName" label="审核人员" min-width="80px" align="center">
      <template slot-scope="scope">{{scope.row.auditorName||'--'}}</template>
    </el-table-column>
    <el-table-column prop="recordStatus" label="状态" min-width="50px" align="center">
      <template slot-scope="scope">
                <span v-if="scope.row.recordStatus === 0">未提交</span>
                <span v-else-if="scope.row.recordStatus === 1" style="color:blue;">已提交</span>
                <span v-else-if="scope.row.recordStatus === 2" style="color:green;">审核通过</span>
                <span v-else-if="scope.row.recordStatus === -1" style="color:red;">审核不通过</span>
              </template>
    </el-table-column>
    <el-table-column label="操作" min-width="80px">
      <template slot-scope="scope">
              <el-button  type="text"  @click="showDiffListEvent(scope.row)">查看变动</el-button>
              <el-button v-if="scope.row.recordStatus === 1" type="text"  @click="withdrawnEvent(scope.row)">撤回</el-button>
          </template>
    </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
    </template>
  </portlet>
  <diff-form :obj="diffObj" :handler="handler" @reset="resetHandler" />
</div>
</template>
<script>
import diffForm from './diff'
import { mapActions, mapGetters } from 'vuex'
export default {
  components: {
    diffForm
  },
  data() {
    return {
      records: [],
      apps: [],
      appId: null,
      handler: '',
      diffObj: {},
      page: { pageNum: 1, pageSize: 30 }
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
    ...mapActions('action', ['queryConfigRecordByUser', 'queryAppsByProject', 'queryDiffList', 'withdrawnConfig']),
    async queryConfigRecords() {
      let response = await this.queryConfigRecordByUser({ pageNum: this.page.pageNum, pageSize: this.page.pageSize, appId: this.appId, proId: this.proId, envId: this.envId })
      if (~~response.code === 1) {
        this.records = response.data.results
        this.page = response.data.meta
      }
    },
    setPageSize(size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.queryConfigRecords()
    },
    setPageNum(num) {
      this.page.pageNum = num
      this.queryConfigRecords()
    },
    showDiffListEvent(row) {
      this.$refs.dataTable.toggleRowExpansion(row, true)
      this.showDiffList(row)
    },
    async showDiffList(data) {
      let record = this.records.filter((item) => { return item.recordId === data.recordId })[0]
      let idx = this.records.indexOf(record)
      this.$set(this.records, idx, Object.assign(record, { 'loading': true }))
      let response = await this.queryDiffList(data.recordId)
      if (~~response.code === 1) {
        this.$set(this.records, this.records.indexOf(record), Object.assign(record, { 'diffs': response.data, 'loading': false }))
      } else {
        this.$set(this.records, this.records.indexOf(record), Object.assign(record, { 'msg': response.msg, 'loading': false }))
      }
    },
    resetHandler() {
      this.handler = ''
    },
    showDiff: function (data) {
      this.diffObj = data
      this.handler = 'DIFF'
    },
    async withdrawnEvent(row) {
      let response = await this.withdrawnConfig(row.recordId)
      if (~~response.code === 1) {
        this.$msg.success('撤回成功！')
        row.recordStatus = 0
      } else {
        this.$msg.success('撤回失败：', response.msg)
      }
    }
  },
  async created() {
    this.queryConfigRecords()
    let response = await this.queryAppsByProject(this.proId)
    if (~~response.code === 1) {
      this.apps = response.data
    }
  }
}
</script>
