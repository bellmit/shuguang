package com.sofn.ducss.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 上报消息,审核消息,通知分页参数(铃铛列表)
 *
 * @author jiangtao
 * @date 2020/11/1
 */
@ApiModel( value = "上报消息,审核消息,通知分页参数(铃铛列表)" )
@Data
@EqualsAndHashCode( callSuper = true )
public class MessagePageParam extends PagingParam {

    @ApiModelProperty( value = "消息类型" )
    private String messageType;

    @ApiModelProperty( value = "用户id" )
    private String userId;

    @ApiModelProperty( value = "区域id" )
    private String areaId;
    @ApiModelProperty( value = "消息状态（已读，未读)" )
    private String status;

    @ApiModelProperty( value = "开始时间" )
    private Date startTime;

    @ApiModelProperty( value = "结束时间" )
    private Date endTime;

}