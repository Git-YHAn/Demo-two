<template>
<el-dialog title="确认提交" :visible.sync="visible" v-loading="loading" @close="reset">
  <el-form :model="form" ref='form' label-width="110px">
    <el-form-item>
      <label slot="label"><b>环境名称：</b></label>
      <span>{{this.form.envName}}</span>
    </el-form-item>
    <el-form-item>
      <label slot="label"><b>项目名称：</b></label>
      <span>{{this.form.proName}}</span>
    </el-form-item>
    <el-form-item label="应用名称">
      <label slot="label"><b>应用名称：</b></label>
      <span>{{this.form.appName}}</span>
    </el-form-item>
    <el-form-item label="分支名">
      <label slot="label"><b>分支名：</b></label>
      <span>{{this.obj.branchName}}</span>
    </el-form-item>
    <el-form-item label="提交信息">
      <label slot="label"><b>提交信息: </b></label>
      <el-main class="codewrapper file-content">
        <codemirror class="code-container fh" ref="codemirror" :merge="isCompare" v-model="code" :options="codeOptions">
        </codemirror>
      </el-main>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" :disabled="this.code.replace(/(^\s*)|(\s*$)/g, '') !== ''?false:true" @click="save(form)">保存</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import {
  mapActions
} from 'vuex'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      isCompare: false,
      form: {},
      loading: false,
      code: '',
      rule: {
        filePath: [{
          required: true,
          message: '不能为空',
          trigger: 'blur'
        }]
      },
      codeOptions: {
        lineNumbers: false,
        connect: 'align',
        orig: ''
      }
    }
  },
  watch: {
    handler: async function(val) {
      this.visible = val === 'COMMIT'
      let response = await this.getAppEnvEnevnt({
        'appId': ~~this.obj.appId,
        'envId': this.obj.envId
      })
      if (~~response.code === 1) {
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', ['addFile', 'getAppEnvEnevnt', 'pushBranch']),
    async save(form) {
      this.loading = true
      let response = await this.pushBranch({
        'appId': ~~this.obj.appId,
        'proId': this.obj.proId,
        'envId': this.obj.envId,
        'branchName': this.obj.branchName,
        'commitMessage': this.code
      })
      if (~~response.code === 1) {
        this.$emit('save')
        this.$msg.success('配置提交成功！')
        this.code = ''
      } else {
        this.$msg.error('配置提交失败：', response.msg)
      }
      this.loading = false
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
<style>
.file-content {
  height: 300px;
  overflow: auto;
}
</style>
