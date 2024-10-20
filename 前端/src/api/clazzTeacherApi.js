import http from '../utils/request'

export default {
	insert( clazzTeacherIdList){  //新增老师
		return http.post("/clazzteacher/insert",clazzTeacherIdList);
	},
	
	removeTeacher(cid , tid){  //移除带班老师
		return http.delete("/clazzteacher/delete",{
			params:{
				cid , tid
			}
		})
	}
	
}