import Vue from 'vue'
import io from 'socket.io-client'
Vue.prototype.$io = Object.assign({}, io)
Vue.prototype.$io.connect = function (url, token, params) {
  let socket = io.connect(url, {
    transports: ['websocket'],
    query: Object.assign({
      token: token
    }, params)
  })
  socket.on('reconnect_attempt', () => {
    socket.io.opts.query = Object.assign({
      token: token
    }, params)
  })
  return socket
}
