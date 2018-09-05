<template>
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content pdt5">
      <h3>管理</h3>
      <div class="tabbable-line">
        <ul class="nav nav-tabs ">
          <li v-for="item in menus" :class="{'active':$route.path.indexOf(item.link)>-1}">
            <nuxt-link tag="a" :to="item.link"> {{item.name}} </nuxt-link>
          </li>
        </ul>
        <div class="tab-content">
          <nuxt-child />
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
</template>
<script type='text/javascript'>
export default {
  data() {
    return {
      menus: [
        { name: '项目', link: '/system/project' },
        { name: '环境', link: '/system/environment' },
        { name: '用户', link: '/system/user/manager' },
        { name: '设置', link: '/system/setting' },
        { name: '日志', link: '/system/log' }
      ]
    }
  },
  computed: {},
  methods: {},
  mounted() {
    this.$store.commit('HIDE_SIDEBAR')
  },
  destroyed() {}
}
</script>
<style scoped>

</style>
