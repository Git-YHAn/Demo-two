orderInfo.<template>
<div>
  <portlet title="发布详情" :showSearchBar="false">
    <div class="row">
      <div class="col-xs-12">
        <div class="portlet box grey-steel">
          <div class="portlet-title">
            <div class="caption">工单信息</div>
            <div class="tools">
              <a href="javascript:;" v-if="showOrderDetail" @click="showOrderDetail=!showOrderDetail" class="collapse" title="收起"> </a>
              <a href="javascript:;" v-if="!showOrderDetail" @click="showOrderDetail=!showOrderDetail" class="expand" title="展开"> </a>
            </div>
          </div>
          <div class="portlet-body" v-show="showOrderDetail">
            <el-form ref="form" :vaule="orderInfo" label-width="80px" label-position="top">
              <el-row>
                <el-col :span="24">
                  <el-form-item label="工单标题">{{orderInfo.orderTitle}}</el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="11">
                  <el-form-item label="创建人">{{orderInfo.operateUserName}}</el-form-item>
                </el-col>
                <el-col :span="2"> </el-col>
                <el-col :span="11">
                  <el-form-item label="创建时间">{{orderInfo.createDate}}</el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item>
                    <template slot="label">发布信息 <el-button @click="showPublishDetail=!showPublishDetail" type="text">{{showPublishDetail?'收起':'展开'}}</el-button></template>
                    <div :class="{'cst-collapse-content': !showPublishDetail}" v-html="orderInfo.orderDetails">{{orderInfo.orderDetails}}</div>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item>
                    <el-button @click="$router.back()">返回</el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-12">
        <div class="portlet box grey-steel">
          <div class="portlet-title">
            <div class="caption">任务列表</div>
            <div class="tools">
              <el-button @click="showOrderDetailLogs" :disabled="selectedOrderDetails.length === 0 || isAllWaiting">发布日志</el-button>
              <el-button @click="autoPublishApp" :disabled="selectedOrderDetails.length === 0 || !isAllWaiting && !isAllFinished">自动发布</el-button>
              <el-button @click="deployCodeApp" :disabled="selectedOrderDetails.length === 0 || !isAllWaiting && !isAllFinished">部署代码</el-button>
              <el-button @click="restartServiceApp" :disabled="selectedOrderDetails.length === 0 || !isAllDeployed">重启服务</el-button>
              <!-- <el-button>取消发布</el-button> -->
            </div>
          </div>
          <div class="portlet-body">
            <el-table ref="selectionTable" :data="orderInfo.deployOrderInfos" row-key='orderInfoId' show-overflow-tooltip @selection-change="selectOrderDetailEvent">
              <el-table-column type="selection" width="50" align="center" :reserve-selection="true"> </el-table-column>
              <el-table-column label="应用实例名称" prop="appName" align="center" width="130px" show-overflow-tooltip></el-table-column>
              <el-table-column label="服务器名称" prop="serverName"  align="center" width="130px" show-overflow-tooltip></el-table-column>
              <el-table-column label="发布版本" align="center" width="130px" show-overflow-tooltip>
                <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" placement="right-start">
                      <template slot="content">
                        {{scope.row.version.versionDesc}}
                      </template>
                      <span>{{scope.row.version.versionCode}}</span>
                    </el-tooltip>
                </template>
              </el-table-column>
              <el-table-column label="是否并行发布" align="center" width="100px" show-overflow-tooltip>
                <template slot-scope="scope">
                  {{scope.row.deployType===1?'是':'否'}}
                </template>
              </el-table-column>
              <el-table-column label="发布状态" align="center" min-width="100px" show-overflow-tooltip>
                <template slot-scope="scope">
                  <div class="progress inline-label-progress" :class="{'progress-striped': scope.row.deployStatus>0  && scope.row.deployStatus<500}" >
                    <div class="progress-bar " :class="getStatusBarClass(50, scope.row.deployStatus)" style="width: 20%">目录检测</div>
                    <div class="progress-bar " :class="getStatusBarClass(100, scope.row.deployStatus)" style="width: 20%">代码检出</div>
                    <div class="progress-bar " :class="getStatusBarClass(150, scope.row.deployStatus)" style="width: 20%">版本检测</div>
                    <div class="progress-bar " :class="getStatusBarClass(200, scope.row.deployStatus)" style="width: 20%">服务启动</div>
                    <div class="progress-bar " :class="getStatusBarClass(250, scope.row.deployStatus)" style="width: 20%">服务检测</div>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="开始时间" prop="startDate" align="center" width="130px" show-overflow-tooltip></el-table-column>
              <el-table-column label="结束时间" prop="endDate" align="center" width="130px" show-overflow-tooltip></el-table-column>
              <el-table-column width="130px" show-overflow-tooltip>
                <template slot-scope="scope">
                    <el-dropdown split-button plain placement="bottom-start" trigger="click">
                        <a href="javascript:;" @click="openLogWindow(scope.row)">发布日志</a>
                        <el-dropdown-menu class="cst-dropdown" slot="dropdown" >
                          <el-dropdown-item :disabled="scope.row.deployStatus>0 && scope.row.deployStatus<300" >自动发布</el-dropdown-item>
                          <el-dropdown-item :disabled="scope.row.deployStatus>0 && scope.row.deployStatus<300">部署代码</el-dropdown-item>
                          <el-dropdown-item :disabled="scope.row.deployStatus<=150 || scope.row.deployStatus>=200">重启服务</el-dropdown-item>
                        </el-dropdown-menu>
                      </el-dropdown>
                  </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </div>
  </portlet>
