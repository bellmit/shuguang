package com.sofn.ducss.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysDictType;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysDictTypeAndValueVo;

import java.util.List;
import java.util.Map;

public interface SysDictTypeService extends IService<SysDictType> {

    public List<SysDictType> getDictTypeByName(String typename,String typevalue);

    public List<SysDictType> getDictTypeByName1(String typename,String typevalue);

    public void saveDictType(SysDictType sysDictType);

    public void delDictType(String id);

    public void updateDictType(String id,String typename,String typevalue);

    public List<SysDictType> getDictTypeById(String dicttypeid);

    void saveSubSystemDict(String subsystemid, String dictids);

    List<SysDictType> getDictBySubSystem(String subsystemid);

    List<SysDictType> getDictParentBySubSystem(String subsystemid);

    List<SysDictType> getDictCurNodeBySubSystem(String subsystemid);

    /**
     * 按条件分页查询字典类型
     * @param params   查询参数
     * @param pageNo   第几页
     * @param pageSize   每页显示多少条
     * @return  PageUtils<SysDictTypeAndValueVo>
     */
    PageUtils<List<SysDictTypeAndValueVo>> getDictTypeAndValueByPage(Map<String,Object> params, Integer pageNo, Integer pageSize);
}
