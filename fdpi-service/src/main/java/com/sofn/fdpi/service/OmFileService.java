package com.sofn.fdpi.service;

import com.sofn.fdpi.vo.OmFileForm;
import com.sofn.fdpi.vo.OmFileVO;

import java.util.List;


public interface OmFileService {

    void add(String sourceId, List<OmFileForm> files);

    void update(String sourceId, List<OmFileForm> files);

    void delete(List<String> id);

    List<OmFileVO> listBySourceId(String sourceId);

}
