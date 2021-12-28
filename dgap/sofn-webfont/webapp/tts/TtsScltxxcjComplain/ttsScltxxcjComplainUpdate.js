//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjComplainUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjComplain_id");
		$http({
			headers:{
				token:window.localStorage.getItem("token")
			},
			url:"/sofn-tts-web/ttsScltxxcjComplain/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
	    	$("input[name='entityIdcode']").val(data.ttsScltxxcjComplain.entityIdcode);
	    	$("input[name='userIdcode']").val(data.ttsScltxxcjComplain.userIdcode);
	    	$("input[name='beEntityIdcode']").val(data.ttsScltxxcjComplain.beEntityIdcode);
	    	$("input[name='beUserIdcode']").val(data.ttsScltxxcjComplain.beUserIdcode);
	    	$("input[name='accEntityIdcode']").val(data.ttsScltxxcjComplain.accEntityIdcode);
	    	$("input[name='accUserIdcode']").val(data.ttsScltxxcjComplain.accUserIdcode);
	    	$("input[name='complaintTime']").val(new Date(data.ttsScltxxcjComplain.complaintTime).format('yyyy-MM-dd'));
	    	$("input[name='complaintTitle']").val(data.ttsScltxxcjComplain.complaintTitle);
	    	$("input[name='content']").val(data.ttsScltxxcjComplain.content);
	    	$("input[name='type']").val(data.ttsScltxxcjComplain.type);
	    	$("input[name='status']").val(data.ttsScltxxcjComplain.status);
	    	$("input[name='acceptance']").val(data.ttsScltxxcjComplain.acceptance);
	    	$("input[name='areaId']").val(data.ttsScltxxcjComplain.areaId);
	    	$("input[name='accTime']").val(new Date(data.ttsScltxxcjComplain.accTime).format('yyyy-MM-dd'));
	    	$("input[name='attachmentName']").val(data.ttsScltxxcjComplain.attachmentName);
	    	$("input[name='complaintEntName']").val(data.ttsScltxxcjComplain.complaintEntName);
	    	$("input[name='attachmentPath']").val(data.ttsScltxxcjComplain.attachmentPath);
	    	$("input[name='complaintCopName']").val(data.ttsScltxxcjComplain.complaintCopName);
	    	//public filed
	    	$("input[name='delFlag']").val(data.ttsScltxxcjComplain.delFlag);
	    	$("input[name='createBy']").val(data.ttsScltxxcjComplain.createBy);
	    	$("input[name='createTime']").val(data.ttsScltxxcjComplain.createTime);
		});
		//
        $scope.updateTtsScltxxcjComplain = function(){
	    	 $scope.ttsScltxxcjComplain.entityIdcode = $("input[name='entityIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.userIdcode = $("input[name='userIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.beEntityIdcode = $("input[name='beEntityIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.beUserIdcode = $("input[name='beUserIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.accEntityIdcode = $("input[name='accEntityIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.accUserIdcode = $("input[name='accUserIdcode']").val();
	    	 $scope.ttsScltxxcjComplain.complaintTime = $("input[name='complaintTime']").val();
	    	 $scope.ttsScltxxcjComplain.complaintTitle = $("input[name='complaintTitle']").val();
	    	 $scope.ttsScltxxcjComplain.content = $("input[name='content']").val();
	    	 $scope.ttsScltxxcjComplain.type = $("input[name='type']").val();
	    	 $scope.ttsScltxxcjComplain.status = $("input[name='status']").val();
	    	 $scope.ttsScltxxcjComplain.acceptance = $("input[name='acceptance']").val();
	    	 $scope.ttsScltxxcjComplain.areaId = $("input[name='areaId']").val();
	    	 $scope.ttsScltxxcjComplain.accTime = $("input[name='accTime']").val();
	    	 $scope.ttsScltxxcjComplain.attachmentName = $("input[name='attachmentName']").val();
	    	 $scope.ttsScltxxcjComplain.complaintEntName = $("input[name='complaintEntName']").val();
	    	 $scope.ttsScltxxcjComplain.attachmentPath = $("input[name='attachmentPath']").val();
	    	 $scope.ttsScltxxcjComplain.complaintCopName = $("input[name='complaintCopName']").val();
	    	 $scope.ttsScltxxcjComplain.delFlag = $("input[name='delFlag']").val();
	    	 $scope.ttsScltxxcjComplain.createBy = $("input[name='createBy']").val();
	    	 $scope.ttsScltxxcjComplain.createTime = $("input[name='createTime']").val();
	    	 $scope.ttsScltxxcjComplain.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjComplain/updateTtsScltxxcjComplain",
                    data:$scope.ttsScltxxcjComplain,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
                alert("修改成功");
                $state.go("index.content.tts/TtsScltxxcjComplain/TtsScltxxcjComplainlist");
            }).
            error(function(){
				alert("修改失败");
            })
        };
		//清楚缓存
		$scope.RemoveLocal=function(){
			window.localStorage.removeItem("id");
		};
    });


