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
 * @TableName survey_record
 */
@TableName(value ="survey_record")
@Data
public class SurveyRecord implements Serializable {
    /**
     * 记录id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 调查项id
     */
    private Integer itemId;

    /**
     * 满分
     */
    private Integer totalScore;

    /**
     * 选项id
     */
    private Integer optionsId;

    /**
     * 选项得分
     */
    private Integer optionsScore;

    /**
     * 是否合格,0合格,1不合格
     */
    private Integer isOk;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除0正常,1删除
     */
    private Integer isDelete;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 学生编号
     */
    private Integer studentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}