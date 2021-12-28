var myapp = angular.module("myapp", []).controller("subjElView", function ($scope, $http, $state, $stateParams) {

    //获取本地存储的ID
    $scope.subjElId = window.localStorage.getItem("subjElId");

    //获取机构备案信息
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/subjEnforceLaw/findSubjEnforceLawById",
        method: "post",
        data: {id: $scope.subjElId}
    }).success(function (data) {
        if (data.httpCode == "200") {
            $scope.subjEl = data.data;
            if ($scope.subjEl.elLevel == "3") {
                $scope.subjEl.elLevel = "县级";
            } else if ($scope.subjEl.elLevel == "2") {
                $scope.subjEl.elLevel = "市级";
            } else if ($scope.subjEl.elLevel == "1") {
                $scope.subjEl.elLevel = "省级";
            } else if ($scope.subjEl.elLevel == "0") {
                $scope.subjEl.elLevel = "部级";
            }
            $scope.subjEl.createTime = new Date(parseInt($scope.subjEl.createTime) + 28800000).toISOString().slice(0, 10);
        } else {
            jBox.tip(data.msg, 'info');
        }
        $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
        $scope.checkData($scope.subjEl);
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage = function () {
        window.localStorage.removeItem("subjElId");
        if ($stateParams.source && $stateParams.source == 'sys') {
            $state.go("index.content.sys/user/subjElList");
        } else {
            $state.go("index.content.asms/subjEl/subjElList");
        }
    }
    //检查json对象值是否为空
    $scope.checkData = function (o) {
        for (var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }

})