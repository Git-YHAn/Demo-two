<template>
  <portlet title="容器发布" :showSearchBar="false">
    <container-form v-show="handler==='CONTANER'" ref="containerForm" :proId="proId" :envId="envId" :publishingApps="publishConApps" :handler="handler"></container-form>
    <progresies-form ref="progressForm" :publishApps="publishConApps" v-show="handler==='PROGRESIES'"></progresies-form>
    <footer slot="footer" style="text-align:right;">
      <el-button v-if="handler==='CONTANER'" type="primary" v-loading="loading" @click="affirmOpen">确认发布</el-button>
      <el-button v-if="handler==='CONTANER'" type="primary" @click="returnCustom">返回</el-button>
      <el-button v-if="handler==='PROGRESIES'" type="primary" @click="returnSelect">上一步
      </el-button>
    </footer>
    <affirm-form :obj="selectApps" :handlers="handlers" @reset="resetHandler" @save="changeServer"></affirm-form>
  </portlet>
  </template>
<script>
import { mapActions, mapGetters } from 'vuex'
import containerForm from './container.vue'
import progresiesForm from './progresies.vue'
import affirmForm from './affirm.vue'
export default {
  components: {
    containerForm,
    progresiesForm,
    affirmForm
  },
  data() {
    return {
      handler: 'CONTANER',
      loading: false,
      interval: null,
      handlers: '',
      selectApps: [],
      publishApps: [],
      publishConApps: []
    }
  },
  computed: {
    ...mapGetters('project', ['currentEnvironment', 'currentProject']),
    proId: function () {
      return this.currentProject.proId
    },
    envId: function () {
      return this.currentEnvironment.envId
    }
  },
  watch: {
    handler: function (val) {
      if (val === 'PROGRESIES') {
        this.pollingQueryPublishApps()
      } else {
        clearInterval(this.interval)
      }
    },
    '$route': function () {
      clearInterval(this.interval)
    }
  },
  methods: {
    ...mapActions('action', ['queryPublishApps', 'savePublishApp', 'queryPublishingApps', 'queryConPublishingApps']),
    resetHandler() {
      this.handlers = ''
    },
    changeServer() {
      this.handlers = ''
      this.handler = 'PROGRESIES'
    },
    affirmOpen() {
      this.selectApps = this.$refs.containerForm.selectApps
      if (this.selectApps.length === 0) {
        this.$msg.warning('你还有没选择要发布的应用哟')
      } else {
        for (var i = 0; i < this.selectApps.length; i++) {
          if (this.selectApps[i].assetsHosts.length === 0) {
            this.$msg.warning(this.selectApps[i].appName + '应用没有选择服务器哟')
            return
          }
        }
        this.handlers = 'AFFIRM'
      }
    },
    pollingQueryPublishApps() {
      this.interval = setInterval(async () => {
        let conResponse = await this.queryConPublishingAppsEvent()
        if (~~conResponse.code !== 1) {
          clearInterval(this.interval)
        }
      }, 1500)
    },
    async queryConPublishingAppsEvent() {
      let conResponse = await this.queryConPublishingApps({
        proId: this.proId,
        envId: this.envId
      })
      if (~~conResponse.code === 1) {
        this.publishConApps = conResponse.data
      }
      return conResponse
    },
    returnCustom() {
      if (this.handler === 'CONTANER') {
        this.handler = 'PROGRESIES'
      }
    },
    returnSelect() {
      this.handler = 'CONTANER'
    }
  },
  async created() {
    await this.queryConPublishingAppsEvent()
    if (this.publishConApps.length > 0) {
      this.handler = 'PROGRESIES'
    }
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>
