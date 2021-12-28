var myapp = angular.module("myapp", []);
myapp.controller("tableDesign", function ($scope, $http, $state) {

    $scope.resourceId = window.localStorage.getItem("designTableId");

    //将英文名  小写转换大写
    $scope.toChangename = function() {
        $scope.englishName=$scope.englishName.toUpperCase();
    };
    $scope.toChangeTablename = function() {
        $scope.englishTabeleName=$scope.englishTabeleName.toUpperCase();
    };
    $scope.toChangeValue = function() {
        var type=$("select[name='type']").val();
        if(type=="varchar"&&$scope.len>4000){
            $scope.len=4000
        }
        if(type=="integer"&&$scope.len>38){
            $scope.len=38
        }
        if(type=="float"){
            if($scope.len>38){
                $scope.len=38
            }
            if($scope.len<5){
                $scope.len=5
            }
        }
    };

    //根据类型改变长度的最大值限制
    $scope.lengthContorl = function() {
        var type=$("select[name='type']").val();
        if(type=="time"||type =="BLOB"){
            $('#len').attr("disabled",true);
        }
        if(type=="varchar"){
            $scope.len=null;
            $('#len').attr("disabled",false);
        }
        if(type=="integer"||type=="float"){
            $scope.len=null;
            $('#len').attr("disabled",false);
        }
    };

    //是否已创建资源表
    $http({
        url: "/sofn-dgap-web/dataImport/checkOrNot",
        data: {resourceId: $scope.resourceId},
        method: "post"
    }).success(function (data) {
        if (data.data== "表不存在") {
            $("#tableAdd").modal("show");
        }
    }).error(function (data) {
        $.jBox.tip("操作不成功！")
    })


    $scope.tableNameDesign = function () {
        $http({
            url: "/sofn-dgap-web/dataImport/dataImportTable/add",
            data: {
                englishName: $scope.englishTabeleName,
                chineseName: $scope.chineseTableName,
                resourceId:$scope.resourceId,
            },
            method: 'post',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            if (data.httpCode == 200) {
                $.jBox.tip("新增成功！！！", 'info');
                $scope.tableName=$scope.chineseTableName;
                $scope.tableEnglishName=$scope.englishTabeleName;
                $("#reDir").dataTable().api().ajax.reload();
            } else {
                $.jBox.tip("新增失败！！！", 'info');
            }
        })
    };

    //获取表名
    $http({
        url: "sofn-dgap-web/dataImport/dataImportTable/getById",
        data: {
            resourceId: $scope.resourceId
        },
        method: 'post',
        headers: {
            post: {'Content-Type': 'application/x-www-form-urlencoded'}
        }
    }).success(function (data) {
        if (data.httpCode == 200) {
            if(data.data!=null){
                $scope.tableName=data.data.chineseName;
                $scope.tableEnglishName=data.data.englishName;
            }
        } else {
            $.jBox.tip("获取表名失败！！！", 'info');
        }
    })


    $scope.returnMenu = function () {
        $state.go("index.content.dgap/resource/resourceMa");
    };

    //获取字段类型
    $http({
        url: "sofn-dgap-web/dataImport/fieldType",
        method: 'post',
        headers: {
            post: {'Content-Type': 'application/x-www-form-urlencoded'}
        }
    }).success(function (data) {
        if (data.httpCode == 200) {
            $scope.filedType=[];
            for(var i=0;i<data.data.length;i++){
                if(data.data[i].name!="时间戳"){
                    $scope.filedType.push(data.data[i])
                }
            }
        } else {
            $.jBox.tip("获取字段类型失败！！！", 'info');
        }
    })

    //返回主页  资源管理页面
    $scope.returnMenu = function () {
        $state.go("index.content.dgap/resource/resourceMa");
    };



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

    /*修改表单认证*/
    $('#updateResoueceDirF').bootstrapValidator();//必须有
    $(".updateResoueceDirSave").click(function () {
        if (!$("#updateResoueceDirF").data('bootstrapValidator').validate().isValid()) {
            return false;
        }//必须有
//           提交执行函数全写在这下面
        update();
    });
    /*执行添加之后清空上次添加的内容*/
    $('#resDirAdd').on('hide.bs.modal', function () {
        $('#attributeForm').data('bootstrapValidator').resetForm(true);
    });
    /*执行修改之后清空上次添加的内容*/
    $('#updates').on('hide.bs.modal', function () {
        $("#updateResoueceDirF").data('bootstrapValidator').destroy();//摧毁验证
        $('#updateResoueceDirF').bootstrapValidator();//必须有
    });

    //模态框显示
    //bootstrap表格
    //资源目录主体信息
    $("#reDir").dataTable({
        processing: false, // 加载时提示
        serverSide: false, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        paging:false,
        bInfo:false,
        bJQueryUI: false,
        bPaginate: false,// 分页按钮
        bFilter: false,// 搜索栏
        // iDisplayLength: 10,// 每页显示行数
        bSort: true,// 排序
        bAutoWidth: false,
        //sScrollY: "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        ajax: {
            url: "sofn-dgap-web/dataImport/fieldList2",
            dataSrc: function (json) {
                return json.data;
            },
            data: function (params) {
                params.token=window.localStorage.getItem("token");
                params.resourceId = $scope.resourceId;
            },
            type: "post"
        },
        columns: [
            {
                data: "englishName",
                title: "英文字段名",
            },
            {
                data: "chineseName",
                title: "中文字段名",
            },
            {
                data: "typeName",
                title: "字段类型",
            },
            {
                data: "len",
                title: "字段长度",
            },
            {
                data: "statusText",
                title: "状态",
                render:function (data,type,row) {
                    $scope.statusText=data;
                    return data;
                }
            },

            {
                data: "id",
                title: "操作",
                render: function (data, v, d) { // 模板化列显示内容  deletes(\'' + data + '\')
                    var unEnable = d.flagStatus;
                    if($scope.statusText=="不存在"){
                        return '<input class="idValue" type="hidden" value="' + data + '">' +
                            '<a data-toggle="modal1" data-target="#resDirAdd1" style="margin-right: 30px;cursor: pointer" onclick="updateShow(\'' + data + '\')">修改</a>' +
                            '<a style="cursor: pointer" ' + (unEnable == "enable" ? "" : " class=\"color-gray\"" ) + 'onclick="' + (unEnable == "disEnable" ? "$.jBox.tip(\'不能删除！\')" : "deletes('" + data + "')") + '">删除</a>';//转义传值
                    }else{
                        // return "修改" +"&nbsp;&nbsp;"+"删除"
                        return "  "
                    }

                },
                width: "20%"
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


    //新增字段;
    function add() {
        $scope.addModal = function () {
            if (!$("#attributeForm").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "sofn-dgap-web/dataImport/dataImportField/add",
                data: {
                    englishName: $scope.englishName,
                    chineseName: $scope.chineseName,
                    len: $scope.len,
                    resourceId:$scope.resourceId,
                    type: $("select[name='type']").val(),
                },
                method: 'post',
                headers: {
                    post: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }).success(function (data) {
                if (data.httpCode == 200) {
                        $.jBox.tip("新增成功！！！", 'info');
                        $scope.len="";//清空长度字段
                        $("#reDir").dataTable().api().ajax.reload();
                } else {
                    if(data.data=="中文字段名过长"){
                        $.jBox.tip("新增失败！！！中文字段名过长");
                    }
                    else if(data.data=="字段名重复"){
                        $.jBox.tip("新增失败！！！字段名重复");
                    }
                    else if(data.data=="字段名重复且中文字段名过长"){
                        $.jBox.tip("新增失败！！！字段名重复且中文字段名过长");
                    }else{
                        $.jBox.tip("新增失败！！！");
                    }
                }
            })
        }
    }

    //修改资源目录
    /*调用模态框*/

    window.updateShow = function (data) {
        /*  数据回显*/
        $scope.dataId = data;
        $http({
            url: "/sofn-dgap-web/dataImport/dataImportField/getFiledById",
            data: {
                id: data,
            },
            //post传输方式   头信息
            method: 'POST',
            headers: {
                post: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        }).success(function (data) {
            $scope.updateList = data.data;
            $("select[name='typeUpdate']").val(data.data.type),
            $("#updates").modal("show");
        }).error(function (data) {

        });
    };

    function update() {
        $scope.updateSave = function () {
            if (!$("#updateResoueceDirF").data('bootstrapValidator').validate().isValid()) {
                return false;
            }
            $http({
                url: "/sofn-dgap-web/dataImport/dataImportField/update",
                data: {
                    id: $scope.dataId,
                    englishName: $scope.updateList.englishName,
                    chineseName: $scope.updateList.chineseName,
                    len: $scope.updateList.len,
                    type: $("select[name='typeUpdate']").val(),
                },
                method: "post"
            }).success(function (data) {
                $("#updates").modal("hide");
                if (data.httpCode == 200) {
                        $.jBox.tip("修改成功！！！");
                        $("#reDir").dataTable().api().ajax.reload();
                } else {
                    $.jBox.tip("操作不成功！")
                }
            }).error(function () {
                $.jBox.tip("操作不成功！！！");
                $("#updates").modal("hide");

            })
        }
    }

    //删除
    window.deletes = function (data) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "sofn-dgap-web/dataImport/dataImportField/delete2",
                    type: "post",
                    data: {
                        id: data,
                        resourceId:$scope.resourceId,
                        token:window.localStorage.getItem("token")
                    },
                    success: function (data) {
                        if (data.httpCode == 200) {
                            $("#reDir").dataTable().api().ajax.reload();
                            jBox.tip("删除成功！");
                        } else {
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



    //删除表
    $scope.deleteTable = function () {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.btnDisabled = false;
            });
        }, 5000);        
        
        $http({
            url: "/sofn-dgap-web/dataImport/deleteTable",
            data: {
                resourceId: $scope.resourceId,
            },
            method: "post"
        }).success(function (data) {
            if (data.httpCode == 200) {
                $.jBox.tip("删除成功！！！");
                $("#reDir").dataTable().api().ajax.reload();
            } else {
                $.jBox.tip("操作不成功！")
            }
        }).error(function () {
            $.jBox.tip("操作不成功！！！");
        })

    };
    //生成表
    $scope.creatTable = function () {
        //点击间隔
        $scope.btnDisabled = true;
        setTimeout(function(){
            $scope.$apply(function(){
                $scope.btnDisabled = false;
            });
        }, 5000); 

        $http({
            url: "/sofn-dgap-web/dataImport/createTable",
            data: {
                resourceId: $scope.resourceId,
            },
            method: "post"
        }).success(function (data) {
            if (data.httpCode == 200) {
                $.jBox.tip("创建成功！！！");
                $("#reDir").dataTable().api().ajax.reload();
            } else {
                $.jBox.tip("操作不成功！")
            }
        }).error(function () {
            $.jBox.tip("操作不成功！！！");
        })

    };



});
