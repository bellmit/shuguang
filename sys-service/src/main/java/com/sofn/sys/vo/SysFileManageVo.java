package com.sofn.sys.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.fileutil.FastDFSClient;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.model.SysFileManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 文件VO对象
 *
 * @author heyongjie
 * @date 2019/6/18 16:15
 */
@ApiModel(value = "文件管理VO对象")
@Data
public class SysFileManageVo {

    @ApiModelProperty(value = "文件ID")
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件大小")
    private long fileSize;

    @ApiModelProperty(value = "文件类型，pdf、word、txt由上传时的后缀名确认")
    private String fileType;

    @ApiModelProperty(value = "操作用户")
    private String operator;

    @ApiModelProperty(value = "操作用户名称")
    private String operatorName;

    @ApiModelProperty(value = "操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @ApiModelProperty(value = "所属系统ID")
    private String systemId;

    @ApiModelProperty(value = "AppId")
    private String appId;

    @ApiModelProperty(value = "所属系统名称")
    private String systemName;

    @ApiModelProperty(value = "下载次数")
    private Integer downloadTimes;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "接口号")
    private String interfaceNum;

    @ApiModelProperty(value = "激活状态")
    private String activationState;

    @ApiModelProperty(value = "接口号，系统的哪个接口上传的文件")
    private String fileNumber;

    @ApiModelProperty(value = "文件状态，Y启用，N停用")
    private String fileState;

    @ApiModelProperty(value = "业务文件类型，一般是数据字典值,32位ID")
    private String businessFileType;

    /**
     * 将持久层对象转为VO对象
     *
     * @param sysFileManage 持久层对象
     * @return VO对象
     */
    public static SysFileManageVo getSysFileManageVo(SysFileManage sysFileManage) {
        SysFileManageVo sysFileManageVo = new SysFileManageVo();
        BeanUtils.copyProperties(sysFileManage, sysFileManageVo);
        sysFileManageVo.setOperator(StringUtils.isBlank(sysFileManage.getOperator()) ? "" : UserUtil.getUsernameById(sysFileManage.getOperator()));
        sysFileManageVo.setFilePath(FastDFSClient.getRealPath(sysFileManage.getFilePath()));
        sysFileManageVo.setSystemId(sysFileManage.getSystem());
        return sysFileManageVo;
    }
}
