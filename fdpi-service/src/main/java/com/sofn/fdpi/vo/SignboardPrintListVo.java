package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import com.sofn.fdpi.model.SignboardPrintList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;


@Data
@ApiModel(value = "标识打印列表VO对象")
@ExcelSheetInfo(title = "打印明细列表", sheetName = "打印明细列表")
public class SignboardPrintListVo {

    @ExcelField(title = "标识编码")
    @ApiModelProperty(value = "标识编码")
    private String code;

    @ExcelField(title = "物种名称")
    @ApiModelProperty(value = "物种名称")
    private String speName;

    /**
     * 实体类转VO类
     */
    public static SignboardPrintListVo entity2Vo(SignboardPrintList entity) {
        SignboardPrintListVo vo = new SignboardPrintListVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }


}
