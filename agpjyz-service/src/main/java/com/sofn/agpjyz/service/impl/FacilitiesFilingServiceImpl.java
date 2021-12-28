package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.FacilitiesFilingMapper;
import com.sofn.agpjyz.model.FacilitiesFiling;
import com.sofn.agpjyz.service.FacilitiesFilingService;
import com.sofn.agpjyz.sysapi.JzbApi;
import com.sofn.agpjyz.util.ApiUtil;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.exportBean.ExportFfBean;
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
 * 基础设施备案登记服务类
 *
 * @Author yumao
 * @Date 2020/3/10 8:53
 **/
@Service(value = "facilitiesFilingService")
public class FacilitiesFilingServiceImpl implements FacilitiesFilingService {


    @Autowired
    private FacilitiesFilingMapper ffMapper;
    @Autowired
    private JzbApi jzbApi;

    @Override
    public FacilitiesFiling save(FacilitiesFiling entity) {
        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        entity.setInputerTime(new Date());
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputer(inputer);
        ffMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        ffMapper.deleteById(id);
    }

    @Override
    public void update(FacilitiesFiling entity) {
        if (Objects.isNull(ffMapper.selectById(entity.getId()))) {
            throw new SofnException("待修改数据不存在");
        }
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputerTime(new Date());
        entity.setInputer(inputer);
        ffMapper.updateById(entity);
    }

    @Override
    public FacilitiesFiling get(String id) {
        return ffMapper.selectById(id);
    }

    @Override
    public PageUtils<FacilitiesFiling> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, String> protectPointsMap = ApiUtil.getResultMap(jzbApi.listForProtectPoints(null));
        List<FacilitiesFiling> ffList = ffMapper.listByParams(params);
        for (FacilitiesFiling facilitiesFiling : ffList) {
            facilitiesFiling.setProtectValue(protectPointsMap.get(facilitiesFiling.getProtectId()));
        }
        return PageUtils.getPageUtils(new PageInfo<>(ffList));
    }

    @Override
    public List<FacilitiesFiling> list(Map<String, Object> params) {
        return ffMapper.listByParams(params);
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<FacilitiesFiling> ffList = ffMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(ffList)) {
            List<ExportFfBean> exportList = new ArrayList<>(ffList.size());
            Map<String, String> map = ApiUtil.getResultMap(jzbApi.listForProtectPoints(null));
            ffList.forEach(o -> {
                ExportFfBean efb = new ExportFfBean();
                BeanUtils.copyProperties(o, efb);
                efb.setProtectValue(map.get(o.getProtectId()));
                exportList.add(efb);
            });
            try {
                ExportUtil.createExcel(ExportFfBean.class, exportList, response, "基础设施备案登记.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
