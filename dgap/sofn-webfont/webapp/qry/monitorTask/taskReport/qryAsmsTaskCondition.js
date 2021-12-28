var myapp=angular.module("myapp",[]);
myapp.controller("qryAsmsTaskCondition",function($scope,$http,$state){

    var asmsMonitorTaskId=window.localStorage.getItem("asmsMonitorTaskId");//监测任务
    var token=window.localStorage.getItem("token");//监测机构名称
    var taskName=window.localStorage.getItem("asmsMonitorTaskName");//任务名称
    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");

    /**
     * 分页展示数据
     */
    $(function() {

        $("#taskName").val(taskName);
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-qry-web/monitorTask/getPageInfoByMonitorTaskID",
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
                    //params.publishStatus= '1';
                    params.is_history = "Y";
                    params.monitorTaskId = asmsMonitorTaskId;
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
                    data: "sampleOrgan",
                    title: "机构名称"
                },
                {
                    data: "numbers",
                    title: "任务数"
                },
                {
                    data: "sampleFinishNum",
                    title: "抽样完成数"
                },
                {
                    data: "sampleReportStatus",
                    title: "抽样上报状态",
                    render : function(data, type, row) {
                        if("0"==data){
                            return "未上报";
                        }else if("1"==data){
                            return "上报中";
                        }else if("2"==data){
                            return "已上报";
                        }else if("3"==data){
                            return "已退回";
                        }else if("4"==data){
                            return "部分退回";
                        }else{
                            return "";
                        }
                    }
                },
                {
                    data: "sampleReportTime",
                    title: "抽样上报时间"
                },
                {
                    data: "checkFinishNum",
                    title: "检测完成数"
                },
                {
                    data: "checkReportStatus",
                    title: "检测上报状态",
                    render : function(data, type, row) {
                        if("0"==data){
                            return "未上报";
                        }else if("1"==data){
                            return "上报中";
                        }else if("2"==data){
                            return "已上报";
                        }else if("3"==data){
                            return "已退回";
                        }else if("4"==data){
                            return "部分退回";
                        }else{
                            return "";
                        }
                    }
                },
                {
                    data: "checkReportTime",
                    title: "检测上报时间"
                }
            ],
        });
    });

    $scope.rebackBtn = function () {
        if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'ZXJC'){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }
    }
});
