var myapp=angular.module("myapp",[]);
myapp.controller("TtsScltxxcjProductlist",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
     var isType = {};
    //改变产品状态
    window.Checkchange = function(obj,val){
        $.ajax({
            headers:{
                token:window.localStorage.getItem("token")
            },
            url :"/sofn-tts-web/ttsScltxxcjProduct/updateProductStatus",
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

    $http({
        headers:{
            token:window.localStorage.getItem("token")
        },
        method:'POST',
        url:'/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo'
    }).success(function(data){
        if(data.list != null){
            for(var i in data.list){
                isType[data.list[i].dictValue]=data.list[i].dictName;
            }
        }
    })

	/**
	 * 分页展示数据
	 */
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {

                url : "/sofn-tts-web/ttsScltxxcjProduct/getPageInfo",
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
                    params.keyWord = $("input[name='in_name']").val();
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
                    title: "产品名称"
                },
                {
                    data: "INDUSTRY",
                    title: "所属行业",
                    render : function(data,type,row){
                        return isType[row.INDUSTRY];
                    }
                },
                {
                    data: "STATUS",
                    title: "状态",
                    render : function(data, type, row) { // 模板化列显示内容
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
                        var update_a = '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="updateData(\''+data+'\')">修改</a>';
                        return update_a;
                    }
                },
            ]
        });
    
    /**
     * 前往产品详细信息页面
     */
    window.lookView=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjProduct_id",data);
        $state.go("index.content.tts/TtsScltxxcjProduct/ttsScltxxcjProductView")
    }
    /**
     * 前往产品信息管理页面
     * @param data
     */
    window.queryList=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjProduct_id",data);
        $state.go("index.content.tts/TtsScltxxcjProduct/TtsScltxxcjProductlist")
    }
    /**
     * 前往产品添加页面
     * @param data
     */
    window.add=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjProduct_id",data);
        $state.go("index.content.tts/TtsScltxxcjProduct/ttsScltxxcjProductAdd");
    }
    /**
     * 前往产品品种信息管理页面
     * @param data
     */
    window.toProType=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjProduct_id",data);
        $state.go("index.content.tts/ttsScltxxcjProductType/list");
    }
   
    /**
     * 执行修改的方法
     */
    window.updateData = function(data){
         window.localStorage.setItem("tts_ttsScltxxcjProduct_id",data);
        $state.go("index.content.tts/TtsScltxxcjProduct/ttsScltxxcjProductUpdate")
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
                    url:"/sofn-tts-web/ttsScltxxcjProduct/deleteTtsScltxxcjProduct",
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
})

