package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.AetlTransformation;
import com.sofn.model.generator.TDgapResource;
import com.sofn.provider.dgap.AetlProvider;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Created by weiqiang on 2016/11/3. */
@Service
public class TDgapEtlService {
  @DubboReference private AetlProvider provider;

  public PageInfo<AetlTransformation> listTrans(
      String transName, int start, int length, List<TDgapResource> resources) {
    return provider.listTransformation(transName, start, length, resources);
  }

  /**
   * 增加etl转换流程
   *
   * @param resourceId
   * @param transformation
   * @return
   */
  public boolean addTrans(String resourceId, String transformation, String transName) throws Exception {
    return provider.addTransformation(resourceId, transformation, transName);
  }

  public File readlist(String resourceLocation){
    return provider.readlist(resourceLocation);
  };



  /**
   * 删除etl转换流程
   *
   * @return
   */
  public boolean deleteTrans(String resourceId, String transName) {
    return provider.removeTransFromRepo(resourceId, transName);
  }

  /**
   * 部署一个etl转换任务
   *
   * @param resourceId
   * @param transName
   * @return
   */
  public boolean deployTrans(String resourceId, String transName) {
    return provider.deployTransFromLocalRepo(resourceId, transName);
  }

  /**
   * 卸载一个etl转换任务
   *
   * @param resourceId
   * @param transName
   * @return
   */
  public boolean unDeployTrans(String resourceId, String transName) {
    return provider.unDeployTrans(resourceId, transName);
  }

  public boolean setScheduleStatus(String resourceId, String transName, String enable) {
    return provider.setScheduleStatus(resourceId, transName, enable);
  }

  public boolean excuteTrans() {
    return provider.excuteTrans();
  }


    public boolean execTrans(String resourceId, String transName,String resourceName) {
        return provider.execTrans(resourceId,transName,resourceName);
    }

  @Transactional
  public boolean batchDeleteTransform(String[] resourceIdAndTransNameArr) {
    for (String info:resourceIdAndTransNameArr){
      String[] resourceIdAndName = info.split(",");
      boolean currFlag = deleteTrans(resourceIdAndName[0], resourceIdAndName[1]);
      if(!currFlag){
        throw new RuntimeException(resourceIdAndName[0] + "," + resourceIdAndName[1]+" 删除失败...");
      }
    }
    return true;
  }
}
