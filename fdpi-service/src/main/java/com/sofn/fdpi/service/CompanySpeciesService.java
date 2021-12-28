package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.vo.CompanySpeciesInfoVO;
import com.sofn.fdpi.vo.CompanySpeciesStockVO;
import com.sofn.fdpi.vo.CompanySpeciesVO;

import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/30 14:37
 */

public interface CompanySpeciesService extends IService<CompanySpeciesVO> {
     PageUtils<CompanySpeciesVO> listCompanySpeciesVO(Map map, Integer pageNo, Integer pageSize);
     CompanySpeciesInfoVO getCompanySpeciesInfo(Map map, Integer pageNo, Integer pageSize);
     PageUtils<CompanySpeciesStockVO> listCompanySpeciesStock(Map map, Integer pageNo, Integer pageSize);
}
