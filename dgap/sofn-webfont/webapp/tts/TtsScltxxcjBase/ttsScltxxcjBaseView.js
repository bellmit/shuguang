var myapp=angular.module("myapp",[]).controller("ttsScltxxcjBaseView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjBase_id");

    //获取测试信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-tts-web/ttsScltxxcjBase/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
        $("#pictureTwo").attr("src", data.ttsScltxxcjBase.pictureTwo);
    	$scope.name = data.ttsScltxxcjBase.name;
    	$scope.area = data.ttsScltxxcjBase.area;
    	$scope.address = data.ttsScltxxcjBase.address;
    	$scope.manager = data.ttsScltxxcjBase.manager;
    	$scope.phone = data.ttsScltxxcjBase.phone;
    	$scope.productNames = data.ttsScltxxcjBase.productNames;
    	$scope.longitude = data.ttsScltxxcjBase.longitude;
    	$scope.latitude = data.ttsScltxxcjBase.latitude;
        $("#picture").attr("src", data.ttsScltxxcjBase.picture);
    	$scope.picture = data.ttsScltxxcjBase.picture;
    	$scope.status = data.ttsScltxxcjBase.status;
    	$scope.ip = data.ttsScltxxcjBase.ip;
    })

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})