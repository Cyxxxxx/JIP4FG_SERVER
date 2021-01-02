package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @classname DoubleMoreJobCompanyVO
 * @date 2020/12/20 15:45
 * @description 发布了2个及以上职位的企业统计
 */
@Data
public class DoubleMoreJobCompanyVO {

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 企业名
     */
    private String companyName;

    /**
     * 职位数量
     */
    private Long jobCount;
}
