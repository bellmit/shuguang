package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.EnvironmentalFactorCollectMapper;
import com.sofn.agpjyz.model.EnvironmentalFactor;
import com.sofn.agpjyz.service.EnvironmentalFactorCollectService;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.exportBean.ExportEfBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.xiaoleilu.hutool.date.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 环境因子监测信息采集模块服务类
 **/
@Service(value = "environmentalFactorCollectService")
public class EnvironmentalFactorCollectServiceImpl implements EnvironmentalFactorCollectService {

    @Autowired
    private EnvironmentalFactorCollectMapper efcMapper;

    @Override
    public EnvironmentalFactor save(EnvironmentalFactor entity) {
        entity.setId(IdUtil.getUUId());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        entity.setInputerTime(new Date());
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputer(inputer);
        efcMapper.insert(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        efcMapper.deleteById(id);
    }

    @Override
    public void update(EnvironmentalFactor entity) {
        String id = entity.getId();
        if (Objects.isNull(efcMapper.selectById(id))) {
            throw new SofnException("待修改数据不存在");
        }
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputerTime(new DateTime());
        entity.setInputer(inputer);
        efcMapper.updateById(entity);
    }

    @Override
    public EnvironmentalFactor get(String id) {
        return efcMapper.selectById(id);
    }

    @Override
    public PageUtils<EnvironmentalFactor> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<EnvironmentalFactor> tsList = efcMapper.listByParams(params);
        return PageUtils.getPageUtils(new PageInfo<>(tsList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<EnvironmentalFactor> tfList = efcMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(tfList)) {
            List<ExportEfBean> exportList = new ArrayList<>(tfList.size());
            tfList.forEach(o -> {
                ExportEfBean etb = new ExportEfBean();
                BeanUtils.copyProperties(o, etb);
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportEfBean.class, exportList, response, "环境因子监测信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }
}
