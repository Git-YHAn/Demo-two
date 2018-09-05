<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" label-width="80px" :rules='rule' ref='form'>
      <el-form-item label="环境名称" prop='envName'>
        <el-input v-model="form.envName" :autofocus='true' ></el-input>
      </el-form-item>
      <el-form-item label="优先级" prop='priority'>
        <el-input v-model="form.priority" ></el-input>
      </el-form-item>
      <el-form-item label="环境描述" prop='description'>
        <el-input type="textarea" autosize v-model="form.description"></el-input>
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
import { envNameValidate, priorityValidate } from './validate'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { envId: '', envName: '', priority: '', description: '' },
      rule: {
        envName: [{ validator: envNameValidate, required: true }],
        priority: [{ validator: priorityValidate, required: true }],
        description: [{ max: 50, message: '描述不超过50个字' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'EDIT'
      if (this.visible) {
        let response = await this.getEnv(this.obj.envId)
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', [ 'updateEnv', 'getEnv' ]),
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
      let response = await this.updateEnv(form)
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
