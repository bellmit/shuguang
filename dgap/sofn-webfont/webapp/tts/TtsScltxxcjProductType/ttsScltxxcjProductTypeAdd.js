var myapp=angular.module("myapp",[]).controller("ttsScltxxcjProductTypeAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    $scope.addTtsScltxxcjProductType = function(){
        var submit = null;
    	 $scope.ttsScltxxcjProductType.name = $("input[name='name']").val();
    	 $scope.ttsScltxxcjProductType.status = $("input[name='status']").val();
    	 $scope.ttsScltxxcjProductType.delFlag = $("input[name='delFlag']").val();
        $http({
            url:"/sofn-tts-web/ttsScltxxcjProductType/addTtsScltxxcjProductType?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
            data:$scope.ttsScltxxcjProductType,
            method:"post",
            headers:{
                token:window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(httpCode==200){
                jBox.tip("保存成功", 'info');
            }else{
                jBox.tip("保存失败", 'info');
            }
            $state.go("index.content.tts/ttsScltxxcjProductType/TtsScltxxcjProductTypelist");
        }).error(function(){
            jBox.tip("保存失败", 'error');
        })
    }
})