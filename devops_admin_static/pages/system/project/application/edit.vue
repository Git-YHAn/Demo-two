<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="100px" :rules='rule' @keyup.enter.native="save(form)">
      <el-form-item label="应用名称" prop='appName'>
        <el-input v-model="form.appName" :autofocus='true' placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="运行环境" label-width="100px" prop='appType'>
        <el-select v-model="form.appType" placeholder="请选择">
          <el-option v-for="item in runData" :key="item.type" :label="item.name" :value="item.type"> </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="应用类型" prop='appTypeName'>
        <el-select  v-model="form.appTypeName" @change="appTypeEnvt" clearable placeholder="请选择">
          <el-option v-for="item in appTypeData" :key="item.appTypeId" :label="item.appTypeName" :value="item.appTypeId"> </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="应用编码" prop='appCode'>
        <el-input v-model="form.appCode" placeholder="请输入编码"  :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="git库地址" prop='appGitUrl'>
        <el-input v-model="form.appGitUrl" placeholder="请输入应用的git地址"></el-input>
      </el-form-item>
      <el-form-item label="日志路径" prop='logPath'>
        <el-input v-model="form.logPath" placeholder="请输入日志路径"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" autosize v-model="form.description" placeholder="请输入描述"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="verify(form)">保存</el-button>
      <el-button @click="reset">取消</el-button>
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
      visible: false,
      appTypeData: [],
      runData: [{ type: 0, name: 'tomcat' },
        { type: 1, name: 'jdk7' },
        { type: 2, name: 'jdk8' }],
      form: { proId: '', proName: '', description: '', appGitUrl: '' },
      rule: {
        appName: [{ required: true, message: '不能为空' }],
        appType: [{ required: true, message: '请选择运行环境' }],
        appTypeName: [{ required: true, message: '请选择应用类型' }],
        appGitUrl: [{ required: true, message: '不能为空' }],
        appCode: [{ required: true, message: '不能为空' }],
        logPath: [{ required: true, message: '不能为空' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'EDIT'
      if (this.visible) {
        let response = await this.getApp(this.obj.appId)
        this.form = response.data
      }
      let res = await this.searchAppTypes()
      if (~~res.code === 1) {
        this.appTypeData = res.data
      }
    }
  },
  methods: {
    ...mapActions('action', [ 'updateApp', 'getApp', 'searchAppTypes' ]),
    appTypeEnvt(val) {
      if (val) {
        let obj = this.appTypeData.find((item) => {
          return item.appTypeId === val
        })
        this.form.appTypeName = obj.appTypeName
        this.form.appTypeId = obj.appTypeId
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
    async save(form) {
      let response = await this.updateApp(form)
      if (response.code === '1') {
        this.$msg.success('保存成功')
      } else {
        this.$msg.error('保存失败：', response.msg)
      }
      this.reset()
    },
    reset() {
      this.$emit('reset')
      this.appTypeData = null
    }
  }
}
</script>
