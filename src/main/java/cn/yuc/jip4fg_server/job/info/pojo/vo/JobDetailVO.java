package cn.yuc.jip4fg_server.job.info.pojo.vo;

import lombok.Data;

/**
 * @author yuc
 * @description 岗位详情
 * @Date 2020/12/10 11:01
 */
@Data
public class JobDetailVO extends JobItemVO{

    /**
     * JD,岗位描述
     */
    private String jobDesc;

    /**
     * 来源链接
     */
    private String originUrl;

}
