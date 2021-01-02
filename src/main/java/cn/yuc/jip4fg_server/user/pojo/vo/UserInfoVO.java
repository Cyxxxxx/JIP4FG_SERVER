package cn.yuc.jip4fg_server.user.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息 VO
 *
 * @Author yuc
 * 2020/11/17
 */
@Data
@Builder
public class UserInfoVO {

    @NotBlank(message = "用户名不能为空！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    private String password;

    private String phone;

    private String email;

    @Tolerate
    public UserInfoVO(){}


}
