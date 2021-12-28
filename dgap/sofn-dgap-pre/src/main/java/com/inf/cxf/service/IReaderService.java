package com.inf.cxf.service;

import javax.jws.WebService;

/**
 * @author HYL
 * @date 2018/8/30 13:31
 */
@WebService(name="test")
public interface IReaderService {


    /**
     * 查询有机食品获证企业信息
     *
     * @param startdate
     * @param enddate
     * @return
     */
    String GetBasePermitEnt(String startdate, String enddate);

    //获得有机食品获证产品信息
    String GetTraceProduct(String startdate, String enddate);

    //获得有机食品暂停产品信息
    String GetPauseProduct(String startdate, String enddate);

    //获得有机食品注销产品信息
    String GetCancelProduct(String startdate, String enddate);

    //获得有机食品撤销产品信息
    String GetRevokeProduct(String startdate, String enddate);

}
