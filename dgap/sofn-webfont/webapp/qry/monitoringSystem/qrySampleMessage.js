var myapp=angular.module("myapp",[]);
myapp.controller("qrySampleMessage",function($scope,$http,$state){

    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//监测任务
    var token=window.localStorage.getItem("token");//监测机构名称

    $scope.organTaskId="";

    $scope.sampleCode = "";
    $scope.sampleName = "";
    $scope.testedDeparment = "";

    $scope.toCheck = function () {
        $("#grid").dataTable().api().ajax.reload();
    }

    //重置
    $scope.reset = function () {
        window.location.reload();
    }

    $http({
        url:"/sofn-ads-web/adsOrganTask/findAdsMonitorTaskByid",
        method:"post",
        params:{"token":token,"monitorTaskId":adsMonitorTaskId,"monitorClass":"","systemType":"qry"}
    }).success(function(data){
        $scope.organTaskId=data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id

        //bootstrap表格
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-ads-web/adsMonitorSample/getSamplePageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.total;//总个数
                    json.recordsFiltered = json.total;
                    json.pageNumber = json.pageNum;//页码
                    json.pageSize = json.pageSize;//每页个数
                    json.start = (json.pageNum - 1) * json.pageSize;//起始位置
                    json.length = json.pageSize;
                    return json.list;
                },
                data: function (params) {
                    params.organTaskId =  $scope.organTaskId;
                    params.sampleCode = $scope.sampleCode;    //抽样编码
                    params.testedDeparment = $scope.testedDeparment;//受检单位
                    params.sampleName = $scope.sampleName;    //样品名称
                },
                type: "post"
            },
            columns: [
                {
                    data: "ID",
                    checkbox: true,
                    title: '<input id="checkAll" type="checkbox" value="" />',
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (row.SAMPLE_REPORT == "2") {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '" disabled/>';
                        } else {
                            return '<input id="c' + meta.row + '" type="checkbox" name="checkOne" class="editor-active"  value="'
                                + data + '"/>';
                        }

                    }
                },
                {
                    data: "RN",
                    title: "序号"
                },
                // {
                //     data: "PRODUCT_TRACEABILITY_CODE",
                //     title: "产品编码",
                //     render: function (data, type, row, meta) { // 模板化列显示内容
                //         return '<input class="gui-input" readonly="true" style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                //     }
                // },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称"
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    render: function (data) { // 模板化列显示内容
                        if($scope.disabled){
                            return '<a name="SAMPLE_CODE" style="color: grey">' + data + '</a>';
                        }
                        return '<a name="SAMPLE_CODE" onclick="checkClick(\'' + data + '\')" >' + data + '</a>';
                    }
                },

                {
                    data: "TESTED_DEPARMENT",
                    title: "受检单位"
                },
                {
                    data: "PRODUCING_AREA",
                    title: "样品产地",
                    render: function (data, type, row) { // 模板化列显示内容
                        if (row.PRODUCING_AREA != undefined) {
                            return $.fn.Cityname(row.PRODUCING_AREA)
                        } else {
                            return "";
                        }
                    }
                },
                {
                    data: "CHECK_LINK",
                    title: "抽样环节",
                    visible: false
                },
                {
                    data: "SAMPLE_DATE",
                    title: "抽样日期"
                },
                {
                    data: "ID",
                    title: "ID",
                    visible: false
                },
                {
                    data: "SAMPLE_REPORT",
                    title: "状态",
                    render: function (data, type, row) {
                        if ("0" == data) {
                            return "未上报";
                        } else if ("2" == data) {
                            return "已上报";
                        } else {
                            return "已退回";
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return '<a class="color" style="cursor: pointer;" onclick="queryInfo(\'' + row.SAMPLE_CODE + '\')">查看</a>'
                    }
                }
            ],
        });
    }).error(function(){
    });

    //查看详情
    window.queryInfo = function (code) {
        // var code = $scope.getSelectRowsSampleCode();
        if (code != false) {
            // if (confirm('是否修改抽样信息？')) {
                $http({
                    url: "/sofn-ads-web/adsMonitorSample/findAdsMonitorSampleById",
                    method: "post",
                    params: {sampleCode: code,organTaskId :  $scope.organTaskId}
                }).success(function (data) {
                    $scope.producingArea2 = data.data.PRODUCING_AREA
                    if (data.data.PRODUCING_AREA == null) {
                        $.fn.mycity("sheng2", "shi2", "xian2", "");
                    } else {
                        $.fn.mycity("sheng2", "shi2", "xian2", $scope.producingArea2);
                    }
                    $scope.data = data.data;
                    if (data.data.PRODUCE_DATE == null || data.data.PRODUCE_DATE == "1970-01-01") {
                        $scope.produceDate = "";
                    } else {
                        $scope.produceDate = data.data.PRODUCE_DATE;
                    }

                    $("#code").empty();// 解决二维码重复生成BUG

                    //二维码
                    star = code[0];
                    $("#code").qrcode({
                        render: "table", //table方式
                        width: 90, //宽度
                        height: 90, //高度
                        text: star //任意内容
                    });
                    $("#dModal").modal("show");
                }).error(function () {

                });
            // }
        }
    }

    $scope.close = function () {
        $("#dModal").modal("hide");
    }

    $scope.rebackBtn = function () {
        $state.go("index.content.qry/monitoringSystem/monitorTaskList");
    }
})
