var myapp = angular.module("myapp", []);
var menuAuthority;
myapp.controller("menulist", function($scope, $http, $state) {
    var token = window.localStorage.getItem("token");
    $("#sectionForm").bootstrapValidator();
    $("#addSysMenuForm").bootstrapValidator();
    $("#updateMenuForm").bootstrapValidator();

    $scope.sysMenu2 = {};
    window.selectedMenuNodeId = "";
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('菜单管理');
    menuAuthority = $scope.menuAuthority;
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

    $scope.menusJson = "";
    $scope.loadAllMenuData = function(successCallback) {
        $http({
            url: "/sofn-sys-web/sysMenu/menuTree.do",
            method: "post",
            headers: {
                post: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }
        }).success(function(data) {
            $scope.menusJson = data.rows;
            //console.log(data.rows);
            if (typeof successCallback == 'function') {
                successCallback();
            }
        }).error(function() {
            alert("系统异常,请联系后台工作人员");
        })
    }
    //默认加载菜单数据
    $scope.loadAllMenuData();

    //新增
    $scope.listAdd = function(id) {
        // console.log("selectedMenuNodeId: %s", window.selectedMenuNodeId);
        // $("#addMenuParentId").val(window.selectedMenuNodeId);
        $scope.sysMenu = {};
        $scope.sysMenu.parentId = window.selectedMenuNodeId;

        $("#addSysMenuForm").data('bootstrapValidator').resetForm(true);//摧毁验证
        $('#addSysMenuForm')[0].reset();

        $("#myModal").modal("show");

    }
            //combotree
        // $('#cc').combotree({
        //     url: 'sofn-sys-web/sysMenu/recursionQuery?',
        //     $('#cc').combotree({
        //     url: 'sofn-sys-web/sysMenu/recursionQuery?',
        //     required: true,
        //     onLoadSuccess: function(node, data) { //数据加载完毕事件
        //     },
        //     onChange: function(n, o) { //will be triggered when value changes
        //         var treeObject = $('#cc').combotree('tree'); // get the tree object
        //         var treeNode = treeObject.tree('getSelected'); // get selected node
        //         $("input[name='parentId']").val(treeNode.id);
        //     }
        // });
        //query
        $scope.queryList = function() {
            if (!$("#sectionForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            var node = $('#easyUi').tree('getSelected');
            var param = {
                "parentId": node ? node.id : "",
                "start": 0,
                menuName: $("input[name='menuName0']").val()
            };
            $("#grid").dataTable().api().settings()[0].ajax.data = param;
            $("#grid").dataTable().api().ajax.reload();
        }
        $("#sectionForm").find(".gui-btn").on("click",$scope.queryList);
        //enter submit
        $scope.onInputChanged = function($event){
            if ($event && $event.keyCode == 13) {
                $scope.queryList();
            }
        };
            //put the value of one record into the modal form
        $scope.putRow = function(row) {
                // var treeObject = $('#cc').combotree('tree');
                var root = treeObject.tree("getRoot");
                if (root.id == row.parentId) {
                    $("#treeCombox").hide();
                } else {
                    $("#treeCombox").show();
                }
                // $('#cc').combotree("setValue", row.parentId);
                $("input[name='id']").val(row.id);
                $("input[name='parentId']").val(row.parentId);
                $("input[name='sysMenuName']").val(row.sysMenuName);
                $("input[name='sysMenuCode']").val(row.sysMenuCode);
                $("input[name='sortid']").val(row.sortid);
                $("textarea[name='remark']").val(row.remark);
            }
            //clear the form of modal
        $scope.clearRow = function() {
                $("input[name='id']").val("");
                $("input[name='parentId']").val("");
                $("input[name='sysMenuName']").val("");
                $("input[name='sysMenuCode']").val("");
                $("input[name='sortid']").val("");
                $("textarea[name='remark']").val("");
            }
            //close the modal
        $scope.cancelMain = function() {
            //$scope.getFormJson( $("#add_modal"));
            $("#add_modal").modal("hide");
        };
        //save the data(update & add)
        $scope.save = function() {
            save($state, $http);
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
        var dataTable = datatable();
        easytree();
        checkBoxChange();
        //新增测试信息
        $scope.addSysMenu = function() {

//            if (!$("#addSysMenuForm").data('bootstrapValidator').validate().isValid()) {
//                return false;
//            }
                $scope.sysMenu.menuName = $("input[name='menuName']").val();
                $scope.sysMenu.numbers = $("input[name='numbers']").val();
                $scope.sysMenu.parentId = $("select[name='parentId']").val();
                $scope.sysMenu.describes = $("input[name='describes']").val();
                $scope.sysMenu.url = $("input[name='url']").val();
                $scope.sysMenu.menuType = $("select[name='menuType']").val();
                $scope.sysMenu.menuValue = $("input[name='menuValue']").val();
                $scope.sysMenu.image = $("input[name='image']").val();
                $scope.sysMenu.reservedField1 = $("input[name='reservedField1']").val();
                $scope.sysMenu.status = 1;
                if($.trim($scope.sysMenu.menuName).length <= 0){
                    alert('菜单名称不能为空');
                    return;
                }
                if($.trim($scope.sysMenu.menuName).length >= 21){
                    alert('菜单名称字符长度不能大于20');
                    return;
                }
                $http({
                    url: "/sofn-sys-web/sysMenu/addSysMenu",
                    data: $scope.sysMenu,
                    method: "post",
                    headers: {
                        token: window.localStorage.getItem("token")
                    }
                }).success(function(data) {
                    if (data.code == 200) {
                        alert("保存成功");
                        $("#myModal").modal("hide");
                        //           $scope.getMenusTable().api().ajax.reload();
                        $("#grid").dataTable().api().ajax.reload();

                        // 重新加载菜单数据
                        $scope.loadAllMenuData();
                    } else {
                        alert(data.msg);
                    }
                }).error(function(data, status) {
                    ajaxErrorHandler(status);
                })
            }



        /**
         * 获取需要修改的数据
         */
        $scope.sysMenu = {}
        window.updateData = function(data) {
            $http({
                url:"/sofn-sys-web/sysMenu/findMenuById",
                method:"post",
                dataType: "json",
                data:data ,
                headers:{
                    token:window.localStorage.getItem("token")
                }
            }).success(function(data){
                $scope.sysMenu2 = data.sysMenu;
                console.log($scope.sysMenu2.parentId);
                $("#myModal2").modal("show");
            }).error(function(data, status){
                ajaxErrorHandler(status);
            });
        }
            //点击保存
        $scope.updateMenu = function() {
            if($.trim($scope.sysMenu2.menuName).length <= 0){
                alert('菜单名称不能为空');
                return;
            }
            if($.trim($scope.sysMenu2.menuName).length > 20) {
                alert('菜单名称字符长度不能大于20');
                return;
            }
            $http({
                url: "/sofn-sys-web/sysMenu/updateMenu",
                data: $scope.sysMenu2,
                method: "post",
                //post传输方式   头信息
                headers: {
                    token:window.localStorage.getItem("token")
                }
            }).success(function(data) {
                if (data.code == 200) {
                    alert("修改成功");
                    $("#myModal2").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();

                    // 重新加载菜单数据
                    $scope.loadAllMenuData();
                } else {
                    alert(data.msg);
                }
            }).error(function(data, status) {
                ajaxErrorHandler(status);
            })
        }
            /**
             * 执行删除的方法
             */
        window.deleteData = function(data) {
            //选择是否进行删除
            if (confirm('是否进行删除...')) {
                $.ajax({
                    url: "/sofn-sys-web/sysMenu/deleteMenu",
                    type: "post",
                    headers:{token:window.localStorage.getItem("token")},
                    dataType: "json",
                    data: {
                        id: data
                    },
                    success: function(data) {
                        alert("删除成功！");
                        $("#grid").dataTable().api().ajax.reload();

                        // 重新加载菜单数据
                        $scope.$evalAsync(function() {
                            $scope.loadAllMenuData();
                        });
                    },
                    error: function(data) {
                        ajaxErrorHandler(data.status);
                    }
                });
            }
        };
    })
    //save the data when update & add
var save = function(state, http) {
    var params = {};
    params.id = $("input[name='id']").val();
    params.parentId = $("input[name='parentId']").val();
    params.sysMenuName = $("input[name='sysMenuName']").val();
    params.sysMenuCode = $("input[name='sysMenuCode']").val();
    params.sortid = $("input[name='sortid']").val();
    params.remark = $("textarea[name='remark']").val();
    if (params.parentId == null || params.parentId == "") {
        // var treeObject = $('#cc').combotree('tree');
        var root = treeObject.tree("getRoot");
        params.parentId = root.id;
    }
    var req = {
        method: 'post',
        url: '/sofn-sys-web/sysMenu/add',
        headers: {
            post: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        },
        data: params
    }
    http(req).then(function(data) {
        $("#add_modal").modal("hide");
        $("#grid").dataTable().api().ajax.reload();
        $('#easyUi').tree('options').url = "/sofn-sys-web/sysMenu/queryMenu?parentId=-1";
        $('#easyUi').tree('reload');
        // $('#cc').combotree('reload');
        $.jBox.tip("保存成功");
    }, function(data) {
        console.log(data);
    });
}
var checkRecode = function(el) {
    var bool = $(el).prop("checked");
    $(el).prop("checked", !bool);
    var tr = $(el).parent().parent();
    bool
        ? tr.removeClass("selected") : tr.addClass("selected");
}
var checkBoxChange = function() {
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
var datatable = function() {
    var dataTable = $("#grid").dataTable({
        serverSide: false, // 分页，是否服务器端处理
        // fnDrawCallback: function() {
        //     //点击行内任意内容即选择行
        //     $("#grid tbody tr td").each(function(i,o){
        //         $(o).click(function(){
        //             var checked = $(this).parent().find("input[name='checkOne']")[0]
        //             if(checked.checked){
        //                 checked.checked = false;
        //                 $(this).parent().removeClass("selected");
        //             }else {
        //                 checked.checked = true;
        //                 $(this).parent().addClass("selected");
        //             }
        //         });
        //     })
        // },
        ajax: {
            url: "/sofn-sys-web/sysMenu/queryByKeyID",
            //dataSrc : "rows", // 加载表格的相应数据源
            headers: {token: window.localStorage.getItem("token")},
            dataSrc: function(json) {
                if (json == null) {} else {
                    return json.list
                }
            },
            data: function(params) {
                params = params || {};
                if (params.parentId == null) {
                    params.parentId = "";
                }
                params.menuName = $("input[name='menuName0']").val();
            },
            type: "post"
        },
        columns: [{
                data: "menuName",
                title: "菜单名单",
                width: "15%"
            }, {
                data: "numbers",
                title: "序号",
                width: "5%"
            }, {
                data: "reservedField1",
                title: "父级菜单",
                width: "15%"
            }, {
                data: "describes",
                title: "菜单描述",
                width: "15%"
            }, {
                data: "url",
                title: "url",
                width: "17%"
            }, {
                data: "menuType",
                title: "菜单类型",
                width: "10%"
            }, {
                data: "menuValue",
                title: "菜单值",
                width: "8%"
            },
            //              {
            //                  data: "image",
            //                  title: "图标"
            //              },
            {
                data: "id",
                title: "操作",
                render: function(data, type, row) {
                    if (menuAuthority != MENU_READWRITE_AUTHORITY) {
                        return '';
                    }
                    // 数据操作选择项
                    var update_a = '<a style="margin-right: 10px;cursor: pointer" class="color" onclick="updateData(\'' + data + '\')">修改</a>';
                    var delete_a = '<a style="cursor: pointer" class="color" onclick="deleteData(\'' + data + '\')">删除</a>';
                    return update_a + delete_a;
                },
                className: 'text-center',
                width: "15%"
            }, {
                data: "id",
                title: "id",
                visible: false
            }
        ]
    })
    return dataTable;
};
var easytree = function(dataTable) {
    $("#easyUi").tree({
        lines: true,
        url: "/sofn-sys-web/sysMenu/queryMenu?parentId=-1",
        onBeforeExpand: function(node, param) {
            $('#easyUi').tree('options').url = "/sofn-sys-web/sysMenu/queryMenu?&delFlag=Y&parentId=" + node.id;
        },
        onClick: function(node) {
            /*$("#grid").dataTable().api().ajax.params().id=node.id;
            //$("#grid").dataTable().api().ajax.params().start=0;
            $("#grid").dataTable().api().ajax.params().draw=5;*/
            var param = {
                "parentId": node.id,
                "start": 0
            };
            // $("select[name='parentId']").val(node.id);
            window.selectedMenuNodeId = node.id;
            $("#grid").dataTable().api().settings()[0].ajax.data = param;
            $("#grid").dataTable().api().ajax.reload();
        },
        onLoadSuccess: function(node) { //隐藏根节点
            /* var nodeId=$('#easyUi').tree('getRoot').id;
            if(nodeId!=1) return;;
            $('#easyUi').tree('options').url = "/sofn-sys-web/sysMenu/queryMenu?sysMenuId=" + nodeId;
            $('#easyUi').tree('reload');
            $("#"+$('#easyUi').tree('getRoot').domId).hide();*/
            //$("#"+$('#easyUi').tree('getRoot').domId).css("display","none")
        }
    })
}
