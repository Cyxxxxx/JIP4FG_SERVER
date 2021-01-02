package cn.yuc.jip4fg_server.user.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息 PO
 *
 * @Author yuc
 * 2020/11/17
 */
@Data
@TableName("tb_user_info")
public class UserInfoPO {

    /**
     * 使用长度为32的随机字符串作为用户的唯一标识主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
