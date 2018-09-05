<template>
<el-dialog title="新增" :visible.sync="visible" @close="reset">
  <el-dialog :title='this.title' :visible.sync="dialogVisible" width="40%" append-to-body>
    <el-scrollbar ref="consoleScrollBar" tag="div" style="overflow-wrap: break-word;" wrap-class="el-select-dropdown__wrap" view-class="el-select-dropdown__list" :horizontal="false">
          <pre class="grid-content grid-content bg-purple-light" style="height:300px" >{{ this.message }}
          </pre>
    </el-scrollbar>
  </el-dialog>
  <el-form :model="form" ref='form' label-width="130px" :rules='rule'>
    <el-form-item label="应用名称" prop='appId'>
      <el-select v-model="form.appId" @change="getAppConfigVersionCode(form.appId)" placeholder="请选择">
        <el-option v-for="item in apps" :key="item.appId" :label="item.appName" :value="item.appId"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="应用版本" prop='appVerId'>
      <el-select id="appVersion" v-model="form.appVerId"  @change="appVersionInfo(form.appVerId)" clearable filterable>
        <option disabled value=""></option>
        <el-option v-for="item in appVersionData" :key="item.appVerId" :label="item.versionCode" :value="item.appVerId"></el-option>
      </el-select>
      <el-button type="text" @click="appOpen" :disabled='appInfo'>版本信息</el-button>
    </el-form-item>
    <el-form-item label="配置版本" prop='configVerId'>
      <el-select id="configVersion" v-model="form.configVerId"  @change="configVersionInfo(form.configVerId)" clearable filterable>
        <option disabled value=""></option>
        <el-option v-for="item in configVersionData" :key="item.configVerId" :label="item.versionCode" :value="item.configVerId"></el-option>
      </el-select>
      <el-button type="text" @click="configOpen" :disabled='configInfo' >版本信息</el-button>
    </el-form-item>
    <el-form-item label="描述" prop='description'>
      <el-input type="textarea" autosize v-model="form.description" placeholder="请输入描述"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer">
    <el-button type="primary" :loading='loading' :disabled='loading' @click="verify(form)">保存</el-button>
    <el-button @click="reset" :disabled='loading'>取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      apps: [],
      title: '',
      message: '',
      visible: false,
      loading: false,
      dialogVisible: false,
      appInfo: true,
      configInfo: true,
      appVersionData: [],
      configVersionData: [],
      form: {},
      rule: {
        appId: [{ required: true, message: '请选择应用' }],
        appVerId: [{ required: true, message: '请选择应用版本' }],
        configVerId: [{ required: true, message: '请选择配置版本' }],
        description: [{ required: true, message: '请输入描述' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ADD'
      if (this.visible) {
        this.form = {}
        this.queryAppsEvent()
      }
    }
  },
  methods: {
    ...mapActions('publish', ['saveReleaseVersion', 'queryAppsByProject', 'searchAppVersionByAppVerId', 'searchConfigVersionByAppVerId', 'getAppVersionCode', 'getConfigVersionCode', 'queryMakingVersion']),
    async queryAppsEvent() {
      let response = await this.queryAppsByProject(this.obj.proId)
      if (~~response.code === 1) {
        this.apps = response.data
      }
    },
    appVersionInfo: function(val) {
      if (val != null && val !== '') {
        this.appInfo = false
      } else {
        this.appInfo = true
      }
    },
    configVersionInfo: function(val) {
      if (val != null && val !== '') {
        this.configInfo = false
      } else {
        this.configInfo = true
      }
    },
    async appOpen() {
      let res = await this.searchAppVersionByAppVerId(this.form.appVerId)
      this.dialogVisible = true
      this.title = res.data.versionCode
      this.message = res.data.description
    },
    async configOpen() {
      let res = await this.searchConfigVersionByAppVerId(this.form.configVerId)
      this.dialogVisible = true
      this.title = res.data.versionCode
      this.message = res.data.description
    },
    async getAppConfigVersionCode(appId) {
      let appResponse = await this.getAppVersionCode({ 'appId': appId, 'proId': this.obj.proId })
      if (appResponse.code === '1') {
        this.appVersionData = appResponse.data
      }
      let configResponse = await this.getConfigVersionCode({ 'appId': appId, 'envId': this.obj.envId, 'proId': this.obj.proId })
      if (configResponse.code === '1') {
        this.configVersionData = configResponse.data
      }
    },
    async verify(form) {
      let response = await this.queryMakingVersion({
        proId: this.obj.proId,
        envId: this.obj.envId
      })
      response.data.forEach(item => {
        if (item.productionStatus === 100) {
          this.$msg.warning('批量制作正在执行，暂不能进行单独发布')
          this.$emit('reset')
          return false
        }
      })
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.save(form)
        } else {
          return false
        }
      })
    },
    async save(form) {
      this.loading = true
      let response = await this.saveReleaseVersion(Object.assign(form, this.obj))
      this.loading = false
      if (response.code === '1') {
        this.$msg.success('保存成功')
        this.$emit('save', response.code)
      } else {
        this.$msg.error('保存失败：', response.msg)
        this.$emit('reset')
      }
    },
    reset() {
      this.appInfo = true
      this.configInfo = true
      this.$emit('reset')
    }
  }
}
</script>
