var myapp = angular
    .module("myapp", [])
    .controller("qrySpecialMonitor", function($scope, $http, $state,$timeout) {
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


        $scope.reloadArea = function () {
            $("#queryForm").form("reset");
            $scope.smFileNum = null;
            if ($scope.organization.orgLevel != "1") {
                $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
                if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                    $("#sheng").attr("disabled", true);
                }
                if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                    $("#shi").attr("disabled", true);
                }
                if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                    $("#xian").attr("disabled", true);
                }
            } else {
                $.fn.mycity("sheng", "shi", "xian");
            }
        };


        $scope.getAreaBySelect = function() {
            var province = $("#sheng").val();
            var city = $("#shi").val();
            var county = $("#xian").val();
            var areaId = "";
            if (county != null && county != undefined && county != "") {
                areaId = county;
            } else if (city != null && city != undefined && city != "") {
                areaId = city;
            } else if (province != null && province != undefined && province != "") {
                areaId = province;
            }
            return areaId;
        };


        //获取用户信息、组织信息
        $scope.user = {};
        $scope.organization = {};
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/subject/findCurrentUserByToken",
            method: "post"
        }).success(function(data) {
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
            //所属行业默认不限
            //$("input[name='entityIndustryLimit']:first").attr("checked", true);
            // loadGrid();
        }).error(function(data){
            
        });

        //查询已有专项监测任务的发布机构
        $scope.createOrgs = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/monitorTask/getSpecialRmOrgList",
            data: "",
            method: "post"
        })
            .success(function(data) {
                $scope.createOrgs = data.data;
            })
            .error(function(data) {
                
            });

        //查询已有专项监测任务的牵头单位
        $scope.routineLeadUnits = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/monitorTask/getSpecialRmLeadUnitList",
            data: "",
            method: "post"
        })
            .success(function(data) {
                $scope.routineLeadUnits = data.data;
            })
            .error(function(data) {
                
            });
        function formatParams(str) {
            return str == undefined ? "" : str;
        }

        $(function() {
            $("#grid").dataTable({
                ordering: true, //开启排序
                order: [[10, "desc"]], //默认排序项
                scrollX: true, //横向滚动
                ajax: {
                    url: "/sofn-qry-web/monitorTask/getSpecialMonitorList_v2",
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
                                json.data.list[i].SMDATEBEGIN != null &&
                                json.data.list[i].SMDATEBEGIN != "" &&
                                json.data.list[i].SMDATEBEGIN != undefined
                            ) {
                                json.data.list[i].SMDATEBEGIN = new Date(
                                    parseInt(json.data.list[i].SMDATEBEGIN) +
                                        28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            }
                            if (
                                json.data.list[i].SMDATEEND != null &&
                                json.data.list[i].SMDATEEND != "" &&
                                json.data.list[i].SMDATEEND != undefined
                            ) {
                                json.data.list[i].SMDATEEND = new Date(
                                    parseInt(json.data.list[i].SMDATEEND) +
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
                        //牵头单位
                        params.leadUnitName = $(
                            "select[name='routineLeadUnit']"
                        ).val();
                        //发布机构
                        params.createOrgId = $(
                            "select[name='createOrg']"
                        ).val();
                        params.smName = $("input[name='smName']").val();
                        params.smState = $("select[name='smState']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.dateCreateBegin = $(
                            "input[name='dateCreateBegin']"
                        ).val();
                        params.dateCreateEnd = $(
                            "input[name='dateCreateEnd']"
                        ).val();
                        params.smYear = $("input[name='queryYear']").val();
                        params.smFileNum = formatParams($scope.smFileNum);
                        params.areaId = $scope.getAreaBySelect();
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
                        data: "SMNAME",
                        title: "任务名称",
                        width: "18%",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 20
                            ) {
                                return (
                                    "<a id='SMNAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('SMNAME" +
                                    row.ID +
                                    "','" +
                                    data +
                                    "')>" +
                                    data.substring(0, 35) +
                                    "...</a>"
                                );
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: "SMTYPE",
                        title: "监测类型",
                        width: "6%"
                    },
                    {
                        data: "SMYEAR",
                        title: "年度",
                        width: "4%"
                    },
                    {
                        data: "SMBATCH",
                        title: "监测批次",
                        width: "6%"
                    },
                    {
                        data: "SMFILENUM",
                        title: "文件号",
                        width: "10%",
                        render: function(data, type, row) {
                            if (
                                data != null &&
                                data != undefined &&
                                data.length > 24
                            ) {
                                return (
                                    "<a id='SMFILENUM" +
                                    row.ID +
                                    "' onmouseover=showPopContent('SMFILENUM" +
                                    row.ID +
                                    "','" +
                                    data +
                                    "')>" +
                                    data.substring(0, 24) +
                                    "...</a>"
                                );
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        data: "CREATEORGNAME",
                        title: "任务发布单位",
                        width: "10%"
                    },
                    {
                        data: "SMDATEBEGIN",
                        title: "开始时间",
                        width: "7%"
                    },
                    {
                        data: "SMDATEEND",
                        title: "结束时间",
                        width: "7%"
                    },
                    {
                        data: "CREATETIME",
                        title: "创建时间",
                        width: "7%"
                    },
                    {
                        data: "SMSTATE",
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
                        data: "LEADUNITNAME",
                        name: "leadUnitId",
                        title: "牵头单位",
                        visible: false,
                        orderable: false
                    },
                    {
                        data: "ID",
                        title: "操作",
                        orderable: false,
                        render: function(data, type, row) {
                            var butt =
                                '<a style="cursor:pointer" onclick="queryInfo(\'' +
                                data +
                                '\')" class="color">详情</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="querySamples(\'' +
                                data +
                                '\')" class="color">抽样单</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="queryReportsForQtUnit(\'' +
                                data +
                                '\')" class="color">牵头单位报告</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="queryReportsForCdUnit(\'' +
                                data +
                                '\')" class="color">承担单位报告</a>';
                            butt +=
                                ' <a style="cursor:pointer" onclick="queryAsmsTaskCondition(\'' +
                                data +
                                "', '" +
                                row.SMNAME +
                                '\')" class="color">任务完成情况</a>';
                            return butt;
                        },
                        width: "15%"
                    }
                ]
            });
        });

        //查看牵头单位报告
        window.queryReportsForQtUnit = function(obj) {
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "ZXJC");
            window.localStorage.setItem("takeTheLeadReporListTaskId", obj);
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryTakeTheLeadReporList"
            );
        };

        //查看承担单位报告
        window.queryReportsForCdUnit = function(obj) {
            window.localStorage.removeItem("asms_Check_checkTask_forReport");
            window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "ZXJC");
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryTaskReportList"
            );
        };

        //查看任务执行情况
        window.queryAsmsTaskCondition = function(
            asmsMonitorTaskId,
            asmsMonitorTaskName
        ) {
            window.localStorage.removeItem("asmsMonitorTaskId");
            window.localStorage.setItem("asmsMonitorTaskId", asmsMonitorTaskId);
            window.localStorage.removeItem("asmsMonitorTaskName");
            window.localStorage.setItem(
                "asmsMonitorTaskName",
                asmsMonitorTaskName
            );
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "ZXJC");
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryAsmsTaskCondition"
            );
        };

        //数据导出
        $scope.exportData = function() {
            //提交数据
            //form
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-qry-web/monitorTask/exportSmList");
            var token = window.localStorage.getItem("token");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "token");
            input1.attr("value", token);
            //牵头单位
            var leadUnitName = $("select[name='routineLeadUnit']").val();
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "leadUnitName");
            input2.attr("value", leadUnitName);
            //发布机构
            var createOrgId = $("select[name='createOrg']").val();
            var input3 = $("<input>");
            input3.attr("type", "hidden");
            input3.attr("name", "createOrgId");
            input3.attr("value", createOrgId);
            var smName = $("input[name='smName']").val();
            var input4 = $("<input>");
            input4.attr("type", "hidden");
            input4.attr("name", "smName");
            input4.attr("value", smName);
            var smState = $("select[name='smState']").val();
            var input5 = $("<input>");
            input5.attr("type", "hidden");
            input5.attr("name", "smState");
            input5.attr("value", smState);
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
            var smYear = $("input[name='queryYear']").val();
            var input8 = $("<input>");
            input8.attr("type", "hidden");
            input8.attr("name", "smYear");
            input8.attr("value", smYear);
            var dateCreateBegin = $("input[name='dateCreateBegin']").val();
            var input9 = $("<input>");
            input9.attr("type", "hidden");
            input9.attr("name", "dateCreateBegin");
            input9.attr("value", dateCreateBegin);
            var dateCreateEnd = $("input[name='dateCreateEnd']").val();
            var input10 = $("<input>");
            input10.attr("type", "hidden");
            input10.attr("name", "dateCreateEnd");
            input10.attr("value", dateCreateEnd);

            var smFileNum = formatParams($scope.smFileNum);
            var input11 = $("<input>");
            input11.attr("type", "hidden");
            input11.attr("name", "smFileNum");
            input11.attr("value", smFileNum);

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

            var areaId = $scope.getAreaBySelect();
            var input13 = $('<input>');
            input13.attr('type', 'hidden');
            input13.attr('name', 'areaId');
            input13.attr('value', areaId);
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
        //查看抽样单
        window.querySamples = function(obj) {
            window.localStorage.removeItem("asms_Check_checkTask_forReport");
            window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
            window.localStorage.removeItem("asms_Check_Task_Type_Title");
            window.localStorage.setItem("asms_Check_Task_Type_Title", "ZXJC");
            $state.go(
                "index.content.qry/monitorTask/taskSample/qryTaskSampleList"
            );
        };

        //查看详情
        $("#TaskInfoModel").modal({
            keyboard: true,
            show: false
        });
        window.queryInfo = function(o) {
            $scope.specialMonitor = {};
            $("#TaskInfoModel").modal("show");
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/monitorTask/getSMTaskById",
                method: "post",
                data: { id: o }
            })
                .success(function(json) {
                    console.info(json.data);
                    $scope.specialMonitor = json.data;
                    $scope.specialMonitor.smDateBegin = new Date(
                        parseInt(json.data.smDateBegin) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    $scope.specialMonitor.smDateEnd = new Date(
                        parseInt(json.data.smDateEnd) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    //牵头单位
                    $scope.specialMonitor.qtName = "";
                    for (var i = 0; i < json.speList.length; i++) {
                        if ($scope.specialMonitor.qtName == "") {
                            $scope.specialMonitor.qtName +=
                                json.speList[i].leadUnitName;
                        } else {
                            $scope.specialMonitor.qtName +=
                                "," + json.speList[i].leadUnitName;
                        }
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
            window.localStorage.setItem("asms_Check_Task_Type_Title", "ZXJC");
            $state.go(
                "index.content.qry/monitorTask/taskReport/qryTaskReportList"
            );
        };

        $scope.resetQuery = function($event) {
            $scope.reloadArea();
            //重置 二次查询
            if ($event) {
                $($event.currentTarget)
                    .prev()
                    .text("查询");
            }
        };

        //表格刷新
        $scope.TableReload = function() {
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
        };

        //提示条初始化
        //$("[data-toggle='tooltip']").tooltip();

        //附件下载
        window.downloadFileName = function() {
            var form = $("<form>");
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "/sofn-qry-web/qryFile/downloadFile");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileUrl");
            input1.attr("value", $scope.specialMonitor.smFile);
            var input2 = $("<input>");
            input2.attr("type", "hidden");
            input2.attr("name", "fileName");
            input2.attr("value", $scope.specialMonitor.smFileName);
            $("body").append(form);
            form.append(input1);
            form.append(input2);
            form.submit();
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
            url: "/sofn-qry-web/monitorTask/getSpecialMonitorStatis",
            grid: [1,1], //水平宽度比例
            //业务参数
            data: function() {
                return $scope.chartParams;
            }
        };
    });
