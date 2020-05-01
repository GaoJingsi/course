import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getChapterList() {
    return axios.get(BASE_URL + 'business/admin/chapter')
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve({data: data.data.data, msg: data.data.msg})
            }

            return Promise.reject(data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}