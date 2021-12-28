package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.vo.HalophytesVo;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-27 14:27
 */
public interface HalophytesService {
    int insertHalophytes(HalophytesVo halophytesVo);
    int delHalophytes(String id);
    int updateHalophytes(Halophytes halophytes);
    PageUtils<Halophytes> getHalophytes(Map map, int pageNo, int pageSize);
    Halophytes get(String id);
    void export(Map<String, Object> params, HttpServletResponse response);

}
