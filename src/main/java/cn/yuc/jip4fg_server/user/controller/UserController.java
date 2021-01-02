package cn.yuc.jip4fg_server.user.controller;

import cn.yuc.common.base.auth.LoginForm;
import cn.yuc.common.base.auth.annotation.LoginRequired;
import cn.yuc.common.base.result.Result;
import cn.yuc.common.base.result.ResultUtil;
import cn.yuc.jip4fg_server.user.pojo.vo.UserInfoVO;
import cn.yuc.jip4fg_server.user.pojo.vo.UserResumeVO;
import cn.yuc.jip4fg_server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 用户请求控制器
 *
 * @Author yuc
 * 2020/11/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户注册接口
     *
     * @param userInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody @Validated UserInfoVO userInfoVO) throws Exception {
        System.out.println(userInfoVO.getUsername());
        System.out.println(userInfoVO.getPassword());
        userService.register(userInfoVO);
    }

    /**
     * 用户登录接口
     *
     * @param loginForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody @Validated LoginForm loginForm) throws Exception {
        Map<String, Object> loginInfo = userService.login(loginForm);
        return ResultUtil.succeed("登录成功！", loginInfo);
    }

    /**
     * 用户登出
     *
     * @param token
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestHeader("Token") String token) throws Exception {
        userService.logout(token);
    }

    /**
     * 获取用户简历信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/getResume", method = RequestMethod.GET)
    public UserResumeVO getResume(@RequestHeader("Token") String token) throws Exception {
        UserResumeVO resume = userService.getResume(token);
        return resume;
    }

    /**
     * 用户简历添加 / 更新
     *
     * @param token
     * @param resumeVO
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/addOrUpdateResume", method = RequestMethod.POST)
    public void addOrUpdateResume(@RequestHeader("Token") String token, @RequestBody @Validated UserResumeVO resumeVO) throws Exception {
        userService.addOrUpdateResume(resumeVO, token);
    }

    /**
     * 用户项目经历删除
     *
     * @param token
     * @param expId
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/delResumeProjExp", method = RequestMethod.POST)
    public void delResumeProjExp(@RequestHeader("Token") String token, @RequestParam @NotBlank(message = "expId不能为空！") String expId) throws Exception {
        userService.delResumeProjExp(expId, token);
    }

}
