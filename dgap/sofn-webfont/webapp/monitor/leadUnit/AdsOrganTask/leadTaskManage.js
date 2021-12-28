var myapp = angular.module("myapp", []);
myapp.controller("leadTaskManage", function ($scope, $http, $state) {
    $scope.pecipenewlist = "";
    $scope.monitornewlist = "";

    $scope.Status = new Array('未发布', '已发布', '执行中', '已结束,已完成.', '已结束,未完成', '已废止');

    var token = window.localStorage.getItem("token");

    var flag = true;
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
            data: {"token":token,"source":1},
            success: function (data) {
                var datas = data.data;
                var Row = '<span>退回任务总计 ：  '+datas.count+'条，其中抽样单总计 ： '+datas.countSample+'条，检测结果总计 ： '+datas.countCheck+'条。</span> ';
                $("#count").html(Row);
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
                    params.source = 1;
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
                        var look_a = '';
                        if(row.ISSEND == '1'){
                            look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="see(\'' + data + '\') ">查看</a>'+
                                '<a style="margin-right: 10px;cursor: pointer;color: darkgray">退回</a>';
                        }else{
                            look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="see(\'' + data + '\') ">查看</a>'+
                                '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="back(\'' + data + '\') ">退回</a>';
                        }
                        return look_a;
                    }
                }
            ],
        });
    });

    var organTaskid = "";
    var selectedType = "";
    var pId = "";
        window.see = function (id) {
            pId = id;
            var tableName = $('#dModal');
            if ($.fn.dataTable.isDataTable(tableName)) {
                tableName.DataTable().clear();
                tableName.DataTable().destroy();
            }
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
                    $("#modifyBtn").html("退回抽样单");
                } else if (data.adsPecipe.receiptClass == "D") {
                    $("#modalTitle").html("检测退回回执单");
                    $("#myFinishNum").html("检测完成数");
                    $("#myReportTime").html("检测上报时间");
                    $("#myReason").html("检测退回原因");
                    $("#modifyBtn").html("退回检测结果");
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
            url: "/sofn-ads-web/adsPecipe/queryById",
            method: "POST",
            data: {"id": pId}
        }).success(function (data) {
            if(data.adsPecipe.isSend == 1){
                $.jBox.tip("已退回到承担单位，请勿重复退回");
                return;
            }
            document.getElementById('reason3').value = "";
            var tableName = $('#dModal2');
            if ($.fn.dataTable.isDataTable(tableName)) {
                tableName.DataTable().clear();
                tableName.DataTable().destroy();
            }
            $("#dModal").modal("hide");
            $("#organName2").text(data.adsPecipe.organName);
            /* if(data.adsPecipe.taskNum==null){
             $("#numbers").text("");
             }else {
             $("#numbers").text(data.adsPecipe.taskNum);
             }*/
            $("#finishNum2").text(data.adsPecipe.finishNum);
            $("#receiptTime2").text(data.adsPecipe.receiptTime);
            $("#reason2").text(data.adsPecipe.reason);

            organTaskid = data.adsPecipe.organTaskId;

            if (data.adsPecipe.scode == null) {
                $("#sCode2").text("");
            } else {
                $("#sCode2").text(data.adsPecipe.scode);
            }
            if (data.adsPecipe.sname == null) {
                $("#sNmae2").text("");
            } else {
                $("#sNmae2").text(data.adsPecipe.sname);
            }

            selectedType = data.adsPecipe.receiptClass;
            if (data.adsPecipe.receiptClass == "S") {

                $("#modalTitle2").html("抽样退回回执单");
                $("#myFinishNum2").html("抽样完成数");
                $("#myReportTime2").html("抽样上报时间");
                $("#myReason2").html("抽样退回原因");
                $("#modifyBtn2").html("退回抽样单");
            } else if (data.adsPecipe.receiptClass == "D") {
                $("#modalTitle2").html("检测退回回执单");
                $("#myFinishNum2").html("检测完成数");
                $("#myReportTime2").html("检测上报时间");
                $("#myReason2").html("检测退回原因");
                $("#modifyBtn2").html("退回检测结果");
            }
            $("#dModal2").modal("show");
        }).error(function () {
        });

    }

    $scope.addPecipe = function(){
        var reason = $('#reason3') .val();
        if(reason == null || reason == ""){
            jBox.tip("请输入退回原因",'info');
            return false;
        }
        $.ajax({
            url:"/sofn-ads-web/adsPecipe/addAdsUnitPecipe",
            type:"post",
            dataType:"json",
            data:{"id":pId,"reason":reason,"token":token},
            success:function(data){
                if(data.httpCode == 500){
                    jBox.tip("保存失败", 'info');
                }else if(data.httpCode == 400){
                    jBox.tip("没有登录", 'info');
                }else{
                    $("#dModal2").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                }
            }
        });
    };

    window.back = function (id) {
        pId = id;
        document.getElementById('reason3').value = "";
        var tableName = $('#dModal2');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $http({
            url: "/sofn-ads-web/adsPecipe/queryById",
            method: "POST",
            data: {"id": id}
        }).success(function (data) {
            if(data.isSend == 1){
                $.jBox.tip("已退回到承担单位，请勿重复退回");
                return;
            }
            $("#organName2").text(data.adsPecipe.organName);
            /* if(data.adsPecipe.taskNum==null){
             $("#numbers").text("");
             }else {
             $("#numbers").text(data.adsPecipe.taskNum);
             }*/
            $("#finishNum2").text(data.adsPecipe.finishNum);
            $("#receiptTime2").text(data.adsPecipe.receiptTime);
            $("#reason2").text(data.adsPecipe.reason);

            organTaskid = data.adsPecipe.organTaskId;

            if (data.adsPecipe.scode == null) {
                $("#sCode2").text("");
            } else {
                $("#sCode2").text(data.adsPecipe.scode);
            }
            if (data.adsPecipe.sname == null) {
                $("#sNmae2").text("");
            } else {
                $("#sNmae2").text(data.adsPecipe.sname);
            }

            selectedType = data.adsPecipe.receiptClass;
            if (data.adsPecipe.receiptClass == "S") {

                $("#modalTitle2").html("抽样退回回执单");
                $("#myFinishNum2").html("抽样完成数");
                $("#myReportTime2").html("抽样上报时间");
                $("#myReason2").html("抽样退回原因");
                $("#modifyBtn2").html("退回抽样单");
            } else if (data.adsPecipe.receiptClass == "D") {
                $("#modalTitle2").html("检测退回回执单");
                $("#myFinishNum2").html("检测完成数");
                $("#myReportTime2").html("检测上报时间");
                $("#myReason2").html("检测退回原因");
                $("#modifyBtn2").html("退回检测结果");
            }
            $("#dModal2").modal("show");
        }).error(function () {
        });
    };

});


