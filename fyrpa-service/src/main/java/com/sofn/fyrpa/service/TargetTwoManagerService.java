package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.model.TargetTwoManager;
import com.sofn.fyrpa.vo.*;

public interface TargetTwoManagerService extends IService<TargetTwoManager> {

    /**
     * 新增一级指标对象
     * @param targetOneManagerAddVo
     * @return
     */
    Result addTargetOneManager(TargetOneManagerAddVo targetOneManagerAddVo);


    /**
     * 修改一级指标对象
     * @param targetOneManagerEditVo
     * @return
     */
    Result updateTargetOneManager(TargetOneManagerEditVo targetOneManagerEditVo);



    /**
     * 新增二级指标对象
     * @param targetTwoManagerAddVo
     * @return
     */
    Result addTargetTwoManager(TargetTwoManagerAddVo targetTwoManagerAddVo);

    /**
     * 编辑
     * @param targetTwoManagerEditVo
     * @return
     */
    Result updateTargetTwoManager(TargetTwoManagerEditVo targetTwoManagerEditVo);

    /**
     *分页查询list
     * @param targetName
     * @param startTime
     * @param endTime
     * @param pageNo
     * @param pageSize
     * @param targetType
     * @return
     */
    IPage<TargetManagerListVo> selectListData(String targetName, String startTime,
                                               String endTime,Integer pageNo, Integer pageSize,String targetType);




    /**
     * 启用或者停用指标
     * @param id
     * @return
     */
     Result startAndstopTarget(String id);

    /**
     * 二级指标详情页查询可编辑
     * @param id
     * @return
     */
    Result selectTargetTwoManagerById(String id);

    /**
     * 一级指标详情页查询可编辑
     * @param id
     * @return
     */
    Result selectTargetOneManagerById( String id);

    /**
     * 查询一级指标
     * @return
     */
    Result selectTargetOneManagerList();

}
