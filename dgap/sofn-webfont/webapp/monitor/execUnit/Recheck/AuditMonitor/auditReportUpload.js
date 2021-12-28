var myapp = angular.module("myapp", []);
myapp.controller("auditReportUpload", function ($scope, $http, $state) {
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//机构任务ID
    var fileName = "tempFile";
    var monitorType = "监督抽查";
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
        allowedFileExtensions: ['doc', 'docx', 'pdf', 'png', 'xlsx', 'rar', 'zip'],
        maxFileSize: 100,
        maxFilesNum: 1,
        //showPreview: false,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "请选择{m}张图片！",
        msgSizeTooLarge: "只允许上传1MB的基地图片"
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
                if(data.httpCode == 500){
                    history.go(0);
                    jBox.tip("保存失败", 'info');
                }else if(data.httpCode == 400){
                    history.go(0);
                    jBox.tip("没有登录", 'info');
                }else{
                    history.go(0);
                    jBox.tip("保存成功", 'info');
                }
            }
        });
    }
    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/sampleMessage")
    }
    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/auditReportUpload")
    }
    $scope.reback = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/taskDetail")
    }
})
