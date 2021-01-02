package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户简历 - 期望 PO
 *
 * @Author yuc
 * 2020/11/18
 */
@Data
@TableName("tb_user_resume_expect")
public class ResumeExpectPO {

    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 期望工作
     */
    @TableField(value = "job")
    private String expectJob;

    /**
     * 期望地点
     */
    @TableField(value = "location")
    private String expectLocation;

    /**
     * 期望薪资
     */
    @TableField(value = "salary")
    private Integer expectSalary;

}
