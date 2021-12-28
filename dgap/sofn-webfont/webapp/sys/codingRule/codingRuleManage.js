var myapp = angular.module("myapp", []);
myapp.controller("codingRuleManage", function($scope, $http, $state) {
    $scope.gotoRuleConfig = function(ruleId, ruleName) {
        window.sessionStorage.setItem("rule.codingRuleId", ruleId);
        window.sessionStorage.setItem("rule.codingRuleName", ruleName);
        $state.go("index.content.sys/codingRule/codingRuleConfig");
    };
    $scope.gotoRuleView = function(ruleId, ruleName) {
        window.sessionStorage.setItem("rule.codingRuleId", ruleId);
        window.sessionStorage.setItem("rule.codingRuleName", ruleName);
        $state.go("index.content.sys/codingRule/codingRuleView");
    };

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
    $scope.isSubmit = false;
    $scope.rules = [];
    $scope.codesegments = [];
    // 获取编码规则列表
    $http({
        url: "/sofn-sys-web/codingmanage/rules",
        method: "post",
        data: {},
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'token': window.localStorage.getItem("token")
        },
        transformRequest: function (data) {
            return $.param(data);
        }
    }).success(function (json) {
        if (json.httpCode == 200) {
            json.data.forEach(function(e) {
                e.selected = 'N';
            });

            $scope.rules = json.data;
        } else {
            jBox.tip("请求数据失败", "error");
        }
    }).error(function (json, status) {
        ajaxErrorHandler(status);
    });

    // 删除编码规则
    $scope.delRules = function() {
        var ids = [];
        $scope.rules.forEach(function(rule) {
            if (rule.selected == 'Y') {
                ids.push(rule.id);
            }
        });
        if (ids.length == 0) {
            jBox.tip("未勾选要删除的编码规则", "error");
            return;
        }

        if(confirm('是否批量删除已选择行？')) {
            $http({
                url: "/sofn-sys-web/codingmanage/delRules",
                method: "post",
                data: {"ids": ids.toString()},
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'token': window.localStorage.getItem("token")
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                if (json.httpCode == 200) {
                    alert("删除成功!");
                    $state.reload("index.content.sys/codingRule/codingRuleManage");
                } else {
                    jBox.tip(json.msg, "error");
                }
            }).error(function (json, status) {
                ajaxErrorHandler(status);
            });
        }
    }

    // 打开新增编码规则窗口
    $scope.openAddRuleModal = function() {
        $scope.addCodingRule.id = "";
        $scope.addCodingRule.ruleName = "";
        $scope.ruleForm.$setPristine();
        $scope.isSubmit = false;
        $("#addRule_modal").modal('show');
    }

    // 更新编码规则状态
    $scope.changeRuleEnable = function(id, enable) {
        // console.log(id + enable);
        $http({
            url: "/sofn-sys-web/codingmanage/updateRuleStatus",
            method: "post",
            data: {
                "id": id,
                "enable": enable
            },
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            if (json.httpCode == 200) {
                jBox.tip("修改成功", "info");
            } else {
                jBox.tip(json.msg, "error");
            }
        }).error(function (json, status) {
            ajaxErrorHandler(status);
        });
    }

    $scope.addCodingRule = {
        "id": "",
        "ruleName": ""
    };
    // 新增编码规则
    $scope.addRule = function() {
        $scope.isSubmit = true;
        if ($scope.ruleForm.$valid) {
            $http({
                url: "/sofn-sys-web/codingmanage/addRule",
                method: "post",
                data: $scope.addCodingRule,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'token': window.localStorage.getItem("token")
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                if (json.httpCode == 200) {
                    alert("保存成功");
                    $("#addRule_modal").modal('hide');
                } else {
                    jBox.tip(json.msg, "error");
                }
            }).error(function (json, status) {
                ajaxErrorHandler(status);
            });
        }
    }

    // 关闭新增编码规则窗口刷新页面
    $("#addRule_modal").on('hidden.bs.modal', function() {
        $state.reload("index.content.sys/codingRule/codingRuleManage");
    });

    $scope.codingRuleId = "";
    // 打开配置码段规则窗口
    $scope.openAddCodeSegmentModal = function(codingRuleId, isOpen) {
        $scope.codingRuleId = codingRuleId;
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
                if ($scope.codesegments.length == 0) {
                    // console.log($scope.codesegments);
                    var initCodeSegment = {
                        rowId: Math.round(Math.random() * 1000000),
                        selected: "N",
                        id: "",
                        codingRuleId: codingRuleId,
                        seqNo: "",
                        length: "",
                        segmentName: "",
                        generateMode: "配置固定值"
                    };

                    $scope.codesegments.push(initCodeSegment);
                } else {
                    $scope.codesegments.forEach(function(e) {
                        e.selected = "N";
                        e.rowId = Math.round(Math.random() * 1000000);
                    });
                }
                if (isOpen) {
                    $("#addCodeSegmentModal").modal('show');
                }
            } else {
                jBox.tip("请求数据失败", "error");
            }
        }).error(function (json, status) {
            ajaxErrorHandler(status);
        });
    }

    // 新增码段输入框
    $scope.addCodeSegment = function() {
        var initCodeSegment = {
            rowId: Math.round(Math.random() * 1000000),
            selected: "N",
            id: "",
            codingRuleId: $scope.codingRuleId,
            seqNo: "",
            length: "",
            segmentName: "",
            generateMode: "配置固定值"
        };

        $scope.codesegments.push(initCodeSegment);
    }

    // 保存码段信息
    $scope.saveCodeSegment = function() {
        for (i = 0; i < $scope.codesegments.length; i++) {
            if (typeof $scope.codesegments[i].segmentName == "undefined") {
                alert('码段名称不能为空！')
                return;
            }
            if (typeof $scope.codesegments[i].seqNo.length == "undefined") {
                alert('顺序号不能为空！')
                return;
            }
            if (typeof $scope.codesegments[i].length == "undefined") {
                alert('码段位数不能为空！');
                return;
            }

            var length = parseInt($scope.codesegments[i].length);
            if (length > 24) {
                alert('码段位数输入值不能大于24！')
                return;
            }
        }

        $http({
            url: "/sofn-sys-web/codingmanage/saveCodeSegment",
            method: "post",
            data: $scope.codesegments,
            headers: {
                'Content-Type': 'application/json',
                'token': window.localStorage.getItem("token")
            }
        }).success(function (json) {
            if (json.httpCode == 200) {
                alert("保存成功");
                $("#addCodeSegmentModal").modal('hide');
            } else {
                jBox.tip("请求数据失败", "error");
            }
        }).error(function (json, status) {
            ajaxErrorHandler(status);
        });
    }

    // 删除码段信息
    $scope.deleteCodeSegment = function() {
        console.log($scope.codesegments);
        var ids = [];
        var rowIdForDelete = [];
        var selectedCount = 0;

        if(confirm('是否确定删除？')) {
            $scope.codesegments.forEach(function(codesegment) {
                if (codesegment.selected == 'Y') {
                    if (codesegment.id.length != 0) {
                        ids.push(codesegment.id);
                    }
                    // $scope.codesegments.splice(index, 1);
                    rowIdForDelete.push(codesegment.rowId);
                    selectedCount++;
                }
            });
            if (selectedCount == 0) {
                jBox.tip("未勾选要删除的码段", "error");
                return;
            }

            rowIdForDelete.forEach(function (rowId) {
                var i = -1;
                for (index = 0; index < $scope.codesegments.length; index++) {
                    if ($scope.codesegments[index].rowId == rowId) {
                        i = index;
                        break;
                    }
                }
                if (i > -1) {
                    $scope.codesegments.splice(i, 1);
                }
            });

            if (ids.length > 0) {
                $http({
                    url: "/sofn-sys-web/codingmanage/delCodeSegments",
                    method: "post",
                    data: {"ids": ids.toString()},
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'token': window.localStorage.getItem("token")
                    },
                    transformRequest: function (data) {
                        return $.param(data);
                    }
                }).success(function (json) {
                    if (json.httpCode == 200) {
                        alert("删除成功!");
                        // $scope.openAddCodeSegmentModal($scope.codingRuleId, false);
                    } else {
                        jBox.tip(json.msg, "error");
                    }
                }).error(function (json, status) {
                    ajaxErrorHandler(status);
                });
            }
        }
    }
})
