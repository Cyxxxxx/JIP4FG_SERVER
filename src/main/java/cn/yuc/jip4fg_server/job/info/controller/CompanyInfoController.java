package cn.yuc.jip4fg_server.job.info.controller;

import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyDetailVO;
import cn.yuc.jip4fg_server.job.info.pojo.vo.CompanyItemVO;
import cn.yuc.jip4fg_server.job.info.service.CompanyInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yuc
 * @Classname CompanyInfoController
 * @Date 2020/12/5 2:59
 */
@RestController
@RequestMapping("/company")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    /**
     * 分页查询公司列表
     *
     * @param current
     * @param size
     * @param companyItemVO
     * @return
     */
    @PostMapping("/getCompanyInfoPage")
    public Page<CompanyItemVO> getCompanyInfoPage(@RequestParam Long current, @RequestParam Long size, @RequestBody CompanyItemVO companyItemVO) {
        Page<CompanyItemVO> page = new Page<>();
        page.setSize(size);
        page.setCurrent(current);
        return companyInfoService.getCompanyInfoPage(page, companyItemVO);
    }

    /**
     * 查询公司详情
     *
     * @param companyId
     * @return
     */
    @GetMapping("/getCompanyDetail/{companyId}")
    public CompanyDetailVO getCompanyDetail(@PathVariable("companyId") String companyId){
        return companyInfoService.getCompanyDetail(companyId);
    }

}
