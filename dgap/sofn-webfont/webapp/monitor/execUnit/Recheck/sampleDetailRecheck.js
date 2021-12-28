var myapp=angular.module("myapp",[]);
myapp.controller("sampleDetailRecheck",function($scope,$http,$state) {
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
        var place = data.data.SAMPLE_PLACE;
        var produce = data.data.PRODUCE_CERTIFICATE;
        var status = data.data.PRODUCTIONS_TATUS;
        $scope.produceDate = data.data.PRODUCE_DATE;
        $scope.sampleDate = data.data.SAMPLE_DATE;
        if (data.data.PRODUCING_AREA == null) {
            $scope.producingAreaName = "";
        } else {
            $scope.producingAreaName = data.data.PRODUCING_AREA_NAME;
        }
        if(place!=undefined){
            if (place=="生产基地/企业") {
                $("#base").prop("checked", true);
            }
            if (place== "屠宰场") {
                $("#slaughter").prop("checked", true);
            }
            if (place== "农贸市场") {
                $("#market").prop("checked", true);
            }
            if (place== "批发市场") {
                $("#wholesale").prop("checked", true);
            }
            if (place== "超市") {
                $("#supermar").prop("checked", true);
            }
            if (place== "其他") {
                $("#else").prop("checked", true);
            }
        }
        if(produce!=undefined){
            if (produce== "无公害农产品") {
                $("#innocuity").prop("checked", true);
            }
            if (produce== "绿色食品") {
                $("#green").prop("checked", true);
            }
            if (produce== "有机食品") {
                $("#organic").prop("checked", true);
            }
            if (produce== "其他") {
                $("#other").prop("checked", true);
            }
        }

        if(status!=undefined){
            if (status== "生产" || status== "自产") {
                $("#production").prop("checked", true);
            }
            if (status=="进货" || status=="收购") {
                $("#stock").prop("checked", true);
            }
        }
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
    function dateFormatUtil(longTypeDate) {
        var dateTypeDate = "";
        var date = new Date();
        date.setTime(longTypeDate);
        dateTypeDate += date.getFullYear();   //年
        dateTypeDate += "-" + getMonth(date); //月
        dateTypeDate += "-" + getDay(date);   //日
        return dateTypeDate;
    }
    //返回 01-12 的月份值
    function getMonth(date) {
        var month = "";
        month = date.getMonth() + 1; //getMonth()得到的月份是0-11
        if (month < 10) {
            month = "0" + month;
        }
        return month;
    }

    //返回01-30的日期
    function getDay(date) {
        var day = "";
        day = date.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return day;
    }

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

    //打印设置
    $("#printArea").click(function(){
        $("#printCount").printArea();
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

    $scope.printAreas = function (data) {
        var sampleCode = $("#showCode").val();
        $("#showTaskName").text( $scope.taskName);
        $("#showSampleCode").text(sampleCode);
        $('#choose_print_modal').modal();
    }

    //打印设置
    $("#printAreaCode").click(function () {
        var sampleCode = $("#showCode").val();
        $("#choose_print_modal").modal("hide");
        $(".modal-backdrop").hide();
        window.localStorage.setItem("adsTaskSampleSampleCode", sampleCode);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey",  $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");

    });
});

