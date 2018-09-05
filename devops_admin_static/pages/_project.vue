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
        <li class="nav-item start sidebar-title">
          <a href="javascript:;">
            <h5 class="sidebar-title-content">
              <span>{{currentProject.proName}}</span>
            </h5>
            <form class="title form-horizontal" role="form">
              <div class="form-group form-md-line-input">
                <!-- <select class="form-control" v-model="envCode" @change="currentEnvChangeEvent">
                    <option v-for="item in environments" :key="item.envCode" :value="item.envCode">{{item.envName}}</option>
                </select> -->
                <el-select class="form-control" popper-class="top-popper" v-model="envCode" placeholder="请选择环境" @change="currentEnvChangeEvent">
                  <el-option v-for="item in environments" :key="item.envCode" :label="item.envName" :value="item.envCode">
                  </el-option>
                </el-select>
              </div>
            </form>
          </a>
        </li>
        <!-- <li class="nav-item start">

        </li> -->
        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
        <!-- <li class="sidebar-search-wrapper"> -->
        <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
        <!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
        <!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
        <!-- <searchform></searchform> -->
        <!-- END RESPONSIVE QUICK SEARCH FORM -->
        <!-- </li> -->
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/instance')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath">
            <i class="icon-bulb"></i>
            <span class="title">Dashboard</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="heading">
          <h3 class="uppercase">应用管理</h3>
        </li>
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/assemble/manage')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/assemble/manage'">
            <i class="icon-bulb"></i>
            <span class="title">应用列表</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>

        <!--新增应用审核和应用审核记录-->
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/assemble/audit')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/assemble/audit'">
            <i class="icon-bulb"></i>
            <span class="title">应用审核</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start " :class="{'open active':$route.path.indexOf('/assemble/record')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/assemble/record'">
            <i class="icon-bulb"></i>
            <span class="title">审核历史</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>


        <li class="heading">
          <h3 class="uppercase">配置管理</h3>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/config/select')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/config/select'">
            <i class="icon-bulb"></i>
            <span class="title">查看配置</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/config/edit')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/config/edit'">
            <i class="icon-bulb"></i>
            <span class="title">修改配置</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/config/audit')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/config/audit'">
            <i class="icon-bulb"></i>
            <span class="title">审核配置</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/config/record')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/config/record'">
            <i class="icon-bulb"></i>
            <span class="title">审核历史</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>

        <li class="heading">
          <h3 class="uppercase">版本管理</h3>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/version/application')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/version/application'">
            <i class="icon-bulb"></i>
            <span class="title">应用版本</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/version/configure')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/version/configure'">
            <i class="icon-bulb"></i>
            <span class="title">配置版本</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/version/release')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/version/release'">
            <i class="icon-bulb"></i>
            <span class="title">发布版本</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>

        <li class="heading">
          <h3 class="uppercase">发布管理</h3>
        </li>
        <ul class="arial">
          <h3 class="uppercase" style="color:#A9B0B7;">常规</h3>
        </ul>
        <li class="arial" :class="{'open active':$route.path.indexOf('/order')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/order'">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <i class="icon-bulb"></i>
            <span class="title">发布工单</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <ul class="arial">
          <h3 class="uppercase" style="color:#A9B0B7;">容器</h3>
        </ul>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/release/container')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/release/container'">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <i class="icon-bulb"></i>
            <span class="title">应用发布</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
          </router-link>
        </li>
        <li class="nav-item start" :class="{'open active':$route.path.indexOf('/release/history')>-1}">
          <router-link tag="a" class="nav-link" :to="basePath+'/release/history'">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <i class="icon-bulb"></i>
            <span class="title">发布历史</span>
            <span class="selected"></span>
            <!-- <span class="badge badge-success">1</span> -->
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
      <nuxt-child v-if="currentProject.proId && currentEnvironment.envId" />
    </div>
    <div class="page-footer"></div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
  <!-- BEGIN QUICK SIDEBAR -->
  <a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-login"></i></a>
  <!-- END QUICK SIDEBAR -->
</div>
</template>
<script type='text/javascript'>
import { mapState, mapMutations, mapGetters, mapActions } from 'vuex'
import Searchform from '@/components/layout/searchform.vue'
export default {
  validate({ params }) {
    return true
  },
  components: {
    Searchform
  },
  data() {
    return {
      isShowEnv: false,
      envCode: {},
      projectMenus: [

      ]
    }
  },
  computed: {
    ...mapState(['isShowSidebar', 'isShowMobileSidebar']),
    ...mapState('project', ['environments']),
    ...mapGetters('project', ['currentProject', 'currentEnvironment']),
    proCode: function () {
      return this.$route.params.project
    },
    basePath: function () {
      return `/${this.proCode}`
    }
  },
  methods: {
    ...mapMutations(['SHOW_SIDEBAR']),
    ...mapMutations('project', ['SET_CURR_PROJECT', 'SET_ENVS', 'SET_CURR_ENV']),
    ...mapActions('action', ['searchEnvs']),
    currentEnvChangeEvent() {
      this.SET_CURR_ENV(this.envCode)
      this.$router.push(this.basePath)
    }
  },
  async created() {
    this.SHOW_SIDEBAR()
    this.SET_CURR_PROJECT(this.proCode)
    let response = await this.searchEnvs()
    if (~~response.code === 1) {
      let envs = response.data
      if (typeof envs === 'object') {
        let length = envs.length
        envs = envs.sort((a, b) => {
          return a.priority < b.priority
        })
        this.SET_ENVS(envs)
        if (!this.currentEnvironment.envId) {
          this.SET_CURR_ENV(length > 0 && envs[length - 1].envCode)
        } else {
          this.envCode = this.currentEnvironment.envCode
        }
      }
    }
    let params = window.location.search.replace('?', '').split('&')
    params.forEach((item) => {
      let kv = item.split('=')
      if (kv[0] === 'env_code') {
        this.envCode = kv[1]
        this.SET_CURR_ENV(this.envCode)
      }
    })
  },
  destroyed() {
    this.SET_CURR_ENV(null)
  }
}
</script>
<style scoped>
  .page-sidebar .page-sidebar-menu>ul.arial>h3, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>ul.arial>h3 {
    margin: 0;
    padding: 6px;
    font-size: 14px;
    font-weight: 600;
  }
</style>
