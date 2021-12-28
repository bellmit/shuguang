var myapp=angular.module("myapp",[]);
myapp.controller("checkSubjEnChange",function($scope,$http){
	//获取本地存储ID
    $scope.subjElChangeId=window.localStorage.getItem("subjElChangeId");

	//获取变更详情
	$http({
		headers:{
			token:window.localStorage.getItem("token")
		},
		url:"/sofn-ales-web/subjEnforceLaw/findSubjElChangeById",
		method:"post",
		data:{id:$scope.subjElChangeId}
	}).success(function(data){
		//得到变更前信息
		$scope.before=data.subjEl;
		//得到变更后信息
		$scope.after=data.subjElChange;
		if($scope.after.elLeaderPhone==null||$scope.after.elLeaderPhone==undefined){
			$scope.after.elLeaderPhone="";
		}
		if($scope.after.elContactQQ==null||$scope.after.elContactQQ==undefined){
			$scope.after.elContactQQ="";
		}
		if($scope.after.elContactEmail==null||$scope.after.elContactEmail==undefined){
			$scope.after.elContactEmail="";
		}
		if($scope.after.elPostcode==null||$scope.after.elPostcode==undefined){
			$scope.after.elPostcode="";
		}
		if($scope.after.elUnitNature==null||$scope.after.elUnitNature==undefined){
			$scope.after.elUnitNature="";
		}
		if($scope.after.elWorkBody==null||$scope.after.elWorkBody==undefined){
			$scope.after.elWorkBody="";
		}
		if($scope.after.elPeopleNum==null||$scope.after.elPeopleNum==undefined){
			$scope.after.elPeopleNum="";
		}
		if($scope.before.elLevel=="0"){
			$scope.before.elLevel="部级";
		}else if($scope.before.elLevel=="1"){
			$scope.before.elLevel="省级";
		}else if($scope.before.elLevel=="2"){
			$scope.before.elLevel="市级";
		}else if($scope.subjEl.elLevel=="3"){
			$scope.before.elLevel="县级";
		}
		if($scope.after.elLevel=="0"){
			$scope.after.elLevel="部级";
		}else if($scope.after.elLevel=="1"){
			$scope.after.elLevel="省级";
		}else if($scope.after.elLevel=="2"){
			$scope.after.elLevel="市级";
		}else if($scope.after.elLevel=="3"){
			$scope.after.elLevel="县级";
		}
		if(data.subjElChange.auditState=="2"){
			$scope.after.auditState="未通过";
		}else if(data.subjElChange.auditState=="1"){
			$scope.after.auditState="已通过";
		}else{
			$scope.after.auditState="待审核";
		}

	}).error(function(data){
	});
});
