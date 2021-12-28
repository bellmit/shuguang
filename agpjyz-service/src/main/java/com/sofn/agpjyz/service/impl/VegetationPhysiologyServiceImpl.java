package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.VegetationPhysiologyMapper;
import com.sofn.agpjyz.model.VegetationPhysiology;
import com.sofn.agpjyz.service.VegetationPhysiologyService;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.VegetationPhysiologyVo;
import com.sofn.agpjyz.vo.exportBean.ExportVpBean;
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
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-02 16:37
 */
@Service("vegetationPhysiologyService")
public class VegetationPhysiologyServiceImpl implements VegetationPhysiologyService {

    @Autowired
    private VegetationPhysiologyMapper vegetationPhysiologyMapper;

    @Override
    public int insertVegetationPhysiology(VegetationPhysiologyVo VegetationPhysiologyVo) {
        VegetationPhysiology hal=new VegetationPhysiology();
        BeanUtils.copyProperties(VegetationPhysiologyVo,hal);
        hal.setId(IdUtil.getUUId());
        String inputer = "操作人";
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        hal.setInputer(inputer);
        hal.setInputerTime(new Date());
        return vegetationPhysiologyMapper.insertVegetationPhysiology(hal);
    }

    @Override
    public int delVegetationPhysiology(String id) {
        return vegetationPhysiologyMapper.delVegetationPhysiology(id);
    }

    @Override
    public int updateVegetationPhysiology(VegetationPhysiology VegetationPhysiology) {
        return vegetationPhysiologyMapper.updateVegetationPhysiology(VegetationPhysiology);
    }

    @Override
    public PageUtils<VegetationPhysiology> getVegetationPhysiology(Map map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<VegetationPhysiology> VegetationPhysiologyList = vegetationPhysiologyMapper.getVegetationPhysiology(map);
        PageInfo<VegetationPhysiology> pageInfo = new PageInfo<>(VegetationPhysiologyList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public VegetationPhysiology get(String id) {
        return vegetationPhysiologyMapper.get(id);
    }

    /**
     * 导出
     */
    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<VegetationPhysiology> tsList = vegetationPhysiologyMapper.getVegetationPhysiology(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<ExportVpBean> exportList = new ArrayList<>(tsList.size());
            tsList.forEach(o -> {
                ExportVpBean etb = new ExportVpBean();
                BeanUtils.copyProperties(o, etb);
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportVpBean.class, exportList, response, "植被生理参数监测信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
