package com.sofn.model.dgap;/**
 * Created by Administrator on 2018/8/16 0016.
 */

/**
 * 常量类
 *
 * @author
 * @create 2018-08-16 上午 9:28
 **/

public class DataConstans {

    /**
     * 导入数据 默认预设字段
     */
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

    /**
     * 导入数据的操作
     */
    public static enum ImportOperate {
        新增("insertData"),
        更新("updateData"),
        删除("deleteData"),
        批量插入("addBatch");
        private String code;

        public String code() {
            return code;
        }

        ImportOperate(String code) {
            this.code = code;
        }
    }


}
