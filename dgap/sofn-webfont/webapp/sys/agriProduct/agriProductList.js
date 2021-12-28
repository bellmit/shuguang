var myapp = angular.module("myapp", []);
myapp.controller("agriProductList", function($scope, $http, $state) {
    //下拉框
    $scope.industry = [
        { name: 'all', val: '', title: '全部' },
        { name: "cropFarming", val: "01", title: "种植业" },
        { name: "animalHusbandry", val: "02", title: "畜牧业" },
        { name: "aquaticProduct", val: "03", title: "水产品" }
    ];
    $scope.importModalIndustrySelected = '';

    $scope.selectIndustry1 = $scope.industry[0].val;
    $scope.selectIndustryAdd = "";
    $scope.ANIMALHUSBANDRY = $scope.industry[2].val;
    window.gIndustrySelected = $scope.selectIndustryAdd;
    window.gANIMALHUSBANDRY = $scope.ANIMALHUSBANDRY;

    var industryCopy = $scope.industry.slice(0);
    industryCopy.shift();
    industryCopy.unshift({name: '', val: '', title: '请选择'});
    $scope.industryAdd = industryCopy;

    $scope.slaughterStatusOptions = [
        { name: '屠宰后', value: '0'},
        { name: '屠宰前', value: '1'},
        { name: '非屠宰', value: '2'}
    ];
    $scope.slaughterStatusSelected = '';

    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('农产品分类管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    $scope.industryChange = function() {
        // console.log($scope.selectIndustry1);
    }

    $scope.openImportDialog = function() {
        $scope.importModalIndustrySelected = '';
        $("#import_modal").modal('show');
    }

    $scope.closeImport = function() {
        $("#import_modal").modal('hide');
    }

    $('#ButonGetCheck').click(function() {
        var checkedItems = $('#dg').datagrid('getChecked');
        var names = [];
        $.each(checkedItems, function(index, item) {
            names.push(item.productname);
        });
        // console.log(names.join(","));
    });
    $(".bottom a").on("click", function() {
        $("input[name='checkAll']").prop("checked", false);
        $("#checkAll").prop("checked", false);
    });

    $scope.queryList = function() {
        var param = {
            keyword: $scope.keyword,
            typeId: $scope.selectIndustry1,
            delFlag: 'Y'
        };
        $("#grid").dataTable().api().settings()[0].ajax.data = param;
        $("#grid").dataTable().api().ajax.reload();
    }
    $scope.addModal = function() {
        $("#modalForm")[0].reset();
        $("#id").val("");
        $scope.selectIndustryAdd = "";
        window.gIndustrySelected = $scope.selectIndustryAdd;
        $scope.slaughterStatusSelected = '';
        $scope.title = "新增";

        $("#add_modal").modal("show");
    }

    $scope.updateModal = function(el) {
        var row = $scope.getSelectRow();
        if (row == null || row == "") return;
        $scope.putRow(row);
        $scope.title = "编辑";
        $("#add_modal").modal("show");
    }

    var productCodeBeforeEdit = "";
    $scope.putRow = function(row) {
        $("input[name='id']").val(row.id);
        $("input[name='typeId']").val(row.typeId);
        $("input[name='productCode']").val(row.productCode);
        productCodeBeforeEdit = row.productCode;
        // $("input[name='useCode']").val(row.useCode);
        $("input[name='name']").val(row.name);
        $("input[name='alias']").val(row.alias);
        $("input[name='enname']").val(row.enname);
        $("input[name='gbCode']").val(row.gbCode);
        $("textarea[name='description']").val(row.description);

        $scope.selectIndustryAdd = row.typeId;
        $scope.slaughterStatusSelected = row.slaughterStatus == null ? "" : row.slaughterStatus;
        window.gIndustrySelected = $scope.selectIndustryAdd;
    }

    $scope.cancelMain = function() {
        //$scope.getFormJson( $("#add_modal"));
        $("#add_modal").modal("hide");
    };

    $('#add_modal').on('hide.bs.modal', function() {
        productCodeBeforeEdit = "";
    });

    $scope.save = function() {
        save($state, $http, productCodeBeforeEdit); // 保存
    }

    $scope.delete = function() {
        var i = $scope.getSelectRowsIds();

        if (i != false) {
            var submit = function(v, h, f) {
                if (v == true) {
                    var params = {
                        jsonIds: JSON.stringify(i)
                    };
                    $.ajax({
                        url: "/sofn-sys-web/agriProduct/delete",
                        type: "post",
                        dataType: "json",
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        data: params,
                        success: function(resp) {
                            if (resp.httpCode == 200) {
                                jBox.tip("删除成功", 'info');
                                $scope.queryList();
                            } else {
                                jBox.tip(resp.msg, 'error');
                            }
                        },
                        fail: function(data) {
                            jBox.tip("删除失败", 'error');
                        }
                    });
                }
            };
            $.jBox.confirm("确认删除吗？", "删除提示", submit, { buttons: { '确认': true, '取消': false } });
        }
    }

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

    $scope.getSelectRow = function() {
        var rows = $("#grid").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length < 1) {
            alert("请至少选择一行记录！");
            return false;
        } else if (length > 1) {
            alert("只能选择一行记录！");
            return false;
        }
        return rows[0];
    }

    $scope.import = function() {
        $("#file-1").trigger("click");
    }

    $scope.typeIdChange = function() {
        window.gIndustrySelected = $scope.selectIndustryAdd;
        if ($scope.selectIndustryAdd != $scope.ANIMALHUSBANDRY) {
            // $scope.slaughterStatus.selected = "";
            $scope.slaughterStatusSelected = "";
        }
    }

    //未知
    /*setup()*/
    var dataTable = agridatatable($scope);
    agriCheckBoxChange();

    var choose = document.getElementById('file-1');
    FileAPI.event.on(choose, 'change', function(evt) {
        var files = FileAPI.getFiles(evt); // Retrieve file list
        FileAPI.filterFiles(files, function(file, info /**Object*/ ) {
            if ($.trim($scope.importModalIndustrySelected) == '') {
                swal('提示', '请选择所属行业！', 'warning');
                return false;
            }
            var acceptFileTypes = /^((doc|docx)|(application\/(msword|vnd\.openxmlformats-officedocument\.wordprocessingml\.document)))$/i;
            if (file.type && file.type != '') {
                if (!acceptFileTypes.test(file.type)) {
                    swal('', '只能上传word类型文档', 'warning');
                    return false;
                }
            } else {
                if (!/\.(doc|docx)$/i.test(file.name)) {
                    swal('', '只能上传word类型文档', 'warning');
                    return false;
                }
            }
            if (file.size > 5 * 1024 * 1024) {
                swal('', '文件大小不能超过5MB', 'warning');
                return false;
            }

            return true;
        }, function(files /**Array*/ , rejected /**Array*/ ) {
            if (files.length) {
                // Uploading Files
                var submit = function(v, h, f) {
                    if (v == true) {
                        FileAPI.upload({
                            url: '/sofn-sys-web/agriProduct/import',
                            files: { file: files[0] },
                            data: {
                                typeId: $scope.importModalIndustrySelected,
                                token: window.localStorage.getItem("token")
                            },
                            progress: function(evt) {

                            },
                            complete: function(err, xhr) {
                                if (!err) {
                                    // All files successfully uploaded.
                                    var data = JSON.parse(xhr.responseText);
                                    if (data.httpCode != 200) {
                                        swal("导入失败", "请检查数据是否正确！", "error");
                                        return;
                                    }
                                    swal("导入成功", "操作已成功!", "success");
                                } else {
                                    swal("导入失败", "操作失败!", "error");
                                }
                                $scope.closeImport();
                                $("#grid").dataTable().api().ajax.reload();
                            }
                        });
                    }
                };
                swal({
                    title: "确认导入",
                    text: files[0].name + " ？",
                    type: "warning",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true,
                }, submit);
            }
        });
    });
});
// var importData = function() {

