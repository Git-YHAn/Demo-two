<template>
  <div>
    <el-table :data="datas" border max-height="400" highlight-current-row>
      <el-table-column prop="appName" label="应用名称" min-width="100px" sortable></el-table-column>
      <el-table-column prop="appCode" label="应用编码" min-width="100px" sortable></el-table-column>
      <el-table-column prop="appTypeName" label="应用类型" min-width="100px" sortable></el-table-column>
      <el-table-column prop="appType" label="运行环境" min-width="100px" :formatter='getTypeStr' sortable></el-table-column>
      <el-table-column prop="createTime" label="创建时间" min-width="150px" sortable></el-table-column>
      <el-table-column prop="logPath" label="日志路径" min-width="150px"></el-table-column>
      <el-table-column prop="description" label="应用描述" min-width="200px" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" min-width="100px" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button v-auth="'edit'" type="text" @click="edit(scope.row)">编辑</el-button>
          <el-button v-auth="'delete'" type="text" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <edit-form :obj="selectRow" :handler="handler" @reset="resetHandler"></edit-form>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import editForm from './edit'
export default {
  components: { editForm },
  data() {
    return {
      // apps: [],
      handler: '',
      selectRow: null
    }
  },
  props: {
    proId: Number,
    datas: Array
  },
  methods: {
    ...mapActions('action', ['deleteApp', 'searchAppsByPro']),
    getTypeStr(row) {
      if (row.appType === 0) {
        return 'tomcat'
      } else if (row.appType === 1) {
        return 'jdk7'
      } else if (row.appType === 2) {
        return 'jdk8'
      }
    },
    resetHandler() {
      this.handler = ''
      this.selectRow = null
      this.queryApps()
    },
    edit(row) {
      this.handler = 'EDIT'
      this.selectRow = row
    },
    async queryApps() {
      let response = await this.searchAppsByPro(this.proId)
      if (response.code === '1') {
        // this.apps = response.data
        this.datas = response.data
      }
      return response
    },
    async del(row) {
      if (row.appId) {
        this.$confirm('是否删除?', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(async () => {
          let response = await this.deleteApp(row.appId)
          if (response.code === '1') {
            this.$msg.success('删除成功！')
            this.queryApps()
          } else {
            this.$msg.error('删除失败：', response.msg)
          }
        })
      }
    }
  }
  // async created() {
  //   this.queryApps()
  // },
  // async beforeDestroy() {
  //   console.log('beforeDestroy')
  //   this.apps = null
  // }
}
</script>
