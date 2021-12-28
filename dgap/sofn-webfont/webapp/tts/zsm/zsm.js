var myapp=angular.module("myapp",[]);
myapp.controller("zsm",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        var zsm = $("#zsm").val();
        getUpZsm(zsm);
        getBaseZsm(zsm);
        //下游追溯码
        downZsm();
    });
    /**
     * 分页展示数据
     */
    window.downZsm= function() {
        //bootstrap表格
        var tableName = $('#grid');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        $("#grid").dataTable({
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
                $("#b_ztmc").html(name);
                $("#b_lxdh").html(phone);
                $("#b_lxr").html(uname);
                $("#b_ztdz").html(address);
                $("#b_entityIdcode").html(entityIdcode);
                $("#b_userIdcode").html(userIdcode);
                //产品信息
                $("#b_product_name").html(vo.productName);
                $("#b_product_sort").html(vo.productSort);
                $("#b_sl").html(vo.cgAmount);
            }
        });
    }

    /**
     * 获取上游追溯信息
     */
    window.getUpZsm = function(zsm){
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

})

