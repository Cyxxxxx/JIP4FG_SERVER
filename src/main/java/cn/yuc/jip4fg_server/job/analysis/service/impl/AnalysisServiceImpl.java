package cn.yuc.jip4fg_server.job.analysis.service.impl;

import cn.yuc.jip4fg_server.job.analysis.dao.AnalysisDAO;
import cn.yuc.jip4fg_server.job.analysis.pojo.vo.*;
import cn.yuc.jip4fg_server.job.analysis.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuc
 * @description
 * @Date 2020/12/10 17:27
 */
@Slf4j
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisDAO analysisDAO;

    @Override
    public Map<String, Object> getCompanyAnalyzedData() {
        Map<String, Object> retMap = new HashMap<>();
        Long companyCount = analysisDAO.getCompanyCount();
        List<BranchCompanyCountVO> branchCompanyCountRankList = analysisDAO.getBranchCompanyCountRankList();
        List<IndustryRankVO> industryRankList = analysisDAO.getIndustryRankList();
        List<CityCompanyCountVO> cityCompanyCountList = analysisDAO.getCityCompanyCountList();
        retMap.put("companyCount", companyCount);
        retMap.put("branchCompanyCountRankList", branchCompanyCountRankList);
        retMap.put("industryRankList", industryRankList);
        retMap.put("cityCompanyCountList", cityCompanyCountList);
        return retMap;
    }

    @Override
    public Map<String, Object> getJobAnalyzedData() {
        Map<String, Object> retMap = new HashMap<>();
        Long jobCount = analysisDAO.getJobCount();
        List<DoubleMoreJobCompanyVO> doubleMoreJobCompanyRankList = analysisDAO.getDoubleMoreJobCompanyRankList();
        List<HotJobVO> hotJobTop10 = analysisDAO.getHotJobTop10();
        Long sameLocationCompanyJobCount = analysisDAO.getSameLocationCompanyJobCount();
        List<CityJobCountVO> cityJobCountTop3 = analysisDAO.getCityJobCountTop3();
        List<SalaryTop5VO> salaryTop5 = analysisDAO.getSalaryTop5();
        List<ReleaseJobCountVO> releaseJobByMonthList = analysisDAO.getReleaseJobByMonthList();
        List<String> jobNamePredList = analysisDAO.getJobNamePredList();
        retMap.put("jobCount", jobCount);
        retMap.put("doubleMoreJobCompanyRankList", doubleMoreJobCompanyRankList);
        retMap.put("hotJobTop10", hotJobTop10);
        retMap.put("sameLocationCompanyJobCount", sameLocationCompanyJobCount);
        retMap.put("cityJobCountTop3", cityJobCountTop3);
        retMap.put("salaryTop5", salaryTop5);
        retMap.put("releaseJobByMonthList", releaseJobByMonthList);
        retMap.put("jobNamePredList", jobNamePredList);
        return retMap;
    }

    @Override
    public Map<String, Object> getJobSalaryPredData(String jobName) {
        List<JobSalaryPredVO> jobSalaryPredList = analysisDAO.getJobSalaryPredList(jobName);
        return getJobSalaryPredData4Echart(jobSalaryPredList);
    }

    /**
     * 将拿到的预测薪资列表处理为Echarts易于接受的数据
     * @param jobSalaryPredList
     * @return
     */
    private Map<String, Object> getJobSalaryPredData4Echart(List<JobSalaryPredVO> jobSalaryPredList) {
        Map<String, Object> retMap = new HashMap<>();
        // 将预测的年份作为x轴
        retMap.put("xAxisData",jobSalaryPredList.stream().map(JobSalaryPredVO::getYear).collect(Collectors.toSet()));
        List<Map<String,Object>> series = new ArrayList<>();
        // 提取每年的薪资上/下限
        List<Integer> minSalaryEachYear = new ArrayList<>();
        List<Integer> maxSalaryEachYear = new ArrayList<>();
        jobSalaryPredList.stream().forEach(vo -> {
            String salaryStr = vo.getSalary();
            String[] salaryArr = salaryStr.split("k-");
            salaryArr[1] = salaryArr[1].substring(0,salaryArr[1].length()-1);
            minSalaryEachYear.add(Integer.valueOf(salaryArr[0]));
            maxSalaryEachYear.add(Integer.valueOf(salaryArr[1]));
        });
        retMap.put("minSalaryEachYear",minSalaryEachYear);
        retMap.put("maxSalaryEachYear",maxSalaryEachYear);
        return retMap;
    }

}
