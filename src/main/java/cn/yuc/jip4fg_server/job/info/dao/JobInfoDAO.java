package cn.yuc.jip4fg_server.job.info.dao;

import cn.yuc.jip4fg_server.job.info.pojo.po.JobInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * tb_job_info 操作类
 *
 * @author YuC
 * 2020/12/03
 */
@Mapper
public interface JobInfoDAO extends BaseMapper<JobInfoPO> {


    Page<JobItemVO> getJobInfoPage(Page page, @Param("params") JobItemVO params);

    JobDetailVO getJobDetail(String jobId);

}
