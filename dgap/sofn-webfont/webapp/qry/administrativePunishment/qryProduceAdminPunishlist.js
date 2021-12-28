var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("qryProduceAdminPunishlist", function($scope, $http, $state) {
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
            .success(function(data) {
                area = data.user.regionId;
                jibie = data.user.orgLevel;
                if (jibie == 1) {
                    area = 0;
                }
                if (jibie == 2) {
                    area = area.substr(0, 2);
                }
                if (jibie == 3) {
                    area = area.substr(0.4);
                }
                if (jibie == 4) {
                    area = area.substring(0, 6);
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
                // $("input[name='entityScaleLimit']:first").attr("checked",true);
                $("input[name='entityTypeLimit']:first").prop("checked", true);
                loadGrid();
            })
            .error(function() {});
        $scope.cleanSelectModelQuery = function($event) {
            if ($event) {
                $($event.currentTarget)
                .prev()
                .text("查询");
            }
            $("input[name='taskYear']").val("");
            $("input[name='datebegin']").val("");
            $("input[name='dateend']").val("");
            $("input[name='queryCon']").val("");
            $("input[type='checkbox']:checked").prop("checked", false);
            $("input[type='checkbox']").prop("disabled", false);
            $.fn.mycity("sheng", "shi", "xian", area);
            //所属行业默认不限、组织形式不限、主体类别不限
            $("input[name='entityIndustryLimit']:first").prop("checked", true);
            // $("input[name='entityScaleLimit']:first").attr("checked",true);
            $("input[name='entityTypeLimit']:first").prop("checked", true);
            //绑值
            $scope.caseName = null;
            $scope.penaltySource = "";
            $scope.punishCode = null;
            // $scope.enierpriseIdName = null;
            $scope.enierpriseCode = null;
            $scope.legalPeson = null;
            $scope.productName = null;
            $scope.enteriseName = null;
            $scope.manufactureDate = null;
            $scope.enforceLawPeople = null;
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };
        //省市县的三级联动
        //$.fn.mycity("sheng","shi","xian",510722)
        //点击查询按钮刷新datatable
        $scope.queryProduceAdminPunishlist = function($event){
            //点击间隔
            $scope.btnDisabled = true;
            setTimeout(function(){
                $scope.$apply($scope.btnDisabled = false);
            }, 500);
            $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
            $($event.currentTarget).text("二次查询");
        }
       /* $("#ToCheck").click(function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        });*/
        //分页
        function formatParams(str) {
            return str == undefined ? "" : str;
        }
        //获取行业数据字典
        $scope.industryType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 2,
            method: "post"
        }).success(function(data) {
            $scope.industryType = JSON.parse(data.data);
        });

        //获取主体类型数据字典
        $scope.entityType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 31,
            method: "post"
        }).success(function(data) {
            $scope.entityType = JSON.parse(data.data);
        });

        //bootstrap表格
        function loadGrid() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[9, "asc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url:
                        "/sofn-qry-web/administrativePenalty/getAdministrativePenaltyList_two",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function(json) {
                        // if(json.code != 200){
                        //     swal('系统提示', '服务器出错', 'error');
                        //     return false;
                        // }
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        console.log(json.data.list);
                        for (var i = 0; i < json.data.list.length; i++) {
                            if (
                                json.data.list[i].ENFORCE_LAW_TIME != null &&
                                json.data.list[i].ENFORCE_LAW_TIME != "" &&
                                json.data.list[i].ENFORCE_LAW_TIME != undefined
                            ) {
                                json.data.list[i].ENFORCE_LAW_TIME = new Date(
                                    parseInt(
                                        json.data.list[i].ENFORCE_LAW_TIME
                                    ) + 28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                        }
                        $scope.$apply(function() {
                            $scope.tableTotal = json.data.total;
                        });
                        return json.data.list;
                    },
                    error: function(msg) {
                        console.log(msg);
                        
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function(params) {
                        //所属行业
                        var entityIndustry = $(
                            "input[name='entityIndustry']:checked"
                        );
                        var arr01 = new Array();
                        for (var i = 0; i < entityIndustry.length; i++) {
                            arr01.push($(entityIndustry[i]).val());
                        }
                        params.entityIndustry = arr01.toString();

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
                        ObjShen = $("select[name='ObjShen']").val();
                        ObjShi = $("select[name='ObjShi']").val();
                        ObjXian = $("select[name='ObjXian']").val();

                        //绑值
                        params.caseName = formatParams($scope.caseName);
                        params.penaltySource = formatParams(
                            $scope.penaltySource
                        );
                        params.punishCode = formatParams($scope.punishCode);
                        // params.enierpriseIdName = formatParams(
                        //     $scope.enierpriseIdName
                        // );
                        params.enierpriseCode = formatParams(
                            $scope.enierpriseCode
                        );
                        params.legalPeson = formatParams($scope.legalPeson);
                        params.productName = formatParams($scope.productName);
                        params.enteriseName = formatParams($scope.enteriseName);
                        params.manufactureDate = formatParams(
                            $scope.manufactureDate
                        );
                        params.enforceLawPeople = formatParams(
                            $scope.enforceLawPeople
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
                                    params.area = ObjXian;
                                } else {
                                    params.area = ObjShi;
                                }
                            } else {
                                params.area = ObjShen;
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
                    type: "post"
                },
                columns: [
                    {
                        //data : "RN",
                        title: "序号",
                        width: "4%",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        orderable: false
                    },
                    {
                        data: "ENFORCE_LAW_TIME",
                        title: "年度",
                        width: "4%",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return data.substring(0, 4);
                        }
                    },
                    {
                        data: "CASE_NAME",
                        title: "案件名称",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 18
                            ) {
                                return (
                                    "<a id='CASE_NAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('CASE_NAME" +
                                    row.ID +
                                    "','" +
                                    data +
                                    "')>" +
                                    data.substring(0, 18) +
                                    "...</a>"
                                );
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: "PUNISH_CODE",
                        title: "行政处罚决定案号",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 25
                            ) {
                                return (
                                    "<a id='PUNISH_CODE" +
                                    row.ID +
                                    "' onmouseover=showPopContent('PUNISH_CODE" +
                                    row.ID +
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
                        data: "ENTERPRISE_ID_NAME",
                        title: "当事主体",
                        width: "25%"
                    },
                    {
                        data: "ENTITY_INDUSTRY_NAME",
                        title: "所属行业"
                    },
                    {
                        data: "ENTITY_TYPE_NAME",
                        title: "主体类型"
                    },
                    {
                        data: "AREA",
                        title: "区域",
                        width: "12%",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            if (
                                data != null &&
                                data != "" &&
                                data != undefined
                            ) {
                                return $.fn.Cityname(row.AREA);
                            }
                        }
                    },
                    {
                        data: "ENFORCE_LAW_TIME",
                        title: "执法时间",
                        width: "7%"
                    },
                  {
                        data: "PENALTY_SOURCE",
                        name: "penaltySource",
                        title: "行政处罚来源",
                        visible: false
                    },{
                        data: "ENTERPRISE_CODE",
                        name: "enierpriseCode",
                        title: "主体身份码",
                        visible: false
                    },{
                        data: "LEGAL_PERSON",
                        name: "legalPeson",
                        title: "当事人",
                        visible: false
                    },{
                        data: "ENFORCE_LAW_PEOPLE",
                        name: "enforceLawPeople",
                        title: "执法人员",
                        visible: false
                    },
                    {
                        data: "ID",
                        title: "操作",
                        width: "4%",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            return (
                                '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#myModal" onclick="Checkreport(\'' +
                                data +
                                "')\">查看</a>"
                            ); //转义传值
                        },
                        orderable: false
                    }
                ]
            });
        }

        //查看详情页面
        window.Checkreport = function(data) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/administrativePenalty/getPunishiById",
                method: "post",
                params: { id: data }
            })
                .success(function(data) {
                    $scope.reCord = data.produceAdminPunish;
                })
                .error(function(data) {
                    console.log(data.httpCode);
                    
                });
        };
        /*   window.produceAdminPunishPrint=function(){
        radioID=$("input[type='radio']:checked").val()
        window.localStorage.setItem("radioID",radioID)
        $state.go("index.content.ales/punish/produceAdminPunishPrint");
    }*/

        //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
        $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
            for (
                var i = 0;
                i < $("input[name='" + limitCheckBoxName + "']").length;
                i++
            ) {
                var lim = $("input[name='" + limitCheckBoxName + "']")[i]
                    .checked;
                if (lim) {
                    $("input[name='" + checkBoxName + "']:checked").attr(
                        "checked",
                        false
                    );
                }
            }
        };

        //数据导出
        $scope.exportData = function() {
            //所属行业
            var entityIndustry = $("input[name='entityIndustry']:checked");
            var arr01 = new Array();
            for (var i = 0; i < entityIndustry.length; i++) {
                arr01.push($(entityIndustry[i]).val());
            }

            //主体类型
            var entityType = $("input[name='entityType']:checked");
            var arr02 = new Array();
            for (var i = 0; i < entityType.length; i++) {
                arr02.push($(entityType[i]).val());
            }
            var entityIndustry = arr01.toString();
            var entityType = arr02.toString();
            var taskYear = $("input[name='taskYear']").val();
            //自定义条件查询
            var caseName = $("input[name='caseName']").val();
            var penaltySource =  $scope.penaltySource;
            var punishCode =  $("input[name='punishCode']").val();
            var enierpriseCode = $("input[name='enierpriseCode']").val();
            var legalPeson = $("input[name='legalPeson']").val();
            var enforceLawPeople = $("input[name='enforceLawPeople']").val();

            var dateBegin = $("input[name='datebegin']").val();
            var dateEnd = $("input[name='dateend']").val();
            ObjShen = $("select[name='ObjShen']").val();
            ObjShi = $("select[name='ObjShi']").val();
            ObjXian = $("select[name='ObjXian']").val();
            var area = "";
            if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                    if (
                        ObjXian != "" &&
                        ObjXian != null &&
                        ObjXian != undefined
                    ) {
                        var area = ObjXian;
                    } else {
                        var area = ObjShi;
                    }
                } else {
                    var area = ObjShen;
                }
            }
            //提交数据
            var token = window.localStorage.getItem("token");

            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr(
                "action",
                "/sofn-qry-web/administrativePenalty/exportSubjSv?token=" +
                    token +
                    "&entityIndustry=" +
                    entityIndustry +
                    "&entityType=" +
                    entityType +
                    "&taskYear=" +
                    taskYear +
                    "&dateBegin=" +
                    dateBegin +
                    "&dateEnd=" +
                    dateEnd +
                    "&area=" +
                    area+
                    "&caseName=" +
                    caseName +
                    "&penaltySource=" +
                    penaltySource +
                    "&punishCode=" +
                    punishCode+
                    "&enierpriseCode=" +
                    enierpriseCode+
                    "&legalPeson=" +
                    legalPeson+
                    "&enforceLawPeople=" +
                    enforceLawPeople

            );
            $("body").append(form);
            form.submit();
        };
        window.downloadFileName = function() {
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-qry-web/qryFile/downloadFile");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileUrl");
            input1.attr("value", $scope.reCord.punishFiles);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "fileName");
            input2.attr("value", $scope.reCord.punishFilesName);
            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
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
                $(".condition-checked-all").prop(
                    "checked",
                    !!!unchecked.length
                );
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
            url:  "/sofn-qry-web/administrativePenalty/getAdministrativePenaltyList_two_Statis",
            grid: [2,2,2,1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
