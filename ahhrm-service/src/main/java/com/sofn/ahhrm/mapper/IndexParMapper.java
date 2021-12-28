package com.sofn.ahhrm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhrm.model.IndexPar;
import com.sofn.ahhrm.vo.DropDownVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-28 14:06
 */
public interface IndexParMapper extends BaseMapper<IndexPar> {
    IndexPar exist(Map map);
    IndexPar repeat(Map map);
    List<IndexPar> listPage(Map map);
    List<DropDownVo> listName();
}
