var myapp = angular.module("myapp", []);
myapp.controller("adsPackageManage", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for (var i = 0; i < a.length; i++) {
        if (a[i].menuName == "检测项包配置") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    //检测项数组
    $scope.checkItems = [];

    $scope.clickEvent = function () {
        var str = $("#packageNames").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("长度过长,请更改输入内容", 'info');
        } else
            $("#grid").dataTable().api().ajax.reload();
    };
    //绑定回车事件
    $scope.enterEvent = function (e) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {
            $scope.clickEvent();
        }
    };
    //获取行业分类字典
    $scope.IndustryType = new Array();
    var industryId = "";
    window.loadA = function () {
        /**
         * 加载行业分类字典
         */

        $http({
            url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
            method: "post",
            dataType: "json",
        }).success(function (data) {
            $scope.IndustryType = data.data;
            $scope.selectedIndustry = $scope.IndustryType[0].dictName;
            industryId = $scope.IndustryType[0].dictCode;
        });
    };

    loadA();

    //二级联动改变值和获取值
    $(document).ready(function () {
        $('#industry').change(function () {
            var s = $('#industry option:selected').val();
            $($scope.IndustryType).each(function (idx) {
                var a = $(this)[0];
                if (a.dictName == s) {
                    industryId = a.dictCode;
                    return false;
                }
            });
        });
    });

    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after")


    //检测包的查询更新
    $("#seachTask").click(function () {
        $scope.clickEvent();
    });


    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsCheckPackage/getPageInfoAll",
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
                    //分页条件查询，不实现，自己写
                    params.packageName = $("#packageNames").val();
                    params.industry = $("#industry_name").val();
                    params.token = token;
                },
                type: "post"
            },
            columns: [
                {
                    title: "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='" + row.id + "'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "packageName",
                    title: "检测包名称"
                },
                {
                    data: "industry",
                    title: "行业"
                },
                {
                    data: "tempTime",
                    title: "创建时间"
                },
                {
                    data: "id",
                    title: "数据操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a class="pointer mr15" onclick="editCheckPackage(\'' + row.id + '\') ">修改</a>';
                        var look_b = '<a class="pointer mr15" onclick="showCheckPackage(\'' + row.id + '\') ">查看</a>';
                        if ($scope.disabled) {
                            look_a = '<a class="pointer mr15" style="color: grey">修改</a>';
                        }
                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    //检测项目模态框的查询更新
    $("#seachTask1").click(function () {
        var str = $("#checkNames").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("长度过长,请更改输入内容", 'info');
        } else $("#itemGrid").dataTable().api().ajax.reload();
    });

    //全选
    window.checkAll = function () {
        var val = $("input[name='checkAll']:checked");
        if (val.length > 0) {
            //全选
            $("input[name='task']").attr("checked", true);
        } else {
            $("input[name='task']").attr("checked", false);
        }
    };

    //新增检测包按钮事件
    $scope.addCheckPackageModel = function () {
        if (!$("#addCheckPackage").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var packageName = $("#packageName2").val();
        var industry = $('#industry2 option:selected').val();
        var checkItems = $('#checkItems2').val();
        var arr = $('#checkId2').val();
        var length = packageName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("检测包名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
            return false;
        }
        if (packageName == null || packageName == "" || packageName == "undefined") {
            jBox.tip("检测包名称不能为空", 'info');
            return false;
        }
        if (industry == null || industry == "" || industry == "undefined") {
            jBox.tip("适用行业不能为空", 'info');
            return false;
        }
        if (checkItems == null || checkItems == "" || checkItems == "undefined") {
            jBox.tip("检测项目不能为空", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsCheckPackage/addCheckPackage",
            type: "post",
            dataType: "json",
            data: {
                "packageName": packageName,
                "industry": industry,
                "industryId": industryId,
                "token": token,
                "checkItems": checkItems,
                "checkIds": arr.toString()
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#addModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    };

    //修改检测包按钮事件
    $scope.updatePackage = function () {
        var packageName = $("#packageName").val();
        var industry = $('#industry option:selected').val();
        var checkItems = $('#checkItems').val();
        var packageId = $('#packageId').val();
        var arr = $('#checkId').val();
        var length = packageName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("检测包名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
            return false;
        }
        if (packageName == null || packageName == "" || packageName == "undefined") {
            jBox.tip("检测包名称不能为空", 'info');
            return false;
        }
        // if (industry==null || industry=="" || industry=="undefined"){
        //     jBox.tip("适用行业不能为空", 'info');
        //     return false;
        // }
        if (checkItems == null || checkItems == "" || checkItems == "undefined") {
            jBox.tip("检测项目不能为空", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsCheckPackage/updatePackage",
            type: "post",
            dataType: "json",
            data: {
                "packageName": packageName,
                "industry": industry,
                "industryId": industryId,
                "token": token,
                "checkItems": checkItems,
                "id": packageId,
                "checkIds": arr.toString()
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#editModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改成功", 'info');
                } else if (data.httpCode == 400) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                } else if (data.httpCode == 409) {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败,该名称已被使用", 'info');
                } else {
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败", 'info');
                }
            }
        });
    };


    //批量删除全中的数据
    window.delCheckPackage = function () {
        var check = $("input[name='task']:checked");
        if (check.length > 0) {
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id = $(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsCheckPackage/delCheckPackage",
                        type: "post",
                        dataType: "json",
                        data: {"ids": arr.toString(), "token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                jBox.tip("删除成功", 'success');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("删除失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除检测包记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先选择检测包记录", 'info');
        }
    };

    //编辑页面模态框
    window.editCheckPackage = function (id) {
        $scope.isAdd = false;
        //显示编辑模态框
        $("#editModal").modal("show");
        $scope.checkItems = [];
        $http({
            url: "/sofn-ads-web/adsCheckPackage/selectByPrimaryKey",
            method: "post",
            dataType: "json",
            params: {"id": id, "token": token}
        }).success(function (data) {
            $scope.packageId = id;
            $scope.packageNameu = data.data.packageName;
            $scope.industryu = data.data.industry;
            $scope.checkItemsu = data.data.checkItems;
            var item = data.data.checkItems;
            var itemArray = item.split('、');
            var itemIdArray = data.data.checkIds.split(',');
            for (var i = 0; i < itemIdArray.length; i++) {
                var map = {
                    id: itemIdArray[i],
                    itemName: itemArray[i]
                };
                $scope.checkItems.push(map);
            }
            var countu = item.split('、').length;
            $scope.countu = countu;
        });

    };

    //显示页面模态框
    window.showCheckPackage = function (id) {
        //显示查看模态框
        $("#showModal").modal("show")
        $http({
            url: "/sofn-ads-web/adsCheckPackage/selectByPrimaryKey",
            method: "post",
            dataType: "json",
            params: {"id": id, "token": token}
        }).success(function (data) {
            $scope.packageName = data.data.packageName;
            $scope.industry = data.data.industry;
            $scope.checkItems = data.data.checkItems;
            var item = data.data.checkItems;
            var count = item.split('、').length;
            $scope.count = count;

        });

    };

    //增加检测项查询条件行业
    $scope.param_industry = "";
    $scope.isAdd = true;

    //显示新增模态框
    $scope.addCheckPackage = function (id) {
        $('#packageName2').val(null);
        $('#industry2').val(null);
        $('#checkItems2').val(null);
        $scope.checkItems = [];
        $scope.count1 = 0;

        $scope.isAdd = true;
        $scope.param_industry = "";

        $("#addModal").modal("show");

    };

    //添加检测项目--添加按钮事件
    var checkItemsArray = new Array();
    var idsArray = new Array();
    var nameArray = new Array();

    //批量添加检测项目
    $scope.addCheckItemsMapping = function () {
        for (var i = 0; i < $scope.checkItems.length; i++) {
            if ($scope.checkItems[i] != undefined) {
                nameArray.push($scope.checkItems[i].itemName);
                idsArray.push($scope.checkItems[i].id);
                checkItemsArray.push($scope.checkItems[i].itemName + "、" + $scope.checkItems[i].id);
            }
        }
        $scope.countu = nameArray.length;
        $scope.count1 = nameArray.length;
        if (checkItemsArray.length > 0 && $scope.checkItems.length > 0) {
            var arr = nameArray;
            var arr2 = idsArray;
            arr = arr.join("、");
            var submit = function (v, h, f) {
                if (v == true) {
                    $("#checkItems2").val(arr);
                    $("#checkItems").val(arr);
                    $("#selectModal").modal("hide");
                    $("#checkId").val(arr2);
                    $("#checkId2").val(arr2);

                } else {
                    jBox.tip("取消成功", 'success');
                }
                return true;
            }
            $.jBox.confirm("确认添加检测项目吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先添加再确定！", 'info');
        }
    }


    //延时加载数据
    $scope.stand_flag = 0;//加载标识
    //选择模态框
    $scope.selectCheckItems = function (id) {
        // $scope.checkItems =[];

        if ($scope.isAdd) {
            $scope.param_industry = $("#industry2").val();

            if($scope.param_industry == null ||$scope.param_industry == ""){
                jBox.tip("请先选择行业", 'info');
                return;
            }

        } else
            $scope.param_industry = $scope.industryu;

        if (idsArray.length > 0) {
            idsArray.splice(0, idsArray.length);
            nameArray.splice(0, nameArray.length);
        }
        $("#selectModal").modal("show");
        setTimeout(function () {
            //弹出未添加的检测项目列表
            if ($scope.stand_flag == 0) {
                $scope.loadCheckStandardData();
            } else {
                $("#itemGrid").dataTable().api().ajax.reload();
            }
        }, 300);
    };


    /**
     * 加载项目列表数据
     */
    $scope.loadCheckStandardData = function () {
        $(function () {
            //bootstrap表格
            $("#itemGrid").dataTable({

                fnDrawCallback: function () {
                    //判断是否全选
                    $("#checkAllItem").prop("checked", $("#itemGrid input[name='checkName']").length == $("#itemGrid input[name='checkName']:checked").length);
                    //点击行内任意内容即选择行
                    $("#itemGrid tbody tr td").each(function (i, o) {
                        $(o).click(function () {
                            var checked = $(this).parent().find("input[type='checkbox']")[0]
                            if (checked.checked) {
                                checked.checked = false;
                                $(this).parent().removeClass("selected");
                                //检测项移除
                                var u_d = $(this).parent().find("[name='checkName']").val().split("、")[1];
                                for (var i = 0; i < $scope.checkItems.length; i++) {
                                    if ($scope.checkItems[i] != undefined && u_d == $scope.checkItems[i].id) {
                                        $scope.checkItems.splice(i, 1);
                                        i--;
                                    }
                                }
                            } else {
                                checked.checked = true;
                                $(this).parent().addClass("selected");
                                //检测项添加
                                var map = {
                                    id: $(this).parent().find("[name='checkName']").val().split("、")[1],
                                    itemName: $(this).parent().find("[name='checkName']").val().split("、")[0]
                                };
                                $scope.checkItems.push(map);
                            }
                            //判断是否全选
                            $("#checkAllItem").prop("checked", $("#itemGrid input[name='checkName']").length == $("#itemGrid input[name='checkName']:checked").length);
                        });
                    })
                },
                ajax: {
                    url: "/sofn-ads-web/adsNewModel/getPageInfoAll",
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
                        //分页条件查询，不实现，自己写
                        params.itemName = $("#checkNames").val()
                        params.token = token;
                        params.idsArray = idsArray.toString();
                        params.industry = $scope.param_industry;
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "<input type='checkbox' id = 'checkAllItem' name='checkAll' onclick='checkAllItems(this.checked)'>",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var view1 = "<input type='hidden' id='checkId' name='checkId' value='" + row.id + "'>";
                            var view2 = "<input type='checkbox' onclick='checkSelectThisBox(this)' name='checkName' value='" + row.itemName + "、" + row.id + "'>";
                            for (var i = 0; i < $scope.checkItems.length; i++) {
                                if ($scope.checkItems[i] != undefined && row.id == $scope.checkItems[i].id) {
                                    view2 = "<input type='checkbox' checked='checked' onclick='checkSelectThisBox(this)' name='checkName' value='" + row.itemName + "、" + row.id + "'>";
                                }
                            }
                            return view1 + view2;
                        }
                    },
                    {
                        data: "rn",
                        title: "序号"
                    },
                    {
                        data: "industry",
                        title: "行业"
                    },
                    {
                        data: "itemName",
                        title: "检测项目"
                    },
                    {
                        data: "standardCode",
                        title: "检测标准"
                    }
                ],
            });
        });
        $scope.stand_flag = 1;
    };

    //针对复选框无法选中bug
    window.checkSelectThisBox = function (o) {
        if (o.checked) {
            o.checked = false;
            $(o).removeClass("selected");
        } else {
            o.checked = true;
            $(o).addClass("selected");
        }
    }
    //检测项全选,全不选
    window.checkAllItems = function (checked) {
        $("#itemGrid input[name='checkName']").each(function (i, o) {
            $(o).prop("checked", checked);
            var u_d = $(o).val().split("、")[1];
            for (var i = 0; i < $scope.checkItems.length; i++) {
                if ($scope.checkItems[i] != undefined && u_d == $scope.checkItems[i].id) {
                    $scope.checkItems.splice(i, 1);
                }
            }
        })
        if (checked) {
            $("#itemGrid input[name='checkName']:checked").each(function (i, o) {
                var map = {
                    id: $(o).val().split("、")[1],
                    itemName: $(o).val().split("、")[0]
                };
                $scope.checkItems.push(map);
            });
        }
    }

    $(document).ready(function () {
        $('#addCheckPackage').bootstrapValidator();
    });

    //增加行业改变，清空检测项
    window.changeIndustry = function() {
        $('#checkItems2').val("");
        $('#checkId2').val("");
        $scope.checkItems = [];

    }


});