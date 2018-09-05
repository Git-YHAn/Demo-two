<template>
  <portlet :showTitle="false" :showPageBar="false" :showSearchBar="false">
    <el-form :inline="true">
      <el-form-item label="主机IP" label-width="80px">
        <el-input v-model="selectSshAddr" placeholder="请输入主机IP" clearable @clear="reloadPage"></el-input>
      </el-form-item>
      <el-form-item label="执行模板" label-width="80px">
        <el-input v-model="selectTplName" placeholder="请输入模板名称" clearable @clear="reloadPage"></el-input>
      </el-form-item>
      <el-form-item label="执行时间" label-width="80px">
        <el-input v-model="selectExecTime" placeholder="请输入执行时间" clearable @clear="reloadPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryExecuteHistory">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table ref="ExecHistoryList" :data="ExecHistoryList" border>
      <el-table-column prop="sshAddress" label="执行主机IP" width="130px"></el-table-column>
      <el-table-column prop="operateUserName" label="操作用户" width="130px"></el-table-column>
      <el-table-column prop="execTplName" label="执行模板" width="130px"></el-table-column>
      <el-table-column prop="execContent" label="执行内容" min-width="130px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="execTime" label="执行时间" width="140px"></el-table-column>
      <el-table-column prop="execResult" label="执行结果" width="100px" show-overflow-tooltip></el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </template>
  </portlet>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      handler: String
    },
    data() {
      return {
        selectRow: {},
        page: {pageNum: 1, pageSize: 30},
        ExecHistoryList: [],
        selectSshAddr: null,
        selectTplName: null,
        selectExecTime: null
      }
    },
    watch: {
      handler: async function (val) {
        this.isVisible = val === 'EXECUTE_HISTORY'
        if (this.isVisible) {
          this.reloadPage()
        }
      }
    },
    methods: {
      ...mapActions('action', ['queryAllExecHis']),
      setPageSize: function(size) {
        this.page.pageSize = size
        this.reloadPage()
      },
      setPageNo: function(num) {
        this.page.pageNum = num
        this.queryExecuteHistory()
      },
      reloadPage() {
        this.page.pageNum = 1
        this.queryExecuteHistory()
      },
      reset() {
        this.$emit('reset')
      },
      async queryExecuteHistory() {
        let request = {
          'pageNum': this.page.pageNum,
          'pageSize': this.page.pageSize,
          'sshAddress': this.selectSshAddr,
          'tplName': this.selectTplName,
          'execTime': this.selectExecTime
        }
        let response = await this.queryAllExecHis(request)
        if (response.code === '1') {
          this.page = response.data.meta
          this.ExecHistoryList = response.data.results
        }
      }
    },
    async created() {
      this.queryExecuteHistory()
    },
    async beforeDestroy() {
      this.ExecHistoryList = []
      this.reset()
    }
  }
</script>
