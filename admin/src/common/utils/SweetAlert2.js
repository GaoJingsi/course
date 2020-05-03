import Swal from 'sweetalert2'

class MessageBox {

    options = {}

    constructor(options) {
        if (!options.title) {
            this.options.title = '消息提示'
        } else {
            this.options.title = options.title
        }
        if (!options.message) {
            this.options.message = ''
        } else {
            this.options.message = options.message
        }
        if (!options.icon) {
            this.options.icon = 'warning'
        } else {
            this.options.icon = options.icon
        }
        if (!options.btnOkText) {
            this.options.btnOkText = '确定'
        } else {
            this.options.btnOkText = options.btnOkText
        }
        if (!options.btnCancelText) {
            this.options.btnCancelText = '取消'
        } else {
            this.options.btnCancelText = options.btnCancelText
        }
        if (!options.callback) {
            this.options.callback = function (result) {

            }
        } else {
            this.options.callback = options.callback
        }
    }

    comfirm() {

        return Swal.fire({
            title: this.options.title,
            text: this.options.message,
            icon: this.options.icon,
            showCancelButton: true,
            confirmButtonText: this.options.btnOkText,
            cancelButtonText: this.options.btnCancelText
        }).then((result) => {
            this.options.callback(result)
        })
    }

    alert() {
        Swal.fire(
            this.options.title,
            this.options.message,
            this.options.icon
        )
    }

    toast() {
        Swal.fire({
            position: 'top-end',
            icon: this.options.icon,
            title: this.options.message,
            showConfirmButton: false,
            timer: 1500
        })
    }
}

export {MessageBox, Swal}