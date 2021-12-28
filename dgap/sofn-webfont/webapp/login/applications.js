var myapp = angular.module("myapp", []);
myapp.controller("applications", function($scope, $http, $state) {
  //获取用户信息
  $scope.user = {};
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
      $scope.currentOrg = json.org;
      $scope.user = json.user;
    },
    error: function(data) {
      alert("失败");
    }
  });
  //跳转个人中心页面
  $scope.gotoPersonalCenter = function($event) {
    $event.preventDefault();
    var prevUrl = window.location.href
      .split("/#/")[1]
      .replace("index/content/", "index.content.");
    console.log("prevUrl", prevUrl);
    window.localStorage.setItem("historyUrl", prevUrl);
    var url = "";
    if (!$scope.currentOrg || !$scope.currentOrg.orgType) {
      return;
    }
    var webappSysname = $scope.currentOrg.orgType.toLowerCase();
    if ($scope.user.userType == "SYSADMIN") {
      //管理员
      switch ($scope.currentOrg.orgType) {
        case "ASMS":
          url = "account/asmsPersonalCenterAdmin";
          break;
        case "ALES":
          url = "account/alesPersonalCenterAdmin";
          break;
        case "ADS":
          url = "monitor/acctInfo/adminAcct";
          break;
      }
    } else {
      window.localStorage.setItem("webappSysname", webappSysname);
      //非管理员
      switch ($scope.currentOrg.orgType) {
        case "ASMS":
          url = "account/asmsPersonalCenterNormal";
          break;
        case "ALES":
          url = "account/alesPersonalCenterNormal";
          break;
        case "ADS":
          url = "monitor/acctInfo/userAcct";
          break;
      }
    }
    $state.go(url);
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
        $state.go("login", {}, { location: "replace" });
      }
    );
  };

  $scope.systems = {};
  //  $scope.authority =;
  var firstLink;
  var getParameterByName = function(name) {
    var search = "?" + window.location.href.split("?")[1];
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
      results = regex.exec(search);
    return results === null
      ? ""
      : decodeURIComponent(results[1].replace(/\+/g, " "));
  };

  $scope.go = function() {
    var token = window.localStorage.getItem("token");
    if (token == null || token == "") {
      swal("系统提示", "获取登录信息失败，请重新登录", "error");
      return;
    }
    // window.location.href = 'http://202.127.45.194/Fxjc/?token=' + token;
    var form = document.createElement("form");
    var input = document.createElement("input");
    input.setAttribute("name", "token");
    input.setAttribute("value", token);
    form.appendChild(input);
    form.setAttribute("action", "/Fxjc/");
    form.setAttribute("method", "post");
    document.body.appendChild(form);
    form.submit();
  };

  $scope.renderTree = function(formatmenu, state) {
    $("#tt").tree({
      data: formatmenu,
      collapseAll: true,
      onClick: function(node) {
        window.localStorage.setItem("menuObj", JSON.stringify(node));
        //针对有Tab页的页面菜单栏控制，现主要为subjEnt、subjSv、subjDt、subjEl的list
        var userTab = JSON.stringify(node);
        var nowTab = window.localStorage.getItem("menuTabObj");
        if (
          node.tab != null &&
          node.tab != "" &&
          node.tab != undefined &&
          userTab != nowTab
        ) {
          window.localStorage.setItem("menuTabObjParentId", node.id);
          window.localStorage.setItem("menuTabObj", userTab);
        }
        // toggle
        $("#tt").tree("toggle", node.target);

        $(node.target)
          .closest("li")
          .siblings()
          .children(".tree-node")
          .each(function() {
            $("#tt").tree("collapse", this);
          });

        if (node.url == null) {
          if (node.tab != null && node.tab.length > 0) {
            ~node.tab[0].url.indexOf("index.content")
              ? $state.go(node.tab[0].url)
              : (window.location.href = node.tab[0].url);
            return;
          } else {
            return;
          }
        }
        if (node.describes != null && ~node.describes.indexOf("#open")) {
          if (~node.url.indexOf("index.content")) {
            var tabUrl = "#/" + node.url.replace(/\./g, "/");
            var nextSystem = tabUrl.split("/")[3];
            localStorage.setItem("webappSysname", nextSystem.toLowerCase());
            window.open(tabUrl);
          } else {
            window.open(node.url);
          }
          return;
        }
        ~node.url.indexOf("index.content")
          ? $state.go(node.url)
          : (window.location.href = node.url);
      },
      onLoadSuccess: function() {
        //隐藏根节点
        //    $("#"+$('#tt').tree('getRoot').domId).hide();
      }
    });
  };

  var source = getParameterByName("source");
  $scope.source = source;
  $scope.sysName = function(name) {
    if (!localStorage.getItem("token")) {
      swal(
        {
          title: "系统提示",
          text: "您的账号已失效或在其它设备登录，账号已退出!",
          type: "warning",
          confirmButtonText: "重新登录",
          closeOnConfirm: true
        },
        function() {
          swal.close();
          window.localStorage && window.localStorage.clear();
          window.sessionStorage && window.sessionStorage.clear();
          window.location.href = "/";
        }
      );
      return;
    }
    window.localStorage.setItem("webappSysname", name);
    //  window.localStorage.setItem("readWriteAuthority",  $scope.authority);
    window.localStorage.setItem("historyUrl", "/#/login/applications");
    var menus = formattedMenuByName($scope.systems[name.toUpperCase()]);
    $scope.renderTree(menus[0].children, $state);
    firstLink = getFirstLink(menus[0].children);
    $state.go(firstLink);
  };

  function getFirstLink(menus) {
    menus.map(function(menu) {
      if (menu.children && menu.children.length) {
        getFirstLink(menu.children);
      } else if (menu.tab && menu.tab.length) {
        getFirstLink(menu.tab);
      } else {
        firstLink = firstLink ? firstLink : menu.url;
      }
    });
    return firstLink;
  }

  var system;
  if (window.localStorage.getItem("webappSysname") == null) {
    window.localStorage.setItem("webappSysname", "sys");
    system = "SYS";
  } else {
    system = window.localStorage.getItem("webappSysname").toUpperCase();
  }

  function formattedSystems(data) {
    var systems = _.groupBy(data, "menuValue");
    return systems;
  }

  function formattedMenuByName(current) {
    var isRoot = false;
    var menus = [];
    _.each(current, function(menu) {
      if (menu.authority == 0) {
        return;
      }
      isRoot = !_.size(
        _.filter(current, function(item) {
          if (item.authority == 0) {
            return;
          }
          if (menu.parentId == item.id) {
            item.children = item.children || [];
            if (menu.menuType == 2) {
              item.tab = item.tab || [];
              item.tab[item.tab.length] = menu;
            } else {
              item.state = "closed";
              item.children[item.children.length] = menu;
            }
          }
          item.text = item.menuName;
          item.url = item.url != "#" ? item.url : null;
          return menu.parentId == item.id;
        })
      );
      menus = isRoot ? [menu] : menus;
    });
    if (menus.length) {
      menus[0].state = "open";
    }
    var formatmenu = menus.length > 0 ? menus[0].children : null;
    //每次刷新Tab页，以防权限变动
    var flag = true;
    //一级菜单
    for (var i = 0; formatmenu != null && i < formatmenu.length && flag; i++) {
      //如果一级菜单就是包含Tab页
      if (
        formatmenu[i].tab != null &&
        formatmenu[i].tab != undefined &&
        formatmenu[i].tab.length > 0
      ) {
        window.localStorage.removeItem("menuTabObj");
        window.localStorage.setItem(
          "menuTabObj",
          JSON.stringify(formatmenu[i])
        );
        flag = false;
        break;
      }
      //如果二级菜单--包含Tab页，暂时只处理这两级
      for (
        var j = 0;
        formatmenu[i].children != null &&
        j < formatmenu[i].children.length &&
        flag;
        j++
      ) {
        if (
          formatmenu[i].children[j].tab != null &&
          formatmenu[i].children[j].tab != undefined &&
          formatmenu[i].children[j].tab.length > 0
        ) {
          window.localStorage.removeItem("menuTabObj");
          window.localStorage.setItem(
            "menuTabObj",
            JSON.stringify(formatmenu[i].children[j])
          );
          flag = false;
        }
      }
    }
    return menus;
  }

  $http({
    url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
    method: "POST",
    headers: {
      token: window.localStorage.getItem("token")
    }
  })
    .success(function(data) {
      //将菜单存入浏览器缓存
      window.localStorage.setItem("menus", JSON.stringify(data.data));
      var root = _.groupBy(data.data, "menuValue");
      var menus = [];
      var current = root[system];
      var isRoot = false;
      _.each(current, function(menu) {
        if (menu.authority == 0) {
          return;
        }
        isRoot = !_.size(
          _.filter(current, function(item) {
            if (item.authority == 0) {
              return;
            }
            if (menu.parentId == item.id) {
              item.children = item.children || [];
              if (menu.menuType == 2) {
                item.tab = item.tab || [];
                item.tab[item.tab.length] = menu;
              } else {
                item.state = "closed";
                item.children[item.children.length] = menu;
              }
            }
            item.text = item.menuName;
            item.url = item.url != "#" ? item.url : null;
            return menu.parentId == item.id;
          })
        );
        menus = isRoot ? [menu] : menus;
      });
      if (menus.length) {
        menus[0].state = "open";
      }
      var formatmenu = menus.length > 0 ? menus[0].children : null;

      //每次刷新Tab页，以防权限变动
      var flag = true;
      //一级菜单
      for (
        var i = 0;
        formatmenu != null && i < formatmenu.length && flag;
        i++
      ) {
        //如果一级菜单就是包含Tab页
        if (
          formatmenu[i].tab != null &&
          formatmenu[i].tab != undefined &&
          formatmenu[i].tab.length > 0
        ) {
          window.localStorage.removeItem("menuTabObj");
          window.localStorage.setItem(
            "menuTabObj",
            JSON.stringify(formatmenu[i])
          );
          flag = false;
          break;
        }
        //如果二级菜单--包含Tab页，暂时只处理这两级
        for (
          var j = 0;
          formatmenu[i].children != null &&
          j < formatmenu[i].children.length &&
          flag;
          j++
        ) {
          if (
            formatmenu[i].children[j].tab != null &&
            formatmenu[i].children[j].tab != undefined &&
            formatmenu[i].children[j].tab.length > 0
          ) {
            window.localStorage.removeItem("menuTabObj");
            window.localStorage.setItem(
              "menuTabObj",
              JSON.stringify(formatmenu[i].children[j])
            );
            flag = false;
          }
        }
      }
    })
    .then(
      function(res) {
        var data = res.data;
        if (data.httpCode != 200) {
          jBox.tip(data.msg);
          swal.close();
          window.localStorage && window.localStorage.clear();
          $state.go("login").then(function() {
            // setTimeout(function() {
            //   window.location.reload();
            // }, 200);
          });
          return;
        }
        $scope.systems = formattedSystems(data.data);
      },
      function() {}
    );
  //监管系统获取数据字典及用户信息方法
  // $http
  //   .post("/sofn-asms-web/sysDict/getAllSysDict", "", {
  //     headers: {
  //       token: window.localStorage.getItem("token")
  //     }
  //   })
  //   .then(
  //     function(res) {
  //       window.localStorage.setItem(
  //         "asmsAllSysDictAndUserInfo",
  //         JSON.stringify(res.data.data)
  //       );
  //     },
  //     function(res) {}
  //   );

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
