package cn.yuc.jip4fg_server.job.exp.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 岗位面经PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_job_interview_exp")
public class JobItvExpPO {

    /**
     * 面经id，主键
     */
    @TableId
    private String expId;

    /**
     * 岗位id
     */
    private String jobId;

    /**
     * 面经
     */
    @TableField(value = "interview_exp")
    private String itv_exp;

    /**
     * 面试评价星级
     */
    private int star;

    /**
     * 面经发表时间
     */
    private Date createDt;

}
