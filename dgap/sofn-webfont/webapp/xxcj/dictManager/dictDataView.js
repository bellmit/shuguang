var myapp = angular.module("myapp", []);
myapp.controller("dictDataView", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });
    $(function() {
        //选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function() {
            $(this).attr("id", "bc_change").siblings().removeAttr("id", "bc_change");
        });
        //各个部分的内容切换
        $(".Sup_contain_section_contain_01").css("top", "80px");
        $(".Sup_contain_section_head_nav li:first-child").attr("id", "bc_change").click(function() {
            $(".Sup_contain_section_contain_01").css("top", "80px").siblings().css("top", "-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(2)").click(function() {
            $(".Sup_contain_section_contain_02").css("top", "80px").siblings().css("top", "-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(3)").click(function() {
            $(".Sup_contain_section_contain_03").css("top", "80px").siblings().css("top", "-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(4)").click(function() {
            $(".Sup_contain_section_contain_04").css("top", "80px").siblings().css("top", "-2000%")
        });
        if (navigator.appVersion.substring(22, 23) == 9) {
            if ($(".myPlaceholder").val() == "") {
                $(this).val("点击选择查询日期");
            }
        }
    });
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('基础代码管理');
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
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            fnDrawCallback: function() {
                //点击行内任意内容即选择行
                $("#grid tbody tr td").each(function(i, o) {
                    $(o).click(function() {
                        var checked = $(this).parent().find("input[type='checkbox']")[0];
                        if (checked.checked) {
                            checked.checked = false;
                            $(this).parent().removeClass("selected");
                        } else {
                            checked.checked = true;
                            $(this).parent().addClass("selected");
                        }
                    });
                })
            },
            ajax: {
                url: "/sofn-sys-web/sysDict/getDictDataByType",
                headers: {
                    token: window.localStorage.getItem('token')
                },
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsTotal;
                    json.pageNumber = json.page.pageNumber; //页码
                    json.pageSize = json.page.pageSize; //每页个数
                    json.start = json.page.start;
                    json.length = json.page.pageSize;
                    return json.list;
                },
                data: function(params) {
                    // console.log(window.localStorage.getItem("dictTypeId"));
                    params.id = window.localStorage.getItem("dictTypeId");
                    params.keyWord = $.trim($("#keyWord").val());
                    var KW=/^[\u4e00-\u9fa5]$/;
                    var len=0;
                    for(var i=0;i<params.keyWord.length;i++){
                        if(KW.test(params.keyWord[i])){
                            len=len+2;
                        }else {
                            len++;
                        }
                        if(len>60){
                            break;
                        }
                    }
                    params.keyWord=params.keyWord.substring(0,i);
                },
                type: "post"
            },
            columns: [{
                title: function(data, type, row) {
                    return '<input type="checkbox" name="checkAll" id="checkAll">';
                },
                data: "active",
                render: function(data, type, row) {
                    if (type === 'display') {
                        return '<input type="checkbox" name="checkOne" class="editor-active" onclick="checkRecode(this)">';
                    }
                    return data;
                },
                className: "dt-body-center"
            }, {
                title: "序号",
                data: function(data, type, row, meta) {
                    return meta.row + 1
                }
            }, {
                data: "dictName",
                title: "项目"
            }, {
                data: "dictCode",
                title: "编码"
            }, {
                data: "dictValue",
                title: "字典值"
            }, {
                data: "spellName",
                title: "简拼"
            }, {
                data: "enable",
                title: "有效",
                render: function(data, type, row) { // 模板化列显示内容
                    if (row.enable == "Y") {
                        return '<img src="assets/imgs/icon/sys-on.png" enable="' + row.enable + '" id="' + row.id + '"  class="xxx" onclick="changeEable(this)"/>';
                    } else {
                        return '<img src="assets/imgs/icon/sys-off.png" enable="' + row.enable + '" id="' + row.id + '"  class="xxx" onclick="changeEable(this)"/>';
                    }
                }
            }, {
                data: "remark",
                title: "备注"
            }, {
                data: "id",
                title: "操作",
                render: function(data, type, row) {
                    // 数据操作选择项
                    return '<button class="gui-btn btn-small" onclick="updateModal(\'' + data + '\')">修改</button>';
                }
            }]
        });
        //全选/全不选
        $("#checkAll").click(function() {
            if ($(this).prop("checked") == true) {
                $("#grid input[type='checkbox']").prop("checked", true);
                $("#grid tbody tr").addClass("selected");
            } else {
                $("#grid input[type='checkbox']").prop("checked", false);
                $("#grid tbody tr").removeClass("selected");
            }
        });
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });


    $scope.queryList = function() {
        $("#grid").dataTable().api().ajax.reload();
    };

    //enter submit
    $scope.onInputChanged = function($event){
        if ($event && $event.keyCode == 13) {
            $scope.queryList();
        }
    };
    // 打开模态框
    $scope.addModal = function() {
        $scope.title = "新增";
        //设置actnion
        $scope.sysDictData = "";
        $("#add_modal").modal("show");
    };

    // 编辑 todo 不再使用
    // $scope.updateModal = function(id) {
    //     var bool = $scope.checkSelect();
    //     var rows = $("#grid").DataTable().rows('.selected').data();
    //     if (bool) {
    //         return;
    //     }
    //     $scope.title = "编辑";
    //     $http({
    //         url: "/sofn-sys-web/sysDict/queryDictDataById",
    //         data: { id: rows[0].id },
    //         method: "post",
    //         headers: {
    //             token: window.localStorage.getItem('token')
    //         }
    //     }).success(function(json) {
    //         $scope.sysDictNewData = json.data;
    //         if (json.data.enable == 'Y') {
    //             $("#newEnable").find("[value='Y']").attr("selected", "selected");
    //         } else
    //             $("#newEnable").find("[value='N']").attr("selected", "selected");
    //         $("#update_modal").modal("show");
    //     }).error(function(data, status) {
    //         ajaxErrorHandler(status);
    //     });
    // };

    // 列表中的修改点击事件 回显数据
    window.updateModal = function(id) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $scope.title = "编辑";
        $http({
            url: "/sofn-sys-web/sysDict/queryDictDataById",
            data: { id: id },
            method: "post",
            headers: {
                token: window.localStorage.getItem('token')
            }
        }).success(function(json) {
            $scope.sysDictNewData = json.data;
            if (json.data.enable == 'Y') {
                $("#newEnable").find("[value='Y']").attr("selected", "selected");
            } else
                $("#newEnable").find("[value='N']").attr("selected", "selected");
            $("#update_modal").modal("show");
        }).error(function(data, status) {
            ajaxErrorHandler(status);
        });
    };

    $scope.cancelMain = function() {
        //$scope.getFormJson( $("#add_modal"));
        $('#modalForm')[0].reset();
        $("#add_modal").modal("hide");
    };

    $scope.save = function() {
        save($state, $http);
    };

    $scope.delete = function() {
        var ids = $scope.getSelectRowsIds();
        if (ids != false) {
            if (confirm('是否删除已选择的字典项？')) {
                $.ajax({
                    url: "/sofn-sys-web/sysDict/deleteDictData",
                    headers: {
                        token: window.localStorage.getItem('token')
                    },
                    type: "post",
                    dataType: "json",
                    data: { idStr: ids.toString() },
                    success: function(json) {
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();
                    },
                    error: function(data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        }
    };

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
    };

    $scope.checkSelect = function() {
        var checkBoxCount = $("#grid input[name='checkOne']");
        var count = 0;
        for (var i = 0; i < checkBoxCount.length; i++) {
            $("#grid input[name='checkOne']")[i].checked == true && count++;
            if (count >= 2) break;
        }
        return !!(count >= 2 || count == 0);
    };

    $scope.addDictDataValided = false
    // 当调用 hide 实例方法时触发。
    $("#add_modal").on("hide.bs.modal", function(){
        $scope.addDictDataValided = false;
    });
    //新增数据字典信息
    $scope.addDictData = function(valid) {
        $scope.addDictDataValided = true;
        if (valid) { // console.log(window.localStorage.getItem("dictTypeId"));
            var params = {};
            //$scope.sysDictData.name = $("input[name='name']").val();
            //$scope.sysDictData.dataValue = $("input[name='dataValue']").val();
            //$scope.sysDictData.code = $("input[name='code']").val();
            //$scope.sysDictData.spellName = $("input[name='spellName']").val();
            //$scope.sysDictData.enable = $("select[name='enable']").val();
            //$scope.sysDictData.remark = $("input[name='remark']").val();
            //$scope.sysDictData.dictId = window.localStorage.getItem("dictTypeId");
            params.dictName = $("input[name='name']").val();
            params.dictValue = $("input[name='dataValue']").val();
            params.dictCode = $("input[name='code']").val();
            params.spellName = $("input[name='spellName']").val();
            params.enable = $("select[name='enable']").val();
            params.remark = $("input[name='remark']").val();
            params.typeId = window.localStorage.getItem("dictTypeId");
            //$.ajax({
            //    url: "/sofn-sys-web/sysDict/addDictData",
            //    data: params,
            //    type: "post"
            //}).success(function () {
            //    $("#grid").dataTable().api().ajax.reload();
            //    $("#add_modal").modal("hide");
            //}).error(function () {
            //    alert("保存失败");
            //});
            $http({
                url: "/sofn-sys-web/sysDict/addDictData",
                data: params,
                method: "post",
                headers: {
                    token: window.localStorage.getItem('token')
                }
            }).success(function (data) {
                $("#grid").dataTable().api().ajax.reload();
                $("#add_modal").modal("hide");
                $scope.addDictDataValided = true;
            }).error(function (data, status) {
                ajaxErrorHandler(status);
            })
        }
    };

    $scope.updateDictDataValided = false;
    $("#update_modal").on("hide.bs.modal", function(){
        $scope.updateDictDataValided = false;
    });
    // 更新方法
    $scope.updateDictData = function(valid) {
        $scope.updateDictDataValided = true;
        if (valid) {
            $scope.sysDictNewData.dictName = $("#name").val();
            $scope.sysDictNewData.dictId = $("#dataValue").val();
            $scope.sysDictNewData.code = $("#code").val();
            $scope.sysDictNewData.spellName = $("#spellName").val();
            $scope.sysDictNewData.enable = $("#newEnable").val();
            $scope.sysDictNewData.remark = $("#remark").val();
            $http({
                url: "/sofn-sys-web/sysDict/updateDictData",
                data: $scope.sysDictNewData,
                method: "post",
                headers: {
                    token: window.localStorage.getItem('token')
                }
            }).success(function (data) {
                $("#grid").dataTable().api().ajax.reload();
                $("#update_modal").modal("hide");
            }).error(function (data, status) {
                ajaxErrorHandler(status);
            })
        }
    };

    // 状态开关
    window.changeEable = function(obj) {

        var submit = function(v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-sys-web/sysDict/updateDictDataEnable",
                    headers: {
                        token: window.localStorage.getItem('token')
                    },
                    data: {
                        "id": id,
                        "enable": enable == "N" ? "Y" : "N"
                    },
                    type: "post"
                }).success(function(data) {
//                    alert(data.httpCode)
                    if (data.httpCode != 200) {
                        return false;
                    }
                    jBox.tip("修改成功", 'info');
                    if (enable == "N") {
                        imgObj.attr("src", "assets/imgs/icon/sys-on.png");
                        imgObj.attr("enable", "Y");
                    } else {
                        imgObj.attr("src", "assets/imgs/icon/sys-off.png");
                        imgObj.attr("enable", "N");
                    }
                }).error(function(data) {
                    ajaxErrorHandler(data.status)
                });
            } else
                jBox.tip("取消成功", 'info');

            return true;
        };
        // 自定义按钮
        $.jBox.confirm("对基础数据修改，可能对其他系统造成重大影响，请谨慎操作！", "修改提示", submit, { buttons: { '确认': true, '取消': false } });

        var enable = obj.getAttribute("enable");
        var id = obj.id;
        var imgObj = $('#' + obj.id);
    }
});
