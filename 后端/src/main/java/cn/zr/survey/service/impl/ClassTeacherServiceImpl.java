package cn.zr.survey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.entity.ClassTeacher;
import cn.zr.survey.service.ClassTeacherService;
import cn.zr.survey.mapper.ClassTeacherMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ClassTeacherServiceImpl extends ServiceImpl<ClassTeacherMapper, ClassTeacher>
    implements ClassTeacherService{

    @Override
    public boolean removeTeacher(Integer cid, Integer tid) {
        //构建条件
        LambdaQueryWrapper<ClassTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClassTeacher::getClassId,cid);//班级id条件
        wrapper.eq(ClassTeacher::getTeacherId,tid);//老师id条件
        return this.remove(wrapper);//根据cid和tid移除带班记录
    }
}




