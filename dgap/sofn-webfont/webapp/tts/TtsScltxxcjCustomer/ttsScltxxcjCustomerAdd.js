var myapp=angular.module("myapp",[]).controller("ttsScltxxcjCustomerAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //新增客户信息
	$scope.ttsScltxxcjCustomer = {};
    $scope.addTtsScltxxcjCustomer = function(){
    	 $scope.ttsScltxxcjCustomer.userIdCode = $("input[name='userIdCode']").val();
    	 $scope.ttsScltxxcjCustomer.customerEntityIdCode = $("input[name='customerEntityIdCode']").val();
    	 $scope.ttsScltxxcjCustomer.customerUserIdCode = $("input[name='customerUserIdCode']").val();
    	 $scope.ttsScltxxcjCustomer.ipAddress = $("input[name='ipAddress']").val();
    	 $scope.ttsScltxxcjCustomer.delflag = $("input[name='delflag']").val();
    	 $scope.ttsScltxxcjCustomer.createby = $("input[name='createby']").val();
    	 $scope.ttsScltxxcjCustomer.updateby = $("input[name='updateby']").val();
    	 $scope.ttsScltxxcjCustomer.updatetime = $("input[name='updatetime']").val();
    	 $scope.ttsScltxxcjCustomer.name = $("input[name='name']").val();
    	 $scope.ttsScltxxcjCustomer.type = $("input[name='type']").val();
    	 $scope.ttsScltxxcjCustomer.organizationType = $("input[name='organizationType']").val();
    	 $scope.ttsScltxxcjCustomer.legalRepresentative = $("input[name='legalRepresentative']").val();
    	 $scope.ttsScltxxcjCustomer.userName = $("input[name='userName']").val();
    	 $scope.ttsScltxxcjCustomer.idCardNo = $("input[name='idCardNo']").val();
    	 $scope.ttsScltxxcjCustomer.phone = $("input[name='phone']").val();
    	 $scope.ttsScltxxcjCustomer.email = $("input[name='email']").val();
    	 $scope.ttsScltxxcjCustomer.address = $("input[name='address']").val();
    	 $scope.ttsScltxxcjCustomer.status = $("input[name='status']").val();
    	 $scope.ttsScltxxcjCustomer.createtime = $("input[name='createtime']").val();
    	 $scope.ttsScltxxcjCustomer.entityIdCode = $("input[name='entityIdCode']").val();
    	 $scope.ttsScltxxcjCustomer.delFlag = $("input[name='delFlag']").val();
        $http({
            url:"/sofn-tts-web/ttsScltxxcjCustomer/addTtsScltxxcjCustomer?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
            data:$scope.ttsScltxxcjCustomer,
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
            $state.go("index.content.tts/TtsScltxxcjCustomer/TtsScltxxcjCustomerlist");
        }).error(function(){
			$.jBox.info("保存失败！", "提示", submit, { buttons: { '确认': true} });
        })
    }
})