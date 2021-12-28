var myapp = angular.module("myapp", []);
myapp.controller("codingRuleView", function($scope, $http, $state) {
    var codingRuleId = window.sessionStorage.getItem("rule.codingRuleId");
    // 异步请求错误处理函数
    function ajaxErrorHandler(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }

    $scope.codesegments = [];
    // 获取指定编码规则的码段信息
    $http({
        url: "/sofn-sys-web/codingmanage/codesegments",
        method: "post",
        data: {codingRuleId : codingRuleId},
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'token': window.localStorage.getItem("token")
        },
        transformRequest: function (data) {
            return $.param(data);
        }
    }).success(function (json) {
        if (json.httpCode == 200) {
            $scope.codesegments = json.data;
        } else {
            jBox.tip("请求数据失败", "error");
        }
    }).error(function (json, status) {
        ajaxErrorHandler(status);
    });

})
