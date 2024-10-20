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
 * @TableName item_options
 */
@TableName(value ="item_options")
@Data
public class ItemOptions implements Serializable {
    /**
     * 选项id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 选项内容
     */
    private String context;

    /**
     * 选项分值
     */
    private Integer score;

    /**
     * 调查项的id
     */
    private Integer itemId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}