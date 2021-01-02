package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户简历 - 基础 PO
 *
 * @Author yuc
 * 2020/11/18
 */
@Data
@TableName("tb_user_resume_base")
public class ResumeBasePO {

    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 出生年月
     */
    private String birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 技能标签
     */
    private String skillTag;
}
