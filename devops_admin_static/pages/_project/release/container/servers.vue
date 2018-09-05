<template>
  <el-dialog title="服务器配置" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="110px">
      <el-form-item label="环境名称"><span>{{this.form.envName}}</span></el-form-item>
      <el-form-item label="项目名称"><span>{{this.form.proName}}</span></el-form-item>
      <el-form-item label="应用名称"><span>{{this.form.appName}}</span></el-form-item>
      <span>服务器列表</span>
      <el-table ref="table" :data="servers" tooltip-effect="dark" @selection-change="selectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="assetsName" label="名称" min-width="30px" min-height="20px" style="text-align:center"></el-table-column>
        <el-table-column prop="sshAddress" label="IP" min-width="30px"></el-table-column>
      </el-table>
    </el-form>
    <div slot="footer">
      <el-button type="primary" @click="save()">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      obj: Object,
      handler: String
    },
    data() {
      return {
        visible: false,
        servers: Array,
        checkedServers: [],
        form: {}
      }
    },
    watch: {
      handler: async function(val) {
        this.visible = val === 'SERVER'
        this.form = this.obj
        if (this.visible) {
          let objHosts = this.obj.assetsHosts.map(item => {
            return item.assetsId
          })
          this.servers.forEach((item) => {
            if (objHosts.indexOf(item.assetsId) > -1) {
              this.$nextTick(function() {
                this.$refs.table.toggleRowSelection(item, true)
              })
            }
          })
        }
      }
    },
    methods: {
      ...mapActions('action', ['getAppEnvServer', 'setServer', 'searchUsableServersByType']),
      async save() {
        if (this.checkedServers != null) {
          this.$emit('save', this.checkedServers)
        } else {
          this.$emit('reset')
        }
      },
      selectionChange: function(val) {
        this.checkedServers = val
      },
      async queryServers() {
        let response = await this.searchUsableServersByType(2)
        this.servers = response.data
      },
      reset() {
        this.$refs.table.clearSelection()
        this.$emit('reset')
      }
    },
    mounted() {
      this.queryServers()
    }
  }
</script>
