//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjProductTypeUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjProductType_id");
		$http({
			headers:{
				token:window.localStorage.getItem("token")
			},
			url:"/sofn-tts-web/ttsScltxxcjProductType/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
	    	$("input[name='name']").val(data.ttsScltxxcjProductType.name);
	    	$("input[name='status']").val(data.ttsScltxxcjProductType.status);
	    	//public filed
	    	$("input[name='delFlag']").val(data.ttsScltxxcjProductType.delFlag);
	    	$("input[name='createBy']").val(data.ttsScltxxcjProductType.createBy);
	    	$("input[name='createTime']").val(data.ttsScltxxcjProductType.createTime);
		});
		//
        $scope.updateTtsScltxxcjProductType = function(){
			var submit = null;
	    	 $scope.ttsScltxxcjProductType.name = $("input[name='name']").val();
	    	 $scope.ttsScltxxcjProductType.status = $("input[name='status']").val();
	    	 $scope.ttsScltxxcjProductType.delFlag = $("input[name='delFlag']").val();
	    	 $scope.ttsScltxxcjProductType.createBy = $("input[name='createBy']").val();
	    	 $scope.ttsScltxxcjProductType.createTime = $("input[name='createTime']").val();
	    	 $scope.ttsScltxxcjProductType.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjProductType/updateTtsScltxxcjProductType?id="+$.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
                    data:$scope.ttsScltxxcjProductType,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
					if(httpCode==200){
						jBox.tip("保存成功", 'info');
					}else{
						jBox.tip("保存失败", 'error');
					}
                $state.go("index.content.tts/ttsScltxxcjProductType/TtsScltxxcjProductTypelist");
            }).
            error(function(){
				jBox.tip("保存失败", 'error');
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};
    });


