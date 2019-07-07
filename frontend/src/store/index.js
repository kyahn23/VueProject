import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    uno: '',
    emailId: '',
    password: '',
    photo: '',
    userName: ''
  },
  getters: {

  },
  mutations: {
    LOGIN (state, {emailId, password}) {
      state.emailId = emailId
      state.password = password
    },
    LOGOUT (state) {
      state.emailId = '',
      state.password = ''
    }
  },
  actions: {
    LOGIN ({commit}, {emailId, password}) {
      commit('LOGIN', {emailId, password})
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    }
  }
})
