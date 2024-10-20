import http from '../utils/request.js'

export default {
	list({gradeId,className,status,page,size}){  //多条件查询班级  ,{} 把查询对象析构,析构出每个参数
		return http.get("/clazz/list",{
			params: {
				gradeId,className,status,page,size
			}
		})
	}
}