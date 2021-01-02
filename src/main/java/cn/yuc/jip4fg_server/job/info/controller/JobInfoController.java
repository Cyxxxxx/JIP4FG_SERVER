package cn.yuc.jip4fg_server.job.info.controller;

import cn.yuc.jip4fg_server.job.info.pojo.vo.JobDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobItemVO;
import cn.yuc.jip4fg_server.job.info.service.JobInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yuc
 * @Classname JobInfoController
 * @Date 2020/12/8 22:56
 */
@RestController
@RequestMapping("/job")
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    /**
     * 分页查询岗位列表
     *
     * @param size
     * @param current
     * @param jobItemVO
     * @return
     */
    @PostMapping("/getJobInfoPage")
    public Page<JobItemVO> getJobInfoPage(@RequestParam Long size, @RequestParam Long current, @RequestBody JobItemVO jobItemVO) {
        Page<JobItemVO> page = new Page<>();
        page.setSize(size);
        page.setCurrent(current);
        return jobInfoService.getJobInfoPage(page, jobItemVO);
    }


    @GetMapping("/getJobDetail/{jobId}")
    public JobDetailVO getJobDetail(@PathVariable("jobId") String jobId){
        return jobInfoService.getJobDetail(jobId);
    }

}
