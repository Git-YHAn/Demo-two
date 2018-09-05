<template>
<div class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-md" :class="{'page-sidebar-closed':!isShowSidebar, 'page-full-width': !hasSidebar}" @click="hideDropdowns">
  <div class="page-wrapper">
    <!-- BEGIN HEADER -->
    <div class="page-header navbar navbar-fixed-top">
      <!-- BEGIN HEADER INNER -->
      <div class="page-header-inner ">
        <!-- BEGIN LOGO -->
        <div class="page-logo">
          <nuxt-link to="/"><img src="../assets/img/logo.png" alt="logo" class="logo-default" /> </nuxt-link>
          <div class="menu-toggler sidebar-toggler" v-show="hasSidebar" @click="toggeleSidebar">
            <span></span>
          </div>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN RESPONSIVE MENU TOGGLER -->
        <a href="javascript:;" class="menu-toggler responsive-toggler" v-show="hasSidebar" @click="toggleMobileSidebar">
          <span></span>
        </a>
        <!-- END RESPONSIVE MENU TOGGLER -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="hor-menu hidden-sm hidden-xs">
          <ul class="nav navbar-nav">
            <!-- DOC: Remove data-hover="megamenu-dropdown" and data-close-others="true" attributes below to disable the horizontal opening on mouse hover -->
            <li class="classic-menu-dropdown" aria-haspopup="true">
              <a href="javascript:;" class="megamenu-dropdown"> 项目 <i v-if="projects&&projects.length>0" class="fa fa-angle-down"></i> </a>
              <ul ref="menu-dropdown-project" v-if="projects&&projects.length>0" class="dropdown-menu pull-left dropdown-header-menu">
                <li v-for="item in projects">
                  <nuxt-link tag="a" :to="'/'+item.proCode"> {{item.proName}} </nuxt-link>
                </li>
              </ul>
            </li>
            <li class="classic-menu-dropdown" aria-haspopup="true">
              <nuxt-link tag="a" class="megamenu-dropdown" to="/asset"> 资源 </nuxt-link>
            </li>
          </ul>
        </div>
        <div class="top-menu">
          <ul class="nav navbar-nav pull-right">
            <li class="dropdown dropdown-extended dropdown-inbox" :class="{'open':showLayoutMessage}">
              <a href="javascript:;" @click.stop="showLayoutMessage=!showLayoutMessage" class="dropdown-toggle">
                <i class="icon-docs"></i>
                <!--<span class="badge badge-default">1</span>-->
              </a>
              <ul class="dropdown-menu">
                <li class="dropdown-header">
                  <h3>《平台操作手册》</h3>
                  <p>帮助您更快捷了解平台的操作使用！</p>
                  <div style="text-align: right; margin-right: 10px;">
                    <el-button type="text" size="medium" @click="downLoad">下载</el-button>
                  </div>
                </li>
              </ul>
            </li>
            <!-- BEGIN NOTIFICATION DROPDOWN -->
            <!-- DOC: Apply "dropdown-dark" class after "dropdown-extended" to change the dropdown styte -->
            <!-- DOC: Apply "dropdown-hoverable" class after below "dropdown" and remove data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to enable hover dropdown mode -->
            <!-- DOC: Remove "dropdown-hoverable" and add data-toggle="dropdown" data-hover="dropdown" data-close-others="true" attributes to the below A element with dropdown-toggle class -->
            <li class="dropdown dropdown-extended dropdown-notification" :class="{'open':showLayoutNotification}">
              <a href="javascript:;" @click.stop="showLayoutNotification=!showLayoutNotification" class="dropdown-toggle"><i class="icon-bell"></i><span class="badge badge-default"> {{notifications.length}} </span></a>
              <ul class="dropdown-menu">
                <li class="external">
                  <h3><span class="bold">{{notifications.length}} pending</span> notifications</h3>
                </li>
                <li>
                  <ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
                    <li v-for="item in notifications">
                      <a href="javascript:;">
                        <span class="time">just now</span>
                        <span class="details">
                          <span class="label label-sm label-icon label-success">
                            <i class="fa fa-plus"></i>
                          </span> New user registered. </span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <!-- END NOTIFICATION DROPDOWN -->
            <!-- BEGIN INBOX DROPDOWN -->
            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
            <!--<li class="dropdown dropdown-extended dropdown-inbox" :class="{'open':showLayoutMessage}">
              <a href="javascript:;" @click.stop="showLayoutMessage=!showLayoutMessage" class="dropdown-toggle">
                              <i class="icon-envelope-open"></i>
                              <span class="badge badge-default"> {{messages.length}} </span>
                          </a>
              <ul class="dropdown-menu">
                <li class="external">
                  <h3>You have <span class="bold">{{messages.length}} New</span> Messages</h3> </li>
                <li>
                  <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                    <li v-for="item in messages">
                      <a href="#">
                        <span class="photo">
                          <img src="/layouts/layout3/img/avatar2.jpg" class="img-circle" alt=""> </span>
                        <span class="subject">
                          <span class="from"> Lisa Wong </span>
                          <span class="time">Just Now </span>
                        </span>
                        <span class="message"> Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>-->
            <!-- END INBOX DROPDOWN -->
            <!-- BEGIN TODO DROPDOWN -->
            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
            <li class="dropdown dropdown-extended dropdown-tasks" :class="{'open':showLayoutTask}">
              <a href="javascript:;" @click.stop="showLayoutTask=!showLayoutTask" class="dropdown-toggle"><i class="icon-calendar"></i><span class="badge badge-default"> {{tasks.length}} </span></a>
              <ul class="dropdown-menu extended tasks">
                <li class="external">
                  <h3>You have <span class="bold">{{tasks.length}} pending</span> tasks</h3>
                </li>
                <li>
                  <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                    <li v-for="item in tasks">
                      <a href="javascript:;">
                        <span class="task">
                          <span class="desc">New release v1.2 </span>
                          <span class="percent">30%</span>
                        </span>
                        <span class="progress">
                          <span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
                            <span class="sr-only">40% Complete</span>
                          </span>
                        </span>
                      </a>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <!-- END TODO DROPDOWN -->
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
            <li class="dropdown dropdown-user" :class="{'open':showLayoutUserMenu}">
              <a href="javascript:;" @click.stop="showLayoutUserMenu=!showLayoutUserMenu" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                <!-- <img alt="" class="img-circle" src="/layouts/layout/img/avatar3_small.jpg" /> -->
                <span class="username username-hide-on-mobile"> {{userInfo.username}} </span>
                <i class="fa fa-angle-down"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-default">
                <li>
                  <!--<nuxt-link to=""> <i class="icon-user"></i>  </nuxt-link>-->
                  <a @click="showPersonalCenter">
                    <i class="icon-user"></i>
                    个人中心
                  </a>
                </li>
                <!-- <li><a href="app_calendar.html"> <i class="icon-calendar"></i> My Calendar </a></li>
                <li><a href="app_inbox.html"> <i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger"> 3 </span> </a></li>
                <li><a href="app_todo.html"> <i class="icon-rocket"></i> My Tasks <span class="badge badge-success"> 7 </span> </a></li> -->
                <li class="divider"> </li>
                <li>
                  <a @click="logout">
                    <i class="icon-key"></i>
                    退出系统
                  </a>
                </li>
              </ul>

            </li>
            <!-- END USER LOGIN DROPDOWN -->
            <!-- BEGIN QUICK SIDEBAR TOGGLER -->
            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
            <li class="dropdown" :class="{'open':showLayoutSettingMenu}">
              <a href="javascript:;" @click.stop="showLayoutSettingMenu=!showLayoutSettingMenu" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                <i class="icon-settings"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-default">
                <li>
                  <nuxt-link to="/system/project"> <i class="icon-list"></i> 项目管理 </nuxt-link>
                </li>
                <li>
                  <nuxt-link to="/system/environment"> <i class="icon-compass"></i> 环境管理 </nuxt-link>
                </li>
                <li>
                  <nuxt-link to="/system/user/manager"> <i class="icon-user"></i> 用户管理 </nuxt-link>
                </li>
                <li>
                  <nuxt-link to="/system/setting"> <i class="icon-settings"></i> 系统设置 </nuxt-link>
                </li>
                <li>
                  <nuxt-link to="/system/log"> <i class="icon-doc"></i> 日志管理 </nuxt-link>
                </li>
              </ul>
            </li>
            <!-- END QUICK SIDEBAR TOGGLER -->
          </ul>
        </div>
        <!-- END TOP NAVIGATION MENU -->
      </div>
      <!-- END HEADER INNER -->
    </div>
    <!-- END HEADER -->

    <!-- BEGIN HEADER & CONTENT DIVIDER -->
    <div class="clearfix"> </div>
    <!-- END HEADER & CONTENT DIVIDER -->
    <!-- BEGIN CONTAINER -->
    <nuxt></nuxt>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
    <div class="page-footer">
      <div class="page-footer-inner">
        2016 &copy; Metronic Theme By
        <a target="_blank" href="http://keenthemes.com">Keenthemes</a> &nbsp;|&nbsp;
        <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
      </div>
      <div class="scroll-to-top">
        <i class="icon-arrow-up"></i>
      </div>
    </div>
    <!-- END FOOTER -->
  </div>
  <!-- BEGIN QUICK NAV -->
  <!-- END QUICK NAV -->
  <div v-if="personalCenterVisible">
    <personal-center-dialog @hidden="hiddenPersonalCenter" style="display: block"></personal-center-dialog>
  </div>
