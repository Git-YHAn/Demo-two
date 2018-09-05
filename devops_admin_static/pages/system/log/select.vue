<template>
<portlet :showTitle="false" :showPageBar="false" :showSearchBar="false">
  <el-form :inline="true" onsubmit="return false;">
    <el-form-item label="关键字">
      <el-input placeholder="输入日志内容的关键字" v-model="searchName" clearable @keyup.enter.native="searchSysLogRecord" @change="searchSysLogRecord"></el-input>
    </el-form-item>
    <el-form-item label="记录时间">
      <template>
        <div class="block">
          <el-date-picker v-model="dateTime" type="daterange" range-separator="至"  start-placeholder="开始时间" end-placeholder="结束时间">
          </el-date-picker>
        </div>
      </template>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="searchSysLogRecord">查询</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="info" @click="reset">返回</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="tableData" :row-class-name="tableRowClassName" border highlight-current-row>
    <el-table-column prop="recordId" label="日志 ID" min-width="15" align="center"> </el-table-column>
    <el-table-column prop="userId" label="用户 ID" min-width="15" align="center"> </el-table-column>
    <el-table-column prop="typeName" label="日志类型" min-width="30" align="center"> </el-table-column>
    <el-table-column prop="recordTime" label="记录时间" min-width="30" align="center"> </el-table-column>
    <el-table-column prop="logContents" label="日志内容" min-width="160" align="left" show-overflow-tooltip> </el-table-column>
  </el-table>
  <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
    </template>
</portlet>
</template>
<script type='text/javascript'>
import {
  mapActions
} from 'vuex'
export default {
  props: {
    obj: Object
  },
  data() {
    return {
      tableData: [],
      searchName: '',
      dateTime: '',
      page: {
        pageNum: 1,
        pageSize: 30
      }
    }
  },
  methods: {
    ...mapActions('action', ['selectSysLogRecord']),
    setPageSize: function(size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.searchSysLogRecord(size, 1)
    },
    setPageNum: function(num) {
      this.page.pageNum = num
      this.searchSysLogRecord(undefined, num)
    },
    reset() {
      this.$emit('reset')
    },
    changeDate(date) {
      var y = date.getFullYear()
      var m = date.getMonth() + 1
      m = m < 10 ? ('0' + m) : m
      var d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      // var h = date.getHours()
      // var minute = date.getMinutes()
      // minute = minute < 10 ? ('0' + minute) : minute
      // var second = date.getSeconds()
      // second = minute < 10 ? ('0' + second) : second
      return y + '-' + m + '-' + d
    },
    tableRowClassName({row, rowIndex}) {
      if (rowIndex % 2 === 1) {
        return 'one-row'
      } else {
        return 'tow-row'
      }
    },
    async searchSysLogRecord() {
      var startTime = ''
      var endTime = ''
      if (this.dateTime && this.dateTime.length > 0) {
        startTime = this.changeDate(this.dateTime[0])
        endTime = this.changeDate(this.dateTime[1])
      }
      let response = await this.selectSysLogRecord({
        'pageNum': this.page.pageNum,
        'pageSize': this.page.pageSize,
        'typeId': this.obj.typeId,
        'searchName': this.searchName,
        'startTime': startTime,
        'endTime': endTime
      })
      if (response.code === '1') {
        this.tableData = response.data.results
        this.page = response.data.meta
      }
      this.tableData.forEach(item => {
        var logContents = '【' + item.recordTime + '】 ' + item.operation + ',' + item.details
        this.$set(item, 'logContents', logContents)
      })
    }
  },
  mounted() {
    this.searchSysLogRecord()
  }
}
</script>
<style>
.el-table .one-row {
  background: #FAF0E6;
  font-weight: bold;
}
.el-table .tow-row {
  background: #FFDAB9;
  font-weight: bold;
}
</style>
