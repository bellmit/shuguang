var myapp = angular.module("myapp", []);
myapp.controller("printEwm",function($scope,$http,$state){
    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjScgl_id");
    //获取测试信息
    $http({
        url:"/sofn-tts-web/ttsScltxxcjScgl/queryById",
        method:"post",
        header:{
            token : window.localStorage.getItem("token")
        },
        data:{
            id:$scope.id
        }
    }).success(function(data){
        $scope.productName = data.ttsScltxxcjScgl.productName;
        $scope.mediCheck = data.ttsScltxxcjScgl.mediCheck;
        $scope.mediResult = data.ttsScltxxcjScgl.mediResult;
        $scope.productAmount = data.ttsScltxxcjScgl.productAmount;
        $scope.harvestUnit = data.ttsScltxxcjScgl.harvestUnit;
        $scope.harvestTime = new Date(data.ttsScltxxcjScgl.harvestTime).format('yyyy-MM-dd');
        var source = data.ttsScltxxcjScgl.productSource;
        if(source == "采购"){
            $("#content").html("追溯码");
            $("#productPc").val(data.ttsScltxxcjScgl.fromzsm);
        }else{
            $("#content").html("批次码");
            $("#productPc").val(data.ttsScltxxcjScgl.productPc);
        }
        $scope.userIdcode = data.ttsScltxxcjScgl.userIdcode;
        //二维码生成
        $("#ewm").html("");
        $("#ewm").qrcode({
            width:250,
            height:250,
            correctLevel:0,
            text:$("#productPc").val()
        });
    }).error(function(){
        alert('数据有误！');
    });
    //清除缓存
    $scope.removeLocalStorage=function () {
        window.localStorage.removeItem("id");
    }
})