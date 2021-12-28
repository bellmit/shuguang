package com.sofn.agpjyz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("农业植物物种信息表")
public class AgricultureSpeciesVo implements Serializable {
    @ApiModelProperty(value="主键")
    private String id;
    @ApiModelProperty(value="中文名")
    private String name;
    //拉丁学名
    @ApiModelProperty(value="拉丁学名")
    private String latinName;
    //科名
    @ApiModelProperty(value="科名")
    private String familyName;
    //科拉丁名
    @ApiModelProperty(value="科拉丁名")
    private String familyLatinName;
    //属名
    @ApiModelProperty(value="属名")
    private String attrName;
    //属拉丁名
    @ApiModelProperty(value="属拉丁名")
    private String attrLatinName;
    //保护级别(1：一批一级保护；2：一批二级保护；3：二批一级保护；4：二批二级保护；5：其它)
    @ApiModelProperty(value="保护级别id(1：一批一级保护；2：一批二级保护；3：二批一级保护；4：二批二级保护；5：其它)")
    private String protectGradeId;
    //形态特征
    @ApiModelProperty(value="形态特征")
    private String shapeFeature;
    //生境
    @ApiModelProperty(value="生境")
    private String habitat;
    //是否中国特有属(1:是；0：否)
    @ApiModelProperty(value="是否中国特有属(1,0")
    private String isOwnInChina;
    //濒危状况Id
    @ApiModelProperty(value="濒危状况Id")
    private String endangerStatusId;
    @ApiModelProperty(value="濒危状况名称")
    private String endangerStatusName;
    //保护现状
    @ApiModelProperty(value="保护现状")
    private String protectStatus;
    //备注
    @ApiModelProperty(value="备注")
    private String remarks;
    //状态0：已保存；1：已撤回；2：已上报；3：市级退回；4：市级通过；5：省级退回；6：省级通过；7：总站退回；8：总站通过
    @ApiModelProperty(value="状态key（0：已保存；1：已撤回；2：已上报；3：市级退回；4：市级通过；5：省级退回；6：省级通过；7：总站退回；8：总站通过）")
    private String status;
    @ApiModelProperty(value="状态value（状态0：已保存；1：已撤回；2：已上报；3：市级退回；4：市级通过；5：省级退回；6：省级通过；7：总站退回；8：总站通过）")
    private String statusName;
    @ApiModelProperty(value="操作人")
    private String updateUserName;
    @ApiModelProperty(value="操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty(value="分布地区列表")
    private List<AgricultureSpeciesAddrVo> addrList;
    @ApiModelProperty(value="照片文件列表")
    private List<AgricultureSpeciesFilesVo> fileList;
    @ApiModelProperty(value="审核列表")
    private List<AgricultureSpeciesProcessVo> processList;
}
