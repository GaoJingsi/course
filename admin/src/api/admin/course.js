import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function getCourseList(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/course', {
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

export function getCourseById(id) {
    return axios.get(BASE_URL + 'business/admin/course' + (id ? '/' + id : ''))
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve({data: data.data.data, msg: data.data.msg})
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function addOneCourse(course) {
    return axios.post(BASE_URL + 'business/admin/course', course)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function editOneCourse(course) {
    return axios.put(BASE_URL + 'business/admin/course', course)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function delOneCourse(id) {
    return axios.delete(BASE_URL + 'business/admin/course/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

