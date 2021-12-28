var myapp=angular.module("myapp",[]);
myapp.controller("punishCheckReport",function($scope,$http){
    $scope.ID=window.localStorage.getItem("ID");

    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-ales-web/administrativePenalty/findAdministrativePenaltyById",
        method:"post",
        data:{id:$scope.ID}
    }).success(function(data){
        $scope.reCord=data.produceAdminPunish;
		$http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-ales-web/administrativePenalty/findEnterpriseById",
			method:"post",
			data:data.produceAdminPunish.enterpriseId
		}).success(function(dataenterprise){
			$scope.enterprise=dataenterprise.enterprise.ENTERPRISE_NAME;
		}).error(function(data){
            console.log(data);
            
		});
        console.log($scope.reCord);
    }).
    error(function(){
    });
    //清楚缓存
    $scope.RemoveLocal=function(){
        window.localStorage.removeItem("xcPsersionId");
    }
    //导航展开
    $("#collapseThree").attr("class","panel-collapse collapse in");
    $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
   
});
