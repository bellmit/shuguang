//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjNotificationUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjNotification_id");
		$http({
			headers:{
				token:window.localStorage.getItem("token")
			},
			url:"/sofn-tts-web/ttsScltxxcjNotification/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
	    	$("input[name='beEntityIdcode']").val(data.ttsScltxxcjNotification.beEntityIdcode);
	    	$("input[name='beUserIdcode']").val(data.ttsScltxxcjNotification.beUserIdcode);
	    	$("input[name='entityIdcode']").val(data.ttsScltxxcjNotification.entityIdcode);
	    	$("input[name='userIdcode']").val(data.ttsScltxxcjNotification.userIdcode);
	    	$("input[name='title']").val(data.ttsScltxxcjNotification.title);
	    	$("input[name='content']").val(data.ttsScltxxcjNotification.content);
	    	$("input[name='createDate']").val(new Date(data.ttsScltxxcjNotification.createDate).format('yyyy-MM-dd'));
	    	$("input[name='updateDate']").val(new Date(data.ttsScltxxcjNotification.updateDate).format('yyyy-MM-dd'));
	    	//public filed
	    	$("input[name='delFlag']").val(data.ttsScltxxcjNotification.delFlag);
	    	$("input[name='createBy']").val(data.ttsScltxxcjNotification.createBy);
	    	$("input[name='createTime']").val(data.ttsScltxxcjNotification.createTime);
		});
		//
        $scope.updateTtsScltxxcjNotification = function(){
			 $scope.ttsScltxxcjNotification = {};
	    	 $scope.ttsScltxxcjNotification.beEntityIdcode = $("input[name='beEntityIdcode']").val();
	    	 $scope.ttsScltxxcjNotification.beUserIdcode = $("input[name='beUserIdcode']").val();
	    	 $scope.ttsScltxxcjNotification.entityIdcode = $("input[name='entityIdcode']").val();
	    	 $scope.ttsScltxxcjNotification.userIdcode = $("input[name='userIdcode']").val();
	    	 $scope.ttsScltxxcjNotification.title = $("input[name='title']").val();
	    	 $scope.ttsScltxxcjNotification.content = $("input[name='content']").val();
	    	 $scope.ttsScltxxcjNotification.createDate = $("input[name='createDate']").val();
	    	 $scope.ttsScltxxcjNotification.updateDate = $("input[name='updateDate']").val();
	    	 $scope.ttsScltxxcjNotification.delFlag = $("input[name='delFlag']").val();
	    	 $scope.ttsScltxxcjNotification.createBy = $("input[name='createBy']").val();
	    	 $scope.ttsScltxxcjNotification.createTime = $("input[name='createTime']").val();
	    	 $scope.ttsScltxxcjNotification.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjNotification/updateTtsScltxxcjNotification?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
                    data:$scope.ttsScltxxcjNotification,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
				if(data.httpCode==200){
					jBox.tip("修改成功", 'info');
				}else{
					jBox.tip("修改失败", 'error');
				}
				$state.go("index.content.tts/ttsScltxxcjNotification/TtsScltxxcjNotificationlist");
			}).error(function(){
				jBox.tip("修改失败", 'error');
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};
    });


