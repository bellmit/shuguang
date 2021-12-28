var myapp = angular.module("myapp", []);
myapp.controller("resourceDir", function ($scope, $http, $state) {
    /*新增表单认证*/
    $('#attributeForm').bootstrapValidator();//必须有
    $(".addRes").click(function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
//           提交执行函数全写在这下面
        add();
    });

    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    /* 清除查询框内容*/
    $scope.cleanData = function () {
        $("input[name='dirName']").val("");
        $("#reDir").dataTable().api().ajax.reload();
    };
    /*修改表单认证*/
    $('#updateResoueceDirF').bootstrapValidator();//必须有
    $(".updateResoueceDirSave").click(function () {
        if (!$("#updateResoueceDirF").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
//           提交执行函数全写在这下面
        update();
    });
    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    /*执行修改之后清空上次添加的内容*/
    $('#updates').on('hide.bs.modal', function () {
        $("#updateResoueceDirF").data('bootstrapValidator').destroy();//摧毁验证
        $('#updateResoueceDirF').bootstrapValidator();//必须有
    });

    //模态框显示

    //bootstrap表格
    //资源目录主体信息
    $("#reDir").dataTable({
        processing: true, // 加载时提示
        serverSide: true, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        //paging:true,
        //bInfo:true,
        //bJQueryUI: true,
        //bPaginate: false,// 分页按钮
        //bFilter: true,// 搜索栏
        //iDisplayLength: 10,// 每页显示行数
        //bSort: false,// 排序
        bAutoWidth: false,
        //sScrollY: "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        ajax: {
            url: "/sofn-dgap-web/resourceDirectory/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                /* for (var i = 0; i < json.data.list.length; i++) {
                 if (json.data.list[i].createTime != null && json.data.list[i].createTime != "" && json.data.list[i].createTime != undefined) {
                 json.data.list[i].createTime = new Date(parseInt(json.data.list[i].createTime)).toISOString().slice(0, 10);
                 }
                 }*/
                return json.data.list;
            },
            data: function (params) {
                params.token = window.localStorage.getItem("token");
                params.name = $("input[name='dirName']").val();
                //params.svAreaId=$("input[name='dateBegin']").val();
            },
            type: "post",
            headers: {
                token: window.localStorage.getItem("token")
            }
        },
        columns: [
            {
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    var unEnable = row.flagStatus;
                    return '<input onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" type="checkbox"' + (unEnable == "disEnable" ? "disabled" : "") + ' ' + (unEnable == "disEnable" ? "" : "name=\"ck\"") + '/>';
                },
                width: "5%"
            },
            {
                data: "NAME",
                title: "名称",
                width: "15%"
            },
            {
                data: "DESCRIPTION",
                title: "描述",
                width: "50%"
            },

            {
                data: "ID",
                title: "操作",
                render: function (data, v, d) { // 模板化列显示内容  deletes(\'' + data + '\')
                    var unEnable = d.flagStatus;
                    return '<input class="idValue" type="hidden" value="' + data + '"><a data-toggle="modal1" data-target="#resDirAdd1" style="margin-right: 30px;cursor: pointer" onclick="updateShow(\'' + data + '\')">修改</a><a style="cursor: pointer" ' + (unEnable == "enable" ? "" : " class=\"color-gray\"") + 'onclick="' + (unEnable == "disEnable" ? "$.jBox.tip(\'不能删除！\')" : "deletes('" + data + "')") + '">删除</a>';//转义传值
                },
                width: "20%"
            }

        ]
    });

    //重新加载表格
    $scope.resourceDirs = function () {

        $("#reDir").dataTable().api().ajax.reload();
    };


    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.resourceDirs();
        }
    };


    //新增资源目录name;
    function add() {

        $scope.addModal = function () {
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resourceDirectory/add",
                data: {
                    name: $scope.dirName,
                    description: $("#dirDesc").val(),
                    delFlag: "N"
                },
                //post传输方式   头信息
                method: 'post',
                //post传输方式   头信息
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'},
                    token: window.localStorage.getItem("token")
                }
            }).success(function (data) {
                if (data.httpCode == 200) {
                    if (data.data == "该资源目录已存在") {
                        $.jBox.tip("新增失败，该资源目录已存在！！！", 'info');
                        $("#reDir").dataTable().api().ajax.reload();
                    } else {
                        $.jBox.tip("新增成功！！！", 'info');
                        $("#reDir").dataTable().api().ajax.reload();
                    }


                } else {
                    $.jBox.tip("新增失败！！！", 'info');
                }
            })
        }
    }

    //修改资源目录
    /*调用模态框*/

    window.updateShow = function (data) {
        /*  数据回显*/
        $scope.dataId = data;
        $http({
            url: "/sofn-dgap-web/resourceDirectory/updateList",
            data: {
                id: data
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'},
                token: window.localStorage.getItem("token")
            }
        }).success(function (data) {
            $scope.updateList = data.data;
            $("#updates").modal("show");
        }).error(function (data) {

        });
    };

    function update() {
        $scope.updateSave = function () {
            if (!$("#updateResoueceDirF").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resourceDirectory/update",
                data: {
                    id: $scope.dataId,
                    name: $scope.updateList.name,
                    description: $("textarea[name='dirDesc']").val()
                },
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }

            }).success(function (data) {
                $("#updates").modal("hide");
                if (data.httpCode == 200) {
                    if (data.data == "该资源目录已存在") {
                        $.jBox.tip("该资源目录已存在！");
                    } else if (data.data == "") {

                    }
                    else {
                        $.jBox.tip("修改成功！！！");
                        $("#reDir").dataTable().api().ajax.reload();
                    }

                } else {
                    $.jBox.tip("操作不成功！")
                }


                //$state.go("index.content.dgap/resource/resourceDir");
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
                $("#updates").modal("hide");

            })
        }
    }

    /*  逐条删除资源目录列表*/
    //删除
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/resourceDirectory/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},

                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#reDir").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！");
                        } else if (data.httpCode == 403) {
                            jBox.tip("该资源目录存在资源，不能删除！");
                        } else {
                            jBox.tip("操作不成功！");
                        }
                    }
                });


            }

            else
                jBox.tip("取消删除", 'info');

            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    };

    /*点击修改时根据id查询*/


    //批量删除
    $scope.deleteResourceDir = function () {

        var delIds = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            delIds = delIds + $(this).parent().parent().children().eq(3).children().eq(0).val() + "|";
        });
        if (delIds != "") {
            var submit = function (v, h, f) {
                if (v == true) {
                    var data = {
                        dataInfo: delIds
                    };
                    $.ajax({
                        url: "/sofn-dgap-web/resourceDirectory/deletes",
                        type: "post",
                        dataType: "json",
                        data: data,
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        success: function (data) {
                            if (data.httpCode == 200) {
                                if (data.data) {
                                    jBox.tip("成功删除" + data.data + "条资源目录", 'info');
                                    $("#reDir").dataTable().api().ajax.reload();
                                } else {
                                    jBox.tip("所选的资源目录都不能删除！", 'info');
                                }
                            } else {
                                $.jBox.tip("操作不成功！")
                            }
                        },
                        fail: function (data) {
                            jBox.tip("失败", 'info');
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
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#reDir input[type='checkbox']:not(:disabled)").prop("checked", true);
            $("#reDir tbody tr").addClass("selected");
        } else {
            $("#reDir input[type='checkbox']").prop("checked", false);
            $("#reDir tbody tr").removeClass("selected");
        }
    });
    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }
});
