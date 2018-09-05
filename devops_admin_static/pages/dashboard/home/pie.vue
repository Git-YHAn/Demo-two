<template>
<div :ref="'ref' + this.proId">
  <no-ssr>
    <chart :options="option"></chart>
  </no-ssr>
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
      interval: null,
      serverList: {},
      // 指定图表的配置项和数据
      option: {
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['发布中', '运行中', '已停止']
        },
        selectedMode: 'single',
        tooltip: {
          confine: true,
          enterable: true,
          position: function(pos, params, dom, rect, size) {
            // 鼠标在左侧时 tooltip 显示到右侧，鼠标在右侧时 tooltip 显示到左侧。
            let obj = {top: 80}
            obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 5
            return obj
          },
          formatter: function(params) {
            let table = ''
            if (params.data.info.length > 0) {
              params.data.info.forEach(item => {
                table = table + '<div class="table-tr"><div class="table-td">' + item.appName + '</div><div class="table-td">' + item.assetsName + '</div><div class="table-td">' + (item.versionCode === null ? '无' : item.versionCode) + '</div></div>'
              })
              let tables = '<div class="table"><div class="table-tr"><div class="table-th">应用名</div><div class="table-th">服务器</div><div class="table-th">当前版本</div></div>' + table + '</div>'
              return params.name + ': ' + params.data.info.length + tables
            } else {
              return params.name + ': ' + params.data.info.length
            }
          }
        },
        series: {
          name: '服务器状态',
          type: 'pie',
          radius: '75%',
          center: ['50%', '50%'],
          data: [{
            value: 0,
            itemStyle: {
              color: '#bfcad1'
            },
            name: '已停止',
            info: []
          }, {
            value: 0,
            itemStyle: {
              color: '#26C281'
            },
            name: '运行中',
            info: []
          }, {
            value: 0,
            itemStyle: {
              color: '#4B77BE'
            },
            name: '发布中',
            info: []
          }],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      }
    }
  },
  watch: {
    envId: function(val) {
      this.getServerStatusData()
    }
  },
  methods: {
    ...mapActions('action', ['getServerStatus']),
    addSeries() {
      if (this.serverList) {
        this.option.series.data.forEach(item => {
          this.serverList.forEach(detail => {
            if (item.name === '发布中' && detail.status === 1) {
              item.value = ~~detail.lists.length
              item.info = detail.lists
            } else if (item.name === '运行中' && detail.status === 0) {
              item.value = ~~detail.lists.length
              item.info = detail.lists
            } else if (item.name === '已停止' && detail.status === -1) {
              item.value = ~~detail.lists.length
              item.info = detail.lists
            }
          })
        })
      }
    },
    async getServerStatusData() {
      let response = await this.getServerStatus({
        proId: this.proId,
        envId: this.envId
      })
      this.serverList = response.data
      this.addSeries()
    }
  },
  created() {
    this.getServerStatusData()
    this.interval = setInterval(() => {
      this.getServerStatusData()
    }, 10000)
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>
<style type="text/css">
.table,
.table * {
  margin: 0 auto;
  padding: 0;
  font-size: 14px;
  font-family: Arial, 宋体, Helvetica, sans-serif;
}

.table {
  border-collapse: collapse;
  overflow-x: auto;
  overflow-y: auto;
  max-height: 300px;
  max-width: 400px;
}

.table-tr {
  display: table-row;
  height: 30px;
}

.table-th {
  display: table-cell;
  font-weight: bold;
  height: 100%;
  border: 1px solid gray;
  text-align: center;
  vertical-align: middle;
}

.table-td {
  display: table-cell;
  padding-left: 5px;
  padding-right: 5px;
  height: 100%;
  border: 1px solid gray;
  text-align: left;
  vertical-align: middle;
}
</style>
