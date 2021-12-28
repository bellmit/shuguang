package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.model.Spe;
import com.sofn.fdpi.vo.SpeNameLevelVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther:
 * @Date: 2019/11/29 10:49
 * @Description:
 */
public interface SpeMapper extends BaseMapper<Spe> {
    Spe getSpeById(@Param("id") String id);

    List<Spe> getSpeByPage(Map<String, Object> params);

    /**
     * 部级 删除物种
     *
     * @param
     * @return
     */
    int deleteSpeInfo(Map<String, Object> map);

    /**
     * 根据物种名字获取物种信息
     * wuXY
     * 2019-12-30 16:23:33
     *
     * @param speName 物种名字
     * @return Spe物种对象
     */
    Spe getSpeciesByName(@Param("speName") String speName);

    /**
     * 获取不同级别的动物列表下拉
     *
     * @param proLevel
     * @return
     */
    List<SpeNameLevelVo> getSecondLevel(@Param("proLevel") String proLevel);

    /**
     * 进出口管理下拉物种
     *
     * @return 物种名
     */
    List<SpeNameLevelVo> getSpeciesName();

    Spe getSpe(Map map);

    Spe speName(Map map);

    /**
     * 查询当前物种id是否已开始绑定证书
     *
     * @param id 物种id
     * @return
     */
    List<Papers> getPapersSpe(String id);


    /**
     * 获取所有的物种数据，返回一个map
     * wXY
     * 2020-7-30 18:45:17
     *
     * @return map
     */
    @MapKey("key")
    Map<String, String> getMapForAllSpecies();
}
