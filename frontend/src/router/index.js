import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import UserList from '@/components/user/Userlist'
import Mypage from '@/components/user/Mypage'
import BootstrapVue from 'bootstrap-vue'

Vue.use(Router)
Vue.use(BootstrapVue)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', name: 'home', component: Home},
    {path: '/userlist', name: 'userlist', component: UserList},
    {path: '/mypage', name: 'mypage', component: Mypage}
  ]
})
