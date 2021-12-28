var myapp = angular.module("myapp", []);
myapp.controller("qrySubjSvList", function($scope, $http, $state, $interval, $timeout) {
    var vm = this;

    $http({
        url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
        method: "post",
        data: {
            type: "QRY"
        },
        headers: {
            Accept: "*/*",
            token: window.localStorage.getItem("token")
        }
    })
        .success(function(data) {
            var root = _.groupBy(data.data, "menuValue");
            var menus = [];
            var current = root["QRY"];
            var isRoot = false;
            _.each(current, function(menu) {
                if (menu.authority == 0) {
                    return;
                }
                isRoot = !_.size(
                    _.filter(current, function(item) {
                        if (item.authority == 0) {
                            return;
                        }
                        if (menu.parentId == item.id) {
                            item.children = item.children || [];
                            if (menu.menuType == 2) {
                                item.tab = item.tab || [];
                                item.tab[item.tab.length] = menu;
                            } else {
                                item.state = "closed";
                                item.children[item.children.length] = menu;
                            }
                        }
                        item.text = item.menuName;
                        item.url = item.url != "#" ? item.url : null;
                        return menu.parentId == item.id;
                    })
                );
                menus = isRoot ? [menu] : menus;
            });

            if (menus.length) {
                menus[0].state = "open";
            }
            var formatmenu = menus.length ? menus[0].children : [];
            //每次刷新Tab页，以防权限变动
            var flag = true;
            //一级菜单
            for (
                var i = 0;
                formatmenu != null && i < formatmenu.length && flag;
                i++
            ) {
                //如果一级菜单就是包含Tab页
                if (
                    formatmenu[i].tab != null &&
                    formatmenu[i].tab != undefined &&
                    formatmenu[i].tab.length > 0
                ) {
                    window.localStorage.removeItem("menuTabObj");
                    window.localStorage.setItem(
                        "menuTabObj",
                        JSON.stringify(formatmenu[i])
                    );
                    flag = false;
                    break;
                }
                //如果二级菜单--包含Tab页，暂时只处理这两级
                for (
                    var j = 0;
                    formatmenu[i].children != null &&
                    j < formatmenu[i].children.length &&
                    flag;
                    j++
                ) {
                    if (
                        formatmenu[i].children[j].tab != null &&
                        formatmenu[i].children[j].tab != undefined &&
                        formatmenu[i].children[j].tab.length > 0
                    ) {
                        window.localStorage.removeItem("menuTabObj");
                        window.localStorage.setItem(
                            "menuTabObj",
                            JSON.stringify(formatmenu[i].children[j])
                        );
                        flag = false;
                    }
                }
            }

            var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
            a.map(function(item, i) {
                if (item.text == "检测机构主体") {
                    $(".qrySubjDtList").css("display", "inline-block");
                } else if (item.text == "执法机构主体") {
                    $(".qrySubjElList").css("display", "inline-block");
                } else if (item.text == "生产经营主体") {
                    $(".qrySubjEntList").css("display", "inline-block");
                }
            });
        })
        .error(function(data) {
            
        });
    //获取用户信息、组织信息
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
            //机构级别默认不限
            setTimeout(function() {
                if ($scope.organization.orgLevel == 2) {
                    //省级限制部级不可选
                    $("input[name='svLevel']").each(function(i, o) {
                        if ($(o).val() == 0) {
                            $(o).prop("disabled", true);
                        }
                    });
                } else if ($scope.organization.orgLevel == 3) {
                    //市级限制省,部级不可选
                    $("input[name='svLevel']").each(function(i, o) {
                        if ($(o).val() == 0) {
                            $(o).prop("disabled", true);
                        }
                        if ($(o).val() == 1) {
                            $(o).prop("disabled", true);
                        }
                    });
                } else if ($scope.organization.orgLevel == 4) {
                    //县级限制市,省,部级不可选
                    $("input[name='svLevel']").each(function(i, o) {
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
    //获取行业数据字典
    $scope.industryType = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "2", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.industryType = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //获取机构级别数据字典
    $scope.svLevel = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "1", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.svLevel = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );

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
    //监管机构主体信息
    function loadGrid() {
        $("#svGrid").dataTable({
            ordering: true, //开启排序
            order: [[6, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/subject/getSubjSuperviseList_v2",
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
                    params.svLevelId = $("select[name='svLevel']").val();
                    var statusarr = $("input[name='status']:checked");
                    var arr = new Array();
                    for (var i = 0; i < statusarr.length; i++) {
                        arr.push($(statusarr[i]).val());
                    }
                    params.status = arr.toString();
                    params.areaId = $scope.getAreaBySelect();
                    params.svName = $("input[name='svName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.token = window.localStorage.getItem("token");
                    //绑值
                    params.svAddress = formatParams($scope.svAddress);
                    params.svLeaderPhone = formatParams($scope.svLeaderPhone);
                    params.svCode = formatParams($scope.svCode);
                    params.svContact = formatParams($scope.svContact);
                    params.svContactPhone = formatParams($scope.svContactPhone);
                    params.svContactQQ = formatParams($scope.svContactQQ);
                    params.svContactEmail = formatParams($scope.svContactEmail);
                    params.svPostcode = formatParams($scope.svPostcode);

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }

                    var entityIndustry = $(
                        "input[name='entityIndustry']:checked"
                    );
                    var arr = new Array();
                    for (var i = 0; i < entityIndustry.length; i++) {
                        arr.push($(entityIndustry[i]).val());
                    }
                    params.industryId = arr.toString();
                    var svLevel = $("input[name='svLevel']:checked");
                    var svLevelArr = new Array();
                    for (var i = 0; i < svLevel.length; i++) {
                        svLevelArr.push($(svLevel[i]).val());
                    }
                    params.svLevel = svLevelArr.toString();
                    params.svLeader = $("input[name='svLeader']").val();

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
                    orderable: false
                },
                {
                    data: "svName",
                    title: "机构名称",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 25
                        ) {
                            return (
                                "<a id='svName" +
                                row.id +
                                "' onmouseover=showPopContent('svName" +
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
                    data: "industryName",
                    title: "所属行业"
                },
                {
                    data: "svLevel",
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
                    data: "svAreaId",
                    title: "所属区域",
                    render: function(data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 14
                        ) {
                            return (
                                "<a id='svAreaId" +
                                row.id +
                                "' onmouseover=showPopContent('svAreaId" +
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
                    data: "svLeader",
                    title: "机构负责人",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 10
                        ) {
                            return (
                                "<a id='svLeader" +
                                row.id +
                                "' onmouseover=showPopContent('svLeader" +
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
                    title: "注册时间"
                },
                {
                    data: "svAddress",
                    name: "svAddress",
                    title: "通讯地址",
                    visible: false,
                    width: "5%"
                },
                {
                    data: "svCode",
                    name: "svCode",
                    title: "机构代码",
                    visible: false
                },
                {
                    data: "svLeaderPhone",
                    name: "svLeaderPhone",
                    title: "负责人电话",
                    visible: false
                },
                {
                    data: "svContact",
                    name: "svContact",
                    title: "联系人",
                    visible: false
                },
                {
                    data: "svContactPhone",
                    name: "svContactPhone",
                    title: "联系人电话",
                    visible: false
                },
                {
                    data: "svContactQQ",
                    name: "svContactQQ",
                    title: "联系人QQ",
                    visible: false
                },
                {
                    data: "svContactEmail",
                    name: "svContactEmail",
                    title: "电子邮箱",
                    visible: false
                },
                {
                    data: "svPostcode",
                    name: "svPostcode",
                    title: "邮编",
                    visible: false
                },
                {
                    data: "id",
                    title: "操作",
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjSvView(\'' +
                            data +
                            "')\">查看</a>";
                        return butt;
                    },
                    orderable: false
                }
            ]
        });
    }

    //重新加载表格
    $scope.querySubjSvList = function($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.btnDisabled = false;
            });
        }, 500);

        $("#svGrid")
            .dataTable()
            .api()
            .ajax.reload();

        //二次查询
        $($event.currentTarget).text("二次查询");
    };

    //重置区域
    $scope.reloadArea = function($event) {
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $(".gui-form-group input[type='checkbox']:checked").prop(
            "checked",
            false
        );
        $("#queryForm").form("reset");

        $scope.svAddress = null;
        $scope.svLeaderPhone = null;
        $scope.svCode = null;
        $scope.svContact = null;
        $scope.svContactPhone = null;
        $scope.svContactQQ = null;
        $scope.svContactEmail = null;
        $scope.svPostcode = null;
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
        //所属行业默认不限
        $("input[name='entityIndustryLimit']:first").prop("checked", true);
        $("input[name='svLevelLimit']:first").prop("checked", true);
        $("input[name='statuslLimit']").prop("checked", true);
        //机构级别默认不限
        if ($scope.organization.orgLevel == 2) {
            //省级限制部级不可选
            $("input[name='svLevel']").each(function(i, o) {
                if ($(o).val() == 0) {
                    $(o).prop("disabled", true);
                }
            });
        } else if ($scope.organization.orgLevel == 3) {
            //市级限制省,部级不可选
            $("input[name='svLevel']").each(function(i, o) {
                if ($(o).val() == 0) {
                    $(o).prop("disabled", true);
                }
                if ($(o).val() == 1) {
                    $(o).prop("disabled", true);
                }
            });
        } else if ($scope.organization.orgLevel == 4) {
            //县级限制市,省,部级不可选
            $("input[name='svLevel']").each(function(i, o) {
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

    //跳转到监管机构主体备案查看页面
    window.subjSvView = function(data) {
        window.localStorage.setItem("subjSvId", data);
        $state.go("index.content.qry/subject/qrySubjSvView");
    };

    //导出Excel
    $scope.exportData = function() {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/subject/exportSubjSv");
        var input1 = $("<input>");
        input1.attr("type", "hidden");
        input1.attr("name", "svLevelId");
        input1.attr("value", $("select[name='svLevel']").val());
        var input2 = $("<input>");
        var statusarr = $("input[name='status']:checked");
        var arr = new Array();
        for (var i = 0; i < statusarr.length; i++) {
            arr.push($(statusarr[i]).val());
        }
        input2.attr("type", "hidden");
        input2.attr("name", "status");
        input2.attr("value", arr.toString());
        var input3 = $("<input>");
        input3.attr("type", "hidden");
        input3.attr("name", "areaId");
        input3.attr("value", $scope.getAreaBySelect());
        var input4 = $("<input>");
        input4.attr("type", "hidden");
        input4.attr("name", "svName");
        input4.attr("value", $("input[name='svName']").val());
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
        var entityIndustry = $("input[name='entityIndustry']:checked");
        var arr = new Array();
        for (var i = 0; i < entityIndustry.length; i++) {
            arr.push($(entityIndustry[i]).val());
        }
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "industryId");
        input8.attr("value", arr.toString());
        var svLevel = $("input[name='svLevel']:checked");
        var svLevelArr = new Array();
        for (var i = 0; i < svLevel.length; i++) {
            svLevelArr.push($(svLevel[i]).val());
        }
        var input9 = $("<input>");
        input9.attr("type", "hidden");
        input9.attr("name", "svLevel");
        input9.attr("value", svLevelArr.toString());
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "svLeader");
        input10.attr("value", $("input[name='svLeader']").val());
        var input11 = $("<input>");
        input11.attr("type", "hidden");
        input11.attr("name", "svAddress");
        input11.attr("value", formatParams($scope.svAddress));
        var input13 = $("<input>");
        input13.attr("type", "hidden");
        input13.attr("name", "svLeaderPhone");
        input13.attr("value", formatParams($scope.svLeaderPhone));
        var input15 = $("<input>");
        input15.attr("type", "hidden");
        input15.attr("name", "svCode");
        input15.attr("value", formatParams($scope.svCode));
        var input16 = $("<input>");
        input16.attr("type", "hidden");
        input16.attr("name", "svContact");
        input16.attr("value", formatParams($scope.svContact));
        var input17 = $("<input>");
        input17.attr("type", "hidden");
        input17.attr("name", "svContactPhone");
        input17.attr("value", formatParams($scope.svContactPhone));
        var input18 = $("<input>");
        input18.attr("type", "hidden");
        input18.attr("name", "svContactQQ");
        input18.attr("value", formatParams($scope.svContactQQ));
        var input19 = $("<input>");
        input19.attr("type", "hidden");
        input19.attr("name", "svContactEmail");
        input19.attr("value", formatParams($scope.svContactEmail));
        var input20 = $("<input>");
        input20.attr("type", "hidden");
        input20.attr("name", "svPostcode");
        input20.attr("value", formatParams($scope.svPostcode));
        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input21 = $("<input>");
        input21.attr("type", "hidden");
        input21.attr("name", "orderBy");
        input21.attr("value", $scope.chartParams.orderBy);
        $("body").append(form);
        form.append(input1);
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
        form.append(input13);
        form.append(input15);
        form.append(input16);
        form.append(input17);
        form.append(input18);
        form.append(input19);
        form.append(input20);
        form.append(input21);
        form.submit();
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
                var table = $("#svGrid").DataTable();
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
        url: "/sofn-qry-web/subject/getSupervisopnStatis",
        grid: [2, 2, 1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };

});
