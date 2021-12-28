package com.sofn.agsjdm.service;

import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.agsjdm.vo.InformationManagementFrom;
import com.sofn.agsjdm.vo.InformationManagementTableVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 9:50
 **/
public interface InformationManagementService {
    void insertIm(InformationManagementFrom im);

    void updateIm(InformationManagementFrom im);

    void delete(String id);

    InformationManagementFrom searchByid(String id);

    PageUtils<InformationManagementTableVo> listPage(Map<String, Object> map, int pageNo, int pageSize);

    List<DropDownVo> listForPointCollect(String regionLastCode);

    Map<String, String> getPointMap();
}
