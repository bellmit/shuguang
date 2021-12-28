var myapp = angular.module("myapp", []).controller("subjElRevokeAudit", function ($scope, $http, $state) {

    //获取本地存储的ID
    $scope.subjElRevokeId = window.localStorage.getItem("subjElRevokeId");
    $('#revokeForm').bootstrapValidator();//初始化验证，必须有

    //获取撤销信息
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/subjEnforceLaw/findSubjElRevokeById",
        method: "post",
        data: {id: $scope.subjElRevokeId}
    }).success(function (data) {
        $scope.subjEl = data.subjEl;
        if ($scope.subjEl.elLevel == "0") {
            $scope.subjEl.elLevelName = "部级";
        } else if ($scope.subjEl.elLevel == "1") {
            $scope.subjEl.elLevelName = "省级";
        } else if ($scope.subjEl.elLevel == "2") {
            $scope.subjEl.elLevelName = "市级";
        } else if ($scope.subjEl.elLevel == "3") {
            $scope.subjEl.elLevelName = "县级";
        }
        $scope.subjElRevoke = data.subjElRevoke;
        if ($scope.subjElRevoke.auditState == "1" || $scope.subjElRevoke.auditState == "2") {
            $("textarea[name='auditSuggestion']").attr("readonly", "readonly");
        } else {
            $("#passBut").css("display", "inline-block");
            $("#refuseBut").css("display", "inline-block");
        }
        $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
    }).error(function (data) {
        
    });

    //审核备案撤销申请
    $scope.auditSubjElRevoke = function (auditState) {
        if (!$("#revokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjElRevoke.auditState = auditState;
        $("button").attr("disabled", true);
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnforceLaw/auditSubjElRevoke",
            method: "post",
            data: $scope.subjElRevoke
        }).success(function (data) {
            $("button").attr("disabled", false);
            if (data.httpCode == "200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjElRevokeId");
                setTimeout(function () {
                    $state.go("index.content.asms/subjEl/subjElRevokeList");
                }, 500)
            } else {
                jBox.tip(data.msg, 'info');
            }
        }).error(function (data) {
                $("button").attr("disabled", false);
                jBox.tip("审核失败", 'info');
        });
    }

    //清除缓存
    $scope.removeLocalStorage = function () {
        window.localStorage.removeItem("subjElRevokeId");
        $state.go("index.content.asms/subjEl/subjElRevokeList");
    }
});