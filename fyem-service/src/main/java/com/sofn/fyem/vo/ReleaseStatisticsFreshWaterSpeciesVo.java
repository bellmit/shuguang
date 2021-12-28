package com.sofn.fyem.vo;

import com.sofn.fyem.model.ProliferationReleaseStatistics;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 增值放流-淡水经济物种
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsFreshWaterSpeciesVo extends ProliferationReleaseStatistics {

    public ReleaseStatisticsFreshWaterSpeciesVo(){}

    /**
     * 将vo转换为po对象
     */
    public static ProliferationReleaseStatistics getProliferationReleaseStatistics(ReleaseStatisticsFreshWaterSpeciesVo releaseStatisticsFreshWaterSpeciesVo){
        ProliferationReleaseStatistics proliferationReleaseStatistics = new ProliferationReleaseStatistics();
        BeanUtils.copyProperties(releaseStatisticsFreshWaterSpeciesVo,proliferationReleaseStatistics);
        return proliferationReleaseStatistics;
    }
    /**
     * po转换为vo
     */
    public static ReleaseStatisticsFreshWaterSpeciesVo getReleaseStatisticsFreshWaterSpeciesVo(ProliferationReleaseStatistics proliferationReleaseStatistics){
        ReleaseStatisticsFreshWaterSpeciesVo releaseStatisticsFreshWaterSpeciesVo = new ReleaseStatisticsFreshWaterSpeciesVo();
        BeanUtils.copyProperties(proliferationReleaseStatistics,releaseStatisticsFreshWaterSpeciesVo);
        return releaseStatisticsFreshWaterSpeciesVo;
    }
}
