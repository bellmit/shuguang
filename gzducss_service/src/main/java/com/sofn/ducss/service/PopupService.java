package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.Popup;

public interface PopupService extends IService<Popup> {

    Popup selectPopupByAreaIdAndYear(String areaId,String year);

}
