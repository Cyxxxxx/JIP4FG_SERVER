package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户简历 - 项目经历 PO
 *
 * @Author yuc
 * 2020/11/18
 */
@Data
@TableName(value = "tb_user_resume_projexp")
public class ResumeProjExpPO {

    /**
     * 经历ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String expId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 担任角色
     */
    private String role;

    /**
     * 项目描述
     */
    private String description;
}
