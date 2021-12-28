var myapp = angular.module("myapp", []);
myapp.controller("subjEntList", function($scope, $http, $state, $timeout) {
    var vm = this;
    $scope.subjEntListQueryparams = JSON.parse(
        window.sessionStorage.getItem("subjEntListQueryparams")
    );
    $scope.subjEntListQueryparams &&
    $scope.subjEntListQueryparams.token != window.localStorage.getItem("token")
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
        }else if(item.text=="注销主体激活申请待审核"){
            $(".subjEntActivationList").css("display","inline-block");
        }
    });
    $scope.authenticationType = [
        { dictName: "绿色食品", dictValue: "绿色认证" },
        { dictName: "有机食品", dictValue: "有机认证" },
        { dictName: "无公害农产品", dictValue: "无公害认证" },
        { dictName: "无", dictValue: "无" }
    ];

    //获取用户数据
    $scope.getUser = function() {
        $http
            .post("/sofn-asms-web/subjSupervise/findCurrentUserByToken", "", {
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
                        if ($scope.organization.orgLevel != 4) {
                            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
                        }else {
                            $scope.underArea = $scope.organization.regionId;
                        }
                        //初始化区域下拉框初始选项
                        $scope.reloadArea();
                    } else {
                        jBox.tip(res.msg, "info");
                    }
                },
                function(res) {
                    
                }
            );
    }
    //重置区域
    $scope.reloadArea = function($event) {
        $("#queryForm").form("reset");
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
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

    //获取当前登录用户行业角色
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-asms-web/subjSupervise/getIndustryRoleByToken",
        method: "post"
    })
        .success(function(data) {
            $scope.industryRole = data.industryRole;
        })
        .error(function(data) {
            
        });

    //获取所有数据字典及用户信息
    $scope.initAllSysDictAndUserInfo = function() {
        $scope.getUser();
        $http
            .post("/sofn-asms-web/sysDict/getAllSysDict", "", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function(res) {
                    $scope.iswrite = getAuthorityByMenuName("生产经营主体信息");
                    window.localStorage.setItem(
                        "asmsAllSysDictAndUserInfo",
                        JSON.stringify(res.data.data)
                    );
                    //获取用户信息
                    $scope.user = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["user"];
                    $scope.organization = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["organization"];
                    //获取主体类别数据字典
                    $scope.subjType = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["subjType"];
                    //获取组织形式数据字典
                    $scope.orgMode = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["orgMode"];
                    //获取行业数据字典
                    $scope.industryType = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["AllIndustryType"];
                    //所属区域
                    $scope.underArea = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["underArea"];
                    //全部拥有的行业
                    $scope.allIndustryId = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["allIndustryId"];
                    //主体属性
                    $scope.entityProperty = JSON.parse(
                        window.localStorage.getItem("asmsAllSysDictAndUserInfo")
                    )["subjProperty"];
                    $scope.iswrite = getAuthorityByMenuName("生产经营主体信息");
                    $scope.countRegisterToAudit();
                    $scope.countChangeToAudit();
                    $scope.countCancelToAudit();
                    $scope.countRevokeToAudit();
                    loadGrid();
                    //参数回显,延时解决参数回显bug
                    setTimeout(function() {
                        if ($scope.subjEntListQueryparams) {
                            $("input[name='entName']").val(
                                $scope.subjEntListQueryparams.enterpriseName
                            );
                            $("input[name='userIdcode']").val(
                                $scope.subjEntListQueryparams.userIdcode
                            );
                            $("select[name='entStatu']").val(
                                $scope.subjEntListQueryparams.approveStatus
                            );
                            $("select[name='badRecord']").val(
                                $scope.subjEntListQueryparams.badRecord
                            );
                            $("select[name='entStatu']").val(
                                $scope.subjEntListQueryparams.approveStatus
                            );
                            $.fn.mycity(
                                "sheng",
                                "shi",
                                "xian",
                                $scope.subjEntListQueryparams.area
                            );
                            $("select[name='entityIndustry']:last").val(
                                $scope.subjEntListQueryparams.entityIndustry
                            );
                            $("select[name='entityScale']").val(
                                $scope.subjEntListQueryparams.entityScale
                            );
                            $("select[name='entityType']").val(
                                $scope.subjEntListQueryparams.entityType
                            );
                            $("select[name='spybLicType']").val(
                                $scope.subjEntListQueryparams.spybLicType
                            );
                            $scope.entityScale =
                                $scope.subjEntListQueryparams.entityScale;
                            $scope.entityType =
                                $scope.subjEntListQueryparams.entityType;
                        }
                    }, 500);
                },
                function(res) {
                    
                }
            );
    };

    //获取主体属性数据字典
    $scope.initEntityProperty = function() {
        $scope.entityProperty = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "8", {
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
    };

    //备案待审核数量
    $scope.registerToAuditCount = 0;
    //变更待审核数量
    $scope.changeToAuditCount = 0;
    //注销待审核数量
    $scope.cancelToAuditCount = 0;
    //撤销待审核数量
    $scope.revokeToAuditCount = 0;
    //注销主体激活申请待审核数量
    $scope.activationToAuditCount = 0;

    $scope.countActivationToAudit=function(){
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnt/countActivationToAudit",
            method:"post",
            data:{area:$scope.underArea,entityIndustry:$scope.allIndustryId}
        }).success(function(data){
            $scope.activationToAuditCount=data.count;
        }).error(function(data) {
             
        });
    }

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
        //获取用户信息
        $scope.user = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["user"];
        $scope.organization = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["organization"];
        //获取主体类别数据字典
        $scope.subjType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["subjType"];
        //获取组织形式数据字典
        $scope.orgMode = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["orgMode"];
        //获取行业数据字典
        $scope.industryType = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["AllIndustryType"];
        //所属区域
        $scope.underArea = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["underArea"];
        //全部拥有的行业
        $scope.allIndustryId = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["allIndustryId"];
        //主体属性
        $scope.entityProperty = JSON.parse(
            window.localStorage.getItem("asmsAllSysDictAndUserInfo")
        )["subjProperty"];
        $scope.iswrite = getAuthorityByMenuName("生产经营主体信息");
        $scope.reloadArea();
        $scope.countRegisterToAudit();
        $scope.countChangeToAudit();
        $scope.countCancelToAudit();
        $scope.countRevokeToAudit();

        $scope.countActivationToAudit();

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
                        $scope.tableTotal = json.recordsTotal;
                    });
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].registerTime != null &&
                            json.list[i].registerTime != "" &&
                            json.list[i].registerTime != undefined
                        ) {
                            json.list[i].registerTime = new Date(
                                parseInt(json.list[i].registerTime) + 28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                    }
                    return json.list;
                },
                data: function(params) {
                    var subjEntListQueryparams = JSON.parse(
                        window.sessionStorage.getItem("subjEntListQueryparams")
                    );
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
                        $timeout(function() {
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
                    width: "9%",
                    orderable: false
                },
                {
                    data: "registerTime",
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
                    data: "cardType",
                    name: "cardType",
                    title: "证件类型",
                    visible: false
                },
                {
                    data: "creditCode",
                    name: "creditCode",
                    title: "企业注册号",
                    visible: false
                },
                {
                    data: "organizationCode",
                    name: "organizationCode",
                    title: "组织机构代码证",
                    visible: false
                },
                {
                    data: "address",
                    name: "address",
                    title: "详细地址",
                    visible: false
                },
                {
                    data: "legalIdnumber",
                    name: "legalIdnumber",
                    title: "法人身份证号",
                    visible: false
                },
                {
                    data: "legalPhone",
                    name: "legalPhone",
                    title: "法人联系电话",
                    visible: false
                },
                {
                    data: "contactName",
                    name: "contactName",
                    title: "联系人姓名",
                    visible: false
                },
                {
                    data: "idcode",
                    name: "idcode",
                    title: "联系人身份证号",
                    visible: false
                },
                {
                    data: "contactPhone",
                    name: "contactPhone",
                    title: "联系人电话",
                    visible: false
                },
                {
                    data: "contactEmail",
                    name: "contactEmail",
                    title: "电子邮箱",
                    visible: false
                },
                {
                    data: "faxNumber",
                    name: "faxNumber",
                    title: "传真号码",
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
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="subjEntView(\'' +
                            data +
                            "'); javascript:void(0);\">查看</a>";
                        //仅主体为正常状态,未注销,撤销,该操作者具有读写权限,该机构为县级监管机构才具有注销权限
                        if (
                            row.approveStatus == "1" &&
                            row.delFlag == "N" &&
                            $scope.iswrite == 2 &&
                            $scope.organization.orgLevel == 4
                        ) {
                            butt +=
                                '<a style="cursor: pointer"  class="Check-report color" onclick="subjEntRevokeApply(\'' +
                                data +
                                "','" +
                                row.approveStatus +
                                "'); javascript:void(0);\">注销</a>"; //转义传值
                        }
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
    $scope.querySubjEntList = function($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function() {
            $scope.$apply(($scope.btnDisabled = false));
        }, 500);

        $("#entGrid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };

    //跳转到生产经营主体备案查看页面
    window.subjEntView = function(data) {
        window.localStorage.removeItem("subjEntId");
        window.localStorage.setItem("subjEntId", data);
        $state.go("index.content.asms/subjEnt/subjEntView");
    };
    //跳转到生产经营主体撤销申请页面
    window.subjEntRevokeApply = function(subjEntId, status) {
        if ($scope.iswrite != "2") {
            jBox.tip("对不起！您没有此操作权限", "info");
            return false;
        }
        //调用接口,判断该主体是否满足可注销条件
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjEnt/findSubjEntStateInfoForRevokeApply",
            method: "post",
            params: { subjEntId: subjEntId }
        })
            .success(function(data) {
                if (data.httpCode == "200") {
                    if (data.data.canRevokeApply == true) {
                        window.localStorage.removeItem("subjEntId");
                        window.localStorage.setItem("subjEntId", subjEntId);
                        $state.go(
                            "index.content.asms/subjEnt/subjEntRevokeApply"
                        );
                    } else {
                        jBox.tip(data.data.revokeApplyMsg, "info");
                    }
                } else {
                    jBox.tip("找不到相关数据", "info");
                }
            })
            .error(function(data) {
                
            });
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
        url: "/sofn-asms-web/subjEnt/getSubjEntStatis",
        grid: [3, 3, 3, 3, 3, 3, 1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
