//对axios封装
import axios from 'axios'

//创建axios实例,withCredentials允许携带cookie
let http = axios.create({
	baseURL: 'http://127.0.0.1:9100',
	timeout: 1000,
	withCredentials:true  
})

//默认导出
export default http;