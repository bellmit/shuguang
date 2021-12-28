package com.sofn.fyem.vo;

import com.sofn.fyem.model.ProliferationReleaseStatistics;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 增值放流-珍稀经济物种
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsRareSpeciesVo  extends ProliferationReleaseStatistics {

    public ReleaseStatisticsRareSpeciesVo(){}

    /**
     * 将vo转换为po对象
     */
    public static ProliferationReleaseStatistics getProliferationReleaseStatistics(ReleaseStatisticsRareSpeciesVo releaseStatisticsRareSpeciesVo){
        ProliferationReleaseStatistics proliferationReleaseStatistics = new ProliferationReleaseStatistics();
        BeanUtils.copyProperties(releaseStatisticsRareSpeciesVo,proliferationReleaseStatistics);
        return proliferationReleaseStatistics;
    }
    /**
     * po转换为vo
     */
    public static ReleaseStatisticsRareSpeciesVo getReleaseStatisticsRareSpeciesVo(ProliferationReleaseStatistics proliferationReleaseStatistics){
        ReleaseStatisticsRareSpeciesVo releaseStatisticsRareSpeciesVo = new ReleaseStatisticsRareSpeciesVo();
        BeanUtils.copyProperties(proliferationReleaseStatistics,releaseStatisticsRareSpeciesVo);
        return releaseStatisticsRareSpeciesVo;
    }
}
