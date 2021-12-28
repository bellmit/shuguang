var myapp=angular.module("myapp",[]);
myapp.controller("sampleInfo",function($scope,$http,$state) {
    //获取本地存储的sampleCode
    $scope.sampleCode=window.localStorage.getItem("ads_sample_code");
    $scope.productTraceabilityCode=window.localStorage.getItem("ads_product_traceability_code");
    $scope.sampleName=window.localStorage.getItem("ads_sample_name");
    $scope.monitorClass=window.localStorage.getItem("ads_monitor_class");
    var token =  window.localStorage.getItem("token");
    var a=$scope.sampleCode;
    var b=$scope.sampleName;
    var c=$scope.productCode;

    if( $scope.productTraceabilityCode=="null"){
        $scope.productTraceabilityCode = "";
    }

    $("#newTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/sampleInfo")
    });

    $("#bePubTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/monitorInfo")
    });

    //返回到监测信息汇总
    $scope.goBack = function () {
        if($scope.monitorClass=="专项监测"){
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/specialMonitorView")
        }else{
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsCheckInfo/routineMonitorView")
        }
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
               /*alert($scope.ads.samplePlace)*/
            //$scope.ads.sampleTime=  $scope.ads.sampleDate;
            var newDate = new Date($scope.ads.sampleDate);
            var y = 1900+newDate.getYear();
            var m = "0"+(newDate.getMonth()+1);
            var d = "0"+newDate.getDate();
            $scope.ads.sampleDate = y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
            var place=$scope.ads.samplePlace;
            var produce=$scope.ads.produceCertificate;
            var status=$scope.ads.productionsTatus;
            if (place.indexOf("生产基地/企业")!=-1) {
                $("#base").prop("checked",true);
            };
            if (place.indexOf("屠宰场")!=-1) {
                $("#slaughter").prop("checked",true);
            };
            if (place.indexOf("农贸市场")!=-1) {
                $("#market").prop("checked",true);
            }
            if (place.indexOf("批发市场")!=-1) {
                $("#wholesale").prop("checked",true);
            }
            if (place.indexOf("超市")!=-1) {
                $("#supermar").prop("checked",true);
            }
            if (place.indexOf("其他")!=-1) {
                $("#else").prop("checked",true);
            }
            if(produce!=null){
                if (produce.indexOf("无公害农产品")!=-1) {
                    $("#innocuity").prop("checked",true);
                }
                if (produce.indexOf("绿色食品")!=-1) {
                    $("#green").prop("checked",true);
                }
                if (produce.indexOf("有机食品")!=-1) {
                    $("#organic").prop("checked",true);
                }
                if (produce.indexOf("其他")!=-1) {
                    $("#other").prop("checked",true);
                }
            }

            if(status!=null){
                if (status.indexOf("自产")!=-1) {
                    $("#production").prop("checked",true);
                }
                if (status.indexOf("收购")!=-1) {
                    $("#stock").prop("checked",true);
                }
            }
        }).error(function (data) {
            jBox.tip("数据有误", 'info');
        });



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
                        a.setAttribute('download', '抽样单信息.xls');
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


    $http({
        url:"/sofn-ads-web/adsOrganTask/getTaskNameBySampleCode",
        method:"post",
        params:{"token":token,"samplecode":a,"monitorClass":"例行监测"}
    }).success(function(data){
        $scope.taskName=data.adsMonitorTask[0].taskName;
    }).error(function(){
    });

    $scope.printSampleCode = function () {

        $("#showTaskName").text( $scope.taskName);
        $("#showSampleCode").text(a);
        $('#choose_print_modal').modal();
    }

    //打印设置
    $("#printArea").click(function () {
        $("#choose_print_modal").modal("hide");
        $(".modal-backdrop").hide();
        window.localStorage.setItem("adsTaskSampleSampleCode", a);//样品编码
        window.localStorage.setItem("adsTaskEntrustTaskName_iskey",  $scope.taskName);//任务名称
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleCodePrint");

    });
});


