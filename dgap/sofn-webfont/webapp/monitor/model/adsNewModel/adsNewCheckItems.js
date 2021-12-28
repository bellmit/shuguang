var myapp=angular.module("myapp",[]);
myapp.controller("adsNewCheckItems",function($scope,$http,$state,$window){
    var token = window.localStorage.getItem("token");

    $scope.reloadRoute = function () {
        $window.location.reload();
    };

    //获取行业分类字典
    $scope.IndustryType = new Array();
    var industryId = "";
    window.loadA = function () {
        /**
         * 加载行业分类字典
         */
        /*$.ajax({
         url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
         type: "post",
         dataType: "json",
         success: function (data) {
         $scope.IndustryType = data.data;
         $scope.selectedIndustry = $scope.IndustryType[0].dictName;
         industryId = $scope.IndustryType[0].dictCode;
         },
         fail: function (data) {
         $.jBox.tip("失败");
         }
         });*/

        $http({
            url: "/sofn-ads-web/adsDictData/getIndustryTypeData",
            method:"post",
            dataType:"json",
        }).success(function(data){
            $scope.IndustryType = data.data;
            $scope.selectedIndustry = $scope.IndustryType[0].dictName;
            industryId = $scope.IndustryType[0].dictCode;
        });
    };

    loadA();

    //二级联动改变值和获取值
    $(document).ready(function(){
        $('#industry').change(function(){
            var s = $('#industry option:selected').val();
            $($scope.IndustryType).each(function (idx) {
                var a = $(this)[0];
                if(a.dictName == s){
                    industryId = a.dictCode;
                    return false;
                }
            });
        });
    });

    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsNewModel/getPageInfoAll",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    params.itemName = $("#itemNames").val()
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                // {
                //     title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                //     render : function(data, type, row) {
                //         // 数据操作选择项
                //         var view = "<input type='checkbox' name='checkName' value='"+row.checkId+"'>";
                //         return view;
                //     }
                // },
                // {
                //     data: "rn",
                //     title: "序号"
                // },
                {
                    data: "itemName",
                    title: "检测项目"
                },
                {
                    data: "standardCode",
                    title: "检测标准"
                },
                {
                    data: "tempTime",
                    title: "创建时间"
                },
                // {
                //     data: "checkId",
                //     title: "数据操作",
                //     render: function (data, type, row) {
                //         // 数据操作选择项
                //         var look_a = '<a class="pointer mr15" onclick="updateCheckItem(\'' + row.checkId + '\') ">修改</a>';
                //         return look_a;
                //     }
                // }
            ],
        });
    });

    //修改检测项目
    window.updateCheckItem=function (checkId) {
        $.ajax({
            url:"/sofn-ads-web/adsNewModel/isOrNotUse",
            type:"post",
            dataType:"json",
            data:{"checkId":checkId,"token":token},
            success:function(data){
                if(data.httpCode==423) {
                    jBox.tip("该检测项已被模型引用，禁止修改", 'info');
                }else if (data.httpCode=200){
                    $("#updateModal").modal("show");
                    $http({
                        url: "/sofn-ads-web/adsNewModel/getCheckItemById",
                        method: "post",
                        dataType: "json",
                        params: {"checkId": checkId, "token": token}
                    }).success(function (data) {
                        $scope.checkNameu = data.data.checkName;
                        $scope.checkId = data.data.checkId;
                    });
                }
            }
        });
    }

    //保存修改的检测项目
    $scope.saveCheckItem=function () {
        if (!$("#updateCheckItem").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var checkName = $("#checkNameu").val();
        var checkId = $("#checkId").val();
        $.ajax({
            url:"/sofn-ads-web/adsNewModel/saveCheckItem",
            type:"post",
            dataType:"json",
            data:{"checkId":checkId,"checkName":checkName,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    $("#addModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改成功", 'info');
                    $("#updateModal").modal("hide");
                }else if(data.httpCode == 400){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                    $("#updateModal").modal("hide");
                }else if(data.httpCode == 409){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改失败,该名称已被使用", 'info');
                    $("#updateModal").modal("hide");
                }else if(data.httpCode == 423){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("改项目已被模型引用，禁止修改", 'info');
                    $("#updateModal").modal("hide");
                }else{
                    jBox.tip("修改失败", 'info');
                }
            }
        });

    };






    //启用检测模型
    window.enableAdsCheckModel = function (id,isEnable) {
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-ads-web/adsCheckModel/enableAdsCheckModel",
                    type:"post",
                    dataType:"json",
                    data:{"id":id,"isEnable":isEnable,"token":token},
                    success:function(data){
                        if(data.httpCode == 500){
                            jBox.tip("启用失败", 'info');
                        }else if(data.httpCode == 400){
                            jBox.tip("没有登录", 'info');
                        }else if(data.httpCode == 406) {
                            jBox.tip("请将模型信息编辑完全后再启用", 'info');
                        }else {
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
        if(isEnable == 1){
            $.jBox.confirm("确认启用该条检测模型吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }else if(isEnable == 0){
            $.jBox.confirm("确认取消启用该条检测模型吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }

    };

    //查询更新
    $("#seachTask").click(function () {
        var str = $("#itemNames").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if(length > 60){
            jBox.tip("长度过长,请更改输入内容", 'info');
        }else{
            $("#grid").dataTable().api().ajax.reload();
        }
    });

    $scope.addAdsModel = function(){
        $('#addCheckModel').data('bootstrapValidator').resetForm(true);
        $("#addModal").modal("show");
    };

    //模板下载
    $scope.download=function () {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-ads-web/adsNewModel/download");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }


    //Excel数据导入
    $scope.addAdsExcelData = function(){
        $("#addExcelData").modal("show");
        $("#file-1").fileinput({
            language: 'zh',
            uploadUrl: "/sofn-ads-web/adsNewModel/importExcel",
            allowedFileExtensions: [ 'xsl', 'xlsx'],
            maxFileSize: 51200,
            maxFilesNum: 1,
            showPreview: false,
            showUpload: true, //是否显示上传按钮
            uploadLabel: '导入',
            enctype: 'multipart/form-data',
            maxFileCount: 1, //表示允许同时上传的最大文件个数
            msgFilesTooMany: "请选择{m}个文件！",
            msgSizeTooLarge: "只允许上传50MB的文件"
        }).on("fileuploaded", function(event, data){
            if(200==data.response.code){
                $("#addExcelData").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
                jBox.tip("导入成功", 'info');
                $("#file-1").fileinput("destroy");
            }else if(409==data.response.httpCode){
                $("#addExcelData").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
                jBox.tip("导入检测项目名已存在，请核对后再试", 'info');
                $("#file-1").fileinput("destroy");
            }else if(400==data.response.httpCode){
                $("#grid").dataTable().api().ajax.reload();
                jBox.tip("没有登录", 'info');
            }else{
                $("#grid").dataTable().api().ajax.reload();
                jBox.tip("导入失败", 'info');
            }

        });
    };

    //全选
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选
            $("input[name='task']").attr("checked",true);
        }else{
            $("input[name='task']").attr("checked",false);
        }
    };

    //新增检测模型按钮事件
    $scope.addAdsCheckModel = function(){
        if (!$("#addCheckModel").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var checkName = $("#checkName").val();
        $.ajax({
            url:"/sofn-ads-web/adsNewModel/addCheckItems",
            type:"post",
            dataType:"json",
            data:{"checkName":checkName,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    $("#addModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                }else if(data.httpCode == 400){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                }else if(data.httpCode == 409){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败,该名称已被使用", 'info');
                }else{
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    };

    //删除检测模型
    window.deleteAdsCheckModel = function (id) {
        var id = id;
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-ads-web/adsCheckModel/deleteAdsCheckModel",
                    type:"post",
                    dataType:"json",
                    data:{"id":id,"token":token},
                    success:function(data){
                        if(data.httpCode == 500){
                            jBox.tip("删除失败", 'info');
                        }else if(data.httpCode == 400){
                            jBox.tip("没有登录", 'info');
                        }else{
                            jBox.tip("删除成功", 'info');
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
        $.jBox.confirm("确认该条检测项目记录吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //批量删除全中的数据
    window.delAdsCheckModel=function () {
        var check = $("input[name='checkName']:checked");
        if(check.length > 0){
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id =$(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $.ajax({
                        url:"/sofn-ads-web/adsNewModel/delAdsCheckItems",
                        type:"post",
                        dataType:"json",
                        data:{"ids":arr.toString(),"token":token},
                        success:function(data){
                            if(data.httpCode==200){
                                jBox.tip("删除成功", 'success');
                            }else if(data.httpCode == 400){
                                jBox.tip("没有登录", 'info');
                            }else{
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
            $.jBox.confirm("确认删除检测项目记录吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择检测项目记录", 'info');
        }
    };

    //跳转到编辑页面
    window.editAdsCheckModel = function (id, modelName, industry, isEnable) {
        if(isEnable == 1){
            jBox.tip("请先取消启用该条模型后进行配置", "info");
            return false;
        }
        window.localStorage.setItem("id",id);
        window.localStorage.setItem("modelName",modelName);
        window.localStorage.setItem("industry",industry);
        window.localStorage.setItem("industryId",industryId);
        $state.go("index.content.monitor/model/adsCheckModel/adsCheckModelEdit");
    };


    $(document).ready(function() {
        $('#addCheckModel').bootstrapValidator();
    });



    $(document).ready(function() {
        $('#updateCheckItem').bootstrapValidator();
    });


});

