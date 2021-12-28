package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Forfei;
import com.sofn.fdpi.vo.ForfeiInfoVo;
import com.sofn.fdpi.vo.ForfeiProcessFrom;

import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/2 16:39
 */
public interface ForfeiService extends IService<Forfei> {
    /**
     * 获取罚没信息(分页)
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<Forfei> getForfeiListByPage(Map<String,Object> map,int pageNo, int pageSize);

    /**
     * 新增罚没信息
     * @param forfeiInfoVo

     * @return
     */
    int saveForfeiInfo(ForfeiInfoVo forfeiInfoVo);

    /**
     *
     * 删除罚没信息
     * @param id
     * @return
     */
    String deleteForFeiInfo(String id);
    //修改罚没信息
    String updateForFeiInfo(ForfeiInfoVo forfeiInfoVo);
    //根据id获取罚没信息
    Forfei getForfeiInfo(String id);
    //更新状态
    void updateStatus(String id, int status);

    /**
     * 审核罚没信息
     * @param from
     */
    void insertForfeiProcess(ForfeiProcessFrom from);
//    void pass(ForfeiProcessFrom from);
//    void back(ForfeiProcessFrom from);
}
