//    angularjs数据传输
var myapp=angular.module("myapp",[]);
var mytype="";
var myend="";
    myapp.controller("ttsScltxxcjProposalUpdate",function($scope,$http,$state){
		$scope.id=window.localStorage.getItem("tts_ttsScltxxcjProposal_id");
		$http({
			headers:{
				token:window.localStorage.getItem("token")
			},
			url:"/sofn-tts-web/ttsScltxxcjProposal/queryById",
			method:"post",
			data:{
				id:$scope.id
			}
		}).success(function(data){
	    	$("input[name='title']").val(data.ttsScltxxcjProposal.title);
	    	$("input[name='content']").val(data.ttsScltxxcjProposal.content);
	    	$("input[name='time']").val(new Date(data.ttsScltxxcjProposal.time).format('yyyy-MM-dd'));
	    	$("input[name='attachName']").val(data.ttsScltxxcjProposal.attachName);
	    	$("input[name='attachPath']").val(data.ttsScltxxcjProposal.attachPath);
	    	$("input[name='entityIdcode']").val(data.ttsScltxxcjProposal.entityIdcode);
	    	$("input[name='userIdcode']").val(data.ttsScltxxcjProposal.userIdcode);
	    	$("input[name='ip']").val(data.ttsScltxxcjProposal.ip);
		});
		//
        $scope.updateTtsScltxxcjProposal = function(){
        	$scope.ttsScltxxcjProposal.title = $("input[name='title']").val();
        	$scope.ttsScltxxcjProposal.content = $("input[name='content']").val();
        	$scope.ttsScltxxcjProposal.time = $("input[name='time']").val();
        	$scope.ttsScltxxcjProposal.attachName = $("input[name='attachName']").val();
        	$scope.ttsScltxxcjProposal.attachPath = $("input[name='attachPath']").val();
        	$scope.ttsScltxxcjProposal.entityIdcode = $("input[name='entityIdcode']").val();
        	$scope.ttsScltxxcjProposal.userIdcode = $("input[name='userIdcode']").val();
        	$scope.ttsScltxxcjProposal.ip = $("input[name='ip']").val();
        	$scope.ttsScltxxcjProposal.delFlag = $("input[name='delFlag']").val();
        	$scope.ttsScltxxcjProposal.createBy = $("input[name='createBy']").val();
        	$scope.ttsScltxxcjProposal.updateBy = $("input[name='updateBy']").val();
        	$scope.ttsScltxxcjProposal.createTime = $("input[name='createTime']").val();
        	$scope.ttsScltxxcjProposal.updateTime = $("input[name='updateTime']").val();
        	$scope.ttsScltxxcjProposal.reservedField1 = $("input[name='reservedField1']").val();
        	$scope.ttsScltxxcjProposal.reservedField2 = $("input[name='reservedField2']").val();
        	$scope.ttsScltxxcjProposal.reservedField3 = $("input[name='reservedField3']").val();
        	$scope.ttsScltxxcjProposal.reservedField4 = $("input[name='reservedField4']").val();
        	$scope.ttsScltxxcjProposal.reservedField5 = $("input[name='reservedField5']").val();
        	$scope.ttsScltxxcjProposal.reservedField6 = $("input[name='reservedField6']").val();
        	$scope.ttsScltxxcjProposal.reservedField7 = $("input[name='reservedField7']").val();
        	$scope.ttsScltxxcjProposal.reservedField8 = $("input[name='reservedField8']").val();
        	$scope.ttsScltxxcjProposal.reservedField9 = $("input[name='reservedField9']").val();
        	$scope.ttsScltxxcjProposal.reservedField10 = $("input[name='reservedField10']").val();
        	$scope.ttsScltxxcjProposal.reservedField11 = $("input[name='reservedField11']").val();
        	$scope.ttsScltxxcjProposal.reservedField12 = $("input[name='reservedField12']").val();
        	$scope.ttsScltxxcjProposal.reservedField13 = $("input[name='reservedField13']").val();
        	$scope.ttsScltxxcjProposal.reservedField14 = $("input[name='reservedField14']").val();
        	$scope.ttsScltxxcjProposal.reservedField15 = $("input[name='reservedField15']").val();
        	$scope.ttsScltxxcjProposal.reservedField16 = $("input[name='reservedField16']").val();
        	$scope.ttsScltxxcjProposal.reservedField17 = $("input[name='reservedField17']").val();
        	$scope.ttsScltxxcjProposal.reservedField18 = $("input[name='reservedField18']").val();
        	$scope.ttsScltxxcjProposal.reservedField19 = $("input[name='reservedField19']").val();
        	$scope.ttsScltxxcjProposal.reservedField20 = $("input[name='reservedField20']").val();
        	$scope.ttsScltxxcjProposal.id = $scope.id;
            $http({
                    url:"/sofn-tts-web/ttsScltxxcjProposal/updateTtsScltxxcjProposal",
                    data:$scope.ttsScltxxcjProposal,
                    method:"post",
                    //post传输方式   头信息
					headers:{
						token:window.localStorage.getItem("token")
					}
                }).success(function(data){
                alert("修改成功");
                $state.go("index.content.tts/TtsScltxxcjProposal/TtsScltxxcjProposallist");
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


