package cn.yuc.jip4fg_server.job.salary.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 岗位薪资评论PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_job_salary_comment")
public class JobSalaryCommentPO {

    /**
     * 评论id
     */
    @TableId
    private String commentId;

    /**
     * 薪资id
     */
    private String salaryId;


    /**
     * 评价内容
     */
    private String content;

    /**
     * 被赞的次数
     */
    private int sup;

    /**
     * 被踩的次数
     */
    private int nonSup;

    /**
     * 评论发表时间
     */
    private Date createDt;
}
