var myapp = angular.module("myapp", []);

function validRoleId(value) {
    var val = value;
    var isOk = val && val.length > 0;
    $("#roleId")
        .nextAll(".color-red")
        .toggle(!isOk);
    return isOk;
}

/**
 * 设置select控件选中
 *
 * @param selectId select的id值
 * @param checkValue 选中option的值
 */
function set_select_checked(selectId, checkValue) {
    var select = document.getElementById(selectId);
    var optionCheck = checkValue == null ? "" : checkValue;
    for (var i = 0; i < select.options.length; i++) {
        if (select.options[i].value == optionCheck) {
            select.options[i].selected = true;
            break;
        }
    }
}
var menuAuthority;
myapp.controller("userList", function($scope, $http, $state) {
    $("#modalForm").bootstrapValidator(); //必须有
    $("#userQueryForm").bootstrapValidator(); //必须有
    var vm = this;
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName("内部用户管理");
    menuAuthority = $scope.menuAuthority;
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;

    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
        if (statusCode == 401) {
            // swal(
            //     {
            //         title: "登录超时",
            //         text: "由于您长时间未操作,请重新登录!",
            //         type: "warning",
            //         timer: 2500,
            //         showConfirmButton: true
            //     },
            //     function(e) {
            //         window.location.href = "/";
            //     }
            // );
        } else {
            jBox.tip("返回数据不正常，操作失败", "error");
        }
    };
    // 新增或修改关闭时清除验证结果并重置form显示
    $("#add_modal").on('hide.bs.modal', function() {
        $("#modalForm").data("bootstrapValidator", null);
        $("#modalForm").bootstrapValidator();
        $("#modalForm").bootstrapValidator('resetForm');
    });
    $scope.queryList = function() {
        if (
            !$("#userQueryForm")
                .data("bootstrapValidator")
                .validate()
                .isValid()
        ) {
            return false;
        }
        //datatable();
        $("#grid")
            .dataTable()
            .api()
            .ajax.reload();
    };

    $("#userQueryForm").find(".gui-btn").on("click", $scope.queryList);
    $scope.addModal = function() {
        if (currentOrg == null) {
            alert("当前用户的所属组织机构为空，不允许创建内部用户！");
            return;
        }

        $("#roleId").combobox("clear");
        $("#modalForm").bootstrapValidator(); //必须有
        $("#myModalLabel").html("新增用户");
        $("#modalForm")
            .find("input")
            .val("");
        set_select_checked('postId', '');
        set_select_checked('status', '1');
        $.ajax({
            url: "/sofn-sys-web/user/getNormalRoleInfo",
            type: "post",
            headers: {
                token: window.localStorage.getItem("token")
            },
            success: function(json) {
                if (json != "")
                    //设置actnion
                    $("#modalForm").attr(
                        "action",
                        "/sofn-sys-web/user/addSysUser"
                    );
                //设置机构信息
                if (currentOrg != null) {
                    $("#organizationId").val(currentOrg.id);
                    $("#orgType").val(currentOrg.orgType);
                    $("#orgName").html(currentOrg.orgName);
                } else {
                    $("#orgName").html("无");
                }
                var roles = [];
                var roleNames = [];
                //lenght导致新增 删除按钮不能点击
                for (var i = 0; i < json.normalRole.length; i++) {
                    roles.push(json.normalRole[i].id);
                    roleNames.push(json.normalRole[i].roleName);
                }
                $("#roleId")
                    .combobox("clear")
                    .combobox("setValues", roles)
                    .combobox("setText", roleNames.join(","));
                $("#pwd").hide();
                $("#save").show();
                $("#add_modal").modal("show");
                $("#modalForm")
                    .find("input, select")
                    .prop("disabled", false)
                    .end()
                    .find(".gui-select")
                    .removeClass("disabled");
                $("#roleId").combobox("readonly", false);
            },
            error: function(data) {
                ajaxErrorHandler(data.status);
            }
        });
    };
    //打开导入页面
    var allMenu = [];
    $scope.batchImport = function() {
        $scope.title = "批量导入";
        $("#importModalForm")[0].reset();
        $("#file").val("");
        //设置actnion
        $("#modalForm").attr(
            "action",
            "/sofn-sys-web/user/importUserListByExcel"
        );
        //设置机构信息
        $("#import_modal").modal("show");
    };
    $scope.cancelMain = function() {
        //$scope.getFormJson( $("#add_modal"));
        // $('#modalForm')[0].reset();
        $("#add_modal").modal("hide");
    };
    $scope.cancelMainImport = function() {
        //$scope.getFormJson( $("#add_modal"));
        $("#import_modal").modal("hide");
    };

    $scope.saveUser = function() {
        if ($("#save").hasClass("disabled")) {
            return;
        }
        saveUserInfo($state, $http);
    };
    $scope.saveImport = function() {
        saveImport($state, $http);
    };

    $scope.delete = function() {
        var ids = $scope.getSelectRowsIds();
        var delFlag = true;
        $("#grid")
            .find("tbody :checked")
            .each(function() {
                // console.log($(this).val())
                if ($(this).val() == $.cookie("account")) {
                    alert("当前用户不能够删除自己的账号信息！");
                    delFlag = false;
                    return;
                }
            });
        if (ids != false && delFlag == true) {
            if (confirm("是否删除已选择的用户？")) {
                $.ajax({
                    url: "/sofn-sys-web/user/deleteUser",
                    type: "post",
                    dataType: "json",
                    data: {
                        idStr: ids.toString()
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    success: function(json) {
                        if (json.httpCode != 200) {
                            alert(json.msg);
                            return;
                        }
                        alert("删除成功！");
                        $("#grid")
                            .dataTable()
                            .api()
                            .ajax.reload();
                    },
                    error: function(data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        }
    };

    $scope.getSelectRowsIds = function() {
        var rows = $("#grid")
            .DataTable()
            .rows(".selected")
            .data();
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
    $scope.getSelectRowsId = function() {
        var rows = $("#grid")
            .DataTable()
            .rows(".selected")
            .data();
        var length = rows.length;
        if (length <= 0) {
            alert("请选择一行记录！");
            return false;
        } else if (length > 1) {
            alert("只能选择一行记录！");
            return false;
        }
        return rows[0].id;
    };

    $scope.checkSelect = function() {
        var checkBoxCount = $("#grid input[name='checkOne']");
        var count = 0;
        for (var i = 0; i < checkBoxCount.length; i++) {
            $("#grid input[name='checkOne']")[i].checked == true && count++;
            if (count >= 2) break;
        }
        return count >= 2 || count == 0 ? true : false;
    };
    //跳转页面-查看基地巡查详情
    window.inspectionView = function(data) {
        window.localStorage.setItem("baseInspectionId", data);
        $state.go("index.content.asms/baseInspection/inspectionView");
    };

    //跳转页面-修改基地巡查
    window.gotoUpdateBaseInspection = function(data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        $state.go("index.content.asms/baseInspection/inspectionUpdate");
    };

    //初始化部门下拉  待权限控制
    /*$.ajax({
         url:"/sofn-sys-web/sysDept/getSysDeptList",
         type:"post",
         dataType:"json",
         data:{status:1,draw:1,start:1,length:50000},
         headers:{
         token:window.localStorage.getItem("token")
         },
         success:function(json){
         var de= document.getElementById("deptId");
         for(var i=0;i<json.list.length;i++){
         var dept=json.list[i];
         de.options.add(new Option(dept.deptName,dept.id));
         }
         },
         fail: function (data) {
         alert("失败");
         }
         });*/
    //初始化职务下拉  589b7ec566c049d78fbe761fd69ed6f4
    $.ajax({
        url: "/sofn-sys-web/sysDict/getDictDataByCode",
        type: "post",
        dataType: "json",
        data: {
            codes: "PostType"
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            var di = document.getElementById("postId");
            di.options.add(new Option("无", ""));
            for (var i = 0; i < json.data[0].PostType.length; i++) {
                var dict = json.data[0].PostType[i];
                di.options.add(new Option(dict.dictName, dict.id));
            }
        },
        fail: function(data) {
            alert("失败");
        }
    });
    //初始化角色下拉  589b7ec566c049d78fbe761fd69ed6f4
    $.ajax({
        url: "/sofn-sys-web/sysRole/getSysRoleListRorUser",
        type: "post",
        dataType: "json",
        data: {
            roleName: "",
            status: "1",
            draw: 1,
            start: 0,
            length: 9999
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            var ro = document.getElementById("roleId");
            /*for(var i=0;i<json.data.length;i++){
                 var role=json.data[i];
                 ro.options.add(new Option(role.roleName,role.id));
                 }*/
            $("#roleId").combobox({
                data: json.data,
                multiple: true,
                valueField: "id",
                width: 198,
                textField: "roleName",
                formatter: function(row) {
                    var opts = $(this).combobox("options");
                    return (
                        '<input type="checkbox" class="combobox-checkbox">' +
                        row[opts.textField]
                    );
                },
                onShowPanel: function() {
                    var opts = $(this).combobox("options");
                    var target = this;
                    var values = $(target).combobox("getValues");
                    $.map(values, function(value) {
                        var el = opts.finder.getEl(target, value);
                        el
                            .find("input.combobox-checkbox")
                            ._propAttr("checked", true);
                    });
                },
                onLoadSuccess: function() {
                    var opts = $(this).combobox("options");
                    var target = this;
                    var values = $(target).combobox("getValues");
                    $.map(values, function(value) {
                        var el = opts.finder.getEl(target, value);
                        el
                            .find("input.combobox-checkbox")
                            ._propAttr("checked", true);
                    });
                },
                onSelect: function(row) {
                    // console.log(row);
                    var opts = $(this).combobox("options");
                    var el = opts.finder.getEl(this, row[opts.valueField]);
                    el
                        .find("input.combobox-checkbox")
                        ._propAttr("checked", true);
                },
                onUnselect: function(row) {
                    // console.log(row);
                    var opts = $(this).combobox("options");
                    var el = opts.finder.getEl(this, row[opts.valueField]);
                    el
                        .find("input.combobox-checkbox")
                        ._propAttr("checked", false);
                },
                onChange: validRoleId
            });
        },
        fail: function(data) {
            alert("失败");
        }
    });
    //初始化当前登录用户的机构信息
    $.ajax({
        url: "/sofn-sys-web/user/findSysUserOrganization",
        type: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            if (json.httpCode == 500) {
                alert("获取用户机构信息失败");
                return;
            }
            currentOrg = json.org;
        },
        fail: function(data) {
            alert("失败");
        }
    });
    //初始化调用
    datatableuser();
    $.fn.iSnotShow(["authority"]);
    //跳转重置密码页面
    window.resetPwd = function(account) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        if (confirm("是否重置密码？")) {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-sys-web/user/resetPassword",
                method: "post",
                params: { account: account }
            })
                .success(function(data) {
                    if (data.httpCode == 200) jBox.tip("重置成功！", "info");
                    else jBox.tip("重置失败！", "info");
                    $("#grid")
                        .dataTable()
                        .api()
                        .ajax.reload();
                })
                .error(function(data, status) {
                    ajaxErrorHandler(status);
                });
        }
    };

    var currentUserRoleStr = "";
    var hasGetNormalRoleInfo = false;
    // 打开导入弹框
    $scope.openImport = function() {
        if (currentOrg == null) {
            alert("当前用户的所属组织机构为空，不允许创建内部用户！");
            return;
        }
        $("#import_modal").modal("show");
        $file = $("#file-1");
        // 清除已选择的文件
        $file.wrap("<form></form>");
        $file.parent()[0].reset();
        $file.unwrap();

        if (currentOrg != null && !hasGetNormalRoleInfo) {
            hasGetNormalRoleInfo = true;
            // 获取内部用户基础角色信息
            $.ajax({
                url: "/sofn-sys-web/user/getNormalRoleInfo",
                type: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                },
                success: function(json) {
                    var roles = [];
                    json.normalRole.forEach(function(value) {
                        roles.push(value.id);
                    });
                    currentUserRoleStr = roles.join(",");
                },
                error: function(data) {
                    ajaxErrorHandler(data.status);
                }
            });
        }
    };

    var choose = document.getElementById("file-1");
    FileAPI.event.on(choose, "change", function(evt) {
        var files = FileAPI.getFiles(evt); // Retrieve file list
        FileAPI.filterFiles(
            files,
            function(file, info /**Object*/) {
                var acceptFileTypes = /^((xls|xlsx)|(application\/(vnd.ms-excel|vnd\.openxmlformats-officedocument\.spreadsheetml\.sheet)))$/i;
                if (!acceptFileTypes.test(file.type)) {
                    swal("", "只能上传Excel类型文档", "warning");
                    return false;
                }
                if (file.size > 5 * 1024 * 1024) {
                    swal("", "文件大小不能超过5MB", "warning");
                    return false;
                }

                return true;
            },
            function(files /**Array*/, rejected /**Array*/) {
                if (files.length) {
                    // Uploading Files
                    var submit = function(v, h, f) {
                        if (v == true) {
                            FileAPI.upload({
                                url: "/sofn-sys-web/user/importUserListByExcel",
                                files: { file: files[0] },
                                data: { roleStr: currentUserRoleStr,
                                        orgType: currentOrg.orgType},
                                headers: {
                                    token: window.localStorage.getItem("token")
                                },
                                progress: function(evt) {},
                                complete: function(err, xhr) {
                                    if (!err) {
                                        // All files successfully uploaded.
                                        var data = JSON.parse(xhr.responseText);
                                        if (data.httpCode != 200) {
                                            swal(
                                                "导入失败",
                                                "请检查数据是否正确！",
                                                "error"
                                            );
                                            return;
                                        }
                                        swal(
                                            "导入成功",
                                            "操作已成功!",
                                            "success"
                                        );
                                        $("#import_modal").modal("hide");
                                        $("#grid")
                                            .dataTable()
                                            .api()
                                            .ajax.reload();
                                    } else {
                                        ajaxErrorHandler(xhr.status);
                                    }
                                }
                            });
                        }
                    };
                    swal(
                        {
                            title: "确认导入",
                            text: files[0].name + " ？",
                            type: "warning",
                            showCancelButton: true,
                            closeOnConfirm: false,
                            showLoaderOnConfirm: true
                        },
                        submit
                    );
                }
            }
        );
    });
});
//当前登陆用户的机构信息
var currentOrg;
var saveUserInfo = function(state, http) {
    //验证表单，不通过则返回
    if (
        !$("#modalForm")
            .data("bootstrapValidator")
            .validate()
            .isValid()
    ) {
        return false;
    }
    if (!validRoleId($("#roleId").combobox("getText"))) {
        return false;
    }
    // if($("#roleId").val() == ""){
    //     alert("角色不能为空");
    //     return ;
    // }
    var params = {};
    params.id = $("#userId").val();
    if ($("#organizationId").val() == "" && JSON.parse(window.localStorage.getItem("menuObj")).authority != "2") {
        alert("当前用户的所属组织机构为空，不允许创建内部用户！");
        return;
    }
    params.organizationId = $("#organizationId").val();
    params.orgType = $("#orgType").val();
    params.deptId = $("#deptId").val();
    params.userName = $("#userName").val();
    params.reservedField1 = $("#reservedField1").val();
    params.reservedField2 = $("#reservedField2").val();
    var roleIds = $("#roleId").combobox("getValues");
    roleIds = roleIds.filter(function(value) {
        return value != "";
    });
    params.roleId = roleIds[0];
    params.postId = $("#postId").val();
    params.phone = $("#phone").val();
    params.email = $("#email").val();
    params.status = $("#status").val();
    params.roleStr = roleIds.join(",");

    var req = {
        method: "post",
        url: $("#modalForm").attr("action"),
        headers: {
            token: window.localStorage.getItem("token")
        },
        data: params
    };
    $("#save").addClass("disabld");
    http(req).then(
        function(data) {
            if (data.data.httpCode == "400") {
                alert(data.data.msg);
                return;
            }
            if (data.data.httpCode == "407") {
                alert("保存失败,该用户已存在!");
                return;
            }
            if (data.data.id == null) {
                alert("保存失败！请确保当前用户具有创建内部账户的权限！");
                return;
            }
            //成功后表单清空
            // $('#modalForm')[0].reset();
            $("#add_modal").modal("hide");
            $("#grid")
                .dataTable()
                .api()
                .ajax.reload();
            alert("保存成功");
            $("#save").removeClass("disabld");
        },
        function(data) {
            $("#save").removeClass("disabld");
            ajaxErrorHandler(data.status);
        }
    );
};
var saveImport = function() {
    alert("进入导入函数");
    $.ajaxFileUpload({
        url: "/sofn-sys-web/user/importUserListByExcel", //需要链接到服务器地址
        secureuri: true,
        fileElementId: "file", //文件选择框的id属性
        success: function(data, status) {
            alert(data);
        },
        error: function(data, status, e) {
            showDialogWithMsg("ideaMsg", "提示", "文件错误！");
        }
    });
};
//获取TreeGrid所有选中的行数据
var getTreeGridBySelectedAll = function(allData, selData) {
    for (var i = 0; i < allData.length; i++) {
        var data = allData[i];
        if (data.checked == true) {
            selData[selData.length] = data;
        }
        if (typeof data.children != "undefined") {
            getTreeGridBySelectedAll(data.children, selData);
        }
    }
};

