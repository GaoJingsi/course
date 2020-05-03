<template>
    <div class="loading">
        <img :src="imageSrc" alt="加载中" class="img">
    </div>
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
        }
    }
</script>

<style scoped>
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