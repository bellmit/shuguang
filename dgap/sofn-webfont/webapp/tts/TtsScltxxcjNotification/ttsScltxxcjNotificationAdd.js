var myapp=angular.module("myapp",[]).controller("ttsScltxxcjNotificationAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    $scope.addTtsScltxxcjNotification = function(){
         $scope.ttsScltxxcjNotification = {};
    	 $scope.ttsScltxxcjNotification.beEntityIdcode = $("input[name='beEntityIdcode']").val();
    	 $scope.ttsScltxxcjNotification.beUserIdcode = $("input[name='beUserIdcode']").val();
    	 $scope.ttsScltxxcjNotification.entityIdcode = $("input[name='entityIdcode']").val();
    	 $scope.ttsScltxxcjNotification.userIdcode = $("input[name='userIdcode']").val();
    	 $scope.ttsScltxxcjNotification.title = $("input[name='title']").val();
    	 $scope.ttsScltxxcjNotification.content = $("input[name='content']").val();
    	 $scope.ttsScltxxcjNotification.createTime = $("input[name='createTime']").val();
    	 $scope.ttsScltxxcjNotification.updateTime = $("input[name='updateTime']").val();
    	 $scope.ttsScltxxcjNotification.delFlag = $("input[name='delFlag']").val();
        $http({
            url:"/sofn-tts-web/ttsScltxxcjNotification/addTtsScltxxcjNotification?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
            data:$scope.ttsScltxxcjNotification,
            method:"post",
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("保存成功", 'info');
            }else{
                jBox.tip("保存失败", 'error');
            }
            $state.go("index.content.tts/TtsScltxxcjNotification/TtsScltxxcjNotificationlist");
        }).error(function(){
            jBox.tip("保存失败", 'error');
        })
    }
})