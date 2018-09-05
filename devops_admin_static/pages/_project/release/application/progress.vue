<template lang="html">
  <el-table ref="appTable" :data="apps" border @expand-change="showLog" :row-key="getRowKey" :expand-row-keys="showLogRows">
    <el-table-column type="expand">
      <template slot-scope="scope">
        <log-form refs="log_" :publishId="scope.row.publishId" :handler="scope.row.isShowLog"></log-form>
      </template>
    </el-table-column>
    <el-table-column prop="appName" label="应用名称" min-width="80px"></el-table-column>
    <el-table-column prop="deployVersionCode" label="发布版本" min-width="120px">
      <template slot-scope="scope">
        <el-button type="text" @click="changeRecord(scope.row)">
          <el-popover
            placement="top-start"
            title="发布版本信息"
            trigger="hover"
            :open-delay="parseInt('200')"
          >
            <span slot=""><pre>{{scope.row.description + '\n【应用版本信息】\n' + scope.row.appVersionInfo + '\n【配置版本信息】\n' + scope.row.configVersionInfo}}</pre></span>
            <span slot="reference">{{scope.row.deployVersionCode}}</span>
          </el-popover>
        </el-button>
      </template>
    </el-table-column>
    <el-table-column prop="deployType" label="发布方式" min-width="70px">
      <template slot-scope="scope">
        <span v-if="scope.row.deployType===0">常规</span>
        <span v-if="scope.row.deployType===2">容器</span>
      </template>
    </el-table-column>
    <el-table-column label="自动重启" min-width="50px;" align="center">
      <template slot-scope="scope">
        <span v-if="scope.row.autoRestart===1">是</span>
        <span v-else>否</span>
      </template>
    </el-table-column>
    <el-table-column prop="serverName" label="服务器" min-width="100px"></el-table-column>
    <el-table-column prop="publishStatus" label="发布状态" min-width="100px">
      <template slot-scope="scope">
        <span v-if="scope.row.publishStatus===0" style="color:gray;">待发布</span>
        <span v-else-if="scope.row.publishStatus===100" style="color:green;">正在部署</span>
        <span v-else-if="scope.row.publishStatus===200" style="color:green;">正在重启</span>
        <span v-else-if="scope.row.publishStatus===-100" style="color:red;">部署失败</span>
        <span v-else-if="scope.row.publishStatus===-200" style="color:red;">重启失败</span>
        <span v-else-if="scope.row.publishStatus===-300" style="color:green;">取消发布</span>
        <span v-else-if="scope.row.publishStatus===300" style="color:green;">发布完毕</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" min-width="100px">
      <template slot-scope="scope">
        <el-button type="text" v-show="scope.row.publishStatus<0||scope.row.publishStatus===300" @click="republishEvent(scope.row)">重新发布</el-button>
        <el-button type="text" v-show="scope.row.publishStatus<0||scope.row.publishStatus===300" @click="publishOverEvent(scope.row)">归档</el-button>
        <el-button type="text" v-show="scope.row.publishStatus===0" @click="publishCancelEvent(scope.row)">取消发布</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import {mapActions} from 'vuex'
  import logForm from './log.vue'

  export default {
    components: {
      logForm
    },
    props: {
      proId: String,
      envId: String,
      publishApps: Array
    },
    data() {
      return {
        apps: [],
        showLogRows: []
      }
    },
    watch: {
      publishApps: function() {
        let map = {}
        this.apps.forEach(item => {
          map[item.publishId] = item.isShowLog
        })
        this.apps = this.publishApps.map(item => {
          return Object.assign(item, {isShowLog: map[item.publishId]})
        })
      }
    },
    methods: {
      ...mapActions('action', ['rePublishApp', 'publishOver', 'publishCancel']),
      getRowKey(row) {
        return row.publishId
      },
      showLog(row) {
        let idx = this.showLogRows.indexOf(row.publishId)
        if (idx > -1) {
          this.showLogRows.splice(idx, 1)
        } else {
          this.showLogRows.push(row.publishId)
        }
        row.isShowLog = true
      },
      republishEvent(row) {
        this.$refs['appTable'].toggleRowExpansion(row, false)
        this.rePublishApp({'publishId': row.publishId, 'deployType': row.deployType})
      },
      async changeRecord(val) {
        var index = val.deployAppGitUrl.lastIndexOf('.')
        var url = val.deployAppGitUrl.substring(0, index)
        window.open(url + '/compare/' + val.tagUrl + '...' + val.deployVersionCode)
      },
      async publishOverEvent(row) {
        let response = await this.publishOver(row.publishId)
        if (~~response.code === 1) {
          this.$msg.success('归档成功')
        } else {
          this.$msg.error('归档失败', response.msg)
        }
      },
      async publishCancelEvent(row) {
        let response = await this.publishCancel(row.publishId)
        if (~~response.code === 1) {
          this.$msg.success('取消发布成功')
        } else {
          this.$msg.error('取消发布失败', response.msg)
        }
      }
    }
  }
</script>

<style scoped="scoped">
  .el-tag + .el-tag {
    margin-top: 5px;
  }
</style>
