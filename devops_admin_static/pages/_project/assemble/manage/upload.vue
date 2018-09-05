<template>
  <el-dialog title="应用上传" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="110px">
      <el-form-item label="当前应用">
        <label>{{form.appName}}</label>
      </el-form-item>
      <el-form-item label="选择应用" style="margin-bottom: 0;">
        <el-upload
          ref="uploads"
          :limit=1
          style="float:left;margin:0 10px"
          :show-file-list=true
          action=""
          accept=".zip"
          :http-request="uploadZip"
          :auto-upload=false>
          <el-button type="primary">点击上传</el-button>
          <div slot="tip" style="color: #ff6266;font-size: small">*请选择当前应用的zip压缩包</div>
        </el-upload>
      </el-form-item>
      <el-form-item label="备注信息">
        <codemirror v-model="comments" :options="codeOptions"></codemirror>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="upload()" :loading="loading" :disabled='loading'>提交审核</el-button>
      <el-button @click="reset" :disabled='loading'>取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  export default {
    props: {
      obj: Object,
      handler: String
    },
    data() {
      return {
        visible: false,
        form: {},
        comments: '',
        loading: false,
        codeOptions: {
          lineNumbers: false
        }
      }
    },
    watch: {
      handler: async function (val) {
        this.visible = val === 'UPLOAD'
        this.form = this.obj
      }
    },
    methods: {
      async upload() {
        let uploadFiles = this.$refs.uploads.uploadFiles
        if (uploadFiles.length === 0) {
          this.$msg.error('请选择zip包!')
          return
        }

        if (!this.comments) {
          this.$msg.error('请输入审核信息!')
          return
        }

        this.$refs.uploads.submit()
      },
      // 上传zip压缩包
      async uploadZip(item) {
        this.$msg.success('应用处理中,请稍后...')
        this.loading = true
        let file = item.file
        let formData = new FormData()
        formData.append('file', file)
        formData.append('comments', this.comments)
        formData.append('appId', this.form.appId)
        formData.append('proId', this.form.proId)
        formData.append('appEnvId', this.form.appEnvId) // 2018/08/27
        this.$axios.setHeader('Content-Type', 'multipart/form-data', ['post'])
        this.$axios.setHeader('Authorization', this.$store.state.token)
        let response = await this.$axios.$post(`/appenv/zip/upload`, formData)
        if (response.code === '1') {
          this.reset()
        } else {
          this.loading = false
          this.$msg.error('导入失败:', response.msg)
          this.reset()
        }
      },
      reset() {
        this.comments = ''
        this.loading = false
        this.$refs.uploads.clearFiles()
        this.$emit('reset')
      }
    }
  }
</script>
