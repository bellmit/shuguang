package com.sofn.fyrpa.service.impl;

import com.sofn.common.model.Result;
import com.sofn.fyrpa.mapper.AquaticResourcesProtectionInfoMapper;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.service.SpatialAnalystService;
import com.sofn.fyrpa.sysapi.SysRegionApi;
import com.sofn.fyrpa.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpatialAnalystServiceImpl implements SpatialAnalystService {

    @Autowired
    private AquaticResourcesProtectionInfoMapper aquaticResourcesProtectionInfoMapper;

    @Autowired(required = false)
    private SysRegionApi sysRegionApi;
    
    @Override
    public List<SpatialAnalystResourcesVoList> selectSpatialAnalystByCondition(String submitTime, String name){
        List<SpatialAnalystResourcesVoList> list = this.aquaticResourcesProtectionInfoMapper.selectSpatialAnalystByCondition(submitTime, name);
        return list;


    }

    @Override
    public List<AreasVo> selectProtectionByCount(String submitTime, String name) {

        List<AquaticResourcesProtectionInfo> oneRegionCodeList = this.aquaticResourcesProtectionInfoMapper.selectOneRegionCodeList(submitTime,name);
        List<AreasVo> areasVoList = new ArrayList<>();
        if(oneRegionCodeList!=null) {
            for (AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo : oneRegionCodeList) {
                AreasVo areasVo = new AreasVo();
//                QueryWrapper<AquaticResourcesProtectionInfo> queryWrapper1 = new QueryWrapper<>();
//                queryWrapper1.eq("is_del", "N");
//                queryWrapper1.eq("status", "已通过");
//                queryWrapper1.like("region_code", oneRegionCodeList.get(i).getRegionCode());
//
//                //获取保护区个数
//                Integer count = this.aquaticResourcesProtectionInfoMapper.selectCount(queryWrapper1);
//                areasVo.setAmount(count);

                //调用平台接口
                Result<String> regionNamesByCodes = sysRegionApi.getRegionNamesByCodes(aquaticResourcesProtectionInfo.getRegionCode());
                String data = regionNamesByCodes.getData();
                areasVo.setRegionName(data);
                areasVo.setRegionCode(aquaticResourcesProtectionInfo.getRegionCode());
                List<ProvinceVo> provinceVoList1 = this.aquaticResourcesProtectionInfoMapper.selectProvinceProtectionList(submitTime, name, aquaticResourcesProtectionInfo.getRegionCode());
                List<ProvinceVo> provinceVoList = new ArrayList<>();
                for (ProvinceVo vo : provinceVoList1) {
                    ProvinceVo provinceVo = new ProvinceVo();
                    provinceVo.setName(vo.getName());
                    provinceVo.setCurrentProtectionArea(vo.getCurrentProtectionArea());
                    provinceVo.setCoreRegionArea(vo.getCoreRegionArea());
                    provinceVo.setExperimentRegionArea(vo.getExperimentRegionArea());
                    provinceVo.setMajorProtectObject(vo.getMajorProtectObject());
                    provinceVoList.add(provinceVo);
                }
                areasVo.setProvinceVoList(provinceVoList);
                areasVo.setAmount(provinceVoList.size());
                areasVoList.add(areasVo);
            }
            return areasVoList;
        }

        return null;
    }

    @Override
    public List<CityAreasVo> selectCityAreasList(String submitTime, String name) {
        List<AquaticResourcesProtectionInfo> twoRegionCodeList = this.aquaticResourcesProtectionInfoMapper.selectTwoRegionCodeList(submitTime,name);
        List<CityAreasVo> cityAreasVoList  = new ArrayList<>();
        if(twoRegionCodeList!=null) {
            for (AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo : twoRegionCodeList) {
                CityAreasVo cityAreasVo = new CityAreasVo();
//                QueryWrapper<AquaticResourcesProtectionInfo> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("is_del", "N");
//                queryWrapper.eq("status", "已通过");
//                queryWrapper.like("region_code", aquaticResourcesProtectionInfo.getRegionCode());
//                Integer count = this.aquaticResourcesProtectionInfoMapper.selectCount(queryWrapper);
//                cityAreasVo.setAmount(count);

                //调用平台接口
                Result<String> regionNamesByCodes = sysRegionApi.getRegionNamesByCodes(aquaticResourcesProtectionInfo.getRegionCode());
                String data = regionNamesByCodes.getData();
                cityAreasVo.setRegionName(data);
                cityAreasVo.setRegionCode(aquaticResourcesProtectionInfo.getRegionCode());
                List<CityVo> cityVoList = new ArrayList<>();
                List<CityVo> cityVoList1 = this.aquaticResourcesProtectionInfoMapper.selectCityProtectionList(submitTime, name, aquaticResourcesProtectionInfo.getRegionCode());
                for (CityVo vo : cityVoList1) {
                    CityVo cityVo = new CityVo();
                    cityVo.setName(vo.getName());
                    cityVo.setCurrentProtectionArea(vo.getCurrentProtectionArea());
                    cityVo.setCoreRegionArea(vo.getCoreRegionArea());
                    cityVo.setExperimentRegionArea(vo.getExperimentRegionArea());
                    cityVo.setMajorProtectObject(vo.getMajorProtectObject());
                    cityVoList.add(cityVo);
                }
                cityAreasVo.setAmount(cityVoList.size());
                cityAreasVo.setCityVoList(cityVoList);
                cityAreasVoList.add(cityAreasVo);
            }
            return cityAreasVoList;
        }

        return null;
    }

    @Override
    public List<CountyAreasVo> selectCountyAreasList(String submitTime, String name) {
        List<AquaticResourcesProtectionInfo> threeRegionCodeList = this.aquaticResourcesProtectionInfoMapper.selectThreeRegionCodeList(submitTime,name);
        List<CountyAreasVo> countyAreasVoList  = new ArrayList<>();
        if(threeRegionCodeList!=null) {
            for (AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo : threeRegionCodeList) {
                CountyAreasVo countyAreasVo = new CountyAreasVo();
//                QueryWrapper<AquaticResourcesProtectionInfo> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("is_del", "N");
//                queryWrapper.eq("status", "已通过");
//                queryWrapper.like("region_code", aquaticResourcesProtectionInfo.getRegionCode());
//                Integer count = this.aquaticResourcesProtectionInfoMapper.selectCount(queryWrapper);
//                countyAreasVo.setAmount(count);

                //调用平台接口
                Result<String> regionNamesByCodes = sysRegionApi.getRegionNamesByCodes(aquaticResourcesProtectionInfo.getRegionCode());
                String data = regionNamesByCodes.getData();
                countyAreasVo.setRegionName(data);
                countyAreasVo.setRegionCode(aquaticResourcesProtectionInfo.getRegionCode());
                List<CountyVo> countyVoList = new ArrayList<>();
                List<CountyVo> countyVoList1 = this.aquaticResourcesProtectionInfoMapper.selectCountyProtectionList(submitTime, name, aquaticResourcesProtectionInfo.getRegionCode());
                for (CountyVo vo : countyVoList1) {
                    CountyVo countyVo = new CountyVo();
                    countyVo.setName(vo.getName());
                    countyVo.setCurrentProtectionArea(vo.getCurrentProtectionArea());
                    countyVo.setCoreRegionArea(vo.getCoreRegionArea());
                    countyVo.setExperimentRegionArea(vo.getExperimentRegionArea());
                    countyVo.setMajorProtectObject(vo.getMajorProtectObject());
                    countyVoList.add(countyVo);
                }
                countyAreasVo.setAmount(countyVoList.size());
                countyAreasVo.setCountyVoList(countyVoList);
                countyAreasVoList.add(countyAreasVo);
            }
            return countyAreasVoList;
        }
        return null;
    }
}
