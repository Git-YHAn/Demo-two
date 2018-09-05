<template>
  <portlet title="服务器管理">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item style="margin-bottom: 0;" label="关键字">
          <el-input v-model="keyWords" clearable placeholder="输入名称/内网IP" @keyup.enter.native="queryServers" @clear="queryServers"></el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 0;">
          <el-button v-auth="'search'" type="primary" @click="queryServers">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template slot="title-bar-tools">
      <el-button v-auth="'add'" type="primary" @click="add" icon="el-icon-plus">新增服务器</el-button>
      <el-button v-auth="'initial'" type="primary" @click="initial" icon="el-icon-refresh">初始化</el-button>
      <el-button @click="connect" type="primary" icon="el-icon-tickets" style="margin-right:10px;">批量执行</el-button>
      <el-dropdown plain placement="bottom-start" trigger="click">
        <el-button>更多操作<i class="el-icon-arrow-down el-icon--right"></i></el-button>
        <el-dropdown-menu class="cst-dropdown" slot="dropdown">
          <el-dropdown-item id="downloadUrl" type="primary" @click.native="downloadUrl">模板下载</el-dropdown-item>
          <el-dropdown-item @click.native="uploadEvent">
            <el-upload :show-file-list=false action="" :http-request="uploadEvent">模板导入</el-upload>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </template>

    <el-table :data="tableData" v-show="handler != 'CONNECT'">
      <el-table-column prop="assetsName" label="服务器名称" min-width="100px" show-overflow-tooltip sortable>
      </el-table-column>
      <el-table-column prop="assetsType" label="服务器类型" min-width="100px" :formatter="typeFormatter" sortable>
      </el-table-column>
      <el-table-column prop="assetsStatus" label="服务器状态" min-width="100px" :formatter='getTypeStr' sortable>
      </el-table-column>
      <el-table-column prop="project" label="项目名称" min-width="100px" show-overflow-tooltip sortable>
      </el-table-column>
      <el-table-column prop="application" label="应用名称" min-width="100px" show-overflow-tooltip sortable>
      </el-table-column>
      <el-table-column prop="env" label="环境名称" min-width="100px" show-overflow-tooltip sortable>
      </el-table-column>
      <el-table-column prop="sshAddress" label="管理地址" min-width="100px">
      </el-table-column>
      <el-table-column prop="sshPort" label="管理端口" min-width="80px">
      </el-table-column>
      <el-table-column prop="outerIp" label="外网ip" min-width="100px">
      </el-table-column>
      <el-table-column prop="innerIp" label="内网ip" min-width="100px">
      </el-table-column>
      <el-table-column prop="initialStatus" label="初始化" min-width="50px">
        <template slot-scope="scope">
          <el-tooltip v-if="scope.row.initialStatus === 0" content="未初始化" placement="right">
            <el-button type="text" icon="el-icon-close" style="font-size:20px;color: red" circle></el-button>
          </el-tooltip>
          <el-tooltip v-if="scope.row.initialStatus === 1" content="已初始化" placement="right">
            <el-button type="text" icon="el-icon-check" style="font-size:20px;color: green" circle></el-button>
          </el-tooltip>
          <el-tooltip v-if="scope.row.initialStatus === 2" content="初始化中" placement="right">
            <el-button type="text" icon="el-icon-loading" style="font-size:20px;color: gray" circle></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="150px" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button v-auth="'edit'" type="text" @click="edit(scope.row)">编辑</el-button>
          <el-button v-auth="'detail'" type="text" @click="detail(scope.row)">详情</el-button>
          <el-button v-auth="'delete'" type="text" @click="del(scope.row)">删除</el-button>
          <el-button v-auth="'account'" type="text" @click="editAccount(scope.row)">账号连接</el-button>
        </template>
      </el-table-column>
    </el-table>

    <template slot="footer" v-if="handler != 'CONNECT'">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize"
                  @pagenum="setPageNo"></pagination>
    </template>
    <execute-form v-if="handler==='CONNECT'" :handlers="handler" @reset="resetHandler"></execute-form>
    <add-form :handler="handler" @save="reloadPage" @reset="resetHandler"></add-form>
    <edit-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></edit-form>
    <detail-form :obj="selectRow" :handler="handler" @reset="resetHandler"></detail-form>
    <account-form :obj="selectRow" :handler="handler" @save="reloadPage" @reset="resetHandler"></account-form>
    <initial-form :handler="handler" @save="reloadPage" @setInitialHandler="setInitialHandler" @reset="resetHandler"></initial-form>
  </portlet>
</template>

