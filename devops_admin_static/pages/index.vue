<template>
  <div class="page-container">
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
      <!-- BEGIN CONTENT BODY -->
      <div class="page-content">
        <dash-board-home></dash-board-home>
        <div style="position: absolute; top: 20px; left: 20px;"></div>
      </div>
      <!-- END CONTENT BODY -->
    </div>
    <!-- END CONTENT -->
  </div>
</template>
<script type='text/javascript'>
  import DashBoardHome from './dashboard/home/dashboard-home'

  export default {
    components: {
      DashBoardHome
    },
    data() {
      return {}
    },
    computed: {},
    methods: {},
    mounted() {
      this.$store.commit('HIDE_SIDEBAR')
    },
    destroyed() {}
  }
</script>
