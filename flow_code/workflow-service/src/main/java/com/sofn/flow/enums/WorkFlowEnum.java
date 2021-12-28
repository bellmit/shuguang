package com.sofn.flow.enums;

import lombok.Getter;


@Getter
public enum WorkFlowEnum {



    HTML_01("displayWithModelId","DisplayModelPicture.html?modelId=$"),
    HTML_02("displayWithProcId","DisplayModelPicture.html?procId=$"),
    HTML_03("displayWithInstId","DisplayModelPicture.html?instId=$"),
    HTML_04("noParamHistory","HistoryPlayback.html"),
    HTML_05("historyWithOperatorId","HistoryPlayback.html?operatorId=$"),
    HTML_06("displayWithPIAndOI","DisplayModelPicture.html?procId=$&operatorId=@"),
    HTML_07("displayWithIIAndOI","DisplayModelPicture.html?instId=$&operatorId=@"),
    HTML_08("displayWithMoreParam","DisplayModelPicture.html?procId=$&url=#&port=%&serviceName=*");


    private String code;
    private String description;

    WorkFlowEnum(String code, String description){
        this.code = code;
        this.description = description;
    }


}
