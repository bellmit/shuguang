var myapp=angular.module("myapp",[]);
myapp.controller("sampleInfo",function($scope,$http,$state) {
    $("#newTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleInfo")
    });

    $("#bePubTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/monitorInfo")
    });

    //获取本地存储的sampleCode
    $scope.sampleCode=window.localStorage.getItem("ads_sample_code");
    $scope.productTraceabilityCode=window.localStorage.getItem("ads_product_traceability_code");
    $scope.sampleName=window.localStorage.getItem("ads_sample_name");

    var a=$scope.sampleCode;
    var b=$scope.sampleName;
    var c=$scope.productCode;
    /*  alert(a);
     */

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.historyTaskList = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/historyTaskList")
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
        /* alert($scope.ads);*/
        $scope.ads.sampleTime=  $scope.ads.sampleDate.substring(0,10);
        var place=$scope.ads.samplePlace;
        var produce=$scope.ads.produceCertificate;
        var status=$scope.ads.productionsTatus;
        if (place.indexOf("生成基地/企业")!=-1) {
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
        if (status.indexOf("生产")!=-1) {
            $("#production").prop("checked",true);
        }
        if (status.indexOf("进货")!=-1) {
            $("#stock").prop("checked",true);
        }

    }).error(function (data) {
        jBox.tip("数据有误", 'info');
    });


    //导出excel
    $(function(){
        $("#export").click(function(){
            var divid="adminTable";
            var tableid="grid";
            if($scope.ads!=null){
                toExcel(divid,tableid);
            }else{
                alert('数据源没有数据');
                return false;
            }

        });
    });
    function toExcel(divid,tableid) {
        if (/msie/.test(navigator.userAgent.toLowerCase()))
        {
            var inStr=getData(tableid);
            var newwindow = window.open("","_blank");
            newwindow.document.write(inStr);
            newwindow.document.close();
            newwindow.document.execCommand('saveas',true,'order.csv');
            newwindow.close();
        } else {
            window.open('data:application/vnd.ms-excel,'
                + encodeURIComponent($("#"+divid).html()));
            e.preventDefault();
        }
    }

    function getData(inTbl)
    {
        var curTbl = document.getElementById(inTbl);
        if (curTbl.rows.length > 65000) {
            alert('源行数不能大于65000行');
            return false;
        }
        if (curTbl.rows.length <= 1) {
            alert('数据源没有数据');
            return false;
        }
        var outStr = "样品名称"+"\t"+"样品编号"+"\t"+"商标"+"\t"+"包装"+"\t";
        outStr=outStr+"等级"+"\t"+"标识"+"\t"+"型号规格"+"\t"+"执行标准"+"\t"+"生成日期或批号"+"\t"+"产品认证情况"+"\t"+"证书编号"+"\t"+"抽样场所"+"\t"+"抽样基数"+"\t"+"受检单位情况"+"\t"+"受检单位名称"+"\t"+"通讯地址"+"\t"+"法定人代表"+"\t"+"联系人"+"\t"+"电话"+"\t"+"传真"+"\t"+"受检人"+"\t"+"电话"+"\t"+"传真"+"\t"+"生产单位情况"+"\t"+"单位名称"+"\t"+"通讯地址"+"\t"+"邮编"+"\t"+"联系人"+"\t"+"电话"+"\t"+"传真"+"\t"+"抽样单位情况"+"\t"+"单位名称"+"\t"+"联系人"+"\t"+"通讯地址"+"\t"+"邮编"+"\t"+"单位名称"+"\t"+"联系电话"+"\t"+"传真"+"\t"+"E-mail"+"\t"+"检测任务依据"+"\t"+"抽样时间"+"\t"+"抽样人员";

        $("#"+inTbl+" tr").each(function(){
            var tdArry=$(this).children("td");
            var tdlen=tdArry.length -1;
            for (var j=0;j<tdlen;j++)
            {
                var d=tdArry.eq(j).text();
                outStr =outStr + d + "\t";
            }
            outStr=outStr+"\r\n";
        });

        return outStr;
    }
});



