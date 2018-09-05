
<template>
  <el-dialog title="详情" :visible.sync="visible"  @close="reset">
    <el-form :model="form" ref='form'  label-width="120px">
      <el-form-item label="环境名称" prop='envName'>{{form.envName}}</el-form-item>
      <el-form-item label="项目名称" prop='proName'>{{form.proName}}</el-form-item>
      <el-form-item label="应用名称" prop='appName'>{{form.appName}}</el-form-item>
      <el-form-item label="应用类型"> <span> {{this.form.appTypeName}}</span> </el-form-item>
      <!-- WEB 应用 -->
      <span v-show="form.appTypeId === 200 || form.appTypeId === 400">
        <el-form-item label="web端口" prop='msPort'>{{form.msPort}}</el-form-item>
      </span>
      <el-form-item label="标签" prop='labels'>{{form.labels}}</el-form-item>
      <el-form-item label="命名空间" prop='namespace'>{{form.namespace}}</el-form-item>
      <el-form-item label="常规版本" prop='currentVersion'>{{form.currentVersion}}</el-form-item>
      <el-form-item label="配置git库地址" prop='configGitUrl'>{{form.configGitUrl}}</el-form-item>
      <el-form-item label="发布包git库地址" prop='deployAppGitUrl'>{{form.deployAppGitUrl}}</el-form-item>
      <el-form-item label="创建时间" prop='createTime'>{{form.createTime}}</el-form-item>
      <el-form-item label="修改时间" prop='updateTime'>{{form.updateTime}}</el-form-item>
      <span v-show="form.appTypeId === 300">
        <el-form-item label="微服务端口" prop='msPort'>{{form.msPort}}</el-form-item>
        <el-form-item label="微服务注册中心" prop='msEurekaAddr'>{{form.msEurekaAddress}}</el-form-item>
        <el-form-item label="微服务配置地址" prop='msConfigUrl'>{{form.msConfigUrl}}</el-form-item>
        <el-form-item label="微服务Profile" prop='msProfileCode'>{{form.msProfileTypeName}}</el-form-item>
        <el-form-item label="微服务Zone" prop='msZoneCode'>{{form.msZoneTypeName}}</el-form-item>
        <el-form-item label="微服务Region" prop='msRegionCode'>{{form.msRegionTypeName}}</el-form-item>
      </span>
    </el-form>
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
      visible: false,
      form: {
        envName: '',
        proName: '',
        appName: '',
        labels: '',
        namespace: '',
        configGitUrl: '',
        deployAppGitUrl: '',
        appTypeName: '',
        msPort: null,
        msEurekaAddress: '',
        msProfileTypeName: '',
        msZoneTypeName: '',
        msRegionTypeName: '',
        msConfigUrl: ''
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'DETAIL'
      if (this.visible) {
        let response = await this.getAppEnv(this.obj.appEnvId)
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', ['getAppEnv']),
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
