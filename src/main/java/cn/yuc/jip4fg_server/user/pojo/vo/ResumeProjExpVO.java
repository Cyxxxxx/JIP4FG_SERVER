package cn.yuc.jip4fg_server.user.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户简历 - 项目经历 DTO
 *
 * @Author yuc
 * 2020/11/19
 */
@Data
public class ResumeProjExpVO {

    /**
     * 经历ID
     */
    @NotBlank(message = "项目ID不能为空！")
    private String expId;

    /**
     * 项目名
     */
    @NotBlank(message = "项目名不能为空！")
    private String projectName;

    /**
     * 担任角色
     */
    @NotBlank(message = "担任角色不能为空！")
    private String role;

    /**
     * 项目描述
     */
    @NotBlank(message = "项目描述不能为空！")
    private String description;

}
