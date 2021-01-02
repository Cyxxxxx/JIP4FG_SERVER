package cn.yuc.jip4fg_server.user.service;

import cn.yuc.common.base.auth.LoginForm;
import cn.yuc.jip4fg_server.user.pojo.po.UserInfoPO;
import cn.yuc.jip4fg_server.user.pojo.vo.UserInfoVO;
import cn.yuc.jip4fg_server.user.pojo.vo.UserResumeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 用户服务接口
 *
 * @Author yuc
 * 2020/11/18
 */
public interface UserService extends IService<UserInfoPO> {

    /**
     * 用户注册
     *
     * @param userInfoVO
     * @throws Exception
     */
    void register(UserInfoVO userInfoVO) throws Exception;

    /**
     * 用户登录
     *
     * @param loginForm
     * @return
     * @throws Exception
     */
    Map<String, Object> login(LoginForm loginForm) throws Exception;

    /**
     * 用户登出
     *
     * @param token
     * @throws Exception
     */
    void logout(String token) throws Exception;

    /**
     * 获取用户简历信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    UserResumeVO getResume(String token) throws Exception;

    /**
     * 增加 / 更新简历
     *
     * @param userResumeVO
     * @param token
     * @throws Exception
     */
    void addOrUpdateResume(UserResumeVO userResumeVO, String token) throws Exception;

    /**
     * 删除项目经历
     *
     * @param expId
     * @param token
     * @throws Exception
     */
    void delResumeProjExp(String expId, String token) throws Exception;

}
