<template>
  <el-dialog title="编辑" :visible.sync="visible" @close="reset">
    <el-form :model="form" label-width="110px" :rules='rule' ref='form'>
      <el-form-item label="服务器名称" prop='assetsName'>
        <el-input v-model="form.assetsName" :autofocus='true' placeholder="请输入名称"></el-input>
      </el-form-item>
      <el-form-item label="服务器类型" prop='assetsType'>
        <el-select v-model="form.assetsType" placeholder="请选择" clearable>
          <el-option v-for="item in hostType" :key='item.assetsType' :label="item.assetsTypeName"
                     :value="item.assetsType">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="服务器状态" prop='assetsStatus'>
        <el-radio-group v-model="form.assetsStatus">
          <el-radio :label="0">可用</el-radio>
          <el-radio :label="1" :disabled="form.used != 0">不可用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="服务器地址" prop='sshAddress'>
        <el-input autosize v-model="form.sshAddress" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="服务器端口号" prop='sshPort'>
        <el-input v-model="form.sshPort" placeholder="请输入端口号"></el-input>
      </el-form-item>
      <el-form-item label="操作系统">
        <el-input v-model="form.operateSystem"></el-input>
      </el-form-item>
      <el-form-item label="内存">
        <el-input v-model="form.memory"></el-input>
      </el-form-item>
      <el-form-item label="系统CPU">
        <el-input v-model="form.cpu"></el-input>
      </el-form-item>
      <el-form-item label="磁盘大小">
        <el-input v-model="form.disk"></el-input>
      </el-form-item>
      <el-form-item label="外网IP" prop='outerIp'>
        <el-input v-model="form.outerIp"></el-input>
      </el-form-item>
      <el-form-item label="内网IP" prop='innerIp'>
        <el-input v-model="form.innerIp"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="verify(form)">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import {mapActions} from 'vuex'
  import {
    assetsNameValidate,
    sshAddressValidate,
    innerIpValidate,
    outterIpValidate,
    sshPortValidate,
    hostAccountValidate
  } from './validate'

  export default {
    props: {
      obj: Object,
      handler: String
    },
    data() {
      return {
        visible: false,
        hostType: [
          {assetsType: 0, assetsTypeName: '物理机'},
          {assetsType: 1, assetsTypeName: '云服务器'},
          {assetsType: 2, assetsTypeName: '容器'}
        ],
        form: {
          assetsId: '',
          assetsName: '',
          assetsType: '',
          sshPort: '',
          sshAddress: '',
          operateSystem: '',
          memory: '',
          cpu: '',
          disk: '',
          outerIp: '',
          innerIp: '',
          assetsStatus: 0,
          used: 0
        },
        rule: {
          assetsName: [{validator: assetsNameValidate, required: true}],
          assetsType: [{message: '请选择服务器类型', required: true}],
          assetsStatus: [{message: '不能为空', required: true}],
          sshPort: [{validator: sshPortValidate, required: true}],
          sshAddress: [{validator: sshAddressValidate, required: true}],
          hostAccount: [{validator: hostAccountValidate, required: true}],
          hostPassword: [{message: '不能为空', required: true}],
          outerIp: [{validator: outterIpValidate}],
          innerIp: [{validator: innerIpValidate}]
        }
      }
    },
    watch: {
      handler: async function(val) {
        this.visible = val === 'EDIT'
        if (this.visible) {
          let response = await this.getServer(this.obj.assetsId)
          this.form = response.data
        }
      }
    },
    methods: {
      ...mapActions('action', ['searchServers', 'updateServer', 'getServer']),
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
