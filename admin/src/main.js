import Vue from 'vue'
import App from './App.vue'
import router from './router'
import filter from 'common/utils/filter'

Vue.config.productionTip = false

// 全局过滤器
Object.keys(filter).forEach(key => {
    Vue.filter(key, filter[key])
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
