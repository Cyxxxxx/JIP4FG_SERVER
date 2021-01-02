package cn.yuc.jip4fg_server.job.info.dao;

import cn.yuc.jip4fg_server.job.info.pojo.po.CompanyInfoPO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * tb_company_info 操作类
 *
 * @author YuC
 * 2020/12/03
 */
@Mapper
public interface CompanyInfoDAO extends BaseMapper<CompanyInfoPO> {

    Page<CompanyItemVO> getCompanyInfoPage(Page page, @Param("params") CompanyItemVO companyItemVO);

}
