package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.vo.SysFileManageVo;
import com.sofn.ducss.model.SysFileManage;
import com.sofn.ducss.model.SysFileManageForm;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * 文件管理相关接口
 *
 * @author heyongjie
 * @date 2019/6/5 15:58
 */
public interface SysFileManageService extends IService<SysFileManage> {

    /**
     * 按条件分页查询信息
     * @param params  查询条件
     * @param pageNo   开始条数
     * @param pageSize   每页显示条数
     * @return
     */
    PageUtils<SysFileManageVo> getSysFileByConditionAndPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 批量激活文件，这一批文件应该是同属一个子系统并且是同一个接口号传入的
     * @param sysFileManageForm   激活文件对象
     * @param token  用户TOKEN  用户获取登录用户ID
     * @return   文件信息
     */
    List<SysFileVo> activationFile(SysFileManageForm sysFileManageForm, String token);

    /**
     * 仅仅上传文件
     * @param multipartFile   上传的文件
     * @param token    用户登录TOKEN
     * @return  文件信息
     */
    SysFileVo uploadFile(MultipartFile multipartFile,String token);

    /**
     * 删除文件
     * @param token   用户登录TOKEN
     * @param id  文件ID
     */
    void deleteFile(String id,String token);

    /**
     * 下载文件
     * @param id  文件ID
     * @param token    用户登录TOKEN
     * @return   文件信息
     */
    Map<String,String> downloadFile(String id,String token );

    /**
     * 替换文件
     * @param id   文件ID
     * @param fileName   文件名称
     * @param remark    备注
     * @param newId   新文件Id
     */
    void replaceFile(String id,String fileName, String remark,String newId,String token);

    /**
     * 展示历史数据
     * @param id   ID
     * @return  Object
     */
    Object showRecords(String id);

    /**
     * 获取文件真实路径
     * @param id  文件ID
     * @return  http://127.0.0.1:8881/path/...
     */
    String getFileRealPath(String id);

    /**
     * 批量下载文件
     * @param strIds   需要下载的文件集合
     * @param token    用户登录TOKEN
     * @param fileName   文件名称
     * @return  压缩文件路径
     */
    String batchDownloadFileByZip(String strIds,String token,String fileName);

    /**
     * 根据ID获取一个文件信息
     * @param id  ID
     * @return  SysFileManageVo
     */
    SysFileManageVo getOne(String id);

    /**
     * 批量删除文件
     * @param fileIds  需要删除的文件集合 保留
     * @param token   登录用户TOKEN
     */
    void batchDelete(List<String> fileIds,String token);

    /**
     * 根据ID集合查询数据
     * @param ids  需要查询的文件ID
     * @return   List<SysFileManageVo>
     */
    List<SysFileManageVo> getFileInfoByIds(List<String> ids);

    /**
     * 删除没有激活的文件
     * @return   删除的数据条数
     */
    long deleteNotActivationFile(Integer beforeDays);

    /**
     * 更新文件名
     * @param id  文件ID
     * @param name  更新后的文件名
     * @param token  登录用户TOKEN
     */
    void updateFileName(String id,String name,String token);

    /**
     * 根据时间差获取已经删除的文件
     * @date    时间差 如30天以前的
     * @return   应该删除的文件
     */
    List<SysFileManage> getDeleteFileByDate(int date);

    /**
     * 更新文件
     * @param token     用户TOKEN
     * @param sysFileManageForm   更新文件对象
     */
    void updateFileInfo(SysFileManageForm sysFileManageForm,String token);


}
