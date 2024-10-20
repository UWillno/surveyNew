package cn.zr.survey.vo;

import cn.zr.survey.entity.Clazz;
import cn.zr.survey.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzVo extends Clazz {
    /**
     * 年级名称
     */
    private String gradeName;


    /**
     * 班级人数
     */
    private Integer count;


    /**
     * 带班老师
     */
    private List<Teacher> teacherList;
}
