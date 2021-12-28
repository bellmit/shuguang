/**
 * Created by Administrator on 2016/11/21.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("pcZsm",function($scope,$http,$state){
    $("#downPc").hide();
    $("#basePc").hide();
    $("#sourcePc").hide();
    $("#ToCheck").click(function(){
        var pc = $("#pc").val();
        getBasePc(pc);
        //下游追溯码
        downPc();
    });
    //浏览器获取登录信息
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if(userdata){
        entity_id = userdata.entityIdcode;
    }
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
                    params.pc =  $("input[name='pc']").val();
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
    window.getBasePc=function(pc){
        $.ajax({
            url:"/sofn-tts-web/pc/getBaseForPc",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                pc:pc,
                entityId:entity_id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.base;
                if(vo==null){
                    $("#downPc").hide();
                    $("#basePc").hide();
                    $("#sourcePc").hide();
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
                    $("#zsm").val(vo.FROMZSM);
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
                    params.pc =  $("input[name='pc']").val();
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
                pc:$("#pc").val()
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
        $("#pc").val("");
    });
})
