var myapp=angular.module("myapp",[]);
myapp.controller("qryBathList",function($scope,$http,$state){

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
                url: "/sofn-qry-web/subject/getSubjEntAndBathList",
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
                    width:"50%"
                },
                {
                    data: "BATH_COUNT",
                    title: "生成批次总数",
                    width:"35%",
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
    //批次信息
    $scope.loadBathList = function(){
        $("#qryBathGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getBathByEntityIdcode",
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
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    params.dateBegin = $("input[name='dateBegin']").val();
                    params.dateEnd = $("input[name='dateEnd']").val();
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
                    width:"18%",
                    render:function(data, type, row, meta){
                        return $scope.enterpriseName;
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
                    width:"10%"
                },
                {
                    data: "PRODUCT_PC",
                    title: "追溯码/批次码",
                    width:"15%",
                    render:function (data, type, row) {
                        if (null != data &&  row.BILL_STRATRUS == "1") {
                            return data;
                        }else if (row.PRODUCT_SOURCE != '采购'){
                            return data;
                        }else if(row.PRODUCT_SOURCE == "采购" && row.BILL_STRATRUS != "1"){
                            return row.FROMZSM;
                        }
                    }
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量",
                    width:"5%",
                    render:function(data, type, row){
                        return data+row.HARVEST_UNIT;
                    }
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "产品来源",
                    width:"5%",
                    render:function(data, type, row){
                        return data;
                    }
                },
                {
                    data: "MEDI_CHECK",
                    title: "质检情况",
                    width:"6%",
                    render:function(data, type, row){
                        if(!data){
                            return "无";
                        }else{
                            return data + row.MEDI_RESULT;
                        }
                    }
                },
                {
                    data: "PROOF_NUMBER",
                    title: "动物检疫合格证号",
                    width:"8%",
                    render:function(data, type, row){
                        if(!data){
                            return "无";
                        }else{
                            return data;
                        }
                    }
                }
            ],
        });
    }
    $scope.loadBathList();

    //重新加载表格
    $scope.querySubjEntAndBathList=function(){
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
        $("#qryBathGrid").dataTable().api().ajax.reload();
        $("#bathList").modal("show");
    }

    //导出Excel
    $scope.exportData = function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/subject/exportTraceCode");
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

    //通过一个复选框，来控制其他复选框，选中的时候清空其他复选框值，不选的时候放开
    $scope.limitCheckBox = function(checkBoxName,limitCheckBoxName){
        for(var i =0;i<$("input[name='" + limitCheckBoxName + "']").length;i++) {
            var lim = $("input[name='" + limitCheckBoxName + "']")[i].checked;
            if (lim) {
                $("input[name='" + checkBoxName + "']:checked").attr("checked", false);
            }
        }
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