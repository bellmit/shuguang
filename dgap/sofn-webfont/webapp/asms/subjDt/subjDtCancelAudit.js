var myapp=angular.module("myapp",[]).controller("subjDtCancelAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjDtCancelId=window.localStorage.getItem("subjDtCancelId");
    $('#cancelForm').bootstrapValidator();//初始化验证，必须有

    //获取注销信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjDetection/findSubjDtCancelById",
        method:"post",
        data:{id:$scope.subjDtCancelId}
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
        $scope.subjDtCancel=data.data.subjDtCancel;
        if($scope.subjDtCancel.auditState=="1"||$scope.subjDtCancel.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjDt.dtArea = $.fn.Cityname($scope.subjDt.dtAreaId);
    }).error(function (data) {
        
    });

    //审核注销申请
    $scope.auditSubjDtCancel=function(auditState){
        if (!$("#cancelForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjDtCancel.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjDetection/auditSubjDtCancel",
            method:"post",
            data:$scope.subjDtCancel
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjDtCancelId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjDt/subjDtCancelList");
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
        window.localStorage.removeItem("subjDtCancelId");
        $state.go("index.content.asms/subjDt/subjDtCancelList");
    }
})