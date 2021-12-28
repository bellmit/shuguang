package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
public interface TDgapResourceProvider extends BaseProvider<TDgapResource> {

    PageInfo<Map<String, Object>> list(Map<String, Object> params);
/*<!--通过资源名字级联资源目录表查询-->*/
    List<TDgapResource> getResourceNameList(String name);
    public TDgapResource getDgapResource(String resourceId);

    List<TDgapResource> getRes();
    /* 资源管理根据ID的修改数据回显*/
    List<Map> getResourceData(String id);
    /*批量删除资源管理*/
    public void batchDelete(String[] ids);
    public List<TDgapResource> getResourceByDirId(String id);

    public  TDgapResource getResourceDelFlagByResourceId(String resourceId);

    public List<TDgapResource> getResourceByName(String resourceName);

    /**
     * 查出所有未新建转换的资源
     * @return
     */
    List<TDgapResource> getResourceList();

    List<TDgapResource> getResourceByFilePath(String filePath);

    List<TDgapResource> getResourceByType(String typeCode);
    //查询所有的文件路径
    List<String> getAllPath();
    //查询不包含本资源所有的文件路径
    List<String> getAllPathsById(String resourceId);
}
