import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getCategoryList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/category', {
        params: {
            page,
            size
        }
    })
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve({data: data.data.data, msg: data.data.msg})
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function addOneCategory(category) {
    return axios.post(BASE_URL + 'business/admin/category', category)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function editOneCategory(category) {
    return axios.put(BASE_URL + 'business/admin/category', category)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function delOneCategory(id) {
    return axios.delete(BASE_URL + 'business/admin/category/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

