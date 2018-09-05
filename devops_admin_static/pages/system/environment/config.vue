<template>
  <el-dialog title="配置" :visible.sync="visible" @close="reset">
    <el-form ref='configure' label-width="80px" :rules='rule'>
      <el-form-item label="环境名称"> <span>{{form.envName}}</span> </el-form-item>
      <el-form-item label="项目名称">
        <el-select v-model="proId" clearable placeholder="请选择" @change="onSubmit()">
          <el-option v-for='item in pros' :label='item.proName' :key="item.proId" :value='item.proId'></el-option>
        </el-select>
      </el-form-item>
      <el-table :data="apps" border ref="multipleTable"  height='300px' @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" prop="appId" reserve-selection> </el-table-column>
        <el-table-column prop="appName" label="应用名称" min-width="30px" min-height="20px" style="text-align:center"> </el-table-column>
        <el-table-column prop="appCode" label="应用编码" min-width="30px"> </el-table-column>
        <el-table-column type="index" :index="runEnv" label="运行环境" width="200px"> </el-table-column>
      </el-table>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="save()">保存</el-button>
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
      proId: '',
      pros: [],
      apps: [],
      multipleSelection: [],
      form: { envId: '', envName: '', priority: '', description: '' },
      rule: {
        envName: [{ required: true, message: '不能为空', trigger: 'blur' }],
        priority: [{ required: true, message: '不能为空', trigger: 'blur' }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'CONFIG'
      if (this.visible) {
        this.proId = ''
        this.apps = []
        this.form.envName = this.obj.envName
        this.form.envId = this.obj.envId
        let response = await this.searchPros()
        this.pros = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', [ 'updateEnv', 'getEnv', 'searchPros', 'serachNosetApp', 'setApp' ]),
    async save() {
      if (this.multipleSelection.length === 0) {
        this.$message('未做任何设置')
      } else {
        let configInfo = { 'envId': this.obj.envId, 'appId': this.multipleSelection, 'proId': this.proId }
        let response = await this.setApp(configInfo)
        if (response.code === '1') {
          this.$msg.success('保存成功')
          this.$emit('save', response.code)
        } else {
          this.$msg.error('保存失败：', response.msg)
          this.$emit('reset')
        }
      }
    },
    handleSelectionChange(val) {
      let num = []
      for (let i = 0; i < val.length; i++) {
        num.push(val[i].appId)
      }
      this.multipleSelection = num
    },
    async onSubmit() {
      let response = await this.serachNosetApp({'proId': this.proId, 'envId': this.form.envId})
      if (response.code === '1') {
        this.apps = response.data
      }
      this.$refs.multipleTable.clearSelection()
    },
    runEnv(type) {
      if (this.apps[type].appType === 0) {
        return 'tomcat'
      } else if (this.apps[type].appType === 1) {
        return 'jdk7'
      } else if (this.apps[type].appType === 2) {
        return 'jdk8'
      }
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
