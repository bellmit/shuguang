var myapp=angular.module("myapp",[]);
myapp.controller("testList",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#mygrid").dataTable().api().ajax.reload();
    });
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID

    var monitorClass = window.localStorage.getItem("monitorClass");//任务类型

    var token = window.localStorage.getItem("token");
    /**
     * 分页展示数据
     */
    $(function() {

        //bootstrap表格
        $("#mygrid").dataTable({
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
                url : "/sofn-ads-web/adsCheckInfo/getPageInfoWithOrgTaskIdAndInsert",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    params.organTaskId = organTaskId;//机构任务ID
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
                        return '<input readOnly="true"  style="overflow: auto; overflow-y:hidden" value="'+data+'" type="text"/>';
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
                        }else if(0==data){
                            return "未上报";
                        }else{
                            return "已退回";
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
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");

        //全选/全不选
        $("#checkall").click(function() {
            if ($(this).prop("checked") == true) {
                $(".editor-active").each(function(){
                    if(!$(this).is(':disabled')){
                        // $("#mygrid input[type='checkbox']").prop("checked", true);
                        // $("#mygrid tbody tr").addClass("selected");
                        $(this).prop('checked',true);
                    }
                })
            } else {
                $("#mygrid input[type='checkbox']").prop("checked", false);
                $("#mygrid tbody tr").removeClass("selected");
            }
        });

        var url = "/sofn-ads-web/adsCheckInfo/getTaskNameByOrganTaskId?time="+new Date();
        //加载任务名称
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"organTaskId":organTaskId,"token":token},
            success: function (data) {
                var myData = data.data;
                if($.isEmptyObject(myData)){
                    return;
                }
                var Row = "<span>任务名称：  "+myData.taskName+" </span>";
                $("#product").html(Row);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

    });

    //页面跳转到新增
    window.add=function(){
        $state.go("index.content.monitor/execUnit/Audit/test")
    }

    //上报给其他系统
    window.up=function(){
        var ids = "";
        var rows = $("#mygrid").DataTable().data();
        var num = 0;
        for (var i = 1; i <=rows.length; i++){
            var b = i-1;
            var td = rows[i-1];
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                ids = ids + td.ID + ",";
                num++;
                if("-1" == td.RESULT){
                    alert("暂未检测，不能上报");
                    return;
                }
            }
        }
        if(0 == num){
            $.jBox.tip("至少选择一行上报！");
            return;
        }
        var url = "/sofn-ads-web/adsCheckInfo/upInfo?time="+new Date();
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"ids":ids,"organTaskId":organTaskId,"token":token},
            success: function (data) {
                $.messager.show({
                    title : "提示信息",
                    msg : "上报成功！",
                    showType : "slide",
                    timeout : 5000
                });
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("上报失败");
            }
        });
        $("#mygrid").dataTable().api().ajax.reload();
    }

    //删除（重置判定结果为-1）
    window.showDel=function(){
        var ids = "";
        if (confirm("确认删除数据？")) {
            var rows = $("#mygrid").DataTable().data();
            var num = 0;
            for (var i = 1; i <=rows.length; i++){
                var b = i-1;
                var td = rows[i-1];
                var checkbox  = document.getElementById('c'+b);
                if(checkbox.checked){
                    ids = ids + td.ID + ",";
                    num++;
                }
            }
            if(0 == num){
                $.jBox.tip("至少选择一行删除！");
                return;
            }
            var url = "/sofn-ads-web/adsCheckInfo/resetResult?time="+new Date();
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {"ids":ids,"token":token},
                success: function (data) {
                    $.messager.show({
                        title : "提示信息",
                        msg : "删除成功！",
                        showType : "slide",
                        timeout : 5000
                    });
                    $("#mygrid").dataTable().api().ajax.reload();
                },
                fail: function (data) {
                    $.jBox.tip("失败");
                }
            });
            $("#mygrid").dataTable().api().ajax.reload();
        }
        else {
        }
    }

    //生成检测任务单
    window.newPage=function(){
        var checkInfoId = "";
        var rows = $("#mygrid").DataTable().data();
        var num = 0;
        for (var i = 1; i <=rows.length; i++){
            var b = i-1;
            var td = rows[i-1];
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                checkInfoId = td.ID;
                num++;
            }
        }
        if(1 != num){
            $.jBox.tip("请选择一行生成！");
            return;
        }
        window.localStorage.setItem("checkInfoId",checkInfoId);
        document.getElementById("win").style.display="";
        var data = new Date().format("yyyy-MM-dd");
        $("#someInfo2").empty();
        var Row2 = "";
        Row2 = '<div style="margin: 0px 0px 10px 0px">任务通知时间:<input type="text" id="finishTime" class="" name="endTime" readonly="readonly" style="width:140px;" value="'+data+'"></div>';
        $("#someInfo2").html(Row2);

    }

    //生成检测任务单
    window.createPage=function(){
        var ipt = document.getElementById('finishTime');//获取input的节点
        var finishTime = ipt.value;
        window.localStorage.setItem("finishTime",finishTime);
        $state.go("index.content.monitor/execUnit/routineMonitor/infoPage");
    }

    //关闭弹出层
    window.gg=function(){
        document.getElementById("win").style.display="none";
    }

    //批量导入
    window.importInfo=function(){
        $("#product_info").modal("show");
    }

    //取消
    window.cancel=function(){
        $("#product_info").modal("hide");
    }

    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsInfoProject/saveInfo",
        // showPreview:false,
        allowedFileExtensions : ['xlsx', 'xls'],
        maxFileSize: 1000,
        maxFilesNum: 1,
        enctype: 'multipart/form-data',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        showRemove:false,//是否显示删除/清空按钮。默认值true
        msgFilesTooMany: "请选择excel文件！",
        msgSizeTooLarge: "只允许上传10MB的excel文件",
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

    //查看详情页面
    window.viewOrganTask=function(obj){
        window.localStorage.setItem("ID",obj);
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
                        }else{
                            return "不合格";
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

    var checkInfoId = "";

    //修改页面
    window.change=function(id,name,yangp,chanp){
        checkInfoId = id
        $("#product_info3").modal("show");
        //清理数据
        var tableName = $('#productList2');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }

        var Row = '<div class="col-md-4" ><span>样品名称：'+name+'</span></div>'+
            '<div class="col-md-4" > <span>样品编码：'+yangp+'</span> </div>'+
            '<div class="col-md-4" > <span>产品编码：'+chanp+'</span></div>';

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
                            return '<input id="'+meta.row+'" value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+');check(this)"/>';
                        }else{
                            return '<input id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+');check(this)"/>';
                        }
                    },
                },
                {
                    data : "judgeNum",
                    title : "限量值"
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
                        if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }
                    }
                },
                {
                    data : "lod",
                    title : "LOD",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(null == data){
                            return '<input id="'+meta.row+"c"+'" type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        }else{
                            return '<input id="'+meta.row+"c"+'" type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                        }
                    }
                },
                {
                    data : "loo",
                    title : "LOQ",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(null == data){
                            return '<input id="'+meta.row+"d"+'" type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        }else{
                            return '<input id="'+meta.row+"d"+'" type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
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

    //修改检测值的时候onblur事件
    window.my123=function(index,judgeNum){
        var inputValue = document.getElementById(index).value;
        if("" == inputValue){
            return;
        }
        if("ND" == inputValue){
            inputValue = 0;
        }
        var real = index+1;
        if(inputValue<=judgeNum){
            $("#productList2 tr:eq("+real+") td:eq(3)").html("合格");
        }else{
            $("#productList2 tr:eq("+real+") td:eq(3)").html("不合格");
        }
    }

    window.check=function(e) {
        var re = /^\d+(?=\.{0,1}\d+$|$)/;
        if(e.value == "ND"){
        }else if(e.value != "") {
            if (!re.test(e.value)) {
                alert("请输入正确的数字");
                e.value = "";
                e.focus();
            }
        }
    }

    //上报所有数据
    var url = "/sofn-ads-web/adsInfoProject/updateResult?time="+new Date();
    window.saveInfo=function(){
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

            if("" == checkNum){
                checkNum = "ND";
            }

            var check2 = 0;
            if("ND" == checkNum){
                checkNum = 0;
                check2 = 1;
            }

            var infoResult = 0;

            //获取检测信息判定结果
            var judgeNum = td.judgeNum;
            if(judgeNum.endsWith(".0")){
                judgeNum = judgeNum.replace(".0","");
            }
            // alert("checkNum:"+checkNum+",judgeNum:"+judgeNum+","+(checkNum>judgeNum));
            if(checkNum>judgeNum){
                result = 0;
                infoResult = 0;
            }else{
                infoResult = 1;
            }

            if(1 == check2){
                checkNum = "ND";
            }
            var obj = new Object();
            obj.id = td.id;
            obj.checkNum = checkNum;
            obj.result = infoResult;
            obj.LOD = $("#"+b+"c").val();
            obj.LOO = $("#"+b+"d").val();
            x.push(obj);

        }
        var json = JSON.stringify(x);
        // var notObj = JSON.stringify(n);

        //检测信息的判定结果
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"jsonArray":json,"checkInfoId":checkInfoId,"result":result,"token":token},
            success: function (data) {
                $.messager.show({
                    title : "提示信息",
                    msg : "修改成功！",
                    showType : "slide",
                    timeout : 5000
                });
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("修改失败");
            }
        });
        $("#product_info3").modal("hide");
        $("#mygrid").dataTable().api().ajax.reload();
    }

    //页面跳转到详情
    window.look=function(data){
        window.localStorage.setItem("sampleCode",data);
        window.localStorage.setItem("organTaskId", organTaskId);
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleDetail")
    }

    //页面跳转到信息汇总页面
    window.allInfo=function(){
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }

    $scope.taskDetail = function (data) {
        $state.go("index.content.monitor/execUnit/Audit/taskDetail")
    }
    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/sampleMessage")
    }
    $scope.test = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/testList")
    }
    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/auditReportUpload")
    }
    $scope.reback = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/taskDetail")
    }

})


