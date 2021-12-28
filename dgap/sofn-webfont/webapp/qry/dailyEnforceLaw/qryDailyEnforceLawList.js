var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("qryDailyEnforceLawList", function($scope, $http, $state) {
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
                    $("#sheng")
                        .attr("disabled", true)
                        .parent()
                        .addClass("disabled");
                }
                if (
                    $("#shi").val() != null &&
                    $("#shi").val() != "" &&
                    $("#shi").val() != undefined
                ) {
                    $("#shi")
                        .attr("disabled", true)
                        .parent()
                        .addClass("disabled");
                }
                if (
                    $("#xian").val() != null &&
                    $("#xian").val() != "" &&
                    $("#xian").val() != undefined
                ) {
                    $("#xian")
                        .attr("disabled", true)
                        .end()
                        .parent()
                        .addClass("disabled");
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
            .error(function(data) {
                console.log(data.httpCode);
                
            });
        //点击查询按钮刷新datatable
        $scope.queryDailyEnforceLawList = function($event) {
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
        };
        /* $("#ToCheck").click(function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        });*/
        //分页

        //获取行业数据字典
        $scope.industryType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 2,
            method: "post"
        })
            .success(function(data) {
                $scope.industryType = JSON.parse(data.data);
            })
            .error(function(data) {
                console.log(data);
                
            });

        //获取主体类型数据字典
        $scope.entityType = [];
        $http({
            url: "/sofn-qry-web/sysDict/getSysDictByCode",
            data: 31,
            method: "post"
        })
            .success(function(data) {
                $scope.entityType = JSON.parse(data.data);
            })
            .error(function(data) {
                console.log(data);
                
            });
        function formatParams(str) {
            return str == undefined ? "" : str;
        }
        //bootstrap表格
        function loadGrid() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[6, "asc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url:
                        "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawList_two",
                    //dataSrc : "rows", // 加载表格的相应数据源
                    dataSrc: function(json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        //console.log(json)
                        for (var i = 0; i < json.data.list.length; i++) {
                            if (
                                json.data.list[i].TASK_BEGIN_TIME != null &&
                                json.data.list[i].TASK_BEGIN_TIME != "" &&
                                json.data.list[i].TASK_BEGIN_TIME != undefined
                            ) {
                                json.data.list[i].TASK_BEGIN_TIME = new Date(
                                    parseInt(
                                        json.data.list[i].TASK_BEGIN_TIME
                                    ) + 28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].CREATE_TIME != null &&
                                json.data.list[i].CREATE_TIME != "" &&
                                json.data.list[i].CREATE_TIME != undefined
                            ) {
                                json.data.list[i].CREATE_TIME = new Date(
                                    parseInt(json.data.list[i].CREATE_TIME) +
                                        28800000
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
                    error: function(json) {
                        console.log(json);
                        
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function(params) {
                        // params.entityIndustry = $("select[name='entityIndustry']").val();
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
                        params.taskPersonName = formatParams($scope.taskPersonName);
                        params.taskName = formatParams($scope.taskName);
                        params.enforceLawResult = $(
                            "select[name='enforceLawResult']"
                        ).val();
                        ObjShen = $("select[name='ObjShen']").val();
                        ObjShi = $("select[name='ObjShi']").val();
                        ObjXian = $("select[name='ObjXian']").val();
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
                            var orderKey =
                                params.columns[orderIndex]["data"];
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
                    type: "POST"
                },
                columns: [
                    {
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        width: "4%",
                        orderable: false
                    },
                    {
                        data: "TASK_BEGIN_TIME",
                        title: "年度",
                        width: "4%",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            if (
                                data != null &&
                                data != "" &&
                                data != undefined
                            ) {
                                return data.substring(0, 4);
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        data: "TASK_NAME",
                        title: "任务名称",
                        render: function(data, type, row) {
                            if (data.length > 10) {
                                // return "<a id='" + row.id + "' onmouseover=showPopContent('" + row.id + "','" + data + "')>" + data.substring(0, 10) + "...</a>";
                                return (
                                    "<a id='TASK_NAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('TASK_NAME" +
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
                    {
                        data: "ENTITY_INDUSTRY_NAME",
                        title: "所属行业"
                    },
                    {
                        data: "ENTERPRISE_NAME",
                        title: "被执法对象"
                    },
                    {
                        data: "ENTITY_TYPE_NAME",
                        title: "主体类型"
                    },
                    {
                        data: "AREA_ID",
                        title: "区域",
                        render: function(data, type, row) {
                            // 模板化列显示内容
                            if (
                                data != null &&
                                data != "" &&
                                data != undefined
                            ) {
                                return $.fn.Cityname(row.AREA_ID);
                            }
                        }
                    } /*
                {
                    data : "ENTERPRISE_ADDRESS",
                    title : "被执法对象地址"
                },*/,
                    {
                        data: "TASK_BEGIN_TIME",
                        title: "执法时间"
                    },
                    {
                        data: "CREATE_TIME",
                        title: "创建时间"
                    },
                    {
                        data: "ENFORCE_LAW_RESULT_FLAG",
                        title: "巡查结果",
                        render: function(data, type, row) {
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
                        visible: false
                    },
                    {
                        data: "ID",
                        title: "操作",
                        width: "4%",
                        render: function(data, type, row, meta) {
                            // 模板化列显示内容
                            return (
                                '<a type="button" style=" padding-left: 0px;padding-right: 0px;margin-left: 0px;cursor:pointer"onclick="Checkreport(\'' +
                                meta.row +
                                "')\">查看</a>"
                            ); //转义传值
                        },
                        orderable: false
                    }
                ]
            });
            /**
             * 显示与隐藏表格框内字符超长数据
             * @param id
             * @param content
             */
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
            //window.Checkreport=function(obj){
            //    window.localStorage.setItem("ID",obj);
            //    $state.go("index.content.ales/etl/etlCheckreport")
        }
        /*    window.GotoUpdateWindow = function(data){
            window.localStorage.setItem("id",data);
            window.localStorage.setItem("ales_etl_updateFlag","1");
            $state.go("index.content.ales/etl/etlAddDailyEnforceLaw")
        }*/
        $scope.cleanSelectModelQuery = function($event) {
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
            $("input[name='queryCon']").val("");
            $("input[name='taskYear']").val("");
            $("input[name='datebegin']").val("");
            $("input[name='dateend']").val("");
            $("select[name='enforceLawResult']").val("");
            $("select[value='']").prop("selected", true);
            $("input[type='checkbox']:checked").prop("checked", false);
            $("input[type='checkbox']").prop("disabled", false);
            //所属行业默认不限、组织形式不限、主体类别不限
            $("input[name='entityIndustryLimit']:first").prop("checked", true);
            // $("input[name='entityScaleLimit']:first").attr("checked",true);
            $("input[name='entityTypeLimit']:first").prop("checked", true);
            $.fn.mycity("sheng", "shi", "xian", area);
            $scope.taskName = null;
            $scope.taskPersonName = null;
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        //查看详情页面
        window.Checkreport = function(index) {
            var rows = $("#grid")
                .DataTable()
                .rows()
                .data();
            console.log(rows[index]);
            /* return false;*/
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawById",
                method: "post",
                data: { id: "" }
            })
                .success(function(data) {
                    $scope.reCord = rows[index];
                    if (
                        $scope.reCord.AREA_ID != null &&
                        $scope.reCord.AREA_ID != "" &&
                        $scope.reCord.AREA_ID != undefined
                    ) {
                        $scope.areaId = $.fn.Cityname($scope.reCord.AREA_ID);
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
                })
                .error(function(data) {
                    console.log(data);
                    
                });
        };
        //    新增
        /*   $scope.lawAdd=function(){
        window.localStorage.setItem("ales_etl_updateFlag","0")
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
            }//所属行业

            //主体类型
            var entityType = $("input[name='entityType']:checked");
            var arr02 = new Array();
            for (var i = 0; i < entityType.length; i++) {
                arr02.push($(entityType[i]).val());
            }//主体类型
            //巡查结果
            var enforceLawResult = $("select[name='enforceLawResult']").val();
            var entityIndustry = arr01.toString();//所属行业
            var entityType = arr02.toString();//主体类型
            var taskYear = $("input[name='taskYear']").val();//年度
            var taskName = $("input[name='taskName']").val();//任务名称
            var taskPersonName = $("input[name='taskPersonName']").val();//执法人员
            var dateBegin = $("input[name='datebegin']").val();//执法时间开始
            var dateEnd = $("input[name='dateend']").val();//执法时间结束
            ObjShen = $("select[name='ObjShen']").val();//省
            ObjShi = $("select[name='ObjShi']").val();//市
            ObjXian = $("select[name='ObjXian']").val();//县
            var areaId = "";
            if (ObjShen != "" && ObjShen != null && ObjShen != undefined) {
                if (ObjShi != "" && ObjShi != null && ObjShi != undefined) {
                    if (
                        ObjXian != "" &&
                        ObjXian != null &&
                        ObjXian != undefined
                    ) {
                        var areaId = ObjXian;
                    } else {
                        var areaId = ObjShi;
                    }
                } else {
                    var areaId = ObjShen;
                }
            } //获取区域id

            //提交数据
            var token = window.localStorage.getItem("token");
            //日常执法标识
            var flag = 0; //日常执法
            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr(
                "action",
                "/sofn-qry-web/dailyEnforceLaw/exportSubjSv?" +
                "token=" +token +
                "&entityIndustry=" +entityIndustry +
                "&entityType=" +entityType +
                "&taskYear=" +taskYear +
                "&taskName=" +taskName +
                "&taskPersonName=" +taskPersonName +
                "&dateBegin=" +dateBegin +
                "&dateEnd=" +dateEnd +
                "&areaId=" +areaId +
                "&flag=" +flag +
                "&enforceLawResult=" +enforceLawResult
            );
            $("body").append(form);
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
            url:
                "/sofn-qry-web/dailyEnforceLaw/getDailyEnforceLawList_two_statis",
            grid: [2, 2, 2, 2, 1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
