package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.Popup;

import java.util.Map;

public interface PopupMapper extends BaseMapper<Popup> {

    Popup selectPopupByAreaIdAndYear(Map<String, Object> params);

}