import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getChapterList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/chapter', {
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

export function getChapterListByCourse(page = 1, size = 5, courseId) {
    return axios.get(BASE_URL + 'business/admin/chapter', {
        params: {
            page,
            size,
            courseId
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
    return axios.post(BASE_URL + 'business/admin/chapter', chapter)
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
    return axios.put(BASE_URL + 'business/admin/chapter', chapter)
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
    return axios.delete(BASE_URL + 'business/admin/chapter/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

