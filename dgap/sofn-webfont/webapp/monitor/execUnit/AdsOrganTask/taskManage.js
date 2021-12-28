var myapp = angular.module("myapp", []);
myapp.controller("taskManage", function ($scope, $http, $state) {
    $scope.pecipenewlist = "";
    $scope.monitornewlist = "";

    $scope.Status = new Array('未发布', '已发布', '执行中', '已结束,已完成.', '已结束,未完成', '已废止');

    var token = window.localStorage.getItem("token");

    var flag = true;
    /**
     * 前往承担单位例行监测列表
     */
    // $scope.goRoutineList = function () {
    //     // $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitoring")
    //     if (flag) {
    //         $http({
    //             url: "/sofn-ads-web/adsOrganTask/queryExecUnitTaskPageInfo",
    //             method: "POST",
    //             params: {"token": token, "start": 0, "length": 500}
    //         }).success(function (data) {
    //             $scope.sampleOrganId = data.sampleOrganId;
    //             $scope.sampleOrgan = data.sampleOrgan;
    //             // $scope.monitornewlist = data.data.list;
    //             $scope.total = data.data.total;
    //             var mapArray =[];
    //             for(var i=0;i<data.data.list.length;i++){
    //                 var taskType = data.data.list[i].monitorClass;
    //                 if(taskType=="例行监测"||taskType=="专项监测"){
    //                     var organTask = data.data.list[i].adsOrganTaskList[0];
    //                     if(organTask.tasksStatus=="1"){
    //                         mapArray.push(data.data.list[i]);
    //                     }
    //                 }else{
    //                     mapArray.push(data.data.list[i]);
    //                 }
    //             }
    //             $scope.monitornewlist = mapArray;
    //         }).error(function () {
    //             jBox.tip("加载异常", "error");
    //         });
    //         flag = false;
    //         $("#more").text("收起");
    //     } else {
    //         $http({
    //             url: "/sofn-ads-web/adsOrganTask/queryExecUnitTaskPageInfo",
    //             method: "POST",
    //             params: {"token": token, "start": 0, "length": 6}
    //         }).success(function (data) {
    //             $scope.sampleOrganId = data.sampleOrganId;
    //             $scope.sampleOrgan = data.sampleOrgan;
    //             // $scope.monitornewlist = data.data.list;
    //             $scope.total = data.data.total;
    //             var mapArray =[];
    //             for(var i=0;i<data.data.list.length;i++){
    //                 var taskType = data.data.list[i].monitorClass;
    //                 if(taskType=="例行监测"||taskType=="专项监测"){
    //                     var organTask = data.data.list[i].adsOrganTaskList[0];
    //                     if(organTask.tasksStatus=="1"){
    //                         mapArray.push(data.data.list[i]);
    //                     }
    //                 }else{
    //                     mapArray.push(data.data.list[i]);
    //                 }
    //             }
    //             $scope.monitornewlist = mapArray;
    //         }).error(function () {
    //             jBox.tip("加载异常", "error");
    //         });
    //         flag = true;
    //         $("#more").text("更多");
    //     }
    // }

    /**
     * 查询承担单位接收新任务列表
     */
    // $http({
    //     url: "/sofn-ads-web/adsOrganTask/queryExecUnitTaskPageInfo",
    //     method: "POST",
    //     params: {"token": token, "start": 0, "length": 6}
    // }).success(function (data) {
    //     $scope.sampleOrganId = data.sampleOrganId;
    //     $scope.sampleOrgan = data.sampleOrgan;
    //     // $scope.monitornewlist = data.data.list;
    //     $scope.total = data.data.total;
    //     var mapArray =[];
    //     for(var i=0;i<data.data.list.length;i++){
    //         var taskType = data.data.list[i].monitorClass;
    //         if(taskType=="例行监测"||taskType=="专项监测"){
    //             var organTask = data.data.list[i].adsOrganTaskList[0];
    //             if(organTask.tasksStatus=="1"){
    //                 mapArray.push(data.data.list[i]);
    //             }
    //         }else{
    //             mapArray.push(data.data.list[i]);
    //         }
    //     }
    //     $scope.monitornewlist = mapArray;
    // }).error(function () {
    //     jBox.tip("加载异常", "error");
    // });

    /**
     * 分页展示数据
     */
    $(function () {

        //获取总数
        var countUrl = "/sofn-ads-web/adsPecipe/getCount?time="+new Date();
        $.ajax({
            url: countUrl,
            type: "post",
            dataType: "json",
            data: {"token":token,"source":0},
            success: function (data) {
                var datas = data.data;
                var Row = '<span>退回任务总计 ：  '+datas.count+'条，其中抽样单总计 ： '+datas.countSample+'条，检测结果总计 ： '+datas.countCheck+'条。</span> ';
                $("#excount").html(Row);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsPecipe/getAdsPecipePageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.adsPecipepageInfo.total;//总个数
                    json.recordsFiltered = json.adsPecipepageInfo.total;
                    json.pageNumber = json.adsPecipepageInfo.pageNum;//页码
                    json.pageSize = json.adsPecipepageInfo.pageSize;//每页个数
                    json.start = (json.adsPecipepageInfo.pageNum - 1) * json.adsPecipepageInfo.pageSize;//起始位置
                    json.length = json.adsPecipepageInfo.pageSize;
                    return json.adsPecipepageInfo.list;
                },
                data: function (params) {
                    params.token = token;
                    params.source = 0;
                },
                type: "post"
            },

            columns: [/*
                {
                    data: "rn",
                    title: "序号"
                },*/
                {
                    data: "TASK_NAME",
                    title: "任务名称",

                },
                {
                    data: "RECEIPT_CLASS",
                    title: "退回类型",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        if (data == "S") {
                            return "抽样类型";
                        } else if (data == "D") {
                            return "检测类型";
                        }
                    }
                },
                {
                    data: "CREATE_TIME",
                    title: "退回时间"
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="see(\'' + data + '\') ">查看</a>';
                        return look_a;
                    }
                }
            ],
        });
    });

    /*$http({
        url:"/sofn-ads-web/adsPecipe/getAdsPecipePageInfo",
        method:"POST",
        params:{"start":0,"length":4}
    }).success(function(data){
        if(data.adsPecipepageInfo.list!=0){
            $('#pecipeta').css('display','table');
            $scope.pecipenewlist=data.adsPecipepageInfo.list;
        }
    }).error(function(){
    });*/
    var organTaskid = "";
    var selectedType = "";
        window.see = function (id) {
            $http({
                url: "/sofn-ads-web/adsPecipe/queryById",
                method: "POST",
                data: {"id": id}
            }).success(function (data) {
                $("#organName").text(data.adsPecipe.organName);
                /* if(data.adsPecipe.taskNum==null){
                     $("#numbers").text("");
                 }else {
                     $("#numbers").text(data.adsPecipe.taskNum);
                 }*/
                $("#finishNum").text(data.adsPecipe.finishNum);
                $("#receiptTime").text(data.adsPecipe.receiptTime);
                $("#reason").text(data.adsPecipe.reason);

                organTaskid = data.adsPecipe.organTaskId;

                if (data.adsPecipe.scode == null) {
                    $("#sCode").text("");
                } else {
                    $("#sCode").text(data.adsPecipe.scode);
                }
                if (data.adsPecipe.sname == null) {
                    $("#sNmae").text("");
                } else {
                    $("#sNmae").text(data.adsPecipe.sname);
                }

                selectedType = data.adsPecipe.receiptClass;
                if (data.adsPecipe.receiptClass == "S") {

                    $("#modalTitle").html("抽样退回回执单");
                    $("#myFinishNum").html("抽样完成数");
                    $("#myReportTime").html("抽样上报时间");
                    $("#myReason").html("抽样退回原因");
                    $("#modifyBtn").html("修改抽样单");
                } else if (data.adsPecipe.receiptClass == "D") {
                    $("#modalTitle").html("检测退回回执单");
                    $("#myFinishNum").html("检测完成数");
                    $("#myReportTime").html("检测上报时间");
                    $("#myReason").html("检测退回原因");
                    $("#modifyBtn").html("修改检测结果");
                }
                $("#dModal").modal("show");
            }).error(function () {
            });
        };
    $scope.OpenCourse = function (json) {
        window.localStorage.setItem("token", token);//监测机构名称
        window.localStorage.setItem("sampleOrgan", $scope.sampleOrgan);//监测机构名称
        window.localStorage.setItem("adsMonitorTaskId", json.id);//监测任务id
        window.localStorage.setItem("sampleOrganId", $scope.sampleOrganId);//机构任务id
        $http({
            url: "/sofn-ads-web/adsMonitorTask/queryAndUpdateById",
            method: "POST",
            params: {"id": json.id, "publishStatus": json.publishStatus}
        }).success(function (data) {
            var monitorClass = data.adsMonitorTask.monitorClass;
            if (monitorClass == "监督抽查") {
                $state.go("index.content.monitor/execUnit/Audit/taskDetail")
            } else {
                $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetailAdsOrgan")
            }
        }).error(function () {
        });
    }


    $scope.modifySampleOrCheck = function () {
        $http({
            url: "/sofn-ads-web/adsOrganTask/queryById",
            method: "post",
            params: {"id": organTaskid, "token": token}
        }).success(function (data) {

            window.localStorage.setItem("sampleOrgan", data.adsOrganTask.sampleOrgan);//机构名称
            window.localStorage.setItem("organTaskId", organTaskid);//机构任务
            window.localStorage.setItem("adsMonitorTaskId", data.adsOrganTask.monitorTaskId);//监测任务
            window.localStorage.setItem("sampleOrganId", data.adsOrganTask.sampleOrganId);//抽样机构代码
            window.localStorage.setItem("numbers", data.adsOrganTask.numbers);//任务数
            window.localStorage.setItem("sampleFinishNum", data.adsOrganTask.sampleFinishNum);//抽样完成数

            $http({
                url: "/sofn-ads-web/adsMonitorTask/queryById",
                method: "post",
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'},
                    token: window.localStorage.getItem("token")
                },
                data: data.adsOrganTask.monitorTaskId,
            }).success(function (result) {
                window.localStorage.setItem("monitorClass", result.adsMonitorTask.monitorClass);//监测类型
                window.localStorage.setItem("deadline", result.adsMonitorTask.deadline);//抽样截至时间

                window.localStorage.setItem("taskName", result.adsMonitorTask.taskName);
                if (result.adsMonitorTask.monitorClass == "例行检测")
                    window.localStorage.setItem("type", "1");//类型 1:例行检测，2:专项检测
                else
                    window.localStorage.setItem("type", "2");//类型 1:例行检测，2:专项检测

                $("#dModal").modal("hide");

                setTimeout(function () {
                    if (selectedType == "S")
                        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage");
                    else
                        $state.go("index.content.monitor/execUnit/routineMonitor/testList");
                }, 500);


            })
        })

    }
});


