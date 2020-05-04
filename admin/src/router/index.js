import Vue from 'vue'
import Router from 'vue-router'
import MaskLoading from 'common/utils/LoadingMask'

Vue.use(Router)

const router = new Router({
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
            path: '/',
            component: () => import('views/Admin/Index'),
            children: [
                {
                    path: 'welcome',
                    meta: {
                        sidebarId: 'welcome-sidebar'
                    },
                    component: () => import('views/Admin/Welcome/Welcome')
                },
                {
                    path: 'business/chapter',
                    meta: {
                        sidebarId: 'business-chapter-sidebar'
                    },
                    component: () => import('views/Admin/Chapter/Chapter')
                },
                {
                    path: 'business/section',
                    meta: {
                        sidebarId: 'business-section-sidebar'
                    },
                    component: () => import('views/Admin/Section/Section')
                },
                {
                    path: 'business/course',
                    meta: {
                        sidebarId: 'business-course-sidebar'
                    },
                    component: () => import('views/Admin/Course/Course')
                },
            ]
        }
    ]
})

router.beforeEach((to, from, next) => {
    MaskLoading.start()
    next()
})

router.afterEach((to, from) => {
    MaskLoading.complete()
})

export default router