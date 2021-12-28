var myapp=angular.module("myapp",[]).controller("subjSvRevokeApply",function($scope,$http,$state){

    //获取本地存储的ID
    $scope.subjSvId=window.localStorage.getItem("subjSvId");
    $('#applyRevokeForm').bootstrapValidator();//初始化验证，必须有

    //获取机构备案信息
    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        url:"/sofn-asms-web/subjSupervise/findSubjSuperviseById",
        method:"post",
        data:{id:$scope.subjSvId}
    }).success(function(data){
        $scope.subjSv=data.data;
        if($scope.subjSv.svLevel=="0"){
            $scope.subjSv.svLevelName="部级";
        }else if($scope.subjSv.svLevel=="1"){
            $scope.subjSv.svLevelName="省级";
        }else if($scope.subjSv.svLevel=="2"){
            $scope.subjSv.svLevelName="市级";
        }else if($scope.subjSv.svLevel=="3"){
            $scope.subjSv.svLevelName="县级";
        }
        $scope.subjSv.svArea = $.fn.Cityname(subjSv.svArea);
        $scope.subjSv.createTime=new Date(parseInt($scope.subjSv.createTime)+28800000).toISOString().slice(0,10);
    }).error(function (data) {
        
    });

    //清除缓存
    $scope.removeLocalStorage=function(){
        window.localStorage.removeItem("subjSvId");
    }

    //申请撤销
    $scope.applySubjSvRevoke=function(){
        if (!$("#applyRevokeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        $("button").attr("disabled",true);
        $http({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url:"/sofn-asms-web/subjSupervise/addSubjSvRevoke",
            method:"post",
            data:{svId:$scope.subjSvId,applyReason:$scope.applyReason}
        }).success(function(data){
            $("button").attr("disabled",false);
            if(data.httpCode=="200") {
                jBox.tip("申请成功", 'info');
                window.localStorage.removeItem("subjSvId");
                setTimeout(function () {
                    $state.go("index.content.asms/subjSv/subjSvList");
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