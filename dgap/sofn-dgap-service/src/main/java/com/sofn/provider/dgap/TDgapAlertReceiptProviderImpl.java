package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapAlertReceiptExpandMapper;
import com.sofn.model.generator.TDgapAlertReceipt;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by weiqiang on 2016/10/26. */
@DubboService(interfaceClass = TDgapAlertReceiptProvider.class)
public class TDgapAlertReceiptProviderImpl extends BaseProviderImpl<TDgapAlertReceipt>
    implements TDgapAlertReceiptProvider {
  @Autowired private TDgapAlertReceiptExpandMapper receiptExpandMapper;

  @Override
  public List<TDgapAlertReceipt> receipts() {
    return receiptExpandMapper.getReceipts();
  }
}
