package cn.yuc.jip4fg_server.user.recommend.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuc
 * @description 推荐服务队列
 * @Date 2020/12/18 16:10
 */
@Configuration
public class RecommendServiceQueue {

    /**
     * 岗位推荐队列
     */
    public final static String JOB_RECOMMEND = "JOB_RECOMMEND_QUEUE";

    /**
     * 岗位推荐 - 邮件发送队列
     *
     * @return
     */
    @Bean
    public Queue emailSendQueue() {
        return new Queue(JOB_RECOMMEND, true, false, false);
    }


    /**
     * 用户需求收集队列
     */
    public final static String USER_DEMAND_COLLECT = "USER_DEMAND_COLLECT_QUEUE";
    @Bean
    public Queue userDemandCollectQueue() {
        return new Queue(USER_DEMAND_COLLECT, true, false, false);
    }

}
