var myapp = angular.module("myapp", []);
myapp.controller("roleList", function($scope, $http, $state) {
    $('#modalForm').bootstrapValidator(); //必须有
    $('#roleQueryForm').bootstrapValidator(); //必须有

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

    $scope.queryList = function() {
        if (!$("#roleQueryForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        $("#gridrole").dataTable().api().ajax.reload();
    };
    $("#roleQueryForm").find(".gui-btn").on("click", $scope.queryList);
    $scope.addModal = function() {
        $("#modalForm").data('bootstrapValidator').destroy(); //摧毁验证
        $('#modalForm').bootstrapValidator(); //必须有
        $('#modalForm')[0].reset();
        $("#myModalLabel").html("新增");
        //设置actnion
        $("#modalForm").attr("action", "/sofn-sys-web/sysRole/addSysRole");
        //设置机构信息
        if (currentOrg != null) {
            $("#organizationId").val(currentOrg.id);
            $("#orgName").html(currentOrg.orgName);
        } else {
            $("#orgName").html("无");
        }
        $("#save").show();
        $("#roleStatus").val('1');
        $("#roleStatus").find("option[value='1']").attr("selected", true);
        $("#add_modal").modal("show");
        $("#roleId").val("");
    }
    //所有菜单集合
    var allMenu = [];
    $scope.updateAuth = function() {
        var checkedId = $scope.getSelectRowsId();
        if (checkedId != false) {
            //打开模态窗
            var xxx = $("#myModal").modal("show");
            setTimeout(function() {
                //查询所有菜单  接口待更换
                if (allMenu != null && allMenu.length > 0) {
                    $scope.getRoleMenus(checkedId);
                } else {
                    $.ajax({
                        url: "/sofn-sys-web/sysMenu/getSysMenuListByToken",
                        type: "post",
                        dataType: "json",
                        data: {
                            menuName: "",
                            draw: 1,
                            start: 0,
                            length: 5000
                        },
                        headers: {
                            token: window.localStorage.getItem("token")
                        },
                        success: function(json) {
                            if (json.list == null || json.list.length <= 0) {
                                alert("读取权限菜单失败");
                                return;
                            }
                            allMenu = json.list;
                            $scope.getRoleMenus(checkedId);
                        },
                        error: function(data) {
                            ajaxErrorHandler(data.status);
                        }
                    });
                }
            }, 200);
        }
    }

    $scope.getRoleMenus = function(checkedId) {
        //查询角色权限菜单
        $.ajax({
            url: "/sofn-sys-web/sysRole/getSysRoleMenusById",
            type: "post",
            dataType: "json",
            headers: {
                token: window.localStorage.getItem("token")
            },
            data: {
                id: checkedId.toString()
            },
            success: function(json) {
                //编制菜单层级、配置默认选中
                var data = [];
                var checkedARR = [];
                //var newAllMenu=[];
                //$.extend(true,newAllMenu, allMenu);
                //要求层级越底层越靠前
                for (var i = 0; i < allMenu.length; i++) {
                    var menuObj = allMenu[i];
                    //初始化取消选中
                    //menuObj._checked=false;
                    //循环默认选中
                    if (json != null && json.menus != null && json.menus.length > 0) {
                        var con = false;
                        for (var n = 0; n < json.menus.length; n++) {
                            var roleMenuObj = json.menus[n];
                            if (roleMenuObj.menuId == menuObj.id) {
                                con = true;
                                checkedARR.push(menuObj.id);
                                menuObj.authority = roleMenuObj.authority;
                                break;
                            }
                        }
                        menuObj.checked = con;
                    }
                    //分层前吧原有分层清空
                    if (menuObj.children != null)
                        delete menuObj.children;

                    //循环分层
                    if (menuObj.parentId == null || menuObj.parentId == "" || menuObj.parentId == "0") {
                        data[data.length] = menuObj;
                        continue;
                    }
                    //默认为顶级
                    var con = true;
                    for (var j = 0; j < allMenu.length; j++) {
                        var dataObj = allMenu[j];
                        if (dataObj.id == menuObj.parentId) {
                            if (dataObj.children == null)
                                dataObj.children = [];
                            dataObj.children[dataObj.children.length] = menuObj;
                            con = false;
                            break;
                        }
                    }
                    if (con)
                        data[data.length] = menuObj;

                }
                var rootId = "";
                for (var i = 0; i < data.length; i++) {
                    if (data[i].parentId == "-1") {
                        rootId = data[i].id;
                        break;
                    }
                }
                for (var i = data.length - 1; i >= 0; i--) {
                    if (data[i].parentId != rootId && data[i].parentId != "-1") {
                        data.splice(i, 1);
                    }
                }
                var treeEl = $('#tb123');
                treeEl.treegrid({
                    showFooter: true,
                    idField: 'id',
                    treeField: 'id',
                    width: '100%',
                    data: data,
                    checkbox: true,
                    onLoadSuccess: function() {
                        // console.log('this', this);
                    },
                    columns: [
                        [{
                            title: '',
                            field: 'id',
                            formatter: function(value, row) {
                                return '<input type="hidden" name="checkedTreeNode" value=' + value + ' >';
                            }
                        }, {
                            title: '菜单名',
                            width: "60%",
                            field: 'menuName'
                        }, {
                            title: '权限',
                            width: '20%',
                            field: 'children',
                            formatter: function(value, row) {
                                //if (value != null)
                                if ((row.url == null || row.url == "") && row.menuType != 2)
                                    return "";

                                //domId规则 name+数据id值
                                return '<select name="checkedTreeSelect" id="checkedTreeSelect' + row.id + '"><option value="2" ' + (row.authority == 2 ? ' selected="true"' : '') + '>读写</option><option value="1" ' + (row.authority == 1 ? ' selected="true"' : '') + '>只读</option></select>';
                            }
                        }]
                    ]
                });

                var checkedRows = treeEl.treegrid('getCheckedNodes');
                //console.log('checkedRows', checkedRows);
                checkedRows.map(function(row) {
                    ~checkedARR.indexOf(row.id) ? treeEl.treegrid('checkNode', row.id) : treeEl.treegrid('uncheckNode', row.id);
                });

            },
            error: function(data) {
                ajaxErrorHandler(data.status);
            }
        });
    }
    $scope.cancelMain = function() {
        //$scope.getFormJson( $("#add_modal"));
        $('#modalForm')[0].reset();
        $("#add_modal").modal("hide");
    };
    $scope.cancelMainAuth = function() {
        //$scope.getFormJson( $("#add_modal"));
        $("#myModal").modal("hide");
    };

    $scope.save = function() {
        saveInfo($state, $http);
    }
    $scope.saveAuth = function() {
        saveAuth($state, $http);
    }

    $scope.delete = function() {
        var ids = $scope.getSelectRowsIds();
        if (ids != false) {
            if (confirm('是否删除已选择的角色？')) {
                $.ajax({
                    url: "/sofn-sys-web/sysRole/deleteRole",
                    type: "post",
                    dataType: "json",
                    data: {
                        idStr: ids.toString()
                    },
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    success: function(json) {
                        if (json.data != "" && json.data != null) {
                            alert("删除失败！角色“" + json.data + "”被使用，不能够删除，请重新选择！");
                        } else {
                            alert("删除成功！");
                            $("#gridrole").dataTable().api().ajax.reload();
                        }
                    },
                    error: function(data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        }
    }

    $scope.getSelectRowsIds = function() {
        var rows = $("#gridrole").DataTable().rows('.selected').data();
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
    $scope.getSelectRowsId = function() {
        var rows = $("#gridrole").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请选择一行记录！");
            return false;
        } else if (length > 1) {
            alert("只能选择一行记录！");
            return false;
        }
        return rows[0].id;
    }

    $scope.checkSelect = function() {
        var checkBoxCount = $("#gridrole input[name='checkOne']");
        var count = 0;
        for (var i = 0; i < checkBoxCount.length; i++) {
            $("#gridrole input[name='checkOne']")[i].checked == true && count++;
            if (count >= 2)
                break;
        }
        return (count >= 2 || count == 0) ? true : false;
    }
    //跳转页面-查看基地巡查详情
    window.inspectionView = function(data) {
        window.localStorage.setItem("baseInspectionId", data);
        $state.go("index.content.asms/baseInspection/inspectionView");
    }

    //跳转页面-修改基地巡查
    window.gotoUpdateBaseInspection = function(data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        $state.go("index.content.asms/baseInspection/inspectionUpdate")
    }

    //初始化部门下拉  待权限控制
    /* $.ajax({
     url:"/sofn-sys-web/sysDept/getSysDeptList",
     type:"post",
     dataType:"json",
     data:{status:1,draw:1,start:0,length:50000},
     success:function(json){
     var de= document.getElementById("deptId");
     for(var i=0;i<json.list.length;i++){
     var dept=json.list[i];
     //de.options.add(new Option(dept.deptName,dept.id));
     }
     },
     fail: function (data) {
     alert("失败");
     }
     });*/
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
    datatablerole();
    //控制权限按钮
    $.fn.iSnotShow(["authority"]);
})
//当前登陆用户的机构信息
var currentOrg;

var saveInfo = function(state, http) {
    //验证表单，不通过则返回
    if (!$("#modalForm").data('bootstrapValidator').validate().isValid()) {
        return false;
    } //必须有
    var params = {};
    params.id = $("#roleId").val();
    params.organizationId = $("#organizationId").val();
    params.deptId = $("#deptId").val();
    params.roleName = $("#roleName").val();
    params.roleCode = $("#roleCode").val();
    params.describe = $("#roleDescribe").val();
    params.status = $("#roleStatus").val();

    var req = {
        method: 'post',
        url: $("#modalForm").attr("action"),
        headers: {
            token: window.localStorage.getItem("token")
        },
        data: params
    }

    http(req).then(function(data) {
        if (data.data.httpCode == "407") {
            alert("保存失败，当前角色已存在！");
            return;
        }
        if (data.data.httpCode == "200") {
            //成功后表单清空
            alert("保存成功");
            $('#modalForm')[0].reset();
            $("#add_modal").modal("hide");
            $("#gridrole").dataTable().api().ajax.reload();
        } else {
            alert("保存失败");
        }

    }, function(data) {
        ajaxErrorHandler(data.status);
    });
}
// 刷新LocalStorage中的菜单信息
var refreshMenusOfLocalStorage = function() {
    $.ajax({
        url: "/sofn-sys-web/ssoMenu/getMenusByUserId",
        type: "post",
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(data) {
            //将菜单存入浏览器缓存
            window.localStorage.setItem("menus", JSON.stringify(data.data));
        }
    });
}
var saveAuth = function(state, http) {
    //获取所选中的值$("#dg").datagrid('getSelected')
    var allData = $('#tb123').treegrid('getData');
    var selData = []; //所有选中值
    getTreeGridBySelectedAll(allData, selData);
    //获取选中的roleId
    //获取roleId对应的读写权限
    var roleMenus = [];
    for (var i = 0; i < selData.length; i++) {
        var row = selData[i];
        var menuObj = {
            menuId: row.id
        };
        //if(typeof(d.children) == "undefined"){
        if ((row.url != null && row.url != "") || row.menuType == 2) {
            menuObj.authority = $("#checkedTreeSelect" + row.id).val();
        }
        roleMenus[roleMenus.length] = menuObj;
    }
    //调用保存接口
    $.ajax({
        url: "/sofn-sys-web/sysRole/updateSysRoleMenus",
        type: "post",
        dataType: "json",
        data: {
            id: $("#gridrole").DataTable().rows('.selected').data()[0].id,
            menusJson: JSON.stringify(roleMenus)
        },
        headers: {
            token: window.localStorage.getItem("token")
        },
        success: function(json) {
            //验证是否成功
            if (json.id != null) {
                window.httpurl('SYS');
                refreshMenusOfLocalStorage();
                alert("权限修改成功");
            }
            //关闭权限窗口
            $("#myModal").modal("hide");
        },
        error: function(data) {
            ajaxErrorHandler(data.status);
        }
    });

}
//获取TreeGrid所有选中的行数据
var getTreeGridBySelectedAll = function(allData, selData) {
    for (var i = 0; i < allData.length; i++) {
        var data = allData[i];
        //选中或者半勾选
        if (data.checked == true || data.checkState == "indeterminate") {
            selData[selData.length] = data;
        }
        if (typeof(data.children) != "undefined") {
            getTreeGridBySelectedAll(data.children, selData);
        }
    }
}
//用于计算列表序号
var pageNumber = 0;
var pageSize = 0;
var datatablerole = function() {
    var dataTable = $("#gridrole").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysRole/getSysRoleList",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function(json) {
                if (json.httpCode != 200) {
                    alert(json.msg);
                    return;
                }
                json.recordsTotal = json.page.recordsTotal; //总个数
                json.recordsFiltered = json.page.recordsFiltered;
                json.pageNumber = json.page.start / json.page.length; //页码
                json.pageSize = json.page.length; //每页个数
                json.start = json.page.start; //起始位置
                json.length = json.page.length;
                pageNumber = json.pageNumber;
                pageSize = json.pageSize;
                /*for(var i=0;i<json.data.list.length;i++){
                 if(json.data.list[i].INSPECTION_TIME!=null&&json.data.list[i].INSPECTION_TIME!=""&&json.data.list[i].INSPECTION_TIME!=undefined){
                 json.data.list[i].INSPECTION_TIME=new Date(parseInt(json.data.list[i].INSPECTION_TIME)+1000*60*60*24).toISOString().slice(0,10);
                 }
                 }*/
                return json.data;

            },
            data: function(params) {
                params.draw = 1;

                params.roleName = $.trim($("#keyWord").val());

                //params=params || {};
                /* params.id=1;
                 */

                //return params;
            },
            headers: {
                token: window.localStorage.getItem("token")
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
                    return '<input type="checkbox" name="checkOne" value="' + row.account + '" class="editor-active">';
                }
                return data;
            },
            className: "text-center",
            width: "5%"
        }, {
            //data : "RN",
            title: "序号",
            data: function(data, type, row, meta) {
                //return pageNumber*pageSize+meta.row + 1
                return meta.row + 1;
            },
            className: "text-center",
            width: "5%"

        }, {
            data: "roleName",
            title: "角色名称",
            width: "25%"
        }, {
            data: "describe",
            title: "角色描述",
            width: "40%"
        }, {
            data: "statusName",
            title: "角色状态",
            data: function(data, type, row, meta) {
                return data.status == 1 ? "正常" : "禁用";
            },
            className: "text-center",
            width: "10%"

        }, {
            title: "操作",
            data: function(data, type, row, meta) {
                //alert(window.localStorage.getItem("menuObj"));
                if (JSON.parse(window.localStorage.getItem("menuObj")).authority != "2") {
                    return '<a class="color mr15" style="cursor: pointer;" onclick="window.queryReports(\'' + data.id + '\')">查看</a>';
                }
                return '<a style="cursor: pointer" class="color mr15" onclick="window.queryReports(\'' + data.id + '\')">查看</a>' + '<a style="cursor: pointer" class="color" onclick="window.updateModal(\'' + data.id + '\')">修改</a>'; //updateModal
            },
            className: "text-center",
            width: "15%"

        }]
    })

    return dataTable;
};
//因列表中点击修改不能调用到$sope.update
window.updateModal = function(id) {
    $("#modalForm").data('bootstrapValidator').destroy(); //摧毁验证
    $('#modalForm').bootstrapValidator(); //必须有
    $('#modalForm')[0].reset();
    $.ajax({
        url: "/sofn-sys-web/sysRole/getSysRoleById",
        type: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        },
        data: {
            id: id
        },
        success: function(json) {
            $("#myModalLabel").html("修改");
            $("#save").show();
            //设置actnion
            $("#modalForm").attr("action", "/sofn-sys-web/sysRole/updateSysRole");
            //查询并设置值
            $("#roleId").val(json.data.id);
            if (json.org != null) {
                $("#organizationId").val(json.data.organizationId);
                $("#orgName").html(json.org.orgName);
            } else {
                $("#orgName").html("无");
            }
            $("#deptId").val(json.data.deptId);
            $("#deptId").find("option[value='" + json.data.deptId + "']").attr("selected", true);
            $("#roleName").val(json.data.roleName);
            $("#roleCode").val(json.data.roleCode);
            $("#roleStatus").val(json.data.status);
            $("#roleStatus").find("option[value='" + json.data.status + "']").attr("selected", true);
            $("#roleDescribe").val(json.data.describe);
            $("#add_modal").modal("show");
        },
        error: function(data) {
            ajaxErrorHandler(data.status);
        }
    });
}
//查看
window.queryReports = function(id) {
    $("#modalForm").data('bootstrapValidator').destroy(); //摧毁验证
    $('#modalForm').bootstrapValidator(); //必须有
    $('#modalForm')[0].reset();
    $.ajax({
        url: "/sofn-sys-web/sysRole/getSysRoleById",
        type: "post",
        dataType: "json",
        headers: {
            token: window.localStorage.getItem("token")
        },
        data: {
            id: id
        },
        success: function(json) {
            $("#myModalLabel").html("查看");
            $("#save").hide();
            //设置actnion
            $("#modalForm").attr("action", "/sofn-sys-web/sysRole/updateSysRole");

            if (json.org != null) {
                $("#organizationId").val(json.data.organizationId);
                $("#orgName").html(json.org.orgName);
            } else {
                $("#orgName").html("无");
            }
            //查询并设置值
            $("#roleId").val(json.data.id);
            $("#deptId").val(json.data.deptId);
            $("#deptId").find("option[value='" + json.data.deptId + "']").attr("selected", true);
            $("#roleName").val(json.data.roleName);
            $("#roleCode").val(json.data.roleCode);
            $("#roleStatus").val(json.data.status);
            $("#roleStatus").find("option[value='" + json.data.status + "']").attr("selected", true);
            $("#roleDescribe").val(json.data.describe);
            $("#add_modal").modal("show");
        },
        error: function(data) {
            ajaxErrorHandler(data.status);
        }
    });
}