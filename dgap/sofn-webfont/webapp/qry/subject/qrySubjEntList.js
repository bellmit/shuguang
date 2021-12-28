var myapp = angular.module("myapp", []);
myapp.controller("qrySubjEntList", function ($scope, $http, $state, $timeout) {
    var vm = this;
    //判定页面权限、tab页、按钮
    var a = JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function (item, i) {
        if (item.text == "检测机构主体") {
            $(".qrySubjDtList").css("display", "inline-block");
        } else if (item.text == "执法机构主体") {
            $(".qrySubjElList").css("display", "inline-block");
        } else if (item.text == "监管机构主体") {
            $(".qrySubjSvList").css("display", "inline-block");
        }
    });
    $scope.authenticationType = [
        {dictName: "绿色食品", dictValue: "绿色认证"},
        {dictName: "有机食品", dictValue: "有机认证"},
        {dictName: "无公害农产品", dictValue: "无公害认证"},
        {dictName: "无", dictValue: "无"}
    ];

    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "get"
    })
        .success(function (data) {
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
            //初始化所属行业、组织形式、主体类别
            $scope.initIndustryRole();
            //所属行业默认不限、组织形式不限、主体类别不限
        })
        .error(function (data) {
            
        });

    $scope.activationStatus = [
        {
            dictName: "激活待审核",
            dictValue: "0"
        },
        {
            dictName: "激活审核通过",
            dictValue: "1"
        }
    ]

    //主体状态
    $scope.approveStatus = [
        {
            dictName: "注册待审核",
            dictValue: "0"
        },
        {
            dictName: "审核通过",
            dictValue: "1"
        },
        {
            dictName: "注册审核不通过",
            dictValue: "2"
        },
        {
            dictName: "变更待审核",
            dictValue: "3"
        },
        {
            dictName: "注销待审核",
            dictValue: "4,5"
        },
        {
            dictName: "已注销",
            dictValue: "6,7"
        }
    ];
    //重置区域
    $scope.reloadArea = function ($event) {
        $("#queryForm").form("reset");
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $("#cardTypeSelected").val("");
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
        $("input[name='entityIndustryLimit']:first").prop("checked", true);
        $("input[name='entityScaleLimit']:first").prop("checked", true);
        $("input[name='entityTypeLimit']:first").prop("checked", true);
        $("input[name='entityPropertyLimit']:first").prop("checked", true);
        $("input[name='approveStatusLimit']:first").prop("checked", true);
        $("input[name='spybLicTypeLimit']:first").prop("checked", true);
        $("input[name='activationStatusLimit']:first").prop("checked", true);
    };

    $scope.getAreaBySelect = function () {
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

    //获取当前登录用户行业角色
    $scope.initIndustryRole = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url:
                "/sofn-qry-web/supervisionInspection/getUserIndustryRoleByToken",
            method: "post"
        })
            .success(function (data) {
                $scope.industryRole = data.industryRole;
                $scope.initIndustryType();
                $scope.initEntityProperty();
            })
            .error(function (data) {
                
            });
    };

    //获取行业数据字典
    $scope.initIndustryType = function () {
        $scope.industryType = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "2", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function (res) {
                    $scope.industryType = JSON.parse(res.data.data);
                    $scope.initSubjType();
                },
                function (res) {
                    
                }
            );
    };
    //获取主体属性数据字典
    $scope.initEntityProperty = function () {
        $scope.entityProperty = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "8", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function (res) {
                    $scope.entityProperty = JSON.parse(res.data.data);
                },
                function (res) {
                    
                }
            );
    };

    $scope.disableIndustryCheck = function () {
        $("input[name='entityIndustry']").attr("disabled", "disabled");
    };

    //获取主体类别数据字典
    $scope.initSubjType = function () {
        $scope.subjType = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "7", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function (res) {
                    $scope.subjType = JSON.parse(res.data.data);
                    $scope.initOrgMode();
                },
                function (res) {
                    
                }
            );
    };

    function formatParams(str) {
        return str == undefined ? "" : str;
    }

    //获取组织形式数据字典
    $scope.initOrgMode = function () {
        $scope.orgMode = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "6", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function (res) {
                    $scope.orgMode = JSON.parse(res.data.data);
                    $scope.loadGrid();
                },
                function (res) {
                    
                }
            );
    };

    //bootstrap表格
    //生产经营主体信息
    $scope.loadGrid = function () {
        $("#entGrid").dataTable({
            ordering: true, //开启排序
            order: [[10, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/subject/getSubjEntList_v2",
                dataSrc: function (json) {
                    if (json.httpCode != 200) {
                        swal("系统提示", "请求服务器出错!", "error");
                        return false;
                    }
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
                            ).toISOString().slice(0, 10);
                        }
                    }
                    $scope.$apply(function () {
                        $scope.tableTotal = json.page.recordsTotal;
                    });
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    // params.entityIndustry = $("select[name='entityIndustry']").val();
                    var entityIndustry = $("input[name='entityIndustry']:checked");
                    var arr = new Array();
                    for (var i = 0; i < entityIndustry.length; i++) {
                        arr.push($(entityIndustry[i]).val());
                    }
                    params.entityIndustry = arr.toString();
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
                    params.approveStatus = approveStatusArr.toString();
                    var spybLicType = $("input[name='spybLicType']:checked");
                    var spybLicTypeArr = new Array();
                    for (var i = 0; i < spybLicType.length; i++) {
                        spybLicTypeArr.push($(spybLicType[i]).val());
                    }

                    //激活状态
                    var reactiveStatus = $("input[name='reactiveStatus']:checked");
                    var reactiveStatusArr = new Array();
                    for (var i = 0; i < reactiveStatus.length; i++) {
                        reactiveStatusArr.push($(reactiveStatus[i]).val());
                    }
                    params.reactiveStatus = reactiveStatusArr.toString();

                    params.spybLicType = spybLicTypeArr.toString();
                    params.userIdcode = $("input[name='userIdcode']").val();
                    //主体是否完善信息标识,传任何值均表示已完善(通过某字段是否是β),需查询全部,则不传
                    params.completeInfoSubj = "1";

                    //custom condition
                    params.cardType = formatParams($("#cardTypeSelected").val());
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

                    //排序传参
                    if (params.order[0]) {
                        var orderIndex = params.order[0]["column"];
                        var orderKey = params.columns[orderIndex]["data"];
                        if (orderKey != "function") {
                            params.orderBy =
                                orderKey + " " + params.order[0]["dir"];
                        }
                    }

                    //删除多余请求参数
                    for(var key in params){
                        if(key.indexOf("columns")==0||key.indexOf("order")==0||key.indexOf("search")==0){ //以columns开头的参数删除
                            delete params[key];
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
                type: "get"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    orderable: false,
                    data: function (data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "enterpriseName",
                    title: "主体名称",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 25
                        ) {
                            return "<a id='enterpriseName" + row.id + "' onmouseover=showPopContent('enterpriseName" +
                                row.id + "','" + data + "')>" + data.substring(0, 25) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                    width: "15%"
                },
                {
                    data: "entityScaleName",
                    title: "组织形式",
                    width: "8%",
                    render: function (data, type, row) {
                        return !data || data == "β" ? "无" : data;
                    }
                },
                {
                    data: "entityPropertyName",
                    title: "主体属性",
                    width: "8%",
                    render: function (data, type, row) {
                        return !data || data == "β" ? "无" : data;
                    }
                },
                {
                    data: "spybLicType",
                    title: "认证类型",
                    width: "7%",
                    render: function (data, type, row) {
                        return data == null ? "无" : data;
                    }
                },
                {
                    data: "entityIndustryName",
                    title: "所属行业",
                    width: "8%",
                    /*bug 2017/5/11 种植、畜牧、渔业用“/”隔开 start*/
                    render: function (data, type, row) {
                        if (!data || data == "β") {
                            return "无";
                        }
                        return data.replace(/,/g, "/");
                    }
                    /*bug 2017/5/11 种植、畜牧、渔业用“/”隔开 end*/
                },
                {
                    data: "entityTypeName",
                    title: "主体类别",
                    width: "8%",
                    render: function (data, type, row) {
                        return !data || data == "β" ? "无" : data;
                    }
                },
                {
                    data: "legalName",
                    title: "法人姓名",
                    width: "8%",
                    render: function (data, type, row) {
                        return !data || data == "β" ? "无" : data;
                    }
                },
                {
                    data: "area",
                    title: "所属区域",
                    render: function (data, type, row) {
                        if (!data || data == "β") {
                            return "无";
                        }
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 13
                        ) {
                            return "<a id='area" + row.id + "' onmouseover=showPopContent('area" +
                                row.id + "','" + data + "')>" + data.substring(0, 12) + "...</a>";
                        } else {
                            return data;
                        }
                    },
                    width: "12%"
                },
                {
                    data: "badRecord",
                    title: "不良记录（次）",
                    width: "9%",
                    orderable: false,
                },
                {
                    data: "registerTime",
                    title: "注册时间",
                    width: "8%",
                    render: function (data, type, row) {
                        return new Date(parseInt(data) + 28800000).toISOString().slice(0, 10);
                    }
                },
                {
                    data: "approveStatus",
                    title: "主体状态",
                    width: "9%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.approveStatus.length; i++) {
                            if ($scope.approveStatus[i].dictValue.indexOf(data) !== -1) {
                                return $scope.approveStatus[i].dictName;
                            }
                        }
                    }
                },
                {
                    data: "reactiveStatus",
                    title: "激活状态",
                    render: function (data, type, row) {
                        if (!data || data == "2") {
                            return "正常";
                        }
                        for (var i = 0; i < $scope.activationStatus.length; i++) {
                            if ($scope.activationStatus[i].dictValue.indexOf(data) !== -1) {
                                return $scope.activationStatus[i].dictName;
                            }
                        }
                    }
                },
                {
                    data: "cardType",
                    name: "cardType",
                    title: "证件类型",
                    visible: false,
                },
                {
                    data: "creditCode",
                    name: "creditCode",
                    title: "企业注册号",
                    visible: false,
                },
                {
                    data: "organizationCode",
                    name: "organizationCode",
                    title: "组织机构代码证",
                    visible: false,
                },
                {
                    data: "address",
                    name: "address",
                    title: "详细地址",
                    visible: false,
                },
                {
                    data: "legalIdnumber",
                    name: "legalIdnumber",
                    title: "法人身份证号",
                    visible: false,
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
                    data: "idcode",
                    name: "idcode",
                    title: "联系人身份证号",
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
                    data: "id",
                    title: "操作",
                    orderable: false,
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        var butt;
                        butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntInfoView(\'' +
                            data +
                            "' , '" +
                            row.entityIdcode +
                            "')\">查看</a>";
                        return butt;
                    }
                }
            ]
        });
    };

    //重新加载表格
    $scope.querySubjEntList = function ($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function () {
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#entGrid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };

    //跳转到生产经营主体备案查看页面
    window.subjEntInfoView = function (id, entityIdcode) {
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId", id);
        window.localStorage.removeItem("subjEntEntityIdcode");
        window.localStorage.setItem("subjEntEntityIdcode", entityIdcode);
        $state.go("index.content.qry/subject/qrySubjEntInfoView");
    };

    //导出Excel
    $scope.exportData = function () {
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/subject/exportSubjEntSPYB");
        var entityIndustry = $("input[name='entityIndustry']:checked");
        var arr = new Array();
        for (var i = 0; i < entityIndustry.length; i++) {
            arr.push($(entityIndustry[i]).val());
        }
        var input1 = $("<input>");
        input1.attr("type", "hidden");
        input1.attr("name", "entityIndustry");
        input1.attr("value", arr.toString());
        var entityScale = $("input[name='entityScale']:checked");
        var arr1 = new Array();
        for (var i = 0; i < entityScale.length; i++) {
            arr1.push($(entityScale[i]).val());
        }
        var input2 = $("<input>");
        input2.attr("type", "hidden");
        input2.attr("name", "entityScale");
        input2.attr("value", arr1.toString());
        var entityType = $("input[name='entityType']:checked");
        var arr2 = new Array();
        for (var i = 0; i < entityType.length; i++) {
            arr2.push($(entityType[i]).val());
        }
        var input3 = $("<input>");
        input3.attr("type", "hidden");
        input3.attr("name", "entityType");
        input3.attr("value", arr2.toString());
        var input4 = $("<input>");
        input4.attr("type", "hidden");
        input4.attr("name", "badRecord");
        input4.attr("value", $("select[name='badRecord']").val());
        var input5 = $("<input>");
        input5.attr("type", "hidden");
        input5.attr("name", "area");
        input5.attr("value", $scope.getAreaBySelect());
        var input6 = $("<input>");
        input6.attr("type", "hidden");
        input6.attr("name", "enterpriseName");
        input6.attr("value", $("input[name='entName']").val());
        var input7 = $("<input>");
        input7.attr("type", "hidden");
        input7.attr("name", "token");
        input7.attr("value", window.localStorage.getItem("token"));
        var entityProperty = $("input[name='entityProperty']:checked");
        var entityPropertyArr = new Array();
        for (var i = 0; i < entityProperty.length; i++) {
            entityPropertyArr.push($(entityProperty[i]).val());
        }
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "entityProperty");
        input8.attr("value", entityPropertyArr.toString());
        var input9 = $("<input>");
        input9.attr("type", "hidden");
        input9.attr("name", "dateBegin");
        input9.attr("value", $("input[name='dateBegin']").val());
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "dateEnd");
        input10.attr("value", $("input[name='dateEnd']").val());
        var approveStatus = $("input[name='approveStatus']:checked");
        var approveStatusArr = new Array();
        for (var i = 0; i < approveStatus.length; i++) {
            approveStatusArr.push($(approveStatus[i]).val());
        }
        var input11 = $("<input>");
        input11.attr("type", "hidden");
        input11.attr("name", "approveStatus");
        input11.attr("value", approveStatusArr.toString());
        var input12 = $("<input>");
        input12.attr("type", "hidden");
        input12.attr("name", "legalName");
        input12.attr("value", $("input[name='legalName']").val());
        var spybLicType = $("input[name='spybLicType']:checked");
        var spybLicTypeArr = new Array();
        for (var i = 0; i < spybLicType.length; i++) {
            spybLicTypeArr.push($(spybLicType[i]).val());
        }
        var input13 = $("<input>");
        input13.attr("type", "hidden");
        input13.attr("name", "spybLicType");
        input13.attr("value", spybLicTypeArr.toString());
        var input14 = $("<input>");
        input14.attr("type", "hidden");
        input14.attr("name", "userIdcode");
        input14.attr("value", $("input[name='userIdcode']").val());
        var input15 = $("<input>");
        input15.attr("type", "hidden");
        input15.attr("name", "cardType");
        input15.attr("value", formatParams($("#cardTypeSelected").val()));
        var input16 = $("<input>");
        input16.attr("type", "hidden");
        input16.attr("name", "creditCode");
        input16.attr("value", formatParams($scope.creditCode));
        var input17 = $("<input>");
        input17.attr("type", "hidden");
        input17.attr("name", "busOperationStart");
        input17.attr("value", $("input[name='businessOperationStart']").val());
        var input18 = $("<input>");
        input18.attr("type", "hidden");
        input18.attr("name", "busOperationEnd");
        input18.attr("value", $("input[name='businessOperationEnd']").val());
        var input19 = $("<input>");
        input19.attr("type", "hidden");
        input19.attr("name", "organizationCode");
        input19.attr("value", formatParams($scope.organizationCode));
        var input20 = $("<input>");
        input20.attr("type", "hidden");
        input20.attr("name", "address");
        input20.attr("value", formatParams($scope.address));
        var input21 = $("<input>");
        input21.attr("type", "hidden");
        input21.attr("name", "legalIdnumber");
        input21.attr("value", formatParams($scope.legalIdnumber));
        var input22 = $("<input>");
        input22.attr("type", "hidden");
        input22.attr("name", "legalPhone");
        input22.attr("value", formatParams($scope.legalPhone));
        var input23 = $("<input>");
        input23.attr("type", "hidden");
        input23.attr("name", "contactName");
        input23.attr("value", formatParams($scope.contactName));
        var input24 = $("<input>");
        input24.attr("type", "hidden");
        input24.attr("name", "idcode");
        input24.attr("value", formatParams($scope.idcode));
        var input25 = $("<input>");
        input25.attr("type", "hidden");
        input25.attr("name", "contactEmail");
        input25.attr("value", formatParams($scope.contactEmail));
        var input26 = $("<input>");
        input26.attr("type", "hidden");
        input26.attr("name", "contactPhone");
        input26.attr("value", formatParams($scope.contactPhone));
        var input27 = $("<input>");
        input27.attr("type", "hidden");
        input27.attr("name", "faxNumber");
        input27.attr("value", formatParams($scope.faxNumber));
        //主体是否完善信息标识,传任何值均表示已完善(通过某字段是否是β),需查询全部,则不传
        var input28 = $("<input>");
        input28.attr("type", "hidden");
        input28.attr("name", "completeInfoSubj");
        input28.attr("value", "1");
        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input29 = $("<input>");
        input29.attr("type", "hidden");
        input29.attr("name", "orderBy");
        input29.attr("value", $scope.chartParams.orderBy);
        //激活状态
        var reactiveStatus = $("input[name='reactiveStatus']:checked");
        var reactiveStatusArr = new Array();
        for (var i = 0; i < reactiveStatus.length; i++) {
            reactiveStatusArr.push($(reactiveStatus[i]).val());
        }
        var input30 = $("<input>");
        input30.attr("type", "hidden");
        input30.attr("name", "reactiveStatus");
        input30.attr("value", reactiveStatusArr.toString());
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
        form.append(input12);
        form.append(input13);
        form.append(input14);
        form.append(input15);
        form.append(input16);
        form.append(input17);
        form.append(input18);
        form.append(input19);
        form.append(input20);
        form.append(input21);
        form.append(input22);
        form.append(input23);
        form.append(input24);
        form.append(input25);
        form.append(input26);
        form.append(input27);
        form.append(input28);
        form.append(input29);
        form.append(input30);
        form.submit();
    };

    //控制复选框
    $scope.limitCheckBox = function (checkBoxName, limitCheckBoxName) {
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
            _limChec.each(function (i, o) {
                $(o).prop("checked", false);
            });
        }
    };
    //控制复选框--不限
    $scope.limitCheckBoxAll = function (checkBoxName, limitCheckBoxName) {
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
    window.showPopContent = function (id, content) {
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

    $scope.confirmCondition = function () {
        var modal = $("#modalCondition");
        modal
            .find(".gui-list li")
            .not(".disabled")
            .map(function () {
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
        .on("change", ".condition-checked-all", function () {
            var checked = $(this).is(":checked");
            $("#modalCondition")
                .find("li")
                .not(".disabled")
                .find(":checkbox")
                .prop("checked", checked);
        })
        .on("change", ".gui-list :checkbox", function () {
            var unchecked = $(this)
                .closest(".gui-list")
                .find(":checkbox")
                .filter(function () {
                    return !$(this).is(":checked");
                });
            $(".condition-checked-all").prop("checked", !!!unchecked.length);
        })
        .on("shown.bs.modal", function () {
            $(".gui-form-group .condition-custom").each(function () {
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
    $scope.toggleView = function (isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-qry-web/subject/getSubjEntList_v2Statis",
        grid: [3, 3, 3, 3, 3, 3, 1], //水平宽度比例
        //业务参数
        data: function () {
            return $scope.chartParams;
        }
    };
});
