package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.exception.dgap.DataImportApplyExeption;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapResource;
import com.sofn.provider.dgap.DataExportProvider;
import com.sofn.provider.dgap.DataImportApplyProvider;
import com.sofn.provider.dgap.DataImportProvider;
import com.sofn.provider.dgap.TDgapResourceProvider;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/** Created by Administrator on 2016/10/13. */
@Service
public class TDgapResourceService extends BaseService<TDgapResourceProvider, TDgapResource> {
  @DubboReference
  public void setProvider(TDgapResourceProvider provider) {
    this.provider = provider;
  }
  @Value("${datafile.import.basedir}")
  @NotNull
  String basedir = "/tmp";
  private final static String TOKEN = "csv-import";
  @Value("${dataImport.csv.maxrows}")
  long MAXLENGTH;

  @Value("${dataImport.applydataRealtime.enable}")
  String applydataRealtime = "false";

  @DubboReference(timeout = 100*5*1000)
  private DataImportProvider dataImportProvider;

  @DubboReference(timeout = 100*5*1000)
  private DataImportApplyProvider dataImportApplyProvider;

  @DubboReference(timeout = 100*5*1000)
  private DataExportProvider dataExportProvider;


  /**
   * 分页查询
   *
   * @param
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Map<String, Object>> list(
      String resourcename, String type, String directoryId, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    params.put("type", type);
    params.put("directoryId", directoryId);
    params.put("resourcename", resourcename);
    return provider.list(params);
  }

  /**
   *
   * <!--通过资源名字级联资源目录表查询-->
   *
   * @return
   */
  public List<TDgapResource> getResourceNameList(String resouorcename) {

    return provider.getResourceNameList(resouorcename);
  }

  public TDgapResource getResource(String resourceId) {
    return this.provider.getDgapResource(resourceId);
  }

  public List<TDgapResource> selectAll() {
    return provider.getRes();
  }
  /**
   * 资源管理根据ID的修改数据回显
   *
   * @param reID
   * @return
   */
  public List<Map> getResoueceData(String reID) {
    return provider.getResourceData(reID);
  }

  public void batchDelete(String[] ids) {
    provider.batchDelete(ids);
  }

  public List<TDgapResource> getResourceByDirId(String id) {
    List<TDgapResource> resourceByDirId = provider.getResourceByDirId(id);
    return resourceByDirId;
  }

  public TDgapResource getResourceDelFlagByResourceId(String resourceId) {
    return provider.getResourceDelFlagByResourceId(resourceId);
  }

  public List<TDgapResource> getResourceByName(String resourceName) {
    return provider.getResourceByName(resourceName);
  }

  /**
   * 查出所有的未新建转换的资源
   *
   * @return
   */
  public List<TDgapResource> getResourceList() {
    return provider.getResourceList();
  }

  /**
   * 查询所有的文件路径
   * @return
   */
    public List<String> getAllPath() {
      return provider.getAllPath();
    }

  /**
   * 查询除了当前资源的所有文件路径
   * @param id
   * @return
   */
    public List<String> getAllPathsById(String id){
       return provider.getAllPathsById(id);
    };

    public ModelMap importCsvData(MultipartFile  file, String resourceId, HttpServletRequest request){
      ModelMap modelMap = new ModelMap();
      try {
        InputStream ins=file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        FileOpStatus impResults = importCsvFile(resourceId, reader);
        modelMap.addAttribute("importMsg","导入成功");
      } catch (IOException e) {
        e.printStackTrace();
      }

      return modelMap;
    }

    @NotNull FileOpStatus importCsvFile(@NotNull String resourceId,@NotNull BufferedReader file) throws IOException {

      //fetch 1st line
      List<RowData> data = readCsvFile(file);

      DataImportResult result = insertBatch(TOKEN,resourceId, data);

      return getPrintOpStatus(FileOpStatus.OP.IMPORT,result.isSuccess(),resourceId, null,null,null);
    }

    static @NotNull FileOpStatus getPrintOpStatus(FileOpStatus.OP op, boolean success,
                                                  @NotNull String resourceId,
                                                  String srcPath, String destPath, String errorMsg){
      FileOpStatus opStatus = new FileOpStatus(op, success, resourceId, srcPath, destPath, errorMsg);
      return opStatus;
    }

    @NotNull List<RowData> readCsvFile(@NotNull BufferedReader file) throws IOException {
      CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(file);
      Map<String, Integer> headers = parser.getHeaderMap();
      List<RowData> rows = new ArrayList<RowData>();

      for (CSVRecord record : parser) {
        if(parser.getRecordNumber()>(MAXLENGTH-1)){
          throw new RuntimeException("csv file too large, exceeds limit of record count of:"+MAXLENGTH);
        }
        RowData row = new RowData();
        List<FieldData> fields = new ArrayList<FieldData>();
        for(String header:headers.keySet()){
          if("ID".equals(header)){
            row.setId(record.get(header));
          }else {
            FieldData field = new FieldData();
            field.setColumnName(header);
            field.setValue(record.get(header));
            fields.add(field);
          }
        }
        row.setFieldsData(fields);
        rows.add(row);
      }
      return rows;
    }

    private String getFileZoneDir(@NotNull String subDirName) {
      return basedir+ File.separator+subDirName;
    }

  public ModelMap fixAppliedData(String id) {
    ModelMap modelMap = new ModelMap();
    String log=dataExportProvider.fixAppliedData(id);
    return modelMap.addAttribute("fixlog",log);
  }

  public static class FileOpStatus {
      enum OP {
        COPY,DELETE_SOURCE_FILE,IMPORT,DELETE_IMPORT_FILE
      }
      OP op;
      String resourceId;
      String srcPath;
      String destPath;

      @Override
      public String toString() {
        return "FileOpStatus{" +
                "op=" + op +
                ", resourceId='" + resourceId + '\'' +
                ", srcPath='" + srcPath + '\'' +
                ", destPath='" + destPath + '\'' +
                ", success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
      }

      boolean success;

      public FileOpStatus(OP op,boolean success,String resourceId, String srcPath, String destPath,  String errorMsg) {
        this.op = op;
        this.resourceId = resourceId;
        this.srcPath = srcPath;
        this.destPath = destPath;
        this.success = success;
        this.errorMsg = errorMsg;
      }

      String errorMsg;
    }

  public DataImportResult insertBatch(String token,String resourceId, List<RowData> data){
    DataImportResult addResult = dataImportProvider.addBatch(token,resourceId, data);
    if("true".equals(applydataRealtime)){
      try {
        addResult = dataImportApplyProvider.applyDataBatchAdd(token,resourceId, data);
      } catch (DataImportApplyExeption dataImportApplyExeption) {
        dataImportApplyExeption.printStackTrace();
        addResult.setSuccess(false);
      }

    }
    return addResult;
  }
}
