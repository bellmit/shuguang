package com.sofn.fdpi.vo;

import com.google.common.collect.Lists;
import com.sofn.fdpi.model.SturgeonSub;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱子表VO对象")
@AllArgsConstructor
@NoArgsConstructor
public class SturgeonSubVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "鲟鱼子酱ID")
    private String sturgeonId;
    @ApiModelProperty(value = "客户")
    private String customer;
    @ApiModelProperty(value = "品种")
    private String variety;
    @ApiModelProperty(value = "标签纸规格")
    private String label;
    @ApiModelProperty(value = "箱号")
    private Integer caseNum;
    @ApiModelProperty(value = "起始号")
    private String startNum;
    @ApiModelProperty(value = "终止号")
    private String endNum;
    @ApiModelProperty(value = "规格(/克)")
    private Integer specs;
    @ApiModelProperty(value = "听数")
    private Integer sum;
    @ApiModelProperty(value = "流程状态 1待审核2已退回3已通过")
    private String status;

    /**
     * 实体类转VO类
     */
    public static SturgeonSubVo entity2Vo(SturgeonSub entity) {
        SturgeonSubVo vo = new SturgeonSubVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    /**
     * FORM类列表转VO类列表
     */
    public static List<SturgeonSubVo> forms2Vos(List<SturgeonSubForm> forms) {
        if (!CollectionUtils.isEmpty(forms)) {
            List<SturgeonSubVo> vos = Lists.newArrayListWithCapacity(forms.size());
            for (SturgeonSubForm form : forms) {
                SturgeonSubVo vo = new SturgeonSubVo();
                BeanUtils.copyProperties(form, vo);
                vos.add(vo);
            }
            return vos;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * FORM类列表转VO类列表
     */
    public static SturgeonSubVo form2Vo(SturgeonSubForm form) {
        SturgeonSubVo vo = new SturgeonSubVo();
        BeanUtils.copyProperties(form, vo);
        return vo;
    }
}
