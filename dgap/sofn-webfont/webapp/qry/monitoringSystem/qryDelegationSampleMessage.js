var myapp=angular.module("myapp",[]);
myapp.controller("qryDelegationSampleMessage",function($scope,$http,$state){

    var adsMonitorTaskId=window.localStorage.getItem("adsMonitorTaskId");//监测任务
    var token=window.localStorage.getItem("token");//监测机构名称

    $scope.organTaskId = "";

    $http({
        url: "/sofn-qry-web/adsMonitorTask/findAdsMonitorTaskByid",
        method: "post",
        params: {"token": token, "monitorTaskId": adsMonitorTaskId, "monitorClass": ""}
    }).success(function (data) {
        $scope.detail = data.adsMonitorTask[0];
        $scope.taskName = data.adsMonitorTask[0].taskName;
        $scope.organTaskId = data.adsMonitorTask[0].adsOrganTaskList[0].id;//机构任务id
        $scope.monitorClass = data.adsMonitorTask[0].monitorClass//监测类型;

        $("#grid").dataTable({
            ajax: {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsOrganTask/pages",
                dataSrc: function (json) {
                    /* console.log("检测对象：",json);*/
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.superviseCheckTaskId = adsMonitorTaskId;//任务id
                    params.detectionUnitId = $scope.organTaskId;
                },
                type: "post"
            },
            columns: [
                {
                    data: "productType",
                    title: "产品种类"
                },
                {
                    data: "productName",
                    title: "产品名称"
                },
                {
                    data: "areaId",
                    title: "受检区域",
                    render: function (data, type, row) {
                        if (data == "" || data == undefined) {
                            return "未填写"
                        } else {
                            return $.fn.Cityname(data);
                        }
                    }
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '';
                        //状态为已上报或者权限为只读时不能配置上限值
                        // if(row.status=="1"||$scope.disabled){
                        //     look_a =  '<a class="pointer mr15" style="color: grey;cursor: default;">配置上限值</a>';
                        // }else{
                        //     look_a = '<a class="pointer mr15" onclick="configValue(\'' + row.id + '\',\'' + row.productName + '\') ">配置上限值</a>';
                        // }
                        var look_b = '<a class="pointer mr15" onclick="sample(\'' + data+ '\')">查看产品信息</a>';
                        return look_b;
                    }
                }
            ],
        });
    }).error(function () {
    });

    window.sample = function (data) {
        window.localStorage.setItem("monitorClass",$scope.monitorClass);//监测类型
        window.localStorage.setItem("organTaskId", $scope.organTaskId);//机构任务
        window.localStorage.setItem("taskName",$scope.taskName);//抽样截至时间
        $state.go("index.content.qry/monitoringSystem/qryDelegationSampleMessageInfo")
    }
});
