package com.sofn.fdpi.util;

import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.vo.SysFileVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FileUtil {

    @Resource
    private SysRegionApi sysRegionApi;

    /**
     * 激活系统文件
     */
    public void activationFile(String ids) {
        if (StringUtils.hasText(ids)) {
            SysFileManageForm sfmf = new SysFileManageForm();
            sfmf.setInterfaceNum("hidden");
            sfmf.setIds(ids);
//            激活id数至少是1
            int fileNumber = 1;
//            获取激活的id数量
            if (ids.contains(",")) {
                fileNumber = ids.split(",").length;
            }
            sfmf.setSystemId(Constants.SYSTEM_ID);
            try {
                Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
//                如果激活的id数不等于支撑平台已返回激活的文件数
                if (fileNumber != result.getData().size()) {
                    throw new SofnException("激活系统文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("激活系统文件失败!请检查文件是否上传成功");
            }
        }
    }

    public void replaceFile(String oldFileId, String newFileId) {
        sysRegionApi.replaceFile(oldFileId, "", newFileId);
    }

    public void batchDeleteFile(String ids) {
        if (StringUtils.hasText(ids)) {
            sysRegionApi.batchDeleteFile(ids);
        }
    }
}
