package com.sofn.fdzem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.enums.StatusEnum;
import com.sofn.fdzem.feign.OrgFeign;
import com.sofn.fdzem.mapper.DistributeMapper;
import com.sofn.fdzem.mapper.MonitorStationMapper;
import com.sofn.fdzem.model.Distribute;
import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.service.DistributeService;
import com.sofn.fdzem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DistributeServiceImpl implements DistributeService {
    @Autowired
    DistributeMapper distributeMapper;
    @Autowired
    MonitorStationMapper monitorStationMapper;
    @Autowired
    OrgFeign orgFeign;

    @Override
    public PageUtils listPage(Integer pageNum, Integer pageSize) {
     /*   Result<PageUtils<SysOrgVo>> org = orgFeign.getThirdOrgByPage("fdzem", pageNum, pageSize);
        List<SysOrgVo> Orglist = org.getData().getList().stream().filter(list -> list.getOrganizationName().contains("监测中心"))
                .collect(Collectors.toList());
        org.getData().setList(Orglist);
        org.getData().setTotalCount(Orglist.size());*/
        /*---------上面方法已淘汰----------*/
        Result<List<SysOrgVo>> listResult = orgFeign.getOrgInfoByAppIdAndFunctionCode("fdzem", "area");
        List<SysOrgVo> list = this.startPage(listResult.getData(), pageNum, pageSize);
        PageInfo<SysOrgVo> pageInfo = new PageInfo(list);
        return new PageUtils<SysOrgVo>(list, (int) pageInfo.getTotal(), pageSize, pageNum);
    }

    @Override
    public DistributeVo getById(String id) {
        //Distribute distribute = distributeMapper.selectById(id);
        List<MonitorStation> undistributed = monitorStationMapper.selectList(new QueryWrapper<MonitorStation>().eq("is_distribute", "N"));
        List<MonitorStation> allocated = monitorStationMapper.selectList(new QueryWrapper<MonitorStation>().eq("distribute_id", id));
        DistributeVo distributeVo = new DistributeVo();
        distributeVo.setId(id);
        distributeVo.setName(orgFeign.getOrgInfoById(id).getData().getOrganizationName());
        distributeVo.setUndistributed(undistributed);
        distributeVo.setAllocated(allocated);
        return distributeVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDistribute(String id, List<Long> monitroIds) {
        //先把监测中心对应的监测站全部清空
        monitorStationMapper.updateDis(id, StatusEnum.ZERO.getDescription(),StatusEnum.N.getDescription());
        //再进行监测站分配
        if (monitroIds.size()!=0&&monitroIds!=null) {
            monitorStationMapper.updateDistribute(id, monitroIds,StatusEnum.Y.getDescription());
        }
    }

   /* @Override
    public void insert(Distribute distribute) {
        distributeMapper.insert(distribute);
        if (!distribute.getMonitroIds().isEmpty()) {
            monitorStationMapper.updateDistribute(distribute.getId(), distribute.getMonitroIds());
        }
    }*/

    /**
     * 集合分页
     *
     * @param list
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

}
