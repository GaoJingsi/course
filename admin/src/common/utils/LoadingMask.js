import Loading from 'components/Loading/Loading.vue';
import Vue from 'vue';


const LoadingMask = Vue.extend(Loading);
let loadingMask = new LoadingMask({
    el: document.createElement('div'),
    data() {
        return {
            show: true
        }
    }
})
// const loadingMask = new LoadingMask()

const MaskLoading = {

    start() {
        loadingMask = new LoadingMask({
            el: document.createElement('div'),
            data() {
                return {
                    show: true
                }
            }
        })
        loadingMask.show = true
        // loadingMask.$mount('#loading')
        document.body.appendChild(loadingMask.$el)
    },

    complete() {
        loadingMask.show = false
    }

}

export default MaskLoading