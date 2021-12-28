package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysSubSystemBaseDataOwn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础数据分配给子系统的数据mapper
 */
public interface SysSubSystemBaseDataOwnMapper extends BaseMapper<SysSubSystemBaseDataOwn> {

    /**
     * 根据基础数据ID集合批量删除分配的基础数据
     * @param baseDataIds 基础数据ID集合
     */
    void deleteByBaseDataIds(@Param("baseDataIds") List<String> baseDataIds);

    /**
     * 批量新增
     * @param sysSubSystemBaseDataOwns   需要存储的关系
     */
    void batchSave(@Param("sysSubSystemBaseDataOwns") List<SysSubSystemBaseDataOwn> sysSubSystemBaseDataOwns);

}
