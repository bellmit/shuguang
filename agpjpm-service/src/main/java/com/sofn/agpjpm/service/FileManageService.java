package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.FileManage;
import com.sofn.agpjpm.vo.FileForm;
import com.sofn.agpjpm.vo.SurveyVo;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:29
 */
public interface FileManageService {
    void insert(FileForm fileForm);
    FileManage get(String Id);
    void update(FileForm fileForm);
    void del(String id);
    PageUtils<FileManage> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
}
