package cn.yuc.jip4fg_server.job.info.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @Classname CompanyInfoVO
 * @Date 2020/12/5 1:43
 */
@Data
public class CompanyItemVO {


    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名，可选入参
     */
    private String companyName;

    /**
     * 行业类别，可选入参
     */
    private String industryType;

    /**
     * 公司地址
     */
    private String location;

}
