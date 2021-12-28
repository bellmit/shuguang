package com.sofn.model.dgap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by helong on 16-10-31.
 */
public class Constants {

    public final static String DEL_FLAG_YES = "Y";
    public final static String DEL_FLAG_NO = "N";

    public final static String ALERT_TYPE_DEFAULT = "NONE";

    public final static String PAGE_SIZE = "pageSize";
    public final static String PAGE_NUM = "pageNum";

    /**
     * web service错误类型
     */
    public static enum WSErrorType {
        SYSTEM_ERR("100000000", "系统错误", "系统错误"),
        AUTH_ERROR("100000001", "认证错误", "认证错误"),
        DATA_ERROR("100000005", "外部数据错误", "外部数据错误"),
        DATALEN_ERROR("100000006", "数据量过大错误", "数据量过大错误"),/*,
        ERR_NET_TIME_OUT("1000000002", "网络超时", "网络超时"),
        ERR_AUTH_CODE("1000000003", "授权码（authCode）错误", "授权码（authCode）错误")*/;

        private String errorCode;

        private String errorInnerDesc;

        private String errorOutDesc;

        WSErrorType(String errorCode, String errorInnerDesc, String errorOutDesc) {
            this.errorCode = errorCode;
            this.errorInnerDesc = errorInnerDesc;
            this.errorOutDesc = errorOutDesc;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorInnerDesc() {
            return errorInnerDesc;
        }

        public String getErrorOutDesc() {
            return errorOutDesc;
        }
    }

    /**
     * 导入数据操作名称
     */
    public static enum DataImportOp {
        添加("addData"), 修改("updateData"), 删除("deleteData"),批量添加("addBatch"),添加日志("addDataLog");
        private String code;

        public String code() {
            return code;
        }

        DataImportOp(String code) {
            this.code = code;
        }

    }

    /**
     * 导入数据错误类型
     */
    public static enum ImportDataErrorType {
        DATA_ERROR(WSErrorType.DATA_ERROR.getErrorCode(), WSErrorType.DATA_ERROR.getErrorInnerDesc(), WSErrorType.DATA_ERROR.getErrorOutDesc()),
        SYSTEM_ERR(WSErrorType.SYSTEM_ERR.getErrorCode(), WSErrorType.SYSTEM_ERR.getErrorInnerDesc(), WSErrorType.SYSTEM_ERR.getErrorOutDesc()),
        DATALEN_ERROR(WSErrorType.DATALEN_ERROR.getErrorCode(),WSErrorType.DATALEN_ERROR.getErrorInnerDesc(),WSErrorType.DATALEN_ERROR.getErrorOutDesc()),
        TABLE_REPEAT_ERROR("1000000005","名称已由现有对象使用","表名重复");

        private String errorCode;

        private String errorInnerDesc;

        private String errorOutDesc;

        ImportDataErrorType(String errorCode, String errorInnerDesc, String errorOutDesc) {
            this.errorCode = errorCode;
            this.errorInnerDesc = errorInnerDesc;
            this.errorOutDesc = errorOutDesc;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorInnerDesc() {
            return errorInnerDesc;
        }

        public String getErrorOutDesc() {
            return errorOutDesc;
        }
    }

    /**
     * 资源状态
     */
    public static enum ResourceStatus {
        可用("A"), 不可用("N");

        private String code;

        public String code() {
            return code;
        }

        ResourceStatus(String code) {
            this.code = code;
        }
    }

    /**
     * 资源申请的审批状态
     */
    public static enum ResourceApplicationPassOrNot {
        通过("Y"), 未通过("N");
        private String code;

        public String code() {
            return code;
        }

        ResourceApplicationPassOrNot(String code) {
            this.code = code;
        }
    }

    /**
     * 资源类型
     */
    public static enum ResourceType {
        数据导入("di", true), 仓库资源("ds", true)/*, 文件导入("fi",true), 文件导出("fe",true)*/;

        private String code;
        private boolean available;

        public String code() {
            return getCode();
        }

        ResourceType(String code, boolean available) {
            this.setCode(code);
            this.setAvailable(available);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }


