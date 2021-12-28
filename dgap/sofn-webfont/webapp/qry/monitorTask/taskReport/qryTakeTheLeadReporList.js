angular.module("myapp", []).controller("qryTakeTheLeadReporList", function ($scope, $http, $state) {

    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-qry-web/monitorTask/getPageInfoAdsFileBySupId",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data: function (params) {
                //通过部级任务查看传递承担单位id
                params.taskId =window.localStorage.getItem("takeTheLeadReporListTaskId") ;
            },
            type: "post"
        },
        columns: [
            {
                data: 'ID',
                title: 'ID',
                visible: false
            },
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                width: "3%"
            },
            {
                data: "YEARS",
                title: "年份",
                width: "5%"
            },
            {
                data: "MONITOR_TASK",
                title: "所属任务",
                width: "30%"
            },
            {
                data: "RELEASE_UNIT",
                title: "单位",
                width: "10%"
            },
            {
                data: "FILE_NAME",
                title: "报告名称",
                width: "30%"
            },
            {
                data: "FILE_ADDRESS",
                title: "操作",
                render: function (data, type, row) {
                    return '<a style="cursor: pointer" class="Check-report"  onclick=downloadFile(\''+row.FILE_ADDRESS+'\',\''+row.FILE_NAME.replace(/\s+/g,"")+'\')>下载</a>';
                },
                width: "5%"
            }
        ]
    });

    $scope.retrunDiskTop = function () {
        if ($scope.asms_Check_Task_Type_Title == 'LXJC'){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'ZXJC'){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == 'FJRW'){
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }else if($scope.asms_Check_Task_Type_Title == 'JDCC'){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }
    }

   //下载
    window.downloadFile=function(takeTheLeadUrl,takeTheLeadFileName){
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/qryFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', takeTheLeadUrl);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', takeTheLeadFileName);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }
    $scope.back = function(){
        $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");
        if($scope.asms_Check_Task_Type_Title == "LXJC"){
            $state.go("index.content.qry/monitorTask/qryRoutineMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "ZXJC"){
            $state.go("index.content.qry/monitorTask/qrySpecialMonitor");
        }else if($scope.asms_Check_Task_Type_Title == "JDCC"){
            $state.go("index.content.qry/monitorTask/qryCheckTask");
        }else{
            $state.go("index.content.qry/monitorTask/qryReCheckTask");
        }
    }
});

