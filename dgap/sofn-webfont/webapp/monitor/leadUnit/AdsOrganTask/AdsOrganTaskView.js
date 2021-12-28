var myapp = angular.module("myapp", []);
myapp.controller("AdsOrganTaskView", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for (var i = 0; i < a.length; i++) {
        if (a[i].menuName == "项目完成情况") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")

    $scope.monitorClass = ["例行监测", "专项监测","监督抽查"];
    $scope.year = [];

    var monitorTaskId = "";
    var orgType = "";
    var organTaskId = "";
    var organName = "";
    var organId = "";
    var receiptTime = "";
    var finishNum = 0;

    var myDate = new Date();
    var startDate = myDate.getFullYear() - 7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for (var i = startDate; i <= endDate; i++) {
        $scope.year.push(i);
    }
    //获取测试信息
    /**
     * 分页展示数据
     */
    $(function () {

        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsOrganTask/getPageInfoByMonitorTaskID",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    //params.publishStatus= '1';
                    params.is_history = "Y";
                    params.monitorClass = $('#monitorClass option:selected').val();
                    params.year = $('#year option:selected').val();
                    params.monitorTaskId = monitorTaskId;
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data: "monitorClass",
                    title: "任务类型"
                },
                {
                    data: "sampleOrgan",
                    title: "机构名称"
                },
                {
                    data: "numbers",
                    title: "抽样数"
                },
                {
                    data: "sampleFinishNum",
                    title: "抽样完成数"
                },
                {
                    data: "sampleReportStatus",
                    title: "抽样上报状态",
                    render: function (data, type, row) {
                        if ("0" == data) {
                            return "未上报";
                        } else if ("1" == data) {
                            return "上报中";
                        } else if ("2" == data) {
                            return "已上报";
                        } else if ("3" == data) {
                            return "已退回";
                        } else if ("4" == data) {
                            return "部分退回";
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "sampleReportTime",
                    title: "抽样上报时间"
                },
                {
                    data: "ID",
                    title: "抽样操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        if (2 == row.sampleReportStatus || 4 == row.sampleReportStatus) {
                            var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="sampleBack(\'' + row.id + "\',\'" + row.sampleOrgan + "\',\'" + row.sampleFinishNum + "\',\'" + row.sampleReportTime + "\',\'" + row.sampleOrganId + '\')">查看</a>';
                            return look_a;
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "checkFinishNum",
                    title: "检测完成数"
                },
                {
                    data: "checkReportStatus",
                    title: "检测上报状态",
                    render: function (data, type, row) {
                        if ("0" == data) {
                            return "未上报";
                        } else if ("1" == data) {
                            return "上报中";
                        } else if ("2" == data) {
                            return "已上报";
                        } else if ("3" == data) {
                            return "已退回";
                        } else if ("4" == data) {
                            return "部分退回";
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "checkReportTime",
                    title: "检测上报时间"
                },
                {
                    data: "ID",
                    title: "检测操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        if (2 == row.checkReportStatus || 4 == row.checkReportStatus) {
                            var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="checkBack(\'' + row.id + "\',\'" + row.sampleOrgan + "\',\'" + row.checkFinishNum + "\',\'" + row.checkReportTime + "\',\'" + row.detectionOrganId + '\') ">查看</a>';
                            return look_a;
                        } else {
                            return "";
                        }
                    }
                }
            ],
        });
    });

    //清除缓存
    $scope.removeLocalStorage = function () {
        window.localStorage.removeItem("id");
    };

    //根据不同条件选择框进行改变
    $(document).ready(function () {
        $('#monitorClass').change(function () {
            $("#grid").dataTable().api().ajax.reload();
        });
        $('#year').change(function () {
            $("#grid").dataTable().api().ajax.reload();
        });
    });

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.selectTaskName = function () {
        $("#taskNameModal").modal("show");
        setTimeout(function () {
            if (selectTaskNameFlag == 0) {
                loadTaskName();
            } else {
                $("#taskNameGrid").dataTable().api().ajax.reload();
            }
        }, 300)
    };

    /**
     * 重新任务名称数据
     */
    $("#seachTaskName").click(function () {
        $("#taskNameGrid").dataTable().api().ajax.reload();
    });


    /**
     * 加载任务名称数据
     */
    function loadTaskName() {
        //bootstrap表格
        $("#taskNameGrid").dataTable({
            ajax: {
                url: "/sofn-ads-web/adsMonitorTask/getPageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    //params.publishStatus = 1;
                    params.is_history = "Y";
                    params.taskName = $("input[name='sTaskName']").val();
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data: "taskName",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn btn-sm" onclick="checkTaskName(\'' + row.taskName + "\',\'" + row.id + "\',\'" + row.publishStatus + '\')">选择</button>';
                        return look_a;
                    }
                },
            ],
        });
        selectTaskNameFlag++;
    }

    /**
     * 选择任务名称
     * @param row
     */
    window.checkTaskName = function (taskName, id, publishStatus) {


        $("input[name='taskName']").val(taskName);
        monitorTaskId = id;
        $("#taskNameModal").modal("hide");

        //增加显示任务状态
        var STATUS = new Array('未发布', '已发布', '执行中', '已终止', '已结束,未完成', '已废止');

        $("#TaskStatusValue").text(STATUS[publishStatus]);
        $('#taskStatusStatus').show();

        $("#grid").dataTable().api().ajax.reload();
    };

    var sampleBackFlag = 0;

    //抽样上报退回操作
    window.sampleBack = function (taskId, sampleOrgan, sampleFinishNum, sampleReportTime, sampleOrganId) {
        organTaskId = taskId;
        orgType = "S";
        organName = sampleOrgan;
        finishNum = sampleFinishNum;
        organId = sampleOrganId;
        receiptTime = sampleReportTime;
        $("#dSampleModal").modal("show");
        setTimeout(function () {
            if (sampleBackFlag == 0) {
                loadSampleBack();
            } else {
                $("#sampleBackGrid").dataTable().api().ajax.reload();
            }
        }, 300)
    };

    //全选
    window.sampleCheckAllS = function () {
        var val = $("input[name='sampleCheckAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='sampleTask']").attr("checked", true);
        } else {
            $("input[name='sampleTask']").attr("checked", false);
        }
    };

    /**
     * 加载抽样单数据
     */
    function loadSampleBack() {
        //bootstrap表格
        $("#sampleBackGrid").dataTable({
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/getSamplePageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.total;//总个数
                    json.recordsFiltered = json.total;
                    json.pageNumber = json.pageNum;//页码
                    json.pageSize = json.pageSize;//每页个数
                    json.start = (json.pageNum - 1) * json.pageSize;//起始位置
                    json.length = json.pageSize;
                    return json.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    params.organTaskId = organTaskId;
                    params.sampleReport = "2";
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='sampleCheckAll' onclick='sampleCheckAllS()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='sampleTask' value='" + row.ID + "' attr-code='" + row.SAMPLE_CODE + "' attr-name='" + row.SAMPLE_NAME + "'>";
                        return view;
                    }
                },
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (data != null) {
                            return '<input class="gui-input" readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        } else {
                            data = "";
                            return '<input class="gui-input" readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }
                    }
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码"
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位"
                },
                {
                    data: "CITY_CODE",
                    title: "抽样地区",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.CITY_CODE != undefined) {
                            return $.fn.Cityname(row.CITY_CODE);
                        }
                    }
                }
            ],
        });
        sampleBackFlag++;
    }

    var checkBackFlag = 0;

    //检测上报退回操作
    window.checkBack = function (taskId, sampleOrgan, checkFinishNum, checkReportTime, detectionOrganId) {
        $("#modalTitle").html("检测信息");
        organTaskId = taskId;
        orgType = "D";
        organName = sampleOrgan;
        finishNum = checkFinishNum;
        organId = detectionOrganId;
        receiptTime = checkReportTime;
        $("#dCheckModal").modal("show");
        setTimeout(function () {
            if (checkBackFlag == 0) {
                loadCheckBack();
            } else {
                $("#checkBackGrid").dataTable().api().ajax.reload();
            }
        }, 300)
    };

    //全选
    window.checkCheckAllC = function () {
        var val = $("input[name='checkCheckAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='checkTask']").attr("checked", true);
        } else {
            $("input[name='checkTask']").attr("checked", false);
        }
    };

    /**
     * 加载抽样单数据
     */
    function loadCheckBack() {
        //bootstrap表格
        $("#checkBackGrid").dataTable({
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/getSamplePageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.total;//总个数
                    json.recordsFiltered = json.total;
                    json.pageNumber = json.pageNum;//页码
                    json.pageSize = json.pageSize;//每页个数
                    json.start = (json.pageNum - 1) * json.pageSize;//起始位置
                    json.length = json.pageSize;
                    return json.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    params.organTaskId = organTaskId;
                    params.checkReport = "1";
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkCheckAll' onclick='checkCheckAllC()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='checkTask' class='g' value='" + row.CHECK_ID + "' attr-code='" + row.SAMPLE_CODE + "' attr-name='" + row.SAMPLE_NAME + "'>";
                        return view;
                    }
                },
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (data == null)
                            data = "";
                        return '<input readonly="true" class="gui-input" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                    }
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码"
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位"
                },
                {
                    data: "CITY_CODE",
                    title: "抽样地区",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.CITY_CODE != undefined) {
                            return $.fn.Cityname(row.CITY_CODE);
                        }
                    }
                },
                {
                    data: "RESULT",
                    title: "检测结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("0" == data) {
                            return "不合格";
                        } else if ("1" == data) {
                            return "合格";
                        }
                    }
                }
            ],
        });
        checkBackFlag++;
    }

    //重置
    $scope.resetBtn = function () {
        $(".gui-select select").val("");
        $(".gui-input").val("");
        monitorTaskId = "";

        $('#taskStatusStatus').hide();

        $("#grid").dataTable().api().ajax.reload();
    };

    var ids = new Array();
    var names = new Array();
    var codes = new Array();
    $scope.backModel = function () {
        if (orgType == "S") {
            var check = $("input[name='sampleTask']:checked");
        } else if (orgType == "D") {
            var check = $("input[name='checkTask']:checked");
        }
        if (check.length > 0) {
            if (orgType == "S") {
                $("#dSampleModal").modal("hide");
            } else if (orgType == "D") {
                $("#dCheckModal").modal("hide");
            }
            $(check).each(function () {
                var task = $(this)[0];
                var name = $(task).attr('attr-name');
                var code = $(task).attr('attr-code');
                var id = $(task).val();
                ids.push(id);
                names.push(name);
                codes.push(code);
            });
            $('#reason').val("");
            $("#backModal").modal("show");
            var j = 0;
            var Row = "";
            Row = Row + '<table width="100%" border="0" cellpadding="2" cellspacing="0">';
            if (ids.length >= 0 && ids.length < 10) {
                j = ids.length;
            } else {
                j = 9;
            }
            for (var i = 0; i < j; i++) {
                Row = Row + '<tr><td style="text-align:left;">样品名称:' + names[i] + '</td><td style="text-align:left;">样品编号:' + codes[i] + '</td></tr>';
            }
            Row = Row + '</table>';
            $("#backTable").html(Row);
        } else {
            jBox.tip("请先选择需要退回的抽样单子", 'info');
        }
    };

    //清空所有数组
    window.clearAllSt = function () {
        ids.length = 0;
        names.length = 0;
        codes.length = 0;
    };

    //清空数组
    $scope.clear = function () {
        clearAllSt();
    };

    //清空数组并返回上一个模态框
    $scope.backClear = function () {
        clearAllSt();
        if (orgType == "S") {
            $("#dSampleModal").modal("show");
            setTimeout(function () {
                if (sampleBackFlag == 0) {
                    loadSampleBack();
                } else {
                    $("#sampleBackGrid").dataTable().api().ajax.reload();
                }
            }, 300)
        } else if (orgType == "D") {
            $("#dCheckModal").modal("show");
            setTimeout(function () {
                if (checkBackFlag == 0) {
                    loadCheckBack();
                } else {
                    $("#checkBackGrid").dataTable().api().ajax.reload();
                }
            }, 300)
        }
    };

    window.check = function (e) {
        var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        if (e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入30个汉字或者60个字母/数字");
                e.value = "";
                e.focus();
            }
        }
    }

    $scope.addAdsPecipe = function () {
        var reason = $('#reason').val();
        if (reason == null || reason == "") {
            jBox.tip("请输入退回原因", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsPecipe/addadsPecipe",
            type: "post",
            dataType: "json",
            data: {
                "organTaskId": organTaskId,
                "receiptClass": orgType,
                "organName": organName,
                "organId": organId,
                "ids": ids.toString(),
                "sName": names.toString(),
                "sCode": codes.toString(),
                "reason": reason,
                "receiptTime": receiptTime,
                "token": token,
                "finishNum": finishNum
            },
            success: function (data) {
                if (data.httpCode == 500) {
                    jBox.tip("保存失败", 'info');
                } else if (data.httpCode == 400) {
                    jBox.tip("没有登录", 'info');
                } else {
                    clearAllSt();
                    $("#backModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                }
            }
        });
    };
});