var myapp=angular.module("myapp",[]);
myapp.controller("adsModelSampleLink",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after")

    //tab跳转
    $scope.goCheckObject = function () {
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject")
    }
    $scope.goCheckStandard = function () {
        $state.go("index.content.monitor/model/adsModelCheckStandard/adsModelCheckStandard")
    }
    $scope.goCheckItem =function () {
        $state.go("index.content.monitor/model/checkItem/adsModelCheckItemList")
    }
    $scope.goModelType = function () {
        $state.go("index.content.monitor/model/adsModelType/modelTypeList")
    }
    $scope.goSampleLink = function () {
        $state.go("index.content.monitor/model/adsModelSampleLink/adsModelSampleLink")
    }
    $scope.goJudgeStabdard = function () {
        $state.go("index.content.monitor/model/judgeStandard/adsModelJudgeStandardList")
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-ads-web/adsModelSampleLink/getPageInfoAll",
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
                    params.name = $("#nameS").val();
                },
                type:"post"
            },
            columns : [
               /* {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                        return view;
                    }
                },*/
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "环节名称"
                },
                /*{
                    data : "ID",
                    title : "数据操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="updateAdsModelSampleLink(\''+row.id+"\',\'"+row.name+'\') ">修改</a>';
                        var look_b = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="deleteAdsModelSampleLink(\''+row.id+'\') ">删除</a>';
                        return look_a + look_b;
                    }
                },*/
            ],
        });
    });

    $scope.addAdsModel = function(){
        $('#addAdsModelSampleLink').data('bootstrapValidator').resetForm(true);
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

    //批量删除全中的数据
    window.delAdsModelSampleLink=function () {
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
                        url:"/sofn-ads-web/adsModelSampleLink/delAdsModelSampleLink",
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
            $.jBox.confirm("确认删除模型_抽样环节吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择模型_抽样环节数据", 'info');
        }
    };

    //新增模型_抽样环节按钮事件
    $scope.addAdsModelSampleLink = function(){
        if (!$("#addAdsModelSampleLink").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var name = $("#name").val();
        $.ajax({
            url:"/sofn-ads-web/adsModelSampleLink/addAdsModelSampleLink",
            type:"post",
            dataType:"json",
            data:{"name":name,"token":token},
            success:function(data){
                if(data.httpCode == 500){
                    jBox.tip("保存失败", 'info');
                }else if(data.httpCode == 400){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                }else{
                    $("#addModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                }
            }
        });
    };

    //修改模型_抽样环节
    window.updateAdsModelSampleLink = function(id,name){
        $('#updateAdsModelSampleLink').data('bootstrapValidator').resetForm(true);
        $("#id").val(id);
        $("#nameU").val(name);
        $("#updateModal").modal("show");
    };

    //修改模型_抽样环节按钮事件
    $scope.updateAdsModel = function(){
        if (!$("#updateAdsModelSampleLink").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var id = $("#id").val();
        var name = $("#nameU").val();
        $.ajax({
            url:"/sofn-ads-web/adsModelSampleLink/updateAdsModelSampleLink",
            type:"post",
            dataType:"json",
            data:{"id":id,"name":name,"token":token},
            success:function(data){
                if(data.httpCode == 500){
                    jBox.tip("修改失败", 'info');
                }else if(data.httpCode == 400){
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("没有登录", 'info');
                }else{
                    $("#updateModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("修改成功", 'info');
                }
            }
        });
    };

    //删除模型_抽样环节
    window.deleteAdsModelSampleLink = function (id) {
        var id = id;
        var submit = function (v, h, f) {
            if (v == true)
                $.ajax({
                    url:"/sofn-ads-web/adsModelSampleLink/deleteAdsModelSampleLink",
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
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除该条模型_抽样环节吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //查询更新
    $("#seachTask").click(function () {
        var str = $("#nameS").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if(length > 60){
            jBox.tip("长度过长,请更改输入内容", 'info');
        }else $("#grid").dataTable().api().ajax.reload();
    });

    $(document).ready(function() {
        $('#addAdsModelSampleLink').bootstrapValidator();
        $('#updateAdsModelSampleLink').bootstrapValidator();
    });
});