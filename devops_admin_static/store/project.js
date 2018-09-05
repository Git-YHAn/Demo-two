export const state = () => ({
  currentProjectCode: null,
  currentEnvironmentCode: null,
  environments: [],
  projects: [],
  orderInfo: {}
})

export const getters = {
  currentProject: function (state) {
    return state.projects.filter(item => item.proCode === state.currentProjectCode)[0] || {}
  },
  currentEnvironment: function (state) {
    return state.environments.filter(item => item.envCode === state.currentEnvironmentCode)[0] || {}
  }
}

export const actions = {

}

export const mutations = {
  SET_PROJECTS(state, projects) {
    state.projects = projects
  },
  SET_CURR_PROJECT(state, proCode) {
    state.currentProjectCode = proCode
  },
  SET_ENVS(state, environments) {
    state.environments = environments
  },
  SET_CURR_ENV(state, envCode) {
    state.currentEnvironmentCode = envCode
  },
  SET_ORDER_INFO(state, orderInfo) {
    state.orderInfo = orderInfo
  }
}
