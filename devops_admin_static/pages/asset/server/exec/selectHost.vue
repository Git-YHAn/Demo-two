<template>
  <el-dialog title="服务器列表" :visible.sync="hostVisible" @close="emitToParent">
    <el-form :inline="true" onsubmit="return false;" style="margin-bottom: 10px;">
      <el-form-item>
        <el-input v-model="keyWords" clearable placeholder="输入服务器名称或IP" @keyup.enter.native="queryServers" @clear="queryServers"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button v-auth="'search'" type="primary" @click="queryServers">查询</el-button>
      </el-form-item>
      <el-form-item style="float: right;">
        <label style="margin-right: 10px;"><b>已选择: </b></label>
        <span style="margin-right: 10px;"> {{allPageSelectedAssets.length}} 台</span>
      </el-form-item>
    </el-form>

    <el-table ref="selectionAssetsTable" :data="assetsTableData" height="460px" border highlight-current-row
              @row-click="toggleSelectionRow" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="30px"></el-table-column>
      <el-table-column prop="assetsName" label="服务器名称" min-width="100px"></el-table-column>
      <el-table-column prop="sshAddress" label="服务器地址" width="150px"></el-table-column>
      <el-table-column prop="env" label="环境名称" width="150px"></el-table-column>
      <el-table-column prop="project" label="项目名称" min-width="100px"></el-table-column>
      <el-table-column prop="application" label="应用名称" min-width="100px" show-overflow-tooltip></el-table-column>
    </el-table>
    <el-row style="float: right;margin-top: 5px;margin-right: 10px;">
      <el-button @click="cancelSelectHosts" :disabled="saveDisabled">重置</el-button>
      <el-button @click="saveSelectHosts" type="primary" :disabled="saveDisabled">确定</el-button>
    </el-row>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNo"></pagination>
    </template>
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
        hostVisible: false,
        saveDisabled: true,
        page: {pageNum: 1, pageSize: 10},
        assetsTableData: [],
        selectAssetsHosts: [],
        allSelectedAssetsIds: [],
        allSelectAssetsSshAddr: '',
        currentPageLastSelectedAssets: [],
        allPageSelectedAssets: [],
        keyWords: ''
      }
    },
    watch: {
      handler: async function(val) {
        this.hostVisible = val === 'SELECT_HOST'
        if (this.hostVisible) {
          this.reloadPage()
        }
      },
      allPageSelectedAssets: async function() {
        this.saveDisabled = !(this.allPageSelectedAssets && this.allPageSelectedAssets.length > 0)
      }
    },
    methods: {
      ...mapActions('action', ['searchServers']),
      emitToParent() {
        this.$emit('setAssetsHosts', this.allSelectedAssetsIds, this.allSelectAssetsSshAddr, this.allPageSelectedAssets)
        this.$emit('reset')
      },
      clearDialogData() {
        this.selectAssetsHosts = []
        this.assetsTableData = []
        this.allPageSelectedAssets = []
        this.allSelectedAssetsIds = []
        this.allSelectAssetsSshAddr = ''
        this.keyWords = null
      },
      reloadPage() {
        this.pageNum = 1
        this.pageSize = 10
        this.queryServers()
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.queryServers()
      },
      setPageNo: function(num) {
        this.page.pageNum = num
        this.queryServers()
      },
      toggleMultipleSelectionRows(rows) {
        if (rows && rows.length > 0) {
          rows.forEach(row => {
            this.$refs.selectionAssetsTable.toggleRowSelection(row)
          })
        } else {
          this.$refs.selectionAssetsTable.clearSelection()
        }
      },
      toggleSelectionRow(row, selected) {
        if (selected.isPrototypeOf(Boolean)) {
          this.$refs.selectionAssetsTable.toggleRowSelection(row, selected)
        } else {
          this.toggleMultipleSelectionRows([row])
        }
      },
      handleSelectionChange(selectedAssets) {
        let unselectedAssets = []
        this.currentPageLastSelectedAssets.forEach(selection => {
          if (selectedAssets.indexOf(selection) === -1) {
            unselectedAssets.push(selection)
          }
        })
        this.currentPageLastSelectedAssets = selectedAssets
        this.syncCurrentPageSelectedAssets(selectedAssets, unselectedAssets)
      },
      /* 同步服务器选择记录 */
      syncCurrentPageSelectedAssets(currentPageSelectedAssets, currentPageUnselectedAssets) {
        currentPageSelectedAssets.forEach(selectedAssets => {
          let index = this.getIndexFromAllSelectedAssets(selectedAssets)
          if (index === -1) {
            this.allPageSelectedAssets.push(selectedAssets)
          }
        })
        currentPageUnselectedAssets.forEach(unselectedAssets => {
          let index = this.getIndexFromAllSelectedAssets(unselectedAssets)
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
          this.assetsTableData.forEach(currentPageSelectedItem => {
            let index = this.getIndexFromAllSelectedAssets(currentPageSelectedItem)
            if (index > -1) {
              this.toggleSelectionRow(currentPageSelectedItem, true)
            }
          })
        })
      },
      saveSelectHosts() {
        let allSelectedAssetsIds = []
        let allSelectAssetsSshAddr = ''
        this.allPageSelectedAssets.forEach(selectedAssetsHost => {
          allSelectedAssetsIds.push(selectedAssetsHost.assetsId)
          allSelectAssetsSshAddr = allSelectAssetsSshAddr.concat(selectedAssetsHost.sshAddress).concat(':').concat(selectedAssetsHost.sshPort).concat(',')
        })

        if (allSelectedAssetsIds.length > 0 && allSelectAssetsSshAddr.length > 1) {
          this.allSelectedAssetsIds = allSelectedAssetsIds
          this.allSelectAssetsSshAddr = allSelectAssetsSshAddr.slice(0, allSelectAssetsSshAddr.length - 1)
        }

        this.emitToParent()
      },
      cancelSelectHosts() {
        this.clearDialogData()
        this.emitToParent()
      },
      async queryServers() {
        this.currentPageLastSelectedAssets = []
        let keyWords = this.keyWords ? this.keyWords.concat('.') : ''
        let response = await this.searchServers({
          'keyWords': keyWords,
          'pageSize': this.page.pageSize,
          'pageNum': this.page.pageNum
        })
        if (response.code === '1') {
          this.assetsTableData = response.data.results
          this.page = response.data.meta
          this.setCurrentPageSelectedAssets()
        }
        return response
      }
    },
    async destroyed() {
      this.clearDialogData()
    }
  }
</script>
