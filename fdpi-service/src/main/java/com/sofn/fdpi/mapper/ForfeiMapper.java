package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Forfei;

import java.util.List;
import java.util.Map;

/**
 * @Description:  罚没救助Mapper
 * @Auther: Bay
 * @Date: 2020/1/2 13:26
 */
public interface ForfeiMapper extends BaseMapper<Forfei> {
    /**
     *  获取罚没信息
     * @param map
     * @return
     */
    List<Forfei> getForFeiList(Map<String, Object>  map);
    int deleteForFeiInfo(Map<String,Object> map);
}
