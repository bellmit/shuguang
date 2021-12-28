var myapp = angular.module("myapp", []);
myapp.controller("reCheckTaskAdd", function ($scope, $http, $state) {

    /**
     * 描述：根据初检任务获取对象信息与抽样单信息组成新复检对象
     * 监督抽查：对象信息[监管系统]，抽样单信息[执法系统]
     * 例行、专项监测：检测系统
     * */

    /**
     * 初始化&公共值
     * */
    $scope.asmsCheckTask = {};//初检对象
    $scope.asmsRecheckTask = {};//复检任务
    $scope.asmsRecheckTask.id = window.localStorage.getItem("asms_Check_recheckTask");
    $scope.asmsRecheckObject = {};//复检对象

    //激活验证规则
    $('#reCheckTaskAddFrom').bootstrapValidator();//必须有

    //操作动作
    $("span[name='OprTitle']").html("新增");
    //Modal初始化
    $('#bModal').modal({
        keyboard: true,
        show: false
    });
    $scope.bModalOprIndex = 'add';//模态框操作标示
    $scope.bModalOprIndexTr = null;//记录暂存
    //修改操作初始化
    if ($scope.asmsRecheckTask.id != null || $scope.asmsRecheckTask.id != undefined) {
        //操作动作
        $("span[name='OprTitle']").html("修改");
        //主信息获取
        $http({
            url: "/sofn-asms-web/reCheckTask/getTaskById",
            method: "post",
            data: {id: $scope.asmsRecheckTask.id},
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).success(function (obj) {
            $scope.asmsRecheckTask = obj.data;
            $scope.asmsRecheckTask.recheckTaskBegin = new Date(parseInt(obj.data.recheckTaskBegin) + 28800000).toISOString().slice(0, 10);
            $scope.asmsRecheckTask.recheckTaskEnd = new Date(parseInt(obj.data.recheckTaskEnd) + 28800000).toISOString().slice(0, 10);
            $("input[name='recheckTaskBegin']").val($scope.asmsRecheckTask.recheckTaskBegin );
            $("input[name='recheckTaskEnd']").val($scope.asmsRecheckTask.recheckTaskEnd);
            $("select[name='recheckchange']").val($scope.asmsRecheckTask.initTaskType);
            $("input[name='taskName']").val($scope.asmsRecheckTask.initTaskName);
            $("input[name='recheckTaskName']").val($scope.asmsRecheckTask.recheckTaskName);
            $("input[name='recheckTaskYear']").val($scope.asmsRecheckTask.recheckTaskYear);
            if ($scope.asmsRecheckTask.initTaskType == "1" || $scope.asmsRecheckTask.initTaskType == "2") {
                $("div[name='batchSelectDiv']").show();
            }
            $("input[name='batch']").val($scope.asmsRecheckTask.batch);

            $scope.selects = null;
            $scope.selects = new Array();
            var map = {
                leadUnitId: $scope.asmsRecheckTask.recheckUnitId,
                leadUnitName: $scope.asmsRecheckTask.recheckUnitName
            };
            $scope.selects.push(map);
            $("input[name='recheckUnitName']").val($scope.asmsRecheckTask.recheckUnitName);

            $("#FJ_OBJ_GRID").dataTable().api().ajax.reload();
            $("div[name='SAMPLE_DIV']").show();
            $("#recheckchange").attr("disabled", "disabled");
            $("#taskNameCheck").attr("disabled", "disabled");
            // $("select[name='batch']").attr("disabled","disabled");
        }).error(function (data) {
            
        });
    }

    /**
     * 基本任务信息
     * */

        //初检任务类型选择
    var recheckcha = 0;
    var recheckchb = 0;
    var recheckchc = 0;
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnRoutineDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnRoutineDisabled = false);
        }, 500);
        $("#sysResGrid").dataTable().api().ajax.reload();
    });
    $("#specialToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnSpecialDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnSpecialDisabled = false);
        }, 500);
        $("#specialGrid").dataTable().api().ajax.reload();
    });
    $("#checkTaskToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnCheckDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnCheckDisabled = false);
        }, 500);
        $("#checkTaskGrid").dataTable().api().ajax.reload();
    });

    $("#sysResGrid").dataTable({
        fnDrawCallback: function () {
            //点击行内任意内容即选择行
            $("#sysResGrid tbody tr").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).find("input[type='radio']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).removeClass("selected");
                    } else {
                        checked.checked = true;
                        $('#sysResGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass("selected");
                    }
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/routineMonitor/listByCeateOrg",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataSrc: function (json) {
                if (json.data == null || json.data == undefined) {
                    json.recordsTotal = 0;//总个数
                    json.recordsFiltered = 0;
                    json.pageNumber = 1;//页码
                    json.pageSize = 10;//每页个数
                    json.start = 0;//起始位置
                    json.length = 10;
                    return json.data;
                }
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    //时间格式化
                    if (json.data.list[i].rmDateBegin != null && json.data.list[i].rmDateBegin != "" && json.data.list[i].rmDateBegin != undefined) {
                        json.data.list[i].rmDateBegin = new Date(parseInt(json.data.list[i].rmDateBegin) + 28800000).toISOString().slice(0, 10);
                    }
                    if (json.data.list[i].rmDateEnd != null && json.data.list[i].rmDateEnd != "" && json.data.list[i].rmDateEnd != undefined) {
                        json.data.list[i].rmDateEnd = new Date(parseInt(json.data.list[i].rmDateEnd) + 28800000).toISOString().slice(0, 10);
                    }
                }
                //返回点击状态
                recheckcha = 1;
                return json.data.list;
            },
            data: function (params) {
                params.rmName = $("input[name='taskRMName']").val();
                params.rmState = "1,3,4,5";
                params.dateBegin = $("input[name='datebegin']").val();
                params.dateEnd = $("input[name='dateend']").val();
            },
            type: "post"
        },
        columns: [
            //选择按钮
            {
                data: "ck",
                title: '选择',
                width: "5%",
                render: function (data, type, row) { // 模板化列显示内容
                    return '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="'
                        + row.ID
                        + '" onclick="checkClick(this)" />';
                }
            }, {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                width: "5%",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "rmName",
                title: "初检任务名称",
                width: "25%"
            },
            {
                data: "rmType",
                title: "监测类型",
                width: "10%"
            },
            {
                data: "rmYear",
                title: "年度",
                width: "5%"
            },
            {
                data: "rmFileNum",
                title: "文件号",
                width: "15%"
            },
            {
                data: "rmDateBegin",
                title: "开始时间",
                width: "8%"
            },
            {
                data: "rmDateEnd",
                title: "结束时间",
                width: "8%"
            }
        ]
    });

    $("#specialGrid").dataTable({
        fnDrawCallback: function () {
            $("#specialGrid tbody tr").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).find("input[type='radio']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).removeClass("selected");
                    } else {
                        checked.checked = true;
                        $('#specialGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass("selected");
                    }
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/specialMonitor/listByOrg",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    //时间格式化
                    if (json.data.list[i].smDateBegin != null && json.data.list[i].smDateBegin != "" && json.data.list[i].smDateBegin != undefined) {
                        json.data.list[i].smDateBegin = new Date(parseInt(json.data.list[i].smDateBegin) + 28800000).toISOString().slice(0, 10);
                    }
                    if (json.data.list[i].smDateEnd != null && json.data.list[i].smDateEnd != "" && json.data.list[i].smDateEnd != undefined) {
                        json.data.list[i].smDateEnd = new Date(parseInt(json.data.list[i].smDateEnd) + 28800000).toISOString().slice(0, 10);
                    }
                }
                //返回点击状态
                recheckchb = 1;
                return json.data.list;
            },
            data: function (params) {
                params.smName = $("input[name='taskSMName']").val();
                params.smState = "1,3,4,5";
                params.dateBegin = $("input[name='specialdatebegin']").val();
                params.dateEnd = $("input[name='specialdateend']").val();
            },
            type: "post"
        },
        columns: [
            //选择按钮
            {
                data: "ck",
                title: '选择',
                width: "5%",
                render: function (data, type, row) { // 模板化列显示内容
                    return '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="'
                        + row.ID
                        + '" onclick="checkClick(this)" />';
                }
            }, {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                width: "5%",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "smName",
                title: "初检任务名称",
                width: "25%"
            },
            {
                data: "smType",
                title: "监测类型",
                width: "10%"
            },
            {
                data: "smYear",
                title: "年度",
                width: "5%"
            },
            {
                data: "smFileNum",
                title: "文件号",
                width: "15%"
            },
            {
                data: "smDateBegin",
                title: "开始时间",
                width: "8%"
            },
            {
                data: "smDateEnd",
                title: "结束时间",
                width: "8%"
            }
        ]
    });

    $("#checkTaskGrid").dataTable({
        fnDrawCallback: function () {
            //点击行内任意内容即选择行
            $("#checkTaskGrid tbody tr").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).find("input[type='radio']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).removeClass("selected");
                    } else {
                        checked.checked = true;
                        $('#checkTaskGrid').DataTable().$('tr.selected').removeClass('selected');
                        $(this).addClass("selected");
                    }
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/checkTask/listByOrg",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    //时间格式化
                    if (json.data.list[i].taskBeginTime != null && json.data.list[i].taskBeginTime != "" && json.data.list[i].taskBeginTime != undefined) {
                        json.data.list[i].taskBeginTime = new Date(parseInt(json.data.list[i].taskBeginTime) + 28800000).toISOString().slice(0, 10);
                    }
                    if (json.data.list[i].taskEndTime != null && json.data.list[i].taskEndTime != "" && json.data.list[i].taskEndTime != undefined) {
                        json.data.list[i].taskEndTime = new Date(parseInt(json.data.list[i].taskEndTime) + 28800000).toISOString().slice(0, 10);
                    }
                }
                //返回点击状态
                recheckchc = 1;
                return json.data.list;
            },
            data: function (params) {
                // params.taskLevel = $scope.orgLevel;
                params.taskName = $("input[name='taskCheckName']").val();
                params.state = "1,3,4,5";
                params.dateBegin = $("input[name='checkTaskdatebegin']").val();
                params.dateEnd = $("input[name='checkTaskdateend']").val();
            },
            type: "post"
        },
        columns: [
            //选择按钮
            {
                data: "ck",
                title: '选择',
                width: "5%",
                render: function (data, type, row) { // 模板化列显示内容
                    return '<input class="selectZiyuan" name="selectZiyuan" type="radio" style="cursor: pointer;width:15px;height:15px" value="'
                        + row.ID
                        + '" onclick="checkClick(this)" />';
                }
            },
            {
                data: 'id',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                width: "5%",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "taskName",
                title: "初检任务名称",
                width: "25%"
            },
            {
                data: "taskYear",
                title: "年度",
                width: "5%"
            },
            {
                data: "fileNumber",
                title: "文件号",
                width: "15%"
            },
            {
                data: "taskBeginTime",
                title: "开始时间",
                width: "8%"
            },
            {
                data: "taskEndTime",
                title: "结束时间",
                width: "8%"
            }
        ]
    });

    //复检任务类型的选择
    $("#recheckchange").change(function () {
        //批次属性显示判断
        var taskType = $("select[name='recheckchange']").val();
        if ("1" == taskType || "2" == taskType) {
            $("div[name='batchSelectDiv']").show();
        } else {
            $("div[name='batchSelectDiv']").hide();
        }
        switch ($('#recheckchange option:selected').val()) {
            case '0':
                $("input[name='taskName']").val("");
                $("input[name='recheckTaskName']").val("");
                break;
            case "1":
                $("#sysResource_modal").modal("show");
                break;
            case "2":
                $("#special_modal").modal("show");
                break;
            case "3":
                $("#checkTask_modal").modal("show");
                break;
        }
    });

    //点击初检任务名称
    $("#taskNameCheck").click(function () {
        switch ($('#recheckchange option:selected').val()) {
            case "0":
                jBox.tip("请选择任务类型", 'info');
                break;
            case "1":
                $("#sysResource_modal").modal("show");
                break;
            case "2":
                $("#special_modal").modal("show");
                break;
            case "3":
                $("#checkTask_modal").modal("show");
                break;
        }
    });

    //提交
    $scope.SubMainRmName = function () {
        row = $("#sysResGrid").DataTable().rows('.selected').data()//选中行
        length = $("#sysResGrid").DataTable().rows('.selected').data().length;
        if (length > 0) {
            for (var i = 0; i < length; i++) {
                $scope.asmsCheckTask.id = row[i].id;
                $("input[name='taskName']").val(row[i].rmName);
                $("#taskBatch").val(row[i].rmBatch);
                $("input[name='recheckTaskName']").val(row[i].rmName += "-复检");
                $("#sysResource_modal").modal("hide");
            }
        }
        //重新初始化dataTable里面的数据
        $("#sysResGrid").dataTable().api().ajax.reload();
    };
    $scope.SubMainSmName = function () {
        row = $("#specialGrid").DataTable().rows('.selected').data()//选中行
        length = $("#specialGrid").DataTable().rows('.selected').data().length;
        if (length > 0) {
            for (var i = 0; i < length; i++) {
                $scope.asmsCheckTask.id = row[i].id;
                $("input[name='taskName']").val(row[i].smName);
                $("#taskBatch").val(row[i].smBatch);
                $("input[name='recheckTaskName']").val(row[i].smName += "-复检");
                $("#special_modal").modal("hide");
            }
        }
        //重新初始化dataTable里面的数据
        $("#specialGrid").dataTable().api().ajax.reload();
    };
    $scope.SubMainCheckName = function () {
        row = $("#checkTaskGrid").DataTable().rows('.selected').data()//选中行
        length = $("#checkTaskGrid").DataTable().rows('.selected').data().length;
        if (length > 0) {
            for (var i = 0; i < length; i++) {
                $scope.asmsCheckTask.id = row[i].id;
                $("input[name='taskName']").val(row[i].taskName);
                $("input[name='recheckTaskName']").val(row[i].taskName += "-复检");
                $("#checkTask_modal").modal("hide");
            }
        }
        //重新初始化dataTable里面的数据
        $("#checkTaskGrid").dataTable().api().ajax.reload();
    };
    //重置
    $scope.cleanQueryRM = function () {
        //点击间隔
        $scope.btnRMReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnRMReloadDisabled = false);
        }, 500);
        $("input[name='taskRMName']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("#sysResGrid").dataTable().api().ajax.reload();
    };
    $scope.cleanQuerySM = function () {
        //点击间隔
        $scope.btnSMReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnSMReloadDisabled = false);
        }, 500);
        $("input[name='taskSMName']").val("");
        $("input[name='specialdatebegin']").val("");
        $("input[name='specialdateend']").val("");
        $("#specialGrid").dataTable().api().ajax.reload();
    };
    $scope.cleanQueryCheck = function () {
        //点击间隔
        $scope.btnCheckTaskReloadDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnCheckTaskReloadDisabled = false);
        }, 500);
        $("input[name='taskCheckName']").val("");
        $("input[name='checkTaskdateend']").val("");
        $("input[name='checkTaskdatebegin']").val("");
        $("#checkTaskGrid").dataTable().api().ajax.reload();
    };

    //任务保存
    $scope.infoSave = function () {
        var taskTypeVal = $("select[name='recheckchange']").val();
        if (taskTypeVal == "0") {
            $.jBox.tip("请选择初检任务类型！", "info");
            return;
        }
        if ($("input[name='recheckTaskName']").val() == "") {
            $.jBox.tip("请选择初检任务！", "info");
            return;
        }
        //if ("1" == taskTypeVal || "2" == taskTypeVal) {
        //    if ("" == $("select[name='batch']").val()) {
        //        $.jBox.tip("请选择批次！");
        //        return;
        //    }
        //}
        if ($scope.selects == "" || $scope.selects == null || $scope.selects == undefined) {
            $.jBox.tip("请选择复检单位！", "info");
            return;
        }
        if ($("select[name='recheckTaskPriority']").val() == "") {
            $.jBox.tip("请选择任务优先级！", "info");
            return;
        }
        if ($("input[name='recheckTaskYear']").val() == "") {
            $.jBox.tip("请选择年度！", "info");
            return;
        }
        if ($("input[name='recheckTaskBegin']").val() == "") {
            $.jBox.tip("请选择任务开始时间！", "info");
            return;
        }
        if ($("input[name='recheckTaskEnd']").val() == "") {
            $.jBox.tip("请选择任务结束时间！", "info");
            return;
        }

        //格式处理
        $scope.asmsRecheckTask.id = $scope.asmsRecheckTask.id;
        $scope.asmsRecheckTask.initTaskType = $('#recheckchange option:selected').val();
        $scope.asmsRecheckTask.initTaskName = $("input[name='taskName']").val();
        $scope.asmsRecheckTask.recheckTaskName = $("input[name='recheckTaskName']").val();
        $scope.asmsRecheckTask.recheckTaskYear = $("input[name='recheckTaskYear']").val();
        $scope.asmsRecheckTask.recheckTaskBegin = new Date(($("input[name='recheckTaskBegin']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.asmsRecheckTask.recheckTaskEnd = new Date(($("input[name='recheckTaskEnd']").val() + " 00:00:00").replace(/-/g, "/"));
        $scope.asmsRecheckTask.initTaskId = $scope.asmsCheckTask.id;
        $scope.asmsRecheckTask.recheckUnitId = $scope.selects[0].leadUnitId;
        $scope.asmsRecheckTask.recheckUnitName = $scope.selects[0].leadUnitName;
        $scope.asmsRecheckTask.batch = $("input[name='batch']").val();
        // $scope.asmsRecheckTask.token = window.localStorage.getItem("token");
        if ($scope.asmsRecheckTask.id != null || $scope.asmsRecheckTask.id != undefined) {
            delete  $scope.asmsRecheckTask.createTime;
            delete  $scope.asmsRecheckTask.updateTime;
            delete  $scope.asmsRecheckTask.updateBy;
            delete  $scope.asmsRecheckTask.createBy;
            $("button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/reCheckTask/update",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.asmsRecheckTask,
                beforeSend: function () {
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {
                    $("button").attr("disabled", false);
                    $("#rmDateBegin").val(format($scope.asmsRecheckTask.recheckTaskBegin, 'yyyy-MM-dd'));
                    $("#rmDateEnd").val(format($scope.asmsRecheckTask.recheckTaskEnd, 'yyyy-MM-dd'));
                    $("#web_app_model_userOprXz").modal("hide");
                    if (data.data.msg == "true") {
                        $("#recheckchange").attr("disabled", "disabled");
                        $("#taskNameCheck").attr("disabled", "disabled");
                        $.jBox.tip("成功！");
                    } else {
                        $.jBox.tip("失败！");
                    }
                },
                error: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    $.jBox.tip("失败");
                }
            });
        } else {
            $("button").attr("disabled", true);
            $.ajax({
                url: "/sofn-asms-web/reCheckTask/add",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post",
                dataType: "json",
                data: $scope.asmsRecheckTask,
                beforeSend: function () {
                    $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                    $("#web_app_model_userOprXz").modal("show");
                },
                success: function (data) {
                    if(data.httpCode == "418"){
                        $("button").attr("disabled", false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("新增失败, 任务数被限制!");
                    }else {
                        $("button").attr("disabled", false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $("#rmDateBegin").val(format($scope.asmsRecheckTask.recheckTaskBegin, 'yyyy-MM-dd'));
                        $("#rmDateEnd").val(format($scope.asmsRecheckTask.recheckTaskEnd, 'yyyy-MM-dd'));
                        if (data.data.msg == "true") {
                            $scope.asmsRecheckTask.id = data.data.data.id;
                            $("#recheckchange").attr("disabled", "disabled");
                            $("#taskNameCheck").attr("disabled", "disabled");
                            $.jBox.tip("成功!");
                            $("#FJ_OBJ_GRID").dataTable().api().ajax.reload();
                            $("div[name='SAMPLE_DIV']").show();
                        } else {
                            $.jBox.tip("失败!");
                        }
                    }
                },
                error: function (data) {
                    $("button").attr("disabled", false);
                    $("#web_app_model_userOprXz").modal("hide");
                    $.jBox.tip("失败");
                }
            });
        }
    };

    //中国时间转标准时间
    var format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        });
    };

    /**
     * 复检对象
     * */

    //复检对象grid
    $("#FJ_OBJ_GRID").dataTable({
        fnDrawCallback: function () {
            $("#checkAll").prop("checked", $("input[name='ck']").length > 0 && $("input[name='ck']").length === $("input[name='ck']:checked").length);
            $("#FJ_OBJ_GRID tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    var checked = $(this).parent().find("input[type='checkbox']")[0];
                    if (checked.checked) {
                        checked.checked = false;
                        $(this).parent().removeClass("selected");
                    } else {
                        checked.checked = true;
                        $(this).parent().addClass("selected");
                    }
                    $("#checkAll").prop("checked", $("input[name='ck']").length > 0 && $("input[name='ck']").length === $("input[name='ck']:checked").length);
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/reCheckObject/reCheckObjlist",
            headers: {
                token: window.localStorage.getItem("token")
            },
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
                params.recheckTaskId = $scope.asmsRecheckTask.id;
                params.recheckSampleName = $("input[name='queryRecheckSampleName']").val();
            },
            type: "post"
        },
        columns: [
            {
                data: "ck",
                title: '<input id="checkAll" onclick="checkAll(this.checked)" type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input type="checkbox" name="ck"/>';
                },
                width: "2%"
            },
            {
                data: 'id',
                title: 'id',
                visible: false
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "recheckSampleName",
                title: "复检样品名称"
            },
            {
                data: "recheckSampleCode",
                title: "复检样品编码"
            },
            {
                data: "testedDeparment",
                title: "复检企业名称"
            }
        ]
    });

    $("#obj_Check").click(function () {
        $("#FJ_OBJ_GRID").dataTable().api().ajax.reload();
    });

    //新增复检对象
    $scope.add_obj = function () {
        $("#attributeForm")[0].reset();
        $scope.bModalOprIndex = 'add';
        $("#modalTitle").html("添加复检对象");
        $("#bModal").modal('show');
    };

    //抽样单选择
    $scope.cydSelect = function () {
        var taskType = $("select[name='recheckchange']").val();
        if ("0" == taskType) {
            $.jBox.tip("请选择初检任务类型！");
            return;
        }
        //if ("1" == taskType || "2" == taskType) {
        //    if ("" == $("select[name='batch']").val()) {
        //        $.jBox.tip("请选择批次！");
        //        return;
        //    }
        //}
        $("#cydSelectGrid").dataTable().api().ajax.reload();
        $("#cydSelect_modal").modal('show');
    };

    //抽样单grid
    $("#cydSelectGrid").dataTable({
        fnDrawCallback: function () {
            $("#cydSelectGrid tbody tr td").each(function (i, o) {
                $(o).click(function () {
                    if ("false" == $(this).parent().find("[name='isSelect']").attr("value")) {
                        $("input[name='oprSampleName']").val($(this).parent().find("[name='sampleName']").html());
                        $("input[name='oprSampleCode']").val($(this).parent().find("[name='sampleCode']").html());
                        $("input[name='oprTestedDeparment']").val($(this).parent().find("[name='testedDeparment']").html());
                        $("#cydSelect_modal").modal('hide');
                    }
                });
            })
        },
        ajax: {
            url: "/sofn-asms-web/reCheckTask/taskSampleList",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataSrc: function (json) {
                if (_.isNull(json.data.list)) {
                    json.recordsTotal = 0;//总个数
                    json.recordsFiltered = 0;
                    json.pageNumber = 1;//页码
                    json.pageSize = 10;//每页个数
                    json.start = 0;//起始位置
                    json.length = 10;
                    return [];
                }
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function (params) {
                params.batch = $("input[name='batch']").val();
                params.taskType = $("select[name='recheckchange']").val();
                params.taskId = $scope.asmsRecheckTask.initTaskId;
                params.sampleName = $("input[name='pro_name']").val();
                params.sampleCode = $("input[name='cyd_code']").val();
            },
            type: "post"
        },
        columns: [
            {
                data: 'ID',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "SAMPLENAME",
                title: "样品名称",
                render: function (data, type, row) {
                    return '<span name = "sampleName">' + data + '</span>';
                }
            },
            {
                data: "SAMPLECODE",
                title: "样品编码",
                render: function (data, type, row) {
                    return '<span name = "sampleCode">' + data + '</span>';
                }
            },
            {
                data: "TESTEDDEPARMENT",
                title: "受检单位",
                render: function (data, type, row) {
                    return '<span name = "testedDeparment">' + data + '</span>';
                }
            },
            // {
            //     data:"CYUNITNAME",
            //     title : "抽样机构"
            // },
            // {
            //     data:"JCUNITNAME",
            //     title : "初检机构"
            // }
            {
                data: "SAMPLECODE",
                title: "已选择",
                render: function (data, type, row) {
                    var codes = $scope.getRecheckSampleCodes();
                    if (null != codes) {
                        for (var i = 0; i < codes.length; i++) {
                            if (codes[i] == data) {
                                return '<span name="isSelect" class="glyphicon glyphicon-ok" style="color: #00B83F" value = "true"></span>';
                            }
                        }
                    }
                    return '<span name="isSelect" value = "false"></span>';
                }
            }
        ]
    });

    $("#cydSelectQueryButton").click(function () {
        $("#cydSelectGrid").dataTable().api().ajax.reload();
    });

    //复检对象保存
    $scope.cyd_SaveFunc = function () {
        $scope.asmsRecheckObject.recheckSampleName = $("input[name='oprSampleName']").val();
        if ($scope.asmsRecheckObject.recheckSampleName == undefined || $scope.asmsRecheckObject.recheckSampleName == null || $scope.asmsRecheckObject.recheckSampleName == "") {
            $.jBox.tip("请选择样品！", "warning");
            return;
        }
        $scope.asmsRecheckObject.recheckSampleCode = $("input[name='oprSampleCode']").val();
        $scope.asmsRecheckObject.testedDeparment = $("input[name='oprTestedDeparment']").val();
        $scope.asmsRecheckObject.recheckTaskId = $scope.asmsRecheckTask.id;
        $.ajax({
            url: "/sofn-asms-web/reCheckObject/addObj",
            headers: {
                token: window.localStorage.getItem("token")
            },
            type: "post",
            dataType: "json",
            data: $scope.asmsRecheckObject,
            beforeSend: function () {
                $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});
                $("#web_app_model_userOprXz").modal("show");
            },
            success: function (data) {
                $("#web_app_model_userOprXz").modal("hide");
                $.jBox.tip("成功！");
                $("#bModal").modal('hide');
                $("#FJ_OBJ_GRID").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $("#web_app_model_userOprXz").modal("hide");
                $.jBox.tip("失败");
            }
        });
    };

    //复检对象删除
    $scope.cyd_DelFunc = function () {
        var rows = $("#FJ_OBJ_GRID").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        $.jBox.confirm("是否删除已选择对象？", "等待服务器响应", function (v) {
            if (v == true) {
                var ids = [];
                for (var i = 0; i < length; i++) {
                    ids.push(rows[i].id);
                }
                var data = {
                    ids: JSON.stringify(ids)
                };
                $("button").attr("disabled", true);
                $.ajax({
                    url: "/sofn-asms-web/reCheckObject/delObj",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    type: "post",
                    dataType: "json",
                    data: data,
                    beforeSend: function () {
                        $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});
                        $("#web_app_model_userOprXz").modal("show");
                    },
                    success: function (data) {
                        $("button").attr("disabled", false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("成功！");
                        $("#FJ_OBJ_GRID").dataTable().api().ajax.reload();
                        $("#checkAll").prop("checked", false);
                    },
                    error: function (data) {
                        $("button").attr("disabled", false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("失败");
                    }
                });
            }
            return true;
        }, {buttons: {'确认': true, '取消': false}});
    };

    //获取已有列表复检样品编码
    $scope.getRecheckSampleCodes = function () {
        var rows = $("#FJ_OBJ_GRID").DataTable().rows().data();
        var length = rows.length;
        if (length <= 0) {
            return null;
        }
        var codes = [];
        for (var i = 0; i < length; i++) {
            codes.push(rows[i].recheckSampleCode);
        }
        return codes;
    };
    /**
     * ---------复检单位选择列表--------------
     * */

    /**
     * 已选择数据
     * id、name
     * */
    $scope.selects = new Array();

    $('#selectModal').modal({
        show: false,
        backdrop: 'static',
        keyboard: false
    });
    var token = window.localStorage.getItem("token")
    var area = "";
    $http({
        url: "/sofn-asms-web/asmsTools/getOrgbyUserToken",
        method: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        area = data.data.regionId;
        if (data.data.orgLevel == "1") {
            $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");
        } else {
            if (data.data.regionId != null && data.data.regionId != "" && data.data.regionId != undefined) {
                area = data.data.regionId.substr(0, 2 * (data.data.orgLevel - 1));
                $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", data.data.regionId.substr(0, 2));
                $("#selectQueryShen").attr("disabled", true);
            } else {
                $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian");
            }
        }
        $scope.initDataTable();
    }).error(function () {

    });
    $scope.selectrecheckUnitId = function () {
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", area);
        if ($("#selectQueryShen").val() != null && $("#selectQueryShen").val() != "" && $("#selectQueryShen").val() != undefined) {
            $("#selectQueryShen").attr("disabled", true);
        }
        // 检测单位选择全省
        /*
        if ($("#selectQueryShi").val() != null && $("#selectQueryShi").val() != "" && $("#selectQueryShi").val() != undefined) {
            $("#selectQueryShi").attr("disabled", true);
        }
        if ($("#selectQueryXian").val() != null && $("#selectQueryXian").val() != "" && $("#selectQueryXian").val() != undefined) {
            $("#selectQueryXian").attr("disabled", true);
        }
        */
        $('#selectGrid').dataTable().api().ajax.reload();
        $("#selectModal").modal('show');
    };
    //表格
    $scope.initDataTable = function () {
        $("#selectGrid").dataTable({
            fnDrawCallback: function () {
                $("#selectGrid tbody tr td").each(function (i, o) {
                    $(o).click(function () {
                        $scope.selects = null;
                        $scope.selects = new Array();
                        var map = {
                            leadUnitId: $(this).parent().find("[name='id']").val(),
                            leadUnitName: $(this).parent().find("[name='name']").html()
                        };
                        $scope.selects.push(map);
                        var name = "";
                        for (var i = 0; i < $scope.selects.length; i++) {
                            if ($scope.selects[i] != undefined) {
                                if (name != "") {
                                    name += "," + $scope.selects[i].leadUnitName;
                                } else {
                                    name = $scope.selects[i].leadUnitName;
                                }
                            }
                        }
                        $("input[name='recheckUnitName']").val(name);
                        $("#selectModal").modal('hide');
                    });
                })
            },
            ajax: {
                url: "/sofn-asms-web/asmsTools/getOrgsListByUserToken",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.data;
                },
                data: function (params) {
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() != "") {
                        params.dtAreaId = $("#selectQueryXian").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() != "" && $("#selectQueryXian").val() == "") {
                        params.dtAreaId = $("#selectQueryShi").val();
                    }
                    if ($("#selectQueryShen").val() != "" && $("#selectQueryShi").val() == "" && $("#selectQueryXian").val() == "") {
                        params.dtAreaId = $("#selectQueryShen").val();
                    }
                    // params.querySheng = $("#querySheng").val();
                    // params.queryShi = $("#queryShi").val();
                    // params.queryXian = $("#queryXian").val();
                    params.dtName = $("input[name='selectQueryName']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: 'id',
                    render: function (data, type, row, meta) {
                        return '<input type="hidden" name="id" value = "' + data + '"/>';
                    }
                },
                {
                    data: 'id',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    width: "5%",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "dtName",
                    title: "单位名称",
                    width: "45%",
                    render: function (data, type, row) {
                        return '<span name = "name">' + data + '</span>';
                    }
                },
                {
                    data: "dtType",
                    title: "机构类别",
                    width: "10%",
                },
                {
                    data: "dtAddress",
                    title: "所属区域",
                    width: "35%",
                }
            ]
        });
    };

    $("#selectTocheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#selectGrid").dataTable().api().ajax.reload();
    });

    //查询条件重置
    $scope.cleanSelectModelQuerys = function () {
        $("input[name='selectQueryName']").val("");
        $.fn.mycity("selectQueryShen", "selectQueryShi", "selectQueryXian", area);
        if ($("#selectQueryShen").val() != null && $("#selectQueryShen").val() != "" && $("#selectQueryShen").val() != undefined) {
            $("#selectQueryShen").attr("disabled", true);
        }
        // 检测单位选择全省
        /*
        if ($("#selectQueryShi").val() != null && $("#selectQueryShi").val() != "" && $("#selectQueryShi").val() != undefined) {
            $("#selectQueryShi").attr("disabled", true);
        }
        if ($("#selectQueryXian").val() != null && $("#selectQueryXian").val() != "" && $("#selectQueryXian").val() != undefined) {
            $("#selectQueryXian").attr("disabled", true);
        }
        */
        $("#selectGrid").dataTable().api().ajax.reload();
    };

    //清空已选择
    $scope.cleanSelecteds = function () {
        $scope.selects = null;
        $scope.selects = new Array();
        $('#selectGrid').dataTable().api().ajax.reload();
    };

    $scope.relTask = function () {
        var ids = [];
        ids.push($scope.asmsRecheckTask.id);
        var data = {
            jsonString : JSON.stringify(ids)
        }
        $.ajax({
            url:"/sofn-asms-web/reCheckTask/rel",
            headers:{
                token:window.localStorage.getItem("token")
            },
            type:"post",
            dataType:"json",
            data:data,
            beforeSend: function(){
                $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                $("#web_app_model_userOprXz").modal("show");
            },
            success:function(data){
                $("button").attr("disabled",false);
                $("#web_app_model_userOprXz").modal("hide");
                var info = data.data.info;
                if (info == "true"){
                    $.jBox.tip("发布成功！");
                    window.setTimeout(function(){
                        $state.go("index.content.asms/check/reCheckTask");
                    }, 500)
                }else if (info == "false"){
                    $.jBox.tip("失败！");
                }else if(info == "NoObj"){
                    $.jBox.tip("所选任务下无复检对象！");
                }
            },
            error: function (data) {
                $("button").attr("disabled",false);
                $("#web_app_model_userOprXz").modal("hide");
                $.jBox.tip("失败");
                $scope.TableReload();
            }
        });
    }

    $scope.cleanCookie = function () {
        $.jBox.tip("保存成功");
        window.setTimeout(function(){
        $state.go("index.content.asms/check/reCheckTask");
        }, 500)
    }

    //针对复选框无法选中bug
    window.checkAll = function (checked) {
        $("input[name='ck']").prop("checked",checked);
        $("input[name='ck']").parent().parent().toggleClass("selected", checked);
    };

});

/**
 * other
 * */
function checkClick(ck) {
    $($(ck).parent().parent()[0]).toggleClass('selected');
    var e = window.event || arguments.callee.caller.arguments[0];
    if (e && e.stopPropagation) {
        e.stopPropagation();
    } else {
        window.event.cancelBubble = true;
    }
}

