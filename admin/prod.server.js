const express = require('express')
const history = require('connect-history-api-fallback')

const app = express()

app.use(history())
app.use(express.static('./dist'))

const port = process.env.PORT || 9000

module.exports = app.listen(port, function (err) {
    if (err) {
        // eslint-disable-next-line no-console
        console.log(err)
        return
    }
    // eslint-disable-next-line no-console
    console.log('Listening at http://localhost:' + port + '\n')
})