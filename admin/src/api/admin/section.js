import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getSectionList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/section', {
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

export function getSectionListByChapter(page = 1, size = 5, chapterId) {
    return axios.get(BASE_URL + 'business/admin/section', {
        params: {
            page,
            size,
            chapterId
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

export function addOneSection(section) {
    return axios.post(BASE_URL + 'business/admin/section', section)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function editOneSection(section) {
    return axios.put(BASE_URL + 'business/admin/section', section)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function delOneSection(id) {
    return axios.delete(BASE_URL + 'business/admin/section/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

