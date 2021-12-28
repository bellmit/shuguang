package com.sofn.provider.dgap;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapAlertReceipt;

import java.util.List;

/**
 * Created by weiqiang on 2016/10/26.
 */
public interface TDgapAlertReceiptProvider extends BaseProvider<TDgapAlertReceipt> {
    List<TDgapAlertReceipt> receipts();
}
