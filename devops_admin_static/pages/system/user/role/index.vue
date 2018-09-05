<template>
<div>
  <el-form :inline="true" onsubmit="return false;">
    <el-form-item>
      <el-input v-model="keyWords" clearable placeholder="角色名" @keyup.enter.native="doSearch" />
    </el-form-item>
    <el-form-item>
      <el-button v-auth="'search'" type="primary" @click="doSearch">查询</el-button>
      <el-button v-auth="'add'" type="primary" @click="add()">新增</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="roleList" border>
    <el-table-column prop="roleId" label="角色ID" min-width="50px"> </el-table-column>
    <el-table-column prop="roleName" label="角色名称" min-width="100px" show-overflow-tooltip> </el-table-column>
    <el-table-column prop="description" label="角色描述" min-width="100px" show-overflow-tooltip> </el-table-column>
    <el-table-column label="操作" min-width="100px" show-overflow-tooltip>
      <template slot-scope="scope">
              <el-button v-auth="'resourceAuth'" type="text" @click="auth(scope.row)">授权管理</el-button>
              <el-button v-auth="'edit'" type="text" @click="edit(scope.row)">编辑</el-button>
              <el-button v-auth="'delete'" type="text" :disabled='scope.row.roleId === 13?true:false' @click="del(scope.row)">删除</el-button>
            </template>
    </el-table-column>
  </el-table>
  <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
  <add-form :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
  <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
  <auth-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler" :roles="roles" :resourceTree="resourceTree"></auth-form>
</div>
</template>
<script>
import { mapActions } from 'vuex'
import addForm from './add.vue'
import editForm from './edit.vue'
import authForm from './auth.vue'
export default {
  components: { addForm, editForm, authForm },
  data() {
    return {
      keyWords: '',
      selectRow: {},
      handler: '',
      roleList: [],
      roles: [],
      resourceTree: [],
      roleId: '',
      page: { pageNum: 1, pageSize: 30 }
    }
  },
  methods: {
    ...mapActions('action', ['searchRoleList', 'searchRoles', 'deleteRole', 'searchResourceTree']),
    reloadPage() {
      // this.page.pageNum = 1
      this.resetHandler()
      this.queryRoleList()
      this.queryRoles()
    },
    resetHandler() {
      this.handler = ''
    },
    auth(row) {
      this.handler = 'AUTH'
      this.selectRow = row
    },
    edit(row) {
      this.handler = 'EDIT'
      this.selectRow = row
    },
    add() {
      this.handler = 'ADD'
    },
    async del(row) {
      if (row.roleId) {
        this.$confirm('是否删除?', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(async () => {
          let response = await this.deleteRole(row.roleId)
          if (response.code === '1') {
            this.$msg.success('删除成功！')
            // let response = await this.queryRoleList()
            // this.roleList = response.data.results
            this.reloadPage()
          } else {
            this.$msg.error('删除失败：', response.msg)
          }
        })
      }
    },
    async doSearch() {
      this.queryRoleList()
    },
    async queryRoles() {
      let res = await this.searchRoles()
      if (res.code === '1') {
        this.roles = res.data
      }
    },
    async queryRoleList() {
      let response = await this.searchRoleList({ 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum, 'roleName': this.keyWords })
      if (response.code === '1') {
        this.roleList = response.data.results
        this.page = response.data.meta
      }
      // return response
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.queryRoleList(size, 1)
    },
    setPageNo: function (num) {
      this.page.pageNum = num
      this.queryRoleList(undefined, num)
    },
    async queryResourceTree() {
      let res = await this.searchResourceTree()
      if (res.code === '1') {
        this.resourceTree = res.data
      }
    }
  },
  async created() {
    this.queryRoleList()
    this.queryRoles()
    this.queryResourceTree()
  }
}
</script>
