package com.sofn.service.dgap;

import com.sofn.core.support.Assert;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.Tree;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapTbResourceConfig;
import com.sofn.provider.dgap.*;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.*;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * author tianjie
 *
 * @date 2016/11/24
 */
@Service
public class TDgapResourcePubishService {

  @Value("${pre.ws.baseurl}")
  String baseUrl;

  @DubboReference private TDgapDwProvider tDgapDwProvider;

  @DubboReference private TDgapResourceProvider tDgapResourceProvider;

  @DubboReference private TDgapTbResourceConfigProvider tDgapTbResourceConfigProvider;

  @DubboReference private TDgapDataImportFieldProvider tDgapDataImportFieldProvider;

  @DubboReference private TDgapDataImportTableProvider tDgapDataImportTableProvider;

  public Tree getvalidTables(String tablePartten) {
    Tree tree = new Tree("0", "");
    List<TDgapDataImportTable> list = tDgapDwProvider.getValidWdTables(tablePartten);
    Tree rootNode = new Tree("0", "仓库表");
    for (TDgapDataImportTable table : list) {
      Tree tree1 = new Tree(table.getId(), table.getEnglishName() + "-" + table.getChineseName());
      rootNode.addChild(tree1);
    }

    return rootNode;
  }

  public List<Map<String, Object>> getFields(String tableId) {
    Assert.isNotBlank(tableId, "tableId");
    List<Map<String, Object>> list = new ArrayList<>();
    for (TDgapDataImportField field : tDgapDwProvider.getFieldsByTable(tableId)) {
      Map<String, Object> map = beanToMap(field);
      for (Constants.FieldType fieldType : Constants.FieldType.values()) {
        if (field.getType().equals(fieldType.type())) {
          map.put("cnFileType", fieldType.name());
        }
      }
      list.add(map);
    }
    return list;
  }

  public List<Map<String, Object>> getDwDatas(
      String tableId, Integer pageSize, Integer pageNum, String condition) {
    Integer start = pageSize * (pageNum - 1);
    Integer end = pageSize * pageNum;
    return tDgapDwProvider.getDatas(tableId, start, end, condition);
  }

  public void publishResource(
      TDgapResource resource, String conditionFields, String publishFields, String userId) {
    resource.setType(Constants.ResourceType.仓库资源.code());
    resource.setCreateTime(new Date());
    resource.setCreateBy(userId);
    resource.setWsdlUrl(baseUrl + "/ws/dataExport?wsdl");
    resource.setDelFlag("N");
    resource = tDgapResourceProvider.update(resource);
    String[] fields = publishFields.split(",");
    List<TDgapDataImportField> list = tDgapDataImportFieldProvider.getByFieldNames(fields);
    TDgapDataImportTable dataById = new TDgapDataImportTable();
    for (TDgapDataImportField field : list) {
      String tableId = field.getDataImportTableId();
      dataById = tDgapDataImportTableProvider.getImportDataById(tableId);
      break;
    }
    List<String> conditonList = Arrays.asList(conditionFields.split(","));
    if (conditionFields == null) {
      conditionFields = "";
    }
    String[] conditionFields1 = conditionFields.split(",");
    for (TDgapDataImportField field : list) {
      TDgapTbResourceConfig tDgapTbResourceConfig = new TDgapTbResourceConfig();
      tDgapTbResourceConfig.setDelFlag("N");
      tDgapTbResourceConfig.setUpdateTime(new Date());
      tDgapTbResourceConfig.setCreateBy(userId);
      tDgapTbResourceConfig.setUpdateBy(userId);
      tDgapTbResourceConfig.setCreateTime(new Date());
      if (conditonList.contains(field.getEnglishName())) {
        tDgapTbResourceConfig.setIsCondition("Y");
      }
      tDgapTbResourceConfig.setCreateDate(new Date());
      tDgapTbResourceConfig.setTableName(dataById.getEnglishName());
      tDgapTbResourceConfig.setTableCnName(dataById.getChineseName());
      tDgapTbResourceConfig.setFieldCnName(field.getChineseName());
      tDgapTbResourceConfig.setFieldName(field.getEnglishName());
      tDgapTbResourceConfig.setLength(BigDecimal.valueOf(Integer.parseInt(field.getLen())));
      tDgapTbResourceConfig.setType(field.getType());
      tDgapTbResourceConfig.setResourceId(resource.getId());
      tDgapTbResourceConfigProvider.update(tDgapTbResourceConfig);
    }
  }

  public List<Map<String, Object>> getFiledsByTableId(String tableId) {

    List<TDgapDataImportField> list = tDgapDwProvider.getFieldsByTable(tableId);
    List<Map<String, Object>> filedList = new ArrayList<>();
    for (TDgapDataImportField field : list) {
      Map<String, Object> map = new HashedMap();
      map.put("title", field.getChineseName().toUpperCase());
      map.put("dataIndex", field.getEnglishName().toUpperCase());
      filedList.add(map);
    }
    return filedList;
  }

  /**
   * 将javabean实体类转为map类型，然后返回一个map类型的值
   * @param obj
   * @return
   */
  public static Map<String, Object> beanToMap(Object obj) {
    Map<String, Object> params = new HashMap<String, Object>(0);
    try {
      PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
      PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
      for (int i = 0; i < descriptors.length; i++) {
        String name = descriptors[i].getName();
        if (!"class".equals(name)) {
          params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return params;
  }
}
