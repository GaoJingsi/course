import axios from 'axios'
import {BASE_URL} from "common/config/apiConfig";

export function get${Domain}List(page = 1, size = 5) {
    return axios.get(BASE_URL + 'business/admin/${domain}', {
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

export function addOne${Domain}(${domain}) {
    return axios.post(BASE_URL + 'business/admin/${domain}', ${domain})
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function editOne${Domain}(${domain}) {
    return axios.put(BASE_URL + 'business/admin/${domain}', ${domain})
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

export function delOne${Domain}(id) {
    return axios.delete(BASE_URL + 'business/admin/${domain}/' + id)
        .then(data => {
            if (data.data.error_no === 0) {
                return Promise.resolve(true)
            }

            return Promise.reject(data.data)
        }).catch(resp => {
            return Promise.reject(resp)
        })
}

