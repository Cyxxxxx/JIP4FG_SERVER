package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @Classname BranchCompanyCountVO
 * @Date 2020/12/20 15:33
 * @description 拥有2家及以上分公司的企业统计
 */
@Data
public class BranchCompanyCountVO {

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 分公司总数
     */
    private Long count;

}
