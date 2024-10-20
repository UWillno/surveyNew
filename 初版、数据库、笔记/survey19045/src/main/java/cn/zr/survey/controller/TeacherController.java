package cn.zr.survey.controller;

import cn.zr.survey.entity.Teacher;
import cn.zr.survey.service.TeacherService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //当所有方法都返回json, 可以为类上添加注解
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher/list")
    public R teacherList(){  //查询所有老师  .list()  ctrl+alt+v : 自动接收返回值
        return R.ok(teacherService.list());
    }
}
