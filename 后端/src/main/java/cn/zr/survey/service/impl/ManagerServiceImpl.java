package cn.zr.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.entity.Manager;
import cn.zr.survey.service.ManagerService;
import cn.zr.survey.mapper.ManagerMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager>
    implements ManagerService{

    @Override
    public Manager login(String username, String password) {
        //构造条件
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Manager::getUsername,username);//用户名

        Manager manager = this.getOne(wrapper);//根据用户名和密码查询
        if (manager != null){//用户名正确
            //当前的密码和加密后的密码比对
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            boolean result = passwordEncoder.matches(password, manager.getPassword());//传入的密码,加密的密码
            if (result){ //为true表示密码正确
                //生成新的token
                String token = UUID.randomUUID().toString()+System.currentTimeMillis();
                //更新数据库token
                manager.setToken(token);
                this.updateById(manager);
                manager.setPassword(null);//清空密码, 将来manager返回浏览器,为安全
            }else{
                manager = null;//密码不正确则清空对象
            }


        }
        return manager;
    }
}




