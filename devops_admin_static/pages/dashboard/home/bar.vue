<template>
  <div>
    <h3 style="float: left">项目应用发布统计图</h3>
    <template>
      <el-radio-group ref='refs' v-model="activeTabName" style="margin-left: 350px;margin-top: 20px" @change="tabClick()">
        <el-radio-button label="month">最近30天</el-radio-button>
        <el-radio-button label="week">最近7天</el-radio-button>
        <el-radio-button label="day">当日发布</el-radio-button>
      </el-radio-group>
    </template>
    <no-ssr>
      <chart ref="ref" :options="option" class="echarts" style="width: 900px"></chart>
    </no-ssr>
  </div>
</template>

<script>
import {
  mapActions
} from 'vuex'
import {
  dateFormat
} from './DashBoardUtils.js'

export default {
  props: {
    proId: Number,
    envId: Number
  },
  data() {
    return {
      time: '',
      interval: null,
      appList: [],
      activeTabName: 'month',
      option: {
        grid: {
          left: '2%',
          right: '4%',
          bottom: '10%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: true,
          axisTick: {
            inside: true,
            interval: 0
          },
          data: []
        },
        dataZoom: [{
          id: 'dataZoomX',
          type: 'slider',
          left: '10%',
          right: '10%'
        }],
        yAxis: {
          type: 'value'
        },
        legend: {
          type: 'scroll',
          top: 'left',
          data: ['发布成功', '发布失败']
        },
        tooltip: {},
        series: [{
          name: '发布成功',
          color: '#26C281',
          barMaxWidth: '20%',
          type: 'bar',
          label: {
            normal: {
              show: false
            }
          },
          data: []
        }, {
          name: '发布失败',
          color: '#D91E18 ',
          barMaxWidth: '20%',
          type: 'bar',
          label: {
            normal: {
              show: false
            }
          },
          data: []
        }]
      }
    }
  },
  watch: {
    envId: function(val) {
      this.getDepAppStatusData()
    }
  },
  methods: {
    ...mapActions('action', ['getDepAppStatus']),
    addSeries() {
      this.option.xAxis.data = []
      this.option.series[0].data = []
      this.option.series[1].data = []
      if (!this.appList) {
        this.option.xAxis.data.push(0)
        this.option.series[0].data.push(0)
        this.option.series[1].data.push(0)
      } else {
        this.appList.forEach(item => {
          this.option.xAxis.data.push(item.appName ? item.appName : 0)
          this.option.series[0].data.push(item.runSuccess ? item.runSuccess : 0)
          this.option.series[1].data.push(item.runFaile ? item.runFaile : 0)
        })
      }
    },
    tabClick() {
      let refTime = this.$refs.refs
      if (refTime.value === 'month') {
        this.time = dateFormat(30)
      } else if (refTime.value === 'week') {
        this.time = dateFormat(7)
      } else {
        this.time = dateFormat(0)
      }
      this.getDepAppStatusData()
    },
    async getDepAppStatusData() {
      var time = ''
      if (this.time) {
        time = this.time
      } else {
        time = dateFormat(30)
      }
      let response = await this.getDepAppStatus({
        proId: this.proId,
        envId: this.envId,
        time: time
      })
      this.appList = response.data
      this.addSeries()
    }
  },
  created() {
    this.getDepAppStatusData()
    this.interval = setInterval(async () => {
      this.getDepAppStatusData()
    }, 1000 * 60)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>
