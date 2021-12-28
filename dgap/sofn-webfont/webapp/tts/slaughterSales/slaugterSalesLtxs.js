/**
 * Created by Administrator on 2016/11/1.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("slaugterSalesLtxs",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            processing: true, // 加载时提示
            serverSide: true, // 分页，是否服务器端处理
            ordering: false,
            searching: false,
            bProcessing: true,
            bAutoWidth: true,
            //sScrollY: "auto",
            dom: '<"top">rt<"bottom"pli><"clear">',
            autoWidth: false,
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjSlaStor/getStorPageInfo",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total;//总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum;//页码
                    json.pageSize = json.data.pageSize;//每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function (params) {
                    params.productType = $("select[name='productType']").val();//产品种类
                    params.productName =  $("input[name='productName']").val();//产品名称
                    params.status =  $("select[name='status']").val();//状态
                    params.startTime =  $("input[name='startTime']").val();//开始日期
                    params.endTime =  $("input[name='endTime']").val();//结束日期
                    params.keyWord =  $("input[name='keyWord']").val();//关键字
                },
                type: "post"
            },
            columns: [
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
                    data: "PRODUCT_SORT",
                    title: "产品种类"
                },
                {
                    data: "HARVEST_TIME",
                    title: "入库时间"
                    ,render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
                },
                {
                    data: "STORE_COUNT",
                    title: "库存数量"
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "已销售",
                    render: function (data, type, row) {
                        var kc = row.STORE_COUNT;
                        var sh = data;
                        return parseFloat(sh-kc).toFixed(2);
                    }
                },
                {
                    data: "PRODUCT_PC",
                    title: "产品批次码"
                },
                {
                    data : "ID",
                    title : "销售数量",
                    render : function(data, type, row) { 
                        // return '<input id=\"'+data+'\" value="0" type="text" class="form-control use-level" placeholder="销售数量" style="width:80px;height:30px" data-options="required:true,missingMessage:\'用量不能为空且必须为数字\',validType:[\'number\']" >';
                         return '<input id=\"'+data+'\" placeholder="请输入数量......." style="outline:none;border:none;text-align:left;width:100px;margin:0px;height:inherit"/>';
                    }
                },
                {
                    data: "HARVEST_UNIT",
                    title: "单位"
                }
            ],
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            sPaginationType: "full_numbers"
        });
        customerList();

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
    /**
     * 初始化加载常客户信息
     */
    function customerList() {
        //传入登录账户 主体用户码，暂时不实现
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjCustomer/getCustomerList",
            data:{
                id:""//$scope.id
            },
            method:"post",
            headers: {
                post: { 'Content-Type': 'application/x-www-form-urlencoded' }
            }
        }).success(function(d){
            var data = d.data;
            var i = 0;
            $(data).each(function(){
                var name = this.name;
                var userName = this.userName;
                var address = this.address;
                var phone = this.phone;
                var customerEntityIdCode = this.customerEntityIdCode;
                var customerUserIdCode = this.customerUserIdCode;
                var id = this.id;
                var a = $("<a>").html(name).click(function(){
                    $("#ztmc").val(name);
                    $("#lxr").val(userName);
                    $("#ztdz").val(address);
                    $("#lxdh").val(phone);
                    $("#customerEntityIdCode").val(customerEntityIdCode);
                    $("#customerUserIdCode").val(customerUserIdCode);
                    $("#customerId").val(id);
                }).appendTo("#kh"+i);
                //
                i++;
            });
        }).error(function(){
            alert("获取常客户信息失败");
        })
    }


    /**
     * 选择客户信息
     */
    $scope.findCustomer = function(){
        //产品信息框展示
        $("#customer_info").modal('show');
        //清理数据
        var tableName = $('#customerList');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#customerList").dataTable({
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
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
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
                }, {
                    data: "ADDRESS",
                    title: "客户地址"
                },
                {
                    data: "PHONE",
                    title: "联系方式"
                },
                {
                    data: "NAME",
                    title: "单位名称"
                }, {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        return "<button class='btn btn-primary btn-xs'" +
                            "onclick=selectCustomer('"+row.USER_NAME+"','"+row.PHONE+"','"+row.NAME+"','"+row.ADDRESS+"','"+row.CUSTOMER_ENTITY_ID_CODE+"','"+row.CUSTOMER_USER_ID_CODE+"','"+data+"')>" +
                            "选择客户</button>";
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
    };


    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectCustomer = function(uname,phone,name,address,eid,uid,id){
        $("#ztmc").val(name);
        $("#lxdh").val(phone);
        $("#lxr").val(uname);
        $("#ztdz").val(address);
        $("#customerEntityIdCode").val(eid);
        $("#customerUserIdCode").val(uid);
        $("#customerId").val(id);
    }

    //确认产品销售后产品模态框
    $scope.subCpxs = function(){
        //customer info
        var ztmc = $("#ztmc").val();
        var lxdh = $("#lxdh").val();
        var lxr = $("#lxr").val();
        var ztdz = $("#ztdz").val();
        var customerEntityIdCode = $("#customerEntityIdCode").val();
        var customerUserIdCode = $("#customerUserIdCode").val();

        //
        $("#s_ztmc").html(ztmc);
        $("#s_lxdh").html(lxdh);
        $("#s_lxr").html(lxr);
        $("#s_ztdz").html(ztdz);
        $("#s_customerEntityIdCode").html(customerEntityIdCode);
        $("#s_customerUserIdCode").html(customerUserIdCode);
        //产品信息框展示
        $("#countersign").modal('show');
        //清空旧数据
        $("#countersignBody").html("");
        //确认数据
        var rows = $('#grid').DataTable().rows().data();
        var tablehtml = $("<tbody>");
        for( i=0;i<rows.length;i++){
            var count = $("#"+rows[i].ID).val();
            var tr =  $("<tr>").appendTo(tablehtml);
            var td_cpmc = $("<td>").html(rows[i].PRODUCT_NAME).appendTo(tr);
            var td_cppc = $("<td>").html(rows[i].PRODUCT_PC).appendTo(tr);
            var td_cpsl = $("<td>").html(count).appendTo(tr);
            var td_cpdw = $("<td>").html(rows[i].HARVEST_UNIT).appendTo(tr);
        }
        $("#countersignBody").html(tablehtml.html());
    };



    /**
     * 产品销售确认按钮点击..
     */
    $scope.toCpxs = function(){
        var xsRows = $('#grid').DataTable().rows().data();
        //封装参入参数
        var bparams = [];
        if(xsRows.length > 0){
            for(var i=0;i<xsRows.length;i++) {
                //获取销售数量
                var saleAmount = $("#" + xsRows[i].ID).val();
                //判断数据是否为数字
                if (isNaN(saleAmount)) {
                    $.messager.alert("提示", "数量必须为数字！")
                    return;
                }
                //库存数量
                var storeCount = xsRows[i].CURRENT_AMOUNT;
                if(parseInt(saleAmount) > parseInt(storeCount)){
                    $.messager.alert("提示", "销售数量不能大于库存数量！")
                    return;
                }
                //如果销售数量大于0的情况，则进行销售确认
                var productName = xsRows[i].PRODUCT_NAME;
                var productSort = xsRows[i].PRODUCT_TYPE;
                var productScglId = xsRows[i].SLAUGHTER_BATCH_NO;
                var productAmount = xsRows[i].INIT_AMOUNT;

                //对数据封装
                if(saleAmount > 0){
                    bparams[i] = {
                        productName:productName,
                        productSort:productSort,
                        productScglId:productScglId,
                        productAmount:productAmount,
                        saleAmount:saleAmount,
                        storeCount:storeCount,
                    }
                }
            }
        }
        var jsonStr = JSON.stringify(bparams);
        var customerId = $("#customerId").val();
        var config = {
            jsonStr:jsonStr,
            customerId:customerId
        };
        $.post("/sofn-tts-web/ttsScltxxcjSlaSale/addXsdjlt",config,
            function(data){
                if(data.httpCode == "200"){
                    // 提示成功
                    $.messager.show({
                        title : "提示信息",
                        msg : "操作成功！",
                        showType : "slide",
                        timeout : 5000
                    });
                    $("#countersign").modal("hide");//隐藏
                    $("#grid").dataTable().api().ajax.reload();
                }else{
                    $.messager.alert("提示","添加数据行失败!");
                }
            },"json");
    }

})
