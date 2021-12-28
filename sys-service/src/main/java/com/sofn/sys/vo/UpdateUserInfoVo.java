package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author heyongjie
 * @date 2019/12/4 9:59
 */
@ApiModel
@Data
public class UpdateUserInfoVo {


    @Length(max = 32, message = "用户ID超长")
    @NotBlank(message = "用户ID不能为空")
    private String id;

    @ApiModelProperty("邮箱")
    @Length(max = 50,message = "邮箱超长，最多50个字符")
    private String email;

    @ApiModelProperty("昵称")
    @Length(max = 50,message = "昵称超长，最多50个字符")
    private String nickname;

    @ApiModelProperty("手机号")
    @Length(max = 20,message = "手机号超长，最多20个字符")
    private String mobile;

    @ApiModelProperty("用户状态")
    private String status;

    @ApiModelProperty("性别，1-男，2-女，3-未知")
    @Max(value = 3, message = "最大值3-未知")
    @Min(value = 1, message = "最小值1-男")
    private Integer sex = 3;

    @ApiModelProperty("备注")
    @Length(max = 100,message = "备注超长，最多100个字符")
    private String remark;

}
