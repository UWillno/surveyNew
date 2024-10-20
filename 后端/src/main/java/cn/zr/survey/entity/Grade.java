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
 * @TableName grade
 */
@TableName(value ="grade")
@Data
public class Grade implements Serializable {
    /**
     * 年级的id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态,1正常,2实习,3毕业
     */
    private Integer status;

    /**
     * 逻辑删除
     */
    private Integer isDelete;

    /**
     * 乐观锁
     */
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}