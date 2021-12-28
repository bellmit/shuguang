package com.sofn.agpjyz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agpjyz.mapper.KnowledgeBaseAttachmentMapper;
import com.sofn.agpjyz.mapper.KnowledgeBaseMapper;

import com.sofn.agpjyz.model.KnowledgeBase;
import com.sofn.agpjyz.model.KnowledgeBaseAttachment;
import com.sofn.agpjyz.service.KnowledgeBaseAttachmentService;
import com.sofn.agpjyz.service.KnowledgeBaseService;
import com.sofn.agpjyz.sysapi.SysFileApi;
import com.sofn.agpjyz.util.ExportUtil;
import com.sofn.agpjyz.vo.KnowledgeVo;
import com.sofn.agpjyz.vo.exportBean.ExportHBean;
import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 9:34
 */
@Transactional
@Service("knowledgeBaseService")
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
    @Autowired
    KnowledgeBaseMapper knowledgeBaseMapper;
    @Autowired
    KnowledgeBaseAttachmentService knowledgeBaseAttachmentService;
    @Autowired
    SysFileApi sysFileApi;
    @Autowired
    private KnowledgeBaseAttachmentMapper Know;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(KnowledgeVo k) {
        Map map1= Maps.newHashMap();
        map1.put("knowledge",k.getKnowledge());
        map1.put("documentId", k.getDocumentId());
        KnowledgeBase nameAndType = knowledgeBaseMapper.getNameAndType(map1);
        if (nameAndType==null) {
            Map map= Maps.newHashMap();
            Map maps= Maps.newHashMap();
            map.put("id", IdUtil.getUUId());
            map.put("documentId", k.getDocumentId());
            map.put("documentValue",k.getDocumentValue());
            map.put("knowledge",k.getKnowledge() );
            int save = knowledgeBaseMapper.save(map);
            maps.put("id", IdUtil.getUUId());
            maps.put("fileId",k.getFileId());
            maps.put("fileName",k.getFileName());
            maps.put("knowledgeId",map.get("id"));
            maps.put("downloads",0);
            int  i=knowledgeBaseAttachmentService.save(maps);
            return  1;
        }
        return 0;
    }

    @Override
    public PageUtils<KnowledgeBase> list(Map map, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<KnowledgeBase> halophytesList = knowledgeBaseMapper.list(map);
        for (KnowledgeBase know:
                halophytesList) {
            String fileId = know.getK().getFileId();
            Result<SysFileManageVo> one = sysFileApi.getOne(fileId);
            if(one.getData()!=null){
                know.getK().setDownloads(one.getData().getDownloadTimes());
            }
        }
        PageInfo<KnowledgeBase> pageInfo = new PageInfo<>(halophytesList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public KnowledgeBase getOne(String id) {
        return knowledgeBaseMapper.getOne(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int del(String id) {
        KnowledgeBase one = knowledgeBaseMapper.getOne(id);
        knowledgeBaseAttachmentService.del( one.getK().getId());
        return knowledgeBaseMapper.del(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(KnowledgeVo know) {
        Map map= Maps.newHashMap();
        map.put("id", know.getId());
        map.put("documentId", know.getDocumentId());
        map.put("documentValue",know.getDocumentValue());
        map.put("knowledge",know.getKnowledge() );
        int update = knowledgeBaseMapper.update(map);
        KnowledgeBaseAttachment one = Know.getOneBySouceId(know.getId());
        if (!one.getFileId().equals(know.getFileId())){
            Map maps= Maps.newHashMap();
            maps.put("fileId",know.getFileId());
            maps.put("fileName",know.getFileName());
            maps.put("id",map.get("id"));
            knowledgeBaseAttachmentService.update(maps);
        }


    }



}
