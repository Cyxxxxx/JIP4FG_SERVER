package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @classname SalaryTop5VO
 * @date 2020/12/20 15:58
 * @description 热门薪资水平top5（按职位数量）
 */
@Data
public class SalaryTop5VO {

    /**
     * 薪资
     */
    private String salary;

    /**
     * 职位数量
     */
    private Long count;

    /**
     * 稠密排序
     */
    private Integer ranks;

}
