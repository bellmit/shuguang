var myapp = angular.module("myapp", []);
myapp.controller("sample", function ($scope, $http, $state) {

    $scope.sampleId = window.localStorage.getItem("asms_sample_info_id");
    $scope.organTaskId = window.localStorage.getItem("asms_sample_info_organTaskId");
    $scope.monitorClass = window.localStorage.getItem("asms_Check_Task_Type_Title");
    if($scope.monitorClass=="LXJC"){
        $scope.monitorClass = "例行监测";
    }else if($scope.monitorClass=="ZXJC"){
        $scope.monitorClass = "专项监测";
    }else{
        $scope.monitorClass = null;
    }
    $scope.alesTaskSample = {};//抽样单
    $scope.alesTaskSample.sampleCode = "";

    $("input").attr("disabled", "disabled");
    $("radio").attr("disabled", "disabled");
    $("select").attr("disabled", "disabled");
    $("button[name='temporarySampleCode']").attr("disabled", "disabled");

    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/checkTask/getTaskSampleById",
        method: "post",
        data: {"sampleCode": $scope.sampleId, "organTaskId":$scope.organTaskId}
    }).success(function (data) {
        $scope.detailData=data.data;
        if (data.data.PRODUCING_AREA == null) {
            $scope.producingAreaName = "";
        } else {
            $scope.producingAreaName = $.fn.Cityname(data.data.PRODUCING_AREA);
        }
        //去除二维码显示
        // $("#erweimacode").empty(); // 解决每次打开模态窗口都重复生成二维码的问题
        // //二维码
        // star = toUtf8(data);
        // $("#erweimacode").qrcode({
        //     render: "table", //table方式
        //     width: 90, //宽度
        //     height: 90, //高度
        //     text: star //任意内容
        // });
    }).error(function (data) {
        
    });

    $scope.samplePrint = function (data) {
        window.localStorage.setItem("alesTaskSampleSampleCode", $scope.alesTaskSample.sampleCode);//监测类型
        $state.go("index.content.asms/check/TaskSample/samplePrint")
    };

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    }

    star = toUtf8($scope.alesTaskSample.sampleCode);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height: 90, //高度
        text: star //任意内容
    });

    $scope.back= function () {
        $state.go("index.content.asms/check/TaskSample/TaskSampleList")
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
});


