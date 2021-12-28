package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("cites_form1")
public class CitesForm1 {

    private String id;
    private String userid;
    private String certificate;
    private String code;
    private String tradetype;
    private String appendix;
    private String source;
    private String basetype;
    private String amount;
    private String region;
    private String exportcountry;
    private Date approvaldate;
    private Date createtime;
    private String standardcode;
    private String subpackage;
    private String zoneletter;
    private String express;
    private String ischeck;
    private String ischeck1;
    private String ischeck2;
    private String checkstuff;

}
