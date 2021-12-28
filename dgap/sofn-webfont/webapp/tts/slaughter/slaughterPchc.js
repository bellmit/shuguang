var myapp=angular.module("myapp",[]);
myapp.controller("slaughterPchc",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
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
            bAutoWidth: true,
            //sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjSlaStor/getPchcPageInfo",
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
                    params.productName = $("input[name='in_productName']").val();
                    params.productType =  $("select[name='in_harvestUnit']").val();
                    params.userId = entity_id;
                },
                type:"post"
            },
            columns : [
                 {
                    sClass: "text-center",
                    title :"<input type='checkbox' class='checkall' />",
                    data: "ID",
                    render: function (data, type, full, meta) {
                        return '<input type="checkbox" name="checkbox"  class="checkchild"  value="' + data + '" />';
                    },
                   "bSortable": false
                },
            	{
            		data: "PRODUCT_NAME",
            		title: "产品名称"
            	},
                {
                    data: "PRODUCT_AMOUNT",
                    title: "初始数量"
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
                    title: "产品批次码"
                },
            	{
            		data: "HARVEST_TIME",
            		title: "入库时间"
            		,render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
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
//数据全选
    $(".checkall").click(function () {
        var check = $(this).prop("checked");
        $(".checkchild").prop("checked", check);
    });
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
    })
    //modal 产品信息
    $scope.findProduct = function(){
        //产品信息框展示
        $("#product_info").modal('show');
        //清理数据
        var tableName = $('#productList');
        if($.fn.dataTable.isDataTable(tableName)){
               tableName.DataTable().clear();
               tableName.DataTable().destroy();
         }
         //加载数据
        $("#productList").dataTable({
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
                url : "/sofn-tts-web/ttsScltxxcjSlaRecord/getSlaProductPageInfo",
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
                    data: "PRODUCT_NAME",
                    title: "所属种类"
                },
                {
                    data: "PRODUCT_SLAUGHTER_NAME",
                    title: "产品名称"
                },
                {
                    data: "PRODUCT_UNIT",
                    title: "单位"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                        // 数据操作选择项
                         return "<button class='btn btn-primary btn-xs'" +
                         "onclick=selectProduct('"+row.ID+"','"+row.PRODUCT_NAME+"','"+row.PRODUCT_SLAUGHTER_NAME+"','"+row.PRODUCT_UNIT+"')>" +
                         "选定产品</button>";
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
    };
    
    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectProduct = function(id,type,name,unit){
        $("input[name='in_productId']").val(id);
        $("input[name='in_productName']").val(name);
    }
    
   
    //产品合成
    $("#ToCphc").click(function(){
         var spids  = "";
          $('input[name="checkbox"]:checked').each(function(){    
              spids+=$(this).val()+";";    
          });
          $scope.spids = spids.substring(0,spids.length-1);
          //没有选中进行提示选择数据
          if(spids.length==0){
              $.jBox.tip("你还没有选择任何内容");
          }
          $scope.userId = $.parseJSON(window.localStorage.getItem("entityInfo")).data.id;
          //待传入数据
          var config = {
            spids:$scope.spids,
            userId:$scope.userId
        };
        $.post("/sofn-tts-web/ttsScltxxcjSlaStor/addSlaPchc",config,function(data){
            if(data.httpCode == "200"){
                alert("保存成功"+data.msg);
            }
            $("#grid").dataTable().api().ajax.reload();

        });
          /*$http({
                //产品批次合成
                url:"/sofn-tts-web/ttsScltxxcjSlaStor/addSlaPchc",
                data :{
                    spids : $scope.Str.spids,
                    userId : $scope.Str.userId
                },
                method:"post",
                headers: {
                     post: { 'Content-Type': 'application/x-www-form-urlencoded' }
                }
             }).success(function(data){
                 if(data.httpCode == "200"){
                     alert("保存成功"+data.msg);
                 }
                 $("#grid").dataTable().api().ajax.reload();
            }).error(function(){
                   alert("保存失败");
            })*/
    });
    
    //ToClean 清空查询条件
    $("#ToClean").click(function(){
        $("input[name='in_productId']").val("");
        $("input[name='in_productName']").val("");
        $("select[name='in_harvestUnit']").val("");
    });
})
