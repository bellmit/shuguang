package com.sofn.ducss.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 通知分页参数
 *
 * @author liuqiang
 * @date 2020/10/28
 */
@ApiModel(value = "通知分页参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class NoticePageParam extends PagingParam {

    @ApiModelProperty(value = "发送对象")
    private String sendObject;

    @ApiModelProperty(value = "开始时间")
    private Date startTime ;

    @ApiModelProperty(value = "结束时间")
    private Date endTime ;
}