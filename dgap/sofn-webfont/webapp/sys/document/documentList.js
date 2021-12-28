/**
 * Created by Administrator on 2018/2/1/001.
 */
var myapp = angular.module("myapp", []);
myapp.controller("documentList", function($scope, $http, $state) {
    // 查询条件
    $scope.queryCondition = {
        title:"",
        type:"",
        applyTo:"",
        createBy:"",
        createTime:"",
        enableFlag:""
    }
    $scope.docTypeOptions = []; // 文档类别下拉选项
    $scope.systemOptions = {}; // 适用系统下拉选项
    $scope.permission = {
        add: false, // 新增权限
        modify: false // 修改权限
    }
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
        }
        /**
         *判断当前用户是否具有新增权限
         */
        if (isSuperAdmin) { // 超级管理员有新增权限
            permission.add = true;
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
            initTable();
        });
    } else {
        $scope.systemOptions = JSON.parse(window.sessionStorage.getItem('systemOptionsCache'));
        initTable();
    }
    // 日期插件选择日期回调函数
    window.createTimeOnPicked = function(dp) {
        $scope.$evalAsync(function(){
            $scope.queryCondition.createTime = dp.cal.getDateStr().trim();
        });
    }
    // 日期插件清空日期回调函数
    window.createTimeOnCleared = function() {
        $scope.$evalAsync(function(){
            $scope.queryCondition.createTime = "";
        });
    }

    // 生成img标签字符串
    function generateImgTagString(id, enableFlag) {
        var imgSrc = enableFlag == 'Y' ? "assets/imgs/icon/sys-on.png" : "assets/imgs/icon/sys-off.png";
        var template =  _.template('<img id="img<%= id %>" src="<%= path %>" status="<%= status %>" onclick="changeEnableStatus(\'<%= id %>\', event)" />');
        return template({id: id, status: enableFlag, path: imgSrc});
    }
    // 设置dataTable数据操作
    function settingTableOperation(id) {
        var link = '<a style="cursor: pointer" class="color mr15" onclick="viewDocument(\'<%= id %>\')">查看</a>';
        if ($scope.permission.modify) {
            link += '<a style="cursor: pointer" class="color mr15" onclick="modifyDocument(\'<%= id %>\')">修改</a>';
        }
        var template = _.template(link);

        return template({id: id});
    }
    // 查看文档
    window.viewDocument = function(id) {
        window.sessionStorage.setItem("documentId", id);
        window.sessionStorage.setItem("docActionType", "view");
        $state.go("index.content.sys/document/documentEdit");
    }
    // 修改文档
    window.modifyDocument = function(id) {
        window.sessionStorage.setItem("documentId", id);
        window.sessionStorage.setItem("docActionType", "modify");
        $state.go("index.content.sys/document/documentEdit");
    }
    // 更新指定记录的启用状态
    window.changeEnableStatus = function(id, e) {
        var $enableFlagImgObj = $('#img' + id);
        var status = $enableFlagImgObj.attr("status");
        var updateStatus = status == 'Y' ? 'N' : 'Y';
        var imgSrc = updateStatus == 'Y' ? "assets/imgs/icon/sys-on.png" : "assets/imgs/icon/sys-off.png";
        //如果提供了事件对象，
        if (e && e.stopPropagation) {
            // 因此它支持W3C的stopPropagation()方法
            e.stopPropagation();
        } else {
            //否则，我们需要使用IE的方式来取消事件冒泡
            window.event.cancelBubble = true;
        }

        $.ajax({
            url: "/sofn-sys-web/document/updateEnableFlag",
            headers: {token: window.localStorage.getItem("token")},
            data: {
                "id": id,
                "enableFlag": updateStatus
            },
            type: "post"
        }).success(function(data) {
            if (data.httpCode == 200) {
                $enableFlagImgObj.attr("status", updateStatus);
                $enableFlagImgObj.attr("src", imgSrc);
                jBox.tip("修改成功", 'info');
            } else {
                // console.log(data);
                jBox.tip("修改失败", 'error');
            }
        }).error(function(data) {
            // console.log(data);
            ajaxErrorHandler(data.status);
        });
    }

    // 初始化表格数据
    function initTable() {
        /**
         * 分页展示数据
         */
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-sys-web/document/queryListByToken",
                headers: {token: window.localStorage.getItem("token")},
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.title = $scope.queryCondition.title;
                    params.type = $scope.queryCondition.type;
                    params.applyTo = $scope.queryCondition.applyTo;
                    params.createBy = $scope.queryCondition.createBy;
                    params.createTime = $scope.queryCondition.createTime;
                    params.enableFlag = $scope.queryCondition.enableFlag;
                },
                type: "post"
            },
            columns: [{
                title: function (data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAll">';
                },
                data: "checkOne",
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" name="checkOne">';
                    }
                    return data;
                },
                className: "text-center",
                width: "5%"
            }, {
                title: "序号",
                data: function (data, type, row, meta) {
                    return meta.row + 1
                },
                className: "text-center",
                width: "5%"
            }, {
                data: "title",
                title: "标题",
                className: "text-center",
                width: "30%"
            }, {
                data: "type",
                title: "类别",
                className: "text-center",
                width: "12%"
            }, {
                data: "applyTo",
                title: "适用系统",
                render: function (data, type, row) {
                    return $scope.systemOptions[data];
                },
                className: "text-center",
                width: "10%"
            }, {
                data: "createBy",
                title: "发布人",
                className: "text-center",
                width: "10%"
            }, {
                data: "createTime",
                title: "发布时间",
                render: function (data, type, row) {
                    return new Date(data).format("yyyy-MM-dd");
                },
                className: "text-center",
                width: "12%"
            }, {
                data: "enableFlag",
                title: "启用",
                render: function (data, type, row) {
                    return generateImgTagString(row.id, data);
                },
                className: "text-center",
                width: "5%"
            }, {
                data: "id",
                title: "操作",
                render: function (data, type, row) {
                    return settingTableOperation(data);
                },
                className: "text-center",
                width: "11%"
            }]
        });
    }
    // 查询文档
    $scope.queryList = function() {
        $("#grid").dataTable().api().ajax.reload();
    }
    // 新增文档
    $scope.addDocument = function() {
        window.sessionStorage.setItem("documentId", "");
        window.sessionStorage.setItem("docActionType", "add");
        $state.go("index.content.sys/document/documentEdit");
    }
});