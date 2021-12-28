package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdpi.model.Essaycolumn;
import com.sofn.fdpi.vo.EssaycolumnVo;

import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/7 09:15
 */
public interface EssaycolumnService extends IService<Essaycolumn> {


    List<Essaycolumn> getcolumnName(String status);

    Essaycolumn getcolumn(String name);

    String saveEssaycolumn(EssaycolumnVo essaycolumnVo);

    String deleteEssaycolumnInfo(String id);

    String updateEssaycolumnInfo(EssaycolumnVo essaycolumnVo);

    Essaycolumn  getEssaycolumnInfo(String id);
}
