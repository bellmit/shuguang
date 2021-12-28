var myapp=angular.module("myapp",[]);
myapp.controller("newTaskList",function($scope,$http,$state){

    var token =  window.localStorage.getItem("token");

    $("#newTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    });

    $("#bePubTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    $("#historyTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList")
    });

    $("#supTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
    });

    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    //年度
    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear()-15;//起始年份
    var endDate = myDate.getFullYear()+15;//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
                    ajax : {
                        url : "/sofn-ads-web/adsMonitorTask/getPageInfo",
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
                    //分页条件查询，不实现，自己写
                   // params.taskSource = 'DEPTASK';   //任务来源，新任务列表只查询部级任务
                    params.publishStatus = 0;   //新任务列表只查询未发布的任务
                    params.taskName = $("input[name='taskName']").val();    //任务名称
                    params.year = $("select[name='year']").val();
                    params.monitorClass = $("select[name='monitorClass']").val();
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    render : function(data, type, row) {
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report " onclick="lookOrganRegion(\''+row.id+'\') ">'+row.taskName+'</a>';
                        return look_a;
                    }
                },
                {
                    data: "year",
                    title: "年度"
                },
                {
                    data: "startTime",
                    title: "开始时间"
                },
                {
                    data: "endTime",
                    title: "结束时间"
                },
                {
                    data: "monitorClass",
                    title: "监测类型"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="lookOrganRegion(\''+row.id+'\') ">任务配置</a>';
                        return look_a;
                    }
                },
            ],
        });
    });

    /**
     * 跳转到机构与地域页面
     */
    window.lookOrganRegion=function(data){
        window.localStorage.setItem("adsMonitorTaskId",data);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView");
    };

    $scope.addTask = function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskAdd");
    };
})

