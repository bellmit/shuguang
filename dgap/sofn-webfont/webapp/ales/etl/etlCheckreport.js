var myapp=angular.module("myapp",[]);
myapp.controller("etlCheckreport",function($scope,$http,$state){
    $scope.ID=window.localStorage.getItem("ID");
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/dailyEnforceLaw/getDailyEnforceLawById",
        method:"post",
        data:{id:$scope.ID}
    }).success(function(data){
        $scope.reCord=data.dailyEnforceLaw;
        if($scope.reCord.taskBeginTime!=null&&$scope.reCord.taskBeginTime!=""&&$scope.reCord.taskBeginTime!=undefined) {
            $scope.beginTime = new Date(parseInt($scope.reCord.taskBeginTime)).toISOString().slice(0, 10);
            $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)).toISOString().slice(0, 10);
        }
        //$http({
			//url:"/sofn-asms-web/baseInspection/findEnterpriseById",
			//method:"post",
			//data:data.dailyEnforceLaw.enterpriseId
        //}).success(function(dataenterprise){
			//$scope.enterprise=dataenterprise.enterprise.ENTERPRISE_NAME;
			//console.log("主体名称"+$scope.enterprise.ENTERPRISE_NAME);
        //}).
        //error(function(){
        //});
        //console.log($scope.reCord);
    }).error(function(data){
        console.log(data);
        
    });
    //清楚缓存
    $scope.RemoveLocal=function(){
        window.localStorage.removeItem("xcPsersionId");
        //$state.go("index.content.ales/etl/etlDailyEnforceLawlist")
    }

});
