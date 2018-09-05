import Vue from 'vue'
import ElementUI from 'element-ui'

Vue.use(ElementUI, { size: 'mini' })

Vue.prototype.confirm = function (msg, type) {
  return this.$confirm(msg, '提示', { confirmButtonText: '确定', cancelButtonText: '取消', type: type | 'info' })
}
const defaultOffset = 60
const defaultDuration = 2000
Vue.prototype.$msg = (() => {
  let self = Vue.prototype
  return {
    success: (...msg) => {
      self.$notify({
        type: 'success',
        message: msg.join(''),
        offset: defaultOffset,
        duration: defaultDuration
      })
    },
    warning: (...msg) => {
      self.$notify({
        type: 'warning',
        message: msg.join(''),
        offset: defaultOffset,
        duration: defaultDuration
      })
    },
    info: (...msg) => {
      self.$notify({
        type: 'info',
        message: msg.join(''),
        offset: defaultOffset,
        duration: defaultDuration
      })
    },
    error: (...msg) => {
      self.$notify({
        type: 'error',
        message: msg.join(''),
        offset: defaultOffset,
        duration: defaultDuration
      })
    }
  }
})()
