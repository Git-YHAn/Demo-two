<template>
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content pdt5">
      <nuxt-child />
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
</template>
<script type='text/javascript'>
export default {
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
<style scoped>

</style>
