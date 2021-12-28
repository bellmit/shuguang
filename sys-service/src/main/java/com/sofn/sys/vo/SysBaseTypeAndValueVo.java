package com.sofn.sys.vo;

import com.sofn.common.treeutil.basic.BasicTreeBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * SysBaseTypeAndValueVo
 * @author heyongjie
 * @date 2020/2/28 15:00
 */
@Data
@ApiModel(value="字典类型和字典值构成的树，第一层为数据类型，第二层为数据值")
public class SysBaseTypeAndValueVo extends BasicTreeBean<SysBaseTypeAndValueVo> {

    @ApiModelProperty(value = "节点名称，当为数据类型的时候为类型名称，当为数据值的时候为数据值名称")
    private String nodeName;

    /**
     *  节点代码  对应数据库中的TYPE_VALUE   BASEDATACODE   二个字段
     */
    @ApiModelProperty(value = "节点代码")
    private String nodeCode;


}


