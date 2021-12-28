/**
 * Created by Administrator on 2016/10/27.
 */
var myapp = angular.module("myapp", []);
myapp.controller("rsxs", function($scope, $http, $state) {
    $("#ToCheck").click(function() {
        $("#grid").dataTable().api().ajax.reload();
    });
    var userdata = $.parseJSON(window.localStorage.getItem("entityInfo")).data;
    var entity_id = "";
    if (userdata) {
        entity_id = userdata.id;
    }

    var isType = {};
    $http({
        method: 'POST',
        url: '/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo',
        header: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        if (data && data.list) {
            for (var i in data.list) {
                isType[data.list[i].dictValue] = data.list[i].dictName;
            }
        }
    });

    window.inputLimit =  function (row){
        console.log('row', row);
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjScgl/getSaleList",
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
                    params.shrq_q = $("input[name='startDate']").val();
                    params.shrq_h = $("input[name='endDate']").val();
                    params.entity_id = entity_id;
                    params.productName = $("input[name='in_productName']").val();
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    data: "NUM"
                }, {
                    data: "PRODUCT_SORT",
                    title: "产品类型"
                }, {
                    data: "PRODUCT_NAME",
                    title: "产品名称"
                }, {
                    data: "HARVEST_TIME",
                    title: "生产时间",
                    render: function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
                }, {
                    data: "STORE_COUNT",
                    title: "库存数量"
                }, {
                    data: "PRODUCT_AMOUNT",
                    title: "销售数量",
                    render: function(data, type, row) {
                        var kc = row.STORE_COUNT;
                        var sh = data;
                        return parseFloat(sh - kc).toFixed(4);
                    }
                }, {
                    data: "PRODUCT_PC",
                    title: "产品批次",
                    width: "25%",
                    render : function(data,type,row){
                        var source = row.PRODUCT_SOURCE;
                        if(source=="采购" && row.BILL_STRATRUS != "1"){
                            return "无";
                        }else{
                            return data;
                        }
                    }
                }, {
                    data: "ID",
                    title: "销售数量",
                    render: function(data, type, row) {
                        return '<input type="text"  id="' + data + '" class="gui-input saleAmountInput" value="" data-max="' + row.STORE_COUNT + '"  data-unit="' + row.HARVEST_UNIT + '"  placeholder="请输入数量..." />';
                    }
                }, {
                    data: "HARVEST_UNIT",
                    title: "计量单位"
                }
            ]
        });
        customerList();
    });

    $('#grid').on('input', '.saleAmountInput', function(){
      var store = $(this).data('max'),
          unit = $(this).data('unit');
          current = $.trim($(this).val());

          if (~['头','只', '羽', '尾'].indexOf(unit)) {
            current = current.replace(/[^0-9]/g, '');
            current = current > store ? store : current;
            $(this).val(current);
          }else{
            current = current.replace(/[^0-9\.]/g, '');
            current = current > store ? store : current;
            if (!/^((0\.?\d{0,2})|([1-9]\d*\.?\d{0,2}))$/.test(current)) {
              current = parseInt(current*100)/100;
              current = current <= 0 ? '' : current;
            }
            $(this).val(current);
          }
    });

    /**
     * 初始化加载常客户信息
     */
    function customerList() {
        //传入登录账户 主体用户码，暂时不实现
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjCustomer/getCusromerListNotCustomerEntityID",
            data: {
                id: entity_id
            },
            method: "post",
            headers: {
                post: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                token: window.localStorage.getItem("token")
            }
        }).success(function(d) {
            var data = d.data;
            var i = 0;
            $(data).each(function() {
                var name = this.name;
                var userName = this.userName;
                var address = this.address;
                var phone = this.phone;
                //var customerEntityIdCode = this.customerEntityIdCode;
                //var customerUserIdCode = this.customerUserIdCode;
                var a = $("<a>").html(name).click(function() {
                    $("#ztmc").val(name);
                    $("#lxr").val(userName);
                    $("#ztdz").val(address);
                    $("#lxdh").val(phone);
                    //$("#customerEntityIdCode").val(customerEntityIdCode);
                    //$("#customerUserIdCode").val(customerUserIdCode);
                }).appendTo("#kh" + i);
                //
                i++;
            });
        }).error(function() {
            alert("获取常客户信息失败");
        })
    }

    /**
     * 检查开始时间是否大于结束时间
     * @param data
     */
    window.changeDate = function(obj) {
        var start = $("input[name='shrq_q']").val();
        var end = $("input[name='shrq_h']").val();
        if (start != null && start != '' && end != null && end != '') {
            if (start > end) {
                jBox.tip("开始日期不能大于结束日期", 'error');
                $(obj).val('');
            }
        }
    }

    /**
     * 选择客户信息
     */
    $scope.findCustomer = function() {
        //产品信息框展示
        $("#customer_info").modal('show');
        //清理数据
        var tableName = $('#customerList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#customerList").dataTable({
            ajax: {
                url: "/sofn-tts-web/ttsScltxxcjCustomer/getPageInfoForEntryId",
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
                    //params.tel =  $("input[name='in_tel']").val();
                    params.id = entity_id;
                    params.isrs = "0";
                },
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
                    data: "NUM",
                    title: "序号"
                }, {
                    data: "USER_NAME",
                    title: "用户名字"
                }, {
                    data: "ADDRESS",
                    title: "客户地址"
                }, {
                    data: "PHONE",
                    title: "联系方式"
                }, {
                    data: "NAME",
                    title: "单位名称"
                }, {
                    data: "ID",
                    title: "操作",
                    render: function(data, type, row) {
                        // 数据操作选择项
                        return "<button class='btn btn-primary btn-xs'" + "onclick=selectCustomer('" + row.USER_NAME + "','" + row.PHONE + "','" + row.NAME + "','" + row.ADDRESS + "','" + row.CUSTOMER_ENTITY_ID_CODE + "')>" + "选择客户</button>";
                    }
                }
            ]
        });
    };

    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.selectCustomer = function(uname, phone, name, address, eid) {
        $("#ztmc").val(name);
        $("#lxdh").val(phone);
        $("#lxr").val(uname);
        $("#ztdz").val(address);
        $("#customerEntityIdCode").val(eid);
    }

    //确认产品销售后产品模态框
    $scope.subCpxs = function() {
        //customer info
        var ztmc = $("#ztmc").val();
        var lxdh = $("#lxdh").val();
        var lxr = $("#lxr").val();
        var ztdz = $("#ztdz").val();
        var customerEntityIdCode = $("#customerEntityIdCode").val();
        var RegExp = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
        /*if (!ztmc || !lxdh || !lxr || !ztdz) {
            $.messager.alert("提示", "客户信息不完整！");
            return;
        }else if(ztmc.length <= 2 || ztmc.length>30){
            $.messager.alert("提示", "请输入2-30位字符的单位名称！");
            return;
        }else */if(ztdz.length < 2 || ztdz.length>30){
            $.messager.alert("提示", "请输入2-30位字符的主体地址！");
            return;
        }else if(lxr.length < 2 || lxr.length>30){
            $.messager.alert("提示", "请输入2-10位字符的客户名称！");
            return;
        }else if(RegExp.test(lxdh) == false){
            $.messager.alert("提示", "请输入正确的手机或座机号码！");
            return;
        }
        if (userdata && customerEntityIdCode && customerEntityIdCode == userdata.entityIdcode) {
            $.messager.alert("提示", "不能和自身主体交易");
            return;
        }
        //
        $("#s_ztmc").html(ztmc);
        $("#s_lxdh").html(lxdh);
        $("#s_lxr").html(lxr);
        $("#s_ztdz").html(ztdz);

        //清空旧数据
        $("#countersignBody").html("");
        //确认数据
        var rows = $('#grid').DataTable().rows().data();
        var tablehtml = $("<tbody>");
        for (i = 0; i < rows.length; i++) {
            var count = $("#" + rows[i].ID).val();
            if (count == 0 || count == "") {
                continue;
            }
            var tr = $("<tr>").appendTo(tablehtml);
            var td_cpmc = $("<td>").html(rows[i].PRODUCT_NAME).appendTo(tr);
            if(rows[i].PRODUCT_SOURCE == "采购" && rows[i].BILL_STRATRUS != "1"){
                var td_cppc = $("<td>").html(rows[i].FROMZSM).appendTo(tr);
            }else{
                var td_cppc = $("<td>").html(rows[i].PRODUCT_PC).appendTo(tr);
            }
            var td_cpsl = $("<td>").html(count).appendTo(tr);
            var td_cpdw = $("<td>").html(rows[i].HARVEST_UNIT).appendTo(tr);
        }
        $("#countersignBody").html(tablehtml.html());
        //表格行数
        var trows = $('#countersignList tr').length;
        if(trows == 2){
            $.messager.alert("提示", "请选择需要销售的产品!");
            return;
        }
        //产品信息框展示
        $("#countersign").modal('show');

    };

    /**
     * 产品销售确认按钮点击..
     */
    $scope.toCpxs = function() {
        var xsRows = $('#grid').DataTable().rows().data();
        //封装参入参数
        var bparams = [];
        if (xsRows.length > 0) {
            for (var i = 0; i < xsRows.length; i++) {
                //获取销售数量
                var saleAmount = $("#" + xsRows[i].ID).val();
                //判断数据是否为数字
                if (isNaN(saleAmount)) {
                    $.messager.alert("提示", "数量必须为数字！")
                    return;
                }
                //库存数量
                var storeCount = xsRows[i].STORE_COUNT;
                if (parseInt(saleAmount) > parseInt(storeCount)) {
                    $.messager.alert("提示", "销售数量不能大于库存数量！")
                    return;
                }
                //如果销售数量大于0的情况，则进行销售确认
                var productId = xsRows[i].PRODUCT_ID;
                var productName = xsRows[i].PRODUCT_NAME;
                var productIndustry = xsRows[i].PRODUCT_INDUSTRY;
                var productSort = xsRows[i].PRODUCT_SORT;
                var productScglId = xsRows[i].PRODUCT_PC;
                var productAmount = xsRows[i].PRODUCT_AMOUNT;
                //来源追溯码
                var fromzsm = xsRows[i].FROMZSM;
                //
                var mediCheck = xsRows[i].MEDI_CHECK;
                var mediResult = xsRows[i].MEDI_RESULT;
                var zjcheck = mediCheck + mediResult;
                var unitId = xsRows[i].UNIT_ID;
                var joinFlag = xsRows[i].JOIN_FLAG;
                var harvestUnit = xsRows[i].HARVEST_UNIT;
                //对数据封装
                if (saleAmount > 0) {
                    bparams[i] = {
                        productId: productId,
                        productName: productName,
                        productIndustry: productIndustry,
                        productSort: productSort,
                        productScglId: productScglId,
                        productAmount: productAmount,
                        saleAmount: saleAmount,
                        storeCount: storeCount,
                        zjcheck: zjcheck,
                        fromzsm: fromzsm,
                        unitId: unitId,
                        joinFlag: joinFlag,
                        harvestUnit: harvestUnit
                    }
                }
            }
        }
        var jsonStr = JSON.stringify(bparams);
        //客户信息转json
        var ztmc = $("#ztmc").val();
        var lxdh = $("#lxdh").val();
        if (isNaN(lxdh)) {
            jBox.tip("当前输入联系电话，不是数字，请重新输入", 'info');
            return;
        }
        var lxr = $("#lxr").val();
        var ztdz = $("#ztdz").val();
        var jsonCustomer = JSON.stringify({name: ztmc, userName: lxr, phone: lxdh, address: ztdz});
        /*var config = {
            jsonStr:jsonStr,
            jsonCustomer:jsonCustomer,
            entity_id:entity_id
        };*/
        $.ajax({
            url: "/sofn-tts-web/ttsScltxxcjXsdj/addXsdjrs", type: 'POST', //POST方式
            dataType: 'json', //返回json类型
            data: {
                jsonStr: jsonStr,
                jsonCustomer: jsonCustomer,
                entity_id: entity_id
            },
            header: {
                token: window.localStorage.getItem("token")
            },
            success: function(data) {
                if (data.httpCode == "200") {
                    // 提示成功
                    jBox.tip("操作成功！", 'info');
                    $("#countersign").modal("hide"); //隐藏
                    $("#grid").dataTable().api().ajax.reload();
                } else {
                    jBox.tip("操作失败！", 'info');
                }
            }
        });
        /* $.post("/sofn-tts-web/ttsScltxxcjXsdj/addXsdjrs",config,
             function(data){
                 if(data.httpCode == "200"){
                     // 提示成功
                     $.messager.show({
                         title : "提示信息",
                         msg : "操作成功！",
                         showType : "slide",
                         timeout : 5000
                     });
                     $("#countersign").modal("hide");//隐藏
                     $("#grid").dataTable().api().ajax.reload();
                 }else{
                     $.messager.alert("提示","添加数据行失败!");
                 }
             },"json");*/
    }

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
                header: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [
                {
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
                        return "<button class='btn btn-primary btn-xs'" + "onclick=selectProduct('" + row.PRODUCT_CODE + "','" + row.NAME + "','" + row.INDUSTRY + "','" + row.TYPE + "')>" + "选定产品</button>";
                    }
                }
            ]
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
        url: '/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo',
        header: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        if (data.list != null) {
            for (var i in data.list) {
                isType[data.list[i].dictValue] = data.list[i].dictName;
            }
        }
    })

    //ToClean 清空查询条件
    $("#ToClean").click(function() {
        $("input[name='in_productId']").val("");
        $("input[name='in_productName']").val("");
        $("#startDate").val("");
        $("#endDate").val("");
    });
})
