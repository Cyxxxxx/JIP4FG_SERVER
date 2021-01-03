package cn.yuc.jip4fg_server.user.recommend.config;

/**
 * @author yuc
 * @description 用户岗位推荐配置类
 * @Date 2020/12/18 15:52
 */
public class UserJobRecommendConfig {

    /**
     * 信息推送，默认每天15点开始推送一次
     */
    public final static String JOB_RECOMMEND_CRON = "0 0 15 * * *";

    /**
     * 默认每天0点收集一次
     */
    public final static String USER_DEMAND_COLLECT = "0 0 0 * * *";

    /**
     * 存储于Redis中的键头，用于搜索
     */
    public final static String REDIS_KEY_HEADER = "userDemand:";


}
