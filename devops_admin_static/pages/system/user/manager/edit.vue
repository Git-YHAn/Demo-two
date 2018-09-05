<template>
<el-dialog title="编辑" :visible.sync="visible" @close="reset">
  <el-form :model="form" label-width="80px" ref='form' :rules='rule'>
    <el-form-item label="用户名" prop='username'>
      <el-input v-model="form.username" :disabled='true'></el-input>
    </el-form-item>
    <el-form-item label="登录密码" prop='password'>
      <el-input type='password' v-model="form.password"></el-input>
    </el-form-item>
    <el-form-item label="用户姓名" prop='realName'>
      <el-input v-model="form.realName" :autofocus='true'></el-input>
    </el-form-item>
    <el-form-item label="用户邮箱" prop='email'>
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop='mobile'>
      <el-input v-model="form.mobile"></el-input>
    </el-form-item>
    <el-form-item label="用户状态">
      <el-radio-group v-model="form.isActive">
        <el-radio :label="0">禁用</el-radio>
        <el-radio :label="1">正常</el-radio>
      </el-radio-group>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" @click="verify(form)" :disabled='this.obj.adminUserId === 1?true:false'>保存</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
import {realNameValidate, passwordValidate, emailValidate, mobileValidate} from './validate'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { adminUserId: '', roleId: '', realName: '', username: '', password: '', email: '', mobile: '', isActive: 1 },
      rule: {
        realName: [{ validator: realNameValidate, required: true, trigger: 'blur' }],
        password: [{ validator: passwordValidate, required: true, trigger: 'blur' }],
        email: [{ validator: emailValidate, required: true, trigger: 'blur' }],
        mobile: [{ validator: mobileValidate, trigger: 'blur' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'EDIT'
      if (this.visible) {
        let response = await this.getAdminUser(this.obj.adminUserId)
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', ['getAdminUser', 'updateAdminUser']),
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
      let response = await this.updateAdminUser(form)
      if (response.code === '1') {
        this.$msg.success('保存成功')
        this.$emit('save', response.code)
      } else {
        this.$msg.error('保存失败：', response.msg)
        this.$emit('reset')
      }
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
