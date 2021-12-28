/**
 * Created by Administrator on 2018/3/27 0027.
 */
var myapp = angular.module("myapp", []).controller("qryAssessList", function ($scope, $http, $state,$timeout) {
    var vm = this;
    //获取用户信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/subject/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        $.fn.mycity("querySheng","queryShi","queryXian",$scope.underArea);
        $scope.disableAreaSelectByAreaId("querySheng","queryShi","queryXian");
        //所属行业默认不限、组织形式不限、主体类别不限
        $("input[name='entityIndustryLimit']:first").attr("checked",true);
        $("input[name='entityScaleLimit']:first").attr("checked",true);
        $("input[name='entityTypeLimit']:first").attr("checked",true);
        $("input[name='inspectionTypeLimit']:first").attr("checked",true);
        loadGrid();
    }).error(function(data){
        
    })
    //限制区域是否可选
    $scope.disableAreaSelectByAreaId = function(provinceId,cityId,countyId){
        if($("#"+provinceId).val()!=null&&$("#"+provinceId).val()!=""&&$("#"+provinceId).val()!=undefined){
            $("#"+provinceId).attr("disabled",true);
        }
        if($("#"+cityId).val()!=null&&$("#"+cityId).val()!=""&&$("#"+cityId).val()!=undefined){
            $("#"+cityId).attr("disabled",true);
        }
        if($("#"+countyId).val()!=null&&$("#"+countyId).val()!=""&&$("#"+countyId).val()!=undefined){
            $("#"+countyId).attr("disabled",true);
        }
    }

    //初始化
    $("#ToCheck").click(function () {
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
        $(this).text("二次查询");
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
    //重置区域
    $scope.resetArea=function($event){
        //重置 二次查询
        if ($event) {
            $($event.currentTarget)
                .prev()
                .text("查询");
        }
        if($scope.organization.orgLevel!="1") {
            $.fn.mycity("querySheng", "queryShi", "queryXian", $scope.underArea);
            if ($("#querySheng").val() != null && $("#querySheng").val() != "" && $("#querySheng").val() != undefined) {
                $("#querySheng").attr("disabled", true);
            }
            if ($("#queryShi").val() != null && $("#queryShi").val() != "" && $("#queryShi").val() != undefined) {
                $("#queryShi").attr("disabled", true);
            }
            if ($("#queryXian").val() != null && $("#queryXian").val() != "" && $("#queryXian").val() != undefined) {
                $("#queryXian").attr("disabled", true);
            }
        }else{
            $.fn.mycity("querySheng", "queryShi", "queryXian");
        }
        $("select[name='taskResult']").val("");
        $("select[name='taskDateType']").val("");
        $("input[name='taskDateYear']").val("");
        $("select[name='taskDateQuarter']").val("");
        $("select[name='taskDateMonths']").val("");
        $("input[name='userName']").val("");
        $("input[name='dateBegin']").val("");
        $("input[name='dateEnd']").val("");
        $("#taskDateYear").parent().hide();
        $("#taskDateQuarter").parent().hide();
        $("#taskDateMonths").parent().hide();
    }
//分页
    function loadGrid() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-qry-web/supervisionInspection/getAssessList_v2",
                // url: "/sofn-asms-web/tspinstask/list",
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
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].INSPECTION_TIME != null && json.data.list[i].INSPECTION_TIME != "" && json.data.list[i].INSPECTION_TIME != undefined) {
                            json.data.list[i].INSPECTION_TIME = new Date(parseInt(json.data.list[i].INSPECTION_TIME)+28800000).toISOString().slice(0, 10);
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
                    params.taskType = $("select[name='Maintype']").val()
                    params.taskDateType = $("select[name='taskDateType']").val();
                    params.taskDateYear = $("#taskDateYear").val();
                    params.taskDateQuarter = $("#taskDateQuarter").val();
                    params.taskDateMonths = $("#taskDateMonths").val();
                    params.userName = $("input[name='userName']").val();
                    params.creStartDate = $("input[name='dateBegin']").val();
                    params.creEndDate = $("input[name='dateEnd']").val();
                    params.taskResult = $("select[name='taskResult']").val();
                    var province = $("#querySheng").val();
                    var city = $("#queryShi").val();
                    var county = $("#queryXian").val();
                    var areaId = "";
                    if (county != null && county != undefined && county != "") {
                        areaId = county;
                    } else if (city != null && city != undefined && city != "") {
                        areaId = city;
                    } else if (province != null && province != undefined && province != "") {
                        areaId = province;
                    }
                    params.inspectionAreaId = areaId;

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
                            return '<p name="taskResult" style="color:#006000">合格</p> ';
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
                }
            ]
        });
    }

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
        var userName = $("input[name='userName']").val();
        var input7 = $('<input>');
        input7.attr('type', 'hidden');
        input7.attr('name', 'userName');
        input7.attr('value', userName);
        var taskResult = $("select[name='taskResult']").val();
        var input8 = $('<input>');
        input8.attr('type', 'hidden');
        input8.attr('name', 'taskResult');
        input8.attr('value', taskResult);
        var province = $("#querySheng").val();
        var city = $("#queryShi").val();
        var county = $("#queryXian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (province != null && province != undefined && province != "") {
            areaId = province;
        }
        var input9 = $('<input>');
        input9.attr('type', 'hidden');
        input9.attr('name', 'area');
        input9.attr('value', areaId);

        var creStartDate = $("input[name='dateBegin']").val();
        var input10 = $('<input>');
        input10.attr('type', 'hidden');
        input10.attr('name', 'creStartDate');
        input10.attr('value', creStartDate);

        var creEndDate = $("input[name='dateEnd']").val();
        var input11 = $('<input>');
        input11.attr('type', 'hidden');
        input11.attr('name', 'creEndDate');
        input11.attr('value', creEndDate);

        //排序传参
        if ($scope.chartParams.order[0]) {
            var orderIndex = $scope.chartParams.order[0]["column"];
            var orderKey = $scope.chartParams.columns[orderIndex]["data"];
            if (orderKey != "function") {
                $scope.chartParams.orderBy =
                    orderKey + " " + $scope.chartParams.order[0]["dir"];
            }
        }
        var input12 = $("<input>");
        input12.attr("type", "hidden");
        input12.attr("name", "orderBy");
        input12.attr("value", $scope.chartParams.orderBy);
        //form
        var form=$("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action','/sofn-qry-web/supervisionInspection/exportAssessList');
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
        form.append(input11);
        form.append(input12);
        $('body').append(form);
        form.submit();
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

    //点击查看列表
    window.AssCheck = function (obj) {
        var dataAryy = obj.split(",");
        window.localStorage.removeItem("QRY_XCKH_XCPERSON_ID");
        window.localStorage.setItem("QRY_XCKH_XCPERSON_ID", dataAryy);
        $state.go("index.content.qry/supervisionInspection/qryIspecReportList");
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
        url: "/sofn-qry-web/supervisionInspection/getAssessStatis",
        grid: [2,2,1], //水平宽度比例
        //业务参数
        data: function() {
            return $scope.chartParams;
        }
    };
});
