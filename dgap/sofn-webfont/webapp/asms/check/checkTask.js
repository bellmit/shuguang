var myapp = angular.module("myapp", []).controller("checkTask", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("监督抽查");
    //获取查询条件缓存参数
    $scope.checkTaskQueryParams = JSON.parse(window.sessionStorage.getItem("checkTaskQueryParams"));


    /**
     * 任务列表
     * */
    //查询按钮
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var checkTaskQueryParams = {};
        checkTaskQueryParams.taskName = $("input[name='taskName']").val();
        checkTaskQueryParams.state = $("select[name='state']").val();
        checkTaskQueryParams.dateBegin = $("input[name='datebegin']").val();
        checkTaskQueryParams.dateEnd = $("input[name='dateend']").val();
        checkTaskQueryParams.querySheng = $("#querySheng").val();
        checkTaskQueryParams.queryShi = $("#queryShi").val();
        checkTaskQueryParams.queryXian = $("#queryXian").val();
        window.sessionStorage.setItem("checkTaskQueryParams",JSON.stringify(checkTaskQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    $scope.resetQuery = function(){
        $("#queryForm").form("reset");
        $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.regionId);
        if ("1" == $scope.orgLevel) {
            //部级
            $("#BjAddTaskBut").show();
            $("#BjupdTaskBut").show();
        } else if ("2" == $scope.orgLevel || "3" ==  $scope.orgLevel || "4" ==  $scope.orgLevel) {
            //省级 市级 县级
            $("#SjAddTaskBut").show();
            $("#SjupdTaskBut").show();
            $("#areaSpan").show();
            $("#queryShengDiv").show();
            $("#queryShiDiv").show();
            $("#queryXianDiv").show();
            if ($scope.orgLevel == "2") {
                $("#querySheng").attr({"disabled": "disabled"});
            } else if ($scope.orgLevel == "3") {
                $("#querySheng").attr({"disabled": "disabled"});
                $("#queryShi").attr({"disabled": "disabled"});
            } else if ($scope.orgLevel == "4") {
                $("#querySheng").attr({"disabled": "disabled"});
                $("#queryShi").attr({"disabled": "disabled"});
                $("#queryXian").attr({"disabled": "disabled"});
            }
        }

    }
    var loadGrid = function(){
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/checkTask/list",
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
                        if (json.data.list[i].TASKBEGINTIME != null && json.data.list[i].TASKBEGINTIME != "" && json.data.list[i].TASKBEGINTIME != undefined) {
                            json.data.list[i].TASKBEGINTIME = new Date(parseInt(json.data.list[i].TASKBEGINTIME) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].TASKENDTIME != null && json.data.list[i].TASKENDTIME != "" && json.data.list[i].TASKENDTIME != undefined) {
                            json.data.list[i].TASKENDTIME = new Date(parseInt(json.data.list[i].TASKENDTIME) + 28800000).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].CREATETIME != null && json.data.list[i].CREATETIME != "" && json.data.list[i].CREATETIME != undefined) {
                            json.data.list[i].CREATETIME = new Date(parseInt(json.data.list[i].CREATETIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.taskLevel = $scope.orgLevel;
                    var checkTaskQueryParams = JSON.parse(window.sessionStorage.getItem("checkTaskQueryParams"));
                    if(checkTaskQueryParams){
                        params.taskName = checkTaskQueryParams.taskName;
                        params.state = checkTaskQueryParams.state;
                        params.dateBegin = checkTaskQueryParams.dateBegin;
                        params.dateEnd = checkTaskQueryParams.dateEnd;
                        params.querySheng = checkTaskQueryParams.querySheng;
                        params.queryShi = checkTaskQueryParams.queryShi;
                        params.queryXian = checkTaskQueryParams.queryXian;
                    }else{
                        params.taskName = $("input[name='taskName']").val();
                        params.state = $("select[name='state']").val();
                        params.dateBegin = $("input[name='datebegin']").val();
                        params.dateEnd = $("input[name='dateend']").val();
                        params.querySheng = $("#querySheng").val();
                        params.queryShi = $("#queryShi").val();
                        params.queryXian = $("#queryXian").val();
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
                    width: "2%"
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
                    width: "3%"
                },
                {
                    data: "TASKNAME",
                    title: "任务名称",
                    width: "12%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='TASKNAME"+row.ID+"' onmouseover=showPopContent('TASKNAME"+row.ID+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "TASKYEAR",
                    title: "年度",
                    width: "5%"
                },
                {
                    data: "TASKBEGINTIME",
                    title: "开始时间",
                    width: "8%"
                },
                {
                    data: "TASKENDTIME",
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
                    data: "STATE",
                    title: "任务状态",
                    width: "8%",
                    render:function (data,type,row) {
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
                    data: "TASKPRIORITY",
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
                    title: "报告",
                    render: function (data, type, row) {
                        var butt =  '<a style="cursor:pointer;margin-right: 5px;" onclick="queryInfo(\'' + data + '\')" class="color">详情</a>  <a style="cursor:pointer;margin-right: 5px;" onclick="queryReports(\'' + data + '\')" class="color">报告</a>  <a style="cursor:pointer;margin-right: 5px;" onclick="querySamples(\'' + data + '\')" class="color">抽样单</a>';
                        //判断是否具有读写权限
                        if($scope.iswrite!=2){
                            return butt;
                        }
                        // 任务仅创建者可以修改
                        if(row.CREATEBY&&row.CREATEBY.trim()!="admin" && row.CREATEBY.trim()!=$scope.user.id){
                            return butt;
                        }
                        //已发布,已废止的任务不能修改
                        if(row.STATE!=0){
                            return butt;
                        }
                        if(row.TASKLEVEL==0){
                            butt = butt + ' <a style="cursor:pointer;margin-right: 5px;" onclick="updBj(\'' + data + '\')">修改</a>';
                        }else{
                            butt = butt + ' <a style="cursor:pointer;margin-right: 5px;" onclick="upd(\'' + data + '\')">修改</a>';
                        }
                        return butt;
                    },
                    width: "12%"
                }
            ]
        });
    }

    $(function () {
        /**
         * 获取当前用户信息
         */
        //从缓存中获取数据
        if(window.localStorage.getItem("asmsAllSysDictAndUserInfo")){
            //获取用户信息
            $scope.user = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['user'];
            $scope.organization = JSON.parse(window.localStorage.getItem("asmsAllSysDictAndUserInfo"))['organization'];
            $scope.orgLevel = $scope.organization.orgLevel;
            //定义一个管辖区域，通过机构级别+区域来判断
            $scope.regionId = $scope.organization.regionId;
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.regionId);
            if ("1" == $scope.orgLevel) {
                //部级
                $("#BjAddTaskBut").show();
                $("#BjupdTaskBut").show();
            } else if ("2" == $scope.orgLevel || "3" == $scope.orgLevel || "4" == $scope.orgLevel) {
                //省级 市级 县级
                $("#SjAddTaskBut").show();
                $("#SjupdTaskBut").show();
                $("#areaSpan").show();
                $("#queryShengDiv").show();
                $("#queryShiDiv").show();
                $("#queryXianDiv").show();
                if ($scope.orgLevel == "2") {
                    $("#querySheng").attr({"disabled": "disabled"});
                } else if ($scope.orgLevel == "3") {
                    $("#querySheng").attr({"disabled": "disabled"});
                    $("#queryShi").attr({"disabled": "disabled"});
                } else if ($scope.orgLevel == "4") {
                    $("#querySheng").attr({"disabled": "disabled"});
                    $("#queryShi").attr({"disabled": "disabled"});
                    $("#queryXian").attr({"disabled": "disabled"});
                }
            }
            loadGrid();
            setTimeout(function(){
                if($scope.checkTaskQueryParams){
                    $("input[name='datebegin']").val($scope.checkTaskQueryParams.dateBegin);
                    $("input[name='dateend']").val($scope.checkTaskQueryParams.dateEnd);
                    $("select[name='state']").val($scope.checkTaskQueryParams.state);
                    $("input[name='taskName']").val($scope.checkTaskQueryParams.taskName);
                    if($scope.checkTaskQueryParams.queryXian){
                        $.fn.mycity("querySheng", "queryShi", "queryXian",$scope.checkTaskQueryParams.queryXian);
                    }else if($scope.checkTaskQueryParams.queryShi){
                        $.fn.mycity("querySheng", "queryShi", "queryXian",$scope.checkTaskQueryParams.queryShi);
                    }else if($scope.checkTaskQueryParams.queryXian){
                        $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.checkTaskQueryParams.queryXian);
                    }else{
                        $.fn.mycity("querySheng", "queryShi", "queryXian");
                    }
                }
            },50)
        }else{
            swal('登录超时', '由于您长时间未操作,请重新登录!', 'warning');
            setTimeout(function(){
                window.location.href = '#/login/login';
                window.location.reload();
            },2500)
        }
    });

    /**
     * 任务操作
     * */
    //查看详情
    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });
    window.queryInfo = function (o) {
        $scope.checkTask = {};
        $("#TaskInfoModel").modal("show");
        //基本信息
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getTaskById",
            method: "post",
            data: {id: o}
        }).success(function (obj) {
            $scope.checkTask = obj.data;
            $scope.checkTask.taskBeginTime = new Date(parseInt(obj.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
            $scope.checkTask.taskEndTime = new Date(parseInt(obj.data.taskEndTime) + 28800000).toISOString().slice(0, 10);
            $scope.checkTask.taskSampleDeadline = new Date(parseInt(obj.data.taskSampleDeadline) + 28800000).toISOString().slice(0, 10);
            if ($scope.checkTask.taskAreaId != null && $scope.checkTask.taskAreaId != undefined && $scope.checkTask.taskAreaId != "") {
                $scope.checkTask.taskAreaId = $.fn.Cityname($scope.checkTask.taskAreaId);
            } else {
                $scope.checkTask.taskAreaId = "无";
            }
            if ($scope.checkTask.taskLevel == "1") {
                //省级
                if ($scope.checkTask.parentTaskName == null) {
                    $scope.checkTask.parentTaskName = "无"
                }
                $("#myTab").show();
                $("div[name='cyUnit']").show();
                $("div[name='jcUnit']").show();
                $("div[name='taskAreaId']").show();
                $("div[name='parentTaskName']").show();//上级任务
                showJcInfoForSj(o);//监测信息
            } else {
                //部级
                $("#myTab").hide();//隐藏监测信息页签
                $("div[name='cdUnit']").show();
                $("div[name='fileCode']").show();
                $("div[name='remark']").show();
            }
            //downloadFile(obj.data.files);
            //承担单位
            $scope.checkTask.cdUnits = "";
            if (obj.speList.length != 0) {
                for (var i = 0; i < obj.speList.length; i++) {
                    if ($scope.checkTask.cdUnits == "") {
                        $scope.checkTask.cdUnits += obj.speList[i].leadUnitName;
                    } else {
                        $scope.checkTask.cdUnits += "," + obj.speList[i].leadUnitName;
                    }
                }
            }
            $scope.checkData($scope.checkTask);
        }).error(function (data) {
            
        });
    };

    //任务发布
    $scope.rel = function () {
        //已发布或废止任务无法进行发布操作
        var rows = $scope.getSelectLinaDates();
        var nowTime = new Date().toISOString().slice(0, 10);
        if (rows) {
            for(var n = 0; n < rows.length; n++){
                if(rows[n].CREATEBY.trim()!="admin" && rows[n].CREATEBY.trim()!=$scope.user.id){
                    $.jBox.tip("没有权限,任务仅创建者可以发布!");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if (rows[k].STATE!=0) {
                    $.jBox.tip("所选任务包括已发布或已废止任务，无法进行发布操作！");
                    return false;
                }
            }
            for(var k = 0;k<rows.length;k++){
                if (nowTime > rows[k].TASKENDTIME) {
                    $.jBox.tip("所选任务已过结束时间，无法进行发布操作!");
                    return false;
                }
            }
        };
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            $.jBox.confirm("是否发布已选择任务？", "等待服务器响应", function (v) {
                if (v == true) {
                    var data = {
                        jsonString: JSON.stringify(i)
                    };
                    $("button").attr("disabled", true);
                    $.ajax({
                        url: "/sofn-asms-web/checkTask/rel",
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
                            if (data.data == "true") {
                                $.jBox.tip("成功！");
                            } else if (data.data == "false") {
                                $.jBox.tip("失败！");
                            } else if (data.data == "NoObj") {
                                $.jBox.tip(data.msgInfo);
                            }
                            $scope.TableReload();
                        },
                        error: function (data) {
                            $("button").attr("disabled", false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip(data.msg);
                        }
                    });
                }
                return true;
            }, {buttons: {'确认': true, '取消': false}});
        }
    };

    //修改(省级)已发布任务无法修改
    window.upd = function (data) {
        window.localStorage.removeItem("asms_checktask_taskinfo_taskid");
        window.localStorage.setItem("asms_checktask_taskinfo_taskid", data);
        $state.go("index.content.asms/check/checkTaskAddSj");
    };

    //修改(部级)已发布任务无法进行修改
    window.updBj = function (data) {
        window.localStorage.removeItem("asms_Check_BjTask");
        window.localStorage.setItem("asms_Check_BjTask", data);
        $state.go("index.content.asms/check/checkTaskAddBj");
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
            if(rows[i].STATE == 2){
                $.jBox.tip("所选任务包含已废止任务！");
                return false;
            }
            if(rows[i].STATE == 0){
                $.jBox.tip("所选任务包含未发布任务！");
                return false;
            }
            if(new Date(rows[i].TASKENDTIME).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())){
                $.jBox.tip("所选任务包含已结束任务!");
                return false;
            }
            data.push(rows[i].ID);
        }
        $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function (v) {
            if (v == true) {
                $("button").attr("disabled", true);
                $.ajax({
                    url: "/sofn-asms-web/checkTask/abo",
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

    //查看报告
    window.queryReports = function (obj) {
        window.localStorage.removeItem("asms_Check_checkTask_forReport");
        window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'JDCC');
        $state.go("index.content.asms/check/TaskReport/checkTaskReportList");
    };

    //查看抽样单
    window.querySamples = function (obj) {
        window.localStorage.removeItem("asms_Check_checkTask_forReport");
        window.localStorage.setItem("asms_Check_checkTask_forReport", obj);
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title", 'JDCC');
        $state.go("index.content.asms/check/TaskSample/TaskSampleList");
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
            if (rows[k].STATE != 0&&rows[k].STATE != 2){
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
                        url: "/sofn-asms-web/checkTask/del",
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

    $scope.cleanCookie = function () {
        window.localStorage.removeItem("asms_Check_BjTask");
        window.localStorage.removeItem("asms_checktask_taskinfo_taskid");
        window.localStorage.removeItem("inspectionIdForCheckTask");
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

    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function () {
        var rows = $("#grid").DataTable().rows('.selected').data();
        if (rows.length <= 0) {
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
    };

    //表格刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
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
        input1.attr('value', $scope.checkTask.attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.checkTask.attachmentName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //初始隐藏
    $("#FJ_DX").hide();//监测信息
    //页签操作
    $scope.myTab = function (o) {
        $("#JB_XX").hide();//基本信息
        $("#FJ_DX").hide();//监测信息
        if (o == "基本信息") {
            $("#JB_XX").show();//基本信息
        }
        if (o == "监测信息") {
            $("#FJ_DX").show();//监测信息
        }
    };

    //显示监测信息
    window.showJcInfoForSj = function (o) {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getObjById",
            method: "post",
            data: {id: o}
        }).success(function (obj) {
            $("#Fjtable tbody").html("");
            var datas = obj.data;
            if (datas.length > 0) {
                for (var i = 1; i <= datas.length; i++) {
                    var td = datas[i - 1];
                    var Row = "<tr><td>" + td.productName + "</td><td>" + td.productType + "</td> <td>" + td.monitorNum + "</td><td>" + $.fn.Cityname(td.areaId) + "</td></tr>";
                    if ($("#Fjtable tbody").children().length == 0) {
                        $("#Fjtable tbody").prepend(Row);
                    } else {
                        $("#Fjtable tbody tr:last").after(Row);
                    }
                }
            }
        }).error(function (data) {
            
        });
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
            if (!o[p]&&p!='attachmentName') {
                o[p] = "无";
            }
        }
    }
});
