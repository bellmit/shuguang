var myapp = angular.module("myapp", []).controller("Assess", function ($scope, $http, $state) {
    //判定页面权限、tab页、按钮
    $scope.iswrite = getAuthorityByMenuName("巡查人员考核");
    $scope.AssessQueryParams = JSON.parse(window.sessionStorage.getItem("AssessQueryParams"));
    //初始化
    $("#ToCheck").click(function () {
        //点击间隔
        $scope.$apply($scope.btnDisabled = true);
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        var AssessQueryParams = {};
        AssessQueryParams.taskType = $("select[name='Maintype']").val()
        AssessQueryParams.taskDateType = $("select[name='Year']").val()
        AssessQueryParams.userName = $("input[name='username']").val();
        AssessQueryParams.taskResult = $("select[name='taskResult']").val();
        window.sessionStorage.setItem("AssessQueryParams",JSON.stringify(AssessQueryParams));
        $("#grid").dataTable().api().ajax.reload();
    });
    if($scope.AssessQueryParams){
        $("select[name='Year']").val($scope.AssessQueryParams.Year);
        $("select[name='taskResult']").val($scope.AssessQueryParams.taskResult);
        $("select[name='username']").val($scope.AssessQueryParams.username);
    }
    //改变考核评判提交信息
    window.Checkchange = function (obj, val) {
        $("button").attr("disabled", true);
        $.ajax({
            url: "/sofn-asms-web/tspinstask/taskResult",
            type: "post",
            data: {id: obj, taskResult: val.value},
            headers: {
                token: window.localStorage.getItem("token")
            },
            beforeSend: function () {
                $('#web_app_model_userOprXz').modal({backdrop: 'static', keyboard: false});//禁止空白处点击与键盘操作关闭
                $("#web_app_model_userOprXz").modal("show");
            },
            success: function (data) {
                $("button").attr("disabled", false);
                if(data.httpCode == 200){
                    if(val.value == '合格'){
                        $(val).parent().parent().parent().find("b[name='taskResult'],p[name='taskResult']").parent().html('<b name="taskResult" style="color:#006000">合格</b>');
                        $(val).val("");
                    }else if(val.value == '不合格'){
                        $(val).parent().parent().parent().find("b[name='taskResult'],p[name='taskResult']").parent().html('<b name="taskResult" style="color:#EA0000">不合格</b>');
                        $(val).val("");
                    }
                }else{
                    $.jBox.tip(data.msg);
                }
                // $("#grid").dataTable().api().ajax.reload();
                $("#web_app_model_userOprXz").modal("hide");
            },
            error: function (data) {
                $("button").attr("disabled", false);
                $("#web_app_model_userOprXz").modal("hide");
                $.jBox.tip("失败");
            }
        });
    };
//分页
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-asms-web/tspinstask/list",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    if (json.data == null || json.data == undefined) {
                        json.recordsTotal = 0;//总个数
                        json.recordsFiltered = 0;
                        json.pageNumber = 1;//页码
                        json.pageSize = 10;//每页个数
                        json.start = 0;//起始位置
                        json.length = 10;
                        return json.data;
                    }
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].INSPECTION_TIME != null && json.data.list[i].INSPECTION_TIME != "" && json.data.list[i].INSPECTION_TIME != undefined) {
                            json.data.list[i].INSPECTION_TIME = new Date(parseInt(json.data.list[i].INSPECTION_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                        //数据填充
                        json.data.list[i].cdData = [];
                        json.data.list[i].cdData[0] = $.trim(json.data.list[i].TASKDATETYPE);//考核类型
                        json.data.list[i].cdData[1] = $.trim(json.data.list[i].TASKDATEYEAR);//年
                        json.data.list[i].cdData[2] = $.trim(json.data.list[i].TASKDATEMONTHS);//月
                        json.data.list[i].cdData[3] = $.trim(json.data.list[i].TASKDATEQUARTER);//季度
                        json.data.list[i].cdData[4] = $.trim(json.data.list[i].USERID);//用户id
                        json.data.list[i].cdData[5] = $.trim(json.data.list[i].TASKTYPE);//任务类型

                        //设定时间
                        var taskType = $.trim(json.data.list[i].TASKDATETYPE);
                        var taskYear = json.data.list[i].TASKDATEYEAR;//任务时间-年
                        var taskYearMonth = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEMONTHS;//任务时间-年+月
                        var taskYearQuarter = json.data.list[i].TASKDATEYEAR + "" + json.data.list[i].TASKDATEQUARTER;//任务时间-年+季度
                        if (taskType == "年度") {
                            json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年";
                        } else if (taskType == "月度") {
                            json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEMONTHS + "月";
                        } else if (taskType == "季度") {
                            json.data.list[i].taskDate = json.data.list[i].TASKDATEYEAR + "年" + json.data.list[i].TASKDATEQUARTER + "季度";
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    var AssessQueryParams = JSON.parse(window.sessionStorage.getItem("AssessQueryParams"));
                    if(AssessQueryParams){
                        params.taskType = AssessQueryParams.taskType;
                        params.taskDateType =AssessQueryParams.taskDateType;
                        params.userName = AssessQueryParams.userName;
                        params.taskResult = AssessQueryParams.taskResult;
                    }else{
                        params.taskType = $("select[name='Maintype']").val()
                        params.taskDateType = $("select[name='Year']").val()
                        params.userName = $("input[name='username']").val()
                        params.taskResult = $("select[name='taskResult']").val()
                    }
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"
                },
                {
                    data: "USERNAME",
                    title: "巡查人员",
                    width: "8%",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>10){
                            return "<a id='USERNAME"+row.ID+"' onmouseover=showPopContent('USERNAME"+row.ID+"','"+data+"')>"+data.substring(0,10)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                },
                //{
                //    data : "TASKTYPE",
                //    title : "任务类型",
                //    width : "3%"
                //},
                {
                    data: "TASKDATETYPE",
                    title: "考核类型",
                    width: "5%"
                },
                {
                    data: "taskDate",
                    title: "考核时间",
                    width: "5%"
                },
                {
                    data: "INSPECTIONCOUNT",
                    title: "设定次数",
                    width: "4%"
                },
                {
                    data: "INSPECTIONAREAID",
                    title: "所属区域",
                    render:function(data,type,row){
                        data = $.fn.Cityname(data);
                        if(data!=null&&data!=undefined&&data.length>13){
                            return "<a id='INSPECTIONAREAID"+row.ID+"' onmouseover=showPopContent('INSPECTIONAREAID"+row.ID+"','"+data+"')>"+data.substring(0,13)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                    width: "10%"
                },
                {
                    data: "INSPECTIONREALCOUNT",
                    title: "实际次数",
                    width: "4%"
                },
                {
                    data: "TASKSTATUS",
                    title: "任务状态",
                    width: "7%",
                    render: function(data, type, row){
                        if (row.INSPECTIONREALCOUNT&&row.INSPECTIONCOUNT&&row.INSPECTIONREALCOUNT >= row.INSPECTIONCOUNT) {
                            data = "已完成";
                        }
                        return data;
                    }
                },
                {
                    data: "TASKRESULT",
                    title: "考核结果",
                    width: "4%",
                    render: function (data, type, row) {
                        if (data == null) {
                            return '<p name="taskResult">未审核</p> ';
                        } else if (data == "合格") {
                            return '<b name="taskResult" style="color:#006000">合格</b> ';
                        } else if (data == "不合格") {
                            return '<b name="taskResult" style="color:#EA0000">不合格</b> ';
                        }
                    }
                },
                {
                    data: "cdData",
                    title: "报告",
                    render: function (data, type, row) {
                        return '<a  style="cursor: pointer" class="color" onclick="AssCheck(\'' + data + '\')">查看</a>';//转义传值
                    },
                    width: "3%"
                },
                {
                    data: "ID",
                    title: "操作",
                    visible: $scope.iswrite==2,
                    render: function (data, type, row) {
                        if($scope.iswrite==2){
                            var disDom = '<div class="gui-select"><select style="background-color:#d7d7d7;" disabled = "disabled"  class="CheckChange" style="color:black"  onchange="Checkchange(\'' + data + '\',this)">' +
                                '<option value="">审核</option>' +
                                '<option style="color:#006000;font-weight:bold" value="合格"></option>' +
                                '<option style="color:#EA0000;font-weight:bold" value="不合格">不合格</option>' +
                                '</select></div>';
                            var dom = '<div class="gui-select"><select style="background-color:#47b995;" class="CheckChange" style="color:black"  onchange="Checkchange(\'' + data + '\',this)">' +
                                '<option value="">审核</option>' +
                                '<option style="color:#006000;font-weight:bold" value="合格">合格</option>' +
                                '<option style="color:#EA0000;font-weight:bold" value="不合格">不合格</option>' +
                                '</select></div>';
                            //任务时间
                            var taskType = $.trim(row.TASKDATETYPE);
                            var taskYear = $.trim(row.TASKDATEYEAR);
                            var taskYearMonth = $.trim(row.TASKDATEYEAR + row.TASKDATEMONTHS);
                            var taskYearQuarter = $.trim(row.TASKDATEYEAR + row.TASKDATEQUARTER);
                            //当前时间
                            var date = new Date();
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
                                if (nowYear > taskYear||row.INSPECTIONREALCOUNT>=row.INSPECTIONCOUNT) {
                                    return dom;
                                } else {
                                    return disDom;
                                }
                            } else if (taskType == "月度") {
                                if (nowYearMonth > taskYearMonth||row.INSPECTIONREALCOUNT>=row.INSPECTIONCOUNT) {
                                    return dom;
                                } else {
                                    return disDom;
                                }
                            } else if (taskType == "季度") {
                                if (nowYearQuarter > taskYearQuarter||row.INSPECTIONREALCOUNT>=row.INSPECTIONCOUNT) {
                                    return dom;
                                } else {
                                    return disDom;
                                }
                            } else {
                                return "Error";
                            }
                        }
                    },
                    width: "5%"
                }
            ]
        });
    });

    //点击查看列表
    window.AssCheck = function (obj) {
        var dataAryy = obj.split(",");
        window.localStorage.removeItem("ASMS_XCKH_XCPERSON_ID");
        window.localStorage.setItem("ASMS_XCKH_XCPERSON_ID", dataAryy);
        $state.go("index.content.asms/ass/ispecReportList");
    };
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
});
