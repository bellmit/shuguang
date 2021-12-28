package com.sofn.agpjyz.service;

import com.sofn.agpjyz.vo.CharacterForm;
import com.sofn.agpjyz.vo.CharacterVo;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 15:18
 */
public interface CharacterService {
    CharacterVo save(CharacterForm characterForm);
    int del(String id);
    List<CharacterVo> getOne(String id);
    int update(List<CharacterForm> characterForm);
}