//     var file = document.querySelector('input[type=file]').files[0];
//     if ((file == undefined) || (file != undefined && file.name.indexOf(".doc") < 0)) {
//         alert("导入失败，请上传word文档且word中的表格内容不能在一行中嵌套多行！");
//         return;
//     }
//     var fd = new FormData()
//     var typeId = $("#industry").val();
//     fd.append("file", file);
//     fd.append("typeId", typeId);

//     var submit = function(v, h, f) {
//         if (v == true)
//             $.ajax({
//                 url: "/sofn-sys-web/agriProduct/import",
//                 type: "post",
//                 processData: false,
//                 contentType: false,
//                 dataType: "json",
//                 data: fd,
//                 success: function(resp) {
//                     if ("200" != resp.httpCode) {
//                         jBox.tip("导入失败", 'error');
//                     } else {
//                         jBox.tip("导入成功", 'info');
//                     }
//                     $scope.queryList();
//                     $("#file-1").val("");
//                 },
//                 fail: function(data) {
//                     jBox.tip("导入失败", 'info');
//                     $("#file-1").val("");
//                 }
//             });
//     }
//     $.jBox.confirm("确认导入" + file.name + "？", "修改提示", submit, { buttons: { '确认': true, '取消': false } });
// };
var save = function(state, http, productCodeBeforeEdit) {
    var params = {};
    var obj = obj || $("#modalForm").serializeArray();
    $.each(obj, function() {
        if (params[this.name]) {
            if (!params[this.name].push) {
                params[this.name] = [params[this.name]];
            }
            params[this.name].push(this.value || '');
        } else {
            params[this.name] = this.value || '';
        }
    });
    var req = {
        method: 'post',
        url: '/sofn-sys-web/agriProduct/add',
        headers: {
            'token': window.localStorage.getItem("token")
        },
        data: params
    }

    validationParams(obj);
    if (validationFlag) {
        // 检查产品代码是否已经存在
        if (params.id == '' || params.productCode != productCodeBeforeEdit) {
            http({
                url: "/sofn-sys-web/agriProduct/getByProductCode",
                method: "post",
                data: {"productCode": params.productCode},
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (data) {
                    return $.param(data);
                }
            }).success(function (json) {
                if (json.httpCode == 200) {
                    if (json.data.length == 0) {
                        http(req).then(function (data) {
                            if (data.data.httpCode == 200) {
                                $("#add_modal").modal("hide");
                                $("#grid").dataTable().api().ajax.reload();
                                jBox.tip("保存成功", 'info');
                            } else {
                                jBox.tip("保存失败", 'error');
                                console.log(data);
                            }
                        }, function (data) {
                            jBox.tip("保存失败", 'error');
                            console.log(data);
                        });
                    } else {
                        jBox.tip('该产品代码已存在', 'error');
                    }
                } else {
                    jBox.tip('保存失败', 'error');
                }
            });
        } else {
            http(req).then(function(data) {
                if (data.data.httpCode == 200) {
                    $("#add_modal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                } else {
                    jBox.tip("保存失败", 'error');
                    console.log(data);
                }
            }, function(data) {
                jBox.tip("保存失败", 'error');
                console.log(data);
            });
        }
    } else {
        jBox.tip("带星号的为必填字段", 'info');
    }
}

var validationFlag = true;
var validationParams = function(obj) {
    validationFlag = true;
    $.each(obj, function(n, value) {
        var isRequiredField = value.name == "productCode" || value.name == "name" || value.name == "enname" || value.name == "typeId";
        if (window.gIndustrySelected == window.gANIMALHUSBANDRY) {
            isRequiredField = isRequiredField || value.name == "slaughterStatus";
        }
        if (isRequiredField && (value.value == undefined || $.trim(value.value) == '')) {
            validationFlag = false;
        }
    });
}

// var checkRecode = function(el) {
//     var bool = $(el).prop("checked");
//     $(el).prop("checked", !bool);
//     var tr = $(el).parent().parent();
//     bool ? tr.removeClass("selected") : tr.addClass("selected");
// }
var agriCheckBoxChange = function() {
    $("#checkAll").click(function() {
        if ($(this).prop("checked") == true) {
            $("#grid input[name='checkOne']").prop("checked", true);
            $("#grid tbody tr").addClass("selected");
        } else {
            $("#grid input[name='checkOne']").prop("checked", false);
            $("#grid tbody tr").removeClass("selected");
        }
    });
}
var agridatatable = function($scope) {
    $("#grid").dataTable({
        //     fnDrawCallback: function() {
        //         //点击行内任意内容即选择行
        //         $("#grid tbody tr td").each(function(i, o) {
        //             $(o).click(function() {
        //                 var checked = $(this).parent().find("input[name='checkOne']")[0]
        //                 if (checked.checked) {
        //                     checked.checked = false;
        //                     $(this).parent().removeClass("selected");
        //                 } else {
        //                     checked.checked = true;
        //                     $(this).parent().addClass("selected");
        //                 }
        //             });
        //         })
        //     },
        ajax: {
            url: "/sofn-sys-web/agriProduct/query",
            headers: {
                token: window.localStorage.getItem("token")
            },
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function(json) {
                if (json == null || json.data == null) {

                } else {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                }

            },
            data: function(params) {
                params = params || {};
                if(typeof($scope) != "undefined")
                    params.typeId = $scope.selectIndustry1;
                params.keyWord = $("#keyWord").val();
                params.delFlag = 'Y';
                return params;
            },
            type: "post"
        },
        columns: [{
            title: function(data, type, row) {
                return '<input type="checkbox" name="checkAll" id="checkAll">';
            },
            data: "active",
            width: "2%",
            className: "text-center",
            render: function(data, type, row) {
                if (type === 'display') {
                    return '<input type="checkbox" name="checkOne" class="editor-active">';
                }
                return data;
            },
        }, {
            data: "productCode",
            title: "产品代码",
            width: "8%"
        }, {
            //     data: "useCode",
            //     title: "用途代码",
            //     width: "10%"
            // }, {
            //data: "name",
            title: "名称",
            width: "12%",
            data: function(data, type, row, meta) {
                var spaceStr = "";
                var count = $.trim(data.productCode).length / 2;
                for (var i = 1; i < count; i++) {
                    spaceStr += "&nbsp;";
                }
                return spaceStr + data.name;
            }
        }, {
            data: "alias",
            title: "别名",
            width: "10%"
        }, {
            data: "enname",
            title: "英文名",
            width: "14%"
        }, {
            data: "gbCode",
            title: "GB/T 7635.1代码",
            width: "10%"
        }, {
            data: "description",
            title: "说明",
            width: "16%"
        }]
    })
    document.onkeydown = keyListener;

    function keyListener(e) {
        if (e.keyCode == 13) {
            $scope.queryList();
        }
    }
};
