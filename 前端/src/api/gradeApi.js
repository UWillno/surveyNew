import http from  '../utils/request'

export default {
	gradeList(){
		return http.get("/grade/list");
	}
}