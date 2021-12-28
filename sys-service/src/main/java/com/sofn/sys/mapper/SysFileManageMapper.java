package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysFileManage;
import com.sofn.sys.vo.SysFileManageForm;
import com.sofn.sys.vo.SysFileManageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author heyongjie
 * @date 2019/6/5 15:56
 */
public interface SysFileManageMapper extends BaseMapper<SysFileManage> {

    /**
     * 按条件查询文件，只会查询出已经激活的并且接口号不是hidden开头的
     * @param params  fileName 文件名称  模糊查询
     *                fileType 文件类型  模糊查询
     *                createUserId 创建用户ID   绝对匹配
     *                systemId  系统ID   绝对匹配
     * @return    List<SysFileManageVo>
     */
    List<SysFileManageVo> getSysFileByCondition(Map<String, Object> params);

    /**
     * 激活文件
     * @param fileName   文件名称
     * @param systemId   所属系统
     * @param remark     备注
     * @param interfaceNum  接口号   hidden开头不会在文件管理中显示
     * @param ids   需要激活的文件
     * @param updateUserId   更新用户ID
     */
    void activationFile(@Param("fileName") String fileName, @Param("systemId") String systemId,@Param("remark") String remark,
                           @Param("interfaceNum")String interfaceNum,@Param("updateUserId")String updateUserId,@Param("ids") List<String> ids);

    /**
     * 激活文件
     * @param sysFileManageForm  文件信息
     * @param ids  待激活的文件ID
     * @param updateUserId    更新人ID
     */
    void activationFile2(@Param("sysFileManageForm")SysFileManageForm sysFileManageForm,
                         @Param("ids")List<String> ids,
                         @Param("updateUserId")String updateUserId);


    /**
     * 根据Id查询出符合条件的文件并且包含未激活的文件
     * @param ids  IDS
     * @return  List<SysFileManage>
     */
    List<SysFileManage> getSysFileByIdsIncludNotActivation(@Param("ids") List<String> ids);

    /**
     * 获取一个，未删除的，已激活的
     * @param id  ID
     * @return  SysFileManage
     */
    SysFileManage getOne(@Param("id") String id);

    /**
     * 批量删除文件
     * @param ids  需要删除的文件ID
     * @param updateUserId 更新用户
     */
    void batchDeleteFile(@Param("ids") List<String> ids,@Param("updateUserId") String updateUserId);

    /**
     * 查询出所有大于1天未激活的文件数量
     * @return   Long
     */
    long selectNotActivationCount(@Param("beforeDays") Integer beforeDays);

    /**
     * 查询出所有大于1天未激活的文件
     * @return  List<SysFileManage>
     */
    List<SysFileManage> selectNotActivationList(@Param("beforeDays") Integer beforeDays);

    /**
     * 将文件下载的次数加一
     * @param ids   下载的文件
     */
    void addFileDownlowdNum(@Param("ids") List<String> ids);


    /**
     * 获取文件名称的数量
     * @param fileName  文件名称
     * @param appId   系统代码
     * @param systemId   系统ID
     * @param fileId  当前文件ID
     * @return    文件数量
     */
    long getFileNameNum(@Param("fileName") String fileName,@Param("appId") String appId, @Param("systemId")String systemId,@Param("fileId") String fileId);

}
