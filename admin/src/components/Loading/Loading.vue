<template>
    <transition name="fade" v-if="show">
        <div class="loading">
            <img :src="imageSrc" alt="加载中" class="img">
        </div>
    </transition>
</template>

<script>
    export default {
        name: "Loading",
        props: {
            imageSrc: {
                type: String,
                default() {
                    return require('./images/loading.gif');
                }
            }
        },
        data() {
            return {
                show: true
            }
        }
    }
</script>

<style scoped>
    .fade-enter, .fade-leave-to {
        opacity: 0
    }

    .fade-enter-active, .fade-leave-active {
        transition: opacity 0.3s
    }

    .fade-enter-to, .fade-leave {
        opacity: 1
    }

    .loading {
        position: fixed;
        z-index: 2000;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background-color: rgba(0, 0, 0, 0.3);
        text-align: center;
        line-height: 100vh;
    }
</style>