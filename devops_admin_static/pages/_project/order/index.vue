<template>
<div>
  <portlet title="发布工单">
    <template slot="search-bar">
      <el-form :inline="true" v-nosubmit>
        <el-form-item label="关键字">
          <el-input v-model="keywords" clearable placeholder="请输入关键字" @keyup.enter.native="searchEvent"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchEvent()">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template slot="title-bar-tools">
      <el-button type="plain" @click="$router.push('order/add')"><i class="glyphicon glyphicon-plus"></i> 创建发布工单</el-button>
    </template>
    <el-table :data="listData" row-key='orderId' show-overflow-tooltip>
      <el-table-column label="ID" prop="orderId" width="50" align="center"></el-table-column>
      <el-table-column label="工单标题" prop="orderTitle" min-width="150" show-overflow-tooltip></el-table-column>
      <el-table-column label="创建人" prop="operateUserName" width="100" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column label="创建时间" prop="createDate" width="180" align="center" ></el-table-column>
      <el-table-column label="状态" min-width="120" >
        <template slot-scope="scope">
          <div v-if="scope.row.orderStatus===0" class="progress bg-default cst-label-progress" >
              <div class="progress-bar progress-bar-default" style="width: 0%"></div>
              <p class="cst-label">等待发布</p>
          </div>
          <div v-if="scope.row.orderStatus===100" class="progress bg-default cst-label-progress progress-striped active" >
              <div class="progress-bar progress-bar-info" :style="{width: ((100*scope.row.successNum/scope.row.totalNum)+'%')}"></div>
              <p class="cst-label">已成功发布节点{{scope.row.successNum}}/{{scope.row.totalNum}}</p>
          </div>
          <div v-if="scope.row.orderStatus===200" class="progress bg-default cst-label-progress" >
              <div class="progress-bar progress-bar-success" style="width: 100%"></div>
              <p class="cst-label">已成功发布节点{{scope.row.successNum}}/{{scope.row.totalNum}}</p>
          </div>
          <div v-if="scope.row.orderStatus===-100" class="progress bg-default cst-label-progress progress-striped active" >
              <div class="progress-bar progress-bar-danger" :style="{width: ((100*scope.row.successNum/scope.row.totalNum)+'%')}"></div>
              <p class="cst-label">已发布失败节点{{scope.row.errorNum}}/{{scope.row.totalNum}}</p>
          </div>
          <div v-if="scope.row.orderStatus===-200" class="progress bg-default cst-label-progress" >
              <div class="progress-bar progress-bar-danger" :style="{width: ((100*scope.row.errorNum/scope.row.totalNum)+'%')}"></div>
              <p class="cst-label">已发布失败节点{{scope.row.errorNum}}/{{scope.row.totalNum}}</p>
          </div>
        </template>
      </el-table-column>
      <el-table-column width="150" >
        <template slot-scope="scope">
          <el-dropdown split-button plain placement="bottom-start" trigger="click">
              <a href="javascript:;" @click="$router.push(`order/detail/${scope.row.orderId}`)">查看详情</a>
              <el-dropdown-menu class="cst-dropdown" slot="dropdown" >
                <el-dropdown-item @click.native="copyOrderEvent(scope.row)">复制工单</el-dropdown-item>
                <el-dropdown-item :disabled="Math.abs(scope.row.orderStatus)!=0" @click.native="dropOrderEvent(scope.row)" divided>删除工单</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <template slot="footer">
      <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
    </template>
  </portlet>
</div>
</template>
<script>
import { mapActions, mapGetters, mapMutations } from 'vuex'
export default {
  data: function () {
    return {
      listData: [],
      keywords: '',
      interval: null,
      page: { pageNum: 1, pageSize: 30 }
    }
  },
  computed: {
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function () {
      return this.currentProject.proId
    },
    envId: function () {
      return this.currentEnvironment.envId
    }
  },
  methods: {
    ...mapActions('action', ['queryPublishOrders', 'getPublishOrderInfo', 'dropPublishOrder']),
    ...mapMutations('project', ['SET_ORDER_INFO']),
    searchEvent() {
      this.page.pageNum = 1
      this.doSearchOrders()
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.doSearchOrders()
    },
    setPageNum: function (num) {
      this.page.pageNum = num
      this.doSearchOrders()
    },
    async doSearchOrders() {
      let response = await this.queryPublishOrders({ 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum, 'proId': this.proId, 'envId': this.envId, 'title': this.keywords })
      if (~~response.code === 1) {
        this.listData = response.data.results
        this.page = response.data.meta
      }
    },
    async copyOrderEvent(row) {
      let response = await this.getPublishOrderInfo(row.orderId)
      if (~~response.code === 1) {
        this.SET_ORDER_INFO(response.data)
      }
      this.$router.push(`order/add`)
    },
    dropOrderEvent(row) {
      this.$confirm(`此操作将永久删除该工单[${row.orderTitle}], 是否继续?`, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }).then(async () => {
        let response = await this.dropPublishOrder(row.orderId)
        if (~~response.code === 1) {
          this.$msg.success('删除工单成功！')
          this.searchEvent()
        } else {
          this.$msg.error(response.msg)
        }
      })
    }
  },
  mounted() {
    this.searchEvent()
    this.interval = setInterval(() => {
      this.doSearchOrders()
    }, 5000)
  },
  destroyed() {
    clearInterval(this.interval)
  }
}
</script>
