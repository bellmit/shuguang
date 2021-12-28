package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.PhysiologicalParametersExcel;
import com.sofn.agzirdd.mapper.PhysiologicalParametersMapper;
import com.sofn.agzirdd.model.PhysiologicalParameters;
import com.sofn.agzirdd.service.PhysiologicalParametersService;
import com.sofn.agzirdd.util.SetValueUtil;
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
public class PhysiologicalParametersServiceImpl extends ServiceImpl<PhysiologicalParametersMapper, PhysiologicalParameters> implements PhysiologicalParametersService {


    @Autowired
    private PhysiologicalParametersMapper physiologicalParametersMapper;

    @Resource
    private SetValueUtil setValueUtil;

    @Override
    public PageUtils<PhysiologicalParameters> getPhysiologicalParametersListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<PhysiologicalParameters> physiologicalParametersList = physiologicalParametersMapper.getPhysiologicalParametersByCondition(params);
        PageInfo<PhysiologicalParameters> pageInfo = new PageInfo<>(physiologicalParametersList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<PhysiologicalParameters> getPhysiologicalParametersListByQuery(Map<String, Object> params) {
        return physiologicalParametersMapper.getPhysiologicalParametersByCondition(params);
    }

    @Override
    public List<PhysiologicalParametersExcel> getPhysiologicalParametersListToExport(Map<String, Object> params) {
        List<PhysiologicalParameters> physiologicalParametersByCondition = physiologicalParametersMapper.getPhysiologicalParametersByCondition(params);
        List<PhysiologicalParametersExcel> physiologicalParametersExcelList = new ArrayList<>();
        physiologicalParametersByCondition.forEach(
                baseData ->{
                    PhysiologicalParametersExcel physiologicalParametersExcel = new PhysiologicalParametersExcel();
                    BeanUtils.copyProperties(baseData,physiologicalParametersExcel);
                    String description = StatusEnum.getDescriptionByCode(baseData.getStatus());
                    physiologicalParametersExcel.setStatus(description);
                    physiologicalParametersExcelList.add(physiologicalParametersExcel);
                }
        );
        return physiologicalParametersExcelList;
    }

    @Override
    public PhysiologicalParameters getPhysiologicalParametersById(String id) {

        return physiologicalParametersMapper.getPhysiologicalParametersById(id);
    }

    @Override
    public void updateStatus(Map<String, Object> params) {
        physiologicalParametersMapper.updateStatus(params);
    }

    @Override
    public void addPhysiologicalParameters(PhysiologicalParameters physiologicalParameters) {
        User loginUser = UserUtil.getLoginUser();
        Calendar calendar = Calendar.getInstance();
        String belongYear = calendar.get(Calendar.YEAR) + "";
        //放入当前年份
        physiologicalParameters.setBelongYear(belongYear);
        //放入创建人id,创建人名称.创建时间
        physiologicalParameters.setCreateUserId(loginUser.getId());
        physiologicalParameters.setCreateUserName(loginUser.getNickname());
        physiologicalParameters.setCreateTime(new Date());
        physiologicalParameters.setId(IdUtil.getUUId());
        if (physiologicalParameters.getCountyId() != null) {
            setValueUtil.setNameById(physiologicalParameters);
        }
        this.save(physiologicalParameters);
    }

    @Override
    public void updatePhysiologicalParameters(PhysiologicalParameters physiologicalParameters) {
        setValueUtil.setNameById(physiologicalParameters);
        this.updateById(physiologicalParameters);
    }

    @Override
    public void removePhysiologicalParameters(String id) {
        this.removeById(id);
    }
}
