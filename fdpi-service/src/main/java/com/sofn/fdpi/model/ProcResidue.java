package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * @Description 加工企业剩余吨数数据model
 * @Author wg
 * @Date 2021/5/21 11:19
 **/
@Data
@TableName("om_proc_residue")
public class ProcResidue extends BaseModel<ProcResidue> {
    private String id;
    private Double residueSum;
    private String fkProcComp;
}
