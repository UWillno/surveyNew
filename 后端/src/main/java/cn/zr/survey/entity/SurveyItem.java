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
 * @TableName survey_item
 */
@TableName(value ="survey_item")
@Data
public class SurveyItem implements Serializable {
    /**
     * 调查项id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 调查内容
     */
    private String surveyText;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除,0正常,1删除
     */
    private Integer isDelete;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否启动,0未启用, 1启用
     */
    private Integer checked;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}