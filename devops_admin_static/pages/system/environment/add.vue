<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form ref='form' :model="form" label-width="80px" :rules='rule'>
      <el-form-item label="环境名称" prop='envName'>
        <el-input v-model="form.envName" :autofocus='true' placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="环境编码" prop='envCode'>
        <el-input v-model="form.envCode" placeholder="请输入编码"></el-input>
      </el-form-item>
      <el-form-item label="优先级" prop='priority'>
        <el-input v-model="form.priority" placeholder="请输入优先级"></el-input>
      </el-form-item>
      <el-form-item label="环境描述" prop='description'>
        <el-input type="textarea" autosize v-model="form.description" placeholder="请输入描述"></el-input>
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
import { envNameValidate, envCodeValidate, priorityValidate } from './validate'
export default {
  props: {
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { envName: '', envCode: '', priority: '', description: '' },
      rule: {
        envName: [{ validator: envNameValidate, required: true }],
        envCode: [{ validator: envCodeValidate, required: true }],
        priority: [{ validator: priorityValidate, required: true }],
        description: [{ max: 50, message: '描述不超过50个字' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ADD'
    }
  },
  methods: {
    ...mapActions('action', ['addEnv']),
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
      let response = await this.addEnv(form)
      if (response.code === '1') {
        this.$msg.success('保存成功')
      } else {
        this.$msg.error('保存失败：', response.msg)
      }
      this.$emit('save', response.code)
      this.form = {}
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
