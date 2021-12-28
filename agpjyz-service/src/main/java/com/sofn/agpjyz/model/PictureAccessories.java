package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 图片附件类
 *
 * @Author yumao
 * @Date 2020/3/9 11:37
 **/
@Data
@TableName("PICTURE_ACCESSORIES")
public class PictureAccessories extends BaseModel<PictureAccessories> {

    /**
     * 文件ID(支撑平台文件ID)
     */
    private String fileId;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件用途
     */
    private String fileUse;
    /**
     * 文件来源 1 资源调查 2 资源调查标本采集
     */
    private String fileSource;
    /**
     * 来源ID
     */
    private String sourceId;
    /**
     * 文件描述
     */
    private String fileDescribe;
    /**
     * 创建时间戳
     */
    private Long crtTime;
}
