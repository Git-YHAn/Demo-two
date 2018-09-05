<template>
  <no-ssr>
    <chart ref="timeChart" :options="option" class="echarts"></chart>
  </no-ssr>
</template>

<script>
  export default {
    name: 'TimeLine',
    data() {
      return {
        seriesData: [],
        intervalId: null,
        option: {
          title: {
            text: '按时间统计发布'
          },
          tooltip: {
            trigger: 'axis',
            formatter: function(params) {
              params = params[0]
              return params.value[0].split(' ')[1] + '\n value: ' + params.value[1]
            },
            axisPointer: {
              animation: false
            }
          },
          xAxis: {
            type: 'time',
            splitLine: {
              show: false
            }
          },
          yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
              show: false
            }
          },
          series: [
            {
              name: '模拟数据',
              type: 'line',
              showSymbol: false,
              hoverAnimation: false,
              data: null
            }
          ]
        }
      }
    },
    watch: {
      seriesData: async function() {
        this.option.series[0].data = this.seriesData
      }
    },
    methods: {
      init() {
        let count = 1000 * 1000 * 1000
        this.intervalId = setInterval(() => {
          count = count - 1
          if (count > 0) {
            let value = this.randomData()
            this.seriesData.push(value)
          }
        }, 5000)
      },
      randomData() {
        let now = new Date()
        let value = Math.random() * 100 + 10
        return {
          name: now.toDateString(),
          value: [
            [now.getFullYear(), now.getMonth(), now.getDate()].join('/') + ' ' + [now.getHours(), now.getMinutes(), now.getSeconds()].join(':'),
            Math.round(value)
          ]
        }
      }
    },
    created: async function() {
      this.init()
    },
    destroyed() {
      clearInterval(this.intervalId)
    }
  }
</script>

<style scoped>
  .echarts {
    max-height: 300px;
    max-width: 100%;
  }
</style>
