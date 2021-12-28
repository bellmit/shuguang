var myapp = angular
    .module("myapp", [])
    .controller("qryReCheckTask", function($scope, $http, $state,$timeout) {
        var vm = this;
        /**
         * 任务列表
         * */
        //查询按钮
        $("#ToCheck").click(function() {
            //点击间隔
            $scope.$apply($scope.btnDisabled = true);
            setTimeout(function(){
                $scope.$apply($scope.btnDisabled = false);
            }, 500);
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
            $(this).text("二次查询");
        });

        function formatParams(str) {
            return str == undefined ? "" : str;
        }
        $(function() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[8, "desc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url: "/sofn-qry-web/monitorTask/getReCheckTaskList_v2",
                    dataSrc: function(json) {
                        json.recordsTotal = json.data.total; //总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum; //页码
                        json.pageSize = json.data.pageSize; //每页个数
                        json.start =
                            (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                        json.length = json.data.pageSize;
                        $scope.$apply(function() {
                            $scope.tableTotal = json.recordsTotal;
                        });
                        for (var i = 0; i < json.data.list.length; i++) {
                            //时间格式化
                            if (
                                json.data.list[i].RECHECKTASKBEGIN != null &&
                                json.data.list[i].RECHECKTASKBEGIN != "" &&
                                json.data.list[i].RECHECKTASKBEGIN != undefined
                            ) {
                                json.data.list[i].RECHECKTASKBEGIN = new Date(
                                    parseInt(
                                        json.data.list[i].RECHECKTASKBEGIN
                                    ) + 28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].RECHECKTASKEND != null &&
                                json.data.list[i].RECHECKTASKEND != "" &&
                                json.data.list[i].RECHECKTASKEND != undefined
                            ) {
                                json.data.list[i].RECHECKTASKEND = new Date(
                                    parseInt(json.data.list[i].RECHECKTASKEND) +
                                        28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].CREATETIME != null &&
                                json.data.list[i].CREATETIME != "" &&
                                json.data.list[i].CREATETIME != undefined
                            ) {
                                json.data.list[i].CREATETIME = new Date(
                                    parseInt(json.data.list[i].CREATETIME) +
                                        28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                        }
                        return json.data.list;
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function(params) {
                        params.recheckTaskName = $(
                            "input[name='recheckTaskName']"
                        ).val();
                        params.recheckTaskYear = $(
                            "input[name='queryYear']"
                        ).val();
                        params.state = $("select[name='state']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.dateCreateBegin = $(
                            "input[name='dateCreateBegin']"
                        ).val();
                        params.dateCreateEnd = $(
                            "input[name='dateCreateEnd']"
                        ).val();
                        params.initTaskName = formatParams($scope.initTaskName);
                        var initTaskTypeChecked = [];
                        $(".initTaskType:checked").map(function() {
                            initTaskTypeChecked.push($(this).val());
                        });
                        params.initTaskType = initTaskTypeChecked.join(",");
                        params.createOrgName = formatParams(
                            $scope.createOrgName
                        );
                        params.recheckUnitName = formatParams(
                            $scope.recheckUnitName
                        );
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
                        data: "ID",
                        title: "ID",
                        visible: false
                    },
                    {
                        title: "序号",
                        data: function(data, type, row, meta) {
                            return meta.row + 1;
                        },
                        orderable: false,
                        width: "4%"
                    },
                    {
                        data: "RECHECKTASKNAME",
                        title: "任务名称",
                        width: "30%",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 30
                            ) {
                                return (
                                    "<a id='RECHECKTASKNAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('RECHECKTASKNAME" +
                                    row.ID +
                                    "','" +
                                    data +
                                    "')>" +
                                    data.substring(0, 30) +
                                    "...</a>"
                                );
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: "RECHECKTASKYEAR",
                        title: "年度",
                        width: "6%"
                    },
                    {
                        data: "CREATEORGNAME",
                        title: "任务发布单位",
                        width: "12%"
                    },
                    {
                        data: "RECHECKUNITNAME",
                        title: "复检检测单位",
                        width: "12%"
                    },
                    {
                        data: "RECHECKTASKBEGIN",
                        title: "开始时间",
                        width: "7%"
                    },
                    {
                        data: "RECHECKTASKEND",
                        title: "结束时间",
                        width: "7%"
                    },
                    {
                        data: "CREATETIME",
                        title: "创建时间",
                        width: "7%"
                    },
                    {
                        data: "STATE",
                        title: "任务状态",
                        width: "9%",
                        render: function(data, type, row) {
                            //未发布
                            if (data == 0) {
                                return '<span style="color:#1f934b;font-weight: bold;">未发布</span>';
                            }
                            //已发布
                            if (data == 1) {
                                return '<span style="color:#1f934b;font-weight: bold;">已发布</span>';
                            }
                            //已废止
                            if (data == 2) {
                                return '<span style="color:#ff0d06;font-weight: bold;">已废止</span>';
                            }
                            //执行中
                            if (data == 3) {
                                return '<span style="color:#333333;font-weight: bold;">执行中</span>';
                            }
                            //已结束,未完成
                            if (data == 4) {
                                return '<span style="color:#ff0d06;font-weight: bold;">已结束,未完成</span>';
                            }
                            //已结束,已完成
                            if (data == 5) {
                                return '<span style="color:#bfbfbf;font-weight: bold;">已结束,已完成</span>';
                            }
                        }
                    },
                    {
                        data: "INITTASKNAME",
                        name: "initTaskName",
                        title: "初检任务名称",
                        visible: false,
                    },
                    {
                        data: "INITTASKTYPE",
                        name: "initTaskType",
                        title: "初检任务类型",
                        visible: false,
                        render: function (data) {
                            switch (data) {
                                case "1":
                                    data = "例行监测";
                                    break;
                                case "2":
                                    data = "专项监测";
                                    break;
                                case "3":
                                    data = "监督抽查";
                                    break;
                            }
                            return data;
                        }
                    },
                     {
                        data: "ID",
                        title: "报告",
                        orderable: false,
                        render: function(data, type, row) {
                            var butt =
                                '<a style="cursor:pointer" onclick="queryInfo(\'' +
                                data +
                                "')\">详情</a>";
                            butt +=
                                ' <a style="cursor:pointer" onclick="queryReports(\'' + data + '\', \''+ row.RECHECKUNITNAME + '\')">报告</a>';
                            return butt;
                        }
                    }
                ]
            });
        });
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
            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-qry-web/monitorTask/exportReCKList");
            //提交数据
            var token = window.localStorage.getItem("token");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "token");
            input1.attr("value", token);
            var recheckTaskName = $("input[name='recheckTaskName']").val();
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "recheckTaskName");
            input2.attr("value", recheckTaskName);
            var recheckTaskYear = $("input[name='queryYear']").val();
            var input3 = $("<input>");
            input3.attr("type", "hidden");
            input3.attr("name", "recheckTaskYear");
            input3.attr("value", recheckTaskYear);
            var state = $("select[name='state']").val();
            var input4 = $("<input>");
            input4.attr("type", "hidden");
            input4.attr("name", "state");
            input4.attr("value", state);
            var dateBegin = $("input[name='datebegin']").val();
            var input5 = $("<input>");
            input5.attr("type", "hidden");
            input5.attr("name", "dateBegin");
            input5.attr("value", dateBegin);
            var dateEnd = $("input[name='dateend']").val();
            var input6 = $("<input>");
            input6.attr("type", "hidden");
            input6.attr("name", "dateEnd");
            input6.attr("value", dateEnd);

            var dateCreateBegin = $("input[name='dateCreateBegin']").val();
            var input7 = $("<input>");
            input7.attr("type", "hidden");
            input7.attr("name", "dateCreateBegin");
            input7.attr("value", dateCreateBegin);

            var dateCreateEnd = $("input[name='dateCreateEnd']").val();
            var input8 = $("<input>");
            input8.attr("type", "hidden");
            input8.attr("name", "dateCreateEnd");
            input8.attr("value", dateCreateEnd);

            var initTaskName = formatParams($scope.initTaskName);
            var input9 = $("<input>");
            input9.attr("type", "hidden");
            input9.attr("name", "initTaskName");
            input9.attr("value", initTaskName);

            var initTaskTypeChecked = [];
            $(".initTaskType:checked").map(function() {
                initTaskTypeChecked.push($(this).val());
            });
            var initTaskType = initTaskTypeChecked.join(",");
            var input10 = $("<input>");
            input10.attr("type", "hidden");
            input10.attr("name", "initTaskType");
            input10.attr("value", initTaskType);

            var createOrgName = formatParams($scope.createOrgName);
            var input11 = $("<input>");
            input11.attr("type", "hidden");
            input11.attr("name", "createOrgName");
            input11.attr("value", createOrgName);

            var recheckUnitName = formatParams($scope.recheckUnitName);
            var input12 = $("<input>");
            input12.attr("type", "hidden");
            input12.attr("name", "recheckUnitName");
            input12.attr("value", recheckUnitName);

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
            form.submit();
        };

        /**
         * 任务操作
         * */
        //查看详情
        $("#TaskInfoModel").modal({
            keyboard: true,
            show: false
        });
        window.queryInfo = function(o) {
            $scope.reCheckTask = {};
            $("#TaskInfoModel").modal("show");
            //基本信息
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/monitorTask/getReTaskById",
                method: "post",
                data: { id: o }
            })
                .success(function(json) {
                    $scope.reCheckTask = json.data;
                    $scope.reCheckTask.recheckTaskBegin = new Date(
                        parseInt(json.data.recheckTaskBegin) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    $scope.reCheckTask.recheckTaskEnd = new Date(
                        parseInt(json.data.recheckTaskEnd) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    if ($scope.reCheckTask.initTaskType == "1") {
                        $scope.reCheckTask.initTaskType = "例行监测";
                    }
                    if ($scope.reCheckTask.initTaskType == "2") {
                        $scope.reCheckTask.initTaskType = "专项检测";
                    }
                    if ($scope.reCheckTask.initTaskType == "3") {
                        $scope.reCheckTask.initTaskType = "监督抽查";
                    }
                })
                .error(function(data) {
                    
                });
            //关联信息获取
            $("#Fjtable tbody").html("");
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/monitorTask/getReObjById",
                method: "post",
                data: { id: o }
            })
                .success(function(obj) {
                    var datas = obj.data;
                    if (datas.length > 0) {
                        for (var i = 1; i <= datas.length; i++) {
                            var td = datas[i - 1];
                            var Row =
                                "<tr><td>" +
                                td.RECHECKSAMPLENAME +
                                "</td> <td>" +
                                td.RECHECKSAMPLECODE +
                                "</td><td>" +
                                td.TESTEDDEPARMENT +
                                "</td></tr>";
                            if ($("#Fjtable tbody").children().length == 0) {
                                $("#Fjtable tbody").prepend(Row);
                            } else {
                                $("#Fjtable tbody tr:last").after(Row);
                            }
                        }
                    }
                })
                .error(function(data) {
                    
                });
        };

        //查看报告
        window.queryReports = function(obj, jcUnitName) {
            window.localStorage.removeItem("asms_Check_checkTask_forReport");
            window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "FJRW");
            window.localStorage.setItem("asms_Check_Task_ReportList_SampleInfoList_jcUnitName", jcUnitName);
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryTaskReportList"
            );
        };

        $scope.cleanCookie = function() {
            window.localStorage.removeItem("asms_Check_recheckTask");
        };

        //表格刷新
        $scope.TableReload = function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        //查询条件重置
        $scope.cleanQueryPar = function($event) {
            $("#queryForm").form("reset");
            //重置 二次查询
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
            $("input[name='datebegin']").val("");
            $("input[name='dateend']").val("");
            $("input[name='queryYear']").val("");
            $("input[name='recheckTaskName']").val("");
            $("select[name='state']").val("");
            $scope.initTaskName = null;
            $scope.initTaskType = null;
            $scope.createOrgName = null;
            $scope.recheckUnitName = null;
            $("input[name='initTaskTypeLimit']").prop("checked", true);
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
        //初始隐藏
        $("#FJ_DX").hide(); //复检对象
        //页签操作
        $scope.myTab = function(o) {
            $("#JB_XX").hide(); //基本信息
            $("#FJ_DX").hide(); //复检对象
            if (o == "基本信息") {
                $("#JB_XX").show(); //新任务
            }
            if (o == "复检对象") {
                $("#FJ_DX").show(); //历史任务
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
            url: "/sofn-qry-web/monitorTask/getReCheckTaskStatis",
            grid: [1,1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
