<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="130px" :rules='rule'>
      <el-form-item label="应用名称" prop='appId'>
        <el-select v-model="form.appId" clearable placeholder="请选择">
          <el-option v-for="item in apps" :key="item.appId" :label="item.appName" :value="item.appId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="版本信息">
        <el-main class="codewrapper file-content">
          <codemirror class="code-container fh" ref="codemirror" v-model="code" :options="codeOptions">
          </codemirror>
        </el-main>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" :loading='loading' :disabled="this.code.replace(/(^\s*)|(\s*$)/g, '') !== ''?false:true" @click="verify(form)">保存</el-button>
      <el-button @click="reset" :disabled='loading'>取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      obj: Object,
      handler: String
    },
    data() {
      return {
        apps: [],
        visible: false,
        loading: false,
        code: '',
        form: {},
        codeOptions: {
          lineNumbers: false,
          connect: 'align',
          orig: ''
        },
        rule: {
          appId: [{required: true, message: '请选择应用'}]
        }
      }
    },
    watch: {
      handler: async function(val) {
        this.visible = val === 'ADD'
        if (this.visible) {
          this.form = {}
          this.queryAppsEvent()
        }
      }
    },
    methods: {
      ...mapActions('action', ['saveAppVersion', 'queryAppsByProject']),
      async queryAppsEvent() {
        let response = await this.queryAppsByProject(this.obj.proId)
        if (~~response.code === 1) {
          this.apps = response.data
        }
      },
      verify(form) {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.save(form)
          } else {
            return false
          }
        })
      },
      async save() {
        this.loading = true
        let response = await this.saveAppVersion({proId: this.obj.proId, appId: this.form.appId, description: this.code})
        this.loading = false
        if (response.code === '1') {
          this.$msg.success('保存成功')
          this.code = ''
          this.$emit('save', response.code)
        } else {
          this.$msg.error('保存失败：', response.msg)
        }
      },
      reset() {
        this.$emit('reset')
      }
    }
  }
</script>

<style>
  .file-content {
    height: 400px;
    overflow: auto;
  }
</style>
