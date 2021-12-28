var myapp=angular.module("myapp",[]);
myapp.controller("slaughterStorList",function($scope,$http,$state){
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
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if(userdata){
        entity_id = userdata.id;
    }
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
                url : "/sofn-tts-web/ttsScltxxcjSlaStor/getStorPageInfo",
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
                    params.productType = $("select[name='productType']").val();//产品种类
                    // params.productName =  $("select[name='productName']").val();//产品名称
                    params.productName = $("input[name='productName']").val();
                    params.status =  $("select[name='status']").val();//状态
                    params.shrq_q =  $("input[name='startDate']").val();//开始日期
                    params.shrq_h =  $("input[name='endDate']").val();//结束日期
                    params.keyWord =  $("input[name='keyWord']").val();//关键字
                    params.userId = entity_id;
                },
                type:"post"
            },
            columns : [
                {
                    data: "ID",
                    title: "ID",
                    visible : false
                }, 
            	{
            		data: "PRODUCT_NAME",
            		title: "产品名称"
            	},
                {
                    data: "PRODUCT_AMOUNT",
                    title: "入库数量"
                },
                {
                    data: "STORE_COUNT",
                    title: "库存数量"
                },
            	{
            		data: "HARVEST_UNIT",
            		title: "单位"
            	},
                {
                    data: "PRODUCT_PC",
                    title: "产品批次码",
                     render : function(data, type, row) {
                        var billstatus = row.BILL_STRATRUS;
                        if(billstatus == "1"){
                            return "<a class='btn btn-primary btn-xs'" +
                                "onclick=findCphc('"+row.ID+"')>" + data+"</a>";
                        }
                        return data;
                    }
                },
            	{
            		data: "HARVEST_TIME",
            		title: "入库时间"
            		,render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
            	},
            	{
                    data: "STATUS",
                    title: "当前状态",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(row.CURRENT_AMOUNT==row.INIT_AMOUNT){
                            return data = '未卖出';
                        }else if(row.CURRENT_AMOUNT < row.INIT_AMOUNT){
                            return data = '未卖完';
                        }
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) {
                        var delete_a = '<a style="margin-right: 10px;cursor: pointer" onclick="deleteData(\''+data+'\')">删除</a>';
                        var print_a = '<a style="cursor: pointer" onclick="printxxx(\''+data+'\')">打印</a>';
                        if (row.STATUS == "1") {
                            return delete_a+print_a;
                        }
                        return print_a;
                    }
                }
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
    window.findCphc = function(hcid){
        //产品信息框展示
        $("#cphc_info").modal('show');
        //清理数据
        var tableName = $('#cphcList');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#cphcList").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
//            bAutoWidth:true,
//            sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : true,
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjCppchc/getPageInfo",
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
                    params.hcid = hcid;

                },
                type:"post"
            },
            columns : [
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量"
                },  {
                    data: "PRODUCT_PC",
                    title: "合成前产品批次码"
                }
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
    };
    /**
     * 跳转到查询页面
     */
    window.printxxx=function(data){
        window.localStorage.setItem("tts_ttsScltxxcjScgl_id",data);
        $state.go("index.content.tts/slaughter/printEwm")
    }
    /**
     * 获取产品分类列表
     */
    $(function () {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getslaughterProType",
            type:'POST',//POST方式
            dataType:'text',//返回json类型
            success:function(data,status){
                var d=eval(data);//解析
                $(d).each(function(index,entity){
                    $("#proType").append($('<option value="'+entity['productName']+'">'+entity['productName']+'</option>'));//后台数据加到下拉框
                });
            }
        });
        //预留获取
        /*var productType = $("#proType").val();
        if(productType != ""||productType != null){
                $.ajax({
                url:"/sofn-tts-web/ttsScltxxcjScgl/getslaughterProType",
                type:'POST',//POST方式
                data: {"productType":productType},
                dataType:'text',//返回json类型
                success:function(data,status){
                    var d=eval(data);//解析
                    $(d).each(function(index,entity){
                        $("#proName").append($('<option value="'+entity['productName']+'">'+entity['productName']+'</option>'));//后台数据加到下拉框
                    });
                }
            });
        }*/
    })

})

