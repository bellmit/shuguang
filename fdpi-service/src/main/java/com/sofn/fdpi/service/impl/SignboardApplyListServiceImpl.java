package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.mapper.SignboardApplyListMapper;
import com.sofn.fdpi.model.SignboardApplyList;
import com.sofn.fdpi.service.SignboardApplyListService;
import com.sofn.fdpi.vo.SignboardApplyListForm;
import com.sofn.fdpi.vo.SignboardApplyListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author yumao
 * @Date 2020/1/2 17:46
 **/
@Service(value = "signboardApplyListService")
@Slf4j
public class SignboardApplyListServiceImpl extends BaseService<SignboardApplyListMapper, SignboardApplyList> implements SignboardApplyListService {

    @Resource
    private SignboardApplyListMapper signboardApplyListMapper;

    @Override
    public SignboardApplyList insertSignboardApplyList(SignboardApplyListForm signboardApplyListForm) {
        SignboardApplyList sal = new SignboardApplyList();
        BeanUtils.copyProperties(signboardApplyListForm, sal);
        sal.preInsert();
        signboardApplyListMapper.insert(sal);
        return sal;
    }

    @Override
    public List<SignboardApplyListVo> listByApplyId(String applyId) {
        List<SignboardApplyList> sals = signboardApplyListMapper.listByApplyId(applyId);
        if (CollectionUtils.isEmpty(sals)) {
            return null;
        } else {
            List<SignboardApplyListVo> salvs = new ArrayList<>(sals.size());
            for (SignboardApplyList sal : sals) {
                salvs.add(SignboardApplyListVo.entity2Vo(sal));
            }
            return salvs;
        }
    }

    @Override
    public PageUtils<SignboardApplyListVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SignboardApplyList> list = signboardApplyListMapper.listByParams(params);
        PageInfo<SignboardApplyList> saPageInfo = new PageInfo<>(list);
        List<SignboardApplyListVo> listVo = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (SignboardApplyList entity : list) {
                listVo.add(SignboardApplyListVo.entity2Vo(entity));
            }
            PageInfo<SignboardApplyListVo> savPageInfo = new PageInfo<>(listVo);
            savPageInfo.setTotal(saPageInfo.getTotal());
            savPageInfo.setPageSize(pageSize);
            savPageInfo.setPageNum(saPageInfo.getPageNum());
            return PageUtils.getPageUtils(savPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo<>(list));
    }

    @Override
    public void deleteByApplyId(String applyId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("APPLY_ID", applyId);
        signboardApplyListMapper.deleteByMap(map);
    }

    @Override
    public Integer countApplyList(String applyId) {
        QueryWrapper<SignboardApplyList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("apply_id", applyId);
        return signboardApplyListMapper.selectCount(queryWrapper);
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            signboardApplyListMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public void updateDelFlagByPringId(String pringId) {
        signboardApplyListMapper.updateDelFlagByPringId(pringId);
    }

    @Override
    public int delByApplyIds(List<String> applyIds) {
        if (!CollectionUtils.isEmpty(applyIds)) {
            QueryWrapper<SignboardApplyList> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("apply_id", applyIds);
            return signboardApplyListMapper.delete(queryWrapper);
        }
        return 0;
    }

}
