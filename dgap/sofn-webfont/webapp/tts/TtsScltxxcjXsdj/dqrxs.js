/**
 * Created by Administrator on 2016/11/1.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("dqrxs",function($scope,$http,$state){
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
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjXsdj/getPageInfo",
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
                    params.confirmState = "N";
                    params.entity_id = entity_id;
                    params.productName = $("input[name='in_productName']").val();
                    params.shrq_q = $("input[name='startDate']").val();
                    params.shrq_h = $("input[name='endDate']").val();
                },
                header:{
                    token : window.localStorage.getItem("token")
                },
                type:"post"
            },
            columns : [
                {
                    title: "序号",
                    data: "NUM"
                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                },
                {
                    data: "PRODUCT_SORT",
                    title: "分类"
                },
                {
                    data: "PRODUCT_XSPC",
                    title: "销售批次",
                    render: function(data, type, row) {
                        return "<a class='btn btn-primary btn-xs'" +
                            "onclick=findXsmc('"+data+"')>"+data+"</a>";
                    }

                },
                {
                    data: "SALE_TIME",
                    title: "销售时间"
                    ,render : function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
                },
                {
                    data: "SALE_AMOUNT",
                    title: "销售数量"
                },
                {
                    data: "HARVEST_UNIT",
                    title: "计量单位"
                },
                {
                    data: "TRACE_CODE",
                    title: "产品追溯码",
                    render : function(data, type, row) {
                        if(null == data || "" == data){
                            return "待确认";
                        }
                        return data;
                    }
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="findXsxq(\''+data+'\')">查看详细</a>';
                        return look_a;
                    }
                },
            ]
        });

    });

    /**
     * 销售明细信息
     */
    window.findXsmc = function(xspc){
        //产品信息框展示
        $("#xsmx_info").modal('show');
        //清理数据
        var tableName = $('#xsmxList');
        if($.fn.dataTable.isDataTable(tableName)){
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#xsmxList").dataTable({
            ajax : {
                url : "/sofn-tts-web/ttsScltxxcjXcdjjl/getPageInfo",
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
                    params.productXspc = xspc;
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
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品分类"
                },
                {
                    data: "PRODUCT_SCGL_ID",
                    title: "产品批次"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "销售数量"
                },
                {
                    data: "SALE_TIME",
                    title: "销售时间"
                    , render: function (data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                    }
                },
                {
                    data: "ZJCHECK",
                    title: "质检情况"
                }
            ]
        });
    };



    /**
     * 销售详情信息
     */
    window.findXsxq = function(id) {
        //产品信息框展示
        $("#xsxq_info").modal('show');
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjXsdj/getXsdjAndCustomerById",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                id : id
            },
            header:{
                token : window.localStorage.getItem("token")
            },
            success:function(data){
                var vo = data.vo;
                //客户信息
                var name = vo.name;
                var uname = vo.userName;
                var phone = vo.phone;
                var address = vo.address;
                var customerEntityIdcode = vo.customerEntityIdcode;
                var customerUserIdcode = vo.customerUserIdcode;
                $("#s_ztmc").html(name);
                $("#s_lxdh").html(phone);
                $("#s_lxr").html(uname);
                $("#s_ztdz").html(address);
                $("#s_customerEntityIdcode").html(customerEntityIdcode);
                $("#s_customerUserIdcode").html(customerUserIdcode);
                //产品信息

                $("#product_name").html(vo.productName);
                $("#product_sort").html(vo.productSort);
                $("#product_check").html("合格");
                //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
                $("#product_pc").html(vo.productXspc);
                $("#xssl").html(vo.saleAmount);
                $("#xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
                var isCirculatesEnd = vo.isCirculatesEnd;
                if("Y" == isCirculatesEnd){
                    $("#cpzsm").html(vo.traceProof);
                } else {
                    $("#cpzsm").html(vo.traceCode);
                }
            }
        });
       /* $.post("/sofn-tts-web/ttsScltxxcjXsdj/getXsdjAndCustomerById",{
            id:id
        },function(data){
            var vo = data.vo;
            //客户信息
            var name = vo.name;
            var uname = vo.userName;
            var phone = vo.phone;
            var address = vo.address;
            var customerEntityIdcode = vo.customerEntityIdcode;
            var customerUserIdcode = vo.customerUserIdcode;
            $("#s_ztmc").html(name);
            $("#s_lxdh").html(phone);
            $("#s_lxr").html(uname);
            $("#s_ztdz").html(address);
            $("#s_customerEntityIdcode").html(customerEntityIdcode);
            $("#s_customerUserIdcode").html(customerUserIdcode);
            //产品信息

            $("#product_name").html(vo.productName);
            $("#product_sort").html(vo.productSort);
            $("#product_check").html("合格");
            //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
            $("#product_pc").html(vo.productXspc);
            $("#xssl").html(vo.saleAmount);
            $("#xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
            var isCirculatesEnd = vo.isCirculatesEnd;
            if("Y" == isCirculatesEnd){
                $("#cpzsm").html(vo.traceProof);
            } else {
                $("#cpzsm").html(vo.traceCode);
            }

        });*/
    }
});