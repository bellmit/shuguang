var myapp=angular.module("myapp",[]);
myapp.controller("adsModelCheckItemList",function($scope,$http,$state){

    var token = window.localStorage.getItem("token");

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
     * 展示新增模态框
     */
    $scope.showAddModal=function () {
        $("#dModal").modal("show");
    }

    $scope.stand_flag = 0;  //加载标识
    $scope.modalType = "S";
    /**
     * 展示选择检测标准模态框
     */
    $scope.showSelectStandModal=function (type) {
        $scope.modalType = type;
        $("#sModal").modal("show");
        //加载检测标准数据
        setTimeout(function () {
            if($scope.stand_flag==0){
                $scope.loadCheckStandardData();
            }else{
                $("#s_grid").dataTable().api().ajax.reload();
            }
        },300);
    }

    /**
     * 加载检测标准列表数据
     */
    $scope.loadCheckStandardData=function () {
        $("#s_grid").dataTable({
            ajax : {
                url : "/sofn-ads-web/adsModelCheckStandard/getPageInfoAll",
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

                },
                type:"post"
            },
            columns : [
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "标准名称"
                },
                {
                    data: "value",
                    title: "检测标准上限值"
                },
                {
                    data : "ID",
                    title : "选择检测标准",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<span class="btn btn-info btn-sm" onclick="checkStandData(\''+row.id+'\',\''+row.name+'\')">选择</span>';
                        return look_a;
                    }
                }
            ],
        });
        $scope.stand_flag =1;
    }

    /**
     * 选中检测标准
     */
    window.checkStandData=function(id,name){
        if($scope.modalType=="S"){
            $("input[name='standardId']").val(id);
            $("input[name='standardName']").val(name);
        }else{
            $("#u_standardId").val(id);
            $("#u_standardName").val(name);
        }
        $("#sModal").modal("hide");
    }

    /**
     * 新增检测项目
     */
    window.addCheckItem=function () {
        //表单验证
        var name = $("input[name='name']").val();
        var standardId = $("input[name='standardId']").val();
        if(name==null || name==''){
            jBox.tip("请输入检测项目名称", 'info');
            return false;
        }
        if(standardId==null || standardId==''){
            jBox.tip("请选择检测标准", 'info');
            return false;
        }
        //提交表单
        $.ajax({
            url:"/sofn-ads-web/adsModelCheckItem/addAdsModelCheckItem",
            type:"post",
            dataType:"json",
            data:{"name":name,"standardId":standardId,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("添加成功", 'success');
                }else{
                    jBox.tip("添加失败", 'info');
                }
                $("#grid").dataTable().api().ajax.reload();
            }
        });
        $("#dModal").modal("hide");
        //清除已有数据
        $("input[name='name']").val("");
        $("input[name='standardId']").val("");
        $("input[name='standardName']").val("");
    }

    $("#search").click(function () {
        var str = $("#nameS").val();
        var length = str.replace(/[\u4e00-\u9fa5]/g, 'aa').length;
        if(length > 60){
            jBox.tip("长度过长,请更改输入内容", 'info');
        }else $("#grid").dataTable().api().ajax.reload();
    });

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-ads-web/adsModelCheckItem/getPageInfo",
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
                    params.name = $("input[name='item']").val();
                },
                type:"post"
            },
            columns : [
                /*{
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='item' value='"+row.id+"'>";
                        return view;
                    }
                },*/
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "检测项目名称"
                },
                {
                    data: "standardName",
                    title: "检测标准名称"
                },
                {
                    data: "createTime",
                    title: "创建日期",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var time = new Date(row.createTime).format('yyyy-MM-dd');
                        return time;
                    }
                },
               /* {
                    data : "id",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var update_a = '<a style="margin-right: 10px;cursor: pointer" onclick="showUpdateModal(\''+row.id+'\',\''+row.name+'\',\''+row.standardId+'\',\''+row.standardName+'\')">修改</a>';
                        return update_a;
                    }
                },*/
            ],
        });
    });

    /**
     * 展示更新模态框
     */
    window.showUpdateModal=function (id,name,standardId,standardName) {
        $("#u_id").val(id);
        $("#u_name").val(name);
        $("#u_standardId").val(standardId);
        $("#u_standardName").val(standardName);
        $("#uModal").modal("show");
    }

    /**
     * 修改检测项目
     */
    window.updateCheckItem=function () {
        //表单验证
        var id = $("#u_id").val();
        var name = $("#u_name").val();
        var standardId = $("#u_standardId").val();
        if(name==null || name==''){
            jBox.tip("请输入检测项目名称", 'info');
            return false;
        }
        if(standardId==null || standardId==''){
            jBox.tip("请选择检测标准", 'info');
            return false;
        }
        //提交
        $.ajax({
            url:"/sofn-ads-web/adsModelCheckItem/updateAdsModelCheckItem",
            type:"post",
            dataType:"json",
            data:{"id":id,"name":name,"standardId":standardId,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("修改成功", 'success');
                }else{
                    jBox.tip("修改失败", 'info');
                }
                $("#grid").dataTable().api().ajax.reload();
            }
        });
        $("#uModal").modal("hide");
    }

    /**
     * 全选任务数据
     */
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选
            $("input[name='item']").attr("checked",true);
        }else{
            $("input[name='item']").attr("checked",false);
        }
    }

    /**
     * 批量删除检测项目
     */
    $scope.batchDelete=function () {
        var check = $("input[name='item']:checked");
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
                        url:"/sofn-ads-web/adsModelCheckItem/batchDeleteCheckItem",
                        type:"post",
                        dataType:"json",
                        data:{"item_ids":arr.toString(),"token":token},
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
            $.jBox.confirm("确认要删除所选检测项目吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }else{
            jBox.tip("请先选择检测项目", 'info');
        }
    }


})

