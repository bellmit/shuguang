package com.sofn.common.fileutil;

import com.sofn.common.fileutil.dao.SysFileManageDto;

/**
 * 翻译工具
 * @author heyongjie
 * @date 2019/11/29 13:51
 */
public class SysFileUtil {

    /**
     * 获取文件信息备注
     * @param id   id
     * @return  文件信息
     */
    public static SysFileManageVo getSysFileInfo(String id){
        SysFileManageDto sysFileManageDto = new SysFileManageDto();
        return sysFileManageDto.getFileInfo(id);
    }

}
