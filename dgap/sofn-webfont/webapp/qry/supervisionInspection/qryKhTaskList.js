var myapp = angular.module("myapp", []).controller("qryKhTaskList", function ($scope, $http, $state,$timeout) {
    var vm = this;
    $scope.taskType = {};
    $scope.taskTypeTouched = false;

    /**
     * 表单验证
     * 新增/修改/退出需清除验证状态
     * */
    //验证初始化
    $('#attributeForm').bootstrapValidator();
    //验证是否通过
    $scope.dataFormValidator = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        } else {
            return true;
        }
    };
    //查询按钮
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#grid").dataTable().api().ajax.reload();
    });
    //清除验证状态
    $scope.dataFormValidatorReset = function () {
        $("#attributeForm").data("bootstrapValidator").destroy();
        $("#attributeForm").bootstrapValidator();
    };
    //默认选中任务类型不限
    $("input[name='taskTypeLimit']").prop("checked",true);
    /**
     *----------变量------------
     * @author LiBling
     * */
    $scope.KhTaskId = "";
    /**
     *----------任务列表------------
     * @author LiBling
     * */
    //列表
    $(function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-qry-web/supervisionInspection/getKhTaskList_v2",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    for (var i = 0; i < json.list.length; i++) {
                        //日期格式化
                        if (json.list[i].CREATETIME != null && json.list[i].CREATETIME != "" && json.list[i].CREATETIME != undefined) {
                            json.list[i].CREATETIME = new Date(parseInt(json.list[i].CREATETIME)+28800000).toISOString().slice(0, 10);
                        }
                        //任务状态判断
                        //任务时间

                        var date = new Date();
                        var taskType = $.trim(json.list[i].TASKDATETYPE);
                        var taskYear = json.list[i].TASKDATEYEAR;//任务时间-年
                        var taskYearMonth = json.list[i].TASKDATEYEAR + "" + json.list[i].TASKDATEMONTHS;//任务时间-年+月
                        var taskDateMonth = json.list[i].TASKDATEMONTHS;
                        var taskYearQuarter = json.list[i].TASKDATEYEAR + "" + json.list[i].TASKDATEQUARTER;//任务时间-年+季度
                        var taskQuarter = json.list[i].TASKDATEQUARTER;

                        //当前时间
                        var nowYear = date.getFullYear();//当前年
                        var nowYearMonth;//当前年+月
                        var nowMonth = date.getMonth() + 1;//当前月份
                        if (nowMonth < 10) {
                            nowYearMonth = date.getFullYear() + "0" + nowMonth;
                        } else {
                            nowYearMonth = date.getFullYear() + "" + nowMonth;
                        }
                        var currQuarter = Math.floor(( nowMonth % 3 == 0 ? ( nowMonth / 3 ) : ( nowMonth / 3 + 1 ) ));
                        var nowYearQuarter = date.getFullYear() + "" + currQuarter;//当前年+季度
                        if (taskType == "年度") {
                            if (nowYear > taskYear) {
                                json.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                            } else if (nowYear < taskYear) {
                                json.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                            } else {
                                json.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                            }
                            json.list[i].taskDate = json.list[i].TASKDATEYEAR + "年";
                        } else if (taskType == "月度") {
                            if (nowYearMonth > taskYearMonth) {
                                json.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                            } else if (nowYearMonth < taskYearMonth) {
                                json.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                            } else {
                                json.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                            }
                            json.list[i].taskDate = json.list[i].TASKDATEYEAR + "年" + json.list[i].TASKDATEMONTHS + "月";
                        } else if (taskType == "季度") {
                            if (nowYearQuarter > taskYearQuarter) {
                                json.list[i].ENABLE = '<p style="color:#EA0000"><B>已结束</B></p>';
                            } else if (nowYearQuarter < taskYearQuarter) {
                                json.list[i].ENABLE = '<p style="color:#5C605F"><B>未开始</B></p>';
                            } else {
                                json.list[i].ENABLE = '<p style="color:#006000"><B>进行中</B></p>';
                            }
                            json.list[i].taskDate = json.list[i].TASKDATEYEAR + "年" + json.list[i].TASKDATEQUARTER + "季度";
                        }
                        if (json.list[i].FINISHFLAG == true) {
                            json.list[i].ENABLE = '<p style="color:#EA0000"><B>已完成</B></p>';
                        }

                    }
                    return json.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.taskDateType = $("select[name='taskDateType']").val();
                    params.taskDateYear = $("#taskDateYear").val();
                    params.taskDateQuarter = $("#taskDateQuarter").val();
                    params.taskDateMonths = $("#taskDateMonths").val();
                    params.inspector = $("input[name='inspector']").val();
                    params.creStartDate = $("input[name='dateBegin']").val();
                    params.creEndDate = $("input[name='dateEnd']").val();
                    var taskType = $("input[name='taskType']:checked");
                    var taskTypeArr = new Array();
                    for (var i = 0; i < taskType.length; i++) {
                        taskTypeArr.push($(taskType[i]).val());
                    }
                    params.taskType = taskTypeArr.toString();

                    $scope.chartParams = params; //图标参数赋值
                    if (vm.chart) {
                        vm.chart.update(); //刷新图标
                    } else {
                        $timeout(function () {
                            vm.chart.update(); //刷新图标
                        }, 1000);
                    }

                },
                type: "post"
            },
            columns: [
                {
                    data: "ck",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        return '<input type="checkbox"  name="ck"/>';
                    }
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    }
                },
                {
                    data: 'ID',
                    title: 'ID',
                    visible: false
                },
                {
                    data: "INSPECTIONAREAID",
                    title: "巡查区域",
                    width:"18%",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>14){
                            return "<a id='INSPECTIONAREAID"+row.ID+"' onmouseover=showPopContent('INSPECTIONAREAID"+row.ID+"','"+data+"')>"+data.substring(0,14)+"...</a>";
                        }else{
                            return data;
                        }
                    }
                },
                {
                    data: "TASKDATETYPE",
                    title: "考核类型"
                },
                {
                    data: "taskDate",
                    title: "考核时间"
                },
                {
                    data: "TASKTYPENAME",
                    title: "任务类型"
                },
                {
                    data: "INSPECTIONCOUNT",
                    title: "巡查次数"
                },
                {
                    data: "CREATETIME",
                    title: "创建日期"
                },
                {
                    data: "ENABLE",
                    title: "任务状态"
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<a style="cursor: pointer" class="color"  onclick="queryKhTaskInfo(\'' + data + '\')">详情</a>';//转义传值
                    }
                }
            ]
        });
    });


    //列表刷新
    $scope.TableReload = function () {
        $('#grid').dataTable().api().ajax.reload();
    };

    $("#selectTocheck").click(function () {
        $("#selectGrid").dataTable().api().ajax.reload();
    });

    /**
     *----------任务操作 新增 修改 查看------------
     * @author LiBling
     * */

    // //字典数据初始化
    // $scope.KhTaskType = [];//考核任务类型
    // $http({
    //     headers: {
    //         token: window.localStorage.getItem("token")
    //     },
    //     url: "/sofn-qry-web/sysDict/getSysDictByCode",
    //     data: 15,
    //     method: "post"
    // }).success(function (data) {
    //     $scope.KhTaskType = JSON.parse(data.data);
    // }).error(function(data){
    //     
    // })


    //查看
    window.queryKhTaskInfo = function (taskId) {
        $scope.examineInfo = {};
        $("#TaskInfoModel").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/supervisionInspection/findKhTaskById",
            method: "post",
            params: {"id": taskId}
        }).success(function (json) {
            $scope.examineInfo = json.data.inspectionTask;
            //设置复选框的选中状态
            $scope.examineInfo.person = "无";
            if (json.data.inspectionAssess != null && json.data.inspectionAssess != undefined && json.data.inspectionAssess.length != 0) {
                $scope.examineInfo.person = "";
                for (var i = 0; i < json.data.inspectionAssess.length; i++) {
                    if ($scope.examineInfo.person != "") {
                        $scope.examineInfo.person += "," + json.data.inspectionAssess[i].userName;
                    } else {
                        $scope.examineInfo.person += json.data.inspectionAssess[i].userName;
                    }
                }
            }
            switch ($scope.examineInfo.taskDateType) {
                case "年度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年";
                    break;
                case "月度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年" + json.data.inspectionTask.taskDateMonths + "月";
                    break;
                case "季度":
                    $scope.examineInfo.taskDate = json.data.inspectionTask.taskDateYear + "年" + json.data.inspectionTask.taskDateQuarter + "季度";
                    break;
            }
            $scope.examineInfo.inspectionAreaId = $.fn.Cityname(json.data.inspectionTask.inspectionAreaId);//行政区划
        }).error(function(data){
            
        })
    };

    /**
     *----------other------------
     * @author LiBling
     * */
    //查询条件重置
    $scope.cleanQueryPar = function () {
        $("select[name='taskType']").val("");
        $("select[name='taskDateType']").val("");
        $("input[name='taskDateYear']").val("");
        $("select[name='taskDateQuarter']").val("");
        $("select[name='taskDateMonths']").val("");
        $("#taskDateYear").parent().hide();
        $("#taskDateQuarter").parent().hide();
        $("#taskDateMonths").parent().hide();
        $("input[type='checkbox']:checked").attr("checked",false);
        //默认选中任务类型不限
        $("input[name='taskTypeLimit']").prop("checked",true);dateBegin
        $("input[name='inspector']").val("");
        $("input[name='dateBegin']").val("");
        $("input[name='dateEnd']").val("");
    };

    //数据导出
    $scope.exportData=function(){
        //提交数据
        var token = window.localStorage.getItem("token");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'token');
        input1.attr('value', token);
        var taskType = $("input[name='taskType']:checked");
        var taskTypeArr = new Array();
        for (var i = 0; i < taskType.length; i++) {
            taskTypeArr.push($(taskType[i]).val());
        }
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'taskType');
        input2.attr('value', taskTypeArr.toString());
        var taskDateType = $("select[name='taskDateType']").val();
        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'taskDateType');
        input3.attr('value', taskDateType);
        var taskDateYear = $("#taskDateYear").val();
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'taskDateYear');
        input4.attr('value', taskDateYear);
        var taskDateQuarter = $("#taskDateQuarter").val();
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'taskDateQuarter');
        input5.attr('value', taskDateQuarter);
        var taskDateMonths = $("#taskDateMonths").val();
        var input6 = $('<input>');
        input6.attr('type', 'hidden');
        input6.attr('name', 'taskDateMonths');
        input6.attr('value', taskDateMonths);


        var creStartDate = $("input[name='dateBegin']").val();
        var input7 = $('<input>');
        input7.attr('type', 'hidden');
        input7.attr('name', 'creStartDate');
        input7.attr('value', creStartDate);

        var creEndDate = $("input[name='dateEnd']").val();
        var input8 = $('<input>');
        input8.attr('type', 'hidden');
        input8.attr('name', 'creEndDate');
        input8.attr('value', creEndDate);

        var inspector = $("input[name='inspector']").val();
        var input9 = $('<input>');
        input9.attr('type', 'hidden');
        input9.attr('name', 'inspector');
        input9.attr('value', inspector);

        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input10 = $("<input>");
        input10.attr("type", "hidden");
        input10.attr("name", "orderBy");
        input10.attr("value", $scope.chartParams.orderBy);

        //form
        var form=$("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action','/sofn-qry-web/supervisionInspection/exportKhTaskList');
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.append(input8);
        form.append(input9);
        form.append(input10);
        $('body').append(form);
        form.submit();
    }

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
        input1.attr('value', $scope.examineInfo.attachmentAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', $scope.examineInfo.attachmentName);
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
    //选择任务时间类型,选择任务考核时间
    $("select[name='taskDateType']").change(function(){
       var val = $("#taskDateType option:selected").val();
        if(val == "年度"){
            $("#taskDateYear").parent().show();
            $("#taskDateQuarter").parent().hide();
            $("#taskDateMonths").parent().hide();
        }else if(val == "季度"){
            $("#taskDateYear").parent().show();
            $("#taskDateQuarter").parent().show();
            $("#taskDateMonths").parent().hide();
        }else if(val == "月度"){
            $("#taskDateYear").parent().show();
            $("#taskDateQuarter").parent().hide();
            $("#taskDateMonths").parent().show();
        }else{
            $("#taskDateYear").parent().hide();
            $("#taskDateQuarter").parent().hide();
            $("#taskDateMonths").parent().hide();
        }
    })
    //控制复选框
    $scope.limitCheckBox = function(checkBoxName, limitCheckBoxName) {
        var _limChec = $("input[name='" + limitCheckBoxName + "']:checked");
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
        }
        if (_limChec.length==0) {
            _chec.prop("checked", true);
        }
        if (_limChec.length>0 &&_lim.length == _limChec.length) {
            _chec.prop(
                "checked",
                true
            );
            _limChec.each(function(i, o){
                $(o).prop("checked", false);
            })
        }
    }
    //控制复选框--不限
    $scope.limitCheckBoxAll = function (checkBoxName, limitCheckBoxName) {
        var _lim = $("input[name='" + limitCheckBoxName + "']");
        var _chec = $("input[name='" + checkBoxName + "']");
        for (var i = 0; i < _lim.length; i++) {
            var lim = _lim[i].checked;
            if (lim) {
                _chec.prop("checked", false);
            }
        }
    }

    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-qry-web/supervisionInspection/getTaskTypeStatis",
        grid: [1,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
