package cn.yuc.jip4fg_server.job.info.service;

import cn.yuc.jip4fg_server.job.info.pojo.po.CompanyInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyItemVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Yuc
 * @Classname CompanyInfoService
 * @Date 2020/12/5 1:24
 */
public interface CompanyInfoService extends IService<CompanyInfoPO> {
    /**
     * 分页获取公司信息
     *
     * @param page
     * @param companyItemVO
     * @return
     */
    Page<CompanyItemVO> getCompanyInfoPage(Page page, CompanyItemVO companyItemVO);

    /**
     * 根据公司id获取公司详情
     *
     * @param companyId
     * @return
     */
    CompanyDetailVO getCompanyDetail(String companyId);

}
