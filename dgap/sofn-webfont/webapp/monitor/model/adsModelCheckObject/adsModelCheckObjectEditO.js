var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckObjectEditO",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");

    //获取本地存储的对象id
    var objectId = window.localStorage.getItem("objectId");

    //跳转到修改检测对象名称页面
    $scope.edit = function(){
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObjectEdit");
    };

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
    $("#checkObject").click(function(){
        $state.go("index.content.monitor/model/adsModelCheckObject/adsModelCheckObject")
    });

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            dom : '<"top">rt<"bottom"pli><"clear">',
            ajax : {
                url : "/sofn-ads-web/adsModelCheckObject/getPageInfoChecked",
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
                    params.objectId = objectId;
                    params.name = $("#nameS").val();
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='taskName' value='"+row.id+"'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "项目名称"
                },
                {
                    data : "id",
                    title : "数据操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="deleteAdsModelObjectItemMapping(\''+row.id+'\') ">删除</a>';
                        return look_a;
                    }
                },
            ],
            language : {
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


    //延时加载数据
    $scope.stand_flag = 0;//加载标识
    /**
     * 展示选择检测标准模态框
     */
    $scope.addAdsModel = function(){
        $("#addModal").modal("show");
        setTimeout(function () {
            //弹出未添加的检测项目列表
            if($scope.stand_flag==0){
                $scope.loadCheckStandardData();
            }else{
                $("#itemGrid").dataTable().api().ajax.reload();
            }
        },300);
    };

    /**
     * 加载项目列表数据
     */
    $scope.loadCheckStandardData = function () {
        $("#itemGrid").dataTable({
            ajax : {
                url : "/sofn-ads-web/adsModelCheckObject/getPageInfoUnChecked",
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
                    params.objectId = objectId;
                    params.name = $("#nameS").val();
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task1' value='"+row.id+"'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "项目名称"
                },
            ],
        });
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
        $scope.stand_flag =1;
    };

    //批量添加项目
    $scope.addAdsModelObjectItemMapping = function () {
        var check = $("input[name='task1']:checked");
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
                        url:"/sofn-ads-web/adsModelCheckObject/addAdsModelObjectItemMapping",
                        type:"post",
                        dataType:"json",
                        data:{"ids":arr.toString(),"objectId":objectId.toString(),"token":token},
                        success:function(data){
                            if(data.httpCode==200){
                                jBox.tip("添加成功", 'success');
                                $("#addModal").modal("hide");
                                $("#grid").dataTable().api().ajax.reload();
                            }else if(data.httpCode == 400){
                                $("#grid").dataTable().api().ajax.reload();
                                jBox.tip("没有登录", 'info');
                            }else{
                                jBox.tip("添加失败", 'info');
                            }
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("确认模型_检测项目吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择模型_检测项目数据", 'info');
        }
    };

    //单个删除对象
    window.deleteAdsModelObjectItemMapping = function (id) {
        var AdsModelObjectItemMapping = {};
        AdsModelObjectItemMapping.id = id;
        var submit = function (v, h, f) {
            if (v == true)
                $http({
                    url:"/sofn-ads-web/adsModelCheckObject/deleteAdsModelObjectItemMapping",
                    data:AdsModelObjectItemMapping,
                    method:"post",
                    headers: {
                        post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                    }
                }).success(function(data){
                    if(data.httpCode == 500){
                        jBox.tip("删除失败", 'info');
                    }else{
                        jBox.tip("删除成功", 'info');
                    }
                    $("#grid").dataTable().api().ajax.reload();
                }).error(function(){
                    jBox.tip("删除失败", 'info');
                });
            else
                jBox.tip("取消成功", 'success');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除该条模型_检测项目吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //查询更新
    $("#seachTask").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });

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
    window.delAdsModelObjectItemMapping=function () {
        var check = $("input[name='taskName']:checked");
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
                        url:"/sofn-ads-web/adsModelCheckObject/delAdsModelObjectItemMapping",
                        type:"post",
                        dataType:"json",
                        data:{"ids":arr.toString()},
                        success:function(data){
                            if(data.httpCode==200){
                                jBox.tip("删除成功", 'success');
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
            $.jBox.confirm("确认删除模型_检测项目吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });

        }else{
            jBox.tip("请先选择模型_检测对象数据", 'info');
        }
    };
});