<template lang="html">
  <div class="portlet light bg-inverse">
    <div class="portlet-title">
      <div class="caption">{{app.appName+'#'+server.assetsName}}</div>
      <div class="tools">
        <a href="javascript:;" v-if="!isStop" @click="toggleLogBtn" class="font-red icon-control-pause" title="停止"></a>
        <a href="javascript:;" v-else @click="toggleLogBtn" class="font-green-jungle icon-control-play" title="继续"></a>
        &nbsp;
        <a href="javascript:;" v-if="showDetail" @click="showDetail=!showDetail" class="collapse" title="收起"> </a>
        <a href="javascript:;" v-if="!showDetail" @click="showDetail=!showDetail" class="expand" title="展开"> </a>
      </div>
    </div>
    <div class="portlet-body" :style="{height: len<=2?'800px':'300px'}" v-show="showDetail">
      <el-scrollbar ref="consoleScrollBar" tag="div" style="overflow-wrap: break-word;" wrap-class="el-select-dropdown__wrap" view-class="el-select-dropdown__list" :horizontal="false">
        <div v-html="logStr">{{logStr}}</div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  props: {
    appId: String,
    serverId: String,
    len: Number
  },
  data() {
    return {
      showDetail: true,
      isStop: false,
      logWs: null,
      server: {},
      app: {},
      logData: []
    }
  },
  computed: {
    logStr: function () {
      return this.logData.join('<br/>')
    }
  },
  watch: {
    isStop: function () {
      this.closeWs()
      if (!this.isStop) {
        this.showLog()
      }
    }
  },
  methods: {
    ...mapActions('action', ['getServer', 'getApp']),
    async getServerEvent() {
      let response = await this.getServer(this.serverId)
      if (~~response.code === 1) {
        this.server = response.data
      }
    },
    async getAppEvent() {
      let response = await this.getApp(this.appId)
      if (~~response.code === 1) {
        this.app = response.data
      }
    },
    showLog() {
      let self = this
      this.$nextTick(function () {
        self.logWs = self.$io.connect(`${process.env.wsURL}/serverlog`, self.$store.state.token, {
          'app_id': this.appId,
          'host_id': this.serverId
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
    toggleLogBtn() {
      this.isStop = !this.isStop
    },
    closeWs() {
      if (this.logWs) {
        this.logWs.close()
      }
    }
  },
  mounted() {
    this.getServerEvent()
    this.getAppEvent()
    this.showLog()
  },
  destroyed() {
    this.closeWs()
  }
}
</script>

<style lang="css">
.portlet > .portlet-title > .tools > a:focus {
    text-decoration: none;
    transition: all 0.1s ease-in-out;
    opacity: 0.8;
    filter: alpha(opacity=80);
}
</style>
