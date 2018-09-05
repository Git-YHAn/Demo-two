<template>
  <div>
    <portlet title="应用审核">
      <template slot="search-bar">
        <el-form :inline="true">
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
            </el-table>
            <span v-else>{{props.row.msg}}</span>
          </template>
        </el-table-column>
        <el-table-column type="index" label="序号" min-width="50px" align="center">
        </el-table-column>
        <el-table-column prop="proName" label="项目名称" min-width="80px" align="center">
        </el-table-column>
        <el-table-column prop="appName" label="应用名称" min-width="80px" align="center">
        </el-table-column>
        <el-table-column prop="branchName" label="分支名称" min-width="80px" align="center">
        </el-table-column>
        <el-table-column prop="editorName" label="提交人员" min-width="80px" align="center">
          <template slot-scope="scope">{{scope.row.editorName||'--'}}</template>
        </el-table-column>
        <el-table-column prop="commitDate" label="提交时间" min-width="100px" align="center">
        </el-table-column>
        <!-- <el-table-column prop="auditDate" label="审核时间" min-width="100px" align="center">
          <template slot-scope="scope">{{scope.row.auditDate||'--'}}</template>
        </el-table-column> -->
        <el-table-column prop="recordStatus" label="状态" min-width="50px" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.recordStatus === 1" style="color:blue;">已提交</span>
            <span v-else-if="scope.row.recordStatus === 2" style="color:green;">审核通过</span>
            <span v-else-if="scope.row.recordStatus === -1" style="color:red;">审核失败</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="100px">
          <template slot-scope="scope">
            <el-button v-if="scope.row.recordStatus === 1" type="text" @click="auditEvent(scope.row, 2)" :disabled="scope.row.auditStatus === 1">通过</el-button>
            <el-button v-if="scope.row.recordStatus === 1" type="text" @click="auditEvent(scope.row, -1)" :disabled="scope.row.auditStatus === 1">不通过
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <template slot="footer">
        <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize"
                    @pagenum="setPageNum"></pagination>
      </template>
    </portlet>
  </div>
</template>
<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    data() {
      return {
        records: [],
        apps: [],
        appId: null,
        handler: '',
        diffObj: {},
        page: {pageNum: 1, pageSize: 30},
        flag: false
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
      ...mapActions('action', ['queryCommitAppConfigRecords', 'queryAppsByProject', 'auditAppConfigRecord', 'queryAppDiffList']),
      // 获取待审应用
      async queryAppConfigRecordsEvent() {
        let response = await this.queryCommitAppConfigRecords({
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize,
          appId: this.appId,
          proId: this.proId
        })
        if (~~response.code === 1) {
          this.records = response.data.results
          this.page = response.data.meta
        }
      },
      setPageSize(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.queryAppConfigRecordsEvent()
      },
      setPageNum(num) {
        this.page.pageNum = num
        this.queryAppConfigRecordsEvent()
      },
      resetHandler() {
        this.handler = ''
      },

      async showDiffList(data) {
        let record = this.records.filter((item) => { return item.recordId === data.recordId })[0]
        let idx = this.records.indexOf(record)
        this.$set(this.records, idx, Object.assign(record, { 'loading': true }))
        let response = await this.queryAppDiffList(data.recordId)
        if (~~response.code === 1) {
          this.$set(this.records, this.records.indexOf(record), Object.assign(record, { 'diffs': response.data, 'loading': false }))
        } else {
          this.$set(this.records, this.records.indexOf(record), Object.assign(record, { 'msg': response.msg, 'loading': false }))
        }
      },

      // 审核应用
      async auditEvent(row, status) {
        // 进行审核操作,按钮置为不可用
        row.auditStatus = 1
        let response = await this.auditAppConfigRecord({'recordId': row.recordId, 'status': status})
        this.flag = true
        if (~~response.code === 1) {
          row.recordStatus = status
          this.$msg.success('审核操作成功！')
          this.queryAppConfigRecordsEvent()
        } else {
          this.$msg.error('审核操作失败：', response.msg)
          this.queryAppConfigRecordsEvent()
        }
      }
    },
    async created() {
      this.queryAppConfigRecordsEvent()
      let response = await this.queryAppsByProject(this.proId)
      if (~~response.code === 1) {
        this.apps = response.data
      }
    }
  }
</script>
