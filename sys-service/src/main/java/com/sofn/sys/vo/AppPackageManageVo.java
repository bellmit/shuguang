package com.sofn.sys.vo;

import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.model.AppPackageManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * AppPackageManageVo
 * @author heyongjie
 * @date 2019/11/18 11:44
 */
@Data
@ApiModel(value="App管理返回格式")
public class AppPackageManageVo extends AppPackageManageForm {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建用户")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String updateTime;

    @ApiModelProperty(value = "下载路径")
    private String downloadPath;

    /**
     * 根据持久层对象做一些翻译操作，下载路径由前台拼接，调用下载文件的接口
     * @param appPackageManage  持久层对象
     * @return  翻译后的VO对象   AppPackageManageVo
     */
    public static AppPackageManageVo getAppPackageManageVo(AppPackageManage appPackageManage){
        AppPackageManageVo appPackageManageVo = new AppPackageManageVo();
        BeanUtils.copyProperties(appPackageManage,appPackageManageVo);
        UserUtil.getUsernameById(appPackageManage.getCreateUserId());
        String updateTime;
        if(appPackageManage.getUpdateTime() == null){
            updateTime =  DateUtils.format(appPackageManage.getCreateTime(),DateUtils.DATE_TIME_PATTERN);
        }else {
            updateTime = DateUtils.format(appPackageManage.getUpdateTime(),DateUtils.DATE_TIME_PATTERN);
        }
        appPackageManageVo.setDownloadPath(appPackageManage.getSysFileManageId());
        appPackageManageVo.setUpdateTime(updateTime);
        appPackageManageVo.setCreateUser(UserUtil.getUsernameById(appPackageManage.getCreateUserId()));
        return appPackageManageVo;
    }

}
