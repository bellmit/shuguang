package com.sofn.ducss.vo;

import com.sofn.ducss.model.ThresholdValueManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 阈值管理
 * 测试提交
 */
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class ThresholdValueManagerVo {

    private String id;

    @ApiModelProperty("year")
    @NotEmpty(message = "年度必传")
    @Length(max = 4, min = 4,message = "年度4位")
    private String year;

    @ApiModelProperty(" 表类型  1.数据审核 2. 产生情况汇总 3. 利用情况汇总 4.还田离田情况")
    @NotEmpty(message = "表类型不能为空")
    @Length(max = 1, min = 1,message = "表类型1位")
    private String tableType;

    /**
     * 指标类型
     * 1-1.产生量（万吨）
     * 1-2.可收集量（万吨）
     * 1-3.综合利用量（万吨）
     * 1-4.综合利用率（%）
     * 1-5. 直接还田量（万吨）
     * 1-6. 离田利用量（万吨）
     * 1-7. 抽样分散户数（个
     * 1-8.市场主体规模化数量（个）
     *
     * 2-1.产生量（万吨）
     * 2-2.产生量占比（%）
     * 2-3.可收集量（万吨）
     * 2-4.可收集量比例（%）
     * 2-5.粮食产量（万吨）
     * 2-6.播种面积（千公顷）
     *
     * 3-1.秸秆利用量（万吨）
     * 3-2 综合利用率（%）
     * 3-3.合计
     * 3-4.肥料化
     * 3-5 饲料化
     * 3-6. 燃料化
     * 3-7.基料化
     * 3-8.原料化
     * 3-9.综合利用能力指数
     * 3-10.产业化利用能力指数
     *
     * 4-1.直接还田量（万吨）
     * 4-2.直接还田率（%）
     * 4-3.合计
     * 4-4.农户分散利用量
     * 4-5.市场主体规模化利用量
     */
    @ApiModelProperty("指标类型:1-1.产生量（万吨） " +
            "1-2.可收集量（万吨）" +
            "1-3.综合利用量（万吨）1-4.综合利用率（%） 1-5. 直接还田量（万吨 ） 1-6. 离田利用量（万吨）" +
            " 1-7. 抽样分散户数（个  1-8.市场主体规模化数量（个）2-1.产生量（万吨）...... 按照原型图中的顺序编号 ")
    @NotEmpty(message = "指标类型不能为空")
    @Length(max = 4, min = 3, message = "指标类型3位：x-x格式" )
    private String targetType;

    @ApiModelProperty("指标类型名称")
    private String targetTypeName;

    @ApiModelProperty("计算类型：，1 数值 2 比例")
//    @NotEmpty(message = "计算类型不能为空")
    @Length(max = 2, message = "计算类型，1 数值 2 比例" )
    private String computerType;

    @ApiModelProperty("阈值1")
//    @Length(max=10, message = "阈值只能小于9999999.99万吨")
    private String value1;

    @ApiModelProperty("操作1, 原型图应该要改，这里的操作符填写 <= ")
    @Length(max = 2, message = "操作符二位 < ，> ，<=，>= " )
    private String operate1;

    @ApiModelProperty(" 阈值2")
//    @Length(max=10, message = "阈值只能小于9999999.99万吨")
    private String value2;

    @ApiModelProperty("操作2 原型图应该要改，这里的操作符填写 >= ")
    @Length(max = 2, message = "操作符二位 < ，> ，<=，>= " )
    private String operate2;


    /**
     * 获取阈值持久层对象
     * @param thresholdValueManagerVo    阈值VO
     * @return ThresholdValueManager
     */
    public static ThresholdValueManager getThresholdValueManager(ThresholdValueManagerVo thresholdValueManagerVo){
        ThresholdValueManager thresholdValueManager = new ThresholdValueManager();
        BeanUtils.copyProperties(thresholdValueManagerVo, thresholdValueManager);
        thresholdValueManager.setValue1(new BigDecimal(StringUtils.isNotBlank(thresholdValueManagerVo.getValue1())  ? thresholdValueManagerVo.getValue1() : "0"));
        thresholdValueManager.setValue2(new BigDecimal(StringUtils.isNotBlank(thresholdValueManagerVo.getValue2()) ? thresholdValueManagerVo.getValue2() : "0"));
        return thresholdValueManager;
    }

    /**
     * 持久层对象转VO
     * @param thresholdValueManager  ThresholdValueManager
     * @return ThresholdValueManagerVo
     */
    public static ThresholdValueManagerVo getThresholdValueManagerVo(ThresholdValueManager thresholdValueManager){
        ThresholdValueManagerVo thresholdValueManagerVo = new ThresholdValueManagerVo();
        BeanUtils.copyProperties(thresholdValueManager, thresholdValueManagerVo);
        thresholdValueManagerVo.setValue1(thresholdValueManager.getValue1().toString());
        thresholdValueManagerVo.setValue2(thresholdValueManager.getValue2().toString());
        return thresholdValueManagerVo;

    }


}
