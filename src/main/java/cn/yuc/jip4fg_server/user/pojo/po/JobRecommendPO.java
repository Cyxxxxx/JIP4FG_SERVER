package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 岗位推荐表
 *
 * @Author yuc
 * 2020/11/19
 */
@Data
@TableName("tb_job_recommend")
public class JobRecommendPO {

    /**
     * 推荐id，唯一
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String recommendId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 岗位id
     */
    private String jobId;

    /**
     * 是否已访问 - true: 已访问 | false: 未访问
     */
    private boolean visited;
}
