package cn.zr.survey.controller;

import cn.zr.survey.service.ClazzService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @GetMapping("/clazz/list")
    public R clazzList(@RequestParam Map<String,Object> map){  //多条件分页查询 .list()  ctrl+alt+v : 自动接收返回值
        return R.ok(clazzService.selectPage(map));
    }
}
