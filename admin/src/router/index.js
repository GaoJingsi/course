import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '*',
            redirect: '/login'
        },
        {
            path: '/login',
            component: () => import('views/Login/Index')
        },
        {
            path: '/admin',
            component: () => import('views/Admin/Index'),
            children:[
                {
                    path: 'welcome',
                    component: () => import('views/Admin/Welcome/Index')
                }
            ]
        }
    ]
})