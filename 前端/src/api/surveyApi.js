import http from '../utils/request.js'

export default {
	delete(id){  //删除, delete请求
		return http.delete("/survey/delete/"+id);
	},
	update(survey){  //修改  put请求
		return http.put("/survey/update",survey);
	},
	
	insert(survey){  //新增  post请求
		return http.post("/survey/insert",survey);
	},
	
	list(selectForm){  //多条件查询调查
		return  http.get("/survey/list",{
			params:selectForm
		});
	}
}