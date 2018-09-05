<template>
<div id="dashboard">
  <el-row :gutter="10" style="padding-bottom: 10px;">
    <el-col :span="6">
      <el-card shadow="hover" class="bg-purple-1">
        <el-container>
          <el-aside align="center" width="50%">
            <span><h4><b>用户数量</b></h4></span>
            <span><h2><b>{{ userInfo.enable+userInfo.disable }}</b></h2></span>
          </el-aside>
          <el-main>
            <span><h5><b>启用： {{ userInfo.enable }}</b></h5></span>
            <span><h5><b>禁用： {{ userInfo.disable }}</b></h5></span>
          </el-main>
        </el-container>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" class="bg-purple-2">
        <el-container>
          <el-aside align="center" width="50%">
            <span><h4><b>服务器数量</b></h4></span>
            <span><h2><b>{{ serverUseInfo.total }}</b></h2></span>
          </el-aside>
          <el-main>
            <span><h5><b>已使用： {{ serverUseInfo.used }}</b></h5></span>
            <span><h5><b>未使用： {{ serverUseInfo.total-serverUseInfo.used }}</b></h5></span>
          </el-main>
        </el-container>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" class="bg-purple-3">
        <el-container>
          <el-aside align="center" width="50%">
            <span><h4><b>发布应用数量</b></h4></span>
            <span><h2><b>{{ deployAppInfo.runSuccess+deployAppInfo.runFaile }}</b></h2></span>
          </el-aside>
          <el-main>
              <span><h5><b>发布成功： {{ deployAppInfo.runSuccess }}</b></h5></span>
              <span><h5><b>发布失败： {{ deployAppInfo.runFaile }}</b></h5></span>
          </el-main>
        </el-container>
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" class="bg-purple-4">
        <el-container>
          <el-aside align='center' width="50%">
            <span><h4><b>当前环境</b></h4></span>
            <span><h2><b>{{ envName }}</b></h2></span>
          </el-aside>
          <el-main>
            <el-select :collapse-tags="true" v-model="envId" placeholder="请选择" @change="getDeployCount">
              <el-option v-for="item in envs" :key="item.envId" :label="item.envName" :value="item.envId"></el-option>
            </el-select>
          </el-main>
        </el-container>
      </el-card>
    </el-col>
  </el-row>
  <el-card shadow="hover" :class="classSets.divRow" v-for="pro in data">
    <el-collapse v-model="activeNames">
      <el-collapse-item :name='pro.proId + ""'>
        <template slot="title">
            <el-container>
              <el-header style="margin-top: -20px;">
                <top :proId="pro.proId" :envId="envId"></top>
              </el-header>
            </el-container>
       </template>
        <div style="width: 100%;">
          <div class="el-row">
            <div class="col-md-5 col-xs-1 col-sm-2" style="width: 900px;">
              <bar :time="time['time' + pro.proId]" :proId="pro.proId" :envId="envId"></bar>
            </div>
            <div class="col-md-5 col-xs-1 col-sm-2" style="width:500px;margin-left: 200px;">
              <h3 style="margin-bottom: 20px;">服务器应用状态</h3>
              <pie :time="time['time' + pro.proId]" :proId="pro.proId" :envId="envId"  align='right'></pie>
            </div>
          </div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</div>
</template>

<script>
import Bar from './bar'
import Pie from './pie'
import Top from './top'
import {
  dateFormat
} from './DashBoardUtils.js'
import {
  mapActions
} from 'vuex'

export default {
  components: {
    Top,
    Bar,
    Pie
  },
  data() {
    return {
      data: {},
      classSets: {
        divRow: 'div-row dashboard-stat visual',
        divCol: 'col-xs-2 col-sm-6 col-xs-1 vertical-middle',
        divSpanNumber: ''
      },
      time: {},
      activeTabName: 'month',
      activeNames: [],
      serverUseInfo: {
        total: 0,
        used: 0
      },
      userInfo: {
        enable: 0,
        disable: 0
      },
      deployAppInfo: {
        runSuccess: 0,
        runFaile: 0
      },
      proCount: 0,
      envId: null,
      envName: '',
      envs: []
    }
  },
  methods: {
    ...mapActions('action', ['getUserCountInfo', 'searchEnvs', 'searchPros', 'getServerUseInfo', 'getDeployAppInfo']),
    tabClick(pro) {
      let refTime = this.$refs['time' + pro.proId][0]
      if (refTime.value === 'month') {
        this.$set(this.time, 'time' + pro.proId, dateFormat(30))
        refTime.value = 'month'
      } else if (refTime.value === 'week') {
        this.$set(this.time, 'time' + pro.proId, dateFormat(7))
        refTime.value = 'week'
      } else {
        this.$set(this.time, 'time' + pro.proId, dateFormat(0))
        refTime.value = 'day'
      }
    },
    getDeployCount(val) {
      this.envs.forEach(item => {
        if (val === item.envId) {
          this.envName = item.envName
        }
      })
    },
    // 获取应用发布状态
    async getDeployInfo() {
      let response = await this.getDeployAppInfo()
      if (response.code === '1') {
        this.deployAppInfo = response.data
      }
    },
    // 获取服务器使用状态
    async getServerInfo() {
      let response = await this.getServerUseInfo()
      if (response.code === '1') {
        this.serverUseInfo = response.data
      }
    },
    // 获取所有的环境
    async getEnv() {
      let response = await this.searchEnvs()
      if (~~response.code === 1) {
        let envs = response.data
        this.envs = envs
        if (this.envs && !this.envId) {
          this.envId = envs[0].envId
          this.envName = envs[0].envName
        }
      }
      this.getPro()
      this.getUserInfo()
    },
    // 获取所有项目
    async getPro() {
      let response = await this.searchPros()
      if (~~response.code === 1) {
        this.data = response.data
      }
      if (this.data) {
        this.data.forEach(item => {
          this.activeNames.push(item.proId + '')
        })
      }
    },
    // 获取用户状态
    async getUserInfo() {
      let response = await this.getUserCountInfo()
      if (response.code === '1') {
        this.userInfo = response.data
      }
    }
  },
  created() {
    this.getEnv()
    this.getServerInfo()
    this.getDeployInfo()
  },
  mounted() {
    document.getElementById('dashboard').parentElement.style = 'background:#F2F4F5'
  }
}
</script>

<style scoped>
.db-label {
  font-size: 18px;
  margin-left: 5px;
  margin-right: 20px;
}

.div-row {
  /*background: #0c203a;*/
  height: 100%;
}

.vertical-middle {
  position: relative;
  padding-top: 18px;
  padding-bottom: 18px;
}

.bg-purple-1 {
  height:140px;
  border-bottom:solid 4px #716aca;
}
.bg-purple-2 {
  height:140px;
  border-bottom:solid 4px #34bfa3;
}
.bg-purple-3 {
  height:140px;
  border-bottom:solid 4px #f4516c;
}
.bg-purple-4 {
  height:140px;
  border-bottom:solid 4px #36a3f7;
}
</style>
