var myapp = angular.module("myapp", []);
myapp.controller("samplePrint", function ($scope, $http, $state) {

    var token = window.localStorage.getItem("token");

    $scope.name = "";
    // $scope.count=0;
    $scope.getcount = 120;
    $scope.printcount = "";
    $scope.sampleCode = window.localStorage.getItem("sampleCode");//样品编号
    $scope.monitorClass = window.localStorage.getItem("monitorClass");//监测任务
    var deadline = window.localStorage.getItem("deadline");//抽样截至时间
    var sampleOrganId = window.localStorage.getItem("sampleOrganId");//抽样机构代码
    var sampleOrgan = window.localStorage.getItem("sampleOrgan");//监测机构名称
    var organTaskId = window.localStorage.getItem("organTaskId");//机构任务ID
    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");//监测任务
    //  var productTraceabilityCode=window.localStorage.getItem("productTraceabilityCode");//产品追溯码
    // $scope.sampleCode=window.localStorage.getItem("sampleCode");//样品编码
    $http({
        url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTask",
        method: "post",
        params: {"id": organTaskId, "token": token}
    }).success(function (data) {
        $scope.data = data;
        $scope.taskName = data.taskName;
    }).error(function () {
    });

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.sampelPrint = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")
    }
    $scope.test = function (data) {

        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {

        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        if ($scope.monitorClass == "例行监测") {
            $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
        } else if ($scope.monitorClass == "专项监测") {
            $state.go("index.content.monitor/execUnit/specialMonitor/specialReportUpload")
        }
    }

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

    $(function () {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
        //默认展示二维码
        star = toUtf8($scope.sampleCode);
        $("#code").qrcode({
            render: "canvas", //table方式
            width: 100, //宽度
            height: 100, //高度
            text: star //任意内容
        });
    });
    $("canvas").attr("id", "erw");
    var canvas = document.getElementById("erw");
    var context = canvas.getContext("2d");
    var image = new Image();
    var strDataURI = canvas.toDataURL("image/png");
    document.getElementById("code").src = strDataURI;
//打印二维码
    $scope.printArea = function () {
        star = toUtf8($scope.sampleCode);
        $("#code").qrcode({
            render: "canvas", //table方式
            width: 100, //宽度
            height: 100, //高度
            text: star //任意内容
        });
        $("canvas").attr("id", "erw");
        var canvas = document.getElementById("erw");
        var context = canvas.getContext("2d");
        var image = new Image();
        var strDataURI = canvas.toDataURL("image/png");
        document.getElementById("code").src = strDataURI;
        var oper = "1";
        bdhtml = window.document.body.innerHTML;//获取当前页的html代码
        sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
        eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
        prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html
        prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
        window.document.body.innerHTML = prnhtml;
        window.print();
        window.document.body.innerHTML = bdhtml;
        window.location.reload();
    }


    $scope.sampelPrint = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")

    }
    $scope.writeSample = function (data) {
        window.localStorage.setItem("taskName", $scope.taskName);//监测类型
        $state.go("index.content.monitor/execUnit/AdsOrganTask/writeSample")

    }
    $scope.downloadSample = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/downloadsample")

    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")

    }
    $scope.sampleReport = function (data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleReport")
    }

    //打印设置
    // $scope.printArea=function () {
    //     star=toUtf8($scope.sampleCode);
    //         $("#code").qrcode({
    //             render: "canvas", //table方式
    //             width: 80, //宽度
    //             height:80, //高度
    //             text:star //任意内容
    //         });
    //     $("canvas").attr("id","erw");
    //         var canvas = document.getElementById("erw");
    //         var context = canvas.getContext("2d");
    //         var image = new Image();
    //         var strDataURI =canvas.toDataURL("image/png");
    //         document.getElementById("code").src = strDataURI;
    //
    //     bdhtml = window.document.body.innerHTML;
    //     sprnstr = "<!--startprint-->";
    //     eprnstr = "<!--endprint-->";
    //     prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    //     prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
    //     var newWindow=window.open("打印二维码","_blank");
    //     newWindow.document.write(prnhtml);
    //     newWindow.document.close();
    //     setTimeout(function(){
    //         newWindow.print();
    //         newWindow.close();
    //     }, 100);
    // }

});


