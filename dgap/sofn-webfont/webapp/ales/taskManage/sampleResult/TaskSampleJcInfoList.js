angular.module("myapp", []).controller("TaskSampleJcInfoList", function ($scope, $http, $state) {
    /**
     * 检测信息列表
     * */
    $scope.organTaskId = window.localStorage.getItem("ales_taskmanage_mychecktask_taskinfomation_samplelist_checkresult");//检测信息id
    $scope.jcUnitName = window.localStorage.getItem("ales_taskManage_myTaskInformation_jcUnitName");//(页面暂取检测单位)；
    $(function () {
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-ales-web/alesmynewchecktask/sampleResultList",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;
                    json.pageSize = json.data.pageSize;
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.organTaskId = $scope.organTaskId;
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
                    data: "SAMPLE_NAME",
                    title: "样品名称",
                    width: "5%"
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
                    width: "10%",
                    render: function (data, type, row) {
                        return $scope.jcUnitName;
                    }
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
                    title: "操作",
                    render: function (data, type, row) {
                        return '<a style="cursor:pointer" onclick="viewOrganTask(\'' + data + '\')">查看</a>';
                    },
                    width: "7%"
                }
            ]
        });
    });
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    window.viewOrganTask = function (data) {
        $("#product_info2").modal("show");
        var tableName = $('#productList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#productList").dataTable({
            "paging": false,
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ales-web/alesmynewchecktask/resultInfomation",
                dataSrc: function (json) {
                    return json.data;
                },
                error:function (msg) {
                    console.log(msg);
                    
                },
                data: function (params) {
                    params.checkInfoId = data;
                },
                type: "post"
            },
            columns: [
                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (0 == data) {
                            return '<a id="' + meta.row + '" value="' + "未检出" + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">未检出</a>';
                        } else {
                            return '<a id="' + meta.row + '" value="' + data + '" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123(' + meta.row + "," + row.judgeNum + ')">' + data + '</a>';
                        }
                    },
                },
                {
                    data: "judgeNum",
                    title: "判定值"
                },
                {
                    data: "result",
                    title: "抽检结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else {
                            return "不合格";
                        }
                    }
                },
                {
                    title: "LOD（位）",
                    data: function (data, type, row, meta) {
                        return ""
                    }
                },
                {
                    title: "：mgkg LOQ",
                    data: function (data, type, row, meta) {
                        return ""
                    }
                }
            ]
        });
    };

    window.my123 = function (index, judgeNum) {
        var inputValue = document.getElementById(index).value;
        if ("未检出" == inputValue || "" == inputValue) {
            return;
        }
        var real = index + 1;
        if (inputValue <= judgeNum) {
            $("#productList2 tr:eq(" + real + ") td:eq(3)").html("合格");
        } else {
            $("#productList2 tr:eq(" + real + ") td:eq(3)").html("不合格");
        }
    }
});

