var myapp=angular.module("myapp",[]);
myapp.controller("import",function($scope,$http,$state){
    // $("#ToCheck").click(function(){
    //     $("#grid").dataTable().api().ajax.reload();
    // });
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#mygrid").dataTable({
            processing : true, // 加载时提示
            serverSide : false, // 分页，是否服务器端处理
            ordering : false,
            //searching : true,
            //bProcessing: true,
            paging:false,
            //bInfo:true,
            //bJQueryUI: true,
            bPaginate: false,// 分页按钮
            bFilter: false,// 搜索栏
            //iDisplayLength: 10,// 每页显示行数
            //bSort: false,// 排序
            dom : '<"top">rt<"bottom"pli><"clear">',
            bAutoWidth:false,
            //sScrollX :true,
            autoWidth : true,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getPageInfoWithParams?time="+new Date(),
                dataSrc:function(json){
                    return json.data;
                },
                data : function(params) {
                    params.ids = $("input[name='bannerid']").val();
                },
                type:"post"
            },
            columns : [
                {
                    data: "ID",
                    checkbox: true,
                    title: '<input id="checkall" name="" type="checkbox" value="" onclick="checkAll(this)" />',
                    render: function (data, type, row,meta) { // 模板化列显示内容
                        return '<input id="c'+meta.row+'" type="checkbox" name="checkOne" onclick="checkRecode(this)" checked="checked"/>';
                    },
                },
                {
                    data : "rn",
                    title : "序号"
                },
                {
                    data : "productCode",
                    title : "产品编码"
                },
                {
                    data : "sampleCode",
                    title : "样品编码"
                },
                {
                    data : "sampleName",
                    title : "样品名称"
                },
                {
                    data : "testedDeparment",
                    title : "受检单位"
                },
                {
                    data : "sampleDeparment",
                    title : "抽样单位"
                },
                {
                    data : "detectionOrgan",
                    title : "检测单位"
                },
                {
                    data : "result",
                    title : "判定结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    }
                },
                {
                    data : "id",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report text-info" onclick="viewOrganTask(\''+data+'\')">查看</a>';
                        return look_a;
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

    });

    //查看详情页面
    window.viewOrganTask=function(obj){
        window.localStorage.setItem("ID",obj);
        // $scope.ID = obj;
        $("#product_info").modal("show");
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
            bAutoWidth:true,
            // sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getChild",
                dataSrc:function(json){
                    return json.data;
                },
                data : function(params) {
                    params.id = obj;
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
                    title : "判定值"
                },
                {
                    data : "result",
                    title : "抽检结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    }
                },
                {
                    title : "LOD（位）",
                    data : function(data, type, row, meta) {
                        return ""
                    }
                },
                {
                    title : "：mgkg LOO",
                    data : function(data, type, row, meta) {
                        return ""
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

    $scope.uploadUrl = "/sofn-ads-web/adsInfoProject/saveInfo";
    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsInfoProject/saveInfo",
        // showPreview:false,
        allowedFileExtensions : ['xlsx', 'xls','gif'],
        maxFileSize: 100,
        maxFilesNum: 1,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        showRemove:false,//是否显示删除/清空按钮。默认值true
        msgFilesTooMany: "请选择{m}张图片！",
        msgSizeTooLarge: "只允许上传1MB的基地图片",
        // dropZoneTitle: '基地图片预览'
    }).on("fileuploaded", function(event, data) {
            if(999==data.response.httpCode){
                alert(data.response.msg);
            }
            if(200==data.response.httpCode){
                var datas = data.response.data;
                var infoIds = "";
                if (datas.length > 0){
                    for (var i = 1; i <=datas.length; i++){
                        var id = datas[i-1];
                        infoIds = infoIds + id + ",";
                    }
                }
                $("#myHidden").empty();
                var Row = '<input type="hidden" name="bannerid" id="bannerid" value="'+infoIds+'">';
                $("#myHidden").html(Row);
                $("#mygrid").dataTable().api().ajax.reload();
                alert('导入成功');
            }
        });

    window.del=function(){
        var rows = $("#mygrid").DataTable().data();
        var ids = "";
        var num = 0;
        for (var i = 1; i <=rows.length; i++){
            var td = rows[i-1];
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                ids = ids + td.id + ",";
                num++;
            }
        }
        if(0 == num){
            $.jBox.tip("至少选择一行删除！");
            return;
        }
        var url = "/sofn-ads-web/adsCheckInfo/delete?time="+new Date();
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"ids":ids},
            success: function (data) {
                $.jBox.tip("删除成功");
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
    }

    var checkInfoId = "";

    window.update=function(){
        var rows = $("#mygrid").DataTable().data();
        var id = "";

        var productCode = "";
        var sampleCode = "";
        var sampleName = "";
        var testedDeparment = "";
        var sampleDeparment = "";
        var detectionOrgan = "";

        var num = 0;
        for (var i = 1; i <=rows.length; i++){
            var td = rows[i-1];
            var b = i-1;
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                id = td.id;
                checkInfoId = id;

                productCode = td.productCode;
                sampleCode = td.sampleCode;
                sampleName = td.sampleName;
                testedDeparment = td.testedDeparment;
                sampleDeparment = td.sampleDeparment;
                detectionOrgan = td.detectionOrgan;

                num++;
            }
        }
        // if(1 != num){
        //     $.messager.show({
        //         title : "提示信息",
        //         msg : "只能选择一行记录修改！",
        //         showType : "slide",
        //         timeout : 5000
        //     });
        //     return;
        // }

        $("#product_info2").modal("show");
        //清理数据
        var tableName = $('#productList2');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }

        var Row = '<div class="col-md-6" ><span>产品编码：'+productCode+'</span></div>'+
        '<div class="col-md-6" > <span>样品编码：'+sampleCode+'</span> </div>'+
        '<div class="col-md-6" > <span>样品名称：'+sampleName+'</span> </div>'+
        '<div class="col-md-6" > <span>受检单位：'+testedDeparment+'</span> </div>'+
        '<div class="col-md-6" > <span>抽样单位：'+sampleDeparment+'</span> </div>'+
        '<div class="col-md-6" > <span>检测单位：'+detectionOrgan+'</span> </div> ';

        $("#someInfo").html(Row);

        //修改页面
        $("#productList2").dataTable({
            processing : true, // 加载时提示
            serverSide : false, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            paging:false,
            bAutoWidth:false,
            // sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-ads-web/adsCheckInfo/getChild",
                dataSrc:function(json){
                    return json.data;
                },
                data : function(params) {
                    params.id = id;
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
                            return '<input id="'+meta.row+'" value="'+"未检出"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')"/>';
                        }else{
                            return '<input id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')"/>';
                        }
                    },
                },
                {
                    data : "judgeNum",
                    title : "判定值"
                },
                // {
                //     data: "ID",
                //     checkbox: true,
                //     title: "必填",
                //     render: function (data, type, row,meta) { // 模板化列显示内容
                //         return '<input id="c'+meta.row+'" type="checkbox" name="checkOne" onclick="checkRecode(this)" checked="checked"/>';
                //     },
                // },
                {
                    data : "result",
                    title : "抽检结果",
                    render : function(data, type, row) {
                        if("1"==data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    }
                },
                {
                    title : "LOD（位）",
                    data : function(data, type, row, meta) {
                        return ""
                    }
                },
                {
                    title : "LOO",
                    data : function(data, type, row, meta) {
                        return ""
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

    //上报所有数据
    var url = "/sofn-ads-web/adsInfoProject/updateResult?time="+new Date();
    window.uploadInfo=function(){
        var x = [];//必填的选项
        // var n = [];//不用填的选项
        var rows = $("#productList2").DataTable().data();
        //检测信息判定结果
        var result = 1;
        for (var i = 1; i <=rows.length; i++){
            var td = rows[i-1];
            var b = i-1;
            var checkNum = $("#"+b+"").val();
            // var checkbox  = document.getElementById('c'+b);
            // if(checkbox.checked){
            // }else{
            //     var not = new Object();
            //     not.id = td.id;
            //     n.push(not);
            //     continue;
            // }
            if("未检出" == checkNum){
                alert("请填写所有待输入检测值");
                return;
            }

            var infoResult = 0;

            //获取检测信息判定结果
            var checkNum = checkNum;
            var judgeNum = td.judgeNum;
            if(checkNum>judgeNum){
                result = 0;
                infoResult = 0;
            }else{
                infoResult = 1;
            }

            var obj = new Object();
            obj.id = td.id;
            obj.checkNum = checkNum;
            obj.result = result;
            x.push(obj);

        }
        var json = JSON.stringify(x);
        // var notObj = JSON.stringify(n);

        //检测信息的判定结果
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"jsonArray":json,"checkInfoId":checkInfoId,"result":result},
            success: function (data) {
                $.messager.show({
                    title : "提示信息",
                    msg : "上报成功！",
                    showType : "slide",
                    timeout : 5000
                });
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });
        $("#mygrid").dataTable().api().ajax.reload();
        $("#product_info2").modal("hide");
    }
    $scope.cancel=function () {
        $("#mygrid").dataTable().api().ajax.reload();
        $("#product_info2").modal("hide");
    }

    //修改检测值的时候onblur事件
    window.my123=function(index,judgeNum){
        var inputValue = document.getElementById(index).value;
        real = index+1;
        if(inputValue<judgeNum || "未检出" == inputValue){
            $("#productList2 tr:eq("+real+") td:eq(3)").html("合格");
        }else{
            $("#productList2 tr:eq("+real+") td:eq(3)").html("不合格");
        }
    }

    //全选/全不选
    $("#checkall").click(function() {
        if ($(this).prop("checked") == true) {
            $("#mygrid input[type='checkbox']").prop("checked", true);
            $("#mygrid tbody tr").addClass("selected");
        } else {
            $("#mygrid input[type='checkbox']").prop("checked", false);
            $("#mygrid tbody tr").removeClass("selected");
        }
    });

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.sampelPrint = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/samplePrint")
    }
    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/check")
    }
    $scope.import = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/import")
    }
    $scope.routineMonitorList = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.historyTaskList = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/historyTaskList")
    }

})


