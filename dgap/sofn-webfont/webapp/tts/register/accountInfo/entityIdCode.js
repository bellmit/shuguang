var myapp = angular.module("myapp", []).controller("entityIdCode", function($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")

    inits();
    //init 初始化数据


    function inits() {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        if (entityInfo) {
            $scope.realName = entityInfo.data.legalName;
            $scope.accounts = entityInfo.data.account;
            $scope.enterpriseName = entityInfo.data.enterpriseName;
            $scope.address = entityInfo.data.address;
            $scope.legalPhone = entityInfo.data.legalPhone;
            $scope.userIdcode = entityInfo.data.userIdcode;
            $scope.entityIdcode = entityInfo.data.entityIdcode;
        }
    };


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
    var star = toUtf8("用户码:" + $scope.userIdcode + "身份码:" + $scope.entityIdcode);
    $("#ewm").qrcode({
        width: 200, //宽度
        height: 200, //高度
        text: star //任意内容
    });
});
