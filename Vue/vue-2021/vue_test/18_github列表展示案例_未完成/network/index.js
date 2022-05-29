import axios from 'axios'

const instance = axios.create({
    baseURL: 'http://localhost:8080/api',
})

// 响应拦截器
instance.interceptors.response.use(req => {
    if (!req) {
        console.log('服务器无响应')
        return 
    }
    // console.log('http status:', req.status)
    if (!req.data) {
        console.log('服务器没有返回数据')
        return 
    }
    return req.data
}, err => console.log(err))

export function request(type, url, data) {
    const configuration = {
        method: type,
        url,
    }
    if (type === 'GET' || type === 'DELETE')
        configuration.params = data
    else if (type === 'PUT' || type === 'POST')
        configuration.data = data
    else
        return console.error(`${type} 类型不正确`)
    // console.log(configuration)
    return instance(configuration)
}
