var myapp=angular.module("myapp",[]);
myapp.controller("cppchc",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    //浏览器获取登录信息
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "", entityIndustry;
    if(userdata){
        entity_id = userdata.id;
        entityIndustry = userdata.entityIndustry;
    }
    //初始化搜索框单位
    getUnits();
    function getUnits() {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getUnits",
            type:'POST',//POST方式
            dataType:'text',//返回json类型
            data:{
                id : entityIndustry
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data,status){
                
                // var d=eval(data);//解析
                var d = JSON.parse(data);
                $(d).each(function(index,entity){
                    $("#units").append($('<option value="'+entity['dictName']+'">'+entity['dictName']+'</option>'));//后台数据加到下拉框
                });
            }
        });
    }
    /**
     * 获取产品分类列表
     */
    $(function () {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjProduct/getProType",
            type:'POST',//POST方式
            dataType:'text',//返回json类型
            data:{
                id : entity_id 
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data,status){
                // var d=eval(data);//解析
                var d = JSON.parse(data);
                $(d).each(function(index,entity){
                    $("#ptype").append($('<option value="'+entity['typeName']+'">'+entity['typeName']+'</option>'));//后台数据加到下拉框
                });
            }
        });
    })
	/**
	 * 分页展示数据
	 */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjScgl/getPageInfoPchc",
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
                    // params.productId = $("input[name='in_productId']").val();
                    params.harvestUnit =  $("select[name='in_harvestUnit']").val();
                    params.shrq_q = $("input[name='startDate']").val();
                    params.shrq_h = $("input[name='endDate']").val();
                    params.entity_id = entity_id;
                    params.productSort = $("#ptype").val();
                    params.productName = $("input[name='in_productName']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
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
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                },
				{
					data: "PRODUCT_AMOUNT",
					title: "收获数量"
				},
                {
                    data: "STORE_COUNT",
                    title: "当前库存"
                },
				{
					data: "HARVEST_UNIT",
					title: "收获单位"
				},
				{
					data: "MEDI_CHECK",
					title: "质检情况"
				},
				{
					data: "MEDI_RESULT",
					title: "质检结论"
				},
				{
					data: "PRODUCT_SOURCE",
					title: "产品来源"
				},
				{
					data: "HARVEST_TIME",
					title: "收获时间",
					render : function(data, type, row) {
					      return new Date(data).format('yyyy-MM-dd');
					}
				},
				{
					data: "PRODUCT_PC",
					title: "产品批次",
                    render : function(data,type,row){
                        var source = row.PRODUCT_SOURCE;
                        if(source=="采购"){
                            return "无";
                        }else if(source!="采购" && null!=data && data.length>18){
                        var str = data.substring(0,18)+'...';
                            return '<a title="'+data+'">'+str+'</a>';}
                        return data;
                    }
				},
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择
                        var productSource = row.PRODUCT_SOURCE;
                        var xsqr_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="prodcment_info(\''+data+'\')">查看详细</a>';
                        return xsqr_a;
                    }
                }
            ]
        });

    });

    window.prodcment_info = function (id) {
        $("#product_informition").modal('show');
        $("#pro_kh").hide();
            $.ajax({
                url: "/sofn-tts-web/ttsScltxxcjScgl/getproductInfo",
                type: 'POST', //POST方式
                dataType: 'json', //返回json类型
                data: {
                    id: id
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                success: function (data) {
                    var entityIdcode =data.data.entityIdcode;
                    $("#s_EntityIdcode").html(entityIdcode);
                    var userIdcode =data.data.userIdcode;
                    $("#s_UserIdcode").html(userIdcode);
                    var harvestBasename =data.data.harvestBasename;
                    $("#s_ztmc").html(harvestBasename);
                    var productAmount =data.data.storeCount;
                    $("#s_lxr").html(productAmount);
                    var  harvestTime = data.data.harvestTime;
                    var times =new Date(harvestTime).format('yyyy-MM-dd');
                    $("#s_ztdz").html(times);
                    var  mediCheck = data.data.mediCheck;
                    $("#s_lxdh").html(mediCheck);
                    var productName = data.data.productName;
                    $("#product_name").html(productName);
                    var  productSort = data.data.productSort;
                    $("#product_sort").html(productSort);
                    var  productPc = data.data.productPc;
                    $("#cpzsm4").html(productPc);
                    var  mediCheck = data.data.mediCheck;
                    $("#xsrq").html(mediCheck);
                    var  productSource = data.data.productSource;
                    $("#xssl").html(productSource);
                    var  productId = data.data.productId;
                    $("#product_check").html(productId);
                    var  freezeCount = data.data.freezeCount;
                    $("#cpzsm1").html(freezeCount);

                    var  mediResult = data.data.mediResult;
                    $("#cpzsm2").html(mediResult);

                    var  harvestUnit = data.data.harvestUnit;
                    $("#cpzsm3").html(harvestUnit);

                    var  productPc = data.data.productPc;
                    var fromzsm = data.data.fromzsm;
                    if(productSource == "采购"){
                        $("#code").html("追溯码");
                        $("#cpzsm").html(fromzsm);
                    }else{
                        $("#code").html("批次码");
                        $("#cpzsm").html(productPc);
                    }
                }

            });
            // if(productSource=="采购"){
            //     $("#pro_kh").show();
            //     $.ajax({
            //         url: "/sofn-tts-web/ttsScltxxcjScgl/getproductInfom",
            //         type: 'POST', //POST方式
            //         dataType: 'json', //返回json类型
            //         data: {
            //             id: id
            //         },
            //         header: {
            //             token: window.localStorage.getItem("token")
            //         },
            //         success: function (data) {
            //
            //         }
            //     })
            // }

    }
    
   //数据全选
    $(".checkall").click(function () {
        var check = $(this).prop("checked");
        $(".checkchild").prop("checked", check);
    });
    
    
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
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjScgl/getProductByTerm",
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
                    params.entity_id =  entity_id;
                    params.typeName = $("#ptype").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
            	{
                    data: "INDUSTRY",
                    title: "所属行业",
                    render : function(data,type,row){
                        return isType[data];
                    }
                },
                {
                    data: "NAME",
                    title: "名称"
                },
                {
                    data: "TYPE",
                    title: "类型",
                    visible : false
                },
                {
                    data: "TYPE_NAME",
                    title: "类型"/*,
                    render : function(data,type,row){
                        return isTypeName[data];
                    }*/
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                    	 return "<button class='btn btn-primary btn-xs'" +
                         "onclick=selectProduct('"+row.PRODUCT_CODE+"','"+row.NAME+"','"+row.INDUSTRY+"','"+row.TYPE+"')>" +
                         "选定产品</button>";
                    }
                },
            ]
        });
    };
    
    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectProduct = function(id,name,industry,product_sort){
    	$("input[name='in_productId']").val(id);
    	$("input[name='in_productName']").val(name);
    }
     var isType = {};
    $http({
        method:'POST',
        url:'/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo',
        header:{
            token : window.localStorage.getItem("token")
        },
    }).success(function(data){
        if(data.list != null){
            for(var i in data.list){
                isType[data.list[i].dictValue]=data.list[i].dictName;
            }
        }
    })   
    //产品合成
    $("#ToCphc").click(function(){
    	 var spids  = "";
    	  $('input[name="checkbox"]:checked').each(function(){    
    		  spids+=$(this).val()+";";    
    	  });
    	  $scope.spids = spids.substring(0,spids.length-1);
    	  //没有选中进行提示选择数据
          if(spids == ""){
              $.jBox.tip("你还没有选择任何内容");
              return;
          }
          if(spids.length==0){
    		  $.jBox.tip("你还没有选择任何内容");
              return;
    	  }

        /*var config = {
            spids:$scope.spids,
            entity_id:entity_id
        };*/
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/addCppchc",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                spids:$scope.spids,
                entity_id:entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                if(data.httpCode == "200"){
                    jBox.tip("合成成功！", 'info');
                }else if(data.httpCode == "400"){
                    jBox.tip("只能合成两个或两个以上的相同属性产品！", 'info');
                }else{
                    jBox.tip("只能合成产品名称和产品分类均相同的产品！", 'info');
                }
                $("#grid").dataTable().api().ajax.reload();
            }
        });
       /* $.post("/sofn-tts-web/ttsScltxxcjScgl/addCppchc",config,function(data){
            if(data.httpCode == "200"){
               jBox.tip("合成成功！", 'info');
            }else{
                jBox.tip("所选数据不符合合成条件！", 'info');
            }
            $("#grid").dataTable().api().ajax.reload();

        });*/
    	  ////待传入数据
    	  //$http({
    	  //      //产品批次合成
    	  //      url:"/sofn-tts-web/ttsScltxxcjScgl/addCppchc",
    	  //      data :{
          //
           //     },
    	  //      method:"post",
    	  //      headers: {
    	  //           post: { 'Content-Type': 'application/x-www-form-urlencoded' }
    	  //      }
    	  //   }).success(function(data){
          //
    	  //  }).error(function(){
    	  //         alert("保存失败");
    	  //  })
    });
    
    //ToClean 清空查询条件
    $("#ToClean").click(function(){
    	$("input[name='in_productId']").val("");
    	$("input[name='in_productName']").val("");
    	$("select[name='in_harvestUnit']").val("");
        $("#startDate").val("");
        $("#endDate").val("");
        $("#ptype").val("");
        $("#grid").dataTable().api().ajax.reload();
        
        

    });
      
})

