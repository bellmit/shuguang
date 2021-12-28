package com.sofn.fdzem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdzem.vo.EvaluateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluateVoMapper extends BaseMapper<EvaluateVo> {
    /*@Select("<script>" +
            "select ms.id,ms.name,ms.province,ms.province_city,ms.county_town,ms.sea_area,te.score as score,te.submit_year as submitYear" +
            " from tb_monitor_station ms" +
            " left outer join tb_evaluate te on te.monitor_id=ms.id and  te.submit_year =#{submitYear}" +
            " where 1=1" +
            "<if test=\"organizationName!=null\">" +
            " and ms.name like \"%\"#{organizationName}::varchar\"%\"" +
            "</if>" +
            "<if test=\"lowestScore!=null\">" +
            " and te.score &gt;=#{lowestScore}" +
            "</if>" +
            "<if test=\"highestScore!=null\">" +
            " and te.score &lt;=#{highestScore}" +
            "</if>" +
            "<if test=\"isAcs!=null\">" +
            " ORDER BY ${fileId} ${isAcs}" +
            "</if>" +
            " limit #{pageNum} OFFSET #{pageSize}" +
            "</script>")*/
    List<EvaluateVo> selectEvaluateVoPage(@Param("organizationName")String organizationName,
                                          @Param("submitYear")String submitYear,
                                          @Param("lowestScore")Double lowestScore,
                                          @Param("highestScore")Double highestScore,
                                          @Param("fileId")String fileId,
                                          @Param("isAcs")String isAcs,
                                          @Param("pageNum")Integer pageNum,
                                          @Param("pageSize")Integer pageSize);

    @Select("<script>select * from  tb_monitor_station where 1=1" +
            "<if test=\"organizationName!=null\">" +
            " and name like \"%\"#{organizationName}::varchar\"%\"" +
            "</if>" +
            " limit #{pageSize} OFFSET #{OFFSET}" +
            "</script>")
    List<EvaluateVo> selectEvaluateVoList(@Param("organizationName") String organizationName, Integer pageNum, Integer pageSize);
}
