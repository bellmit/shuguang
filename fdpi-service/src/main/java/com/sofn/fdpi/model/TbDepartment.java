package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;
import org.springframework.amqp.rabbit.listener.exception.FatalListenerStartupException;

import java.util.Map;
import java.util.Set;

@Data
@TableName("TB_DEPARTMENT")
public class TbDepartment extends BaseModel<TbDepartment> {
    //主键
    @TableId(value="id",type = IdType.UUID)
    private String id;
    //省
    private String deptPro;
    //市
    private String deptCity;
    //区
    private String deptArea;
    //机构名称
    private String deptName;
    //行政区划中文：省-市-区
    private String regionInChina;
    //支撑平台中机构id
    private String orgId;
    //类型：1：直属机构；2：执法机构
    private String type;
    //市级存在的证书年审机构的市级编码（code），排除这些市的数据，如果区中存在，则市中不用保存，按最小单位
    @TableField(exist = false)
    private Set<String> cityCodeSet;
    //证书年审机构下级的区级机构的区编码，排除这些区的数据
    @TableField(exist = false)
    private Set<String> areaCodeSet;
}
