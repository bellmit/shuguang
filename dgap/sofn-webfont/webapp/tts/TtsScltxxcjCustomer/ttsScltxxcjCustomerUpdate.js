//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjCustomerUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjCustomer_id");
		$scope.ttsScltxxcjCustomer = {};
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
	    	$("input[name='userIdCode']").val(data.ttsScltxxcjCustomer.userIdCode);
	    	$("input[name='customerEntityIdCode']").val(data.ttsScltxxcjCustomer.customerEntityIdCode);
	    	$("input[name='customerUserIdCode']").val(data.ttsScltxxcjCustomer.customerUserIdCode);
	    	$("input[name='ipAddress']").val(data.ttsScltxxcjCustomer.ipAddress);
	    	$("input[name='delflag']").val(data.ttsScltxxcjCustomer.delflag);
	    	$("input[name='createby']").val(data.ttsScltxxcjCustomer.createby);
	    	$("input[name='updateby']").val(new Date(data.ttsScltxxcjCustomer.updateby).format('yyyy-MM-dd'));
	    	$("input[name='updatetime']").val(new Date(data.ttsScltxxcjCustomer.updatetime).format('yyyy-MM-dd'));
	    	$("input[name='name']").val(data.ttsScltxxcjCustomer.name);
	    	$("input[name='type']").val(data.ttsScltxxcjCustomer.type);
	    	$("input[name='organizationType']").val(data.ttsScltxxcjCustomer.organizationType);
	    	$("input[name='legalRepresentative']").val(data.ttsScltxxcjCustomer.legalRepresentative);
	    	$("input[name='userName']").val(data.ttsScltxxcjCustomer.userName);
	    	$("input[name='idCardNo']").val(data.ttsScltxxcjCustomer.idCardNo);
	    	$("input[name='phone']").val(data.ttsScltxxcjCustomer.phone);
	    	$("input[name='email']").val(data.ttsScltxxcjCustomer.email);
	    	$("input[name='address']").val(data.ttsScltxxcjCustomer.address);
	    	$("input[name='status']").val(data.ttsScltxxcjCustomer.status);
	    	$("input[name='createtime']").val(new Date(data.ttsScltxxcjCustomer.createtime).format('yyyy-MM-dd'));
	    	$("input[name='entityIdCode']").val(data.ttsScltxxcjCustomer.entityIdCode);
	    	//public filed
	    	$("input[name='delFlag']").val(data.ttsScltxxcjCustomer.delFlag);
	    	$("input[name='createBy']").val(data.ttsScltxxcjCustomer.createBy);
	    	$("input[name='createTime']").val(data.ttsScltxxcjCustomer.createTime);
		});
		//
        $scope.updateTtsScltxxcjCustomer = function(){
			 var submit = null;
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
	    	 $scope.ttsScltxxcjCustomer.createBy = $("input[name='createBy']").val();
	    	 $scope.ttsScltxxcjCustomer.createTime = $("input[name='createTime']").val();
	    	 $scope.ttsScltxxcjCustomer.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjCustomer/updateTtsScltxxcjCustomer?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
                    data:$scope.ttsScltxxcjCustomer,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
				$.jBox.info("修改成功！", "提示", submit, { buttons: { '确认': true} });
                $state.go("index.content.tts/TtsScltxxcjCustomer/TtsScltxxcjCustomerlist");
            }).
            error(function(){
				$.jBox.info("修改失败！", "提示", submit, { buttons: { '确认': true} });
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};
    });


