var myapp = angular.module("myapp", []);
myapp.controller("qryTestListInfo", function ($scope, $http, $state) {

    var token = window.localStorage.getItem("token");
    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");
    var monitorClass = window.localStorage.getItem("monitorClass");

    $scope.organTaskId="";

    $http({
        url:"/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method:"post",
        params:{"token":token,"monitorTaskId":adsMonitorTaskId,"monitorClass":"","systemType":"qry"}
    }).success(function(data){
        $scope.organTaskId=data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id

        //bootstrap表格
        $("#grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            // sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getPageInfoWithOrgTaskId",
                dataSrc:function(json){
                    console.log(json);
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    params.organTaskId =  $scope.organTaskId;//机构任务ID
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data: "CHECK_REPORT",
                    checkbox: true,
                    title: '<input id="checkall" name="" type="checkbox" value="" />',
                    render: function (data, type, row,meta) {
                        if(1==data){
                            return '<input id="c'+meta.row+'" class="editor-active" type="checkbox" name="checkOne" disabled/>';
                        }else{
                            return '<input id="c'+meta.row+'" class="editor-active" type="checkbox" name="checkOne"/>';
                        }
                    },
                    width:"5%"
                },
                {
                    data : "RN",
                    title : "序号",
                    width:"5%"
                },
                {
                    data : "SAMPLE_NAME",
                    title : "样品名称",
                    width:"10%"
                },
                {
                    data : "SAMPLE_CODE",
                    title : "样品编码",
                    width:"10%"
                },
                {
                    data : "PRODUCT_TRACEABILITY_CODE",
                    title : "产品编码",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if (data!=null){
                            return '<input readOnly="true"  style="overflow: auto; overflow-y:hidden" value="'+data+'" type="text"/>';
                        }else {
                            data = "";
                            return '<input readOnly="true"  style="overflow: auto; overflow-y:hidden" value="'+data+'" type="text"/>';
                        }

                    },
                    width:"10%"
                },
                {
                    data : "TESTED_DEPARMENT",
                    title : "受检单位",
                    width:"10%"
                },
                {
                    data : "SAMPLE_DEPARMENT",
                    title : "抽样单位",
                    width:"10%"
                },
                {
                    data : "CHECK_ORGAN",
                    title : "检测单位",
                    width:"10%"
                },
                {
                    data : "RESULT",
                    title : "判定结果",
                    render : function(data, type, row) {
                        if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    },
                    width:"10%"
                },
                {
                    data : "CHECK_REPORT",
                    title : "状态",
                    render : function(data, type, row) {
                        if(1==data){
                            return "已上报";
                        }else{
                            return "未上报";
                        }
                    },
                    width:"10%"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\''+data+'\')">查看</a>';
                        // if(1==row.CHECK_REPORT || $scope.disabled){
                        //     butt+= '<a style="cursor: pointer;opacity: 0.2;margin-right: 10px;" disabled>修改</a>';
                        // }else{
                        //     butt+= '<a style="cursor: pointer;margin-right: 10px;" onclick="change('+"'"+row.ID+"'"+","+"'"+row.SAMPLE_NAME+"'"+","+"'"+row.SAMPLE_CODE+"'"+","+"'"+row.PRODUCT_TRACEABILITY_CODE+"'"+')">修改</a>';
                        // }
                        // butt+='<a style="cursor: pointer" onclick="printArea('+"'"+row.ID+"'"+","+"'"+row.SAMPLE_NAME+"'"+","+"'"+row.SAMPLE_CODE+"'"+","+"'"+row.SAMPLE_DEPARMENT+"'"+","+"'"+row.CHECK_ORGAN+"'"+","+"'"+adsMonitorTaskId+"'"+')">打印</a>'
                        return butt;
                    },
                    width:"10%"
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
    }).error(function(){
    });

    //查看详情页面
    window.viewOrganTask=function(obj){
        window.localStorage.setItem("ID",obj);
        var url = "";
        var url1 = "/sofn-ads-web/adsCheckInfo/getChild";
        var url2 = "/sofn-ads-web/adsCheckInfo/getChildForB";
        var url3 = "/sofn-ads-web/adsCheckInfo/getChildForRecheck";

        // $scope.ID = obj;


        if(monitorClass=="例行监测"||monitorClass=="专项监测"){
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
                    url : "/sofn-ads-web/adsCheckInfo/getChild",
                    dataSrc:function(json){
                        console.log(json);
                        // json.recordsTotal=json.data.total;//总个数
                        // json.recordsFiltered=json.data.total;
                        // json.pageNumber=json.data.pageNum;//页码
                        // json.pageSize=json.data.pageSize;//每页个数
                        // json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                        // json.length=json.data.pageSize;
                        return json.data;
                    },
                    data : function(params) {
                        params.id = obj;
                        params.token = token;
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
                            if(" " == data){
                                return '<a  value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">ND</a>';
                            }else{
                                return '<a  value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
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
                        data : "result",
                        title : "抽检结果",
                        render : function(data, type, row) {
                            if("-1" == data){
                                return "暂未检测";
                            }else if("1" == data){
                                return "合格";
                            }else if("0" == data){
                                return "不合格";
                            }else {
                                return "不作判定";
                            }
                        }
                    },
                    {
                        data : "lod",
                        title : "LOD",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            /*if(null == data){
                                return '<input STYLE="border:0px;" type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                            }else{
                                return '<input STYLE="border:0px;" type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                            }*/
                            if(null == data){
                                return "";
                            }else{
                                return data;
                            }
                        }
                    },
                    {
                        data : "loo",
                        title : "LOQ",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            if(null == data){
                                return "";
                            }else{
                                return data;
                            }
                        }
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
        } else if (monitorClass=="监督抽查"||monitorClass=="受托检测"){
            $("#product_info3").modal("show");
            //清理数据
            var tableName = $('#productList1');
            if ($.fn.dataTable.isDataTable(tableName)) {
                tableName.DataTable().clear();
                tableName.DataTable().destroy();
            }
            //加载数据
            $("#productList1").dataTable({
                processing : true, // 加载时提示
                serverSide : false, // 分页，是否服务器端处理
                ordering : false,
                searching : false,
                bProcessing: true,
                paging:false,
                bAutoWidth:true,
                // sScrollY : "auto",
                dom : '<"top">rt<"bottom"pli><"clear">',
                autoWidth : false,
                ajax : {
                    url : "/sofn-ads-web/adsCheckInfo/getChildForB",
                    dataSrc:function(json){
                        console.log(json);
                        // json.recordsTotal=json.data.total;//总个数
                        // json.recordsFiltered=json.data.total;
                        // json.pageNumber=json.data.pageNum;//页码
                        // json.pageSize=json.data.pageSize;//每页个数
                        // json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                        // json.length=json.data.pageSize;
                        return json.data;
                    },
                    data : function(params) {
                        params.id = obj;
                        params.token = token;
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
                            if(" " == data){
                                return '<a  value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">ND</a>';
                            }else{
                                return '<a  value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                            }
                        },
                    },
                    {
                        data : "judgeNum",
                        title : "限量值"
                    },
                    {
                        data : "result",
                        title : "抽检结果",
                        render : function(data, type, row) {
                            if("-1" == data){
                                return "暂未检测";
                            }else if("1" == data){
                                return "合格";
                            }else if("0" == data){
                                return "不合格";
                            } else {
                                return "不作判定";
                            }
                        }
                    },
                    {
                        data : "lod",
                        title : "LOD",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            if(null == data){
                                return '<input  type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                            }else{
                                return '<input  type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                            }
                        }
                    },
                    {
                        data : "loo",
                        title : "LOQ",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            if(null == data){
                                return '<input  type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                            }else{
                                return '<input  type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                            }
                        }
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
        } else if (monitorClass=="复检任务"){
            $("#product_info3").modal("show");
            //清理数据
            var tableName = $('#productList1');
            if ($.fn.dataTable.isDataTable(tableName)) {
                tableName.DataTable().clear();
                tableName.DataTable().destroy();
            }
            //加载数据
            $("#productList1").dataTable({
                processing : true, // 加载时提示
                serverSide : false, // 分页，是否服务器端处理
                ordering : false,
                searching : false,
                bProcessing: true,
                paging:false,
                bAutoWidth:true,
                // sScrollY : "auto",
                dom : '<"top">rt<"bottom"pli><"clear">',
                autoWidth : false,
                ajax : {
                    url : "/sofn-ads-web/adsCheckInfo/getChildForRecheck",
                    dataSrc:function(json){
                        console.log(json);
                        return json.data;
                    },
                    data : function(params) {
                        params.id = obj;
                        params.token = token;
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
                            if(" " == data){
                                return '<a  value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">ND</a>';
                            }else{
                                return '<a  value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                            }
                        },
                    },
                    {
                        data : "judgeNum",
                        title : "限量值"
                    },
                    {
                        data : "result",
                        title : "抽检结果",
                        render : function(data, type, row) {
                            if("-1" == data){
                                return "暂未检测";
                            }else if("1" == data){
                                return "合格";
                            }else if ("0" == data){
                                return "不合格";
                            } else {
                                return "不作判定";
                            }
                        }
                    },
                    {
                        data : "lod",
                        title : "LOD",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            if(null == data){
                                return '<input  type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                            }else{
                                return '<input  type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                            }
                        }
                    },
                    {
                        data : "loo",
                        title : "LOQ",
                        render : function(data, type, row,meta) { // 模板化列显示内容
                            if(null == data){
                                return '<input  type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                            }else{
                                return '<input  type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                            }
                        }
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
        }

    }

    $scope.rebackBtn = function () {
        $state.go("index.content.qry/monitoringSystem/qryReportsList");
    }
});
