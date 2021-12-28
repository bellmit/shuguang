var myapp = angular.module("myapp", []);
myapp.controller("welcome", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");
//判定页面权限、tab页、按钮
    $http({
        url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
        method: "post",
        data: {
            type: "ADS"
        },
        headers: {
            "Accept": "*/*",
            token: window.localStorage.getItem("token")
        },
    }).success(function(data){
        var root = _.groupBy(data.data, 'menuValue');
        var current = root["ADS"];
        window.localStorage.setItem("AdsMenuObj",JSON.stringify(current));
    }).error(function(){
    });

    $.ajax({
        url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
        type: "post",
        dataType: "json",
        data: {"token":token},
        success: function (data) {
            var userType = data.user.userType;
            // if("SUPERADMIN" == userType){
            //     var htm="<li><a name = '帮助中心'>帮助中心</a> </li>";
            //     $("#userListForAll li").eq(0).after(htm);

            // }
            orgId = data.user.orgId;
            $("#grid").dataTable().api().ajax.reload();
        },
        fail: function (data) {
            $.jBox.tip("失败");
        }
    });
    $(document).ready(function(){

    })
});

