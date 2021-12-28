angular.module("myapp",[]).controller("qryTaskSampleJcInfo",function($scope,$http,$state){

    /**
     * 该页面为检测信息详情页面
     * */

    /**
     * 公共值
     * */
    $scope.jbInfomation = {};//检测详情基本信息
    //详情id
    $scope.xQ_Id = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfo");


    $scope.asms_Check_Task_Type_Title = window.localStorage.getItem("asms_Check_Task_Type_Title");

    $scope.jcUnitName = window.localStorage.getItem("asms_Check_Task_ReportList_SampleInfoList_jcUnitName");//检测单位名称



    if ($scope.asms_Check_Task_Type_Title == 'JDCC')$("#titleName").html("监督抽查");
    if ($scope.asms_Check_Task_Type_Title == 'LXJC')$("#titleName").html("例行监测");
    if ($scope.asms_Check_Task_Type_Title == 'ZXJC')$("#titleName").html("专项检测");
    if ($scope.asms_Check_Task_Type_Title == 'FJRW')$("#titleName").html("复检任务");

    /**
     * 检测详情列表
     * */
    $("#ToCheck").click(function(){
        $("#productList").dataTable().api().ajax.reload();
    });
    $("#productList").dataTable({
        ajax : {
            url : "/sofn-qry-web/monitorTask/getReportByJcInfo",
            dataSrc:function(json){
                //基本信息
                getJbInfomation(json.jbInfo);
                //列表
                json.recordsTotal=json.data.total;//总个数
                json.recordsFiltered=json.data.total;
                json.pageNumber=json.data.pageNum;//页码
                json.pageSize=json.data.pageSize;//每页个数
                json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                json.length=json.data.pageSize;
                return json.data.list;
            },
            headers:{
                token:window.localStorage.getItem("token")
            },
            data : function(params) {
                params.checkInfoId = $scope.xQ_Id;
            },
            type:"post"
        },
        columns : [
            {
                data : "checkProject",
                title : "检测项目"
            },
            {
                data : "checkNum",
                title : "检测值",
                render : function(data, type, row,meta) { // 模板化列显示内容
                    if(0 == data){
                        return '<a id="'+meta.row+'" value="'+"未检出"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">未检出</a>';
                    }else{
                        return '<a id="'+meta.row+'" value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                    }
                }
            },
            {
                data : "unit",
                title : "单位",
                render : function(data, type, row) {
                    return data?data:"无";

                }
            },
            {
                data: "result",
                title: "抽检结果",
                render: function (data, type, row) {
                    if ("-1" == data) {
                        return "暂未检测";
                    } else if ("1" == data) {
                        return "合格";
                    } else if ("0" == data){
                        return "不合格";
                    }else if ("2" == data) {
                        return "不作判定";
                    }
                }
            },
            {
                data: "lod",
                title: "LOD"
            },
            {
                data: "loo",
                title: "LOQ"
            }
        ]
    });

    //基本信息
    window.getJbInfomation = function(o){
        var  productCode = o[0].adsCheckInfo.productTraceabilityCode;
        if(productCode == null || productCode == undefined || productCode==''){
            productCode = "无";
        }
        $("span[name='productCode']").text(productCode);
        $("span[name='sampleCode']").text(o[0].adsCheckInfo.sampleCode);
        $("span[name='sampleName']").text(o[0].adsCheckInfo.sampleName);
        $("span[name='testedDeparment']").text(o[0].adsCheckInfo.testedDeparment);
        $("span[name='sampleDeparment']").text(o[0].adsCheckInfo.sampleDeparment);
        $("span[name='checkOrgan']").text(o[0].adsCheckInfo.checkOrgan ? o[0].adsCheckInfo.checkOrgan :$scope.jcUnitName);
        $("span[name='taskName']").text(o[1].TaskName);
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

