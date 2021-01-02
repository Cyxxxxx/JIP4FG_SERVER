package cn.yuc.jip4fg_server.user.dao;

import cn.yuc.jip4fg_server.user.pojo.po.JobRecommendPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 岗位推荐表 - 数据库操作类
 *
 * @Author yuc
 * 2020/11/19
 */
@Mapper
public interface JobRecommendDAO extends BaseMapper<JobRecommendPO> {

    /**
     * 获取没推荐过的岗位（只找有邮箱的用户）
     * 每次获取2个，细水长流
     *
     * @return
     */
    @Select("SELECT r.* FROM " +
            "tb_job_recommend r INNER JOIN tb_user_info u " +
            "ON u.email IS NOT NULL AND u.email<>'' " +
            "AND r.visited = 0 " +
            "AND r.user_id = u.user_id limit 2")
    List<JobRecommendPO> getEmailRecommendList();
}
