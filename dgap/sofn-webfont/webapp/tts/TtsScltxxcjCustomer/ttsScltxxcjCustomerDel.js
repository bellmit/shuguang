var myapp=angular.module("myapp",[]).controller("ttsScltxxcjCustomerDel",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjCustomer_id");

    //获取测试信息
    $http({
		headers:{
			token:window.localStorage.getItem("token")
		},
        url:"/sofn-tts-web/ttsScltxxcjCustomer/queryById",
        method:"post",
        data:{
        		id:$scope.id
        	}
    }).success(function(data){
		$scope.id = data.ttsScltxxcjCustomer.id;
    	$scope.userIdCode = data.ttsScltxxcjCustomer.userIdCode;
    	$scope.customerEntityIdCode = data.ttsScltxxcjCustomer.customerEntityIdCode;
    	$scope.customerUserIdCode = data.ttsScltxxcjCustomer.customerUserIdCode;
    	$scope.ipAddress = data.ttsScltxxcjCustomer.ipAddress;
    	$scope.delflag = data.ttsScltxxcjCustomer.delflag;
    	$scope.createby = data.ttsScltxxcjCustomer.createby;
    	$scope.updateby = new Date(data.ttsScltxxcjCustomer.updateby).format('yyyy-MM-dd');
    	$scope.updatetime = new Date(data.ttsScltxxcjCustomer.updatetime).format('yyyy-MM-dd');
    	$scope.name = data.ttsScltxxcjCustomer.name;
    	$scope.type = data.ttsScltxxcjCustomer.type;
    	$scope.organizationType = data.ttsScltxxcjCustomer.organizationType;
    	$scope.legalRepresentative = data.ttsScltxxcjCustomer.legalRepresentative;
    	$scope.userName = data.ttsScltxxcjCustomer.userName;
    	$scope.idCardNo = data.ttsScltxxcjCustomer.idCardNo;
    	$scope.phone = data.ttsScltxxcjCustomer.phone;
    	$scope.email = data.ttsScltxxcjCustomer.email;
    	$scope.address = data.ttsScltxxcjCustomer.address;
    	$scope.status = data.ttsScltxxcjCustomer.status;
    	$scope.createtime = new Date(data.ttsScltxxcjCustomer.createtime).format('yyyy-MM-dd');
    	$scope.entityIdCode = data.ttsScltxxcjCustomer.entityIdCode;
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

	/**
	 * 执行删除的方法
	 */
	$scope.deleteData = function(){
		//选择是否进行删除
		var submit = function (v, h, f) {
			if (v == true){
				$.ajax({
					headers:{
						token:window.localStorage.getItem("token")
					},
					url:"/sofn-tts-web/ttsScltxxcjCustomer/deleteTtsScltxxcjCustomer",
					type:"post",
					dataType:"json",
					data:{id: $("#customerId").val()},
					success:function(data){
						if(data.httpCode!=200){
							jBox.tip("删除失败！", 'error');
						}else{
							jBox.tip("删除成功！", 'info');
						}
						$state.go("index.content.tts/TtsScltxxcjCustomer/TtsScltxxcjCustomerlist");
					}
				});
			}
			return true;
		};
		// 自定义按钮
		$.jBox.confirm("确认删除吗？", "删除提示", submit, { buttons: { '确认': true, '取消': false} });
	};

})