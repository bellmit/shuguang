var myapp = angular.module("myapp", []);
myapp.controller("alesPersonalCenterNormal", function($scope, $http, $state) {
  //返回前一页
  $scope.callBack = function() {
    $state.go(window.localStorage.getItem("historyUrl"));
  };
  $("#changePwdForm").bootstrapValidator(); //初始化验证，必须有
  $("#subjElCancelForm").bootstrapValidator(); //初始化验证，必须有
  $("#subjElChangeForm").bootstrapValidator(); //初始化验证，必须有
  //获取用户信息
  $scope.user = {};
  $http({
    headers: {
      token: window.localStorage.getItem("token")
    },
    url: "/sofn-ales-web/subjEnforceLaw/findCurrentUserByToken",
    method: "post"
  })
    .success(function(data) {
      $scope.user = data.user;
      if ($scope.user.userType == "SUPERADMIN") {
        $scope.user.userType = "超级管理员";
        $("#orgInfo").show();
      } else if ($scope.user.userType == "SYSADMIN") {
        $scope.user.userType = "用户管理员";
        $("#orgInfo").show();
      } else if ($scope.user.userType == "SUBJADMIN") {
        $scope.user.userType = "主体管理员";
        $("#orgInfo").show();
      } else if ($scope.user.userType == "NORMAL") {
        $scope.user.userType = "用户";
        $("#orgInfo").hide();
      }
      $scope.httpurl();
    })
    .error(function() {});
  //当前用户所在机构
  $scope.currentOrg = {};
  $.ajax({
    url: "/sofn-sys-web/user/findSysUserOrganization",
    type: "post",
    dataType: "json",
    headers: {
      token: window.localStorage.getItem("token")
    },
    success: function(json) {
      if (json.httpCode == 500) {
        alert("获取用户机构信息失败");
        return;
      }
      $scope.currentOrg = json.user.organization;
      $scope.user = json.user;
    },
    error: function(data) {
      alert("失败");
    }
  });
  //请求菜单数据
  $scope.httpurl = function() {
    //向后台发起请求获取数据
    var token = window.localStorage.getItem("token");
    return $http
      .post(
        "/sofn-sys-web/ssoMenu/getMenusByUserId",
        {},
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            token: token
          }
        }
      )
      .then(
        function(res) {
          var data = res.data;
          if (data.httpCode != 200) {
            jBox.tip(data.msg);
            return;
          }
          var systems = formattedSystems(data.data);
          $scope.systems = systems;
          return data;
        },
        function(err) {}
      );
  };

  function formattedSystems(data) {
    var systems = _.groupBy(data, "menuValue");
    return systems;
  }

  //显示密码修改模态框
  $scope.showPwdModal = function() {
    $("#pwdModal").modal("show");
  };
  //获取焦点清除验证
  $(".oldPwd").focus(function() {
    $(".oldPwd").css("border-color", "#2b542c");
    $(".dirVilidator").css("display", "none");
  });
  //修改密码
  $scope.changePwd = function() {
    //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
    var a = $("input[name='newPass']").val();
    var b = $("input[name='newPassword']").val();
    console.log("=====新密码" + a + "======确认密码" + b);
    if (
      !$("#changePwdForm")
        .data("bootstrapValidator")
        .validate()
        .isValid()
    ) {
      return false;
    } //必须有

    if (
      $("input[name='newPass']").val() != $("input[name='newPassword']").val()
    ) {
      $("#changePwdForm")
        .data("bootstrapValidator")
        .destroy(); //先去掉验证，与下面对应
      $("#changePwdForm").bootstrapValidator(); //初始化验证，与上面对应
      $("#changePwdForm")
        .data("bootstrapValidator")
        .validate();
      return false;
    }

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
          account: $scope.user.account,
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
              /*   $http({
                        headers: {
                            token: window.localStorage.getItem("token")
                            //  type: "pc"
                        },
                        url: "/sofn-ales-web/login/logout",
                        dataType: "data",
                        method: "post"
                    }).success(function (data) {
                        if (data.httpCode != 200){
                            jBox.tip("修改失败", 'info');
                            alert(2);
                            return ;
                        }
                        $(".dirVilidator").css("display","none");
                        $(".oldPwd").css("border-color","#2b542c");
                        jBox.tip("修改成功", 'info');
                        $("#pwdModal").modal("hide");
                        localStorage.clear();
                        $.cookie("account", "", { expires: -1 }); // 清除cookie
                        $.cookie("password", "", { expires: -1 }); // 清除 cookie
                        setTimeout(function () {$state.go("login/login");},200);//跳转至登录界面；
                    }).
                    error(function () {
                            jBox.tip("修改失败", 'info');
                        alert(1);
                    });*/
              $(".dirVilidator").css("display", "none");
              $(".oldPwd").css("border-color", "#2b542c");
              $("#pwdModal").modal("hide");
              localStorage.clear();
              $.cookie("account", "", { expires: -1 }); // 清除cookie
              $.cookie("password", "", { expires: -1 }); // 清除 cookie
              jBox.tip("修改成功", "info");
              setTimeout(function() {
                $state.go("login/login");
              }, 200); //跳转至登录界面；
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
        jBox.tip("修改失败", "info");
        $("button").attr("disabled", false);
      });
  };
  //打开帮助中心
  $scope.gotoHelpCenter = function($event) {
    $event.preventDefault();
    if (!$scope.currentOrg || !$scope.currentOrg.orgType) {
      return;
    }
    var webappSysname = $scope.currentOrg.orgType.toLowerCase();
    $.ajax({
      url: "/sofn-sys-web/document/queryList",
      type: "post",
      dataType: "json",
      data: {
        token: window.localStorage.getItem("token"),
        enableFlag: "Y",
        applyTo: webappSysname,
        start: 0,
        length: 1000000
      },
      success: function(data) {
        var result = data.data.list;
        var html = "";
        for (var i = 0; i < result.length; i++) {
          html += '<li class="document"><div></div>';
          html +=
            "<a onclick=\"goto('" +
            result[i].id +
            "')\">" +
            result[i].title +
            '</a href="">';
          html += "<em>" + timestampToDate(result[i].createTime) + "</em>";
          html += "</li>";
        }
        $("#helpList .message-list").html(html);
        $("#helpList").modal("show");
      },
      fail: function(data) {
        alert("获取数据失败");
      }
    });
  };
  //帮助中心查看详情
  window.goto = function(id) {
    var token = window.localStorage.getItem("token");
    $.ajax({
      url: "/sofn-sys-web/document/queryOne",
      type: "post",
      dataType: "json",
      data: { id: id, token: token },
      success: function(data) {
        var htm = data.data.content;
        $("#helpListInfo").modal("show");
        $("#myInfo").html(htm);
        $("#helpListTitile2").html(data.data.title);
      },
      fail: function(data) {
        $.jBox.tip("失败");
      }
    });
  };
  //退出当前账号
  $scope.loginOut = function($event) {
    $event.preventDefault();
    swal(
      {
        title: "退出登录",
        text: "已成功注销账号信息!",
        type: "success",
        timer: 2000,
        showConfirmButton: false
      },
      function() {
        swal.close();
        window.localStorage && window.localStorage.clear();
        window.sessionStorage && window.sessionStorage.clear();
        $state.go("login");
      }
    );
  };
  window.timestampToDate = function(timestamp) {
    var date = new Date(timestamp);
    var Y = date.getFullYear() + "-";
    var M =
      (date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1)
        : date.getMonth() + 1) + "-";
    var D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return Y + M + D;
  };
});
