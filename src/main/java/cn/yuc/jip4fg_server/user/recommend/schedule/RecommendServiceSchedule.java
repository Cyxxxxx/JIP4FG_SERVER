package cn.yuc.jip4fg_server.user.recommend.schedule;

import cn.yuc.common.base.redis.RedisUtil;
import cn.yuc.jip4fg_server.user.dao.JobRecommendDAO;
import cn.yuc.jip4fg_server.user.pojo.po.JobRecommendPO;
import cn.yuc.jip4fg_server.user.recommend.config.UserJobRecommendConfig;
import cn.yuc.jip4fg_server.user.recommend.mq.config.RecommendServiceQueue;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author yuc
 * @description 推荐服务定时任务
 * @Date 2020/12/18 15:55
 */
@Component
public class RecommendServiceSchedule {

    @Autowired
    private JobRecommendDAO jobRecommendDAO;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 岗位推送 - Email通道
     */
    @Scheduled(cron = UserJobRecommendConfig.JOB_RECOMMEND_CRON)
    public void sendRecommendEmail() {
        // 没推荐过的岗位发送给有邮箱的用户
        List<JobRecommendPO> jobRecommendPOList = jobRecommendDAO.getEmailRecommendList();
        for (JobRecommendPO po : jobRecommendPOList) {
            amqpTemplate.convertAndSend(RecommendServiceQueue.JOB_RECOMMEND, JSON.toJSONString(po));
        }
    }


    /**
     * 用户（岗位）需求收集
     *
     * @description 将存放于Redis中的用户需求定时收集到MySQL的tb_job_recommend表中
     */
    @Scheduled(cron = UserJobRecommendConfig.USER_DEMAND_COLLECT)
    public void userDemandCollect() {
        Set<String> keys = redisUtil.scan(UserJobRecommendConfig.REDIS_KEY_HEADER);
        for (String key : keys) {
            amqpTemplate.convertAndSend(key,RecommendServiceQueue.USER_DEMAND_COLLECT);
        }
    }

}
