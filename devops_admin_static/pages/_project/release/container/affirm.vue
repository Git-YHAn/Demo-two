<template>
  <el-dialog title="确认发布" :visible.sync="visible" v-loading="loading" @close="reset">
    <el-form :model="form" ref='form' label-width="110px">
      <el-form-item label="应用名称">
        <label slot="label"><b>应用名称：</b></label>
        <el-tooltip :enterable="false" placement="top-start" v-for="tag in obj" :key="tag.serverName">
          <div slot="content" v-for="server in tag.assetsHosts">{{ server.assetsName }}<br/></div>
          <el-tag  size="medium" type="info">
            {{tag.appName}}
          </el-tag>
        </el-tooltip>
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
      <el-button type="primary" :disabled="this.code.replace(/(^\s*)|(\s*$)/g, '') !== ''?false:true" @click="save(form)">发布</el-button>
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
    obj: Array,
    handlers: String
  },
  data() {
    return {
      visible: false,
      isCompare: false,
      form: {},
      loading: false,
      code: '',
      codeOptions: {
        lineNumbers: false,
        connect: 'align',
        orig: ''
      }
    }
  },
  watch: {
    handlers: async function(val) {
      console.log(this.obj)
      this.visible = val === 'AFFIRM'
    }
  },
  methods: {
    ...mapActions('action', ['addFile', 'getAppEnvEnevnt', 'pushBranch', 'savePublishApp']),
    async save(form) {
      this.obj.forEach(async item => {
        item.publishInfo = this.code
        await this.savePublishApp(item)
      })
      this.$emit('save')
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
