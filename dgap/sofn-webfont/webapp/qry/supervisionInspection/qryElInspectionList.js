var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("qryElInspectionList", function ($scope, $http, $state, $timeout) {
        var vm = this;
        var area = "";
        var jibie = "";
        $http({
            url: "/sofn-qry-web/subject/findCurrentUserByToken",
            method: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
            .success(function (data) {
                area = data.user.regionId;
                jibie = data.user.orgLevel;
                if (jibie == 1) {
                    area = 0;
                }
                if (jibie == 2) {
                    area = area.substr(0, 2);
                }
                if (jibie == 3) {
                    area = area.substr(0, 4);
                }
                $.fn.mycity("sheng", "shi", "xian", area);
                //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
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
                //所属行业默认不限、组织形式不限、主体类别不限
                $("input[name='entityIndustryLimit']:first").prop(
                    "checked",
                    true
                );
                $("input[name='entityTypeLimit']:first").prop("checked", true);
                loadGrid();
            })
            .error(function (data) {

            });
        //点击查询按钮刷新datatable
        $("#ToCheck").click(function () {
            //点击间隔
            $scope.$apply($scope.btnDisabled = true);
            setTimeout(function () {
                $scope.$apply($scope.btnDisabled = false);
            }, 500);
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
            $(this).text("二次查询");
        });
        //分页

        //获取行业数据字典
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
                },
                function (res) {

                }
            );

        //获取主体类型数据字典
        $scope.entityType = [];
        $http
            .post("/sofn-qry-web/sysDict/getSysDictByCode", "30", {
                headers: {
                    token: window.localStorage.getItem("token")
                }
            })
            .then(
                function (res) {
                    $scope.entityType = JSON.parse(res.data.data);
                },
                function (res) {

                }
            );

        function formatParams(str) {
            return str == undefined ? "" : str;
        }

        //bootstrap表格
        function loadGrid() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[7, "desc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url:
                        "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawList_v2",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function (json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        $scope.$apply(function () {
                            $scope.tableTotal = json.recordsTotal;
                        });
                        return json.data.list;
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function (params) {
                        // params.entityIndustry = $("select[name='entityIndustry']").val();
                        //所属行业
                        var entityIndustry = $(
                            "input[name='entityIndustry']:checked"
                        );
                        var arr = new Array();
                        for (var i = 0; i < entityIndustry.length; i++) {
                            arr.push($(entityIndustry[i]).val());
                        }
                        params.entityIndustry = arr.toString();
                        //主体类型
                        var entityType = $("input[name='entityType']:checked");
                        var arr02 = new Array();
                        for (var i = 0; i < entityType.length; i++) {
                            arr02.push($(entityType[i]).val());
                        }
                        params.entityType = arr02.toString();
                        params.xcPsersionId = window.localStorage.getItem(
                            "xcPsersionId",
                            "xcPsersionId"
                        );
                        params.taskYear = $("input[name='taskYear']").val();
                        params.queryCon = $("input[name='queryCon']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();

                        //巡查结果
                        var enforceLawResult = $(
                            "input[name='enforceLawResult']:checked"
                        );
                        var arr03 = new Array();
                        for (var i = 0; i < enforceLawResult.length; i++) {
                            arr03.push($(enforceLawResult[i]).val());
                        }
                        params.enforceLawResult = arr03.toString();

                        ObjShen = $("select[name='ObjShen']").val();
                        ObjShi = $("select[name='ObjShi']").val();
                        ObjXian = $("select[name='ObjXian']").val();
                        params.taskName = formatParams($scope.taskName);
                        params.enterpriseName = formatParams(
                            $scope.enterpriseName
                        );
                        params.taskPersonName = formatParams(
                            $scope.taskPersonName
                        );
                        if (
                            ObjShen != "" &&
                            ObjShen != null &&
                            ObjShen != undefined
                        ) {
                            if (
                                ObjShi != "" &&
                                ObjShi != null &&
                                ObjShi != undefined
                            ) {
                                if (
                                    ObjXian != "" &&
                                    ObjXian != null &&
                                    ObjXian != undefined
                                ) {
                                    params.areaId = ObjXian;
                                } else {
                                    params.areaId = ObjShi;
                                }
                            } else {
                                params.areaId = ObjShen;
                            }
                        }

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
                    type: "POST"
                },
                columns: [
                    {
                        title: "序号",
                        orderable: false,
                        data: function (data, type, row, meta) {
                            return meta.row + 1;
                        },
                        width: "4%"
                    },
                    {
                        data: "TASK_BEGIN_TIME",
                        title: "年度",
                        render: function (data, type, row) {
                            // 模板化列显示内容
                            if (data) {
                                data = new Date(parseInt(data) + 28800000)
                                    .toISOString()
                                    .slice(0, 4);
                            }
                            return data;
                        },
                        width: "4%"
                    },
                    {
                        data: "TASK_NAME",
                        title: "任务名称",
                        width: "19%",
                        render: function (data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 15
                            ) {
                                return (
                                    "<a id='TASK_NAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('TASK_NAME" +
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
                        data: "ENTERPRISE_NAME",
                        title: "被执法对象",
                        width: "16%",
                        render: function (data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 12
                            ) {
                                return (
                                    "<a id='ENTERPRISE_NAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('ENTERPRISE_NAME" +
                                    row.ID +
                                    "','" +
                                    data +
                                    "')>" +
                                    data.substring(0, 12) +
                                    "...</a>"
                                );
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: "ENTITY_TYPE_NAME",
                        title: "主体类型",
                        width: "8%"
                    },
                    {
                        data: "AREA_ID",
                        title: "区域",
                        width: "14%",
                        render: function (data, type, row) {
                            if (!data) {
                                return "";
                            }
                            data = $.fn.Cityname(data);
                            if (data.length > 10) {

                                return (
                                    "<a id='AREA_ID" +
                                    row.ID +
                                    "' onmouseover=showPopContent('AREA_ID" +
                                    row.ID +
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
                    /*{
                 data : "ENTERPRISE_ADDRESS",
                 title : "被执法对象地址"
                 },*/
                    {
                        data: "TASK_BEGIN_TIME",
                        title: "执法时间",
                        width: "7%",
                        render: function (data, type, row) {
                            if (data) {
                                data = new Date(parseInt(data) + 28800000)
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            return data;
                        }
                    },
                    {
                        data: "ENFORCE_LAW_RESULT_FLAG",
                        title: "巡查结果",
                        width: "6%",
                        render: function (data, type, row) {
                            if (
                                data != null &&
                                data != "" &&
                                data != undefined
                            ) {
                                if ("1" == data) {
                                    return "合格";
                                } else if ("2" == data) {
                                    return "不合格";
                                } else if ("3" == data) {
                                    return "整改";
                                } else {
                                    return "";
                                }
                            }
                        }
                    },
                    {
                        data: "TASK_PERSON_NAME",
                        name: "taskPersonName",
                        title: "执法人员",
                        visible: false,
                    },
                    {
                        data: "ID",
                        title: "操作",
                        orderable: false,
                        render: function (data, type, row, meta) {
                            // 模板化列显示内容
                            return (
                                '<a type="button" data-toggle="modal" data-target="#myModal" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="Checkreport(\'' +
                                meta.row +
                                "')\">查看</a>"
                            ); //转义传值
                        }
                    }
                ]
            });
            //window.Checkreport=function(obj){
            //    window.localStorage.setItem("ID",obj);
            //    $state.go("index.content.ales/etl/etlCheckreport")
        }

        //数据导出
        $scope.exportData = function () {
            // params.entityIndustry = $("select[name='entityIndustry']").val();
            //所属行业
            var entityIndustry = $("input[name='entityIndustry']:checked");
            var arr = new Array();
            for (var i = 0; i < entityIndustry.length; i++) {
                arr.push($(entityIndustry[i]).val());
            }
            var entityIndustry = arr.toString();
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "entityIndustry");
            input1.attr("value", entityIndustry);

            //主体类型
            var entityType = $("input[name='entityType']:checked");
            var arr02 = new Array();
            for (var i = 0; i < entityType.length; i++) {
                arr02.push($(entityType[i]).val());
            }
            entityType = arr02.toString();
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "entityType");
            input2.attr("value", entityType);

            var xcPsersionId = window.localStorage.getItem(
                "xcPsersionId",
                "xcPsersionId"
            );
            var input3 = $("<input>");
            input3.attr("type", "hidden");
            input3.attr("name", "xcPsersionId");
            input3.attr("value", xcPsersionId);

            var taskYear = $("input[name='taskYear']").val();
            var input4 = $("<input>");
            input4.attr("type", "hidden");
            input4.attr("name", "taskYear");
            input4.attr("value", taskYear);

            var queryCon = $("input[name='queryCon']").val();
            var input5 = $("<input>");
            input5.attr("type", "hidden");
            input5.attr("name", "queryCon");
            input5.attr("value", queryCon);

            var dateBegin = $("input[name='datebegin']").val();
            var input6 = $("<input>");
            input6.attr("type", "hidden");
            input6.attr("name", "dateBegin");
            input6.attr("value", dateBegin);

            var dateEnd = $("input[name='dateend']").val();
            var input7 = $("<input>");
            input7.attr("type", "hidden");
            input7.attr("name", "dateEnd");
            input7.attr("value", dateEnd);

            //巡查结果
            var enforceLawResult = $(
                "input[name='enforceLawResult']:checked"
            );
            var arr03 = new Array();
            for (var i = 0; i < enforceLawResult.length; i++) {
                arr03.push($(enforceLawResult[i]).val());
            }
            var input8 = $("<input>");
            input8.attr("type", "hidden");
            input8.attr("name", "enforceLawResult");
            input8.attr("value", arr03.toString());

            ObjShen = $("select[name='ObjShen']").val();
            ObjShi = $("select[name='ObjShi']").val();
            ObjXian = $("select[name='ObjXian']").val();
            var taskName = formatParams($scope.taskName);
            var input9 = $("<input>");
            input9.attr("type", "hidden");
            input9.attr("name", "taskName");
            input9.attr("value", taskName);

            var enterpriseName = formatParams($scope.enterpriseName);
            var input10 = $("<input>");
            input10.attr("type", "hidden");
            input10.attr("name", "enterpriseName");
            input10.attr("value", enterpriseName);

            var taskPersonName = formatParams($scope.taskPersonName);
            var input11 = $("<input>");
            input11.attr("type", "hidden");
            input11.attr("name", "taskPersonName");
            input11.attr("value", taskPersonName);

            var areaId;
            if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                    if (
                        ObjXian != "" &&
                        ObjXian != null &&
                        ObjXian != undefined
                    ) {
                        areaId = ObjXian;
                    } else {
                        areaId = ObjShi;
                    }
                } else {
                    areaId = ObjShen;
                }
            }
            var input12 = $("<input>");
            input12.attr("type", "hidden");
            input12.attr("name", "areaId");
            input12.attr("value", areaId);
            //排序传参
            if ($scope.chartParams.order[0]) {
                var orderIndex = $scope.chartParams.order[0]["column"];
                var orderKey = $scope.chartParams.columns[orderIndex]["data"];
                if (orderKey != "function") {
                    $scope.chartParams.orderBy =
                        orderKey + " " + $scope.chartParams.order[0]["dir"];
                }
            }
            var input13 = $("<input>");
            input13.attr("type", "hidden");
            input13.attr("name", "orderBy");
            input13.attr("value", $scope.chartParams.orderBy);
            var input14 = $("<input>");
            input14.attr("type", "hidden");
            input14.attr("name", "token");
            input14.attr("value", localStorage.getItem("token"));
            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr(
                "action",
                "/sofn-qry-web/dailyEnforceLaw/exportEnforceLawList"
            );
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

            $("body").append(form);
            form.submit();
        };

        /*    window.GotoUpdateWindow = function(data){
     window.localStorage.setItem("id",data);
     window.localStorage.setItem("ales_etl_updateFlag","1");
     $state.go("index.content.ales/etl/etlAddDailyEnforceLaw")
     }*/
        $scope.cleanSelectModelQuery = function ($event) {
            $("input[name='queryCon']").val("");
            $("input[name='taskYear']").val("");
            $("input[name='datebegin']").val("");
            $("input[name='dateend']").val("");
            $("select[name='enforceLawResult']").val("");
            $("input[type='checkbox']:checked").prop("checked", false);
            //所属行业默认不限、组织形式不限、主体类别不限
            $("input[name='entityIndustryLimit']:first").prop("checked", true);
            $("input[name='entityTypeLimit']:first").prop("checked", true);
            $.fn.mycity("sheng", "shi", "xian", area);
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
            //重置 二次查询
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
            $("input[name='enforceLawResultLimit']").prop("checked", true);
            $scope.taskName = null;
            $scope.enterpriseName = null;
            $scope.taskPersonName = null;
        };
        //查看详情页面
        window.Checkreport = function (index) {
            var rows = $("#grid")
                .DataTable()
                .rows()
                .data();
            /* return false;*/
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawById",
                method: "post",
                data: {id: ""}
            })
                .success(function (data) {
                    $scope.reCord = rows[index];
                    if (
                        $scope.reCord.AREA_ID != null &&
                        $scope.reCord.AREA_ID != "" &&
                        $scope.reCord.AREA_ID != undefined
                    ) {
                        $scope.areaId = $.fn.Cityname($scope.reCord.AREA_ID);
                    }
                    if ($scope.reCord.TASK_BEGIN_TIME) {
                        $scope.reCord.TASK_BEGIN_TIME = new Date(parseInt($scope.reCord.TASK_BEGIN_TIME) + 28800000).toISOString().slice(0, 10);
                    }
                    $("#myModal").modal("show");
                    //$scope.reCord=data.dailyEnforceLaw;
                    //if($scope.reCord.taskBeginTime!=null&&$scope.reCord.taskBeginTime!=""&&$scope.reCord.taskBeginTime!=undefined) {
                    //    $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)+28800000).toISOString().slice(0, 10);
                    //    $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
                    //}
                    //if($scope.reCord.areaId!=null&&$scope.reCord.areaId!=""&&$scope.reCord.areaId!=undefined) {
                    //    $scope.areaId=$.fn.Cityname($scope.reCord.areaId);
                    //}
                    //获取现场图片
                    var array = $scope.reCord.SCENE_PICTURES.split("*##*");
                    for (var i = 0; i < array.length; i++) {
                        if (i > 0) {
                            $("#scenePictures").append(
                                '<img style="padding-right: 10px; width: 80px;" src="' +
                                "/sofn-sys-web/sysTemplate/downPic?fileUrl=" +
                                array[i] +
                                '" alt=""/>'
                            );
                        } else {
                            $("#scenePictures").html(
                                '<img style="padding-right: 10px; width: 80px;" src="' +
                                "/sofn-sys-web/sysTemplate/downPic?fileUrl=" +
                                array[i] +
                                '" alt=""/>'
                            );
                        }
                    }
                })
                .error(function (data) {

                });
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
            url: "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawStatis",
            grid: [2, 2, 1], //水平宽度比例
            //业务参数
            data: function () {
                return $scope.chartParams;
            }
        };
    });
