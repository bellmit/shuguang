package com.sofn.web.dgap;

import com.alibaba.fastjson.JSONObject;
//import com.github.nickvl.xspring.core.log.aop.annotation.LogDebug;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.dgap.TDgapDataImportFieldDto;
import com.sofn.model.generator.TDgapDataImportField;
import com.sofn.model.generator.TDgapDataImportTable;
import com.sofn.service.dgap.TDgapDataImportFieldService;
import com.sofn.service.dgap.TDgapDataImportService;
import com.sofn.service.dgap.TDgapDataImportTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** Created by Administrator on 2016/11/23. */
@RestController
@Api(value = "数据导入资源", description = "数据导入资源")
@RequestMapping(value = "/dataImport")
public class TDgapDataImportController extends BaseController {
  @Autowired private TDgapDataImportFieldService tDgapDataImportFieldService;

  @Autowired private TDgapDataImportTableService tDgapDataImportTableService;

  @Autowired private TDgapDataImportService tDgapDataImportService;

  @ApiOperation(value = "数据条件分页列表")
  @RequestMapping(value = "/dataList")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的数据", operationType = "查询")
  @Authorization
  public Object dataList(@RequestBody JSONObject jsonObject) throws SQLException {
    Map<String, Object> map = new HashMap();
    Integer start = jsonObject.getInteger("start");
    Integer length = jsonObject.getInteger("length");
    String status = jsonObject.getString("status");
    String resourceId = String.valueOf(jsonObject.get("resourceId"));
    String conditions = String.valueOf(jsonObject.get("condition"));
    String id = tDgapDataImportTableService.getDataById(resourceId).getId();
    List<TDgapDataImportFieldDto> fieldlist = tDgapDataImportFieldService.list(id);
    List<TDgapDataImportFieldDto> defaultField = getDefaultField();
    for (TDgapDataImportFieldDto defFiled : defaultField) {
      if (!defFiled.getEnglishName().equals("STATUS")) {
        fieldlist.add(defFiled);
      }
    }
    String tableName = tDgapDataImportTableService.selectENName(resourceId);
    PageInfo<Map<String, Object>> pageInfo =
        tDgapDataImportService.dataList(start, length, status, tableName, conditions);
    map.put("columns", fieldlist);
    map.put("dataSource", pageInfo);
    return setSuccessModelMap(new ModelMap(), map);
  }

  @ApiOperation(value = "数据条件分页列表")
  @RequestMapping(value = "/dataLists")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的数据", operationType = "查询")
  @Authorization
  public Object dataLists(Integer start, Integer length, String resourceId,String status,String condition) throws SQLException {
    Map<String, Object> map = new HashMap();
    String tableName = tDgapDataImportTableService.selectENName(resourceId);
    PageInfo<Map<String, Object>> pageInfo =
            tDgapDataImportService.dataList(start, length, status, tableName, condition);
    map.put("dataSource", pageInfo);
    return setSuccessModelMap(new ModelMap(), map);
  }

  @ApiOperation(value = "获得创建表字段")
  @RequestMapping(value = "/getcolumns")
  @SystemControllerLog(description = "(共享系统)获得创建表字段", operationType = "查询")
  @Authorization
  public Object getcolumns( String resourceId) throws SQLException {
    Map<String, Object> map = new HashMap();
    String id = tDgapDataImportTableService.getDataById(resourceId).getId();
    List<TDgapDataImportFieldDto> fieldlist = tDgapDataImportFieldService.list(id);
    List<TDgapDataImportFieldDto> defaultField = getDefaultField();
    for (TDgapDataImportFieldDto defFiled : defaultField) {
      if (!defFiled.getEnglishName().equals("STATUS")) {
        fieldlist.add(defFiled);
      }
    }
    map.put("columns", fieldlist);
    return setSuccessModelMap(new ModelMap(), map);
  }


