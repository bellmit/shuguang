var myapp = angular.module("myapp", []);
myapp.controller("cppcgl", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });

    /**
     * 检查开始时间是否大于结束时间
     * @param data
     */
    window.changeDate = function() {
        var start = $("input[name='shrq_q']").val();
        var end = $("input[name='shrq_h']").val();
        if (start != null && start != '' && end != null && end != '') {
            if (start > end) {
                jBox.tip("开始日期不能大于结束日期", 'error');
                $("input[name='shrq_h']").val('');
            }
        }
    }

    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entityIndustry;
    var entity_id = "";
    if (userdata) {
        entity_id = userdata.id;
        entityIndustry = userdata.entityIndustry;
    }
    /**
     *初始化搜索栏单位
     */
    getUnits();

    function getUnits() {
        $.ajax({
            url:"/sofn-tts-web/ttsScltxxcjScgl/getUnits",
            type:'POST',//POST方式
            dataType:'text',//返回text类型
            data: {
                id: entityIndustry
            },
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function(data, status) {
                // var d=eval(data);//解析
                var d = JSON.parse(data); //解析
                $(d).each(function(index, entity) {
                    $("#units").append($('<option value="' + entity['dictName'] + '">' + entity['dictName'] + '</option>')); //后台数据加到下拉框
                });
            }
        });
    }
    /**
     * 获取产品分类列表
     */
    $(function() {
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjProduct/getProType",
            type: 'POST', //POST方式
            dataType: 'text', //返回json类型
            data: {
                id: entity_id
            },
            success: function(data, status) {
                // var d=eval(data);//解析
                var d = JSON.parse(data); //解析
                $(d).each(function(index, entity) {
                    $("#ptype").append($('<option value="' + entity['typeName'] + '">' + entity['typeName'] + '</option>')); //后台数据加到下拉框
                });
            }
        });
    })

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjScgl/getCpdjgl",
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
                    // params.productId = $("input[name='in_productId']").val();
                    params.status = $("select[name='in_status']").val();
                    params.shrq_q = $("input[name='startDate']").val();
                    params.shrq_h = $("input[name='endDate']").val();
                    params.entity_id = entity_id;
                    params.productSort = $("#ptype").val();
                    params.harvestUnit = $("select[name='in_harvestUnit']").val();
                    params.productName = $("input[name='in_productName']").val();
                },
                type: "post"
            },
            columns: [{
                title: "序号",
                data: "NUM"
            }, {
                data: "PRODUCT_SORT",
                title: "产品种类"
            }, {
                data: "PRODUCT_NAME",
                title: "产品名称"
            }, {
                data: "PRODUCT_AMOUNT",
                title: "收获数量"
            }, {
                data: "STORE_COUNT",
                title: "当前库存"
            }, {
                data: "HARVEST_UNIT",
                title: "计量单位"
            }, {
                data: "HARVEST_BASENAME",
                title: "收获基地",
                render: function(data, type, row) {
                    var billstatus = row.BILL_STRATRUS;
                    if (billstatus == "1") {
                        return "<a class='btn btn-primary btn-xs'" +
                            "onclick=findCphc('" + row.ID + "')>查看详细</a>";
                    }
                    return data;
                }
            }, {
                data: "PRODUCT_SOURCE",
                title: "产品来源"
            }, {
                data: "MEDI_CHECK",
                title: "质检情况"
            }, {
                data: "MEDI_RESULT",
                title: "质检结论"
            }, {
                data: "HARVEST_TIME",
                title: "收获时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
            }, {
                data: "PRODUCT_PC",
                title: "产品批次",
                /*render : function(data, type, row) {
                    var billstatus = row.BILL_STRATRUS;
                    if(billstatus == "1"){
                        return "<a class='btn btn-primary btn-xs'" +
                            "onclick=findCphc('"+row.ID+"')>" + data+"</a>";
                    }
                    return data;
                }*/
                render: function(data, type, row) {
                    var source = row.PRODUCT_SOURCE;
                    var billstatus = row.BILL_STRATRUS;
                    if (null != data && data.length > 12 && billstatus == "1") {
                        var str = data.substring(0, 12) + '...';
                        return "<a class='btn btn-primary btn-xs'" +
                            "onclick=findCphc('"+row.ID+"')>" + str +"</a>";
                    }else if(source != "采购"){
                        var str = data.substring(0, 12) + '...';
                        return '<a title="'+data+'">'+str+'</a>';
                    }else if(source == "采购" && billstatus != "1"){
                        return "无";
                    }
                },
                width: "10%"
            }, {
                data: "STATUS",
                title: "当前状态",
                render: function(data, type, row) {
                    if (data == "1") {
                        return "未卖出";
                    }
                    if (data == "2") {
                        return "未卖完";
                    }
                    if (data == "3") {
                        return "已卖完";
                    }
                    if (data == "4") {
                        return "已合成";
                    }
                    return "未知";
                },
                visible: true
            }, {
                data: "ID",
                title: "操作",
                render: function(data, type, row) {
                    var delete_a = '<a class="color" style="margin-right: 10px;cursor: pointer" onclick="deleteData(\'' + data + '\')">删除</a>';
                    var print_a = '<a class="color" style="margin-right: 10px; cursor: pointer" onclick="lookView(\'' + data + '\')">打印</a>';
                    var xsqr_a = '<a class="color" style="margin-right: 10px; cursor: pointer"  onclick="prodcment_info(\''+data+'\')">查看详细</a>';
                    if (row.STATUS == "1") {
                        return delete_a + print_a+xsqr_a;
                    }
                    return print_a+xsqr_a;
                },
                width: "15%"
            }]
        });

    });
    window.prodcment_info = function (id) {
        $("#product_informition").modal('show');
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjScgl/getproductInfo",
            type: 'POST', //POST方式
            dataType: 'json', //返回json类型
            data: {
                id: id
            },
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function (data) {
                var entityIdcode =data.data.entityIdcode;
                $("#s_EntityIdcode").html(entityIdcode);
                var userIdcode =data.data.userIdcode;
                $("#s_UserIdcode").html(userIdcode);
                var harvestBasename =data.data.harvestBasename;
                $("#s_ztmc").html(harvestBasename);
                var productAmount =data.data.storeCount;
                $("#s_lxr").html(productAmount);
                var  harvestTime = data.data.harvestTime;
                var times =new Date(harvestTime).format('yyyy-MM-dd');
                $("#s_ztdz").html(times);
                var  mediCheck = data.data.mediCheck;
                $("#s_lxdh").html(mediCheck);
                var productName = data.data.productName;
                $("#product_name").html(productName);
                var  productSort = data.data.productSort;
                $("#product_sort").html(productSort);
                var  productPc = data.data.productPc;
                $("#cpzsm4").html(productPc);
                var  mediCheck = data.data.mediCheck;
                $("#xsrq").html(mediCheck);
                var  productSource = data.data.productSource;
                $("#xssl").html(productSource);
                var  productId = data.data.productId;
                $("#product_check").html(productId);
                var  freezeCount = data.data.freezeCount;
                $("#cpzsm1").html(freezeCount);

                var  mediResult = data.data.mediResult;
                $("#cpzsm2").html(mediResult);

                var  harvestUnit = data.data.harvestUnit;
                $("#cpzsm3").html(harvestUnit);

                var  productPc = data.data.productPc;

                var fromzsm = data.data.fromzsm;
                var billing = data.data.billStratrus;
                if(productSource == "采购" && billing != "1"){
                    $("#code").html("追溯码");
                    $("#cpzsm").html(fromzsm);
                }else{
                    $("#code").html("批次码");
                    $("#cpzsm").html(productPc);
                    // $("#xssl").html("合成");
                }
            }

        });
    }

    /**
     * 跳转到查询页面
     */
    window.lookView = function(data) {
        window.localStorage.setItem("tts_ttsScltxxcjScgl_id", data);
        $state.go("index.content.tts/TtsScltxxcjScgl/printEwm");
    }


    window.deleteData = function(data) {
        //选择是否进行删除
        if (confirm('是否进行删除...')) {
            $.ajax({
                url: "/sofn-tts-web/ttsScltxxcjScgl/deleteTtsScltxxcjScgl",
                type: "post",
                dataType: "json",
                data: { id: data },
                success: function(data) {
                    $.jBox.tip("删除成功！");
                    $("#grid").dataTable().api().ajax.reload();
                }
            });
        }
    };



    //modal 产品信息
    $scope.findProduct = function() {
        //产品信息框展示
        $("#product_info").modal('show');
        //清理数据
        var tableName = $('#productList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#productList").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjScgl/getProductByTerm",
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
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    params.entity_id = entity_id;
                    params.typeName = $("#ptype").val();
                },
                type: "post"
            },
            columns: [{
                data: "INDUSTRY",
                title: "所属行业",
                render: function(data, type, row) {
                    return isType[data];
                }
            }, {
                data: "NAME",
                title: "名称"
            }, {
                data: "TYPE",
                title: "类型",
                visible: false
            }, {
                data: "TYPE_NAME",
                title: "类型"
                    /*,
                                        render : function(data,type,row){
                                            return isTypeName[data];
                                        }*/
            }, {
                data: "ID",
                title: "操作",
                render: function(data, type, row) {
                    // 数据操作选择项
                    return "<button class='btn btn-primary btn-xs'" +
                        "onclick=selectProduct('" + row.PRODUCT_CODE + "','" + row.NAME + "','" + row.INDUSTRY + "','" + row.TYPE + "')>" +
                        "选定产品</button>";
                }
            }]
        });
    };

    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectProduct = function(id, name, industry, product_sort) {
        $("input[name='in_productId']").val(id);
        $("input[name='in_productName']").val(name);
    }
    var isType = {};
    $http({
        method: 'POST',
        url: '/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo'
    }).success(function(data) {
        if (data.list != null) {
            for (var i in data.list) {
                isType[data.list[i].dictValue] = data.list[i].dictName;
            }
        }
    })

    window.findCphc = function(hcid) {
        //产品信息框展示
        $("#cphc_info").modal('show');
        //清理数据
        var tableName = $('#cphcList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#cphcList").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjCppchc/getPageInfo",
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
                    params.hcid = hcid;

                },
                type: "post"
            },
            columns: [{
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "MEDI_CHECK",
                    title: "质检情况"
                }, {
                    data: "MEDI_RESULT",
                    title: "质检结论"
                }, {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量"
                },
                /* {
                     data: "PRODUCT_PC",
                     title: "合成前产品批次码"
                 },*/
                {
                    data: "HARVEST_BASENAME",
                    title: "收获基地名称"
                }, {
                    data: "PRODUCT_PC",
                    title: "产品批次码",
                    render : function(data,type,row){
                        var source = row.PRODUCT_SOURCE;
                        if(source=="采购"){
                            return "无";
                        }else if(source!="采购"){
                            return data;
                        }
                    }
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "来源标识"
                }
            ]
        });
    };



    //ToClean 清空查询条件
    $("#ToClean").click(function() {
        $("input[name='in_productId']").val("");
        $("input[name='in_productName']").val("");
        $("select[name='in_status']").val("");
        $("#startDate").val("");
        $("#endDate").val("");
        $("#productSort").val("");
        $("#harvestUnit").val("");
        $("#ptype").val("");
        $("#grid").dataTable().api().ajax.reload();
    });
})
