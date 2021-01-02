package cn.yuc.jip4fg_server.job.analysis.pojo.vo;

import lombok.Data;

/**
 * @author Yuc
 * @classname JobSalaryPred
 * @date 2021/1/2 17:26
 * @description 岗位薪资预测（后三年）
 */
@Data
public class JobSalaryPredVO {

    private String jobType;

    private String jobName;

    private Integer year;

    private String salary;

}
