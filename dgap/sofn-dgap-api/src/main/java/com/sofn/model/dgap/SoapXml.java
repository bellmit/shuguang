package com.sofn.model.dgap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * author tianjie
 *
 * @date 2016/11/15
 */
@XmlRootElement(name="SoapXml")
public class SoapXml {

    private String resource;

    private String returnFields;

    private String condition;

    private String recordNum;

    public SoapXml() {
        super();
    }

    public SoapXml(String resource, String returnFields, String condition, String recordNum) {
        this.resource = resource;
        this.returnFields = returnFields;
        this.condition = condition;
        this.recordNum = recordNum;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getReturnFields() {
        return returnFields;
    }

    public void setReturnFields(String returnFields) {
        this.returnFields = returnFields;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }
}
