package com.sofn.agpjyz.mapper;

import com.sofn.agpjyz.model.Halophytes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-27 14:25
 */
public interface HalophytesMapper {
    int insertHalophytes(Halophytes halophytes);
    int delHalophytes(@Param("id") String id);
    int updateHalophytes(Halophytes halophytes);
    List<Halophytes> getHalophytes(Map map);
    Halophytes get(@Param("id") String id);

}
