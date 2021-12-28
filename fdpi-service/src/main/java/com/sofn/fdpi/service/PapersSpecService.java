package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdpi.model.PapersSpec;
import com.sofn.fdpi.vo.PapersSpecForm;
import com.sofn.fdpi.vo.PapersSpecVo;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-23 10:22
 */
public interface PapersSpecService extends IService<PapersSpec> {
    /**
     * 添加证书物种
     * @param papersSpecList
     */
    void save(List<PapersSpecForm> papersSpecList, String papersId);

    void saveSpec(PapersSpecForm papersSpecForm);
    /**
     * 根据证书id 逻辑删除证书物种信息
     * @param papersId 证书id
     */
    void del(String papersId);

    /**
     * 通过证书id 获取证书物种信息
     * @param papersId 证书id
     * @return
     */
    List<PapersSpec> getBypapersId(String papersId);

    void update(List<PapersSpecForm> papersSpecList,String papersId);

    /**
     * 通过证书id，获取物种列表
     * wuXY
     * 2020-6-24 16:05:08
     * @param papersId 证书编号
     * @return List<PapersSpecVo>
     */
    List<PapersSpecVo> listForCondition(String papersId);

    List<PapersSpecVo> listByPapersIds(List<String> papersIdList);
    /**
     *
     */
    PapersSpec checkPapersSpe(String papersId,String speId);

    int delByPaperIds(List<String> papersIds);
}
