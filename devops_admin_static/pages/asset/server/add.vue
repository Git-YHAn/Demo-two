<template>
  <el-dialog title="新增" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' :rules='rule' label-width="120px">
      <el-form-item label="服务器名称" prop='assetsName'>
        <el-input v-model="form.assetsName" :autofocus='true' placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="服务器类型" prop='assetsType'>
        <el-select v-model="form.assetsType" placeholder="请选择" clearable>
          <el-option v-for="item in hostType" :key="item.assetsType" :label="item.assetsTypeName"
                     :value="item.assetsType">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="服务器状态" prop='assetsStatus'>
        <el-radio-group v-model="form.assetsStatus">
          <el-radio :label="0">可用</el-radio>
          <el-radio :label="1">不可用</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="服务器地址" prop='sshAddress'>
        <el-input autosize v-model="form.sshAddress" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="端口号" prop='sshPort'>
        <el-input autosize v-model="form.sshPort" placeholder="请输入端口号"></el-input>
      </el-form-item>
      <el-form-item label="服务器账号" prop='hostAccount'>
        <el-input autosize v-model="form.hostAccount" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="服务器密码" prop='hostPassword'>
        <el-input autosize v-model="form.hostPassword" type="password" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="外网IP" prop='outerIp'>
        <el-input autosize v-model="form.outerIp" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="内网IP" prop='innerIp'>
        <el-input autosize v-model="form.innerIp" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="操作系统">
        <el-input v-model="form.operateSystem" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="内存">
        <el-input autosize v-model="form.memory" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="系统CPU">
        <el-input autosize v-model="form.cpu" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item label="磁盘大小">
        <el-input autosize v-model="form.disk" placeholder="请输入"></el-input>
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
      handler: String
    },
    data() {
      return {
        visible: false,
        hostType: [
          {
            assetsType: '0',
            assetsTypeName: '物理机'
          }, {
            assetsType: '1',
            assetsTypeName: '云服务器'
          }, {
            assetsType: '2',
            assetsTypeName: '容器'
          }],
        form: {
          assetsName: '',
          assetsType: '',
          sshPort: '',
          sshAddress: '',
          hostAccount: '',
          hostPassword: '',
          operateSystem: '',
          memory: '',
          cpu: '',
          disk: '',
          outerIp: '',
          innerIp: '',
          assetsStatus: 0
        },
        rule: {
          assetsName: [{validator: assetsNameValidate, required: true, trigger: 'blur'}],
          assetsType: [{message: '请选择服务器类型', required: true, trigger: 'blur'}],
          assetsStatus: [{message: '不能为空！', required: true, trigger: 'blur'}],
          sshPort: [{validator: sshPortValidate, required: true, trigger: 'blur'}],
          sshAddress: [{validator: sshAddressValidate, required: true, trigger: 'blur'}],
          hostAccount: [{validator: hostAccountValidate, required: true, trigger: 'blur'}],
          hostPassword: [{message: '不能为空！', required: true, trigger: 'blur'}],
          outerIp: [{validator: outterIpValidate, required: true, trigger: 'blur'}],
          innerIp: [{validator: innerIpValidate, required: true, trigger: 'blur'}]
        }
      }
    },
    watch: {
      handler: function(val) {
        this.visible = val === 'ADD'
      }
    },
    methods: {
      ...mapActions('action', ['addServer']),
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
        let response = await this.addServer(form)
        if (response.code === '1') {
          this.$msg.success('保存成功')
        } else {
          this.$msg.error('保存失败：', response.msg)
        }
        this.$emit('save', response.code)
        this.form = {assetsStatus: 0}
      },
      reset() {
        this.$emit('reset')
      }
    }
  }
</script>
