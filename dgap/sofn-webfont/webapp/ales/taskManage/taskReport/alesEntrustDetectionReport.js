angular.module("myapp", []).controller("alesEntrustDetectionReport", function ($scope, $http, $state) {

    /**
     * public
     * */
    $scope.TaskId = window.localStorage.getItem("ales_wtTaskInfo_ID_Report");

    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-ales-web/llesEntrustDetection/presList",
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
                title: "检测机构名称",
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
                       
                        if(row.CHECK_REPORT_STATUS == 0){
                            return '<div style="cursor:pointer" >未上报</div>';
                        }else{

                            return '<a style="cursor:pointer" onclick="querySampleJcInfoList(\'' + data.trim() + '\')">查看</a>';
                        }
                    }
                },
                width: "10%"
            },
            {
                data: "FILEADDRESS",
                title: "操作",
                render: function (data, type, row) {
                    if (row.FILEADDRESS!=null&&row.FILENAME!=null){
                        return settingTableOperation(data, row.FILEADDRESS, row.FILENAME);
                    }else {
                        var link = '<a class="color " style="cursor: pointer;color: grey;" href="#">下载</a>';
                        return link;
                    }
                },
                width: "5%"
            }
        ]
    });
    $scope.returnTaskList = function () {
        $state.go("index.content.ales/taskManage/alesEntrustDetection")
    };
    /**
     * 样品及检测信息列表
     * */
    window.querySampleJcInfoList = function (o) {
        window.localStorage.removeItem("ales_Task_ReportList_SampleInfoList");
        window.localStorage.setItem("ales_Task_ReportList_SampleInfoList", o);
        $state.go("index.content.ales/taskManage/taskReport/TaskSampleJcInfoList");
    }
    /*下载文件*/
    function settingTableOperation(id, fileAddress, fileName) {
        var link = '<a class="color " style="cursor: pointer" href="/sofn-sys-web/sysTemplate/fileDownload?fileUrl=<%= fileAddress %>&fileName=<%= fileName %>">下载</a>';
        var template = _.template(link);
        return template({id: id, fileAddress: encodeURIComponent(fileAddress), fileName: encodeURIComponent(fileName)});
    }
});