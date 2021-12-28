var myapp = angular.module("myapp", []);
myapp.controller("sampleTask", function ($scope, $http, $state) {


    var token = window.localStorage.getItem("token");

    // var samplePlanId = window.localStorage.getItem("samplePlanId");
    $scope.toCheck = function () {
        $("#grid").dataTable().api().ajax.reload();
    };

    $scope.clear = function () {
        $("#name").val("");
        $("#taskName").val("");
        $("#userName").val("");
    }

    //检测对象包分页查询显示
    $(function () {
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsMonitorSample/getSampleTaskList",
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
                    params.name = $("#name").val();
                    params.taskName = $("#taskName").val();
                    params.userName = $("#userName").val();
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
                    data: "dateStart",
                    title: "开始时间"
                },
                {
                    data: "dateEnd",
                    title: "结束时间"
                },
                {
                    data: "status",
                    title: "状态",
                    render: function (data, type, row) {
                        var status = row.status;
                        if (status == 0) {
                            return '<span id="status_' + row.id + '"style="line-height:2;" class="label label-warning" val="0">未发布</span>';
                        } else if (status == 1) {
                            return '<span id="status_' + row.id + '"style="line-height:2;" class="label label-primary" val="1">已发布</span>';
                        } else if (status == 2) {
                            return '<span id="status_' + row.id + '"style="line-height:2;" class="label label-success" val="2">执行中</span>';
                        }else if (status == 3) {
                            return '<span id="status_' + row.id + '"style="line-height:2;" class="label label-success" val="3">已完成</span>';
                        }else if (status == 4) {
                            return '<span id="status_' + row.id + '"style="line-height:2;" class="label label-success" val="4">已结束，未完成</span>';
                        }
                    }
                },
                {
                    data: "id",
                    title: "数据操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var status = row.status;
                        var look_a;
                        if (status == 1 || status == 2) {
                            /* look_a = '<a class="pointer mr15" onclick="configSamplePlan(\'' + row.id + '\') ">执行任务</a>';*/
                            look_a = '<a style="cursor: pointer;margin-right: 10px;" onclick="configSamplePlan(' + "'" + row.id + "'" + "," + "'" + row.status + "'" + "," + "'" + row.monitorTaskId + "'" + ')">执行任务</a>';
                        } else {
                            look_a = '<a style="cursor: pointer;opacity: 0.2;margin-right: 10px;" disabled>执行任务</a>';
                        }
                        var look_b = '<a class="pointer mr15" onclick="modfySamplePlan(\'' + row.configId + '\') ">查看</a>';
                        /* if ($scope.disabled) {
                         look_a = '<a class="pointer mr15" style="color: grey">修改</a>'
                         }*/
                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    window.modfySamplePlan = function (id) {
        $("#showModal").modal("show");
        $http({
            url: "/sofn-ads-web/adsMonitorSample/getSamplePlanDetail",
            method: "post",
            dataType: "json",
            params: {"id": id, "token": token}
        }).success(function (data) {
            $scope.dateStart = data.data.dateStart;
            $scope.userNmames = data.data.userNmames;
            /*var city = $.fn.Cityname(data.data.areaId);
            $scope.areaId = city;*/
            $scope.sampleObjectName = data.data.objectNameAndNum;
            $scope.taskName = data.data.taskName;
            $scope.name = data.data.name;
            //$("#modyfySampleId").val($scope.samplePlanId);
        });
    }

    window.configSamplePlan = function (data, status, adsMonitorTaskId) {

        if (status == '1') {
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsMonitorSample/updateSamplePlanStatus",
                        type: "post",
                        dataType: "json",
                        data: {"ids": data, "type": "2", "token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                $scope.goto(adsMonitorTaskId);
                                //jBox.tip("执行任务成功", 'success');
                                //$state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage");
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("执行任务失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认执行抽样计划记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        } else {

            $scope.goto(adsMonitorTaskId);
            //获取任务详情，添加任务缓存信息
            /*$http({
                url: "/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
                method: "post",
                params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
            }).success(function (result) {

                $scope.detail = result.adsMonitorTask[0];
                $scope.taskName = result.adsMonitorTask[0].taskName;
                $scope.deadline = result.adsMonitorTask[0].deadline;//上报截至时间
                $scope.organTaskId = result.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
                $scope.checkModel = result.adsMonitorTask[0].checkModel;//机构任务id
                $scope.sampleFinishNum = result.adsMonitorTask[0].adsOrganTaskList[0].sampleFinishNum;
                $scope.numbers = result.adsMonitorTask[0].adsOrganTaskList[0].numbers;

                if (result.adsMonitorTask[0].checkObject != null) {
                    $scope.checkObject = result.adsMonitorTask[0].checkObject.split("、");
                    window.localStorage.setItem("checkObject", $scope.checkObject);
                }

                $scope.publishStatus = result.adsMonitorTask[0].publishStatus;

                $scope.monitorClass = result.adsMonitorTask[0].monitorClass;//监测类型
                var type = "0";
                if ($scope.monitorClass == "例行监测")
                    type = "1";

                window.localStorage.setItem("adsMonitorTaskId", adsMonitorTaskId);
                window.localStorage.setItem("deadline", $scope.deadline);
                window.localStorage.setItem("type", type);//类型
                window.localStorage.setItem("status", $scope.publishStatus);
                window.localStorage.setItem("taskName", $scope.taskName);
                window.localStorage.setItem("organTaskId", $scope.organTaskId);
                window.localStorage.setItem("monitorClass", $scope.monitorClass);
                window.localStorage.setItem("numbers", $scope.numbers);

                $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessageNew");
            });*/
        }

    }

    $scope.goto = function (id) {
        var token = window.localStorage.getItem("token");
        var status = 1;
        var type = "1";
        var orgId = "";
        $.ajax({
            url: "/sofn-ads-web/adsMonitorTask/getAdsMonitorTaskById",
            type: "POST",
            async: false,
            data: {"id": id, "token": token}
        }).success(function (data) {
            status =  data.data.publishStatus;
            if(data.data.monitorClass !="例行监测")
                type = "2";
            orgId = data.data.organId;

        })



        $scope.sampleOrganId = orgId; //通过token获取抽样机构代码
        window.localStorage.setItem("adsMonitorTaskId", id); //监测任务id
        window.localStorage.setItem("token", token); //监测任务id
        window.localStorage.setItem("sampleOrganId", $scope.sampleOrganId); //监测任务id
        window.localStorage.setItem("type", type); //类型
        window.localStorage.setItem("status", status.toString());
        $state.go(
            "index.content.monitor/execUnit/AdsOrganTask/taskDetailAdsOrgan"
        );
    };

});
