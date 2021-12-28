var myapp=angular.module("myapp",[]).controller("subjSvRevokeAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjSvRevokeId=window.localStorage.getItem("subjSvRevokeId");
    $('#revokeForm').bootstrapValidator();//初始化验证，必须有

    //获取撤销信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSvRevokeById",
        method:"post",
        data:{id:$scope.subjSvRevokeId}
    }).success(function(data){
        $scope.subjSv=data.subjSv;
        $scope.subjSvRevoke=data.subjSvRevoke;
        if($scope.subjSv.svLevel=="0"){
            $scope.subjSv.svLevelName="部级";
        }else if($scope.subjSv.svLevel=="1"){
            $scope.subjSv.svLevelName="省级";
        }else if($scope.subjSv.svLevel=="2"){
            $scope.subjSv.svLevelName="市级";
        }else if($scope.subjSv.svLevel=="3"){
            $scope.subjSv.svLevelName="县级";
        }
        if($scope.subjSvRevoke.auditState=="1"||$scope.subjSvRevoke.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjSv.svArea = $.fn.Cityname($scope.subjSv.svAreaId);
    }).error(function (data) {
        
    });

    //审核撤销申请
    $scope.auditSubjSvRevoke=function(auditState){
        if (!$("#revokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjSvRevoke.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/auditSubjSvRevoke",
            method:"post",
            data:$scope.subjSvRevoke
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("申请成功", 'info');
                window.localStorage.removeItem("subjSvRevokeId");
               setTimeout(function () {
                   $state.go("index.content.asms/subjSv/subjSvRevokeList");
               },500)
            }else{
                jBox.tip(data.msg, 'info');
            }
        }).error(function(data){
            $("button").attr("disabled",false);
            jBox.tip("审核失败", 'info');
        });
    }

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjSvRevokeId");
        $state.go("index.content.asms/subjSv/subjSvRevokeList");
    }
});