    /**
     * 数据导入表存在与否的状态
     */
    public static enum DataImportTableStatus {
        存在("AE"), 不存在("NE");
        private String code;

        public String code() {
            return code;
        }

        DataImportTableStatus(String code) {
            this.code = code;
        }
    }

    /**
     * 数据导入表存在与否的状态
     */
    public static enum DataImportFieldStatus {
        存在("AE"), 不存在("NE"), 默认("DEF");
        private String code;

        public String code() {
            return code;
        }

        DataImportFieldStatus(String code) {
            this.code = code;
        }
    }

    /**
     * 数据导入数据是否审核通过的的状态
     */
    public static enum DataImportStatus {
        人工审核通过("Y"), 人工审核不通过("N"), 未审核("U"), 自动导入("A");
        private String code;

        public String code() {
            return code;
        }

        DataImportStatus(String code) {
            this.code = code;
        }
    }

    /**
     * 数据导入数据是否审核通过的的状态
     */
    public static enum DataNotifier {
        无需通知("none"),
        数据采集通知("lp_to_localplatform"),
        数据下发通知("lp_to_gjzspt_zhuisu");
        private String code;

        public String code() {
            return code;
        }

        DataNotifier(String code) {
            this.code = code;
        }
    }

    public static enum PresetField {
        表ID("ID"),
        状态("STATUS"),
        操作("OPREATE"),
        数据ID("DATAID"),
        访问令牌("TOKEN"),
        数据导入时间("DATA_IMPORT_TIME"),
        序列号("MARK");
        private String code;

        public String code() {
            return code;
        }

        PresetField(String code) {
            this.code = code;
        }
    }

    public static enum SpybData {
        有机("youji"),
        绿色("lvse");
        
        private String code;

        public String code() {
            return code;
        }

        SpybData(String code) {
            this.code = code;
        }
    }

    /**
     * 字段类型
     */
    public static enum FieldType {

        文本("varchar", true, 4000, 1, "支持中文、字母、数字、“-”“_”的组合"), 数值("integer", true, 38, 1, "形如458668"),
        小数("float", true, 38, 1, "包含整数部分和小数部分,形如5.2"), 时间("time", false, null, null, "包含时分秒的信息，形如2016-12-05 13:10:10 "),
        时间戳("timestamp", false, null, null, "1481011781227"),
        二进制数据("BLOB", false, null, null, "二进制大文件，是一张图片或一个声音文件");
        private String type;
        private boolean hasLength;
        private Integer maxLength;
        private Integer minLength;
        private String description;


        public String type() {
            return type;
        }

        public boolean hasLength() {
            return hasLength;
        }

        public Integer maxLength() {
            return maxLength;
        }

        public Integer minLength() {
            return minLength;
        }

        public String description() {
            return description;
        }

        FieldType(String type, boolean hasLength, Integer maxLen, Integer minLen, String description) {
            this.type = type;
            this.hasLength = hasLength;
            this.maxLength = maxLen;
            this.minLength = minLen;
            this.description = description;
        }

        public Map<String, Object> getMpValues() {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("type", this.type);
            map.put("hasLength", this.hasLength);
            map.put("maxLength", this.maxLength);
            map.put("minLength", this.minLength);
            map.put("description", this.description);
            return map;
        }


    }

    public static enum Features {
        Spybdj_e2g("feature_spybdj_e2g"),Spybdj_g2z("feature_spybdj_g2z"), Dfptdj("feature_dfptdj"),
        Dgappre("feature_dgappre"), Dgappre_csv("feature_dgappre_csv");

        private String logTag;

        public String logTag(){
            return logTag;
        }

        Features(String logTag) {
            this.logTag = logTag;
        }
    }

    public final static String RESOURCE_ID_DFZSPTDJ = "dfzsptdj-00000000000000000000000000000000000000000";
    public final static String RESOURCE_DIR_ID_DFZSPTDJ = "dfzsptdj-00000000000000000000000000000000000000000";

    public static final String getDataImportWsdlUrl(String prefix){
        return prefix+"/ws/dataImport?wsdl";
    }
    public static final String getDataExportWsdlUrl(String prefix){
        return prefix+"/ws/dataExport?wsdl";
    }

}
