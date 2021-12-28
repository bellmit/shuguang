var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjBaselist",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
	/**
	 * 分页展示数据
	 */
        //bootstrap表格
        $("#grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            //sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url:"/sofn-tts-web/ttsScltxxcjBase/getPageInfo",
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
                    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
                    params.id = entityInfo.data.id;
                    params.status = $("select[name='in_status']").val();
                    params.name =  $("input[name='keyWord']").val();
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data : "NUM",
                    title : "序号"
                },
                {
                    data: "NAME",
                    title: "基地名称"
                },
                {
                    data: "ADDRESS",
                    title: "基地地址"
                },
                {
            		data: "AREA",
            		title: "基地面积",
                    render : function(data,type,row){
                        return "<span>"+row.AREA+"亩</span>";
                    }
            	},
            	{
            		data: "PRODUCT_NAMES",
            		title: "主要产品"
            	},
            	{
            		data: "STATUS",
            		title: "基地状态",
                    render : function(data,type,row){
                        if(row.STATUS=="N"){
                            return '<select  class="CheckChange" onchange="Checkchange(\''+row.ID+'\',this)">' +
                                '<option value="Y" >启用</option>' +
                                '<option value="N" selected>停用</option>' +
                                '</select>';
                        }else if(row.STATUS=="Y"){
                            return '<select  class="CheckChange" onchange="Checkchange(\''+row.ID+'\',this)">' +
                                '<option value="Y" selected>启用</option>' +
                                '<option value="N" >停用</option>' +
                                '</select>';
                        }else{
                            return '<select  class="CheckChange" onchange="Checkchange(\''+row.ID+'\',this)">' +
                                '<option value="Y">启用</option>' +
                                '<option value="N">停用</option>' +
                                '</select>';
                        }
                    }
            	},
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="lookView(\''+data+'\')">查看</a>';
                        var update_a = '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="updateData(\''+data+'\')">修改</a>';
                        var delete_a = '<a class="color" style="cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
                        return look_a+update_a+delete_a;
                    }
                },
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

    
    /**
     * 跳转到查询页面
     */
    window.lookView=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjBase_id",data);
        $state.go("index.content.tts/TtsScltxxcjBase/ttsScltxxcjBaseView")
    }

    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjBase_id",data);
        $state.go("index.content.tts/TtsScltxxcjBase/ttsScltxxcjBaseUpdate")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-tts-web/ttsScltxxcjBase/deleteTtsScltxxcjBase",
                    type:"post",
                    dataType:"json",
                    data:{id:data},
                    success:function(data){
                        if(data.httpCode!=200){
                            jBox.tip("删除失败！", 'error');
                        }else{
                            jBox.tip("删除成功！", 'info');
                        }
                        $("#grid").dataTable().api().ajax.reload();
                    }
                });
            }
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认删除吗？", "删除提示", submit, { buttons: { '确认': true, '取消': false} });
    };

    //改变基地状态
    window.Checkchange = function(obj,val){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url :"/sofn-tts-web/ttsScltxxcjBase/changeBaseStatus",
            type:"post",
            contentType: "application/x-www-form-urlencoded",
            data:{id:obj,status:val.value},
            success:function(data){
                if(data.httpCode!=200){
                    jBox.tip("修改失败！", 'error');
                }else{
                    jBox.tip("修改成功！", 'info');
                }
                $("#grid").dataTable().api().ajax.reload();
            }
        });
    };
})

