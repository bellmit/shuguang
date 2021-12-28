var myapp=angular.module("myapp",[]);
myapp.controller("queryStatistics",function($scope,$http,$state){

    //获取用户信息、组织信息
    $scope.user = {};
    $scope.organization = {};
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findCurrentUserByToken",
        method: "post"
    }).success(function(data) {
        $scope.user = data.user;
        $scope.organization = $scope.user.organization;
        //定义一个管辖区域，通过机构级别+区域来判断
        // $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        // //初始化区域下拉框初始选项
        // $scope.reloadArea();
        $scope.initDate();
        $scope.loadGrid();
    }).error(function(data){
        
    })

    $scope.initDate = function () {
        //初始化查询开始时间 默认2017-06-30
        $("input[name='qryStartTime']").val('2017-06-30');
        window.localStorage.setItem('qryStartTime', $("input[name='qryStartTime']").val());

        var now = new Date();
        var qryEndTime = now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
        $("input[name='qryEndTime']").val(qryEndTime);
        window.localStorage.setItem('qryEndTime', $("input[name='qryEndTime']").val());
    }


    //重置搜索条件
    $scope.reSetFilter = function() {
        $("#queryForm").form("reset");
        $("input[name='qryStartTime']").val('2017-06-30');
        var now = new Date();
        var qryEndTime = now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
        $("input[name='qryEndTime']").val(qryEndTime);
    }
    //bootstrap表格
    //生产经营主体信息
    $scope.loadGrid = function() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/qryStatis/queryStatistics",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    return json.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.productName = $("input[name='productName']").val();
                    params.productTraceCode = $("input[name='productTraceCode']").val();
                    params.qryStartTime = $("input[name='qryStartTime']").val();
                    params.qryEndTime = $("input[name='qryEndTime']").val();
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"
                },
                {
                    data: "PRO_NAME",
                    title: "产品名称",
                    width:"10%"
                },
                {
                    data: "PRO_TYPE",
                    title: "产品种类",
                    width:"10%"
                },
                {
                    data: "CPZSM",
                    title: "追溯码",
                    width:"30%"
                },
                {
                    data:"",
                    title:"查询时间",
                    width:"14%",
                    render:function (data,type,row) {
                        return window.localStorage.getItem('qryStartTime') + '至' + window.localStorage.getItem('qryEndTime');
                    }
                },
                {
                    data:"TOTAL_COUNT",
                    title:"总查询次数",
                    width:"8%",
                },
                {
                    data:"WEB_COUNT",
                    title:"web门户端查询次数",
                    width:"8%",
                },
                {
                    data:"APP_COUNT",
                    title:"第三方应用查询次数",
                    width:"8%",
                },
                {
                    data:"",
                    title:"操作",
                    width:"10%",
                    render:function(data, type, row,meta){
                        return '<a href="javascript:showDetailQryInfo(\''+meta.row+'\')">查看</a>';
                    }
                }
            ],
        });
    }

    //重新加载表格
    $scope.queryInfoList=function(){
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#entGrid").dataTable().api().ajax.reload();
        window.localStorage.setItem('qryStartTime', $("input[name='qryStartTime']").val());
        window.localStorage.setItem('qryEndTime', $("input[name='qryEndTime']").val());
    }

    //导出Excel
    $scope.exportData = function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/qryStatis/exportQueryInfo");
        var token = window.localStorage.getItem("token");

        var productName = $("input[name='productName']").val();
        var productTraceCode = $("input[name='productTraceCode']").val();
        var qryStartTime = $("input[name='qryStartTime']").val();
        var qryEndTime = $("input[name='qryEndTime']").val();

        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'token');
        input1.attr('value', token);

        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'productName');
        input2.attr('value', productName);

        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'productTraceCode');
        input3.attr('value', productTraceCode);

        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'qryStartTime');
        input4.attr('value', qryStartTime);

        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'qryEndTime');
        input5.attr('value', qryEndTime);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
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

    $scope.loadDeatilQryInfo = function () {
        if($scope.row != null) {
            $scope.detailQryInfoTable = $("#detailQryInfoTable").dataTable({
                ajax: {
                    url: "/sofn-qry-web/qryStatis/queryStatisticsDetailInfo",
                    dataSrc: function (json) {
                        json.recordsTotal = json.page.recordsTotal;//总个数
                        json.recordsFiltered = json.page.recordsFiltered;
                        json.start = json.page.start;//起始位置
                        json.length = json.page.length;
                        return json.list;
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    data: function (params) {
                        params.productName = $scope.row.PRO_NAME;
                        params.productTraceCode = $scope.row.CPZSM;
                        params.qryStartTime = $("input[name='qryStartTime']").val();
                        params.qryEndTime = $("input[name='qryEndTime']").val();
                        params.sourcesQuery = $("#sourcesQuery").val();
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "序号",
                        data: function (data, type, row, meta) {
                            return meta.row + 1
                        },
                        width:"4%"
                    },
                    {
                        data: "CPZSM",
                        title: "追溯码",
                        width:"30%"
                    },
                    {
                        data: "SOURCESQUERY",
                        title: "查询来源",
                        width: "16%",
                        render: function (data, type, row) {
                            if (data == '1') {
                                return "web门户";
                            } else if (data == '2') {
                                return "第三方应用";
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        data: "QUERY_IP",
                        title: "查询IP",
                        width: "16%"
                    },
                    {
                        data: "QUERY_TIME",
                        title: "查询时间",
                        render: function(data){
                            return data?new Date(data).toISOString().slice(0,10):"";
                        },
                        width: "20%"
                    },
                ],

            });
        }
    }

    window.reloadDetailQryData = function () {
        $scope.detailQryInfoTable.api().ajax.reload();
    }

    window.showDetailQryInfo = function (index) {
        $("#sourcesQuery").val("");
        var rows = $("#entGrid").DataTable().rows().data();
        $scope.row = rows[index];
        if($("#detailQryInfoTable").hasClass('dataTable')){
            $scope.detailQryInfoTable.api().ajax.reload();
        }else{
            $scope.loadDeatilQryInfo();
        }
        $("#myModal").modal("show");

    }

})