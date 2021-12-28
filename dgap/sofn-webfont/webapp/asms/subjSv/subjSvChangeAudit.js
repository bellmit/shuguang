var myapp=angular.module("myapp",[]);
myapp.controller("subjSvChangeAudit",function($scope,$http,$state){
    $scope.subjSv = {};
    $scope.subjSvChange = {};
    //获取本地存储的ID
    $scope.subjSvChangeId=window.localStorage.getItem("subjSvChangeId");
    $('#changeForm').bootstrapValidator();//初始化验证，必须有

    //获取变更信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSvChangeById",
        method:"post",
        data:{id:$scope.subjSvChangeId}
    }).success(function(data){
        $scope.subjSv=data.subjSv;
        $scope.subjSvChange=data.subjSvChange;
        if($scope.subjSvChange.svLeaderPhone==null||$scope.subjSvChange.svLeaderPhone==undefined){
            $scope.subjSvChange.svLeaderPhone = "";
        }
        if($scope.subjSvChange.svContactPhone==null||$scope.subjSvChange.svContactPhone==undefined){
            $scope.subjSvChange.svContactPhone="";
        }
        if($scope.subjSvChange.svContactQQ==null||$scope.subjSvChange.svContactQQ==undefined){
            $scope.subjSvChange.svContactQQ="";
        }
        if($scope.subjSvChange.svContactEmail==null||$scope.subjSvChange.svContactEmail==undefined){
            $scope.subjSvChange.svContactEmail="";
        }
        if($scope.subjSvChange.svPostcode==null||$scope.subjSvChange.svPostcode==undefined){
            $scope.subjSvChange.svPostcode="";
        }
        if($scope.subjSv.svLevel=="0"){
            $scope.subjSv.svLevelName="部级";
        }else if($scope.subjSv.svLevel=="1"){
            $scope.subjSv.svLevelName="省级";
        }else if($scope.subjSv.svLevel=="2"){
            $scope.subjSv.svLevelName="市级";
        }else if($scope.subjSv.svLevel=="3"){
            $scope.subjSv.svLevelName="县级";
        }
        if($scope.subjSvChange.svLevel=="0"){
            $scope.subjSvChange.svLevelName="部级";
        }else if($scope.subjSvChange.svLevel=="1"){
            $scope.subjSvChange.svLevelName="省级";
        }else if($scope.subjSvChange.svLevel=="2"){
            $scope.subjSvChange.svLevelName="市级";
        }else if($scope.subjSvChange.svLevel=="3"){
            $scope.subjSvChange.svLevelName="县级";
        }
        if($scope.subjSvChange.auditState=="1"||$scope.subjSvChange.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjSvChange.svArea = $.fn.Cityname(subjSvChange.svArea);
        $scope.subjSv.svArea = $.fn.Cityname(subjSv.svArea);
    }).error(function (data) {
        
    });

    //审核变更申请
    $scope.auditSubjSvChange=function(auditState){
        if (!$("#changeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjSvChange.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/auditSubjSvChange",
            method:"post",
            data:$scope.subjSvChange
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info');
                window.localStorage.removeItem("subjSvChangeId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjSv/subjSvChangeList");
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
        window.localStorage.removeItem("subjSvChangeId");
        $state.go("index.content.asms/subjSv/subjSvChangeList");
    }
});