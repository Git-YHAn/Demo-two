<template>
<portlet title="常规发布" :showSearchBar="false">
  <select-form v-show="handler==='SELECT'" ref="selectForm" :proId="proId" :envId="envId" :publishingApps="publishPhyApps" :handler="handler"></select-form>
  <progress-form ref="progressForm" :publishApps="publishPhyApps" v-show="handler==='PROGRESS'"></progress-form>

  <footer slot="footer" style="text-align:right;">
    <el-button v-if="handler==='SELECT'" type="primary" v-loading="loading" @click="confirmPublish">确认发布</el-button>
    <el-button v-if="handler==='SELECT'" type="primary" @click="returnCustom">返回</el-button>
    <el-button v-if="handler==='PROGRESS' || handler==='PROGRESIES'" type="primary" @click="returnSelect">上一步
    </el-button>
  </footer>
</portlet>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import selectForm from './select.vue'
import progressForm from './progress.vue'

export default {
  components: {
    selectForm,
    progressForm
  },
  data() {
    return {
      handler: 'SELECT',
      loading: false,
      interval: null,
      publishApps: [],
      publishPhyApps: []
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
      if (val === 'PROGRESS') {
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
    ...mapActions('action', ['queryPublishApps', 'savePublishApp', 'queryPublishingApps', 'queryPhyPublishingApps']),
    pollingQueryPublishApps() {
      this.interval = setInterval(async () => {
        let phyResponse = await this.queryPhyPublishingAppsEvent()
        if (~~phyResponse.code !== 1) {
          clearInterval(this.interval)
        }
      }, 1500)
    },
    async queryPhyPublishingAppsEvent() {
      let phyResponse = await this.queryPhyPublishingApps({
        proId: this.proId,
        envId: this.envId
      })
      if (~~phyResponse.code === 1) {
        this.publishPhyApps = phyResponse.data
      }
      return phyResponse
    },
    returnCustom() {
      if (this.handler === 'SELECT') {
        this.handler = 'PROGRESS'
      }
    },
    confirmPublish() {
      let selectApps = this.$refs.selectForm.selectApps
      if (selectApps && selectApps.length > 0) {
        this.loading = true
        selectApps.forEach(async item => {
          await this.savePublishApp(item)
        })
        this.loading = false
        this.handler = 'PROGRESS'
      } else {
        this.$msg.warning('请选择需要发布的项目')
      }
    },
    returnSelect() {
      this.handler = 'SELECT'
    }
  },
  async created() {
    await this.queryPhyPublishingAppsEvent()
    if (this.publishPhyApps.length > 0) {
      this.handler = 'PROGRESS'
    }
  },
  beforeDestroy() {
    clearInterval(this.interval)
  }
}
</script>
