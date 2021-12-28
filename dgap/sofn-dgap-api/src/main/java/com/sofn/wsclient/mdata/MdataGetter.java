package com.sofn.wsclient.mdata;

import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.RowData;

import java.util.Date;

/**
 * Created by helong on 16-12-21.
 */

public interface MdataGetter {

    String wsDataStausFieldName="dataStatus";
    /*List<RowData> getMdata(String dataSetId, Date startDate, Date endDate);*/

    static interface RowDataProcessor {

        enum Op {
            添加("0", Constants.DataImportOp.添加.code()),
            修改("1",Constants.DataImportOp.修改.code()),
            删除("2",Constants.DataImportOp.删除.code());
            private String wsStr;
            private String dbStr;

            public String getwsStr(){
                return wsStr;
            }

            public String getDbStr(){
                return dbStr;
            }

            Op(String wsStr, String dbStr){
                this.wsStr = wsStr;
                this.dbStr = dbStr;
            }
        }

        /**
         * 将数据写入到数据导入资源的暂存库的暂存表中
         *
         * @param resourceId
         * @param op
         * @param data
         * @return
         */
        boolean logData(String resourceId, Op op, RowData data);

        /**
         * 将数据应用到主题库的主题表中
         *
         * @param resourceId
         * @param op
         * @param data
         * @return
         */
        //boolean applyData(String resourceId, Op op, RowData data);
    }

    /**
     * 获取主数据的代码集的接口
     *
     * @param config
     * @param startDate
     * @param endDate
     * @param processor
     *
     * 1.从config读取ws的配置信息，传入查询数据的起始和截至日期，获取到数据
     * 2.解析返回的json数据，转换成RowData类型的数据和Op操作类型
     * 3.调用RowDataProcessor.logData接口，将接收到的数据写入暂存库
     */
    void getMdataCodeSets(MdataWsClientConfig config, Date startDate, Date endDate, RowDataProcessor processor);

    /**
     * 获取主数据的代码数据的接口
     * @param config
     * @param startDate
     * @param endDate
     * @param processor
     */
    void getMdataCodes(MdataWsClientConfig config, Date startDate, Date endDate, RowDataProcessor processor);
}
