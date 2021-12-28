package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.ducss.mapper.PopupMapper;
import com.sofn.ducss.model.Popup;
import com.sofn.ducss.service.PopupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PopupServiceImpl extends ServiceImpl<PopupMapper,Popup> implements PopupService {
    @Autowired
    private PopupMapper popupMapper;

    @Override
    public Popup selectPopupByAreaIdAndYear(String areaId,String year) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("areaId", areaId);
        params.put("year", year);
        Popup popup = popupMapper.selectPopupByAreaIdAndYear(params);
        return popup;
    }

}
