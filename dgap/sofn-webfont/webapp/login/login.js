var myapp = angular.module("myapp", ["ngCookies"]);
myapp.controller("login", function($scope, $http, $state, $cookies, $location) {
  $scope.ptName = "政务办公";
  $scope.localHref = window.location.href;
  if ($location.search() && $location.search().pf) {
    var pfs = {
      asms: "监管系统",
      ads: "监测系统",
      ales: "执法系统",
      qry: "信息查询",
      dgap: "数据共享",
      sys: "系统管理",
      fxjc: "分析决策"
    };
    $scope.ptName = pfs[$location.search().pf] || "政务办公";
  }

  function deleteAllCookies() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i];
      var eqPos = cookie.indexOf("=");
      var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
  window.localStorage && window.localStorage.setItem("webappSysname", "sys");
  //默认Tab选中项Index
  //默认Tab选中项Index
  $scope.selectedTabIndex = 0;
  //初始化UUID和生成验证码
  $scope.prevUUID = {};
  $scope.account = $.cookie("account");
  $scope.password = $.cookie("password");
  var rememberPassword = $.cookie("rememberPassword");
  rememberPassword = rememberPassword == undefined ? 0 : rememberPassword;
  rememberPassword = ~["1", "true"].indexOf(rememberPassword) ? true : false;
  $scope.rememberPwd = !!+rememberPassword;
  //登录方式Tab切换
  $scope.toggleTab = function(index) {
    $scope.selectedTabIndex = index;
    $(".login-wrap")
      .find(".section-wapper")
      .css({
        transform: "translate(-" + 50 * index + "%, 0)"
      });
    console.log($location.search().pf);
    if (index == 0) {
      if ($location.search().pf != undefined) {
        window.location.href =
          "http://www.bg.qsst.moa.gov.cn/#/login?pf=" + $location.search().pf;
      } else {
        window.location.href = "http://www.bg.qsst.moa.gov.cn/#/login";
      }
    } else if (index == 1) {
      if ($location.search().pf != undefined) {
        window.location.href =
          "https://202.127.45.149:643/#/login?pf=" + $location.search().pf;
      } else {
        window.location.href = "https://202.127.45.149:643/#/login";
      }
    }
  };

  function verForm() {
    if ($scope.account == null || !$scope.account.length) {
      jBox.tip("请输入用户名", "info");
      return;
    }
    if ($scope.password == null || !$scope.password.length) {
      jBox.tip("请输入密码", "info");
      return;
    }
    if ($scope.code == null || !$scope.code.length) {
      jBox.tip("请输入验证码", "info");
      return;
    }
    return true;
  }

  function verUkForm() {
    if ($scope.ukaccount == null || !$scope.ukaccount.length) {
      jBox.tip("请输入用户名", "info");
      return;
    }
    if ($scope.ukpassword == null || !$scope.ukpassword.length) {
      jBox.tip("请输入密码", "info");
      return;
    }
    if ($scope.ukcode == null || !$scope.ukcode.length) {
      jBox.tip("请输入验证码", "info");
      return;
    }
    return true;
  }

  $scope.flushValidateCode = function() {
    var now = +new Date();
    $http({
      url: "/sofn-sys-web/getUUID?" + now,
      data: { prev: $scope.prevUUID },
      method: "get"
    }).success(function(data) {
      $scope.prevUUID = data.uuid;
      //先获取UUID，再获取验证码图片
      // $http({
      //     url: "/sofn-asms-web/generatorImage",
      //     data: { curr: data.uuid },
      //     method: "get"
      // }).success(function(data) {
      $scope.codeImageSrc =
        "/sofn-sys-web/generatorImage?time=" + now + "&curr=" + $scope.prevUUID;
      // });
    });
  };
  //获取公钥
  // $scope.getPublic = function() {
  //   $.ajax({
  //     url: "/sofn-sys-web/getPublicKey",
  //     type: "post",
  //     dataType: "json",
  //     success: function(json) {
  //       if (json.httpCode != 200) {
  //         alert(json.msg);
  //         return;
  //       }
  //       //des随机key解密token
  //       window.localStorage.setItem(
  //         "publicKey",
  //         JSON.stringify(json.publicKey)
  //       );
  //     },
  //     fail: function(data) {
  //       alert("失败");
  //     }
  //   });
  // };

  //每次刷新页面验证码刷新，公钥刷新
  $scope.flushValidateCode();
  // $scope.getPublic();

  $scope.submit = function($event) {
    if ($event && $event.keyCode == 13) {
      $scope.login();
    }
  };
  $scope.rememberPassword = function() {
    console.log("$scope.rememberPwd", $scope.rememberPwd);
  };

  $scope.login = function() {
    if (!verForm() || $scope.disabled) {
      return;
    }
    $scope.disabled = true;

    //生成des随机key,此key用作提交的业务数据加密和服务器返回数据的解密
    var desKey = Math.uuid().replace(/-/g, "");
    //调用加密
    // var dataStr = encodeDES(data, desKey);
    //从缓存中获取公钥对象
    // var publicKey = JSON.parse(window.localStorage.getItem("publicKey"));
    //调用RSA加密
    // var encodeKey = encodeRSA(desKey, publicKey);
    $.ajax({
      url: "/sofn-sys-web/login",
      data: {
          account: $scope.account,
          password: $scope.password,
          uuid: $scope.prevUUID,
          code: $scope.code
      },
      type: "post",
      success: function(data) {
        if (data.httpCode != 200) {
            jBox.tip(data.msg, "info");
            return;
        }
        $.cookie("account", $scope.account, { expires: 15 }); // 存储一个带15天期限的 cookie
        if (data.user) {
          $.cookie("userName", data.user.username, { expires: 15 }); // 存储一个带15天期限的 cookie
        } else {
          $.cookie("userName", $scope.account, { expires: 15 }); // 存储一个带15天期限的 cookie
        }
        if ($scope.rememberPwd) {
          $.cookie("rememberPassword", +$scope.rememberPwd);
          $.cookie("password", $scope.password, { expires: 15 }); // 存储一个带15天期限的 cookie
        } else {
          $.cookie("rememberPassword", "", { expires: -1 });
          $.cookie("password", $scope.password, { expires: -1 }); // 销毁 cookie
        }
        window.localStorage.setItem("token", data.token);
        window.localStorage.setItem("userType", data.userType);
        window.sessionStorage && window.sessionStorage.clear();

        $state.go("login/applications");
      },
      error: function() {
        jBox.tip("网络连接的错误", "info");
        $scope.codeImageSrc = null;
        $scope.flushValidateCode();
      },
      complete: function() {
        $scope.disabled = false;
      }
    });
  };
  $scope.ukLogin = function() {
    $.ajax({
      url: "/sofn-sys-web/uklogin",
      data: {},
      type: "post",
      success: function(data) {
        if (data.httpCode != 200) {
          jBox.tip(data.msg, "info");
          return;
        }
        $.cookie("account", data.user.account, { expires: 15 });
        $.cookie("userName", data.user.userName, { expires: 15 });

        //des随机key解密token
        window.localStorage.setItem("token", data.token);
        window.localStorage.setItem("userType", data.userType);
        window.sessionStorage && window.sessionStorage.clear();

        $state.go("login/applications");
      },
      error: function() {
        jBox.tip("网络连接的错误", "info");
      },
      complete: function() {
        // $scope.disabled = false;
      }
    });
  };
});
