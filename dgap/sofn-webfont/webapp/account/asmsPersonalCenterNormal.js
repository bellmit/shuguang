var myapp = angular.module("myapp", []);
angular
    .module("myapp", [])
    .controller("asmsPersonalCenterNormal", function(
        $scope,
        $http,
        $state,
        $timeout
    ) {
        $scope.callBack = function() {
            $state.go(window.localStorage.getItem("historyUrl"));
        };
        /** 第一部分，初始化时加载的内容 **/
        $("#changePwdForm").bootstrapValidator(); //初始化验证，必须有
        $("#subjSvCancelForm").bootstrapValidator(); //初始化验证，必须有
        $("#subjSvChangeForm").bootstrapValidator(); //初始化验证，必须有
        //获取用户信息
        $scope.user = {};
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/findCurrentUserByToken",
            method: "post"
        })
            .success(function(data) {
                $scope.user = data.user;
                $scope.currentOrg = data.user.organization;
                if ($scope.user.userType == "SUPERADMIN") {
                    $scope.user.userType = "超级管理员";
                } else if ($scope.user.userType == "SYSADMIN") {
                    $scope.user.userType = "用户管理员";
                } else if ($scope.user.userType == "SUBJADMIN") {
                    $scope.user.userType = "主体管理员";
                } else if ($scope.user.userType == "NORMAL") {
                    $scope.user.userType = "用户";
                }
            })
            .error(function(data) {
                
            });

        //请求机构数据
        $scope.subjSvChange = {};
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/subjSupervise/findSubjSvByUser",
            method: "post"
        })
            .success(function(data) {
                $scope.subjSv = data.subjSv;
                if ($scope.subjSv.svLevel == "0") {
                    $scope.subjSv.svLevel = "部级";
                } else if ($scope.subjSv.svLevel == "1") {
                    $scope.subjSv.svLevel = "省级";
                } else if ($scope.subjSv.svLevel == "2") {
                    $scope.subjSv.svLevel = "市级";
                } else if ($scope.subjSv.svLevel == "3") {
                    $scope.subjSv.svLevel = "县级";
                }
                window.localStorage.setItem("subjSvId", data.subjSv.id);
                $scope.subjSvChange = data.subjSvChange;
                $scope.subjSv.svAreaId = $.fn.Cityname($scope.subjSv.svAreaId);
                //初始化区域下拉框初始选项
                $.fn.mycity(
                    "sheng",
                    "shi",
                    "xian",
                    $scope.subjSvChange.svAreaId
                );
            })
            .error(function(data) {
                
            });

        //获取机构级别数据字典
        $scope.orgLevel = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "1",
            method: "post"
        })
            .success(function(data) {
                $scope.orgLevel = JSON.parse(data.data);
            })
            .error(function(data) {
                
            });
        //获取机构类别数据字典
        $scope.orgType = [];
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-asms-web/sysDict/getSysDictByCode",
            data: "0",
            method: "post"
        }).success(function(data) {
            $scope.orgType = JSON.parse(data.data);
            for (var i = 0; i < $scope.orgType.length; i++) {
                if ($scope.orgType[i].dictName == "监管机构") {
                    $scope.subjSvChange.svTypeId = $scope.orgType[i].id;
                    $("select[name='svType']").attr("disabled", "disabled");
                }
            }
        });

        /** 第二部分，主动调用才执行的部分 **/
        //显示密码修改模态框
        $scope.showPwdModal = function() {
            $("#pwdModal").modal("show");
            $("#changePwdForm")
                .data("bootstrapValidator")
                .resetForm(true);
        };
        //获取焦点清除验证
        $(".oldPwd").focus(function() {
            $(".oldPwd").css("border-color", "#2b542c");
            $(".dirVilidator").css("display", "none");
        });

        //修改密码
        $scope.changePwd = function() {
            //输入确认密码之后再修改新密码会不进行验证，这时候手动验证一下
            if (
                $("input[name='newPwd']").val() !=
                $("input[name='newPwdC']").val()
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
                url: "/sofn-asms-web/getPublicKey",
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
                        url: "/sofn-asms-web/subjSupervise/changePwd",
                        data: { ciphertext: dataStr, desKey: encodeKey },
                        method: "post"
                    })
                        .success(function(data) {
                            $("button").attr("disabled", false);
                            if (data.httpCode == "200") {
                                $http({
                                    url: "/sofn-asms-web/logout",
                                    method: "post",
                                    dataType: "json",
                                    headers: {
                                        token: window.localStorage.getItem(
                                            "token"
                                        ),
                                        type: "pc"
                                    }
                                })
                                    .success(function(json) {
                                        if (json.httpCode != 200) {
                                            return;
                                        }
                                        $(".dirVilidator").css(
                                            "display",
                                            "none"
                                        );
                                        $(".oldPwd").css(
                                            "border-color",
                                            "#2b542c"
                                        );
                                        jBox.tip("修改成功", "info");
                                        $("#pwdModal").modal("hide");
                                        localStorage.clear();
                                        $.cookie("account", "", {
                                            expires: -1
                                        }); // 清除cookie
                                        $.cookie("password", "", {
                                            expires: -1
                                        }); // 清除 cookie
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
                        html +=
                            "<em>" +
                            timestampToDate(result[i].createTime) +
                            "</em>";
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
