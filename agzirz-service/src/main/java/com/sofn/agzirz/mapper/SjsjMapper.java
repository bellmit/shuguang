package com.sofn.agzirz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sofn.agzirz.model.Sjsj;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 事件收集模块 Mapper 接口
 * </p>
 *
 * @author simon
 * @since 2020-03-04
 */
public interface SjsjMapper extends BaseMapper<Sjsj> {

    List<Sjsj> getPage(Map<String, Object> param);
}
