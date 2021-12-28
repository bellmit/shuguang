angular.module("myapp", []).controller("TaskSampleJcInfo", function ($scope, $http, $state) {

    /**
     * 该页面为检测信息详情页面
     * */

    /**
     * 公共值
     * */
    $scope.jbInfomation = {};//检测详情基本信息
    //详情id
    $scope.xQ_Id = window.localStorage.getItem("ales_Task_ReportList_SampleInfo");


    /**
     * 检测详情列表
     * */
    $("#ToCheck").click(function () {
        $("#productList").dataTable().api().ajax.reload();
    });
    $("#productList").dataTable({
        ajax: {
            headers:{
                token:window.localStorage.getItem("token")
            },
            url: "/sofn-ales-web/llesEntrustDetection/getReportByJcInfo",
            dataSrc: function (json) {
                getJbInfomation(json.jbInfo);//基本信息
                json.recordsTotal = json.data.length;//总个数
                json.recordsFiltered = json.data.length;
                json.start = 0;//起始位置
                json.length = json.data.length;
                return json.data;//列表
            },
            data: function (params) {
                params.checkInfoId = $scope.xQ_Id;
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
                }
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
                    }else if("2" == data){
                        return "不作判定";
                    } else {
                        return "不合格";
                    }
                }
            },
            {
                title: "LOD",
                data: 'lod'
            },
            {
                title: "LOO",
                data: 'loo'
            }
        ]
    });

    //基本信息
    window.getJbInfomation = function (o) {
        $("span[name='productCode']").text(o[0].adsCheckInfo.productTraceabilityCode==null?"无":o[0].adsCheckInfo.productTraceabilityCode);
        $("span[name='sampleCode']").text(o[0].adsCheckInfo.sampleCode==null?"无":o[0].adsCheckInfo.sampleCode);
        $("span[name='sampleName']").text(o[0].adsCheckInfo.sampleName==null?"无":o[0].adsCheckInfo.sampleName);
        $("span[name='testedDeparment']").text(o[0].adsCheckInfo.testedDeparment==null?"无":o[0].adsCheckInfo.testedDeparment);
        $("span[name='sampleDeparment']").text(o[0].adsCheckInfo.sampleDeparment==null?"无":o[0].adsCheckInfo.sampleDeparment);
        $("span[name='checkOrgan']").text(o[0].adsCheckInfo.checkOrgan==null?"无":o[0].adsCheckInfo.checkOrgan);
        $("span[name='taskName']").text(o[1].TaskName);
        // $scope.jbInfomation.productCode=o[0].adsCheckInfo.productCode;
        // $scope.jbInfomation.sampleCode=o[0].adsCheckInfo.sampleCode;
        // $scope.jbInfomation.sampleName=o[0].adsCheckInfo.sampleName;
        // $scope.jbInfomation.testedDeparment=o[0].adsCheckInfo.testedDeparment;
        // $scope.jbInfomation.sampleDeparment=o[0].adsCheckInfo.sampleDeparment;
        // $scope.jbInfomation.checkOrgan=o[0].adsCheckInfo.checkOrgan;
        // $scope.jbInfomation.taskName=o[1].TaskName;
    }
});

