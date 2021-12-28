var myapp=angular.module("myapp",[]);
myapp.controller("subjDtChangeAudit",function($scope,$http,$state){
    $scope.subjDt={};
    $scope.subjDtChange={};
    //获取本地存储的ID
    $scope.subjDtChangeId=window.localStorage.getItem("subjDtChangeId");
    $('#changeForm').bootstrapValidator();//初始化验证，必须有

    //获取变更信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjDetection/findSubjDtChangeById",
        method:"post",
        data:{id:$scope.subjDtChangeId}
    }).success(function(data){
        $scope.subjDt=data.data.subjDt;
        $scope.subjDtChange=data.data.subjDtChange;
        if($scope.subjDt.dtLevel=="0"){
            $scope.subjDt.dtLevelName="部级";
        }else if($scope.subjDt.dtLevel=="1"){
            $scope.subjDt.dtLevelName="省级";
        }else if($scope.subjDt.dtLevel=="2"){
            $scope.subjDt.dtLevelName="市级";
        }else if($scope.subjDt.dtLevel=="3"){
            $scope.subjDt.dtLevelName="县级";
        }
        if($scope.subjDtChange.dtLevel=="0"){
            $scope.subjDtChange.dtLevelName="部级";
        }else if($scope.subjDtChange.dtLevel=="1"){
            $scope.subjDtChange.dtLevelName="省级";
        }else if($scope.subjDtChange.dtLevel=="2"){
            $scope.subjDtChange.dtLevelName="市级";
        }else if($scope.subjDtChange.dtLevel=="3"){
            $scope.subjDtChange.dtLevelName="县级";
        }
        if($scope.subjDtChange.auditState=="1"||$scope.subjDtChange.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjDt.dtArea = $.fn.Cityname($scope.subjDt.dtAreaId);
        $scope.subjDtChange.dtArea = $.fn.Cityname($scope.subjDtChange.dtAreaId);
    }).error(function (data) {
        
    });

    //审核变更申请
    $scope.auditSubjDtChange=function(auditState){
        if (!$("#changeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjDtChange.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjDetection/auditSubjDtChange",
            method:"post",
            data:$scope.subjDtChange
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjDtChangeId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjDt/subjDtChangeList");
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
        window.localStorage.removeItem("subjDtChangeId");
        $state.go("index.content.asms/subjDt/subjDtChangeList");
    }
});