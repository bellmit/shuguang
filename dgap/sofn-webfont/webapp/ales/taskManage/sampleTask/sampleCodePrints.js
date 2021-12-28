var myapp = angular.module("myapp", []);
myapp.controller("sampleCodePrints", function ($scope, $http, $state) {
    $scope.sampleCode = window.localStorage.getItem("alesTaskSampleSampleCodes");//样品编号
    $scope.entrustTaskName = window.localStorage.getItem("alesTaskEntrustTaskName_iskeys");//任务名称
    console.log("样品编码===:"+$scope.sampleCode);
    console.log("委托任务===:"+$scope.entrustTaskName);
  /*  window.onload()/!*{
        if(location.href.indexOf('#reloaded')==-1){
            location.href=location.href+"#reloaded";
            location.reload();
        }
    }*!/*/
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
    $scope.printArea = function () {
        star = toUtf8($scope.sampleCode);
        var oper = "1";
        bdhtml = window.document.body.innerHTML;//获取当前页的html代码
        sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
        eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
        prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
        window.document.body.innerHTML = prnhtml;
        window.print();
        window.document.body.innerHTML = bdhtml;
        window.location.reload();
    }
   // window.location.reload();
    $scope.back = function () {
        $state.go("index.content.ales/taskManage/sampleTask/writeMytaskSample")
    }
});


