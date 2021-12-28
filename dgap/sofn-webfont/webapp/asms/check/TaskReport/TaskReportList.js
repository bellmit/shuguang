angular.module("myapp", []).controller("TaskReportList", function ($scope, $http, $state) {

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
    $scope.recheckTaskInfomation = {};//复检任务信息
    $scope.taskInfo = {};//任务信息
    $scope.adsFile = {};
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.asms_Check_checkTask_forReport = window.localStorage.getItem("asms_Check_checkTask_forReport");//任务id
    if ($scope.asms_Check_checkTask_forReport == null || $scope.asms_Check_checkTask_forReport == undefined) {
        $.jBox.tip("未获取到任务标示，无法获取报告列表");
        return;
    }
    if ($scope.asms_Check_Task_Type_Title == 'JDCC') {
        $("#titleName").html("监督抽查");
        $scope.iswrite = getAuthorityByMenuName("监督抽查");
        //部级任务提供承担单位选择列表
        $scope.CdUnit = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.checkTaskInfomation = data.data;
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.taskEndTime) + 28800000).toISOString().slice(0, 10);

            }
            //部级用户显示承担单位选择下拉列表
            if ("0" == data.data.taskLevel) {
                $("div[name='CdUnitSpan']").show();
                $scope.CdUnit = data.speList;
            }
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'LXJC') {
        $("#titleName").html("例行监测");
        $scope.iswrite = getAuthorityByMenuName("例行监测");
        //任务信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/routineMonitor/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.rmTaskInfomation = data.data;
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.rmDateBegin) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.rmDateEnd) + 28800000).toISOString().slice(0, 10);
            }
            loadGrid();
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC') {
        $("#titleName").html("专项监测");
        $scope.iswrite = getAuthorityByMenuName("专项监测");
        //任务信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/specialMonitor/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.smTaskInfomation = data.data;
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.smDateBegin) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.smDateEnd) + 28800000).toISOString().slice(0, 10);
            }
            loadGrid();
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'FJRW') {
        $("#titleName").html("复检任务");
        $scope.iswrite = getAuthorityByMenuName("复检任务");
        //任务信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/reCheckTask/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.recheckTaskInfomation = data.data;
            $scope.taskInfo =  data.data;
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.recheckTaskBegin) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.recheckTaskEnd) + 28800000).toISOString().slice(0, 10);
            }
            loadGrid();
        }).error(function (data) {
            
        });
    }


    window.selectCdUnit = function () {
        $("#grid").dataTable().api().ajax.reload();
    };
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    var loadGrid = function () {
        $("#grid").dataTable({
            ajax: {
                "stateSave": true,
                url: "/sofn-asms-web/AsmsCheckTaskReport/list",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    //通过部级任务查看传递承担单位id
                    params.token = window.localStorage.getItem("token");
                    params.taskLevel = $scope.checkTaskInfomation.taskLevel;
                    params.CdUnitId = $("select[name='CdUnit']").val();
                    params.monitorTaskId = $scope.asms_Check_checkTask_forReport;
                    params.monitorTask = $("input[name='monitorTask']").val();
                    params.years = $("input[name='years']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: 'ID',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"
                },
                {
                    data: "YEARS",
                    title: "年份",
                    width: "5%",
                    render: function (data, type, row) {
                        if (data == null) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data: "MONITORTASK",
                    title: "所属任务",
                    width: "20%"
                },
                {
                    data: "UPLOADDEPARTMENT",
                    title: "机构名称",
                    width: "10%"
                },
                {
                    data: "FILENAME",
                    title: "报告名称",
                    width: "20%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        }
                        return data;
                    },
                },
                {
                    data: "ORGANTASKID",
                    title: "样品及检测信息",
                    render: function (data, type, row) {
                        if (data == null) {
                            return null;
                        } else {
                            return '<a style="cursor:pointer" onclick="querySampleJcInfoList(\'' + data.trim() + '\')">查看</a>';
                        }
                    },
                    width: "10%"
                },
                {
                    data:"REPORTSTATUS",
                    title:"状态",
                    render: function (data, type, row) {
                        if (!row.ID) {
                            return "未上报";
                        }
                        return data == '1' ? '已退回':'正常';
                    },
                    width:"8%"
                },
                {
                    data: "FILEADDRESS",
                    title: "操作",
                    render: function (data, type, row ) {
                        if (!data) {
                            return '<a style="color: grey;cursor: default;margin-right: 15px;" disabled="true">下载</a>'
                        }
                        var str = '';
                        //判断是否具有读写权限,该报告是否已退回
                        if($scope.iswrite==2&&row.REPORTSTATUS != '1'){
                            var str = '<a style="cursor: pointer;margin-right: 15px;" onclick="returnReport(\'' + row.MONITORTASK + '\',\'' + row.UPLOADDEPARTMENT + '\',\'' + row.ID + '\')">退回报告</a>';
                        }
                        str +=  '<a style="cursor:pointer"  onclick="downloadFile(\''+row.FILEADDRESS+'\',\''+row.FILENAME.replace(/\s+/g,"")+'\')">下载</a>';
                        return str;
                    },
                    width: "10%"
                }
            ]
        });
    }


    /**
     * 样品及检测信息列表
     * */
    window.querySampleJcInfoList = function (o) {
        window.localStorage.setItem("asms_Check_Task_ReportList_SampleInfoList",o);
        $state.go("index.content.asms/check/TaskReport/TaskSampleJcInfoList");
    }

    $scope.backTaskList=function(){
        var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if("LXJC"==type){
            $state.go("index.content.asms/check/routineMonitor");
        }
        if("ZXJC"==type){
            $state.go("index.content.asms/check/specialMonitor");
        }
        if("JDCC"==type){
            $state.go("index.content.asms/check/checkTask");
        }
        if("FJRW"==type){
            $state.go("index.content.asms/check/reCheckTask");
        }
    }
    //附件的下载
    window.downloadFile=function(fileUrl,fileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', decodeURI(fileUrl));
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', decodeURI(fileName));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //退回报告模态框
    $('#ReturnReportModel').modal({
        keyboard: true,
        show: false
    });
    window.returnReport = function (taskName,unit,adsFileId) {
        if ($scope.taskInfo.taskEndTime < new Date().toISOString().slice(0, 10)) {
            $.jBox.tip("任务已结束,不支持退回");
            return false;
        }
        $scope.$apply(function(){
            $scope.taskInfo.taskName = taskName;
            $scope.taskInfo.taskReleaseUnit = unit;
            $scope.adsFile.id = adsFileId;
        });
        $("#ReturnReportModel").modal("show");
        $("#returnReason").val('');
    };

    //保存承担单位报告退回原因
    $scope.saveReturnReportReason = function () {
        var returnReason = $("#returnReason").val();
        if(returnReason){
            //禁用按钮,防止重复操作
            $("#ReturnReportModel button").attr("disabled", true);
            //上传承担报告退回原因
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-asms-web/AsmsCheckTaskReport/saveReturnReportReason",
                data:{'id':$scope.adsFile.id,'reportStatus':'1','reason':$.trim(returnReason)},
                method: "post"
            }).success(function (data) {
                //关闭模态框
                $("#ReturnReportModel").modal("hide");
                //恢复按钮禁用
                $("#ReturnReportModel button").attr("disabled", false);
                if(data.httpCode=="200") {
                    jBox.tip("保存成功", 'info');
                    $("#grid").dataTable().api().ajax.reload();
                }else{
                    jBox.tip(data.msg, 'info');
                }
            }).error(function (data) {
                //恢复按钮禁用
                $("#ReturnReportModel button").attr("disabled", false);
                jBox.tip("保存失败", 'info');
            });
        } else {
            jBox.tip("请填写退回原因", 'info');
        }
    };

});

