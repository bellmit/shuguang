package com.sofn.ahhdp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.vo.DropDownVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-20 17:36
 */
public interface DirectoriesMapper extends BaseMapper<Directories> {
    List<Directories> listByParams(Map<String, Object> params);
    List<DropDownVo> getRusult(Map map);
    List<DropDownVo> getOldName();
}
