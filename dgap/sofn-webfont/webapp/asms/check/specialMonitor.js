var myapp = angular.module("myapp", []).controller("specialMonitor", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("专项监测");
    //获取查询条件缓存
    $scope.specialMonitorQueryParams = JSON.parse(window.sessionStorage.getItem("specialMonitorQueryParams"))

        //查询按钮
    $("#ToCheck").click(function () {

        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);

        var specialMonitorQueryParams = {};
        specialMonitorQueryParams.smName = $("input[name='smName']").val();
        specialMonitorQueryParams.smState = $("select[name='smState']").val();
        specialMonitorQueryParams.dateBegin = $("input[name='datebegin']").val();
        specialMonitorQueryParams.dateEnd = $("input[name='dateend']").val();
        specialMonitorQueryParams.smYear = $("input[name='queryYear']").val();
        window.sessionStorage.setItem("specialMonitorQueryParams",JSON.stringify(specialMonitorQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    if($scope.specialMonitorQueryParams){
        $("input[name='queryYear']").val($scope.specialMonitorQueryParams.smYear);
        $("select[name='smState']").val($scope.specialMonitorQueryParams.smState);
        $("input[name='datebegin']").val($scope.specialMonitorQueryParams.dateBegin);
        $("input[name='dateend']").val($scope.specialMonitorQueryParams.dateEnd);
        $("input[name='smName']").val($scope.specialMonitorQueryParams.smName);
    }

    /**
     * 任务列表
     * */
    var loadGrid = function(){
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/specialMonitor/list",
                headers:{
                    token:window.localStorage.getItem("token")
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
                        if (json.data.list[i].SMDATEBEGIN != null && json.data.list[i].SMDATEBEGIN != "" && json.data.list[i].SMDATEBEGIN != undefined) {
                            json.data.list[i].SMDATEBEGIN = new Date(parseInt(json.data.list[i].SMDATEBEGIN) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].SMDATEEND != null && json.data.list[i].SMDATEEND != "" && json.data.list[i].SMDATEEND != undefined) {
                            json.data.list[i].SMDATEEND = new Date(parseInt(json.data.list[i].SMDATEEND) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].CREATETIME != null && json.data.list[i].CREATETIME != "" && json.data.list[i].CREATETIME != undefined) {
                            json.data.list[i].CREATETIME = new Date(parseInt(json.data.list[i].CREATETIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    var specialMonitorQueryParams = JSON.parse(window.sessionStorage.getItem("specialMonitorQueryParams"));
                    if(specialMonitorQueryParams){
                        params.smName = specialMonitorQueryParams.smName;
                        params.smState = specialMonitorQueryParams.smState;
                        params.dateBegin = specialMonitorQueryParams.dateBegin;
                        params.dateEnd = specialMonitorQueryParams.dateEnd;
                        params.smYear = specialMonitorQueryParams.smYear;
                    }else{
                        params.smName = $("input[name='smName']").val();
                        params.smState = $("select[name='smState']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.smYear = $("input[name='queryYear']").val();
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
                    data: "SMNAME",
                    title: "任务名称",
                    width: "12%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>32){
                            return "<a id='SMNAME"+row.ID+"' onmouseover=showPopContent('SMNAME"+row.ID+"','"+data+"')>"+data.substring(0,32)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "SMYEAR",
                    title: "年度",
                    width: "4%"
                },
                {
                    data: "SMFILENUM",
                    title: "文件号",
                    width: "13%"
                },
                {
                    data: "SMDATEBEGIN",
                    title: "开始时间",
                    width: "8%"
                },
                {
                    data: "SMDATEEND",
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
                    data: "SMSTATE",
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
                    data: "SMPRIORITY",
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
                        var butt = '<a style="cursor:pointer;margin-right: 5px;" onclick="queryInfo(\'' + data + '\')" class="color">详情</a>  <a style="cursor:pointer;margin-right: 5px;" onclick="querySamples(\'' + data + '\',\'' + row.SMNAME + '\',\'' + row.SMSTATE + '\')" class="color">抽样单</a> <a style="cursor:pointer" onclick="queryReportsForQtUnit(\'' + data + '\')" class="color">牵头单位报告<br/></a>  <a style="cursor:pointer;margin-right: 5px;" onclick="queryReportsForCdUnit(\'' + data + '\',\'' + row.SMNAME + '\',\'' + row.SMSTATE + '\')" class="color">承担单位报告</a>';
                        //判断是否具有读写权限
                        if($scope.iswrite!=2){
                            return butt;
                        }
                        //任务仅创建者可以修改
                        if(row.CREATEBY&&row.CREATEBY.trim()!="admin" && row.CREATEBY.trim()!=$scope.user.id){
                            return butt;
                        }
                        //已发布,已废止的任务不能修改
                        if(row.SMSTATE != 0){
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
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'ZXJC');
        $state.go("index.content.asms/check/TaskSample/TaskSampleList");
    };

    //查看详情
    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });
    window.queryInfo = function (o) {
        $scope.specialMonitor = {};
        $("#TaskInfoModel").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/specialMonitor/getTaskById",
            method: "post",
            data: {"id": o}
        }).success(function (json) {
            $scope.specialMonitor = json.data;
            $scope.specialMonitor.smDateBegin = new Date(parseInt(json.data.smDateBegin) + 28800000).toISOString().slice(0, 10);
            $scope.specialMonitor.smDateEnd = new Date(parseInt(json.data.smDateEnd) + 28800000).toISOString().slice(0, 10);
            //downloadFile(json.data.smFile);
            //牵头单位
            $scope.specialMonitor.qtName = "";
            for (var i = 0; i < json.speList.length; i++) {
                if ($scope.specialMonitor.qtName == "") {
                    $scope.specialMonitor.qtName += json.speList[i].leadUnitName;
                } else {
                    $scope.specialMonitor.qtName += "," + json.speList[i].leadUnitName;
                }
            }
            $scope.checkData($scope.specialMonitor);
        });
    };

    //查看牵头单位报告
    window.queryReportsForQtUnit = function (obj) {
        window.localStorage.setItem("takeTheLeadReporListTaskId", obj);
        window.localStorage.setItem("takeTheLeadReporListTaskType", "specialMonitor");
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'ZXJC');
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
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'ZXJC');
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
                if (rows[k].SMSTATE != 0) {
                    $.jBox.tip("所选任务包括已发布或已废止任务，无法进行发布操作！");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if (new Date(rows[k].SMDATEEND).getTime() < (new Date(new Date().setHours(0, 0, 0, 0)).getTime())) {
                    $.jBox.tip("所选任务已过结束时间，无法进行发布操作！!");
                    return false;
                }
            }
        }
        //-
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            $.jBox.confirm("是否发布已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $("button").attr("disabled", true);
                    $.ajax({
                        url: "/sofn-asms-web/specialMonitor/rel",
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
        window.localStorage.removeItem("asms_Check_specialMonitor");
        window.localStorage.setItem("asms_Check_specialMonitor", data);
        $state.go("index.content.asms/check/specialMonitorAdd");
    };

    //废止
    $scope.abo = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
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
        var data = [];
        for(var i=0;i<rows.length;i++){
            if(rows[i].SMSTATE == 2){
                $.jBox.tip("所选任务包含已废止任务！");
                return false;
            }
            if(rows[i].SMSTATE == 0){
                $.jBox.tip("所选任务包含未发布任务！");
                return false;
            }
            if(new Date(rows[i].SMDATEEND).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())){
                $.jBox.tip("所选任务包含已结束任务!");
                return false;
            }
            data.push(rows[i].ID);
        }

        $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function (v) {
            if (v == true) {
                $("button").attr("disabled", true);
                $.ajax({
                    url: "/sofn-asms-web/specialMonitor/abo",
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
            if (rows[k].SMSTATE != 0&&rows[k].SMSTATE != 2) {
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
                    $("button").attr("disabled", true);
                    $.ajax({
                        url: "/sofn-asms-web/specialMonitor/del",
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

    /**
     * other
     * */
    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
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


    //清除存储信息
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("asms_Check_specialMonitor");
    };
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
        input1.attr('value', $scope.specialMonitor.smFile);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.specialMonitor.smFileName);
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
            if (!o[p]&&p!='smFileName') {
                o[p] = "无";
            }
        }
    }
});
