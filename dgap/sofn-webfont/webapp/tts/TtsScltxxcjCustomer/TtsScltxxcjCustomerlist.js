var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjCustomerlist",function($scope,$http,$state){
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
                url : "/sofn-tts-web/ttsScltxxcjCustomer/getPageInfo",
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
                    params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
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
                    data: "USER_NAME",
                    title: "用户名字"
                },
                {
                    data: "PHONE",
                    title: "联系方式"
                },
                {
                    data: "NAME",
                    title: "单位名称"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="lookView(\''+data+'\')">查看</a>';
                        var delete_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="toDeleteView(\''+data+'\')">删除</a>';
                        return look_a+delete_a;
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
        window.localStorage.setItem("tts_ttsScltxxcjCustomer_id",data);
        $state.go("index.content.tts/TtsScltxxcjCustomer/ttsScltxxcjCustomerView")
    }
    window.toDeleteView=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjCustomer_id",data);
        $state.go("index.content.tts/TtsScltxxcjCustomer/ttsScltxxcjCustomerDel")
    }

    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjCustomer_id",data);
        $state.go("index.content.tts/TtsScltxxcjCustomer/ttsScltxxcjCustomerUpdate")
    }

})

