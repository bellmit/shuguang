var myapp=angular.module("myapp",[]);
myapp.controller("testListRecheck",function($scope,$http,$state){

    $scope.iswrite = "";
    var b = window.localStorage.getItem("AdsMenuObj");
    var a= JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    var status = window.localStorage.getItem("status");

    var arrayNotJudge = new Array();
    for(var i=0;i< a.length;i++){
        if(a[i].menuName=="复检任务"){
            $scope.iswrite=a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite!="2";

    if($scope.disabled){
        $("#import").removeAttr("onclick").css("background-color","grey");
        $("#after").removeAttr("onclick").css("background-color","grey");
        $("#next").removeAttr("onclick").css("background-color","grey");
        $("#open").removeAttr("onclick").css("background-color","grey");
    }

    if(status=='3'||status=='4'||status=='5'){
        $("#after").removeAttr("onclick").css("background-color","grey");
        $("#import").removeAttr("onclick").css("background-color","grey");
        $("#next").removeAttr("onclick").css("background-color","grey");
    }

    $("#ToCheck").click(function(){
        $("#mygrid").dataTable().api().ajax.reload();
    });
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID

    var monitorClass = window.localStorage.getItem("monitorClass");//任务类型

    var token = window.localStorage.getItem("token");

    var adsMonitorTaskId = window.localStorage.getItem("adsMonitorTaskId");
    $scope.detail="";

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
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
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
                       /* butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\''+data+'\')">查看</a>';*/
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\''+data+'\' ' + ',\''+ row.SAMPLE_NAME+'\''+')">查看</a>';
                        if(1==row.CHECK_REPORT){
                            butt+= '<a style="cursor: pointer;opacity: 0.2;margin-right: 10px;" disabled>修改</a>';
                        }else{
                            if($scope.disabled){
                                butt+='<a style="color: grey;cursor: pointer;margin-right: 10px;">修改</a>';
                            }else {
                                butt+= '<a style="cursor: pointer;margin-right: 10px;" onclick="change('+"'"+row.ID+"'"+","+"'"+row.SAMPLE_NAME+"'"+","+"'"+row.SAMPLE_CODE+"'"+","+"'"+row.PRODUCT_TRACEABILITY_CODE+"'"+')">修改</a>';
                            }
                        }
                        butt+='<a style="cursor: pointer" onclick="printArea('+"'"+row.ID+"'"+","+"'"+row.SAMPLE_NAME+"'"+","+"'"+row.SAMPLE_CODE+"'"+","+"'"+row.SAMPLE_DEPARMENT+"'"+","+"'"+row.CHECK_ORGAN+"'"+","+"'"+adsMonitorTaskId+"'"+')">打印</a>'
                        butt += '<a style="cursor: pointer;margin-left: 10px;" onclick="allInfo2(\''+data+'\')">生成报告</a>';
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
                        $(this).prop('checked',true);
                    }
                })
            } else {
                $("#mygrid input[type='checkbox']").prop("checked", false);
                $("#mygrid tbody tr").removeClass("selected");
            }
        });

        window.allInfo2=function(obj){

            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url:"/sofn-ads-web/adsCheckInfo/getAllByCheckInfoId",
                        method:"post",
                        dataType:"json",
                        params : {"checkInfoId":obj},
                        responseType: 'arraybuffer',
                        headers: { "Content-Type": "application/x-www-form-urlencoded" },
                    }).success(function(data){
                        var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                        if(blob.size > 0){
                            var objectUrl = URL.createObjectURL(blob);
                            var a = document.createElement('a');
                            document.body.appendChild(a);
                            a.setAttribute('style', 'display:none');
                            a.setAttribute('href', objectUrl);
                            a.setAttribute('download', "检测报告.doc");
                            a.click();
                        }else{
                            jBox.tip("导出失败，没有数据！","error")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }

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
        var id = "";
        var rows = $("#mygrid").DataTable().data();
        var num = 0;
        for (var i = 1; i <=rows.length; i++){
            var b = i-1;
            var td = rows[i-1];
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                id = td.ID;
                num++;
            }
        }
        if(num>1){
            $.jBox.tip("只能选择一行检测！");
            return;
        }
        window.localStorage.setItem("checkInfoId",id);
        $state.go("index.content.monitor/execUnit/Recheck/testRecheck")
    }

    //上报给其他系统
    $scope.isSubmit = false;
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
        if($scope.isSubmit)
            return;
        $scope.isSubmit = true;
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
                $scope.isSubmit = false;
                $("#mygrid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("上报失败");
                $scope.isSubmit = false;
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
        var ipt = document.getElementById('startTime');//获取input的节点
        var finishTime = ipt.value;
        window.localStorage.setItem("finishTime",finishTime);
        $state.go("index.content.monitor/execUnit/routineMonitor/infoPage");
    }

    //关闭弹出层
    window.gg=function(){
        document.getElementById("win").style.display="none";
    }

    //批量导出
    window.exportExcel = function () {
        var ids = "";
        var num = 0;
        var rows = $("#mygrid").DataTable().data();
        for (var i = 1; i <=rows.length; i++){
            var td = rows[i-1];
            var b = i-1;
            var checkbox  = document.getElementById('c'+b);
            if(checkbox.checked){
                ids = ids + td.ID + ",";
                num++;
            }
        }
        if(0 == num){
            $.jBox.tip("至少选择一行导出！");
            return;
        }
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsInfoProject/downloadInfoProject",
                    method: "post",
                    dataType: "json",
                    params: {"ids": ids},
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '检测填写表.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    //批量导入
    window.importInfo=function(){
        $("#product_info").modal("show");
    }

    //批量导入模板下载
    window.downloadDemo=function(){
        var ids = "";
        $http({
            url: "/sofn-ads-web/adsInfoProject/downloadDemo",
            method: "post",
            dataType: "json",
            params: {"ids": ids},
            responseType: 'arraybuffer',
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
        }).success(function (data) {
            var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
            var objectUrl = URL.createObjectURL(blob);
            var a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display:none');
            a.setAttribute('href', objectUrl);
            a.setAttribute('download', '导入测试.xls');
            a.click();
            $("#product_info").modal("hide");
        });
    }

    //取消
    window.cancel=function(){
        $("#product_info").modal("hide");
    }

    $("#file-1").fileinput({
        language: 'zh',
        uploadUrl: "/sofn-ads-web/adsInfoProject/saveInfo?organTaskId="+organTaskId,
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

    //打印设置
    $("#printArea").click(function () {
        $("#printCount").printArea();
        $("#product_info4").modal("hide");
    });

    //打印按钮弹窗
    window.printArea=function (id,name,code,cy,jc,taskid) {
        window.localStorage.setItem("ID",id);
        $("#product_info4").modal("show");
        //清理数据
        var tableName = $('#productList1');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }

        //检测信息赋值
        $("#samname").text(name);
        $("#samcode").text(code);
        $("#cy").text(cy);
        $("#jc").text(jc);
        $("#monclass").text(monitorClass);
        //查询任务详情
        $http({
            url:"/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
            method:"post",
            params:{"token":token,"monitorTaskId":adsMonitorTaskId,"monitorClass":""}
        }).success(function(data){
            $scope.detail=data.adsMonitorTask[0];
            $scope.taskName=data.adsMonitorTask[0].taskName;
            $scope.deadline=data.adsMonitorTask[0].deadline;//上报截至时间
            $scope.organTaskId=data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
            $scope.checkModel=data.adsMonitorTask[0].checkModel;//机构任务id
            $scope.sampleFinishNum=data.adsMonitorTask[0].adsOrganTaskList[0].sampleFinishNum;
            $scope.numbers=data.adsMonitorTask[0].adsOrganTaskList[0].numbers;
            $scope.monitorClass=data.adsMonitorTask[0].monitorClass//监测类型
            if(data.adsMonitorTask[0].checkObject!=null){
                object=data.adsMonitorTask[0].checkObject.split("、").length;
            }
            if(data.adsMonitorTask[0].checkProject!=null){
                project=data.adsMonitorTask[0].checkProject.split("、").length;
            }
        }).error(function(){
        });
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
                       /* if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }*/
                        var checkProject = row.checkProject;
                        var url = "/sofn-ads-web/adsCheckInfo/judgeCheckItem";

                        var str = "";

                        $.ajax({
                            url: url,
                            type: "post",
                            async: false,
                            data: {
                                checkItem: checkProject,
                                sampleName: name
                            },
                            success: function(datas) {
                                var status = datas.data;
                                if (status == "1"){
                                    str =  "不作判定";
                                    //增加未判断的检测项id
                                    if(arrayNotJudge.indexOf(row.id)<0)
                                        arrayNotJudge.push(row.id);
                                }else {
                                    if ("-1" == data) {
                                        str =  "暂未检测";
                                    } else if ("1" == data) {
                                        str =  "合格";
                                    } else {
                                        str =  "不合格";
                                    }
                                }
                            },
                            fail: function(data) {
                                $.jBox.tip("失败");
                            }
                        });
                        return str;
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
    //查看详情页面
    window.viewOrganTask=function(obj,name){
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
                url : "/sofn-ads-web/adsCheckInfo/getChildForRecheck",
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
                        /*if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }*/
                        var checkProject = row.checkProject;
                        var url = "/sofn-ads-web/adsCheckInfo/judgeCheckItem";

                        var str = "";

                        $.ajax({
                            url: url,
                            type: "post",
                            async: false,
                            data: {
                                checkItem: checkProject,
                                sampleName: name
                            },
                            success: function(datas) {
                                var status = datas.data;
                                if (status == "1"){
                                    str =  "不作判定";
                                    //增加未判断的检测项id
                                    if(arrayNotJudge.indexOf(row.id)<0)
                                        arrayNotJudge.push(row.id);
                                }else {
                                    if ("-1" == data) {
                                        str =  "暂未检测";
                                    } else if ("1" == data) {
                                        str =  "合格";
                                    } else {
                                        str =  "不合格";
                                    }
                                }
                            },
                            fail: function(data) {
                                $.jBox.tip("失败");
                            }
                        });
                        return str;
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
            '<div class="col-md-4" > <span>样品编码：'+yangp+'</span> </div>';
            // '<div class="col-md-4" > <span>产品编码：'+chanp+'</span></div>';
        //var Row1 = '产品编码  :&nbsp;&nbsp' + chanp;
        var Row1 = "";
        if (chanp=="null"){
            chanp = "";
            Row1 = '产品编码  :&nbsp;&nbsp' + chanp;
        }else {
            Row1 = '产品编码  :&nbsp;&nbsp' + chanp;
        }
        $("#someInfo").html(Row);
        $("#productCode").html(Row1);
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
                url : "/sofn-ads-web/adsCheckInfo/getChildForRecheck",
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
                            return '<input id="'+meta.row+'"placeholder="ND" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+  ",'" + row.id + "'" +');check(this)"/>';
                        }else{
                            return '<input id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+  ",'" + row.id + "'" +');check(this)"/>';
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
                      /*  if("-1" == data){
                            return "暂未检测";
                        }else if("1" == data){
                            return "合格";
                        }else{
                            return "不合格";
                        }*/
                        var checkProject = row.checkProject;
                        var url = "/sofn-ads-web/adsCheckInfo/judgeCheckItem";

                        var str = "";

                        $.ajax({
                            url: url,
                            type: "post",
                            async: false,
                            data: {
                                checkItem: checkProject,
                                sampleName: name
                            },
                            success: function(datas) {
                                var status = datas.data;
                                if (status == "1"){
                                    str =  "不作判定";
                                    //增加未判断的检测项id
                                    if(arrayNotJudge.indexOf(row.id)<0)
                                        arrayNotJudge.push(row.id);
                                }else {
                                    if ("-1" == data) {
                                        str =  "暂未检测";
                                    } else if ("1" == data) {
                                        str =  "合格";
                                    } else {
                                        str =  "不合格";
                                    }
                                }
                            },
                            fail: function(data) {
                                $.jBox.tip("失败");
                            }
                        });
                        return str;
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
    window.my123=function(index,judgeNum,id){

        if(arrayNotJudge.indexOf(id)>-1)
            return;
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
            /*if(parseFloat(checkNum)>parseFloat(judgeNum)){
                result = 0;
                infoResult = 0;
            }else{
                infoResult = 1;
            }*/

            if(arrayNotJudge.indexOf(td.id)<0){
                if ( parseFloat(checkNum) > parseFloat(judgeNum)) {
                    result = 0;
                    infoResult = 0;
                } else {
                    infoResult = 1;
                }
            } else
                infoResult = 2;

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
        $state.go("index.content.monitor/execUnit/Recheck/sampleDetail")
    }

    //页面跳转到信息汇总页面
    window.allInfo=function(){
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorList")
    }

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/Recheck/resTaskDetail")
    }

    $scope.routineMonitorList = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck")
    }

    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/Recheck/testListRecheck")
    }

    $scope.sampleMessage = function (data) {
        $state.go("index.content.monitor/execUnit/Recheck/sampleMessageRecheck")
    }

    $scope.routineReportUpload = function(data) {
        $state.go("index.content.monitor/execUnit/Recheck/recheckReportUpload")
    }


})


