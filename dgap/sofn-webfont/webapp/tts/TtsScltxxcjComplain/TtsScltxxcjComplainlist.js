var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjComplainlist",function($scope,$http,$state){
    $("#search_bt").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
       /**
     * 检查开始时间是否大于结束时间
     * @param data
     */
    window.changeDate=function(obj){
        var start = $("input[name='dateBegin']").val();
        var end =  $("input[name='dateEnd']").val();
        if(start != null && start != '' && end != null && end != ''){
            if(start > end){
                jBox.tip("开始日期不能大于结束日期", 'error');
                $(obj).val('');
            }
        }
    }
	/**
	 * 分页展示数据
	 */
    $(function() {
        var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
        var entityIdcode;
        if(entityInfo&&entityInfo.data){
            entityIdcode = entityInfo.data.entityIdcode;
        }
        var map = $.parseJSON(window.localStorage.getItem('IssueType'));
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjComplain/getPageInfo",
                dataSrc:function(json){
                        if(!entityIdcode){
                            json.recordsTotal=0;//总个数
                            json.recordsFiltered=0;
                            json.pageNumber=0;//页码
                            json.pageSize=0;//每页个数
                            json.start=0;//起始位置
                            json.length=0;
                            return {};
                        }
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    params.entityIdcode = entityIdcode;
                    params.dateBegin = $("#dateBegin").val();
                    params.dateEnd =  $("#dateEnd").val();
                    params.keywords = $("input[name='keywords']").val();
                },
                headers:{
                    token:window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "ENTITY_IDCODE",
                    title: "投诉主体身份码",
                    visible : false
                },
                {
                    data: "USER_IDCODE",
                    title: "投诉主体用户码",
                    visible : false
                },
                {
                    data: "BE_ENTITY_IDCODE",
                    title: "被投诉主体身份码",
                    visible : false
                },
                {
                    data: "BE_USER_IDCODE",
                    title: "被投诉主体用户码",
                    visible : false
                },
                {
                    data: "ACC_ENTITY_IDCODE",
                    title: "受理主体身份码",
                    visible : false
                },
                {
                    data: "ACC_USER_IDCODE",
                    title: "受理主体用户码",
                    visible : false
                },
                {
                    data: "COMPLAINT_ENT_NAME",
                    title: "被投诉主体名称"
                },
                {
                    data: "COMPLAINT_TITLE",
                    title: "投诉标题"
                },
                {
                    data: "CONTENT",
                    title: "投诉内容",
                    visible : false
                },
                {
                    data: "TYPE_NAME",
                    title: "问题类型"

                },
                {
                    data: "STATUS",
                    title: "投诉状态"
                    ,render : function(data, type, row) {
                    if(data){
                        return data=data=="0"?"未受理":"已受理";
                    }else{
                        return data;
                    }
                },
                },
                {
                    data: "ACCEPTANCE",
                    title: "受理意见",
                    visible : false
                },
                {
                    data: "AREA_ID",
                    title: "所属区域ID",
                    visible : false
                },
                {
                    data: "ACC_TIME",
                    title: "受理时间"
                    ,render : function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');

                },
                    visible : false
                },
                {
                    data: "ATTACHMENT_NAME",
                    title: "附件名称",
                    visible : false
                },
                {
                    data: "CREATE_TIME",
                    title: "投诉时间"
                    ,render : function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
                },
                {
                    data: "ATTACHMENT_PATH",
                    title: "附件路径",
                    visible : false
                },
                {
                    data: "COMPLAINT_COP_NAME",
                    title: "投诉主体名称",
                    visible : false
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="lookView(\'' + data + '\')">查看详情</a>';
                        //var update_a = '<a style="margin-right: 10px;cursor: pointer" onclick="updateData(\''+data+'\')">修改</a>';
                        //var delete_a = '<a style="cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
                        return look_a;
                    }
                },
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
        window.localStorage.setItem("tts_ttsScltxxcjComplain_id",data);
        $state.go("index.content.tts/TtsScltxxcjComplain/ttsScltxxcjComplainView")
    }
   
    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjComplain_id",data);
        $state.go("index.content.tts/TtsScltxxcjComplain/ttsScltxxcjComplainUpdate")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
    	//选择是否进行删除
    	if(confirm('是否进行删除...'))
    	{
    		$.ajax({
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url:"/sofn-tts-web/ttsScltxxcjComplain/deleteTtsScltxxcjComplain",
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

