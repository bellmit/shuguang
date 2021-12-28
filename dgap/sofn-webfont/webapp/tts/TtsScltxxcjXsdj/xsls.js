/**
 * Created by Administrator on 2016/10/31.
 */
var myapp = angular.module("myapp", []);
myapp.controller("xsls", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });

    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if (userdata) {
        entity_id = userdata.id;
    }
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjXsdj/getPageInfo",
                dataSrc: function(json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function(params) {
                    params.confirmState = $("#confirmState").val();
                    params.entity_id = entity_id;
                    params.productName = $("input[name='in_productName']").val();
                    params.shrq_q = $("input[name='startDate']").val();
                    params.shrq_h = $("input[name='endDate']").val();
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                title: "序号",
                data: "NUM"
            }, {
                data: "PRODUCT_NAME",
                title: "产品名称"
            }, {
                data: "PRODUCT_SORT",
                title: "分类"
            }, {
                data: "PRODUCT_XSPC",
                title: "销售明细",
                render: function(data, type, row) {
                    return "<a class='btn btn-primary btn-xs'" +
                        "onclick=findXsmc('" + data + "')>查看销售明细</a>";
                }
            }, {
                data: "SALE_TIME",
                title: "销售时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
            }, {
                data: "SALE_AMOUNT",
                title: "销售数量"
            }, {
                data: "HARVEST_UNIT",
                title: "计量单位"
            }, {
                data: "CONFIRM_STATE",
                title: "交易状态",
                render: function(data, type, row) {
                    if (data == "Y") {
                        return "已完成";
                    }
                    if (data == "T") {
                        return "已退货";
                    }
                    return "待确认";
                }
            }, {
                data: "TRACE_CODE",
                title: "产品追溯码",
                render: function(data, type, row) {
                    if (null == data || "" == data) {
                        return "无";
                    }
                    return data.substr(0, 10) + "...";
                }
            }, {
                data: "TRACE_PROOF",
                title: "入市追溯凭证",
                render: function(data, type, row) {
                    if (null == data || "" == data) {
                        return "无";
                    }
                    return data.substr(0, 10) + "...";
                }
            }, {
                data: "ID",
                title: "操作",
                render: function(data, type, row) {
                    // 数据操作选择项
                    var state = row.CONFIRM_STATE;
                    var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="findXsxq(\'' + data + '\',\'' + state + '\')">查看详细</a>';
                    var print_a = '<a style="margin-right: 10px;cursor: pointer" class="color" onclick="rsxsewm(\'' + data + '\')">打印</a>';
                    var is_circulates_end = row.IS_CIRCULATES_END;
                    //直接入市销售，增加打印按钮
                    if ("Y" == is_circulates_end) {
                        return look_a + print_a;
                    }
                    return look_a;
                }
            }, ]
        });

    });



    /**
     * 销售明细信息
     */
    window.findXsmc = function(xspc) {
        //产品信息框展示
        $("#xsmx_info").modal('show');
        //清理数据
        var tableName = $('#xsmxList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#xsmxList").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjXcdjjl/getPageInfo",
                dataSrc: function(json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start = (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    return json.data.list;
                },
                data: function(params) {
                    params.productXspc = xspc;
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                data: "NUM",
                title: "序号"
            }, {
                data: "PRODUCT_NAME",
                title: "产品名称"
            }, {
                data: "PRODUCT_SORT",
                title: "产品分类"
            }, {
                data: "PRODUCT_SCGL_ID",
                title: "产品批次",
                render : function(data,type,row){
                    var source = row.FROMZSM;
                    if(source != "" && source != null){
                        return "无";
                    }else{
                        return data;
                    }
                }
            }, {
                data: "SALE_AMOUNT",
                title: "销售数量"
            }, {
                data: "SALE_TIME",
                title: "销售时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
            }, {
                data: "ZJCHECK",
                title: "质检情况"
            }]
        });
    };

    //ToClean 清空查询条件
    $("#ToClean").click(function() {
        $("input[name='in_productId']").val("");
        $("input[name='in_productName']").val("");
        $("select[name='confirmState']").val("");
        $("#startDate").val("");
        $("#endDate").val("");
    });
    /**
     * 销售详情信息
     */
    window.findXsxq = function(id, state) {
        //产品信息框展示
        if (state != "T") {
            $("#xsxq_info").modal('show');
            $("#detailTitle").html("销售详情");
            $("#xsth").html("追溯码");
            $("#qrcode").show();
            $("#cpzsm").html("");
            $("#ckewm").html("");
            $("#s_ztmc").html("");
            $("#s_lxdh").html("");
            $("#s_lxr").html("");
            $("#s_ztdz").html("");
            $("#s_entitycode").html("");
            $("#s_useridcode").html("");
            $("#product_name").html("");
            $("#product_sort").html("");
            $("#product_pc").html("");
            $("#xssl").html("");
            $("#xsrq").html("");
            $.ajax({
                url: "/sofn-tts-web/ttsScltxxcjXsdj/getXsdjAndCustomerById",
                type: 'POST', //POST方式
                dataType: 'json', //返回json类型
                data: {
                    id: id,
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                success: function(data) {
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
                    $("#s_entitycode").html(customerEntityIdcode);
                    $("#s_useridcode").html(customerUserIdcode);
                    //产品信息
                    $("#product_name").html(vo.productName);
                    $("#product_sort").html(vo.productSort);
                    $("#product_check").html("合格");
                    //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
                    $("#product_pc").html(vo.harvestUnit);
                    $("#xssl").html(vo.saleAmount);
                    $("#xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
                    var isCirculatesEnd = vo.isCirculatesEnd;
                    if ("Y" == isCirculatesEnd) {
                        $("#cpewm").html("");
                        $("#cpewm").html("产品追溯二维码");
                        $("#xsth").html();
                        $("#xsth").html("入市追溯码");
                        $("#ckewm").html("");
                        $("#ckewm").qrcode({
                            width: 250,
                            height: 250,
                            correctLevel: 0,
                            text: vo.traceProof
                        });
                        $("#cpzsm").html(vo.traceProof);
                    } else {
                        $("#cpewm").html("");
                        $("#cpewm").html("产品追溯二维码");
                        $("#xsth").html();
                        $("#xsth").html("追溯码");
                        $("#ckewm").html("");
                        $("#ckewm").qrcode({
                            width: 250,
                            height: 250,
                            correctLevel: 0,
                            text: vo.traceCode
                        });
                        $("#cpzsm").html(vo.traceCode);
                    }
                }
            });
        } else if (state == "T") {
            $("#xsxq_info").modal('show');
            $("#detailTitle").html("退货详情");
            $("#xsth").html("退货原因");
            $("#qrcode").hide();
            $("#cpzsm").html("");
            $("#ckewm").html("");
            $("#s_ztmc").html("");
            $("#s_lxdh").html("");
            $("#s_lxr").html("");
            $("#s_ztdz").html("");
            $("#s_entitycode").html("");
            $("#s_useridcode").html("");
            $("#product_name").html("");
            $("#product_sort").html("");
            $("#product_pc").html("");
            $("#xssl").html("");
            $("#xsrq").html("");
            $.ajax({
                url: "/sofn-tts-web/ttsScltxxcjXsth/queryById",
                type: 'POST', //POST方式
                dataType: 'json', //返回json类型
                data: {
                    id: id
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                success: function(data) {
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
                    $("#s_entitycode").html(customerEntityIdcode);
                    $("#s_useridcode").html(customerUserIdcode);
                    //产品信息
                    $("#product_name").html(vo.productName);
                    $("#product_sort").html(vo.productSort);
                    $("#product_check").html("合格");
                    //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
                    $("#product_pc").html(vo.harvestUnit);
                    $("#xssl").html(vo.saleAmount);
                    $("#xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
                    $("#cpzsm").html(vo.xsthyy);
                }
            });
        }
    }
    /**
     * 打印控件
     */

    $("#myElementId").click(function () {
        $("#xsrsewm_info .print-area").printArea();
    });

    // $("#myElementId").print(/*options*/);
    //     alert("***********");

    /**
     * 入市销售二维码
     */
    window.rsxsewm = function(id) {
        //产品信息框展示
        $("#xsrsewm_info").modal('show');
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjXsdj/getXsdjAndCustomerById",
            type: 'POST', //POST方式
            dataType: 'json', //返回json类型
            data: {
                id: id
            },
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function(data) {
                var vo = data.vo;
                //客户信息
                var name = vo.name;
                var uname = vo.userName;
                var phone = vo.phone;
                var address = vo.address;
                var customerEntityIdcode = vo.customerEntityIdcode;
                var customerUserIdcode = vo.customerUserIdcode;
                $("#e_ztmc").html(name);
                $("#e_lxdh").html(phone);
                $("#e_lxr").html(uname);
                $("#e_ztdz").html(address);
                $("#e_customerEntityIdcode").html(customerEntityIdcode);
                $("#e_customerUserIdcode").html(customerUserIdcode);

                //产品信息
                $("#e_product_name").html(vo.productName);
                $("#e_product_sort").html(vo.productSort);
                $("#e_product_check").html("合格");

                //这里展示的是销售批次，产品来源于多个，无法展示批次，可以到销售明细查看
                $("#e_product_pc").html(vo.harvestUnit);
                $("#e_xssl").html(vo.saleAmount);
                $("#e_xsrq").html(new Date(vo.saleTime).format('yyyy-MM-dd'));
                var isCirculatesEnd = vo.isCirculatesEnd;
                if ("Y" == isCirculatesEnd) {
                    $("#e_cpzsm").html(vo.traceProof);
                } else {
                    $("#e_cpzsm").html(vo.traceCode);
                }
                $("#ewm").html("");
                $("#ewm").qrcode({
                    width: 250,
                    height: 250,
                    correctLevel: 0,
                    text: vo.traceProof
                });
                var img = $('.qrcode_area').find('img'); /// get image element
                var canvas  = $('.qrcode_area').find('canvas');  /// get canvas element
                img[0].src = canvas[0].toDataURL();
                canvas.remove();
                /// update image
            }
        });
    }
});
