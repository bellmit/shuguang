/**
 * @Author 文俊云
 * @Date 2020/1/2 13:56
 * @Version 1.0
 */
package com.sofn.fdpi.mapper;

/**
 * @Author 文俊云
 * @Date 2020/1/2 13:56
 * @Version 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.vo.CompanySpeciesInfoVO;
import com.sofn.fdpi.vo.CompanySpeciesStockVO;
import com.sofn.fdpi.vo.CompanySpeciesVO;

import java.util.List;
import java.util.Map;

public interface CompanySpeciesMapper extends BaseMapper<CompanySpeciesVO> {
    List<CompanySpeciesVO> listCompanySpeciesVO(Map map);
    CompanySpeciesInfoVO getCompanySpeciesInfo(Map map);
    List<CompanySpeciesStockVO> listCompanySpeciesStock(Map map);
}
