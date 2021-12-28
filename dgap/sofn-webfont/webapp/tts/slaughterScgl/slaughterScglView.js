var myapp=angular.module("myapp",[]).controller("slaughterScglView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_slaughterScgl_id");

    //获取测试信息
    $http({
        url:"/sofn-tts-web/ttsScltxxcjScgl/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
    	$scope.status = data.ttsScltxxcjScgl.status;
    	$scope.billStratrus = data.ttsScltxxcjScgl.billStratrus;
    	$scope.ip = data.ttsScltxxcjScgl.ip;
    	$scope.entityIdcode = data.ttsScltxxcjScgl.entityIdcode;
    	$scope.productInnerKey = data.ttsScltxxcjScgl.productInnerKey;
    	$scope.mediResult = data.ttsScltxxcjScgl.mediResult;
    	$scope.productSource = data.ttsScltxxcjScgl.productSource;
    	$scope.harvestBaseid = data.ttsScltxxcjScgl.harvestBaseid;
    	$scope.harvestBasename = data.ttsScltxxcjScgl.harvestBasename;
    	$scope.productName = data.ttsScltxxcjScgl.productName;
    	$scope.productId = data.ttsScltxxcjScgl.productId;
    	$scope.productSort = data.ttsScltxxcjScgl.productSort;
    	$scope.userIdcode = data.ttsScltxxcjScgl.userIdcode;
    	$scope.productIndustry = data.ttsScltxxcjScgl.productIndustry;
    	$scope.productAmount = data.ttsScltxxcjScgl.productAmount;
    	$scope.storeCount = data.ttsScltxxcjScgl.storeCount;
    	$scope.freezeCount = data.ttsScltxxcjScgl.freezeCount;
    	$scope.harvestUnit = data.ttsScltxxcjScgl.harvestUnit;
    	$scope.harvestTime = new Date(data.ttsScltxxcjScgl.harvestTime).format('yyyy-MM-dd');
    	$scope.productPc = data.ttsScltxxcjScgl.productPc;
    	$scope.mediCheck = data.ttsScltxxcjScgl.mediCheck;
    	$scope.checkImagename = data.ttsScltxxcjScgl.checkImagename;
    	$scope.checkImagepath = data.ttsScltxxcjScgl.checkImagepath;
    }).error(function(){
        alert('数据有误！');
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})