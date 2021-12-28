var myapp=angular.module("myapp",[]);
myapp.controller("qrySubjEntPrint_new",function($scope,$http,$state){

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
        $scope.underArea = $scope.organization.regionId.substr(0, 2 * ($scope.organization.orgLevel - 1));
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
                url: "/sofn-qry-web/subject/getSubjEntAndPrintInfoList",
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
                    params.productSort = $("input[name='productSort']").val();
                    params.productName = $("input[name='productName']").val();
                    params.productScglId = $("input[name='productScglId']").val();
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
                    title: "企业名称",
                    render:function(data,type,row){
                        if(data!=null&&data!=undefined&&data.length>40){
                            return "<a id='enterpriseName"+row.id+"' onmouseover=showPopContent('enterpriseName"+row.id+"','"+data+"')>"+data.substring(0,40)+"...</a>";
                        }else{
                            return data;
                        }
                    },
                    width:"15%"
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width:"10%"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width:"10%"
                },
                {
                    data: "TWODIMENSIONCODE_LONG",
                    title: "追溯码",
                    width:"35%",
                    render:function(data,type,row,meta){
                        // var str = row.FROMZSM != null ? row.FROMZSM : data;
                        // if(str!=null&&str!=undefined&&str.length>40){
                        //     return "<a id='productscglid"+meta.row+"' onmouseover=showPopContent('productscglid"+meta.row+"','"+str+"')>"+str.substring(0,40)+"...</a>";
                        // }else{
                        //     return data;
                        // }
                        return data;
                    }
                },
                {
                    data:"PRINT_COUNT",
                    title:"打印次数",
                    width:"10%"
                },
                {
                    data:"",
                    title:"操作",
                    width:"10%",
                    render:function(data, type, row,meta){
                        return '<a href="javascript:showPrintInfo(\''+meta.row+'\')">查看</a>';
                    }
                }
            ],
        });
    }

    //重新加载表格
    $scope.querySubjEntAndPrintInfoList=function(){
        $("#entGrid").dataTable().api().ajax.reload();
    }

    //导出Excel
    $scope.exportData = function(){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/subject/exportPrint");
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
        var productSort = $("input[name='productSort']").val();
        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'productSort');
        input4.attr('value', productSort);
        var productName = $("input[name='productName']").val();
        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'productName');
        input5.attr('value', productName);
        var productScglId = $("input[name='productScglId']").val();
        var input6 = $('<input>');
        input6.attr('type', 'hidden');
        input6.attr('name', 'productScglId');
        input6.attr('value', productScglId);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
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

    $scope.loadDeatilPrintInfo = function () {
        if($scope.row != null) {
            $scope.detailPrintInfoTable = $("#detailPrintInfoTable").dataTable({
                ajax: {
                    url: "/sofn-qry-web/subject/getSubjEntAndPrintDeatilInfoList",
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
                        params.entityIdCode = $scope.row.ENTITY_IDCODE;
                        params.twodimensionCodeLong = $scope.row.TWODIMENSIONCODE_LONG;
                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "TWODIMENSIONCODE_TYPE",
                        title: "标签类别",
                        width: "15%",
                        render: function (data, type, row) {
                            if (data == 'T') {
                                return "产品追溯码";
                            } else if (data == 'P') {
                                return "追溯凭证";
                            } else if (data == 'X') {
                                return "入市追溯凭证";
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        data: "TWODIMENSIONCODE_SIZE",
                        title: "打印尺寸",
                        width: "15%"
                    },
                    {
                        data: "PRINT_IP",
                        title: "打印IP",
                        width: "20%"
                    },
                    {
                        data: "PRINT_COUNT",
                        title: "打印次数",
                        width: "10%"
                    }

                ],

            });
        }
    }


    window.showPrintInfo = function (index) {
        var rows = $("#entGrid").DataTable().rows().data();
        $scope.row = rows[index];
        if($("#detailPrintInfoTable").hasClass('dataTable')){
            $scope.detailPrintInfoTable.api().ajax.reload();
        }else{
            $scope.loadDeatilPrintInfo();
        }
        $("#myModal").modal("show");

    }
})