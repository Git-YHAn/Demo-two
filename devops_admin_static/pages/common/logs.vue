<template>
<section>
  <div v-for="(o,i) in rows" class="row" :key="i" style="margin-top:20px">
    <div v-for="(item,idx) in o" :key="idx" :class="o.length===1?'col-xs-12':'col-xs-6'" >
      <log-item :appId="appId" :serverId="item" :len="serverIds.length"></log-item>
    </div>
  </div>
</section>
</template>
<script type='text/javascript'>
import logItem from './component/singlelog'
export default {
  layout: 'blank',
  components: {
    logItem
  },
  data() {
    return {}
  },
  computed: {
    rows: function () {
      let arr = []
      this.serverIds.forEach((item, idx) => {
        let arrIdx = parseInt(idx / 2)
        if (!arr[arrIdx]) {
          arr[arrIdx] = []
        }
        arr[arrIdx].push(item)
      })
      return arr
    },
    appId: function () {
      return this.$route.query.app_id
    },
    serverIds: function () {
      return this.$route.query.server_ids.split(',')
    }
  },
  methods: {},
  mounted() {

  },
  destroyed() {}
}
</script>
