package com.sofn.agpjpm.service;

import com.sofn.agpjpm.vo.PictureAttForm;
import com.sofn.agpjpm.vo.PictureAttVo;

import java.util.List;

/**
 * 图片附件服务接口
 *
 * @Author yumao
 * @Date 2020/3/10 15:57
 **/
public interface PictureAttService {


    /**
     * 新增
     */
    PictureAttVo save(PictureAttForm form, String fileSource);

    /**
     * 根据来源ID逻辑删除
     */
    void deleteBySourceId(String sourceId);

    /**
     * 根据来源ID查询列表
     */
    List<PictureAttVo> listBySourceId(String sourceId);

    /**
     * 更新图片文件
     */
    void updateSourceId(String sourceId, List<PictureAttForm> pictureAccessoriesForms, String fileSource, String fileUse);
}
