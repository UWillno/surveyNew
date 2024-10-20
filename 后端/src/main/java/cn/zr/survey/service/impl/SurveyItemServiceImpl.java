package cn.zr.survey.service.impl;

import cn.zr.survey.entity.Teacher;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.entity.SurveyItem;
import cn.zr.survey.service.SurveyItemService;
import cn.zr.survey.mapper.SurveyItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class SurveyItemServiceImpl extends ServiceImpl<SurveyItemMapper, SurveyItem>
    implements SurveyItemService{


    @Override
    public P selectPage(Map<String, Object> map) {
        //构建条件  调查内容模糊查询
        LambdaQueryWrapper<SurveyItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(map.containsKey("surveyText") && !StringUtils.isEmpty(map.get("surveyText").toString()),SurveyItem::getSurveyText,map.get("surveyText"));
        //创建日期降序排序
        wrapper.orderByDesc(SurveyItem::getChecked,SurveyItem::getCreateTime);
        //判断是否包含分页条件(分页 page当前页, size每页条数 )
        if (map.containsKey("page")){  //page表示当前页
            Long current =Long.parseLong( map.get("page").toString());
            Long size =Long.parseLong( map.get("size").toString());
            Page<SurveyItem> page = new Page<>(current,size);//创建分页对象
            page = this.page(page,wrapper);//多条件分页查询
            return new P(page.getTotal(),page.getRecords()); //总条数, 老师的集合
        }else{
            return new P(0L,this.list(wrapper));//.list(wrapper)多条件查询
        }
    }
}




