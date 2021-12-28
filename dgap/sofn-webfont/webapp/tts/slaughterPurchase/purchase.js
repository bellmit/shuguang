/**
 * Created by Administrator on 2016/11/1.
 */
var myapp=angular.module("myapp",[]);
myapp.controller("purchase",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
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
                url : "/sofn-tts-web/ttsScltxxcjSlaPurchase/getPageInfo",
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
                },
                type:"post"
            },
            columns : [
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
                    title: "采购数量",
                    render : function(data, type, row) {
                    return data + 'kg';
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
                        var xsqr_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="findXsxq(\''+data+'\')">查看详细</a>';
                        return xsqr_a;
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

    });




    /**
     * 销售详情信息
     */
    window.findXsxq = function(id) {
        //产品信息框展示
        $("#xsxq_info").modal('show');
        $.post("/sofn-tts-web/ttsScltxxcjSlaSale/getDetailsById",{
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
            $("#xsid").val(id);//当前查看数据的id 不显示
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

        });
    }


});