/**
 * Created by Administrator on 2016/11/1.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("cggl",function($scope,$http,$state){
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
                url : "/sofn-tts-web/ttsScltxxcjCggl/getPageInfo",
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
                    params.confirmState = "Y";
                    params.isCirculatesEnd = "N";
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
                    data: "num",
                    title: "序号"
                },
                {
                    data: "userIdcode",
                    title: "主体用户码",
                    visible : false
                },
                {
                    data: "productName",
                    title: "产品名称"
                },
                {
                    data: "productSort",
                    title: "产品分类"
                },
                {
                    data: "saleAmount",
                    title: "采购数量"
                },
                {
                    data: "zjcheck",
                    title: "检测结果",
                    render : function(data, type, row) {
                        return "合格";
                    }

                },
                {
                    data: "saleTime",
                    title: "采购时间",
                    render : function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                    }
                },

                {
                    data: "traceCode",
                    title: "产品追溯码"
                },
                {
                    data : "id",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var userIdCode = row.userIdcode;
                        var xsqr_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="findXsxq(\''+data+'\',\''+userIdCode+'\')">查看详细</a>';
                        return xsqr_a;
                    }
                },
            ]
        });

    });

    $("#ToClean").click(function(){
        $("input[name='in_productId']").val("");
        $("input[name='in_productName']").val("");
        $("#startDate").val("");
        $("#endDate").val("");
    });

    /**
     * 销售详情信息
     */
    window.findXsxq = function(id,userIdCode) {
        //产品信息框展示
        $("#xsxq_info").modal('show');
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjXsdj/getCgqrAndCustomerById",
            type:'POST',//POST方式
            dataType:'json',//返回json类型
            data:{
                id:id,
                userIdCode:userIdCode
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
                var customerEntityIdcode = vo.entityIdcode;
                var customerUserIdcode = vo.userIdcode;
                $("#s_ztmc").html(name);
                $("#s_lxdh").html(phone);
                $("#s_lxr").html(uname);
                $("#s_ztdz").html(address);
                $("#s_EntityIdcode").html(customerEntityIdcode);
                $("#s_UserIdcode").html(customerUserIdcode);
                //产品信息
                $("#xsid").val(id);//当前查看数据的id 不显示
                $("#product_name").html(vo.productName);
                $("#product_sort").html(vo.productSort);
                $("#product_check").html("合格");
                //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
                $("#product_pc").html(vo.harvestUnit);
                $("#xssl").html(vo.saleAmount);
                $("#xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
                var isCirculatesEnd = vo.isCirculatesEnd;
                if("Y" == isCirculatesEnd){
                    $("#cpzsm").html(vo.traceProof);
                } else {
                    $("#cpzsm").html(vo.traceCode);
                }
                $("#ewm").html("");
                $("#ewm").qrcode({
                    width:250,
                    height:250,
                    correctLevel:0,
                    text:vo.traceCode
                });
            }
        });
    }
});