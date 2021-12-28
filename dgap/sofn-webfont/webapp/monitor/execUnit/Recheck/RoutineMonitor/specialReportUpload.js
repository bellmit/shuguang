var myapp = angular.module("myapp", []);
myapp.controller("specialReportUpload", function ($scope, $http, $state) {
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//机构任务ID
    var fileName = "tempFile";
    var monitorType = "专项监测";
    var fileAddress = "";
    var monitorTaskId = "testTaskID";
    var monitorTask = "testTaskName";
    var uploadDepartment = "成都检测中心";
    var year = "2016";
    var source = "承担单位";
    var folder = "no";
    var field = "蔬菜";
    var token = window.localStorage.getItem("token");
/*    if (window.localStorage.getItem("ID") == null) {
        window.localStorage.setItem("ID", "58bfb76ea0894b4bb294c204c65ddadd");
    }
    var id = window.localStorage.getItem("ID");*/

    var id=adsMonitorTaskId;
    var createBy="";
    $(function () {
        /**
         * 查询监测任务基本信息
         */
        $http({
            url: "/sofn-ads-web/adsMonitorTask/queryById",
            method: "post",
            dataType: "json",
            data: adsMonitorTaskId,
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            var adsMonitorTask = data.adsMonitorTask;
            monitorTask = adsMonitorTask.taskName;
            monitorTaskId = adsMonitorTaskId;
            year = adsMonitorTask.year;
            field = adsMonitorTask.industry;
        });
    });

    $scope.uploadUrl = "/sofn-ads-web/adsFile/saveInfo";
    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: $scope.uploadUrl,
        allowedFileExtensions: ['doc', 'docx', 'pdf', 'xlsx', 'rar'],
        maxFileSize: 51200,
        maxFilesNum: 1,
        //showPreview: false,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "只能选择{m}个文件！",
        msgSizeTooLarge: "只允许上传50MB以内的文件"
    }).on("fileuploaded", function (event, data) {
        if (200 == data.response.code) {
            /*$("#fileName").val($("#file-1").val());
             $("#loadModal").modal("hide");*/
            //fileName=$("#file-1").val();
            fileName = data.response.origFileName;
            fileAddress = "http://" + data.response.address;
            addAdsFile();
            /*$("#addModal").modal("show");*/
        }
    });


    window.addAdsFile = function () {
        /*var adsMonitorTask;
         adsMonitorTask.monitorClass = monitorType;
         adsMonitorTask.industry = hangYe;*/
        var adsFile = {};
        adsFile.fileName = fileName;
        adsFile.monitorType = monitorType;
        adsFile.fileAddress = fileAddress;
        adsFile.monitorTaskId = monitorTaskId;
        adsFile.monitorTask = monitorTask;
        adsFile.uploadDepartment = uploadDepartment;
        adsFile.years = year;
        adsFile.source = source;
        adsFile.folder = folder;
        adsFile.field = field;
        adsFile.createBy=createBy;
        adsFile.updateBy=createBy;

        $.ajax({
            url:"/sofn-ads-web/adsFile/addAdsFile",
            type:"post",
            dataType:"json",
            data:{"fileAddress":fileAddress,"monitorTaskId":monitorTaskId,"organTaskId":organTaskId,"monitorTask":monitorTask,
                "uploadDepartment":uploadDepartment,"fileName":fileName,"createBy":createBy,"updateBy":createBy,
                "monitorType":monitorType,"years":year,"field":field,"source":source,"folder":folder,
                "token":token},
            success:function(data){
                if(data.httpCode == 200){
                    jBox.tip("上传成功", 'success');
                    var submit = function (v, h, f) {
                        if (v == true)  $state.go("index.content.monitor/leadUnit/reportDownload/ReportDLSpecial")
                        return true;
                    };
                    // 自定义按钮
                    $.jBox.confirm("提示，上传成功，是否跳转到报告汇总页面？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
                }else{
                    jBox.tip("上传失败", 'error');
                }
            }
        });
    }
    $scope.taskDetail = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.sampleMessage = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")
    }
    $scope.test = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.import = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/import")
    }
    $scope.routineMonitorList = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        if($scope.monitorClass=="例行监测"){
            $state.go("index.content.monitor/execUnit/Recheck/RoutineMonitor/routineReportUpload")
        }else if($scope.monitorClass=="专项监测"){
            $state.go("index.content.monitor/execUnit/Recheck/RoutineMonitor/specialReportUpload")
        }
    }

})
