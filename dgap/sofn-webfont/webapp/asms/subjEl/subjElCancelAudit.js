var myapp=angular.module("myapp",[]).controller("subjElCancelAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjElCancelId=window.localStorage.getItem("subjElCancelId");
    $('#cancelForm').bootstrapValidator();//初始化验证，必须有

    //获取注销信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnforceLaw/findSubjElCancelById",
        method:"post",
        data:{id:$scope.subjElCancelId}
    }).success(function(data){
        $scope.subjEl=data.subjEl;
        if($scope.subjEl.elLevel=="0"){
            $scope.subjEl.elLevelName="部级";
        }else if($scope.subjEl.elLevel=="1"){
            $scope.subjEl.elLevelName="省级";
        }else if($scope.subjEl.elLevel=="2"){
            $scope.subjEl.elLevelName="市级";
        }else if($scope.subjEl.elLevel=="3"){
            $scope.subjEl.elLevelName="县级";
        }
        $scope.subjElCancel=data.subjElCancel;
        if($scope.subjElCancel.auditState=="1"||$scope.subjElCancel.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
    }).error(function (data) {
        
    });

    //审核注销申请
    $scope.auditSubjElCancel=function(auditState){
        if (!$("#cancelForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjElCancel.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnforceLaw/auditSubjElCancel",
            method:"post",
            data:$scope.subjElCancel
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjElCancelId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjEl/subjElCancelList");
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
        window.localStorage.removeItem("subjElCancelId");
        $state.go("index.content.asms/subjEl/subjElCancelList");
    }
})