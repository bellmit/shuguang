var myapp=angular.module("myapp",[]).controller("slaughterScglAdd",function($scope,$http,$state){
    //导航对齐
    $("#collapseTwo").attr("class","panel-collapse collapse in");
    $("#collapseTwo li:nth-of-type(2)").css("background-color","#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color","#3695D4").removeClass("Sup_contain_side_nav_item_after");

    //产品批次登记信息
    $scope.addTtsScltxxcjScgl = function(){
        //产品id和产品名称
        $scope.ttsScltxxcjScgl.productName = $("input[name='productName']").val();
        $scope.ttsScltxxcjScgl.productId = $("input[name='productId']").val();
        //收获时间
        $scope.ttsScltxxcjScgl.harvestTime = $("input[name='harvestTime']").val();
        //收获数量
        $scope.ttsScltxxcjScgl.productAmount = $("input[name='productAmount']").val();
         //收获单位
        $scope.ttsScltxxcjScgl.harvestUnit = $("input[name='harvestUnit']").val();
        //收获基地
        $scope.ttsScltxxcjScgl.harvestBaseid = $("input[name='harvestBaseid']").val();
        $scope.ttsScltxxcjScgl.harvestBasename = $("input[name='harvestBasename']").val();
        //内部追溯标识
        $scope.ttsScltxxcjScgl.productInnerKey = $("input[name='productInnerKey']").val();
        //产品来源
        $scope.ttsScltxxcjScgl.productSource = $("input[name='productSource']").val();
         //检测情况、检测结论
        $scope.ttsScltxxcjScgl.mediCheck = $("input[name='mediCheck']").val();
        $scope.ttsScltxxcjScgl.mediResult = $("input[name='mediResult']").val();
         //数据状态和单据状态，后台自动生成
         //$scope.ttsScltxxcjScgl.status = $("input[name='status']").val();
         //$scope.ttsScltxxcjScgl.billStratrus = $("input[name='billStratrus']").val();
         //删除标记和所属行业
         $scope.ttsScltxxcjScgl.delFlag = $("input[name='delFlag']").val();
         $scope.ttsScltxxcjScgl.productIndustry = $("input[name='productIndustry']").val();
        $http({
            //产品批次登记
            url:"/sofn-tts-web/ttsScltxxcjScgl/addCppcdj",
            data:$scope.ttsScltxxcjScgl,
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(data){
            alert("保存成功");
            $state.go("index.content.tts/ttsScltxxcjScgl/list");
        }).error(function(){
            alert("保存失败");
        })
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
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                },
                type:"post"
            },
            columns : [
                {
                    data: "INDUSTRY",
                    title: "所属行业"
                },
                {
                    data: "NAME",
                    title: "名称"
                },
                {
                    data: "TYPE",
                    title: "类型"
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) { 
                        // 数据操作选择项
                         return "<button class='btn btn-primary btn-xs'" +
                         "onclick=selectProduct('"+row.id+"','"+row.NAME+"','"+row.INDUSTRY+"')>" +
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
    window.selectProduct = function(id,name,industry){
        $("input[name='productId']").val(id);
        $("input[name='productName']").val(name);
        $("input[name='productIndustry']").val(industry);
    }
    
    
    
   /**
    * 选中基地信息
    */
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
                url : "/sofn-tts-web/tscltxxcjBaseInfo/getBaseList",
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
    window.selectBase = function(id,name){
        $("input[name='harvestBaseid']").val(id);
        $("input[name='harvestBasename']").val(name);
    }
    
    
})