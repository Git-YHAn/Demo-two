<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="80px" :rules='rule'>
      <el-form-item label="角色名" prop='roleName'>
        <el-input v-model="form.roleName" :autofocus='true' placeholder="请输入角色名"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop='description'>
        <el-input v-model="form.description" placeholder="描述哟"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="verify(form)" >保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import { mapActions } from 'vuex'
  import {roleNameValidate, descValidate} from './validate'
  export default {
    props: {
      handler: String
    },
    data() {
      return {
        visible: false,
        form: { roleName: '', description: '' },
        rule: {
          roleName: [{ validator: roleNameValidate, required: true }],
          description: [{ validator: descValidate }]
        }
      }
    },
    watch: {
      handler: function (val) {
        this.visible = val === 'ADD'
      }
    },
    methods: {
      ...mapActions('action', ['addRole']),
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
        let response = await this.addRole(form)
        if (response.code === '1') {
          this.$msg.success('保存成功')
        } else {
          this.$msg.error('保存失败：', response.msg)
        }
        this.$emit('save', response.code)
        this.form = { roleName: '', description: '' }
      },
      reset() {
        this.$emit('reset')
      }
    }
  }
</script>
