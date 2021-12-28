package com.sofn.dhhrp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.dhhrp.mapper.IndexParMapper;
import com.sofn.dhhrp.model.IndexPar;
import com.sofn.dhhrp.service.IndexParService;
import com.sofn.dhhrp.vo.DropDownVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.dhhrp.service.IndexParService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:08
 */
@Service("IndexParService")
public class IndexParServiceImpl implements IndexParService {
    @Autowired
     private IndexParMapper indexParMapper;
    @Override
    public void save(IndexPar indexpar) {
        Map map =new HashMap<>();
        map.put("value",indexpar.getValue());
        IndexPar exist = indexParMapper.exist(map);
        if (exist!=null){
            throw  new SofnException("当前指标参数已存在");
        }
        indexpar.setId(IdUtil.getUUId());
        indexParMapper.insert(indexpar);
    }

    @Override
    public IndexPar get(String id) {
        return indexParMapper.selectById(id);
    }

    @Override
    public void update(IndexPar indexpar) {
        Map map =new HashMap<>();
        map.put("value",indexpar.getValue());
        map.put("id",indexpar.getId());
        IndexPar repeat = indexParMapper.repeat(map);
        if (repeat!=null){
            throw  new SofnException("当前指标参数已存在");
        }
        indexParMapper.updateById(indexpar);
    }

    @Override
    public void delete(String id) {
        indexParMapper.deleteById(id);
    }

    @Override
    public PageUtils<IndexPar> listPage(Map<String, Object> params,Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<IndexPar> indexPars = indexParMapper.listPage(params);
        return PageUtils.getPageUtils(new PageInfo(indexPars));
    }

    @Override
    public List<DropDownVo> listName() {
        List<DropDownVo> dropDownVos = indexParMapper.listName();
        if(CollectionUtils.isEmpty(dropDownVos)){
            dropDownVos.add(new DropDownVo());
        }
        return dropDownVos;
    }
}
