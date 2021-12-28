package com.sofn.ahhrm.vo;

import com.sofn.ahhrm.enums.ProcessEnum;
import com.sofn.ahhrm.model.Baseinfo;
import com.sofn.ahhrm.model.BaseinfoSub;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Data
@ApiModel(value = "基础信息子表Vo对象")
public class BaseinfoSubVo {

    @ApiModelProperty(value = "品种")
    private String variety;
    @ApiModelProperty(value = "品种名称")
    private String varietyName;
    @ApiModelProperty(value = "群体数量(个)")
    private Integer amount;
    @ApiModelProperty(value = "产地与分布")
    private String originDistribution;
    @ApiModelProperty(value = "公母比例")
    private String proportion;
    @ApiModelProperty(value = "有效群体含量")
    private Double effectiveGroup;
    @ApiModelProperty(value = "性能指标")
    private String perIndex;

    /**
     * 实体类转VO类
     */
    public static BaseinfoSubVo entity2Vo(BaseinfoSub entity) {
        BaseinfoSubVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new BaseinfoSubVo();
            BeanUtils.copyProperties(entity, vo);
        }
        return vo;
    }
}