  @ApiOperation(value = "检查数据是否可以审核")
  @RequestMapping(value = "/checkOrNot")
  @SystemControllerLog(description = "(共享系统)查询导入数据资源的表中是否有数据", operationType = "查询")
  public Object checkOrNot(@RequestBody TDgapDataImportTable tDgapDataImportTable)
      throws SQLException {
    String result = null;
    String tableName =
        tDgapDataImportTableService.selectENName(tDgapDataImportTable.getResourceId());
    if (tableName != null) {
      String tableStatus =
          tDgapDataImportTableService.getDataById(tDgapDataImportTable.getResourceId()).getStatus();
      if (tableStatus.equals("NE")) {
        result = "表未创建";
      } else {
        // 根据资源ID得到表名
        String enName =
            tDgapDataImportTableService.selectENName(tDgapDataImportTable.getResourceId());
        // 根据表名检查表中是否有数据
        Integer selectData = tDgapDataImportTableService.selectData(enName);
        if (selectData.equals(0)) {
          result = "表中没有数据";
        }
      }
    } else {
      result = "表不存在";
    }
    return setSuccessModelMap(new ModelMap(), result);
  }

  @ApiOperation(value = "新增表名")
  @RequestMapping(value = "/dataImportTable/add")
  @SystemControllerLog(description = "(共享系统)给数据导入资源指定数据库表", operationType = "添加")
  public Object addDataImportTable(@RequestBody JSONObject jsonObject) {
    StringBuffer result = null;
    TDgapDataImportTable tDgapDataImportTable =
        (TDgapDataImportTable) JSONObject.toJavaObject(jsonObject, TDgapDataImportTable.class);
    String englishName = jsonObject.getString("englishName");
    String resourceId = jsonObject.getString("resourceId");
    if(englishName.length()>20){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    // 根据资源id查询表名，如果表名为空说明是新增，否则为修改
    String enName = tDgapDataImportTableService.selectENName(resourceId);
    // 查询出不是这条resourceId所对应的其他所有表
    List<String> allTables = tDgapDataImportTableService.getAllTables(resourceId);
    if (enName != null && !allTables.contains(englishName)) {
      TDgapDataImportTable oldImportTable = tDgapDataImportTableService.getDataById(resourceId);
      oldImportTable.setChineseName(tDgapDataImportTable.getChineseName());
      oldImportTable.setEnglishName(tDgapDataImportTable.getEnglishName());
      tDgapDataImportTableService.update(oldImportTable);
      TDgapDataImportTable updateImportTable = tDgapDataImportTableService.getDataById(resourceId);
      if (updateImportTable.equals(oldImportTable)) {
        return setSuccessModelMap(new ModelMap(), "修改失败");
      } else {
        return setSuccessModelMap(new ModelMap(), "修改成功");
      }
    } else if (allTables.contains(englishName)) {
      return setModelMap(new ModelMap(), HttpCode.CONFLICT, "表名重复");
    } else {
      tDgapDataImportTable.setStatus("NE");
      tDgapDataImportTable.setCreateDate(new Date());
      TDgapDataImportTable importTable = tDgapDataImportTableService.add(tDgapDataImportTable);
      if (null == tDgapDataImportTableService.queryById(importTable.getId())) {
        return setSuccessModelMap(new ModelMap(), "保存失败");
      } else return setSuccessModelMap(new ModelMap(), "保存成功");
    }
  }

  @ApiOperation(value = "修改回显")
  @RequestMapping(value = "/dataImportTable/getById")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的表定义信息", operationType = "查询")
  public Object getDataById(@RequestBody JSONObject jsonObject) {
    TDgapDataImportTable tDgapDataImportTable =
        (TDgapDataImportTable) JSONObject.toJavaObject(jsonObject, TDgapDataImportTable.class);
    TDgapDataImportTable table =
        tDgapDataImportTableService.getDataById(tDgapDataImportTable.getResourceId());
    if (null == table) {
      return setSuccessModelMap(new ModelMap(), null);
    } else {
      TDgapDataImportTable queryById =
          tDgapDataImportTableService.getDataById(tDgapDataImportTable.getResourceId());
      return setSuccessModelMap(new ModelMap(), queryById);
    }
  }

  @ApiOperation(value = "字段列表")
  @RequestMapping(value = "/fieldList")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的字段定义信息", operationType = "查询")
  public Object fieldList(@RequestBody JSONObject jsonObject) {
    List<TDgapDataImportFieldDto> list = new ArrayList<TDgapDataImportFieldDto>();
    String resourceId = jsonObject.getString("resourceId");
    TDgapDataImportTable table = tDgapDataImportTableService.getDataById(resourceId);
    if (null == table) {
      return setSuccessModelMap(new ModelMap(), null);
    } else {
      String id = tDgapDataImportTableService.getDataById(resourceId).getId();
      List<TDgapDataImportFieldDto> fieldlist = tDgapDataImportFieldService.list(id);
      List<TDgapDataImportFieldDto> defaultField = getDefaultField();
      for (TDgapDataImportFieldDto defFiled : defaultField) {
        list.add(defFiled);
      }
      for (TDgapDataImportFieldDto field : fieldlist) {
        list.add(field);
      }
      return setModelMap(new ModelMap(), HttpCode.OK, list);
    }
  }

  @ApiOperation(value = "字段列表")
  @RequestMapping(value = "/fieldList2")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的字段定义信息", operationType = "查询")
  @Authorization
  public Object fieldList2(String resourceId,String token) {
    List<TDgapDataImportFieldDto> list = new ArrayList<TDgapDataImportFieldDto>();
    TDgapDataImportTable table = tDgapDataImportTableService.getDataById(resourceId);
    if (null == table) {
      return setSuccessModelMap(new ModelMap(), null);
    } else {
      String id = tDgapDataImportTableService.getDataById(resourceId).getId();
      List<TDgapDataImportFieldDto> fieldlist = tDgapDataImportFieldService.list(id);
      List<TDgapDataImportFieldDto> defaultField = getDefaultField();
      for (TDgapDataImportFieldDto defFiled : defaultField) {
        list.add(defFiled);
      }
      for (TDgapDataImportFieldDto field : fieldlist) {
        list.add(field);
      }
      return setModelMap(new ModelMap(), HttpCode.OK, list);
    }
  }

  @ApiOperation(value = "新增字段名")
  @RequestMapping(value = "/dataImportField/add")
  @SystemControllerLog(description = "(共享系统)给数据导入资源的表添加字段", operationType = "添加")
  public Object addDataImportField(@RequestBody JSONObject jsonObject) {
    // 资源id（resourceId）
    String resourceId = String.valueOf(jsonObject.get("resourceId"));
    TDgapDataImportField tDgapDataImportField =
        (TDgapDataImportField) JSONObject.toJavaObject(jsonObject, TDgapDataImportField.class);
    // 根据资源ID获得改ID对应的表名信息
    TDgapDataImportTable dgapDataImportTable = tDgapDataImportTableService.getDataById(resourceId);
    // 得到对应表的ID
    String tableId = dgapDataImportTable.getId();
    List<String> allEnglishName = tDgapDataImportFieldService.getAllEnglishName(tableId);
    allEnglishName.add("ID");
    allEnglishName.add("OPREATE");
    allEnglishName.add("STATUS");
    allEnglishName.add("DATAID");
    allEnglishName.add("DATA_IMPORT_TIME");
    tDgapDataImportField.setDataImportTableId(tableId);
    tDgapDataImportField.setCreateDate(new Date());
    tDgapDataImportField.setStatus(Constants.DataImportFieldStatus.不存在.code());
    boolean contains = allEnglishName.contains(tDgapDataImportField.getEnglishName());
    boolean length = tDgapDataImportField.getChineseName().length() > 20;
    if (length == true && contains == false) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "中文字段名过长");
    } else if (length == false && contains == true) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "字段名重复");
    } else if (length == true && contains == true) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "字段名重复且中文字段名过长");
    } else {
      TDgapDataImportField importField = tDgapDataImportFieldService.add(tDgapDataImportField);
      TDgapDataImportField queryById = tDgapDataImportFieldService.queryById(importField.getId());
      if (null == queryById) {
        return setSuccessModelMap(new ModelMap(), "保存失败");
      } else return setSuccessModelMap(new ModelMap(), "保存成功");
    }
  }

  @ApiOperation(value = "删除字段名")
  @RequestMapping(value = "/dataImportField/delete")
  @SystemControllerLog(description = "(共享系统)删除数据导入资源的表字段", operationType = "删除")
  public Object deleteDataImportField(@RequestBody JSONObject jsonObject) throws SQLException {
    String id = jsonObject.getString("id");
    String resourceId = jsonObject.getString("resourceId");
    TDgapDataImportField tDgapDataImportField =
        (TDgapDataImportField) JSONObject.toJavaObject(jsonObject, TDgapDataImportField.class);
    TDgapDataImportField queryById = tDgapDataImportFieldService.queryById(id);
    if (queryById != null) {
      queryById.setDelFlag("Y");
      tDgapDataImportFieldService.update(queryById);
      return setSuccessModelMap(new ModelMap(), "删除成功");
    } else {
      return setSuccessModelMap(new ModelMap(), "删除失败");
    }
  }


  @ApiOperation(value = "删除字段名")
  @RequestMapping(value = "/dataImportField/delete2")
  @SystemControllerLog(description = "(共享系统)删除数据导入资源的表字段", operationType = "删除")
  @Authorization
  public Object deleteDataImportField2(String id,String resourceId,String token){
    TDgapDataImportField queryById = tDgapDataImportFieldService.queryById(id);
    if (queryById != null) {
      queryById.setDelFlag("Y");
      tDgapDataImportFieldService.update(queryById);
      return setSuccessModelMap(new ModelMap(), "删除成功");
    } else {
      return setSuccessModelMap(new ModelMap(), "删除失败");
    }
  }

  @ApiOperation(value = "修改字段名")
  @RequestMapping(value = "/dataImportField/update")
  @SystemControllerLog(description = "(共享系统)修改数据导入资源的表字段", operationType = "修改")
  public Object updateDataImportField(@RequestBody JSONObject jsonObject) throws SQLException {
    TDgapDataImportField tDgapDataImportField =
        (TDgapDataImportField) JSONObject.toJavaObject(jsonObject, TDgapDataImportField.class);
    TDgapDataImportField dataImportField =
        tDgapDataImportFieldService.queryById(tDgapDataImportField.getId());
    String tableId = tDgapDataImportField.getId();
    List<String> allEnglishName = tDgapDataImportFieldService.getAllEnglishName(tableId);
    allEnglishName.add("ID");
    allEnglishName.add("OPREATE");
    allEnglishName.add("STATUS");
    boolean contains = allEnglishName.contains(tDgapDataImportField.getEnglishName());
    boolean length = tDgapDataImportField.getChineseName().length() > 20;
    if (length == true && contains == false) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "中文字段名过长");
    } else if (length == false && contains == true) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "字段名重复");
    } else if (length == true && contains == true) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "字段名重复且中文字段名过长");
    } else {
      dataImportField.setLen(tDgapDataImportField.getLen());
      dataImportField.setType(tDgapDataImportField.getType());
      dataImportField.setEnglishName(tDgapDataImportField.getEnglishName());
      dataImportField.setChineseName(tDgapDataImportField.getChineseName());
      tDgapDataImportFieldService.update(dataImportField);
      if (tDgapDataImportFieldService
          .queryById(tDgapDataImportField.getId())
          .equals(dataImportField)) {
        return setSuccessModelMap(new ModelMap(), "修改失败");
      } else return setSuccessModelMap(new ModelMap(), "修改成功");
    }
  }

  @ApiOperation(value = "新建表")
  @RequestMapping(value = "/createTable")
  @SystemControllerLog(description = "(共享系统)生成数据导入资源的表", operationType = "添加")
  public Object createTable(@RequestBody JSONObject jsonObject) {
    String id = String.valueOf(jsonObject.get("resourceId"));
    String tableId = tDgapDataImportTableService.getDataById(id).getId();
    List<TDgapDataImportField> allField = tDgapDataImportFieldService.getAllField(tableId);
    tDgapDataImportService.createTable(id, allField);
    return setSuccessModelMap(new ModelMap(), "新建成功");
  }

  @ApiOperation(value = "删除表")
  @RequestMapping(value = "/deleteTable")
  @SystemControllerLog(description = "(共享系统)删除数据导入资源的表", operationType = "删除")
  public Object deleteTable(@RequestBody JSONObject jsonObject) throws SQLException {
    String id = String.valueOf(jsonObject.get("resourceId"));

    // 根据资源ID得到表名
    String enName = tDgapDataImportTableService.selectENName(id);
    // 根据表名检查表中是否有数据
    Integer selectData = 0;
    try {
      selectData = tDgapDataImportTableService.selectData(enName);
    }catch(Exception e){
      logger.error("error when query table,tableName:"+enName);
    }
    if (selectData != 0) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED, "该表已被使用不能删除");
    } else {
      String tableId = tDgapDataImportTableService.getDataById(id).getId();
      List<TDgapDataImportField> allField = tDgapDataImportFieldService.getAllField(tableId);
      tDgapDataImportService.deleteTable(id, allField);
      return setSuccessModelMap(new ModelMap(), "删除成功");
    }
  }

  @ApiOperation(value = "审核状态")
  @RequestMapping(value = "/checkDataY")
  @SystemControllerLog(description = "(共享系统)对数据导入资源的数据进行审核操作", operationType = "修改")
  public Object checkDataY(@RequestBody JSONObject jsonObject) throws SQLException, DataImportApplyExeption {
    String resourceId = jsonObject.getString("resourceId");
    String dataIds = jsonObject.getString("dataId");
    String checkStatus = jsonObject.getString("status");
    String[] ids = dataIds.split(",");
    String enName = tDgapDataImportTableService.selectENName(resourceId);
    for (String dataId : ids) {
        tDgapDataImportService.updateDataStatus(enName, Constants.DataImportStatus.人工审核通过.code(), dataId);
    }
    return setModelMap(new ModelMap(), HttpCode.OK);
  }

  @ApiOperation(value = "审核状态")
  @RequestMapping(value = "/checkDataN")
  @SystemControllerLog(description = "(共享系统)对数据导入资源的数据进行审核操作", operationType = "修改")
  public Object checkDataN(@RequestBody JSONObject jsonObject) throws SQLException, DataImportApplyExeption {
    String resourceId = jsonObject.getString("resourceId");
    String dataIds = jsonObject.getString("dataId");
    String checkStatus = jsonObject.getString("status");
    String[] ids = dataIds.split(",");
    String enName = tDgapDataImportTableService.selectENName(resourceId);
    for (String dataId : ids) {
      tDgapDataImportService.updateDataStatus(enName, Constants.DataImportStatus.人工审核不通过.code(), dataId);
    }
    return setModelMap(new ModelMap(), HttpCode.OK);
  }

  @ApiOperation(value = "获取字段类型")
  @RequestMapping(value = "/fieldType")
  @SystemControllerLog(description = "(共享系统)获取表字段的支持的类型", operationType = "查询")
  public Object getFieldType() {
    List list = new ArrayList();
    Constants.FieldType[] fieldTypes = Constants.FieldType.values();
    for (int i = 0; i < fieldTypes.length; i++) {
      Map map = fieldTypes[i].getMpValues();
      map.put("name", fieldTypes[i]);
      list.add(map);
    }
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 得到中文名的长度
   *
   * @param s
   * @return
   */
  private int getCount(String s) {
    int length = 0;
    for (int i = 0; i < s.length(); i++) {
      int ascil = Character.codePointAt(s, i);
      if (ascil >= 0 && ascil <= 255) length++;
      else length += 3;
    }
    return length;
  }

  /** 添加表名的时候，添加每张表的默认字段 */
  private List<TDgapDataImportFieldDto> getDefaultField() {
    List<TDgapDataImportFieldDto> list = new ArrayList<>();
    TDgapDataImportFieldDto tDgapDataImportField1 = new TDgapDataImportFieldDto();
    tDgapDataImportField1.setChineseName("表ID");
    tDgapDataImportField1.setEnglishName("ID");
    tDgapDataImportField1.setStatusText("默认字段");
    tDgapDataImportField1.setLen("64");
    tDgapDataImportField1.setType("varchar2");
    tDgapDataImportField1.setTypeName("文本");
    tDgapDataImportField1.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField1);
    TDgapDataImportFieldDto tDgapDataImportField = new TDgapDataImportFieldDto();
    tDgapDataImportField.setChineseName("状态");
    tDgapDataImportField.setStatusText("默认字段");
    tDgapDataImportField.setEnglishName("STATUS");
    tDgapDataImportField.setLen("10");
    tDgapDataImportField.setType("varchar2");
    tDgapDataImportField.setTypeName("文本");
    tDgapDataImportField.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField);
    TDgapDataImportFieldDto tDgapDataImportField3 = new TDgapDataImportFieldDto();
    tDgapDataImportField3.setChineseName("操作");
    tDgapDataImportField3.setEnglishName("OPREATE");
    tDgapDataImportField3.setStatusText("默认字段");
    tDgapDataImportField3.setLen("10");
    tDgapDataImportField3.setType("varchar2");
    tDgapDataImportField3.setTypeName("文本");
    tDgapDataImportField3.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField3);
    TDgapDataImportFieldDto tDgapDataImportField4 = new TDgapDataImportFieldDto();
    tDgapDataImportField4.setChineseName("数据ID");
    tDgapDataImportField4.setEnglishName("DATAID");
    tDgapDataImportField4.setStatusText("默认字段");
    tDgapDataImportField4.setLen("64");
    tDgapDataImportField4.setType("varchar2");
    tDgapDataImportField4.setTypeName("文本");
    tDgapDataImportField4.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField4);
    TDgapDataImportFieldDto tDgapDataImportField5 = new TDgapDataImportFieldDto();
    tDgapDataImportField5.setChineseName("访问令牌");
    tDgapDataImportField5.setEnglishName("TOKEN");
    tDgapDataImportField5.setStatusText("默认字段");
    tDgapDataImportField5.setLen("10");
    tDgapDataImportField5.setType("varchar2");
    tDgapDataImportField5.setTypeName("文本");
    tDgapDataImportField5.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField5);
    TDgapDataImportFieldDto tDgapDataImportField6 = new TDgapDataImportFieldDto();
    tDgapDataImportField6.setChineseName("数据导入时间");
    tDgapDataImportField6.setEnglishName("DATA_IMPORT_TIME");
    tDgapDataImportField6.setStatusText("默认字段");
    tDgapDataImportField6.setType("timestamp");
    tDgapDataImportField6.setTypeName("时间戳");
    tDgapDataImportField6.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField6);
    TDgapDataImportFieldDto tDgapDataImportField7 = new TDgapDataImportFieldDto();
    tDgapDataImportField7.setChineseName("序列号");
    tDgapDataImportField7.setEnglishName("MARK");
    tDgapDataImportField7.setStatusText("默认字段");
    tDgapDataImportField7.setLen("64");
    tDgapDataImportField7.setType("varchar2");
    tDgapDataImportField7.setTypeName("文本");
    tDgapDataImportField7.setStatus(Constants.DataImportFieldStatus.默认.code());
    list.add(tDgapDataImportField7);
    return list;
  }

  @ApiOperation(value = "修改回显字段信息")
  @RequestMapping(value = "/dataImportField/getFiledById")
  @SystemControllerLog(description = "(共享系统)修改获取字段信息", operationType = "查询")
  public Object getFiledById(@RequestBody JSONObject jsonObject)  {
    String id = jsonObject.getString("id");
    TDgapDataImportField queryById = tDgapDataImportFieldService.queryById(id);
    return setSuccessModelMap(new ModelMap(),queryById);
  }

  @ApiOperation(value = "数据条件分页列表")
  @RequestMapping(value = "/getDataById")
  @SystemControllerLog(description = "(共享系统)查询数据导入资源的数据", operationType = "查询")
  @Authorization
  public Object getDataById(String resourceId,String id) throws SQLException {
    String tableName = tDgapDataImportTableService.selectENName(resourceId);
    Map<String, Object> dataMap =
            tDgapDataImportService.getDataById(tableName, id);
    String tableId = tDgapDataImportTableService.getDataById(resourceId).getId();
    List<TDgapDataImportFieldDto> fieldlist = tDgapDataImportFieldService.list(tableId);
    List<TDgapDataImportFieldDto> defaultField = getDefaultField();
    for (TDgapDataImportFieldDto defFiled : defaultField) {
      if (!defFiled.getEnglishName().equals("STATUS")) {
        fieldlist.add(defFiled);
      }
    }
    RowData rowData= new RowData();
    List<FieldData> fields = new ArrayList<FieldData>();
    for(TDgapDataImportFieldDto dtoFiled:fieldlist){
      String englishName=dtoFiled.getEnglishName().toString();
      FieldData field = new FieldData();
      if(dataMap.containsKey(englishName) ){
        field.setColumnName(dtoFiled.getChineseName());
        if(dataMap.get(englishName)!=null){
          field.setValue(dataMap.get(englishName)!=null?dataMap.get(englishName).toString():"");
        }
      }
      fields.add(field);
      rowData.setFieldsData(fields);
      }
    return setSuccessModelMap(new ModelMap(), rowData);
  }
}
