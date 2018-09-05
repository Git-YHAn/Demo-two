<template lang="html">
  <form class="sidebar-search" :class="{'open':!isShowSidebar}" action="page_general_search_3.html" method="POST">
    <a href="javascript:;" class="remove"> <i class="icon-close"></i></a>
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search...">
      <span class="input-group-btn"> <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a> </span>
    </div>
  </form>
</template>

<script>
export default {
  props: {
    isShowSidebar: Boolean
  }
}
</script>

<style lang="css">
</style>
