<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" label-width="80px" ref='form' :rules='rule'>
      <el-form-item label="角色名" prop='roleName'>
        <el-input v-model="form.roleName" :autofocus='true' placeholder="请输入角色名"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop='description'>
        <el-input type="textarea" autosize v-model="form.description" placeholder="描述哟"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="verify(form)">保存</el-button>
      <el-button @click="reset('form')">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
import {roleNameValidate, descValidate} from './validate'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { roleId: '', roleName: '', description: '' },
      rule: {
        roleName: [{validator: roleNameValidate, required: true, trigger: 'blur'}],
        description: [{validator: descValidate, trigger: 'blur'}]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'EDIT'
      this.form = { roleId: this.obj.roleId, roleName: this.obj.roleName, description: this.obj.description }
    }
  },
  methods: {
    ...mapActions('action', ['updateRole']),
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
      let response = await this.updateRole(form)
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
