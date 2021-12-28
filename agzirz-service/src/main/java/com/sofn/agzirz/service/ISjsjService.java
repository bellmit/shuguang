package com.sofn.agzirz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirz.model.Sjsj;
import com.sofn.common.utils.PageUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 事件收集模块 服务类
 * </p>
 *
 * @author simon
 * @since 2020-03-04
 */
public interface ISjsjService extends IService<Sjsj> {

    PageUtils<Sjsj> getSjsjPageList(Integer pageNo,Integer pageSize, String disposalOrgani, String eventLocation, Date startTime, Date endTime);

    List<Sjsj> getSjsjList(String disposalOrgani, String eventLocation, Date startTime, Date endTime);

    int saveOrupdateSjsj(Sjsj model);

    boolean removeSjsjById(String sjsjNo);

    Sjsj getSjsjShowById(String sjsjNo);
}
