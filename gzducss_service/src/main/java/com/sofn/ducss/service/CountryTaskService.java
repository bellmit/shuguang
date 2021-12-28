package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.CountryTask;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.CountryTaskForm;
import com.sofn.ducss.vo.TapVo;

import java.util.List;
import java.util.Map;

/**
 * 获取当前年任务
 */
public interface CountryTaskService extends IService<CountryTask> {

    PageUtils<CountryTask> getTaskByPage(Integer pageNo, Integer pageSize, String countyId, String year);

    PageUtils<CountryTask> getMinistryTaskByPage(Integer pageNo, Integer pageSize, String year);

    /**
     * 根据条件查询年度任务列表（不分页）
     */
    List<CountryTask> getTaskByCondition(Map<String, Object> param);

    /**
     * 新增年度任务
     **/
    String addOrUpdateMinistryTask(CountryTask vo);

    String addAndIssueMinistryTask(CountryTask vo);

    String updateCountryTask(CountryTask vo);

    String supplyTask(String year);

    String issueMinistryTask(String id, String userId, String userName);

    String publishMinistryTaskInfo(String id, String userId, String userName);

    /**
     * 根据id获取年度任务
     **/
    CountryTaskForm getCountryTaskFormById(String id);

    /**
     * 删除年度任务
     **/
    Result deleteCountryTaskById(String id);

    Result examineCountryTask(CountryTask task, String userName, String userId);

    TapVo getTapVo(String year, String regioncode);

//    void insertOrUpdateSuperTaskNum(String year, String areaId);

    List<StrawUtilizeSum> selectDucStrawUtilizeSum(String areaId, String year);

    List<StrawUtilizeSum> selectDucStrawUtilizeSumStatus(String areaId, String year, List<String> status);

    /**
     * 获取已发布的年份数据
     *
     * @return 年份数据集合
     */
    List<String> getCountryTaskYearList();

    /**
     * 获取已下发、已发布的年份数据
     *
     * @return 年份数据集合
     */
    List<String> listYear();
}
