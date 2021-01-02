package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Yuc
 * @classname CityJobCountVO
 * @date 2020/12/20 15:53
 * @description
 */
@Data
public class CityJobCountVO {

    /**
     * 城市
     */
    private String location;

    /**
     * 职位数量
     */
    private Long count;

    /**
     * 所占比例（千分位）
     */
    private BigDecimal per;

    /**
     * 稠密排序
     */
    private Integer ranks;
}
