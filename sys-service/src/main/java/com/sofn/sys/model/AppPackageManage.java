package com.sofn.sys.model;

import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * 应用安装包管理
 * @author heyongjie
 * @date 2019/11/15 14:29
 */
@Data
public class AppPackageManage extends BaseModel<AppPackageManage> {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 平台
     */
    private String platform;

    /**
     * 版本号，由上传者自定义
     */
    private String version;

    /**
     * 整数版本号，只有android系统才会进行控制，数据=intVersion+1
     */
    private int intVersion;

    /**
     * 版本状态 请看AppPackageManageStateEnum
     */
    private String state;

    /**
     * 更新描述
     */
    private String updateDescription;

    /**
     * 文件管理Id
     */
    private String sysFileManageId;


}
