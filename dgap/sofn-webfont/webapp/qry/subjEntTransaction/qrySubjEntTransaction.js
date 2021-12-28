var myapp=angular.module("myapp",[]);
myapp.controller("qrySubjEntTransaction",function($scope,$http,$state){

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
        if ($scope.organization.orgLevel != 4) {
            $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
        }else {
            $scope.underArea = $scope.organization.regionId;
        }
        //初始化区域下拉框初始选项
        $scope.reloadArea();
        $scope.loadGrid();
    }).error(function(data){
        
    })
    //重置区域
    $scope.reloadArea = function() {
        $("#queryForm").form("reset");
        if ($scope.organization.orgLevel != "1") {
            $.fn.mycity("sheng", "shi", "xian", $scope.underArea);
            if ($("#sheng").val() != null && $("#sheng").val() != "" && $("#sheng").val() != undefined) {
                $("#sheng").attr("disabled", true);
            }
            if ($("#shi").val() != null && $("#shi").val() != "" && $("#shi").val() != undefined) {
                $("#shi").attr("disabled", true);
            }
            if ($("#xian").val() != null && $("#xian").val() != "" && $("#xian").val() != undefined) {
                $("#xian").attr("disabled", true);
            }
        } else {
            $.fn.mycity("sheng", "shi", "xian");
        }
    }
    $scope.getAreaBySelect = function() {
        var province = $("#sheng").val();
        var city = $("#shi").val();
        var county = $("#xian").val();
        var areaId = "";
        if (county != null && county != undefined && county != "") {
            areaId = county;
        } else if (city != null && city != undefined && city != "") {
            areaId = city;
        } else if (province != null && province != undefined && province != "") {
            areaId = province;
        }
        return areaId;
    }

    //bootstrap表格
    //生产经营主体信息
    $scope.loadGrid = function() {
        $("#entGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSubjEntAndTransactionInfoList",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    return json.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.enterpriseName = $("input[name='enterpriseName']").val();
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.area = $scope.getAreaBySelect();
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"4%"

                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>40){
                            return "<a id='enterpriseName"+row.id+"' onmouseover=showPopContent('enterpriseName"+row.id+"','"+data+"')>"+data.substring(0,40)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                    width:"30%"
                },
                {
                    data: "XSY_COU",
                    title: "已确认销售数量",
                    width:"15%",
                },
                {
                    data: "XSN_COU",
                    title: "待确认销售数量",
                    width:"15%",
                },
                {
                    data: "SHY_COU",
                    title: "已确认采购数量",
                    width:"15%",
                },
                {
                    data: "SHN_COU",
                    title: "待确认采购数量",
                    width:"15%",
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="queryInfo(\'' + row.ENTITY_IDCODE + '\',\'' + row.ENTERPRISE_NAME + '\')">详情</a>';
                        return butt;
                    }
                },
            ],
        });
    }
    //交易信息
    $scope.loadTransactionList = function(){
        $("#qryTransactionGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getTransactionInfoCountByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].HARVEST_TIME != null && json.list[i].HARVEST_TIME != "" && json.list[i].HARVEST_TIME != undefined) {
                            json.list[i].HARVEST_TIME = new Date(parseInt(json.list[i].HARVEST_TIME)+28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
                    params.area = $scope.getAreaBySelect();
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width:"3%"

                },
                {
                    title:"主体名称",
                    width:"15%",
                    render:function(data, type, row, meta){
                        return $scope.enterpriseName;
                    }
                },
                {
                    data: "IS_CIRCULATES_END",
                    title: "交易方式",
                    width:"6%",
                    render:function(data, type, row){
                        if (row.SALE_OR_BUY == 'S') {
                            if(data=="Y"){
                                return "入市销售";
                            }else if(data=="N"){
                                return "流通销售";
                            }else {
                                return "无";
                            }
                        }else {
                            return "采购";
                        }

                    }
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width:"8%"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width:"8%"
                },
                {
                    data: "TRACE_CODE",
                    title: "追溯码/批次码",
                    width:"15%",
                    render:function (data, type, row) {
                        if(data != "" && data != null){
                            return data;
                        }else{
                            return "无";
                        }
                    }
                },
                {
                    data: "SALE_AMOUNT",
                    title: "交易数量",
                    width:"5%",
                    render:function(data, type, row){
                        return data+row.HARVEST_UNIT;
                    }
                },
                // {
                //     data: "PRODUCT_SOURCE",
                //     title: "产品来源",
                //     width:"5%",
                //     render:function(data, type, row){
                //         return data;
                //     }
                // },
                {
                    data: "SALE_TIME",
                    title: "交易时间",
                    width:"6%",
                    render:function(data, type, row){
                        return new Date(parseInt(data)+28800000).toISOString().slice(0, 10);
                    }
                },
                {
                    data: "CONFIRM_STATE",
                    title: "交易状态",
                    width:"8%",
                    render:function(data, type, row){
                        if (data == "Y") {
                            return "已确认";
                        }else if (data == "T") {
                            return "退货";
                        }else if (data == "H") {
                            return "换货";
                        }else{
                         return "待确认";
                        }
                    }
                }
            ],
        });
    }
    $scope.loadTransactionList();

    //重新加载表格
    $scope.reloadGrid=function(){
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply($scope.btnDisabled = false);
        }, 500);
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //查看详情
    window.queryInfo=function(entityIdcode,enterpriseName){
        $scope.subjEntEntityIdcode = entityIdcode;
        $scope.enterpriseName = enterpriseName;
        $("#qryTransactionGrid").dataTable().api().ajax.reload();
        $("#transactionList").modal("show");
    }

    //导出Excel
    $scope.exportData = function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/subject/exportTransactionInfo");
        var token = window.localStorage.getItem("token");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'token');
        input1.attr('value', token);
        var enterpriseName = $("input[name='enterpriseName']").val();
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'enterpriseName');
        input2.attr('value', enterpriseName);
        var area = $scope.getAreaBySelect();
        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'area');
        input3.attr('value', area);
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'dateBegin');
        input4.attr('value', $("input[name='dateBegin']").val());
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'dateEnd');
        input5.attr('value', $("input[name='dateEnd']").val());
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
})