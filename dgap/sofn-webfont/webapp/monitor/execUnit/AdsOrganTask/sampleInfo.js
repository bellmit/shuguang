var myapp=angular.module("myapp",[]);
myapp.controller("sampleInfo",function($scope,$http,$state) {
    $("#newTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleInfo")
    });

    $("#bePubTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/monitorInfo")
    });

    //获取本地存储的sampleCode
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    $scope.sampleCode=window.localStorage.getItem("ads_sample_code");
    $scope.productTraceabilityCode=window.localStorage.getItem("ads_product_traceability_code");
    $scope.sampleName=window.localStorage.getItem("ads_sample_name");
    $scope.monitorClass=window.localStorage.getItem("monitorClass");
    var taskName = window.localStorage.getItem("taskName");
    var urlSource = window.localStorage.getItem("urlSource");

    var a=$scope.sampleCode;


        // $scope.sampleCode=data;
        $http({
            url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById?time=" + new Date(),
            method: "post",
            params: {sampleCode: $scope.sampleCode,organTaskId : organTaskId}
        }).success(function (data) {
            $scope.detailData=data.data;
            if (data.data.PRODUCING_AREA == null) {
                $scope.producingAreaName = "";
            } else {
                $scope.producingAreaName = data.data.PRODUCING_AREA_NAME;
            }

            $("#erweimacode").empty(); // 解决每次打开模态窗口都重复生成二维码的问题
            //二维码
            star = toUtf8(data);
            $("#erweimacode").qrcode({
                render: "table", //table方式
                width: 90, //宽度
                height: 90, //高度
                text: star //任意内容
            });
            // $("#detailModal").modal("show");
        }).error(function () {
        });


    /*  alert(a);
     */

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.sampleMessage = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")
    }
    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
    }

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
    $scope.name=$scope.sampleName;
    star=toUtf8($scope.name);
    $("#code").qrcode({
        render: "table", //table方式
        width: 100, //宽度
        height:100, //高度
        text:star //任意内容
    });

    //获取抽样信息
    $http({
        url:"/sofn-ads-web/adsCheckInfo/getSampleBySampleCode",
        data:a,
        params:{'sampleCode':a},
        method:"post",
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function (datax) {
        $scope.ads=datax.adsMonitorSample[0];

        var produceTime = $scope.ads.produceDate;
        var d = new Date(produceTime);    //根据时间戳生成的时间对象
        var date = (d.getFullYear()) + "-" +
            (d.getMonth() + 1) + "-" +
            (d.getDate());
        $scope.ads.produceTime=date;
        if($scope.ads.fileAddress!=null&&$scope.ads.fileAddress!=undefined&&$scope.ads.fileAddress!="") {
            var array = $scope.ads.fileAddress.split("*##*");
            for (var i = 0; i < array.length; i++) {
                // $("#imgSpot").append('<img style="padding-right: 10px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/>');
                $("#imgSpot").append('<a data-fancybox href="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + '/sofn-sys-web/sysTemplate/downPic?fileUrl=' + array[i]+ '" alt=""/> </a>');

            }
        }
        if( $scope.ads.sampleSource == "自产"){
            $(".sampleSource").css("display","none");
            $(".selfProduced").attr("checked",true);

        }else{
            $(".sampleSource").css("display","block");
            $(".notSelfProduced").attr("checked",true);
        };

    }).error(function (data) {
        jBox.tip("数据有误", 'info');
    });
    window.clearValidate=function(){
        $("#attributeForm").data("bootstrapValidator").destroy();//先去掉验证，与下面对应
        $("#attributeForm").bootstrapValidator();//初始化验证，与上面对应
    };
    $scope.switchIsSelfProduced=function(){
        var isSelfProduced=$("input[name='isSelfProduced']:checked").val()
        if(isSelfProduced=="自产"){
            $(".sampleSource").css("display","none")
            $scope.ads .sampleSource="自产";
        }else{
            $(".sampleSource").css("display","block")
        }
    };
    //导出excel
    $scope.exportExcel = function () {
        var sampleCode = $scope.sampleCode;
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsCheckInfo/getSampleList",
                    method: "post",
                    dataType: "json",
                    params: {"sampleCode": sampleCode},
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '抽样信息.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    //返回按钮事件
    $scope.backBtn = function () {
       // $state.go("index.content.monitor/execUnit/Audit/routineMonitorListAudit");
        if(urlSource=="初检任务"){
            $state.go("index.content.monitor/execUnit/Recheck/RoutineMonitor/routineMonitorListRck");
        }else if ($scope.monitorClass=="例行监测"){
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList");
        }else if ($scope.monitorClass=="专项监测"){
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList");
        }else if ($scope.monitorClass=="监督抽查"){
            $state.go("index.content.monitor/execUnit/Audit/routineMonitorListAudit");
        }else if ($scope.monitorClass=="复检任务"){
            $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck");
        }
    }

    $scope.printSample = function () {
        var sampleCode = $("#sampleCode").val();
        $("#showTaskName").text(taskName);
        $("#showSampleCode").text(sampleCode);
        $('#choose_print_modal').modal("show");
    }

    //打印设置
    $("#printArea").click(function () {
        /* $("#printCount").printArea();*/
        var sampleCode = $("#sampleCode").val();
        $("#choose_print_modal").modal("hide");
        $(".modal-backdrop").hide();
        window.localStorage.setItem("adsTaskSampleSampleCode", sampleCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey", taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");

    });
});