<script>
  import {mapActions} from 'vuex'
  import AddForm from './add.vue'
  import EditForm from './edit.vue'
  import DetailForm from './detail.vue'
  import AccountForm from './account.vue'
  import InitialForm from './initial.vue'
  import ExecuteForm from './execute.vue'

  export default {
    components: {
      AddForm,
      EditForm,
      DetailForm,
      InitialForm,
      AccountForm,
      ExecuteForm
    },
    data() {
      return {
        keyWords: '',
        handler: '',
        interval: null,
        selectRow: {},
        tableData: [],
        page: {
          pageNum: 1,
          pageSize: 30
        }
      }
    },
    watch: {
      handler: function(val) {
        if (val === 'INITIALIZING') {
          this.queryServers()
          this.interval = setInterval(() => {
            this.queryServers()
          }, 2000)
        } else {
          clearInterval(this.interval)
        }
      }
    },
    methods: {
      ...mapActions('action', ['searchServers', 'deleteServer']),
      getTypeStr(row) {
        if (row.assetsStatus === 0) {
          return '可用'
        } else if (row.assetsStatus === 1) {
          return '不可用'
        } else {
          return ''
        }
      },
      add() {
        this.handler = 'ADD'
      },
      detail(row) {
        this.handler = 'DETAIL'
        this.selectRow = row
      },
      edit(row) {
        this.handler = 'EDIT'
        this.selectRow = row
      },
      editAccount(row) {
        this.handler = 'ACCOUNT'
        this.selectRow = row
      },
      connect() {
        this.handler = 'CONNECT'
      },
      initial() {
        this.handler = 'INITIAL'
      },
      async del(row) {
        if (row.assetsId) {
          this.$confirm('是否删除?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            let response = await this.deleteServer(row.assetsId)
            if (response.code === '1') {
              this.$msg.success('删除成功！')
              let res = await this.queryServers()
              this.tableData = res.data.results
            } else {
              this.$msg.error('删除失败：', response.msg)
            }
          })
        }
      },
      reloadPage() {
        this.page.pageNum = 1
        this.resetHandler()
        this.queryServers()
      },
      resetHandler() {
        this.handler = ''
      },
      setInitialHandler() {
        this.handler = 'INITIALIZING'
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.queryServers(size, 1)
      },
      setPageNo: function(num) {
        this.page.pageNum = num
        this.queryServers(undefined, num)
      },
      setPageForExecHistory(page) {
        this.page = page
      },
      typeFormatter: function(row) {
        switch (row.assetsType) {
          case 0:
            return '物理机'
          case 1:
            return '云服务器'
          case 2:
            return '容器'
        }
      },
      async queryServers() {
        let response = await this.searchServers({
          'keyWords': this.keyWords ? this.keyWords.concat('.') : '',
          'pageSize': this.page.pageSize,
          'pageNum': this.page.pageNum
        })
        if (response.code === '1') {
          this.tableData = response.data.results
          this.page = response.data.meta
        }
        let num = false
        this.tableData.forEach(item => {
          if (item.initialStatus === 2) {
            this.handler = 'INITIALIZING'
            num = true
          }
        })
        if (!num) {
          this.handler = ''
        }
        return response
      },
      async downloadUrl() {
        await this.$axios.get(`/assets/download`, {responseType: 'arraybuffer'}).then(res => {
          let blob = new Blob([res.data], {type: 'application/vnd.ms-excel'})
          let objectUrl = URL.createObjectURL(blob)
          let link = document.createElement('a')
          // 需要添加到body尾部，否则部分浏览器无法下载，如firefox
          document.body.appendChild(link)
          link.href = objectUrl
          link.setAttribute('download', '服务器标准模板.xlsx')
          link.click()
          // 释放一个之前通过调用 URL.createObjectURL() 创建的已经存在的 URL 对象
          window.URL.revokeObjectURL(objectUrl)
        }).catch(err => {
          console.error('模板下载失败：', err)
        })
      },
      async uploadEvent(item) {
        let file = item.file
        let formData = new FormData()
        formData.append('file', file)
        this.$axios.setHeader('Content-Type', 'multipart/form-data', ['post'])
        let response = await this.$axios.$post(`/assets/upload`, formData)
        if (response.code === '1') {
          this.$msg.success(response.data)
          this.queryServers()
        } else {
          this.$msg.error('导入失败:', response.msg)
        }
      }
    },
    async created() {
      this.queryServers()
    },
    beforeDestroy() {
      clearInterval(this.interval)
    }
  }
</script>
<style>
  .el-button-group {
    margin-left: 10px;
    margin-right: 10px;
  }

  .el-button {
    vertical-align: middle;
  }
</style>
