var myapp=angular.module("myapp",[]).controller("ttsScltxxcjProductTypeView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjProductType_id");

    //获取测试信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-tts-web/ttsScltxxcjProductType/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
    	$scope.name = data.ttsScltxxcjProductType.name;
    	$scope.status = data.ttsScltxxcjProductType.status;
    }).error(function(){
        $.messager.alert("提示","数据有误！");
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})