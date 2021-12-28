package com.sofn.ducss.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 秸秆系统操作日志相关接口
 */
@Data
@ApiModel("日志对象")
public class DucssOperateLog {

    @ApiModelProperty("ID")
    private String id;

    /**
     *
     * 1. 新增
     * 2. 编辑
     * 3. 删除
     * 4. 上报
     * 5. 撤回
     * 6. 退回
     * 7. 通过
     * 8. 年度任务下发
     * 9. 年度任务编辑
     * 10.通知下发
     * 11.公布数据
     * 12.参数新增
     * 13.参数编辑
     * 14.参数删除
     * 15.阈值新增
     * 16.阈值编辑
     * 17.阈值删除
     */
    @ApiModelProperty("操作类型，1. 新增2. 编辑3. 删除4. 上报5. 撤回6. 退回7. 通过8. 年度任务下发9. 年度任务编辑10.通知下发11.公布数据12.参数新增13.参数编辑" +
            "14.参数删除15.阈值新增16.阈值编辑17.阈值删除")
    private String operateType;

    @ApiModelProperty("操作名称")
    private String operateTypeName;

    /**
     * 操作详情
     */
    @ApiModelProperty("操作详情")
    private String  operateDetail;

    /**
     * 操作用户ID
     */
    @ApiModelProperty("操作用户ID")
    private String operateUserId;

    /**
     * 操作用户名字
     */
    @ApiModelProperty("操作用户名称")
    private String operateUserName;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private Date operateTime;

    /**
     *  查看： RegionLevel
     */
    @ApiModelProperty("操作级别，省级、市级、县级 目前前端不用显示")
    private String level;

    /**
     *  操作用户所属区划
     */
    @ApiModelProperty("操作用户所属区划")
    private String areaId;

}