</div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  data: function () {
    return {
      interval: null,
      showOrderDetail: true,
      showPublishDetail: false,
      selectedOrderDetails: [],
      orderInfo: {}
    }
  },
  computed: {
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function () {
      return this.currentProject.proId
    },
    envId: function () {
      return this.currentEnvironment.envId
    },
    orderId: function () {
      return this.$route.params.id
    },
    isAllWaiting: function () {
      let isWait = true
      this.selectedOrderDetails.forEach(item => {
        if (item.deployStatus !== 0) {
          isWait = false
        }
      })
      return isWait
    },
    isAllDeployed: function () {
      let isDeployed = true
      this.selectedOrderDetails.forEach(item => {
        if (item.deployStatus <= 150 || item.deployStatus >= 200) {
          isDeployed = false
        }
      })
      return isDeployed
    },
    isAllFinished: function () {
      let isFinish = true
      this.selectedOrderDetails.forEach(item => {
        if (item.deployStatus >= 0 && item.deployStatus <= 250) {
          isFinish = false
        }
      })
      return isFinish
    }
  },
  methods: {
    ...mapActions('action', ['getPublishOrderInfo', 'autoPublish', 'deployCode', 'restartService']),
    getNewObj() {
      // 解除双向绑定
      let obj = JSON.parse(JSON.stringify(this.orderInfo))
      obj.deployOrderInfos = this.selectedOrderDetails
      obj.deployOrderInfos.forEach(item => {
        item.depAppVerId = item.version.versionId
      })
      return obj
    },
    // 自动发布
    async autoPublishApp() {
      let obj = this.getNewObj()
      this.autoPublish(obj)
    },
    // 部署代码
    async deployCodeApp() {
      let obj = this.getNewObj()
      this.deployCode(obj)
    },
    // 重启服务
    async restartServiceApp() {
      let obj = this.getNewObj()
      this.restartService(obj)
    },
    getStatusBarClass(statusCode, currentCode) {
      if (Math.abs(currentCode) < statusCode) { // 等待发布
        return 'progress-bar-wait'
      } else if (currentCode === -statusCode) { // 发布失败
        return 'progress-bar-danger'
      } else if (currentCode === statusCode) { // 发布中
        return 'progress-bar-info active'
      } else { // 发布成功
        return 'progress-bar-success'
      }
    },
    selectOrderDetailEvent(val) {
      this.selectedOrderDetails = val
    },
    showOrderDetailLogs() {
      let orderInfoIds = this.selectedOrderDetails.map(item => {
        return item.orderInfoId
      })
      window.open(`/${this.currentProject.proCode}/order/publish_log?id=${orderInfoIds}`)
    },
    openLogWindow(item) {
      window.open(`/${this.currentProject.proCode}/order/publish_log?id=${item.orderInfoId}`)
    },
    async getPublishOrderInfoEvent() {
      let response = await this.getPublishOrderInfo(this.orderId)
      if (~~response.code === 1) {
        this.orderInfo = response.data
      }
    }
  },
  mounted() {
    this.getPublishOrderInfoEvent()
    this.interval = setInterval(() => {
      this.getPublishOrderInfoEvent()
    }, 5000)
  },
  destroyed() {
    clearInterval(this.interval)
  }
}
</script>
