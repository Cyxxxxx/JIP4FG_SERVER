package cn.yuc.jip4fg_server.user.recommend.mq.listener;

import cn.yuc.common.base.email.EmailService;
import cn.yuc.common.base.exception.UserException;
import cn.yuc.jip4fg_server.job.info.pojo.vo.JobDetailVO;
import cn.yuc.jip4fg_server.job.info.service.JobInfoService;
import cn.yuc.jip4fg_server.user.pojo.po.JobRecommendPO;
import cn.yuc.jip4fg_server.user.pojo.po.UserInfoPO;
import cn.yuc.jip4fg_server.user.recommend.mq.config.RecommendServiceQueue;
import cn.yuc.jip4fg_server.user.service.JobRecommendService;
import cn.yuc.jip4fg_server.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 岗位推荐 - 消息监听
 *
 * @Author yuc
 * 2020/11/19
 */
@Component
@Slf4j
@RabbitListener(queues = RecommendServiceQueue.JOB_RECOMMEND)
public class JobRecommendMsgListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JobInfoService jobInfoService;

    @Autowired
    private JobRecommendService jobRecommendService;

    /**
     * 标题模板
     */
    private final String titleTemplate = "【JIP4FG】岗位推荐 - %s | %s";

    /**
     * 内容模板
     */
    private final String contentTemplate = "亲爱的%s同学，%s的%s正在热招，还不来看看吗? \n\n>><a href=\"%s\">点击查看</a>";

    /**
     * API
     */
    private final String api = "/job/";

    @RabbitHandler
    @Transactional(rollbackFor = Exception.class)
    public void emailSendQueueListener(String jsonStr) throws Exception {
        JobRecommendPO job = JSONObject.parseObject(jsonStr, JobRecommendPO.class);
        UserInfoPO user = userService.getById(job.getUserId());
        // 用户邮箱判空
        if (StringUtils.isBlank(user.getEmail())) {
            log.error("发生异常:", UserException.Type.EMAIL_DOESNT_EXIST.ofException());
            throw new MessageConversionException("消息消费失败，移出消息队列！");
        }
        JobDetailVO jobDetail = jobInfoService.getJobDetail(job.getJobId());
        // 使用jobId找到岗位信息的来源url
        String url = jobDetail.getOriginUrl();
        String title = String.format(titleTemplate, job.getCompanyName(), job.getJobName());
        String content = String.format(contentTemplate, user.getUsername(), job.getCompanyName(), job.getJobName(), url);
        log.info(content);
        log.info("user:{}", user);
        emailService.sendSimpleMail(user.getEmail(), title, content);
        // 推荐过的岗位设置为true并存回数据库
        job.setVisited(true);
        jobRecommendService.updateById(job);
    }


}
