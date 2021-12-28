package com.sofn.provider.dgap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Constant;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.dao.dgap.TDgapAlertConfigExpandMapper;
import com.sofn.dao.dgap.TDgapAlertReceiptExpandMapper;
import com.sofn.dao.generator.TDgapAlertConfigMapper;
import com.sofn.dao.generator.TDgapAlertReceiptMapper;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapAlertReceipt;
import com.sofn.model.generator.TDgapCommon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.sun.tools.classfile.ConstantPool;
import org.springframework.beans.factory.annotation.Autowired;

/** Created by weiqiang on 2016/11/3. */
@DubboService(interfaceClass = TDgapCommomProvider.class)
public class TDgapCommonProviderImpl extends BaseProviderImpl<TDgapAlertConfig>
    implements TDgapCommomProvider {
  @Autowired private TDgapAlertConfigMapper configMapper;
  @Autowired private TDgapAlertReceiptMapper receiptMapper;
  @Autowired private TDgapAlertReceiptExpandMapper receiptExpandMapper;
  @Autowired private TDgapAlertConfigExpandMapper alertConfigMapper;

  @Override
  public TDgapCommon add(String json, String token) {
    JSONObject jsonObject = JSONArray.parseObject(json);
    TDgapCommon common = jsonObject.getObject("common", TDgapCommon.class);

    String uid = WebUtil.getCurrentUserId(token);
    if (StringUtils.isBlank(common.getDelFlag())) {
      common.setDelFlag("N");
    }
    if (StringUtils.isBlank(common.getCreateBy())) {
      common.setCreateBy(uid == null ? "" : uid);
    }
    if (StringUtils.isBlank(common.getUpdateBy())) {
      common.setUpdateBy(uid == null ? "" : uid);
    } else if (StringUtils.isNotBlank(uid)) {
      common.setUpdateBy(uid);
    }

    TDgapAlertConfig tDgapAlertConfig = new TDgapAlertConfig();
    TDgapAlertReceipt alertReceipt = new TDgapAlertReceipt();
    try {
      if (StringUtils.isBlank(common.getId())) {
        String key = common.getClass().getSimpleName();
        common.setId(createId(key));
        common.setCreateTime(new Date());
        tDgapAlertConfig.setAlertType(common.getAlertType());
        tDgapAlertConfig.setDescription(common.getDescription());
        tDgapAlertConfig.setThreshold(common.getThreshold());
        tDgapAlertConfig.setDelFlag(common.getDelFlag());
        tDgapAlertConfig.setId(common.getId());
        tDgapAlertConfig.setCreateTime(common.getCreateTime());
        tDgapAlertConfig.setTargetId(common.getTargetId());
        tDgapAlertConfig.setUpdateTime(new Date());
        tDgapAlertConfig.setCreateBy(common.getCreateBy());
        tDgapAlertConfig.setUpdateBy(common.getUpdateBy());
        configMapper.insert(tDgapAlertConfig);

        alertReceipt.setAlertId(tDgapAlertConfig.getId());
        JSONArray user = (JSONArray) jsonObject.get("user");
        for (int i = 0; i < user.size(); i++) {
          alertReceipt.setReceiptBy(user.get(i).toString());
          alertReceipt.setCreateTime(new Date());
          alertReceipt.setUpdateTime(new Date());
          alertReceipt.setDelFlag(common.getDelFlag());
          alertReceipt.setCreateBy(common.getCreateBy());
          alertReceipt.setUpdateBy(common.getUpdateBy());
          String key1 = alertReceipt.getClass().getSimpleName();
          alertReceipt.setId(createId(key1));
          receiptMapper.insert(alertReceipt);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return common;
  }

  @Override
  public TDgapCommon update(String json, String token) {
    JSONObject jsonObject = JSONArray.parseObject(json);
    TDgapCommon common = jsonObject.getObject("common", TDgapCommon.class);
    String uid = WebUtil.getCurrentUserId(token);
    if (StringUtils.isBlank(common.getUpdateBy())) {
      common.setUpdateBy(uid == null ? "" : uid);
    } else if (StringUtils.isNotBlank(uid)) {
      common.setUpdateBy(uid);
    }
    if (StringUtils.isBlank(common.getCreateBy())) {
      common.setCreateBy(uid == null ? "" : uid);
    } else if (StringUtils.isNotBlank(uid)) {
      common.setCreateBy(uid);
    }
    TDgapAlertConfig config = configMapper.selectByPrimaryKey(common.getId());
    List<TDgapAlertReceipt> oldReceipts = receiptExpandMapper.selectReceipts(common.getId());

    TDgapAlertReceipt newReceipt = new TDgapAlertReceipt();
    JSONArray user = (JSONArray) jsonObject.get("user");
    newReceipt.setAlertId(config.getId());
    try {
      config.setAlertType(common.getAlertType());
      config.setDescription(common.getDescription());
      config.setThreshold(common.getThreshold());
      config.setTargetId(common.getTargetId());
      config.setUpdateTime(new Date());
      config.setUpdateBy(common.getUpdateBy());
      configMapper.updateByPrimaryKey(config);
      for (int i = 0; i<  oldReceipts.size() ; i++) {
          TDgapAlertReceipt oldReceipt = oldReceipts.get(i);
          oldReceipt.setUpdateBy(common.getUpdateBy());
          oldReceipt.setUpdateTime(new Date());
          oldReceipt.setDelFlag("Y");
          receiptMapper.updateByPrimaryKey(oldReceipt);
        }
        for (int j = 0; j < user.size(); j++) {
          newReceipt.setReceiptBy(user.get(j).toString());
          newReceipt.setCreateTime(new Date());
          newReceipt.setUpdateTime(new Date());
          newReceipt.setDelFlag(common.getDelFlag());
          newReceipt.setCreateBy(common.getCreateBy());
          newReceipt.setUpdateBy(common.getUpdateBy());
          String key1 = newReceipt.getClass().getSimpleName();
          newReceipt.setId(createId(key1));
          receiptMapper.insert(newReceipt);
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return common;
  }

  @Override
  public List<TDgapCommon> checkRepeat(String targetId,String id) {
    return alertConfigMapper.checkRepeat(targetId,id);
  }

  @Override
  public List checkUser(String alertId) {
    List users = receiptExpandMapper.checkUser(alertId);
    return users;
  }
}
