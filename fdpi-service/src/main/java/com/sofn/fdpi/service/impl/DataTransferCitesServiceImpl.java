package com.sofn.fdpi.service.impl;

import com.sofn.fdpi.service.CitesForm1Service;
import com.sofn.fdpi.service.DataTransferCitesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("dataTransferCitesService")
public class DataTransferCitesServiceImpl implements DataTransferCitesService {

    @Resource
    private CitesForm1Service citesForm1Service;

    @Override
    public void test() {
        System.out.println(citesForm1Service.listAll());;
    }
}
