import Loading from 'components/Loading/Loading.vue';
import Vue from 'vue';


const LoadingMask = Vue.extend(Loading);
// const loadingMask = new LoadingMask({
//     el: document.createElement('div'),
//     data() {
//         return {
//             show: true
//         }
//     }
// })
const loadingMask = new LoadingMask()

const MaskLoading = {

    start() {
        loadingMask.show = true
        loadingMask.$mount('#loading')
    },

    complete() {
        loadingMask.show = false
    }

}

export default MaskLoading