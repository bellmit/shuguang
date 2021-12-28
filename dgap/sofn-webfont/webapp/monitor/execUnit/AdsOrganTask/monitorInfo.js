
var myapp=angular.module("myapp",[]);
myapp.controller("monitorInfo",function($scope,$http,$state){

    $("#newTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleInfo")
    });

    $("#bePubTask").click(function(){
        $state.go("index.content.monitor/execUnit/AdsOrganTask/monitorInfo")
    });

    //获取本地存储的sampleCode
    var organTaskId=window.localStorage.getItem("organTaskId");//机构任务ID
    $scope.sampleCode=window.localStorage.getItem("ads_sample_code");
    var productTraceabilityCodes=window.localStorage.getItem("ads_product_traceability_code");
    if (productTraceabilityCodes != "null"){
        $scope.productTraceabilityCode = productTraceabilityCodes;
    }else {
        $scope.productTraceabilityCode = "";
    }
    $scope.sampleName=window.localStorage.getItem("ads_sample_name");
    $scope.monitorClass=window.localStorage.getItem("monitorClass");

    var urlSource = window.localStorage.getItem("urlSource");

    $scope.taskDetail = function(data) {
        $state.go("index.content.monitor/execUnit/AdsOrganTask/taskDetail")
    }
    $scope.sampleMessage = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/AdsOrganTask/sampleMessage")
    }
    $scope.test = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/testList")
    }
    $scope.routineMonitorList = function(data) {
        $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList")
    }
    $scope.routineReportUpload = function(data) {
        window.localStorage.setItem("organTaskId",organTaskId);//机构任务ID
        $state.go("index.content.monitor/execUnit/routineMonitor/routineReportUpload")
    }

//设置中文
    function toUtf8(str) {

        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
    $scope.name=$scope.sampleName;
    star=toUtf8($scope.name);
    $("#code").qrcode({
        render: "table", //table方式
        width: 100, //宽度
        height:100, //高度
        text:star //任意内容
    });
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsCheckInfo/getInfoProject",
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
                    params.sampleCode=$scope.sampleCode;

                },
                type:"post"
            },
            columns : [

                {
                    title : "序号",
                    data : function(data, type, row, meta) {
                        return meta.row + 1
                    }
                },

                {
                    data: "checkProject",
                    title: "检测项目"
                },
                {
                    data: "checkNum",
                    title: "检测值"
                },
                {
                    data: "judgeNum",
                    title: "判定值"
                },
                {
                    data: "result",
                    title: "判定结果",
                    render : function(data, type, row) {
                       /* if("1"==data){
                            return "合格";
                        }else if("0"==data){
                            return "不合格";
                        }else if("-1"==data){
                            return "检测未上报";
                        }*/
                        var sampleName =$scope.sampleName;
                        var checkProject = row.checkProject;
                        var url = "/sofn-ads-web/adsCheckInfo/judgeCheckItem";

                        var str = "";

                        $.ajax({
                            url: url,
                            type: "post",
                            async: false,
                            data: {
                                checkItem: checkProject,
                                sampleName: sampleName
                            },
                            success: function(datas) {
                                var status = datas.data;
                                if (status == "1"){
                                    str =  "不作判定";
                                }else {
                                    if ("-1" == data) {
                                        str =  "检测未上报";
                                    } else if ("1" == data) {
                                        str =  "合格";
                                    } else {
                                        str =  "不合格";
                                    }
                                }
                            },
                            fail: function(data) {
                                $.jBox.tip("失败");
                            }
                        });
                        return str;
                    }
                },
                /*{
                 data : "ID",
                 title : "状态",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var look_a = '待发布';
                 return look_a;
                 }
                 },*/
            ],
        });
    });
//导出excel
    $scope.exportExcel = function () {
        var sampleCode = $scope.sampleCode;
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url: "/sofn-ads-web/adsCheckInfo/getProjectList",
                    method: "post",
                    dataType: "json",
                    params: {"sampleCode": sampleCode},
                    responseType: 'arraybuffer',
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                    if (blob.size > 0) {
                        var objectUrl = URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        document.body.appendChild(a);
                        a.setAttribute('style', 'display:none');
                        a.setAttribute('href', objectUrl);
                        a.setAttribute('download', '检测项目.xls');
                        a.click();
                    } else {
                        jBox.tip("导出失败，没有数据！", "info")
                    }
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
    }

    $scope.backBtn = function () {
       // $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList");
        if(urlSource=="初检任务"){
            $state.go("index.content.monitor/execUnit/Recheck/RoutineMonitor/routineMonitorListRck");
        }else if ($scope.monitorClass=="例行监测"){
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList");
        }else if ($scope.monitorClass=="专项监测"){
            $state.go("index.content.monitor/execUnit/routineMonitor/routineMonitorList");
        }else if ($scope.monitorClass=="监督抽查"){
            $state.go("index.content.monitor/execUnit/Audit/routineMonitorListAudit");
        }else if ($scope.monitorClass=="复检任务"){
            $state.go("index.content.monitor/execUnit/Recheck/routineMonitorListRecheck");
        }
    }

})








