var myapp = angular.module("myapp", []);
myapp.controller("samplePlan", function ($scope, $http, $state){


    var token = window.localStorage.getItem("token");

    var monitorTaskId="";

    var status = "";

    $scope.samplePlanNameHasError = false;

    $scope.toCheck=function(){
        $("#grid").dataTable().api().ajax.reload();
    };
    
    $scope.clear = function () {
        $("#samplePlanNameParam").val("");
        $("#taskNameParam").val("");
        document.getElementById('status').options[0].selected = true;
    }

    //检测对象包分页查询显示
    $(function () {
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getSamplePlanList",
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
                    params.name = $("#samplePlanNameParam").val();
                    params.taskName = $("#taskNameParam").val();
                    params.status = $("#status").val();
                    params.token = token;
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
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: "name",
                    title: "计划名称"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data: "status",
                    title: "状态",
                    render : function (data,type,row) {
                        var status = row.status;
                        if (status ==0){
                             return '<span id="status_'+row.id+'"style="line-height:2;" class="label label-warning" val="0">未发布</span>';
                        }else if (status==1){
                            return '<span id="status_'+row.id+'"style="line-height:2;" class="label label-primary" val="1">已发布</span>';
                        }else if (status==2){
                            return '<span id="status_'+row.id+'"style="line-height:2;" class="label label-success" val="2">执行中</span>';
                        } else if (status==3){
                            return '<span id="status_'+row.id+'"style="line-height:2;" class="label label-success" val="3">已结束，已完成</span>';
                        }else if(status==4){
                            return '<span id="status_'+row.id+'"style="line-height:2;" class="label label-success" val="4">已结束，未完成</span>';
                        }
                    }
                },
                {
                    data: "id",
                    title: "数据操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a class="pointer mr15" onclick="configSamplePlan(\''+row.taskName+"\',\'"+row.id+"\',\'"+row.monitorTaskId+ "\',\'" +row.status+'\') ">配置</a>';
                        if(row.status == 0)
                            var look_b = '<a class="pointer mr15" onclick="modfySamplePlan(\'' + row.id + '\') ">修改</a>';
                        else
                            var look_b = '<span class="mr15">修改</span>';

                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    //新增检测对象包对话框
    $scope.addObjectPackage = function () {
        $("#samplePlanName").val("");
        $("#taskName").val("");
        $('#addObjectPackageModal').modal("show");
    };

    var selectTaskNameFlag = 0;
    /**
     * 选择任务名称
     */
    $scope.isAdd = true;
    $scope.selectTaskName = function(flag){
        if(flag == 1)
            $scope.isAdd = true;
        else
            $scope.isAdd = false;

        $("#taskNameModal").modal("show");
        setTimeout(function () {
            if(selectTaskNameFlag==0){
                loadTaskName();
            }else{
                $("#taskNameGrid").dataTable().api().ajax.reload();
            }
        },300)
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
            ajax : {
                url : "/sofn-ads-web/adsMonitorTask/getPageInfoBySamplePlan",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    //params.publishStatus = 1;
                    params.is_history = "Y";
                    params.taskName =  $("input[name='sTaskName']").val();
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称"
                },
                {
                    data : "taskName",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<button class="gui-btn btn-sm" onclick="checkTaskName(\''+row.taskName+"\',\'"+row.id+"\',\'"+row.publishStatus+'\')">选择</button>';
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
    window.checkTaskName = function(taskName,id,publishStatus){

        if($scope.isAdd){
            $("input[name='taskName']").val(taskName);
        } else {
            $("#taskNameModfy").val(taskName);
        }

        monitorTaskId = id;
        $("#taskNameModal").modal("hide");
        //增加显示任务状态
        var STATUS = new Array('未发布','已发布','执行中','已终止','已结束,未完成','已废止');

        status = publishStatus;
    };

    $scope.saveSamplePlan = function () {
        var samplePlanName = $('#samplePlanName').val();
        var samplePlanName = $("#samplePlanName").val();
        var taskName = $("#taskName").val();
        if (samplePlanName == null || samplePlanName == '') {
            jBox.tip("请输入抽样计划名称", 'info');
            $scope.samplePlanNameHasError = true;
            return false;
        }

        if (taskName == null || taskName == '') {
            jBox.tip("请输入关联任务名称", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/addSamplePlanInfo",
            type: "post",
            dataType: "json",
            data: {
                "name": samplePlanName,
                "monitorTaskId": monitorTaskId,
                "status": "0",
                "token":token
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#addObjectPackageModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败,该任务名称已被关联", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    };

    window.modfySamplePlan = function (id) {
        $("#modifyModal").modal("show");
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getSamplePlanById",
            method: "post",
            dataType: "json",
            params: {"samplePlanId": id, "token": token}
        }).success(function (data) {
            $scope.samplePlanId = data.data.id;
            $scope.name = data.data.name;
            $scope.monitorTaskId = data.data.monitorTaskId;
            $scope.taskNameModfiy = data.data.taskName;
            $("#modyfySampleId").val($scope.samplePlanId);
        });
    }

    window.configSamplePlan = function (taskName,data,monitorTaskId,status) {
        window.localStorage.setItem("samplePlanId",data);
        window.localStorage.setItem("monitorTaskId",monitorTaskId)
        window.localStorage.setItem("taskStatus",status);
        $state.go("index.content.monitor/execUnit/sample/samplePlanConfig");
    }
    $scope.updateSamplePlan = function () {

        //var taskName = $('#taskNameModfy').val();
        var samplePlanName = $('#samplePlanNameModfy').val();
        var modyfySampleId = $('#modyfySampleId').val();

        if (samplePlanName == null || samplePlanName == '') {
            jBox.tip("请输入抽样计划名称", 'info');
            return false;
        }

        $.ajax({
            url: "/sofn-ads-web/adsMonitorSample/updateSamplePlanById",
            type: "post",
            dataType: "json",
            data: {
                "name": samplePlanName,
                "id": modyfySampleId,
                "token":token
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#modifyModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败", 'info');
                }
            }
        });
    }

    //发布抽样任务事件
    $scope.publicTask = function () {
        var check = $("input[name='task']:checked");
        if (check.length > 0) {
            var arr = new Array();
            var count = 0;
            $(check).each(function () {
                var task = $(this)[0];
                var status = $(this).parent("tr").find("span").attr("val");
                var id = $(task).val();
                arr.push(id);
                var statusVal = $("#status_" + id).attr("val");
                if (statusVal==1||statusVal==2){
                    count++;
                }
            });
            if (count>0){
                jBox.tip("只能选择未发布的抽样计划，请重新选择", 'info');
                return false;
            }

            var configCount = 0;
            $.ajax({
                url: "/sofn-ads-web/adsMonitorSample/getCountSamplePlanConfigList",
                type: "post",
                dataType: "json",
                data: {"ids": arr.toString(),"token": token},
                success: function (data) {
                    if (data.httpCode == 200) {
                        var list = data.data;
                        var len = list.length;
                        for (var i=0;i<list.length;i++){
                            var count = list[i];
                            if (count>0){
                                configCount++;
                            }
                        }
                        if (len != configCount){
                            jBox.tip("只能发布配置完成的抽样计划，请重新选择", 'info');
                            return false;
                        }
                    } else if (data.httpCode == 400) {
                        jBox.tip("没有登录", 'info');
                    } else {
                        jBox.tip("删除失败", 'info');
                    }
                }
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsMonitorSample/updateSamplePlanStatus",
                        type: "post",
                        dataType: "json",
                        data: {"ids": arr.toString(), "type":"1","token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                jBox.tip("发布成功", 'success');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("删除失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认发布抽样计划记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先选择抽样计划记录", 'info');
        }
    }

    //批量删除抽样计划
    window.delSamplePlanInfo = function () {
        var check = $("input[name='task']:checked");
        if (check.length > 0) {
            var arr = new Array();
            var count = 0;
            $(check).each(function () {
                var task = $(this)[0];
                var id = $(task).val();
                arr.push(id);
                var statusVal = $("#status_" + id).attr("val");
                if (statusVal==1||statusVal==2){
                    count++;
                }
            });

            if (count>0){
                jBox.tip("只能删除未发布的抽样计划，请重新选择", 'info');
                return false;
            }
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsMonitorSample/delSamplePlanById",
                        type: "post",
                        dataType: "json",
                        data: {"ids": arr.toString(), "token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                jBox.tip("删除成功", 'success');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("删除失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除抽样计划记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先选择抽样计划记录", 'info');
        }
    };
});
