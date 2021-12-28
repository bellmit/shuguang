angular.module("myapp", []).controller("TaskSampleList", function ($scope, $http, $state) {

    /**
     * 该页面作为监督抽查、例行监测、专项抽样单展示页面
     * */

    /**
     * 公共值
     * */
    $scope.JdccCdUnit = [];//承担单位下拉
    $scope.JDccSonTask = [];//承担单位任务
    $scope.jcObjList = [];//检测对象下拉
    $scope.checkTaskInfomation = {};//监督抽查任务信息
    $scope.rmTaskInfomation = {};//例行监测任务信息
    $scope.smTaskInfomation = {};//专项检测任务信息
    $scope.dataTableUrl = "";//数据表格url
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    $scope.asms_Check_checkTask_forReport = window.localStorage.getItem("asms_Check_checkTask_forReport");//任务id
    $scope.taskStatus = window.localStorage.getItem("asms_Check_checkTask_status_forReport");//任务状态
    if ($scope.asms_Check_checkTask_forReport == null || $scope.asms_Check_checkTask_forReport == undefined) {
        $.jBox.tip("未获取到任务标示，无法获取抽样单列表");
        return;
    }
    //监督抽查,复检任务,隐藏退回按钮
    if ($scope.asms_Check_Task_Type_Title == 'JDCC' || $scope.asms_Check_Task_Type_Title == 'FJRW') {
        $("#backSample").hide();
    }

    /**
     * 根据任务区分暂时牵头单位或承担单位
     * */
    if ($scope.asms_Check_Task_Type_Title == 'JDCC') {
        $("#titleName").html("监督抽查");
        $scope.dataTableUrl = "/sofn-asms-web/checkTask/getTaskSampleListByJcInfo";
        //部级提供承担单位、监测对象
        //省级提供检测对象
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.checkTaskInfomation = data.data;
            if ("0" == data.data.taskLevel) {//部级
                $("div[name='JDccCdUnitSpan']").show();
                $("div[name='JDccSonTaskSpan']").show();
                $("div[name='jcObjSpan']").show();
                $scope.JdccCdUnit = data.speList;
            } else {//省级
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-asms-web/checkTask/getObjById",
                    data: {id: $scope.asms_Check_checkTask_forReport},
                    method: "post"
                }).success(function (data) {
                    $scope.checkTaskInfomation = data.data;
                    $scope.jcObjList = data.data;
                    $("div[name='jcObjSpan']").show();
                });
            }
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'LXJC') {
        $("#titleName").html("例行监测");
        //数据表格参数
        $scope.dataTableUrl = "/sofn-asms-web/checkTask/getZLTaskSampleListByJcInfo";
        $scope.lxjcQtUnit = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/routineMonitor/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.rmTaskInfomation = data.data;
            $("div[name='LxJcQtUnitSpan']").show();
            $scope.lxjcQtUnit = data.rouList;
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC') {
        $("#titleName").html("专项检测");
        //数据表格参数
        $scope.dataTableUrl = "/sofn-asms-web/checkTask/getZLTaskSampleListByJcInfo";
        $scope.ZxjcQtUnit = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/specialMonitor/getTaskById",
            data: {id: $scope.asms_Check_checkTask_forReport},
            method: "post"
        }).success(function (data) {
            $scope.smTaskInfomation = data.data;
            $("div[name='ZxjcQtUnitSpan']").show();
            $scope.ZxjcQtUnit = data.speList;
        }).error(function (data) {
            
        });
    }
    if ($scope.asms_Check_Task_Type_Title == 'FJRW') {
        $("#titleName").html("复检任务");
    }


    /**
     * 根据承担单位id和父级任务id查找承担单位新建子任务列表
     * */
    window.selectCdUnit = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getTasksByCreateOrgIdAndParentTaskId",
            data: {
                parentTaskId: $scope.asms_Check_checkTask_forReport,
                createOrgId: $("select[name='JdccCdUnit']").val()
            },
            method: "post"
        }).success(function (data) {
            $scope.JDccSonTask = data.data;
            $scope.jcObjList = [];
            $("select[name='JDccSonTask']").val("");
            $("select[name='jcObj']").val("");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function (data) {
            
        });
    };

    /**
     * 根据子任务查找检测对象列表
     * */
    window.selectCdUnitTask = function () {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/checkTask/getObjById",
            data: {id: $("select[name='JDccSonTask']").val()},
            method: "post"
        }).success(function (data) {
            $scope.jcObjList = data.data;
            $("select[name='jcObj']").val("");
            $("#grid").dataTable().api().ajax.reload();
        }).error(function (data) {
            
        });
    };

    window.selectObj = function () {
        $("#grid").dataTable().api().ajax.reload();
    };


    /**
     * 抽样单列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        ajax: {
            url: $scope.dataTableUrl,
            headers: {
                token: window.localStorage.getItem("token")
            },
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
                params.id = $scope.asms_Check_checkTask_forReport;//任务id[部级任务、非部级任务]
                var JdccCdUnit = $("select[name='JdccCdUnit']").val();//承担单位
                var sonTaskId = $("select[name='JDccSonTask']").val();//子任务
                if (JdccCdUnit != "") {
                    params.JdccCdUnit = JdccCdUnit;
                }
                if (sonTaskId != "") {
                    params.sonTaskId = sonTaskId;
                }
                //根据不同任务确定查询参数
                if ($scope.asms_Check_Task_Type_Title == 'JDCC') {
                    if ($("select[name='jcObj']").val() != "") {
                        params.taskObjId = $("select[name='jcObj']").val().trim();
                    }
                } else if ($scope.asms_Check_Task_Type_Title == 'LXJC') {
                    if ($("select[name='lxjcQtUnit']").val() != "") {
                        params.parentTaskId = $("select[name='lxjcQtUnit']").val().trim();
                    }
                } else if ($scope.asms_Check_Task_Type_Title == 'ZXJC') {
                    if ($("select[name='ZxjcQtUnit']").val() != "") {
                        params.parentTaskId = $("select[name='ZxjcQtUnit']").val().trim();
                    }
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
                }
            },
            {
                data: "sampleName",
                title: "样品名称"
            },
            {
                data: "sampleCode",
                title: "样品编码"
            },
            {
                data: "tradeMark",
                title: "商标"
            },
            {
                data: "grade",
                title: "等级"
            },
            {
                data: "sampleBase",
                title: "抽样基数"
            },
            {
                data: "samplePlace",
                title: "抽样场所"
            },
            {
                data: "sampleReport",
                title: "状态",
                width: "6%",
                render: function (data, type, row) {
                    if ("2" == data) {
                        return "已上报";
                    } else if ("3" == data || "4" == data) {
                        return "已退回";
                    }
                }
            },
            {
                data: "sampleCode",
                title: "详情",
                render: function (data, type, row) {
                    var butt = "";
                    butt += '<a style="cursor:pointer" onclick="querySampleInfo(\'' + data + '\' , \''+ row.organTaskId + '\')">抽样单详情</a>';
                    return butt;
                }
            }
        ]
    });

    /**
     * 样品及检测信息列表
     * */
    window.querySampleInfo = function (o,p) {
        window.localStorage.removeItem("asms_sample_info_id");
        window.localStorage.setItem("asms_sample_info_id", o);
        window.localStorage.removeItem("asms_sample_info_organTaskId");
        window.localStorage.setItem("asms_sample_info_organTaskId", p);
        var type = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if(type=="JDCC"){
            $state.go("index.content.asms/check/TaskSample/checkSample");
        }else{
            $state.go("index.content.asms/check/TaskSample/sample");
        }

    }

    //查看详情页面
    window.viewOrganTask=function(obj){
        $.ajax({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesSample/getCheckInfoIdBySampleId",
            type:"post",
            dataType:"json",
            data:{sampleId:obj},
            success:function(data){
                if(data.httpCode=="200"){
                    window.localStorage.setItem("ID",data.checkInfoId);
                    // $scope.ID = obj;
                    $("#product_info2").modal("show");
                    //清理数据
                    var tableName = $('#productList');
                    if ($.fn.dataTable.isDataTable(tableName)) {
                        tableName.DataTable().clear();
                        tableName.DataTable().destroy();
                    }
                    //加载数据
                    $("#productList").dataTable({
                        processing : true, // 加载时提示
                        serverSide : false, // 分页，是否服务器端处理
                        ordering : false,
                        searching : false,
                        bProcessing: true,
                        paging:false,
                        dom : '<"top">rt<"bottom"pli><"clear">',
                        autoWidth : false,
                        ajax : {
                            headers: {
                                token: window.localStorage.getItem("token")
                            },
                            url : "/sofn-ads-web/adsCheckInfo/getChild",
                            dataSrc:function(json){
                                // json.recordsTotal=json.data.total;//总个数
                                // json.recordsFiltered=json.data.total;
                                // json.pageNumber=json.data.pageNum;//页码
                                // json.pageSize=json.data.pageSize;//每页个数
                                // json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                                // json.length=json.data.pageSize;
                                return json.data;
                            },
                            data : function(params) {
                                params.id = data.checkInfoId;
                                params.token = window.localStorage.getItem("token");
                            },
                            type:"post"
                        },
                        columns : [
                            {
                                data : "checkProject",
                                title : "检测项目"
                            },
                            {
                                data : "checkNum",
                                title : "检测值",
                                render : function(data, type, row,meta) { // 模板化列显示内容
                                    if(0 == data){
                                        return '<a id="'+meta.row+'" value="'+"未检出"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">未检出</a>';
                                    }else{
                                        return '<a id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                                    }
                                },
                            },
                            {
                                data : "judgeNum",
                                title : "限量值"
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
                        ],
                        language : {
                            "sProcessing" : "处理中...",
                            "sLengthMenu" : "显示 _MENU_ 项结果",
                            "sZeroRecords" : "没有匹配结果",
                            "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                            "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
                            "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
                            "sInfoPostFix" : "",
                            "sSearch" : "搜索:",
                            "sUrl" : "",
                            "sEmptyTable" : "表中数据为空",
                            "sLoadingRecords" : "载入中...",
                            "sInfoThousands" : ",",
                            "oPaginate" : {
                                "sFirst" : "首页",
                                "sPrevious" : "上页",
                                "sNext" : "下页",
                                "sLast" : "末页"
                            },
                            "oAria" : {
                                "sSortAscending" : ": 以升序排列此列",
                                "sSortDescending" : ": 以降序排列此列"
                            }
                        },
                        sPaginationType : "full_numbers"
                    });
                }else{
                    $.jBox.tip(data.msg);
                }
            },
            error: function (data) {
                $.jBox.tip("失败");
            }
        });
    }
    //返回
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
                if (rows[k].sampleReport == 3 || rows[k].sampleReport == 4 ) {
                    $.jBox.tip("包含已退回样品!");
                    return false;
                }
            }
        }
        $scope.taskName = window.localStorage.getItem("asms_Check_checkTask_taskName_forReport");
        $scope.sampleDeparment = rows[0].sampleDeparment;
        $scope.sampleBackIds = [];
        $.each(rows, function(i,o){
            $scope.sampleBackIds.push(o.id);
        });
        $("#sampleTable").html("");
        for (var i = 0; i < rows.length; i++) {
            var _row = '<tr><td class="mt15" style="text-align: right;padding-left: 50px;font-weight: bold;height: 30px;padding-right: 10px;">样品名称 :</td><td width="200px"><span>'+rows[i].sampleName+'</span></td>';
            _row += '<td><label>样品编号 :</label></td><td width="200px"><span>'+ rows[i].sampleCode +'</span></td></tr>';
            $("#sampleTable").append(_row);
        }
        //打开模态框
        $("#sampleBackModal").modal("show");
    }

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
            params: {"ids": $scope.sampleBackIds.join(","),"reason": reason, "receiptClass":"S"}
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

