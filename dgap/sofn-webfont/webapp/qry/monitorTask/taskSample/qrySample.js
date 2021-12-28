var myapp=angular.module("myapp",[]);
myapp.controller("qrySample",function($scope,$http,$state) {

    /**
     * public
     * */
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    if ($scope.asms_Check_Task_Type_Title == 'JDCC'){
        $("#titleName").html("监督抽查");
        $scope.monitorClass = '监督抽查';
    }
    if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
        $("#titleName").html("例行监测");
        $scope.monitorClass = '例行监测';
    }
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC'){
        $("#titleName").html("专项检测");
        $scope.monitorClass = '专项检测';
    }
    if ($scope.asms_Check_Task_Type_Title == 'FJRW'){
        $("#titleName").html("复检任务");
        $scope.monitorClass = '复检任务';
    }

    $scope.cyxxid = window.localStorage.getItem("asms_sample_info_id");//抽样单id
    $scope.organTaskId = window.localStorage.getItem("asms_sample_info_organTaskId");
    $scope.alesTaskSample = {};//抽样单信息
    $scope.alesTaskSample.sampleCode = "";

    $("input").attr("disabled","disabled");
    $("select").attr("disabled","disabled");

    /**
     * 信息展示
     * */
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/monitorTask/getTaskSampleById",
        method:"post",
        data: {"sampleCode": $scope.cyxxid, "organTaskId":$scope.organTaskId}
    }).success(function(data){
        $scope.detailData=data.data;
        if (data.data.PRODUCING_AREA == null) {
            $scope.producingAreaName = "";
        } else {
            $scope.producingAreaName = $.fn.Cityname(data.data.PRODUCING_AREA);
        }
        //抽样单取消二维码
        // $("#erweimacode").empty(); // 解决每次打开模态窗口都重复生成二维码的问题
        // //二维码
        // star = toUtf8(data);
        // $("#erweimacode").qrcode({
        //     render: "table", //table方式
        //     width: 90, //宽度
        //     height: 90, //高度
        //     text: star //任意内容
        // });
    }).error(function(data){
        
    });

    //设置中文
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
    star=toUtf8($scope.alesTaskSample.sampleCode);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height:90, //高度
        text:star //任意内容
    });
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
});


