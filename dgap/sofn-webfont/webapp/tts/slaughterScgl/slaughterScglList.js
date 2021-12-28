var myapp=angular.module("myapp",[]);
myapp.controller("slaughterScglList",function($scope,$http,$state){
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
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjScgl/getPageInfo",
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
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                },
                type:"post"
            },
            columns : [
            	{
                    data : "RN",
                    title : "序号"
                },	
            	{
            		data: "PRODUCT_NAME",
            		title: "产品名称"
            	},
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量"
                },
            	{
            		data: "HARVEST_UNIT",
            		title: "单位"
            	},
                {
                    data: "MEDI_CHECK",
                    title: "质检情况"
                },
                {
                    data: "HARVEST_BASENAME",
                    title: "收获基地"
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "产品来源"
                },
            	{
            		data: "HARVEST_TIME",
            		title: "收获时间"
            		,render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
            	},
            	{
            		data: "PRODUCT_PC",
            		title: "产品批次码"
            	},
            	{
                    data: "STATUS",
                    title: "当前状态"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="lookView(\''+data+'\')">查看</a>';
                        var update_a = '<a style="margin-right: 10px;cursor: pointer" onclick="updateData(\''+data+'\')">修改</a>';
                        var delete_a = '<a style="cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
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
        //导航展开
        $("#collapseThree").attr("class","panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });
    
    
    /**
     * 跳转到查询页面
     */
    window.lookView=function(data){
        window.localStorage.setItem("tts_slaughterScgl_id",data);
        $state.go("index.content.tts/slaughterScgl/slaughterScglView")
    }

    /**
     * 执行删除的方法
     */
    window.deleteData = function(data){
    	//选择是否进行删除
    	if(confirm('是否进行删除...'))
    	{
    		$.ajax({
                url:"/sofn-tts-web/ttsScltxxcjScgl/deleteTtsScltxxcjScgl",
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

