package com.sofn.agsjdm.vo.exportBean;


import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:48
 */
@Data
@ExcelSheetInfo(title = "设施监控管理", sheetName = "设施监控管理")
public class FacilityMainExcel {



        /**
         * 湿地区名称
         */
        @ExcelField(title = "湿地区名称")
        private String wetlandName;
        /**
         * 道路情况
         */
        @ExcelField(title = "道路情况")
        private String road;
        /**
         * 房屋数量
         */
        @ExcelField(title = "房屋数量")
        private String houseNum;
        /**
         * 房屋情况
         */
        @ExcelField(title = "房屋情况")
        private String house;
        /**
         * 仪器设备
         */

        @ExcelField(title = "仪器设备")
        private String instrEq;
        /**
         * 人员情况
         */
        @ExcelField(title = "人员情况")
        private String personSit;
        /**
         * 备注
         */
        @ExcelField(title = "备注")
        private String remarks;
        /**
         * 操作人
         */
        @ExcelField(title = "操作人")
        private String operator;
        /**
         * 操作时间
         */
        @ExcelField(title = "操作时间")
        private Date operatorTime;
}
