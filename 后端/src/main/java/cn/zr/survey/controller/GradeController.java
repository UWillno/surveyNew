package cn.zr.survey.controller;

import cn.zr.survey.service.GradeService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @GetMapping("/grade/list")
    public R gradeList(){
        return  R.ok(gradeService.list());
    }
}
