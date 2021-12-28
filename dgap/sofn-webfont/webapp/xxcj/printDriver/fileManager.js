var myapp = angular.module("myapp", []);
myapp.controller("fileManager", function($scope, $http, $state) {
    //查询
    $("#ToCheck").click(function() {
        var keyWord = $.trim($("input[name='templateName']").val());
        /*汉字1-30个，字母和数字1-60个*/
        var KW = /^[\u4e00-\u9fa5]$/;
        var len = 0;
        for (var i = 0; i < keyWord.length; i++) {
            if (KW.test(keyWord[i])) {
                len = len + 2;
            } else {
                len++;
            }
        }
        if (len > 60) {
            alert("1-60个字符或者1-30个汉字")
        } else {
            $("#grid").dataTable().api().ajax.reload();
        }
    });

    $scope.systemDict = {}; // 适用系统数据
    $scope.appSystemDict = {}; // app适用系统数据
    $scope.applySystemOptions = {}; //适用系统下拉框选项数据
    $scope.platformTypeOptions = {}; // 适用平台下拉框选项数据
    $scope.fileCategoryOptions = {}; // 文件分类下拉框选项数据
    $scope.applySystemOptionsUpdate = {};
    $scope.sysTemplate = {
        templateName : "",
        templateType : "",
        remark : "",
        templateFilename : "",
        fileAddress : "",
        flags : "",
        platform : "",
        category : ""
    };
    $scope.queryCondition = {
        templateName : "",
        system : "",
        platform : "",
        category : ""
    };
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
    $scope.customFilter = function(item) {
        if (item.dictValue == 'pc' || item.dictValue == 'app') {
            return true;
        } else {
            return false;
        }
    };
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('打印驱动上传');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    //新增
    $scope.listAdd = function() {
        $scope.sysTemplate.platform = "pc,app"; // 适用平台
        $scope.sysTemplate.category = "驱动"; // 文件分类
        $scope.sysTemplate.templateName = "lodop_" + new Date().format("yyyyMMddhhmmss");
        $scope.sysTemplate.templateType = "";
        $scope.sysTemplate.remark = "打印驱动安装程序";
        $scope.sysTemplate.templateFilename = ""
        $scope.sysTemplate.fileAddress = "";
        $scope.sysTemplate.flags = "tts"  // 适用系统
        if (vm.files.length > 0) {
            vm.files.length = 0;
        }
        $("#myModal").modal("show");
    }
    // 关闭新增弹框的回调函数
    $("#myModal").on("hide.bs.modal", function() {
        $scope.$evalAsync(function () {
            $scope.addFileSubmitting = false;
            $scope.addFileForm.$setPristine();
        });
    });

    // 根据适用平台的不同动态改变适用系统的下拉选项
    $scope.platformTypeOnChange = function(opt) {
        if (opt == 'add') {
            if ($scope.sysTemplate.platform == 'pc') {
                $scope.applySystemOptions = $scope.systemDict;
            } else if ($scope.sysTemplate.platform == 'app') {
                $scope.applySystemOptions = $scope.appSystemDict;
            } else {
                $scope.applySystemOptions = $scope.systemDict;
            }
        } else {
            if ($scope.sysTemplate.platform == 'pc') {
                $scope.applySystemOptionsUpdate = $scope.systemDict;
            } else if ($scope.sysTemplate.platform == 'app') {
                $scope.applySystemOptionsUpdate = $scope.appSystemDict;
            } else {
                $scope.applySystemOptionsUpdate = $scope.systemDict;
            }
        }
    };
    var vm = this;
    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-sys-web/sysTemplate/upload',
        //业务参数
        data: function() {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        accept: '*/*',
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            //	           if (!acceptFileTypes.test(file.type)) {
            //	               swal('', '只能上传word类型文档', 'warning');
            //	               return false;
            //	           }
            if (file.size > 100 * 1024 * 1024) {
                swal('', '文件大小不能超过100MB', 'warning');
                return false;
            }
            return true;
        }
    };

    vm.uploadOptions2 = {
        //接口地址
        url: '/sofn-sys-web/sysTemplate/upload',
        //业务参数
        data: function() {
            return {};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        accept: '*/*',
        //文件占位信息
        filter: function(file, info) {
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            var imageTypes = /^((jpg|jpeg|png|gif|bmp)|(image\/(jpg|jpeg|png|gif|bmp)))$/i;
            if (file.size > 100 * 1024 * 1024) {
                swal('', '文件大小不能超过100MB', 'warning');
                return false;
            }
            return true;
        }
    };

    $scope.addFileSubmitting = false;
    //点击保存
    $scope.addSysTemplate = function() {
        $scope.addFileSubmitting = true
        if (!$scope.addFileForm.$valid) {
            return false;
        }
        add();
    };

    function add() {
        if (vm.files.length <= 0) {
            jBox.tip("文件未上传,请上传文件后再保存", 'info');
            return false;
        }
        if (vm.files[0].name == null) {
            jBox.tip("文件为空，请上传文件后再保存", 'info');
            return false;
        }
        if (vm.files[0].uploading == true) {
            jBox.tip('文件正在上传,请上传完成后再保存', 'info');
            return false;
        }

        $scope.sysTemplate.templateFilename = vm.files[0].name;
        $scope.sysTemplate.fileAddress = vm.files[0].src;

        $http({
            url: "/sofn-sys-web/sysTemplate/addSysTemplate",
            data: $scope.sysTemplate,
            method: "post",
            headers: {
                "token": window.localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }
        }).success(function(data) {
            if (data.httpCode == 409) {
                jBox.tip('资料名称重复，请重新输入!', 'error');
            } else if (data.httpCode == 200) {
                jBox.tip("保存成功", 'info');
                $("#myModal").modal("hide");
                $(".kv-upload-progress").hide();
                $("#grid").dataTable().api().ajax.reload();
                vm.files.pop();
            } else {
                jBox.tip('保存失败!', 'error');
            }
        }).error(function(data) {
            ajaxErrorHandler(data.httpCode);
        })
    }
    // 获取适用平台字典数据
    if (typeof(window.sessionStorage.platformTypesCache) == "undefined") {
        $http({
            url: "/sofn-sys-web/sysTemplate/getPlatformTypes",
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
            $scope.platformTypeOptions = json.data;
            window.sessionStorage.setItem('platformTypesCache', JSON.stringify(json.data)); //缓存适用平台字典数据
        });
    } else {
        $scope.platformTypeOptions = JSON.parse(window.sessionStorage.getItem('platformTypesCache'));
    }
    // 获取文件分类字典数据
    if (typeof(window.sessionStorage.fileCategoriesCache) == "undefined") {
        $http({
            url: "/sofn-sys-web/sysTemplate/getFileCategories",
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
            $scope.fileCategoryOptions = json.data;
            window.sessionStorage.setItem('fileCategoriesCache', JSON.stringify(json.data)); //缓存文件分类字典数据
        });
    } else {
        $scope.fileCategoryOptions = JSON.parse(window.sessionStorage.getItem('fileCategoriesCache'));
    }
    // 获取app适用系统数据
    if (typeof(window.sessionStorage.appSystemOptionsCache) == "undefined") {
        $http({
            url: "/sofn-sys-web/sysTemplate/getAppSystemOptions",
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
            $scope.appSystemDict = json.data;
            window.sessionStorage.setItem('appSystemOptionsCache', JSON.stringify(json.data)); //缓存app适用系统数据
        });
    } else {
        $scope.appSystemDict = JSON.parse(window.sessionStorage.getItem('appSystemOptionsCache'));
    }
    // 获取适用系统数据
    if (window.sessionStorage.getItem('systemOptionsCache') == null) {
        $http({
            url: "/sofn-sys-web/sysTemplate/getSystemOptions",
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
            $scope.systemDict = json.data;
            window.sessionStorage.setItem('systemOptionsCache', JSON.stringify(json.data)); //缓存适用系统数据
            $scope.applySystemOptionsUpdate = $scope.systemDict;
            initTable();
        });
    } else {
        $scope.systemDict = JSON.parse(window.sessionStorage.getItem('systemOptionsCache'));
        $scope.applySystemOptionsUpdate = $scope.systemDict;
        initTable();
    }
    // 设置dataTable数据操作
    function settingTableOperation(id, fileAddress, fileName) {
        var link = '<a class="color " style="cursor: pointer" href="/sofn-sys-web/sysTemplate/fileDownload?fileUrl=<%= fileAddress %>&fileName=<%= fileName %>">下载</a>';
        if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
            // link += '<a class="color mr15 ml15" style="cursor: pointer" onclick="updateData(\'<%= id %>\')">修改</a>';
        }

        var template = _.template(link);

        return template({id: id, fileAddress: encodeURIComponent(fileAddress), fileName: encodeURIComponent(fileName)});
    }

    // 设置启用状态显示
    function settingEnabledIcon(id, status) {
        if (status == "Y") {
            return '<img src="assets/imgs/icon/sys-on.png" status="' + status + '" id="' + id + '"  class="xxx" onclick="changeStatus(this)"/>';
        } else {
            return '<img src="assets/imgs/icon/sys-off.png" status="' + status + '" id="' + id + '"  class="xxx" onclick="changeStatus(this)"/>';
        }
    }

    function initTable() {
        /**
         * 分页展示数据
         */
        //bootstrap表格
        $("#grid").dataTable({
            pageLength: 6,
            ajax: {
                url: "/sofn-sys-web/sysTemplate/getPageInfo",
                headers: {token: window.localStorage.getItem("token")},
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = 6;
                    // json.pageNumber = json.page.pageNum; //页码
                    // json.pageSize = json.page.pageSize; //每页个数
                    // json.start = (json.page.pageNum - 1) * json.page.pageSize; //起始位置
                    // json.length = 6;//json.page.pageSize;
                    return json.list;
                },
                data: function (params) {
                    params.templateName = "lodop";//$.trim($scope.queryCondition.templateName);
                    params.flags = "tts";//$scope.queryCondition.system;
                    params.platform = "pc,app";//$scope.queryCondition.platform;
                    params.category = "驱动";//$scope.queryCondition.category;
                },
                type: "post"
            },
            columns: [{
                data: "tpck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input type="checkbox" name="tpck"/>';
                },
                className: "text-center",
                width: "3%"
            }, {
                data: "templateName",
                title: "资料名称",
                className: "text-center",
                width: "15%",
                render: function(data, type, row) {
                    return '<span class="noWrappingLines">' + data + '</span>'
                }
            }, {
                data: "templateFilename",
                title: "资料文件名",
                className: "text-center",
                width: "22%",
                render: function(data, type, row) {
                    return '<span class="noWrappingLines">' + data + '</span>'
                }
            }, /*{
                data: "platform",
                title: "适用平台",
                className: "text-center",
                width: "8%"
            }, {
                data: "flags",
                title: "适用系统",
                render: function (data, type, row) {
                    return $scope.systemDict[data];
                },
                className: "text-center",
                width: "8%"
            }, {
                data: "category",
                title: "文件分类",
                className: "text-center",
                width: "8%"
            }, */{
                data: "updateTime",
                title: "更新时间",
                render: function (data, type, row) {
                    return new Date(data).format('yyyy-MM-dd hh:mm:ss');
                },
                className: "text-center",
                width: "10%"
            }, {
                data: "remark",
                title: "备注",
                className: "text-center",
                render: function(data, type, row) {
                    if (data == null) {
                        data = "";
                    }
                    return '<span class="noWrappingLines-max">' + data + '</span>'
                }
                // width: "18%"
            }, /*{
                data: "status",
                title: "启用",
                render: function (data, type, row) { // 模板化列显示内容
                    return settingEnabledIcon(row.id, data);
                },
                className: "text-center",
                width: "4%"
            }, */{
                data: "id",
                title: "操作",
                render: function (data, type, row) {
                    // 数据操作选择项
                    return settingTableOperation(data, row.fileAddress, row.templateFilename);
                },
                className: "text-center",
                width: "8%"
            }
            ]
        }).on('page.dt', function () {
            var table = $(this);
            table.find('thead').find(':checkbox').prop('checked', false);
        });
    }

    /**
     * 跳转到查询页面
     */
    window.lookView = function(data) {
        $http({
            url: "/sofn-sys-web/sysTemplate/queryById",
            method: "post",
            data: {
                id: data
            }
        }).success(function(data1) {
            if (data1 != null) {
                window.open(data1.sysTemplate.pdfAddress);
            } else {
                alert("系统异常,请联系工作人员");
            }
        })
    }

    $scope.updateFileSubmitting = false;
    // 关闭修改弹框的回调函数
    $("#myModal2").on("hide.bs.modal", function() {
        $scope.$evalAsync(function () {
            $scope.updateFileSubmitting = false;
        });
    });
    /**
     * 获取需要修改的数据
     */
    window.updateData = function(data) {
        $scope.id = data;

        $http({
            url: "/sofn-sys-web/sysTemplate/queryById",
            method: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: {
                id: $scope.id
            }
        }).success(function(data) {
            $scope.sysTemplate = data.sysTemplate;
            vm.files2 = [{
                name: data.sysTemplate.templateFilename,
                src: data.sysTemplate.fileAddress
            }];

            $("#myModal2").modal("show");
        }).error(function(data) {
            ajaxErrorHandler(data.httpCode);
        });

        /**
         * 修改
         */
        $scope.updateSysTemplate = function() {
            $scope.updateFileSubmitting = true;
            if (!$scope.updateFileForm.$valid) {
                return false;
            }
            if (vm.files2.length <= 0) {
                jBox.tip("文件未上传,请上传文件后再保存", 'info');
                return false;
            }
            if (vm.files2[0].name == null) {
                jBox.tip("文件为空，请上传文件后再保存", 'info');
                return false;
            }
            if (vm.files2[0].uploading == true) {
                jBox.tip('文件正在上传,请上传完成后再保存', 'info');
                return false;
            }

            $scope.sysTemplate.templateFilename = vm.files2[0].name;
            $scope.sysTemplate.fileAddress = vm.files2[0].src;

            $http({
                url: "/sofn-sys-web/sysTemplate/updateSysTemplate",
                data: $scope.sysTemplate,
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function(data) {
                jBox.tip("保存成功", 'info');
                $("#myModal2").modal("hide");
                $(".kv-upload-progress").hide();
                $("#grid").dataTable().api().ajax.reload();
            }).error(function(data) {
                ajaxErrorHandler(data.httpCode);
            })
            //$.jBox.confirm("文件已准备就绪,再次点击查看文件", "查看提示", submit, { buttons: { '确认': true} });

        }

        //清楚缓存
        // $scope.RemoveLocal = function() {
        //     window.localStorage.removeItem("id");
        // };
    }

    /**
     * 批量删除
     */
    $scope.deleteTemp = function() {
        var i = $scope.getSelectRowsIds();
        if (i != false) {
            if (confirm('是否批量删除已选择行？')) {
                $.ajax({
                    url: "sofn-sys-web/sysTemplate/deleteAll",
                    type: "post",
                    dataType: "json",
                    data: {
                        idStr: i.toString()
                    },
                    success: function(data) {
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();
                    },
                    fail: function(data) {
                        alert("失败");
                    }
                });
            }
        }
    }

    /**
     * 获取复选框选中行
     * @returns {*}
     */
    $scope.getSelectRowsIds = function() {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }

        return ids;
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data) {
        //选择是否进行删除
        if (confirm('是否进行删除...')) {
            $.ajax({
                url: "/sofn-sys-web/sysTemplate/deleteSysTemplate",
                type: "post",
                dataType: "json",
                data: {
                    id: data
                },
                success: function(data) {
                    alert("删除成功！");
                    $("#grid").dataTable().api().ajax.reload();
                }
            });
        }
    };
    document.onkeydown = keyListener;

    function keyListener(e) {
        if (e.keyCode == 13) {
            $("#grid").dataTable().api().ajax.reload();
        }
    }
    window.changeStatus = function(obj) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        var enable = obj.getAttribute("status") == "N" ?
            "Y" :
            "N";
        var id = obj.id;
        var imgObj = $('#' + obj.id);
        var submit = function(v, h, f) {
            $.ajax({
                url: "/sofn-sys-web/sysTemplate/updateTempStatus",
                data: {
                    "id": id,
                    "enable": enable,
                    "token": window.localStorage.getItem("token")
                },
                type: "post"
            }).success(function(data) {
                if (data.httpCode != 200) {
                    return false;
                }
                jBox.tip("修改成功", 'info');
                if (enable == "Y") {
                    imgObj.attr("src", "assets/imgs/icon/sys-on.png");
                    imgObj.attr("status", "Y");
                } else {
                    imgObj.attr("src", "assets/imgs/icon/sys-off.png");
                    imgObj.attr("status", "N");
                }
            }).error(function(data) {
                ajaxErrorHandler(data.status);
            });

            return true;
        };
        submit();
    }
})
