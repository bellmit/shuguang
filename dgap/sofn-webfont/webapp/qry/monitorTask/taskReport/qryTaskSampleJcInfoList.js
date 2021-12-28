angular.module("myapp",[]).controller("qryTaskSampleJcInfoList",function($scope,$http,$state){

    /**
     * 该页面为检测对象的检测信息列表
     * */

    /**
     * 公共值
     * */
    //检测信息id
    $scope.JcInfoId = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfoList");
    $scope.jcUnitName = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfoList_jcUnitName");//检测单位名称
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");

    if ($scope.asms_Check_Task_Type_Title == 'JDCC')$("#titleName").html("监督抽查");
    if ($scope.asms_Check_Task_Type_Title == 'LXJC')$("#titleName").html("例行监测");
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC')$("#titleName").html("专项检测");
    if ($scope.asms_Check_Task_Type_Title == 'FJRW')$("#titleName").html("复检任务");
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });

    $(function() {
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-qry-web/monitorTask/getJclistByReport",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data : function(params) {
                    params.organTaskId=$scope.JcInfoId;
                    params.monitorTaskId=$scope.JcInfoId;
                    params.monitorTaskId=$scope.JcInfoId;
                    var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
                    if("JDCC"==type){
                        params.monitorType = "监督抽查";
                        params.monitorTaskId = $scope.JcInfoId;
                    }
                },
                type:"post"
            },
            columns : [
                {
                    data : 'ID',
                    title : 'ID',
                    visible : false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width : "3%"
                },
                {
                    data : "SAMPLE_NAME",
                    title : "样品名称",
                    width : "5%"
                },
                {
                    data : "SAMPLE_CODE",
                    title : "样品编码",
                    width : "10%",
                    render:function (data, type, row) {
                        if(data == null || data =='' || data == undefined){
                            return "无";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "PRODUCT_TRACEABILITY_CODE",
                    title : "产品编码",
                    width : "28%",
                    render:function (data, type, row) {
                        if(data == null || data =='' || data == undefined){
                            return "无";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "TESTED_DEPARMENT",
                    title : "受检单位",
                    width : "9%",
                    render: function (data) {
                        return data?data:"无";
                    }
                },
                {
                    data : "SAMPLE_DEPARMENT",
                    title : "抽样单位",
                    width : "9%"
                },
                {
                    data : "CHECK_ORGAN",
                    title : "检测单位",
                    width : "9%",
                    render: function (data) {
                        return data?data:$scope.jcUnitName;
                    }
                },
                {
                    data : "RESULT",
                    title : "检测结果",
                    width : "8%",
                    render : function(data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else if ("2" == data) {
                            return "不作判定";
                        }
                    }
                },
                {
                    data : "ID",
                    title : "样品及检测信息",
                    render : function(data, type, row) {
                        return '<a style="cursor:pointer" onclick="queryReports(\''+data+'\')">查看</a>';
                    },
                    width : "8%"
                }
            ],
        });
    });

    /**
     *查看检测详情
     **/
    window.queryReports = function(o){
        $scope.asms_Check_Task_Type_Title = window.localStorage.removeItem("asms_Check_Task_ReportList_SampleInfo");
        $scope.asms_Check_Task_Type_Title = window.localStorage.setItem("asms_Check_Task_ReportList_SampleInfo",o);
        $state.go("index.content.qry/monitorTask/taskReport/qryTaskSampleJcInfo");
    }
    $scope.back = function(){
        $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if($scope.asms_Check_Task_Type_Title == "LXJC"){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "ZXJC"){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "JDCC"){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }else{
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }
    }

    $scope.backReportList = function () {
        var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if("JDCC"==type){
            $state.go("index.content.qry/monitorTask/taskReport/qryCheckTaskReportList");
        }else {
            $state.go("index.content.qry/monitorTask/taskReport/qryTaskReportList");
        }

    }


});

