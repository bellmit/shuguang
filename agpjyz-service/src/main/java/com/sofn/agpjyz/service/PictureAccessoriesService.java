package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.PictureAccessoriesForm;
import com.sofn.agpjyz.vo.PictureAccessoriesVo;

import java.util.List;

/**
 * 图片附件服务接口
 *
 * @Author yumao
 * @Date 2020/3/10 15:57
 **/
public interface PictureAccessoriesService {


    /**
     * 新增
     */
    PictureAccessoriesVo save(PictureAccessoriesForm form, String fileSource);

    /**
     * 根据来源ID逻辑删除
     */
    void deleteBySourceId(String sourceId);

    /**
     * 根据来源ID查询列表
     */
    List<PictureAccessoriesVo> listBySourceId(String sourceId);

    /**
     * 更新图片文件
     */
    void updateSourceId(String sourceId, List<PictureAccessoriesForm> pictureAccessoriesForms, String fileSource, String fileUse);
}
