//package com.sofn.sys.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.sofn.common.exception.SofnException;
//import com.sofn.common.utils.IdUtil;
//import com.sofn.sys.enums.SysManageEnum;
//import com.sofn.sys.mapper.SysUserClickSystemNumMapper;
//import com.sofn.sys.model.SysSubsystem;
//import com.sofn.sys.model.SysUser;
//import com.sofn.sys.model.SysUserClickSystemNum;
//import com.sofn.sys.service.SysSubsystemService;
//import com.sofn.sys.service.SysUserClickSystemNumService;
//import com.sofn.sys.service.SysUserService;
//import com.sofn.sys.vo.SysSubsystemForm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * TODO： 暂时不使用
// * @author heyongjie
// * @date 2020/6/29 10:00
// */
//@Service
//public class SysUserClickSystemNumServiceImpl implements SysUserClickSystemNumService {
//
//    @Autowired
//    private SysUserClickSystemNumMapper sysUserClickSystemNumMapper;
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    @Autowired
//    private SysSubsystemService sysSubsystemService;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void addClickNum(String userId, String appId) {
//        // 1. 校验用户ID和系统ID是否存在
//        SysUser sysUser = sysUserService.getById(userId);
//        if(sysUser == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(sysUser.getDelFlag())){
//            throw new SofnException("用户不存在");
//        }
//        SysSubsystem sysSubsystemAppId = sysSubsystemService.getSysSubsystemAppId(appId);
//        if(sysSubsystemAppId == null){
//            throw new SofnException("系统不存在");
//        }
//        // 3. 如果当前系统下还有子系统不做点击加操作   TODO
//
//
//        // 2. 判断当前用户和系统是否已经在表中存在，如果不存在就添加记录，如果存在就取出数据然后做+1操作
//        SysUserClickSystemNum sysUserClickSystemNum = sysUserClickSystemNumMapper.selectOne(
//                new QueryWrapper<SysUserClickSystemNum>()
//                        .eq("USER_ID", userId)
//                        .eq("APP_ID", appId)
//        );
//        if(sysUserClickSystemNum != null){
//            sysUserClickSystemNum.setClickNum(sysUserClickSystemNum.getClickNum() + 1);
//            sysUserClickSystemNumMapper.updateById(sysUserClickSystemNum);
//        }else{
//            sysUserClickSystemNum = new SysUserClickSystemNum();
//            sysUserClickSystemNum.setId(IdUtil.getUUId());
//            sysUserClickSystemNum.setAppId(appId);
//            sysUserClickSystemNum.setUserId(userId);
//            sysUserClickSystemNum.setClickNum(Long.parseLong("1"));
//            sysUserClickSystemNumMapper.insert(sysUserClickSystemNum);
//        }
//    }
//
//    @Override
//    public List<SysSubsystemForm> getCommonSystems(int returnNum) {
//
//
//        return null;
//    }
//}
