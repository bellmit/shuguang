var myapp = angular.module("myapp", []).controller("login", function ($scope, $http, $state) {
    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //进入页面就刷新生成验证码
    xxx();

    //校验样式
    $('#attributeForm').bootstrapValidator();//必须有

    //刷新验证码
    $scope.flushValidateCodes = function () {
        xxx();
    };
    //登录
    $scope.ttsLogin = function () {
        //验证
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        var params =  {
            "account": $("#loginName").val(),
            "password": $("#password").val(),
            "code": $("#validateCode").val(),
            "uuid": localStorage.getItem("prev")
        }
        params = JSON.stringify(params);
        //注册
        $http({
            url: "/sofn-tts-web/ttsLogin/login?account="+$("#loginName").val()+"&password="+ $("#password").val()+"&code="+$("#validateCode").val()+"&uuid="+localStorage.getItem("prev"),
            data:  params,
            method: "post",
            //post传输方式   头信息
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            var entityInfo =  JSON.stringify(data);
            localStorage.setItem("entityInfo",entityInfo);
            // alert("状态码:"+data.httpCode+",状态消息:"+data.msg+"!!");
            $state.go("index.content.tts/register/accountInfo/accountInfo");
        }).error(function () {
            alert("登录异常！");
        })
    };
    //检验验证码是否输入正确！
    $scope.checkImg = function () {
        alert("验证成功！");
    }
})
var xxx = function flushValidateCode() {
    var prev = localStorage.getItem("prev");
    $.ajax({
        url: '/sofn-tts-web/ttsLogin/getUUID',
        type: 'get',
        dataType: 'json',
        data: {
            "prve": prev
        }
    }).done(function (data) {
        var models = eval(data);
        prev = models.uuid;
        localStorage.setItem("prev", prev);
        $.ajax({
            url: '/sofn-tts-web/ttsLogin/generatorImage',
            type: 'get',
            data: {
                "curr": models.uuid
            }
        })
            .done(function (image) {
                var reader = new FileReader();
                $('#codeValidateImg').hide().attr('src', '/sofn-tts-web/ttsLogin/generatorImage?curr=' + models.uuid).fadeIn();
            })
            .fail(function () {
                console.log("error");
            })
            .always(function () {
                console.log("complete");
            });


    }).fail(function () {
        console.log("error");
    })
        .always(function () {
            console.log("complete");
        });
}

