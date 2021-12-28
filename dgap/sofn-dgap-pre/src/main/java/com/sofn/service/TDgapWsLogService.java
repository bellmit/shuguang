package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapWsErrorLog;
import com.sofn.model.generator.TDgapWsLog;
import com.sofn.provider.dgap.TDgapResourceProvider;
import com.sofn.provider.dgap.TDgapWsErrorLogProvider;
import com.sofn.provider.dgap.TDgapWsLogProvider;
import com.sofn.util.Constant;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author tianjie
 *
 * @date 2016/10/20
 */
@Service
public class TDgapWsLogService {

    @DubboReference
    private TDgapWsLogProvider tDgapWsLogProvider;

    @DubboReference
    private TDgapWsErrorLogProvider tDgapWsErrorLogProvider;

    @DubboReference
    private TDgapResourceProvider tDgapResourceProvider;

    public int insertWsLog(TDgapWsLog tDgapWsLog){

        TDgapResource tDgapResource = tDgapResourceProvider.getDgapResource(tDgapWsLog.getWebServiceId());
        if(tDgapResource != null) {
            tDgapWsLog.setWebServiceId(tDgapResource.getId());
            tDgapWsLog.setResourceDir(tDgapResource.getDirectoryId());
            tDgapWsLog.setWebServiceName(tDgapResource.getName());
        }
        return tDgapWsLogProvider.insertInvokeLog(tDgapWsLog);
    }

    public int insertWsErrLog(TDgapWsErrorLog tDgapWsErrorLog){
        TDgapResource tDgapResource = tDgapResourceProvider.getDgapResource(tDgapWsErrorLog.getWebServiceId());
        if(tDgapResource==null){
            tDgapWsErrorLog.setResourceDirectoryId(null);
            tDgapWsErrorLog.setWebServiceName(null);
        }else{
            tDgapWsErrorLog.setWebServiceName(tDgapResource.getName());
            tDgapWsErrorLog.setResourceDirectoryId(tDgapResource.getDirectoryId());
        }

        return tDgapWsErrorLogProvider.insertWsErrorLog(tDgapWsErrorLog);
    }
}
