var myapp=angular.module("myapp",[]);
myapp.controller("slaughterAdd",function($scope,$http,$state){
    $("#ToChange").change(function(){
        $("#befor_grid").dataTable().api().ajax.reload();
        $("#after_grid").dataTable().api().ajax.reload();
    });
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if(userdata){
        entity_id = userdata.id;
        entityIndustry = userdata.entityIndustry;
    }
   /**
     * 获取产品分类列表
     */
    $(function () {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getslaughterProType",
            type:'POST',//POST方式
            dataType:'text',//返回json类型
            data:{
                userId : entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data,status){
                var d=eval(data);//解析
                $(d).each(function(index,entity){
                    $("#ToChange").append($('<option value="'+entity['productId']+'">'+entity['productName']+'</option>'));//后台数据加到下拉框
                });
            }
        });
    })
    initProduct();
    /**
     * 分页展示数据
     */
        //bootstrap表格
        $("#befor_grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            //sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjScgl/getslaughterProduct",
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
                    //分页条件查询
                    params.productId =  $("select[name='productName']").val();
                    params.userId = entity_id;
                },
                type:"post"
            },
            columns : [
                {
                    data : "NUM",
                    title : "序号"
                },
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
                    data: "STORE_COUNT",
                    title : "库存数量"
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
                    title: "产品批次码",
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
                    data: "HARVEST_BASENAME",
                    title: "出产地",
                    render : function(data, type, row) {
                        var billstatus = row.BILL_STRATRUS;
                        if(billstatus == "1"){
                            return "<a class='btn btn-primary btn-xs'" +
                                "onclick=findCphc('"+row.ID+"')>查看详细</a>";
                        }
                        return data;
                    }
                },
                {
                    data : "ID",
                    title : "屠宰数量",
                    render : function(data, type, row) {
                        // return '<input id=\"'+data+'\" value="0" type="text" class="form-control use-level" placeholder="销售数量" style="width:80px;height:30px" data-options="required:true,missingMessage:\'用量不能为空且必须为数字\',validType:[\'number\']" >';
                         return '<input id=\"'+data+'\" placeholder="请输入数量......." style="outline:none;border:none;text-align:left;width:100px;margin:0px;height:inherit" data-options="required:true,missingMessage:\'用量不能为空且必须为数字\',validType:[\'number\']"/>';
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
                    var d=eval(data);//解析
                    $(d).each(function(index,entity){
                        var afterRows = $('#after_grid').DataTable().rows().data();
                            for(var i=0;i<afterRows.length;i++){
                                $("#"+afterRows[i].productCode).append($('<option value="'+entity['id']+'">'+entity['dictName']+'</option>'));//后台数据加到下拉框
                            }
                    });
                }
            });
    }
    //屠宰后产品模态框
