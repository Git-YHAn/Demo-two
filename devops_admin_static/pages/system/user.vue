<template>
<el-container style="height:calc(100vh - 300px)">
  <el-aside width="200px">
    <el-menu default-active="0" style="height:100%;">
      <el-menu-item v-for="(item,idx) in userMenus" :key="idx" :index="idx+''" @click="$router.push(item.link)">
        <span slot="title">{{item.name}}</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
  <el-main>
    <nuxt-child/>
  </el-main>
</el-container>
</template>
<script type='text/javascript'>
export default {
  data() {
    return {
      userMenus: [
        { name: '用户管理', link: '/system/user/manager' },
        { name: '角色管理', link: '/system/user/role' }
      ]
    }
  },
  computed: {},
  methods: {},
  async created() {},
  destroyed() {}
}
</script>
<style scoped>

</style>
