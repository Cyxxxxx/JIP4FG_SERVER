package cn.yuc.jip4fg_server.user.service.impl;


import cn.yuc.common.base.auth.LoginForm;
import cn.yuc.common.base.auth.cache.impl.UserCacheServiceRedis;
import cn.yuc.common.base.auth.token.TokenInfo;
import cn.yuc.common.base.exception.UserException;
import cn.yuc.jip4fg_server.user.dao.UserInfoDAO;
import cn.yuc.jip4fg_server.user.pojo.dto.ResumeProjExpDTO;
import cn.yuc.jip4fg_server.user.pojo.po.ResumeBasePO;
import cn.yuc.jip4fg_server.user.pojo.po.ResumeExpectPO;
import cn.yuc.jip4fg_server.user.pojo.po.ResumeProjExpPO;
import cn.yuc.jip4fg_server.user.pojo.po.UserInfoPO;
import cn.yuc.jip4fg_server.user.pojo.vo.UserInfoVO;
import cn.yuc.jip4fg_server.user.pojo.vo.UserResumeVO;
import cn.yuc.jip4fg_server.user.service.ResumeBaseService;
import cn.yuc.jip4fg_server.user.service.ResumeExpectService;
import cn.yuc.jip4fg_server.user.service.ResumeProjExpService;
import cn.yuc.jip4fg_server.user.service.UserService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 服务实现：用户服务
 *
 * @Author yuc
 * 2020/11/17
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends UserCacheServiceRedis<UserInfoDAO, UserInfoPO> implements UserService {

    @Autowired
    private ResumeBaseService resumeBaseService;

    @Autowired
    private ResumeExpectService resumeExpectService;

    @Autowired
    private ResumeProjExpService resumeProjExpService;

    @Override
    public void register(UserInfoVO userInfoVO) throws Exception {
        if (baseMapper.userAlreadyExist(userInfoVO.getUsername())) {
            throw UserException.Type.USER_NAME_ALREADY_EXIST.ofException();
        }
        UserInfoPO userInfoPO = new UserInfoPO();
        BeanUtils.copyProperties(userInfoVO, userInfoPO);
        this.save(userInfoPO);
    }


    @Override
    public Map<String, Object> login(LoginForm loginForm) throws Exception {
        String pwd = baseMapper.getPwdByUsername(loginForm.getUsername());
        if (pwd == null || !pwd.equals(loginForm.getPassword())) {
            throw UserException.Type.USER_NAME_OR_PWD_INCORRECT.ofException();
        } else {
            // 将用户信息存入缓存
            UserInfoPO userInfoPO = new UserInfoPO();
            BeanUtils.copyProperties(loginForm,userInfoPO);
            TokenInfo tokenInfo = this.cacheUserInfo(userInfoPO);
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("token",tokenInfo.getToken());
            retMap.put("username",tokenInfo.getUsername());
            return retMap;
        }
    }

    @Override
    public void logout(String token) throws Exception {
        this.cacheEvictUserInfo(token);
    }

    @Override
    public UserResumeVO getResume(String token) throws Exception {
        UserInfoPO userInfoPO = this.getUserInfoCached(token);
        // 从数据库中获取对象
        ResumeBasePO resumeBasePO = resumeBaseService.getById(userInfoPO.getUserId());
        ResumeExpectPO resumeExpectPO = resumeExpectService.getById(userInfoPO.getUserId());
        List<ResumeProjExpDTO> projectExpList = baseMapper.getProjectExpListByUserId(userInfoPO.getUserId());

        // 判空并组成对象
        UserResumeVO userResumeVO = new UserResumeVO();
        Optional.ofNullable(resumeBasePO).ifPresent(po -> {
            BeanUtils.copyProperties(po, userResumeVO);
            userResumeVO.setSkillTag(JSON.parseArray(po.getSkillTag(), String.class));
        });
        Optional.ofNullable(resumeExpectPO).ifPresent(po -> BeanUtils.copyProperties(po, userResumeVO));
        userResumeVO.setProjectExpList(projectExpList);
        return userResumeVO;
    }


    @Override
    public void addOrUpdateResume(UserResumeVO userResumeVO, String token) throws Exception {
        UserInfoPO userInfoPO = getUserInfoCached(token);
        // tb_user_resume_base 插入
        ResumeBasePO resumeBasePO = new ResumeBasePO();
        resumeBasePO.setUserId(userInfoPO.getUserId());
        BeanUtils.copyProperties(userResumeVO, resumeBasePO);
        resumeBasePO.setSkillTag(JSON.toJSONString(userResumeVO.getSkillTag()));
        resumeBaseService.saveOrUpdate(resumeBasePO);

        // tb_user_resume_expect 插入
        ResumeExpectPO resumeExpectPO = new ResumeExpectPO();
        resumeExpectPO.setUserId(userInfoPO.getUserId());
        BeanUtils.copyProperties(userResumeVO, resumeBasePO);
        BeanUtils.copyProperties(userResumeVO, resumeExpectPO);
        resumeExpectService.saveOrUpdate(resumeExpectPO);

        // tb_user_resume_projexp 插入
        List<ResumeProjExpDTO> projectExpVOList = userResumeVO.getProjectExpList();
        List<ResumeProjExpPO> projExpPOList = projectExpVOList
                .stream().map(dto -> {
                    ResumeProjExpPO po = dto.convertToPO();
                    po.setUserId(userInfoPO.getUserId());
                    return po;
                }).collect(Collectors.toList());
        log.info("projExpPOList:{}", projExpPOList.toString());
        resumeProjExpService.saveOrUpdateBatch(projExpPOList);
    }

    @Override
    public void delResumeProjExp(String expId, String token) throws Exception {
        String userId = this.getUserInfoCached(token).getUserId();
        ResumeProjExpPO projExpPO = resumeProjExpService.getById(expId);
        // 校验expId的有效性
        Optional.ofNullable(projExpPO).orElseThrow(() -> UserException.Type.EXPID_DOESNT_EXIST.ofException());

        // 校验当前登录用户是否有权限删除该项目经历
        String userIdDB = projExpPO.getUserId();
        log.info(">>>>>userId:{},userIdDB:{}", userId, userIdDB);
        if (!userId.equals(userIdDB)) {
            throw UserException.Type.NO_PERMISSION.ofException();
        }
        resumeProjExpService.removeById(expId);
    }

}
