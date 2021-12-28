angular.module("myapp",[]).controller("qryCheckTaskReportList",function($scope,$http,$state){

    /**
     * 该页面作为监督抽查、例行监测、专项检测报告展示页面(检测系统上传的报告文件)
     * 为方便用户使用，可以将检测对象信息一并带出
     * */

    /**
     * 公共值
     * */
    $scope.checkTaskInfomation = {};//监督抽查任务信息
    $scope.rmTaskInfomation = {};//例行监测任务信息
    $scope.smTaskInfomation = {};//专项检测任务信息
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.asms_Check_checkTask_forReport = window.localStorage.getItem("asms_Check_checkTask_forReport");//任务id
    if ($scope.asms_Check_checkTask_forReport == null || $scope.asms_Check_checkTask_forReport == undefined){
        $.jBox.tip("未获取到任务标示，无法获取报告列表");
        return;
    }
    if ($scope.asms_Check_Task_Type_Title == 'JDCC'){
        $("#titleName").html("监督抽查");
        //部级任务提供承担单位选择列表
        $scope.CdUnit=[];
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-qry-web/monitorTask/getTaskById",
            data:{id:$scope.asms_Check_checkTask_forReport},
            method:"post"
        }).success(function(data) {
            $scope.checkTaskInfomation = data.data;
            //部级用户显示承担单位选择下拉列表
            if ("0" == data.data.taskLevel){
                $("div[name='CdUnitSpan']").show();
                $scope.CdUnit=data.speList;
            }
            loadGrid();
        }).error(function(data){
            
        });
    }


    window.selectCdUnit = function(){
        $("#grid").dataTable().api().ajax.reload();
    };
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    var loadGrid = function () {
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-qry-web/monitorTask/listCheckTaskReport",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    if ($scope.checkTaskInfomation.state!=0) {
                        var monitorTask=$("input[name='monitorTask']").val();
                        var years=$("input[name='years']").val();
                        if (json.data.list.length < 1 && !monitorTask && !years){
                            json.data.list[0] = {YEARS:"无",UPLOADDEPARTMENT:$scope.checkTaskInfomation.jcUnitName,MONITORTASK:$scope.checkTaskInfomation.taskName,ORGANTASKID:$scope.checkTaskInfomation.id,FILENAME:"无"}
                            json.recordsFiltered = 1;
                            json.recordsTotal = 1;
                        }
                    }
                    return json.data.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data : function(params) {
                    //通过部级任务查看传递承担单位id
                    params.taskLevel = $scope.checkTaskInfomation.taskLevel;
                    params.CdUnitId = $("select[name='CdUnit']").val();
                    params.monitorTaskId=$scope.asms_Check_checkTask_forReport;
                    params.monitorTask=$("input[name='monitorTask']").val();
                    params.years=$("input[name='years']").val();
                    params.monitorType = '监督抽查';
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
                    width : "4%"
                },
                {
                    data : "YEARS",
                    title : "年份",
                    width : "5%",
                    render : function (data) {
                        if (!data) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data : "MONITORTASK",
                    title : "所属任务",
                    width : "25%"
                },
                {
                    data : "UPLOADDEPARTMENT",
                    title : "机构名称",
                    width : "15%",
                    render: function (data, type, row, meta) {
                        if (!data) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data : "FILENAME",
                    title : "报告名称",
                    width : "20%",
                    render : function (data) {
                        if (!data) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data : "ORGANTASKID",
                    title : "样品及检测信息",
                    render: function (data, type, row) {
                        return '<a style="cursor:pointer" onclick="querySampleJcInfoList(\'' + $scope.checkTaskInfomation.id + '\' , \''+ $scope.checkTaskInfomation.jcUnitName + '\')">查看</a>';
                    },
                    width : "10%"
                },
                {
                    data:"REPORTSTATUS",
                    title:"报告状态",
                    render: function (data, type, row ) {
                        if (!row.ID) {
                            return "未上报";
                        }
                        return data == '1' ? '已退回':'正常';
                    },
                    width:"8%"
                },
                {
                    data : "FILEADDRESS",
                    title : "操作",
                    render : function(data, type, row) {
                        if (!row.ID) {
                            return '<a style="color: grey;cursor: default;margin-right: 15px;" disabled="true">下载</a>'
                        }
                        return '<a style="cursor:pointer"  onclick="downloadFile(\'' + row.FILEADDRESS + '\',\'' + row.FILENAME.replace(/\s+/g, "") + '\')">下载</a>';
                    },
                    width : "5%"
                }
            ],
        });
    };

    /**
     * 样品及检测信息列表
     * */
    window.querySampleJcInfoList = function(o, p){
        window.localStorage.setItem("asms_Check_Task_ReportList_SampleInfoList",o);
        window.localStorage.setItem("asms_Check_Task_ReportList_SampleInfoList_jcUnitName",p);
        $state.go("index.content.qry/monitorTask/taskReport/qryTaskSampleJcInfoList");
    }

    $scope.retrunDiskTop = function () {
        if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'ZXJC'){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'FJRW'){
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }else if($scope.asms_Check_Task_Type_Title == 'JDCC'){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }
    }
    //附件下载
    window.downloadFile=function(fileUrl, fileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/qryFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', fileUrl);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', fileName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

});

