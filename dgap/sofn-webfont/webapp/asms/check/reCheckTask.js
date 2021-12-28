var myapp=angular.module("myapp",[]).controller("reCheckTask",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("监督抽查");
    //获取缓存查询参数
    $scope.reCheckTaskQueryParams = JSON.parse(window.sessionStorage.getItem("reCheckTaskQueryParams"));

    //查询按钮
    $("#ToCheck").click(function(){
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var reCheckTaskQueryParams = {};
        reCheckTaskQueryParams.recheckTaskName=$("input[name='recheckTaskName']").val();
        reCheckTaskQueryParams.recheckTaskYear=$("input[name='queryYear']").val();
        reCheckTaskQueryParams.state=$("select[name='state']").val();
        reCheckTaskQueryParams.dateBegin=$("input[name='datebegin']").val();
        reCheckTaskQueryParams.dateEnd=$("input[name='dateend']").val();
        window.sessionStorage.setItem("reCheckTaskQueryParams",JSON.stringify(reCheckTaskQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    if($scope.reCheckTaskQueryParams){
            $("input[name='queryYear']").val($scope.reCheckTaskQueryParams.recheckTaskYear);
            $("select[name='state']").val($scope.reCheckTaskQueryParams.state);
            $("input[name='datebegin']").val($scope.reCheckTaskQueryParams.dateBegin);
            $("input[name='dateend']").val($scope.reCheckTaskQueryParams.dateEnd);
            $("input[name='recheckTaskName']").val($scope.reCheckTaskQueryParams.recheckTaskName);
    }
    /**
     * 任务列表
     * */
    var loadGrid = function(){
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-asms-web/reCheckTask/list",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        //时间格式化
                        if(json.data.list[i].recheckTaskBegin!=null&&json.data.list[i].recheckTaskBegin!=""&&json.data.list[i].recheckTaskBegin!=undefined){
                            json.data.list[i].recheckTaskBegin=new Date(parseInt(json.data.list[i].recheckTaskBegin)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].recheckTaskEnd!=null&&json.data.list[i].recheckTaskEnd!=""&&json.data.list[i].recheckTaskEnd!=undefined){
                            json.data.list[i].recheckTaskEnd=new Date(parseInt(json.data.list[i].recheckTaskEnd)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].createTime!=null&&json.data.list[i].createTime!=""&&json.data.list[i].createTime!=undefined){
                            json.data.list[i].createTime=new Date(parseInt(json.data.list[i].createTime)+28800000).toISOString().slice(0,10);
                        }
                    }
                    return json.data.list;
                },
                data : function(params) {
                    var reCheckTaskQueryParams = JSON.parse(window.sessionStorage.getItem("reCheckTaskQueryParams"));
                    if(reCheckTaskQueryParams){
                        params.recheckTaskName=reCheckTaskQueryParams.recheckTaskName;
                        params.recheckTaskYear=reCheckTaskQueryParams.recheckTaskYear;
                        params.state=reCheckTaskQueryParams.state;
                        params.dateBegin=reCheckTaskQueryParams.dateBegin;
                        params.dateEnd=reCheckTaskQueryParams.dateEnd;
                    }else{
                        params.recheckTaskName=$("input[name='recheckTaskName']").val();
                        params.recheckTaskYear=$("input[name='queryYear']").val();
                        params.state=$("select[name='state']").val();
                        params.dateBegin=$("input[name='datebegin']").val();
                        params.dateEnd=$("input[name='dateend']").val();
                    }
                },
                type:"post"
            },
            columns : [
                {
                    data : "ck",
                    title : '<input id="checkAll"  type="checkbox"/>',
                    render : function(data, type, row) {
                        return '<input type="checkbox" name="ck"/>';
                    },
                    width: "3%"
                },
                {
                    data : 'id',
                    title : 'id',
                    visible : false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"
                },
                {
                    data : "recheckTaskName",
                    title : "任务名称",
                    width: "15%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>35){
                            return "<a id='recheckTaskName"+row.id+"' onmouseover=showPopContent('recheckTaskName"+row.id+"','"+data+"')>"+data.substring(0,35)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "recheckTaskYear",
                    title : "年度",
                    width: "5%"
                },
                {
                    data:"recheckUnitName",
                    title : "复检检测单位",
                    width:"14%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>20){
                            return "<a id='recheckUnitName"+row.id+"' onmouseover=showPopContent('recheckUnitName"+row.id+"','"+data+"')>"+data.substring(0,20)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data : "recheckTaskBegin",
                    title : "开始时间",
                    width: "8%"
                },
                {
                    data : "recheckTaskEnd",
                    title : "结束时间",
                    width: "8%"
                },
                {
                    data: "userName",
                    title: "创建者",
                    width: "6%",
                    render:function (data) {
                        return data||"admin";
                    }
                },
                {
                    data : "createTime",
                    title : "创建时间",
                    width: "8%"
                },
                {
                    data : "state",
                    title : "任务状态",
                    width : "9%",
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
                    data: "recheckTaskPriority",
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
                    data : "id",
                    title : "报告",
                    render : function(data, type, row) {
                        var butt =  '<a style="cursor:pointer;margin-right: 5px;" onclick="queryInfo(\''+data+'\')">详情</a> <a style="cursor:pointer;margin-right: 5px;"  onclick="queryReports(\''+data+'\')">报告</a>';
                        //判断是否具有读写权限
                        if($scope.iswrite!=2){
                            return butt;
                        }
                        // 任务仅创建者可以修改
                        if(row.createBy&&row.createBy.trim()!="admin" && row.createBy.trim()!=$scope.user.id){
                            return butt;
                        }
                        //已发布,已废止的任务不能修改
                        if(row.state != 0){
                            return butt;
                        }
                        return butt + ' <a style="cursor:pointer;margin-right: 5px;" onclick="upd(\'' + data + '\')">修改</a>';
                    }
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
    //查看详情
    $('#TaskInfoModel').modal({
        keyboard: true,
        show:false
    });
    window.queryInfo=function(o){
        $scope.reCheckTask = {};
        $("#TaskInfoModel").modal("show");
        //基本信息
        $http({
            url:"/sofn-asms-web/reCheckTask/getTaskById",
            headers:{
                token:window.localStorage.getItem("token")
            },
            method:"post",
            data:{"id":o}
        }).success(function(json){
            $scope.reCheckTask = json.data;
            $scope.reCheckTask.recheckTaskBegin = new Date(parseInt(json.data.recheckTaskBegin)+28800000).toISOString().slice(0,10);
            $scope.reCheckTask.recheckTaskEnd = new Date(parseInt(json.data.recheckTaskEnd)+28800000).toISOString().slice(0,10);
            if( $scope.reCheckTask.initTaskType=="1"){
                $scope.reCheckTask.initTaskType="例行监测"
            }
            if( $scope.reCheckTask.initTaskType=="2"){
                $scope.reCheckTask.initTaskType="专项检测"
            }
            if( $scope.reCheckTask.initTaskType=="3"){
                $scope.reCheckTask.initTaskType="监督抽查"
            }

        });
        //关联信息获取
        $("#Fjtable tbody").html("<tr><td colspan='3'>表中数据为空</td></tr>");
        $http({
            url:"/sofn-asms-web/reCheckTask/getObjById",
            headers:{
                token:window.localStorage.getItem("token")
            },
            method:"post",
            data:{id:o}
        }).success(function(obj){
            var datas = obj.data;
            if (datas.length > 0){
                $("#Fjtable tbody").html("");
                for (var i = 1; i <=datas.length; i++){
                    var td = datas[i-1];
                    var Row = "<tr><td style='text-align: center'>"+td.recheckSampleName+"</td> <td style='text-align: center'>"+td.recheckSampleCode+"</td><td style='text-align: center'>"+td.testedDeparment+"</td></tr>";
                    if($("#Fjtable tbody").children().length==0){
                        $("#Fjtable tbody").prepend(Row);
                    }else {
                        $("#Fjtable tbody tr:last").after(Row);
                    }
                }
            }
        }).error(function(){
        });
        $scope.checkData($scope.reCheckTask);
    };

    //查看报告
    window.queryReports=function(obj){
        window.localStorage.removeItem("asms_Check_checkTask_forReport");
        window.localStorage.setItem("asms_Check_checkTask_forReport",obj);
        window.localStorage.removeItem("asms_Check_Task_Type_Title");
        window.localStorage.setItem("asms_Check_Task_Type_Title",'FJRW');
        $state.go("index.content.asms/check/TaskReport/TaskReportList");
    };
    //发布
    $scope.rel=function(){
        //已发布或废止任务无法进行发布操作
        var rows = $scope.getSelectLinaDates();
        if(rows){
            for(var n = 0; n < rows.length; n++){
                if(rows[n].createBy.trim()!="admin" && rows[n].createBy.trim()!=$scope.user.id){
                    $.jBox.tip("没有权限,任务仅创建者可以发布!");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if(rows[k].state != 0){
                    $.jBox.tip("所选任务包括已发布或已废止任务，无法进行发布操作！");
                    return false;
                }
            }
            for (var k = 0; k < rows.length; k++) {
                if(new Date(rows[k].recheckTaskEnd).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())){
                    $.jBox.tip("所选任务已过结束时间，无法进行发布操作!");
                    return false;
                }
            }

        }
        //-
        var i = $scope.getSelectRowsIds();
        if (i!=false){
            $.jBox.confirm("是否发布已选择任务？", "等待服务器响应", function(v){
                if(v==true){
                    var data = {
                        jsonString : JSON.stringify(i)
                    };
                    $("button").attr("disabled",true);
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
                                $.jBox.tip("成功！");
                            }else if (info == "false"){
                                $.jBox.tip("失败！");
                            }else if(info == "NoObj"){
                                $.jBox.tip(data.data.msg);
                            }
                            $scope.TableReload();
                        },
                        error: function (data) {
                            $("button").attr("disabled",false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                            $scope.TableReload();
                        }
                    });
                }
                return true;
            }, { buttons: { '确认': true, '取消': false} });
        }
    };

    //修改
    window.upd=function(data){
        window.localStorage.removeItem("asms_Check_recheckTask");
        window.localStorage.setItem("asms_Check_recheckTask",data);
        $state.go("index.content.asms/check/reCheckTaskAdd");
    };

    //废止
    $scope.abo=function(){

        var rows = $("#grid").DataTable().rows('.selected').data();
        var data = [];
        if(rows.length<=0){
            $.jBox.tip("请至少选择一条记录！");
            return false;
        }
        for(var k = 0;k<rows.length;k++){
            if(rows[k].createBy.trim()!="admin" && rows[k].createBy.trim()!=$scope.user.id){
                $.jBox.tip("没有权限,任务仅创建者可以废止!");
                return false;
            }
        }
        for(var i=0;i<rows.length;i++){
            if(rows[i].state == 2){
                $.jBox.tip("所选任务包含已废止任务！");
                return false;
            }
            if(rows[i].state == 0){
                $.jBox.tip("所选任务包含未发布任务！");
                return false;
            }
            if(new Date(rows[i].recheckTaskEnd).getTime()<(new Date(new Date().setHours(0,0,0,0)).getTime())){
                $.jBox.tip("所选任务包含已结束任务!");
                return false;
            }
            data.push(rows[i].id);
        }
        $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function(v){
            if(v==true){
                $("button").attr("disabled",true);
                $.ajax({
                    url:"/sofn-asms-web/reCheckTask/abo",
                    type:"post",
                    dataType:"json",
                    data:{jsonString:JSON.stringify(data)},
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    beforeSend: function(){
                        $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                        $("#web_app_model_userOprXz").modal("show");
                    },
                    success:function(data){
                        $("button").attr("disabled",false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("成功！");
                        $scope.TableReload();
                    },
                    error: function (data) {
                        $("button").attr("disabled",false);
                        $("#web_app_model_userOprXz").modal("hide");
                        $.jBox.tip("失败");
                    }
                });
            }
            return true;
        }, { buttons: { '确认': true, '取消': false} });
    };

    //新增任务
    $scope.addTask=function(){
        window.localStorage.removeItem("asms_Check_recheckTask");
    };

    //删除
    $scope.del=function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if(length == 0){
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        for (var n = 0; n < length; n++) {
            if (rows[n].createBy.trim() != "admin" && rows[n].createBy.trim() != $scope.user.id) {
                $.jBox.tip("没有权限,任务仅创建者可以删除!");
                return false;
            }
        }
        for (var k=0;k<length;k++){
            if (rows[k].state != 0&&rows[k].state != 2){
                $.jBox.tip("删除操作仅限于未发布或已废止任务！");
                return false;
            }
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }

        if (rows!=false){
            $.jBox.confirm("是否删除已选择任务？", "等待服务器响应", function(v){
                if(v==true){
                    var data = {
                        jsonString : JSON.stringify(ids)
                    };
                    $("button").attr("disabled",true);
                    $.ajax({
                        url:"/sofn-asms-web/reCheckTask/del",
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
                            $.jBox.tip("成功！");
                            $scope.TableReload();
                        },
                        error: function (data) {
                            $("button").attr("disabled",false);
                            $("#web_app_model_userOprXz").modal("hide");
                            $.jBox.tip("失败");
                        }
                    });
                }
                return true;
            }, { buttons: { '确认': true, '取消': false} });
        }
    };


    $scope.cleanCookie=function(){
        window.localStorage.removeItem("asms_Check_recheckTask");
    };

    //获取选择行记录id集合
    $scope.getSelectRowsIds = function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if(length<=0){
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }
        return ids;
    };

    //表格刷新
    $scope.TableReload = function(){
        $('#grid').dataTable().api().ajax.reload();
    };

    //获取所选记录集合的行数据
    $scope.getSelectLinaDates = function(){
        var rows = $("#grid").DataTable().rows('.selected').data();
        if(rows.length <=0){
            $.jBox.tip("请至少选择一行记录！");
            return false;
        }
        return rows;
    };

    //查询条件重置
    $scope.cleanQueryPar = function(){
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='queryYear']").val("");
        $("input[name='recheckTaskName']").val("");
        $("select[name='state']").val("");
        $("#grid").dataTable().api().ajax.reload();
    };

    // //初始隐藏
    // $("#FJ_DX").hide();//复检对象
    // //页签操作
    // $scope.myTab = function(o){
    //     $("#JB_XX").hide();//基本信息
    //     $("#FJ_DX").hide();//复检对象
    //     if(o=="基本信息"){
    //         $("#JB_XX").show();//新任务
    //     }
    //     if(o=="复检对象"){
    //         $("#FJ_DX").show();//历史任务
    //     }
    // };
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
