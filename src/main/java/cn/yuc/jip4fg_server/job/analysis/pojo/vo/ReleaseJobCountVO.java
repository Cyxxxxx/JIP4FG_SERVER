package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Yuc
 * @classname ReleaseJobCountVO
 * @date 2020/12/20 16:00
 * @description 月发布的职位数量及其比例
 */
@Data
public class ReleaseJobCountVO {

    /**
     * 月份枚举，表里直接用了'xx月'
     */
    private String monthId;

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
