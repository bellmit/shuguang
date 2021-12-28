var myapp=angular.module("myapp",[]).controller("sysSubjDtView",function($scope,$http,$state, $stateParams){

    //获取本地存储的ID
    $scope.subjDtId=window.localStorage.getItem("subjDtId");

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjDetection/findSubjDetectionById",
        method:"post",
        data:{id:$scope.subjDtId}
    }).success(function(data){
        if(data.httpCode=="200") {
            $scope.subjDt=data.data;
            if($scope.subjDt.dtLevel=="3"){
                $scope.subjDt.dtLevel = "县级";
            }else if($scope.subjDt.dtLevel=="2"){
                $scope.subjDt.dtLevel = "市级";
            }else if($scope.subjDt.dtLevel=="1"){
                $scope.subjDt.dtLevel = "省级";
            }else if($scope.subjDt.dtLevel=="0"){
                $scope.subjDt.dtLevel = "部级";
            }
            $scope.subjDt.createTime=new Date(parseInt($scope.subjDt.createTime)+28800000).toISOString().slice(0,10);
        }else{
            jBox.tip(data.msg, 'info');
        }
    }).error(function(){
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjDtId");
        $state.go("index.content.sys/user/sysSubjDtList");
    }

})