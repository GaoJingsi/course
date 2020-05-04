import Tool from './tool'
import {MessageBox} from "./SweetAlert2";

const Validator = {
    require: function (value, text) {
        if (Tool.isEmpty(value)) {
            new MessageBox({
                message: text + "不能为空",
                icon: 'error'
            }).toast()
            return false;
        } else {
            return true
        }
    },

    length: function (value, text, min, max) {
        if (Tool.isEmpty(value)) {
            return true;
        }
        if (!Tool.isLength(value, min, max)) {
            new MessageBox({
                message: text + "长度" + min + "~" + max + "位",
                icon: 'error'
            }).toast()
            return false;
        } else {
            return true
        }
    }
};

export default Validator