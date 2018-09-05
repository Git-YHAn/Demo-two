<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' :rules='rule' label-width="120px">
      <el-form-item label="模板名称" prop="tplName" label-width="100px">
        <el-input v-model="form.tplName" clearable></el-input>
      </el-form-item>
      <el-form-item label="模板类型" prop="tplType" label-width="100px">
        <el-input v-model="form.tplType" clearable></el-input>
      </el-form-item>
      <el-form-item label="模板描述" label-width="100px">
        <el-input v-model="form.tplDescription" clearable></el-input>
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
      editTplHandler: String,
      currentTpl: Object
    },
    data() {
      return {
        visible: false,
        form: {tplName: '', tplType: '', tplContent: '', tplDescription: ''},
        rule: {
          tplName: [{message: '不能为空！', required: true, trigger: 'blur'}],
          tplType: [{message: '不能为空！', required: true, trigger: 'blur'}],
          tplContent: [{validator: execContentValidate, required: true, trigger: 'blur'}]
        }
      }
    },
    watch: {
      editTplHandler: function (val) {
        this.visible = val === 'EDIT_TEMPLATE'
        if (this.visible) {
          this.form = this.currentTpl
        }
      }
    },
    methods: {
      ...mapActions('action', ['updateExecuteTemplate']),
      reset() {
        this.$emit('reset')
      },
      verify(form) {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.update(form)
          } else {
            return false
          }
        })
      },
      async update(row) {
        let response = await this.updateExecuteTemplate(row)
        if (response.code === '1') {
          this.$msg.success('修改成功')
        } else {
          this.$msg.error('修改失败:' + response.data)
        }
        this.reset()
      }
    }
  }
</script>
