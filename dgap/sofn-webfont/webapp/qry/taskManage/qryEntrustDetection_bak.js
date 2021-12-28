var myapp = angular.module("myapp", []).controller("qryEntrustDetection", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function(item,i){
        if(item.text=="监督抽查任务"){
            $(".qryMyCheckTask").css("display","inline-block");
        }
    });
    //只读权限控制
    $scope.authorityArray = window.localStorage.getItem("menus");
    var authorityArray = JSON.parse($scope.authorityArray);
    var author ="";
    for(var i =0; i < authorityArray.length;i++){
        if(authorityArray[i].id == "513"){
            if(authorityArray[i].authority == "1"){
                author = authorityArray[i].authority;
                $("#aboId").attr("disabled", true);
                $("#delId").attr("disabled", true);
                $("#relId").attr("disabled", true);
                $("#addTaskId").attr("disabled", true);
                window.localStorage.setItem("alesEntrustDetection_subset_authority",authorityArray[i].authority);
                break;
            }
        }
    }
    /**
     * 获取当前登录用户Id
     * 注释日期:2018年2月28日11:14:50
     */
    $scope.entrustDetectionId='';
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/llesEntrustDetection/getCreateBy",
        data:"2",
        method:"post"
    }).success(function(data) {
        $scope.entrustDetectionId =data.data;
    }).error(function (msg) {
        console.log(msg);
        
    });
    /**
     * 任务信息列表
     * */
    //查询按钮
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
//绑定回车事件
   $('#taskNameId').keydown(function (e) {
       if(e.keyCode == 13){
           $("#grid").dataTable().api().ajax.reload();
       }
   });
    $(function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-qry-web/alesEntrustDetection/list",
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
                        //任务状态
                        if (json.data.list[i].STATE != null && json.data.list[i].STATE != "" && json.data.list[i].STATE != undefined) {
                            if (json.data.list[i].STATE == '0') {
                                json.data.list[i].STATE = '未发布';
                            }
                            if (json.data.list[i].STATE == '1') {
                                json.data.list[i].STATE = '已发布';
                            }
                            if (json.data.list[i].STATE == '2') {
                                json.data.list[i].STATE = '已废止';
                            }
                            if (json.data.list[i].STATE =='3') {
                                json.data.list[i].STATE = '执行中';
                            }
                            if (json.data.list[i].STATE =='4') {
                                json.data.list[i].STATE = '已结束，未完成';
                            }
                            if (json.data.list[i].STATE =='5') {
                                json.data.list[i].STATE = '已结束，已完成';
                            }
                        }
                    }
                    return json.data.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },error:function (msg) {
                    console.log(msg);
                    
                },
                data: function (params) {
                    params.taskName = $("input[name='taskName']").val();
                    params.taskyear = $("input[name='taskyear']").val();
                    params.state = $("select[name='state']").val();
                    params.beginTime = $("input[name='beginTime']").val();
                    params.endTime = $("input[name='endTime']").val();
                    params.token = window.localStorage.getItem("token");
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
                    width: "3%"
                },
                {
                    data: "TASKYEAR",
                    title: "年度",
                    width: "4%"
                },
                {
                    data: "TASKNAME",
                    title: "任务名称",
                    render: function(data, type, row, meta) {
                        if (data) {
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                    width: "10%"
                },
                {
                    data: "STUNITNAME",
                    title: "受托单位",
                    render: function(data, type, row, meta) {
                        if (data) {
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '">' + showdata + '</div>';
                        } else {
                            return '--';
                        }
                    },
                    width: "10%"
                },
                {
                    data: "TASKBEGINTIME",
                    title: "开始时间",
                    width: "6%"
                },
                {
                    data: "TASKENDTIME",
                    title: "结束时间",
                    width: "6%"
                },
                {
                    data: "CREATETIME",
                    title: "创建时间",
                    width: "6%"
                },
                {
                    data: "STATE",
                    title: "任务状态",
                    width: "6%",
                    render: function (data,type,row) {
                        if(data == '已废止'){
                            return '<span name ="state" style="color: #ff0d06;font-weight: bold;">'+data+'</span>';
                        }
                        return '<span name = "state" STYLE="color: #1f934b;font-weight: bold;">'+data+'</span>';
                    }
                },
                {
                    data:"CREATEBY",
                    title:"",
                    visible: false
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                      /*return '<a style="cursor: pointer;"onclick="queryInfo(\'' + data + '\')">详情</a> <a style="cursor: pointer;"onclick="upd(\'' + data + '\',\'' + row.STATE + '\')">修改</a> <a style="cursor: pointer;"onclick="entrustDetections(\'' + data + '\')">配置任务</a>  <a style="cursor: pointer;"onclick="dowLoadReport(\'' + data + '\')">报告</a>';*/
                        return '<a style="cursor: pointer;"onclick="queryInfo(\'' + data + '\')">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor: pointer;" onclick="dowLoadReport(\'' + data + '\')">报告</a>';
                        //}
                    },
                    width: "5%"
                }
            ]
        });
    });
    /*
     重置查询条件
     */
    $scope.cleeanAlesEntrust = function () {
        $("input[name='taskName']").val("");
        $("input[name='taskyear']").val("");
        $("select[name='state']").val("");
        $("input[name='beginTime']").val("");
        $("input[name='endTime']").val("");
        $("#grid").dataTable().api().ajax.reload();
    };

    /**
     *报告下载
     * */
    window.dowLoadReport = function (data) {
        window.localStorage.removeItem("qry_wtTaskInfo_ID_Report");
        window.localStorage.setItem("qry_wtTaskInfo_ID_Report", data);
        $state.go("index.content.qry/taskManage/taskReport/qryEntrustDetectionReport")
    };
    //配置任务
   /* window.entrustDetections = function (data) {
        window.localStorage.removeItem("ales_wtTaskInfo_ID_DetecTionObject");
        window.localStorage.setItem("ales_wtTaskInfo_ID_DetecTionObject", data);
        $state.go("index.content.ales/taskManage/alesEntrustDetectionObject/alesEntrustDetectionObject")
    };*/

    /**
     * 任务信息查看
     * */
    $('#TaskInfoModel').modal({
        keyboard: true,
        show: false
    });
    //查看报告
    $scope.EntrustDetectionInfo = {};
    window.queryInfo = function (data) {
        $scope.EntrustDetectionInfo = {};
        $('#TaskInfoModel').modal('show');
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/alesEntrustDetection/getTaskById",
            method: "post",
            data: {id: data}
        }).success(function (obj) {
            $scope.EntrustDetectionInfo = obj.data;
            $scope.EntrustDetectionInfo.taskBeginTime = new Date(parseInt(obj.data.taskBeginTime) + 28800000).toISOString().slice(0, 10);
            $scope.EntrustDetectionInfo.taskEndTime = new Date(parseInt(obj.data.taskEndTime) + 28800000).toISOString().slice(0, 10);
            downloadFile(obj.data.fileUrl);
        }).error(function (msg) {
            console.log(msg);
            
        });
    };

    //数据导出
    $scope.exportData=function(){
        //提交数据
        var taskYear = $("input[name='taskyear']").val();
        var state = $("select[name='state']").val();
        console.log("----------"+state);
        var taskName = $("input[name='taskName']").val();
        var beginTime = $("input[name='beginTime']").val();
        var endTime = $("input[name='endTime']").val();
        var taskName=$("input[name='taskName']").val();
        var token = window.localStorage.getItem("token");
        var form=$("<form>");
        form.attr('style','display:none');
        form.attr('target','');
        form.attr('method','post');
        form.attr('action',"/sofn-qry-web/alesEntrustDetection/exportSubjSv?token="+token+'&taskName='+taskName+'&beginTime='+beginTime+'&endTime='+endTime+'&taskYear='+taskYear+'&state='+state);
        $('body').append(form);
        form.submit();
    }
    //缓存清理
    $scope.cleanCookie = function () {
        window.localStorage.removeItem("ales_taskManage_alesEntrustDetectionOpr");
        window.localStorage.removeItem("ales_taskManage_alesEntrustDetectionOpr_Index");
        window.localStorage.removeItem("Cy_Info_Back_IDX_CODE");
    };

    // window.checkThisBox = function (o) {
    //     if (o.checked) {
    //         o.checked = false;
    //         $(o).removeClass("selected");
    //     } else {
    //         o.checked = true;
    //         $(o).addClass("selected");
    //     }
    // };

    //全选/全不选
    // $("#checkAll").click(function () {
    //     if ($(this).prop("checked") == true) {
    //         $("#grid input[type='checkbox']").prop("checked", true);
    //         $("#grid tbody tr").addClass("selected");
    //     } else {
    //         $("#grid input[type='checkbox']").prop("checked", false);
    //         $("#grid tbody tr").removeClass("selected");
    //     }
    // });

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

    //附件下载
    window.downloadFile = function (o) {
        $("#fileUrlDownLoad").html('');
        $("#fileUrlDownLoad").append('<span class="col-lg-4 col-md-4 col-sm-4" style="text-align: right;padding-right: 10px;">附件&nbsp;</span>');
        if (o != undefined && o != null && o != "" && o != "|") {
            var rs;
            rs = o.split("|");
            for (var i = 0; i < rs.length; i++) {
                if (rs[i] != undefined && rs[i] != null && rs[i] != "") {
                    var fileName = rs[i].substr(rs[i].lastIndexOf('\/') + 1);
                    $("#fileUrlDownLoad").append('&nbsp;&nbsp;附件' + i + ':<a href="http://' + rs[i] + '">' + fileName + '</a>');
                }
            }
        } else {
            $("#fileUrlDownLoad").append('<span>&nbsp;&nbsp;无</span>');
        }
    };
    /* /!**
     * 任务操作
     * *!/
     //发布
     $scope.rel = function () {
     var rows = $scope.getSelectLinaDates();
     if (rows) {
     for (var k = 0; k < rows.length; k++) {
     if (rows[k].STATE == '已发布' || rows[k].STATE == '已废止') {
     $.jBox.tip("所选任务包括已发布任务，无法进行发布操作！");
     return false;
     }
     var date = new Date();
     date = date.format("yyyy-MM-dd");
     if (rows[k].TASKENDTIME < date) {
     $.jBox.tip("所选任务已有任务时间已到期，无法发布！");
     return false;
     }
     }
     }
     //-
     var i = $scope.getSelectRowsIds();
     if (i != false) {
     $.jBox.confirm("是否批量发布已选择任务？", "等待服务器响应", function (v) {
     if (v == true) {
     var data = {
     jsonString: JSON.stringify(i)
     };
     $("button").attr("disabled", true);
     $.ajax({
     url: "/sofn-ales-web/llesEntrustDetection/rel",
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
     error:function (msg) {
     console.log(msg);
     
     } ,
     success: function (data) {
     $("button").attr("disabled", false);
     $("#web_app_model_userOprXz").modal("hide");
     console.info(data);
     if (!data.data.success) {
     if (data.data.errorCode == "3") {
     $.jBox.tip(data.data.errorName + "\n任务：" + data.data.errorData[0].taskName, "warning");
     }
     if (data.data.errorCode == "4") {
     $.jBox.tip(data.data.errorName + "\n任务：" + data.data.errorData[0].taskName + "\n检测对象：" + data.data.errorData[1].productType, "warning");
     }
     if (data.data.errorTypeCode == "-3") {
     $.jBox.tip(data.data.errorTypeName, "warning");
     }
     } else {
     $("#grid").dataTable().api().ajax.reload();
     $.jBox.tip("成功","info")
     }
     },
     fail: function (data) {
     $("button").attr("disabled", false);
     $("#web_app_model_userOprXz").modal("hide");
     $.jBox.tip("失败","error");
     }
     });
     }
     return true;
     }, {buttons: {'确认': true, '取消': false}});
     }
     };

     //废止
     $scope.abo = function () {
     var rows = $scope.getSelectLinaDates();
     if (rows) {
     for (var k = 0; k < rows.length; k++) {
     if (rows[k].STATE == '已废止') {
     $.jBox.tip("所选任务包括已废止任务，无法进行废止操作！");
     return false;
     }

     if (rows[k].STATE == '未发布') {
     $.jBox.tip("所选任务包括未发布任务，无法进行废止操作！");
     return false;
     }
     }
     }
     var i = $scope.getSelectRowsIds();
     if (i != false) {
     $.jBox.confirm("是否废止已选择任务？", "等待服务器响应", function (v) {
     if (v == true) {
     var data = {
     jsonString: JSON.stringify(i),
     token:window.localStorage.getItem("token")
     };
     $("button").attr("disabled", true);
     $.ajax({
     headers:{
     token:window.localStorage.getItem("token")
     },
     url: "/sofn-ales-web/llesEntrustDetection/abo",
     type: "post",
     dataType: "json",
     data: data,
     beforeSend: function () {
     $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
     $("#web_app_model_userOprXz").modal("show");
     },
     error:function (msg) {
     console.log(msg);
     
     },
     success: function (data) {
     $("button").attr("disabled", false);
     $("#web_app_model_userOprXz").modal("hide");
     $.jBox.tip("成功！");
     $scope.TableReload();
     },
     fail: function (data) {
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
     */
    /* //新增任务
     $scope.addTask = function () {
     $scope.cleanCookie();
     $state.go("index.content.ales/taskManage/alesEntrustDetectionAdd");
     };

     //修改
     /!* window.upd = function (id,state) {
     console.log("=======row.STATE=========="+state);
     // var rows = $("#grid").DataTable().rows('.selected').data();
     // var length = rows.length;
     // if (length != 1) {
     //     $.jBox.tip("请选择一行记录！");
     //     return false;
     // }
     if (state == '已发布') {
     $.jBox.tip("所选任务已发布，无法进行修改操作！");
     return false;
     }
     if (state == '已废止') {
     $.jBox.tip("所选任务已废止，无法进行修改操作！");
     return false;
     }
     $scope.cleanCookie();
     window.localStorage.setItem("ales_taskManage_alesEntrustDetectionOpr", id);
     window.localStorage.setItem("ales_etl_updateFlag", "1");
     $state.go("index.content.ales/taskManage/alesEntrustDetectionAdd");
     };*!/

     //删除
     $scope.del = function () {
     var rows = $("#grid").DataTable().rows('.selected').data();
     var length = rows.length;
     if (length == 0) {
     $.jBox.tip("请选择一行记录！");
     return false;
     }
     for (var k = 0; k < length; k++) {
     if (rows[k].STATE != '未发布') {
     $.jBox.tip("删除操作仅限于未发布任务！");
     return false;
     }
     if(rows[k].CREATEBY != $scope.entrustDetectionId){
     $.jBox.tip("没有权限删除本任务");
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
     headers:{
     token:window.localStorage.getItem("token")
     },
     url: "/sofn-ales-web/llesEntrustDetection/del",
     type: "post",
     dataType: "json",
     data: data,
     beforeSend: function () {
     $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
     $("#web_app_model_userOprXz").modal("show");
     },
     error:function (msg) {
     console.log(msg);
     
     },
     success: function (data) {
     $("#web_app_model_userOprXz").modal("hide");
     $.jBox.tip("成功！");
     $scope.TableReload();
     },
     fail: function (data) {
     $("#web_app_model_userOprXz").modal("hide");
     $.jBox.tip("失败");
     }
     });
     }
     return true;
     }, {buttons: {'确认': true, '取消': false}});
     }
     };
     */
});
