var myapp=angular.module("myapp",[]).controller("subjSvCancelAudit",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjSvCancelId=window.localStorage.getItem("subjSvCancelId");
    $('#cancelForm').bootstrapValidator();//初始化验证，必须有

    //获取监管机构注销信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSvCancelById",
        method:"post",
        data:{id:$scope.subjSvCancelId}
    }).success(function(data){
        $scope.subjSv=data.subjSv;
        if($scope.subjSv.svLevel=="0"){
            $scope.subjSv.svLevelName="部级";
        }else if($scope.subjSv.svLevel=="1"){
            $scope.subjSv.svLevelName="省级";
        }else if($scope.subjSv.svLevel=="2"){
            $scope.subjSv.svLevelName="市级";
        }else if($scope.subjSv.svLevel=="3"){
            $scope.subjSv.svLevelName="县级";
        }
        $scope.subjSvCancel=data.subjSvCancel;
        if($scope.subjSvCancel.auditState=="1"||$scope.subjSvCancel.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjSv.svArea = $.fn.Cityname(subjSv.svArea);
    }).error(function (data) {
        
    });

    //审核监管机构注销申请
    $scope.auditSubjSvCancel=function(auditState){
        if (!$("#cancelForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjSvCancel.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/auditSubjSvCancel",
            method:"post",
            data:$scope.subjSvCancel
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjSvCancelId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjSv/subjSvCancelList");
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
        window.localStorage.removeItem("subjSvCancelId");
        $state.go("index.content.asms/subjSv/subjSvCancelList");
    }
})