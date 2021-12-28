var myapp=angular.module("myapp",[]).controller("subjSvView",function($scope,$http, $state, $stateParams){

    //获取本地存储的ID
    $scope.subjSvId=window.localStorage.getItem("subjSvId");

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSuperviseById",
        method:"post",
        data:{id:$scope.subjSvId}
    }).success(function(data){
        if(data.httpCode=="200") {
            $scope.subjSv=data.data;
            if($scope.subjSv.svLevel=="3"){
                $scope.subjSv.svLevel = "县级";
            }else if($scope.subjSv.svLevel=="2"){
                $scope.subjSv.svLevel = "市级";
            }else if($scope.subjSv.svLevel=="1"){
                $scope.subjSv.svLevel = "省级";
            }else if($scope.subjSv.svLevel=="0"){
                $scope.subjSv.svLevel = "部级";
            }
            $scope.subjSv.createTime=new Date(parseInt($scope.subjSv.createTime)+28800000).toISOString().slice(0,10);
            $scope.subjSv.svArea = $.fn.Cityname($scope.subjSv.svAreaId);
            $scope.checkData($scope.subjSv);
        }else{
            jBox.tip(data.msg, 'info');
        }
    }).error(function (data) {
        
    });
    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjSvId");
        
        if ($stateParams.source && $stateParams.source == 'sys') {
            $state.go("index.content.sys/user/subjSvList");
        }else{
            $state.go("index.content.asms/subjSv/subjSvList");
        }
    }
    //检查json对象值是否为空
    $scope.checkData = function(o){
        for(var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }

})