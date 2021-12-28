var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjProductTypelist",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
     $(function(){
		//选择栏背景颜色变化
        $(".Sup_contain_section_head_nav li").click(function(){
            $(this).attr("id","bc_change").siblings().removeAttr("id","bc_change");
        });
        //各个部分的内容切换
        $(".Sup_contain_section_contain_01").css("top","80px");
        $(".Sup_contain_section_head_nav li:first-child").attr("id","bc_change").click(function(){
            $(".Sup_contain_section_contain_01").css("top","80px").siblings().css("top","-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(2)").click(function(){
            $(".Sup_contain_section_contain_02").css("top","80px").siblings().css("top","-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(3)").click(function(){
            $(".Sup_contain_section_contain_03").css("top","80px").siblings().css("top","-2000%")
        });
        $(".Sup_contain_section_head_nav li:nth-of-type(4)").click(function(){
            $(".Sup_contain_section_contain_04").css("top","80px").siblings().css("top","-2000%")
        });
        if(navigator.appVersion.substring(22,23)==9){
            if($(".myPlaceholder").val()==""){
                $(this).val("点击选择查询日期");
            }
        }
    });

	/**
	 * 分页展示数据
	 */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjProductType/getPageInfo",
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
            		title: "品种名称"
            	},
            	/*{
            		data: "STATUS",
            		title: "品种状态",
                    render:function(data,type,row){
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
            	},*/
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                        var delete_a = '<a style="cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
                        return delete_a;
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
        window.localStorage.setItem("tts_ttsScltxxcjProductType_id",data);
        $state.go("index.content.tts/ttsScltxxcjProductType/ttsScltxxcjProductTypeView")
    }
   
    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjProductType_id",data);
        $state.go("index.content.tts/ttsScltxxcjProductType/ttsScltxxcjProductTypeUpdate")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
    	//选择是否进行删除
        var submit = function (v, h, f) {
            if (v == true){
                $.ajax({
                    headers:{
                        token:window.localStorage.getItem("token")
                    },
                    url:"/sofn-tts-web/ttsScltxxcjProductType/deleteTtsScltxxcjProductType",
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

    //改变产品状态
    window.Checkchange = function(obj,val){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url :"/sofn-tts-web/ttsScltxxcjProductType/updateProductStatus",
            type:"post",
            contentType: "application/x-www-form-urlencoded",
            data:{id:obj,status:val.value},
            success:function(data){
                if(data.httpCode==200){
                    jBox.tip("修改成功", 'info');
                }else{
                    jBox.tip("修改失败", 'info');
                }
                $("#grid").dataTable().api().ajax.reload();
            }
        });
    };
})

