//用户方法编写   .js可以省略不写  导入axios实例
import http from '../utils/request'

export default {
	login(username,password){  //传来用户名,密码
		return http.get("/manager/login",{
			params:{
				username,
				password
			}
		});   //http.get("url请求",参数)  
	}
}
