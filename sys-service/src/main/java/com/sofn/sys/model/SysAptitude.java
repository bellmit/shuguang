package com.sofn.sys.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 组织机构资质证书关系
 * @author heyongjie
 * @date 2019/11/22 14:37
 */
@TableName("SYS_APTITUDE")
@Data
public class SysAptitude extends BaseModel {

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 资质类型,参考字典类型-zzzs
     */
    private String calificacionType;

}
