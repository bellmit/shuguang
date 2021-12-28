package com.sofn.fyem.vo;

import com.sofn.fyem.model.ProliferationReleaseStatistics;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 增值放流-海水经济物种
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsSeaWaterSpeciesVo  extends ProliferationReleaseStatistics {

    public ReleaseStatisticsSeaWaterSpeciesVo(){}

    /**
     * 将vo转换为po对象
     */
    public static ProliferationReleaseStatistics getProliferationReleaseStatistics(ReleaseStatisticsSeaWaterSpeciesVo releaseStatisticsSeaWaterSpeciesVo){
        ProliferationReleaseStatistics proliferationReleaseStatistics = new ProliferationReleaseStatistics();
        BeanUtils.copyProperties(releaseStatisticsSeaWaterSpeciesVo,proliferationReleaseStatistics);
        return proliferationReleaseStatistics;
    }
    /**
     * po转换为vo
     */
    public static ReleaseStatisticsSeaWaterSpeciesVo getReleaseStatisticsSeaWaterSpeciesVo(ProliferationReleaseStatistics proliferationReleaseStatistics){
        ReleaseStatisticsSeaWaterSpeciesVo releaseStatisticsSeaWaterSpeciesVo =
                new ReleaseStatisticsSeaWaterSpeciesVo();
        BeanUtils.copyProperties(proliferationReleaseStatistics,releaseStatisticsSeaWaterSpeciesVo);
        return releaseStatisticsSeaWaterSpeciesVo;
    }

}
