var myapp=angular.module("myapp",[]).controller("ttsScltxxcjNotificationView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjNotification_id");

    //获取测试信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-tts-web/ttsScltxxcjNotification/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
    	$scope.beEntityIdcode = data.ttsScltxxcjNotification.beEntityIdcode;
    	$scope.beUserIdcode = data.ttsScltxxcjNotification.beUserIdcode;
    	$scope.entityIdcode = data.ttsScltxxcjNotification.entityIdcode;
    	$scope.userIdcode = data.ttsScltxxcjNotification.userIdcode;
    	$scope.title = data.ttsScltxxcjNotification.title;
    	$scope.content = data.ttsScltxxcjNotification.content;
    	$scope.createTime = new Date(data.ttsScltxxcjNotification.createTime).format('yyyy-MM-dd');
    	$scope.updateTime = new Date(data.ttsScltxxcjNotification.updateTime).format('yyyy-MM-dd');
    }).error(function(){
        alert('数据有误！');
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})