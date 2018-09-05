import Vue from 'vue'
Vue.directive('auth', function (el, binding, vnode) {
  // let store = vnode.context.$store
  // if (store && store.state && store.state.authList) {
  //   let authUrls = store.getters.authUrls
  //   if (authUrls === 'undefined') {
  //     authUrls = store.state.authList.map((item) => {
  //       return item.url
  //     })
  //   }
  //   if (authUrls.indexOf(binding.value) > -1) {
  //     el.style.display = ''
  //   } else {
  //     el.style.display = 'none'
  //   }
  // }
})

Vue.directive('nosubmit', function (el, binding, vnode) {
  let input = document.createElement('input')
  input.setAttribute('type', 'text')
  input.style = 'display:none;'
  el.append(input)
})

Vue.filter('ksyunDateFormatter', function (value) {
  return value.replace('T', ' ').replace('Z', '')
})

Vue.filter('sizeTransfer', function (value) {
  let result = value + 'byte'
  let units = ['byte', 'K', 'M', 'G', 'T']
  for (let i = units.length; i >= 0; i--) {
    if (value >= Math.pow(1024, i)) {
      result = Math.round(value * 100 / Math.pow(1024, i)) / 100 + units[i]
      return result
    }
  }
  return result
})