function initProduct(){
        var tableName = $('#after_grid');
        if($.fn.dataTable.isDataTable(tableName)){
               tableName.DataTable().clear();
               tableName.DataTable().destroy();
         }
        //bootstrap表格
        $("#after_grid").dataTable({
            processing : true, // 加载时提示
            serverSide : true, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            bAutoWidth:true,
            //sScrollY : "auto",
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjProduct/getSlaProductPageInfo",
                dataSrc:function(json){
                    $scope.targe="1";
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    getUnits();
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    params.productId =  $("select[name='productName']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data : "reservedField2",
                    title : "序号"
                },
                // {
                //     data: "productCode",
                //     title: "产品ID",
                //     visible : false
                // },
                {
                    data: "parentId",
                    title: "产品种类ID",
                    visible : false
                },
                {
                    data: "parentName",
                    title: "产品种类"
                },
                {
                    data: "name",
                    title: "产品名称"
                },
                {
                    data : "id",
                    title : "产品数量",
                    render : function(data, type, row) {
                        return '<input id=\"'+data+'\" placeholder="请输入数量......." style="outline:none;border:none;text-align:left;width:100px;margin:0px;height:inherit"/>';
                    }
                },
                {
                    data: "productCode",
                    title: "单位",
                    render : function(data, type, row) {
                       return '<select  class="CheckChange" id=\"'+data+'\">' +
                                '<option value="">单位</option>' +
                                '</select>';
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
    }
    //查看合成产品的详细基地和详细批次
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
                    data: "MEDI_CHECK",
                    title: "质检情况"
                },
                {
                    data: "MEDI_RESULT",
                    title: "质检结论"
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量"
                },  {
                    data: "PRODUCT_PC",
                    title: "合成前产品批次码",
                    render : function(data,type,row){
                        var source = row.PRODUCT_SOURCE;
                        if(source=="采购"){
                            return "无";
                        }else if(source!="采购" && null!=data && data.length>18){
                            var str = data.substring(0,18)+'...';
                            return '<a title="'+data+'">'+str+'</a>';}
                        return data;
                    }
                }, {
                    data: "FROMZSM",
                    title: "来源追溯码"
                },
                {
                    data: "HARVEST_BASENAME",
                    title: "收获基地名称"
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "来源标识"
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
     * 去除参数中的null
     */
    function ClearNullArr(arr){
    for(var i=0,len=arr.length;i<len;i++){
        if(!arr[i] == undefined){
            arr.splice(i,1);
            len--;
            i--;
            }
        }
        return arr;
    }

    $scope.subSlaughter = function(){

        $("#tzbody").html("");
        //清理数据
        var tableName = $('#slaughterModal');
        if($.fn.dataTable.isDataTable(tableName)){
               tableName.DataTable().clear();
               tableName.DataTable().destroy();
         }
         var arows = $('#befor_grid').DataTable().rows().data();
         var myArray=new Array();
         for( i=0;i<arows.length;i++){
             var acount = $("#"+arows[i].ID).val();
             if(acount != ""){
                 myArray.push(acount);
             }else{
                 continue;
             }
         };
        if(myArray.length == 0){
            jBox.tip("请正确填写屠宰前产品的数量！", 'info');
            return;
        };
         //确认数据
         var rows = $('#after_grid').DataTable().rows().data();
         var tablehtml = $("<tbody>");
         for( i=0;i<rows.length;i++){
            var count = $("#"+rows[i].id).val();
            var unit = $("#"+rows[i].productCode).find("option:selected").text();
            var unitValue = $("#"+rows[i].productCode).val();
            if(count == ""){
             continue;
            }
            if(unitValue == ""){
                jBox.tip("请选择单位！", 'info');
                return;
            }
            var tr =  $("<tr>").appendTo(tablehtml);
            var td_cpnm = $("<td>").html(rows[i].name).appendTo(tr);
            var td_cpsl = $("<td>").html(count).appendTo(tr);
            var td_cpdw = $("<td>").html(unit).appendTo(tr);
         }
         $("#tzbody").html(tablehtml.html());
        //表格行数
        var trows = $('#slaughter_grid tr').length;
        if(trows == 1){
            jBox.tip("请正确填写屠宰后产品的数量！", 'info');
            return;
        }
         //产品信息框展示
        $("#slaughterModal").modal('show');
    };
    //提交屠宰后产品信息
    $scope.addslaughter = function(){
        var afterRows = $('#after_grid').DataTable().rows().data();
        var beforRows = $('#befor_grid').DataTable().rows().data();
        //整理存入库存的参数
        var bparams = [];
        var aparams = [];
        if(beforRows.length>0){
            for(var i=0;i<beforRows.length;i++){
                var count = $("#"+beforRows[i].ID).val();
                //判断数据是否为数字
                if(isNaN(count)){
                    // $.messager.alert("提示","数量必须为数字！")
                    jBox.tip("数量必须为数字！", 'info');
                    return;
                };
                //判断屠宰量是否大于库存量
                if(parseInt(count)>parseInt(beforRows[i].STORE_COUNT)){
                    // $.messager.alert("提示","屠宰数量不能大于库存量")
                    jBox.tip("屠宰数量不能大于库存量", 'info');
                    return;
                };
                if (count != "") {
                    bparams[i] = {
                        slaughterAmount : $("#"+beforRows[i].ID).val(),
                        productPc : beforRows[i].PRODUCT_PC,
                        id : beforRows[i].ID,
                        name : beforRows[i].PRODUCT_NAME
                    }
                };
            }
            // var productTypeName =  $("select[name='productName']").val();
            for(var i=0;i<afterRows.length;i++){
                var proAmount = $("#"+afterRows[i].id).val();
                //判断数据是否为数字
                if(isNaN(proAmount)){
                    // $.messager.alert("提示","数量必须为数字！");
                    jBox.tip("数量必须为数字！", 'info');
                    return;
                };
                if (proAmount != "") {
                    aparams[i] = {
                        productId : afterRows[i].productCode,
                        productSort : afterRows[i].parentName,
                        productName : afterRows[i].name,
                        productAmount : proAmount,
                        harvestUnit : $("#"+afterRows[i].productCode).val()
                    }
                 }
            }
        };
        //循环过滤数组中为null的元素
        for(var i=0;i<aparams.length;i++){
            var afterParams = aparams.filter(function(e){
                return e != null;
            });
        };
        for(var i=0;i<bparams.length;i++){
            var beforeParams = bparams.filter(function(e){
                return e != null;
            });
        };
        //对字符串进行stringify
        ajsonStr = JSON.stringify(afterParams);
        jsonStr = JSON.stringify(beforeParams);
        var userId = $.parseJSON(window.localStorage.getItem("entityInfo")).data.id;
        //保存屠宰记录
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjSlaRecord/addTtsScltxxcjSlaRecord",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                jsonStr:jsonStr,
                ajsonStr:ajsonStr,
                userId:userId
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                if(data.httpCode == "200"){
                    jBox.tip("添加数据成功！", 'info');
                    $("#slaughterModal").modal("hide");//隐藏
                    $("#befor_grid").dataTable().api().ajax.reload();
                    $("#after_grid").dataTable().api().ajax.reload();
                }else{
                    jBox.tip("添加数据失败！", 'info');
                    $("#slaughterModal").modal("hide");//隐藏
                }
            }
        });
       /* $.post("/sofn-tts-web/ttsScltxxcjSlaRecord/addTtsScltxxcjSlaRecord",{jsonStr:jsonStr,ajsonStr:ajsonStr,userId:userId},
            function(data){
                if(data.httpCode == "200"){
                    jBox.tip("添加数据成功！", 'info');
                    $("#slaughterModal").modal("hide");//隐藏
                    $("#befor_grid").dataTable().api().ajax.reload();
                    $("#after_grid").dataTable().api().ajax.reload();
                }else{
                    jBox.tip("添加数据失败！请查看是否填写了屠宰数量", 'info');
                    $("#slaughterModal").modal("hide");//隐藏
                    $("#befor_grid").dataTable().api().ajax.reload();
                    $("#after_grid").dataTable().api().ajax.reload();
                }
            },"json");*/
    };
})