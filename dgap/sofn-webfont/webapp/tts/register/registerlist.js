var myapp=angular.module("myapp",[]);
myapp.controller("registerlist",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    	/**
	 * 分页展示数据
	 */

        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjUser/getPageInfo",
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
                    params.createTimeStart = $("#beginDate").val();
                    params.createTimeEnd =  $("#lessDate").val();
                    params.keyWord =  $("input[name='keyWord']").val();

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
            		title: "姓名"
            	},
                {
            		data: "PHONE",
            		title: "联系电话"
            	},
                {
            		data: "EMAIL",
            		title: "邮箱"
            	},
                {
                    data: "ACCOUNT",
                    title: "账号"
                },
				{
                    data: "ACCOUNT",
                    title: "初始密码"
                },
                {
            		data: "CREATE_TIME",
            		title: "创建时间",
                    render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
            	},
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                        /*var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="lookView(\''+data+'\')">查看</a>';
                        var update_a = '<a style="margin-right: 10px;cursor: pointer" onclick="updateData(\''+data+'\')">修改</a>';*/
                        var delete_a = '<a style="cursor: pointer" class="color" onclick="deleteData(\''+data+'\')">删除</a>';
                        var reset_a = '<a style="cursor: pointer" class="color" onclick="resetData(\''+row.ACCOUNT+'\')">重置密码</a>';
                        return delete_a+'&nbsp'+reset_a;
                    }
                },
            ],
        });
    
    
    /**
     * 跳转到查询页面
     */
    window.lookView=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjUser_id",data);
        $state.go("index.content.tts/ttsScltxxcjUser/ttsScltxxcjUserView")
    }

    /**
     * 检查开始时间是否大于结束时间
     * @param data
     */
    window.changeDate=function(){
        var start = $("input[name='createTimeStart']").val();
        var end =  $("input[name='createTimeEnd']").val();
        if(start != null && start != '' && end != null && end != ''){
            if(start > end){
                jBox.tip("开始日期不能大于结束日期", 'error');
                $("input[name='createTimeEnd']").val('');
            }
        }
    }
   
    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjUser_id",data);
        $state.go("index.content.tts/ttsScltxxcjUser/ttsScltxxcjUserUpdate")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-tts-web/ttsScltxxcjUser/deleteTtsScltxxcjUser",
                    type:"post",
                    dataType:"json",
                    data:{id:data},
                    success:function(data){
                        if(data.httpCode==200){
                            jBox.tip("删除成功", 'info');
                        }else{
                            jBox.tip("删除失败", 'error');
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
    /**
     * 重置密码
     * @param data
     */
    window.resetData = function(data){
        var token =  window.localStorage.getItem("token");
    	//选择是否进行重置
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    url:"/sofn-tts-web/ttsScltxxcjUser/resetTtsScltxxcjUser?token="+token,
                    type:"post",
                    dataType:"json",
                    data:{account:data},
                    success:function(data){
                        if(data.httpCode==200){
                            jBox.tip("重置成功", 'info');
                        }else{
                            jBox.tip("重置失败", 'error');
                        }
                        $("#grid").dataTable().api().ajax.reload();
                    }
                });
            }
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("重置为初始密码？", "提示", submit, { buttons: { '确认': true, '取消': false} });



    };
})

