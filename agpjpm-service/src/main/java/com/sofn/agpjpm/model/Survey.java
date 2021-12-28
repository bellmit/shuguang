package com.sofn.agpjpm.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 9:11
 */
@TableName("survey")
@Data
public class Survey {
    private String id;		//主键
    private Date surveyDate;		//调查日期
    private String  surveyNum;	//调查点编号
    private String  province;	//省
    private String  provinceName;		//省名
    private String city;		//市
    private String cityName;	//	市名
    @TableField(strategy = FieldStrategy.IGNORED)
    private String county;		//县
    @TableField(strategy = FieldStrategy.IGNORED)
    private String countyName;//		县名
    private String surveyor;	//调查人
    private String  tel;	//电话
    private String qq;//	qq号
    private Double altitude; //海拔
    private String createUserId;

}
