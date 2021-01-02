package cn.yuc.jip4fg_server.job.salary.dao;

import cn.yuc.jip4fg_server.job.salary.pojo.po.JobSalaryCommentPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * tb_job_salary_comment 操作类
 *
 * @author YuC
 * 2020/12/03
 */
@Mapper
public interface JobSalaryCommentDAO extends BaseMapper<JobSalaryCommentPO> {
}
