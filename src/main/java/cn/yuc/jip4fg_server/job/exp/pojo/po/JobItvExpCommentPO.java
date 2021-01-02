package cn.yuc.jip4fg_server.job.exp.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 岗位面经评价PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_job_interview_exp_comment")
public class JobItvExpCommentPO {

    /**
     * 评价id
     */
    @TableId
    private String commentId;

    /**
     * 面经id
     */
    private String expId;

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
