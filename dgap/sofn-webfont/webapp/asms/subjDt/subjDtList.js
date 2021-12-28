 var myapp = angular.module("myapp", []);
myapp.controller("subjDtList", function($scope, $http, $state,$timeout) {
    var vm = this;
    //判定页面权限、tab页、按钮
    var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    for (var i = 0; i < a.length; i++) {
        if (a[i].text == "检测机构主体信息") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    if ($scope.iswrite != "2") {
        $(".addSubjSv").css("display", "none");
        $(".intoSubjSv").css("display", "none");
    }
    a.map(function(item, i) {
        if (item.text == "备案变更待审核") {
            $(".subjDtChangeList").css("display", "inline-block");
        } else if (item.text == "备案注销待审核") {
            $(".subjDtCancelList").css("display", "inline-block");
        } else if (item.text == "备案撤销待审核") {
            $(".subjDtRevokeList").css("display", "inline-block");
        }
    });
    $scope.subjDtListQueryParams = JSON.parse(
        window.sessionStorage.getItem("subjDtListQueryParams")
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
    $scope.dtLevel = [];
    $http
        .post("/sofn-asms-web/sysDict/getSysDictByCode", "1", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.dtLevel = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //获取机构性质数据字典
    $scope.dtNature = {};
    $http
        .post("/sofn-asms-web/sysDict/getSysDictByCode", "25", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.dtNature = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //获取资质数据字典
    $scope.dtQualifications = {};
    $http
        .post("/sofn-asms-web/sysDict/getSysDictByCode", "18", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.dtQualifications = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //重置区域
    $scope.reloadArea = function($event) {
        $("#queryForm").form("reset");
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $scope.dtCode = null;
        $scope.dtLeaderPhone = null;
        $scope.dtContact = null;
        $scope.dtContactPhone = null;
        $scope.dtContactQQ = null;
        $scope.dtContactEmail = null;
        $scope.dtPostcode = null;
        $scope.dtAddress = null;
        $scope.dtRelyOnUnit = null;
        $scope.dtLegalMan = null;
        $scope.dtQualityDirector = null;
        $scope.dtTechnicalDirector = null;
        $scope.dtParameter = null;
        $scope.dtProductRange = null;
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
            url: "/sofn-asms-web/subjDetection/countChangeToAudit",
            method: "post",
            data: { dtAreaId: $scope.getAreaBySelect() }
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
            url: "/sofn-asms-web/subjDetection/countCancelToAudit",
            method: "post",
            data: { dtAreaId: $scope.getAreaBySelect() }
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
            url: "/sofn-asms-web/subjDetection/countRevokeToAudit",
            method: "post",
            data: { dtAreaId: $scope.getAreaBySelect() }
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
        $scope.underArea = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['underArea'];
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        if ($scope.subjDtListQueryParams) {
            $("#dateBegin").val($scope.subjDtListQueryParams.dateBegin);
            $("#dateEnd").val($scope.subjDtListQueryParams.dateEnd);
            $("#status").val($scope.subjDtListQueryParams.status);
            $("input[name='dtName']").val($scope.subjDtListQueryParams.dtName);
            $.fn.mycity(
                "sheng",
                "shi",
                "xian",
                $scope.subjDtListQueryParams.dtAreaId
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

    //批量导入检测机构主体
    $scope.importSubjDt = function() {
        $("#save").attr("disabled", true);
        var fd = new FormData();
        var file = document.querySelector("input[type=file]").files[0];
        fd.append("file", file);
        $http({
            method: "POST",
            url: "/sofn-asms-web/subjDetection/importSubjDetection",
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
                $("#dtGrid")
                    .dataTable()
                    .api()
                    .ajax.reload();
            } else {
                jBox.tip(data.msg, "info");
            }
        });
    };

    //模态框显示
    $("#importSubjDt").click(function() {
        $("#importModal").modal("show");
    });
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    //bootstrap表格
    //检测机构主体信息
    function loadGrid() {
        $("#dtGrid").dataTable({
            ordering: true, //开启排序
            order: [[6, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-asms-web/subjDetection/getSubjDetectionList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
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
                    params.token = window.localStorage.getItem("token");
                    var subjDtListQueryParams = JSON.parse(
                        window.sessionStorage.getItem("subjDtListQueryParams")
                    );
                    params.dtAreaId = $scope.getAreaBySelect();
                    params.dtName = $("input[name='dtName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    // params.status = $("#status").val();
                    var statusArr = $("input[name='status']:checked");
                    var arr = new Array();
                    for (var i = 0; i < statusArr.length; i++) {
                        arr.push($(statusArr[i]).val());
                    }
                    params.status = arr.toString();
                    params.dtCode = formatParams($scope.dtCode);
                    params.dtLeaderPhone = formatParams($scope.dtLeaderPhone);
                    params.dtContact = formatParams($scope.dtContact);
                    params.dtContactPhone = formatParams($scope.dtContactPhone);
                    params.dtContactQQ = formatParams($scope.dtContactQQ);
                    params.dtContactEmail = formatParams($scope.dtContactEmail);
                    params.dtPostcode = formatParams($scope.dtPostcode);
                    params.dtAddress = formatParams($scope.dtAddress);
                    params.dtRelyOnUnit = formatParams($scope.dtRelyOnUnit);
                    params.dtLegalMan = formatParams($scope.dtLegalMan);
                    params.dtTechnicalDirector = formatParams(
                        $scope.dtTechnicalDirector
                    );
                    params.dtQualityDirector = formatParams(
                        $scope.dtQualityDirector
                    );
                    params.dtParameter = formatParams($scope.dtParameter);
                    params.dtProductRange = formatParams($scope.dtProductRange);
                    var dtNature = $("input[name='dtNature']:checked");
                    var dtNatureArr = new Array();
                    for (var i = 0; i < dtNature.length; i++) {
                        dtNatureArr.push($(dtNature[i]).val());
                    }
                    params.dtNatureId = dtNatureArr.toString();

                    var dtQualifications = $(
                        "input[name='dtQualifications']:checked"
                    );
                    var dtQualificationsArr = new Array();
                    for (var i = 0; i < dtQualifications.length; i++) {
                        dtQualificationsArr.push($(dtQualifications[i]).val());
                    }
                    params.dtQualifications = dtQualificationsArr.toString();
                    params.dtLeader = $("input[name='dtLeader']").val();
                    var dtLevel = $("input[name='dtLevel']:checked");
                    var dtLevelArr = new Array();
                    for (var i = 0; i < dtLevel.length; i++) {
                        dtLevelArr.push($(dtLevel[i]).val());
                    }
                    params.dtLevel = dtLevelArr.toString();

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
                        }, 500);
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    orderable: false,
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "dtName",
                    title: "机构名称",
                    width: "25%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 22
                        ) {
                            return (
                                "<a id='dtName" +
                                row.id +
                                "' onmouseover=showPopContent('dtName" +
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
                    data: "dtType",
                    title: "机构类别",
                    width: "10%"
                },
                {
                    data: "dtLevel",
                    title: "机构级别",
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
                    },
                    width: "10%"
                },
                {
                    data: "dtAreaId",
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
                                "<a id='dtAreaId" +
                                row.id +
                                "' onmouseover=showPopContent('dtAreaId" +
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
                    data: "dtLeader",
                    title: "机构负责人",
                    width: "10%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 7
                        ) {
                            return (
                                "<a id='dtLeader" +
                                row.id +
                                "' onmouseover=showPopContent('dtLeader" +
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
                    title: "注册时间"
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
                    data: "dtCode",
                    name: "dtCode",
                    title: "机构代码",
                    visible: false
                },
                {
                    data: "dtLeaderPhone",
                    name: "dtLeaderPhone",
                    title: "负责人电话",
                    visible: false
                },
                {
                    data: "dtContact",
                    name: "dtContact",
                    title: "联系人",
                    visible: false
                },
                {
                    data: "dtContactPhone",
                    name: "dtContactPhone",
                    title: "联系人电话",
                    visible: false
                },
                {
                    data: "dtContactQQ",
                    name: "dtContactQQ",
                    title: "联系人QQ",
                    visible: false
                },
                {
                    data: "dtPostcode",
                    name: "dtPostcode",
                    title: "邮编",
                    visible: false
                },
                {
                    data: "dtContactEmail",
                    name: "dtContactEmail",
                    title: "电子邮箱",
                    visible: false
                },
                {
                    data: "dtAddress",
                    name: "dtAddress",
                    title: "通讯地址",
                    visible: false
                },

                {
                    data: "dtRelyOnUnit",
                    name: "dtRelyOnUnit",
                    title: "依托单位",
                    visible: false
                },
                {
                    data: "dtLegalMan",
                    name: "dtLegalMan",
                    title: "法人",
                    visible: false
                },
                {
                    data: "dtTechnicalDirector",
                    name: "dtTechnicalDirector",
                    title: "技术负责人",
                    visible: false
                },
                {
                    data: "dtQualityDirector",
                    name: "dtQualityDirector",
                    title: "质量负责人",
                    visible: false
                },
                {
                    data: "dtParameter",
                    name: "dtParameter",
                    title: "参数",
                    visible: false
                },
                {
                    data: "dtProductRange",
                    name: "dtProductRange",
                    title: "产品范围",
                    visible: false
                },
                {
                    data: "id",
                    title: "操作",
                    orderable: false,
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjDtView(\'' +
                            data +
                            "')\">查看</a>";
                        if ($scope.iswrite == 2) {
                            butt +=
                                '<a style="cursor: pointer" class="Check-report color" onclick="subjDtRevokeApply(\'' +
                                row.status +
                                "','" +
                                data +
                                "','" +
                                row.dtLevel +
                                "')\">撤销</a>"; //转义传值
                        }
                        return butt;
                    }
                }
            ]
        });
    }

    //重新加载列表
    $scope.querySubjDtList = function($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var subjDtListQueryParams = {};
        subjDtListQueryParams.dtAreaId = $scope.getAreaBySelect();
        subjDtListQueryParams.dtName = $("input[name='dtName']").val();
        subjDtListQueryParams.dateBegin = $("input[name='dateBegin']").val();
        subjDtListQueryParams.dateEnd = $("input[name='dateEnd']").val();
        subjDtListQueryParams.status = $("#status").val();
        window.sessionStorage.setItem(
            "subjDtListQueryParams",
            JSON.stringify(subjDtListQueryParams)
        );
        $("#dtGrid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };

    //跳转到主体备案查看页面
    window.subjDtView = function(data) {
        window.localStorage.removeItem("subjDtId");
        window.localStorage.setItem("subjDtId", data);
        $state.go("index.content.asms/subjDt/subjDtView");
    };
    //跳转到主体撤销申请页面
    window.subjDtRevokeApply = function(status, data, dtLevel) {
        if ($scope.iswrite != "2") {
            jBox.tip("对不起！您没有此操作权限", "info");
            return false;
        }
        if ($scope.organization.orgLevel - 1 != dtLevel) {
            jBox.tip("只能撤销同级检测机构！", "info");
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
            window.localStorage.removeItem("subjDtId");
            window.localStorage.setItem("subjDtId", data);
            $state.go("index.content.asms/subjDt/subjDtRevokeApply");
        }
    };
    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(".subjDtList,.subjDtChangeList,.subjDtCancelList,.subjDtRevokeList").each(
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
    //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
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
                var table = $("#dtGrid").DataTable();
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
    /************************************************
     ******* chart *******
     ************************************************/

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
        url: "/sofn-asms-web/subjDetection/getSubjDetectionStatis",
        grid: [3,3,3,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
