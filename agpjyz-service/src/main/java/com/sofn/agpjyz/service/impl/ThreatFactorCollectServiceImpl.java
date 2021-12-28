package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.ThreatFactorCollectMapper;
import com.sofn.agpjyz.model.ThreatFactor;
import com.sofn.agpjyz.service.ThreatFactorCollectService;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.exportBean.ExportTfBean;
import com.sofn.agpjyz.vo.exportBean.ExportTsBean;
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
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 威胁因素基础信息收集模块服务类
 *
 * @Author yumao
 * @Date 2020/3/4 9:30
 **/
@Service(value = "threatFactorCollectService")
public class ThreatFactorCollectServiceImpl implements ThreatFactorCollectService {

    @Autowired
    private ThreatFactorCollectMapper tfcMapper;

    @Override
    public ThreatFactor save(ThreatFactor entity) {
        entity.setId(IdUtil.getUUId());
        entity.setInputerTime(new DateTime());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputer(inputer);
        this.validRepeat(entity.getProtectId(), "");
        tfcMapper.insert(entity);
        return entity;
    }

    //验证重复
    private void validRepeat(String protectId, String id) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("protectId", protectId);
        if (StringUtils.hasText(id)) {
            params.put("id", id);
        }
        List<ThreatFactor> list = tfcMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(list)) {
            throw new SofnException("已存在相同保护点,不可以新增/修改");
        }
    }

    @Override
    public void delete(String id) {
        tfcMapper.deleteById(id);
    }

    @Override
    public void update(ThreatFactor entity) {
        String id = entity.getId();
        if (Objects.isNull(tfcMapper.selectById(id))) {
            throw new SofnException("待修改数据不存在");
        }
        entity.setInputerTime(new DateTime());
        User user = UserUtil.getLoginUser();
        String inputer = "操作人";
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        entity.setInputer(inputer);
        this.validRepeat(entity.getProtectId(), id);
        tfcMapper.updateById(entity);
        if (entity.getExcavation() == null) {
            tfcMapper.updateExcavation(id);
        }
    }

    @Override
    public ThreatFactor get(String id) {
        ThreatFactor tf = tfcMapper.selectById(id);
        this.handleDouble(tf);
        return tf;
    }

    private void handleDouble(ThreatFactor tf) {
        tf.setExcavation(Double.valueOf(new DecimalFormat("#.00").format(tf.getExcavation())));
    }

    @Override
    public PageUtils<ThreatFactor> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ThreatFactor> tsList = tfcMapper.listByParams(params);
        tsList.forEach(o -> {
            this.handleDouble(o);
        });
        return PageUtils.getPageUtils(new PageInfo<>(tsList));
    }

    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<ThreatFactor> tfList = tfcMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(tfList)) {
            List<ExportTfBean> exportList = new ArrayList<>(tfList.size());
            tfList.forEach(o -> {
                ExportTfBean etb = new ExportTfBean();
                this.handleDouble(o);
                BeanUtils.copyProperties(o, etb);
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportTfBean.class, exportList, response, "威胁因素基础信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

    @Override
    public ThreatFactor getNum(String id) {
        return tfcMapper.getNum(id);
    }
}
