package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @classname CityCompanyCountVO
 * @date 2020/12/20 15:39
 * @description
 */
@Data
public class CityCompanyCountVO {

    /**
     * 城市名
     */
    private String city;

    /**
     * 企业数量
     */
    private Integer comCount;
}
