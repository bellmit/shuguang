package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceDirectory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20.
 */
public interface TDgapResourceDirectoryProvider extends BaseProvider<TDgapResourceDirectory> {
    PageInfo<Map<String,Object>> list(Map<String, Object> params);

    List<TDgapResourceDirectory> getTypes(String name);
    List<TDgapResourceDirectory> getType(String name);

    int addResourceDir(TDgapResourceDirectory resDir);

    List<Map<String,Object>> list();

    public List<TDgapResourceDirectory> getAllDirs();

    /*  资源目录名表单验证*/
    List<TDgapResourceDirectory> getByDirName(String dirName);
    List<TDgapResourceDirectory> getByDirNameY(String dirName);

}
