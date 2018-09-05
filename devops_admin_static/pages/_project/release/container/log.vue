<template lang="html">
  <el-scrollbar ref="consoleScrollBar" tag="div" style="overflow-wrap: break-word;" wrap-class="el-select-dropdown__row" view-class="el-select-dropdown__list" :horizontal="false">
    <div v-html="logStr">{{logStr}}</div>
  </el-scrollbar>
</template>

<script>
  export default {
    props: {
      publishId: Number,
      handler: Boolean
    },
    data() {
      return {
        logWs: null,
        logData: []
      }
    },
    computed: {
      logStr: function() {
        return this.logData.join('<br/>')
      }
    },
    methods: {
      reloadLog() {
        this.closeEvent()
        this.showLog()
      },
      showLog() {
        let self = this
        this.$nextTick(function() {
          self.logWs = self.$io.connect(`${process.env.wsURL}/publishlog`, self.$store.state.token, {
            'publish_id': self.publishId
          })
          self.logWs.on('connect', () => {
            self.logWs.emit('show')
            self.logData = []
          })

          let wrap = self.$refs['consoleScrollBar']
          self.logWs.on('show', (data) => {
            if (self.logData.length >= 500) {
              self.logData = self.logData.slice(-300)
            }
            self.logData = self.logData.concat(data.msg)
            wrap.wrap.scrollTop = 100 + (100 * wrap.wrap.clientHeight / parseFloat(wrap.sizeHeight || 1))
          })
        })
      },
      closeEvent() {
        if (this.logWs) {
          this.logWs.close()
        }
      }
    },
    mounted() {
      if (this.handler) {
        this.showLog()
      }
    },
    destroyed() {
      this.closeEvent()
    }
  }
</script>

<style lang="css" scoped="scoped">

</style>
