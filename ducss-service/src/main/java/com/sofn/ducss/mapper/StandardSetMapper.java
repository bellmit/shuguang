package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StandardSet;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/10/27 18:03
 * @Version 1.0
 * 标准值设定
 */
public interface StandardSetMapper extends BaseMapper<StandardSet> {
    ArrayList<StandardSet> getStandardSetList(@Param("years") List<String> years);

    List<String> selectCopyYear();

}
