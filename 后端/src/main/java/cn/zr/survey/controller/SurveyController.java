package cn.zr.survey.controller;

import cn.zr.survey.entity.SurveyItem;
import cn.zr.survey.entity.Teacher;
import cn.zr.survey.service.SurveyItemService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController //当所有方法都返回json, 可以为类上添加注解
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class SurveyController {
    @Autowired
    SurveyItemService surveyItemService;

    @GetMapping("/survey/list")
    public R surveyList(@RequestParam Map<String,Object> map){  //多条件分页查询 .list()  ctrl+alt+v : 自动接收返回值
        return R.ok(surveyItemService.selectPage(map));
    }

    @DeleteMapping("/survey/delete/{id}")
    public R surveyDelete(@PathVariable Integer id){ //@PathVariable获取占位符的值
        return R.ok(surveyItemService.removeById(id));
    }

    @PostMapping("/survey/insert")
    public R surveyInsert(@RequestBody SurveyItem surveyItem){
        return R.ok(surveyItemService.save(surveyItem));
    }

    @PutMapping("/survey/update")
    public R surveyUpdate(@RequestBody SurveyItem surveyItem){
        return R.ok(surveyItemService.updateById(surveyItem));
    }

}
