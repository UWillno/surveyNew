package cn.zr.survey.controller;

import cn.zr.survey.entity.Teacher;
import cn.zr.survey.service.TeacherService;
import cn.zr.survey.vo.R;
import javafx.beans.binding.MapBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController //当所有方法都返回json, 可以为类上添加注解
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /*@GetMapping("/teacher/list")
    public R teacherList(){  //查询所有老师  .list()  ctrl+alt+v : 自动接收返回值
        return R.ok(teacherService.list());
    }*/

    @GetMapping("/teacher/list")
    public R teacherList(@RequestParam Map<String,Object> map){  //多条件分页查询 .list()  ctrl+alt+v : 自动接收返回值
        return R.ok(teacherService.selectPage(map));
    }

    @DeleteMapping("/teacher/delete/{id}")
    public R teacherDelete(@PathVariable Integer id){ //@PathVariable获取占位符的值
        return R.ok(teacherService.removeById(id));
    }

    @PostMapping("/teacher/insert")
    public R teacherInsert(@RequestBody Teacher teacher){
        return R.ok(teacherService.save(teacher));
    }

    @PutMapping("/teacher/update")
    public R teacherUpdate(@RequestBody Teacher teacher){
        return R.ok(teacherService.updateById(teacher));
    }
}

