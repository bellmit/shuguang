package com.sofn.fdzem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdzem.model.Index;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.checkerframework.common.aliasing.qual.MaybeAliased;

/**
 * Created with IntelliJ IDEA.
 * User: gaosheng
 * Date: 2020/05/19 9:13
 * Description:
 * Version: V1.0
 */
@Mapper
public interface IndexMapper extends BaseMapper<Index> {
    @Select("select sum(score) from tb_indexs where index_type=#{index} and state=1")
    Double selectScore(String index);

    @Select("select index_name from tb_indexs where id=#{parentId}")
    String selectParentName(Long parentId);
}
