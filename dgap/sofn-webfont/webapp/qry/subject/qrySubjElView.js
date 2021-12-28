var myapp = angular.module("myapp", []);
myapp.controller("qrySubjElView", function ($scope, $http, $state) {

    /** 第一部分，初始化时加载的内容 **/
    //获取本地存储的ID
    $scope.subjElId = window.localStorage.getItem("subjElId");
    //获取用户信息
    $scope.user = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post"
    }).success(function (data) {
        $scope.user = data.user;
        if ($scope.user.userType == "SYSADMIN") {
            $scope.user.userType = "管理员";
        }
    }).error(function (data) {
        
    })

    //获取机构备案信息
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findSubjEnforceLawById",
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
            $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
            //空字段默认为"无"
            $scope.checkData($scope.subjEl);

        } else {
            jBox.tip(data.msg, 'info');
        }
    }).error(function (data) {
        
    })

    //分页
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-qry-web/subject/getChangeListByElId",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                if (json && json.httpCode != 200) {
                    jBox.tip(json.msg);
                    return;
                }
                json.recordsTotal = json.page.recordsTotal; //总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.start = json.page.start; //起始位置
                json.length = json.page.length;
                for (var i = 0; i < json.list.length; i++) {
                    if (json.list[i].APPLY_TIME != null && json.list[i].APPLY_TIME != "" && json.list[i].APPLY_TIME != undefined) {
                        json.list[i].APPLY_TIME = new Date(parseInt(json.list[i].APPLY_TIME) + 28800000).toISOString().slice(0, 10);
                    }
                }
                return json.list;
            },
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: function (params) {
                params.token = window.localStorage.getItem("token");
                params.id = $scope.subjElId;
            },
            type: "post"
        },
        columns: [
            {
                //data : "RN",
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }

            }, {
                data: "CHANGE_CONTENT",
                title: "变更内容",
                render: function (data, type, row) {
                    if (data != null && data != undefined && data.length > 8) {
                        return "<a id='" + row.ID + "' onmouseover=showPopContent('" + row.ID + "','" + data + "')>" + data.substring(0, 8) + "...</a>";
                    } else {
                        return data;
                    }
                }
            }, {
                data: "APPLY_REASON",
                title: "变更原因"
            }, {
                data: "APPLY_TIME",
                title: "申请时间"
            },
            {
                data: "AUDIT_STATE",
                title: "审核状态",
                render: function (data, type, row) {
                    if (data != null && data != "" && data != undefined) {
                        if (data == "2") {
                            return "未通过"
                        } else if (data == "1") {
                            return "已通过";
                        } else if (data == "0") {
                            return "待审核";
                        }
                    } else {
                        return "待审核";
                    }
                }
            }, {
                data: "ID",
                title: "操作",
                render: function (data, type, row) { // 模板化列显示内容
                    return '<a style="cursor: pointer" class="Check-report"  onclick="viewSubjElChange(\'' + data + '\')">查看</a>'; //转义传值
                }
            }
        ]
    });

    /** 第二部分，主动调用才执行的部分 **/
    //返回列表页面
    $scope.callBack = function () {
        window.localStorage.removeItem("subjElId");
        $state.go("index.content.qry/subject/qrySubjElList");
    }

    //显示变更明细数据
    window.checkChangeDetail = function (changeId) {
        $("#change_modal").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/subject/findAdministrativePenaltyById",
            method: "post",
            data: {
                id: changeId
            }
        }).success(function (data) {
            //得到变更前信息
            $scope.before = data.alesSubjEnforceLaw;
            //得到变更后信息
            $scope.after = data.alesSubjElChange;

        }).error(function (data) {
            
        })
    };

    //查看详情页面
    window.viewSubjElChange = function (id) {
        $("#changeModal").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/subject/findSubjElChangeById",
            method: "post",
            data: {id: id}
        }).success(function (data) {
            //得到变更前信息
            $scope.before = data.subjEl;
            //得到变更后信息
            $scope.after = data.subjElChange;
            if ($scope.after.elLeaderPhone == null || $scope.after.elLeaderPhone == undefined) {
                $scope.after.elLeaderPhone = "";
            }
            if ($scope.after.elContactQQ == null || $scope.after.elContactQQ == undefined) {
                $scope.after.elContactQQ = "";
            }
            if ($scope.after.elContactEmail == null || $scope.after.elContactEmail == undefined) {
                $scope.after.elContactEmail = "";
            }
            if ($scope.after.elPostcode == null || $scope.after.elPostcode == undefined) {
                $scope.after.elPostcode = "";
            }
            if ($scope.after.elUnitNature == null || $scope.after.elUnitNature == undefined) {
                $scope.after.elUnitNature = "";
            }
            if ($scope.after.elWorkBody == null || $scope.after.elWorkBody == undefined) {
                $scope.after.elWorkBody = "";
            }
            if ($scope.after.elPeopleNum == null || $scope.after.elPeopleNum == undefined) {
                $scope.after.elPeopleNum = "";
            }
            if ($scope.before.elLevel == "0") {
                $scope.before.elLevel = "部级";
            } else if ($scope.before.elLevel == "1") {
                $scope.before.elLevel = "省级";
            } else if ($scope.before.elLevel == "2") {
                $scope.before.elLevel = "市级";
            } else if ($scope.before.elLevel == "3") {
                $scope.before.elLevel = "县级";
            }
            if ($scope.after.elLevel == "0") {
                $scope.after.elLevel = "部级";
            } else if ($scope.after.elLevel == "1") {
                $scope.after.elLevel = "省级";
            } else if ($scope.after.elLevel == "2") {
                $scope.after.elLevel = "市级";
            } else if ($scope.after.elLevel == "3") {
                $scope.after.elLevel = "县级";
            }
            var state = $scope.after.auditState;
            if (state) {
                if (state == "2") {
                    state = "未通过"
                } else if (state == "1") {
                    state = "已通过";
                } else if (state == "0") {
                    state = "待审核";
                }
            }else {
                state = "待审核";
            }
            $scope.after.auditState = state;
            $scope.checkData($scope.before);
            $scope.checkData($scope.after);
        }).error(function (data) {
            
        })
    }
    //检查json对象值是否为空
    $scope.checkData = function (o) {
        for (var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }
    //鼠标悬停弹窗显示
    window.showPopContent = function (id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: 'right',
            trigger: 'hover',
            content: content,
            delay: 0,
            container: 'body'
        });
        $("#" + id).popover("show");
    }
});
