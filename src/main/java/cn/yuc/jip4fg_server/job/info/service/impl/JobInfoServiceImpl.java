package cn.yuc.jip4fg_server.job.info.service.impl;

import cn.yuc.jip4fg_server.job.enums.WorkNatureEnum;
import cn.yuc.jip4fg_server.job.info.dao.JobInfoDAO;
import cn.yuc.jip4fg_server.job.info.pojo.po.JobInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobItemVO;
import cn.yuc.jip4fg_server.job.info.service.JobInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuc
 * @Classname JobInfoServiceImpl
 * @Date 2020/12/5 1:26
 */
@Service
@Slf4j
public class JobInfoServiceImpl extends ServiceImpl<JobInfoDAO, JobInfoPO> implements JobInfoService {

    @Override
    public Page getJobInfoPage(Page page, JobItemVO jobItemVO) {
        page = this.baseMapper.getJobInfoPage(page, jobItemVO);
        List<JobItemVO> list =  page.getRecords();
        list.stream().map(o -> {
            o.setWorkNatureDesc(WorkNatureEnum.getWorkNatureDesc(o.getWorkNature()));
            return o;
        }).collect(Collectors.toList());
        page.setRecords(list);
        return page;
    }

    @Override
    public JobDetailVO getJobDetail(String jobId) {
        return this.baseMapper.getJobDetail(jobId);
    }
}
