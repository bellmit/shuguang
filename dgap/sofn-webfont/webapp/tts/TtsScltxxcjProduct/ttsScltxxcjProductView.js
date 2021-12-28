var myapp=angular.module("myapp",[]).controller("ttsScltxxcjProductView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjProduct_id");

    //获取产品管理信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-tts-web/ttsScltxxcjProduct/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
    	$scope.industry = data.ttsScltxxcjProduct.industry;
    	$scope.name = data.ttsScltxxcjProduct.name;
    	$scope.type = data.ttsScltxxcjProduct.type;
    	$scope.model = data.ttsScltxxcjProduct.model;
    	$scope.picture = data.ttsScltxxcjProduct.picture;
    	$scope.status = data.ttsScltxxcjProduct.status;
    	$scope.selfCheck = data.ttsScltxxcjProduct.selfCheck;
    	$scope.authentication = data.ttsScltxxcjProduct.authentication;
    	$scope.ip = data.ttsScltxxcjProduct.ip;
    }).error(function(){
        $.messager.alert("提示","数据有误！");
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})