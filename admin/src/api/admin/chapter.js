import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getChapterList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/chapter/list', {
        params: {
            page,
            size
        }
    })
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve({data: data.data.data, msg: data.data.msg})
            }

            return Promise.reject(data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}