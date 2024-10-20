package cn.zr.survey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName class_teacher
 */
@TableName(value ="class_teacher")
@Data
public class ClassTeacher implements Serializable {
    /**
     * 班级老师编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    private Integer classId;

    /**
     * 老师编号
     */
    private Integer teacherId;

    /**
     * 创建时间
     */
    private Date createTime = new Date();

    /**
     * 乐观锁
     */
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}