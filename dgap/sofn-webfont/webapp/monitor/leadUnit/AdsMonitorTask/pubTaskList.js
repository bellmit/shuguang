var myapp = angular.module("myapp", []);
myapp.controller("pubTaskList", function ($scope, $http, $state) {
    window.localStorage.setItem("task", "pub");
    var monitorClass = window.localStorage.getItem("monitorClass");
    // localStorage.removeItem("monitorClass");
    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for (var i = 0; i < a.length; i++) {
        // if (a[i].menuName == "例行监测" || a[i].menuName == "专项监测") {
        //     $scope.iswrite = a[i].authority;
        //     break;
        // }
        if(monitorClass=="例行监测"){
            if(a[i].menuName == "例行监测"){
                    $scope.iswrite = a[i].authority;
                    break;
            }
        }else {
            if(a[i].menuName == "专项监测"){
                $scope.iswrite = a[i].authority;
                break;
            }
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    $("#newTask").click(function () {
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    });

    $("#bePubTask").click(function () {
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    $("#historyTask").click(function () {
        window.localStorage.setItem("monitorClass", monitorClass);//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList")
    });

    $("#supTask").click(function () {
        if (monitorClass == "例行监测") {
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
        } else if (monitorClass == "专项监测") {
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor")
        }
    });

    $("#seachTask").click(function () {
        $scope.clickEvent();
    });
    $scope.clickEvent = function () {
        $("#grid").dataTable().api().ajax.reload();
    };
    //绑定回车事件
    $scope.enterEvent = function (e) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {
            $scope.clickEvent();
        }
    };
    /**
     * 导出监测任务excel数据
     */
    $scope.exportExcel = function () {
        var taskName = $("input[name='taskName']").val();
        var year = $("#year").find("option:selected").val();
        if (monitorClass == "" || monitorClass == null || monitorClass == '' || monitorClass == 'undefined ') {
            monitorClass = $('#monitorClass option:selected').val();
        }

        var ids = "";
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }

        for (var k = 0; k < rows.length; k++) {
            ids += rows[k].id + ",";
        }
        //去除字符串中最后一个逗号
        ids = ids.substring(0, ids.lastIndexOf(','));

        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorTask/exportAdsTaskExcel",
                    method: "post",
                    dataType: "json",
                    params: {
                        "flag": 0,
                        "taskName": taskName,
                        "monitorClass": monitorClass,
                        "year": year,
                        "token": token,
                        "ids": ids
                    },
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '待发布任务列表.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    /**
     * 发布任务
     */
    $scope.publicTask = function () {
        var check = $("input[name='task']:checked");
        if (check.length > 0) {
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id = $(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsMonitorTask/publishAdsMonitorTask",
                        type: "post",
                        dataType: "json",
                        data: {"token": token, "task_ids": arr.toString()},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                jBox.tip("发布成功，在历史任务里可以查看已发布的任务", 'success');
                            } else {
                                jBox.tip(data.msg, 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认发布任务吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        } else {
            jBox.tip("请先选择任务数据", 'info');
        }
    }

    if (monitorClass == "例行监测") {
        $("#jiance").html("例行监测");
    } else if (monitorClass == "专项监测") {
        $("#jiance").html("专项监测");
    }
    $("#jiance").click(function () {
        if (monitorClass == "例行监测") {
            $("#jiance").prop("href", "#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        } else if (monitorClass == "专项监测") {
            $("#jiance").prop("href", "#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor");
        } else {
            $("#jiance").prop("href", "#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        }
    })
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
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                    params.publishStatus = 0;   //查询未发布的任务
                    params.is_history = "";
                    params.taskName = $("input[name='taskName']").val();    //任务名称
                    params.token = token;
                    params.monitorClass = monitorClass;
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='" + row.id + "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    width:"20%",
                    render: function (data, type, row) {
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="lookOrganRegion(\'' + row.id + '\') ">' + row.taskName + '</a>';
                        return look_a;
                    }
                },
                {
                    data: "year",
                    title: "年度"
                },
                {
                    data: "batch",
                    title: "批次"
                },
                {
                    data: "startTime",
                    title: "开始时间"
                },
                {
                    data: "endTime",
                    title: "结束时间"
                },
                {
                    data: "monitorClass",
                    title: "监测类型"
                },
                {
                    data: "priority",
                    title: "优先级",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        return getPriorityName(data);
                    }
                },
                {
                    data: "ID",
                    title: "状态",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '待发布';
                        return look_a;
                    }
                },
                /*{
                    data: "id",
                    title: "操作",
                    render : function(data, type, row) {
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="exportWord(\''+data+'\',\''+row.taskName+'\') ">导出总览</a>';
                        return look_a;
                    }
                },*/
            ],
        });
    });


    //获取发布状态名称
    function getPriorityName(type) {
        var str = "";
        switch (type){
            case 1:
                str = '<span style="line-height:2;" class="label label-danger">特急</span>';
                break;
            case 2:
                str = '<span style="line-height:2;" class="label label-warning">紧急</span>';
                break;
            case 3:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
            default:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
        }
        return str;
    }

    /**
     * 导出监测任务excel数据
     */
    window.exportWord = function (adsMonitorTaskId, name) {
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsMonitorTask/exportTaskDoc",
                    method: "post",
                    dataType: "json",
                    params: {"adsMonitorTaskId": adsMonitorTaskId},
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var taskName = name + ".doc";
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', taskName);
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "error")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    /**
     * 跳转到机构与地域页面
     */
    window.lookOrganRegion = function (data) {
        window.localStorage.setItem("adsMonitorTaskId", data);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView", {source: 'pub'});
    };

})

window.check = function (e) {
    //var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
    var re = /^[\u4E00-\u9FA5A-Za-z0-9_]{1,40}$/;
    if (e.value != "") {
        if (!re.test(e.value)) {
            alert("最多只能输入40个字符");
            e.value = "";
            e.focus();
        }
    }
}


/**
 * 全选任务数据
 */
function checkAll() {
    var val = $("input[name='checkAll']:checked");
    if (val.length > 0) {
        //全选
        $("input[name='task']").attr("checked", true);
    } else {
        $("input[name='task']").attr("checked", false);
    }
}
