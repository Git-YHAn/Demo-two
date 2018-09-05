<template>
  <el-dialog title="详情" :visible.sync="visible" @close="reset">
    <el-form :model="form" label-width="120px">
      <el-form-item label="服务器名称">{{form.assetsName}}</el-form-item>
      <el-form-item label="服务器类型">{{form.assetsTypeName}}</el-form-item>
      <el-form-item label="服务器地址">{{form.sshAddress}}</el-form-item>
      <el-form-item label="端口号">{{form.sshPort}}</el-form-item>
      <el-form-item label="操作员">{{form.operateUserName}}</el-form-item>
      <el-form-item label="操作系统">{{form.operateSystem}}</el-form-item>
      <el-form-item label="内存">{{form.memory}}</el-form-item>
      <el-form-item label="CPU">{{form.cpu}}</el-form-item>
      <el-form-item label="磁盘">{{form.disk}}</el-form-item>
      <el-form-item label="内网ip">{{form.innerIp}}</el-form-item>
      <el-form-item label="外网ip">{{form.outerIp}}</el-form-item>
      <el-form-item label="创建时间">{{form.createTime}}</el-form-item>
      <el-form-item label="更新时间">{{form.updateTime}}</el-form-item>
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
      form: { assetsId: '', assetsName: '', assetsType: '', sshPort: '', sshAddress: '', operateSystem: '', memory: '', cpu: '', disk: '', outerIp: '', innerIp: '', assetsStatus: 0 }
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'DETAIL'
      if (this.visible) {
        let response = await this.getServer(this.obj.assetsId)
        this.form = response.data
      }
    }
  },
  methods: {
    ...mapActions('action', ['getServer']),
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
