var myapp = angular.module("myapp", []);
myapp.controller("qrySubjElList", function($scope, $http, $state,$timeout) {
    var vm = this;
    //判定页面权限、tab页、按钮
    var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function(item, i) {
        if (item.text == "检测机构主体") {
            $(".qrySubjDtList").css("display", "inline-block");
        } else if (item.text == "监管机构主体") {
            $(".qrySubjSvList").css("display", "inline-block");
        } else if (item.text == "生产经营主体") {
            $(".qrySubjEntList").css("display", "inline-block");
        }
    });

    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post"
    })
        .success(function(data) {
            $scope.user = data.user;
            $scope.organization = $scope.user.organization;
            //定义一个管辖区域，通过机构级别+区域来判断
            if ($scope.organization.orgLevel != 4) {
                $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
            }else {
                $scope.underArea = $scope.organization.regionId;
            }
            //初始化区域下拉框初始选项
            $scope.reloadArea();
            loadGrid();
            //机构级别限制
            setTimeout(function() {
                if ($scope.organization.orgLevel == 2) {
                    //省级限制部级不可选
                    $("input[name='elLevel']").each(function(i, o) {
                        if ($(o).val() == 0) {
                            $(o).prop("disabled", true);
                        }
                    });
                } else if ($scope.organization.orgLevel == 3) {
                    //市级限制省,部级不可选
                    $("input[name='elLevel']").each(function(i, o) {
                        if ($(o).val() == 0) {
                            $(o).prop("disabled", true);
                        }
                        if ($(o).val() == 1) {
                            $(o).prop("disabled", true);
                        }
                    });
                } else if ($scope.organization.orgLevel == 4) {
                    //县级限制市,省,部级不可选
                    $("input[name='elLevel']").each(function(i, o) {
                        if ($(o).val() == 0) {
                            $(o).prop("disabled", true);
                        }
                        if ($(o).val() == 1) {
                            $(o).prop("disabled", true);
                        }
                        if ($(o).val() == 2) {
                            $(o).prop("disabled", true);
                        }
                    });
                }
            }, 500);
        })
        .error(function(data) {
            
        });
    //获取机构级别数据字典
    $scope.elLevel = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "1", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.elLevel = JSON.parse(res.data.data);
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
        $scope.elCode = null;
        $scope.elLeaderPhone = null;
        $scope.elContact = null;
        $scope.elContactPhone = null;
        $scope.elContactQQ = null;
        $scope.elContactEmail = null;
        $scope.elPostcode = null;
        $scope.elPeopleNum = null;
        $scope.elAddress = null;
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
        $("input[name='elLevelLimit']:first").prop("checked", true);
        $("input[name='statuslLimit']").prop("checked", true);
        //机构级别默认不限
        if ($scope.organization.orgLevel == 2) {
            //省级限制部级不可选
            $("input[name='elLevel']").each(function(i, o) {
                if ($(o).val() == 0) {
                    $(o).prop("disabled", true);
                }
            });
        } else if ($scope.organization.orgLevel == 3) {
            //市级限制省,部级不可选
            $("input[name='elLevel']").each(function(i, o) {
                if ($(o).val() == 0) {
                    $(o).prop("disabled", true);
                }
                if ($(o).val() == 1) {
                    $(o).prop("disabled", true);
                }
            });
        } else if ($scope.organization.orgLevel == 4) {
            //县级限制市,省,部级不可选
            $("input[name='elLevel']").each(function(i, o) {
                if ($(o).val() == 0) {
                    $(o).prop("disabled", true);
                }
                if ($(o).val() == 1) {
                    $(o).prop("disabled", true);
                }
                if ($(o).val() == 2) {
                    $(o).prop("disabled", true);
                }
            });
        }
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
                url: "/sofn-qry-web/subject/getSubjEnforceLawList_v2",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
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
                    $scope.$apply(function() {
                        $scope.tableTotal = json.page.recordsTotal;
                    });
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    var statusArr = $("input[name='status']:checked");
                    var arr = new Array();
                    for (var i = 0; i < statusArr.length; i++) {
                        arr.push($(statusArr[i]).val());
                    }
                    params.status = arr.toString();
                    params.elAreaId = $scope.getAreaBySelect();
                    params.elName = $("input[name='elName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.token = window.localStorage.getItem("token");
                    params.elUnitNature = $("input[name='elUnitNature']").val();
                    params.elWorkBody = $("input[name='elWorkBody']").val();
                    params.elLeader = $("input[name='elLeader']").val();
                    var elLevel = $("input[name='elLevel']:checked");
                    var elLevelArr = new Array();
                    for (var i = 0; i < elLevel.length; i++) {
                        elLevelArr.push($(elLevel[i]).val());
                    }
                    params.elLevel = elLevelArr.toString();

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }
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
                },
                {
                    data: "elName",
                    title: "机构名称",
                    width: "30%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 25
                        ) {
                            return (
                                "<a id='elName" +
                                row.id +
                                "' onmouseover=showPopContent('elName" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 25) +
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
                },
                {
                    data: "elLevel",
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
                    }
                },
                {
                    data: "elAreaId",
                    title: "所属区域",
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
                    },
                },
                {
                    data: "elLeader",
                    title: "机构负责人",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 10
                        ) {
                            return (
                                "<a id='elLeader" +
                                row.id +
                                "' onmouseover=showPopContent('elLeader" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 10) +
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
        $state.go("index.content.qry/subject/qrySubjElView");
    };

    //导出Excel
    $scope.exportData = function() {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/subject/exportSubjEl");
        var input2 = $("<input>");
        var statusArr = $("input[name='status']:checked");
        var arr = new Array();
        for (var i = 0; i < statusArr.length; i++) {
            arr.push($(statusArr[i]).val());
        }
        input2.attr("type", "hidden");
        input2.attr("name", "status");
        input2.attr("value", arr.toString());
        var input3 = $("<input>");
        input3.attr("type", "hidden");
        input3.attr("name", "elAreaId");
        input3.attr("value", $scope.getAreaBySelect());
        var input4 = $("<input>");
        input4.attr("type", "hidden");
        input4.attr("name", "elName");
        input4.attr("value", $("input[name='elName']").val());
        var input5 = $("<input>");
        input5.attr("type", "hidden");
        input5.attr("name", "dateBegin");
        input5.attr("value", $("input[name='dateBegin']").val());
        var input6 = $("<input>");
        input6.attr("type", "hidden");
        input6.attr("name", "dateEnd");
        input6.attr("value", $("input[name='dateEnd']").val());
        var input7 = $("<input>");
        input7.attr("type", "hidden");
        input7.attr("name", "token");
        input7.attr("value", window.localStorage.getItem("token"));
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "elUnitNature");
        input8.attr("value", $("input[name='elUnitNature']").val());
        var input9 = $("<input>");
        input9.attr("type", "hidden");
        input9.attr("name", "elWorkBody");
        input9.attr("value", $("input[name='elWorkBody']").val());
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "elLeader");
        input10.attr("value", $("input[name='elLeader']").val());
        var elLevel = $("input[name='elLevel']:checked");
        var elLevelArr = new Array();
        for (var i = 0; i < elLevel.length; i++) {
            elLevelArr.push($(elLevel[i]).val());
        }
        var input11 = $("<input>");
        input11.attr("type", "hidden");
        input11.attr("name", "elLevel");
        input11.attr("value", elLevelArr.toString());
        var input12 = $("<input>");
        input12.attr("type", "hidden");
        input12.attr("name", "elCode");
        input12.attr("value", formatParams($scope.elCode));
        var input13 = $("<input>");
        input13.attr("type", "hidden");
        input13.attr("name", "elLeaderPhone");
        input13.attr("value", formatParams($scope.elLeaderPhone));
        var input16 = $("<input>");
        input16.attr("type", "hidden");
        input16.attr("name", "elContact");
        input16.attr("value", formatParams($scope.elContact));
        var input17 = $("<input>");
        input17.attr("type", "hidden");
        input17.attr("name", "elContactPhone");
        input17.attr("value", formatParams($scope.elContactPhone));
        var input18 = $("<input>");
        input18.attr("type", "hidden");
        input18.attr("name", "elContactQQ");
        input18.attr("value", formatParams($scope.elContactQQ));
        var input20 = $("<input>");
        input20.attr("type", "hidden");
        input20.attr("name", "elContactEmail");
        input20.attr("value", formatParams($scope.elContactEmail));
        var input21 = $("<input>");
        input21.attr("type", "hidden");
        input21.attr("name", "elPostcode");
        input21.attr("value", formatParams($scope.elPostcode));
        var input22 = $("<input>");
        input22.attr("type", "hidden");
        input22.attr("name", "elPeopleNum");
        input22.attr("value", formatParams($scope.elPeopleNum));
        var input23 = $("<input>");
        input23.attr("type", "hidden");
        input23.attr("name", "elAddress");
        input23.attr("value", formatParams($scope.elAddress));
        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input24 = $("<input>");
        input24.attr("type", "hidden");
        input24.attr("name", "orderBy");
        input24.attr("value", $scope.chartParams.orderBy);
        $("body").append(form);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.append(input8);
        form.append(input9);
        form.append(input10);
        form.append(input11);
        form.append(input12);
        form.append(input13);
        form.append(input16);
        form.append(input17);
        form.append(input18);
        form.append(input20);
        form.append(input21);
        form.append(input22);
        form.append(input23);
        form.append(input24);
        form.submit();
    };
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
    //控制复选框--不限
    $scope.limitCheckBoxAll = function(checkBoxName, limitCheckBoxName) {
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
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
        url: "/sofn-qry-web/subject/getSubjEnforceLawStatis",
        grid: [1,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
