/**
 * Created by Administrator on 2016/11/10.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("trace",function($scope,$http,$state) {
    //获取用户信息
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if(userdata){
        entity_id = userdata.entityIdcode;
    }
    //批次查询先隐藏
    $("#downPc").hide();
    $("#basePc").hide();
    $("#sourcePc").hide();
    //采购先隐藏
    $("#cgup").hide();
    $("#cgbase").hide();
    $("#cgdown").hide();
    //销售先隐藏
    $("#xsup").hide();
    $("#xsbase").hide();
    $("#xsdown").hide();
    //点击查询事件
    $("#ToCheck").click(function () {
        //批次查询先隐藏
        $("#downPc").hide();
        $("#basePc").hide();
        $("#sourcePc").hide();
        //采购先隐藏
        $("#cgup").hide();
        $("#cgbase").hide();
        $("#cgdown").hide();
        //销售先隐藏
        $("#xsup").hide();
        $("#xsbase").hide();
        $("#xsdown").hide();
        //判断输入的码段为追溯码还是批次码
        var zsm = $("#zsm").val();
        var type = zsm.substr(16,1);

        //追溯码
        if (type == "3") {
            queryByTrace(zsm);
        }
        //批次码
        if (type == "2") {
            //本级追溯
            getBasePc(zsm);
            //下游追溯
            downPc();
        }
        if(zsm == "" || zsm == null){
            jBox.tip("批次码或追溯码不能为空！", 'info');
            return;
        }else if(type != "2" && type != "3"){
            jBox.tip("批次码或追溯码格式错误！", 'info');
            return;
        }
    });

    /**
     * 追溯码查询
     */
    window.queryByTrace=function (zsm) {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjCggl/zsmIsCgOrXs",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                zsm:zsm,
                entity_id:entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(d){
                var result = d.data;
                //进行采购追溯
                if(result > 0){
                    $("#cgup").show();
                    $("#cgbase").show();
                    $("#cgdown").show();
                    //
                    $("#xsup").hide();
                    $("#xsbase").hide();
                    $("#xsdown").hide();
                    //追溯码查询展示
                    cgup(zsm);
                    cgbase(zsm);
                    cgdown();
                } else {
                    isXsZs(zsm);
                }
            }
        });
    }
    //判断是否存在销售追溯
    window.isXsZs= function(zsm) {
        $.ajax({
            url:"/sofn-tts-web/zsm/existsTrace",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                zsm:zsm,
                entityId:entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(d){
                var result = d.data;
                //进行采购追溯
                if(result > 0){
                    $("#xsup").show();
                    $("#xsbase").show();
                    $("#xsdown").show();
                    xsup();
                    xsbase();
                    //下游追溯码
                    xsdown(zsm);
                }else{
                    jBox.tip("追溯码不存在！", 'info');
                }
            }
        });
    }
    /**
     * 分页展示数据
     */
    window.cgdown= function() {
        //bootstrap表格
        var tableName = $('#zs_grid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#zs_grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/zsm/getDownZsm",
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
                    params.fromzsm =  $("input[name='zsm']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "CUSTOMER_ENTITY_IDCODE",
                    title: "企业身份码"
                },
                {
                    data: "NAME",
                    title: "企业名称"
                },
                {
                    data: "USER_NAME",
                    title: "联系人"
                },
                {
                    data: "PHONE",
                    title: "联系电话"
                },
                {
                    data: "ADDRESS",
                    title: "地址"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "数量"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                }
            ]
        });

    };


    /**
     * 获取本级追溯信息
     */
    window.cgbase=function(zsm){
        $.ajax({
            url:"/sofn-tts-web/zsm/getBaseZsm",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                zsm:zsm
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.base;
                //客户信息
                var name = vo.name;
                var uname = vo.userName;
                var phone = vo.phone;
                var address = vo.address;
                var entityIdcode = vo.entityIdcode;
                var userIdcode = vo.userIdcode;
                $("#zs_ztmc").html(name);
                $("#zs_lxdh").html(phone);
                $("#zs_lxr").html(uname);
                $("#zs_ztdz").html(address);
                $("#zs_entityIdcode").html(entityIdcode);
                $("#zs_userIdcode").html(userIdcode);
                //产品信息
                $("#zs_product_name").html(vo.productName);
                $("#zs_product_sort").html(vo.productSort);
                $("#zs_sl").html(vo.cgAmount);
            }
        });
    }

    /**
     * 获取上游追溯信息
     */
    window.cgup = function(zsm){
        $.ajax({
            url:"/sofn-tts-web/zsm/getUpZsm",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                zsm:zsm
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.up;
                //客户信息
                var name = vo.name;
                var uname = vo.userName;
                var phone = vo.phone;
                var address = vo.address;
                var entityIdcode = vo.entityIdcode;
                var userIdcode = vo.userIdcode;
                $("#u_ztmc").html(name);
                $("#u_lxdh").html(phone);
                $("#u_lxr").html(uname);
                $("#u_ztdz").html(address);
                $("#u_entityIdcode").html(entityIdcode);
                $("#u_userIdcode").html(userIdcode);
                //产品信息
                $("#u_product_name").html(vo.productName);
                $("#u_product_sort").html(vo.productSort);
                $("#u_sl").html(vo.saleAmount);
            }
        });
    }
    /************************销售查询展示************************/

    /**
     * 分页展示上游追溯数据
     */
    window.xsup= function() {
        //bootstrap表格
        var tableName = $('#upgrid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#upgrid").dataTable({
            ajax : {
                url : "/sofn-tts-web/zsm/getXsUpZsm",
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
                    params.zsm =  $("input[name='zsm']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "ENTITY_IDCODE",
                    title: "企业身份码"
                },
                {
                    data: "NAME",
                    title: "企业名称"
                },
                {
                    data: "USER_NAME",
                    title: "联系人"
                },
                {
                    data: "PHONE",
                    title: "联系电话"
                },
                {
                    data: "ADDRESS",
                    title: "地址"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "数量"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                }
            ]
        });
    };

    /**
     * 分页展示本级追溯数据
     */
    window.xsbase= function() {
        //bootstrap表格
        var tableName = $('#basegrid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#basegrid").dataTable({
            ajax : {
                url : "/sofn-tts-web/zsm/getXsbaseZsm",
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
                    params.tozsm =  $("input[name='zsm']").val();
                    params.entityId = entity_id;
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "ENTITY_IDCODE",
                    title: "企业身份码"
                },
                {
                    data: "NAME",
                    title: "企业名称"
                },
                {
                    data: "USER_NAME",
                    title: "联系人"
                },
                {
                    data: "PHONE",
                    title: "联系电话"
                },
                {
                    data: "ADDRESS",
                    title: "地址"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "数量"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                }
            ]
        });

    };

    /**
     * 获取下游追溯信息
     */
    window.xsdown = function(zsm){
        $.ajax({
            url:"/sofn-tts-web/zsm/getBaseZsm",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                zsm:zsm
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.base;
                    //客户信息
                    var name = vo.name;
                    var uname = vo.userName;
                    var phone = vo.phone;
                    var address = vo.address;
                    var entityIdcode = vo.entityIdcode;
                    var userIdcode = vo.userIdcode;
                    $("#d_ztmc").html(name);
                    $("#d_lxdh").html(phone);
                    $("#d_lxr").html(uname);
                    $("#d_ztdz").html(address);
                    $("#d_entityIdcode").html(entityIdcode);
                    $("#d_userIdcode").html(userIdcode);
                    //产品信息
                    $("#d_product_name").html(vo.productName);
                    $("#d_product_sort").html(vo.productSort);
                    $("#d_sl").html(vo.cgAmount);
                }
        });
    }
    //批次查询部分
    /**
     * 分页展示数据
     */
    window.getUpPc= function(fromzsm,status,billStratrus) {
        //bootstrap表格
        var tableName = $('#up_grid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#up_grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/pc/getTopForPc",
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
                    params.pc =  $("input[name='zsm']").val();
                    params.status =  status;
                    params.billStatus =  billStratrus;
                    params.fromzsm=  fromzsm;
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "ENTITY_IDCODE",
                    title: "企业身份码"
                },
                {
                    data: "NAME",
                    title: "企业名称"
                },
                {
                    data: "USERNAME",
                    title: "联系人"
                },
                {
                    data: "PHONE",
                    title: "联系电话"
                },
                {
                    data: "ADDRESS",
                    title: "地址"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "数量"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                }
            ]
        });

    };


    /**
     * 获取本级追溯信息
     */
    window.getBasePc=function(zsm){
        $.ajax({
            url:"/sofn-tts-web/pc/getBaseForPc",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                pc:zsm,
                entityId:entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.base;
                if(vo==null){
                    jBox.tip("批次码不存在！", 'info');
                }else{
                    $("#hchdiv").hide();
                    $("#downPc").show();
                    $("#basePc").show();
                    $("#sourcePc").show();
                    //客户信息
                    var name = vo.NAME;
                    var uname = vo.USERNAME;
                    var phone = vo.PHONE;
                    var address = vo.ADDRESS;
                    var entityIdcode = vo.ENTITY_IDCODE;
                    var userIdcode = vo.USER_IDCODE;
                    $("#b_ztmc").html(name);
                    $("#b_lxdh").html(phone);
                    $("#b_lxr").html(uname);
                    $("#b_ztdz").html(address);
                    $("#b_entityIdcode").html(entityIdcode);
                    $("#b_userIdcode").html(userIdcode);
                    //产品信息
                    $("#b_product_name").html(vo.PRODUCT_NAME);
                    $("#b_product_sort").html(vo.PRODUCT_SORT);
                    $("#b_sl").html(vo.PRODUCT_AMOUNT);
                    //
                    $("#pc").val(vo.FROMZSM);
                    $("#status").val(vo.STATUS);
                    $("#billstatus").val(vo.BILL_STRATRUS);
                    //
                    getUpPc(vo.FROMZSM,vo.STATUS,vo.BILL_STRATRUS);
                    hchPc(vo.STATUS);
                }
            }
        });
    }
    /**
     * 下游分页展示数据
     */
    window.downPc= function() {
        //bootstrap表格
        var tableName = $('#grid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#grid").dataTable({
            ajax : {
                url : "/sofn-tts-web/pc/getDownForPc",
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
                    params.pc =  $("input[name='zsm']").val();
                    params.entityId = entity_id;
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    data: "NUM",
                    title: "序号"
                },
                {
                    data: "CUSTOMER_ENTITY_ID_CODE",
                    title: "企业身份码"
                },
                {
                    data: "NAME",
                    title: "企业名称"
                },
                {
                    data: "USER_NAME",
                    title: "联系人"
                },
                {
                    data: "PHONE",
                    title: "联系电话"
                },
                {
                    data: "ADDRESS",
                    title: "地址"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "数量"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                }
            ]
        });
    };
    /**
     * 合成后信息
     */
    window.hchPc= function(status) {
        var status = status;
        //如果产品已被合成
        if(parseInt(status) != 4){
            return;
        }
        $("#hchdiv").show();
        $.ajax({
            url:"/sofn-tts-web/pc/getHclx",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                pc:$("#zsm").val()
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.data;
                //客户信息
                var name = vo.NAME;
                var uname = vo.USERNAME;
                var phone = vo.PHONE;
                var address = vo.ADDRESS;
                var entityIdcode = vo.ENTITY_IDCODE;
                var productPcNew = vo.PRODUCT_PC_NEW;
                $("#h_ztmc").html(name);
                $("#h_lxdh").html(phone);
                $("#h_lxr").html(uname);
                $("#h_ztdz").html(address);
                $("#h_entityIdcode").html(entityIdcode);
                $("#h_product_pc_new").html(productPcNew);
                //产品信息
                $("#h_product_name").html(vo.PRODUCT_NAME);
                $("#h_product_sort").html(vo.PRODUCT_SORT);
                $("#h_sl").html(vo.PRODUCT_AMOUNT);
            }
        });
    };
    //ToClean 清空查询条件
    $("#ToClean").click(function(){
        $("#zsm").val("");
    });
});