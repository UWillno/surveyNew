package cn.zr.survey.service;

import cn.zr.survey.entity.Clazz;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface ClazzService extends IService<Clazz> {

    P selectPage(Map<String, Object> map);
}
