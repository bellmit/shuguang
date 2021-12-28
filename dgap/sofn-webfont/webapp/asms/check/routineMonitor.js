var myapp = angular.module("myapp", []).controller("routineMonitor", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("例行监测");
    //获取缓存查询参数
    $scope.routineMonitorQueryParams = JSON.parse(window.sessionStorage.getItem("routineMonitorQueryParams"));
    //查询按钮
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var routineMonitorQueryParams = {};
        routineMonitorQueryParams.rmName = $("input[name='rmName']").val();
        routineMonitorQueryParams.rmState = $("select[name='rmState']").val();
        routineMonitorQueryParams.dateBegin = $("input[name='datebegin']").val();
        routineMonitorQueryParams.dateEnd = $("input[name='dateend']").val();
        routineMonitorQueryParams.rmYear = $("input[name='queryYear']").val();
        window.sessionStorage.setItem("routineMonitorQueryParams",JSON.stringify(routineMonitorQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    if($scope.routineMonitorQueryParams){
        $("input[name='queryYear']").val($scope.routineMonitorQueryParams.rmYear);
        $("select[name='rmState']").val($scope.routineMonitorQueryParams.rmState);
        $("input[name='datebegin']").val($scope.routineMonitorQueryParams.dateBegin);
        $("input[name='dateend']").val($scope.routineMonitorQueryParams.dateEnd);
        $("input[name='rmName']").val($scope.routineMonitorQueryParams.rmName);
    }
    /**
     * 任务列表
     * */
    var loadGrid = function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/routineMonitor/list",
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
                        if (json.data.list[i].RMDATEBEGIN != null && json.data.list[i].RMDATEBEGIN != "" && json.data.list[i].RMDATEBEGIN != undefined) {
                            json.data.list[i].RMDATEBEGIN = new Date(parseInt(json.data.list[i].RMDATEBEGIN) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].RMDATEEND != null && json.data.list[i].RMDATEEND != "" && json.data.list[i].RMDATEEND != undefined) {
                            json.data.list[i].RMDATEEND = new Date(parseInt(json.data.list[i].RMDATEEND) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].CREATETIME != null && json.data.list[i].CREATETIME != "" && json.data.list[i].CREATETIME != undefined) {
                            json.data.list[i].CREATETIME = new Date(parseInt(json.data.list[i].CREATETIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    // params.token = window.localStorage.getItem("token");
                    var routineMonitorQueryParams = JSON.parse(window.sessionStorage.getItem("routineMonitorQueryParams"));
                    if(routineMonitorQueryParams){
                        params.rmName = routineMonitorQueryParams.rmName;
                        params.rmState = routineMonitorQueryParams.rmState;
                        params.dateBegin = routineMonitorQueryParams.dateBegin;
                        params.dateEnd = routineMonitorQueryParams.dateEnd;
                        params.rmYear = routineMonitorQueryParams.rmYear;
                    }else{
                        params.rmName = $("input[name='rmName']").val();
                        params.rmState = $("select[name='rmState']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.rmYear = $("input[name='queryYear']").val();
                    }
                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width: "3%"
                },
                {
                    data: 'ID',
                    title: 'ID',
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"
                },
                {
                    data: "RMNAME",
                    title: "任务名称",
                    width: "12%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>32){
                            return "<a id='RMNAME"+row.ID+"' onmouseover=showPopContent('RMNAME"+row.ID+"','"+data+"')>"+data.substring(0,32)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "RMYEAR",
                    title: "年度",
                    width: "4%"
                },
                {
                    data: "RMFILENUM",
                    title: "文件号",
                    width: "13%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='RMFILENUM"+row.ID+"' onmouseover=showPopContent('RMFILENUM"+row.ID+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "RMDATEBEGIN",
                    title: "开始时间",
                    width: "8%"
                },
                {
                    data: "RMDATEEND",
                    title: "结束时间",
                    width: "8%"
                },
                {
                    data: "USERNAME",
                    title: "创建者",
                    width: "6%",
                    render:function (data) {
                        return data||"admin";
                    }
                },
                {
                    data: "CREATETIME",
                    title: "创建时间",
                    width: "8%"
                },
                {
                    data: "RMSTATE",
                    title: "任务状态",
                    width: "9%",
                    render: function (data, type, row) {
                        //未发布
                        if(data == 0){
                            return '<span style="color:#1f934b;font-weight: bold;">未发布</span>';
                        }
                        //已发布
                        if(data == 1){
                            return '<span style="color:#1f934b;font-weight: bold;">已发布</span>';
                        }
                        //已废止
                        if(data == 2){
                            return '<span style="color:#ff0d06;font-weight: bold;">已废止</span>';
                        }
                        //执行中
                        if(data == 3){
                            return '<span style="color:#333333;font-weight: bold;">执行中</span>';
                        }
                        //已结束,未完成
                        if(data == 4){
                            return '<span style="color:#ff0d06;font-weight: bold;">已结束,未完成</span>';
                        }
                        //已结束,已完成
                        if(data == 5){
                            return '<span style="color:#bfbfbf;font-weight: bold;">已结束,已完成</span>';
                        }
                    }
                },
                {
                    data: "RMPRIORITY",
                    title: "任务优先级",
                    width: "8%",
                    render: function (data, type, row) {
                        //特急
                        if(data == 1){
                            return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #CE0000;">特急</label>';
                        }
                        //紧急
                        if(data == 2){
                            return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #D9B300;">紧急</label>';
                        }
                        //一般
                        if(data == 3){
                            return '<label  style="color:#FFFFFF;font-weight: bold;text-align: center;width: 50%;border: 1px;background: #0080FF;">一般</label>';
                        }

                    }

                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        var butt = '<a style="cursor:pointer;margin-right: 5px;" onclick="queryInfo(\'' + data + '\')" class="color">详情</a>  <a style="cursor:pointer;margin-right: 5px;" onclick="querySamples(\'' + data + '\' , \''+row.RMNAME+'\', \''+row.RMSTATE+'\')" class="color">抽样单</a> <a style="cursor:pointer;margin-right: 5px;" onclick="queryReportsForQtUnit(\'' + data + '\')" class="color">牵头单位报告<br/></a> <a style="cursor:pointer;margin-right: 5px;" onclick="queryReportsForCdUnit(\'' + data + '\' , \''+row.RMNAME+'\', \''+row.RMSTATE+'\')" class="color">承担单位报告</a> ';
                        //判断是否具有读写权限
                        if($scope.iswrite!=2){
                            return butt;
                        }
                        // 任务仅创建者可以修改,且具有读写权限
                        if(row.CREATEBY&&row.CREATEBY.trim()!="admin" && row.CREATEBY.trim()!=$scope.user.id){
                            return butt;
                        }
                        //已发布,已废止的任务不能修改
                        if(row.RMSTATE != 0){
                            return butt;
                        }
                        return butt + ' <a style="cursor:pointer;margin-right: 5px;" onclick="upd(\'' + data + '\')">修改</a>';
                    },
                    width: "30%"
                }
            ]
        });
    }

    //从缓存中获取数据
    if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
        //获取用户信息
        $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
        loadGrid();
    }else{
        swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
        setTimeout(function(){
            window.location.href = '#/login/login';
            window.location.reload();
        },2500)
    }

    /**
     * 任务操作
     * */
    //查看抽样单
    window.querySamples = function (id, taskName, status) {
        window.localStorage.removeItem("asms_Check_checkTask_forReport");
        window.localStorage.setItem("asms_Check_checkTask_forReport", id);
        window.localStorage.removeItem("asms_Check_checkTask_taskName_forReport");
        window.localStorage.setItem("asms_Check_checkTask_taskName_forReport", taskName);
        window.localStorage.removeItem("asms_Check_checkTask_status_forReport");
        window.localStorage.setItem("asms_Check_checkTask_status_forReport", status);
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'LXJC');
        $state.go("index.content.asms/check/TaskSample/TaskSampleList");
    };

    //查看详情
    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });
    window.queryInfo = function (o) {
        $scope.routineMonitor = {};
        $("#TaskInfoModel").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/routineMonitor/getTaskById",
            method: "post",
            data: {"id": o}
        }).success(function (json) {
            //任务
            $scope.routineMonitor = json.data;
            $scope.routineMonitor.rmDateBegin = new Date(parseInt(json.data.rmDateBegin) + 28800000).toISOString().slice(0, 10);
            $scope.routineMonitor.rmDateEnd = new Date(parseInt(json.data.rmDateEnd) + 28800000).toISOString().slice(0, 10);
            //downloadFile(json.data.rmFile);
            //牵头单位
            $scope.routineMonitor.qtName = "";
            for (var i = 0; i < json.rouList.length; i++) {
                if ($scope.routineMonitor.qtName == "") {
                    $scope.routineMonitor.qtName += json.rouList[i].leadUnitName;
                } else {
                    $scope.routineMonitor.qtName += "," + json.rouList[i].leadUnitName;
                }
            }
            $scope.checkData($scope.routineMonitor);
        });
    };
    //查看牵头单位报告
    window.queryReportsForQtUnit = function (obj) {
        window.localStorage.setItem("takeTheLeadReporListTaskId", obj);
        window.localStorage.setItem("takeTheLeadReporListTaskType", "routineMonitor");
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'LXJC');
        $state.go("index.content.asms/check/TaskReport/takeTheLeadReporList");
    };

    //查看承担单位报告
    window.queryReportsForCdUnit = function (id, taskName, status) {
        window.localStorage.removeItem("asms_Check_checkTask_forReport");
        window.localStorage.setItem("asms_Check_checkTask_forReport", id);
        window.localStorage.removeItem("asms_Check_checkTask_taskName_forReport");
        window.localStorage.setItem("asms_Check_checkTask_taskName_forReport", taskName);
        window.localStorage.removeItem("asms_Check_checkTask_status_forReport");
        window.localStorage.setItem("asms_Check_checkTask_status_forReport", status);
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'LXJC');
        $state.go("index.content.asms/check/TaskReport/TaskReportList");
    };

    //发布
    $scope.rel = function () {
        //已发布或废止任务无法进行发布操作
        var rows = $scope.getSelectLinaDates();
        if (rows) {
            for(var n = 0; n < rows.length; n++){
                if(rows[n].CREATEBY.trim()!="admin" && rows[n].CREATEBY.trim()!=$scope.user.id){
                    $.jBox.tip("没有权限,任务仅创建者可以发布!");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if (rows[k].RMSTATE != 0) {
                    $.jBox.tip("所选任务包括已发布或已废止任务，无法进行发布操作！");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if (new Date(rows[k].RMDATEEND).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())) {
                    $.jBox.tip("所选任务已过结束时间，无法进行发布操作！");
                    return false;
                }
            }
        }
        //同步发送操作限制
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            $.jBox.confirm("是否发布已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $("button").attr("disabled", true);
                    $.ajax({
                        url: "/sofn-asms-web/routineMonitor/rel",
                        type: "post",
                        dataType: "json",
                        data: data,
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("button").attr("disabled", false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $scope.TableReload();
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
        }
    };


    //修改、已发布任务无法修改
    window.upd = function (data) {
        window.localStorage.removeItem("asms_Check_routineMonitor");
        window.localStorage.setItem("asms_Check_routineMonitor", data);
        $state.go("index.content.asms/check/routineMonitorAdd");
    };

    //废止
    $scope.abo = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var data = [];
        if(rows.length<=0){
            $.jBox.tip("请至少选择一条记录！");
            return false;
        }
        for(var k = 0;k<rows.length;k++){
            if(rows[k].CREATEBY.trim()!="admin" && rows[k].CREATEBY.trim()!=$scope.user.id){
                $.jBox.tip("没有权限,任务仅创建者可以废止!");
                return false;
            }
        }
        for(var i=0;i<rows.length;i++){
            if(rows[i].RMSTATE == 2){
                $.jBox.tip("所选任务包含已废止任务！");
                return false;
            }
            if(rows[i].RMSTATE == 0){
                $.jBox.tip("所选任务包含未发布任务！");
                return false;
            }
            if(new Date(rows[i].RMDATEEND).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())){
                $.jBox.tip("所选任务包含已结束任务!");
                return false;
            }
            data.push(rows[i].ID);
        }
        $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function (v) {
            if (v == true) {
                $("button").attr("disabled", true);
                $.ajax({
                    url: "/sofn-asms-web/routineMonitor/abo",
                    type: "post",
                    dataType: "json",
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    data: {jsonString:JSON.stringify(data)},
                    beforeSend: function () {
                        $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                        $("#web_app_model_userOprXz").modal("show");
                    },
                    success: function (data) {
                        $("button").attr("disabled", false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("成功！");
                        $scope.TableReload();
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

    //清除存储信息
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("asms_Check_routineMonitor");
    };

    //导出
    $scope.exportExcel = function () {
        $.ajax({
            url: "/sofn-asms-web/routineMonitor/export",
            headers:{
                token:window.localStorage.getItem("token")
            },
            type: "post",
            dataType: "json",
            data: {"id": "123"},
            success: function (data) {
                // console.info(data)
            },
            error: function (data) {
            }
        });
    };

    //删除
    $scope.del = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].CREATEBY.trim() != "admin" && rows[n].CREATEBY.trim() != $scope.user.id) {
                $.jBox.tip("没有权限,任务仅创建者可以删除!");
                return false;
            }
        }
        for (var k = 0; k < length; k++) {
            if (rows[k].RMSTATE != 0&&rows[k].RMSTATE != 2) {
                $.jBox.tip("删除操作仅限于未发布或已废止任务！");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }

        if (rows != false) {
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(ids)
                    };
                    $.ajax({
                        url: "/sofn-asms-web/routineMonitor/del",
                        type: "post",
                        dataType: "json",
                        data: data,
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        beforeSend: function () {
                            $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                            $("#web_app_model_userOprXz").modal("show");
                        },
                        success: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("成功！");
                            $scope.TableReload();
                        },
                        error: function (data) {
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };


    //获取选择行记录id集合
    $scope.getSelectRowsIds = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        return ids;
    };

    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };

    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
    };

    //提示条初始化
    //$("[data-toggle='tooltip']").tooltip();

    //附件下载
    //window.downloadFile = function (o) {
    //    $("#File").html('<label class="col-lg-4 col-md-4 col-sm-4" style="text-align: right;padding-right: 10px">附件：</label>');
    //    if (o != undefined && o != null && o != "") {
    //        var rs;
    //        rs = o.split("|");
    //        console.info(rs);
    //        for (var i = 0; i < rs.length; i++) {
    //            if (rs[i] != undefined && rs[i] != null && rs[i] != "") {
    //                var fileName = rs[i].substr(rs[i].lastIndexOf('\/') + 1);
    //                $("#File").append('附件' + i + ':<a href="http://' + rs[i] + '">' + fileName + '</a>');
    //            }
    //        }
    //    } else {
    //        $("#File").append('无');
    //    }
    //};
    //附件下载
    window.downloadFileName=function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-asms-web/asmsFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', $scope.routineMonitor.rmFile);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.routineMonitor.rmFileName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
    //鼠标悬停弹窗显示
    window.showPopContent=function(id,content){
        $("#"+id).popover({
            animation:true,
            html:false,
            placement:'right',
            trigger:'hover',
            content:content,
            delay:0,
            container:'body'
        });
        $("#"+id).popover("show");
    }
    //空字段显示为无,附件除外
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]&&p!='rmFileName') {
                o[p] = "无";
            }
        }
    }
});
