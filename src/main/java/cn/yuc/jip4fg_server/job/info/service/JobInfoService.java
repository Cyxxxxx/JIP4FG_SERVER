package cn.yuc.jip4fg_server.job.info.service;


import cn.yuc.jip4fg_server.job.info.pojo.po.JobInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobItemVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Yuc
 * @Classname JobInfoService
 * @Date 2020/12/5 1:25
 */
public interface JobInfoService extends IService<JobInfoPO> {

    /**
     * 分页查询岗位信息
     *
     * @param page
     * @param jobItemVO
     * @return
     */
    Page getJobInfoPage(Page page, JobItemVO jobItemVO);

    /**
     * 查询岗位详情
     *
     * @param jobId
     * @return
     */
    JobDetailVO getJobDetail(String jobId);

}
