var myapp=angular.module("myapp",[]);
myapp.controller("sampleDetail",function($scope,$http,$state) {
    $scope.monitorClass = window.localStorage.getItem("monitorClass");//监测类型
    $scope.taskName = window.localStorage.getItem("taskName");//监测任务名称
    var sampleCode = window.localStorage.getItem("sampleCode");
    var organTaskId = window.localStorage.getItem("organTaskId");
    $http({
        url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById?time="+new Date(),
        method: "post",
        params: {sampleCode: sampleCode,organTaskId : organTaskId}
    }).success(function (data) {
        $scope.data = data.data;
        $scope.produceDate = data.data.PRODUCE_DATE;
        $scope.sampleDate = data.data.SAMPLE_DATE;
        $scope.FILE_ADDRESS = data.data.FILE_ADDRESS;
        if($scope.FILE_ADDRESS!=null&&$scope.FILE_ADDRESS!=undefined&&$scope.FILE_ADDRESS!="") {
            var array = $scope.FILE_ADDRESS.split("*##*");
            for (var i = 0; i < array.length; i++) {
                // $("#imgSpot").append('<img style="padding-right: 10px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                $("#imgSpot").append('<a data-fancybox href="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/> </a>');

            }
        }
        if( data.data.PRODUCTIONS_TATUS == "自产"){
            $(".sampleSource").css("display","none");
            $(".selfProduced").attr("checked",true);

        }else{
            $(".sampleSource").css("display","block");
            $(".notSelfProduced").attr("checked",true);
        };


        //二维码
        star = toUtf8(sampleCode);
        $("#code").qrcode({
            render: "table", //table方式
            width: 90, //宽度
            height:90, //高度
            text:star //任意内容
        });
    }).error(function(){
    });

    $scope.downloadSample = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/downloadSample")

    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/sampleMessage")

    }

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }
    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/auditReportUpload")

    }
    $scope.historyTaskList = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/historyTaskList")
    }
    //返回
    $scope.back = function () {
        switch ($scope.taskType) {
            case "JDCC":
                $state.go("index.content.monitor/execUnit/Audit/");
                break;
        }
    };
    //打印设置
    $("#printArea").click(function(){
        $(".print_hidden").hide();
        var a_html = $("#imgSpot").html();
        var img_html = $("#imgSpot a").html();
        $("#imgSpot").html(img_html);
        $("#printCount").printArea();
        $("#imgSpot").html(a_html);
        $(".print_hidden").show();
    });
    //返回
    $("#rtnLastPage").click(function(){
        window.localStorage.setItem("organTaskId", organTaskId);
        $state.go("index.content.monitor/execUnit/Audit/sampleMessageAudit");
    });
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
    window.clearValidate=function(){
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    };
    $scope.switchIsSelfProduced=function(){
        var isSelfProduced=$("input[name='isSelfProduced']:checked").val()
        if(isSelfProduced=="自产"){
            $(".sampleSource").css("display","none")
            data.data.sampleSource="自产";
        }else{
            $(".sampleSource").css("display","block")
        }
    };

    $scope.printAreas = function () {
        var sampleShowCode = $("#showSample").val();
        $("#showTaskName").text( $scope.taskName);
        $("#showSampleCode").text(sampleShowCode);
        $('#choose_print_modal').modal();
    }

    $("#printAreaCode").click(function () {
        $("#choose_print_modal").modal("hide");
        $(".modal-backdrop").hide();
        var sampleShowCode = $("#showSample").val();
        window.localStorage.setItem("adsTaskSampleSampleCode", sampleShowCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey",  $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");
    });
});

