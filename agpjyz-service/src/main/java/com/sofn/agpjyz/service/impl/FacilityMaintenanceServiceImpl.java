package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.FacilityMaintenanceMapper;
import com.sofn.agpjyz.model.FacilityMaintenance;
import com.sofn.agpjyz.service.FacilityMaintenanceService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.ApiUtil;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.exportBean.ExportFmBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 基础设施维护情况备案登记服务类
 *
 * @Author yumao
 * @Date 2020/3/12 9:22
 **/
@Service(value = "facilityMaintenanceService")
public class FacilityMaintenanceServiceImpl implements FacilityMaintenanceService {

    @Autowired
    private FacilityMaintenanceMapper fmMapper;
    @Autowired
    private JzbApi jzbApi;

    @Override
    public FacilityMaintenance save(FacilityMaintenance entity) {
        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        entity.setRepairTime(new Date());
        String repairMan = "维护人";
        if (!Objects.isNull(user)) {
            repairMan = user.getNickname();
        }
        entity.setRepairMan(repairMan);
        fmMapper.insert(entity);
        return entity;
    }


    @Override
    public void delete(String id) {
        fmMapper.deleteById(id);
    }

    @Override
    public void update(FacilityMaintenance entity) {
        if (Objects.isNull(fmMapper.selectById(entity.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        User user = UserUtil.getLoginUser();
        String repairMan = "维护人";
        if (!Objects.isNull(user)) {
            repairMan = user.getNickname();
        }
        entity.setRepairTime(new Date());
        entity.setRepairMan(repairMan);
        fmMapper.updateById(entity);
    }

    @Override
    public FacilityMaintenance get(String id) {
        return fmMapper.selectById(id);
    }

    @Override
    public PageUtils<FacilityMaintenance> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<FacilityMaintenance> fmList = fmMapper.listByParams(params);
        Map<String, String> protectPointsMap = ApiUtil.getResultMap(jzbApi.listForProtectPoints(null));
        for (FacilityMaintenance facilityMaintenance : fmList) {
            facilityMaintenance.setProtectValue(protectPointsMap.get(facilityMaintenance.getProtectId()));
        }
        return PageUtils.getPageUtils(new PageInfo<>(fmList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<FacilityMaintenance> fmList = fmMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(fmList)) {
            List<ExportFmBean> exportList = new ArrayList<>(fmList.size());
            Map<String, String> protectPointsMap = ApiUtil.getResultMap(jzbApi.listForProtectPoints(null));
            fmList.forEach(o -> {
                ExportFmBean efb = new ExportFmBean();
                BeanUtils.copyProperties(o, efb);
                efb.setProtectValue(protectPointsMap.get(o.getProtectId()));
                exportList.add(efb);
            });
            try {
                ExportUtil.createExcel(ExportFmBean.class, exportList, response, "基础设施维护情况备案登记.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}

