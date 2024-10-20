package cn.zr.survey.service.impl;

import cn.zr.survey.entity.ClassTeacher;
import cn.zr.survey.service.ClassTeacherService;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.entity.Teacher;
import cn.zr.survey.service.TeacherService;
import cn.zr.survey.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

    @Autowired
    ClassTeacherService classTeacherService;

    @Override
    public P selectPage(Map<String, Object> map) {
        //构建多条件  编号,老师姓名模糊查询, 状态
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        //判断map集合中是否包含id条件,如果包含则wrapper添加id条件
        wrapper.eq(map.containsKey("id") && !StringUtils.isEmpty(map.get("id").toString()),Teacher::getId,map.get("id"));
        wrapper.like(map.containsKey("teacherName") && !StringUtils.isEmpty(map.get("teacherName").toString()),Teacher::getTeacherName,map.get("teacherName"));
        wrapper.eq(map.containsKey("status") && !StringUtils.isEmpty(map.get("status").toString()),Teacher::getStatus,map.get("status"));

        //判断是否包含cid   ,  是否不带班老师查询
        if (map.containsKey("cid") && !StringUtils.isEmpty(map.get("cid").toString())){
            //根据cid查询带班老师集合
            LambdaQueryWrapper<ClassTeacher> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ClassTeacher::getClassId,map.get("cid"));//cid条件
            List<ClassTeacher> classTeacherList = classTeacherService.list(wrapper1);
            //从带班老师集合中取出老师id
            List<Integer> tidList = classTeacherList.stream().map(ClassTeacher::getTeacherId).collect(Collectors.toList());
            //判断该班是否有带班老师
            if (tidList.size() >0 ){
                //构建不带这个班的老师条件,  从原来的老师条件排除带这个班的老师id
                wrapper.notIn(Teacher::getId,tidList);
            }
        }

        //创建日期降序排序
        wrapper.orderByDesc(Teacher::getCreateTime);
        //判断是否包含分页条件(分页 page当前页, size每页条数 )
        if (map.containsKey("page")){  //page表示当前页
            Long current =Long.parseLong( map.get("page").toString());
            Long size =Long.parseLong( map.get("size").toString());
            Page<Teacher> page = new Page<>(current,size);//创建分页对象
            page = this.page(page,wrapper);//多条件分页查询
            return new P(page.getTotal(),page.getRecords()); //总条数, 老师的集合
        }else{
            return new P(0L,this.list(wrapper));//.list(wrapper)多条件查询
        }
    }
}




