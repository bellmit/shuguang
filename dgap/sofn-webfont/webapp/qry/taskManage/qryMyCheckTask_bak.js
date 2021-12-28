var myapp=angular.module("myapp",[]);
myapp.controller("qryMyCheckTask",function($scope,$http,$state){
    //判定页面权限、tab页、按钮
/*    var a= JSON.parse(window.localStorage.getItem("menuTabObj")).tab;
    a.map(function(item,i){
        if(item.text=="监督抽查任务"){
            $(".qryEntrustDetection").css("display","inline-block");
        }
    });*/
    $scope.tabOrIdx = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II");
    if ($scope.tabOrIdx == "history"){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr_idx_II");
        $("li[name='newTask']").removeClass("active");
        $("li[name='historyTask']").addClass("active");
    }
    /**
     * *
     * *承担任务包含新任务与历史任务，判断标准为任务是否填写抽样单信息
     * *
     * */
    var token = window.localStorage.getItem("token");

    //获取用户信息
    $scope.user = {};
    $scope.organization = {};
    //机构类型
    $scope.orgType="";
    var area = ""
    var jibie=""
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-qry-web/subject/findCurrentUserByToken",
        method:"post"
    }).success(function(data){
        area = data.user.regionId;
        jibie= data.user.orgLevel;
        if(jibie==1){
            area=0
        }
        if(jibie==2){
            area=area.substr(0,2);
        }
        if(jibie==3){
            area=area.substr(0.4)
        }
        if(jibie==4){
            area=area.substring(0,6)
        }
        $.fn.mycity("sheng","shi","xian",area);
        //$.fn.mycity("sheng","shi","xian",$scope.organization.regionId);
        if($("#sheng").val()!=null&&$("#sheng").val()!=""&&$("#sheng").val()!=undefined){
            $("#sheng").attr("disabled",true);
        }
        if($("#shi").val()!=null&&$("#shi").val()!=""&&$("#shi").val()!=undefined){
            $("#shi").attr("disabled",true);
        }
        if($("#xian").val()!=null&&$("#xian").val()!=""&&$("#xian").val()!=undefined){
            $("#xian").attr("disabled",true);
        }
        $scope.loadGrid();
       /* $scope.user=data.user;
        $scope.organization = $scope.user.organization;
        $scope.orgType= $scope.organization.orgType;
        //定义一个管辖区域，通过机构级别+区域来判断
        $scope.underArea = $scope.organization.regionId.substr(0,2*($scope.organization.orgLevel-1));*/

        //监管机构可以按照区域查询
    /*   if($scope.orgType=='ASMS'){
            //监管机构，显示区域框
            $("#AnyArea").show();
            //初始化区域下拉框初始选项
            $scope.reloadArea();
            $scope.loadGrid();
            //$("#grid").dataTable().api().ajax.reload();
        }*//*else{        取消的区域隐藏 2018年5月24日08:48:26
            //执法机构   隐藏区域
           $("#AnyArea").hide();
            $scope.loadGrid();
            //$("#grid").dataTable().api().ajax.reload();
        }*/
    }).error(function(data){
        console.log(data.httpCode);
        
            });
