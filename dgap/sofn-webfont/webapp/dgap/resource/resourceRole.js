var myapp = angular.module("myapp", []);
myapp.controller("resourceRole", function ($scope, $http, $state) {
    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#resourceRoleAddF').data('bootstrapValidator').resetForm(true);
    });
    /*   新增资源权限设置*/
    $('#resourceRoleAddF').bootstrapValidator();//必须有
    $(".resourceRoleSaveB").click(function () {
        if (!$("#resourceRoleAddF").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        add();
    });
    /*   修改资源权限设置*/
    $('#resourceRoleUpdateF').bootstrapValidator();//必须有
    $(".resourceRoleB").click(function () {
        if (!$("#resourceRoleUpdateF").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
        update();
    });
    /*执行修改之后清空上次添加的内容*/
    $('#resourceRoleUpdate').on('hide.bs.modal', function () {
        $("#resourceRoleUpdateF").data('bootstrapValidator').destroy();//摧毁验证
        $('#resourceRoleUpdateF').bootstrapValidator();//必须有
    });
    //资源目录主体信息
    $("#reRole").dataTable({
        ajax: {
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/resourceRole/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].createTime != null && json.data.list[i].createTime != "" && json.data.list[i].createTime != undefined) {
                        json.data.list[i].createTime = new Date(parseInt(json.data.list[i].createTime)).toISOString().slice(0, 10);
                    }
                }
                return json.data.list;
            },
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.resourceDirectoryName = $("select[name='resourceDirectoryName']").val();
                params.resourceName = $("input[name='resourceName']").val();
                params.roleId = $("select[name='roleId']").val();
            },
            type: "post"
        },
        columns: [
            {
                data: "ID",
                checkbox: true,
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" type="checkbox" name="ck"/>';
                },
                width: "5%"
            }, {
                data: "TDNAME",
                title: "资源目录",
                width: "15%"

            },
            {
                data: "TRNAME",
                title: "资源名称",

                width: "15%"

            },
            {
                data: "ROLE_ID",
                title: "角色名称",
                render: function (data) { // 模板化列显示内容
                    return lookupName(data, $scope.roleList);//转义传值
                },
                width: "25%"
            },
            {
                data: "ID",
                title: "操作",
                render: function (data) { // 模板化列显示内容
                    return '<input class="idValue" type="hidden" value="' + data + '"><a data-toggle="modal1" data-target="#resourceRoleUpdate"  style="margin-right: 10px;cursor: pointer" onclick="resourceRoleshow(\'' + data + '\')">修改</a><a style="cursor: pointer" onclick="deleteResourceRole(\'' + data + '\')">删除</a>';//转义传值
                },
                width: "15%"
            }

        ]
    });
    //重新加载表格
    $scope.resourceDirs = function () {
        $("#reRole").dataTable().api().ajax.reload();
    };

    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.resourceDirs();
        }
    };
    //清除输入框的查询条件
    $scope.cleanData = function () {
        $("select[name='resourceDirectoryName']").val("");
        $("select[name='roleId']").val("");
        $("input[name='resourceName']").val("");
        $("#reRole").dataTable().api().ajax.reload();
    };
    //新增资源权限设置name;
    function add() {
        $scope.addResRole = function () {
            if (!$("#resourceRoleAddF").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resourceRole/add",
                data: {
                    resourceId: $("select[name='resourceName']").val(),
                    roleId: $scope.roleIdModel,
                    delFlag: "N"
                },
                //post传输方式   头信息
                method: 'post'
            }).success(function (data) {
                $("#resDirAdd").modal("hide");
                if (data.httpCode == 400) {
                    $.jBox.tip("数据已存在，请勿重复添加");
                    $("#reRole").dataTable().api().ajax.reload();
                } else {
                    if (data.httpCode == 200) {
                        $.jBox.tip("新增成功！！！");
                        $("#reRole").dataTable().api().ajax.reload();
                    } else {
                        $.jBox.tip("操作不成功！");
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
            })
        }
    }

    /*新增的资源名称的下拉列表*/
    $http({
        url: "/sofn-dgap-web/resourceRole/relist",
        data: {},
        method: "post"
    }).success(function (data) {
        $scope.resources = data.data;
        $("#reRole").dataTable().api().ajax.reload();
    }).error(function () {
    });


    //修改资源权限设置
    window.resourceRoleshow = function (data) {
        $scope.applicationdata = data;
        $http({
            url: "/sofn-dgap-web/resourceRole/findById",
            data: {id: data},
            method: 'POST'
        }).success(function (data) {
            if (data.httpCode == 200) {
                $("#resourceRoleUpdate").modal("show");
                $scope.resourceRole = data.data;
                $scope.updateResourceName = $scope.resourceRole.resourceId;
                $scope.updateRoleName = $scope.resourceRole.roleId
            } else {
                jBox.tip("操作不成功", 'info');
            }
        })
    };


    function update() {
        $scope.resourceRoleUpdata = function () {
            if (!$("#resourceRoleUpdateF").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resourceRole/update",
                data: {
                    id: $scope.applicationdata,
                    resourceId: $("select[name='resourceId']").val(),
                    roleId: $scope.updateRoleName
                },
                method: "post"
            }).success(function (data) {
                $("#resourceRoleUpdate").modal("hide");
                if (data.httpCode == 400) {
                    $.jBox.tip("已存在相同数据，请勿重复添加");
                    $("#reRole").dataTable().api().ajax.reload();
                } else {
                    if (data.httpCode == 200) {
                        $.jBox.tip("修改成功！！！");
                        $("#reRole").dataTable().api().ajax.reload();
                    } else {
                        $.jBox.tip("操作不成功！");
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
                $("#resourceRoleUpdate").modal("hide");

            })
        }
    }

    /*资源权限设置列表查询中显示的资源目录json*/
    $http({
        url: "/sofn-dgap-web/resourceDirectory/tree",
        data: {},
        method: "post"
    }).success(function (data) {
        $scope.resourceDir = data.children;
    }).error(function () {
        $.jBox.tip("操作不成功！");
    });

    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-dgap-web/resourceRole/rolelist",
        method: "post"
    }).success(function (json) {
        $scope.roleList = json.data;
        return json.data;
        //$("#reRole").dataTable().api().ajax.reload();
    }).error(function () {
    });

    //删除
    window.deleteResourceRole = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/resourceRole/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            jBox.tip("删除成功", 'info');
                            $("#reRole").dataTable().api().ajax.reload();
                        } else {
                            $.jBox.tip("操作不成功！");
                        }
                    }
                });

            } else
                jBox.tip("取消删除", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    };
    //批量删除
    $scope.deleteResourceRole = function () {
        var delIds = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            delIds = delIds + $(this).parent().parent().children().eq(4).children().eq(0).val() + "|";
        });
        if (delIds != "") {
            var submit = function (v, h, f) {
                if (v == true) {
                    var data = {
                        dataInfo: delIds
                    };
                    $.ajax({
                        url: "/sofn-dgap-web/resourceRole/deletes",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            if (data.httpCode == 200) {
                                $("#reRole").dataTable().api().ajax.reload();
                                $.jBox.tip("删除成功！");

                            } else {
                                $.jBox.tip("操作不成功！");
                            }
                        },
                        fail: function (data) {
                            $.jBox.tip("删除失败！");
                        }
                    });
                }

                else
                    jBox.tip("取消删除", 'info');

                return true;
            };
            // 自定义按钮
            $.jBox.confirm("是否批量删除已选择行？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
        } else {
            $.jBox.tip("请至少选中一行记录", 'info');
        }
    };
    //全选/全不选
    // $("#checkAll").click(function () {
    //     if ($(this).prop("checked") == true) {
    //         $("#reRole input[type='checkbox']").prop("checked", true);
    //         $("#reRole tbody tr").addClass("selected");
    //     } else {
    //         $("#reRole input[type='checkbox']").prop("checked", false);
    //         $("#reRole tbody tr").removeClass("selected");
    //     }
    // });

    /*把id转化成name*/
    function lookupName(id, map) {
        if (map != null && map != "") {
            for (var j = 0; j < map.length; j++) {
                if (map[j].id == id) {
                    return map[j].roleName;
                }
            }
        }
        return "";
    }

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }
});
