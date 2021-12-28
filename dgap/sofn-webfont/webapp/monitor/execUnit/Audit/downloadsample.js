var myapp = angular.module("myapp", []);
myapp.controller("downloadsample", function ($scope, $http, $state) {
    $scope.monitorClass=window.localStorage.getItem("monitorClass");//监测任务
    var taskName=window.localStorage.getItem("taskName");//监测任务名称
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    var productTraceabilityCode=window.localStorage.getItem("productTraceabilityCode");
    $http({
        // url:"/sofn-ads-web/adsOrganTask/findAllAdsOrganTask",
        // method:"post",
        // params:{id:$scope.subjSvId}
    }).success(function (data) {


    }).error(function () {
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
    $scope.name="11";
    star=toUtf8($scope.name);
    $("#code").qrcode({
        render: "table", //table方式
        width: 90, //宽度
        height:90, //高度
        text:star //任意内容
    });
    //打印设置
    $("#printArea").click(function(){
        $("#printCount").printArea();
    });

    /**
     * 下载抽样单
     */
    $scope.downFile = function () {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-ads-web/adsMonitorSample/downloadSampleList",
                    method:"post",
                    dataType:"json",
                    params : {"monitorClass":$scope.monitorClass},
                    responseType: 'arraybuffer',
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                }).success(function(data){
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if(blob.size > 0){
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '抽样单.doc');
                        a.click();
                    }else{
                        jBox.tip("下载失败！","info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("下载操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    }
    //状态栏样式
    $(function () {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function () {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
    });
    $scope.downloadSample = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/downloadsample")

    }
    $scope.sampleMessage = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/sampleMessage")

    }

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }

    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/testList")
    }
    $scope.import = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/import")
    }
    $scope.routineMonitorList = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/routineMonitorList")
    }
    $scope.routineReportUpload = function(data) {
        $state.go("index.content.monitor/execUnit/Audit/auditReportUpload")
    }
});


