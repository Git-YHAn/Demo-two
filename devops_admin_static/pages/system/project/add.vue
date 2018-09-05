<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="80px" :rules='rule'>
      <el-form-item label="项目名称" prop='proName'>
        <el-input v-model="form.proName" :autofocus='true' placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="项目编码" prop='proCode'>
        <el-input v-model="form.proCode" placeholder="请输入编码"></el-input>
      </el-form-item>
      <el-form-item label="项目描述" prop='description'>
        <el-input type="textarea" autosize v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>
      <el-form-item label="webhook" prop='webHook'>
        <el-input type="textarea" autosize v-model="form.webHook" placeholder="请输入钉钉机器人的webHook"></el-input>
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
import { proNameValidate, proCodeValidate } from './validate'
export default {
  props: {
    handler: String
  },
  data() {
    return {
      visible: false,
      form: { proName: '', proCode: '', description: '' },
      rule: {
        proName: [{ validator: proNameValidate, required: true }],
        proCode: [{ validator: proCodeValidate, required: true }],
        description: [{ max: 50, message: '最大长度50个字符' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ADD'
    }
  },
  methods: {
    ...mapActions('action', ['addProject']),
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
      let response = await this.addProject(form)
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
