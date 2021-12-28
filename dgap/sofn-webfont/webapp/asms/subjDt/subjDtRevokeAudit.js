var myapp=angular.module("myapp",[]).controller("subjDtRevokeAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjDtRevokeId=window.localStorage.getItem("subjDtRevokeId");
    $('#revokeForm').bootstrapValidator();//初始化验证，必须有

    //获取撤销信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjDetection/findSubjDtRevokeById",
        method:"post",
        data:{id:$scope.subjDtRevokeId}
    }).success(function(data){
        $scope.subjDt=data.data.subjDt;
        if($scope.subjDt.dtLevel=="0"){
            $scope.subjDt.dtLevelName="部级";
        }else if($scope.subjDt.dtLevel=="1"){
            $scope.subjDt.dtLevelName="省级";
        }else if($scope.subjDt.dtLevel=="2"){
            $scope.subjDt.dtLevelName="市级";
        }else if($scope.subjDt.dtLevel=="3"){
            $scope.subjDt.dtLevelName="县级";
        }
        $scope.subjDtRevoke=data.data.subjDtRevoke;
        if($scope.subjDtRevoke.auditState=="1"||$scope.subjDtRevoke.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjDt.dtArea = $.fn.Cityname($scope.subjDt.dtAreaId);
    }).error(function (data) {
        
    });

    //审核备案撤销申请
    $scope.auditSubjDtRevoke=function(auditState){
        if (!$("#revokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjDtRevoke.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjDetection/auditSubjDtRevoke",
            method:"post",
            data:$scope.subjDtRevoke
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                setTimeout(function(){
                    $state.go("index.content.asms/subjDt/subjDtRevokeList");
                },500)
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(data){
        });
    }

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjDtRevokeId");
        $state.go("index.content.asms/subjDt/subjDtRevokeList");
    }
});