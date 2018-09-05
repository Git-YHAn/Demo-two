<template>
  <div>
    <div :class="classSets.divCol">
      <span class="icon-directions"></span>
      <span class="db-label"><b>项目</b></span>
      <span class="db-label">{{ data.proName }}</span>
    </div>
    <div :class="classSets.divCol">
      <span class="icon-social-dropbox"></span>
      <span class="db-label"><b>使用服务器数量</b></span>
      <span class="db-label">{{ data.serverNum }}</span>
    </div>
    <div :class="classSets.divCol">
      <div class="icon-pie-chart"></div>
      <label class="db-label"><b>应用发布总计</b></label>
      <span class="db-label">{{ data.deployAppInfo.total }}</span>
    </div>
    <div :class="classSets.divCol">
      <div class="icon-pie-chart"></div>
      <label class="db-label"><b>应用发布成功率</b></label>
      <span class="db-label">{{ data.deployAppInfo.successNum === 0 ? 0 : Number(data.deployAppInfo.successNum/data.deployAppInfo.total*100).toFixed(2) }}%</span>
    </div>
  </div>
</template>

<script>
import {
  mapActions
} from 'vuex'

export default {
  props: {
    proId: Number,
    envId: Number
  },
  data() {
    return {
      data: {
        interval: '',
        deployAppInfo: {
          total: 0,
          successNum: 0,
          failNum: 0
        }
      },
      classSets: {
        divCol: 'col-md-3 vertical-middle'
      }
    }
  },
  watch: {
    envId: function(val) {
      this.dashboardDate()
    }
  },
  methods: {
    ...mapActions('action', ['getDashboardDate']),
    // 获取面板数据
    async dashboardDate() {
      let response = await this.getDashboardDate({envId: this.envId, proId: this.proId})
      if (~~response.code === 1) {
        if (response.data.deployAppInfo) {
          this.data = response.data
        } else {
          let deployAppInfo = {total: 0, successNum: 0, failNum: 0}
          response.data.deployAppInfo = deployAppInfo
          this.data = response.data
        }
      }
    }
  },
  created() {
    this.dashboardDate()
    this.interval = setInterval(async () => {
      this.dashboardDate()
    }, 1000 * 30)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>

<style scoped>
.db-label {
  font-size: 18px;
  margin-left: 5px;
  margin-right: 20px;
}

.vertical-middle {
  position: relative;
  padding-top: 18px;
  padding-bottom: 18px;
}
</style>
