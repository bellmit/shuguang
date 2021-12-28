package com.sofn.flow.util;

import com.cvicse.workflow.api.*;

import java.sql.Connection;

/**
 * 获取连接工具
 * @author heyongjie
 * @date 2020/5/7 11:00
 */
public class GetWfClientUtil {

    /**
     * 获取WfClient连接
     * @param username   用户名称
     * @return  WfClient
     */
    public static WfClient getWfClientConnection(String username){
        WfConnectInfo conInfo = new WfConnectInfo(username);
        WfClientManager cm = WfClientManager.getInstance();
        WfClient client = cm.getWfClient();
        client.connect(conInfo);
        return client;
    }

    /**
     * 根据数据库连接获取WfClient
     * @param username   用户名称
     * @param connection   数据库连接
     * @return  WfClient
     */
    public static WfClient getWfClientConnection(String username, Connection connection){
        WfClient wfClient = WfClientManager.getInstance().getWfClient();
        wfClient.connect(new WfConnectInfo(username,connection));
        return wfClient;
    }

    /**
     * 根据数据库连接获取WfManagerClient
     * @param username
     * @param connection
     * @return
     */
    public static WfManagerClient getWfManageClientConnection(String username, Connection connection){
        WfManagerClient wfManagerClient = WfManagerClientFactory.getInstance().getWfManagerClient();
        wfManagerClient.connect(new WfConnectInfo(username,connection));
        return wfManagerClient;
    }

    /**
     * 关闭连接
     * @param wfClient   需要关闭的连接
     */
    public static void closeConnection(WfClient wfClient){
        if(wfClient != null){
            try{
                wfClient.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     * @param wfManagerClient   需要关闭的连接
     */
    public static void closeConnection(WfManagerClient wfManagerClient){
        if(wfManagerClient != null){
            try{
                wfManagerClient.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
