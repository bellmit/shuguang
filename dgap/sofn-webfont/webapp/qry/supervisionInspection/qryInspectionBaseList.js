var myapp = angular.module("myapp", []);
myapp.controller("qryInspectionBaseList", function($scope, $http, $state,$timeout) {
    var vm = this;
    //获取用户信息
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
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            $scope.disableAreaSelectByAreaId("sheng", "shi", "xian");
            //所属行业默认不限、组织形式不限、主体类别不限
            $("input[name='entityIndustryLimit']:first").attr("checked", true);
            $("input[name='entityScaleLimit']:first").attr("checked", true);
            $("input[name='entityTypeLimit']:first").attr("checked", true);
            $("input[name='inspectionTypeLimit']:first").attr("checked", true);
            loadGrid();
        })
        .error(function(data) {
            
        });
    //重置区域
    $scope.resetArea = function($event) {
        $("#searchForm").form("reset");
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        $scope.inspectionSvName = null;
        $scope.address = null;
        $scope.legalName = null;
        $scope.legalPhone = null;
        $scope.contactName = null;
        $scope.contactPhone = null;

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
        $("input[name='createDateBegin']").val("");
        $("input[name='createDateEnd']").val("");
        $("select[name='inspectionResult']").val("");
        $("input[type='checkbox']:checked").attr("checked", false);
        $("input[name='entityIndustryLimit']").prop("checked", true);
        $("input[name='entityScaleLimit']").prop("checked", true);
        $("input[name='entityTypeLimit']").prop("checked", true);
        $("input[name='inspectionTypeLimit']").prop("checked", true);
        $("input[name='inspectionResultLimit']").prop("checked", true);

        $scope.inspectionSvName = null;
        $scope.address = null;
        $scope.legalName = null;
        $scope.legalPhone = null;
        $scope.contactName = null;
        $scope.contactPhone = null;
    };

    //限制区域是否可选
    $scope.disableAreaSelectByAreaId = function(provinceId, cityId, countyId) {
        if (
            $("#" + provinceId).val() != null &&
            $("#" + provinceId).val() != "" &&
            $("#" + provinceId).val() != undefined
        ) {
            $("#" + provinceId).attr("disabled", true);
        }
        if (
            $("#" + cityId).val() != null &&
            $("#" + cityId).val() != "" &&
            $("#" + cityId).val() != undefined
        ) {
            $("#" + cityId).attr("disabled", true);
        }
        if (
            $("#" + countyId).val() != null &&
            $("#" + countyId).val() != "" &&
            $("#" + countyId).val() != undefined
        ) {
            $("#" + countyId).attr("disabled", true);
        }
    };

    //获取行业数据字典
    $scope.industryType = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "2", {
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataType: "json",
            contentType: "application/json"
        })
        .then(
            function(res) {
                $scope.industryType = JSON.parse(res.data.data);
            },
            function(res) {
                
            }
        );
    //获取主体类别数据字典
    $scope.subjType = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "30", {
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
    //获取组织形式数据字典
    $scope.orgMode = [];
    $http
        .post("/sofn-qry-web/sysDict/getSysDictByCode", "6", {
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
    function formatParams(str) {
        return str == undefined ? "" : str;
    }
    //dataTable
    //基地巡查列表
    function loadGrid() {
        $("#grid").dataTable({
            ordering: true, //开启排序
            order: [[9, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url:
                    "/sofn-qry-web/supervisionInspection/getBaseInspectionAllList_v2",
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
                            json.list[i].INSPECTION_TIME != null &&
                            json.list[i].INSPECTION_TIME != "" &&
                            json.list[i].INSPECTION_TIME != undefined
                        ) {
                            json.list[i].INSPECTION_TIME = new Date(
                                parseInt(json.list[i].INSPECTION_TIME) +
                                    28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                    }
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].CREATE_TIME != null &&
                            json.list[i].CREATE_TIME != "" &&
                            json.list[i].CREATE_TIME != undefined
                        ) {
                            json.list[i].CREATE_TIME = new Date(
                                parseInt(json.list[i].CREATE_TIME) + 28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.token = window.localStorage.getItem("token");
                    var entityIndustry = $(
                        "input[name='entityIndustry']:checked"
                    );
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
                    var entityType = $("input[name='entityType']:checked");
                    var arr2 = new Array();
                    for (var i = 0; i < entityType.length; i++) {
                        arr2.push($(entityType[i]).val());
                    }
                    params.entityType = arr2.toString();
                    var inspectionType = $(
                        "input[name='inspectionType']:checked"
                    );
                    var inspectionTypeArr = new Array();
                    for (var i = 0; i < inspectionType.length; i++) {
                        inspectionTypeArr.push($(inspectionType[i]).val());
                    }

                    var arr3 = $("input[name='inspectionResult']:checked");
                    var inspectionResultArr = new Array();
                    for (var i = 0; i < arr3.length; i++) {
                        inspectionResultArr.push($(arr3[i]).val());
                    }
                    params.inspectionResult = inspectionResultArr.toString();

                    params.inspectionType = inspectionTypeArr.toString();
                    // params.entityType = $("select[name='entityType']").val();

                    // params.entityScale = $("select[name='entityScale']").val();
                    params.enterpriseName = $(
                        "input[name='enterpriseName']"
                    ).val();
                    params.inspectionUserName = $(
                        "input[name='inspectionUserName']"
                    ).val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.createDateBegin = $(
                        "input[name='createDateBegin']"
                    ).val();
                    params.createDateEnd = $(
                        "input[name='createDateEnd']"
                    ).val();
                    var province = $("#sheng").val();
                    var city = $("#shi").val();
                    var county = $("#xian").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (
                        city != null &&
                        city != undefined &&
                        city != ""
                    ) {
                        areaId = city;
                    } else if (
                        province != null &&
                        province != undefined &&
                        province != ""
                    ) {
                        areaId = province;
                    }
                    params.area = areaId;
                    params.inspectionSvName = formatParams(
                        $scope.inspectionSvName
                    );
                    params.address = formatParams($scope.address);
                    params.legalName = formatParams($scope.legalName);
                    params.legalPhone = formatParams($scope.legalPhone);
                    params.contactName = formatParams($scope.contactName);
                    params.contactPhone = formatParams($scope.contactPhone);

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
                    orderable: false,
                    data: function(data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    width: "19%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 15
                        ) {
                            return (
                                "<a id='ENTERPRISE_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('ENTERPRISE_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 15) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "ENTITY_INDUSTRY_NAME",
                    title: "所属行业",
                    width: "11%"
                },
                {
                    data: "AREA",
                    title: "区域",
                    width: "12%",
                    render: function(data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 10
                        ) {
                            return (
                                "<a id='AREA" +
                                row.ID +
                                "' onmouseover=showPopContent('AREA" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 9) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "CONTACT_NAME",
                    title: "联系人",
                    width: "8%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 5
                        ) {
                            return (
                                "<a id='CONTACT_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('CONTACT_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 5) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "INSPECTION_TYPE_NAME",
                    title: "任务类型",
                    width: "12%"
                },
                {
                    data: "INSPECTION_RESULT",
                    title: "巡查结果",
                    width: "6%",
                    render: function(data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        }
                    }
                },
                {
                    data: "INSPECTION_TIME",
                    title: "巡查时间",
                    width: "7%"
                },
                {
                    data: "INSPECTION_USER_NAME",
                    title: "巡查员",
                    width: "10%",
                    render: function(data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 14
                        ) {
                            return (
                                "<a id='INSPECTION_USER_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('INSPECTION_USER_NAME" +
                                row.ID +
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
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width: "7%"
                },
                {
                    data: "INSPECTION_SV_NAME",
                    name: "inspectionSvName",
                    title: "巡查机构",
                    visible: false,
                },
                {
                    data: "LEGAL_NAME",
                    name: "legalName",
                    title: "法定代表人",
                    visible: false,
                },
                {
                    data: "LEGAL_PHONE",
                    name: "legalPhone",
                    title: "法人联系电话",
                    visible: false,
                },
                {
                    data: "CONTACT_PHONE",
                    name: "contactPhone",
                    title: "联系人电话",
                    visible: false,
                },
                {
                    data: "ID",
                    title: "操作",
                    orderable: false,
                    render: function(data, type, row) {
                        // 模板化列显示内容
                        var butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="inspectionView(\'' +
                            data +
                            "')\">查看</a>";
                        return butt;
                    }
                }
            ]
        });
    }

    //数据导出
    $scope.exportData = function() {
        //提交数据
        var token = window.localStorage.getItem("token");
        var input1 = $("<input>");
        input1.attr("type", "hidden");
        input1.attr("name", "token");
        input1.attr("value", token);
        var arr = new Array();
        var entityIndustrys = $("input[name='entityIndustry']:checked");
        for (var i = 0; i < entityIndustrys.length; i++) {
            arr.push($(entityIndustrys[i]).val());
        }
        var entityIndustry = arr.toString();
        var input2 = $("<input>");
        input2.attr("type", "hidden");
        input2.attr("name", "entityIndustry");
        input2.attr("value", entityIndustry);
        var entityScales = $("input[name='entityScale']:checked");
        var arr1 = new Array();
        for (var i = 0; i < entityScales.length; i++) {
            arr1.push($(entityScales[i]).val());
        }
        var entityScale = arr1.toString();
        var input3 = $("<input>");
        input3.attr("type", "hidden");
        input3.attr("name", "entityScale");
        input3.attr("value", entityScale);
        var entityTypes = $("input[name='entityType']:checked");
        var arr2 = new Array();
        for (var i = 0; i < entityTypes.length; i++) {
            arr2.push($(entityTypes[i]).val());
        }
        var entityType = arr2.toString();
        var input19 = $("<input>");
        input19.attr("type", "hidden");
        input19.attr("name", "entityType");
        input19.attr("value", entityType);
        // params.entityType = $("select[name='entityType']").val();
        var arr3 = $("input[name='inspectionResult']:checked");
        var inspectionResultArr = new Array();
        for (var i = 0; i < arr3.length; i++) {
            inspectionResultArr.push($(arr3[i]).val());
        }
        var input4 = $("<input>");
        input4.attr("type", "hidden");
        input4.attr("name", "inspectionResult");
        input4.attr("value", inspectionResultArr.toString());
        // params.entityScale = $("select[name='entityScale']").val();
        var enterpriseName = $("input[name='enterpriseName']").val();
        var input20 = $("<input>");
        input20.attr("type", "hidden");
        input20.attr("name", "enterpriseName");
        input20.attr("value", enterpriseName);
        var dateBegin = $("input[name='dateBegin']").val();
        var input5 = $("<input>");
        input5.attr("type", "hidden");
        input5.attr("name", "dateBegin");
        input5.attr("value", dateBegin);
        var dateEnd = $("input[name='dateEnd']").val();
        var input6 = $("<input>");
        input6.attr("type", "hidden");
        input6.attr("name", "dateEnd");
        input6.attr("value", dateEnd);
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var area = "";
        if (county != null && county != undefined && county != "") {
            area = county;
        } else if (city != null && city != undefined && city != "") {
            area = city;
        } else if (
            province != null &&
            province != undefined &&
            province != ""
        ) {
            area = province;
        }
        var input7 = $("<input>");
        input7.attr("type", "hidden");
        input7.attr("name", "area");
        input7.attr("value", area);
        var inspectionUserName = $("input[name='inspectionUserName']").val();
        var input8 = $("<input>");
        input8.attr("type", "hidden");
        input8.attr("name", "inspectionUserName");
        input8.attr("value", inspectionUserName);
        var createDateBegin = $("input[name='createDateBegin']").val();
        var input9 = $("<input>");
        input9.attr("type", "hidden");
        input9.attr("name", "createDateBegin");
        input9.attr("value", createDateBegin);
        var createDateEnd = $("input[name='createDateEnd']").val();
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "createDateEnd");
        input10.attr("value", createDateEnd);
        var inspectionTypes = $("input[name='inspectionType']:checked");
        var inspectionTypeArr = new Array();
        for (var i = 0; i < inspectionTypes.length; i++) {
            inspectionTypeArr.push($(inspectionTypes[i]).val());
        }
        var inspectionType = inspectionTypeArr.toString();
        var input11 = $("<input>");
        input11.attr("type", "hidden");
        input11.attr("name", "inspectionType");
        input11.attr("value", inspectionType);
        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input12 = $("<input>");
        input12.attr("type", "hidden");
        input12.attr("name", "orderBy");
        input12.attr("value", $scope.chartParams.orderBy);
        var input13 = $("<input>");
        input13.attr("type", "hidden");
        input13.attr("name", "inspectionSvName");
        input13.attr("value", formatParams($scope.inspectionSvName));
        var input14 = $("<input>");
        input14.attr("type", "hidden");
        input14.attr("name", "address");
        input14.attr("value", formatParams($scope.address));
        var input15 = $("<input>");
        input15.attr("type", "hidden");
        input15.attr("name", "legalName");
        input15.attr("value", formatParams($scope.legalName));
        var input16 = $("<input>");
        input16.attr("type", "hidden");
        input16.attr("name", "legalPhone");
        input16.attr("value", formatParams($scope.legalPhone));
        var input17 = $("<input>");
        input17.attr("type", "hidden");
        input17.attr("name", "contactName");
        input17.attr("value", formatParams($scope.contactName));
        var input18 = $("<input>");
        input18.attr("type", "hidden");
        input18.attr("name", "contactPhone");
        input18.attr("value", formatParams($scope.contactPhone));
        //form
        var form = $("<form>");
        form.attr("style", "display:none");
        form.attr("target", "");
        form.attr("method", "post");
        form.attr("action", "/sofn-qry-web/supervisionInspection/exportBIList");
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
        $("body").append(form);
        form.submit();
    };

    //重新加载基地巡查列表
    $scope.queryBaseInspection = function($event) {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
        //二次查询
        $($event.currentTarget).text("二次查询");
    };

    //跳转页面-查看基地巡查详情
    window.inspectionView = function(data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        window.localStorage.removeItem("inspectionViewEntryType");
        window.localStorage.setItem("inspectionViewEntryType", "0"); //基地巡查详情，从基地列表进入查看
        $state.go(
            "index.content.qry/supervisionInspection/qryInspectionBaseView"
        );
    };

    //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
    $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
        for (
            var i = 0;
            i < $("input[name='" + limitCheckBoxName + "']").length;
            i++
        ) {
            var lim = $("input[name='" + limitCheckBoxName + "']")[i].checked;
            if (lim) {
                $("input[name='" + checkBoxName + "']:checked").attr(
                    "checked",
                    false
                );
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
                            control.find('option:first').prop('selected', true).trigger('change');
                            break;
                        case "checkbox":
                            $scope[key] = "";
                            control.find(':checkbox').first().prop('checked', true).end().siblings().prop('checked', false);
                            break;
                        case "date":
                            control.find('input:text').val('').trigger('input');
                            break;
                        case "radio":
                            break;
                    }
                }
                $scope["visible" + key] = checked;
                //切换表格列是否可见
                var table = $("#grid").DataTable();
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
        url: "/sofn-qry-web/supervisionInspection/getBaseInspectionStatis",
        grid: [3,3,3,2,2,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
