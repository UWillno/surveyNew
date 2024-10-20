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
 * @TableName clazz
 */
@TableName(value ="clazz")
@Data
public class Clazz implements Serializable {
    /**
     * 班级的id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级id
     */
    private Integer gradeId;

    /**
     * 创建修改事件
     */
    private Date createTime;

    /**
     * 逻辑删除,0为正常,1为删除
     */
    private Integer isDelete;

    /**
     * 班级状态,1为正常,2为实习,3为毕业
     */
    private Integer status;

    /**
     * 乐观锁
     */
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}