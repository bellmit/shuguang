package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.SysDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictionaryMapper extends BaseMapper<SysDictionary> {

    List<SysDictionary> getAllSysDictionaries(String dictType);

    /***
     * 传入dictKey 排序后返回
     * @param list
     * @return
     */
    List<String> getDictKeyList(@Param("list")List<String> list);

    List<String> getDictKeyListBy(@Param("list")List<String> list);

    SysDictionary getDictionariesByKey(@Param("dictType")String dictType, @Param("dictKey")String dictKey);

    Integer updateValueByTypeKey(@Param("type") String type,@Param("key")  String key,@Param("value")  String value);


    List<SysDictionary> getSysDictionaryListByKey(@Param("dictType")String dictType, @Param("dictKey")String dictKey);

    String getDictValue(@Param("dictKey")String dictKey);
}
