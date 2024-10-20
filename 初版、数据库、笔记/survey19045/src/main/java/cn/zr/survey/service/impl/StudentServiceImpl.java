package cn.zr.survey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.zr.survey.entity.Student;
import cn.zr.survey.service.StudentService;
import cn.zr.survey.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




