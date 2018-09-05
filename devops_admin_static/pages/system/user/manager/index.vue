<template>
<div>
  <el-form :inline="true" onsubmit="return false;">
    <el-form-item>
      <el-input v-model="keyWords" clearable placeholder="ID、用户名、真实姓名" @keyup.enter.native="queryAdminUsers" />
    </el-form-item>
    <el-form-item>
      <el-button v-auth="'search'" type="primary" @click="queryAdminUsers">查询</el-button>
      <el-button v-auth="'add'" type="primary" @click="add()">新增</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="tableData" border>
    <el-table-column prop="adminUserId" label="用户ID" min-width="70px" style="text-align:center"> </el-table-column>
    <el-table-column prop="username" label="用户名" show-overflow-tooltip></el-table-column>
    <el-table-column prop="realName" label="真实姓名" show-overflow-tooltip></el-table-column>
    <el-table-column prop="email" label="用户邮箱" show-overflow-tooltip min-width="200px"></el-table-column>
    <el-table-column prop="mobile" label="联系电话" show-overflow-tooltip min-width="100px"></el-table-column>
    <el-table-column prop="roleName" label="角色" show-overflow-tooltip min-width="200px"></el-table-column>
    <el-table-column label="操作" min-width="200px">
      <template slot-scope="scope">
              <el-button v-auth="'edit'" type="text" @click="edit(scope.row)" :disabled='scope.row.adminUserId === 1?true:false'>编辑</el-button>
              <el-button v-auth="'delete'" type="text" :disabled='scope.row.adminUserId === 1?true:false' @click="del(scope.row)">删除</el-button>
              <el-button v-auth="'detail'" type="text" @click="detail(scope.row)">详情</el-button>
              <el-button v-auth="'roleAuth'" type="text" @click="roleAuth(scope.row)">用户角色</el-button>
          </template>
    </el-table-column>
  </el-table>
  <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
  <add-form :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
  <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
  <detail-form :obj="selectRow" :handler="handler" @reset="resetHandler"></detail-form>
  <auth-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler" :roles="roles"></auth-form>
</div>
</template>
<script>
import { mapActions } from 'vuex'
import addForm from './add.vue'
import editForm from './edit.vue'
import detailForm from './detail.vue'
import authForm from './auth.vue'
export default {
  components: { addForm, editForm, detailForm, authForm },
  data() {
    return {
      keyWords: '',
      roles: [],
      adminUsers: [],
      handler: '',
      selectRow: {},
      page: { pageNum: 1, pageSize: 30 }
    }
  },
  computed: {
    tableData: function () {
      return this.adminUsers.map((item) => {
        let roleNames = item.roleInfo.map((role) => {
          return role.roleName
        })
        item.roleName = roleNames.join(',')
        return item
      })
    }
  },
  methods: {
    ...mapActions('action', ['searchRoles', 'deleteAdminUser', 'searchAdminUsers']),
    reloadPage() {
      this.page.pageNum = 1
      this.resetHandler()
      this.queryAdminUsers()
    },
    resetHandler() {
      this.handler = ''
    },
    add() {
      this.handler = 'ADD'
    },
    detail(row) {
      this.handler = 'DETAIL'
      this.selectRow = row
    },
    edit(row) {
      this.handler = 'EDIT'
      this.selectRow = row
    },
    roleAuth(row) {
      this.handler = 'ROLE_AUTH'
      this.selectRow = row
    },
    async del(row) {
      if (row.adminUserId) {
        this.$confirm('是否删除?', '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(async () => {
          let response = await this.deleteAdminUser(row.adminUserId)
          if (response.code === '1') {
            this.$msg.success('删除成功！')
            let res = await this.queryAdminUsers()
            this.adminUsers = res.data.results
          } else {
            this.$msg.error('删除失败：', response.msg)
          }
        })
      }
    },
    async queryAdminUsers() {
      let response = await this.searchAdminUsers({ 'keyWords': this.keyWords, 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum })
      if (~~response.code === 1) {
        this.adminUsers = response.data.results
        this.page = response.data.meta
      }
      return response
    },
    async banAdmin() {

    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.queryAdminUsers(size, 1)
    },
    setPageNo: function (num) {
      this.page.pageNum = num
      this.queryAdminUsers(undefined, num)
    },
    async queryRoles() {
      let res = await this.searchRoles()
      if (res.code === '1') {
        this.roles = res.data
      }
    },
    changeUserStatus: function () {
      if (this.userStatus === 1) {
        this.userStatus = 0
      } else {
        this.userStatus = 1
      }
    }
  },
  async created() {
    this.queryAdminUsers()
    this.queryRoles()
  }
}
</script>
