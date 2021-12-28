var myapp = angular.module("myapp", []);
myapp.controller("delegationReportUpload", function ($scope, $http, $state) {

    $scope.iswrite = "";
    var b = window.localStorage.getItem("AdsMenuObj");
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="受托检测"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    if($scope.disabled){
        $("#file-1").css("display","none");
    }
    // var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    var organTaskId="";
    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//机构任务ID
    var fileName = "tempFile";
    var monitorType = "受托检测";
    var fileAddress = "";
    var monitorTaskId = "testTaskID";
    var monitorTask = "testTaskName";
    var uploadDepartment = "成都检测中心";
    var year = "2016";
    var source = "承担单位";
    var folder = "no";
    var field = "蔬菜";
    var token = window.localStorage.getItem("token");
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
                post: {'Content-Type': 'application/x-www-form-urlencoded'},
                token:window.localStorage.getItem("token")
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
        maxFileSize: 51200,
        maxFilesNum: 1,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        msgFilesTooMany: "只能上传一个文件！",
        msgSizeTooLarge: "文件大小不能超过50MB"
    }).on("fileuploaded", function (event, data) {
        if (200 == data.response.code) {
            fileName = data.response.origFileName;
            fileAddress = "http://" + data.response.address;
            addAdsFile();
        }
    });


    window.addAdsFile = function () {
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
                    var submit = function (v, h, f) {
                        if (v == true)  $state.go("index.content.monitor/execUnit/report/reportSummary")
                        return true;
                    };
                    // 自定义按钮
                    jBox.confirm("提示，上传成功，是否跳转到报告汇总页面？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
                }else{
                    jBox.tip("上传失败", 'error');
                }
            }
        });
    }

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/delegation/delegatetaskDetail")
    }

    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/delegation/delegationTestList")
    }

    $scope.routineReportUpload = function(data) {
        $state.go("index.content.monitor/execUnit/delegation/delegationReportUpload")
    }

})
