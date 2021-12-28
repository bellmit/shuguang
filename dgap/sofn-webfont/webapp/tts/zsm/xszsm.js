/**
 * Created by Administrator on 2016/11/9.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("xszsm",function($scope,$http,$state) {
    $("#ToCheck").click(function () {
        var zsm = $("#zsm").val();

        upZsm();
        baseZsm();
        //下游追溯码
        getBaseZsm(zsm);
    });
    /**
     * 分页展示上游追溯数据
     */
    window.upZsm= function() {
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
     * 分页展示本级追溯数据
     */
    window.baseZsm= function() {
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
     * 获取下游追溯信息
     */
    window.getBaseZsm=function(zsm){
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
})