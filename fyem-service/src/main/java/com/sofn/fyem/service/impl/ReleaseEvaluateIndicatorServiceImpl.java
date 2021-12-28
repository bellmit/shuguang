package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.mapper.ReleaseEvaluateIndicatorMapper;
import com.sofn.fyem.model.ReleaseEvaluateIndicator;
import com.sofn.fyem.service.ReleaseEvaluateIndicatorService;
import com.sofn.fyem.vo.SecondEvaluateIndicatorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Service
public class ReleaseEvaluateIndicatorServiceImpl  extends ServiceImpl<ReleaseEvaluateIndicatorMapper, ReleaseEvaluateIndicator> implements ReleaseEvaluateIndicatorService {

    @Autowired
    private ReleaseEvaluateIndicatorMapper releaseEvaluateIndicatorMapper;

    @Override
    public PageUtils<ReleaseEvaluateIndicator> getReleaseEvaluateIndicatorListByPage(Map<String, Object> params, int pageNo, int pageSize) {

        PageHelper.offsetPage(pageNo,pageSize);
        List<ReleaseEvaluateIndicator> releaseEvaluateIndicatorList = releaseEvaluateIndicatorMapper.getReleaseEvaluateIndicatorList(params);
        PageInfo<ReleaseEvaluateIndicator> pageInfo = new PageInfo<>(releaseEvaluateIndicatorList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<ReleaseEvaluateIndicator> getReleaseEvaluateIndicatorListByQuery(Map<String, Object> params) {

        return releaseEvaluateIndicatorMapper.getReleaseEvaluateIndicatorList(params);
    }

    @Override
    public ReleaseEvaluateIndicator getReleaseEvaluateIndicatorById(String id) {
        return releaseEvaluateIndicatorMapper.getReleaseEvaluateIndicatorById(id);
    }

    @Override
    public void updateStatus(Map<String, Object> params) {
        releaseEvaluateIndicatorMapper.updateStatus(params);
    }

    @Override
    public void addReleaseEvaluateIndicator(ReleaseEvaluateIndicator releaseEvaluateIndicator) {
        String userId = UserUtil.getLoginUserId();
        releaseEvaluateIndicator.setId(IdUtil.getUUId());
        releaseEvaluateIndicator.setCreateUserId(userId);
        releaseEvaluateIndicator.setStatus("0");
        releaseEvaluateIndicator.setCreateTime(new Date());
        this.save(releaseEvaluateIndicator);
    }

    @Override
    public void updateReleaseEvaluateIndicator(ReleaseEvaluateIndicator releaseEvaluateIndicator) {
        this.updateById(releaseEvaluateIndicator);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeReleaseEvaluateIndicator(String id) {
        ReleaseEvaluateIndicator byId = this.getById(id);
        String indicatorType = byId.getIndicatorType();
        //判断是否为一级指标
        if("0".equals(indicatorType)){
            //删除一级指标下所有的二级指标
            Map<String,Object> params = Maps.newHashMap();
            params.put("parentId",id);
            releaseEvaluateIndicatorMapper.removeByParentId(params);
        }
        this.removeById(id);
    }

    @Override
    public List<SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorType(List<String> firstIds) {
        return releaseEvaluateIndicatorMapper.getSecondEvaluateIndicatorType(firstIds);
    }

    @Override
    public List<SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorVo(List<String> firstIds,String BelongYear,String basicReleaseId) {
        return releaseEvaluateIndicatorMapper.getSecondEvaluateIndicatorVo(firstIds,BelongYear,basicReleaseId);
    }

    @Override
    public List<SecondEvaluateIndicatorVo> getSecondEvaluateIndicatorHistory(List<String> firstIds,String BelongYear,String basicReleaseId) {
        return releaseEvaluateIndicatorMapper.getSecondEvaluateIndicatorHistory(firstIds,BelongYear,basicReleaseId);
    }
}
