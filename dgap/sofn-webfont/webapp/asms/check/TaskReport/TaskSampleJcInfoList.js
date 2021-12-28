angular.module("myapp", []).controller("TaskSampleJcInfoList", function ($scope, $http, $state) {

    /**
     * 检测信息列表
     * */
    $scope.JcInfoId = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfoList");//检测信息id
    $scope.jcUnitName = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfoList_jcUnitName");//检测单位名称
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    //监督抽查,复检任务,隐藏退回按钮
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.taskStatus = window.localStorage.getItem("asms_Check_checkTask_status_forReport");//任务状态
    if ($scope.asms_Check_Task_Type_Title == 'JDCC' || $scope.asms_Check_Task_Type_Title == 'FJRW') {
        $("#backSample").hide();
    }

    $(function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/AsmsCheckTaskReport/getJclistByReport",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;
                    json.pageSize = json.data.pageSize;
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.organTaskId = $scope.JcInfoId;
                    var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
                    if("JDCC"==type){
                        params.monitorType = "监督抽查";
                        params.monitorTaskId = $scope.JcInfoId;
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
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称",
                    width: "6%"
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    width: "15%"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    width: "28%"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位",
                    width: "10%",
                    render: function (data, type, row) {
                        return data?data:"无";
                    }
                },
                {
                    data: "SAMPLE_DEPARMENT",
                    title: "抽样单位",
                    width: "11%"
                },
                {
                    data: "CHECK_ORGAN",
                    title: "检测单位",
                    width: "11%",
                    render: function (data, type, row) {
                        return data?data:$scope.jcUnitName;
                    }
                },
                {
                    data: "RESULT",
                    title: "检测结果",
                    width: "6%",
                    render: function (data, type, row) {
                        if ("1" == data) {
                            return "合格";
                        } else if ("0" == data) {
                            return "不合格";
                        } else if ("-1" == data) {
                            return "暂未检测";
                        } else if("2" == data) {
                            return "不作判定";
                        }
                    }
                },
                {
                    data: "CHECK_REPORT",
                    title: "状态",
                    width: "6%",
                    render: function (data, type, row) {
                        if ("1" == data) {
                            return "已上报";
                        } else if ("3" == data || "4" == data) {
                            return "已退回";
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        return '<a style="cursor:pointer" onclick="viewOrganTask(\'' + data + '\')">查看</a>';
                    },
                    width: "7%"
                }
            ]
        });
    });

    window.viewOrganTask = function (data) {
        $("#product_info2").modal("show");
        var tableName = $('#asmsProductList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#asmsProductList").dataTable({
            bPaginate: false,
            bInfo:false,
            ajax: {
                url: "/sofn-asms-web/AsmsCheckTaskReport/getReportByJcInfo",
                headers:{
                    token:window.localStorage.getItem("token")
                },
                dataSrc: function (json) {
                    return json.data;
                },
                data: function (params) {
                    params.checkInfoId = data;
                },
                type: "post"
            },
            columns: [
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if(0 == data){
                            return '<a id="'+meta.row+'" value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">ND</a>';
                        }else{
                            return '<a id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                        }
                    },
                },
                {
                    data: "judgeNum",
                    title: "限量值"
                },
                {
                    data : "unit",
                    title : "单位",
                    render : function(data, type, row) {
                       return data?data:"无";
                        
                    }
                },
                {
                    data: "result",
                    title: "抽检结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else if ("2" == data) {
                            return "不作判定";
                        }
                    }
                },
                {
                    data: "lod",
                    title: "LOD"
                },
                {
                    data: "loo",
                    title: "LOQ"
                }
            ]
        });
    };

    window.my123 = function (index, judgeNum) {
        var inputValue = document.getElementById(index).value;
        if ("未检出" == inputValue || "" == inputValue) {
            return;
        }
        var real = index + 1;
        if (inputValue <= judgeNum) {
            $("#asmsProductList2 tr:eq(" + real + ") td:eq(3)").html("合格");
        } else {
            $("#asmsProductList2 tr:eq(" + real + ") td:eq(3)").html("不合格");
        }
    }

    $scope.backTaskList=function(){
        var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if("LXJC"==type){
            $state.go("index.content.asms/check/routineMonitor");
        }
        if("ZXJC"==type){
            $state.go("index.content.asms/check/specialMonitor");
        }
        if("JDCC"==type){
            $state.go("index.content.asms/check/checkTask");
        }
        if("FJRW"==type){
            $state.go("index.content.asms/check/reCheckTask");
        }
    }
    $scope.backReportList = function () {
        var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if("JDCC"==type){
            $state.go("index.content.asms/check/TaskReport/checkTaskReportList");
        }else {
            $state.go("index.content.asms/check/TaskReport/TaskReportList");
        }

    }

    $scope.backSampleList = function() {
        if ($scope.taskStatus) {
            //已废止
            if($scope.taskStatus == 2){
                $.jBox.tip("已废止任务不能退回!");
                return;
            }
            //已结束
            if($scope.taskStatus == 4 || $scope.taskStatus == 5){
                $.jBox.tip("已结束任务不能退回!");
                return;
            }
        }
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length == 0) {
            $.jBox.tip("请选择一行记录！");
            return false;
        }
        if (rows) {
            for (var k = 0; k < rows.length; k++) {
                if (rows[k].CHECK_REPORT == 3 || rows[k].CHECK_REPORT == 4 ) {
                    $.jBox.tip("包含已退回样品!");
                    return false;
                }
            }
        }
        $scope.taskName = window.localStorage.getItem("asms_Check_checkTask_taskName_forReport");
        $scope.sampleDeparment = rows[0].SAMPLE_DEPARMENT;
        $scope.sampleBackIds = [];
        $.each(rows, function(i,o){
            $scope.sampleBackIds.push(o.ID);
        });
        $("#sampleTable").html("");
        for (var i = 0; i < rows.length; i++) {
            var _row = '<tr><td class="mt15" style="text-align: right;padding-left: 50px;font-weight: bold;height: 30px;padding-right: 10px;">样品名称 :</td><td width="200px"><span>'+rows[i].SAMPLE_NAME+'</span></td>';
            _row += '<td><label>样品编号 :</label></td><td width="200px"><span>'+ rows[i].SAMPLE_CODE +'</span></td></tr>';
            $("#sampleTable").append(_row);
        }
        //打开模态框
        $("#sampleBackModal").modal("show");
    };

    $scope.submitSampleBack = function($event) {
        var reason = $("textarea[name='reason']").val();
        if (!reason) {
            $.jBox.tip("请填写退回原因");
            return;
        }
        $($event.currentTarget).prop("disabled", true);
        //提交
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/sampleBack",
            method: "post",
            params: {"ids": $scope.sampleBackIds.join(","),"reason": reason, "receiptClass":"D"}
        }).success(function (data) {
            $($event.currentTarget).prop("disabled", false);
            if (data.httpCode == 200) {
                $.jBox.tip("退回成功");
                $("#grid").dataTable().api().ajax.reload();
                $("#sampleBackModal").modal("hide");
            }else {
                $.jBox.tip("退回失败");
            }
        });
    }
});

