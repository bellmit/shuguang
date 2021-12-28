package com.sofn.fdpi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/24 17:15
 **/
@Data
public class SourceInfoModel implements Serializable {
    private String transferComp;
    private Double sum;
    private Double remainingQty;
    private Date dealDate;
}
