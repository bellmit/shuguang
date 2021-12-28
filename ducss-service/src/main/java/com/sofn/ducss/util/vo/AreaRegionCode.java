package com.sofn.ducss.util.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaRegionCode {
    /**
     * 省ID
     */
    private String provinceId;
    /**
     * 市ID
     */
    private String cityId;
    /**
     * 区县ID
     */
    private String countyId;
    /**
     * 地区级别
     */
    private String regionLevel;

    /**
     * 省名称
     */
    private String provinceName;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区县名称
     */
    private String countyName;

    /**
     * 本区域的ID
     * 如果是省级，那么就等于provinceId
     * 如果是市级，那么就等于cityId
     * 如果是县级，那么就等于countyId
     */
    private String lastId;
    /**
     * 本区域的名称
     * 如果是省级，那么就等于provinceName
     * 如果是市级，那么就等于cityName
     * 如果是县级，那么就等于countyName
     */
    private String lastName;


    /**
     * 获得完整区域名称
     * @return
     */
    public String getFullName() {
        return provinceName.concat(cityName).concat(countyName);
    }
}
