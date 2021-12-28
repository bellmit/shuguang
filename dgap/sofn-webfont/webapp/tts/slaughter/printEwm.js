var myapp=angular.module("myapp",[]).controller("printEwm",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")
    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjScgl_id");
    //获取测试信息
    $http({
        url:"/sofn-tts-web/ttsScltxxcjScgl/queryById",
        method:"post",
        data:{
            id:$scope.id
        }
    }).success(function(data){
        $scope.productName = data.ttsScltxxcjScgl.productName;
        $scope.productAmount = data.ttsScltxxcjScgl.productAmount;
        $scope.harvestUnit = data.ttsScltxxcjScgl.harvestUnit;
        $scope.harvestTime = new Date(data.ttsScltxxcjScgl.harvestTime).format('yyyy-MM-dd');
        $scope.productPc = data.ttsScltxxcjScgl.productPc;
        $scope.userIdcode = data.ttsScltxxcjScgl.userIdcode;
        //二维码生成
        $("#ewm").html("");
        $("#ewm").qrcode({
            width:200,
            height:200,
            correctLevel:0,
            text:data.ttsScltxxcjScgl.productPc
        });
    }).error(function(){
        alert('数据有误！');
    });
    //清除缓存
    $scope.removeLocalStorage=function () {
        window.localStorage.removeItem("id");
    }
})