package cn.zr.survey.service.impl;

import cn.zr.survey.entity.*;
import cn.zr.survey.service.*;
import cn.zr.survey.vo.ClazzVo;
import cn.zr.survey.vo.P;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.mapper.ClazzMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz>
    implements ClazzService{

    @Autowired
    StudentService studentService;

    @Autowired
    ClassTeacherService classTeacherService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    GradeService gradeService;

    @Override
    public P selectPage(Map<String, Object> map) {
        //构建多条件  年级编号,班级模糊查询, 状态
        LambdaQueryWrapper<Clazz> wrapper = new LambdaQueryWrapper<>();
        //判断map集合中是否包含id条件,如果包含则wrapper添加id条件
        wrapper.eq(map.containsKey("gradeId") && !StringUtils.isEmpty(map.get("gradeId").toString()),Clazz::getGradeId,map.get("gradeId"));
        wrapper.like(map.containsKey("className") && !StringUtils.isEmpty(map.get("className").toString()),Clazz::getClassName,map.get("className"));
        wrapper.eq(map.containsKey("status") && !StringUtils.isEmpty(map.get("status").toString()),Clazz::getStatus,map.get("status"));




        //判断是否包含分页条件(分页 page当前页, size每页条数 )
        if (map.containsKey("page")){  //page表示当前页
            Long current =Long.parseLong( map.get("page").toString());
            Long size =Long.parseLong( map.get("size").toString());
            IPage<Clazz> page = new Page<>(current,size);//创建分页对象
            page = this.page(page,wrapper);//多条件分页查询
            List<Clazz> clazzList = page.getRecords();//班级的集合
            //把clazz类型集合转为clazzVo类型

            return new P(page.getTotal(),getClazzVo(clazzList)); //总条数,  vo类型集合
        }else{
            List<Clazz> clazzList = this.list(wrapper);
            return new P(0L,getClazzVo(clazzList));//.list(wrapper)多条件查询
        }
    }

    private List<ClazzVo> getClazzVo(List<Clazz> clazzList) {
        //jdk8新特性
        List<ClazzVo> voList = clazzList.stream().map(clazz -> {
            ClazzVo vo = new ClazzVo();
            BeanUtils.copyProperties(clazz,vo);//把班级属性值复制给vo对象
            //查询年级名称
            Grade grade = gradeService.getById(clazz.getGradeId());//根据年级id查询年级
            vo.setGradeName(grade.getGradeName());

            //查询班级人数
            LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Student::getClassId,clazz.getId());//构建班级id
            int count = studentService.count(wrapper);//查询该班级的人数
            vo.setCount(count);//设置人数

            //查询老师集合
            LambdaQueryWrapper<ClassTeacher> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ClassTeacher::getClassId,clazz.getId());//构建班级id, 班级和老师关系表
            List<ClassTeacher> classTeacherList = classTeacherService.list(wrapper1);//带班老师和班级集合
            List<Teacher> teacherList  =new ArrayList<>();//空集合
            //判断是否有带班老师
            if (classTeacherList.size() > 0){
                //取出带班级的老师的id   stream()获取流  map()中间操作
                List<Integer> tidList = classTeacherList.stream().map(ClassTeacher::getTeacherId).collect(Collectors.toList());
                //通过老师id查询对应的老师
                teacherList = teacherService.listByIds(tidList);
            }
            vo.setTeacherList(teacherList);//设置带班老师集合

            return vo;
        }).collect(Collectors.toList());
        return voList;//返回vo类型的集合
    }
}





