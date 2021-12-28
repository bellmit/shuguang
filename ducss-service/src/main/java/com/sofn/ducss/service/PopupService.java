package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.Popup;
import com.sofn.ducss.model.ProStill;

public interface PopupService extends IService<Popup> {

    Popup selectPopupByAreaIdAndYear(String areaId,String year);

}
