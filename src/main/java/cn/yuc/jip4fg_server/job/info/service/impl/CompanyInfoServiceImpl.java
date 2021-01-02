package cn.yuc.jip4fg_server.job.info.service.impl;

import cn.yuc.jip4fg_server.job.info.dao.CompanyInfoDAO;
import cn.yuc.jip4fg_server.job.info.pojo.po.CompanyInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyItemVO;
import cn.yuc.jip4fg_server.job.info.service.CompanyInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yuc
 * @Classname CompanyInfoServiceImpl
 * @Date 2020/12/5 1:27
 */
@Slf4j
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoDAO, CompanyInfoPO> implements CompanyInfoService {

    @Override
    public Page getCompanyInfoPage(Page page, CompanyItemVO companyItemVO) {
        // 条件查询
        page = this.baseMapper.getCompanyInfoPage(page, companyItemVO);
        return page;
    }

    @Override
    public CompanyDetailVO getCompanyDetail(String companyId) {
        return this.getById(companyId).toCompanyDetailVO();
    }
}
