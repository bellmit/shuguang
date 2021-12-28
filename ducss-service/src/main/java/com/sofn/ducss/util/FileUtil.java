package com.sofn.ducss.util;

import com.sofn.common.model.Result;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.sysapi.SysFileApi;
import com.sofn.ducss.sysapi.bean.SysFileManageForm;
import com.sofn.ducss.sysapi.bean.SysFileVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 *
 */
public class FileUtil {

    private static SysFileApi sysFileApi = SpringContextHolder.getBean(SysFileApi.class);

    public static Boolean activationFiles(String fileIds) {
        if (StringUtils.isEmpty(fileIds)) {
            return false;
        }
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        // 系统Id
        sysFileManageForm.setSystemId(Constants.APPID);
        // 文件激活Id
        sysFileManageForm.setIds(fileIds);
        Result<List<SysFileVo>> fileResult = sysFileApi.activationFile(sysFileManageForm, UserUtil.getLoginToken());
        return (fileResult != null && CollectionUtils.isNotEmpty(fileResult.getData()));
    }
}
