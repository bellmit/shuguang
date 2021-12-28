package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Essaycolumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/7 09:11
 */
public interface EssaycolumnMapper extends BaseMapper<Essaycolumn> {
   public List<Essaycolumn>  getcolumnName(@Param("status") String status);

   int deleteEssaycolumnInfo(Map<String,Object> map);
   Essaycolumn getcolumn(@Param("name") String name);
}
