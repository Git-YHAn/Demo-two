<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' :rules='rule' label-width="120px">
      <el-form-item label="模板名称" prop="tplName" label-width="100px">
        <el-input v-model="form.tplName" autofocus></el-input>
      </el-form-item>
      <el-form-item label="模板类型" prop="tplType" label-width="100px">
        <el-input v-model="form.tplType"></el-input>
      </el-form-item>
      <el-form-item label="模板描述" label-width="100px">
        <el-input v-model="form.tplDescription"></el-input>
      </el-form-item>
      <el-form-item label="模板内容" prop="tplContent" label-width="100px">
        <template>
          <no-ssr>
            <codemirror v-model="form.tplContent"></codemirror>
          </no-ssr>
        </template>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="verify(form)">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import {mapActions} from 'vuex'
  import { execContentValidate } from '../validate'

  export default {
    props: {
      addTplHandler: String
    },
    data() {
      return {
        visible: false,
        form: {tplContent: ''},
        rule: {
          tplName: [{message: '不能为空！', required: true, trigger: 'blur'}],
          tplType: [{message: '不能为空！', required: true, trigger: 'blur'}],
          tplContent: [{validator: execContentValidate, required: true, trigger: 'blur'}]
        }
      }
    },
    watch: {
      addTplHandler: function (val) {
        this.visible = val === 'ADD_TEMPLATE'
      }
    },
    methods: {
      ...mapActions('action', ['addExecuteTemplate']),
      reset() {
        this.$emit('reset')
      },
      verify(form) {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.saveTemplate(form)
          } else {
            return false
          }
        })
      },
      async saveTemplate(newTemplate) {
        let response = await this.addExecuteTemplate(newTemplate)
        if (response.code === '1') {
          this.$msg.success('保存成功')
        } else {
          this.$msg.error('保存失败:', response.data)
        }
        this.form = {tplContent: ''}
        this.reset()
      }
    }
  }
</script>
