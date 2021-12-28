var myapp=angular.module("myapp",[]).controller("demoAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //新增测试信息
    $scope.addDemoInfo=function(){
    	 $scope.demo.name=$("input[name='name']").val();
         $scope.demo.tel=$("input[name='tel']").val();
         $scope.demo.cjrq=$("input[name='cjrq']").val();
        $http({
            url:"/sofn-tts-web/testDemo/addTempDemo",
            data:$scope.demo,
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(data){
            alert("保存成功");
            $state.go("index.content.tts/demo/demo");
        }).error(function(){
            alert("保存失败");
        })
    }
})