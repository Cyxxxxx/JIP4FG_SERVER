package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @classname HotJobVO
 * @date 2020/12/20 15:47
 * @description 热门需求岗位
 */
@Data
public class HotJobVO {

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 发布数量
     */
    private Long count;

    /**
     * 稠密排序
     */
    private Integer ranks;

}
