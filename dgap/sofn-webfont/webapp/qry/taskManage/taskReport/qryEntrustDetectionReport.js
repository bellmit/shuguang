angular.module("myapp", []).controller("qryEntrustDetectionReport", function ($scope, $http, $state) {

    /**
     * public
     * */
    $scope.TaskId = window.localStorage.getItem("qry_wtTaskInfo_ID_Report");
    $scope.Taskstate = window.localStorage.getItem("qry_wtTaskInfo_ID_Report_state");

    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-qry-web/alesEntrustDetection/presList",
            dataSrc: function (json) {
                console.info(json.data);
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
                params.monitorTaskId = $scope.TaskId;
                params.years = $("input[name='years']").val();
                params.monitorTask = $("input[name='monitorTask']").val();
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
                data: "MONITORTASK",
                title: "所属任务",
                width: "30%"
            },
            {
                data: "UPLOADDEPARTMENT",
                title: "机构名称",
                width: "10%"
            },
            {
                data: "FILENAME",
                title: "报告名称",
                width: "20%"
            },
            {
                data: "ORGANTASKID",
                title: "样品及检测信息",
                render: function (data, type, row) {
                    if (data == null) {
                        return null;
                    } else {
                        return '<a style="cursor:pointer" onclick="querySampleJcInfoList(\'' + data.trim() + '\')">查看</a>';
                    }
                    return "----"
                },
                width: "10%"
            },
            {
                data: "FILEADDRESS",
                title: "操作",
                render: function (data, type, row) {

                    return settingTableOperation(data, row.FILEADDRESS, row.FILENAME);

                },
                width: "5%"
            }
        ]
    });
    $scope.returnTaskList = function () {
        $state.go("index.content.qry/taskManage/qryEntrustDetection")
    };
    /**
     * 样品及检测信息列表
     * */
    window.querySampleJcInfoList = function (o) {
        window.localStorage.removeItem("ales_Task_ReportList_SampleInfoList");
        window.localStorage.setItem("ales_Task_ReportList_SampleInfoList", o);
        $state.go("index.content.qry/taskManage/taskReport/TaskSampleJcInfoList");
    }
    /*下载文件*/
    function settingTableOperation(id, fileAddress, fileName) {
        var link;
        if($scope.Taskstate == '已结束，已完成'){
            link = '<a class="color " style="cursor: pointer" href="/sofn-sys-web/sysTemplate/fileDownload?fileUrl=<%= fileAddress %>&fileName=<%= fileName %>">下载</a>';
        }else{
            link ='无'
        }
        var template = _.template(link);
        return template({id: id, fileAddress: encodeURIComponent(fileAddress), fileName: encodeURIComponent(fileName)});
    }
});