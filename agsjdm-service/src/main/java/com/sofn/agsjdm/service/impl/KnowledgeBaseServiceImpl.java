package com.sofn.agsjdm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.agsjdm.api.SysFileApi;
import com.sofn.agsjdm.mapper.KnowledgeBaseAttachmentMapper;
import com.sofn.agsjdm.mapper.KnowledgeBaseMapper;
import com.sofn.agsjdm.model.KnowledgeBase;
import com.sofn.agsjdm.model.KnowledgeBaseAttachment;
import com.sofn.agsjdm.service.KnowledgeBaseAttachmentService;
import com.sofn.agsjdm.service.KnowledgeBaseService;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.KnowledgeVo;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public int save(KnowledgeVo k) {
        Map map1= Maps.newHashMap();
        map1.put("knowledge",k.getKnowledge());
        map1.put("documentId", k.getDocumentId());
        KnowledgeBase nameAndType = knowledgeBaseMapper.getNameAndType(map1);
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        if (nameAndType==null) {
            KnowledgeBase know=new KnowledgeBase();
            BeanUtils.copyProperties(k,know);
            know.setId(IdUtil.getUUId());
            String[] regions = RegionUtil.getRegions();
            know.setProvince(regions[0]);
            know.setCity(regions[1]);
            know.setCounty(regions[2]);
            Map maps= Maps.newHashMap();
            int save = knowledgeBaseMapper.insert(know);
            KnowledgeBaseAttachment baseAttachment=new KnowledgeBaseAttachment();
            baseAttachment.setKnowledgeId(know.getId());
            baseAttachment.setFileId(k.getFileId());
            baseAttachment.setFileName(k.getFileName());
            baseAttachment.setId(IdUtil.getUUId());
            int  i=knowledgeBaseAttachmentService.save(baseAttachment);
            return  1;
        }
        return 0;
    }

    @Override
    public PageUtils<KnowledgeBase> list(Map map, int pageNo, int pageSize) {
        //完善区域查询参数
        RegionUtil.regionParams(map);
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
        return knowledgeBaseMapper.selectById(id);
    }

    @Override
    public int del(String id) {

        knowledgeBaseAttachmentService.del(id);
        return knowledgeBaseMapper.deleteById(id);
    }

    @Override
    public void update(KnowledgeVo know) {
        //检查添加级别是不是县级
        RegionUtil.checkAddLevel();
        KnowledgeBase know1=new KnowledgeBase();
        BeanUtils.copyProperties(know,know1);
        int update = knowledgeBaseMapper.updateById(know1);
        KnowledgeBaseAttachment one = knowledgeBaseAttachmentService.getSource(know.getId());
        if (!one.getFileId().equals(know.getFileId())){
            Map maps= Maps.newHashMap();
            maps.put("fileId",know.getFileId());
            maps.put("fileName",know.getFileName());
            maps.put("id",know.getId());
            knowledgeBaseAttachmentService.update(maps);
        }


    }



}
