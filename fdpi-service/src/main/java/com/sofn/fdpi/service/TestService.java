package com.sofn.fdpi.service;

import org.springframework.web.multipart.MultipartFile;


public interface TestService {

    void checkData(MultipartFile multipartFile, String id);
}
