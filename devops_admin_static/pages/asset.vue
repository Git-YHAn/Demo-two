<template>
<div class="page-container">
  <!-- BEGIN SIDEBAR -->
  <div class="page-sidebar-wrapper">
    <!-- BEGIN SIDEBAR -->
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse" :class="{'in':isShowMobileSidebar}">
      <!-- BEGIN SIDEBAR MENU -->
      <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
      <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
      <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
      <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
      <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
      <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
      <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-light" :class="{'page-sidebar-menu-closed':!isShowSidebar}" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
        <!-- <li class="sidebar-search-wrapper"> -->
        <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
        <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
        <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
        <!-- <searchform></searchform> -->
        <!-- END RESPONSIVE QUICK SEARCH FORM -->
        <!-- </li> -->
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/server')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/server'">
            <i class="icon-bulb"></i>
            <span class="title">服务器管理</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="heading">
          <h3 class="uppercase">金山云</h3>
        </li>
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/ksyun/kec')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/ksyun/kec'">
            <i class="icon-bulb"></i>
            <span class="title">云服务器管理</span>
            <span class="selected"></span>
          </router-link>
        </li>
      </ul>
      <!-- END SIDEBAR MENU -->
      <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
  </div>
  <!-- END SIDEBAR -->
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <nuxt-child />
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
  <!-- BEGIN QUICK SIDEBAR -->
  <a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-login"></i></a>
  <!-- END QUICK SIDEBAR -->
</div>
</template>
<script type='text/javascript'>
import { mapState, mapMutations } from 'vuex'
export default {
  validate({ params }) {
    return true
  },
  components: {},
  data() {
    return {}
  },
  computed: {
    ...mapState(['isShowSidebar', 'isShowMobileSidebar']),
    basePath: function () {
      return `/asset`
    }
  },
  methods: {
    ...mapMutations(['SHOW_SIDEBAR'])
  },
  async created() {
    this.SHOW_SIDEBAR()
  },
  destroyed() {}
}
</script>
<style scoped>

</style>
