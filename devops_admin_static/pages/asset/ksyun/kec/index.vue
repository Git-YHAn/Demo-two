<template>
<div>
  <portlet title="金山云KEC">
    <div class="header-regions" slot="top-bar">
      <el-button :disabled="loading" v-for="item in regions" @click="form.region=item.region" :type="item.region===form.region?'primary':''" :key="item.region">{{item.regionName}}</el-button>
    </div>
    <template slot="search-bar">
    <el-form :disabled="loading" :inline="true" v-nosubmit>
      <el-form-item label="关键字">
        <el-input v-model="form.keywords" clearable placeholder="输入名称/ID/内网IP/EIP" @keyup.enter.native="searchEvent"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="searchEvent()">查询</el-button>
      </el-form-item>
    </el-form>
  </template>
    <template slot="title-bar-tools">
    <el-button type="primary" @click="createInstance" icon="el-icon-plus">新建实例</el-button>
    <el-button-group>
      <el-button :disabled="selectedInstance.length===0||selectedInstance.filter(item=>item.instanceState.name==='active').length>0" icon="el-icon-edit" @click="startupInstance">开启</el-button>
      <el-button :disabled="selectedInstance.length===0||selectedInstance.filter(item=>item.instanceState.name!=='active').length>0" icon="el-icon-share" @click="stopInstance">关闭</el-button>
      <el-button :disabled="selectedInstance.length===0||selectedInstance.filter(item=>item.instanceState.name!=='active').length>0" icon="el-icon-delete" @click="rebootInstance">重启</el-button>
    </el-button-group>
    <el-dropdown plain  placement="bottom-start" trigger="click">
      <el-button>更多操作<i class="el-icon-arrow-down el-icon--right"></i></el-button>
      <el-dropdown-menu class="cst-dropdown" slot="dropdown">
        <el-dropdown-item :disabled="selectedInstance.length==0" @click.native="reinstallSystem()">重装系统</el-dropdown-item>
        <el-dropdown-item :disabled="selectedInstance.length==0" @click.native="deleteInstance()">删除实例</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </template>

    <el-table :data="listData" ref="dataTable" @selection-change="selectionChangeEvent" row-key="instanceId" v-loading="loading">
    <el-table-column type="selection" width="50" align="center" :reserve-selection="true" > </el-table-column>
    <el-table-column label="名称/ID" width="180" align="left" >
      <template slot-scope="scope">
        <div class="primary-cell" :title="scope.row.instanceId">{{scope.row.instanceName}}</div>
        <div >
          <el-tooltip class="item" effect="light" placement="top">
            <template slot="content">
              {{scope.row.image.name}}
            </template>
            <i class="fa fa-ge"><span class="tooltip-icon mgl5">{{imageTypeKvm[scope.row.image.platform]||scope.row.image.platform}}</span></i>
          </el-tooltip>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="类型" width="100" align="center">
      <template slot-scope="scope">
        {{instanceFamilys[transInstanceType(scope.row.instanceType)]}}
      </template>
    </el-table-column>
    <el-table-column label="状态" prop="instanceState.name" width="120" align="center" sortable>
      <template slot-scope="scope">
        <span :class="transStateClass(scope.row.instanceState.name)">
          <i :class="scope.row.instanceState.name.indexOf('ing')>-1?'fa fa-spin fa-spinner':'glyphicon glyphicon-stop'" ></i>
          <span> {{instanceStateMap[scope.row.instanceState.name]}}</span>
        </span>
      </template>
    </el-table-column>
    <el-table-column label="所属网络" width="120" align="left">
      <template slot-scope="scope">
        <div class="multiline">{{scope.row.networkInterfaceSet[0].vpc.vpcName}}</div>
        <div class="multiline">{{scope.row.networkInterfaceSet[0].subNet.subnetName}}</div>
        <div class="multiline">{{scope.row.availabilityZoneName}}</div>
      </template>
    </el-table-column>
    <el-table-column label="配置" width="120" align="center">
      <template slot-scope="scope">
        {{scope.row.instanceConfigure.vcpu}}核{{scope.row.instanceConfigure.memoryGb}}G
        <el-tooltip class="item" effect="light" placement="top">
          <template slot="content">
            <template v-if="scope.row.instanceConfigure.rootDiskGb>0">
              系统盘:本地SSD盘[共{{scope.row.instanceConfigure.rootDiskGb}}G]
            </template>
            <template v-if="scope.row.instanceConfigure.dataDiskGb>0">
              数据盘:本地{{scope.row.instanceConfigure.dataDiskType}}盘[共{{scope.row.instanceConfigure.dataDiskGb}}G]
            </template>
          </template>
          <i class="fa fa-microchip"></i>
        </el-tooltip>
      </template>
    </el-table-column>
    <el-table-column label="IP地址" width="200" align="left">
      <template slot-scope="scope">
        <div :title="scope.row.privateIpAddress+'内'+(scope.row.networkInterfaceSet[0].address?(scope.row.networkInterfaceSet[0].address.publicIp+'公('+scope.row.networkInterfaceSet[0].address.bandWidth+'Mbps)'):'')">
          <el-tooltip class="item" effect="light" placement="top">
            <template slot="content">
              内网IP
            </template>
            <i class="ip-show pra-ip">内</i>
          </el-tooltip>
           {{scope.row.privateIpAddress}}
        </div>
        <div v-if="scope.row.networkInterfaceSet[0].address" :title="scope.row.privateIpAddress+'内'+(scope.row.networkInterfaceSet[0].address?(scope.row.networkInterfaceSet[0].address.publicIp+'公('+scope.row.networkInterfaceSet[0].address.bandWidth+'Mbps)'):'')">
          <el-tooltip class="item" effect="light" placement="top">
            <template slot="content">
              外网IP
            </template>
            <i class="ip-show pub-ip">公</i>
          </el-tooltip>
         {{scope.row.networkInterfaceSet[0].address.publicIp}}({{scope.row.networkInterfaceSet[0].address.bandWidth}}Mbps)
        </div>
      </template>
    </el-table-column>
    <el-table-column label="计费方式" width="180" align="left">
      <template slot-scope="scope">
        <div class="multiline">{{chargeTypeMap[scope.row.chargeType]}}</div>
        <div class="multiline">{{scope.row.creationDate | ksyunDateFormatter}} 创建</div>
      </template>
    </el-table-column>
    <el-table-column label="操作" min-width="150" align="center">
      <template slot-scope="scope">
        <el-dropdown split-button plain placement="bottom-start" trigger="click" @click='connectInstance(scope.row)'>
          <span>连接实例</span>
          <el-dropdown-menu class="cst-dropdown" slot="dropdown">
            <el-dropdown-item :disabled="scope.row.instanceState.name!=='stopped'" @click.native="reinstallSystem(scope.row)">重装系统</el-dropdown-item>
            <el-dropdown-item @click.native="deleteInstance(scope.row)">删除实例</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
    </el-table-column>
  </el-table>
    <template slot="footer">
    <pagination :current="page.pageNum" :total="page.total" :pagesize="page.pageSize" @pagesize="setPageSize" @pagenum="setPageNum"></pagination>
  </template>
  </portlet>
  <reboot-instance v-if="handler==='REBOOT'" :instances="selectedInstance" @save="changeStateEvent" @reset="resetHandler"></reboot-instance>
  <start-instance v-if="handler==='START'" :instances="selectedInstance" @save="changeStateEvent" @reset="resetHandler"></start-instance>
  <stop-instance v-if="handler==='STOP'" :instances="selectedInstance" @save="changeStateEvent" @reset="resetHandler"></stop-instance>
  <modify-image v-if="handler==='MODIFY_IMAGE'" :instances="selectedInstance" @save="changeStateEvent" @reset="resetHandler"></modify-image>
