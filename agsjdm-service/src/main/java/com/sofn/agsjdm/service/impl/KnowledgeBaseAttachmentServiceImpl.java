package com.sofn.agsjdm.service.impl;


import com.sofn.agsjdm.api.SysFileApi;
import com.sofn.agsjdm.constats.Constants;
import com.sofn.agsjdm.mapper.KnowledgeBaseAttachmentMapper;
import com.sofn.agsjdm.model.KnowledgeBaseAttachment;
import com.sofn.agsjdm.service.KnowledgeBaseAttachmentService;
import com.sofn.agsjdm.vo.SysFileManageForm;
import com.sofn.agsjdm.vo.SysFileVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import org.springframework.beans.BeanUtils;
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
    @Transactional
    @Override
    public int save(KnowledgeBaseAttachment map) {
        String  fileId = map.getFileId();
        String  fileName = map.getFileName();
        Result<List<SysFileVo>> listResult = activationFile(fileId, fileName);
        if (listResult.getData().size()<1){
            throw new SofnException("激活文件失败!请检查文件是否上传成功");
        }
        Result<SysFileManageVo> one = sysFileApi.getOne(fileId);
        if (one.getCode()==500){
            throw  new SofnException("保存失败");
        }
        KnowledgeBaseAttachment baseAttachment=new KnowledgeBaseAttachment();
        BeanUtils.copyProperties(map,baseAttachment);
        return Know.insert(map);

    }
//    激活文件
    private  Result<List<SysFileVo>>  activationFile(String  fileId, String fileName){
        SysFileManageForm sfmf=new SysFileManageForm();
        sfmf.setFileName(fileName);
        sfmf.setIds(fileId);
        sfmf.setSystemId(Constants.SYSTEM_ID);
        sfmf.setInterfaceNum("hidden");
        Result<List<SysFileVo>> result = sysFileApi.activationFile(sfmf);
        return  result;
    }

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
    @Override
    public void update(Map map) {
        String id=(String)map.get("id");
        KnowledgeBaseAttachment one = Know.getOneBySouceId1(id);
        String fileId = one.getFileId();
        deleteFile(fileId);
        String fileIdnew=(String)map.get("fileId");
        String fileName=(String)map.get("fileName");
        activationFile(fileIdnew,fileName);
        Know.updateKnow(map);
    }

    @Override
    public KnowledgeBaseAttachment getSource(String id) {
        return Know.getOneBySouceId1(id);
    }
}
