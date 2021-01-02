package cn.yuc.jip4fg_server.job.salary.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 岗位薪资PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_job_salary")
public class JobSalaryPO {

    /**
     * 薪资id，主键
     */
    @TableId
    private String salaryId;

    /**
     * 岗位id
     */
    private String jobId;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 被赞次数
     */
    private int sup;

    /**
     * 被踩次数
     */
    private int nonSup;

    /**
     * 发表时间
     */
    private Date createDt;
}
