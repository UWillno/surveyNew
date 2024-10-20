package cn.zr.survey.service;

import cn.zr.survey.entity.Teacher;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 多条件分页查询
     * @param map 多条件封装map
     * @return P 包含总条数, 集合
     */
    P selectPage(Map<String, Object> map);
}
