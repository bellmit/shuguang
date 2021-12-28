package com.sofn.agpjyz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjyz.mapper.CharacterMapper;
import com.sofn.agpjyz.model.CharactModel;
import com.sofn.agpjyz.service.CharacterService;
import com.sofn.agpjyz.vo.CharacterForm;
import com.sofn.agpjyz.vo.CharacterVo;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:18
 */
@Service("characterService")
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Override
    public CharacterVo save(CharacterForm characterForm) {
        CharactModel entity= new CharactModel();
        BeanUtils.copyProperties(characterForm, entity);
        entity.setId(IdUtil.getUUId());
        int insert = characterMapper.insert(entity);
        return CharacterVo.entity2Vo(entity);
    }

    @Override
    public int del(String id) {
        QueryWrapper<CharactModel> ch=new QueryWrapper();
        ch.eq("SPECIMEN_ID", id);
        return characterMapper.delete(ch);
    }

    @Override
    public List<CharacterVo> getOne(String id) {
        QueryWrapper<CharactModel> ch=new QueryWrapper();
        ch.eq("SPECIMEN_ID", id);
        List<CharactModel> charact = characterMapper.selectList(ch);
        if (!CollectionUtils.isEmpty(charact)) {
            List<CharacterVo> listVo = new ArrayList<>(charact.size());
            for (CharactModel pa : charact) {
                listVo.add(CharacterVo.entity2Vo(pa));
            }
            return listVo;
        }
        return null;
    }

    @Override
    public int update(List<CharacterForm> characterForm) {
        for (CharacterForm ch:
                characterForm) {
            CharactModel charact=new CharactModel();
            BeanUtils.copyProperties(ch,charact);
            characterMapper.updateById(charact);
        }
        return 0;
    }
}
