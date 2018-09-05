<template>
<el-dialog title="新增" :visible.sync="visible" @close="reset">
  <el-form :model="form" label-width="80px" ref='form' :rules='rule'>
    <el-form-item label="用户名" prop='username'>
      <el-input v-model="form.username" placeholder="请输入用户登陆名"></el-input>
    </el-form-item>
    <el-form-item label="登录密码" prop='password'>
      <el-input v-model="form.password" type='password' placeholder="请输入用户登录密码"></el-input>
    </el-form-item>
    <el-form-item label="用户姓名" prop='realName'>
      <el-input v-model="form.realName" placeholder="请输入用户姓名"></el-input>
    </el-form-item>
    <el-form-item label="用户邮箱" prop='email'>
      <el-input v-model="form.email" placeholder="请输入用户邮箱"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop='mobile'>
      <el-input v-model="form.mobile" placeholder="请输入联系电话"></el-input>
    </el-form-item>
    <el-form-item label="用户状态">
      <el-radio-group v-model="form.isActive">
        <el-radio :label="0">禁用</el-radio>
        <el-radio :label="1">正常</el-radio>
      </el-radio-group>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" @click="verify(form)">保存</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
import {usernameValidate, realNameValidate, passwordValidate, emailValidate, mobileValidate} from './validate'
export default {
  props: {
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { adminUserId: '', roleId: '', realName: '', username: '', password: '', email: '', mobile: '', isActive: 1 },
      rule: {
        username: [{validator: usernameValidate, required: true, trigger: 'blur'}],
        realName: [{validator: realNameValidate, required: true, trigger: 'blur'}],
        password: [{validator: passwordValidate, required: true, trigger: 'blur'}],
        email: [{validator: emailValidate, trigger: 'blur'}],
        mobile: [{validator: mobileValidate, trigger: 'blur'}]
      }
    }
  },
  watch: {
    handler: function (val) {
      this.visible = val === 'ADD'
    }
  },
  methods: {
    ...mapActions('action', ['addAdminUser']),
    verify(form) {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.save(form)
        } else {
          return false
        }
      })
    },
    async save(form) {
      let response = await this.addAdminUser(form)
      if (response.code === '1') {
        this.$msg.success('保存成功')
      } else {
        this.$msg.error('保存失败：', response.msg)
      }
      this.$emit('save', response.code)
      this.form = { adminUserId: '', roleId: '', realName: '', username: '', password: '', email: '', mobile: '', isActive: 1 }
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
