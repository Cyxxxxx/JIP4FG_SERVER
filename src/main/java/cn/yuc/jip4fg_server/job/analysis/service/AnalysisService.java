package cn.yuc.jip4fg_server.job.analysis.service;

import java.util.Map;

/**
 * @author yuc
 * @description
 * @Date 2020/12/10 17:26
 */
public interface AnalysisService {

    /**
     * 获取企业相关的分析数据
     *
     * @return
     */
    Map<String, Object> getCompanyAnalyzedData();

    /**
     * 获取岗位相关的分析数据
     *
     * @return
     */
    Map<String, Object> getJobAnalyzedData();

    /**
     * 根据岗位名获取该岗位后三年的薪资预测
     *
     * @param jobName
     * @return
     */
    Map<String, Object> getJobSalaryPredData(String jobName);

}
