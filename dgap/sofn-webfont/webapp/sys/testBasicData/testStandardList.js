/**
 * Created by Administrator on 2017/11/10/010.
 */
var myapp = angular.module("myapp", []);
myapp.controller("testStandardList", function($scope, $http, $state) {
    $("#modalForm").bootstrapValidator();
    $("#testItemModalForm").bootstrapValidator();
    $scope.standardCodeBeforeUpdate = "";
    $scope.standardNameBeforeUpdate = "";
    $scope.remarkBeforeUpdate = "";
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('检测基础数据');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;

    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
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

    $scope.industryTypeOptions = {};
    $http({
        url: "/sofn-sys-web/sysTestItem/getIndustryTypes",
        method: "post",
        data: {},
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'token': window.localStorage.getItem("token")
        },
        transformRequest: function (data) {
            return $.param(data);
        }
    }).success(function(json) {
        if (json.httpCode == 200) {
            $scope.industryTypeOptions = json.data;
        }
    }).error(function(json, status) {
        ajaxErrorHandler(status);
    });
    // 通过行业字典值获取对应的字典名称
    window.getIndustryTypeDictName = function(value) {
        if (value == null) {
            return "";
        }
        var industryType = _.findWhere($scope.industryTypeOptions, {dictValue: value});
        return industryType.dictName;
    }
    /**
     * 分页展示数据
     */
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysTestStandard/queryList",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataSrc: function(json) {
                json.recordsTotal = json.data.total; //总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum; //页码
                json.pageSize = json.data.pageSize; //每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function(params) {
                params.standardCode = $scope.code;
                params.standardName = $scope.name;
            },
            type: "post"
        },
        columns: [{
            title: function(data, type, row) {
                return '<input type="checkbox" name="checkAll" id="checkAll">';
            },
            data: "checkOne",
            render: function(data, type, row) {
                if (type === 'display') {
                    return '<input type="checkbox" name="checkOne">';
                }
                return data;
            },
            className: "text-center",
            width: "5%"
        }, {
            title: "序号",
            data: function(data, type, row, meta) {
                return meta.row + 1
            },
            className: "text-center",
            width: "5%"
        }, {
            data: "standardCode",
            title: "标准号",
            className: "text-center",
            width: "20%"
        }, {
            data: "standardName",
            title: "检测标准名称",
            className: "text-center",
            width: "28%"
        }, {
            data: "remark",
            title: "说明",
            className: "text-center"
        }, {
            data: "id",
            title: "操作",
            render: function(data, type, row) {
                // 数据操作选择项
                var opTagString = '<a style="cursor: pointer" class="color mr15" onclick="openTestItem(\'' + data + '\')">配置</a>';
                if ($scope.menuAuthority == MENU_READWRITE_AUTHORITY) {
                    opTagString += '<a style="cursor: pointer" class="color mr15" onclick="updateStandard(\'' + data + '\')">修改</a>';
                }
                opTagString += '<a style="cursor: pointer" class="color mr15" onclick="queryStandard(\'' + data + '\')">查看</a>';

                return opTagString
            },
            className: "text-center",
            width: "18%"
        }]
    });
    // 点击查询按钮事件处理
    $scope.queryList = function() {
        $("#grid").dataTable().api().ajax.reload();
    }
    // 点击弹出模态窗口取消按钮事件处理
    $scope.cancel = function() {
        $("#add_modal").modal("hide");
    }
    // 点击弹出模态窗口保存按钮事件处理
    $scope.save = function() {
        // 表单验证，验证通过再提交数据
        if (!$("#modalForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var ajaxURL = "";
        if ($scope.id != "") {
            ajaxURL = "/sofn-sys-web/sysTestStandard/update";
            // 表单数据无修改时，不做保存操作直接关闭窗口
            if ($scope.standardCode == $scope.standardCodeBeforeUpdate && $scope.standardName == $scope.standardNameBeforeUpdate
                && $scope.remark == $scope.remarkBeforeUpdate) {
                $("#add_modal").modal("hide");
                return true;
            }
        } else {
            ajaxURL = "/sofn-sys-web/sysTestStandard/add";
        }
        $http({
            url: ajaxURL,
            method: "post",
            data: { "id": $scope.id,
                    "standardCode": $scope.standardCode,
                    "standardCodeBeforeUpdate": $scope.standardCodeBeforeUpdate,
                    "standardName": $scope.standardName,
                    "remark": $scope.remark,
                    "token": window.localStorage.getItem("token")
                  },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                alert("保存成功");
                $("#add_modal").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
            } else {
                alert(json.msg);
                $("#add_modal").modal("hide");
            }
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }

    // 新增按钮事件处理
    $scope.addModal = function() {
        $('#modalForm').data('bootstrapValidator').destroy();
        $("#modalForm").bootstrapValidator();
        $scope.myModalLabel = "新增检测标准";
        $scope.id = "";
        $scope.standardCode = "";
        $scope.standardName = "";
        $scope.remark = "";

        $("[inputtype='standard']").removeAttr("readonly");
        $("#save").show();
        $("#add_modal").modal("show"); //打开模态窗口

    }
    /**
     * 公用的获取复选框选中行函数
     * @param tableId  表格标签的id名称（不带#号）
     */
    $scope.getSelectedRowsIds = function(tableId) {
        var rows = $("#" + tableId).DataTable().rows('.selected').data();
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
     * 公用的ajax删除操作函数
     * @param url   删除操作请求地址
     * @param condition   删除条件对象
     * @param successCallback 删除成功后的回调函数
     */
    window.ajaxDelete = function(url, condition, successCallback) {
        $http({
            url: url,
            method: "post",
            data: condition,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                successCallback(json);
            } else {
                alert(json.msg + "，删除失败");
            }

        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }
    // 删除按钮事件处理
    $scope.delete = function() {
        var idArray = $scope.getSelectedRowsIds("grid");
        if (idArray != false) {
            if (confirm('是否删除已选择数据？')) {
                var condition = {
                    "ids": idArray.toString(),
                    "token": window.localStorage.getItem("token")
                }
                ajaxDelete("/sofn-sys-web/sysTestStandard/delete", condition, function(json) {
                    var message = "";
                    if (json.data.deletedRows > 0) {
                        message += json.data.deletedRows + "条数据删除成功";
                    }

                    if (json.data.notDeletedRows > 0) {
                        var standardCodes = [];
                        var selectedRows = $("#grid").DataTable().rows('.selected').data();
                        json.data.unDeletedIdList.forEach(function(standardId) {
                            for (var i = 0; i < selectedRows.length; i++) {
                                if (selectedRows[i].id == standardId) {
                                    standardCodes.push(selectedRows[i].standardCode);
                                }
                            }
                        });
                        message += "\n标准号：" + standardCodes.toString() + " 因为检测标准限量值中已存在关联配置，未做删除，如需删除，请先删除“检测标准限量值”中相应配置。";
                    }
                    alert(message);
                    $("#grid").dataTable().api().ajax.reload();
                });
            }
        }
    }
    // 查看测试标准信息
    window.queryStandard = function(id) {
        $http({
            url: "/sofn-sys-web/sysTestStandard/queryOne",
            method: "post",
            data: {"id": id},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            $('#modalForm').data('bootstrapValidator').destroy();
            $("#modalForm").bootstrapValidator();
            $scope.myModalLabel = "查看检测标准";
            $scope.readonly = 'readonly="readonly"';
            $scope.standardCode = json.data.standardCode;
            $scope.standardName = json.data.standardName;
            $scope.remark = json.data.remark;

            $("[inputtype='standard']").attr("readonly", "readonly");
            $("#save").hide();
            $("#add_modal").modal("show");
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })

    }
    // 修改测试标准信息
    window.updateStandard = function(id) {
        $http({
            url: "/sofn-sys-web/sysTestStandard/queryOne",
            method: "post",
            data: {"id": id},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            $('#modalForm').data('bootstrapValidator').destroy();
            $("#modalForm").bootstrapValidator();
            $scope.myModalLabel = "修改检测标准";
            $scope.id = id;
            $scope.standardCode = json.data.standardCode;
            $scope.standardCodeBeforeUpdate = json.data.standardCode;
            $scope.standardName = json.data.standardName;
            $scope.standardNameBeforeUpdate = json.data.standardName;
            $scope.remark = json.data.remark;
            $scope.remarkBeforeUpdate = json.data.remark;

            $("[inputtype='standard']").removeAttr("readonly");
            $("#save").show();
            $("#add_modal").modal("show");
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }
    // 打开检查项配置模态窗口
    window.openTestItem = function(id) {
        $scope.itemNameQuery = "";
        $scope.industryTypeQuery = "";
        $("#test-items_modal").modal("show");
        $http({
            url: "/sofn-sys-web/sysTestStandard/queryOne",
            method: "post",
            data: {"id": id},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            //$scope.id = id;
            $scope.standardCode = json.data.standardCode;
            $scope.standardName = json.data.standardName;
            //加载数据
            $("#testItemsGrid").dataTable().api().ajax.reload();

        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }

    $("#testItemsGrid").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysTestItem/queryList",
            dataSrc: function(json) {
                json.recordsTotal = json.data.total; //总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum; //页码
                json.pageSize = json.data.pageSize; //每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function(params) {
                params.standardCode = $scope.standardCode;
                params.itemName = $scope.itemNameQuery;
                params.scope = $scope.industryTypeQuery;
            },
            type: "post"
        },
        columns: [{
            title: function(data, type, row) {
                return '<input type="checkbox" name="checkAll" id="checkAll2">';
            },
            data: "checkOne",
            render: function(data, type, row) {
                if (type === 'display') {
                    return '<input type="checkbox" name="checkOne">';
                }
                return data;
            },
            className: "text-center",
            width: "5%"
        }, {
            title: "序号",
            data: function(data, type, row, meta) {
                return meta.row + 1
            },
            className: "text-center",
            width: "5%"
        }, {
            data: "itemName",
            title: "检测项",
            className: "text-center",
            width: "50%"
        }, {
            data: "scope",
            title: "行业",
            // render: function(data, type, row) {
            //     return getIndustryTypeDictName(data);
            // },
            className: "text-center",
            width: "8%"
        }, {
            data: "remark",
            title: "说明",
            className: "text-center"
        }, {
            data: "id",
            title: "操作",
            render: function(data, type, row) {
                // 数据操作选择项
                var opTagString = '<a style="cursor: pointer" class="color mr15" onclick="updateTestItem(\'' + data + '\')">修改</a>';
                return opTagString
            },
            className: "text-center",
            width: "8%"
        }]
    });

    // 检测项查询按钮事件处理
    $scope.queryItemList = function() {
        $("#testItemsGrid").dataTable().api().ajax.reload();
    }
    // 检测项删除按钮事件处理
    $scope.deleteTestItems = function() {
        var idArray = $scope.getSelectedRowsIds("testItemsGrid");
        if (idArray != false) {
            if (confirm('是否删除已选择数据？')) {
                var condition = {
                    "ids": idArray.toString(),
                    "token": window.localStorage.getItem("token")
                }
                ajaxDelete("/sofn-sys-web/sysTestItem/delete", condition, function(json) {
                    var message = "";
                    if (json.data.deletedRows > 0) {
                        message += json.data.deletedRows + "条数据删除成功";
                    }

                    if (json.data.notDeletedRows > 0) {
                        var testItemNames = [];
                        var selectedRows = $("#testItemsGrid").DataTable().rows('.selected').data();

                        json.data.unDeletedIdList.forEach(function(testItemId) {
                            for (var i = 0; i < selectedRows.length; i++) {
                                if (selectedRows[i].id == testItemId) {
                                    testItemNames.push(selectedRows[i].itemName);
                                }
                            }
                        });
                        message += "\n检测项：" + testItemNames.toString() + "，因为检测标准限量值中已存在关联配置，未做删除，如需删除，请先删除“检测标准限量值”中相应配置。"
                    }
                    alert(message);
                    $("#testItemsGrid").dataTable().api().ajax.reload();
                });
            }
        }
    }
    // 打开修改或者新增的检测项模态窗口
    $scope.openTestItemDetail = function(id) {
        $scope.testItemForm.$setPristine();
        $('#testItemModalForm').data('bootstrapValidator').destroy();
        $("#testItemModalForm").bootstrapValidator();
        if (id == null) {
            $scope.testItemModalTitle = "新增检测项";
            $scope.itemId = "";
            $scope.itemStandardCode = $scope.standardCode;
            $scope.itemStandardName = $scope.standardName;
            $scope.itemName = "";
            $scope.itemRemark = "";
            $scope.itemIndustryType = "";
            $("#test-item_add_modal").modal("show");
        } else {
            $http({
                url: "/sofn-sys-web/sysTestItem/queryOne",
                method: "post",
                data: {"id": id},
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'token': window.localStorage.getItem("token")
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function(json) {
                $scope.testItemModalTitle = "修改检测项";
                $scope.itemId = id;
                $scope.itemStandardCode = $scope.standardCode;
                $scope.itemStandardName = $scope.standardName;
                $scope.itemName = json.data.itemName;
                $scope.itemNameOld = json.data.itemName;
                $scope.itemRemark = json.data.remark;
                $scope.itemIndustryType = json.data.scope == null ? "" : json.data.scope;
                $("#test-item_add_modal").modal("show");
            }).error(function(json, status) {
                ajaxErrorHandler(status);
            })
        }
    }
    // 检测项修改点击事件处理
    window.updateTestItem = function(id) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $scope.openTestItemDetail(id);
    }
    // 检测项新增按钮事件处理
    $scope.addTestItem = function() {
        $scope.openTestItemDetail(null);
    }
    // 检测项模态窗口的取消按钮事件处理
    $scope.itemCancel = function() {
        $("#test-item_add_modal").modal("hide");
    }
    // 检测项模态窗口的保存按钮事件处理
    $scope.itemSave = function() {
        var itemName = "";
        var scope = "";
        var testItemEditFlag = "0";

        // 表单验证，验证通过再提交数据
        if (!$("#testItemModalForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        if ($scope.itemIndustryType == "") {
            alert("请选择行业");
            return false;
        }
        var ajaxURL = "";
        if ($scope.itemId != "") {
            ajaxURL = "/sofn-sys-web/sysTestItem/update";
            // if ($scope.testItemForm.industryType.$dirty) {
                scope = $scope.itemIndustryType;
            // }
            if ($scope.testItemForm.itemName.$dirty && $scope.itemName != $scope.itemNameOld) {
                testItemEditFlag = "1";
            }
            itemName = $scope.itemName;
        } else {
            ajaxURL = "/sofn-sys-web/sysTestItem/add";
            itemName = $scope.itemName;
            scope = $scope.itemIndustryType;
            testItemEditFlag = "1";
        }
        $http({
            url: ajaxURL,
            method: "post",
            data: { "id": $scope.itemId,
                "standardCode": $scope.itemStandardCode,
                "itemName": itemName,
                "remark": $scope.itemRemark,
                "scope": scope,
                "testItemEditFlag": testItemEditFlag,
                "token": window.localStorage.getItem("token")
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                alert("保存成功");
                $("#test-item_add_modal").modal("hide");
                $("#testItemsGrid").dataTable().api().ajax.reload();
            } else {
                alert(json.msg);
                $("#test-item_add_modal").modal("hide");
            }
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }
});