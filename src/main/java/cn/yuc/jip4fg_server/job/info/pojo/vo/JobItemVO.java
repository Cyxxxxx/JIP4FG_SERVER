package cn.yuc.jip4fg_server.job.info.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Yuc
 * @Classname JobInfoVO
 * @Date 2020/12/8 19:58
 */
@Data
public class JobItemVO {
    /**
     * 岗位id
     */
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
     * 工作地址
     */
    private String location;

    /**
     * 工作性质，0 - 实习 | 1 - 校招 | 2 - 社招
     */
    private Integer workNature;
    private String workNatureDesc;

    /**
     * 工作薪资
     */
    private String salary;

    /**
     * 岗位消息来源
     */
    private String origin;

    /**
     * 来源链接
     */
    private String originUrl;

    /**
     * 发布时间
     */
    private Date createDt;

}
