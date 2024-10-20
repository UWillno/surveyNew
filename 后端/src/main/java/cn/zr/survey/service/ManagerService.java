package cn.zr.survey.service;

import cn.zr.survey.entity.Manager;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ManagerService extends IService<Manager> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Manager login(String username, String password);
}
