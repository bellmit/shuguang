var myapp = angular.module("myapp", []);
myapp.controller("alertConfig", function ($scope, $http, $state) {
    var userInTable = $('#userIn');
    var addUserTable = $('#userInfo');
    $scope.selectedCheckboxs = [];
    $('#attributeForm').bootstrapValidator(); //必须有
    $('#attribute').bootstrapValidator();

    $(".addRes").click(function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        addRes();
    });
    $(".updateRes").click(function () {
        if (!$("#attribute").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        updateRes();
    });
    /*执行添加之后清空上次添加的内容*/
    $('#myModal').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    $('#Modal').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    //点击查询按钮刷新datatable
    $scope.search=function(){
        $("#alertConfig").dataTable().api().ajax.reload();
    };
    //  预警阈值不小于0
    $scope.thresholdMatch=function () {
        var value=  $('#threshold').val();
        if(value<=0){
            $scope.threshold=1;
        }
        var value3=$('#threshold3').val();
        if(value3<=0){
            $scope.threshold3=1;
        }
    }

//清除查询条件
    $scope.cleanAltert = function () {
        $("select[name='targetId']").val("").change();
        $("select[name='alertType']").val("").change();
        $scope.a = undefined;
        // $scope.getWeb("");
        $scope.webs = [];
        $("#alertConfig").dataTable().api().ajax.reload();
    };
    //分页
    $("#alertConfig").dataTable({
        ajax: {
            url: "/sofn-dgap-web/alertConfig/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total; //总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum; //页码
                json.pageSize = json.data.pageSize; //每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].createTime != null && json.data.list[i].createTime != "" && json.data.list[i].createTime != undefined) {
                        json.data.list[i].createTime = new Date(parseInt(json.data.list[i].createTime)).toISOString().slice(0, 10);
                    }
                }
                return json.data.list;
            },
            //查询条件
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.alertType = $("select[name='alertType']").val();
                params.targetId = $("select[name='targetId']").val();
            },
            type: "post"
        },

        columns: [{
            data: "ck",
            title: '<input id="checkAll"  type="checkbox"/>',
            render: function (data, type, row) {
                return '<input onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" type="checkbox" name="ck" />';
            },
            width: "5%"
        }, {
            data: "RNAME",
            title: "资源目录",
            width: "20%"

        }, {
            data: "DNAME",
            title: "webService服务接口",
            width: "20%"

        }, {
            data: "DESCRIPTION",
            title: "预警内容",
            width: "20%"

        },

            {
                data: "ID",
                title: "操作",
                render: function (data) { // 模板化列显示内容
                    return '<input class="idValue" type="hidden" value="' + data + '"><a data-toggle="modal1" class="color" data-target="#Modal" style="margin-right: 10px;cursor: pointer" onclick="update(\'' + data + '\')">修改</a><a class="color" style="cursor: pointer" onclick="deletes(\'' + data + '\')">删除</a>'; //转义传值
                },
                width: "15%"

            }

        ]
    });
    /**
     * 新增 默认隐藏人员选择框
     */
    $("#addSelect").click(function () {
        var kkkw = document.getElementById("addDis");
        if (kkkw.style.display == "none") {
            kkkw.style.display = "block";
        } else {
            kkkw.style.display = "none";
        }
    });
    /**
     * 修改 默认隐藏人员选择框
     */
    var kkw =
        $("#updateUser").click(function () {
            var kkw = document.getElementById("updateDis");
            if (kkw.style.display == "none") {
                kkw.style.display = "block";
            } else {
                kkw.style.display = "none";
            }

        });
    var treeGridObj;
    var pageNumber = 0;
    var pageSize = 0;
    $("#userInfo").dataTable({
        fnDrawCallback: function () {

            //点击行内任意内容即选择行
            var syncCheckboxSelects = function (target) {
                var checkboxs = target.closest('tbody').find(':checkbox:checked');
                var vals = [];
                checkboxs.each(function (index, item) {
                    var map = {
                        id: $.trim($(item).val()),
                        text: $.trim($(item).closest('td').next().text())
                    };
                    vals[vals.length] = map;
                });
                $scope.selectedCheckboxs = vals;
                var selectedRowTexts = $scope.selectedCheckboxs.map(function (item) {
                    return item.text;
                }).join(',');
                $('#selectedUser').val(selectedRowTexts);
                $('#selectedUser').trigger('input');
            };
            this.on('click', 'tbody tr', function (e) {
                if (~['a', 'input', 'select'].indexOf(e.target.tagName.toLowerCase())) {
                    return;
                }
                var checkbox = $(this).find(':checkbox');
                var checked = checkbox.prop('checked');
                checkbox.prop('checked', !checked);
                syncCheckboxSelects(checkbox);
            }).on('change', 'tbody :checkbox', function () {
                syncCheckboxSelects($(this));
            });
        },
        ajax: {
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/alertConfig/getUser",
            dataSrc: function (json) {
                json.recordsTotal = json.page.recordsTotal;//总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.pageNumber = json.page.start / json.page.length;//页码
                json.pageSize = json.page.length;//每页个数
                json.start = json.page.start;//起始位置
                json.length = json.page.length;
                pageNumber = json.pageNumber;
                pageSize = json.pageSize;
                return json.data;
            },
            type: "post"
        },
        columns: [{
            data: "id",
            title: null,
            render: function (data, type, row) {
                return '<input type="checkbox" value="' + data + '"/>';
            }
        }, {
            data: "userName",
            title: "人员信息"
        }]
    });
    function addRes() {
        //新增配置信息
            addUserTable.find('tbody :checkbox').prop('checked', false);
            var c = [];
            c = $scope.selectedCheckboxs.map(function (item) {
                return item.id;
            });
            $http({
                url: "/sofn-dgap-web/alertConfig/add",
                data: {
                    common: {
                        alertType: $("select[name='alertType2']").val(),
                        targetId: $("select[name='targetId2']").val(),
                        description: $("input[name='description']").val(),
                        threshold: $("input[name='threshold']").val(),
                        delFlag: 'N'
                    },
                    user: c
                },
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function (data) {
                if (data.httpCode == 400) {
                    jBox.tip("相同数据已存在，请勿重复添加！");
                    $("#alertConfig").dataTable().api().ajax.reload();
                } else {
                    if (data.httpCode == 200) {
                        jBox.tip("添加成功", 'info');
                        $("#alertConfig").dataTable().api().ajax.reload();
                    } else {
                        jBox.tip("添加失败", 'info');
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
            })
    }

    $scope.deleteData = function () {
        addUserTable.find('tbody :checkbox').prop('checked', false);
    };
    $("#userIn").dataTable({
        fnDrawCallback: function () {
            //点击行内任意内容即选择行
            var syncCheckboxSelects = function (target) {
                var checkboxs = target.closest('tbody').find(':checkbox:checked');
                var vals = [];
                checkboxs.each(function (index, item) {
                    var map = {
                        id: $.trim($(item).val()),
                        text: $.trim($(item).closest('td').next().text())
                    };
                    vals[vals.length] = map;
                });
                $scope.selectedCheckboxs = vals;
                var selectedRowUpdateTexts = $scope.selectedCheckboxs.map(function (item) {
                    return item.text;
                }).join(',');
                $('#selectUpdateUser').val(selectedRowUpdateTexts);
                $('#selectUpdateUser').trigger('input');
            };
            this.on('click', 'tbody tr', function (e) {
                if (~['a', 'input', 'select'].indexOf(e.target.tagName.toLowerCase())) {
                    return;
                }
                var checkbox = $(this).find(':checkbox');
                var checked = checkbox.prop('checked');
                checkbox.prop('checked', !checked);
                syncCheckboxSelects(checkbox);
            }).on('change', 'tbody :checkbox', function () {
                syncCheckboxSelects($(this));
            });
        },
        ajax: {
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/alertConfig/getUser",
            dataSrc: function (json) {
                json.recordsTotal = json.page.recordsTotal;//总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.pageNumber = json.page.start / json.page.length;//页码
                json.pageSize = json.page.length;//每页个数
                json.start = json.page.start;//起始位置
                json.length = json.page.length;
                pageNumber = json.pageNumber;
                pageSize = json.pageSize;
                $scope.users = json.data;
                return json.data;
            },
            type: "post"
        },
        columns: [{
            data: "id",
            title: null,
            render: function (data, type, row) {
                return '<input type="checkbox" value="' + data + '" />';
            }
        }, {
            data: "userName",
            title: "人员信息"
        }]
    });
//清空人员选择表
    $scope.cleanData = function () {
        userInTable.find('tbody :checkbox').prop('checked', false);
    };
    $scope.targetIdToId =null;
    $scope.alertType3 =null;
    //修改配置信息
    window.update = function (data) {
        $scope.alertId = data;
        $("#Modal").modal("show");
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/alertConfig/getAlertById",
            method: "post",
            data: {id: data}
        }).success(function (data) {
            $scope.alertConfig = data.data;
            repeatundate(data.data[0].RNAME);
            $scope.targetId3 = $scope.alertConfig[0].DNAME;
            $scope.alertType3 = $scope.alertConfig[0].RNAME;
            $scope.targetIdToId=$scope.alertConfig[0].TARGETID;
            $scope.description3 = $scope.alertConfig[0].DESCRIPTION;
            $scope.threshold3 = $scope.alertConfig[0].THRESHOLD;
            var users = [];
            $scope.users.map(function (user) {
                $scope.alertConfig.map(function (alertConfig) {
                    if (alertConfig.RECEIPT_BY == user.id) {
                        users[users.length] = user.userName;
                    }
                    userInTable.find('tbody :checkbox[value="' + alertConfig.RECEIPT_BY + '"]').prop('checked', true);

                });


            });

            $scope.updateName = users.join(',');
        });
    };

    function updateRes() {
            userInTable.find('tbody :checkbox').prop('checked', false);
            var d = [];
            d = $scope.selectedCheckboxs.map(function (item) {
                return item.id;
            });
            if($("select[name='targetId3']").val()!=null){
                $scope.targetIdToId=$("select[name='targetId3']").val();
            }
            if($("select[name='alertType3']").val()!=null){
                $scope.alertType3 =$("select[name='alertType3']").val();
            }
            $http({
                url: "/sofn-dgap-web/alertConfig/update",
                data: {
                    common: {
                        id: $scope.alertId,
                        alertType: $scope.alertType3,
                        targetId: $scope.targetIdToId,
                        description: $("input[name='description3']").val(),
                        threshold: $("input[name='threshold3']").val(),
                        delFlag: 'N'
                    },
                    user: d
                },
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function (data) {
                if (data.httpCode == 400) {
                    jBox.tip("相同数据已存在，请重新修改数据");
                    $("#alertConfig").dataTable().api().ajax.reload();
                } else {
                    if (data.httpCode == 200) {
                        jBox.tip("修改成功", 'info');
                        $("#alertConfig").dataTable().api().ajax.reload();
                    } else {
                        jBox.tip("修改失败", 'info');
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
            })
    }

    //批量删除
    $scope.deleteResource = function () {
        var delIds = "";
        $("input[name='ck']:checked ").each(function () {
            delIds = delIds + $(this).parent().parent().children().eq(4).children().eq(0).val() + "|";
        });
        if (delIds != "") {
            var submit = function (v, h, f) {
                if (v == true) {
                    var data = {
                        dataInfo: delIds
                    };
                    $.ajax({
                        url: "/sofn-dgap-web/alertConfig/deletes",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            jBox.tip("删除成功", 'info');
                            $("#alertConfig").dataTable().api().ajax.reload();
                        },
                        fail: function (data) {
                            jBox.tip("删除失败", 'info');
                        }
                    });
                } else
                    jBox.tip("取消成功", 'info');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}})
        } else
            $.jBox.tip("请至少选择一行记录！");
    };
    //全选/全不选
    $("#checkAll").click(function () {
        if ($(this).prop("checked") == true) {
            $("#alertConfig input[type='checkbox']").prop("checked", true);
            $("#alertConfig tbody tr").addClass("selected");
        } else {
            $("#alertConfig input[type='checkbox']").prop("checked", false);
            $("#alertConfig tbody tr").removeClass("selected");
        }
    });
    $scope.getSelectRowsIds = function () {
        var rows = $("#alertConfig").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            jBox.tip("请至少选择一行记录！", 'info');
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }
        return ids;
    };

    //删除
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url: "/sofn-dgap-web/alertConfig/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            jBox.tip("删除成功", 'info');
                            $("#alertConfig").dataTable().api().ajax.reload();
                        } else {
                            jBox.tip("删除失败", 'info');
                        }
                    }
                });
            else
                jBox.tip("取消成功", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
        /**/
    };

    //获取资源目录名称
    $http({
        url: "/sofn-dgap-web/alertConfig/getType",
        method: "post"
    }).success(function (data) {
        $scope.type = data.data;
    }).error(function () {
    });

    //获取web服务接口名称    AngulaJS方式
    $scope.getWeb = function (data) {
        $http({
            url: "/sofn-dgap-web/alertConfig/getWeb",
            method: "post",
            data: {name: data}
        }).success(function (data) {
            $scope.webs = data.data;
        })
    };
    $scope.getAdd = function (data) {
        $http({
            url: "/sofn-dgap-web/alertConfig/getWeb",
            method: "post",
            data: {name: data}
        }).success(function (data) {
            $scope.webAdd = data.data;
        })
    };
    $scope.getUpdate = function (data) {
        repeatundate(data)

    };

    function repeatundate(data) {
        $http({
            url: "/sofn-dgap-web/alertConfig/getWeb",
            method: "post",
            data: {name: data}
        }).success(function (data) {
            $scope.webUpdate = data.data;
            if ($scope.targetId == "" && $scope.targetId == null && $scope.targetId == undefined) {
                $(".targetId3").val("SADSAF")
            }
            $(".targetId3").val($scope.targetId)
        })
    }

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }
});
