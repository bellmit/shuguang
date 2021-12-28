var myapp=angular.module("myapp",[]);
myapp.controller("checkSubjEntChange",function($scope,$http){
    $scope.subjSvChangeId=window.localStorage.getItem("subjSvChangeId");

	//获取变更详情
	$http({
		headers:{
			token:window.localStorage.getItem("token")
		},
		url:"/sofn-asms-web/subjSupervise/findSubjSvChangeById",
		method:"post",
		data:{id:$scope.subjSvChangeId}
	}).success(function(data){
		//得到变更前信息
		$scope.before=data.subjSv;
		//得到变更后信息
		$scope.after=data.subjSvChange;//得到变更后信息
		//对几个非必填项进行null处理
		if($scope.after.svLeaderPhone==null||$scope.after.svLeaderPhone==undefined){
			$scope.after.svLeaderPhone = "";
		}
		if($scope.after.svContactQQ==null||$scope.after.svContactQQ==undefined){
			$scope.after.svContactQQ = "";
		}
		if($scope.after.svContactEmail==null||$scope.after.svContactEmail==undefined){
			$scope.after.svContactEmail = "";
		}
		if($scope.after.svPostcode==null||$scope.after.svPostcode==undefined){
			$scope.after.svPostcode = "";
		}
		if($scope.before.svLevel=="0"){
			$scope.before.svLevel="部级";
		}else if($scope.before.svLevel=="1"){
			$scope.before.svLevel="省级";
		}else if($scope.before.svLevel=="2"){
			$scope.before.svLevel="市级";
		}else if($scope.subjEl.svLevel=="3"){
			$scope.before.svLevel="县级";
		}
		if($scope.after.svLevel=="0"){
			$scope.after.svLevel="部级";
		}else if($scope.after.svLevel=="1"){
			$scope.after.svLevel="省级";
		}else if($scope.after.svLevel=="2"){
			$scope.after.svLevel="市级";
		}else if($scope.after.svLevel=="3"){
			$scope.after.svLevel="县级";
		}
		if(data.subjSvChange.auditState=="2"){
			$scope.after.auditState="未通过";
		}else if(data.subjSvChange.auditState=="1"){
			$scope.after.auditState="已通过";
		}else{
			$scope.after.auditState="待审核";
		}
	}).error(function(){
	});

});
