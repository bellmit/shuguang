var myapp=angular.module("myapp",[]).controller("subjDtRevokeApply",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjDtId=window.localStorage.getItem("subjDtId");
    $('#applyRevokeForm').bootstrapValidator();//初始化验证，必须有

    //获取机构信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjDetection/findSubjDetectionById",
        method:"post",
        data:{id:$scope.subjDtId}
    }).success(function(data){
        $scope.subjDt=data.data;
        if($scope.subjDt.dtLevel=="0"){
            $scope.subjDt.dtLevelName="部级";
        }else if($scope.subjDt.dtLevel=="1"){
            $scope.subjDt.dtLevelName="省级";
        }else if($scope.subjDt.dtLevel=="2"){
            $scope.subjDt.dtLevelName="市级";
        }else if($scope.subjDt.dtLevel=="3"){
            $scope.subjDt.dtLevelName="县级";
        }
        $scope.subjDt.dtArea = $.fn.Cityname($scope.subjDt.dtAreaId);
        $scope.subjDt.createTime=new Date(parseInt($scope.subjDt.createTime)+28800000).toISOString().slice(0,10);
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjDtId");
        $state.go("index.content.asms/subjDt/subjDtList");
    }

    //撤销申请
    $scope.applySubjDtRevoke=function(){
        if (!$("#applyRevokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjDetection/addSubjDtRevoke",
            method:"post",
            data:{dtId:$scope.subjDtId,applyReason:$scope.applyReason}
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("申请成功", 'info');
                window.localStorage.removeItem("subjDtId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjDt/subjDtList");
                },500)
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(data){
            $("button").attr("disabled",false);
            jBox.tip("申请失败", 'info');
        });
    }

})