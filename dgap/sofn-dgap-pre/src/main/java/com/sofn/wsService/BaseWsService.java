package com.sofn.wsService;


import com.sofn.model.dgap.SoapXml;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * author tianjie
 *  webservice的基类
 * @date 2016/10/21
 */
public abstract class BaseWsService {

    private static String  REPEAT_STRING="ACTIVE";

    public String heartbeatMessage(){
        return BaseWsService.REPEAT_STRING;
    }

}
