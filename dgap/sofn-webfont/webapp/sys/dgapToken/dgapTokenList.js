var myapp = angular.module("myapp", []);
myapp.controller("dgapTokenList", function ($scope, $http, $state) {
    //预先声明变量
    $scope.sysDgapToken = {};
    var area = "";
    $scope.updateFlag="";
    //获取当前登录用户机构信息，并完成初始化


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
        $scope.resourceName="";
        $("#reDir").dataTable().api().ajax.reload();
    };



    //模态框显示

    //bootstrap表格
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
            url: "/sofn-sys-web/dgapToken/getSysTokenList",
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                return json.data.list;
            },
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.resourceName =$scope.resourceName;//启用状态
            },
            type: "post"
        },
        columns: [
            {
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    var unEnable = row.flagStatus;
                    return '<input onclick="unCheckAll(\'' + row.ID + '\')"  type="checkbox"' + (unEnable == "disEnable" ? "disabled" : "") + ' ' + (unEnable == "disEnable" ? "" : "name=\"ck\"") + '/>';
                },
            },
            {
                data: "RESOURCE_NAME",
                title: "资源名称",
            },
            {
                data: "TOKEN",
                title: "访问令牌",
            },
            {
                data: "ID",
                title: "操作",
                render: function (data, type, row) { // 模板化列显示内容  deletes(\'' + data + '\')
                    return'<a data-toggle="modal1" data-target="#resDirAdd" style="margin-right: 30px;cursor: pointer" onclick="updateShow(\'' + data + '\')">修改</a>'+

                   '<a data-toggle="modal1" style="margin-right: 30px;cursor: pointer" onclick="deletes(\'' + data + '\')">删除</a>';//转义传值
                }
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

    //新增或 修改
    function add() {
        $scope.addModal = function () {
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            if ($scope.updateFlag == "1") {
                $scope.sysDgapToken.id=$("input[name='HiddenID']").val();
                $http({
                    url: "/sofn-sys-web/dgapToken/updateDgapToken",
                    data: $scope.sysDgapToken,
                    method: 'post',
                    headers: {
                        token:window.localStorage.getItem("token")
                    }
                }).success(function (data) {
                    $("button").attr("disabled", false);
                    $scope.updateFlag="";
                    $scope.partPlatFormApply={};
                    if (data.httpCode == 200) {
                        $("button").attr("disabled", false);
                        $.jBox.tip("修改成功！");
                    }else if(data.httpCode == 409){
                        $.jBox.tip("修改失败！资源名重复");
                    }else{
                        $.jBox.tip("修改失败");
                    }
                    $("#reDir").dataTable().api().ajax.reload();

                }).error(function () {
                    $("button").attr("disabled", false);
                    $.jBox.tip("修改失败！");
                })
            }
            else {
                $http({
                    url: "/sofn-sys-web/dgapToken/addDgapToken",
                    data: $scope.sysDgapToken,
                    //post传输方式   头信息
                    method: 'post',
                    //post传输方式   头信息
                    headers: {
                        token:window.localStorage.getItem("token")
                    }
                }).success(function (data) {
                    if (data.code == 200) {
                        $("button").attr("disabled", false);
                        $.jBox.tip("新增成功！");
                    }else if(data.httpCode == 409){
                        $.jBox.tip("新增失败！资源名重复");
                    }else{
                        $.jBox.tip("新增失败");
                    }
                    $("#reDir").dataTable().api().ajax.reload();

                }).error(function () {
                    $("button").attr("disabled", false);
                    $.jBox.tip("新增失败");
                })
            };
            }
    }


  //修改数据回显
    window.updateShow = function (data) {
        /*  数据回显*/
        $scope.dataId = data;
        $http({
            url: "/sofn-sys-web/dgapToken/queryById",
            data: {
                id: data
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                token:window.localStorage.getItem("token")
            }
        }).success(function (data) {
            $scope.sysDgapToken = data.data;
            //隐藏域赋值
            $scope.updateFlag="1";
            $("#resDirAddLabel").html("修改共享系统访问令牌");
            $("input[name='HiddenID']").val($scope.sysDgapToken.id);
            $("input[name='addResourceName']").val($scope.sysDgapToken.resourceName);
            $("input[name='addToken']").val($scope.sysDgapToken.token);
            $("#resDirAdd").modal("show");
        }).error(function (data) {

        });
    };



    //删除
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-sys-web/dgapToken/deleteDgapToken",
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
                        }else {
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
