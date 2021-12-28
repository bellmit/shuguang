package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.model.dgap.AetlTransformation;
import com.sofn.model.dgap.Tree;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapTrans;

import java.io.File;
import java.util.List;

/**
 * Created by helong on 17-5-23.
 */
public interface AetlProvider {


    /**
     * 读取模板
     * @param resourceLocation
     * @return
     */
    File readlist(String resourceLocation);



    /**
     * 添加转换
     * @param resourceId
     * @param transformation
     * @return
     */
    boolean addTransformation(String resourceId, String transformation, String transName) throws Exception;

    /**
     * 部署转换
     * @param resourceId
     * @param transName
     * @return
     */
    boolean deployTransFromLocalRepo(String resourceId, String transName);

    /**
     * 转换列表
     * @param transName
     * @param start
     * @param length
     * @param resources
     * @return
     */
    PageInfo<AetlTransformation> listTransformation(String transName, int start, int length, List<TDgapResource> resources);

    /**
     * 卸载转换
     * @param resourceId
     * @param transName
     * @return
     */
    boolean unDeployTrans(String resourceId, String transName);

    /**
     * 删除转换
     * @param resourceId
     * @param transName
     * @return
     */
    boolean removeTransFromRepo(String resourceId, String transName);

    /**
     * 设置调度状态
     * @param resourceId
     * @param enable
     * @return
     */
    boolean setScheduleStatus(String resourceId, String transName, String enable);

    boolean excuteTrans();

    boolean execTrans(String resourceId, String transName,String resourceName);

    boolean batchDeleteTransform(List<String> resourceList);
}
