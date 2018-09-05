<template>
<el-dialog title="详情" :visible.sync="visible" @close="reset">
  <el-form :model="form" label-width="80px" ref='form'>
    <el-form-item label="用户姓名" prop='realName'>{{form.realName}}</el-form-item>
    <el-form-item label="用户名" prop='userName'>{{form.username}}</el-form-item>
    <el-form-item label="用户邮箱" prop='email'>{{form.email}}</el-form-item>
    <el-form-item label="联系电话" prop='mobile'>{{form.mobile}}</el-form-item>
    <el-form-item label="角色名称" prop='roleName'>{{form.roleName}}</el-form-item>
    <el-form-item label="用户状态">{{form.isActive==1?'正常':'禁用'}}</el-form-item>
    <el-form-item label="创建时间" prop='createTime'>{{form.createTime}}</el-form-item>
    <el-form-item label="修改时间" prop='updateTime'>{{form.updateTime}}</el-form-item>
  </el-form>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { adminUserId: '', roleId: '', realName: '', userName: '', password: '', email: '', mobile: '', roleName: '', isActive: 1 }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'DETAIL'
      if (this.visible) {
        this.form = this.obj
      }
    }
  },
  methods: {
    ...mapActions('action', ['getAdminUser']),
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
