<template lang="html">
  <div>
    <el-table ref="publishAppTable" :data="apps" border @selection-change="selectAppEvent">
      <el-table-column type="selection" width="50px" align="center" :selectable="getRowIsSelectable"></el-table-column>
      <el-table-column prop="appName" label="应用名称" min-width="40%"></el-table-column>
      <el-table-column label="服务器" min-width="40%">
        <template slot-scope="scope">
          <template v-for="(item,idx) in scope.row.assetsHosts">
            <el-tag type="info" size="mini" closable @close="spliceItem(scope.row.assetsHosts, item)">{{item.assetsName}}</el-tag>
            <br v-if="idx!=scope.row.assetsHosts.length-1"/>
          </template>
          <el-tag type="info" size="mini" style="float:right;cursor: pointer;">
            <div @click="showServer(scope.row)">+</div>
          </el-tag>
        </template>
      </el-table-column>
    </el-table>
    <server-form :obj="selectRow" :handler="serverHandler" @reset="resetHandler" @save="changeServer"></server-form>
  </div>
</template>
<script>
  import {mapActions} from 'vuex'
  import serverForm from './servers'

  export default {
    components: {
      serverForm
    },
    props: {
      proId: Number,
      envId: Number,
      publishingApps: Array
    },
    data() {
      return {
        apps: [],
        selectApps: [],
        selectRow: {},
        serverHandler: '',
        page: {pageNum: 1, pageSize: 30}
      }
    },
    computed: {
      publishingAppEnvIds: function() {
        return this.publishingApps.map(item => {
          return item.appEnvId
        })
      }
    },
    methods: {
      ...mapActions('action', ['queryPublishAppsByCon']),
      spliceItem(arr, item) {
        arr.splice(arr.indexOf(item), 1)
      },
      resetHandler() {
        this.serverHandler = ''
      },
      getRowIsSelectable(row) {
        return this.publishingAppEnvIds.indexOf(row.appEnvId) < 0
      },
      async queryPublishAppsEvent() {
        let response = await this.queryPublishAppsByCon({proId: this.proId, envId: this.envId})
        if (~~response.code === 1) {
          this.apps = response.data
        }
      },
      selectAppEvent(selection) {
        this.selectApps = selection
      },
      showServer(row) {
        this.selectRow = row
        this.serverHandler = 'SERVER'
      },
      changeServer(server) {
        this.selectRow.assetsHosts = server
        this.selectRow = {}
        this.resetHandler()
      }
    },
    mounted() {
      this.queryPublishAppsEvent()
      this.apps.forEach(item => {
        if (this.publishingAppEnvIds.indexOf(item.appEnvId) !== -1) {
          this.$refs.publishAppTable.toggleRowSelection(item, true)
        }
      })
    }
  }
</script>

<style scoped="scoped">
  .el-tag + .el-tag {
    margin-top: 5px;
  }
</style>
