package cn.yuc.jip4fg_server.job.info.pojo.po;

import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyDetailVO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 公司信息PO
 *
 * @author YuC
 * 2020/12/03
 */
@Data
@TableName("tb_company_info")
public class CompanyInfoPO {

    /**
     * 公司id，主键
     */
    @TableId
    private String companyId;

    /**
     * 公司名，可选入参
     */
    private String companyName;

    /**
     * 公司信息
     */
    private String companyInfo;

    /**
     * 行业类别，可选入参
     */
    private String industryType;

    /**
     * 公司地址
     */
    private String location;

    /**
     * 转化为CompanyDetailVO类型
     *
     * @return
     */
    public CompanyDetailVO toCompanyDetailVO() {
        CompanyDetailVO companyDetailVO = new CompanyDetailVO();
        BeanUtils.copyProperties(this, companyDetailVO);
        return companyDetailVO;
    }
}
