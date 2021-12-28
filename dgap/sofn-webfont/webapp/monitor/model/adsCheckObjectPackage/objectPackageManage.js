var myapp = angular.module("myapp", []);
myapp.controller("objectPackageManage", function ($scope, $http, $state) {
    var token = window.localStorage.getItem("token");

    $scope.iswrite = "";
    var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
    for (var i = 0; i < a.length; i++) {
        if (a[i].menuName == "检测对象包配置") {
            $scope.iswrite = a[i].authority;
            break;
        }
    }
    $scope.disabled = $scope.iswrite != "2";

    //检测对象数组
    $scope.checkedObjects = [];

    $scope.clickEvent = function () {
        var str = $("#objectPackageName").val();
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


    //将农产品种类编码表存储到本地缓存,同时判断是否需要被刷新
    $scope.agriProductData = window.localStorage.getItem("agriProductData");
    $scope.isRefreshed = false;
    if ($scope.agriProductData == null || $scope.agriProductData == undefined || $scope.agriProductData == "") {
        $scope.agriProductData = [];
        $scope.isRefreshed = true;
    }


    //获取行业类型列表
    $scope.industries = new Array();
    var industryId = "";
    var isType = {};
    var dictValue = "";
    window.loadIndustries = function () {
        $http({
            url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
            method: "post",
            dataType: "json"
        }).success(function (json) {
            $scope.industries = json.data;
            $scope.hasSelectedIndustry = $scope.industries[0].dictName;
            industryId = $scope.industries[0].dictCode;
            for (var i in json.data) {
                isType[json.data[i].dictValue] = json.data[i].dictName;
            }

            //判断是否刷新
            if ($scope.isRefreshed) {
                for (var i in $scope.industries) {
                    $scope.getAgriProductForParentCode($scope.industries[i].dictValue, $scope.industries[i].dictName);
                }
            }

        });
    };

    loadIndustries();


    $scope.getAgriProductForParentCode = function (code, name) {
        $http({
            url: "/sofn-sys-web/agriProduct/getAgriProductForParentCode?parentCode=" + code,
            method: "get",
            dataType: "json"
        }).success(function (data) {
            var json = {"code": code, "name": name, "data": data};
            $scope.agriProductData[code] = json;
            window.localStorage.setItem("agriProductData", $scope.agriProductData);
        });
    }


    //二级联动改变值和获取值
    $(document).ready(function () {
        $('#industry2').change(function () {
            var s = $('#industry2 option:selected').val();
            for (var i in $scope.industries) {
                if ($scope.industries[i].dictName == s) {
                    $('#dictValue').val($scope.industries[i].dictValue);
                    dictValue = $('#dictValue').val();
                    break;
                }
            }

            //清空文本框的值和统计数量值
            $("#checkObject2").val("");
            $scope.count1 = 0;
        });
    });

    //导航对齐
    $("#collapseTwo").attr("class", "panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");

    //检测对象包查询
    $("#searchObjectPackage").click(function () {
        $scope.clickEvent();
    });

    //全选
    window.checkAll = function () {
        var val = $("input[name='checkAll']:checked");
        if (val.length > 0) {
            $("input[name='task']").attr("checked", true);
        } else {
            $("input[name='task']").attr("checked", false);
        }
    };

    //检测对象包分页查询显示
    $(function () {
        $("#grid").dataTable({
            ajax: {
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-ads-web/adsCheckPackage/getObjectPackagePageInfoAll",
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
                    params.objectPackageName = $("#objectPackageName").val();
                    params.industry = $("#industry").val();
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
                    data: "objectPackageName",
                    title: "检测对象包名称"
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
                        var look_a = '<a class="pointer mr15" onclick="modifyObjectPackage(\'' + row.id + '\') ">修改</a>';
                        var look_b = '<a class="pointer mr15" onclick="showObjectPackage(\'' + row.id + '\') ">查看</a>';
                        if ($scope.disabled) {
                            look_a = '<a class="pointer mr15" style="color: grey">修改</a>'
                        }
                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    //绑定回车事件
    $scope.enterEvent = function (e) {
        var keycode = window.event ? e.keyCode : e.which;
        if (keycode == 13) {
            // $scope.saveObjectPackage();
        }
    };
    //新增检测对象包对话框
    $scope.addObjectPackage = function () {
        $('#objectPackageName2').val(null);
        $('#industry2').val(null);
        $('#checkObject2').val(null);
        $('#productId2').val(null);
        $('#checkObjectName').val(null);
        $scope.checkedObjects = [];
        $scope.count1 = 0;

        $('#industry2 input[type=checkbox]').prop("checked", false);

        $('#addObjectPackageModal').modal("show");

        currentIndustryId = "industry2";
    };

    //检测对象选择列表
    var checkObjectsArray = new Array();
    var idsArray = new Array();
    var nameArray = new Array();

    var currentIndustryId = "";//当前新增或修改的使用行业选择框id,用于获取该值
    var selectCheckObjectIds = "";//选中的检测包对象

    // $scope.add = function () {//单个添加检测对象
    //     var select = $("input[name='selectName']:checked");
    //     if(select.length>0){
    //         $(select).each(function (i,o) {
    //
    //             var productName = $(o).val().split(",")[0];
    //             //存productId，为ADS_CHECK_OBJECT_PACKAGE表中的PRODUCT_IDS
    //             var productId = $(o).val().split(",")[1];
    //             checkObjectsArray.push(productId);
    //             nameArray.push(productName); //获得选中的id对应的名字
    //             idsArray.push(productId); //获得选中的id
    //         });
    //         jBox.tip("添加成功！", 'info');
    //         $("#objectGrid").dataTable().api().ajax.reload();
    //     }else{
    //         jBox.tip("请先选择检测对象！", 'info');
    //     }
    // };

    $scope.addCheckObjectsMapping = function () {//批量添加检测对象

        //获取选中的节点
        var nodes = $('#easyUi').tree('getChecked');

        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].children.length > 0)
                continue;
            nameArray.push(nodes[i].text);
            idsArray.push(nodes[i].code);
            checkObjectsArray.push(nodes[i].text + "," + nodes[i].code);

        }
        $scope.countu = nameArray.length;
        $scope.count1 = nameArray.length;
        if (checkObjectsArray.length > 0) {
            var arr = nameArray;
            var arr2 = idsArray;
            var submit = function (v, h, f) {
                if (v == true) {
                    $("#selectCheckObjectModal").modal("hide");
                    $("#checkObject2").val(arr);
                    $("#checkObjects").val(arr);
                    $("#productId2").val(arr2);
                    $("#productId3").val(arr2);
                } else {
                    jBox.tip("取消成功", 'success');
                }
                return true;
            }
            $.jBox.confirm("确认添加检测对象吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        }
        else {
            jBox.tip("请先添加再确定！", 'info');
        }
    };

    $scope.load_flag = 0; //加载标志
    $scope.selectCheckObjects = function () {
        // $scope.checkedObjects =[];
        if (idsArray.length > 0) {
            idsArray.splice(0, idsArray.length);
            nameArray.splice(0, nameArray.length);
        }

        var selectedIndustry = $scope.getSelectedIndustry();
        var name = selectedIndustry.toString();//$("#" + currentIndustryId).val();
        if (name == null || name == "") {
            jBox.tip("请选择适用行业", 'info');
            return false;
        }

        var code = "";
        var temp = [];
        if (selectedIndustry.length > 0) {
            for (var i in $scope.industries) {
                if ($.inArray($scope.industries[i].dictName, selectedIndustry) > -1) {
                    temp.push($scope.industries[i].dictValue);
                }
            }
        }
        code = temp.toString();

        if (currentIndustryId == "industry2")
            selectCheckObjectIds = $("#productId2").val();
        else
            selectCheckObjectIds = $("#productId3").val();

        showTree(code, selectCheckObjectIds, $scope.agriProductData);

        $("#selectCheckObjectModal").modal("show");
        setTimeout(function () {
            if ($scope.load_flag == 0) {
                $scope.loadCheckObjectListData();//加载检测对象列表数据
            } else {
                $("#objectGrid").dataTable().api().ajax.reload();
            }
        }, 300);


    };

    //检测对象查询
    $("#searchCheckObject").click(function () {
        var str = $("#checkObjectName").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("长度过长,请更改输入内容", 'info');
        } else {
            var node = $('#easyUi').tree('find', str);
            console.log(node);
            $('#easyUi').tree('expandTo', node.target);
            $('#easyUi').tree('select', node.target);
        }
    });


    $scope.loadCheckObjectListData = function () {
        $(function () {
            $("#objectGrid").dataTable({
                fnDrawCallback: function () {
                    //判断是否全选
                    $("#checkAllObject").prop("checked", $("#objectGrid input[name='selectName']").length == $("#objectGrid input[name='selectName']:checked").length);
                    //点击行内任意内容即选择行
                    $("#objectGrid tbody tr td").each(function (i, o) {
                        $(o).click(function () {
                            var checked = $(this).parent().find("input[type='checkbox']")[0]
                            if (checked.checked) {
                                checked.checked = false;
                                $(this).parent().removeClass("selected");
                                //检测项移除
                                var u_d = $(this).parent().find("[name='selectName']").val().split(",")[1];
                                for (var i = 0; i < $scope.checkedObjects.length; i++) {
                                    if ($scope.checkedObjects[i] != undefined && u_d == $scope.checkedObjects[i].productCode) {
                                        $scope.checkedObjects.splice(i, 1);
                                        i--;
                                    }
                                }
                            } else {
                                checked.checked = true;
                                $(this).parent().addClass("selected");
                                //检测项添加
                                var map = {
                                    productCode: $(this).parent().find("[name='selectName']").val().split(",")[1],
                                    name: $(this).parent().find("[name='selectName']").val().split(",")[0]
                                };
                                $scope.checkedObjects.push(map);
                            }
                            //判断是否全选
                            $("#checkAllObject").prop("checked", $("#objectGrid input[name='selectName']").length == $("#objectGrid input[name='selectName']:checked").length);
                        });
                    })
                },
                ajax: {
                    url: "/sofn-ads-web/adsDictData/queryProduct",
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
                        params.typeId = $("#dictValue").val();
                        params.keyWord = $("#checkObjectName").val();
                        params.token = token;
                        params.idsArray = idsArray.toString();
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "<input type='checkbox' id='checkAllObject' name='checkAll' onclick='checkAllObjects(this.checked)'>",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var view1 = "<input type='hidden' id='checkId' name='checkId' value='" + row.id + "'>";
                            var view2 = "<input type='checkbox' onclick='checkSelectThisBox(this)' name='selectName' value='" + row.name + "," + row.productCode + "'>";
                            for (var i = 0; i < $scope.checkedObjects.length; i++) {
                                if ($scope.checkedObjects[i] != undefined && row.productCode == $scope.checkedObjects[i].productCode) {
                                    view2 = "<input type='checkbox' checked='checked' onclick='checkSelectThisBox(this)' name='selectName' value='" + row.name + "," + row.productCode + "'>";
                                }
                            }
                            return view1 + view2;
                        }
                    },
                    {
                        title: "序号",
                        data: function (data, type, row, meta) {
                            return meta.row + 1
                        }

                    },
                    {
                        data: "typeId",
                        title: "所属行业",
                        render: function (data, type, row) {
                            return isType[row.typeId];
                        }
                    },

                    {
                        data: "name",
                        title: "检测对象"
                    }
                ],
            })
        });
        $scope.load_flag = 1;
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
    window.checkAllObjects = function (checked) {
        $("#objectGrid input[name='selectName']").each(function (i, o) {
            $(o).prop("checked", checked);
            var u_d = $(o).val().split(",")[1];
            for (var i = 0; i < $scope.checkedObjects.length; i++) {
                if ($scope.checkedObjects[i] != undefined && u_d == $scope.checkedObjects[i].productCode) {
                    $scope.checkedObjects.splice(i, 1);
                }
            }
        })
        if (checked) {
            $("#objectGrid input[name='selectName']:checked").each(function (i, o) {
                var map = {
                    productCode: $(o).val().split(",")[1],
                    name: $(o).val().split(",")[0]
                };
                $scope.checkedObjects.push(map);
            });
        }
    }

    //获取指定模态框中的被选中的行业
    $scope.getSelectedIndustry = function () {
        var id = "#" + currentIndustryId + " input:checkbox:checked";
        var arr = [];
        $.each($(id), function () {
            arr.push($(this).val())
        });

        return arr;
    }

    $scope.saveObjectPackage = function () {//保存检测对象包
        if (!$("#addCheckObjectPackage").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var objectPackageName = $('#objectPackageName2').val();
        var industry = $scope.getSelectedIndustry().toString();//$('#industry2 option:selected').val();


        var checkObjects = $('#checkObject2').val();
        var arr = $('#productId2').val();
        var length = objectPackageName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("检测对象包名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
            return false;
        }
        if (objectPackageName == null || objectPackageName == "" || objectPackageName == "undefined") {
            jBox.tip("检测对象包名称不能为空", 'info');
            return false;
        }
        if (industry == null || industry == "" || industry == "undefined") {
            jBox.tip("行业不能为空", 'info');
            return false;
        }
        if (checkObjects == null || checkObjects == "" || checkObjects == "undefined") {
            jBox.tip("检测对象不能为空", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsCheckPackage/addCheckObjectPackage",
            type: "post",
            dataType: "json",
            data: {
                "objectPackageName": objectPackageName,
                "industry": industry,
                "industryId": industryId,
                "token": token,
                "checkObjects": checkObjects,
                "productIds": arr.toString()
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#addObjectPackageModal").modal("hide");
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

    //修改检测对象包对话框
    window.modifyObjectPackage = function (id) {
        $("#modifyModal").modal("show");
        currentIndustryId = "industry3";
        $scope.checkedObjects = [];
        $http({
            url: "/sofn-ads-web/adsCheckPackage/selectObjectPackageById",
            method: "post",
            dataType: "json",
            params: {"id": id, "token": token}
        }).success(function (data) {
            $scope.packageId = data.data.id;
            $scope.objectPackageNameu = data.data.objectPackageName;
            $scope.industryu = data.data.industry;
            for (var i in $scope.industries) {
                if ($scope.industries[i].dictName == $scope.industryu) {
                    $('#dictValue').val($scope.industries[i].dictValue);
                    dictValue = $('#dictValue').val();
                    break;
                }
            }
            $("#dictValue").val(dictValue);
            $scope.checkObjectsu = data.data.checkObjects;
            $("#checkObjects").val($scope.checkObjectsu);
            $('#productId3').val(data.data.productIds);
            var item = data.data.checkObjects;
            var itemArray = item.split(',');
            var itemIdArray = data.data.productIds.split(',');
            for (var i = 0; i < itemIdArray.length; i++) {
                var map = {
                    productCode: itemIdArray[i],
                    name: itemArray[i]
                };
                $scope.checkedObjects.push(map);
            }
            var countu = item.split(',').length;
            $scope.countu = countu;

            //当前选中的检测包Ids，用于判断tree中对象是否被选中
            selectCheckObjectIds = data.data.productIds;

        });

    };

    $scope.updateObjectPackage = function () {
        var objectPackageName = $("#objectPackageName3").val();
        var industry = $scope.getSelectedIndustry().toString();
        ;//$('#industry3 option:selected').val();
        var checkObjects = $("#checkObjects").val();
        var packageId = $('#packageId').val();
        var arr = $('#productId3').val();
        var length = objectPackageName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if (length > 60) {
            jBox.tip("检测对象包名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
            return false;
        }
        if (objectPackageName == null || objectPackageName == "" || objectPackageName == "undefined") {
            jBox.tip("检测对象包名称不能为空", 'info');
            return false;
        }
        if (checkObjects == null || checkObjects == "" || checkObjects == "undefined") {
            jBox.tip("检测对象不能为空", 'info');
            return false;
        }
        $.ajax({
            url: "/sofn-ads-web/adsCheckPackage/updateObjectPackage",
            type: "post",
            dataType: "json",
            data: {
                "objectPackageName": objectPackageName,
                "industry": industry,
                "industryId": industryId,
                "token": token,
                "checkObjects": checkObjects,
                "productIds": arr.toString(),
                "id": $scope.packageId
            },
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#modifyModal").modal("hide");
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

    //批量删除检测对象包
    window.delObjectPackage = function () {
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
                        url: "/sofn-ads-web/adsCheckPackage/delCheckObjectPackage",
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
            $.jBox.confirm("确认删除检测对象包记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

        } else {
            jBox.tip("请先选择检测对象包记录", 'info');
        }
    };


    //查看检测对象包对话框
    window.showObjectPackage = function (id) {
        $("#showModal").modal("show");
        $http({
            url: "/sofn-ads-web/adsCheckPackage/selectObjectPackageById",
            method: "post",
            dataType: "json",
            params: {"id": id, "token": token}
        }).success(function (data) {
            $scope.objectPackageName = data.data.objectPackageName;
            $scope.industry = data.data.industry;
            $scope.checkObjects = data.data.checkObjects;
            var item = data.data.checkObjects;
            var count = item.split(',').length;
            $scope.count = count;

        });
    };

    $(document).ready(function () {
        $('#addCheckObjectPackage').bootstrapValidator();
    });


});

var showTree = function (code, selectCheckObjectIds, agriProductData) {
    $("#easyUi").tree({
        lines: true,
        checkbox: true,
        animate: true,
        url: "sofn-sys-web/agriProduct/getAgriProductForParentCode?parentCode=-1",
        onBeforeLoad: function () {
            $("#process").show();
            $("#tree").hide();
        },
        loadFilter: function (data) {
            //增加多行业展示
            var data = [];
            var codeArr = code.split(",");
            for (var i in codeArr) {
                var json = {
                    "parent": "-1",
                    "code": codeArr[i],
                    "text": agriProductData[codeArr[i]]['name'],
                    "state": "open",
                    "children": agriProductData[codeArr[i]]['data']
                };
                data.push(json);
            }

            return initTree(data, selectCheckObjectIds.split(','));
        },
        onLoadSuccess: function () {
            $("#process").hide();
            $("#tree").show();
        }
    })
}

var initTree = function (data, idsArr) {

    //清空tree
    clearTree();

    if (idsArr != undefined && idsArr != null && idsArr != "")
        for (var i = 0; i < idsArr.length; i++) {
            for (var k = 0; k < data.length; k++) {
                if (data[k].code != idsArr[i].substring(0, data[k].code.length))
                    continue;

                if (idsArr[i] == data[k].code) {
                    data[k].checked = true;
                    break;
                }

                if (data[k].children.length > 0)
                    data[k].children = initTree(data[k].children, new Array(idsArr[i]));
            }
        }
    return data;
}

var clearTree = function () {

    var nodes = $('#easyUi').tree('getChecked');
    for (var i = 0; i < nodes.length; i++) {
        $('#easyUi').tree('uncheck', nodes[i].target);
    }
}