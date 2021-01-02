package cn.yuc.jip4fg_server.user.recommend.mq.listener;

import cn.yuc.common.base.redis.RedisUtil;
import cn.yuc.jip4fg_server.user.dao.UserInfoDAO;
import cn.yuc.jip4fg_server.user.pojo.po.UserJobDemandCountPO;
import cn.yuc.jip4fg_server.user.recommend.mq.config.RecommendServiceQueue;
import cn.yuc.jip4fg_server.user.service.JobRecommendService;
import cn.yuc.jip4fg_server.user.service.UserJobDemandCountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yuc
 * @description
 * @Date 2020/12/18 16:16
 */
@Slf4j
@Component
@RabbitListener(queues = RecommendServiceQueue.USER_DEMAND_COLLECT)
public class UserDemandCollectMsgListener {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private JobRecommendService jobRecommendService;

    @Autowired
    private UserJobDemandCountService userJobDemandCountService;

    @RabbitHandler
    public void userDemandCollectListener(String key) {
        log.info("对RedisKey:{}下的数据进行收集...", key);
        // 分隔key得到username,key格式：REDIS_HEAD:username
        String username = key.substring(key.indexOf(":") + 1);
        String userId = userInfoDAO.getIdByUsername(username);

        // 拿出数据库中该用户所有的数据
        List<UserJobDemandCountPO> userJobDemandCountPOList = userJobDemandCountService.getBaseMapper()
                .selectList(new QueryWrapper<UserJobDemandCountPO>().eq("user_id", userId));

        // 将拿出来的List数据转为 Map<keyword,Obj>,方便后续读取
        Map<String, UserJobDemandCountPO> userKeywordPOMap = userJobDemandCountPOList.stream()
                .collect(Collectors.toMap(UserJobDemandCountPO::getKeyword, po -> po, (key1, key2) -> key2));

        List<UserJobDemandCountPO> resList = new ArrayList<>(userJobDemandCountPOList.size());
        try {
            // redis scan
            Map<Object, Object> mapOfKey = redisUtil.hashScan(key);
            for (Map.Entry<Object, Object> entry : mapOfKey.entrySet()) {
                // 获取用户关键词数据
                String keyword = (String) entry.getKey();
                Long freq = Long.valueOf((String) entry.getValue());
                // 获取用户对应关键词信息
                UserJobDemandCountPO thisPO = userKeywordPOMap.get(keyword);
                if (thisPO == null) {
                    // 当数据库中无该用户的此关键词记录时，新建一条记录
                    thisPO = UserJobDemandCountPO.builder()
                            .userId(userId)
                            .keyword(keyword)
                            .freq(0L)
                            .build();
                }
                freq += thisPO.getFreq();
                thisPO.setFreq(freq);
                resList.add(thisPO);
                // 释放缓存
                redisUtil.hashDel(key, keyword);
            }
            userJobDemandCountService.saveOrUpdateBatch(resList);
        } catch (IOException e) {
            log.error("发生错误:{}", e);
        }
    }

}
