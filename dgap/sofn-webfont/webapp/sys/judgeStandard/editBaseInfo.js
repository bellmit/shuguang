var myapp=angular.module("myapp",[]);
myapp.controller("editBaseInfo",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");

    var judgeStandardId = window.localStorage.getItem("judgeStandardId");

    $("#editBaseInfo").bootstrapValidator();

    /**
     * 返回判定标准列表页
     */
    $scope.goJudgeList = function () {
        $state.go("index.content.sys/judgeStandard/adsModelJudgeStandardList");
    }

    /**
     * 跳转到上传附件页面
     */
    $scope.goUploadFile = function () {
        $state.go("index.content.sys/judgeStandard/editFile");
    }

    /**
     * 查询判定标准基础信息
     */
    $http({
        url:"/sofn-sys-web/adsModelJudgeStandard/queryById",
        method:"post",
        dataType:"json",
        params : {"id" : judgeStandardId},
        headers: {
            post: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }
    }).success(function(data){
        $scope.adsModelJudgeStandard = data.adsModelJudgeStandard;
        $("input[name='name']").val(data.adsModelJudgeStandard.name);
    });

    /**
     * 修改判定标准名称
     */
    $scope.updateInfo = function () {
        //表单验证
        var name = $("input[name='name']").val();
        if(name==null || name==''){
            jBox.tip("请输入判定标准名称", 'info');
            return false;
        }
        //提交表单
        $.ajax({
            url:"/sofn-sys-web/adsModelJudgeStandard/updateAdsModelJudgeStandard",
            type:"post",
            dataType:"json",
            data:{"id":judgeStandardId,"name":name,"filePath" : $scope.adsModelJudgeStandard.filePath,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("修改成功", 'success');
                }else{
                    jBox.tip("修改失败", 'info');
                }
            }
        });
    }

})

