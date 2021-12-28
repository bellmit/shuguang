var myapp=angular.module("myapp",[]);
myapp.controller("subjElChangeAudit",function($scope,$http,$state){
    $scope.subjEl={};
    $scope.subjElChange={};
    //获取本地存储的ID
    $scope.subjElChangeId=window.localStorage.getItem("subjElChangeId");
    $('#changeForm').bootstrapValidator();//初始化验证，必须有

    //获取变更信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnforceLaw/findSubjElChangeById",
        method:"post",
        data:{id:$scope.subjElChangeId}
    }).success(function(data){
        $scope.subjEl=data.subjEl;
        $scope.subjElChange=data.subjElChange;
        if($scope.subjElChange.elLeaderPhone==null||$scope.subjElChange.elLeaderPhone==undefined){
            $scope.subjElChange.elLeaderPhone="";
        }
        if($scope.subjElChange.elContactQQ==null||$scope.subjElChange.elContactQQ==undefined){
            $scope.subjElChange.elContactQQ="";
        }
        if($scope.subjElChange.elContactEmail==null||$scope.subjElChange.elContactEmail==undefined){
            $scope.subjElChange.elContactEmail="";
        }
        if($scope.subjElChange.elPostcode==null||$scope.subjElChange.elPostcode==undefined){
            $scope.subjElChange.elPostcode="";
        }
        if($scope.subjElChange.elUnitNature==null||$scope.subjElChange.elUnitNature==undefined){
            $scope.subjElChange.elUnitNature="";
        }
        if($scope.subjElChange.elWorkBody==null||$scope.subjElChange.elWorkBody==undefined){
            $scope.subjElChange.elWorkBody="";
        }
        if($scope.subjElChange.elPeopleNum==null||$scope.subjElChange.elPeopleNum==undefined){
            $scope.subjElChange.elPeopleNum="";
        }
        if($scope.subjEl.elLevel=="0"){
            $scope.subjEl.elLevelName="部级";
        }else if($scope.subjEl.elLevel=="1"){
            $scope.subjEl.elLevelName="省级";
        }else if($scope.subjEl.elLevel=="2"){
            $scope.subjEl.elLevelName="市级";
        }else if($scope.subjEl.elLevel=="3"){
            $scope.subjEl.elLevelName="县级";
        }
        if($scope.subjElChange.elLevel=="0"){
            $scope.subjElChange.elLevelName="部级";
        }else if($scope.subjElChange.elLevel=="1"){
            $scope.subjElChange.elLevelName="省级";
        }else if($scope.subjElChange.elLevel=="2"){
            $scope.subjElChange.elLevelName="市级";
        }else if($scope.subjElChange.elLevel=="3"){
            $scope.subjElChange.elLevelName="县级";
        }
        if($scope.subjElChange.auditState=="1"||$scope.subjElChange.auditState=="2"){
            $("textarea[name='auditSuggestion']").attr("readonly","readonly");
        }else{
            $("#passBut").css("display","inline-block");
            $("#refuseBut").css("display","inline-block");
        }
        $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
        $scope.subjElChange.elArea = $.fn.Cityname($scope.subjElChange.elAreaId);
    }).error(function (data) {
        
    });
    //审核变更申请
    $scope.auditSubjElChange=function(auditState){
        if (!$("#changeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $scope.subjElChange.auditState = auditState;
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnforceLaw/auditSubjElChange",
            method:"post",
            data:$scope.subjElChange
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("审核成功", 'info')
                window.localStorage.removeItem("subjElChangeId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjEl/subjElChangeList");
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
        window.localStorage.removeItem("subjElChangeId");
        $state.go("index.content.asms/subjEl/subjElChangeList");
    }
});