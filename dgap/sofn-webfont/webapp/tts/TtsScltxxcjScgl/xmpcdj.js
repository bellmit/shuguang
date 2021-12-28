var myapp=angular.module("myapp",[]).controller("xmpcdj",function($scope,$http,$state){
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if(userdata){
        entity_id = userdata.id;
        entityIndustry = userdata.entityIndustry;
    }
    $("#ToChange").change(function(){
        var fromSource = $("#ToChange").val();
        if(fromSource=="收购"){
            $("#time").html("收购时间");
            $("#amount").html("收购数量");
            $("input[name='productAmount']").attr("placeholder","请输入收购数量");
            $("#isHarvest").hide();
        }else{
            $("#time").html("出栏时间");
            $("#amount").html("出栏数量");
            $("input[name='productAmount']").attr("placeholder","请输入出栏数量");
            $("#isHarvest").show();
        }
    });
    //产品批次登记信息
    $scope.addTtsScltxxcjScgl = function(){
        var dt = new Date($("input[name='harvestTime']").val().replace(/-/g,"/"));
        if(!$("input[name='productName']").val()){
            jBox.tip("请选择产品！", 'info');
            return;
        }
        if(!$("select[name='productSource']").val()){
            jBox.tip("请选择产品来源！", 'info');
            return;
        }
        if(!$("input[name='harvestTime']").val()){
            jBox.tip("请选择出栏时间！", 'info');
            return;
        }else if(dt > new Date()){
            jBox.tip("选择的出栏时间必须小于当前时间！", 'info');
            return;
        }
        if(!$("input[name='productAmount']").val()){
            jBox.tip("请输入出栏数量，只能输入1~99999之间的数字！", 'info');
            return;
        }else if(!(/^\+?[1-9]\d*$/).test($("input[name='productAmount']").val())){
            jBox.tip("出栏数量只能输入1~99999之间的数字！", 'info');
            return;
        }
        if(!$("select[name='harvestUnit']").val()){
            jBox.tip("请选择出栏单位！", 'info');
            return;
        }
        var source = $("#ToChange").val();
        if(source != "收购" && !$("input[name='harvestBasename']").val()){
            jBox.tip("请选择出栏圈舍！", 'info');
            return;
        }
        //if(!$("input[name='productInnerKey']").val()){
        //    jBox.tip("请输入内部追溯标识！", 'info');
        //    return;
        //}
        if(!$("select[name='mediCheck']").val()){
            jBox.tip("请选择质检情况！", 'info');
            return;
        }
        if(!$("select[name='mediResult']").val()){
            jBox.tip("请选择质检结论！", 'info');
            return;
        }
        var mediResult = $("select[name='mediResult']").val();
        if(mediResult == "不合格"){
            jBox.tip("您的产品质检情况不合格，不能在国家平台进行登记！", 'info');
            return;
        }
    	//产品id、产品名称、所属行业、产品类型
    	$scope.ttsScltxxcjScgl.productId = $("input[name='productId']").val();
    	$scope.ttsScltxxcjScgl.productName = $("input[name='productName']").val();
    	$scope.ttsScltxxcjScgl.productIndustry = $("input[name='productIndustry']").val();
   	 	$scope.ttsScltxxcjScgl.productSort = $("input[name='productSort']").val();
    	//收获时间
        $scope.ttsScltxxcjScgl.harvestTime = $("input[name='harvestTime']").val();
    	//收获数量
        $scope.ttsScltxxcjScgl.productAmount = $("input[name='productAmount']").val();
    	 //收获单位
        $scope.ttsScltxxcjScgl.unitId = $("select[name='harvestUnit']").val();
        //收获基地
        $scope.ttsScltxxcjScgl.harvestBaseid = $("input[name='harvestBaseid']").val();
   	 	$scope.ttsScltxxcjScgl.harvestBasename = $("input[name='harvestBasename']").val();
   	 	//内部追溯标识
   	 	$scope.ttsScltxxcjScgl.productInnerKey = $("input[name='productInnerKey']").val();
   	 	//产品来源
   	    $scope.ttsScltxxcjScgl.productSource = $("select[name='productSource']").val();
    	 //检测情况、检测结论
   	    $scope.ttsScltxxcjScgl.mediCheck = $("select[name='mediCheck']").val();
   	    $scope.ttsScltxxcjScgl.mediResult = $("select[name='mediResult']").val();
    	 //删除标记、加入类型标记
    	$scope.ttsScltxxcjScgl.delFlag = $("input[name='delFlag']").val();
    	$scope.ttsScltxxcjScgl.joinFlag = $("input[name='joinFlag']").val();
        $scope.ttsScltxxcjScgl.tempEnId = entity_id;
        $http({
            //产品批次登记
        	url:"/sofn-tts-web/ttsScltxxcjScgl/addCppcdj",
            data:$scope.ttsScltxxcjScgl,
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' },
                token : window.localStorage.getItem("token")
            }
        }).success(function(data){
            if(data.httpCode=="200"){
                jBox.tip("保存成功", 'info');
                $("#regForm").form("reset");
                $state.go("index.content.tts/ttsScltxxcjScgl/xmpcdj");
            }else{
                jBox.tip("保存失败", 'info');
            }
        }).error(function(){
            jBox.tip("服务器出错！", 'info');
        })
    }
    /**
     * 根据行业类型显示不同的菜单栏
     */
    $(function (){
            $("#plant").hide();
            $("#pasturage").hide();
            $("#water").hide();
            // $scope.ttsScltxxcjScgl.mediResult= '合格';
            $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getIndustry",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                id:entityIndustry
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(d){
                var length = Object.keys(d).length;
                if(length > 0){
                    for(var key in d){
                        if(0 == key){
                            var data = d[key];
                            var id = data.id;
                            $("#plant").show();
                            // getUnits(id,"units");
                        }
                        if(1 == key){
                            var data = d[key];
                            var id = data.id;
                            $("#pasturage").show();
                            getUnits(id,"punits");
                        }
                        if(2 == key){
                            var data = d[key];
                            var id = data.id;
                            $("#water").show();
                            // getUnits(id,"wunits");
                        }
                    }
                }
            }
        });
    });
    /**
     * 根据行业类型初始化单位下拉框
     */
   function getUnits(industry,units) {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getUnits",
            type:'POST',//POST方式
            dataType:'text',//返回json类型
            data:{
                id : industry
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data,status){
                var d=eval(data);//解析
                $(d).each(function(index,entity){
                    $("#"+units).append($('<option value="'+entity['id']+'">'+entity['dictName']+'</option>'));//后台数据加到下拉框
                });
            }
        });
    }
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
                    params.entity_id = entity_id;
                    params.industry = "02";
                    //params.tel =  $("input[name='in_tel']").val();
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
                         "onclick=selectProduct('"+row.PRODUCT_CODE+"','"+row.NAME+"','"+row.INDUSTRY+"','"+row.TYPE_NAME+"')>" +
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
    	$("input[name='productId']").val(id);
    	$("input[name='productName']").val(name);
    	$("input[name='productIndustry']").val(industry);
    	$("input[name='productSort']").val(product_sort);
        $("#product_info").modal('hide');
    }
    //解析行业类型
    var isType = {};
    $http({
        method:'POST',
        url:'/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo',
        header:{
            token : window.localStorage.getItem("token")
        }
    }).success(function(data){
        if(data.list != null){
            for(var i in data.list){
                isType[data.list[i].dictValue]=data.list[i].dictName;
            }
        }
    })
   /**
    * 选中基地信息
    */
    $("#search_btbaseName").click(function(){
       $("#baseList").dataTable().api().ajax.reload();
    });
    $scope.findBase = function(){
    	//产品信息框展示
    	$("#base_info").modal('show');
    	//清理数据
       var tableName = $('#baseList');
       if($.fn.dataTable.isDataTable(tableName)){
    	       tableName.DataTable().clear();
    	       tableName.DataTable().destroy();
        }
    	 //加载数据
        $("#baseList").dataTable({
            ajax : {
            	url : "/sofn-tts-web/ttsScltxxcjBase/getPageInfo",
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
                    params.id = entity_id;
                    params.status = "Y";
                    params.name = $("#in_base_name").val();
                    //params.tel =  $("input[name='in_tel']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
            	 {
                    data : "NUM",
                    title : "序号"
                },
                {
                    data : "NAME",
                    title : "基地名称"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                    	// 数据操作选择项
                    	 return "<button class='btn btn-primary btn-xs'" +
                         "onclick=selectBase('"+row.ID+"','"+row.NAME+"')>" +
                         "选定基地</button>";
                    }
                },
            ]
        });
    };
    
    
    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectBase = function(id,name){
    	$("input[name='harvestBaseid']").val(id);
    	$("input[name='harvestBasename']").val(name);
        $("#base_info").modal('hide');
    }
    
})