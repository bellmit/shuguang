package com.sofn.agpjpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agpjpm.mapper.FileAttMapper;
import com.sofn.agpjpm.model.FileAtt;
import com.sofn.agpjpm.model.FileManage;
import com.sofn.agpjpm.service.FileAttService;
import com.sofn.agpjpm.sysapi.SysFileApi;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.vo.AttFrom;
import com.sofn.agpjpm.vo.FileAttForm;
import com.sofn.agpjpm.vo.PicFrom;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-16 17:36
 */
@Service("fileAttService")
public class FileAttServiceImpl implements FileAttService {
    @Autowired
   private FileAttMapper fileAttMapper;
    @Resource
    private FileUtil fileUtil;
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public void insert(FileAttForm f,String sourceId) {
        FileAtt  att=new FileAtt();
        PicFrom pic = f.getPic();
        AttFrom att1 = f.getAtt();
        BeanUtils.copyProperties(pic,att);
        BeanUtils.copyProperties(att1,att);
        att.setId(IdUtil.getUUId());
        att.setSourceId(sourceId);
        int insert = fileAttMapper.insert(att);

    }

    @Override
    public List<FileAtt> getBySouceId(String souceId) {
        QueryWrapper<FileAtt> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", souceId);
        List<FileAtt> fileAtt = fileAttMapper.selectList(queryWrapper);
        return fileAtt;
    }
    @Transactional
    @Override
    public void updateList(List<FileAttForm> list,String sourceId) {
        QueryWrapper<FileAtt> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId);
        List<FileAtt> fileAtt = fileAttMapper.selectList(queryWrapper);
//        过去拥有的文件附件id
        List<String> oldlist=new ArrayList<>(fileAtt.size());
        for (FileAtt f:
             fileAtt) {
            oldlist.add(f.getId());
        }
//        现在拥有的文件附件id
        List<String> newList=new ArrayList<>();
        for (FileAttForm f1:
             list) {
            if (f1.getId()!=null){
                newList.add(f1.getId());
            }
                update(f1,sourceId);
        }
//        删除不需要的文件附件id
        oldlist.removeAll(newList);
        for (String id:
             oldlist) {
            del(id);
        }

    }
    @Transactional
    @Override
    public void del(String id) {
        FileAtt fileAtt = fileAttMapper.selectById(id);
        sysFileApi.delFile(fileAtt.getAttId());
        sysFileApi.delFile(fileAtt.getPicId());
        fileAttMapper.deleteById(id);
    }
    @Transactional
    @Override
    public void update(FileAttForm f, String sourceId) {
        FileAtt ft=new FileAtt();
        PicFrom pic = f.getPic();
        AttFrom att1 = f.getAtt();
        BeanUtils.copyProperties(pic,ft);
        BeanUtils.copyProperties(att1,ft);
        ft.setSourceId(sourceId);
        if (f.getId()!=null){
            ft.setId(f.getId());
            FileAtt fileAtt = fileAttMapper.selectById(f.getId());
            if (fileAtt!=null){
//                原有附件id
                String oldattId = fileAtt.getAttId();
//                现有附件id
                String newattId = f.getAtt().getAttId();
                if (oldattId!=null&&newattId!=null&&!oldattId.equals(newattId)){
                    sysFileApi.delFile(oldattId);
                    fileUtil.activationFile(newattId);
                }
//                 原有图片id
                String oldPicId = fileAtt.getPicId();
//                现有图片id
                String newPicId = f.getPic().getPicId();
                if (oldPicId!=null&&newPicId!=null&&!oldPicId.equals(newPicId)){
                    sysFileApi.delFile(oldPicId);
                    fileUtil.activationFile(newPicId);
                }
                fileAttMapper.updateById(ft);
            }
        }else {

            insert(f,sourceId);
            StringBuilder ids = new StringBuilder();
            ids.append(","+f.getAtt().getAttId());
            ids.append(","+f.getPic().getPicId());
            if (StringUtils.hasText(ids.toString())) {
                fileUtil.activationFile(ids.toString().substring(1));
            }

        }

    }
    @Transactional
    @Override
    public void delBySourceId(String sourceId) {
        QueryWrapper<FileAtt> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("source_id", sourceId);
        List<FileAtt> fileAtt = fileAttMapper.selectList(queryWrapper);
        for (FileAtt att:
             fileAtt) {
            del(att.getId());
        }
    }
}
