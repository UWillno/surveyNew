import http from '../utils/request.js'

export default {
	delete(id){  //删除老师, delete请求
		return http.delete("/teacher/delete/"+id);
	},
	update(teacher){  //修改老师  put请求
		return http.put("/teacher/update",teacher);
	},
	
	insert(teacher){  //新增老师  post请求
		return http.post("/teacher/insert",teacher);
	},
	
	list(teacherForm){  //多条件查询老师 条件中包含cid ,{} 把查询对象析构,析构出每个参数
		return  http.get("/teacher/list",{
			params:teacherForm
		});
	}
}