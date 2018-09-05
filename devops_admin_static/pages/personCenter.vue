<template>
  <div>
    <el-dialog title="个人中心" @close="reset" :visible="true">
      <el-button type="text" @click="setEditHandler" border>修改信息</el-button>
      <hr v-show="lineVisible">
      <el-form :model="personalInfoForm" label-width="80px">
        <el-form-item label="用户名">
          {{personalInfoForm.username}}
        </el-form-item>
        <el-form-item label="用户姓名" prop="realName" >
          {{personalInfoForm.realName}}
        </el-form-item>
        <el-form-item label="用户邮箱" prop="email">
          {{personalInfoForm.email}}
        </el-form-item>
        <el-form-item label="联系电话">
          {{personalInfoForm.mobile}}
        </el-form-item>
        <el-form-item label="用户状态">
          <label v-if="personalInfoForm.isActive === 0">禁用</label>
          <label v-if="personalInfoForm.isActive === 1">正常</label>
        </el-form-item>
        <el-form-item label="用户角色">
          {{personalInfoForm.roleName}}
        </el-form-item>
        <el-form-item label="创建时间">
          {{personalInfoForm.createTime}}
        </el-form-item>
      </el-form>
      <footer></footer>
    </el-dialog>
    <edit-user-dialog :obj="personalInfoForm" :handler="personalHandler" @reset="reset" @save="reset"></edit-user-dialog>
  </div>
</template>

<script>
  import {mapActions, mapState} from 'vuex'
  import EditUserDialog from './system/user/manager/edit'

  export default {
    components: {EditUserDialog},
    data: function() {
      return {
        personalInfoForm: {},
        personalHandler: '',
        lineVisible: true
      }
    },
    computed: {
      ...mapState(['userInfo'])
    },
    methods: {
      ...mapActions('action', ['searchAdminUsers']),
      setEditHandler() {
        this.personalHandler = 'EDIT'
        this.lineVisible = false
      },
      reset: function() {
        this.personalHandler = ''
        this.$emit('hidden')
      },
      async getCurrentUser() {
        let currentUserId = this.userInfo.adminUserId
        let response = await this.searchAdminUsers(
          {'keyWords': currentUserId, 'pageSize': 10, 'pageNum': 1})
        if (~~response.code === 1) {
          response.data.results.map((user) => {
            if (user.adminUserId === currentUserId) {
              let roleNames = user.roleInfo.map((role) => {
                return role.roleName
              })
              user.roleName = roleNames.join(',')
              this.personalInfoForm = user
            }
          })
        }
      }
    },
    mounted() {
      this.getCurrentUser()
      this.lineVisible = true
    },
    beforeDestroy() {
      this.lineVisible = false
      this.personalInfoForm = {}
    }
  }
</script>
