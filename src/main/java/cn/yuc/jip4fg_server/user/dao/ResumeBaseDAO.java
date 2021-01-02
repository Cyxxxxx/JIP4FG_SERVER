package cn.yuc.jip4fg_server.user.dao;

import cn.yuc.jip4fg_server.user.pojo.po.ResumeBasePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * tb_user_resume_base 操作类
 *
 * @Author yuc
 * 2020/11/17
 */
@Mapper
public interface ResumeBaseDAO extends BaseMapper<ResumeBasePO> {
}
