var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckObject",function($scope,$http,$state){
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
                url : "/sofn-ads-web/adsModelCheckObject/getPageInfoAll",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    for(var i=0;i<json.data.list.length;i++){
                        //时间格式化
                        if(json.data.list[i].createTime!=null&&json.data.list[i].createTime!=""&&json.data.list[i].createTime!=undefined){
                            json.data.list[i].createTime=new Date(parseInt(json.data.list[i].createTime)+28800000).toISOString().slice(0,10);
                        }
                    }
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    params.name = $("#nameS").val();
                    params.token = token;
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
                    data: "name",
                    title: "对象名称"
                },
                {
                    data: "createTime",
                    title: "创建时间"/*,
                    render : function(data, type, row) {
                        return new Date(parseInt(data)+28800000).toISOString().slice(0,10);
                    }*/

                },
                {
                    data : "ID",
                    title : "数据操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a  class="pointer mr15" onclick="editAdsModelCheckObject(\''+row.id+"\',\'"+row.name+"\',\'"+row.numbers+'\') ">编辑</a>';
                        var look_b = '<a  class="pointer" onclick="deleteAdsModelCheckObject(\''+row.id+'\') ">删除</a>';
                        return look_a + look_b;
                    }
                },
            ],
        });
    });

    $scope.addAdsModel = function(){
      /*  $('#addAdsModelCheckObject').data('bootstrapValidator').resetForm(true);
        $("#addModal").modal("show");*/
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObjectAdd");
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
    window.delAdsModelCheckObject=function () {
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
                        url:"/sofn-ads-web/adsModelCheckObject/delAdsModelCheckObject",
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
            $.jBox.confirm("确认删除检测对象记录吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择检测对象记录", 'info');
        }
    };

    //新增模型_检测对象按钮事件
    $scope.addAdsModelCheckObject = function(){
        if (!$("#addAdsModelCheckObject").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var name = $("#name").val();
        var numbers = $("#numbers").val();
        $.ajax({
            url:"/sofn-ads-web/adsModelCheckObject/addAdsModelCheckObject",
            type:"post",
            dataType:"json",
            data:{"numbers":numbers,"name":name,"token":token},
            success:function(data){
                if(data.httpCode == 200){
                    $("#addModal").modal("hide");
                    $("#grid").dataTable().api().ajax.reload();
                    jBox.tip("保存成功", 'info');
                }else if(data.httpCode == 409){
                    jBox.tip("保存失败,该名称已被使用", 'info');
                }else{
                    jBox.tip("保存失败", 'info');
                }
            }
        });
    };

    //删除模型_检测对象
    window.deleteAdsModelCheckObject = function (id) {
        var id = id;
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-ads-web/adsModelCheckObject/deleteAdsModelCheckObject",
                    type:"post",
                    dataType:"json",
                    data:{"id":id,"token":token},
                    success:function(data){
                        if(data.httpCode == 500){
                            $("#grid").dataTable().api().ajax.reload();
                            jBox.tip("删除失败", 'info');
                        }else{
                            $("#grid").dataTable().api().ajax.reload();
                            jBox.tip("删除成功", 'info');
                        }
                    }
                });
            }
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认该条检测对象记录吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //查询更新
    $("#seachTask").click(function () {
        var str = $("#nameS").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if(length > 60){
            jBox.tip("长度过长,请更改输入内容", 'info');
        }else $("#grid").dataTable().api().ajax.reload();
    });

    window.editAdsModelCheckObject = function (id, name, numbers) {
        window.localStorage.setItem("checkObjectId",id);
/*        window.localStorage.setItem("name",name);
        window.localStorage.setItem("numbers",numbers);*/
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObjectEdit");
    }

    $(document).ready(function() {
        $('#addAdsModelCheckObject').bootstrapValidator();
    });
});