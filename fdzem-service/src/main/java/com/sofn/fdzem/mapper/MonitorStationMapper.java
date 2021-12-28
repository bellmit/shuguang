package com.sofn.fdzem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdzem.model.MonitorStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MonitorStationMapper extends BaseMapper<MonitorStation> {
    @Update("<script>update tb_monitor_station set distribute_id=#{id},is_distribute=#{word} " +
            " where id in" +
            " <foreach item='ids' collection='monitroIds' open='(' separator=',' close=')'> " +
            " #{ids}" +
            " </foreach>" +
            "</script>")
    void updateDistribute(String id, List<Long> monitroIds,String word);

    @Update("update tb_monitor_station set distribute_id=#{zero} ,is_distribute=#{word} where distribute_id=#{id}")
    void updateDis(String id,String zero,String word);

    @Select("<script>select name from tb_monitor_station where id in " +
            " <foreach item='ids' collection='monitroIds' open='(' separator=',' close=')'> " +
            " #{ids}" +
            " </foreach> " +
            "</script>")
    List<String> selectNameByIds(@Param("monitroIds") List<Long> monitroIds);
}
