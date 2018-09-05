<template>
  <el-dialog title="服务器账号" :visible.sync="visible" @close="reset">
    <el-form :model="form" label-width="110px" ref='form' :rules='rule'>
      <el-form-item label="服务器名称">{{form.assetsName}}</el-form-item>
      <el-form-item label="服务器类型">{{form.assetsTypeName}}</el-form-item>
      <el-form-item label="服务器状态">{{getTypeState(form.assetsStatus)}}</el-form-item>
      <el-form-item label="服务器地址">{{form.sshAddress}}</el-form-item>
      <el-form-item label="端口号">{{form.sshPort}}</el-form-item>
      <el-form-item label="用户名" prop='hostAccount'>
        <el-input autosize v-model="form.hostAccount" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop='hostPassword'>
        <el-input autosize type='password' v-model="form.hostPassword" placeholder="请输入"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="connect(form)" :loading='loading'>测试连接 </el-button>
      <el-button type="primary" @click="verify(form)">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
import { hostAccountValidate } from './validate'
export default {
  props: {
    obj: Object,
    handler: String
  },
  data() {
    return {
      visible: false,
      loading: false,
      form: { assetsId: '', assetsName: '', assetsType: '', sshPort: '', sshAddress: '', operateSystem: '', memory: '', cpu: '', disk: '', outerIp: '', innerIp: '', assetsStatus: 0 },
      rule: {
        hostAccount: [{ validator: hostAccountValidate, trigger: 'blur', required: true }],
        hostPassword: [{ message: '不能为空', trigger: 'blur', required: true }]
      }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'ACCOUNT'
      if (this.visible) {
        let response = await this.getServer(this.obj.assetsId)
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', [ 'getServer', 'updateServer', 'verifyServer' ]),
    getTypeState(type) {
      if (type === 0) {
        return '可用'
      } else if (type === 1) {
        return '不可用'
      } else {
        return ''
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
    async connect(form) {
      this.loading = true
      let response = await this.verifyServer(form)
      if (~~response.code === 1) {
        this.$msg.success('测试通过！')
      } else {
        this.$msg.error('连接失败！')
      }
      this.loading = false
    },
    async save(form) {
      let response = await this.updateServer(form)
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
    }
  }
}
</script>
