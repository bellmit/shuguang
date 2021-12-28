var myapp = angular.module("myapp", []);
myapp.controller("dgapResourceApplication", function ($scope, $http) {
    ///*修改和添加的表单验证*/
    $('#updateAttributeForm').bootstrapValidator();//必须有
    $('#attributeForm').bootstrapValidator();//必须有

    /*执行添加之后清空上次添加的内容*/
    $('#resourceApplyAdd').on('hide.bs.modal', function () {
        document.getElementById("lessDate").value = "";
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    })
    /*执行修改之后清空上次修改的内容*/
    $('#resourceApplyUpdate').on('hide.bs.modal', function () {
        $("#updateAttributeForm").data('bootstrapValidator').destroy();//摧毁验证
        $('#updateAttributeForm').bootstrapValidator();//必须有
    })
    /*点击保存按钮之后通过验证在执行保存（修改）方法，否则不集训执行方法*/
    $(".updateRes").click(function () {
        //if (!$("#updateAttributeForm").data('bootstrapValidator').validate().isValid()) {
        //    return false;
        //}
    })

    //清除查询条件
    $scope.cleanData = function () {
        $("select[name='resourceType']").val("");
        $("input[name='resourceName']").val("");
        $("#resourceApply").dataTable().api().ajax.reload();
    }
    $scope.search = function () {
        $("#resourceApply").dataTable().api().ajax.reload();
    }
    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.search();
        }
    };


    //bootstrap表格
    function loadGrid() {
        $("#resourceApply").dataTable({
            ajax: {
                headers: {
                    "token": window.localStorage.getItem("token")
                },
                url: "/sofn-dgap-web/resourceApplication/list",//id
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].EFFECTIVE_DATE != null && json.data.list[i].EFFECTIVE_DATE != "" && json.data.list[i].EFFECTIVE_DATE != undefined) {
                            json.data.list[i].EFFECTIVE_DATE = new Date(parseInt(json.data.list[i].EFFECTIVE_DATE)+8*3600*1000).toISOString().slice(0, 10);
                        }else{
                            json.data.list[i].EFFECTIVE_DATE ="无";
                        }
                    }
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].EXPIRE_DATE != null && json.data.list[i].EXPIRE_DATE != "" && json.data.list[i].EXPIRE_DATE != undefined) {
                            json.data.list[i].EXPIRE_DATE = new Date(parseInt(json.data.list[i].EXPIRE_DATE)+8*3600*1000).toISOString().slice(0, 10);
                        }
                        else{
                            json.data.list[i].EXPIRE_DATE ="无";
                        }
                    }
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].CREATE_TIME != null && json.data.list[i].CREATE_TIME != "" && json.data.list[i].CREATE_TIME != undefined) {
                            json.data.list[i].CREATE_TIME = new Date(parseInt(json.data.list[i].CREATE_TIME)).toISOString().slice(0, 10);
                        }
                        else{
                            json.data.list[i].CREATE_TIME ="无";
                        }
                    }
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].AUDIT_TIME != null && json.data.list[i].AUDIT_TIME != "" && json.data.list[i].AUDIT_TIME != undefined) {
                            json.data.list[i].AUDIT_TIME = new Date(parseInt(json.data.list[i].AUDIT_TIME)).toISOString().slice(0, 10);
                        }
                        else{
                            json.data.list[i].AUDIT_TIME ="无";
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.token=window.localStorage.getItem("token");
                    params.name = $("input[name='resourceName']").val();
                    params.type = $("select[name='resourceType']").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "PASS_OR_NOT",
                    title: '<input id="checkAll"  type="checkbox"/>',
                    render: function (data, type, row) {
                        $scope.PassOrNot = data;
                        return '<input onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '"   type="checkbox" ' + (data == "Y" ? "disabled" : "name=\"ck\"") + ' />'
                    },
                    width: "5%"
                },
                {
                    data: "RESOURCENAME",
                    title: "资源名称",
                    width: "10%",
                },
                {
                    data: "RESOURCETYPE",
                    render: function (data) { // 模板化列显示内容
                        return lookupName(data, $scope.ResourceType);//转义传值
                    },
                    title: "资源类型",
                    width: "5%"
                },
                {
                    data: "WSDL_URL",
                    title: "资源url",
                    render: function(data) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' + data + '"><a href="' + data + '">' + showdata + '</a>  </div>';
                            }
                            return '<div class=""  data-toggle="tooltip"  title="' + data + '"><a href="' + data + '">' + showdata + '</a>  </div>';
                        } else {
                            return '--';
                        }
                    } ,
                    width: "5%"
                },
                {
                    data: "CALLER_TOKEN",
                    title: "授权码",
                    render: function (data, type, row) {
                         if(data!=null&&data!=""&&data!= undefined){
                             return  data;
                         }else{
                             return "无"
                         }
                    },
                    width: "10%"
                },
                {
                    data: "EFFECTIVE_DATE",
                    title: "生效时间",
                    width: "8%"
                },
                {
                    data: "EXPIRE_DATE",
                    title: "失效时间",
                    width: "8%"
                },
                {
                    data: "AUDIT_TIME",
                    title: "审批时间",
                    width: "8%"
                },
                {
                    data: "PASS_OR_NOT",
                    title: "审批状态",
                    render: function (data) {
                        if(data!=null&&data!=""&&data!= undefined){
                            return lookupName(data, $scope.ApplicationPassOrNot);//转义传值
                        }else{
                            return "待审批"
                        }

                    },
                    width: "5%"

                },
                {
                    data: "APPROVAL",
                    title: "审批意见",
                    render: function (data, type, row) {
                        if(data!=null&&data!=""&&data!= undefined){
                            return  data;
                        }else{
                            return "无"
                        }
                    },
                    width: "15%"
                }, {
                    data: "CREATE_TIME",
                    title: "申请时间",
                    width: "8%"
                },
                {
                    data: "PLATFORM_NAME",
                    title: "平台名称",
                    width: "8%"
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data) { // 模板化列显示内容
                        if ($scope.PassOrNot == "Y" || $scope.PassOrNot == "N") {
                            return '<input class="idValue" type="hidden" value="' + data + '"><a style="margin-right: 10px;text-decoration: none;" class="colorGrey" onclick="updateApply(\'' + data + '\')">修改</a><span class="color" >已审批</span>';
                        } else {
                            return '<input class="idValue" type="hidden" value="' + data + '"><a style="margin-right: 10px;cursor: pointer"  class="color" onclick="updateApply(\'' + data + '\')">修改</a><a style="cursor: pointer" class="color" onclick="deletes(\'' + data + '\')">删除</a>';//转义传值
                        }
                    },
                    width: "10%",
                },

            ]
        })
    };
    //新增按钮跳转新增摸态框
    $scope.addApply = function () {
        $("#resourceApplyAdd").modal("show");
        $("#beginDate").val(new Date().toISOString().slice(0, 10));
    }


    //所有的启动资源名称()
    $http({
        url: "/sofn-dgap-web/resourceRole/relist",
        method: 'POST',
    }).success(function (data) {
        $scope.tdgpResourceApplication = data.data;
    })

    //新增申请
    $scope.addResourceApply = function () {
        if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        if ($("#beginDate").val() == "") {
            jBox.tip("开始时间不能为空", 'info');
            return false;
        }
        var date = $("input[name='EFFECTIVE_DATE']").val();
        date = date.substring(0,10);
        date = date.replace(/-/g,'/');
        var effectiveDate = new Date(date).getTime();
        var date2 =  $("input[name='EXPIRE_DATE']").val();
        if(date2!=null){
            date2 = date2.substring(0,10);
            date2 = date2.replace(/-/g,'/');
        }
        var expireDate = new Date(date2).getTime();

        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-dgap-web/resourceApplication/add",
            data: {
                resourceId: $scope.resName,
                expireDate:expireDate,
                effectiveDate: effectiveDate,
                reason: $("textarea[name='reason']").val()
            },
            //post传输方式   头信息
            method: 'POST',
        }).success(function (data) {
            jBox.tip(data.data, 'info');
            $("#resourceApplyAdd").modal("hide");
            $("#resourceApply").dataTable().api().draw();
        }).error(function (data) {

        });
    }


    //修改资源是否有权限
    $scope.resourceId="";
    window.updateApply = function (data) {
        $scope.dataId = data;
        $scope.updateResItem = {};
        $scope.update_REASON = null;
        var tdgpResourceApplications = $.extend([], $scope.tdgpResourceApplication);
        $http({
            url: "/sofn-dgap-web/resourceApplication/resourceById",
            data: {
                id: data,
            },
            method: 'POST',
        }).success(function (data) {
            if (data.httpCode == 200) {
                $("#resourceApplyUpdate").modal("show");
                $scope.resourceId= data.data[1].RESOURCE_ID;
                var currentItem = {
                    id: data.data[1].RESOURCE_ID,
                    name: data.data[1].RESOURCENAME,
                    disabled: !!data.data[0].error.length,
                    selected: true
                };
                $scope.updateResItem = currentItem;
                $scope.tdgpResourceApplicationById = data.data;
                tdgpResourceApplications = tdgpResourceApplications.filter(function (ele) {
                    return ele.id != currentItem.id;
                });
                tdgpResourceApplications.unshift(currentItem);
                $scope.tdgpResourceApplications = tdgpResourceApplications;
                //$scope.updateResName = $scope.tdgpResourceApplicationById[1].RESOURCE_ID;
                $scope.update_REASON = $scope.tdgpResourceApplicationById[1].REASON;
                if (data.data[1].EXPIRE_DATE != null && data.data[1].EXPIRE_DATE != "" && data.data[1].EXPIRE_DATE != undefined) {
                    $scope.update_EXPIRE_DATE = new Date(parseInt(data.data[1].EXPIRE_DATE)+8*3600*1000).toISOString().slice(0, 10);
                }
                $scope.update_EFFECTIVE_DATE = new Date(parseInt(data.data[1].EFFECTIVE_DATE+8*3600*1000)).toISOString().slice(0, 10);
            } else {
                jBox.tip("已锁定", 'info');
            }

        })
    }
    /*修改资源申请*/
    $scope.updateResourceApply = function () {
        if (!$("#updateAttributeForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        if ($("#u_beginDate").val() == "") {
            jBox.tip("开始时间不能为空", 'info');
            return false;
        }
        var dateUp = $("input[name='update_EFFECTIVE_DATE']").val();
        dateUp = dateUp.substring(0,10);
        dateUp = dateUp.replace(/-/g,'/');
        var effectiveDateUp = new Date(dateUp).getTime();
        var dateUp2 =  $("input[name='update_EXPIRE_DATE']").val();
        if(dateUp2!=null){
            dateUp2 = dateUp2.substring(0,10);
            dateUp2 = dateUp2.replace(/-/g,'/');
        }
        var expireDateUp = new Date(dateUp2).getTime();
        if(effectiveDateUp>expireDateUp){
            jBox.tip("开始时间不能大于失效时间", 'info');
            return false;
        }
        if($scope.updateResId==null){
            $scope.updateResId=$scope.resourceId;
        }
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    headers: {
                        token: window.localStorage.getItem("token")
                    },
                    url: "/sofn-dgap-web/resourceApplication/update",
                    data: {
                        id: $scope.dataId,
                        resourceId: $scope.updateResId,
                        expireDate: expireDateUp,
                        effectiveDate: effectiveDateUp,
                        reason: $("textarea[name='update_REASON']").val()
                    },
                    //post传输方式   头信息
                    method: 'POST',
                }).success(function (data) {
                    jBox.tip(data.data, 'info');
                    $("#resourceApplyUpdate").modal("hide");
                    $("#resourceApply").dataTable().api().ajax.reload();
                }).error(function (data) {

                });
            else
                jBox.tip("取消成功", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认修改吗？", "修改提示", submit, {buttons: {'确认': true, '取消': false}});
    }


    /*  逐条删除资源目录列表*/
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url: "/sofn-dgap-web/resourceApplication/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#resourceApply").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！", 'info');
                        } else {
                            jBox.tip("操作不成功！", 'info');
                        }
                    }
                });
            else
                jBox.tip("取消成功", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    }



     //批量下载
    $scope.downSurface = function () {
        var submit = function (v, h, f) {
            if (v == true) {
                $http({
                    url: "/sofn-dgap-web/resourceApplication/downSurface",
                    method:"post",
                    dataType: "json",
                    responseType: 'arraybuffer',
                    headers: {
                        "token":window.localStorage.getItem("token"),
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                }).success(function (data) {
                    var blob = new Blob([data], {type: "application/zip"});
                    var objectUrl = URL.createObjectURL(blob);
                    var a = document.createElement('a');
                    document.body.appendChild(a);
                    a.setAttribute('style', 'display:none');
                    a.setAttribute('href', objectUrl);
                    a.setAttribute('download', '开发包.zip');
                    a.click();
                });
            }
            else
                jBox.tip("取消成功", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认下载吗？", "下载提示", submit, {buttons: {'确认': true, '取消': false}})
    };
    //批量删除
    $scope.deleteResourceApply = function () {
        var delIds = "";
        $("input[name='ck']:checkbox:checked ").each(function () {
            delIds = delIds + $(this).parent().parent().children().eq(12).children().eq(0).val() + "|";
        })
        if (delIds != "") {
            var submit = function (v, h, f) {
                if (v == true) {
                    var data = {
                        dataInfo: delIds
                    };
                    $.ajax({
                        url: "/sofn-dgap-web/resourceApplication/deleteAll",
                        type: "post",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            jBox.tip("删除成功", 'info');
                            $("#resourceApply").dataTable().api().ajax.reload();
                        },
                        fail: function (data) {
                            jBox.tip("删除失败", 'info');
                        }
                    });
                }
                else
                    jBox.tip("取消成功", 'info');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}})
        } else
            $.jBox.tip("请至少选择一行记录！");
    };
    var getResourceTypePromise = $.Deferred(),
        getResourceApplicationPassOrNotPromise = $.Deferred();
    //所有资源类型
    var getResourceTypeAjaxer = $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceType",
        method: 'POST',
    }).success(function (data) {
        $scope.ResourceType = data.data;
        getResourceTypePromise.resolve();
    });

    var getResourceApplicationPassOrNotAjaxer = $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceApplicationPassOrNot",
        data: {},
        mathod: "post"
    }).success(function (data) {
        $scope.ApplicationPassOrNot = data.data;
        getResourceApplicationPassOrNotPromise.resolve();
        //$("#resourceApply").dataTable().api().draw(false);
    }).error(function () {
        $.jBox.tip("操作不成功！！！");
    });

    $.when(getResourceTypePromise, getResourceApplicationPassOrNotPromise).done(function () {
        loadGrid();
        //全选/全不选
        $("#checkAll").click(function () {
            if ($(this).prop("checked") == true) {
                $("#resourceApply input[type='checkbox']:not(:disabled)").prop("checked", true);
                $("#resourceApply tbody tr").addClass("selected");
            } else {
                $("#resourceApply input[type='checkbox']").prop("checked", false);
                $("#resourceApply tbody tr").removeClass("selected");
            }
        });
    });

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }
});

