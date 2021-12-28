var myapp = angular.module("myapp", []);
myapp.controller("samplePrint", function ($scope, $http, $state) {
    $scope.sampleCode = window.localStorage.getItem("alesTaskSampleSampleCode");//样品编号

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

    $scope.back = function () {
        $state.go("index.content.asms/check/TaskSample/sample")
    };
});


