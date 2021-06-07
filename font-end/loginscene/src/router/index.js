import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '../views/LoginPage';
import SignUpPage from '../views/SignUpPage';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/SignUpPage',
      name: 'SignUpPage',
      component: SignUpPage
    }
  ]
})
