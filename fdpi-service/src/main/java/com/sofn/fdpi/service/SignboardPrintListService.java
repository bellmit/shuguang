package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardPrintList;
import com.sofn.fdpi.vo.SignboardPrintListForm;
import com.sofn.fdpi.vo.SignboardVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface SignboardPrintListService  {

    SignboardPrintList insertSignboardPrintList(SignboardPrintListForm signboardPrintListForm, String printId);

    PageUtils<SignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    void export(Map<String, Object> params, HttpServletResponse response);

    void export2(Map<String, Object> params, HttpServletResponse response);

    void upload(MultipartFile multipartFile, String id);

    String getSequenceNum(String contractNum);

}
