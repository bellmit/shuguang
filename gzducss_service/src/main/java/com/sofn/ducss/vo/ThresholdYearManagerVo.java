package com.sofn.ducss.vo;

import com.sofn.ducss.util.DateUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.model.ThresholdYearManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 阈值年度管理
 */
@Data
@ApiModel
public class ThresholdYearManagerVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("数据年度")
    @NotBlank(message = "数据年度不能为空")
    @Length(max = 4,min = 4, message = "年度只能是4位")
    private String year;

    @ApiModelProperty("操作人，新增时不传")
    private String operation;

    @ApiModelProperty("操作时间, 新增时不传")
    private String operationTime;

    @ApiModelProperty("是否新增 1新增 2不是新增 ")
    @Length(max = 1, min = 1, message = "是否新增的值只能是1或者2")
    private String isAdd;

    @ApiModelProperty("如果不是新增使用哪年的数据进行对比")
    @Length(max = 4, message = "数据年度超长，只能是4位")
    private String oddYear;

    /**
     * 获取阈值年份数据持久层对象
     * @param thresholdYearManagerVo ThresholdYearManagerVo
     * @return ThresholdYearManager
     */
    public static ThresholdYearManager getThresholdYearManager(ThresholdYearManagerVo thresholdYearManagerVo){
        ThresholdYearManager thresholdYearManager = new ThresholdYearManager();
        BeanUtils.copyProperties(thresholdYearManagerVo,thresholdYearManager);
        thresholdYearManager.setOperationTime(new Date());
//        thresholdYearManager.setOperation("test");
        thresholdYearManager.setOperation(UserUtil.getLoginUserName());
        return thresholdYearManager;
    }

    /**
     * 数据持久层对象转VO对象
     * @param thresholdYearManager 数据持久层对象
     * @return ThresholdYearManagerVo
     */
    public static ThresholdYearManagerVo getThresholdYearManagerVo(ThresholdYearManager thresholdYearManager) {
        ThresholdYearManagerVo thresholdYearManagerVo = new ThresholdYearManagerVo();
        BeanUtils.copyProperties(thresholdYearManager,thresholdYearManagerVo);
        thresholdYearManagerVo.setOperationTime(DateUtils.format(thresholdYearManager.getOperationTime(),DateUtils.DATE_TIME_PATTERN));
        return thresholdYearManagerVo;
    }

}
