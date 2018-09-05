<template>
  <portlet title="发布历史" :showPageBar="false">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item label="应用名称">
          <el-input v-model="appName" clearable v-on:clear="reloadPage" placeholder="应用名称" @keyup.enter.native="searchPublishHistoryEvent"></el-input>
        </el-form-item>
        <el-form-item label="发布状态">
          <template>
            <el-select v-model="optionStatus" clearable v-on:clear="reloadPage" @change="searchPublishHistoryEvent" placeholder="请选择状态" style="width: 80%;">
              <el-option
                v-for="item in publishStatusOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item>
          <el-button v-auth="'search'" type="primary" @click="searchPublishHistoryEvent">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <el-table :data="publishHistory" border highlight-current-row>
      <el-table-column type="index" label="序号" min-width="40px" align="center"></el-table-column>
      <el-table-column prop="envName" label="环境名称" min-width="80px"></el-table-column>
      <el-table-column prop="proName" label="项目名称" min-width="100px"></el-table-column>
      <el-table-column prop="appName" label="应用名称" min-width="100px"></el-table-column>
      <el-table-column prop="assetsType" label="发布方式" min-width="50px" :formatter="formatterDeployType"></el-table-column>
      <el-table-column prop="createTime" label="发布时间" min-width="90px"></el-table-column>
      <el-table-column prop="publishStatus" :formatter="formatterPublishStatus" label="发布状态" min-width="90px"></el-table-column>
      <el-table-column prop="autoRestart" label="自动重启" min-width="50px">
        <template slot-scope="scope">
          <span v-if="scope.row.autoRestart===1">是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column prop="operationUserName" label="操作员" min-width="100px">{{publishHistory.operationUserName}}</el-table-column>
      <el-table-column prop="assetsName" label="服务器名称" min-width="100px"></el-table-column>
      <el-table-column prop="sshAddress" label="服务器IP" min-width="80px">{{publishHistory.sshAddress}}</el-table-column>
      <el-table-column prop="publishInfo" label="发布信息" min-width="90px">
        <template slot-scope="scope">
          <el-popover placement="left" width="400" trigger="click" >
            <h4 style="font-weight:bold;"> 发布信息 </h4>
            <pre>{{ scope.row.publishInfo }}</pre>
            <el-button type="text" slot="reference" @click = "centerDialogVisible = true">点击查看
            </el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
    </template>
  </portlet>
</template>
<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    data() {
      return {
        handler: '',
        publishHistory: [],
        appName: null,
        page: {
          pageNum: 1,
          pageSize: 30
        },
        centerDialogVisible: false,
        publishStatusOptions: [
          {
            value: '300',
            label: '发布完毕'
          }, {
            value: '-100',
            label: '应用部署失败'
          }],
        optionStatus: ''
      }
    },
    computed: {
      ...mapGetters('project', ['currentEnvironment', 'currentProject']),
      proId: function() {
        return this.currentProject.proId
      },
      envId: function() {
        return this.currentEnvironment.envId
      }
    },
    methods: {
      ...mapActions('action', ['searchPublishHistory']),
      reloadPage() {
        this.page.pageNum = 1
        this.resetHandler()
        this.searchPublishHistoryEvent()
      },
      resetHandler() {
        this.handler = ''
      },
      setPageSize: function(size) {
        this.page.pageSize = size
        this.page.pageNum = 1
        this.searchPublishHistoryEvent()
      },
      setPageNum: function(num) {
        this.page.pageNum = num
        this.searchPublishHistoryEvent()
      },
      formatterDeployType: function(row, column) {
        if (row.assetsType === 0) {
          return '常规'
        } else if (row.assetsType === 2) {
          return '容器'
        }
      },
      // 更改记录
      async changeRecord(val) {
        var index = val.deployAppGitUrl.lastIndexOf('.')
        var url = val.deployAppGitUrl.substring(0, index)
        window.open(url + '/compare/' + val.tagUrl + '...' + val.deployVersionCode)
      },
      formatterPublishStatus: function(row, column) {
        switch (row.publishStatus) {
          case 300:
            return '发布完毕'
          case -100:
            return '应用部署失败'
        }
      },
      async searchPublishHistoryEvent() {
        let response = await this.searchPublishHistory(Object.assign(this.page, {
          proId: this.proId,
          envId: this.envId,
          appName: this.appName,
          optionStatus: this.optionStatus ? this.optionStatus : '-1'
        }))
        if (~~response.code === 1) {
          this.publishHistory = response.data.results
          this.page = response.data.meta
        }
      }
    },
    async created() {
      this.searchPublishHistoryEvent()
    },
    async beforeDestroy() {
      this.publishHistory = []
    }
  }
</script>
