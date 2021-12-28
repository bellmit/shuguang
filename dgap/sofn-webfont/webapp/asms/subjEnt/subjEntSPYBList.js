/**
 * Created by Administrator on 2018/8/20 0020.
 */
var myapp = angular.module("myapp", []);
myapp.controller("subjEntSPYBList", function($scope, $http, $state, $timeout) {
    var vm = this;
    $scope.subjEntSPYBListQueryparams = JSON.parse(
        window.sessionStorage.getItem("subjEntSPYBListQueryparams")
    );
    $scope.subjEntSPYBListQueryparams &&
    $scope.subjEntSPYBListQueryparams.token !=
        window.localStorage.getItem("token")
        ? window.sessionStorage.clear()
        : "";
    //判定页面权限、tab页、按钮
    var data = JSON.parse(window.localStorage.getItem("menus"));
    var root = _.groupBy(data, "menuValue");
    var menus = [];
    var current = root["ASMS"];
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
    var formatmenu = menus.length ? menus[0].children : jsontree(data.data);
    //每次刷新Tab页，以防权限变动
    var flag = true;
    //一级菜单
    for (var i = 0; formatmenu != null && i < formatmenu.length && flag; i++) {
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
    for (var i = 0; i < a.length; i++) {
        if (a[i].text == "生产经营主体信息") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    a.map(function(item, i) {
        if (item.text == "临时备案主体") {
            $(".subjEntTempList").css("display", "inline-block");
        } else if (item.text == "备案待审核") {
            $(".subjEntRegisterList").css("display", "inline-block");
        } else if (item.text == "备案变更待审核") {
            $(".subjEntChangeList").css("display", "inline-block");
        } else if (item.text == "备案撤销待审核") {
            $(".subjEntRevokeList").css("display", "inline-block");
        } else if (item.text == "备案注销待审核") {
            $(".subjEntCancelList").css("display", "inline-block");
        }
    });
    //认证信息字典
    $scope.authenticationType = [
        { dictName: "绿色食品", dictValue: "绿色认证" },
        { dictName: "有机食品", dictValue: "有机认证" },
        { dictName: "无公害农产品", dictValue: "无公害认证" },
        { dictName: "无", dictValue: "无" }
    ];
    //三品一标主体信息完善情况下拉菜单(2为未完善,其余为已完善)
    $scope.infoImprovedStateList = [
        { dictName: "已完善", dictValue: "0,1,3,4,5,6,7" },
        { dictName: "未完善", dictValue: "2" }
    ];

    $scope.countRegisterToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countRegisterToAudit",
            method: "post",
            data: {
                area: $scope.underArea,
                entityIndustry: $scope.allIndustryId
            }
        })
            .success(function(data) {
                $scope.registerToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.countChangeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countChangeToAudit",
            method: "post",
            data: {
                area: $scope.underArea,
                entityIndustry: $scope.allIndustryId
            }
        })
            .success(function(data) {
                $scope.changeToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.countCancelToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countCancelToAudit",
            method: "post",
            data: {
                area: $scope.underArea,
                entityIndustry: $scope.allIndustryId
            }
        })
            .success(function(data) {
                $scope.cancelToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };
    $scope.countRevokeToAudit = function() {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/countRevokeToAudit",
            method: "post",
            data: {
                area: $scope.underArea,
                entityIndustry: $scope.allIndustryId
            }
        })
            .success(function(data) {
                $scope.revokeToAuditCount = data.count;
            })
            .error(function(data) {
                
            });
    };

    if (
        window.localStorage.getItem("asmsAllSysDictAndUserInfo") &&
        window.localStorage.getItem("token") ==
            JSON.parse(
                window.localStorage.getItem("asmsAllSysDictAndUserInfo")
            )["token"]
    ) {
        //所属区域
        $scope.underArea = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["underArea"];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["allIndustryId"];
        //获取组织形式数据字典
        $scope.orgMode = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["orgMode"];
        $scope.countRegisterToAudit();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();
        loadGrid();
    } else {
        $scope.initAllSysDictAndUserInfo();
    }
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    //bootstrap表格
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
            ordering: true, //开启排序
            order: [[8, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-asms-web/subjEnt/getSubjEntList",
                headers: {
                    token: window.localStorage.getItem("token")
                },
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
                        $scope.handleDataβ(json.list[i]);
                    }
                    return json.list;
                },
                data: function(params) {
                    var subjEntSPYBListQueryparams = JSON.parse(
                        window.sessionStorage.getItem(
                            "subjEntSPYBListQueryparams"
                        )
                    );
                    params.accountResource = "SPYB";
                    params.enterpriseName = $("input[name='entName']").val();
                    var spybLicType = $("input[name='spybLicType']:checked");
                    var spybLicTypeArr = new Array();
                    for (var i = 0; i < spybLicType.length; i++) {
                        spybLicTypeArr.push($(spybLicType[i]).val());
                    }
                    params.spybLicType = spybLicTypeArr.toString();
                    var approveStatus = $(
                        "input[name='approveStatus']:checked"
                    );
                    var approveStatusArr = new Array();
                    for (var i = 0; i < approveStatus.length; i++) {
                        approveStatusArr.push($(approveStatus[i]).val());
                    }
                    params.approveStatus = approveStatusArr.toString();
                    var entityScale = $("input[name='entityScale']:checked");
                    var arr1 = new Array();
                    for (var i = 0; i < entityScale.length; i++) {
                        arr1.push($(entityScale[i]).val());
                    }
                    params.entityScale = arr1.toString();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.entName = $("input[name='entName']").val();
                    params.legalName = formatParams($scope.legalName);
                    params.legalPhone = formatParams($scope.legalPhone);
                    params.contactName = formatParams($scope.contactName);
                    params.contactPhone = formatParams($scope.contactPhone);
                    params.contactEmail = formatParams($scope.contactEmail);
                    params.faxNumber = formatParams($scope.faxNumber);
                    params.address = formatParams($scope.address);

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
                    //data : "RN",
                    title: "序号",
                    orderable: false,
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    width: "24%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 20
                        ) {
                            return (
                                "<a id='enterpriseName" +
                                row.id +
                                "' onmouseover=showPopContent('enterpriseName" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 20) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "entityScaleName",
                    title: "组织形式",
                    width: "10%",
                    render: function(data) {
                        if (data && "β" == data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width: "12%",
                    render: function(data) {
                        if (data && "β" == data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "entityTypeName",
                    title: "主体类别",
                    width: "10%",
                    render: function(data) {
                        if (data && "β" == data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "approveStatus",
                    title: "信息完善情况",
                    width: "10%",
                    render: function(data) {
                        if (data == 2) {
                            return "未完善";
                        }
                        return "已完善";
                    }
                },
                {
                    data: "spybLicType",
                    title: "认证类型",
                    width: "15%",
                    render: function(data) {
                        return data == null ? "无" : data;
                    }
                },
                {
                    data: "createTime",
                    title: "注册时间",
                    width: "10%"
                },
                {
                    data: "legalPhone",
                    name: "legalPhone",
                    title: "法人联系电话",
                    visible: false,
                },
                {
                    data: "contactName",
                    name: "contactName",
                    title: "联系人姓名",
                    visible: false,
                },
                {
                    data: "contactPhone",
                    name: "contactPhone",
                    title: "联系人电话",
                    visible: false,
                },
                {
                    data: "contactEmail",
                    name: "contactEmail",
                    title: "电子邮箱",
                    visible: false,
                },
                {
                    data: "faxNumber",
                    name: "faxNumber",
                    title: "传真号码",
                    visible: false,
                },
                {
                    data: "address",
                    name: "address",
                    title: "详细地址",
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
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntSPYBView(\'' +
                            data +
                            "'); javascript:void(0);\">查看</a>";
                        return butt;
                    }
                }
            ]
        });
    }
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
    $scope.reloadArea = function($event) {
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $("#queryForm").form("reset");
        $scope.legalName = null;
        $scope.legalPhone = null;
        $scope.contactName = null;
        $scope.contactPhone = null;
        $scope.contactEmail = null;
        $scope.faxNumber = null;
        $scope.address = null;
        $("input[name='entName']").val("");
        $("input[name='approveStatusLimit']").prop("checked", true);
        $("input[name='entityScaleLimit']").prop("checked", true);
        $("input[name='spybLicTypeLimit']").prop("checked", true);
    };

    //重新加载表格
    $scope.querySubjEntList = function($event) {

        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        var subjEntSPYBListQueryparams = {};
        subjEntSPYBListQueryparams.enterpriseName = $(
            "input[name='entName']"
        ).val();
        subjEntSPYBListQueryparams.spybLicType = $(
            "select[name='spybLicType']"
        ).val();
        subjEntSPYBListQueryparams.infoImprovedState = $(
            "select[name='infoImprovedState']"
        ).val();
        window.sessionStorage.setItem(
            "subjEntSPYBListQueryparams",
            JSON.stringify(subjEntSPYBListQueryparams)
        );
        //二次查询
        $($event.currentTarget).text("二次查询");
        $("#entGrid")
            .dataTable()
            .api()
            .ajax.reload();
    };

    //跳转到生产经营主体备案查看页面
    window.subjEntSPYBView = function(data) {
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId", data);
        $state.go("index.content.asms/subjEnt/subjEntSPYBView");
    };

    // 为页面tab切换操作绑定事件,清除查询参数缓存
    $(
        ".subjEntList,.subjEntTempList,.subjEntChangeList,.subjEntRevokeList,.subjEntRegisterList,.subjEntCancelList,.subjEntSPYBList"
    ).each(function(i, o) {
        $(o).click(function() {
            window.sessionStorage.clear();
        });
    });
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
                var table = $("#entGrid").DataTable();
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
        url: "/sofn-asms-web/subjEnt/getSubjEntSPYBStatis",
        grid: [2,2], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
    //处理β为无
    $scope.handleDataβ = function(o) {
        for (var p in o) {
            if (o[p] == "β") {
                o[p] = "无";
            }
        }
    }

});
