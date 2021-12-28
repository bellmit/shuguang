package com.sofn.wsclient;

import java.util.Date;

/**
 * Created by helong on 16-12-21.
 */
public class WsClientLog {

    public static enum ErrorType {
        对接方服务错误, 数据未订阅, 表未创建, 数据错误
    }

    WsPartner partner;
    String wsdl;
    ErrorType errorType;
    String errorMessage;
    boolean success;
    String operation;
    Date startTime;
    Date endTime;
}
