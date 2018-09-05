<template>
  <el-dialog title="服务器配置" :visible.sync="visible" @close="reset">
    <el-form :model="form" ref='form' label-width="110px" inline>
      <el-form-item>
        <label slot="label"><b>环境名称：</b></label>
        <span>{{this.form.envName}}</span>
      </el-form-item>
      <el-form-item>
        <label slot="label"><b>项目名称：</b></label>
        <span>{{this.form.proName}}</span>
      </el-form-item>
      <el-form-item label="应用名称">
        <label slot="label"><b>应用名称：</b></label>
        <span>{{this.form.appName}}</span>
      </el-form-item>
    </el-form>
    <hr>
    <h4>服务器列表</h4>
    <el-row style="margin-top:20px; margin-bottom: 20px;margin-left: 10px;">
      <el-col style="width: 200px; margin-right: 20px;">
        <el-input placeholder="请输入服务器名称" v-model="keywordAssetsName" clearable @clear="clearKeywordAssetsName"></el-input>
      </el-col>
      <el-col style="width: 150px; margin-right: 20px;">
        <el-input placeholder="请输入服务器地址" v-model="keywordSshAddress" clearable @clear="clearKeywordSshAddress"></el-input>
      </el-col>
      <el-col :span="11" style="width: 80px; margin-left: 60px;">
        <el-button type="primary" @click="searchByMultipleKeywords">搜索</el-button>
      </el-col>
    </el-row>
    <el-table ref="table" :data="availableServers" tooltip-effect="dark" @selection-change="selectionChange" max-height="300" border="">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="assetsName" label="服务器名称" min-width="30px" sortable></el-table-column>
      <el-table-column prop="sshAddress" label="服务器地址" min-width="30px" sortable></el-table-column>
      <el-table-column prop="sshPort" label="服务器端口" min-width="30px" sortable></el-table-column>
    </el-table>
    <template slot="footer">
      <el-button type="primary" @click="save()">保存</el-button>
      <el-button @click="reset">取消</el-button>
    </template>
  </el-dialog>
</template>
<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      obj: Object,
      servers: Array,
      handler: String
    },
    data() {
      return {
        visible: false,
        checkedServers: [],
        checkedServerIds: [],
        form: {},
        availableServers: [],
        assetsNameFilters: [],
        keywordAssetsName: '',
        keywordSshAddress: ''
      }
    },
    watch: {
      handler: async function(val) {
        this.visible = val === 'DEPLOY_SERVER'
        this.form = this.obj
        if (this.visible) {
          this.availableServers = this.servers
          let serversId = await this.getAppEnvServer(this.obj.appEnvId)
          this.availableServers.filter((item) => {
            return serversId.data.indexOf(item.assetsId) > -1
          }).forEach((item) => {
            this.$nextTick(function() {
              this.$refs.table.toggleRowSelection(item, true)
            })
          })
        }
      }
    },
    methods: {
      ...mapActions('action', ['getAppEnvServer', 'setServer']),
      async save() {
        if (this.checkedServers != null) {
          let appEnvId = this.obj.appEnvId
          let hostId = this.checkedServers.map((item) => {
            return item.assetsId
          })
          let configInfo = {'appEnvId': appEnvId, 'hostId': hostId}
          let response = await this.setServer(configInfo)
          if (response.code === '1') {
            this.$msg.success('保存成功！')
          } else {
            this.$msg.error('保存失败：', response.msg)
          }
          this.$emit('save', response.code)
        } else {
          this.$emit('reset')
        }
      },
      selectionChange: function(val) {
        let checkedServerIds = []
        val.forEach(item => {
          checkedServerIds.push(item.assetsId)
        })
        this.checkedServers = val
        this.checkedServerIds = checkedServerIds
      },
      reset() {
        this.$emit('reset')
        this.keywordAssetsName = ''
        this.keywordSshAddress = ''
        this.$refs.table.clearSelection()
      },
      searchByMultipleKeywords() {
        this.searchByKeywordAssetsName()
        this.searchByKeywordSshAddress()
      },
      searchByKeywordAssetsName() {
        let keywordAssetsName = this.keywordAssetsName
        if (keywordAssetsName.length > 0) {
          let filterServers = []
          this.availableServers.filter((item) => {
            if (this.checkedServerIds.indexOf(item.assetsId) > -1) {
              return true
            }
            return item.assetsName.includes(keywordAssetsName)
          }).forEach((item) => {
            filterServers.push(item)
          })
          this.availableServers = filterServers
          this.showCheckedServers()
        }
      },
      searchByKeywordSshAddress() {
        let keywordSshAddress = this.keywordSshAddress
        if (keywordSshAddress.length > 0) {
          let filterServers = []
          this.availableServers.filter((item) => {
            if (this.checkedServerIds.indexOf(item.assetsId) > -1) {
              return true
            }
            return item.sshAddress.includes(keywordSshAddress)
          }).forEach((item) => {
            filterServers.push(item)
          })
          this.availableServers = filterServers
          this.showCheckedServers()
        }
      },
      clearKeywordAssetsName() {
        this.keywordAssetsName = ''
        this.availableServers = this.servers
        if (this.keywordSshAddress.length > 0) {
          this.searchByKeywordSshAddress()
        }
      },
      clearKeywordSshAddress() {
        this.keywordSshAddress = ''
        this.availableServers = this.servers
        if (this.keywordAssetsName.length > 0) {
          this.searchByKeywordAssetsName()
        }
      },
      showCheckedServers() {
        this.availableServers.forEach((item) => {
          if (this.checkedServerIds.indexOf(item.assetsId) > -1) {
            this.$nextTick(function() {
              this.$refs.table.toggleRowSelection(item, true)
            })
          }
        })
      }
    }
  }
</script>
