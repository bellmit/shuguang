package com.sofn.agpjpm.service;

import com.sofn.agpjpm.model.FileAtt;
import com.sofn.agpjpm.model.FileManage;
import com.sofn.agpjpm.vo.FileAttForm;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:36
 */
public interface FileAttService {
    void insert(FileAttForm f,String sourceId);
    List<FileAtt> getBySouceId(String souceId);
    void updateList(List<FileAttForm> list,String sourceId);
    void del(String id);
    void update(FileAttForm f,String sourceId );
    void delBySourceId(String sourceId);

}
