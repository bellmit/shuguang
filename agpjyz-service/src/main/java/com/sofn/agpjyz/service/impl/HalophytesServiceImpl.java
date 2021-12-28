package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.agpjyz.mapper.HalophytesMapper;
import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.service.HalophytesService;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.HalophytesVo;
import com.sofn.agpjyz.vo.exportBean.ExportHBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.User;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-27 14:27
 */
@Service("halophytesService")
public class HalophytesServiceImpl implements HalophytesService {
    @Autowired
    private HalophytesMapper halophytesMapper;

    @Override
    public int insertHalophytes(HalophytesVo halophytesVo) {
        Halophytes hal=new Halophytes();
        BeanUtils.copyProperties(halophytesVo,hal);
        hal.setId(IdUtil.getUUId());
        String inputer = "操作人";
        User user = UserUtil.getLoginUser();
        if (!Objects.isNull(user)) {
            inputer = user.getNickname();
        }
        hal.setInputer(inputer);
        hal.setInputerTime(new Date());
        return halophytesMapper.insertHalophytes(hal);
    }

    @Override
    public int delHalophytes(String id) {
        return halophytesMapper.delHalophytes(id);
    }

    @Override
    public int updateHalophytes(Halophytes halophytes) {
        return halophytesMapper.updateHalophytes(halophytes);
    }

    @Override
    public PageUtils<Halophytes> getHalophytes(Map map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<Halophytes> halophytesList = halophytesMapper.getHalophytes(map);
        PageInfo<Halophytes> pageInfo = new PageInfo<>(halophytesList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public Halophytes get(String id) {
        return halophytesMapper.get(id);
    }
    /**
     * 导出
     */
    @Override
    public void export(Map<String, Object> params, HttpServletResponse response) {
        List<Halophytes> tsList = halophytesMapper.getHalophytes(params);
        if (!CollectionUtils.isEmpty(tsList)) {
            List<ExportHBean> exportList = new ArrayList<>(tsList.size());
            tsList.forEach(o -> {
                ExportHBean etb = new ExportHBean();
                BeanUtils.copyProperties(o, etb);
                exportList.add(etb);
            });
            try {
                ExportUtil.createExcel(ExportHBean.class, exportList, response, "伴生植物基础信息.xlsx");
            } catch (Exception e) {
                throw new SofnException(e.getMessage());
            }
        }
    }

}
