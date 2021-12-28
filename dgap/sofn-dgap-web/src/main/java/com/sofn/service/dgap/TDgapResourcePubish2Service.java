package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.Assert;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
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
public class TDgapResourcePubish2Service {

  @Value("${pre.ws.baseurl}")
  String baseUrl;



  @DubboReference(timeout = 10*5*1000)
  private TDgapDw3Provider tDgapDw3Provider;


  @DubboReference
  private TDgapResourceProvider tDgapResourceProvider;

  @DubboReference
  private TDgapTbResourceConfigProvider tDgapTbResourceConfigProvider;

  public Tree getvalidTables(String tablePartten) {
    Tree tree = new Tree("0", "");
    List<TDgapDataImportTable> list = tDgapDw3Provider.getValidWdTables(tablePartten);

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
    String upStr=tableId.toUpperCase();
    for (TDgapDataImportField field : tDgapDw3Provider.getFieldsByTable(upStr)) {
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

  public PageInfo<Map<String, Object>> getDwDatas(
      String tableId, Integer pageSize, Integer pageNum, String condition) {
    Integer start = pageSize * (pageNum - 1);
    Integer end = pageSize * pageNum;
    return tDgapDw3Provider.getDatas(tableId, start, end, condition);
  }

  public void publishResource(
      TDgapResource resource,
      String conditionFields,
      String publishFields,
      String tableId,
      String userId) {
     resource.setType(Constants.ResourceType.仓库资源.code());
    resource.setCreateTime(new Date());
    resource.setCreateBy(userId);
//    resource.setWsdlUrl(baseUrl + "/ws/dataExport?wsdl");
    resource.setDelFlag("N");
    if(StringUtils.isBlank(resource.getDataNotifier())){
      resource.setDataNotifier("none");
    }
    resource = tDgapResourceProvider.update(resource);
    String[] fields = publishFields.split(",");
    List<TDgapDataImportField> list = tDgapDw3Provider.getByFieldNames(tableId, fields);
    TDgapDataImportTable table = tDgapDw3Provider.getByTableName(tableId);

    if (conditionFields == null) {
      conditionFields = "";
    }
    List<String> conditonList = Arrays.asList(conditionFields.split(","));
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
      tDgapTbResourceConfig.setTableName(table.getEnglishName());

      String tableCnName = table.getChineseName();
      if (tableCnName != null && tableCnName.length() > 64){
        tableCnName = tableCnName.substring(0,64);
      }
      tDgapTbResourceConfig.setTableCnName(tableCnName);

      String fieldCnName = field.getChineseName();
      if (fieldCnName != null && fieldCnName.length() > 64){
        fieldCnName = fieldCnName.substring(0,64);
      }
      tDgapTbResourceConfig.setFieldCnName(fieldCnName);
      tDgapTbResourceConfig.setFieldName(field.getEnglishName());
      tDgapTbResourceConfig.setLength(BigDecimal.valueOf(Integer.parseInt(field.getLen())));
      tDgapTbResourceConfig.setType(field.getType());
      tDgapTbResourceConfig.setResourceId(resource.getId());
      tDgapTbResourceConfigProvider.update(tDgapTbResourceConfig);
    }
  }

  public List<Map<String, Object>> getPublishedFiledsByResourceId(String resourceId) {

    List<TDgapTbResourceConfig> list = tDgapTbResourceConfigProvider.getByResourceId(resourceId);
    List<Map<String, Object>> filedList = new ArrayList<>();
    for (TDgapTbResourceConfig field : list) {
      Map<String, Object> map = new HashedMap();
      if(field.getFieldCnName()==null||field.getFieldCnName().equals("")){
        map.put("fieldCnName", "");
      }else{
        map.put("fieldCnName", field.getFieldCnName().toUpperCase());
      }
      map.put("fieldName", field.getFieldName().toUpperCase());
      map.put("type", field.getType().toUpperCase());
      map.put("length",field.getLength());
      map.put("isCondition", field.getIsCondition());
      filedList.add(map);
    }
    return filedList;
  }

  public List<Map<String, Object>> getFiledsByTableId(String tableId) {
    List<TDgapDataImportField> list = tDgapDw3Provider.getFieldsByTable(tableId);
    List<Map<String, Object>> filedList = new ArrayList<>();
    for (TDgapDataImportField field : list) {
      Map<String, Object> map = new HashedMap();
      if(field.getChineseName()==null||field.getChineseName().equals("")){
        map.put("title", "");
      }else{
        map.put("title", field.getChineseName().toUpperCase());
      }
      map.put("dataIndex", field.getEnglishName().toUpperCase());
      map.put("type", field.getType().toUpperCase());
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

  /**
   * 获取所有表及其字段
   * @return
   */
  public Map<String, List<Map<String, Object>>> getAllTables(Map<String, Object> params) {
    List<TDgapDataImportTable> list = tDgapDw3Provider.getTablesforPages(params);
    Map<String, List<Map<String, Object>>> tableMap= new HashMap<String, List<Map<String, Object>>>();
    for (TDgapDataImportTable table : list) {
      String tableId=table.getId();
      Assert.isNotBlank(tableId, "tableId");
      List<Map<String, Object>> fieldList = new ArrayList<>();
      for (TDgapDataImportField field : tDgapDw3Provider.getFieldsByTable(tableId)) {
        Map<String, Object> map = beanToMap(field);
        for (Constants.FieldType fieldType : Constants.FieldType.values()) {
          if (field.getType().equals(fieldType.type())) {
            map.put("cnFileType", fieldType.name());
          }
        }
        fieldList.add(map);
      }
      tableMap.put(table.getEnglishName() + "-" + table.getChineseName(),fieldList);
    }
    return  tableMap;
  }

  /**
   * 获取所有表的数量
   * @return
   */
  public long getAllTablesCount() {
    return tDgapDw3Provider.getAllTablesCount();
  }
}
