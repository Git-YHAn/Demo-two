<template>
  <el-dialog title="服务器初始化列表" :visible.sync="initialVisible" @close="reset">
    <el-form :inline="true" v-model="searchInfo" class="demo-form-inline">
      <el-form-item>
        <el-select v-model="searchInfo.proName" placeholder="项目名称" clearable @clear="queryUninitializedServers">
          <el-option v-for="pro in availableProNames" :key="pro" :label="pro" :value="pro"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchInfo.envName" placeholder="环境名称" clearable @clear="queryUninitializedServers">
          <el-option v-for="env in availableEnvNames" :key="env" :label="env" :value="env"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchInfo.appName" placeholder="应用名称" clearable @clear="queryUninitializedServers">
          <el-option v-for="app in availableAppNames" :key="app" :label="app" :value="app"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchInfo.assetsNameOrIP" placeholder="输入服务器名称或地址" clearable @clear="queryUninitializedServers"></el-input>
      </el-form-item>
      <el-form-item>
        <span>已选择：{{allPageSelectedAssets.length}}台</span>
      </el-form-item>
      <el-form-item style="float: right;">
        <el-button type="primary" @click="queryUninitializedServers">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table ref="multipleTable" :data="initialTable" @row-click="rowClickEvent" @selection-change="handleSelectionChange" border show-overflow-tooltip>
      <el-table-column type="selection" width="30px"></el-table-column>
      <el-table-column prop="assetsName" label="服务器名称" min-width="60px"></el-table-column>
      <el-table-column prop="sshAddress" label="服务器地址" width="120px"></el-table-column>
      <el-table-column prop="env" label="环境名称" width="100px"></el-table-column>
      <el-table-column prop="project" label="项目名称" width="100px"></el-table-column>
      <el-table-column prop="application" label="应用名称" min-width="100px"></el-table-column>
    </el-table>
    <div slot="footer" >
      <el-button @click="toggleSelection" type="primary" :disabled.sync="cancelDisable">取消</el-button>
      <el-button @click="doInitial" type="primary" :disabled.sync="cancelDisable">执行</el-button>
    </div>
    <footer slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </footer>
  </el-dialog>
</template>

<script>
  import {mapActions} from 'vuex'

  export default {
    props: {
      handler: String
    },
    data() {
      return {
        initialVisible: false,
        cancelDisable: true,
        initialTable: [],
        page: {pageNum: 1, pageSize: 10},
        searchInfo: {
          assetsNameOrIP: null,
          envName: null,
          proName: null,
          appName: null
        },
        allPageSelectedAssets: [],
        currentPageLastSelectedAssets: [],
        availableEnvNames: [],
        availableProNames: [],
        availableAppNames: []
      }
    },
    watch: {
      handler: async function (val) {
        this.initialVisible = val === 'INITIAL'
        if (this.initialVisible) {
          this.reloadPage()
        }
      }
    },
    methods: {
      ...mapActions('action', ['searchUninitializedServers', 'doInitialServers']),
      reset() {
        this.$emit('reset')
        this.allPageSelectedAssets = []
      },
      reloadPage() {
        this.currentPageLastSelectedAssets = []
        this.queryUninitializedServers()
      },
      resetHandler() {
        this.handler = ''
      },
      setPageSize: function (size) {
        this.page.pageSize = size
        this.reloadPage()
      },
      setPageNo: function (num) {
        this.page.pageNum = num
        this.reloadPage()
      },
      toggleSelection(rows) {
        if (rows.length > 0) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.multipleTable.clearSelection()
        }
      },
      rowClickEvent(row) {
        this.toggleSelection([row])
      },
      handleSelectionChange (selectedServers) {
        let unselectedAssets = []
        this.currentPageLastSelectedAssets.forEach(selection => {
          if (selectedServers.indexOf(selection) === -1) {
            unselectedAssets.push(selection)
          }
        })
        this.currentPageLastSelectedAssets = selectedServers
        this.syncCurrentPageSelectedAssets(selectedServers, unselectedAssets)
        this.cancelDisable = this.allPageSelectedAssets.length === 0
      },
      /* 同步服务器选择记录 */
      syncCurrentPageSelectedAssets(currentPageSelectedAssets, currentPageUnselectedAssets) {
        currentPageSelectedAssets.forEach(selection => {
          let index = this.getIndexFromAllSelectedAssets(selection)
          if (index === -1) {
            this.allPageSelectedAssets.push({
              assetsId: selection.assetsId,
              appName: selection.application
            })
          }
        })
        currentPageUnselectedAssets.forEach(unselection => {
          let index = this.getIndexFromAllSelectedAssets(unselection)
          if (index > -1) {
            this.allPageSelectedAssets.splice(index, 1)
          }
        })
      },
      /* 返回传入服务器的索引，若存在返回记录集合中的索引，否则返回 -1 */
      getIndexFromAllSelectedAssets(host) {
        for (let index in this.allPageSelectedAssets) {
          let selectedAssets = this.allPageSelectedAssets[index]
          if (selectedAssets.assetsId === host.assetsId) {
            return index
          }
        }
        return -1
      },
      /* 设置当前页已选中的服务器 */
      setCurrentPageSelectedAssets() {
        this.$nextTick(function() {
          this.initialTable.forEach(item => {
            let index = this.getIndexFromAllSelectedAssets(item)
            if (index > -1) {
              this.$refs.multipleTable.toggleRowSelection(item, true)
            }
          })
        })
      },
      async queryUninitializedServers() {
        let info = {
          assetsNameOrSsh: this.searchInfo.assetsNameOrIP,
          envName: this.searchInfo.envName,
          proName: this.searchInfo.proName,
          appName: this.searchInfo.appName,
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize
        }
        let response = await this.searchUninitializedServers(info)
        if (response.code === '1') {
          let results = response.data.results
          let availableEnvNames = []
          let availableProNames = []
          let availableAppNames = []
          results.forEach(item => {
            if (availableEnvNames.indexOf(item.env) === -1) {
              availableEnvNames.push(item.env)
            }
            if (availableProNames.indexOf(item.project) === -1) {
              availableProNames.push(item.project)
            }
            if (availableAppNames.indexOf(item.application) === -1) {
              availableAppNames.push(item.application)
            }
          })
          this.availableEnvNames = availableEnvNames
          this.availableProNames = availableProNames
          this.availableAppNames = availableAppNames
          this.initialTable = results
          this.page = response.data.meta
          this.setCurrentPageSelectedAssets()
        } else {
          this.$msg.error('查询初始化服务器异常：', response.msg)
        }
      },
      async doInitial() {
        this.allPageSelectedAssets.forEach(async item => {
          let response = await this.doInitialServers(item)
          if (~~response.code === 1) {
            this.$msg.success(response.msg)
          } else {
            this.$msg.error(response.msg)
          }
        })
        this.$emit('setInitialHandler')
      }
    }
  }
</script>
