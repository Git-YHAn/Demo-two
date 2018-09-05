<template>
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <transition name="page">
        <section class="container" v-show="error">
          <img src="../assets/images/404.png" />
          <h1 class="title">
            {{ error.statusCode }}
          </h1>
          <h2 class="info">
            {{ error.message }}
          </h2>
          <nuxt-link class="button" to="/" v-if="error.statusCode === 404"> Homepage </nuxt-link>
        </section>
      </transition>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
</template>

<script>
export default {
  props: ['error'],
  mounted() {}
}
</script>

<style scoped>
img {
  width: 300px;
  align-self: center;
}

.container {
  min-height: calc(100vh - 500px);
  background-color: #fff;
  padding: 50px 10px;
  padding-top: 120px;
  text-align: center;
}

.title {
  font-weight: 300;
  letter-spacing: 1px;
  margin: 20px 0;
  color: #35495e;
  font-size: 128px;
  line-height: 100px;
}

.info {
  letter-spacing: 1px;
  line-height: normal;
  margin: 20px 0;
  color: #35495e;
}

.button {
  width: auto;
  position: relative;
  display: inline-block;
  align-self: center;
  color: #fff !important;
  font-size: 16px;
  font-weight: 600;
  padding: 14px 42px;
  padding-top: 13px;
  text-align: center;
  text-transform: uppercase;
  text-decoration: none;
  background-color: #3b8070;
  border-radius: 4px;
  letter-spacing: 1px;
  border: 1px solid #3b8070;
}
</style>
