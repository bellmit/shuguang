var myapp = angular.module("myapp", []);
myapp.controller("modifyPassword", function($scope, $http, $state) {
  var passwordHttpCode = localStorage.getItem("passwordHttpCode");
  passwordHttpCode && passwordHttpCode.length > 0
    ? +passwordHttpCode
    : undefined;
  $scope.visibleCancelButton = passwordHttpCode != 408;
  $scope.accountName = localStorage.getItem("accountName");

  $scope.nextPage = function() {
    $state.go("login/applications", {}, { location: "replace" });
  };
  $("#changePwdForm").bootstrapValidator(); //初始化验证，必须有
  //修改密码
  $scope.changePwd = function() {
    //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
    if ($("input[name='newPwd']").val() != $("input[name='newPwdC']").val()) {
      $("#changePwdForm")
        .data("bootstrapValidator")
        .destroy(); //先去掉验证，与下面对应
      $("#changePwdForm").bootstrapValidator(); //初始化验证，与上面对应
      $("#changePwdForm")
        .data("bootstrapValidator")
        .validate();
      return false;
    }
    if (
      !$("#changePwdForm")
        .data("bootstrapValidator")
        .validate()
        .isValid()
    ) {
      return false;
    } //必须有
    $("button").attr("disabled", true);
    $http({
      headers: {
        token: window.localStorage.getItem("token")
      },
      url: "/sofn-ales-web/login/getPublicKey",
      method: "post",
      dataType: "json"
    })
      .success(function(json) {
        //需要加密的业务数据
        var data = {
          account: $scope.accountName,
          oldPwd: $scope.oldPwd,
          newPwd: $scope.newPwd
        };
        //生成des随机key,此key用作提交的业务数据加密和服务器返回数据的解密
        var desKey = Math.uuid().replace(/-/g, "");
        //调用加密
        var dataStr = encodeDES(data, desKey);
        //调用RSA加密
        var encodeKey = encodeRSA(desKey, json.publicKey);
        $http({
          headers: {
            token: window.localStorage.getItem("token")
          },
          url: "/sofn-ales-web/subjEnforceLaw/changePwd",
          data: { ciphertext: dataStr, desKey: encodeKey },
          method: "post"
        })
          .success(function(data) {
            $("button").attr("disabled", false);
            if (data.httpCode == "200") {
              $http({
                url: "/sofn-ales-web/login/logout",
                method: "post",
                dataType: "json",
                headers: {
                  token: window.localStorage.getItem("token"),
                  type: "pc"
                }
              })
                .success(function(json) {
                  if (json.httpCode != 200) {
                    return;
                  }
                  $(".dirVilidator").css("display", "none");
                  $(".oldPwd").css("border-color", "#2b542c");
                  jBox.tip("修改成功", "info");
                  $("#pwdModal").modal("hide");
                  localStorage.clear();
                  $.cookie("account", "", { expires: -1 }); // 清除cookie
                  $.cookie("password", "", { expires: -1 }); // 清除 cookie
                  setTimeout(function() {
                    $state.go("login");
                  }, 200);
                })
                .error(function() {
                  jBox.tip("修改失败", "info");
                });
            } else if (data.httpCode == "405") {
              $(".dirVilidator").css("display", "block");
              $(".oldPwd").css("border-color", "#843534");
              jBox.tip("旧密码不正确", "info");
            } else {
              jBox.tip("修改失败", "info");
            }
          })
          .error(function() {
            $("button").attr("disabled", false);
          });
      })
      .error(function() {
        $("button").attr("disabled", false);
      });
  };
});
