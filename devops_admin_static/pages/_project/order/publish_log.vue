<template>
<div>
  <portlet title="发布日志" :showSearchBar="false">
    <div class="row" v-for="(item,key) in orderDetails">
      <div class="col-xs-12">
        <div class="portlet box grey-steel">
          <div class="portlet-title">
            <div class="caption">{{key}}</div>
            <div class="tools">
              <a href="javascript:;" v-if="item.showDetail" @click="toggleDetail(key, item)" class="collapse" title="收起"> </a>
              <a href="javascript:;" v-if="!item.showDetail" @click="toggleDetail(key, item)" class="expand" title="展开"> </a>
            </div>
          </div>
          <div class="portlet-body" v-show="item.showDetail">
            <el-form label-width="120px">
              <el-row>
                <el-col :span="11">
                  <el-form-item label="应用名称">{{item.appName}}</el-form-item>
                </el-col>
                <el-col :span="2"> </el-col>
                <el-col :span="11">
                  <el-form-item label="应用版本">
                    <el-tooltip class="item" effect="dark" placement="right-start">
                      <template slot="content">
                        {{item.version.versionDesc}}
                      </template>
                      <span>{{item.version.versionCode}}</span>
                    </el-tooltip>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="发布服务器">{{item.serverName}}</el-form-item>
                </el-col>
                <el-col :span="2"> </el-col>
                <el-col :span="11">
                  <el-form-item label="发布时间">{{item.createDate}}</el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-steps :active="1" finish-status="success" simple>
                    <el-step title="目录检测" :status="getPublishStatus(50, item.deployStatus)"></el-step>
                    <el-step title="代码检出" :status="getPublishStatus(100, item.deployStatus)"></el-step>
                    <el-step title="版本检测" :status="getPublishStatus(150, item.deployStatus)"></el-step>
                    <el-step title="服务启动" :status="getPublishStatus(200, item.deployStatus)"></el-step>
                    <el-step title="服务检测" :status="getPublishStatus(250, item.deployStatus)"></el-step>
                  </el-steps>
                </el-col>
              </el-row>
              <el-row>
                <el-col>
                  <el-main class="codewrapper" style="height: 300px;">
                    <no-ssr placeholder="Loading...">
                      <codemirror class="code-container fh" v-model="item.deployLog" :options="codeOptions">
                      </codemirror>
                    </no-ssr>
                  </el-main>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </portlet>
</div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  data: function () {
    return {
      interval: null,
      showAppInstanceDetail: true,
      orderDetails: {},
      codeOptions: {
        readOnly: true,
        lineNumbers: false
      }
    }
  },
  computed: {
    orderInfoIds: function () {
      return this.$route.query.id.split(',')
    }
  },
  watch: {
    orderDetails: function () {
      clearInterval(this.interval)
      this.interval = setInterval(() => {
        this.pollQueryOrderDetails()
      }, 10000)
    }
  },
  methods: {
    ...mapActions('action', ['getPublishOrderDetail']),
    getPublishStatus(statusCode, currentStatus) {
      if (currentStatus === statusCode) {
        return 'progress'
      } else if (currentStatus === -statusCode) {
        return 'error'
      } else if (Math.abs(currentStatus) > statusCode) {
        return 'success'
      } else if (Math.abs(currentStatus) < statusCode) {
        return 'wait'
      }
    },
    toggleDetail(key, item) {
      item.showDetail = !item.showDetail
      this.$set(this.orderDetails, key, JSON.parse(JSON.stringify(item)))
    },
    async getOrderDetail(orderDetailId) {
      let response = await this.getPublishOrderDetail(orderDetailId)
      if (~~response.code === 1) {
        return response.data
      }
      return null
    },
    async initOrderDetails() {
      this.orderInfoIds.forEach(async (item, idx) => {
        let orderDetail = await this.getOrderDetail(item)
        if (orderDetail) {
          orderDetail.showDetail = true
          this.$set(this.orderDetails, orderDetail.appName + '[' + orderDetail.serverName + ']', orderDetail)
        }
      })
    },
    async pollQueryOrderDetails() {
      for (let key in this.orderDetails) {
        let value = this.orderDetails[key]
        if (value.showDetail) {
          let orderDetail = await this.getOrderDetail(value.orderInfoId)
          if (orderDetail) {
            this.$set(this.orderDetails, key, Object.assign(value, orderDetail))
          }
        }
      }
    }
  },
  async mounted() {
    this.initOrderDetails()
  }
}
</script>
