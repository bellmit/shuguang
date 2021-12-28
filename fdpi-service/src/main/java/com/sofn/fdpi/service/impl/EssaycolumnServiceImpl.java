package com.sofn.fdpi.service.impl;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.ArticlesStatusEnum;
import com.sofn.fdpi.mapper.EssaycolumnMapper;
import com.sofn.fdpi.model.Essaycolumn;
import com.sofn.fdpi.service.EssaycolumnService;
import com.sofn.fdpi.vo.EssaycolumnVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/7 09:47
 */
@Service
@Slf4j
public class EssaycolumnServiceImpl extends BaseService<EssaycolumnMapper, Essaycolumn>implements EssaycolumnService {
    @Autowired
    EssaycolumnMapper essaycolumnMapper;

    /**
     * 根据状态获取栏目名
     * @param status
     * @return
     */
    @Override
    public List<Essaycolumn> getcolumnName(String status){
        return essaycolumnMapper.getcolumnName(status);
    }

    /**
     * 根据栏目名查询出栏目信息
     * @param name
     * @return
     */
    @Override
    public Essaycolumn getcolumn(String name){
        Essaycolumn essaycolumn = essaycolumnMapper.getcolumn(name);
        return essaycolumn;
    }

    /**
     * 新增栏目
     * @param essaycolumnVo
     * @return
     */
    @Override
    public String saveEssaycolumn(EssaycolumnVo essaycolumnVo){
        // 根据栏目名查询
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("COLUMN_NAME",essaycolumnVo.getColumnName());
        List<Essaycolumn> list = essaycolumnMapper.selectByMap(map);
        if (CollectionUtils.isEmpty(list)){
            // 新增
            Essaycolumn essaycolumn = essaycolumnVo.convertToModel(Essaycolumn.class);
            essaycolumn.preInsert();
            //essaycolumn.setStatus(ArticlesStatusEnum.IS_PUBLISH.getKey());
            boolean save = this.save(essaycolumn);
            if (save){
                return "1";
            }
        }else {
            return "该栏目已存在";
        }
        throw new SofnException("新增栏目失败");
    }
    @Override
    public String deleteEssaycolumnInfo(String id){
        Essaycolumn essaycolumn = this.getEssaycolumnInfo(id);
        if (essaycolumn==null){
            return "该栏目不存在";
        }
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id",id);
        map.put("updateTime",new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        map.put("status",ArticlesStatusEnum.IS_HIDE.getKey());
        int i = essaycolumnMapper.deleteEssaycolumnInfo(map);
        if (i==1){
            return "1";
        }
        throw new SofnException("删除栏目失败");
    }
    @Override
    public String updateEssaycolumnInfo(EssaycolumnVo essaycolumnVo){
        Essaycolumn essaycolumnInfo = this.getEssaycolumnInfo(essaycolumnVo.getId());
        if (essaycolumnInfo==null){
            return "该栏目不存在";
        }
        Essaycolumn esVo = essaycolumnVo.convertToModel(Essaycolumn.class);
        esVo.preUpdate();
        boolean b = this.updateById(esVo);
        if (b){
            return "1";
        }
        throw new SofnException("修改栏目失败");
    }

    /**
     * 根据id查询详细栏目信息
     * @param id
     * @return
     */
    @Override
    public Essaycolumn  getEssaycolumnInfo(String id){
        return this.getById(id);
    }


}
