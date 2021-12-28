angular.module("myapp", []).controller("TaskSampleJcInfoList", function ($scope, $http, $state) {

    /**
     * 该页面为检测对象的检测信息列表
     * */

    /**
     * 公共值
     * */
    $scope.JcInfoId = window.localStorage.getItem("ales_Task_ReportList_SampleInfoList");//检测信息id
    /**
     * 报告列表
     * */
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    $(function () {
        $("#grid").dataTable({
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/llesEntrustDetection/getJclistByReport",
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
                data: function (params) {
                    params.organTaskId = $scope.JcInfoId;
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
                    width: "5%"
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称",
                    width: "7%"
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    width: "10%"
                },
                {
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    width: "30%"
                },
                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位",
                    width: "10%"
                },
                {
                    data: "SAMPLE_DEPARMENT",
                    title: "抽样单位",
                    width: "10%"
                },
                {
                    data: "CHECK_ORGAN",
                    title: "检测单位",
                    width: "10%"
                },
                {
                    data: "RESULT",
                    title: "检测结果",
                    width: "10%",
                    render: function (data, type, row) {
                        if ("1" == data) {
                            return "合格";
                        } else if ("0" == data) {
                            return "不合格";
                        } else if ("-1" == data) {
                            return "暂未检测";
                        }else if("2" == data){
                            return "不作判定";
                        }
                    }
                },
                {
                    data: "ID",
                    title: "样品及检测信息",
                    render: function (data, type, row) {
                        if(row.RESULT == "-1"){
                            return ;
                        }else {
                        return '<a style="cursor:pointer" onclick="queryReports(\'' + data + '\')">查看</a>';
                        }
                    },
                    width: "7%"
                }
            ]
        });
    });

    /**
     *查看检测详情
     **/
    window.queryReports = function (o) {
        window.localStorage.removeItem("ales_Task_ReportList_SampleInfo");
        window.localStorage.setItem("ales_Task_ReportList_SampleInfo", o);
        $state.go("index.content.ales/taskManage/taskReport/TaskSampleJcInfo");
    }

});

