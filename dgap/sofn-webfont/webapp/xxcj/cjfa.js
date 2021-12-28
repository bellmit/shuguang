var myapp = angular
	.module("myapp", [])
	.controller("cjfa", function(
		$scope,
		$http,
		$state,
		$timeout
	) {
        var token = window.localStorage.getItem("token");
        var industryChoseId = [];//获取行业
        //检测项数组
        $scope.checkItems = [];
        //检测项包数组
        $scope.checkPackages = [];

        $scope.iswrite = "";
        var a = JSON.parse(window.localStorage.getItem("AdsMenuObj"));
        /*for (var i = 0; i < a.length; i++) {
            if (a[i].menuName == "模型配置") {
                $scope.iswrite = a[i].authority;
                break;
            }
        }*/
        $scope.disabled = $scope.iswrite != "2";

        var isType = {};    //行业类型
        $http({
            method: 'POST',
            url: '/sofn-ads-web/adsDictData/getIndustryTypeData'
        }).success(function (data) {
            $scope.activities = data.data;
            if (data.data != null) {
                for (var i in data.data) {
                    isType[data.data[i].dictValue] = data.data[i].dictName;
                }
            }
        });

        $scope.clickEvent = function () {
            var str = $("#modelNameS").val();
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
                    url: "/sofn-ads-web/adsNewModel/getModelPageInfoAll",
                    dataSrc: function (json) {
                        json.recordsTotal = json.data.total;//总个数
                        json.recordsFiltered = json.data.total;
                        json.pageNumber = json.data.pageNum;//页码
                        json.pageSize = json.data.pageSize;//每页个数
                        json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                        json.length = json.data.pageSize;
                        return json.data.list;
                    },
                    error: function (msg) {
                        
                    },
                    data: function (params) {
                        //分页条件查询，不实现，自己写
                        params.modelName = $("#modelNameS").val();
                        params.industry = $("#industry_name").val();
                        params.token = token;
                        /*params.monitorType = $('#monitorTypeS option:selected').val();*/
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var view = "<input type='checkbox' name='task' value='" + row.modelId + "'>";
                            return view;
                        }
                    },
                    {
                        data: "rn",
                        title: "序号"
                    },
                    {
                        data: "modelName",
                        title: "模型名称"
                    },
                    /*{
                     data: "monitorType",
                     title: "监测类型"
                     },*/
                    {
                        data: "industry",
                        title: "适用行业"
                    },
                    {
                        data: "isEnable",
                        title: "是否启用",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var info = "";
                            switch (data) {
                                case "0":
                                    info = "<span style='line-height: 2;' class='label label-warning'>未启用</span>";
                                    break;
                                case "1":
                                    info = "<span style='line-height: 2;' class='label label-success'>已启用</span>";
                                    break;
                            }
                            return info;
                        }
                    },
                    {
                        data: "ID",
                        title: "数据操作",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var look_a = '<a class="pointer mr15" onclick="modelConfig(\'' + row.modelId + "\',\'" + row.isEnable + "\',\'" + row.industry + '\') ">配置</a>';
                            if ($scope.disabled) {
                                look_a = '<a class="pointer mr15" style="color: grey">配置</a>';
                            }
                            var look_b = '<a class="pointer mr15" onclick="editAdsModel(\'' + row.modelId + "\',\'" + row.isEnable + '\') ">修改</a>';
                            if ($scope.disabled) {
                                look_b = '<a class="pointer mr15" style="color: grey">修改</a>';
                            }
                            var look_c = '<a class="pointer mr15" onclick="showConfigDetail(\'' + row.modelId + '\') ">查看</a>';
                            if (row.isEnable == 0 && !$scope.disabled) {
                                look_d = '<a class="pointer" onclick="enableAdsCheckModel(\'' + row.modelId + "\',\'" + 1 + '\') ">启用</a>';
                            } else if (row.isEnable == 0 && $scope.disabled) {
                                look_d = '<a class="pointer" style="color: grey">启用</a>';
                            } else if (row.isEnable == 1 && !$scope.disabled) {
                                look_d = '<a class="pointer" onclick="enableAdsCheckModel(\'' + row.modelId + "\',\'" + 0 + '\') ">禁用</a>';
                            } else if (row.isEnable == 1 && $scope.disabled) {
                                look_d = '<a class="pointer" style="color: grey">禁用</a>';
                            }
                            return look_a + look_b + look_c + look_d;
                        }
                    },
                ],
            });
        });

        //启用检测模型
        window.enableAdsCheckModel = function (modelId, isEnable) {
            var submit = function (v, h, f) {
                if (v == true) {
                    $.ajax({
                        url: "/sofn-ads-web/adsNewModel/enableAdsCheckModel",
                        type: "post",
                        dataType: "json",
                        data: {"modelId": modelId, "isEnable": isEnable, "token": token},
                        success: function (data) {
                            if (data.httpCode == 500) {
                                jBox.tip("启用失败", 'info');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else if (data.httpCode == 406) {
                                jBox.tip("请将模型信息编辑完全后再启用", 'info');
                            } else {
                                jBox.tip("操作成功", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                }
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            if (isEnable == 1) {
                $.jBox.confirm("确认启用该条检测模型吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
            } else if (isEnable == 0) {
                $.jBox.confirm("确认取消启用该条检测模型吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
            }

        };

        //查询更新
        $("#seachTask").click(function () {
            $scope.clickEvent();
        });

        $scope.addAdsModel = function () {
            $('#addCheckModel').data('bootstrapValidator').resetForm(true);
            $("#addModal").modal("show");
        };

        //编辑模态框
        window.editAdsModel = function (modelId, isEnable) {
            if (isEnable == 1) {
                jBox.tip("改模型已启用，请先取消启用后再修改", 'info');
                return;
            }
            $("#editModal").modal("show");
            $http({
                url: "/sofn-ads-web/adsNewModel/selectByPrimaryKey",
                method: "post",
                dataType: "json",
                params: {"modelId": modelId, "token": token}
            }).success(function (data) {
                $scope.modelNameu = data.data.modelName;
                $scope.industryu = data.data.industry;
                $scope.modelId = data.data.modelId;
            });
        };


        //全选监测模型
        window.checkAll = function () {
            var val = $("input[name='checkAll']:checked");
            if (val.length > 0) {
                //全选
                $("input[name='task']").attr("checked", true);
            } else {
                $("input[name='task']").attr("checked", false);
            }
        };

        //全选监测模型配置
        window.checkAllConfig = function () {
            var val = $("input[name='checkAllConfig']:checked");
            if (val.length > 0) {
                //全选
                $("input[name='taskConfig']").attr("checked", true);
            } else {
                $("input[name='taskConfig']").attr("checked", false);
            }
        };

        //全选监测包
        window.checkAllPackage = function () {
            var val = $("input[name='checkAllPackage']:checked");
            if (val.length > 0) {
                //全选
                $("input[name='checkAllPackage']").attr("checked", true);
            } else {
                $("input[name='checkAllPackage']").attr("checked", false);
            }
        };

        //新增绑定保存回车事件
        $scope.enterAddEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.addAdsCheckModel();
            }
        };
        //修改绑定保存回车事件
        $scope.enterEditEvent = function (e) {
            var keycode = window.event ? e.keyCode : e.which;
            if (keycode == 13) {
                $scope.editAdsCheckModel();
            }
        };
        //新增检测模型按钮事件
        $scope.addAdsCheckModel = function () {
            if (!$("#addCheckModel").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            var modelName = $("#modelName").val();
            var arr = [];
            var checkbox1 = document.getElementById('c');
            var checkbox2 = document.getElementById('c1');
            var checkbox3 = document.getElementById('c2');
            if (checkbox1.checked) {
                arr.push($("#c").val());
            }
            if (checkbox2.checked) {
                arr.push($("#c1").val());
            }
            if (checkbox3.checked) {
                arr.push($("#c2").val());
            }
            var industry = arr.join(",");
            var length = modelName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
            if (length > 60) {
                jBox.tip("模型名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
                return false;
            }
            if (arr.length === 0) {
                jBox.tip("行业不能为空", 'info');
                return false;
            }
            $.ajax({
                url: "/sofn-ads-web/adsNewModel/addMonitorModel",
                type: "post",
                dataType: "json",
                data: {"modelName": modelName, "industry": industry, "industryId": industryId, "token": token},
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

        //编辑检测模型按钮事件
        $scope.editAdsCheckModel = function () {
            if (!$("#editCheckModel").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            var modelName = $("#modelNameu").val();
            var modelId = $("#modelId").val();
            var industry = $('#industryu option:selected').val();
            var length = modelName.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
            if (length > 60) {
                jBox.tip("模型名称汉字不得超过30或者字母数字不得超过60,请更改输入内容", 'info');
                return false;
            }
            $.ajax({
                url: "/sofn-ads-web/adsNewModel/editMonitorModel",
                type: "post",
                dataType: "json",
                data: {
                    "modelName": modelName,
                    "modelId": modelId,
                    "industry": industry,
                    "industryId": industryId,
                    "token": token
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

        //批量删除模型配置选项
        window.delAdsCheckModelCongif = function () {
            var modelId = $("#modelId1").val();
            var check = $("input[name='taskConfig']:checked");
            if (check.length > 0) {
                var arr = new Array();
                $(check).each(function () {
                    var task = $(this)[0];
                    var id = $(task).val();
                    arr.push(id);
                });
                var submit = function (v, h, f) {
                    if (v == true) {
                        $.ajax({
                            url: "/sofn-ads-web/adsNewModel/delAdsCheckModelCongif",
                            type: "post",
                            dataType: "json",
                            data: {"ids": arr.toString(), "modelId": modelId, "token": token},
                            success: function (data) {
                                if (data.httpCode == 200) {
                                    jBox.tip("删除成功", 'success');
                                } else if (data.httpCode == 400) {
                                    jBox.tip("没有登录", 'info');
                                } else if (data.httpCode == 409) {
                                    jBox.tip("改模型已被启用，请取消启用后再删除", 'info');
                                } else {
                                    jBox.tip("删除失败", 'info');
                                }
                                $("#configGrid").dataTable().api().ajax.reload();
                            }
                        });
                    } else {
                        jBox.tip("取消成功", 'success');
                    }
                    return true;
                };
                // 自定义按钮
                $.jBox.confirm("确认删除模型配置记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

            } else {
                jBox.tip("请先选择模型配置记录", 'info');
            }
        };

        //批量删除模型
        window.delAdsCheckModel = function () {
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
                            url: "/sofn-ads-web/adsNewModel/delAdsCheckModel",
                            type: "post",
                            dataType: "json",
                            data: {"ids": arr.toString(), "token": token},
                            success: function (data) {
                                if (data.httpCode == 200) {
                                    jBox.tip("删除成功", 'success');
                                } else if (data.httpCode == 400) {
                                    jBox.tip("没有登录", 'info');
                                } else if (data.httpCode == 409) {
                                    jBox.tip("改模型已被启用，请取消启用后再删除", 'info');
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
                $.jBox.confirm("确认删除模型记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});

            } else {
                jBox.tip("请先选择模型记录", 'info');
            }
        };

        //模型配置模态框
        $scope.stand_flag = 0;//加载标识
        window.modelConfig = function (modelId, isEnable, industry) {

            if (isEnable == 1) {
                jBox.tip("该模型已启用，请先取消启用后再进行配置", 'info');
                return;
            }
            $("#isEnable").val(isEnable);
            $("#modelId1").val(modelId);
            $http({
                method: 'POST',
                url: '/sofn-ads-web/adsDictData/getIndustryTypeData'
            }).success(function (data) {
                industryChoseId = [];
                if (data.data != null) {
                    for (var i in data.data) {
                        if (industry.indexOf(data.data[i].dictName) !== -1) {
                            industryChoseId.push(data.data[i].dictValue);
                        }
                    }
                }
            });
            $("#modelConfig").modal("show");
            setTimeout(function () {
                //弹出未添加的检测项目列表
                if ($scope.stand_flag == 0) {
                    $scope.loadCheckStandardData();
                } else {
                    $("#configGrid").dataTable().api().ajax.reload();
                }
            }, 300);
        };
        /**
         * 模型配置分页展示数据
         */
        $scope.loadCheckStandardData = function () {
            $(function () {
                //bootstrap表格
                $("#configGrid").dataTable({
                    ajax: {
                        url: "/sofn-ads-web/adsNewModel/getModelConfigPageInfoAll",
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
                            params.modelId = $("#modelId1").val();
                            params.token = token;

                            var arr = new Array();
                            var unitMeasureArr = new Array();
                            $("input[name='maxValue']").each(function (i, o) {
                                arr.push($(o).val());
                                unitMeasureArr.push($(o).parent().parent().parent().next().children().val());
                            });

                            if (arr.length > 0) {
                                $.ajax({
                                    url: "/sofn-ads-web/adsNewModel/addModelConfigValue",
                                    type: "post",
                                    dataType: "json",
                                    data: {"ids": arr.toString(), "unitMeasure": unitMeasureArr.toString(), "token": token},
                                    success: function (data) {
                                        console.log(data);
                                    }
                                })
                            }
                            /*params.monitorType = $('#monitorTypeS option:selected').val();*/
                        },
                        type: "post"
                    },
                    columns: [
                        {
                            title: "<input type='checkbox' name='checkAllConfig' onclick='checkAllConfig()'>",
                            render: function (data, type, row) {
                                // 数据操作选择项
                                var view = "<input type='checkbox' name='taskConfig' value='" + row.id + "'>";
                                return view;
                            }
                        },
                        {
                            data: "rn",
                            title: "序号"
                        },
                        {
                            data: "productName",
                            title: "检测对象"
                        },
                        {
                            data: "checkName",
                            title: "检测项目"
                        },
                        {
                            title: "限量值",
                            render: function (data, type, row) {
                                // 数据操作选择项
                                var maxValueIsND = "";
                                if (row.maxValue == 0) {
                                    maxValueIsND = "ND";
                                    var view = "<div class='col-sm-3 col-md-offset-4' >" +
                                        "<div class='input-group'>" +
                                        "<input type='text' id='maxValue' name='maxValue' value='" + maxValueIsND + "' class='form-control gui-input text-height-28 text-center' onblur='check(this)'/>" + "</div>" + "</div>"
                                } else {
                                    var view = "<div class='col-sm-3 col-md-offset-4' >" +
                                        "<div class='input-group'>" +
                                        "<input type='text' id='maxValue' name='maxValue' value='" + row.maxValue + "' class='form-control gui-input text-height-28 text-center' onblur='check(this)' />" + "</div>" + "</div>"
                                }

                                var view2 = "<input  type='hidden' name='maxValue' value='" + row.id + "'>";
                                return view + view2;
                            }
                        },
                        {
                            data: "unitMeasure",
                            title: "单位",
                            render: function (data,type,row) {
                                /* alert(data);
                                 if (!data) {
                                     data = "mg/kg";
                                 }*/
                                var url = "/sofn-ads-web/adsDictData/getDictDataByCode";
                                var optionStr = "";
                                $.ajax({
                                    url: url,
                                    type: "post",
                                    data: {"code": "LIMITUNIT"},
                                    async:false,
                                    success: function (data) {
                                        var list = data.data;
                                        var s = "";
                                        for (var i=0;i<data.data.length;i++){
                                            if (row.unitMeasure==list[i].dictName){
                                                s = "<option selected>"+list[i].dictName+"</option>";
                                            }else {
                                                optionStr += "<option>"+list[i].dictName+"</option>";
                                            }
                                        }
                                        optionStr = optionStr + s;
                                    }
                                });
                                var str = "<select width='50px;'>" + optionStr + "</select>";
                                return str;
                                /* return "<input class='form-control gui-input text-height-28 text-center' name='unitMeasure' value='" + data + "'>";*/
                            }
                        },

                    ],
                });
            });
            $scope.stand_flag = 1;
        };


        //保存模型值的信息
        $scope.addModelConfigValue = function () {
            var arr = new Array();
            var unitMeasureArr = new Array();
            $("input[name='maxValue']").each(function (i, o) {
                arr.push($(o).val());
                unitMeasureArr.push($(o).parent().parent().parent().next().children().val());
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url: "/sofn-ads-web/adsNewModel/addModelConfigValue",
                        type: "post",
                        dataType: "json",
                        data: {"ids": arr.toString(), "unitMeasure": unitMeasureArr.toString(), "token": token},
                        success: function (data) {
                            if (data.httpCode == 200) {
                                $("#modelConfig").modal("hide");
                                jBox.tip("保存成功", 'success');
                            } else if (data.httpCode == 400) {
                                jBox.tip("没有登录", 'info');
                            } else {
                                jBox.tip("保存失败", 'info');
                            }
                            $("#grid").dataTable().api().ajax.reload();
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认保存模型配置记录吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        };

        //选择检测对象点击事件
        $scope.chooseCheckObject = function ($event) {
            $event.preventDefault();
            $("#objectGrid").dataTable().api().ajax.reload();
        };

        //选择检测对象包点击事件
        $scope.checkObjectPackage = function ($event) {
            $event.preventDefault();
            $("#objectPackageGrid").dataTable().api().ajax.reload();
        };
        //选择检测产品模态框
        $scope.addProduct = function () {

            var isEnable = $('#isEnable').val();
            if (isEnable == 1) {
                jBox.tip("改模型已被启用，请取消启用后再新增", 'info');
                return;
            }
            $("#selectCheckObject").modal("show");
            $scope.checkItems = [];
            $scope.checkPackages = [];
            //清理数据
            var tableName = $('#objectGrid');
            if ($.fn.dataTable.isDataTable(tableName)) {
                tableName.DataTable().clear();
                tableName.DataTable().destroy();
            }

            $("#searchCheckObjects").click(function () {
                var str = $("#checkObjectName").val();
                var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
                if (length > 60) {
                    jBox.tip("长度过长,请更改输入内容", 'info');
                } else $("#objectGrid").dataTable().api().ajax.reload();
            });

            //加载检测对象数据
            $("#objectGrid").dataTable({
                ajax: {
                    url: "/sofn-ads-web/adsDictData/getLeafNodeOfProduct",
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
                        params.keyWord = $("#checkObjectName").val();
                        params.typeId = industryChoseId.toString();

                    },
                    type: "post"
                },
                columns: [
                    {
                        data: "typeId",
                        title: "所属行业",
                        render: function (data, type, row) {
                            return isType[row.typeId];
                        }
                    },
                    {
                        data: "name",
                        title: "名称"
                    },
                    {
                        data: "id",
                        title: "操作",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var name = row.name;
                            var gbCode = row.gbCode;
                            var productCode = row.productCode;
                            var productName = row.parentName;
                            var pro = {name: name, gbCode: gbCode, productCode: productCode, productName: productName};
                            return "<button class='gui-btn' onclick='findProduct(" + JSON.stringify(pro) + ")'>选定产品</button>"

                        }
                    },
                ]
            }).on('shown.bs.tab', function (e) {
                $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
            });
        };

        $("#searchObjectPackage").click(function () {
            var str = $("#objectPackageName").val();
            var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
            if (length > 60) {
                jBox.tip("长度过长,请更改输入内容", 'info');
            } else $("#objectPackageGrid").dataTable().api().ajax.reload();
        });

        //加载检测对象包数据
        $("#objectPackageGrid").dataTable({
            ajax: {
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
                    params.industry = $("#industry_name2").val();
                    params.token = token;
                },
                type: "post"
            },
            columns: [
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
                    title: "所属行业"
                },
                {
                    data: "tempTime",
                    title: "创建时间"
                },
                {
                    data: "id",
                    title: "操作",
                    render: function (data, type, row) {
                        return "<button class='gui-btn' onclick='findProductPackage(" + JSON.stringify(data) + ")'>检测对象包</button>"
                    }
                },
            ]
        }).on('shown.bs.tab', function (e) {
            $.fn.dataTable.tables({visible: true, api: true}).columns.adjust();
        });
        /**
         * 选定产品，保存必要数据
         */
        window.findProduct = function (product) {
            $("#itemGrid,#packageGrid").find("input:checked").removeProp("checked");
            $scope.checkItems = [];
            $scope.checkPackages = [];
            $("input[name='name']").val(product.name);  //产品名称
            $("input[name='productCode']").val(product.productCode);    //产品编号
            $("#product_info").modal('hide');
            jBox.tip("选定检测对象成功，请继续选择检测项目", 'success');
            setTimeout(function () {
                $("#selectPackage").modal("show");
            }, 1300);

        };


        $("#seachTask1").click(function () {
            var str = $("#checkNames").val();
            var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
            if (length > 60) {
                jBox.tip("长度过长,请更改输入内容", 'info');
            } else $("#itemGrid").dataTable().api().ajax.reload();
        });

        //选择检测项点击事件
        $scope.chooseCheckItem = function ($event) {
            $event.preventDefault();
            $scope.checkPackages = [];
            $("#itemGrid").dataTable().api().ajax.reload();
        };
        //选择检测包点击事件
        $scope.choosePackage = function ($event) {
            $event.preventDefault();
            $scope.checkItems = [];
            $("#packageGrid").dataTable().api().ajax.reload();
        };

        //检测项目的分页查询
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
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "<input type='checkbox' id='checkAllItem' name='checkAll' onclick='checkAllItems(this.checked)'>",
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


        var t;
        var checkPackages = false;
        var tempModelId;
        //批量添加检测项目或者检测包之后 进行批量保存
        $scope.addModelConfig = function () {
            if ($scope.checkItems.length > 0 && $scope.checkPackages.length > 0) {
                jBox.tip("不能同时选择检测包与检测项", 'info');
            }
            var productId = $("#productCode").val();
            var productName = $("#productName").val();
            var modelId1 = $("#modelId1").val();
            tempModelId = modelId1;
            if ($scope.checkItems.length > 0 && $scope.checkPackages.length < 1) {//为选择的是检测项
                checkPackages = false;
                var arr = new Array();
                var arr2 = new Array();
                var arr3 = new Array();
                for (var i = 0; i < $scope.checkItems.length; i++) {
                    if ($scope.checkItems[i] != undefined) {
                        arr.push($scope.checkItems[i].itemName);
                        arr2.push($scope.checkItems[i].id);
                        arr3.push($scope.checkItems[i].itemName + "、" + $scope.checkItems[i].id);
                    }
                }
                var submit = function (v, h, f) {
                    if (v == true) {
                        $("#selectPackage").modal("hide");
                        $("#selectCheckObject").modal('hide');
                        $.ajax({
                            url: "/sofn-ads-web/adsNewModel/saveModelConfig",
                            type: "post",
                            dataType: "json",
                            data: {
                                "modelId": modelId1,
                                "productId": productId,
                                "productName": productName,
                                "ids": arr3.join("、"),
                                "token": token
                            },
                            success: function (data) {
                                checkPackages = false;
                                $("#process").modal("show");
                                t = setInterval(getSaveModelConfigResult, 2000);

                            },
                            error: function (data) {
                                if (data.responseText)
                                    jBox.tip(jQuery.parseJSON(data.responseText).msg, 'success');
                            }
                        });

                    } else {
                        jBox.tip("取消成功", 'success');
                    }
                    return true;
                };
                $.jBox.confirm("确认添加检测项目吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
            } else if ($scope.checkItems.length < 1 && $scope.checkPackages.length > 0) {//选择的为检测包
                var arr = new Array();
                for (var j = 0; j < $scope.checkPackages.length; j++) {
                    if ($scope.checkPackages[j] != undefined) {
                        arr.push($scope.checkPackages[j]);
                    }
                }
                var submit = function (v, h, f) {
                    if (v == true) {
                        $("#selectPackage").modal("hide");
                        $.ajax({
                            url: "/sofn-ads-web/adsNewModel/saveCheckPackageItems",
                            type: "post",
                            dataType: "json",
                            data: {
                                "modelId": modelId1,
                                "productId": productId,
                                "productName": productName,
                                "ids": arr.join("、"),
                                "token": token
                            },
                            success: function (data) {
                                checkPackages = true;
                                $("#process").modal("show");
                                t = setInterval(getSaveModelConfigResult, 2000);
                            },
                            error: function (data) {
                                if (data.responseText)
                                    jBox.tip(jQuery.parseJSON(data.responseText).msg, 'success');
                            }
                        });

                    } else {
                        jBox.tip("取消成功", 'success');
                    }
                    return true;
                };
                $.jBox.confirm("确认添加检测包吗？", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
            } else if ($scope.checkItems.length > 0 && $scope.checkPackages.length > 0) {
                jBox.tip("不能同时选择检测项目和检测包", 'info');
            } else {
                jBox.tip("请先选择检测项目或者检测包", 'info');
            }
        }


        //获取批量保存模型结果
        window.getSaveModelConfigResult = function () {
            $.ajax({
                url: "/sofn-ads-web/adsNewModel/getSaveModelConfigResult",
                type: "post",
                dataType: "json",
                data: {
                    "modelId": tempModelId
                },
                success: function (data) {
                    if (data.finished == 1) {
                        $("#process").modal("hide");

                        clearInterval(t);

                        if (data.httpCode == 200) {
                            if (checkPackages) {
                                $("#selectPackage").modal("hide");
                                $("#selectCheckObject").modal('hide');
                            } else {
                                $("#itemGrid").find("input[name='checkName']:checked").removeProp("checked");
                                $("#packageGrid").find("input[name='package']:checked").removeProp("checked");
                                $("#itemGrid").find("input[name='checkName']:checked").removeProp("checked");
                                $("#packageGrid").find("input[name='package']:checked").removeProp("checked");
                            }

                            $("#configGrid").dataTable().api().ajax.reload();
                            if (data.msgInfo) {
                                jBox.tip("保存成功,已忽略" + data.msgInfo + "条重复项", 'info');
                            } else {
                                jBox.tip("保存成功", 'info');
                            }
                        } else if (data.httpCode == 400) {
                            $("#configGrid").dataTable().api().ajax.reload();
                            jBox.tip("没有登录", 'info');
                        } else if (data.httpCode == 409) {
                            $("#configGrid").dataTable().api().ajax.reload();
                            jBox.tip("保存失败,存在检测项冲突", 'info');
                        } else {
                            $("#configGrid").dataTable().api().ajax.reload();
                            jBox.tip("保存失败", 'info');
                        }
                    }
                }
            });
        }


        //检测包的查询更新
        $("#seachTask2").click(function () {
            var str = $("#packageNames").val();
            var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
            if (length > 60) {
                jBox.tip("长度过长,请更改输入内容", 'info');
            } else $("#packageGrid").dataTable().api().ajax.reload();
        });


        /**
         * 检测包的分页展示数据
         */
        $(function () {
            //bootstrap表格
            $("#packageGrid").dataTable({
                fnDrawCallback: function () {
                    //判断是否全选
                    $("#checkAllPackage").prop("checked", $("#packageGrid input[name='package']").length == $("#packageGrid input[name='package']:checked").length);
                    //点击行内任意内容即选择行
                    $("#packageGrid tbody tr td").each(function (i, o) {
                        $(o).click(function () {
                            var checked = $(this).parent().find("input[type='checkbox']")[0]
                            if (checked.checked) {
                                checked.checked = false;
                                $(this).parent().removeClass("selected");
                                //检测项包移除
                                var u_d = $(this).parent().find("[name='package']").val();
                                for (var i = 0; i < $scope.checkPackages.length; i++) {
                                    if ($scope.checkPackages[i] != undefined && u_d == $scope.checkPackages[i]) {
                                        $scope.checkPackages.splice(i, 1);
                                        i--;
                                    }
                                }
                            } else {
                                checked.checked = true;
                                $(this).parent().addClass("selected");
                                //检测项包选择
                                $scope.checkPackages.push($(this).parent().find("[name='package']").val());
                            }
                            //判断是否全选
                            $("#checkAllPackage").prop("checked", $("#packageGrid input[name='package']").length == $("#packageGrid input[name='package']:checked").length);
                        });
                    })
                },
                ajax: {
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
                        params.industry = $("#industry_name1").val();
                        params.token = token;
                    },
                    type: "post"
                },
                columns: [
                    {
                        title: "<input type='checkbox' id='checkAllPackage' name='checkAllPackage' onclick='checkAllPackages(this.checked)'>",
                        render: function (data, type, row) {
                            // 数据操作选择项
                            var view = "<input type='checkbox' onclick='checkSelectThisBox(this)' name='package' value='" + row.id + "'>";
                            for (var i = 0; i < $scope.checkPackages.length; i++) {
                                if ($scope.checkPackages[i] != undefined && data == $scope.checkPackages[i].id) {
                                    return '<input type="checkbox" checked = "true" name="ck" onclick="checkSelectThisBox(this)" value="' + data + '"/>';
                                }
                            }
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

                ],
            });
        });
        //检测项包全选,全不选
        window.checkAllPackages = function (checked) {
            $("#packageGrid input[name='package']").each(function (i, o) {
                $(o).prop("checked", checked);
                var u_d = $(o).val();
                for (var i = 0; i < $scope.checkPackages.length; i++) {
                    if ($scope.checkPackages[i] != undefined && u_d == $scope.checkPackages[i]) {
                        $scope.checkPackages.splice(i, 1);
                        i--;
                    }
                }
            })
            if (checked) {
                $("#packageGrid input[name='package']:checked").each(function (i, o) {
                    $scope.checkPackages.push($(o).val());
                });
            }
        }

        //监测模型配置详情
        $scope.stand_flag1 = 0;//加载标识
        window.showConfigDetail = function (modelId) {
            $("#modelConfigDetail").modal("show");
            $("#modelId1").val(modelId);
            setTimeout(function () {
                //弹出未添加的检测项目列表
                if ($scope.stand_flag1 == 0) {
                    $scope.loadCheckStandardDataDetail();
                } else {
                    $("#detailGrid").dataTable().api().ajax.reload();
                }
            }, 300);
        };

        $scope.loadCheckStandardDataDetail = function () {
            $(function () {
                //bootstrap表格
                $("#detailGrid").dataTable({
                    ajax: {
                        url: "/sofn-ads-web/adsNewModel/getModelConfigDetail",
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
                            params.modelId = $("#modelId1").val();
                            params.token = token;
                        },
                        type: "post"
                    },
                    columns: [
                        {
                            data: "rn",
                            title: "序号",
                            width: '70'
                        },
                        {
                            data: "productName",
                            title: "检测对象",
                            width: '180'
                        },
                        {
                            data: "checkName",
                            title: "检测项目"
                        },
                        {
                            data: "maxValue",
                            title: "限量值",
                            width: '70',
                            render: function (data) {
                                if (data == 0) {
                                    data = "ND";
                                }
                                return data;
                            }

                        },
                        {
                            data: "unitMeasure",
                            title: "单位",
                            width: '70',
                            render: function (data) {
                                if (!data) {
                                    data = "mg/kg";
                                }
                                return data;
                            }
                        },

                    ],
                });
                $scope.stand_flag1 = 1;
            });
        }

        window.check = function (e) {
            var re = /^\d+(?=\.{0,1}\d+$|$)/;
            if (e.value == "ND") {
            } else if (e.value != "") {
                if (!re.test(e.value)) {
                    alert("请输入正确的数字");
                    e.value = "";
                    e.focus();
                }
            }
        }


        $(document).ready(function () {
            $('#editCheckModel').bootstrapValidator();
            $('#addCheckModel').bootstrapValidator();

        });
        /**
         * 选定检测对象包，保存必要数据
         */
        window.findProductPackage = function (id) {
            $("#itemGrid,#packageGrid").find("input:checked").removeProp("checked");
            var token = window.localStorage.getItem("token");
            $http({
                method: 'POST',
                url: '/sofn-ads-web/adsCheckPackage/selectObjectPackageById',
                params: {'id': id, 'token': token}
            }).success(function (data) {
                var productNames = data.data.checkObjects.split("，");
                var productIds = data.data.productIds.split(",");
                $("input[name='productCode']").val(productIds);//产品码
                $("input[name='name']").val(productNames);  //产品名称
                $("#product_info").modal('hide');
                jBox.tip("选定检测对象包成功，请继续选择检测项目", 'success');
                setTimeout(function () {
                    $("#selectPackage").modal("show");
                }, 1300);

            });


        };

	});
