var myapp = angular.module("myapp", []);
myapp.controller("subjEntList", function($scope, $http, $state) {
    //获取用户数据
    $http
        .post("/sofn-ales-web/subjSupervise/findCurrentUserByToken", "", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                if (res.data.httpCode == "200") {
                    $scope.user = res.data.user;
                    $scope.organization = $scope.user.organization;
                    //定义一个管辖区域，通过机构级别+区域来判断
                    $scope.underArea = $scope.organization.regionId.substr(
                        0,
                        2 * ($scope.organization.orgLevel - 1)
                    );
                    //初始化区域下拉框初始选项
                    $scope.reloadArea();
                    loadGrid();
                } else {
                    jBox.tip(res.msg, "info");
                }
            },
            function(res) {
                
            }
        );
    //重置区域
    $scope.reloadArea = function() {
        $("#queryForm").form("reset");
        $("select[name='entityScale']")
            .find("option:first")
            .prop("selected", true);
        $("select[name='entityIndustry']")
            .find("option:first")
            .prop("selected", true);
        $("select[name='entityType']")
            .find("option:first")
            .prop("selected", true);
        $("select[name='badRecord']")
            .find("option:first")
            .prop("selected", true);
        $("select[name='spybLicType']")
            .find("option:first")
            .prop("selected", true);
        $("input[name='entName']").val("");
        $("input[name='userIdcode']").val("");
        $scope.cardType = null;
        $scope.creditCode = null;
        $scope.organizationCode = null;
        $scope.address = null;
        $scope.legalIdnumber = null;
        $scope.legalPhone = null;
        $scope.contactName = null;
        $scope.idcode = null;
        $scope.contactEmail = null;
        $scope.contactPhone = null;
        $scope.faxNumber = null;
        //默认只显示正常状态
        $("select[name='entStatu']").val("1");
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
    //获取行业数据字典
    $scope.industryType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-ales-web/sysDict/getSysDictByCode",
        data: 2,
        method: "post"
    }).success(function(data) {
        $scope.industryType = JSON.parse(data.data);
    });
    //获取主体属性数据字典
    $scope.entityProperty = [];
    $http
        .post("/sofn-ales-web/sysDict/getSysDictByCode", "8", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.entityProperty = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //组织形式数据字典
    $scope.orgMode = [];
    $http
        .post("/sofn-ales-web/sysDict/getSysDictByCode", "6", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.orgMode = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );

    //主体类别数据字典
    $scope.subjType = [];
    $http
        .post("/sofn-ales-web/sysDict/getSysDictByCode", "7", {
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
        .then(
            function(res) {
                $scope.subjType = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //认证类型数据字典
    $scope.authenticationType = [
        { dictName: "绿色食品", dictValue: "绿色认证" },
        { dictName: "有机食品", dictValue: "有机认证" },
        { dictName: "无公害农产品", dictValue: "无公害认证" },
        { dictName: "无", dictValue: "无" }
    ];

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
    //生产经营主体信息
    function loadGrid() {
        $("#entGrid").dataTable({
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
                    var entityIndustry = $(
                        "input[name='entityIndustry']:checked"
                    );
                    var arr = new Array();
                    for (var i = 0; i < entityIndustry.length; i++) {
                        arr.push($(entityIndustry[i]).val());
                    }
                    params.entityIndustry =
                        arr.toString() || $scope.allIndustryId;
                    var entityScale = $("input[name='entityScale']:checked");
                    var arr1 = new Array();
                    for (var i = 0; i < entityScale.length; i++) {
                        arr1.push($(entityScale[i]).val());
                    }
                    params.entityScale = arr1.toString();
                    // params.entityScale = $("select[name='entityScale']").val();
                    var entityType = $("input[name='entityType']:checked");
                    var arr2 = new Array();
                    for (var i = 0; i < entityType.length; i++) {
                        arr2.push($(entityType[i]).val());
                    }
                    params.entityType = arr2.toString();
                    // params.entityType = $("select[name='entityType']").val();
                    params.badRecord = $("select[name='badRecord']").val();
                    params.area = $scope.getAreaBySelect();
                    params.enterpriseName = $("input[name='entName']").val();
                    var entityProperty = $(
                        "input[name='entityProperty']:checked"
                    );
                    var entityPropertyArr = new Array();
                    for (var i = 0; i < entityProperty.length; i++) {
                        entityPropertyArr.push($(entityProperty[i]).val());
                    }
                    params.entityProperty = entityPropertyArr.toString();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.legalName = $("input[name='legalName']").val();
                    var approveStatus = $(
                        "input[name='approveStatus']:checked"
                    );
                    var approveStatusArr = new Array();
                    for (var i = 0; i < approveStatus.length; i++) {
                        approveStatusArr.push($(approveStatus[i]).val());
                    }
                    params.approveStatus =
                        approveStatusArr.toString() || "1,6,7";
                    var spybLicType = $("input[name='spybLicType']:checked");
                    var spybLicTypeArr = new Array();
                    for (var i = 0; i < spybLicType.length; i++) {
                        spybLicTypeArr.push($(spybLicType[i]).val());
                    }
                    params.spybLicType = spybLicTypeArr.toString();
                    params.userIdcode = $("input[name='userIdcode']").val();

                    //custom condition
                    params.cardType = formatParams($scope.cardType);
                    params.creditCode = formatParams($scope.creditCode);
                    params.busOperationStart = $(
                        "input[name='businessOperationStart']"
                    ).val();
                    params.busOperationEnd = $(
                        "input[name='businessOperationEnd']"
                    ).val();
                    params.organizationCode = formatParams(
                        $scope.organizationCode
                    );
                    params.address = formatParams($scope.address);
                    params.legalIdnumber = formatParams($scope.legalIdnumber);
                    params.legalPhone = formatParams($scope.legalPhone);
                    params.contactName = formatParams($scope.contactName);
                    params.idcode = formatParams($scope.idcode);
                    params.contactEmail = formatParams($scope.contactEmail);
                    params.contactPhone = formatParams($scope.contactPhone);
                    params.faxNumber = formatParams($scope.faxNumber);

                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 16
                        ) {
                            return (
                                "<a id='enterpriseName" +
                                row.id +
                                "' onmouseover=showPopContent('enterpriseName" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 16) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    },
                    width: "18%"
                },
                {
                    data: "entityScaleName",
                    title: "组织形式",
                    width: "10%"
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width: "11%"
                },
                {
                    data: "entityTypeName",
                    title: "主体类别",
                    width: "8%"
                },
                {
                    data: "spybLicType",
                    title: "认证类型",
                    width: "9%",
                    render: function(data) {
                        return data == null ? "无" : data;
                    }
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function(data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 11
                        ) {
                            return (
                                "<a id='area" +
                                row.id +
                                "' onmouseover=showPopContent('area" +
                                row.id +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 11) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    },
                    width: "12%"
                },
                {
                    data: "badRecord",
                    title: "不良记录（次）",
                    width: "9%"
                },
                {
                    data: "createTime",
                    title: "注册时间",
                    width: "7%"
                },
                {
                    data: "entityStatus",
                    title: "主体状态",
                    render: function(data, type, row) {
                        if (row.approveStatus == "1") {
                            //正常
                            return "正常";
                        } else if (row.approveStatus == "6") {
                            //已撤销
                            return "已注销";
                        } else if (row.approveStatus == "7") {
                            //已注销
                            return "已注销";
                        } else {
                            return "";
                        }
                    },
                    width: "6%"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntView(\'' +
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

    //重新加载表格
    $scope.querySubjEntList = function() {
        $("#entGrid")
            .dataTable()
            .api()
            .ajax.reload();
    };

    //跳转到生产经营主体备案查看页面
    window.subjEntView = function(data) {
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId", data);
        $state.go("index.content.ales/subjectInfo/produceSubject/subjEntView");
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
});