//重置区域 取消限制 2018年5月24日10:48:14
 /*   $scope.reloadArea=function(){
        if($scope.organization.orgLevel!="1") {
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
        }else{
            $.fn.mycity("sheng", "shi", "xian");
        }
    }*/

    $scope.getAreaBySelect=function() {
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
        console.log("------------"+areaId);
        return areaId;
    }

    /**
     *公共值
     * */

    /**
     *任务列表
     * */
    $scope.loadGrid = function() {
        $("#grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : true,
            fnDrawCallback: function() {
            },
            ajax : {
                url : "/sofn-qry-web/alesmynewchecktask/getHistoryCheckTaskListByUser",
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
                        //任务状态
                        if(json.data.list[i].STATE!=null&&json.data.list[i].STATE!=""&&json.data.list[i].STATE!=undefined){
                            if(json.data.list[i].STATE=='0'){
                                json.data.list[i].STATE='未发布';
                            }
                            if(json.data.list[i].STATE=='1'){
                                json.data.list[i].STATE='已发布';
                            }
                            if(json.data.list[i].STATE=='2'){
                                json.data.list[i].STATE='已废止';
                            }
                        }
                    }
                    return json.data.list;
                },error:function(data){
                    console.log(data);
                    
                },
                data : function(params) {
                    //监管区域
                   // if($scope.orgType=='ASMS'){
                        params.regionId=$scope.getAreaBySelect();
                  //  }else{
                      //  params.regionId="";
                   // }
                    //任务状态
                    params.isSample=$("select[name='isSample']").val();
                    //任务类型
                    params.taskType=$("select[name='taskType']").val();
                    //任务年度
                    params.taskYear=$("input[name='taskYear']").val();
                    params.taskName=$("input[name='History_Task_NAME']").val();
                    //开始时间beginEndyzncxz功能 2018年5月31日10:42:52
                    params.dateBegin=$("input[name='History_Task_Date_Begin']").val();
                    params.beginEnd=$("input[name='History_Task_Date_BeginEnd']").val();
                    //结束时间yzncxz功能 2018年5月31日10:42:52
                    params.dateEnd=$("input[name='History_Task_Date_End']").val();
                    params.endBegin=$("input[name='History_Task_Date_EndBegin']").val();
                    params.token = token;

                    console.log("taskYear==>>"+params.taskYear+"taskName==>>"+params.taskName+"dateBegin==>>"+params.dateBegin+"dateEnd==>>"+params.dateEnd+"regionId==>>"+params.regionId)
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
                    width: "4%"
                },
                {
                    data : "TASKNAME",
                    title : "任务名称",
                    width: "10%",
                    render:function (data,type,row) {
                        if(data!=null && data.length>30){
                            return "<a id='TASKNAME"+row.ID+"' onmouseover=showPopContent('TASKNAME"+row.ID+"','"+data+"')>"+data.substring(0,29)+"...</a>";
                        }else {
                            return data;
                        }
                    }
                },
                {
                    data : "TASKYEAR",
                    title : "年度",
                    width: "8%",
                },
                {
                    data : "TASKTYPE",
                    title : "监测类型",
                    width: "8%",
                },
                {
                    data : "TASKBEGINTIME",
                    title : "开始时间",
                    width: "10%"
                },
                {
                    data : "TASKENDTIME",
                    title : "结束时间",
                    width: "10%"
                },
                {
                    data : "ISSAMPLE",
                    title : "任务状态",
                    width: "8%",
                    render : function(data, type, row) {
                        if (data == "0"){
                            return "未提交";
                        }else if (data == "1"){
                            return "已提交";
                        }else if(data == "2"){
                            return  "部分提交";
                        }
                    }
                },
                {
                    data : "ISSAMPLE",
                    title : "抽样单",
                    width: "8%",
                    render : function(data, type, row) {
                        if (data == "0"){
                            return "未提交";
                        }else if (data == "1"){
                            return "已提交";
                        }else if(data == "2"){
                            return  "部分提交";
                        }
                    }
                },
                {
                    data : "ID",
                    title : "报告",
                    width: "5%",
                    render : function(data, type, row) {
                        return '<a style="cursor: pointer" onclick="queryHistoryInfo(\''+data+'\')">任务详情</a>';/*     &nbsp;&nbsp;&nbsp;' +
                            ' <a style="cursor: pointer" onclick="queryHistoryInfo(\''+data+'\')">查看报告</a>';*/
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
    };

    /**
     *查询按钮
     * */
    $("#History_Task_ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });

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

    /**
     *任务详情查看
     * */
    window.queryHistoryInfo=function(o){
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr_idx");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr_idx","history");
        window.localStorage.removeItem("ales_taskmanage_mychecktask_taskinformation_opr");
        window.localStorage.setItem("ales_taskmanage_mychecktask_taskinformation_opr",o);
        $state.go("index.content.qry/taskManage/qryMyTaskInformation");
    };

    //数据导出
    $scope.exportData=function(){

        //提交数据
        var taskYear=$("input[name='taskYear']").val();
        var isSample=$("select[name='isSample']").val();
       // var taskType=$("select[name='taskType']").val(); 类型取消 2018年5月24日14:55:47
        var taskName=$("input[name='History_Task_NAME']").val();
        var tsBeginTime=$("input[name='History_Task_Date_Begin']").val();
        var beginEnd=$("input[name='History_Task_Date_BeginEnd']").val();
        //结束时间yzncxz功能 2018年5月31日10:42:52
        var endBegin=$("input[name='History_Task_Date_EndBegin']").val();
        var tsEndTime=$("input[name='History_Task_Date_End']").val();
        var token=window.localStorage.getItem("token");
        //监管区域
        //if($scope.orgType=='ASMS'){ 取消消监管机构限制 2018年5月24日14:45:48
            var regionId = $scope.getAreaBySelect();
        /*}else{
            var regionId="";
        }*/

        //form
        var form=$("<form>");
        form.attr('style','display:none');
        form.attr('target','');
        form.attr('method','post');
        form.attr('action',"/sofn-qry-web/alesmynewchecktask/exportSubjSv?token="+token+'&taskName='+taskName+'&tsBeginTime='+tsBeginTime+'&beginEnd='+beginEnd+'&endBegin='+endBegin+'&tsEndTime='+tsEndTime
                            +'&regionId='+regionId +'&taskYear='+taskYear+'&isSample='+isSample);//+'&taskType='+taskType 取消类型 2018年5月24日14:58:24
         $('body').append(form);
        form.submit();
    }

    //重置
    $scope.cleanSelectModelQuery = function(){
        $("input[name='History_Task_NAME']").val("");
        $("input[name='taskYear']").val("");
        $("input[name='History_Task_Date_Begin']").val("");
        //yzxj功能 2018年5月31日11:13:10
        $("input[name='History_Task_Date_BeginEnd']").val("");
        $("input[name='History_Task_Date_EndBegin']").val("");
        $("input[name='History_Task_Date_End']").val("");
        $("select[name='taskType']").val("");
        $("select[name='isSample']").val("");
        $("select[value='']").attr("selected",true);
        if($scope.orgType=='ASMS'){
            $scope.reloadArea();
        }
        $("#grid").dataTable().api().ajax.reload();
    };
});
