package cn.zr.survey.controller;

import cn.zr.survey.entity.Manager;
import cn.zr.survey.service.ManagerService;
import cn.zr.survey.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
//解决跨域问题 {"http://127.0.0.1:8080"} 前台的地址, allowCredentials允许携带cookie
@CrossOrigin(origins = {"http://127.0.0.1:8080"},allowCredentials = "true")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/manager/login")
    public R login(String username,String password){
        return R.ok(managerService.login(username,password));
    }

    @PostMapping("/manager/register")
    public R register(@RequestBody Manager manager){
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return R.ok(managerService.save(manager));//新增
    }
}
