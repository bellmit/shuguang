angular.module("myapp", []).controller("takeTheLeadReporList", function ($scope, $http, $state) {

    /**
     * 报告列表
     * */

    $scope.taskInfo = {};//任务信息
    $scope.adsFile = {};
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.asms_Check_checkTask_forReport = window.localStorage.getItem("takeTheLeadReporListTaskId");//任务id
    //例行监测
    if ($scope.asms_Check_Task_Type_Title == 'LXJC') {
        $scope.iswrite = getAuthorityByMenuName("例行监测");
        $("#titleName").html("例行监测");
        //任务信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/routineMonitor/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.rmDateBegin) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.rmDateEnd) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskReleaseUnit = data.data.rmReleaseUnit;
            }
        }).error(function (data) {
            
        });
    }
    //专项监测
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
            if(data.data){
                $scope.taskInfo.taskBeginTime = new Date(parseInt(data.data.smDateBegin) + 28800000).toISOString().slice(0, 10);
                $scope.taskInfo.taskEndTime = new Date(parseInt(data.data.smDateEnd) + 28800000).toISOString().slice(0, 10);
            }
        }).error(function (data) {
            
        });
    }

    $("#grid").dataTable({
        ajax: {
            url: "/sofn-asms-web/routineMonitor/getPageInfoAdsFileBySupId",
            headers: {
                token: window.localStorage.getItem("token")
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
                params.taskId = window.localStorage.getItem("takeTheLeadReporListTaskId");
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
                width: "5%"
            },
            {
                data: "MONITOR_TASK",
                title: "所属任务",
                width: "25%"
            },
            {
                data: "UPLOAD_DEPARTMENT",
                title: "单位",
                width: "10%"
            },
            {
                data: "FILE_NAME",
                title: "报告名称",
                width: "27%"
            },
            {
                data:"REPORT_STATUS",
                title:"状态",
                render: function (data) {
                    return data == '1' ? '已退回':'正常';
                },
                width:"8%"
            },
            {
                data: "FILE_ADDRESS",
                title: "操作",
                render: function (data, type, row) {
                    var str = '';
                    //判断是否具有读写权限,该报告是否已退回
                    if($scope.iswrite==2&&row.REPORT_STATUS != '1'){
                        str = '<a style="cursor: pointer;margin-right: 15px;" onclick="returnReport(\'' + row.MONITOR_TASK + '\',\'' + row.UPLOAD_DEPARTMENT + '\',\'' + row.ID + '\')">退回报告</a>';
                    }
                    str += '<a style="cursor: pointer" class="Check-report"  onclick=downloadFile(\'' + row.FILE_ADDRESS + '\',\'' + row.FILE_NAME.replace(/\s+/g, "") + '\')>下载</a>';
                    return str;
                },
                width: "10%"
            }
        ]
    });

    //下载
    window.downloadFile = function (takeTheLeadUrl, takeTheLeadFileName) {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', takeTheLeadUrl);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', takeTheLeadFileName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    $scope.backTaskList = function () {
        var type = window.localStorage.getItem("takeTheLeadReporListTaskType");
        if ("routineMonitor" == type) {
            $state.go("index.content.asms/check/routineMonitor");
        }
        if ("specialMonitor" == type) {
            $state.go("index.content.asms/check/specialMonitor");
        }
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
        })
        $("#ReturnReportModel").modal("show");
        $("#returnReason").val('');

    };

    //保存牵头单位报告退回原因
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

