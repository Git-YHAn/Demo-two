<template>
<div>
  <div style="">
    <h4>基础配置</h4>
    <el-form label-width="140px" :model="formSysParam" class="div-form">
      <el-form-item label="ApiKey secret">
        <el-input v-model="formSysParam.loginSinging" class="div-form-input" @change="appendNewSysParameter('loginSinging', formSysParam.loginSinging)"></el-input>
      </el-form-item>
      <el-form-item label="登录超时时间">
        <el-input v-model="formSysParam.loginTimeout" class="div-form-input" @change="appendNewSysParameter('loginTimeout', formSysParam.loginTimeout)"></el-input>
        <span class="div-form-input-span">单位： 秒</span>
      </el-form-item>
      <el-form-item label="Token有效时间">
        <el-input v-model="formSysParam.loginExpireTime" class="div-form-input" @change="appendNewSysParameter('loginExpireTime', formSysParam.loginExpireTime)"></el-input>
        <span class="div-form-input-span">单位： 秒</span>
      </el-form-item>
      <el-form-item label="Tomcat检测时长">
        <el-input v-model="formSysParam.tomcatCheckTime" class="div-form-input" @change="appendNewSysParameter('tomcatCheckTime', formSysParam.tomcatCheckTime)"></el-input>
        <span class="div-form-input-span">单位： 秒</span>
      </el-form-item>
    </el-form>
  </div>
  <div class="div-line"></div>
  <div>
    <h4>路径配置</h4>
    <el-form label-width="140px" :model="formSysParam" class="div-form">
      <el-form-item label="应用版本路径">
        <el-input v-model="formSysParam.applicationVersionHomePath" class="div-form-input" @change="appendNewSysParameter('applicationVersionHomePath', formSysParam.applicationVersionHomePath)"></el-input>
      </el-form-item>
      <el-form-item label="配置版本路径">
        <el-input v-model="formSysParam.configVersionHomePath" class="div-form-input" @change="appendNewSysParameter('configVersionHomePath', formSysParam.configVersionHomePath)"></el-input>
      </el-form-item>
      <el-form-item label="发布版本路径">
        <el-input v-model="formSysParam.deployVersionHomePath" class="div-form-input" @change="appendNewSysParameter('deployVersionHomePath', formSysParam.deployVersionHomePath)"></el-input>
      </el-form-item>
      <el-form-item label="发布日志路径">
        <el-input v-model="formSysParam.deployLogPath" class="div-form-input" @change="appendNewSysParameter('deployLogPath', formSysParam.deployLogPath)"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="div-line"></div>
  <div>
    <h4>GITLAB配置</h4>
    <el-form label-width="140px" :model="formSysParam" class="div-form">
      <el-form-item label="GITLAB默认分支">
        <el-input v-model="formSysParam.gitlabRepoBranch" class="div-form-input" @change="appendNewSysParameter('gitlabRepoBranch', formSysParam.gitlabRepoBranch)"></el-input>
      </el-form-item>
      <el-form-item label="GITLAB默认用户">
        <el-input v-model="formSysParam.gitlabUsername" class="div-form-input" @change="appendNewSysParameter('gitlabUsername', formSysParam.gitlabUsername)"></el-input>
      </el-form-item>
      <el-form-item label="GITLAB默认密码">
        <el-input v-model="formSysParam.gitlabPassword" class="div-form-input" type="password" @change="appendNewSysParameter('gitlabPassword', formSysParam.gitlabPassword)"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="div-line"></div>
  <div>
    <h4>KSC配置</h4>
    <el-form label-width="140px" :model="formSysParam" class="div-form">
      <el-form-item label="KSC访问密钥">
        <el-input v-model="formSysParam.kscAccessKey" class="div-form-input" @change="appendNewSysParameter('kscAccessKey', formSysParam.kscAccessKey)"></el-input>
      </el-form-item>
      <el-form-item label="KSC私有密钥">
        <el-input v-model="formSysParam.kscPrivateKey" class="div-form-input" @change="appendNewSysParameter('kscPrivateKey', formSysParam.kscPrivateKey)"></el-input>
      </el-form-item>
      <el-form-item label="KSC节点地址">
        <el-input v-model="formSysParam.kscEndpoint" class="div-form-input" @change="appendNewSysParameter('kscEndpoint', formSysParam.kscEndpoint)"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <div class="div-line"></div>
  <div>
    <h4>KSC配置</h4>
    <el-form label-width="140px" :model="formSysParam" class="div-form">
      <el-form-item label="备份日志路径">
        <el-input v-model="formSysParam.backLogPath" class="div-form-input" @change="appendNewSysParameter('backLogPath', formSysParam.backLogPath)"></el-input>
      </el-form-item>
    </el-form>
  </div>
  <el-footer style="position: relative; text-align: center">
    <el-button @click="resetUpdateParamForm">重置</el-button>
    <el-button type="primary" @click="updateSysParameter">保存</el-button>
  </el-footer>
</div>
</template>
<script type='text/javascript'>
import { mapActions } from 'vuex'

export default {
  data() {
    return {
      formSysParam: {},
      updateParamForm: {}
    }
  },
  computed: {},
  methods: {
    ...mapActions('action', ['searchAllOpsSysParameters', 'saveOpsSysParameter', 'updateOpsSysParameter']),
    async searchSysParameter() {
      let response = await this.searchAllOpsSysParameters()
      if (response.code === '1') {
        this.formSysParam = response.data
      }
    },
    appendNewSysParameter(parameterName, value) {
      this.$set(this.updateParamForm, parameterName, value)
    },
    resetUpdateParamForm() {
      this.updateParamForm = {}
      this.searchSysParameter()
    },
    async updateSysParameter() {
      let updateParamForm = this.updateParamForm
      if (updateParamForm === null || updateParamForm === {}) {
        this.$msg.warning('未修改，无法进行保存！')
        return
      }
      this.resetUpdateParamForm()
      let response = await this.updateOpsSysParameter(updateParamForm)
      if (response.code === '1') {
        this.$msg.success('保存系统参数成功！')
      } else {
        this.$msg.warning('保存系统参数失败：' + response.msg)
      }
    }
  },
  mounted() {
    this.$store.commit('HIDE_SIDEBAR')
    this.searchSysParameter()
  },
  destroyed() {
    this.formSysParam = {}
    this.updateParamForm = {}
  }
}
</script>

<style>
.div-form {
  margin-left: 30px;
  font-size: 10px;
}

.div-form-input {
  width: 60%;
}

.div-form-input-span {
  margin-left: 10px;
}

.div-line {
  height: 1px;
  border: none;
  border-top: 1px solid #ebeef5;
  margin-bottom: 20px;
  margin-top: 20px;
}
</style>