var treeGridObj;
//用于计算列表序号
var pageNumber = 0;
var pageSize = 0;
var datatableuser = function() {
    var dataTable = $("#grid")
        .dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: false,
            // sScrollY: "auto",
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-sys-web/user/getSysUserList",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function(json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.pageNumber = json.page.start / json.page.length; //页码
                    json.pageSize = json.page.length; //每页个数
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    pageNumber = json.pageNumber;
                    pageSize = json.pageSize;
                    return json.data;
                },
                data: function(params) {
                    params.draw = 1;
                    params.userName = $("#keyWord").val();
                    params.status = $("#queryStatus").val();
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
                    title: function(data, type, row) {
                        return '<input type="checkbox" name="checkAll" id="checkAll">';
                    },
                    data: "active",
                    render: function(data, type, row) {
                        if (type === "display") {
                            return (
                                '<input type="checkbox" name="checkOne" value="' +
                                row.account +
                                '" class="editor-active">'
                            );
                        }
                        return data;
                    },
                    width: "3%",
                    className: "dt-body-center text-center"
                },
                // {
                //     //data : "RN",
                //     title: "序号",
                //     data: function (data, type, row, meta) {
                //         //return pageNumber*pageSize+meta.row + 1
                //         return meta.row+1;
                //     },
                //     width:"3%"
                //
                // },
                {
                    data: "account",
                    title: "帐号",
                    width: "15%"
                },
                {
                    data: "reservedField2",
                    title: "初始密码",
                    width: "15%"
                },
                {
                    data: "userName",
                    title: "用户姓名",
                    width: "15%"
                },
                {
                    data: "roleName",
                    title: "角色",
                    width: "15%"
                },
                {
                    data: "orgName",
                    title: "机构"
                },
                {
                    data: "phone",
                    title: "电话"
                },
                // {
                //     data: "email",
                //     title: "邮箱",
                //
                // },
                {
                    title: "状态",
                    data: function(data, type, row, meta) {
                        //0限制 1正常  2禁用 3注销
                        switch (data.status) {
                            case "0":
                                return "限制";
                                break;
                            case "1":
                                return "正常";
                                break;
                            case "2":
                                return "禁用";
                                break;
                            case "3":
                                return "注销";
                                break;
                        }
                        return "";
                    },
                    className: ""
                },
                {
                    title: "操作",
                    data: function(data, type, row, meta) {
                        //如果是超级管理员登录，则判断是否为部级监管机构管理员
                        if (
                            window.localStorage.getItem("userType") ==
                                "SUPERADMIN" &&
                            data.reservedField1 < 8
                        ) {
                            return (
                                '<a class="color" style="cursor: pointer;" onclick="window.queryUserReports(\'' +
                                data.id +
                                "')\">查看</a>"
                            );
                        }
                        if (
                            JSON.parse(window.localStorage.getItem("menuObj"))
                                .authority != "2"
                        ) {
                            return (
                                '<a class="color" style="cursor: pointer;" onclick="window.queryUserReports(\'' +
                                data.id +
                                "')\">查看</a>"
                            );
                        }
                        return (
                            '<a class="color" style="cursor: pointer; margin-right:15px" onclick="window.queryUserReports(\'' +
                            data.id +
                            "')\">查看</a>" +
                            '<a class="color" style="cursor: pointer; margin-right:15px" onclick="window.updateUserModal(\'' +
                            data.id +
                            "')\">修改</a>" +
                            '<a style="cursor: pointer" class="color" onclick="window.resetPwd(\'' +
                            data.account +
                            "')\">重置密码</a>"
                        ); //updateModal
                    },
                    className: ""
                }
            ]
        })
        .on("page.dt, draw.dt", function() {
            var table = $(this);
            table
                .find("thead")
                .find(":checkbox")
                .prop("checked", false);
        });

    return dataTable;
};

