var isExist = "N";
var myapp = angular.module("myapp", []).controller("register", function ($scope, $http, $state) {
    //$("#accounts").val("123");

    var first =window.localStorage.getItem("tts_register_first");
    if(first==null){

    }else {
       var first_json =$.parseJSON(window.localStorage.getItem("tts_register_first"));
        console.debug(first_json.password);
        $scope.bean = {};
        $scope.bean.accounts= first_json.account;
        $scope.bean.password=first_json.password;
        $scope.bean.rePassword=first_json.rePassword;
    }

    // $scope.account=innerHTML="first.account";
    // $scope.bean.password=innerHTML="first.password";
    // $scope.bean.rePassword=innerHTML="first.rePassword"
    $('#attributeForm').bootstrapValidator();

    window.repeatCheck = function () {


        if ($("#accounts").val().length > 5) {
            $http({
                url: "/sofn-tts-web/ttsScltxxcjRegiter/repeatCheck",
                data: $("#accounts").val(),
                method: "post",
                //post传输方式   头信息
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }).success(function (data) {
                if (data.isExists == "Y") {
                    isExist = "Y"
                    $scope.flag=true;
                    return false;
                } else {
                    isExist = "N"
                    $scope.flag=false;
                }
            }).error(function () {
                alert("网络连接的错误异常!");
                return false;
            })
        }
    };


    //下一步
    $scope.regNextStep = function () {
        if ($("#password").val() != $("#rePassword").val()) {
            alert("密码不一致!");
            return false;
        }
        if (isExist == "Y") {
            alert("此账号已经存在,请核实重新填写!")
            return false;
        }
        //表单验证
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有

        var data = {
            "account": $scope.bean.accounts,
            "password": $scope.bean.password,
            "rePassword": $scope.bean.rePassword,
            "code": $scope.bean.code
        };
        //将对象转成字符串后，再放入缓存
        data = JSON.stringify(data);
        //清除tts_register_first缓存
        window.localStorage.removeItem("tts_register_first");
        //生成tts_register_first缓存
        window.localStorage.setItem("tts_register_first", data);
        $state.go("tts/register/secondChoose/secondChoose")
    };

    
})
