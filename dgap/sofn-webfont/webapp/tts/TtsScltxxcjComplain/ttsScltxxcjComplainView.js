var myapp=angular.module("myapp",[]).controller("ttsScltxxcjComplainView",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //获取本地存储的ID
    $scope.id=window.localStorage.getItem("tts_ttsScltxxcjComplain_id");

    //获取测试信息
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
		var map = $.parseJSON(window.localStorage.getItem('IssueType'));
		$("#title").html(data.ttsScltxxcjComplain.complaintTitle?data.ttsScltxxcjComplain.complaintTitle:"");
		$("#complaintEntName").html(data.ttsScltxxcjComplain.complaintEntName?data.ttsScltxxcjComplain.complaintEntName:"");
		$("#accTime").html(data.ttsScltxxcjComplain.createTime?new Date(data.ttsScltxxcjComplain.createTime).format('yyyy-MM-dd'):"");
		$("#type").html(data.ttsScltxxcjComplain.typeName?data.ttsScltxxcjComplain.typeName:"");
		$("#areaId").html(data.ttsScltxxcjComplain.areaId?$.fn.Cityname(data.ttsScltxxcjComplain.areaId):"");
		$('td').attr({"align":"center"});
    	$scope.entityIdcode = data.ttsScltxxcjComplain.entityIdcode;
    	$scope.userIdcode = data.ttsScltxxcjComplain.userIdcode;
    	$scope.beEntityIdcode = data.ttsScltxxcjComplain.beEntityIdcode;
    	$scope.beUserIdcode = data.ttsScltxxcjComplain.beUserIdcode;
    	$scope.accEntityIdcode = data.ttsScltxxcjComplain.accEntityIdcode;
    	$scope.accUserIdcode = data.ttsScltxxcjComplain.accUserIdcode;
    	$scope.complaintTime = new Date(data.ttsScltxxcjComplain.complaintTime).format('yyyy-MM-dd');
    	$scope.complaintTitle = data.ttsScltxxcjComplain.complaintTitle;
    	$scope.content = data.ttsScltxxcjComplain.content;
    	$scope.type = map?map[data.ttsScltxxcjComplain.type]:data.ttsScltxxcjComplain.type;
    	$scope.status = data.ttsScltxxcjComplain.status==1?"受理中":"未受理";
    	$scope.acceptance = data.ttsScltxxcjComplain.acceptance;
    	$scope.areaId = data.ttsScltxxcjComplain.areaId;
    	//$scope.accTime = new Date(data.ttsScltxxcjComplain.accTime).format('yyyy-MM-dd');
    	$scope.attachmentName = data.ttsScltxxcjComplain.attachmentName;
    	$scope.complaintEntName = data.ttsScltxxcjComplain.complaintEntName;
    	$scope.attachmentPath = data.ttsScltxxcjComplain.attachmentPath;
    	$scope.complaintCopName = data.ttsScltxxcjComplain.complaintCopName;

    }).error(function(){
        alert('数据有误！');
    });


    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("id");
    }

})