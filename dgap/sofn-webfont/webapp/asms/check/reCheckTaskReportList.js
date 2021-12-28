angular.module("myapp", []).controller("reCheckTaskReportList", function ($scope, $http, $state) {
    //任务id
    $scope.asmsCheckRoutineMonitorTaskId = window.localStorage.getItem("asms_check_routineMonitor_reportlist_xxxtaskid");
    if ($scope.asmsCheckRoutineMonitorTaskId == null || $scope.asmsCheckRoutineMonitorTaskId == undefined) {
        $.jBox.tip("无法获取任务报告列表");
        return;
    }
    //点击查询按钮刷新datatable
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    //分页
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-asms-web/routineMonitor/getReportsListByTask",
            headers:{
                token:window.localStorage.getItem("token")
            },
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                return json.data.list[0];
            },
            data: function (params) {
                params.xcPsersionId = "18aa1336a7614d69841c61b5b1a70645";
            },
            type: "post"
        },
        columns: [
            {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                }
            },
            {
                data: "ORG_ID",
                title: "机构名称",
                render: function (data, type, row) { // 模板化列显示内容
                    if (data == null || data == undefined) {
                        return '农业部农产品质量安全监督检验测试中心（北京）';
                    } else {
                        return data;
                    }
                }
            },
            {
                data: "REPORT_ID",
                title: "报告名称",
                render: function (data, type, row) { // 模板化列显示内容
                    if (data == null || data == undefined) {
                        return '2016年例行监测-蔬菜第四次';
                    } else {
                        return data;
                    }
                }
            },
            {
                data: "ID",
                title: "操作",
                render: function (data, type, row) { // 模板化列显示内容
                    //data.ID;
                    //console.log(data)
                    return '<a href="http://sw.bos.baidu.com/sw-search-sp/software/e6207d37846ba/QQ_8.7.19113.0_setup.exe" style="cursor: pointer" onclick="Checkreport(\'' + data + '\')">下载</a>';
                }
            },
        ],
    });
    //查看详情页面
    window.Checkreport = function (obj) {

        //window.localStorage.removeItem("asms_check_routineMonitor_reportlist_reportid");
        //window.localStorage.setItem("asms_check_routineMonitor_reportlist_reportid",obj);
        //$state.go("index.content.asms/check/routineMonitorReport");
    }
});

