package cn.zr.survey.controller;

import cn.zr.survey.entity.ClassTeacher;
import cn.zr.survey.service.ClassTeacherService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class ClazzTeacherController {

    @Autowired
    ClassTeacherService classTeacherService;

    @DeleteMapping("/clazzteacher/delete") //移除带班老师
    public R clazzteacherDelete(Integer cid, Integer tid){
        //System.out.println(cid+" , " +tid);
        return  R.ok(classTeacherService.removeTeacher(cid,tid));
    }

    @PostMapping("/clazzteacher/insert") //添加带班老师
    public R clazzteacherInsert(@RequestBody List<ClassTeacher> list){
        System.out.println(list);

       return R.ok(classTeacherService.saveBatch(list)) ;//saveBatch(传入集合)
    }
}
