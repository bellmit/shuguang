package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.service.SysFileManageService;
import com.sofn.sys.vo.SysFileVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * 文件管理接口测试
 *
 * 因很多接口都需要获取当前登录用户，所以只能使用Swagger 或者 Postman测试了
 *
 * @author heyongjie
 * @date 2019/11/25 11:40
 */
@Slf4j
public class SysFileManageServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private SysFileManageService sysFileManageService;

    @Test
    public void deleteFile() {

    }

    @Test
    public void showRecords() {
    }

    @Test
    public void getFileRealPath() {


    }


    @Test
    public void getOne() {
    }

    @Test
    public void batchDelete() {
    }

    @Test
    public void getFileInfoByIds() {
    }


    @Test
    public void replaceFile() {
    }

    @Test
    public void getSysFileByConditionAndPage() {
    }

    @Test
    public void uploadFile() throws Exception{
        MockMultipartFile firstFile = null;
        File file = new File("C:\\Users\\heyongjie\\Desktop\\aaa.png");
        firstFile = new MockMultipartFile("mapUrl", file.getName(), "multipart/form-data", new FileInputStream(file));
        SysFileVo sysFileVo = sysFileManageService.uploadFile(firstFile, UserUtil.getLoginToken());
        log.info("{}",sysFileVo);
    }

    @Test
    public void activationFile() {
    }
}