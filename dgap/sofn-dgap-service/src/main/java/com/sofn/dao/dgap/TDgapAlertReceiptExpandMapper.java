package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapAlertReceipt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** Created by weiqiang on 2016/10/26. */
@MyBatisDao
public interface TDgapAlertReceiptExpandMapper extends BaseExpandMapper {
  List<TDgapAlertReceipt> getReceipts();

  List<TDgapAlertReceipt> selectReceipts(String id);

  List checkUser(String alertId);

  TDgapAlertReceipt getbyReceipt(@Param(value = "alertId") String alertId,@Param(value = "receiptBy") String receiptBy);
}
