<template>
<el-dialog title="开启服务器" :visible.sync="visible" @close="reset">
  <span>是否确定开启以下{{instances.length}}台服务器？</span>
  <el-table :data="instances">
    <el-table-column label="服务器名称" prop="instanceName" min-width="120" align="left" ></el-table-column>
    <el-table-column label="操作系统" prop="image.platform" min-width="150" align="left" ></el-table-column>
    <el-table-column label="内网地址" prop="privateIpAddress" min-width="200" align="left"></el-table-column>
  </el-table>
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
      visible: true,
      loading: false
    }
  },
  methods: {
    ...mapActions('action', ['startKsyunKecInstance']),
    async confirm() {
      this.loading = true
      let instanceIds = this.instances.map(item => {
        return item.instanceId
      })
      let response = await this.startKsyunKecInstance(instanceIds)
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
