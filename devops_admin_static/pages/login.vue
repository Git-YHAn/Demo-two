<template lang="html">
  <div class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="index.html">
                <img src="../assets/img/logo-big.png" alt=""> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <el-form ref='form' :model='form' :rules='rule' class="login-form" onsubmit="return false;" @keyup.enter.native='handleSubmit'>
                <h3 class="form-title font-green">Sign In</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> Enter any username and password. </span>
                </div>
              <el-form-item prop="username">
                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                <label class="control-label visible-ie8 visible-ie9">Username</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" v-model='form.username' placeholder="Username" name="username"/>
              </el-form-item>
              <el-form-item prop="password">
                <label class="control-label visible-ie8 visible-ie9">Password</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" v-model='form.password' placeholder="Password"
                       name="password"/>
              </el-form-item>

                <div class="form-actions">
                    <button class="btn green uppercase" @click.stop.prevent='handleSubmit' :disabled="loading">Login</button>
                    <label class="rememberme check mt-checkbox mt-checkbox-outline">
                      <input type="checkbox" v-model='checked' name="remember">Remember
                      <span></span>
                    </label>
                </div>
                <div class="login-options">
                    <el-alert v-if='error' :title='error' type='error' style='margin-top: -10px; margin-bottom: 10px' show-icon></el-alert>
                </div>
            </el-form>
            <!-- END LOGIN FORM -->
        </div>
        <div class="copyright"> 2014 © Metronic. Admin Dashboard Template. </div>
</div>
</template>

<script>
export default {
  layout: 'blank',
  data() {
    return {
      loading: false,
      checked: false,
      error: '',
      form: {
        username: '',
        password: ''
      },
      rule: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    getCookie() {
      if (document.cookie && document.cookie.length > 0) {
        var aCookie = document.cookie.split(';')
        for (var i = 0; i < aCookie.length; i++) {
          var aCrumb = aCookie[i].split('=')
          if (aCrumb[0].indexOf('name') > -1) {
            this.form.username = aCrumb[1]
          } else if (aCrumb[0].indexOf('psw') > -1) {
            this.form.password = aCrumb[1]
          }
        }
      }
    },
    handleSubmit() {
      this.error = ''
      this.$refs['form'].validate(pass => {
        if (pass) {
          this.loading = true
          this.form.rember = this.checked
          this.$store.dispatch('login', this.form).then(() => {
            this.$router.push({ path: '/' })
          }).catch((error) => {
            this.error = error.message
          }).finally(() => {
            this.loading = false
          })
        }
      })
    }
  },
  mounted() {
    this.getCookie()
  },
  watch: {
    form: {
      handler: function () {
        this.error = ''
      },
      deep: true
    }
  }
}
</script>

<style lang="css">
</style>
