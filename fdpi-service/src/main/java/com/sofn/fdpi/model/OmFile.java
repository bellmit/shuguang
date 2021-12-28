package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("om_file_info")
public class OmFile {

    /**
     * id
     */
    private String id;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 文件名字
     */
    private String fileName;
    /**
     * 文件用途
     */
    private String fileUse;
    /**
     * 源id
     */
    private String sourceId;

    private String  delFlag;


}
