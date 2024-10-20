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
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 学生的id
     */
    @TableId(type = IdType.AUTO)
    private Integer studnetId;

    /**
     * 学生的学号
     */
    private String studentNo;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生性别,0女,1男
     */
    private Integer gender;

    /**
     * 学生手机号码
     */
    private String phoneNumber;

    /**
     * 出生日期
     */
    private Date bornday;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 班级编号
     */
    private Integer classId;

    /**
     * 状态,1正常,2请假,3休学,4毕业
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除,0正常,1删除
     */
    private Integer isDelete;

    /**
     * 乐观所
     */
    private Integer version;

    /**
     * 微信的唯一Id
     */
    private String openid;

    /**
     * 用户微信的密钥
     */
    private String sessionkey;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}