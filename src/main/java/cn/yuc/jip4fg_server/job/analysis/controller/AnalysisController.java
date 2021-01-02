package cn.yuc.jip4fg_server.job.analysis.controller;

import cn.yuc.jip4fg_server.job.analysis.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuc
 * @description
 * @Date 2020/12/10 17:39
 */
@RequestMapping("/analysis")
@RestController
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("getData")
    public Map<String,Object> getData(){
        Map<String, Object> retMap = new HashMap<>();
        Map<String, Object> companyAnalyzedData = analysisService.getCompanyAnalyzedData();
        Map<String, Object> jobAnalyzedData = analysisService.getJobAnalyzedData();
        retMap.put("companyAnalyzedData",companyAnalyzedData);
        retMap.put("jobAnalyzedData",jobAnalyzedData);
        return retMap;
    }

    @GetMapping("getJobSalaryPredList")
    public Map<String, Object> getJobSalaryPredList(@RequestParam String jobName) {
        return analysisService.getJobSalaryPredData(jobName);
    }

}
