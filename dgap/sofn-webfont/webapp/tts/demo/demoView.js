var myapp=angular.module("myapp",[]).controller("demoView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("id");

    //获取测试信息
    $http({
        url:"/sofn-tts-web/testDemo/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
            $scope.name = data.demo.name;
            $scope.tel = data.demo.tel;
            $scope.cjrq = new Date(data.demo.cjrq).format('yyyy-MM-dd');
    }).error(function(){
        alert('数据有误！');
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})