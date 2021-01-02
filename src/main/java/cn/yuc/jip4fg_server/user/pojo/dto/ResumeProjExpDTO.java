package cn.yuc.jip4fg_server.user.pojo.dto;

import cn.yuc.jip4fg_server.user.pojo.po.ResumeProjExpPO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * 用户简历 - 项目经历 DTO
 *
 * @Author yuc
 * 2020/11/17
 */
@Data
public class ResumeProjExpDTO {

    /**
     * 经历ID
     * 传入的对象中，经历ID为空时，代表是新增的项目经理
     * 否则为已存在的项目经历
     */
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

    /**
     * 转化为 PO
     *
     * @return
     */
    public ResumeProjExpPO convertToPO() {
        ResumeProjExpPO po = new ResumeProjExpPO();
        BeanUtils.copyProperties(this, po);
        return po;
    }

}
