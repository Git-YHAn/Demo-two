<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="130px" :rules='rule'>
      <el-form-item label="环境名称"><span>{{this.form.envName}}</span></el-form-item>
      <el-form-item label="项目名称"><span>{{this.form.proName}}</span></el-form-item>
      <el-form-item label="应用名称"><span>{{this.form.appName}}</span></el-form-item>
      <el-form-item label="应用类型"><span> {{this.form.appTypeName}}</span></el-form-item>
      <!-- WEB 应用 -->
      <span v-if="form.appTypeId === 200 || form.appTypeId === 400">
         <el-form-item label="web端口" prop='msPort'>
          <el-input v-model.number="form.msPort"></el-input>
        </el-form-item>
      </span>
      <el-form-item label="标签" prop='labels'>
        <el-input v-model.trim="form.labels"></el-input>
      </el-form-item>
      <el-form-item label="名称空间" prop='namespace'>
        <el-input v-model.trim="form.namespace"></el-input>
      </el-form-item>
      <el-form-item label="配置git库地址" prop='configGitUrl'>
        <el-input v-model.trim="form.configGitUrl"></el-input>
      </el-form-item>
      <el-form-item label="发布包git库地址" prop='deployAppGitUrl'>
        <el-input v-model.trim="form.deployAppGitUrl"></el-input>
      </el-form-item>
      <!--微服务信息-->
      <span v-if="form.appTypeId === this.microServiceApp">
        <el-form-item label="微服务端口" prop='msPort'>
          <el-input v-model.number="form.msPort"></el-input>
        </el-form-item>
        <el-form-item label="微服务注册中心" prop='msEurekaAddress'>
          <el-input v-model="form.msEurekaAddress"></el-input>
        </el-form-item>
        <el-form-item label="微服务配置地址" prop='msConfigUrl'>
          <el-input v-model="form.msConfigUrl"></el-input>
        </el-form-item>
        <el-form-item label="微服务Profile" prop="profileTypeName">
         <el-select v-model="selectProfileTypeId">
           <el-option v-for="item in optionalProfileTypes"
                      :key="item.profileTypeId"
                      :label="item.profileTypeName"
                      :value="item.profileTypeId">
           </el-option>
         </el-select>
        </el-form-item>
        <el-form-item label="微服务Zone" prop='msZoneTypeName'>
          <el-select v-model="selectZoneTypeId">
           <el-option v-for="item in optionalZoneTypes"
                      :key="item.zoneTypeId"
                      :label="item.zoneTypeName"
                      :value="item.zoneTypeId">
           </el-option>
         </el-select>
        </el-form-item>
        <el-form-item label="微服务Region" prop='msRegionTypeName'>
          <el-select v-model="selectRegionTypeId" >
           <el-option v-for="item in optionalRegionTypes"
                      :key="item.regionTypeId"
                      :label="item.regionTypeName"
                      :value="item.regionTypeId">
           </el-option>
         </el-select>
        </el-form-item>
      </span>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="save(form)">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
  </el-container>
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
          msProfileTypeId: null,
          msProfileTypeName: '',
          msZoneTypeId: null,
          msZoneTypeName: '',
          msRegionTypeId: null,
          msRegionTypeName: '',
          msConfigUrl: ''
        },
        rule: {
          appGitUrl: [{required: true, message: '不能为空', trigger: 'blur'}],
          configGitUrl: [{required: true, message: '不能为空', trigger: 'blur'}],
          deployAppGitUrl: [{required: true, message: '不能为空', trigger: 'blur'}]
        },
        microServiceApp: 300,
        optionalProfileTypes: [],
        optionalZoneTypes: [],
        optionalRegionTypes: [],
        selectProfileTypeId: null,
        selectZoneTypeId: null,
        selectRegionTypeId: null
      }
    },
    watch: {
      handler: async function (val) {
        this.visible = val === 'EDIT'
        if (this.visible) {
          let response = await this.getAppEnv(this.obj.appEnvId)
          this.form = response.data
          if (this.form.appTypeId === this.microServiceApp) {
            this.getOptionMicroTypes()
            this.selectProfileTypeId = this.form.msProfileTypeId
            this.selectZoneTypeId = this.form.msZoneTypeId
            this.selectRegionTypeId = this.form.msRegionTypeId
          }
        }
      }
    },
    methods: {
      ...mapActions('action', ['getAppEnv', 'updateAppEnv', 'queryAllMicroServiceTypes']),
      async save(form) {
        if (form.appTypeId === this.microServiceApp) {
          form.msProfileTypeId = this.selectProfileTypeId
          form.msRegionTypeId = this.selectRegionTypeId
          form.msZoneTypeId = this.selectZoneTypeId
        }
        let response = await this.updateAppEnv(form)
        if (response.code === '1') {
          this.$msg.success('保存成功')
          this.$emit('save', response.code)
        } else {
          this.$msg.error('保存失败：', response.msg)
          this.$emit('reset')
        }
      },
      reset() {
        this.$emit('reset')
      },
      async getOptionMicroTypes() {
        let response = await this.queryAllMicroServiceTypes()
        if (response.code === '1') {
          this.optionalProfileTypes = response.data.profileTypes
          this.optionalRegionTypes = response.data.regionTypes
          this.optionalZoneTypes = response.data.zoneTypes
        }
      }
    }
  }
</script>
