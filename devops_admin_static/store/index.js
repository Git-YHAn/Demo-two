export const state = () => ({
  token: null,
  userInfo: {},
  resources: [],
  baseRoute: null,
  authList: [],
  hasSidebar: false,
  isShowSidebar: true,
  isShowMobileSidebar: false
})

export const getters = {
  persistedstate: () => {
    return ['token', 'authList', 'userInfo', 'project.currentEnvironmentCode']
  },
  authUrls: state => {
    return state.authList.map((item) => {
      return item.url
    })
  }
}

export const actions = {
  nuxtServerInit({ commit }, { req }) {},
  async login({ commit }, { username, password, token, rember }) {
    try {
      let login = await this.$axios.$post('/login/dologin', { username, 'pwd': password, token, 'rember': rember })
      if (~~login.code === 1) {
        commit('SET_TOKEN', login.data)
        let userInfo = await this.$axios.$get('/user/info')
        if (~~userInfo.code === 1) {
          commit('SET_USER_INFO', userInfo.data)
        }
      } else {
        throw new Error(login.msg)
      }
    } catch (error) {
      commit('SET_TOKEN', null)
      if (error.response && error.response.status === 401) {
        throw new Error('Bad credentials')
      }
      throw error
    }
  },
  async initResource({ commit }) {
    let res = await this.$axios.$get('sys/resource/get/resource')
    if (res.code === '1') {
      commit('SET_RESOURCE', res.data)
      commit('SET_AUTH_LIST', this.$router.currentRoute.path)
    }
  },
  async logout({ commit }) {
    await this.$axios.post('/login/logout')
    commit('SET_TOKEN', null)
  }
}

export const mutations = {
  TOGGLE_SIDEBAR(state) {
    state.isShowSidebar = !state.isShowSidebar
  },
  TOGGLE_MOBILE_SIDEBAR(state) {
    state.isShowMobileSidebar = !state.isShowMobileSidebar
  },
  SHOW_SIDEBAR(state) {
    state.hasSidebar = true
  },
  HIDE_SIDEBAR(state) {
    state.hasSidebar = false
  },
  SET_TOKEN(state, token) {
    state.token = token
  },
  SET_USER_INFO(state, userInfo) {
    state.userInfo = userInfo
  },
  SELECT_MENU(state, route) {
    state.baseRoute = route
  },
  SET_RESOURCE(state, resource) {
    state.resources = resource
  },
  SET_AUTH_LIST(state, path) {
    if (path !== '/') {
      state.authList = findResource(state.resources, path)
    }
  },
  OPEN_MENU(state, menu) {
    state.openedMenu.push(menu)
  },
  CLOSE_MENU(state, menu) {
    state.openedMenu.splice(state.openedMenu.indexOf(menu), 1)
  }
}

let routerSuffixes = ['', '/', 'index.html', '/index.html']

function findResource(res, path) {
  for (let i = 0; i < res.length; i++) {
    let resUrl = res[i].url && res[i].url.split('?')[0]
    if (resUrl && path.indexOf(resUrl) > -1 && routerSuffixes.indexOf(path.replace(resUrl, '')) > -1) {
      return res[i].children
    } else {
      if (res[i].children.length > 0) {
        let result = findResource(res[i].children, path)
        if (result.length > 0) {
          return result
        }
      }
    }
  }
  return []
}
