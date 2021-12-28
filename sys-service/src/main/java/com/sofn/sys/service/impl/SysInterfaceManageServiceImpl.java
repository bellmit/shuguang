package com.sofn.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysInterParamEnum;
import com.sofn.sys.mapper.SysInterParamsMapper;
import com.sofn.sys.mapper.SysInterfaceManageMapper;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysInterfaceManageService;
import com.sofn.sys.vo.SysInterAllParVo;
import com.sofn.sys.vo.SysInterfaceManageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class SysInterfaceManageServiceImpl implements SysInterfaceManageService {

	@Autowired
	private SysInterfaceManageMapper sysInterfaceManageMapper;

	@Autowired
	private SysInterParamsMapper sysInterParamsMapper;

	@Override
	public List<SysInterfaceManageVo> getSysIntefaceContion(Map<String, Object> params) {
		List<SysInterfaceManage> sysInterfaceManageList = sysInterfaceManageMapper.findByCondition(params);
		//不分页转为vo
		List<SysInterfaceManageVo> sysInterfaceManageVoList = new ArrayList<>();
		SysInterfaceManageVo sysInterfaceManageVo = null;
		for (SysInterfaceManage sysInterfaceManage : sysInterfaceManageList) {
			sysInterfaceManageVo = new SysInterfaceManageVo();
			BeanUtils.copyProperties(sysInterfaceManage, sysInterfaceManageVo);
			sysInterfaceManageVoList.add(sysInterfaceManageVo);
		}

		return sysInterfaceManageVoList;
	}

	@Override
	public PageUtils<SysInterfaceManageVo> getSysIntefaceContion(Map<String, Object> params, Integer pageNo, Integer pageSize) {
		PageHelper.offsetPage(pageNo, pageSize);//开启分页


		List<SysInterfaceManage> sysInterfaceManageList = sysInterfaceManageMapper.findByCondition(params);
		PageInfo<SysInterfaceManage> sysInterfaceManagePageInfo = new PageInfo<>(sysInterfaceManageList);

		//转为vo
		PageInfo<SysInterfaceManageVo> sysInterfaceManageVoPageInfo = new PageInfo<>();
		BeanUtils.copyProperties(sysInterfaceManagePageInfo, sysInterfaceManageVoPageInfo);
		//设置值
		if (!CollectionUtils.isEmpty(sysInterfaceManageList)) {
			List<SysInterfaceManageVo> sysInterfaceManageVos = Lists.newArrayList();
			sysInterfaceManageList.forEach((sysInterfaceManage -> {
				SysInterfaceManageVo sysInterfaceManageVo = SysInterfaceManageVo.getSysInterfaceManageVo(sysInterfaceManage);
				sysInterfaceManageVos.add(sysInterfaceManageVo);
			}));
			sysInterfaceManageVoPageInfo.setList(sysInterfaceManageVos);
		}

		return PageUtils.getPageUtils(sysInterfaceManageVoPageInfo);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveSysInterfacePar(SysInterfaceManage sysInterfaceManage) {
		sysInterfaceManage.preInsert();//设置uuid
		sysInterfaceManage.setOperator(UserUtil.getLoginUserName());//设置操作人
		sysInterfaceManage.setOperateTime(new Date());
		sysInterfaceManageMapper.insert(sysInterfaceManage);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysInterfaceManage sysInterfaceManage) {
		sysInterfaceManage.preUpdate();
		sysInterfaceManage.setOperator(UserUtil.getLoginUserName());//设置操作人
		sysInterfaceManageMapper.update(sysInterfaceManage);
	}

	@Override
	public SysInterfaceManageVo getOne(String id) {
		SysInterfaceManage sysInterfaceManage = sysInterfaceManageMapper.getOne(id);
		if (sysInterfaceManage == null) {
			return null;
		}
		SysInterfaceManageVo sysInterfaceManageVo = SysInterfaceManageVo.getSysInterfaceManageVo(sysInterfaceManage);//将model转为vo
		return sysInterfaceManageVo;


	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	//软删除,修改标记
	public void deleteById(String id) {
		sysInterfaceManageMapper.deleteById(id);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchDeleteInter(List<String> idList) {


		sysInterfaceManageMapper.batchDelete(idList, UserUtil.getLoginUserId(), new Date());


	}


	/**
	 * 根据接口外键查询该接口请求参数详情
	 *
	 * @param interId
	 * @return
	 */
	@Override
	public SysInterAllParVo showReqParams(String interId) {
		//如果这个接口被删除
		String del=	sysInterfaceManageMapper.findStatus(interId);
		if (StringUtils.isEmpty(del) || StringUtils.equals(del, BoolUtils.Y )){
			return null;
		}

		List<SysInterBase> baseListList = sysInterParamsMapper.showReqParams(interId);
		ArrayList<SysInterParams> paramsList = new ArrayList<>();
		ArrayList<SysInterHeader> headerList = new ArrayList<>();
		ArrayList<SysInterJson> jsonList = new ArrayList<>();
		ArrayList<SysInterReturn> returnList = new ArrayList<>();
		SysInterAllParVo sysInterAllParVo = new SysInterAllParVo();

	//筛选进不同类型的集和中
		for (SysInterBase sysInterBase : baseListList) {
			String differentReq = sysInterBase.getDifferentReq();

			switch (differentReq) {
				case "0":
					SysInterParams sysInterParams = new SysInterParams();
					BeanUtils.copyProperties(sysInterBase,sysInterParams);
					paramsList.add(sysInterParams);
					break;
				case "1":
					SysInterHeader sysInterHeader = new SysInterHeader();
					BeanUtils.copyProperties(sysInterBase,sysInterHeader);
					headerList.add(sysInterHeader);
					break;
				case "2":
					SysInterJson sysInterJson =new SysInterJson();
					BeanUtils.copyProperties(sysInterBase,sysInterJson);
					jsonList.add(sysInterJson);
					break;
				case "3":
					SysInterReturn sysInterReturn = new SysInterReturn();
					BeanUtils.copyProperties(sysInterBase,sysInterReturn);
					returnList.add(sysInterReturn);
					break;
				default:
					log.error("查询参数异常");
			}

		}

		sysInterAllParVo.setSysInterParams(paramsList);
		sysInterAllParVo.setSysInterHeader(headerList);
		sysInterAllParVo.setSysInterJson(jsonList);
		sysInterAllParVo.setSysInterReturns(returnList);
		//清空base
		baseListList.clear();

		return sysInterAllParVo;

	}

	/**
	 * 根据字段id删除参数字段
	 *
	 * @param id
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteParamsById(String id) {
		sysInterParamsMapper.deleteById(id);

	}

	/**
	 * 修改请求参数字段
	 *
	 * @param sysInterAllParVo
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateParFields(SysInterAllParVo sysInterAllParVo) {
		//1、现根据接口id删除原有数据，
		sysInterParamsMapper.deleteByInterId(sysInterAllParVo.getInterId());

		batchCreate(sysInterAllParVo);//重新覆盖

	}


	/**
	 * 批量插入请求参数格式
	 *
	 * @param sysInterAllParVo
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void batchInsertParams(SysInterAllParVo sysInterAllParVo) {

		batchCreate(sysInterAllParVo);
	}

	private void batchCreate(SysInterAllParVo sysInterAllParVo) {
		List<SysInterParams> sysInterParams = sysInterAllParVo.getSysInterParams();//循环插入get参数
		if (sysInterParams != null && sysInterParams.size() > 0) {
			for (SysInterParams sysInterParam : sysInterParams) {
				sysInterParam.setId(IdUtil.getUUId());//设置id
				sysInterParam.setDifferentReq(SysInterParamEnum.DIFFERENT_REQ_GET.getCode());//get设为0
				sysInterParamsMapper.insert(sysInterParam);
			}
		}

		List<SysInterHeader> sysInterHeader = sysInterAllParVo.getSysInterHeader(); //循环插入header参数
		SysInterParams sysInterParamsCopy = null;
		if (sysInterHeader != null && sysInterHeader.size() > 0) {
			for (SysInterHeader interHeader : sysInterHeader) {
				interHeader.setId(IdUtil.getUUId());//设置id
				interHeader.setDifferentReq(SysInterParamEnum.DIFFERENT_REQ_HEADER.getCode());//header设为1
				sysInterParamsCopy = new SysInterParams();
				BeanUtils.copyProperties(interHeader, sysInterParamsCopy);//复制对象
				sysInterParamsMapper.insert(sysInterParamsCopy);

			}

		}

		List<SysInterJson> sysInterJson = sysInterAllParVo.getSysInterJson(); //循环插入请求体参数
		if (sysInterJson != null && sysInterJson.size() > 0) {
			for (SysInterJson interJson : sysInterJson) {
				interJson.setId(IdUtil.getUUId());
				interJson.setDifferentReq(SysInterParamEnum.DIFFERENT_REQ_BODY.getCode());//json设为2
				sysInterParamsCopy = new SysInterParams();
				BeanUtils.copyProperties(interJson, sysInterParamsCopy);//复制对象
				sysInterParamsMapper.insert(sysInterParamsCopy);
			}

		}

		List<SysInterReturn> sysInterReturns = sysInterAllParVo.getSysInterReturns();//循环插入请求体参数
		if (sysInterReturns != null && sysInterReturns.size() > 0) {
			for (SysInterReturn sysInterReturn : sysInterReturns) {
				sysInterReturn.setId(IdUtil.getUUId());
				sysInterReturn.setDifferentReq(SysInterParamEnum.DIFFERENT_REQ_RETURN.getCode());//json设为2
				sysInterParamsCopy = new SysInterParams();
				BeanUtils.copyProperties(sysInterReturn, sysInterParamsCopy);//复制对象
				sysInterParamsMapper.insert(sysInterParamsCopy);
			}

		}
	}


}