//修改
//因列表中点击修改不能调用到$sope.update
window.updateUserModal = function(id) {
    if (menuAuthority != MENU_READWRITE_AUTHORITY) {
        swal({
            title: "权限不足",
            text: "抱歉你没有修改权限!",
            type: "warning",
            showConfirmButton: true
        });
        return false;
    }
    $("#modalForm")
        .find("input, select")
        .prop("disabled", false)
        .end()
        .find(".gui-select")
        .removeClass("disabled");

    $("#modalForm").bootstrapValidator(); //必须有
    $("#modalForm")[0].reset();
    $.ajax({
        url: "/sofn-sys-web/user/getSysUserById",
        type: "post",
        dataType: "json",
        data: {
            id: id
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            $("#myModalLabel").html("修改用户");
            $("#roleId").combobox("readonly", false);
            $("#save").show();
            //设置actnion
            $("#modalForm").attr("action", "/sofn-sys-web/user/updateSysUser");
            //查询并设置值
            $("#userId").val(json.data.id);
            if (json.org != null) {
                $("#organizationId").val(json.data.organizationId);
                $("#orgName").html(json.org.orgName);
            } else {
                $("#orgName").html("无");
            }
            $("#deptId")
                .find("option[value='" + json.data.deptId + "']")
                .attr("selected", true);
            $("#userName").val(json.data.userName);
            //组装角色串
            var roles = [];
            var roleNames = [];
            for (var i = 0; i < json.roles.length; i++) {
                roles.push(json.roles[i].ROLEID);
                roleNames.push(json.roles[i].ROLENAME);
            }
            $("#pwd").show();
            $("#roleId")
                .combobox("clear")
                .combobox("setValues", roles)
                .combobox("setText", roleNames.join(","));
            set_select_checked('postId', json.data.postId);
            $("#phone").val(json.data.phone);
            $("#email").val(json.data.email);
            $("#reservedField2").val(json.data.reservedField2);
            set_select_checked('status', json.data.status);
            $("#add_modal").modal("show");
        },
        error: function(data) {
            ajaxErrorHandler(data.status);
        }
    });
};
//查看
window.queryUserReports = function(id) {
    $.ajax({
        url: "/sofn-sys-web/user/getSysUserById",
        type: "post",
        dataType: "json",
        data: {
            id: id
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            $("#myModalLabel").html("用户详情");
            $("#save").hide();
            //设置action
            $("#modalForm").attr("action", "/sofn-sys-web/user/updateSysUser");
            //查询并设置值
            $("#userId").val(json.data.id);
            // $("#organizationId").val(json.data.organizationId);
            // $("#orgName").html(json.org.orgName);
            if (json.org != null) {
                $("#organizationId").val(json.data.organizationId);
                $("#orgName").html(json.org.orgName);
            } else {
                $("#orgName").html("无");
            }
            $("#deptId")
                .find("option[value='" + json.data.deptId + "']")
                .attr("selected", true);
            $("#userName").val(json.data.userName);
            //组装角色串
            var roles = [];
            for (var i = 0; i < json.roles.length; i++) {
                roles.push(json.roles[i].ROLEID);
            }
            $("#pwd").show();
            $("#roleId").combobox("setValues", roles);
            set_select_checked('postId', json.data.postId);
            $("#phone").val(json.data.phone);
            $("#email").val(json.data.email);
            set_select_checked('status', json.data.status);
            $("#reservedField2").val(json.data.reservedField2);
            $("#add_modal").modal("show");
            $("#modalForm")
                .find("input, select")
                .prop("disabled", true)
                .end()
                .find(".gui-select")
                .addClass("disabled");
            $("#roleId").combobox("readonly", true);
        },
        error: function(data) {
            ajaxErrorHandler(data.status);
        }
    });
};

window.fileChange = function() {
    $("#filePath").val($("#file").val());
};