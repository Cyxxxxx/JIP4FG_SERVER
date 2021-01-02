package cn.yuc.jip4fg_server.job.info.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 岗位信息PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_job_info")
public class JobInfoPO {

    /**
     * 岗位id，主键
     */
    @TableId
    private String jobId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 岗位名
     */
    private String jobName;

    /**
     * 岗位类别
     */
    private String jobType;

    /**
     * JD,岗位描述
     */
    private String jobDesc;

    /**
     * 工作地址
     */
    private String location;

    /**
     * 工作性质，0 - 实习 | 1 - 校招 | 2 - 社招
     */
    private Integer workNature;

    /**
     * 岗位消息来源
     */
    private String origin;

    /**
     * 来源链接
     */
    private String originUrl;
}
