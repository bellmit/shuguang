package com.sofn.agpjyz.util;

import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.vo.SysFileManageForm;
import com.sofn.agpjyz.vo.SysFileVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class FileUtil {

    @Autowired
    private SysFileApi sysFileApi;

    /**
     * 激活系统文件
     */
    public void activationFile(String ids) {
        if (StringUtils.hasText(ids)) {
            SysFileManageForm sfmf = new SysFileManageForm();
            sfmf.setInterfaceNum("hidden");
            sfmf.setIds(ids);
//            激活id数至少是1
            int fileNumber=1;
//            获取激活的id数量
            if (ids.contains(",")){
                fileNumber = ids.split(",").length;
            }
            sfmf.setSystemId(Constants.SYSTEM_ID);
            try {
                Result<List<SysFileVo>> result = sysFileApi.activationFile(sfmf);
//                如果激活的id数不等于支撑平台已返回激活的文件数
                if (fileNumber!=result.getData().size()) {
                    throw new SofnException("激活系统文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("激活系统文件失败!请检查文件是否上传成功");
            }
        }
    }
}
