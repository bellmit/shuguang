var myapp=angular.module("myapp",[]);
myapp.controller("myCheckTask",function($scope,$http,$state){
    $scope.authorityArray = window.localStorage.getItem("menus");
    var authorityArray = JSON.parse($scope.authorityArray);

    for(var i =0; i < authorityArray.length;i++){
        if(authorityArray[i].id == "512"){
               window.localStorage.setItem("myCheckTask_subset_authority",authorityArray[i].authority);
                break;
        }
    }
    /**
     *页签操作
     * */
    $("#ypinfo").hide();
    $scope.myTab = function(o){
        $("#wtInfo").hide();//新任务
        $("#ypinfo").hide();//历史任务
        if(o=="新任务"){
            $("#wtInfo").show();//新任务
        }
        if(o=="历史任务"){
            $("#ypinfo").show();//历史任务
        }
    };
    $scope.tabOrIdx = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II");
    if ($scope.tabOrIdx == "history"){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II");
        $("#wtInfo").hide();
        $("#ypinfo").show();
        $("li[name='newTask']").removeClass("active");
        $("li[name='historyTask']").addClass("active");
    }
    /**
     * *
     * *承担任务包含新任务与历史任务，判断标准为任务是否填写抽样单信息
     * *
     * */
    var token = window.localStorage.getItem("token");
    /**
     *公共值
     * */

    /**
     *历史任务列表
     * */
    $(function() {
        $("#grid").dataTable({
            fnDrawCallback: function() {
            },
            ajax : {
                url : "/sofn-ales-web/alesmynewchecktask/getHistoryCheckTaskListByUser",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        //时间格式化
                        if(json.data.list[i].TASKBEGINTIME!=null&&json.data.list[i].TASKBEGINTIME!=""&&json.data.list[i].TASKBEGINTIME!=undefined){
                            json.data.list[i].TASKBEGINTIME=new Date(parseInt(json.data.list[i].TASKBEGINTIME)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].TASKENDTIME!=null&&json.data.list[i].TASKENDTIME!=""&&json.data.list[i].TASKENDTIME!=undefined){
                            json.data.list[i].TASKENDTIME=new Date(parseInt(json.data.list[i].TASKENDTIME)+28800000).toISOString().slice(0,10);
                        }
                        if(json.data.list[i].CREATETIME!=null&&json.data.list[i].CREATETIME!=""&&json.data.list[i].CREATETIME!=undefined){
                            json.data.list[i].CREATETIME=new Date(parseInt(json.data.list[i].CREATETIME)+28800000).toISOString().slice(0,10);
                        }
                        //任务状态
                        if(json.data.list[i].STATE!=null&&json.data.list[i].STATE!=""&&json.data.list[i].STATE!=undefined){
                            if(json.data.list[i].STATE =='0'){
                                json.data.list[i].STATE ='未发布';
                               // json.data.list[i].STATE ='未执行';
                            }else if(json.data.list[i].STATE =='1'){
                                json.data.list[i].STATE ='已发布';
                            }else if(json.data.list[i].STATE =='2'){
                                json.data.list[i].STATE ='已废止';
                            }else if(json.data.list[i].STATE == '3'){
                                json.data.list[i].STATE ='执行中';
                            }else if(json.data.list[i].STATE == '4'){
                                json.data.list[i].STATE = '已结束,未完成';
                            }else if(json.data.list[i].STATE == '5'){
                                json.data.list[i].STATE = '已结束,已完成';
                            }
                        }
                        if(json.data.list[i].TASKPRIORITY!=null&&json.data.list[i].TASKPRIORITY!=""&&json.data.list[i].TASKPRIORITY!=undefined){
                            if(json.data.list[i].TASKPRIORITY =='1'){
                                json.data.list[i].TASKPRIORITY ='特急';
                                // json.data.list[i].STATE ='未执行';
                            }else if(json.data.list[i].TASKPRIORITY =='2'){
                                json.data.list[i].TASKPRIORITY ='紧急';
                            }else if(json.data.list[i].TASKPRIORITY =='3'){
                                json.data.list[i].TASKPRIORITY ='一般';
                            }
                        }
                    }
                    return json.data.list;
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data : function(params) {
                    params.taskName=$("input[name='History_Task_NAME']").val();
                    params.dateBegin=$("input[name='History_Task_Date_Begin']").val();
                    params.dateEnd=$("input[name='History_Task_Date_End']").val();
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data : 'ID',
                    title : 'ID',
                    visible : false
                },
                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%",
                },
                {
                    data : "TASKNAME",
                    title : "任务名称",
                    width: "10%",
                },
                {
                    data : "TASKYEAR",
                    title : "年度",
                    width: "7%",
                },
                {
                 data : "STATE",
                 title : "任务状态",
                 render: function (data,type,row) {
                     if(data == '已废止'){
                         return '<span name ="state" style="color: #ff0d06">'+data+'</span>';
                     }else if (data == '已结束,已完成'){
                         return '<span name ="state" style="color: #bfbfbf">'+data+'</span>';
                     }else if (data == '已结束,未完成'){
                         return '<span name ="state" style="color: #ff0d06">'+data+'</span>';
                     }else if (data == '已发布' || data == '未发布'){
                         return '<span name ="state" style="color: #1f934b">'+data+'</span>';
                     }
                     return '<span name = "state" style="color: #333333">'+data+'</span>';
                 },
                    width: "10%",
                },
                {
                    data:"TASKPRIORITY",
                    title:"任务优先级",
                    render:function (data,type,row) {
                        if(data == '一般'){
                            return '<span name ="state" style="color:green ">'+data+'</span>';
                        }else if (data == '紧急'){
                            return '<span name ="state" style="color: yellow">'+data+'</span>';
                        }else if (data == '特急'){
                            return '<span name ="state" style="color: red">'+data+'</span>';
                        }

                    }
                },
                 {
                    data : "TASKTYPE",
                    title : "监测类型",
                    render : function(data, type, row) {
                        return '监督抽查';
                    },
                     width: "10%",
                },
                {
                    data : "TASKBEGINTIME",
                    title : "开始时间",
                    width: "10%",
                },
                {
                    data : "TASKENDTIME",
                    title : "结束时间",
                    width: "10%",
                },
                {
                    data : "CREATETIME",
                    title : "创建时间",
                    width: "10%",
                },
             /*   { 隐藏字段(2018年2月28日15:44:06)
                    data : "ISSAMPLE",
                    title : "抽样单",
                    render : function(data, type, row) {
                        if (data == "0"){
                            return "未提交";
                        }else if (data == "1"){
                            return "已提交";
                        }
                    }
                },*/
                {
                    data : "ID",
                    title : "报告",
                    render : function(data, type, row) {
                        return '<a style="cursor: pointer" onclick="queryHistoryInfo(\''+data+'\')">查看</a>';
                    },
                    width: "10%",
                }
            ]
        });
    });

    /**
     *查询按钮
     * */
    $("#historyTaskToCheckid").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 重置cleanSelectHistoryQuery
     */
    $scope.cleanSelectModelQuery = function(){
        $("input[name='myTaskName']").val("");
        $("input[name='taskDateBegin']").val("");
        $("input[name='taskDateEnd']").val("");
       // $("#grid").dataTable().api().ajax.reload();
    };
    $scope.cleanSelectHistoryQuery = function(){
        $("input[name='History_Task_NAME']").val("");
        $("input[name='History_Task_Date_Begin']").val("");
        $("input[name='History_Task_Date_End']").val("");
        $("#grid").dataTable().api().ajax.reload();
    };
    //绑定回车事件
    $("#historyTaskToCheckid").keydown(function(event){
        event=document.all?window.event:event;
        if((event.keyCode || event.which)==13){
            $("#grid").dataTable().api().ajax.reload();
        }
    });

    /**
     *我的新任务
     * */
    $scope.queryNewTask = function(){
        $scope.newTaskQueryPar = {};
        var taskName=$("input[name='myTaskName']").val();
        var taskBeginTime=$("input[name='taskDateBegin']").val();
        var taskEndTime=$("input[name='taskDateEnd']").val();
        if (taskName != ""){
            $scope.newTaskQueryPar.taskName = taskName;
        }
        if (taskBeginTime != ""){
            $scope.newTaskQueryPar.taskBeginTime =new Date((taskBeginTime+" 00:00:00").replace(/-/g,"/"));
        }
        if (taskEndTime != ""){
            $scope.newTaskQueryPar.taskEndTime =new Date((taskEndTime+" 00:00:00").replace(/-/g,"/")) ;
        }
        $scope.newTaskQueryPar.token = token;
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesmynewchecktask/getCheckTaskByUser",
            type:"post",
            dataType:"json",
            data:$scope.newTaskQueryPar,
            success:function(obj){
                $scope.showMyTask(obj);
            },
            fail: function (data) {
            }
        }).error(function (msg) {
           
        });
    };
    $("#CS_DIV_NO_TASK").fadeIn("slow");//无最新任务
    $.ajax({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/alesmynewchecktask/getCheckTaskByUser",
        type:"post",
        dataType:"json",
        data:{'token':token},
        success:function(obj){
            $scope.showMyTask(obj);
        },
        fail: function (data) {
        }
    }).error(function(msg){
        console.log(msg);
        
    });
