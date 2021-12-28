var myapp = angular.module("myapp", []);
myapp.controller("subjElList", function($scope, $http, $state, $timeout) {
    var vm = this;
    //判定页面权限、tab页、按钮
    var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for (var i = 0; i < a.length; i++) {
        if (a[i].text == "执法机构主体信息") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    a.map(function(item, i) {
        if (item.text == "备案变更待审核") {
            $(".subjElChangeList").css("display", "inline-block");
        } else if (item.text == "备案注销待审核") {
            $(".subjElCancelList").css("display", "inline-block");
        } else if (item.text == "备案撤销待审核") {
            $(".subjElRevokeList").css("display", "inline-block");
        }
    });
    $scope.subjElListQueryParams = JSON.parse(
        window.sessionStorage.getItem("subjElListQueryParams")
    );
    //获取用户信息
    $scope.user = {};
    //用户类型  根据用户类型限制是否有撤销权限功能
    var userType = "";
    $scope.organization = {};
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    //获取机构级别数据字典
    $scope.elLevel = [];
    //重置区域
    $scope.reloadArea = function($event) {
        $("#queryForm").form("reset");
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $scope.elCode = null;
        $scope.elLeaderPhone = null;
        $scope.elContact = null;
        $scope.elContactPhone = null;
        $scope.elContactQQ = null;
        $scope.elContactEmail = null;
        $scope.elPostcode = null;
        $scope.elPeopleNum = null;
        $scope.elAddress = null;
        $("#status").val("0");
        if ($scope.organization.orgLevel != "1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if (
                $("#sheng").val() != null &&
                $("#sheng").val() != "" &&
                $("#sheng").val() != undefined
            ) {
                $("#sheng").attr("disabled", true);
            }
            if (
                $("#shi").val() != null &&
                $("#shi").val() != "" &&
                $("#shi").val() != undefined
            ) {
                $("#shi").attr("disabled", true);
            }
            if (
                $("#xian").val() != null &&
                $("#xian").val() != "" &&
                $("#xian").val() != undefined
            ) {
                $("#xian").attr("disabled", true);
            }
        } else {
            $.fn.mycity("sheng", "shi", "xian");
        }
    };

    $scope.countChangeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnforceLaw/countChangeToAudit",
            method: "post",
            data: { elAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.changeToAuditCount = data.count;
            })
            .error(function() {});
    };
    $scope.countCancelToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnforceLaw/countCancelToAudit",
            method: "post",
            data: { elAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.cancelToAuditCount = data.count;
            })
            .error(function() {});
    };
    $scope.countRevokeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnforceLaw/countRevokeToAudit",
            method: "post",
            data: { elAreaId: $scope.getAreaBySelect() }
        })
            .success(function(data) {
                $scope.revokeToAuditCount = data.count;
            })
            .error(function() {});
    };

    $scope.getAreaBySelect = function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (
            province != null &&
            province != undefined &&
            province != ""
        ) {
            areaId = province;
        }
        return areaId;
    };

    //从缓存中获取数据
    if (window.localStorage.getItem("asmsAllSysDictAndUserInfo")) {
        //获取用户信息
        $scope.user = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["user"];
        $scope.organization = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["organization"];
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        $scope.elLevel = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["orgLevel"];
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        if ($scope.subjElListQueryParams) {
            $("#dateBegin").val($scope.subjElListQueryParams.dateBegin);
            $("#dateEnd").val($scope.subjElListQueryParams.dateEnd);
            $("#status").val($scope.subjElListQueryParams.status);
            $("input[name='elName']").val($scope.subjElListQueryParams.elName);
            $.fn.mycity(
                "sheng",
                "shi",
                "xian",
                $scope.subjElListQueryParams.elAreaId
            );
        }
        loadGrid();
    } else {
        swal("登录超时", "由于您长时间未操作,请重新登录!", "warning");
        setTimeout(function() {
            window.location.href = "#/login/login";
            window.location.reload();
        }, 2500);
    }

    //批量导入执法机构主体
    $scope.importSubjEl = function() {
        $("#save").attr("disabled", true);
        var fd = new FormData();
        var file = document.querySelector("input[type=file]").files[0];
        fd.append("file", file);
        $http({
            method: "POST",
            url: "/sofn-asms-web/subjEnforceLaw/importSubjEnforceLaw",
            data: fd,
            headers: {
                token: window.localStorage.getItem("token"),
                "Content-Type": undefined
            },
            transformRequest: angular.identity
        }).success(function(data) {
            $("#save").attr("disabled", false);
            if (data.httpCode == "200") {
                //上传成功的操作
                jBox.tip("上传成功", "info");
                $("#importModal").modal("hide");
                $("#elGrid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            } else {
                jBox.tip(data.msg, "info");
            }
        });
    };

    //模态框显示
    $("#importSubjEl").click(function() {
        $("#importModal").modal("show");
    });
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    //bootstrap表格
    //执法机构主体信息
    function loadGrid() {
        $("#elGrid").dataTable({
            ordering: true, //开启排序
            order: [[6, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-asms-web/subjEnforceLaw/getSubjEnforceLawList",
                beforeSend: function(request) {
                    request.setRequestHeader(
                        "token",
                        window.localStorage.getItem("token")
                    );
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.page.recordsTotal;
                    });
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].createTime != null &&
                            json.list[i].createTime != "" &&
                            json.list[i].createTime != undefined
                        ) {
                            json.list[i].createTime = new Date(
                                parseInt(json.list[i].createTime) + 28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function(params) {
                    var subjElListQueryParams = JSON.parse(
                        window.sessionStorage.getItem("subjElListQueryParams")
                    );
                    params.elAreaId = $scope.getAreaBySelect();
                    params.elName = $("input[name='elName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.token = window.localStorage.getItem("token");
                    params.elUnitNature = $("input[name='elUnitNature']").val();
                    params.elWorkBody = $("input[name='elWorkBody']").val();
                    params.elLeader = $("input[name='elLeader']").val();

                    //绑值
                    params.elCode = formatParams($scope.elCode);
                    params.elLeaderPhone = formatParams($scope.elLeaderPhone);
                    params.elContact = formatParams($scope.elContact);
                    params.elContactPhone = formatParams($scope.elContactPhone);
                    params.elContactQQ = formatParams($scope.elContactQQ);
                    params.elContactEmail = formatParams($scope.elContactEmail);
                    params.elPostcode = formatParams($scope.elPostcode);
                    params.elPeopleNum = formatParams($scope.elPeopleNum);
                    params.elAddress = formatParams($scope.elAddress);

                    var statusarr = $("input[name='status']:checked");
                    var arr = new Array();
                    for (var i = 0; i < statusarr.length; i++) {
                        arr.push($(statusarr[i]).val());
                    }
                    params.status = arr.toString();

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }

                    $scope.chartParams = params; //图标参数赋值
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    orderable: false,
                    width: "4%"
                },
                {
                    data: "elName",
                    title: "机构名称",
                    width: "25%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 22
                        ) {
                            return (
                                "<a id='elName" +
                                row.id +
                                "' onmouseover=showPopContent('elName" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 22) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "elType",
                    title: "机构类别",
                    width: "10%"
                },
                {
                    data: "elLevel",
                    title: "机构级别",
                    width: "10%",
                    render: function(data, type, row) {
                        if (data == "3") {
                            return "县级";
                        } else if (data == "2") {
                            return "市级";
                        } else if (data == "1") {
                            return "省级";
                        } else if (data == "0") {
                            return "部级";
                        }
                    }
                },
                {
                    data: "elAreaId",
                    title: "所属区域",
                    width: "18%",
                    render: function(data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 14
                        ) {
                            return (
                                "<a id='elAreaId" +
                                row.id +
                                "' onmouseover=showPopContent('elAreaId" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 14) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "elLeader",
                    title: "机构负责人",
                    width: "10%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 7
                        ) {
                            return (
                                "<a id='elLeader" +
                                row.id +
                                "' onmouseover=showPopContent('elLeader" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 7) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "createTime",
                    title: "注册时间",
                    width: "10%"
                },
                {
                    data: "elCode",
                    name: "elCode",
                    title: "机构代码",
                    visible: false,
                },
                {
                    data: "svAddress",
                    name: "svAddress",
                    title: "通讯地址",
                    visible: false,
                },
                {
                    data: "elLeaderPhone",
                    name: "elLeaderPhone",
                    title: "负责人电话",
                    visible: false,
                },
                {
                    data: "svAddress",
                    name: "svAddress",
                    title: "通讯地址",
                    visible: false,
                },
                {
                    data: "elContact",
                    name: "elContact",
                    title: "联系人",
                    visible: false,
                },
                {
                    data: "elContactPhone",
                    name: "elContactPhone",
                    title: "联系人电话",
                    visible: false,
                },
                {
                    data: "elContactQQ",
                    name: "elContactQQ",
                    title: "联系人QQ",
                    visible: false,
                },
                {
                    data: "elContactEmail",
                    name: "elContactEmail",
                    title: "电子邮箱",
                    visible: false,
                },
                {
                    data: "elPostcode",
                    name: "elPostcode",
                    title: "邮编",
                    visible: false,
                },
                {
                    data: "elAddress",
                    name: "elAddress",
                    title: "通讯地址",
                    visible: false,
                },
                {
                    data: "status",
                    title: "状态",
                    render: function(data, type, row) {
                        if (data == "0") {
                            return "正常";
                        } else if (data == "1") {
                            return "待变更";
                        } else if (data == "2") {
                            return "待注销";
                        } else if (data == "3") {
                            return "待撤销";
                        } else if (data == "4") {
                            return "已注销";
                        } else if (data == "5") {
                            return "已撤销";
                        } else {
                            return "正常";
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    orderable: false,
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjElView(\'' +
                            data +
                            "')\">查看</a>";
                        if ($scope.iswrite == 2) {
                            butt +=
                                '<a style="cursor: pointer" class="Check-report color" onclick="subjElRevokeApply(\'' +
                                row.status +
                                "','" +
                                data +
                                "','" +
                                row.elLevel +
                                "')\">撤销</a>"; //转义传值
                        }
                        return butt;
                    }
                }
            ]
        });
    }

    //重新加载列表
    $scope.querySubjElList = function($event) {

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        var subjElListQueryParams = {};
        subjElListQueryParams.elAreaId = $scope.getAreaBySelect();
        subjElListQueryParams.elName = $("input[name='elName']").val();
        subjElListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        subjElListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        subjElListQueryParams.status = $("select[id='status']").val();
        window.sessionStorage.setItem(
            "subjElListQueryParams",
            JSON.stringify(subjElListQueryParams)
        );
        $("#elGrid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };
    window.showPopContent = function(id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: "right",
            trigger: "hover",
            content: content,
            delay: 0,
            container: "body"
        });
        $("#" + id).popover("show");
    };
    //跳转到主体备案查看页面
    window.subjElView = function(data) {
        window.localStorage.setItem("subjElId", data);
        $state.go("index.content.asms/subjEl/subjElView");
    };
    //权限判定按钮是否能操作
    //跳转到主体撤销申请页面
    window.subjElRevokeApply = function(status, data, elLevel) {
        if ($scope.iswrite != "2") {
            jBox.tip("对不起！您没有此操作权限", "info");
            return false;
        }
        if ($scope.organization.orgLevel - 1 != elLevel) {
            jBox.tip("只能撤销同级执法机构！", "info");
            return false;
        }
        if (status == "1") {
            jBox.tip("还有变更申请未处理！", "info");
            return false;
        } else if (status == "2") {
            jBox.tip("还有注销申请未处理！", "info");
            return false;
        } else if (status == "3") {
            jBox.tip("还有撤销申请未处理！", "info");
            return false;
        } else if (status == "0") {
            window.localStorage.setItem("subjElId", data);
            $state.go("index.content.asms/subjEl/subjElRevokeApply");
        }
    };
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjElList,.subjElChangeList,.subjElCancelList,.subjElRevokeList").each(
        function(i, o) {
            $(o).click(function() {
                window.sessionStorage.clear();
            });
        }
    );
    //鼠标悬停弹窗显示
    window.showPopContent = function(id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: "right",
            trigger: "hover",
            content: content,
            delay: 0,
            container: "body"
        });
        $("#" + id).popover("show");
    };
    //控制复选框
    $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
        var _limChec = $("input[name='" + limitCheckBoxName + "']:checked");
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
        }
        if (_limChec.length == 0) {
            _chec.prop("checked", true);
        }
        if (_limChec.length > 0 && _lim.length == _limChec.length) {
            _chec.prop("checked", true);
            _limChec.each(function(i, o) {
                $(o).prop("checked", false);
            });
        }
    };


    /**************************
     CUSTOM QUERY CONDITION
     *************************/

    $scope.confirmCondition = function() {
        var modal = $("#modalCondition");
        modal
            .find(".gui-list li")
            .not(".disabled")
            .map(function() {
                var key = $.trim($(this).attr("data-key"));
                var checked = $(this)
                    .find(":checkbox")
                    .is(":checked");
                if (!checked) {
                    var control = $(
                        '.gui-form-group .condition-custom[ng-show="visible' +
                        key +
                        '"]'
                    );
                    var controlType = control.attr("data-control");
                    switch (controlType) {
                        case "text":
                            $scope[key] = "";
                            break;
                        case "select":
                            $scope[key] = "";
                            control
                                .find("option:first")
                                .prop("selected", true)
                                .trigger("change");
                            break;
                        case "checkbox":
                            $scope[key] = "";
                            control
                                .find(":checkbox")
                                .first()
                                .prop("checked", true)
                                .end()
                                .siblings()
                                .prop("checked", false);
                            break;
                        case "date":
                            control
                                .find("input:text")
                                .val("")
                                .trigger("input");
                            break;
                        case "radio":
                            break;
                    }
                }
                $scope["visible" + key] = checked;
                //切换表格列是否可见
                var table = $("#elGrid").DataTable();
                var column = table.column(key + ":name");
                column.visible(checked);
            });
        modal.modal("hide");
    };
    $("#modalCondition")
        .on("change", ".condition-checked-all", function() {
            var checked = $(this).is(":checked");
            $("#modalCondition")
                .find("li")
                .not(".disabled")
                .find(":checkbox")
                .prop("checked", checked);
        })
        .on("change", ".gui-list :checkbox", function() {
            var unchecked = $(this)
                .closest(".gui-list")
                .find(":checkbox")
                .filter(function() {
                    return !$(this).is(":checked");
                });
            $(".condition-checked-all").prop("checked", !!!unchecked.length);
        })
        .on("shown.bs.modal", function() {
            $(".gui-form-group .condition-custom").each(function() {
                var visible = $(this).is(":visible");
                var key = $(this)
                    .attr("ng-show")
                    .replace("visible", "");
                $(".gui-list.list-condition")
                    .find('li[data-key="' + key + '"]')
                    .find(":checkbox")
                    .prop("checked", visible)
                    .trigger("change");
            });
        });

    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-asms-web/subjEnforceLaw/getSubjEnforceLawStatis",
        grid: [1,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
