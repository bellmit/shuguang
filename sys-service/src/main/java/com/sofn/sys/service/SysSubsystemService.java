package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.vo.SysSubsystemForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 业务系统服务层
 *
 * Created by heyongjie on 2019/5/29 11:15
 */
public interface SysSubsystemService extends IService<SysSubsystem> {

    List<SysSubsystem> getSysSubsystemTreeById(String subsystemId);
    void addSysSubsystem(SysSubsystem sysSubsystem);

    void updateSysSubsystem(SysSubsystem sysSubsystem);

    /**
     * 删除行政区划内容
     * @param id
     */
    void deleteSysSubsystem(String id);

    /**
     * 检查SysSubsystem是否重复
     * @param name  名称
     * @param code  代码
     * @return  true 重复 false 不重复
     */
    boolean checkSysSubsystemIsExists(String name, String code, String id);
    SysSubsystem getSysSubsystemAppId(String appId);
    PageUtils<SysSubsystemForm> getSysSubsystemByContion(Map<String, Object> paramas, Integer pageNo, Integer pageSize);
    SysSubsystemForm getSysSubsystemTreeByUser();
    SysSubsystemForm getSysSubsystemTree(String subsystemName);
    void batchDeleteSubsystem (List<String>ids);

    List<SysSubsystem> getSubSystemByIds(String[] ids);

    List<String> getAllAppidList();


    /**
     * 根据Id或者AppId查询子系统
     * @param idOrAppId  Id或者AppId
     * @return 子系统信息
     */
    SysSubsystem getSubsystemByIdOrAppId(String idOrAppId);


    /**
     * 根据类型获取子系统
     * @param type  菜单类型
     * @return  List<SysSubsystemForm>
     */
    List<SysSubsystemForm> getSubsystemByType(String type);

    /**
     * 根据IDS更新子节点
     * @param ids   待更新的子节点ID
     * @param newParentIdPrefix  新的parentIds前缀
     * @param oldParentIdPrefix  旧的parentIds前缀
     */
    void updateParentIdsByIds(List<String> ids,  String newParentIdPrefix,
                               String oldParentIdPrefix);

    /**
     * 根据id集合获取系统列表
     * @param ids id集合
     */
    List<SysSubsystem> getSubsystemListByIds(Set<String> ids);

    /**
     * 获取全部系统列表
     */
    List<SysSubsystem> getAllSubsystemList();
}
