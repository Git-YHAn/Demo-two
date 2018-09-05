<template>
<el-dialog title="新增配置" :visible.sync="visible" v-loading="loading" @close="reset">
  <el-form :model="form" label-width="80px" ref='form' :rules='rule'>
    <el-form-item label="文件路径" prop='filePath'>
      <el-input v-model="form.filePath" placeholder="请输入文件路径名称"></el-input>
    </el-form-item>
    <el-form-item label="文件内容" prop='fileContent'>
      <el-input v-model="form.fileContent" type="textarea" :autosize="{ minRows: 30, maxRows: 30}" placeholder="请输入文件内容"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" @click="save(form)">保存</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { Base64 } from 'js-base64'
import { mapActions } from 'vuex'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      form: {},
      loading: false,
      rule: {
        filePath: [{ required: true, message: '不能为空', trigger: 'blur' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ADD'
    }
  },
  methods: {
    ...mapActions('action', ['addFile']),
    async save(form) {
      this.loading = true
      let param = Object.assign(this.obj, this.form, { fileContent: Base64.encode(this.form.fileContent) })
      let response = await this.addFile(param)
      this.loading = false
      if (~~response.code === 1) {
        this.$emit('save')
        this.$msg.success('新增配置文件成功！')
        this.$emit('showPushBranchButton')
      } else {
        this.$msg.error('新增配置文件失败：', response.msg)
      }
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
