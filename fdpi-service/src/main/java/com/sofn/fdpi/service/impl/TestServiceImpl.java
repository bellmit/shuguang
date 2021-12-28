package com.sofn.fdpi.service.impl;

import com.google.common.collect.Lists;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.service.SignboardService;
import com.sofn.fdpi.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private SignboardService signboardService;

    @Override
    public void checkData(MultipartFile multipartFile, String id) {
        List<String> printList = this.getPrintList(multipartFile, id);
        List<String> signboards = signboardService.listByPrintId(id).stream().map(Signboard::getCode).collect(Collectors.toList());
        signboards.removeAll(printList);
        signboards = signboards.stream().sorted().collect(Collectors.toList());
        for (String str : signboards) {
            System.out.println(str + "\t");
        }
    }

    private List<String> getPrintList(MultipartFile multipartFile, String id) {
        List<String> codes = this.getPrintListWithExcelTxt(multipartFile, id);
        Integer printSize = codes.size();
        List<String> printList = Lists.newArrayListWithCapacity(printSize);
        for (String code : codes) {
            printList.add(code.trim());
        }
        return printList;
    }

    private List<String> getPrintListWithExcelTxt(MultipartFile multipartFile, String id) {
        List<String> codes = Lists.newArrayList();
        // 获取txt 编码列表
        try {
            byte[] byteArr = multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            byte[] getData = this.readInputStream(inputStream);
            String str = new String(getData).trim();
            if (str.contains("/r/n")) {
                codes = Arrays.asList(str.split("/r/n"));
            } else if (str.contains("\r\n")) {
                codes = Arrays.asList(str.split("\r\n"));
            } else if (str.contains("\t\n")) {
                codes = Arrays.asList(str.split("\t\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codes;
    }

    public byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
