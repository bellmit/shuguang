var myapp = angular.module("myapp", []);
myapp.controller("resourceMa", function ($scope, $http, $state) {
    /*加载外部的js*/
    var testScript = document.createElement('script');
    testScript.src = 'assets/js/dgap/common.js';
    testScript.type = 'text/javascript';
    var head = document.getElementsByTagName('head');
    head[0].appendChild(testScript);
    $scope.reourceURL="";
    $scope.reourceURLTwo="";
    //通知
    $scope.changeNotifier = function() {
        if( $scope.resourceTypes == "ds" ){
            $("#notifier").css("display","none");
        }else{
            $("#notifier").css("display","block");
        }
    };


    //清除查询输入框的内容
    $scope.cleanData=function(){
        $("input[name='resourceNameSe']").val("");
        $("select[name='resourceTypeSelct']").val("");
        $("#resource").dataTable().api().ajax.reload();
    }
    //所有资源类型
    $http({
        url: "/sofn-dgap-web/resourceApplication/getResourceType",
        method: 'POST',
    }).success(function (data) {
        $scope.resourceType = data.data;

        loadGrid();
        //全选/全不选
        $("#checkAll").click(function () {
            if ($(this).prop("checked") == true) {
                $("#resource input[type='checkbox']:not(:disabled)").prop("checked", true);
                $("#resource tbody tr").addClass("selected");
            } else {
                $("#resource input[type='checkbox']").prop("checked", false);
                $("#resource tbody tr").removeClass("selected");
            }
        });
    })
    //得到默认资源url
    $http({
        url: "/sofn-dgap-web/resource/getbaseUrls",
        method: 'POST',
    }).success(function (data) {
        $scope.reourceURL = data.data;
        $scope.reourceURLTwo= data.data;
    })

    //得到通知接口标识
    $http({
        url: "/sofn-dgap-web/resource/getDataNotifier",
        method: 'POST',
    }).success(function (data) {
        $scope.reourceNotifier = data.data;
    })

    /*   新增资源管理*/
    $('#addForm').bootstrapValidator(); //必须有
    $(".addResources").click(function () {

        if (!$("#addForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        add();

    });
    $(".addDismiss").click(function () {
        $scope.reourceURL =$scope.reourceURLTwo;
    });
    $('#reouseceModal').on('show.bs.modal', function () {
        $('#reourceUrl').val($scope.reourceURLTwo);
            $scope.reourceURL =$scope.reourceURLTwo;
    })

    /*执行添加之后清空上次添加的内容*/
    $('#reouseceModal').on('hide.bs.modal', function () {
        $('#addForm').data('bootstrapValidator').resetForm(true);
    })


    /* 修改资源管理*/
    $('#resourceUpdateF').bootstrapValidator(); //必须有
    $(".resourceUpdateDs").click(function () {
        if (!$("#resourceUpdateF").data('bootstrapValidator').validate().isValid()) {
            return false;
        } //必须有
        //           提交执行函数全写在这下面
        update();
    })
    /*执行修改之后清空上次添加的内容*/
    $('#reouseceUpdateModal').on('hide.bs.modal', function () {
        $("#resourceUpdateF").data('bootstrapValidator').destroy();//摧毁验证
        $('#resourceUpdateF').bootstrapValidator();//必须有
    })
    //bootstrap表格
    //资源目录主体信息
    function loadGrid() {
        $("#resource").dataTable({
            ajax: {
                url: "/sofn-dgap-web/resource/list",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].CREATE_TIME != null && json.data.list[i].CREATE_TIME != "" && json.data.list[i].CREATE_TIME != undefined) {
                            json.data.list[i].CREATE_TIME = new Date(parseInt(json.data.list[i].CREATE_TIME)).toISOString().slice(0, 10);
                        }
                        if (json.data.list[i].UPDATE_TIME != null && json.data.list[i].UPDATE_TIME != "" && json.data.list[i].UPDATE_TIME != undefined) {
                            json.data.list[i].UPDATE_TIME = new Date(parseInt(json.data.list[i].UPDATE_TIME)).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                data: function (params) {
                    params.token=window.localStorage.getItem("token");
                    params.directoryId = $scope.directoryIds;
                    params.resourcename = $("input[name='resourceNameSe']").val();
                    params.type = $("select[name='resourceTypeSelct']").val();
                    //params.svAreaId=$("input[name='dateBegin']").val();
                },
                type: "post"
            },
            columns: [{
                data: "ck",
                title: '<input id="checkAll"  type="checkbox"/>',
                render: function (data, type, row) {
                    var flagStatus = row.flagStatus;
                    return '<input name="ck" onclick="unCheckAll(\'' + row.ID + '\')" id="' + row.ID + '" type="checkbox" ' + (flagStatus == "disEnable" ? "disabled" : "name=\"ck\"") + '/>';
                },
                width: "3%",
            }, {
                data: "NAME",
                title: "资源名称",
                width: "8%",
            }, {
                data: "TYPE",
                render: function (data) { // 模板化列显示内容
                    $scope.resourceTypeShow = data;
                    return lookupName(data, $scope.resourceType); //转义传值
                },
                title: "资源类型",
                width: "10%",
            }, {
                data: "WSDL_URL",
                title: "资源URL",
                width: "10%",
            }, {
                data: "CREATE_TIME",
                title: "创建时间",
                width: "10%",
            }, {
                data: "TDNAME",
                title: "资源所属目录",
                width: "10%",
            }, {
                data: "FILE_PATH",
                title: "文件路径",
                render: function (data, type, row) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return  data;
                    }else{
                        return "无"
                    }
                },
                width: "10%",
            }, {
                data: "DESCRIPTION",
                title: "资源描述",
                width: "13%",
            }, {
                data: "UPDATE_TIME",
                title: "修改时间",
                width: "8%",
            }, {
                data: "FLAG",
                title: "资源状态",
                render: function (data) {
                    if(data=="N"){
                        return  "不可用";
                    }else{
                        return "可用"
                    }
                },
                width: "5%"
            },

                {
                    data: "ID",
                    title: "操作",
                    render: function (data, c, d) { // 模板化列显示内容
                        var flagStatus = d.flagStatus;
                        var tableStatus = d.TABLE_STATUS;
                        if ($scope.resourceTypeShow == "di") {
                            return '<input class="idValue" type="hidden" value="' + data + '">' +
                                '<a style="margin-right: 10px;cursor: pointer"' + (flagStatus == "disEnable" ? " class=\"color-gray\"" : "") + 'onClick="' + (flagStatus == "disEnable" ? "$.jBox.tip(\'不能修改！\')" : "resourceshow('" + data + "')" ) + '">修改</a>' +
                                '<a style="cursor: pointer;margin-right: 10px;"' + (flagStatus == "disEnable" ? " class=\"color-gray\"" : "") + ' onClick="' + (flagStatus == "disEnable" ? "$.jBox.tip(\'不能删除！\')" : "deleteResource('" + data + "')" ) + '">删除<br></a>' +
                                '<a style="cursor: pointer;margin-right: 10px;"  class="color-gray" onclick="designTable(\'' + data + '\')">表设计</a>' +
                                '<a style="cursor: pointer;margin-right: 10px;"  class="color-gray" onclick="' + ((tableStatus == "NE" || tableStatus == null) ? "$.jBox.tip(\'表未定义或者创建，不能审阅数据！\')" : "dataAudit('" + data + "')")+ '">数据审核<br></a> '+
                                '<a style="cursor: pointer;margin-right: 10px;"  class="color-gray" onclick="' + ((tableStatus == "NE" || tableStatus == null) ? "$.jBox.tip(\'表未定义或者创建，不能审阅数据！\')" : "dataImport('" + data + "')")+ '">数据导入</a> '+
                                '<a style="cursor: pointer;margin-right: 10px;"  class="color-gray" onclick="dataCheck(\'' + data + '\');return false" >查看详情</a> ';
                        } else {
                            return '<input class="idValue" type="hidden" value="' + data + '">' +
                                '<a style="margin-right: 10px;cursor: pointer"' + (flagStatus == "disEnable" ? " class=\"color-gray\"" : "") + 'onClick="' + (flagStatus == "disEnable" ? "$.jBox.tip(\'不能修改！\')" : "resourceshow('" + data + "')" ) + '">修改</a>' +
                                '<a style="cursor: pointer;margin-right: 10px;"' + (flagStatus == "disEnable" ? " class=\"color-gray\"" : "") + ' onClick="' + (flagStatus == "disEnable" ? "$.jBox.tip(\'不能删除！\')" : "deleteResource('" + data + "')" ) + '">删除</a><br>'+
                                '<a style="cursor: pointer;margin-right: 10px;"  onclick="viewDXFields(\'' + data + '\');return false" >查看字段</a> '+
                                '<a style="cursor: pointer;margin-right: 10px;"  onclick="dataApplication(\'' + data + '\');return false" >数据应用</a> '+
                                '<a style="cursor: pointer;margin-right: 10px;"  class="color-gray" onclick="dataCheck(\'' + data + '\');return false" >查看详情</a> ';
                        }
                    },
                    width: "25%",
                }
            ]
        }).on('draw.dt', function () {
            $("[name='my-checkbox']").bootstrapSwitch({
                size: 'mini',
                onText: '启用',
                offText: '禁用',
                onSwitchChange: function (evt, state) {
                    flagResourceUpdate(evt.currentTarget.getAttribute('data-id'));
                }
            });
        });
    }

   //导入modal
    window.dataImport = function (importResourceId) {
        $scope.importResourceId=importResourceId;
        $("#importModal").modal("show");
    };
    //数据导入
    $scope.importCsvData=function(){
        $("#save").attr("disabled",true);
        var fd = new FormData();
        var file = document.querySelector('input[type=file]').files[0];
        if(!file){
            jBox.tip("请先选择文件，再提交","info");
            $("#save").attr("disabled", false);
            return;
        }
        console.log("file.type"+file.type);
        if (!"application/vnd.ms-excel"==file.type) {
            swal('', '只能上传“csv” 类型文件', 'warning');
            return false;
        }
        console.log("file.size"+file.size);
        if (file.size > 20*1024 * 1024) {
            swal('', '文件大小不能超过20MB', 'warning');
            return false;
        }
        fd.append('file', file);
        fd.append('resourceId',$scope.importResourceId)
        $http({
            method:'POST',
            url:"/sofn-dgap-web/resource/importCsvData",
            data: fd,
            headers: {
                token:window.localStorage.getItem("token"),
                'Content-Type':undefined},
            transformRequest: angular.identity
        }).success( function ( data )
        {
            $("#save").attr("disabled", false);
            if(data.httpCode=="200") {
                //上传成功的操作
                jBox.tip("上传成功","info");
                $("#importModal").modal("hide");
                $("#elGrid").dataTable().api().ajax.reload();
            }else if(data.httpCode=="400"){
                alert(data.data,"info");
            }else{
                jBox.tip(data.data,"info");
            }
        });
    }




    //设计表
    window.designTable=function(designTableId){
        window.localStorage.setItem("designTableId",designTableId);
        $state.go("index.content.dgap/resource_operation/field/tableDesign");
    }
    window.del = function (resourceId) {
        $http({
            url: "/sofn-dgap-web/dataImport/checkOrNot",
            data: {resourceId: resourceId},
            method: "post"
        }).success(function (data) {
            if (data.data == "表不存在") {
                $.jBox.tip("资源表未创建，不能审核数据！");
            } else if (data.data == "表中没有数据") {
                $.jBox.tip("表中没数据，不能审核数据！");
            } else {
                var id = resourceId;
                window.location.href = '/webapp/dgap/resource_operation/review/?id=' + id;
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！")
        })
    }

    //获取后台数据库表是否创建成功
    window.dataAudit = function (resourceId) {
        $http({
            url: "/sofn-dgap-web/dataImport/checkOrNot",
            data: {resourceId: resourceId},
            method: "post"
        }).success(function (data) {
            if (data.data == "表不存在") {
                $.jBox.tip("资源表未创建，不能审核数据！");
            } else if (data.data == "表中没有数据") {
                $.jBox.tip("表中没数据，不能审核数据！");
            } else {
                window.localStorage.setItem("resourceId",resourceId);
                $state.go("index.content.dgap/resource_operation/review/dataAudit");
            }
        }).error(function (data) {
            $.jBox.tip("操作不成功！")
        })
    }

    //获取后台数据库表是否创建成功
    window.viewDXFields = function (resourceId) {
        window.localStorage.setItem("resourceId",resourceId);
        $state.go("index.content.dgap/resource_operation/resource_export_fields");
    }    

    //重新加载表格
    $scope.queryList = function () {
        $("#resource").dataTable().api().ajax.reload();
    }

    $scope.submit = function($event) {
        if ($event && $event.keyCode == 13) {
            $scope.queryList();
        }
    };
    $scope.add = function () {
        $("#resource").dataTable().api().ajax.reload();
    }
    //新增资源管理;
    function add() {
        $scope.addResource = function () {
            if (!$("#addForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resource/add",
                data: {
                    directoryId: $("select[name='resourceDirName']").val(),
                    type:"di",
                    name: $scope.resourceName,
                    wsdlUrl: $scope.reourceURL,
                    filePath: $scope.reourceFilePath,
                    description: $("textarea[name='description']").val(),
                    flag: $("select[name='resourceFlag']").val(),
                    delFlag: "N",
                    dataNotifier : $("select[name='DataNotifier']").val(),
                },
                method: 'post'
            }).success(function (data) {
                // location.reload(true);
                $("#reouseceModal").modal("hide");
                if(data.httpCode == 400){
                    $.jBox.tip("数据类型不存在","info");
                }else if(data.data == "不能新增资源类型为仓库资源的数据"){
                    $.jBox.tip("不能新增资源类型为仓库资源的数据","info");
                }else if(data.data=="文件路径重复"){
                    $.jBox.tip("新增失败！！！文件路径重复");
                }
                else {
                    if (data.httpCode == 200) {
                        $("#resource").dataTable().api().ajax.reload();
                        $.jBox.tip("新增成功！！！");
                    } else {
                        $.jBox.tip("操作不成功！");
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
            })
        }
    }

    //修改资源管理
    window.resourceshow = function (data) {
        $scope.applicationdata = data;
        /*  数据回显*/
        $http({
            url: "/sofn-dgap-web/resource/resourceData",
            data: {
                id: data
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {

            $scope.dataShow = data.data;
            $scope.resourceDirNameU = $scope.dataShow.TDNAME;
            $scope.resourceDirNameUId = $scope.dataShow.DIRECTORY_ID;
            $scope.resourceTypeU = $scope.dataShow.TYPE;
            $scope.resourceTypeUName = lookupNameSelect($scope.resourceType, $scope.resourceTypeU);
            $scope.resourceNameU = $scope.dataShow.NAME;
            $scope.reourceURLU = $scope.dataShow.WSDL_URL;
            $scope.reourceFilePathU = $scope.dataShow.FILE_PATH;
            $scope.descriptionU = $scope.dataShow.DESCRIPTION;
            $scope.notifierid = $scope.dataShow.DATA_NOTIFIER;
            $scope.notifierName = lookupNameSelect($scope.reourceNotifier, $scope.notifierid);
            $scope.DataNotifierUpdate= $scope.dataShow.DATA_NOTIFIER;

            if( $scope.resourceTypeU == "ds" ){
                $("#notifierUpdate").css("display","none");
            }else{
                $("#notifierUpdate").css("display","block");
            }
            $("#reouseceUpdateModal").modal("show");
        }).error(function (data) {
        });
    }
    //查看详情
    window.dataCheck = function (data) {
        $scope.applicationdata = data;
        /*  数据回显*/
        $http({
            url: "/sofn-dgap-web/resource/resourceData",
            data: {
                id: data
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {

            $scope.dataShow = data.data;
            $scope.resourceDirNameC = $scope.dataShow.TDNAME;
            $scope.resourceDirNameCId = $scope.dataShow.DIRECTORY_ID;
            $scope.resourceTypeU = $scope.dataShow.TYPE;
            $scope.resourceTypeUName = lookupNameSelect($scope.resourceType, $scope.resourceTypeU);
            $scope.resourceNameC= $scope.dataShow.NAME;
            $scope.reourceURLC = $scope.dataShow.WSDL_URL;
            $scope.reourceFilePathC = $scope.dataShow.FILE_PATH;
            $scope.descriptionC = $scope.dataShow.DESCRIPTION;
            $scope.DataNotifierC= $scope.dataShow.DATA_NOTIFIER;
            if( $scope.resourceTypeU == "ds" ){
                $("#notifierCheck").css("display","none");
            }else{
                $("#notifierCheck").css("display","block");
            }
            $("#reouseceCheckModal").modal("show");
        }).error(function (data) {
        });
    }
    function update() {
        $scope.resourceUpdata = function () {
            if (!$("#resourceUpdateF").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/resource/update",
                data: {
                    id: $scope.applicationdata,
                    directoryId: $("select[name='resourceDirNameU']").val(),
                    type: $("select[name='resourceTypeUsesS']").val(),
                    name: $scope.resourceNameU,
                    wsdlUrl: $scope.reourceURLU,
                    filePath: $scope.reourceFilePathU,
                    description: $("textarea[name='descriptionU']").val(),
                    dataNotifier : $scope.DataNotifierUpdate,
                },
                method: "post"

            }).success(function (data) {
                $("#reouseceUpdateModal").modal("hide");
                if(data.data=="文件路径重复"){
                    $.jBox.tip("修改失败！！！文件路径重复");
                }
                else {
                    if (data.httpCode == 200) {
                        $("#resource").dataTable().api().ajax.reload();
                        $.jBox.tip("修改成功！！！");
                    } else {
                        $.jBox.tip("操作不成功！");
                    }
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
                $("#updates").modal("hide");

            })
        }
    }

    /*  逐条删除资源权限设置列表*/
    //删除
    window.deleteResource = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/resource/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#resource").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！", 'info');
                        } else if (data.httpCode == 403) {
                            jBox.tip("该资源不能删除！", 'info');
                        } else {
                            jBox.tip("操作不成功！", 'info');
                        }
                    }
                });
            } else
                jBox.tip("取消删除", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
    };
    /*修改状态*/
    window.flagResourceUpdate = function (data) {
        $.ajax({
            url: "/sofn-dgap-web/resource/flagResourceUpdate",
            type: "post",
            dataType: "json",
            data: {id: data},
            success: function (data) {
                if (data.httpCode == 200) {
                    $("#resource").dataTable().api().ajax.reload();
                } else if (data.httpCode == 423) {
                    $.jBox.tip("该资源下有申请，不能禁用！");
                } else {
                    $.jBox.tip("操作不成功！");
                }
            }
        });
    }
    window.flags = function () {
    }
    //批量删除
    $scope.deleteResources = function () {
        var delIds = "";
        var obj = document.getElementsByName("ck");
        for(var k in obj){
            if(obj[k].checked)
                delIds+=obj[k].id+ "|";
        }
        // $("input[name='ck']:checkbox:checked ").each(function () {
        //     delIds = delIds + $(this).parent().parent().children().eq(0).children().eq(0).val() + "|";
        // })
        if (delIds != "") {
            var submit = function (v, h, f) {
                if (v == true) {

                    if (delIds != "") {
                        var data = {
                            dataInfo: delIds
                        };
                        $.ajax({
                            url: "/sofn-dgap-web/resource/deleteAll",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (data) {
                                if (data.httpCode == 200) {
                                    jBox.tip("成功删除数据！", 'info');
                                    $("#resource").dataTable().api().ajax.reload();
                                } else if (data.httpCode == 403) {
                                    jBox.tip("所选资源都不能删除！", 'info');
                                    $("#resource").dataTable().api().ajax.reload();
                                } else {
                                    $.jBox.tip("操作不成功！");
                                }
                            },
                            fail: function (data) {
                                jBox.tip("删除失败", 'info');
                            }
                        });
                    }
                } else
                    jBox.tip("取消成功", 'info');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认删除吗？", "删除提示", submit, {buttons: {'确认': true, '取消': false}});
        } else {
            $.jBox.tip("请至少选中一行记录", 'info');
        }
    };
    $scope.getSelectRowsIds = function () {
        var rows = $("#resource").DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            jBox.tip("请至少选中一行记录", 'info');
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].ID);
        }

        return ids;
    }

    //数据应用
    window.dataApplication = function (data) {
        // var submit = function (v, h, f) {
        //     if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/resource/fixAppliedData",
                    type: "post",
                    dataType: "json",
                    data: {id: data},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#resource").dataTable().api().ajax.reload();
                            jBox.tip(data.fixlog, 'info');
                        } else if (data.httpCode == 403) {
                            jBox.tip("该资源不能应用！", 'info');
                        } else {
                            jBox.tip("数据无需应用！", 'info');
                        }
                    }
                });
        jBox.tip("应用正在进行中，请稍等.....")
            // } else
            //     jBox.tip("取消应用", 'info');
            // return true;
        // };
        // // 自定义按钮
        // $.jBox.confirm("确认进行数据应用吗？", "应用正在进行中，请稍等", submit, {buttons: {'确认': true, '取消': false}});
    };
    /*显示资源状态的list集合*/
    $http({

        url: "/sofn-dgap-web/resource/resourceFlag",
        data: {},
        method: "post",
    }).success(function (data) {
        $scope.resourceFlags = data.data;
        /*alert($scope.resourceType[0].name);*/

    }).error(function () {
        $.jBox.tip("操作不成功！");
    })

    $http({
        url: "/sofn-dgap-web/resourceDirectory/tree",
        data: {},
        method: "post",
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function (data) {
        $scope.resourceDir = data.children;
    }).error(function () {
        $.jBox.tip("操作不成功！");
    })

    //    显示隐藏列表
    $scope.treeshow = function ($event) {
        $scope.directoryIds = null;
        $($event.currentTarget).addClass('active').siblings().removeClass('active');
        $("#resource").dataTable().api().ajax.reload();
    }
    //    点击资源目录
    $scope.treeMainLi = function (obj, $event) {
        $($event.currentTarget).addClass('active').siblings().removeClass('active');
        $scope.directoryIds = obj;
        $("#resource").dataTable().api().ajax.reload();
    }

    //取消#checkAll
    window.unCheckAll = function (data) {
        $("#checkAll").prop("checked", false);
    }
})
