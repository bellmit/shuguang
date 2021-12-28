/**
 * Created by Administrator on 2018/2/1/001.
 */

var myapp = angular.module("myapp", []);
myapp.filter('toTrustHtml', ['$sce', function($sce){
    return function(text) {return $sce.trustAsHtml(text);}
}]);
myapp.controller("documentEdit", function($scope, $http, $state) {
    $scope.submittingValidate = false; // 提交校验标识，控制前端显示校验提示信息
    $scope.document = {
        id: "",
        title: "",
        content: "",
        type: "",
        applyTo: ""
    }

    var actionTypes = {
        add: "新增文档",
        modify: "修改文档",
        view: "查看文档"
    }
    $scope.docTypeOptions = []; // 文档类别下拉选项
    $scope.systemOptions = {}; // 适用系统下拉选项
    $scope.actionType = window.sessionStorage.getItem("docActionType"); // 操作类别（add:新增，modify:修改，view:查看）

    $scope.permission = {
        action: false, // 操作权限，不具有权限的用户不能保存文档
        add: false, // 新增权限
        modify: false // 修改权限
    }

    $scope.headerText = actionTypes[$scope.actionType] == null ? "新增文档" : actionTypes[$scope.actionType];

    // 设置用户权限
    function settingPermission(user, permission) {
        var isSuperAdmin = user.userType == "SUPERADMIN";
        var isMinisterialLevelAdmin = (user.userType == "SYSADMIN" && user.orgLevel == "1");
        /**
         * 判断当前用户是否具有修改权限
         *
         */
        if (isSuperAdmin || isMinisterialLevelAdmin) { // 超级管理员或者部级管理员有修改权限
            permission.modify = true;
            if ($scope.actionType == "modify") {
                permission.action = true;
            }
        }
        /**
         *判断当前用户是否具有新增权限
         */
        if (isSuperAdmin) { // 超级管理员有新增权限
            permission.add = true;
            if ($scope.actionType == "add") {
                permission.action = true;
            }
        }
    }

    // 缓存当前登录用户信息
    if (_.isEmpty(window.sessionStorage.getItem("currentUserCache"))) {
        $http({
            url: "/sofn-sys-web/user/getCurrentUser",
            method: "post",
            data: {},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            window.sessionStorage.setItem('currentUserCache', JSON.stringify(json.currentUser)); //缓存当前登录用户信息
            settingPermission(json.currentUser, $scope.permission);
        });
    } else {
        var currentUser = JSON.parse(window.sessionStorage.getItem('currentUserCache'));
        settingPermission(currentUser, $scope.permission);
    }

    // 获取文档类别下拉选项
    if (typeof(window.sessionStorage.docTypeOptionsCache) == "undefined") {
        $http({
            url: "/sofn-sys-web/document/getDocTypeOptions",
            method: "get",
            data: {},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            $scope.docTypeOptions = json.data;
            window.sessionStorage.setItem('docTypeOptionsCache', JSON.stringify(json.data)); //缓存文档类别下拉选项字典数据
        });
    } else {
        $scope.docTypeOptions = JSON.parse(window.sessionStorage.getItem('docTypeOptionsCache'));
    }

    // 获取适用系统下拉选项
    if (typeof(window.sessionStorage.systemOptionsCache) == "undefined") {
        $http({
            url: "/sofn-sys-web/document/getSystemOptions",
            method: "get",
            data: {},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            $scope.systemOptions = json.data;
            window.sessionStorage.setItem('systemOptionsCache', JSON.stringify(json.data)); //缓存文档类别下拉选项字典数据
        });
    } else {
        $scope.systemOptions = JSON.parse(window.sessionStorage.getItem('systemOptionsCache'));
    }

    var editor = CKEDITOR.replace('documentContent'); // 富文本框初始化
    editor.on('loaded', function (event) {
        getDocument();
    });

    // 当富文本框的内容改变时刷新绑定变量的值
    editor.on('change', function (event) {
        $scope.$evalAsync(function () {
            $scope.document.content = event.editor.getData();
        });
    });
    // 当富文本框粘贴动作完成时刷新绑定变量的值
    editor.on('afterPaste', function(event) {
        $scope.$evalAsync(function () {
            $scope.document.content = event.editor.getData();
        });
    });
    // 点击富文本框保存按钮后保存文档
    editor.on('save', function (event) {
        event.cancel(); // 阻止默认的表单提交操作
        $scope.$evalAsync(function () {
            $scope.save($scope.documentForm.$valid);
        });
    });

    // 获取文档信息
    function getDocument() {
        // 操作类别是查看或者修改时，根据文档的ID获取文档信息
        if ($scope.actionType == "view" || $scope.actionType == "modify") {
            $http({
                url: "/sofn-sys-web/document/queryOneByToken",
                method: "post",
                data: {
                    id: window.sessionStorage.getItem("documentId"),
                    token: window.localStorage.getItem("token")
                },
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                if (json.httpCode == 200) {
                    $scope.document.id = json.data.id;
                    $scope.document.title = json.data.title;
                    $scope.document.content = json.data.content;
                    $scope.document.type = json.data.type;
                    $scope.document.applyTo = json.data.applyTo;

                    // https://docs.ckeditor.com/ckeditor4/latest/api/CKEDITOR_editor.html#method-setData
                    editor.setData($scope.document.content, {
                        callback: function() {
                            /**
                             * 解决有时setData方法调用后富文本框内容为空的问题（火狐浏览器常见此问题，其他浏览器暂未发现此问题）
                             * 参考文档：https://stackoverflow.com/questions/19319214/the-view-area-of-ckeditor-sometimes-shows-empty-at-the-start
                             */
                            if (this.getData() == "") {
                                this.setData($scope.document.content);
                            }
                        }
                    });
                    // console.log("文档信息获取成功");
                } else {
                    // console.log("文档信息获取失败");
                }
            }).error(function (json, status) {
                ajaxErrorHandler(status);
            });
        }
    }

    // 异步请求错误处理函数
    function ajaxErrorHandler(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }

    var submitting = false; // 正在提交数据的标识，用于防重复提交
    // 保存文档
    $scope.save = function(isValid) {
        var requestURL = "";
        $scope.submittingValidate = true;
        $scope.document.content = editor.getData();

        // 表单校验通过，保存文档信息
        if (isValid && (! submitting)) {
            submitting = true;
            $scope.submittingValidate = false;

            if ($scope.actionType == "add" && $scope.permission.add) {
                requestURL = "/sofn-sys-web/document/add";
            } else if ($scope.actionType == "modify" && $scope.permission.modify) {
                requestURL = "/sofn-sys-web/document/update";
            } else if (_.isEmpty($scope.actionType) && $scope.permission.add) {
                requestURL = "/sofn-sys-web/document/add";
            }

            if (requestURL != "") {
                $http({
                    url: requestURL,
                    method: "post",
                    data: $scope.document,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'token': window.localStorage.getItem("token")
                    },
                    transformRequest: function (data) {
                        return $.param(data);
                    }
                }).success(function (json) {
                    submitting = false;
                    if (json.httpCode == 200) {
                        jBox.tip('保存成功', 'success');
                        if ($scope.actionType == "add") {
                            $scope.document.title = "";
                            $scope.document.content = "";
                            // 清空富文本框内容
                            editor.setData("");
                            $state.go("index.content.sys/document/documentList");
                        }
                    } else {
                        jBox.tip('保存失败', 'error');
                    }

                }).error(function (json, status) {
                    submitting = false;
                    ajaxErrorHandler(status);
                });
            } else {
                jBox.tip('没有权限', 'error');
            }
        }
    }

    // 取消，返回文档列表页面
    $scope.cancel = function() {
        window.sessionStorage.removeItem("documentId");
        window.sessionStorage.removeItem("docActionType");
        $state.go("index.content.sys/document/documentList");
    }

    // 打开预览
    $scope.preview = function() {
        $scope.document.content = editor.getData();
        $("#previewModal").modal("show");
    }

    // 关闭预览
    $scope.close = function() {
        $("#previewModal").modal("hide");
    }
});
