package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author yuc
 * @description
 * @Date 2020/12/10 17:22
 */
@Data
public class IndustryRankVO {

    /**
     * 行业类别
     */
    private String industryType;

    /**
     * 岗位数量
     */
    private Integer count;
}
