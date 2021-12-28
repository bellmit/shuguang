var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjNotificationlist",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
     	/**
	 * 分页展示数据
	 */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjNotification/getPageInfo",
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
                    params.createTimeStart = $("input[name='createTimeStart']").val();
                    params.createTimeEnd =  $("input[name='createTimeEnd']").val();
                    params.keyWord =  $("input[name='keyWord']").val();
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
            	{
            		data: "NUM",
            		title: "序号"
            	},
            	{
            		data: "TITLE",
            		title: "标题",
                    render : function (data,type,row) {
                        return '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="lookView(\''+row.ID+'\')">'+row.TITLE+'</a>';
                    }
            	},
            	{
            		data: "CREATE_TIME",
            		title: "创建时间",
                    render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
            	}
            ]
        });
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });
    
    
    /**
     * 跳转到查询页面
     */
    window.lookView=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjNotification_id",data);
        $state.go("index.content.tts/TtsScltxxcjNotification/ttsScltxxcjNotificationView")
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
         window.localStorage.setItem("tts_ttsScltxxcjNotification_id",data);
        $state.go("index.content.tts/TtsScltxxcjNotification/ttsScltxxcjNotificationUpdate")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
    	//选择是否进行删除
    	if(confirm('是否进行删除...'))
    	{
    		$.ajax({
                url:"/sofn-tts-web/ttsScltxxcjNotification/deleteTtsScltxxcjNotification",
                type:"post",
                dataType:"json",
                data:{id:data},
                success:function(data){
                    alert("删除成功！");
                    $("#grid").dataTable().api().ajax.reload();
                }
            });
    	}
    };
})

