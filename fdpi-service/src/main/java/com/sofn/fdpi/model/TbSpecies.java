/**
 * @Author 文俊云
 * @Date 2020/1/2 14:34
 * @Version 1.0
 */
package com.sofn.fdpi.model;

import com.sofn.common.model.BaseModel;
import lombok.Data;

/**
 * @Author 文俊云
 * @Date 2020/1/2 14:34
 * @Version 1.0
 */

@Data
public class TbSpecies extends BaseModel<TbSpecies> {
    private String id;
    private String speName;
    private String latinName;
    private String tradeName;
    private String localName;
    private String proLevel;
    private String cites;
    private String identify;
    private String pedigree;
    private String intro;
    private String habit;
    private String distribution;
    private String conStatus;
    private String tamType;
    private String speStatus;
    private String speOrigin;
    private String photo;
}
