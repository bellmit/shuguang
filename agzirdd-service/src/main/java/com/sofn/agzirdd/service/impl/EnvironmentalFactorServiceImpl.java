package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.excelmodel.EnvironmentalFactorExcel;
import com.sofn.agzirdd.mapper.EnvironmentalFactorMapper;
import com.sofn.agzirdd.model.EnvironmentalFactor;
import com.sofn.agzirdd.service.EnvironmentalFactorService;
import com.sofn.agzirdd.util.SetValueUtil;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class EnvironmentalFactorServiceImpl extends ServiceImpl<EnvironmentalFactorMapper, EnvironmentalFactor> implements EnvironmentalFactorService {

    @Autowired
    private EnvironmentalFactorMapper environmentalFactorMapper;
    @Resource
    private SetValueUtil setValueUtil;

    @Override
    public PageUtils<EnvironmentalFactor> getEnvironmentalFactorListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<EnvironmentalFactor> environmentalFactorList = environmentalFactorMapper.getEnvironmentalFactorByCondition(params);
        PageInfo<EnvironmentalFactor> pageInfo = new PageInfo<>(environmentalFactorList);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<EnvironmentalFactor> getEnvironmentalFactorListByQuery(Map<String, Object> params) {
        return environmentalFactorMapper.getEnvironmentalFactorByCondition(params);
    }

    @Override
    public List<EnvironmentalFactorExcel> getEnvironmentalFactorListToExport(Map<String, Object> params) {

        List<EnvironmentalFactor> environmentalFactorByCondition = environmentalFactorMapper.getEnvironmentalFactorByCondition(params);
        List<EnvironmentalFactorExcel> environmentalFactorExcelList = new ArrayList<>();
        environmentalFactorByCondition.forEach(
                baseData ->{
                    EnvironmentalFactorExcel environmentalFactorExcel = new EnvironmentalFactorExcel();
                    BeanUtils.copyProperties(baseData,environmentalFactorExcel);
                    environmentalFactorExcelList.add(environmentalFactorExcel);
                }
        );
        return environmentalFactorExcelList;
    }

    @Override
    public EnvironmentalFactor getEnvironmentalFactorById(String id) {

        return environmentalFactorMapper.getEnvironmentalFactorById(id);
    }

    @Override
    public void updateStatus(Map<String, Object> params) {
        environmentalFactorMapper.updateStatus(params);
    }

    @Override
    public void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {

        User loginUser = UserUtil.getLoginUser();
        Calendar calendar = Calendar.getInstance();
        String belongYear = calendar.get(Calendar.YEAR) + "";
        //放入当前年份
        environmentalFactor.setBelongYear(belongYear);
        //放入创建人id,创建人名称,创建时间
        environmentalFactor.setCreateUserId(loginUser.getId());
        environmentalFactor.setCreateUserName(loginUser.getNickname());
        environmentalFactor.setCreateTime(new Date());
        environmentalFactor.setId(IdUtil.getUUId());
        if (environmentalFactor.getCountyId() != null) {
            setValueUtil.setNameById(environmentalFactor);
        }
        this.save(environmentalFactor);
    }

    @Override
    public void updateEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
        //更新前处理省市区
        setValueUtil.setNameById(environmentalFactor);
        this.updateById(environmentalFactor);
    }

    @Override
    public void removeEnvironmentalFactor(String id) {
        this.removeById(id);
    }
}
