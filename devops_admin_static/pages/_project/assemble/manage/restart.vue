
<template>
<el-dialog title="选择应用所在服务器" :visible.sync="visible" @close="reset">
  <el-form :model="form" ref='form' label-width="110px">
    <el-form-item label="环境名称">
      <span>{{this.form.envName}}</span>
    </el-form-item>
    <el-form-item label="项目名称">
      <span>{{this.form.proName}}</span>
    </el-form-item>
    <el-form-item label="应用名称">
      <span>{{this.form.appName}}</span>
    </el-form-item>
    <span>服务器列表</span>
    <el-table ref="table" :data="ownServers" tooltip-effect="dark" @selection-change="selectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="assetsName" label="名称" min-width="30px" min-height="20px" style="text-align:center"> </el-table-column>
      <el-table-column prop="sshAddress" label="IP" min-width="30px"> </el-table-column>
    </el-table>
  </el-form>
  <div slot="footer">
    <el-button type="primary" @click="restart()">确定</el-button>
    <el-button @click="reset">取消</el-button>
  </div>
</el-dialog>
</el-container>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  props: {
    obj: Object,
    servers: Array,
    handler: String
  },
  data() {
    return {
      visible: false,
      ownServers: [],
      checkedServers: [],
      form: {}
    }
  },
  watch: {
    handler: async function (val) {
      this.visible = val === 'RESTART'
      this.form = this.obj
      if (this.visible) {
        let serversId = await this.getAppEnvServer(this.obj.appEnvId)
        this.ownServers = this.servers.filter((item) => {
          return serversId.data.indexOf(item.assetsId) > -1
        })
      }
    }
  },
  methods: {
    ...mapActions('action', ['getAppEnvServer', 'restartApp']),
    async restart() {
      if (this.checkedServers != null && this.checkedServers.length > 0) {
        let appEnvId = this.obj.appEnvId
        let serverIds = this.checkedServers.map((item) => {
          return item.assetsId
        })
        let response = await this.restartApp({ 'appEnvId': appEnvId, 'serverIds': serverIds })
        if (response.code === '1') {
          this.$msg.success(response.msg)
          window.open(`/base/appenv/logs?app_id=${this.obj.appId}&server_ids=${serverIds}`)
          this.$emit('reset')
        } else {
          this.$msg.error('重启失败：', response.msg)
        }
        this.$emit('save', response.code)
      } else {
        this.$msg('未指定服务器')
      }
    },
    selectionChange: function (val) {
      this.checkedServers = val
    },
    reset() {
      this.$refs.table.clearSelection()
      this.$emit('reset')
    }
  }
}
</script>
