const path = require('path')

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {
    devServer: {
        before(app) {

        }
    },
    chainWebpack(config) {
        config.resolve.alias
            .set('components', resolve('src/components'))
            .set('common', resolve('src/common'))
            .set('api', resolve('src/api'))
            .set('base', resolve('src/base'))
            .set('views', resolve('src/views'))
            .set('router', resolve('src/router'))
    },
    publicPath: ''
}