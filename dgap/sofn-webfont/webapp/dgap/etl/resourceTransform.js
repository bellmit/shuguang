var myapp = angular.module("myapp", []);
myapp.controller("resourceTransform", function ($scope, $http) {
    ///*修改和添加的表单验证*/
    $('#updateAttributeForm').bootstrapValidator();//修改 必须有
    $('#attributeForm').bootstrapValidator();//新增 必须有

    $('#attributeForm1').bootstrapValidator();//新增模板 必须有

    /*执行添加之后清空上次添加的内容*/
    $('#resourceTransformAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });


    /*执行模板导入之后清空上次添加的内容*/
    $('#resourceTransformRead').on('hide.bs.modal', function () {
        $('#attributeForm1').data('bootstrapValidator').resetForm(true);
    });
    /*执行修改之后清空上次修改的内容*/
    //$('#resourceTransformUpdate').on('hide.bs.modal', function () {
    //    $("#updateAttributeForm").data('bootstrapValidator').resetForm(true);//摧毁验证
    //    $('#updateAttributeForm').bootstrapValidator();
    //});

    //清除查询条件
    $scope.cleanData = function () {
        $("input[name='resourceName']").val("");
        $("input[name='transName']").val("");
        $("#resourceTransform").dataTable().api().ajax.reload();
    };
    $scope.search = function () {
        $("#resourceTransform").dataTable().api().ajax.reload();
    };
    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.search();
        }
    };

    //bootstrap表格
    function loadGrid() {
        $("#resourceTransform").dataTable({
            ajax: {
                headers: {
                    "token": window.localStorage.getItem("token")
                },
                url: "/sofn-dgap-web/etl-transform/list",//id
                dataSrc: function (json) {
                    if (json != null && json.httpCode !== 200) {
                        jBox.tip("数据错误！", 'info');
                        return null;
                    }
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.resourceName = $("input[name='resourceName']").val();
                    params.transName = $("input[name='transName']").val();
                },
                type: "post"
            },
            columns: [{
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    return '<input onclick="unCheckAll(\'' + row.resourceID + '\',\'' + row.deloyStatus + '\',\''+row.transName+'\')" value="' + row.resourceID + ',' + row.deloyStatus + ',' + row.transName + '" type="checkbox" name="ck" />';
                },
                width: "5%"
            },
                {
                    data: "resourceName",
                    title: "资源名称",
                    width: "10%"
                },
                {
                    data: "transName",
                    title: "转换名称",
                    width: "10%"
                },
                {
                    data: "dispatcher",
                    title: "调度状态",
                    render: function (data) {
                        $scope.dispatcher = data;
                        if (data == 'Y') {
                            return '已调度';
                        } else if (data == 'N') {
                            return '未调度';
                        } else {
                            return '未知';
                        }
                    },
                    width: "10%"
                },
                {
                    data: "deloyStatus",
                    title: "部署状态",
                    render: function (data) {
                        if (data == 'Y') {
                            return '已部署';
                        } else if (data == 'N') {
                            return '未部署';
                        } else {
                            return '未知';
                        }
                    },
                    width: "10%"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        if ("Y" == $scope.dispatcher) {
                            return '<input class="  idValue" type="hidden" value="' + data + '">' +

                                '<a data-toggle="modal1" class="color" data-target="#Modal" style="margin-right: 10px;cursor: pointer" onclick="updateResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.resourceName + '\',' + '\'' + row.transName + '\')">修改</a>' +
                                '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="deletes(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\')">删除</a>' +
                                '<a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="deployResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\')">部署</a>' +
                                '<a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="uninstallResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.transName + '\','+'\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher + '\')">卸载</a>' +
                                '<a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="schedule(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher + '\')">取消调度</a>' +
                                '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="runSchedule(\'' + row.resourceID + '\',' + '\'' + row.resourceName + '\',' + '\'' + row.transName + '\','+'\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher +  '\')">执行</a>';
                        } else {
                            return '<input class="  idValue" type="hidden" value="' + data + '">' +
                                '<a data-toggle="modal1" class="color" data-target="#Modal" style="margin-right: 10px;cursor: pointer" onclick="updateResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.resourceName + '\',' + '\'' + row.transName + '\')">修改</a><a class="color" style="margin-right: 10px;cursor: pointer" onclick="deletes(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\')">删除</a> <a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="deployResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\')">部署</a><a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="uninstallResourceTrans(\'' + row.resourceID + '\',' + '\'' + row.transName + '\','+ '\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher + '\')">卸载</a>' +
                                '<a style="margin-right: 10px;cursor: pointer"text-decoration: none;" class="colorGrey" onclick="schedule(\'' + row.resourceID + '\',' + '\'' + row.transName + '\',' + '\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher + '\')">调度</a>' +
                                '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="runSchedule(\'' + row.resourceID + '\',' + '\'' + row.resourceName + '\',' + '\'' + row.transName + '\','+'\'' + row.deloyStatus + '\',' + '\'' + row.dispatcher +  '\')">执行</a>';
                        }
                    },
                    width: "10%"
                }
            ]
        })
    }

    $http({
        url: "/sofn-dgap-web/resourceRole/relist",
        data: {},
        headers: {
            token: window.localStorage.getItem("token")
        },
        method: "post"
    }).success(function (data) {
        $scope.resources = data.data;
    }).error(function () {
    });



    $http({
        url: "/sofn-dgap-web/etl-transform/readlist",
        data: {},
        headers: {
            token: window.localStorage.getItem("token")
        },
        method: "post"
    }).success(function (data) {
        $scope.readlist = data.data;
    }).error(function () {
    });



    var vm = this;
    vm.files = [{
        name: '金碟etl转换文件'
    }];

    //文件上传配置
    vm.uploadOptions = {
        //接口地址
        url: '/sofn-dgap-web/etl-transform/add',
        headers: {
            token: window.localStorage.getItem("token")
        },
        //业务参数
        data: function () {
            var resourceId = $("select[name='resName']").val();
            return {"resourceId": resourceId};
        },
        //初始文件数量
        min: 1,
        //最大文件数量
        max: 1,
        //文件占位信息
        filter: function (file, info) {
            var acceptFileTypes = /^((ktr))$/i;
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过５MB', 'warning');
                return false;
            }
            return true;
        }
    };
    $scope.files = [];
    var choose = document.getElementById('file-1');
    FileAPI.event.on(choose, 'change', function (evt) {
        var files = FileAPI.getFiles(evt); // Retrieve file list
        FileAPI.filterFiles(files, function (file, info /**Object*/) {

            if (!/\.(ktr)$/i.test(file.name)) {
                swal('', '只能上传ktr类型文档', 'warning');
                return false;
            }

            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }

            return true;
        }, function (files /**Array*/, rejected /**Array*/) {
            if (files.length) {
                $scope.$apply(function () {
                    $scope.file = files[0];
                    $scope.filePreview = '../assets/imgs/dist/file-default.png';
                });

            }
        });
    });

    //新增按钮跳转新增摸态框
    $scope.addTransform = function () {
        $("#resourceTransformAdd").modal("show");
        $scope.resName = '';
        $scope.file = null;
        $scope.filePreview = '../assets/imgs/dist/plus-mask.png';
    };

    //导入模板按钮 跳转 导入模板摸态框
    $scope.addReadTransform = function () {
        $("#resourceTransformRead").modal("show");
        $scope.resName = '';
        $scope.file = null;
        $scope.filePreview = '../assets/imgs/dist/plus-mask.png';
    };


    //新增ETL转换
    $scope.addResourceTransform = function () {
        if ($.trim($scope.resName) == '') {
            swal('提示', '请选择资源名称！', 'warning');
            return false;
        }
        if ($scope.file == null) {
            jBox.tip("请选择文件！", 'info');
            return false;
        }

        FileAPI.upload({
            url: '/sofn-dgap-web/etl-transform/add',
            files: {file: $scope.file},
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: {resourceId: $scope.resName},
            complete: function (err, xhr) {
                if (!err) {
                    // All files successfully uploaded.
                    var data = JSON.parse(xhr.responseText);
                    if (data.httpCode == 500) {
                        swal("导入失败", "操作不成功！", "error");
                        $("#resourceTransform").dataTable().api().ajax.reload();
                        return;
                    }
                    jBox.tip("新增成功！", 'info');
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    $scope.file = null;
                    $scope.filePreview = null;
                    $scope.resName = null;
                    $('#resourceTransformAdd').modal('hide');
                    $('#file-1').val('');
                } else {
                    swal("导入失败", "操作失败!", "error");
                    $('#file-1').val('');
                }
            }
        });

    };


    //新增ETL模板导入
    $scope.readResourceTransform = function () {
        if ($.trim($scope.resName) == '') {
            swal('提示', '请选择资源名称！', 'warning');
            return false;
        }
        if ($.trim($scope.readName) == '') {
            swal('提示', '请选择模板名称！', 'warning');
            return false;
        }

        FileAPI.upload({
            url: '/sofn-dgap-web/etl-transform/addRead',
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: {resourceId: $scope.resName,
                    readName: $scope.readName
            },
            complete: function (err, xhr) {
                if (!err) {
                    // All files successfully uploaded.
                    var data = JSON.parse(xhr.responseText);
                    if (data.httpCode == 500) {
                        swal("导入失败", "操作不成功！", "error");
                        $("#resourceTransform").dataTable().api().ajax.reload();
                        return;
                    }
                    jBox.tip("导入成功！", 'info');
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    $scope.file = null;
                    $scope.filePreview = null;
                    $scope.resName = null;
                    $('#resourceTransformRead').modal('hide')
                } else {
                    swal("导入失败", "操作失败!", "error");
                }
            }
        });

    };



    /*修改ETL转换 数据回显*/
    window.updateResourceTrans = function (resourceID, resourceName, transName) {
        $("#resourceTransformUpdate").modal("show");
        $scope.$apply(function () {
            $scope.resName = resourceID;
            $scope.resourceId = resourceName;
            $scope.file = {name: transName + ".ktr"};
            $scope.filePreview = '../assets/imgs/dist/file-default.png';

        });
    };

    var choose2 = document.getElementById('file-2');
    FileAPI.event.on(choose2, 'change', function (evt) {
        var files = FileAPI.getFiles(evt); // Retrieve file list
        FileAPI.filterFiles(files, function (file, info /**Object*/) {

            if (!/\.(ktr)$/i.test(file.name)) {
                swal('', '只能上传ktr类型文档', 'warning');
                return false;
            }

            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }

            return true;
        }, function (files /**Array*/, rejected /**Array*/) {
            if (files.length) {
                $scope.$apply(function () {
                    $scope.file = files[0];
                    $scope.filePreview = '../assets/imgs/dist/file-default.png';
                });

            }
        });
    });
    $scope.updateTransform = function () {
        if ($scope.file == null) {
            jBox.tip("请选择文件！", 'info');
            return false;
        }

        FileAPI.upload({
            url: '/sofn-dgap-web/etl-transform/update',
            files: {file: $scope.file},
            data: {resourceId: $scope.resName},
            headers: {
                token: window.localStorage.getItem("token")
            },
            complete: function (err, xhr) {
                if (!err) {
                    // All files successfully uploaded.
                    var data = JSON.parse(xhr.responseText);
                    if (data.httpCode == 500) {
                        swal("导入失败", "操作不成功！", "error");
                        $("#resourceTransform").dataTable().api().ajax.reload();
                        return;
                    }
                    jBox.tip("修改成功！", 'info');
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    $scope.file = null;
                    $scope.filePreview = null;
                    $scope.resName = null;
                    $('#resourceTransformUpdate').modal('hide')
                } else {
                    swal("导入失败", "操作失败!", "error");
                }
            }
        });

    };

    /*  逐条删除ETL转换*/
    window.deletes = function (data, transName, deloyStatus) {
        if ("Y" == deloyStatus) {
            jBox.tip("转换已部署，请先卸载再删除", 'info');
            return;
        }
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url: "/sofn-dgap-web/etl-transform/delete",
                    type: "post",
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    dataType: "json",
                    data: {
                        resourceId: data,
                        transName: transName
                    },
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#resourceTransform").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！", 'info');
                        } else {
                            jBox.tip("操作不成功！", 'info');
                        }
                    }
                });
            else
                jBox.tip("取消成功", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    };


    /*  立即执行ETL转换*/
    window.runSchedule = function (resourceID, resourceName,transName, deloyStatus, dispatcher) {
        if ("N" == deloyStatus) {
            jBox.tip("转换未部署，无法执行", 'info');
            return;
        }
        if ("N" == dispatcher) {
            jBox.tip("转换未调度，无法执行", 'info');
            return;
        }
        $.ajax({
            url: "/sofn-dgap-web/etl-transform/runSchedule",
            type: "post",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataType: "json",
            data: {
                resourceId: resourceID,
                transName: transName ,
                resourceName:resourceName
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    jBox.tip("操作成功！", 'info');
                } else {
                    jBox.tip("操作失败！", 'info');
                }
            }
        });
        return true;
    };


    //批量删除ETL转换
    $scope.deleteResourceTransforms = function () {
        var resourceID = "";
        var a = "";
        var b = "";
        var ids = [];
        $("input[name='ck']:checkbox:checked ").each(function () {
            ids.push($(this).val());
        });

        //resourceID: 93e4906dd734494ba05afb91b72513d899b2e877c8b548e38ef64f7cd7efcced,N,transName|93e4906dd734494ba05afb91b72513d899b2e877c8b548e38ef64f7cd7efcced,N,transName
        resourceID = ids.join('|');
        a = resourceID.split('|');
        var idAndNames = []; //包含resourceId和transName
        for (var i = 0; i < a.length; i++) {
            b = a[i].split(',');
            idAndNames.push(b[0]+","+b[2]);
            if ("Y" == b[1]) {
                jBox.tip("转换已部署，请先卸载再删除", 'info');
                return;
            }
        }
        if (resourceID != "") {
            var submit = function (v, h, f) {
                if (v == true) {
                    var data = {
                        resourceId: idAndNames.join('|')
                    };
                    $.ajax({
                        url: "/sofn-dgap-web/etl-transform/deleteAll",
                        type: "post",
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            $("#resourceTransform").dataTable().api().ajax.reload();
                            jBox.tip("删除成功", 'info');
                        },
                        fail: function (data) {
                            jBox.tip("删除失败", 'info');
                        }
                    });
                }
                else
                    jBox.tip("取消成功", 'info');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}})
        } else
            $.jBox.tip("请至少选择一行记录！");
    };

    loadGrid();
    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#resourceTransform input[type='checkbox']").prop("checked", true);
            $("#resourceTransform tbody tr").addClass("selected");
        } else {
            $("#resourceTransform input[type='checkbox']").prop("checked", false);
            $("#resourceTransform tbody tr").removeClass("selected");
        }
    });
    //部署etl转换
    window.deployResourceTrans = function (id, transName, deloyStatus) {
        if ("Y" == deloyStatus) {
            jBox.tip("转换已部署，无需重复部署", 'info');
            return;
        }
        $.ajax({
            url: "/sofn-dgap-web/etl-transform/deploy",
            type: "post",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataType: "json",
            data: {"resourceId": id, "transName": transName},
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    jBox.tip("部署成功！", 'info');
                } else {
                    jBox.tip("操作不成功！", 'info');
                }
            }
        });
    };
    //卸载etl转换
    window.uninstallResourceTrans = function (id, transName,deloyStatus, dispatcher) {
        if ("Y" == dispatcher) {
            jBox.tip("该资源已经调度不能卸载！", 'info');
            return
        }
        if ("N" == deloyStatus) {
            jBox.tip("转换未部署，无法卸载！", 'info');
            return;
        }
        $.ajax({
            url: "/sofn-dgap-web/etl-transform/undeploy",
            type: "post",
            headers: {
                token: window.localStorage.getItem("token")
            },
            dataType: "json",
            data: {"resourceId": id, "transName": transName},
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    jBox.tip("卸载成功！", 'info');
                } else {
                    jBox.tip("操作不成功！", 'info');
                }
            }
        });
    };

    //调度etl转换
    window.schedule = function (id, transName, deloyStatus, dispatcher) {
        if ("N" == deloyStatus) {
            jBox.tip("转换未部署，无法调度", 'info');
            return;
        }
        var enable = "Y" == dispatcher ? "N" : "Y";
        $.ajax({
            url: "/sofn-dgap-web/etl-transform/setScheduleStatus",
            type: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: {"resourceId": id, "enable": enable, "transName": transName},
            success: function (data) {
                if (data.httpCode == 200 && "Y" == enable) {
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    jBox.tip("调度成功！", 'info');
                } else if (data.httpCode == 200 && "N" == enable) {
                    $("#resourceTransform").dataTable().api().ajax.reload();
                    jBox.tip("取消调度成功！", 'info');
                } else {
                    jBox.tip("操作不成功！", 'info');
                }
            }
        });
    };

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    };
});


