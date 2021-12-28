package com.sofn.fdzem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.exception.SofnException;
import com.sofn.fdzem.feign.OrgFeign;
import com.sofn.fdzem.feign.RegionFeign;
import com.sofn.fdzem.feign.UserFeign;
import com.sofn.fdzem.mapper.MonitorStationMapper;
import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.service.MonitorStationService;
import com.sofn.fdzem.util.JustGetOrganization;
import com.sofn.fdzem.vo.SysOrganizationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 高升
 * @since 2020-06-10
 */
@Service
public class MonitorStationServiceImpl extends ServiceImpl<MonitorStationMapper, MonitorStation> implements MonitorStationService {

    private String operator;

    @Autowired
    private MonitorStationMapper monitorStationMapper;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OrgFeign orgFeign;
    @Autowired
    private RegionFeign regionFeign;

    @Override
    public Object getMonitorStationNameOrDetailInfo() {
        List<String> list = new ArrayList<>();
        //1.判断登录用户中是否带有机构的id信息
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//获取到机构id
        if (organizationId == null || organizationId.equals("")) {
            throw new SofnException("用户没有机构信息");
        }
        //2.通过机构id获取到监测站的一些信息，不包括检测机构的名称和地址信息
        MonitorStation monitorStation = monitorStationMapper.selectOne(new QueryWrapper<MonitorStation>().eq("organization_id", organizationId));//获取到
        if (monitorStation == null) {
            monitorStation = new MonitorStation();
        }
        //3.通过机构的id抵用orgfeign获取支撑平台的---机构详细信息
        SysOrganizationForm sysOrganizationForm = JustGetOrganization.getSysOrganizationForm(organizationId, orgFeign);
        String address = sysOrganizationForm.getAddress();
        if (!address.isEmpty()) {
            address = address.substring(1, address.length() - 1).replaceAll("\"", "");
            String[] ar = address.split(",");
            for (String s : ar) {
                list.add(regionFeign.getSysRegionName(s).getData());
            }
            monitorStation.setProvince(list.get(0));
            if (list.size() > 1) {
                monitorStation.setProvinceCity(list.get(1));
                monitorStation.setCountyTown(list.get(2));
            }

        }
        //4.封装机构的名称和地址信息
        if (monitorStation.getName() == null) {//如果用户机构还没在监测站保存过
           /* monitorStation = new MonitorStation();*/
            monitorStation.setOrganizationId(organizationId);
            monitorStation.setName(sysOrganizationForm.getOrganizationName());
            //monitorStation.setAddress(sysOrganizationForm.getAddressDetail());
            monitorStation.setIsDistribute(null);
            return monitorStation;
        } else {
            monitorStation.setName(sysOrganizationForm.getOrganizationName());
            //monitorStation.setAddress(sysOrganizationForm.getAddressDetail());
            return monitorStation;
        }
    }
}
