package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author yuc
 * @description
 * @Date 2020/12/18 17:04
 */
@Data
@Builder
@TableName("tb_user_job_demand_count")
public class UserJobDemandCountPO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String userId;

    private String keyword;

    /**
     * 用户点击关键词的次数
     */
    private Long freq;

    @Tolerate
    public UserJobDemandCountPO(){}

}
