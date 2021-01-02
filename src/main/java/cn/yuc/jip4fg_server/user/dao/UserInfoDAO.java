package cn.yuc.jip4fg_server.user.dao;

import cn.yuc.jip4fg_server.user.pojo.dto.ResumeProjExpDTO;
import cn.yuc.jip4fg_server.user.pojo.po.UserInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * tb_user_info 操作类
 *
 * @Author yuc
 * 2020/11/17
 */
@Mapper
public interface UserInfoDAO extends BaseMapper<UserInfoPO> {

    /**
     * 判断用户名是否已经存在
     *
     * @param username
     * @return
     */
    @Select("SELECT COUNT(1)>0 FROM tb_user_info WHERE username = #{username}")
    boolean userAlreadyExist(@Param("username") String username);

    /**
     * 根据用户名获取密码
     *
     * @param username
     * @return
     */
    @Select("SELECT password FROM tb_user_info WHERE username = #{username}")
    String getPwdByUsername(@Param("username") String username);

    /**
     * 根据用户ID，获取项目经历
     *
     * @param userId
     * @return
     */
    @Select("SELECT exp_id,project_name,role,description FROM tb_user_resume_projexp WHERE user_id = #{userId}")
    List<ResumeProjExpDTO> getProjectExpListByUserId(@Param("userId") String userId);

    @Select("SELECT user_id FROM tb_user_info WHERE username = #{username}")
    String getIdByUsername(@Param("username") String username);

}
