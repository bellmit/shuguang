//路由配置
var loc = document.location;
var hash = loc.hash;
var path = hash.substring(1);
var stateUrl = path.substring(1).replace("index/content/", "index.content.");
var url = path.substring(path.indexOf("index/content") + 14);
var controllerName = path.substring(path.lastIndexOf("/") + 1);
var myapp = angular.module("myapp", [
  "ui.router",
  "oc.lazyLoad",
  "ngMessages",
  "720kb.tooltips"
]);
//设置登录界面
var interceptUrl = window.location.href;
if (interceptUrl.indexOf(".com") > -1) {
  interceptUrl = interceptUrl.split("//")[1].split(".")[0];
  window.localStorage.setItem("webappSysname", interceptUrl);
} else {
  interceptUrl = "Check";
}



myapp
  .config([
    "$stateProvider",
    "$urlRouterProvider",
    "$httpProvider",
    function($stateProvider, $urlRouterProvider, $httpProvider) {
      $httpProvider.defaults.timeout = 15000;
      //initialize get if not there
      if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
      }
      //disable IE ajax request caching
      $httpProvider.defaults.headers.get["If-Modified-Since"] =
        "Mon, 26 Jul 1997 05:00:00 GMT";
      // extra
      $httpProvider.defaults.headers.get["Cache-Control"] = "no-cache";
      $httpProvider.defaults.headers.get["Pragma"] = "no-cache";
      $httpProvider.interceptors.push("httpInterceptor");
      // 默认地址
      $urlRouterProvider.when("", "/login");
      //判定路径方式执行指定默认登录界面
      $stateProvider
        .state("login", {
          url: "/login",
          views: {
            login: {
              templateUrl: "webapp/login/login.html",
              controller: "login",
              resolve: {
                deps: [
                  "$ocLazyLoad",
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load("webapp/login/login.js");
                  }
                ]
              }
            },
            header: {
              template: ""
            },
            footer: {
              template: ""
            },
            mid: {
              template: ""
            }
          }
        })
        .state("appcx", {
          url: "/appcx",
          views: {
            appcx: {
              templateUrl: "webapp/qry/zsmquery/appcx.html",
              controller: "appcx",
              resolve: {
                deps: [
                  "$ocLazyLoad",
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load("webapp/qry/zsmquery/appcx.js");
                  }
                ]
              }
            },
            header: {
              template: ""
            },
            footer: {
              template: ""
            },
            mid: {
              template: ""
            }
          }
        })
        .state("printSubjEntCreditInfo", {
          url: "/printSubjEntCreditInfo",
          views: {
            appcx: {
              templateUrl: "webapp/asms/badrecode/printSubjEntCreditInfo.html",
              controller: "printSubjEntCreditInfo",
              resolve: {
                deps: [
                  "$ocLazyLoad",
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load(
                      "webapp/asms/badrecode/printSubjEntCreditInfo.js"
                    );
                  }
                ]
              }
            },
            header: {
              template: ""
            },
            footer: {
              template: ""
            },
            mid: {
              template: ""
            }
          }
        })
        .state("index", {
          url: "/index",
          views: {
            login: {
              template: ""
            },
            header: {
              templateUrl: "include/header.html",
              controller: "header"
            },
            footer: {
              templateUrl: "include/footer.html",
              controller: "footer"
            },
            mid: {
              templateUrl: "include/mid.html",
              controller: "mid"
            }
          }
        })
        .state("index.content", {
          url: "/content",
          views: {
            content: {
              templateUrl: "include/content.html",
              controller: function($state, $scope, $rootScope) {
                // console.log('$scope', $scope.$ChildScope, $rootScope);
                $rootScope.$on("$stateChangeSuccess", function() {
                  document.body.scrollTop = document.documentElement.scrollTop = 0;
                });
                $(document).off("focus.date");
                $(document).on("focus.date", ".Wdate", function() {
                  var $datePickerScope = angular.element($(this)).scope();
                  // console.log('$datePickerScope', $datePickerScope);
                  var hasNext = $(this)
                    .next("input")
                    .hasClass("Wdate");
                  var hasPrev = $(this)
                    .prev("input")
                    .hasClass("Wdate");

                  var nextInput = hasNext ? $(this).next("input") : null;
                  var prevInput = hasPrev ? $(this).prev("input") : null;

                  var model = $.trim($(this).attr("ng-model"));
                  if (model == "") {
                    return;
                  }
                  var settings = $(this).data();
                  // console.log('settings', settings);
                  var configs = {};

                  for (setting in settings) {
                    if (~["dateFmt", "minDate", "maxDate"].indexOf(setting)) {
                      configs[setting] = settings[setting];
                    }
                  }

                  var models = model.split(".");
                  var currentModel = $datePickerScope;
                  models.map(function(item, index) {
                    currentModel =
                      index < models.length - 1
                        ? $datePickerScope[item]
                        : currentModel;
                  });
                  var lastModel = models[models.length - 1];
                  // maxDate: '#F{$dp.$D(\'taskEndTime\')}', minDate: '%y-%M-%d'
                  var defaults = {
                    onpicked: function() {
                      $scope.$apply(function() {
                        currentModel[lastModel] = $dp.cal.getDateStr();
                        if (hasNext) {
                          nextInput.data("minDate", $dp.cal.getDateStr());
                          nextInput.trigger("focus");
                        } else if (hasPrev) {
                          prevInput.data("maxDate", $dp.cal.getDateStr());
                        }
                      });
                    }
                  };
                  $.extend(true, defaults, configs);
                  // console.log('defaults', defaults);
                  WdatePicker(defaults);
                }); //日期控件与angl验证处理
              }
            },
            menu: {
              templateUrl: "include/menu.html",
              controller: "menu"
            }
          }
        });
      if (stateUrl.indexOf("index.content.") >= 0) {
        $stateProvider.state(stateUrl, {
          url: "/" + url,
          views: {
            "": {
              templateUrl: "webapp/" + url + ".html",
              controller: controllerName,
              resolve: {
                deps: [
                  "$ocLazyLoad",
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load("webapp/" + url + ".js");
                  }
                ]
              }
            }
          }
        });
      } else if (
        stateUrl != "" &&
        stateUrl != null &&
        stateUrl != undefined &&
        stateUrl != "login" &&
        stateUrl != "appcx" &&
        stateUrl != "printSubjEntCreditInfo"
      ) {
        var thisUrl = stateUrl.split("/");
        thisUrl = thisUrl[thisUrl.length - 1];
        $stateProvider.state(stateUrl, {
          url: "/" + stateUrl,
          views: {
            login: {
              templateUrl: "webapp/" + stateUrl + ".html",
              controller: thisUrl,
              resolve: {
                deps: [
                  "$ocLazyLoad",
                  function($ocLazyLoad) {
                    return $ocLazyLoad.load("webapp/" + stateUrl + ".js");
                  }
                ]
              }
            },
            header: {
              template: ""
            },
            mid: {
              template: ""
            }
          }
        });
      }
    }
  ])
  .controller("index", function($scope, $state) {})
  .controller("header", function($scope, $http, $state) {
    $scope.account = $.cookie("account");
    $scope.userName = $.cookie("userName");
    $scope.systemName = window.localStorage.getItem("webappSysname");
    httpurl();
    // $scope.go = function() {
    // 	var token = window.localStorage.getItem("token");
    // 	if (token == null || token == "") {
    // 		swal("系统提示", "获取登录信息失败，请重新登录", "error");
    // 		return;
    // 	}
    // 	window.location.href = "/Fxjc/?token=" + token;
    // };

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

    function httpurl() {
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
            if (data.httpCode == 401) {
              return;
            }
            if (data.httpCode != 200) {
              swal("", data.msg, "warning");
              return;
            }
            var systems = formattedSystems(data.data);
            $scope.systems = systems;
            return data;
          },
          function(err) {}
        );
    }

    function formattedSystems(data) {
      var systems = _.groupBy(data, "menuValue");
      // console.log('systems', systems);
      return systems;
    }

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
    }

    $scope.sysName = function(name) {
      window.localStorage.setItem("webappSysname", name);
      httpurl(name.toUpperCase()).then(function(res) {
        var systems = formattedSystems(res.data);
        $scope.systems = systems;
        var menus = formattedMenuByName(systems[name.toUpperCase()]);
        $scope.renderTree(menus[0].children, $state);
        firstLink = null;
        firstLink = getFirstLink(menus[0].children);
        // console.log('firstLink', firstLink);
        $state.go(firstLink);
      });
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
      var formatmenu = menus[0].children;
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
          window.localStorage.setItem("menuTabObjParentId", formatmenu[i].id);
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
              "menuTabObjParentId",
              formatmenu[i].children[j].id
            );
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

    $scope.goDetailUserCenter = function(currentOrg, currentUser) {
      var userType = currentUser.userType;
      var orgType = currentOrg.orgType;
      var url = "";
      if (userType == "SYSADMIN") {
        //管理员
        switch (orgType) {
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
        //非管理员
        switch (orgType) {
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

    $(document).on("click", "#userListForAll li", function(ev) {
      ev.preventDefault();
      var interceptUrl = localStorage.getItem("webappSysname");

      // interceptUrl = interceptUrl.split("index/content/")[1].split("/")[0];
      window.localStorage.setItem(
        "historyUrl",
        window.location.href
          .split("/#/")[1]
          .replace("index/content/", "index.content.")
      );
      var ev = ev || window.event;
      var target = ev.target || ev.srcElement;
      if (target.nodeName.toLowerCase() == "a") {
        switch (target.name) {
          case "个人中心":
            switch (interceptUrl) {
              case "asms":
                //当前用户所在机构
                var currentOrg;
                //当前用户
                var currentUser;
                $.ajax({
                  url: "/sofn-asms-web/subjSupervise/findCurrentUserByToken",
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
                    currentOrg = json.user.organization;
                    currentUser = json.user;
                    $scope.goDetailUserCenter(currentOrg, currentUser);
                  },
                  fail: function(data) {
                    alert("失败");
                  }
                });
                break;
              case "ales":
                //当前用户所在机构
                var currentOrg;
                //当前用户
                var currentUser;
                $.ajax({
                  url: "/sofn-ales-web/subjEnforceLaw/findCurrentUserByToken",
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
                    currentOrg = json.user.organization;
                    currentUser = json.user;
                    $scope.goDetailUserCenter(currentOrg, currentUser);
                  },
                  fail: function(data) {
                    alert("失败");
                  }
                });
                break;
              case "dgap":
                //$state.go("")
                $state.go("dgap/acctInfo/dgapUserAcct");
                break;
              case "ads":
              case "monitor":
                $.ajax({
                  url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
                  type: "post",
                  dataType: "json",
                  data: {
                    token: window.localStorage.getItem("token")
                  },
                  success: function(data) {
                    var userType = data.user.userType;
                    if (userType == "SYSADMIN") {
                      //管理员
                      $state.go("monitor/acctInfo/adminAcct");
                    } else {
                      //非管理员
                      $state.go("monitor/acctInfo/userAcct");
                    }
                  },
                  fail: function(data) {
                    //请求失败
                  }
                });
                break;
              case "sys":
                //当前用户所在机构
                var currentOrg;
                //当前用户
                var currentUser;
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
                    currentOrg = json.org;
                    currentUser = json.user;
                    $scope.goDetailUserCenter(currentOrg, currentUser);
                  },
                  fail: function(data) {
                    alert("失败");
                  }
                });
                break;
              case "qry":
                //当前用户所在机构
                var currentOrg;
                //当前用户
                var currentUser;
                $.ajax({
                  url: "/sofn-qry-web/subject/findCurrentUserByToken",
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
                    currentOrg = json.user.organization;
                    currentUser = json.user;
                    $scope.goDetailUserCenter(currentOrg, currentUser);
                  },
                  fail: function(data) {
                    alert("失败");
                  }
                });
                break;
              case "tts":
                var isMain = localStorage.getItem("isMain");
                if (isMain && isMain > 0) {
                  $state.go("account/managerInfo");
                } else {
                  $state.go(
                    "index.content.tts/register/accountInfo/noManagerInfo"
                  );
                }
                break;
            }
            break;
          case "退出登录":
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
                // $state.go("login/login").then(function () {
                //     setTimeout(function () {
                //         window.location.reload();
                //     }, 200);
                // });
                window.location.href = "/";
              }
            );
            break;
          case "帮助中心":
            $("#helpList").modal("show");
            $.ajax({
              url: "/sofn-sys-web/document/queryList",
              type: "post",
              dataType: "json",
              data: {
                token: window.localStorage.getItem("token"),
                enableFlag: "Y",
                applyTo: window.localStorage.getItem("webappSysname"),
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
                  html +=
                    "<em>" + timestampToDate(result[i].createTime) + "</em>";
                  html += "</li>";
                }

                $("#helpList .message-list").html(html);
              },
              fail: function(data) {
                alert("获取数据失败");
              }
            });
            break;
          case "注销登录":
            alert("注销登录");
            $state.go("login");
          //$http({
          //    url: "/sofn-sys-web/logout",
          //    method: "post",
          //    dataType: "json",
          //    header: {
          //        token: window.localStorage.getItem("token"),
          //        type: "pc"
          //    }
          //}).success(function(json) {
          //    if (json.httpCode != 200) {
          //        return;
          //    }
          //    //localStorage.clear();
          //    $state.go("login/login" + interceptUrl);
          //
          //}).
          //error(function(data) {
          //    alert("失败");
          //});
          ////$state.go("login/login"+interceptUrl);
          //break;
        }
      }
    });
  })
  .controller("footer", function($scope, $http) {})
  .controller("mid", function($scope, $http) {})
  .controller("menu", function($scope, $http, $state) {
    //        easyui
    //    菜单配置
    //    初次默认角色
    if (window.localStorage.getItem("menuObj") == null) {
      window.localStorage.setItem(
        "menuObj",
        JSON.stringify({
          id: "22",
          remark: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          delFlag: "N",
          menuName: "角色管理",
          parentId: "2",
          isSubitem: null,
          menuType: "1",
          menuValue: "SYS",
          numbers: "11",
          url: "index.content.sys/role/roleList",
          image: null,
          describes: null,
          status: null,
          reservedField1: null,
          reservedField2: null,
          authority: "2",
          children: [],
          text: "角色管理",
          state: "open",
          domId: "_easyui_tree_2",
          target: {}
        })
      );
    }

    function httpurl(obj) {
      //向后台发起请求获取数据
      $.ajax({
        url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
        method: "post",
        data: {
          type: obj
        },
        headers: {
          Accept: "*/*",
          token: window.localStorage.getItem("token")
        },
        success: function(data) {
          if (data.httpCode != 200) {
            swal("", data.msg, "warning");
            return;
          }
          var root = _.groupBy(data.data, "menuValue");
          // console.log('root', root);
          var menus = [];
          var current = root[obj];
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
            // console.log('isRoot', isRoot);
          });

          if (menus.length) {
            menus[0].state = "open";
          }
          // console.log('menus', menus);
          // return;
          var formatmenu = menus.length
            ? menus[0].children
            : jsontree(data.data);
          //每次刷新Tab页，以防权限变动
          var flag = true;
          var menuTabObjParentId = window.localStorage.getItem(
            "menuTabObjParentId"
          );
          //一级菜单
          for (
            var i = 0;
            formatmenu != null && i < formatmenu.length && flag;
            i++
          ) {
            //如果一级菜单就是包含Tab页
            if (
              formatmenu[i].id == menuTabObjParentId &&
              formatmenu[i].id == menuTabObjParentId &&
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
                formatmenu[i].children[j].id == menuTabObjParentId &&
                formatmenu[i].children[j].id == menuTabObjParentId &&
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
                  localStorage.setItem(
                    "webappSysname",
                    nextSystem.toLowerCase()
                  );
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
          $(".tree-node").each(function(i, o) {
            $(o).click(function() {
              window.sessionStorage.clear();
            });
          });
        }
      });
    }

    window.httpurl = httpurl;

    // 数据处理（json转换树）
    function jsontree(obj) {
      // console.log('obj', obj);
      //alert(JSON.stringify(obj));
      //树型list
      var treeData = [];
      for (var i = 0; i < obj.length; i++) {
        var o = obj[i];
        //过滤禁用菜单 如果菜单权限为禁用，则不做任何处理
        if (o.authority == 0) continue;
        o.children = [];
        o.text = o.menuName;
        o.url = o.url != "#" ? o.url : null;
        //如果parentId为空，则代表顶级
        if (o.id.length < 2) {
          treeData[treeData.length] = o;
          //alert(JSON.stringify(o));
        }
        for (var j = 0; j < obj.length; j++) {
          var m = obj[j];
          //过滤禁用菜单 如果菜单权限为禁用，则不做任何处理
          if (m.authority == 0) continue;

          //obj.length
          //如果pid与id相同，则表示为子级
          if (m.parentId == o.id) {
            //如果菜单类型为2(Tab)则添加自定义属性
            if (m.menuType == 2) {
              if (o.tab == null) o.tab = [];
              o.tab[o.tab.length] = m;
            } else {
              o.children[o.children.length] = m;
              o.state = "closed";
            }
          }
        }
        //根节点默认打开
        if (o.parentId == 1) {
          o.state = "open";
        }
      }
      // console.log('treeData', treeData);
      return treeData.length ? treeData[0].children : treeData;
      // return treeData;
    }

    var sessionMenuUrl = window.localStorage.getItem("webappSysname");
    if (
      sessionMenuUrl != null &&
      sessionMenuUrl != undefined &&
      sessionMenuUrl != ""
    ) {
      thisMenuUrl = sessionMenuUrl;
      if (thisMenuUrl == "monitor") {
        thisMenuUrl = "ads";
      }
      httpurl(thisMenuUrl.toUpperCase()); //调取请求
    } else {
      var a = window.location.href;
      var b = a.split("/index/content/")[1];
      var c = b.split("/")[0];
      thisMenuUrl = c;
      if (thisMenuUrl == "monitor") {
        thisMenuUrl = "ads";
      }
      httpurl(thisMenuUrl.toUpperCase()); //调取请求
    }
    //请求静态目录
    // $("#tt").tree({
    //    //lines:true,
    //    url:"include/navData/"+thisMenuUrl+"_data.json",
    //    //     data:menusJson,
    //    onClick:function(node){
    //        //设置
    //        if(node.state=="closed"){
    //            $("#tt").tree("expand",node.target).parent().siblings().children().tree("collapse",node.target);
    //        }else{
    //            $("#tt").tree("collapse",node.target);
    //        }
    //        //node.menuUrl!=null && $state.go(node.menuUrl);
    //        if (node.menuUrl==null) {
    //            return;
    //        }
    //        ~node.menuUrl.indexOf('index.content') ? $state.go(node.menuUrl) : window.location.href = node.menuUrl;

    //    },
    //    onLoadSuccess:function(){//隐藏根节点
    //        $("#"+$('#tt').tree('getRoot').domId).hide();
    //    }
    // });

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
          swal("", "失败", "error");
        }
      });
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
    //根据菜单名获取该页菜单的权限 1为只读,2为读写
    window.getAuthorityByMenuName = function(menuName) {
      var a = JSON.parse(window.localStorage.getItem("menus"));
      for (var i = 0; i < a.length; i++) {
        if (a[i].menuName == menuName) {
          return a[i].authority;
        }
      }
    };
    var md = new Date(); //第二个输入框最大值的全局变量
    //第一个输入框选择好日期的时候操作
    window.pickedFunc = function() {
      var Y = $dp.cal.getP("y"); //用内置方法获取到选中的年月日
      var M = $dp.cal.getP("M");
      var D = $dp.cal.getP("d");
      M = parseInt(M, 10) - 1;
      D = parseInt(D, 10); //字符串的数字转换成int再运算。并且如果超过30天，也能自动处理。
      var d = new Date();
      d.setFullYear(Y, M, D); //设置时间
      var nowDate = new Date();
      if (nowDate > d) {
        //现在的时间比较，如果算出来的值大于现在时间，修改全局变量md为现在时间。
        md = nowDate;
      } else {
        //全局变量设置为算出来的值得
        var month = d.getMonth() + 1; //月份的范围是（0到11）;
        md = d.getFullYear() + "-" + month + "-" + d.getDate(); //直接把d给过去会有问题，所以拼成字符串发过去
      }
      //
      var nextInput = $(this).next(".Wdate");
      nextInput.trigger("focus");
      nextInput = $(this)
        .parent()
        .next()
        .find(".Wdate");
      nextInput.trigger("focus");
    };
    //第一个清空的时候的操作
    window.clearedFunc = function() {
      md = new Date();
    };
    //给第二个输入框定义规则
    window.endDatePickerRule = function(ele) {
      WdatePicker({ el: ele, minDate: md });
    };

    // 菜单读写权限常量
    window.MENU_READWRITE_AUTHORITY = 2;
    // 菜单只读权限常量
    window.MENU_READONLY_AUTHORITY = 1;

    /**
       * 处理图片显示
       * @param o 目标对象
       * @param picArray 目标对象的图片属性
       */
      window.handlePicUrl = function(o, picArray) {
          // 服务器访问前缀
          var PrefixUrl = "/sofn-sys-web/sysTemplate/downPic?fileUrl=";
          // 该ip地址为追溯文件服务器地址,监管端无法通过后端服务器访问
          var exceptionIp = "//202.127.45.189";
          for (var i = 0; i < picArray.length; i++) {
              if (o[picArray[i]]) {
                  if (o[picArray[i]].indexOf(PrefixUrl) !== -1) {
                      break;
                  }
                  if (o[picArray[i]].indexOf(exceptionIp) === -1) {
                      // 增加服务器访问前缀
                      o[picArray[i]] = PrefixUrl + o[picArray[i]];
                      // 直接访问指定http协议, https会被浏览器拦截
                  } else if (o[picArray[i]].indexOf("http") === -1) {
                      o[picArray[i]] = "http:" + o[picArray[i]];
                  }
              }else {
                  o[picArray[i]] = null;
              }
          }
      };

      /**
       * 处理单个图片地址
       *
       * @param picUrl
       * @returns {*}
       */
      window.handleOnePicUrl = function(picUrl) {
          // 服务器访问前缀
          var PrefixUrl = "/sofn-sys-web/sysTemplate/downPic?fileUrl=";
          // 该ip地址为追溯文件服务器地址,监管端无法通过后端服务器访问
          var exceptionIp = "//202.127.45.189";
          if (picUrl.indexOf(PrefixUrl) !== -1) {
              return picUrl;
          }
          if (picUrl.indexOf(exceptionIp) === -1) {
              return PrefixUrl + picUrl;
          } else if (picUrl.indexOf("http") === -1) {
              return  "http:" + picUrl;
          }
      };

    /**
     * 获取文件下载链接
     * @param fileName 文件名
     * @param fileAddress 文件存储地址
     */
    window.getFileDownloadHref = function(fileName, fileAddress) {
      var template = _.template(
        "/sofn-sys-web/sysTemplate/fileDownload?fileUrl=<%= fileAddress %>&fileName=<%= fileName %>"
      );

      return template({
        fileAddress: encodeURIComponent(fileAddress),
        fileName: encodeURIComponent(fileName)
      });
    };
  });
