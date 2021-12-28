package com.sofn.agpjyz.service.impl;

import com.sofn.agpjyz.constants.Constants;
import com.sofn.agpjyz.mapper.KnowledgeBaseAttachmentMapper;
import com.sofn.agpjyz.model.KnowledgeBaseAttachment;
import com.sofn.agpjyz.service.KnowledgeBaseAttachmentService;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.vo.SysFileManageForm;
import com.sofn.agpjyz.vo.SysFileVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:35
 */
@Service("knowledgeBaseAttachmentService")
public class KnowledgeBaseAttachmentServiceImpl implements KnowledgeBaseAttachmentService {
    @Autowired
    private KnowledgeBaseAttachmentMapper Know;
    @Autowired
    private SysFileApi sysFileApi;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(Map map) {
        String  fileId = (String )map.get("fileId");
        String  fileName = (String )map.get("fileName");
        Result<List<SysFileVo>> listResult = activationFile(fileId, fileName);
        if (listResult.getData().size()<1){
            throw new SofnException("激活文件失败!请检查文件是否上传成功");
        }
        return Know.save(map);

    }
//    激活文件
    private  Result<List<SysFileVo>>  activationFile(String  fileId,String fileName){
        SysFileManageForm sfmf=new SysFileManageForm();
        sfmf.setFileName(fileName);
        sfmf.setIds(fileId);
        sfmf.setSystemId(Constants.SYSTEM_ID);
        sfmf.setInterfaceNum("hidden");
        Result<List<SysFileVo>> result = sysFileApi.activationFile(sfmf);
        return  result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int del(String id) {
        KnowledgeBaseAttachment one = Know.getOne(id);
        String fileId = one.getFileId();
        deleteFile(fileId);
        return Know.del(id);
    }
//    删除文件
    private Result deleteFile(String  fileId){
        Result result = sysFileApi.batchDeleteFile(fileId);
        return  result;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Map map) {
        String id=(String)map.get("id");
        KnowledgeBaseAttachment one = Know.getOneBySouceId(id);
        String fileId = one.getFileId();
        deleteFile(fileId);
        String fileIdnew=(String)map.get("fileId");
        String fileName=(String)map.get("fileName");
        activationFile(fileIdnew,fileName);
        Know.update(map);
    }
}
