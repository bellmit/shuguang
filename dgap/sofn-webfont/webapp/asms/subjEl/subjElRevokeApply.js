var myapp=angular.module("myapp",[]).controller("subjElRevokeApply",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjElId=window.localStorage.getItem("subjElId");
    $('#applyRevokeForm').bootstrapValidator();//初始化验证，必须有

    //获取机构信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjEnforceLaw/findSubjEnforceLawById",
        method:"post",
        data:{id:$scope.subjElId}
    }).success(function(data){
        $scope.subjEl=data.data;
        if($scope.subjEl.elLevel=="0"){
            $scope.subjEl.elLevelName="部级";
        }else if($scope.subjEl.elLevel=="1"){
            $scope.subjEl.elLevelName="省级";
        }else if($scope.subjEl.elLevel=="2"){
            $scope.subjEl.elLevelName="市级";
        }else if($scope.subjEl.elLevel=="3"){
            $scope.subjEl.elLevelName="县级";
        }
        $scope.subjEl.elArea = $.fn.Cityname($scope.subjEl.elAreaId);
        $scope.subjEl.createTime=new Date(parseInt($scope.subjEl.createTime)+28800000).toISOString().slice(0,10);
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjElId");
        $state.go("index.content.asms/subjEl/subjElList");
    }

    //撤销申请
    $scope.applySubjElRevoke=function(){
        if (!$("#applyRevokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjEnforceLaw/addSubjElRevoke",
            method:"post",
            data:{elId:$scope.subjElId,applyReason:$scope.applyReason}
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("申请成功", 'info');
                window.localStorage.removeItem("subjElId");
                setTimeout(function(){
                    $state.go("index.content.asms/subjEl/subjElList");
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