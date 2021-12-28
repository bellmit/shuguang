package com.sofn.ducss.sysapi;

import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SysFileManageService;
import com.sofn.ducss.model.SysFileManageForm;
import com.sofn.ducss.vo.SysFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 使用Feign 调用sys服务
 *
 * @author simon
 */
/*@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)*/
@Component
public class SysFileApi {

    @Autowired
    private SysFileManageService sysFileManageService;


    /**
     * 上传文件
     *
     * @return Result
     */
    public SysFileVo uploadFile(MultipartFile file, String token) {
        SysFileVo sysFileVo = sysFileManageService.uploadFile(file, token);
        return sysFileVo;
    }

    /**
     * 激活文件
     */
    public void activationFile(SysFileManageForm sysFileManageForm, String token) {
        List<SysFileVo> sysFileVos = sysFileManageService.activationFile(sysFileManageForm, token);
    }
    /**
     * 删除文件
     *
     * @param id
     * @return
     */
/*    @PostMapping("/fileManage/deleteFile")
    Result<String> delFile(@ApiParam("要删除的文件ID") @RequestParam("id") String id);

    @GetMapping("/fileManage/getOne/{id}")
    Result<SysFileManageVo> getOne(@PathVariable("id") String id);*/
}
