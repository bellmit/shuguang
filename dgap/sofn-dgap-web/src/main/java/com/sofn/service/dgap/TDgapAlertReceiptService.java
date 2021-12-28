package com.sofn.service.dgap;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TDgapAlertReceipt;
import com.sofn.provider.dgap.TDgapAlertReceiptProvider;
import java.util.List;
import org.springframework.stereotype.Service;

/** Created by weiqiang on 2016/10/26. */
@Service
public class TDgapAlertReceiptService
    extends BaseService<TDgapAlertReceiptProvider, TDgapAlertReceipt> {
  @DubboReference
  private void setProvider(TDgapAlertReceiptProvider provider) {
    this.provider = provider;
  }

  public List<TDgapAlertReceipt> list() {
    return provider.receipts();
  }
}
