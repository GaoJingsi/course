import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getChapterList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/chapters', {
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

export function addOneChapter(chapter) {
    return axios.post(BASE_URL + 'business/admin/chapters', chapter)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function editOneChapter(chapter) {
    return axios.put(BASE_URL + 'business/admin/chapters', chapter)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function delOneChapter(id) {
    return axios.delete(BASE_URL + 'business/admin/chapters/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

