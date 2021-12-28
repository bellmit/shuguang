var myapp = angular.module("myapp", []);
myapp.controller("delegateReportUpload", function ($scope, $http, $state) {
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//机构任务ID
    var fileName = "tempFile";
    var monitorType = "委托检测";
    var fileAddress = "";
    var monitorTaskId = "testTaskID";
    var monitorTask = "testTaskName";
    var uploadDepartment = "成都检测中心";
    var year = "2016";
    var source = "承担单位";
    var folder = "no";
    var field = "蔬菜";
    var publishStatus = "";
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
            publishStatus = adsMonitorTask.publishStatus;
            monitorType = adsMonitorTask.monitorClass;
        });
    });

    $scope.uploadUrl = "/sofn-ads-web/adsFile/upload";
    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: $scope.uploadUrl,
        //业务参数
        data: function() {
            return { };
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        accept:"*",
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (imageTypes.test(file.type)) {
                swal('', '只能上传“doc，docx，pdf，xlsl，rar” 类型文档', 'warning');
                return false;
            }
            if(publishStatus == "3"){
                jBox.tip("该任务已停止,不允许上传报告.",'info');
                return false;
            }
            if (file.size > 50 * 1024 * 1024) {
                swal('', '文件大小不能超过50MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.addAdsFile = function () {
        if (vm.files.length <= 0) {
            alert("请先选择一个报告再进行上传操作");
            return false;
        }
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
    $scope.taskDetail = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/delegatetaskDetail")
    }
    $scope.sampelPrint = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")
    }
    $scope.test = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/delegationtestList")
    }
    $scope.import = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/import")
    }
    $scope.routineMonitorList = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/delegateList")
    }
    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
    }

})
