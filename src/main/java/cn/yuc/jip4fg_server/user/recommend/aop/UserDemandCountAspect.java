package cn.yuc.jip4fg_server.user.recommend.aop;

import cn.yuc.common.base.auth.token.TokenInfo;
import cn.yuc.common.base.auth.token.util.TokenUtil;
import cn.yuc.common.base.redis.RedisUtil;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobItemVO;
import cn.yuc.jip4fg_server.user.recommend.config.UserJobRecommendConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuc
 * @description
 * @Date 2020/12/11 20:36
 */
@Slf4j
@Aspect
@Component
public class UserDemandCountAspect {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 拦截cn.yuc.jip4fg_server.job.info.controller.*下所有的get方法
     */
    @Pointcut("execution(* cn.yuc.jip4fg_server.job.info.controller.*.get*(..))")
    public void userDemandCountPointCut() {
    }

    @Before("userDemandCountPointCut()")
    public void before(JoinPoint jp) throws Exception {
        log.info("获取HttpServletRequest...");
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 没有token时，不处理
        log.info("获取token...");
        String token = request.getHeader("Token");
        log.info("token: {}", token);
        if (token == null) {
            return;
        }
        TokenInfo tokenInfo = TokenUtil.getInfoFromToken(token);
        // 参数值
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof JobItemVO) {
                String jobName = ((JobItemVO) arg).getJobName();
                log.info("获取用户搜索的jobName: {}", jobName);
                redisUtil.hashIncr(UserJobRecommendConfig.REDIS_KEY_HEADER +tokenInfo.getUsername(),jobName,1L);
            }
        }
    }

}
