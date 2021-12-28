var myapp=angular.module("myapp",[]);
myapp.controller("adsModelJudgeStandardList",function($scope,$http,$state){
    var token = window.localStorage.getItem("token");
    $("#sectionForm").bootstrapValidator();
    $("#addAdsModelJudgeStandardList").bootstrapValidator();
    /**
     * 显示新增模态框
     */
    $scope.showAddModal=function () {
        $("#dModal").modal("show");
    }

    $("#search").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    })

    //enter submit
    $scope.onInputChanged = function($event){
        if ($event && $event.keyCode == 13) {
            $("#grid").dataTable().api().ajax.reload();
        }
    };

    /**
     * 新增执行标准
     */
    $scope.addJudgeStandard=function () {
        //表单验证
        var name = $("input[name='name']").val();
        if(name==null || name==''){
            jBox.tip("请输入判定标准名称", 'info');
            return false;
        }
        //提交表单
        $.ajax({
            url:"/sofn-sys-web/adsModelJudgeStandard/addAdsModelJudgeStandard",
            type:"post",
            dataType:"json",
            data:{"name":name,"token":token},
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("添加成功", 'success');
                    goEditBaseInfo(data.data.id);   //跳转到编辑页面
                }else{
                    jBox.tip("添加失败", 'info');
                    $("#grid").dataTable().api().ajax.reload();
                }
            }
        });
        $("#dModal").modal("hide");
        //清除已有数据
        $("input[name='name']").val("");
    }

    /**
     * 跳转到编辑页面
     */
    window.goEditBaseInfo = function (id) {
        window.localStorage.setItem("judgeStandardId",id);
           setTimeout(function(){
               $state.go("index.content.sys/judgeStandard/editBaseInfo");
           },200);
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-sys-web/adsModelJudgeStandard/getPageInfo",
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
                    params.name = $("#judge_name").val();
                },
                type:"post"
            },
            columns : [
                {
                 title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var view = "<input type='checkbox' name='judge' value='"+row.id+"'>";
                 return view;
                 }
                 },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "name",
                    title: "标准名称"
                },
                {
                    data: "filePath",
                    title: "附件",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var file = "";
                        if(row.filePath!=null && row.filePath!=''){
//                            file = '<a download="" href="'+row.filePath+'" class="btn btn-link" style="margin-right: 10px;cursor: pointer">下载</a>';
                            var tempname = row.name+'附件';
                           file = '<a class="color " style="cursor: pointer" class="Check-report"  onclick="downloadFile(\'' + row.filePath + "','" + tempname + '\')">下载</a>';
                        }else{
                            file = '无';
                        }
                        return file;
                    }
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
                {
                 data : "ID",
                 title : "操作",
                 render : function(data, type, row) {
                 // 数据操作选择项
                 var update_a = '<span class="btn btn-link" style="margin-right: 10px;cursor: pointer" onclick="goEditBaseInfo(\''+row.id+'\')">修改</span>';
                 return update_a;
                 }
                 }
            ]
        });
    });

    /**
     * 全选任务数据
     */
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选
            $("input[name='judge']").attr("checked",true);
        }else{
            $("input[name='judge']").attr("checked",false);
        }
    }

    /**
     * 批量删除判定标准
     */
    $scope.batchDelete=function () {
        var check = $("input[name='judge']:checked");
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
                        url:"/sofn-sys-web/adsModelJudgeStandard/batchDeleteAdsModelJudgeStandard",
                        type:"post",
                        dataType:"json",
                        data:{"judge_ids":arr.toString(),"token":token},
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
            $.jBox.confirm("确认要删除所选判定标准吗？", "操作提示", submit, { buttons: { '确认': true, '取消': false} });
        }else{
            jBox.tip("请先选择判定标准", 'info');
        }
    }

    window.downloadFile = function(fileAddress, filename) {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-sys-web/sysTemplate/dowload");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', fileAddress);
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', filename);
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

})
