package cn.yuc.jip4fg_server.job.analysis.dao;

import cn.yuc.jip4fg_server.job.analysis.pojo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yuc
 * @description
 * @Date 2020/12/10 17:21
 */
@Mapper
public interface AnalysisDAO {

    /**
     * 企业总数量，代替
     * select count(1) from tb_company_info;
     *
     * @return
     */
    Long getCompanyCount();

    /**
     * 拥有2家及以上分公司的企业统计，以分公司数量进行排行
     *
     * @return
     */
    List<BranchCompanyCountVO> getBranchCompanyCountRankList();


    /**
     * 统计每个行业的企业数量来进行排行
     *
     * @return
     */
    List<IndustryRankVO> getIndustryRankList();

    /**
     * 企业城市统计
     *
     * @return
     */
    List<CityCompanyCountVO> getCityCompanyCountList();

    /**
     * 职位总数量，代替
     * select count(1) from tb_job_info;
     *
     * @return
     */
    Long getJobCount();

    /**
     * 发布了2个及以上职位的企业统计排行(按职位数）
     *
     * @return
     */
    List<DoubleMoreJobCompanyVO> getDoubleMoreJobCompanyRankList();

    /**
     * 热门需求岗位top10（按发布数量）
     *
     * @return
     */
    List<HotJobVO> getHotJobTop10();

    /**
     * 职位工作地点与企业所在位置相同的职位统计
     *
     * @return
     */
    Long getSameLocationCompanyJobCount();

    /**
     * 在众多实习职位中，职位数量前3的城市，以及它们分别提供了多少职位
     *
     * @return
     */
    List<CityJobCountVO> getCityJobCountTop3();

    /**
     * 热门薪资水平top5（按职位数量）
     *
     * @return
     */
    List<SalaryTop5VO> getSalaryTop5();

    /**
     * 每月发布的职位数量及其比例
     *
     * @return
     */
    List<ReleaseJobCountVO> getReleaseJobByMonthList();

    /**
     * 岗位薪资预测（后三年）
     *
     * @return
     */
    List<JobSalaryPredVO> getJobSalaryPredList(@Param("jobName") String jobName);

    /**
     * 岗位薪资预测（后三年）- 岗位名列表
     *
     * @return
     */
    List<String> getJobNamePredList();

}
