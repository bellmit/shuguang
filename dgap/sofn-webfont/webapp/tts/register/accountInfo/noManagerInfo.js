var myapp = angular.module("myapp", []).controller("noManagerInfo", function ($scope, $http, $state) {
    inits($scope);
    $scope.flag = false;
    //打开修改密码窗口
    window.showUpdatePwd = function () {
        $("#updatePwd").modal('show');
    }

    //验证法人身份证是否已经存在
    window.queryByIdCode = function () {
        //验证企业注册码是否存在
        if (null != $("#idCode").val() && $("#idCode").val().length == 18) {
            $http({
                url: "/sofn-tts-web/ttsScltxxcjRegiter/queryByIdCode",
                data: $scope.legalIdnumber,
                method: "post",
                //post传输方式   头信息
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }).success(function (data) {
                if (data.isExists == "Y") {
                    $scope.flag = true;
                    return false;
                } else {
                    $scope.flag = false;
                }
            })
        }
    }

    //检查新密码是否和原密码一样
    window.checkNewPwd = function () {
        var password = $("#formerPwd").val();
        var newPassword = $scope.TtsScltxxcjRegiter.password;
        if(password != null && newPassword != null && password != '' && newPassword != ''){
            if(password == newPassword){
                $("#newPwdMsg").show().text("新密码不能和原密码一样");
                return;
            }else{
                $("#newPwdMsg").show().text("");
            }
        }
    }

    /**
     * 修改个人信息
     */
    window.editInfo = function () {
        $("#realName").removeAttr("readOnly");
        $("#contactPhone").removeAttr("readOnly");
        $("#contactEmail").removeAttr("readOnly");
        $("#legalIdnumber").removeAttr("readOnly");
        $("#anniu").removeAttr("hidden");
    }

    /**
     *  取消编辑
     */
    window.cancelEdit = function () {
        $("#realName").attr("readOnly","true");
        $("#contactPhone").attr("readOnly","true");
        $("#contactEmail").attr("readOnly","true");
        $("#legalIdnumber").attr("readOnly","true");
        $("#anniu").attr("hidden","hidden");
    }

    $scope.ttsScltxxcjUser = {};
    $scope.updateUserInfo = function () {
        $('#attributeForm1').bootstrapValidator();//必须有
        if($scope.flag == true){
            return;
        }
        if (!$("#attributeForm1").data('bootstrapValidator').validate().isValid()) {
            return;
        }
        $scope.ttsScltxxcjUser.name = $scope.realName;
        $scope.ttsScltxxcjUser.phone = $scope.contactPhone;
        $scope.ttsScltxxcjUser.email = $scope.contactEmail;
        $scope.ttsScltxxcjUser.idCard = $scope.legalIdnumber;
        $scope.ttsScltxxcjUser.account = $scope.accounts;
        $scope.ttsScltxxcjUser.delFlag = 'N';
        $http({
            url:'/sofn-tts-web/ttsScltxxcjUser/updateByAcc',
            data:$scope.ttsScltxxcjUser,
            method:'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {

        });
    }

    $scope.updatepassword=function(){
        $('#attributeForm').bootstrapValidator();//必须有
        if($scope.flg == false){
            return;
        }
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        $scope.TtsScltxxcjRegiter.password = $("#newPwd").val();
        $scope.TtsScltxxcjRegiter.password = $("#checkNewPwd").val();
        $scope.TtsScltxxcjRegiter.account = entityInfo.data.account;
        $("#updatePwd").modal('hide');
        $http({
            url:"/sofn-tts-web/ttsScltxxcjRegiter/updatepassword",
            data: $scope.TtsScltxxcjRegiter,
            method:"post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }

        }).success(function(data){
            if(data.httpCode==200){
                jBox.tip("修改密码成功！","info");
                $state.go("login/logintts");
            }
        }).error(function (){
            jBox.tip("修改密码失败！","error")
        })

    };

    //检查原密码是否正确
    window.checkPwd = function(){
        var password = $("#formerPwd").val();
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        $http({
            url:"/sofn-tts-web/ttsScltxxcjRegiter/checkFormerPwd",
            data: {password : password,account : entityInfo.data.account},
            method:"post",
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function(data){
            if(data.code==200){
                if(data.data != null && data.data != ''){
                    $("#formerPwdMsg").show().text(data.data);
                    $scope.flg = false;
                }else{
                    $("#formerPwdMsg").hide();
                    $scope.flg = true;
                }
            }
        });
    }
});
var inits = function ($scope) {
    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
    if (entityInfo) {
        $scope.accounts = entityInfo.data.account;
        $scope.enterpriseName = entityInfo.data.enterpriseName;
        $scope.address = entityInfo.data.address;
        $scope.entityIdcode = entityInfo.data.entityIdcode;
        $scope.userIdcode = entityInfo.data.userIdcode;
        if(entityInfo.data.isMain && entityInfo.data.isMain == '0'){
            $scope.realName = entityInfo.data.childName;
            $scope.contactPhone = entityInfo.data.childPhone;
            $scope.contactEmail = entityInfo.data.childEmail;
            $scope.legalIdnumber = entityInfo.data.childIdcard;
        }else{
            $scope.realName = entityInfo.data.realName;
            $scope.contactPhone = entityInfo.data.contactPhone;
            $scope.contactEmail = entityInfo.data.contactEmail;
            $scope.legalIdnumber = entityInfo.data.legalIdnumber;
        }
    }
};