//页面取值
var myapp=angular.module("myapp",[]);
angular.module("myapp",[]).controller("routineMonitorReport",function($scope,$http,$state){
//打印设置
    $("#PrintArea").click(function(){
        $(".Sup_contain_section_contain").printArea()
    });
    $scope.reportid=window.localStorage.getItem("asms_check_routineMonitor_reportlist_reportid");
    $http({
        url:"/sofn-asms-web/tspinstask/getReportsInfo",
        method:"post",
        data:{id:$scope.reportid}
    }).success(function(obj){
        $scope.reCord=obj.data;
        $scope.reCord.inspectionTime=new Date(parseInt($scope.reCord.inspectionTime) + 28800000).toISOString().slice(0,10);
    }).error(function (data) {
        
    });
    //清楚缓存
    $scope.RemoveLocal=function(){
        window.localStorage.removeItem("asms_check_routineMonitor_reportlist_reportid");
        $state.go("index.content.asms/check/routineMonitorReportList");
    }
    //导航展开
    $("#collapseThree").attr("class","panel-collapse collapse in");
    $("#collapseThree li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
});
