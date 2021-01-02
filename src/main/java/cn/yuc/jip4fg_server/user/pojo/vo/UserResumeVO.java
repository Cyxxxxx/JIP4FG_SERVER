package cn.yuc.jip4fg_server.user.pojo.vo;

import cn.yuc.jip4fg_server.user.pojo.dto.ResumeProjExpDTO;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户简历信息 VO
 *
 * @Author yuc
 * 2020/11/17
 */
@Data
public class UserResumeVO {

    /** 基本资料 **/

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
    private List<String> skillTag;


    /** 工作期望 **/

    /**
     * 期望工作
     */
    private String expectJob;

    /**
     * 期望地点
     */
    private String expectLocation;

    /**
     * 期望薪资
     */
    private Integer expectSalary;

    /** 项目经历 **/

    /**
     * 项目经历列表
     */
    @Valid
    private List<ResumeProjExpDTO> projectExpList;

}
