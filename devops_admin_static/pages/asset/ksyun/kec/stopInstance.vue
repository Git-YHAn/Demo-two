<template>
<el-dialog title="关闭服务器" :visible.sync="visible" @close="reset">
  <span>是否确定关闭以下{{instances.length}}台服务器？</span>
  <el-table :data="instances">
    <el-table-column label="服务器名称" prop="instanceName" min-width="120" align="left" ></el-table-column>
    <el-table-column label="操作系统" prop="image.platform" min-width="150" align="left" ></el-table-column>
    <el-table-column label="内网地址" prop="privateIpAddress" min-width="200" align="left"></el-table-column>
  </el-table>
  <div class="shutdown-box">
    <div class="md-radio-inline">
      选择关闭方式：
      <div class="md-radio">
        <input type="radio" id="radio0" v-model="forceStop" value="false" class="md-radiobtn">
        <label for="radio0"><span class="check"></span><span class="box"></span> 关闭 </label>
      </div>
      <div class="md-radio">
        <input type="radio" id="radio1" v-model="forceStop" value="true" class="md-radiobtn">
        <label for="radio1"><span class="check"></span><span class="box"></span> 强制关闭 </label>
      </div>
    </div>
  </div>
  <div slot="footer">
    <el-button type="primary" :loading='loading' :disabled='loading' @click="confirm">确认</el-button>
    <el-button @click="reset" :disabled='loading'>取消</el-button>
  </div>
</el-dialog>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    instances: Array
  },
  data() {
    return {
      forceStop: false,
      visible: true,
      loading: false
    }
  },
  methods: {
    ...mapActions('action', ['stopKsyunKecInstance']),
    async confirm() {
      this.loading = true
      let instanceIds = this.instances.map(item => {
        return item.instanceId
      })
      let forceStop = this.forceStop
      let response = await this.stopKsyunKecInstance({ forceStop, instanceIds })
      if (response.code === '1') {
        this.$emit('save', response.data)
      } else {
        this.$msg.error(response.msg)
      }
      this.loading = false
    },
    reset() {
      this.$emit('reset')
    }
  }
}
</script>
<style scoped>
.shutdown-box {
  margin-top: 10px;
  font-size: 12px;
}
</style>
