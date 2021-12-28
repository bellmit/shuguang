package com.sofn.fdpi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.enums.ComeStockFlowEnum;
import com.sofn.fdpi.mapper.CompanySpeciesMapper;
import com.sofn.fdpi.service.CompanySpeciesService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import com.sofn.fdpi.vo.CompanySpeciesInfoVO;
import com.sofn.fdpi.vo.CompanySpeciesStockVO;
import com.sofn.fdpi.vo.CompanySpeciesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:44
 */
@Slf4j
@Service
public class CompanySpeciesServiceImpl extends BaseService<CompanySpeciesMapper, CompanySpeciesVO> implements CompanySpeciesService {


    @Autowired(required = false)
    private CompanySpeciesMapper companySpeciesMapper;

    @Resource
    private SysRegionApi sysRegionApi;

    @Override
    public PageUtils<CompanySpeciesVO> listCompanySpeciesVO(Map map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<CompanySpeciesVO> list = companySpeciesMapper.listCompanySpeciesVO(map);
        PageInfo<CompanySpeciesVO> listPageInfo = new PageInfo<CompanySpeciesVO>(list);
        listPageInfo.setList(list);
        return PageUtils.getPageUtils(listPageInfo);
    }

    @Override
    public CompanySpeciesInfoVO getCompanySpeciesInfo(Map map, Integer pageNo, Integer pageSize) {
        CompanySpeciesInfoVO companySpeciesInfoVO = companySpeciesMapper.getCompanySpeciesInfo(map);
        companySpeciesInfoVO.setPageUtils(listCompanySpeciesStock(map, pageNo, pageSize));
        return companySpeciesInfoVO;
    }

    @Override
    public PageUtils<CompanySpeciesStockVO> listCompanySpeciesStock(Map map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<CompanySpeciesStockVO> list = companySpeciesMapper.listCompanySpeciesStock(map);
        Map<String, String> mapReason = sysRegionApi.getDictListByType("fdpi_changeType").getData().
                stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
        for (CompanySpeciesStockVO c : list) {
            String billType = c.getBillType();
            String changeReason = ComeStockFlowEnum.getVal(billType);
            c.setChangeReason(StringUtils.hasText(changeReason) ? changeReason : mapReason.get(billType));
            if (StringUtils.isEmpty(c.getOtherComName())) {
                c.setOtherComName("无");
            }
        }
        PageInfo<CompanySpeciesStockVO> listPageInfo = new PageInfo<CompanySpeciesStockVO>(list);
        listPageInfo.setList(list);
        return PageUtils.getPageUtils(listPageInfo);
    }

}
