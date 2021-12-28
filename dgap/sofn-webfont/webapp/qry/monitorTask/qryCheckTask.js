var myapp = angular
    .module("myapp", [])
    .controller("qryCheckTask", function($scope, $http, $state,$timeout) {
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
        $scope.orgLevel = "0";
        /**
         * 根据用户权限判断部或省级
         * */
        $http({
            url: "/sofn-qry-web/subject/findCurrentUserByToken",
            method: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            }
        })
            .success(function(data) {
                $scope.orgLevel = data.user.organization.orgLevel; //机构级别
                $scope.regionId = data.user.organization.regionId; //区域ID
                if ($scope.orgLevel != 4) {
                    $scope.region = $scope.regionId.substr(0, 2 * ($scope.orgLevel - 1));
                }else {
                    $scope.region = $scope.regionId;
                }
                if ("1" == $scope.orgLevel) {
                    //部级
                    $("#BjAddTaskBut").show();
                    $("#BjupdTaskBut").show();
                    $("#relBut").show();
                    $("#aboBut").show();
                    $("#expBut").show();
                    $.fn.mycity("querySheng", "queryShi", "queryXian");
                } else {
                    //省级 市级 县级
                    $("#SjAddTaskBut").show();
                    $("#SjupdTaskBut").show();
                    $("#relBut").show();
                    $("#aboBut").show();
                    $("#expBut").show();
                    $("#areaSpan").show();
                    $("#queryShengDiv").show();
                    $("#queryShiDiv").show();
                    $("#queryXianDiv").show();
                    $("#listAreaSpan").show();
                    $.fn.mycity(
                        "querySheng",
                        "queryShi",
                        "queryXian",
                        $scope.region
                    );
                    if (
                        $("#querySheng").val() != null &&
                        $("#querySheng").val() != "" &&
                        $("#querySheng").val() != undefined
                    ) {
                        $("#querySheng").attr("disabled", true);
                    }
                    if (
                        $("#queryShi").val() != null &&
                        $("#queryShi").val() != "" &&
                        $("#queryShi").val() != undefined
                    ) {
                        $("#queryShi").attr("disabled", true);
                    }
                    if (
                        $("#queryXian").val() != null &&
                        $("#queryXian").val() != "" &&
                        $("#queryXian").val() != undefined
                    ) {
                        $("#queryXian").attr("disabled", true);
                    }
                }
                $scope.loadGrid();
            })
            .error(function(data) {
                
            });

        function formatParams(str) {
            return str == undefined ? "" : str;
        }
        $scope.loadGrid = function() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[9, "desc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url: "/sofn-qry-web/monitorTask/getCheckTaskList_v2",
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
                                json.data.list[i].TASKBEGINTIME != null &&
                                json.data.list[i].TASKBEGINTIME != "" &&
                                json.data.list[i].TASKBEGINTIME != undefined
                            ) {
                                json.data.list[i].TASKBEGINTIME = new Date(
                                    parseInt(json.data.list[i].TASKBEGINTIME) +
                                        28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].TASKENDTIME != null &&
                                json.data.list[i].TASKENDTIME != "" &&
                                json.data.list[i].TASKENDTIME != undefined
                            ) {
                                json.data.list[i].TASKENDTIME = new Date(
                                    parseInt(json.data.list[i].TASKENDTIME) +
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
                        //省
                        params.querySheng = $("#querySheng").val();
                        params.queryShi = $("#queryShi").val(); //市
                        params.queryXian = $("#queryXian").val(); //县
                        params.taskLevel = $scope.orgLevel;
                        params.taskName = $("input[name='taskName']").val();
                        params.state = $("select[name='state']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.dateCreateBegin = $(
                            "input[name='dateCreateBegin']"
                        ).val();
                        params.dateCreateEnd = $(
                            "input[name='dateCreateEnd']"
                        ).val();
                        params.taskYear = formatParams($scope.taskYear);
                        params.taskReleaseUnit = formatParams(
                            $scope.taskReleaseUnit
                        );
                        params.cyzzUnitName = formatParams($scope.cyzzUnitName);
                        params.jcUnitName = formatParams($scope.jcUnitName);
                        params.cyUnitName = formatParams($scope.cyUnitName);
                        params.fileNumber = formatParams($scope.fileNumber);
                        params.detectionStandard = formatParams(
                            $scope.detectionStandard
                        );
                        params.judgeStandard = formatParams(
                            $scope.judgeStandard
                        );
                        params.jdUnitName = formatParams($scope.jdUnitName);

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
                        data: "TASKLEVEL",
                        title: "级别",
                        render: function(data, type, row) {
                            if (data == 0) {
                                return "<B>部级</B>";
                            } else {
                                return "<B>省级</B>";
                            }
                        },
                        width: "4%"
                    },
                    {
                        data: "TASKNAME",
                        title: "任务名称",
                        width: "30%",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 30
                            ) {
                                return (
                                    "<a id='TASKNAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('TASKNAME" +
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
                        data: "TASKTYPE",
                        title: "监测类型",
                        render: function(data, type, row) {
                            return "监督抽查";
                        },
                        width: "6%"
                    },
                    {
                        data: "TASKYEAR",
                        title: "年度",
                        width: "5%"
                    },
                    {
                        data: "CREATEORGNAME",
                        title: "任务发布单位",
                        width: "10%"
                    },
                    {
                        data: "TASKBEGINTIME",
                        title: "开始时间",
                        width: "7%"
                    },
                    {
                        data: "TASKENDTIME",
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
                        width: "8%",
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
                        data: "JDUINTNAME",
                        name: "jdUnitName",
                        title: "监督执法单位",
                        visible: false,
                    },
                    {
                        data: "JCUNITNAME",
                        name: "jcUnitName",
                        title: "检测单位",
                        visible: false,
                    },
                    {
                        data: "JCUNITNAME",
                        name: "jcUnitName",
                        title: "检测单位",
                        visible: false,
                    },
                    {
                        data: "TASKRELEASEUNIT",
                        name: "cyzzUnitName",
                        title: "抽样组织单位",
                        visible: false,
                    },
                    {
                        data: "CYUNITNAME",
                        name: "cyUnitName",
                        title: "抽样单位",
                        visible: false,
                    },
                    {
                        data: "FILENUMBER",
                        name: "fileNumber",
                        title: "文件号",
                        visible: false,
                    },
                    {
                        data: "DETECTIONSTANDARD",
                        name: "detectionStandard",
                        title: "检测标准",
                        visible: false,
                        orderable: false,
                    },
                    {
                        data: "JUDGESTANDARD",
                        name: "judgeStandard",
                        title: "判断标准",
                        visible: false,
                        orderable: false,
                    },
                    {
                        data: "ID",
                        title: "报告",
                        orderable: false,
                        render: function(data, type, row) {
                            var butt =
                                '<a style="cursor:pointer" onclick="queryInfo(\'' +
                                data +
                                '\')" class="color">详情</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="queryReports(\'' +
                                data +
                                '\')" class="color">报告</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="querySamples(\'' +
                                data +
                                '\')" class="color">抽样单</a>';
                            return butt;
                        },
                        width: "10%"
                    }
                ]
            });
        };

        //数据导出
        $scope.exportData = function() {
            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-qry-web/monitorTask/exportCKList");
            //提交数据
            var token = window.localStorage.getItem("token");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "token");
            input1.attr("value", token);
            var taskName = $("input[name='taskName']").val();
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "taskName");
            input2.attr("value", taskName);
            var state = $("select[name='state']").val();
            var input3 = $("<input>");
            input3.attr("type", "hidden");
            input3.attr("name", "state");
            input3.attr("value", state);
            var dateBegin = $("input[name='datebegin']").val();
            var input4 = $("<input>");
            input4.attr("type", "hidden");
            input4.attr("name", "dateBegin");
            input4.attr("value", dateBegin);
            var dateEnd = $("input[name='dateend']").val();
            var input5 = $("<input>");
            input5.attr("type", "hidden");
            input5.attr("name", "dateEnd");
            input5.attr("value", dateEnd);
            var taskLevel = $scope.orgLevel;
            var input6 = $("<input>");
            input6.attr("type", "hidden");
            input6.attr("name", "taskLevel");
            input6.attr("value", taskLevel);
            var querySheng = $("#querySheng").val(); //省
            var input7 = $("<input>");
            input7.attr("type", "hidden");
            input7.attr("name", "querySheng");
            input7.attr("value", querySheng);
            var queryShi = $("#queryShi").val(); //市
            var input8 = $("<input>");
            input8.attr("type", "hidden");
            input8.attr("name", "queryShi");
            input8.attr("value", queryShi);
            var queryXian = $("#queryXian").val(); //县
            var input9 = $("<input>");
            input9.attr("type", "hidden");
            input9.attr("name", "queryXian");
            input9.attr("value", queryXian);

            var dateCreateBegin = $("input[name='dateCreateBegin']").val();
            var input10 = $("<input>");
            input10.attr("type", "hidden");
            input10.attr("name", "dateCreateBegin");
            input10.attr("value", dateCreateBegin);

            var dateCreateEnd = $("input[name='dateCreateEnd']").val();
            var input11 = $("<input>");
            input11.attr("type", "hidden");
            input11.attr("name", "dateCreateEnd");
            input11.attr("value", dateCreateEnd);

            var taskYear = formatParams($scope.taskYear);
            var input12 = $("<input>");
            input12.attr("type", "hidden");
            input12.attr("name", "taskYear");
            input12.attr("value", taskYear);

            var taskReleaseUnit = formatParams($scope.taskReleaseUnit);
            var input13 = $("<input>");
            input13.attr("type", "hidden");
            input13.attr("name", "taskReleaseUnit");
            input13.attr("value", taskReleaseUnit);

            var cyzzUnitName = formatParams($scope.cyzzUnitName);
            var input14 = $("<input>");
            input14.attr("type", "hidden");
            input14.attr("name", "cyzzUnitName");
            input14.attr("value", cyzzUnitName);

            var jcUnitName = formatParams($scope.jcUnitName);
            var input15 = $("<input>");
            input15.attr("type", "hidden");
            input15.attr("name", "jcUnitName");
            input15.attr("value", jcUnitName);

            var cyUnitName = formatParams($scope.cyUnitName);
            var input16 = $("<input>");
            input16.attr("type", "hidden");
            input16.attr("name", "cyUnitName");
            input16.attr("value", cyUnitName);

            var fileNumber = formatParams($scope.fileNumber);
            var input17 = $("<input>");
            input17.attr("type", "hidden");
            input17.attr("name", "fileNumber");
            input17.attr("value", fileNumber);

            var detectionStandard = formatParams($scope.detectionStandard);
            var input18 = $("<input>");
            input18.attr("type", "hidden");
            input18.attr("name", "detectionStandard");
            input18.attr("value", detectionStandard);

            var judgeStandard = formatParams($scope.judgeStandard);
            var input19 = $("<input>");
            input19.attr("type", "hidden");
            input19.attr("name", "judgeStandard");
            input19.attr("value", judgeStandard);

            var jdUnitName = formatParams($scope.jdUnitName);
            var input20 = $("<input>");
            input20.attr("type", "hidden");
            input20.attr("name", "jdUnitName");
            input20.attr("value", jdUnitName);
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
            $scope.checkTask = {};
            $("#TaskInfoModel").modal("show");
            //基本信息
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/monitorTask/getTaskById",
                method: "post",
                data: { id: o }
            })
                .success(function(obj) {
                    $scope.checkTask = obj.data;
                    $scope.checkTask.taskBeginTime = new Date(
                        parseInt(obj.data.taskBeginTime) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    $scope.checkTask.taskEndTime = new Date(
                        parseInt(obj.data.taskEndTime) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    if ($scope.checkTask.taskLevel == "1") {
                        //省级
                        if ($scope.checkTask.parentTaskName == null) {
                            $scope.checkTask.parentTaskName = "无";
                        }
                        $("#myTab").show();
                        $("div[name='cyUnit']").show();
                        $("div[name='jcUnit']").show();
                        $("div[name='parentTaskName']").show(); //上级任务
                        showJcInfoForSj(o); //监测信息
                        
                    } else {
                        //部级
                        $("#myTab").hide(); //隐藏监测信息页签
                        $("div[name='cdUnit']").show();
                    }
                    //承担单位
                    $scope.checkTask.cdUnits = "";
                    if (obj.speList.length != 0) {
                        for (var i = 0; i < obj.speList.length; i++) {
                            if ($scope.checkTask.cdUnits == "") {
                                $scope.checkTask.cdUnits +=
                                    obj.speList[i].leadUnitName;
                            } else {
                                $scope.checkTask.cdUnits +=
                                    "," + obj.speList[i].leadUnitName;
                            }
                        }
                    } else {
                        $scope.checkTask.cdUnits = "无";
                    }
                })
                .error(function(data) {
                    
                });
        };

        //查看报告
        window.queryReports = function(obj) {
            window.localStorage.removeItem("asms_Check_checkTask_forReport");
            window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "JDCC");
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryCheckTaskReportList"
            );
        };

        //查看抽样单
        window.querySamples = function(obj) {
            window.localStorage.removeItem("asms_Check_checkTask_forReport");
            window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "JDCC");
            $state.go(
                "index.content.qry/monitorTask/taskSample/qryTaskSampleList"
            );
        };

        $scope.cleanCookie = function() {
            window.localStorage.removeItem("asms_Check_BjTask");
            window.localStorage.removeItem("asms_checktask_taskinfo_taskid");
            window.localStorage.removeItem("inspectionIdForCheckTask");
        };

        //表格刷新
        $scope.TableReload = function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        //附件下载
        window.downloadFileName = function() {
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-sys-web/sysTemplate/dowload");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileUrl");
            input1.attr("value", $scope.checkTask.attachmentAddress);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "fileName");
            input2.attr("value", $scope.checkTask.attachmentName);
            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
        };
        //初始隐藏
        $("#FJ_DX").hide(); //监测信息
        //页签操作
        $scope.myTab = function(o) {
            $("#JB_XX").hide(); //基本信息
            $("#FJ_DX").hide(); //监测信息
            if (o == "基本信息") {
                $("#JB_XX").show(); //基本信息
            }
            if (o == "监测信息") {
                $("#FJ_DX").show(); //监测信息
            }
        };

        //显示监测信息
        window.showJcInfoForSj = function(o) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/monitorTask/getObjById",
                method: "post",
                data: { id: o }
            })
                .success(function(obj) {
                    $("#Fjtable tbody").html("");
                    var datas = obj.data;
                    if (datas.length > 0) {
                        for (var i = 1; i <= datas.length; i++) {
                            var td = datas[i - 1];
                            var Row =
                                "<tr><td>" +
                                td.productType +
                                "<td>" +
                                td.productName +
                                "</td> <td>" +
                                td.monitorNum +
                                "</td><td>" +
                                $.fn.Cityname(td.areaId) +
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
        //重置区域
        $scope.reloadArea = function($event) {
            $("#queryForm").form("reset");
            //重置 二次查询
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
            if ($scope.orgLevel != "1") {
                $.fn.mycity(
                    "querySheng",
                    "queryShi",
                    "queryXian",
                    $scope.region
                );
                if (
                    $("#querySheng").val() != null &&
                    $("#querySheng").val() != "" &&
                    $("#querySheng").val() != undefined
                ) {
                    $("#querySheng").attr("disabled", true);
                }
                if (
                    $("#queryShi").val() != null &&
                    $("#queryShi").val() != "" &&
                    $("#queryShi").val() != undefined
                ) {
                    $("#queryShi").attr("disabled", true);
                }
                if (
                    $("#queryXian").val() != null &&
                    $("#queryXian").val() != "" &&
                    $("#queryXian").val() != undefined
                ) {
                    $("#queryXian").attr("disabled", true);
                }
            } else {
                $.fn.mycity("querySheng", "queryShi", "queryXian");
            }
            $scope.taskYear = null;
            $scope.taskReleaseUnit = null;
            $scope.cyzzUnitName = null;
            $scope.taskReleaseUnit = null;
            $scope.jcUnitName = null;
            $scope.cyUnitName = null;
            $scope.fileNumber = null;
            $scope.detectionStandard = null;
            $scope.judgeStandard = null;
            $scope.jdUnitName = null;
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
            url: "/sofn-qry-web/monitorTask/getCheckTaskStatis",
            grid: [1,1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
