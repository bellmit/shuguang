package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.Popup;
import com.sofn.ducss.model.ProStill;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface PopupMapper extends BaseMapper<Popup> {

    Popup selectPopupByAreaIdAndYear(Map<String, Object> params);

}