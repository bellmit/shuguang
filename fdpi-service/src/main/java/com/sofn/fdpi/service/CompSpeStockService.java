package com.sofn.fdpi.service;

import com.sofn.fdpi.model.CompSpeStock;

public interface CompSpeStockService {

    /**
     * 根据物种ID和企业ID 物种库存
     */
    CompSpeStock getBySpeIdAndCompId(String speId, String compId);

    int delByCompId(String compId);
}
