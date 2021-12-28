package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.vo.AquaticResourcesProtectionInfoVoList;

import javax.servlet.http.HttpServletResponse;

public interface ResourcesInfoGatherService {

    /**
     * 保护区信息汇总分页查询
     * @param pageNo
     * @param pageSize
     * @param submitTime
     * @param regionCodeArr
     * @param basinOrSeaArea
     * @param riverOrMaritimeSpace
     * @param keyword
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByCondition(Integer pageNo, Integer pageSize, String submitTime, String[] regionCodeArr,
                                     String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name,String majorProtectObject);

    /**
     *详情页查询
     * @param id
     * @return
     */
    Result selectDetailsById(String id);

    /**
     * 审批意见查询
     * @param id
     * @return
     */
    Result selectInfoAuditList(String id);

    /**
     * 导出
     * @param fileName
     * @param httpServletResponse
     * @param submitTime
     * @param regionCodeArr
     * @param basinOrSeaArea
     * @param riverOrMaritimeSpace
     * @param keyword
     * @return
     */
    void export(String fileName, HttpServletResponse httpServletResponse, String submitTime, String[] regionCodeArr,
                 String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name, String majorProtectObject);


    /**
     * 按面积排序
     * @param sort
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByAreasSort(String sort,Integer pageNo,Integer pageSize, String submitTime, String[] regionCodeArr,
                                                                                    String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name,String majorProtectObject);

    /**
     * 按时间排序
     * @param sort
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectGatherListByTimesSort(String sort,Integer pageNo,Integer pageSize, String submitTime, String[] regionCodeArr,
                                                                                    String basinOrSeaArea, String riverOrMaritimeSpace, String keyword, String name,String majorProtectObject);

}
