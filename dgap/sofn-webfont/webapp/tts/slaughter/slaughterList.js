var myapp = angular.module("myapp", []);
myapp.controller("slaughterList", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if (userdata) {
        entity_id = userdata.id;
    }
    //ToClean 清空查询条件
    $("#ToClean").click(function() {
        $("input[name='productName']").val("");
        $("select[name='status']").val(""); //状态
        $("input[name='startDate']").val(""); //开始日期
        $("input[name='endDate']").val(""); //结束日期
        $("input[name='keyWord']").val(""); //关键字
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjSlaStor/getSlaughterPageInfo",
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
                    // params.productSort = $("select[name='productType']").val();//产品种类
                    // params.productName =  $("select[name='productName']").val();//产品名称
                    params.productName = $("input[name='productName']").val();
                    params.status = $("select[name='status']").val(); //状态
                    params.shrq_q = $("input[name='startDate']").val(); //开始日期
                    params.shrq_h = $("input[name='endDate']").val(); //结束日期
                    params.keyWord = $("input[name='keyWord']").val(); //关键字
                    // params.sortFlag = "1";
                    params.userId = entity_id;
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
                    data: "ID",
                    title: "ID",
                    visible: false
                }, {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "PRODUCT_AMOUNT",
                    title: "初始数量"
                }, {
                    data: "STORE_COUNT",
                    title: "库存数量"
                }, {
                    data: "HARVEST_UNIT",
                    title: "单位"
                }, {
                    data: "PRODUCT_PC",
                    title: "产品批次码"
                }, {
                    data: "HARVEST_TIME",
                    title: "入库时间",
                    render: function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
                },
                {
                    data: "STATUS",
                    title: "当前状态",
                    render : function(data, type, row) { // 模板化列显示内容
                        if(row.STATUS=="2"){
                            return data = '未卖完';
                        }else if(row.STATUS=="1"){
                            return data = '未卖出';
                        }else if(row.STATUS=="3"){
                            return data = '已卖完';
                        }else if (data == "4") {
                            return "已合成";
                        }
                    }
                },
                {
                    data: "INSIDE_TRACE_CODE",
                    title: "操作",
                    render: function(data, type, row) {
                        // 数据操作选择项
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="lookView(\'' + row.ID + '\')">查看</a>'
                        return look_a;
                    }
                }
            ]
        });
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });
    /**
     * 获取产品分类列表
     */
    $(function() {
            $.ajax({
                url: "/sofn-tts-web/ttsScltxxcjScgl/getslaughterProType",
                type: 'POST', //POST方式
                dataType: 'json', //返回json类型
                header: {
                    token: window.localStorage.getItem("token")
                },
                success: function(data, status) {
                    var d = eval(data); //解析
                    $(d).each(function(index, entity) {
                        $("#proType").append($('<option value="' + entity['productName'] + '">' + entity['productName'] + '</option>')); //后台数据加到下拉框
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
         * 跳转到查看详情页面
         */
    window.lookView = function(data) {
        //屠宰记录信息框展示
        $("#record").modal('show');
        var tableName = $('#recordList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#recordList").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjSlaRecord/findById",
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
                    params.id = data;
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                data: "ROWNUM",
                title: "序号"
            }, {
                data: "NAME",
                title: "屠宰物名称"
            }, {
                data: "PRODUCT_PC",
                title: "屠宰物批次号"
            }, {
                data: "SLAUGHTER_AMOUNT",
                title: "屠宰数量"
            }, {
                data: "HARVEST_UNIT",
                title: "单位"
            }, {
                data: "HARVEST_BASENAME",
                title: "收获基地"
            }, {
                data: "SLAUGHTER_TIME",
                title: "屠宰时间",
                render: function(data, type, row) {
                    return new Date(data).format('yyyy-MM-dd');
                }
            }]
        });
    };

})