</div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'
import personalCenterDialog from '../pages/personCenter'
export default {
  components: {
    personalCenterDialog
  },
  data() {
    return {
      notifications: [],
      messages: [],
      tasks: [],
      showLayoutNotification: false,
      showLayoutMessage: false,
      showLayoutTask: false,
      showLayoutUserMenu: false,
      showLayoutSettingMenu: false,
      personalCenterVisible: false
    }
  },
  computed: {
    ...mapState(['hasSidebar', 'isShowSidebar', 'isShowMobileSidebar', 'userInfo']),
    ...mapState('project', ['projects'])
  },
  watch: {},
  methods: {
    ...mapActions('action', ['searchProjects']),
    ...mapMutations('project', ['SET_PROJECTS']),
    toggeleSidebar() {
      this.$store.commit('TOGGLE_SIDEBAR')
    },
    toggleMobileSidebar() {
      this.$store.commit('TOGGLE_MOBILE_SIDEBAR')
    },
    hideDropdowns() {
      this.showLayoutNotification = false
      this.showLayoutMessage = false
      this.showLayoutTask = false
      this.showLayoutUserMenu = false
      this.showLayoutSettingMenu = false
    },
    logout() {
      console.log('logout')
      this.$store.dispatch('logout')
      this.$router.push('/login')
    },
    hiddenPersonalCenter() {
      this.personalCenterVisible = false
    },
    showPersonalCenter() {
      this.personalCenterVisible = true
    },
    async downLoad() {
      await this.$axios.get(`/download/procedures`, {responseType: 'arraybuffer'}).then(res => {
        let blob = new Blob([res.data], {type: 'application/msword'})
        let objectUrl = URL.createObjectURL(blob)
        let link = document.createElement('a')
        // 需要添加到body尾部，否则部分浏览器无法下载，如firefox
        document.body.appendChild(link)
        link.href = objectUrl
        link.setAttribute('download', '用户操作手册.doc')
        link.click()
        // 释放一个之前通过调用 URL.createObjectURL() 创建的已经存在的 URL 对象
        window.URL.revokeObjectURL(objectUrl)
      }).catch(err => {
        console.error('用户手册下载失败: ', err)
      })
    }
  },
  async created() {
    let response = await this.searchProjects({ pageNum: 1, pageSize: 999 })
    if (~~response.code === 1 && response.data.results.length > 0) {
      this.SET_PROJECTS(response.data.results)
    }
  }
}
</script>
