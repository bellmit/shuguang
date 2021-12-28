package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author zzx
 */
@Data
@Valid
public class BatchIdsVo implements Serializable {

    @ApiModelProperty("id数组")
    @Size(min = 1, message = "id不能为空")
    private List<String> ids;
}