//绑定回车事件
    $("#taskDateEndId").keydown(function(event){
        event=document.all?window.event:event;
        if((event.keyCode || event.which)==13){
            $scope.queryNewTask();
        }
    });
    $("#myTaskNameid").keydown(function(event){
        event=document.all?window.event:event;
        if((event.keyCode || event.which)==13){
            $scope.queryNewTask();
        }
    });
    /**
     *初始化显示我的新任务
     * */
    $scope.showMyTask=function(o){
        $("span[name='span_Task_Count']").html(o.data.length);
        $("#MY-TASK").html('<div class="col-md-6 col-lg-4 col-sm-6 column "><div id="CS_DIV_NO_TASK" style="display: none"><div class="div_gz_style" ><p class="text-info">您没有新的任务！</p></div></div></div>');
        $("#CS_DIV_NO_TASK").fadeIn(1000);//无最新任务显示
        var d = o.data;
        /*var nubmer = o.data.list
        var dlength =d.length;*/
        if (d.length > 0){
            for (var i = 1; i <=d.length; i++){
                var k = i-1;
                var ctyNme = "未指定";
                if (d[k].TASK_AREA_ID != null){
                    ctyNme = $.fn.Cityname(d[k].TASK_AREA_ID);
                }
                if(d[k].STATE == '2'){
                    var Row = '<div class="col-md-6 col-lg-4 col-sm-6 column "><div id="My_New_Task_Div'+i+'" style="display: none"><div class="div_gz_style" ></P><h4>'+ d[k].TASK_NAME+'&nbsp;<span class="badge pull-right" style="background-color: red">已废止</span></h4><p class="text-info">区域：'+ ctyNme+'</p><p class="text-info">时间：'+new Date(parseInt(d[k].TASK_BEGIN_TIME)+28800000).toISOString().slice(0,10)+' 至 '+new Date(parseInt(d[k].TASK_END_TIME)+28800000).toISOString().slice(0,10)+' </p><p class="text-info">主管单位：'+ d[k].TASK_RELEASE_UNIT+' </p> <p> <button class="btn" onclick="queryInfo(\''+d[k].ID+'\')"><span class="glyphicon glyphicon-search"></span></button> </p></div></div></div>';
                }
               if(d[k].STATE == '3'){
                   var Row = '<div class="col-md-6 col-lg-4 col-sm-6 column "><div id="My_New_Task_Div'+i+'" style="display: none"><div class="div_gz_style" ></P><h4>'+ d[k].TASK_NAME+'&nbsp;<span class="badge pull-right" style="background-color: #6aa84f">已接受任务</span></h4><p class="text-info">区域：'+ ctyNme+'</p><p class="text-info">时间：'+new Date(parseInt(d[k].TASK_BEGIN_TIME)+28800000).toISOString().slice(0,10)+' 至 '+new Date(parseInt(d[k].TASK_END_TIME)+28800000).toISOString().slice(0,10)+' </p><p class="text-info">主管单位：'+ d[k].TASK_RELEASE_UNIT+' </p> <p> <button class="btn" onclick="queryInfo(\''+d[k].ID+'\')"><span class="glyphicon glyphicon-search"></span></button> </p></div></div></div>';

               }else {
                   var Row = '<div class="col-md-6 col-lg-4 col-sm-6 column "><div id="My_New_Task_Div'+i+'" style="display: none"><div class="div_gz_style" ></P><h4>'+ d[k].TASK_NAME+'&nbsp;<span class="badge pull-right" style="background-color: #6aa84f">新</span></h4><p class="text-info">区域：'+ ctyNme+'</p><p class="text-info">时间：'+new Date(parseInt(d[k].TASK_BEGIN_TIME)+28800000).toISOString().slice(0,10)+' 至 '+new Date(parseInt(d[k].TASK_END_TIME)+28800000).toISOString().slice(0,10)+' </p><p class="text-info">主管单位：'+ d[k].TASK_RELEASE_UNIT+' </p> <p> <button class="btn" onclick="queryInfo(\''+d[k].ID+'\')"><span class="glyphicon glyphicon-search"></span></button> </p></div></div></div>';

               }
                if(i==1){
                    $("#MY-TASK").html(Row);
                    var si = 0;
                    if (i<10) si= i*200;else si = 3000;
                    $("#My_New_Task_Div"+i).fadeIn(si);
                }else{
                    $("#MY-TASK").append(Row);
                    var si = 0;
                    if (i<10) si= i*200;else si = 3000;
                    $("#My_New_Task_Div"+i).fadeIn(si);
                }
            }
        }
    };
    setInterval("getAmqMesssage()","200000");

    window.getAmqMesssage=function(){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/alesmynewchecktask/getAmqMessage",
            type:"post",
            dataType:"json",
            data:{'token':token},
            success:function(obj){
                if (obj.httpCode==200&&obj.data.length>0){
                    var titleStr = "接收到"+ obj.data.length + "条新任务"
                    var msgStr = "";
                    for (var i = 0; i < obj.data.length; i++) {
                        msgStr += obj.data[i].content+"<br/>";
                    }
                    $.messager.show({
                        title : titleStr,
                        msg : msgStr,
                        showType : msgStr,
                        timeout : 5000
                    });
                    $.ajax({
                        headers:{
                            token:window.localStorage.getItem("token")
                        },
                        url:"/sofn-ales-web/alesmynewchecktask/getCheckTaskByUser",
                        type:"post",
                        dataType:"json",
                        data:{'token':token},
                        success:function(obj){
                            $scope.showMyTask(obj);
                        },
                        fail: function (data) {
                        }
                    });
                }
            },
            fail: function (data) {
            }
        });
    };

    /**
     *历史任务详情查看
     * */
    window.queryHistoryInfo=function(o){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx","history");
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr",o);
        $state.go("index.content.ales/taskManage/myTaskInformation");
    };

    /**
     *新任务详情查看
     * */
    window.queryInfo=function(o){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx","new");
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr",o);
        $state.go("index.content.ales/taskManage/myTaskInformation");
    };

});
