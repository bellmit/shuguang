var myapp=angular.module("myapp",[]);
myapp.controller("adsCheckModel",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //获取行业分类字典
    $scope.IndustryType = new Array();
    var industryId = "";
    window.loadA = function () {
        /**
         * 加载行业分类字典
         */
        $.ajax({
            url: "/sofn-sys-web/adsDictData/getIndustryTypeData",
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
        });
    };

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
        loadA();
        //bootstrap表格
        $("#grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-sys-web/adsCheckModel/getPageInfoAll",
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
                    params.modelName = $("#modelNameS").val();
                    /*params.monitorType = $('#monitorTypeS option:selected').val();*/
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
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
                    title: "行业"
                },
                {
                    data : "ID",
                    title : "数据操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="editAdsCheckModel(\''+row.id+"\',\'"+row.modelName+"\',\'"+row.industry+'\') ">编辑</a>';
                        var look_b = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="deleteAdsCheckModel(\''+row.id+'\') ">删除</a>';
                        return look_a + look_b;
                    }
                }
            ],
            language : {
                "sProcessing" : "处理中...",
                "sLengthMenu" : "显示 _MENU_ 项结果",
                "sZeroRecords" : "没有匹配结果",
                "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty" : "显示第 0 至 0 项结果，共 项",
                "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix" : "",
                "sSearch" : "搜索:",
                "sUrl" : "",
                "sEmptyTable" : "表中数据为空",
                "sLoadingRecords" : "载入中...",
                "sInfoThousands" : ",",
                "oPaginate" : {
                    "sFirst" : "首页",
                    "sPrevious" : "上页",
                    "sNext" : "下页",
                    "sLast" : "末页"
                },
                "oAria" : {
                    "sSortAscending" : ": 以升序排列此列",
                    "sSortDescending" : ": 以降序排列此列"
                }
            },
            sPaginationType : "full_numbers"
        });
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });

    //查询更新
    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

    $scope.addAdsModel = function(){
        $('#addCheckModel').data('bootstrapValidator').resetForm(true);
        $("#addModal").modal("show");
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
        var modelName = $("#modelName").val();
        var industry = $('#industry option:selected').val();
        $.ajax({
            url:"/sofn-sys-web/adsCheckModel/addAdsCheckModel",
            type:"post",
            dataType:"json",
            data:{"modelName":modelName,"industry":industry,"industryId":industryId,"token":token},
            success:function(data){
                console.log(data);
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
                    url:"/sofn-sys-web/adsCheckModel/deleteAdsCheckModel",
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
        $.jBox.confirm("确认该条模型_检测对象吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //批量删除全中的数据
    window.delAdsCheckModel=function () {
        var check = $("input[name='task']:checked");
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
                        url:"/sofn-sys-web/adsCheckModel/delAdsCheckModel",
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
            $.jBox.confirm("确认删除模型_检测对象吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择模型_检测对象数据", 'info');
        }
    };

    //跳转到编辑页面
    window.editAdsCheckModel = function (id, modelName, industry) {
        window.localStorage.setItem("id",id);
        window.localStorage.setItem("modelName",modelName);
        window.localStorage.setItem("industry",industry);
        window.localStorage.setItem("industryId",industryId);
        $state.go("index.content.sys/adsCheckModel/adsCheckModelEdit");
    };


    $(document).ready(function() {
        $('#addCheckModel').bootstrapValidator();
    });
});