</div>
</template>

<script>
import 'static/js/ksyun/kvm-zh.js'
import { mapActions, mapGetters, mapState } from 'vuex'
import modifyImage from './modifyImage.vue'
import rebootInstance from './rebootInstance.vue'
import startInstance from './startInstance.vue'
import stopInstance from './stopInstance.vue'

export default {
  components: {
    modifyImage,
    rebootInstance,
    startInstance,
    stopInstance
  },
  data() {
    return {
      form: {
        region: 'cn-beijing-6',
        keywords: ''
      },
      interval: null,
      loading: false,
      handler: '',
      listData: [],
      selectedInstance: [],
      page: { pageNum: 1, pageSize: 30 }
    }
  },
  computed: {
    ...mapGetters('ksyun', ['imageTypeKvm', 'chargeTypeMap']),
    ...mapState('ksyun', ['regions', 'instanceFamilys']),
    instanceStateMap: function () {
      return global.kvm_lang_dict
    }
  },
  watch: {
    listData: function (data) {
      let list = data.filter(item => {
        return item.instanceState.name.indexOf('ing') > -1
      })
      if (list.length > 0 && !this.interval) {
        this.interval = setInterval(() => {
          this.doSearchEvent()
        }, 3000)
      } else if (list.length === 0 && this.interval) {
        clearInterval(this.interval)
      }
    },
    'form.region': async function () {
      this.page.pageNum = 1
      this.loading = true
      await this.doSearchEvent()
      this.loading = false
    }
  },
  methods: {
    ...mapActions('action', ['searchKsyunKecInstance']),
    ...mapActions('ksyun', ['searchInstanceFamilys', 'searchRegions']),
    searchEvent() {
      this.page.pageNum = 1
      this.doSearchEvent()
    },
    async doSearchEvent() {
      let response = await this.searchKsyunKecInstance({ 'pageSize': this.page.pageSize, 'pageNum': this.page.pageNum, 'keywords': this.form.keywords, 'region': this.form.region })
      if (~~response.code === 1) {
        this.listData = response.data.results
        this.page = response.data.meta
      }
    },
    selectionChangeEvent(selection) {
      this.selectedInstance = selection
    },
    setPageSize: function (size) {
      this.page.pageSize = size
      this.page.pageNum = 1
      this.doSearchEvent()
    },
    setPageNum: function (num) {
      this.page.pageNum = num
      this.doSearchEvent()
    },
    transInstanceType(type) {
      if (!type) {
        return ''
      }
      return type.split('.')[0]
    },
    transStateClass: function (state) {
      if (state.match(/^active$/g)) {
        return 'font-green-jungle'
      } else if (state.match(/^paused$/g)) {
        return 'font-blue'
      } else if (state.match(/^suspended|stopped|soft-delete|error$/g)) {
        return 'font-red'
      } else if (state.match(/^.*ing$/g)) {
        return 'font-yellow-gold'
      } else {
        return 'font-grey'
      }
    },
    createInstance() {
      this.$router.push('/asset/ksyun/kec/newInstance')
    },
    async startupInstance() {
      this.handler = 'START'
    },
    async stopInstance() {
      this.handler = 'STOP'
    },
    async rebootInstance() {
      this.handler = 'REBOOT'
    },
    reinstallSystem(...instance) {
      if (instance.length > 0) {
        this.selectedInstance = instance
      }
      this.handler = 'MODIFY_IMAGE'
    },
    deleteInstance(...instance) {
      console.error(instance)
    },
    resetHandler() {
      this.handler = ''
    },
    async changeStateEvent(list) {
      this.resetHandler()
      await this.doSearchEvent()
      this.$refs['dataTable'].clearSelection()
    }
  },
  async mounted() {
    this.searchRegions()
    this.loading = true
    await this.searchInstanceFamilys(this.form.region)
    await this.doSearchEvent()
    this.loading = false
  }
}
</script>
<style scoped>
.el-button-group {
  margin-left: 10px;
  margin-right: 10px;
}

.el-button {
  vertical-align: middle;
}

.cst-dropdown .el-dropdown-menu__item {
  text-align: center;
}

.el-table .primary-cell {
  font-size: 14px;
}

.multiline {
  line-height: 1.4rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.pra-ip {
  background-color: #ff9110;
}

.pub-ip {
  background-color: #40a54a;
}

.ip-show {
  font-style: normal;
  border-radius: 2px;
  color: #fff;
  padding: 0 3px;
  line-height: 14px;
  margin-left: 2px;
}
</style>
