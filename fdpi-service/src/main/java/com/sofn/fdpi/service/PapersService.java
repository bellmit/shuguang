package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.AuditProcess;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.vo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface PapersService extends IService<Papers> {


    /**
     * 插入人工繁育许可证
     */
    int saveArt(ArtPaperFrom artPaperFrom);

    /**
     * 已经修改
     * 修改人工繁育证书
     */
    int updateArt(ArtPaperFrom artPaperFrom);

    /**
     * 添加经营利用许可证
     *
     * @param papersForm
     */

    int saveMan(PapersForm papersForm);

    /**
     * 修改经营许可证
     */
    int updateMan(PapersForm papersForm);

    /**
     * 已修改
     * 通过证书编号查看证书详细信息
     *
     * @param papersNumber
     * @return
     */
    Papers selectPaperInfoById(String papersNumber);


    /**
     * 移除证书
     *
     * @param id
     * @return
     */
    int delPapers(String id);

    /**
     * 查询当前部门下的企业人工繁殖/驯养许可证书信息
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<PapersListVo> getArtificialPaperListPage(Map<String, Object> map, int pageNo, int pageSize);


    /**
     * 查询当前部门下的经营许可证书信息
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<PapersListVo> getManagementPaperListPage(Map<String, Object> map, int pageNo, int pageSize);


    /**
     * 获取证书下拉列表
     * wuXY
     * 2019-12-30 16:42:44
     *
     * @param map 证书类型：1：人工繁育；2：驯养繁殖；3：经营利用
     * @return List<SelectVo>
     */
    List<SelectVo> listPapersForSelect(Map<String, Object> map);

    /**
     * 获取证书绑定申请列表
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param map 查询阐述
     * @return 返回分页数据
     */
    PageUtils<PapersVo> listForBinding(Map<String, Object> map, Integer pageNo, Integer pageSize);

    /**
     * 新增证书绑定申请保存
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersBindingForm 表单
     * @return 返回1：成功；其他：异常
     */
    String saveForBinding(PapersBindingForm papersBindingForm, String status);

    /**
     * 上报——证书绑定申请列表中上报功能
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersId 证书编号
     * @return 返回1：成功；其他：异常
     */
    String reportForBinding(String papersId, String status);

    /**
     * 根据id获取证书信息（编辑获取数据）
     * wuXY
     * 2019-12-31 11:32:21
     *
     * @param papersId 证书编号
     * @return EnterpriseForm
     */
    PapersBindingVo getPapersInfo(String papersId);


    /**
     * 根据id或者证书信息（编辑或者详情使用），返回列表是可能有多证书（注册多证书）
     * wuXY
     * 2020-11-4 16:32:35
     *
     * @param papersId 证书编号
     * @return 返回证书信息列表
     */
    List<PapersBindingVo> papersListForView(String papersId);

    /**
     * 获取证书绑定审核列表
     * wuXY
     * 2020-1-2 10:08:57
     *
     * @param map 查询参数
     * @return list
     */
    PageUtils<PapersVo> listForBindingApprove(Map<String, Object> map);

    /**
     * 证书审核
     * wuXY
     * 2020-1-2 10:51:06
     *
     * @param processForm 审核表单
     * @param isApprove   1:审核，0：退回
     * @return 1：成功；其它：提示
     */
    String approveOrBack(ProcessForm processForm, String isApprove);

    /**
     * 获取证书审核/退回意见
     * wuXY
     * 2020-1-2 10:51:06
     *
     * @param papersId 证书编号
     * @param status   状态
     * @return AuditProcess
     */
    AuditProcess getAuditProcessByPapersId(String papersId, String status);

    /**
     * 获取证书审核/退回意见 流程组件
     *
     * @return AuditProcess
     */
    AuditProcess getAuditProcessByPapersIdInfo(String papersId, String status);

    //    /**
//     * 证书信息-》修改证书文件
//     * @param papersFileForm 证书文件表单对象
//     * @return 1：成功；其他：提示异常
//     */
    String updatePapersFile(PapersFileForm papersFileForm);

    //证书绑定申请中删除功能
    String deleteBindingByPapersId(String papersId);

    String importArt(List<PaperExcel> importList);

    String importMan(List<PaperExcel> importList);

    Papers getPapersById(String papersId);


    /**
     * 打印证书列表
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<PaperPrintVo> listPrint(Map<String, Object> map, int pageNo, int pageSize);

    Papers print(String id);
//    Papers copyPrint(String id,String type);

    /**
     * @param map      模糊查询 人工繁育和特许证的 公司名
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<LicenceVo> licence(Map<String, Object> map, int pageNo, int pageSize);

    int delLicence(String id, String papersType);


    /**
     * 通过企业名称获取最近一次证书中企业的信息
     * wuXy
     * 2020-11-4 10:56:59
     *
     * @param compName 企业名称
     * @return 企业对象
     */
    CompInRegisterVo getCompByCompName(String compName);

    /**
     *
     */
    void setPaperCache();

    String getTodayMaxApplyNum(String todayStr);

    /**
     * 提示到期
     */
    String promptingExpire();

    /**
     * @param id
     * @return void
     * @author wg
     * @description 证书申请变更撤回
     * @date 2021/4/8 16:04
     */
    void cancel(String id);

    /**
     * 查看有效有证书列表
     */
    List<Papers> listEnablePapers();

    PapersVo paperInfo(String id);

    void downArtPaperTemplate(HttpServletResponse response);

    void downManPaperTemplate(HttpServletResponse response, String paperType);

    /**
     * 无证书获取证书编号
     */
    String getPapersNumber();

    List<PapersVo> getCurrentPapers(String compId);

    void exportArt(Map<String, Object> params, HttpServletResponse hsr);

    void exportMan(Map<String, Object> params, HttpServletResponse hsr);

    List<Papers> listPaperByCompId(String compId);

    int delByCompId(String compId);

    List<SelectVo> getUnitsByType(String speType);
}
