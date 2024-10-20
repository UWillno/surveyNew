package cn.zr.survey.service;

import cn.zr.survey.entity.SurveyItem;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface SurveyItemService extends IService<SurveyItem> {

    P selectPage(Map<String, Object> map);
}
