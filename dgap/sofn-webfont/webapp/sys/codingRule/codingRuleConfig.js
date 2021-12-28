var myapp = angular.module("myapp", []);
myapp.controller("codingRuleConfig", function($scope, $http, $state) {
    var codingRuleId = window.sessionStorage.getItem("rule.codingRuleId");
    $scope.codingRuleName = window.sessionStorage.getItem("rule.codingRuleName");
    console.log($scope.codingRuleName);
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
            $scope.codesegments.forEach(function (codesegment) {
                if (codesegment.sysCodeSegmentValues.length == 0) {
                    var newSegmentValue = {
                        id : "",
                        remark : null,
                        createBy : null,
                        createTime : null,
                        updateBy : null,
                        updateTime : null,
                        delFlag : "N",
                        codeSegmentId : codesegment.id,
                        value : "",
                        descr : "",
                        enableDelete : "Y"
                    };
                    codesegment.sysCodeSegmentValues.push(newSegmentValue);
                }
            });
        } else {
            jBox.tip("请求数据失败", "error");
        }
    }).error(function (json, status) {
        ajaxErrorHandler(status);
    });

    // 新增码段值
    $scope.addValue = function(segmentValueList, segmentId) {
        var newSegmentValue = {
            id : "",
            remark : null,
            createBy : null,
            createTime : null,
            updateBy : null,
            updateTime : null,
            delFlag : "N",
            codeSegmentId : segmentId,
            value : "",
            descr : "",
            enableDelete : "Y"
        };

        segmentValueList.push(newSegmentValue);
    }

    // 删除码段值
    $scope.deleteValue = function(segmentValueList, index) {
        var deletedValueList = [];

        if (confirm('是否删除该配置项？')) {
            deletedValueList = segmentValueList.splice(index, 1);
            var codeSegmentValueId = deletedValueList[0].id;

            if (codeSegmentValueId != "") {
                $http({
                    url: "/sofn-sys-web/codingmanage/deleteCodeValue",
                    method: "post",
                    data: {id: codeSegmentValueId},
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'token': window.localStorage.getItem("token")
                    },
                    transformRequest: function (data) {
                        return $.param(data);
                    }
                }).success(function (json) {
                    if (json.httpCode == 200) {
                        jBox.tip("数据删除成功", "info");
                    } else {
                        jBox.tip("请求数据失败", "error");
                    }
                }).error(function (json, status) {
                    ajaxErrorHandler(status);
                });
            }
        }
    }

    // 保存码段值
    $scope.saveValues = function() {
        var codeSegmentValuesForSave = [];
        for (var i = 0; i < $scope.codesegments.length; i++) {
            codeSegmentValuesForSave = codeSegmentValuesForSave.concat($scope.codesegments[i].sysCodeSegmentValues);
        }

        $http({
            url: "/sofn-sys-web/codingmanage/saveCodeSegmentValue",
            method: "post",
            data: codeSegmentValuesForSave,
            headers: {
                'Content-Type': 'application/json',
                'token': window.localStorage.getItem("token")
            }
        }).success(function (json) {
            if (json.httpCode == 200) {
                jBox.tip("保存成功", "info");
            } else {
                jBox.tip("请求数据失败", "error");
            }
        }).error(function (json, status) {
            ajaxErrorHandler(status);
        });
    }

})
