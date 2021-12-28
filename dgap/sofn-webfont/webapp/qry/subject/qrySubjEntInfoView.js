var myapp = angular.module("myapp", []).controller("qrySubjEntInfoView", function ($scope, $http, $stateParams, $state) {
    //获取本地存储的ID
    $scope.subjEntId = window.localStorage.getItem("subjEntId");
    $scope.subjEntEntityIdcode = window.localStorage.getItem("subjEntEntityIdcode");
    $scope.productStatus = [{
        name: "启用",
        value: "Y"
    }, {
        name: "禁用",
        value: "N"
    }]


    //获取生产经营主体备案信息
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/subject/findSubjEntById",
        method: "post",
        data: {id: $scope.subjEntId}
    }).success(function (data) {
        $scope.subjEnt = data.data;
        $scope.subjEnt.area = $.fn.Cityname($scope.subjEnt.area);
        //根据生产经营主体组织形式展示不同内容
        if (!$scope.subjEnt.businessLicenceimg) {
            $scope.subjEnt.businessLicenceimg = $scope.subjEnt.positiveIdcardeimg;
        }
        if ($scope.subjEnt.entityScaleName.trim() == "家庭农场" || $scope.subjEnt.entityScaleName.trim() == "个人") {
            $scope.subjEnt.businessLicenceimg = $scope.subjEnt.positiveIdcardeimg;
        }
        //处理图片
        handlePicUrl($scope.subjEnt, ["businessLicenceimg", "organizationCertificateimg", "positiveIdcardeimg", "negativeIdcardimg", "handIdcardimg"]);
        //相关图片显示判断
        if ($scope.subjEnt.organizationCertificateimg) {//组织机构代码证照
            $("#organizationCertificateimg").append('<a data-fancybox href="' + $scope.subjEnt.organizationCertificateimg + '"><img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/> </a>');
            $("#organizationCertificateimgF").show();
        } else {
            $("#organizationCertificateimgF").hide();
        }
        if ($scope.subjEnt.businessLicenceimg) {
            $("#businessLicenceimg").append('<a data-fancybox href="' + $scope.subjEnt.businessLicenceimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.businessLicenceimg + '" alt=""/> </a>');
        }
        // $("#organizationCertificateimg").append('<img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.organizationCertificateimg + '" alt=""/>');
        $("#positiveIdcardeimg").append('<a data-fancybox href="' + $scope.subjEnt.positiveIdcardeimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimg").append('<a data-fancybox href="' + $scope.subjEnt.negativeIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimg").append('<a data-fancybox href="' + $scope.subjEnt.handIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        $("#positiveIdcardeimgFarm").append('<a data-fancybox href="' + $scope.subjEnt.positiveIdcardeimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimgFarm").append('<a data-fancybox href="' + $scope.subjEnt.negativeIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimgFarm").append('<a data-fancybox href="' + $scope.subjEnt.handIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        $("#positiveIdcardeimgPerson").append('<a data-fancybox href="' + $scope.subjEnt.positiveIdcardeimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.positiveIdcardeimg + '" alt=""/> </a>');
        $("#negativeIdcardimgPerson").append('<a data-fancybox href="' + $scope.subjEnt.negativeIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.negativeIdcardimg + '" alt=""/> </a>');
        $("#handIdcardimgPerson").append('<a data-fancybox href="' + $scope.subjEnt.handIdcardimg + '"> <img class="ml15" style="height: 120px;width: 200px;" src="' + $scope.subjEnt.handIdcardimg + '" alt=""/> </a>');
        //时间戳转成ISO时间格式要加8个小时，即28800000毫秒
        if ($scope.subjEnt.businessOperationStart != null && $scope.subjEnt.businessOperationEnd != null) {
            $scope.subjEnt.businessOperationStart = new Date(parseInt($scope.subjEnt.businessOperationStart) + 28800000).toISOString().slice(0, 10);
            $scope.subjEnt.businessOperationEnd = new Date(parseInt($scope.subjEnt.businessOperationEnd) + 28800000).toISOString().slice(0, 10);
        }
        //空字段默认为"无"
        $scope.checkData($scope.subjEnt);
        $scope.loadProductList();
    }).error(function (data) {
        
    })
    //获取行业数据字典
    $scope.industryType = [];
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-qry-web/sysDict/getSysDictByCode",
        data: "2",
        method: "post"
    }).success(function (data) {
        $scope.industryType = JSON.parse(data.data);
        $scope.loadProductList();
    }).error(function (data) {
        
    })
    //产品信息
    $scope.loadProductList = function () {
        $("#qryProductGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getProductListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    // params.entityIdcode = '1.2.156.326.8.2.000102354668899855.00000';
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "NAME",
                    title: "产品名称",
                    width: "28%"
                },
                {
                    data: "INDUSTRY",
                    title: "所属行业",
                    width: "12%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.industryType.length; i++) {
                            if (data == $scope.industryType[i].dictValue) {
                                return $scope.industryType[i].dictName;
                            }
                        }
                        return "无";
                    }
                },
                {
                    data: "STATUS",
                    title: "产品状态",
                    width: "8%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.productStatus.length; i++) {
                            if (data == $scope.productStatus[i].value) {
                                return $scope.productStatus[i].name;
                            }
                        }
                        return "无";
                    }
                }
            ],
        });
    }
    //基地信息
    $scope.loadBaseList = function () {
        $("#qryBaseGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSubjEntBaseByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].createTime != null && json.list[i].createTime != "" && json.list[i].createTime != undefined) {
                            json.list[i].createTime = new Date(parseInt(json.list[i].createTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    // params.entityIdcode = '1.2.156.326.8.2.000102354668899855.00000';
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "NAME",
                    title: "基地名称",
                    width: "28%"
                },
                {
                    data: "ADDRESS",
                    title: "基地地址",
                    width: "12%"
                },
                {
                    data: "AREA",
                    title: "基地面积",
                    width: "8%"
                },
                {
                    data: "PRODUCT_NAMES",
                    title: "主要产品",
                    width: "8%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "STATUS",
                    title: "产品状态",
                    width: "8%",
                    render: function (data, type, row) {
                        for (var i = 0; i < $scope.productStatus.length; i++) {
                            if (data == $scope.productStatus[i].value) {
                                return $scope.productStatus[i].name;
                            }
                        }
                        return "无";
                    }
                }
            ],
        });
    }
    //批次信息
    $scope.loadBathList = function () {
        $("#qryBathGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getBathByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].HARVEST_TIME != null && json.list[i].HARVEST_TIME != "" && json.list[i].HARVEST_TIME != undefined) {
                            json.list[i].HARVEST_TIME = new Date(parseInt(json.list[i].HARVEST_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    // params.entityIdcode = '1.2.156.326.8.2.000102354668899855.00000';
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"

                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width: "8%"
                },
                {
                    data: "PRODUCT_PC",
                    title: "产品追溯码",
                    width: "13%",
                    render: function (data, type, row) {
                        if (null != data && row.BILL_STRATRUS == "1") {
                            return "<a style='cursor: pointer' class='btn btn-primary btn-xs' onclick=findCphc('" + row.ID + "')>" + data + "</a>";
                        } else if (row.PRODUCT_SOURCE != '采购') {
                            return data;
                        } else if (row.PRODUCT_SOURCE == "采购" && row.BILL_STRATRUS != "1") {
                            return row.FROMZSM;
                        }
                    }
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width: "8%"
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量",
                    width: "5%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "STORE_COUNT",
                    title: "当前库存",
                    width: "5%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "HARVEST_TIME",
                    title: "收货时间",
                    width: "5%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "产品来源",
                    width: "5%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "MEDI_CHECK",
                    title: "质检情况",
                    width: "6%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data + row.MEDI_RESULT;
                        }
                    }
                },
                {
                    data: "PROOF_NUMBER",
                    title: "动物检疫合格证号",
                    width: "8%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                }
            ],
        });
    }
    //合成批次
    $scope.loadHcBathList = function () {
        $("#qryHcBathGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getHcBathById",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].HARVEST_TIME != null && json.list[i].HARVEST_TIME != "" && json.list[i].HARVEST_TIME != undefined) {
                            json.list[i].HARVEST_TIME = new Date(parseInt(json.list[i].HARVEST_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    // params.entityIdcode = '1.2.156.326.8.2.000102354668899855.00000';
                    params.hcid = $scope.hcid;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"

                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width: "10%"
                },
                {
                    data: "PRODUCT_PC",
                    title: "产品追溯码",
                    width: "25%",
                    render: function (data, type, row) {
                        if (null != data && row.BILL_STRATRUS == "1") {
                            return "<a style='cursor: pointer' class='btn btn-primary btn-xs' onclick=findCphc('" + row.ID + "')>" + data + "</a>";
                        } else if (row.PRODUCT_SOURCE != '采购') {
                            return data;
                        } else if (row.PRODUCT_SOURCE == "采购" && row.BILL_STRATRUS != "1") {
                            return row.FROMZSM;
                        }
                    }
                },
                {
                    data: "HARVEST_BASENAME",
                    title: "收获基地",
                    width: "5%"
                },
                {
                    data: "PROOF_NUMBER",
                    title: "动物检疫合格证号",
                    width: "8%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "产品来源",
                    width: "5%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "MEDI_CHECK",
                    title: "质检情况",
                    width: "6%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data + row.MEDI_RESULT;
                        }
                    }
                }
            ],
        });
    }
    $scope.loadHcBathList();
    //销售信息
    $scope.loadSaleList = function () {
        $("#qrySaleGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSaleByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].SALE_TIME != null && json.list[i].SALE_TIME != "" && json.list[i].SALE_TIME != undefined) {
                            json.list[i].SALE_TIME = new Date(parseInt(json.list[i].SALE_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    // params.entityIdcode = '1.2.156.326.8.2.000102354668899855.00000';
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品名称",
                    width: "8%"
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width: "8%"
                },
                {
                    data: "SALE_TIME",
                    title: "销售时间",
                    width: "12%"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "销售数量",
                    width: "12%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "TRACE_CODE",
                    title: "产品追溯码",
                    width: "22%",
                    render: function (data, type, row) {
                        if (null == data || "" == data) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data: "TRACE_PROOF",
                    title: "入市追溯凭证",
                    width: "22%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "CONFIRM_STATE",
                    title: "销售状态",
                    width: "8%",
                    render: function (data, type, row) {
                        if (data == "Y") {
                            return "完成";
                        }
                        if (data == "T") {
                            return "退货";
                        }
                        if (data == "H") {
                            return "换货";
                        }
                        return "待确认";
                    }
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        var butt;
                        butt = '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="qrySaleDetail(\'' + data + '\',\'' + row.CONFIRM_STATE + '\')">详情</a>';
                        return butt;
                    }
                }
            ],
        });
    }
    //收货信息
    $scope.loadCgglList = function () {
        $("#qryCgglGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSubjCgglListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].saleTime != null && json.list[i].saleTime != "" && json.list[i].saleTime != undefined) {
                            json.list[i].saleTime = new Date(parseInt(json.list[i].saleTime) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                    params.isCirculatesEnd = 'N';
                    params.purchaseRecord = 'Y';
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"

                },
                {
                    data: "xsName",
                    title: "供应商",
                    width: "12%"
                },
                {
                    data: "productName",
                    title: "产品名称",
                    width: "10%"
                },
                {
                    data: "saleAmount",
                    title: "数量",
                    width: "5%",
                    render: function (data, type, row) {
                        return data + row.harvestUnit;
                    }
                },
                {
                    data: "zjcheck",
                    title: "质检情况",
                    width: "5%",
                    render: function (data, type, row) {
                        return "合格";
                    }
                },
                {
                    data: "saleTime",
                    title: "采购时间",
                    width: "5%"
                },
                {
                    data: "traceCode",
                    title: "产品追溯码",
                    width: "18%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "userName",
                    title: "采购人",
                    width: "5%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "confirmState",
                    title: "交易状态",
                    width: "5%",
                    render: function (data, type, row) {
                        if (data == "Y") {
                            return "已收货";
                        } else if (data == "T") {
                            return "退货";
                        } else if (data == "H") {
                            return "换货";
                        }
                    }
                }
            ],
        });
    }
    //销售打印
    $scope.loadSalePrintList = function () {
        $("#qrySalePrintGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSalePrintListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].SALE_TIME != null && json.list[i].SALE_TIME != "" && json.list[i].SALE_TIME != undefined) {
                            json.list[i].SALE_TIME = new Date(parseInt(json.list[i].SALE_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "3%"

                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width: "8%"
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width: "8%"
                },
                {
                    data: "SALE_TIME",
                    title: "销售时间",
                    width: "8%"
                },
                {
                    data: "SALE_AMOUNT",
                    title: "销售数量",
                    width: "6%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "PRODUCT_SCGL_ID",
                    title: "产品追溯码",
                    width: "25%",
                    render: function (data, type, row) {
                        var source = row.FROMZSM;
                        var isProof = row.TRACE_PROOF;
                        if (isProof != null) {
                            return "无";
                        } else if (source != null && isProof == null) {
                            return source;
                        } else if (source == null && isProof == null) {
                            return data;
                        }
                    }
                },
                {
                    data: "TRACE_PROOF",
                    title: "入市追溯凭证",
                    width: "18%",
                    render: function (data, type, row) {
                        if (!data) {
                            return "无";
                        }
                        return data;
                    }
                },
                {
                    data: "COU",
                    title: "打印数量",
                    width: "6%",
                    render: function (data, type, row) {
                        return data == null ? "" : data + "张";
                    }
                }
            ],
        });
    }
    //库存打印
    $scope.loadStockPrintList = function () {
        $("#qryStockPrintGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getStockPrintListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].HARVEST_TIME != null && json.list[i].HARVEST_TIME != "" && json.list[i].HARVEST_TIME != undefined) {
                            json.list[i].HARVEST_TIME = new Date(parseInt(json.list[i].HARVEST_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width: "8%"
                },
                {
                    data: "PRODUCT_SORT",
                    title: "产品种类",
                    width: "8%"
                },
                {
                    data: "PRODUCT_PC",
                    title: "产品追溯码",
                    width: "25%",
                    render: function (data, type, row) {
                        if (row.FROMZSM != null) {
                            return row.FROMZSM;
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "收获数量",
                    width: "12%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "STORE_COUNT",
                    title: "当前库存",
                    width: "12%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "PRODUCT_SOURCE",
                    title: "产品来源",
                    width: "8%"
                },
                {
                    data: "HARVEST_TIME",
                    title: "收获时间",
                    width: "8%"
                },
                {
                    data: "MEDI_CHECK",
                    title: "质检情况",
                    width: "8%",
                    render: function (data, type, row) {
                        return data + row.MEDI_RESULT;
                    }
                },
                {
                    data: "COU",
                    title: "打印数量",
                    width: "8%",
                    render: function (data, type, row) {
                        return data == null ? "" : data + "张";
                    }
                }
            ],
        });
    }
    //屠宰信息
    $scope.loadSlaughterList = function () {
        $("#qrySlaughterGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getSlaughterListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal;//总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start;//起始位置
                    json.length = json.page.length;
                    for (var i = 0; i < json.list.length; i++) {
                        if (json.list[i].HARVEST_TIME != null && json.list[i].HARVEST_TIME != "" && json.list[i].HARVEST_TIME != undefined) {
                            json.list[i].HARVEST_TIME = new Date(parseInt(json.list[i].HARVEST_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.token = window.localStorage.getItem("token");
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1
                    },
                    width: "4%"

                },
                {
                    data: "PRODUCT_NAME",
                    title: "产品名称",
                    width: "15%"
                },
                {
                    data: "PRODUCT_AMOUNT",
                    title: "屠宰后数量",
                    width: "8%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "STORE_COUNT",
                    title: "库存数量",
                    width: "8%",
                    render: function (data, type, row) {
                        return data + row.HARVEST_UNIT;
                    }
                },
                {
                    data: "HARVEST_TIME",
                    title: "屠宰时间",
                    width: "12%"
                },
                {
                    data: "MEDI_CHECK",
                    title: "质检情况",
                    width: "8%",
                    render: function (data, type, row) {
                        return data + row.MEDI_RESULT;
                    }
                },
                {
                    data: "PROOF_NUMBER",
                    title: "检疫合格证号",
                    width: "8%",
                    render: function (data, type, row) {
                        if (data != "" && data != null) {
                            return data;
                        } else {
                            return "无";
                        }
                    }
                },

                {
                    data: "PRODUCT_PC",
                    title: "产品追溯码",
                    width: "20%"
                }
            ],
        });
    }

    //基地巡查
    $scope.loadBaseInspectionList = function () {
        $("#qryBaseInspectionGrid").dataTable({
            ordering: false, //开启排序
            order: [[9, "desc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/subject/getBaseInspectionListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.page.recordsTotal; //总个数
                    json.recordsFiltered = json.page.recordsFiltered;
                    json.start = json.page.start; //起始位置
                    json.length = json.page.length;
                    $scope.$apply(function () {
                        $scope.tableTotal = json.recordsTotal;
                    });
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].INSPECTION_TIME != null &&
                            json.list[i].INSPECTION_TIME != "" &&
                            json.list[i].INSPECTION_TIME != undefined
                        ) {
                            json.list[i].INSPECTION_TIME = new Date(
                                parseInt(json.list[i].INSPECTION_TIME) +
                                28800000
                            ).toISOString().slice(0, 10);
                        }
                    }
                    for (var i = 0; i < json.list.length; i++) {
                        if (
                            json.list[i].CREATE_TIME != null &&
                            json.list[i].CREATE_TIME != "" &&
                            json.list[i].CREATE_TIME != undefined
                        ) {
                            json.list[i].CREATE_TIME = new Date(
                                parseInt(json.list[i].CREATE_TIME) + 28800000
                            ).toISOString().slice(0, 10);
                        }
                    }
                    return json.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "post"
            },
            columns: [
                {
                    title: "序号",
                    orderable: false,
                    data: function (data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "4%"
                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "主体名称",
                    width: "19%",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 15
                        ) {
                            return (
                                "<a id='ENTERPRISE_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('ENTERPRISE_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 15) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "ENTITY_INDUSTRY_NAME",
                    title: "所属行业",
                    width: "11%"
                },
                {
                    data: "AREA",
                    title: "区域",
                    width: "12%",
                    render: function (data, type, row) {
                        data = $.fn.Cityname(data);
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 10
                        ) {
                            return (
                                "<a id='AREA" +
                                row.ID +
                                "' onmouseover=showPopContent('AREA" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 9) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "CONTACT_NAME",
                    title: "联系人",
                    width: "8%",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 5
                        ) {
                            return (
                                "<a id='CONTACT_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('CONTACT_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 5) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "INSPECTION_TYPE_NAME",
                    title: "任务类型",
                    width: "12%"
                },
                {
                    data: "INSPECTION_RESULT",
                    title: "巡查结果",
                    width: "6%",
                    render: function (data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        }
                    }
                },
                {
                    data: "INSPECTION_TIME",
                    title: "巡查时间",
                    width: "7%"
                },
                {
                    data: "INSPECTION_USER_NAME",
                    title: "巡查员",
                    width: "10%",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 14
                        ) {
                            return (
                                "<a id='INSPECTION_USER_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('INSPECTION_USER_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 14) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width: "7%"
                },
                {
                    data: "INSPECTION_SV_NAME",
                    name: "inspectionSvName",
                    title: "巡查机构",
                    visible: false,
                },
                {
                    data: "LEGAL_NAME",
                    name: "legalName",
                    title: "法定代表人",
                    visible: false,
                },
                {
                    data: "LEGAL_PHONE",
                    name: "legalPhone",
                    title: "法人联系电话",
                    visible: false,
                },
                {
                    data: "CONTACT_NAME",
                    name: "contactName",
                    title: "联系人",
                    visible: false,
                },
                {
                    data: "CONTACT_PHONE",
                    name: "contactPhone",
                    title: "联系人电话",
                    visible: false,
                },
                {
                    data: "ID",
                    title: "操作",
                    orderable: false,
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        var butt =
                            '<a style="margin-right: 10px;cursor: pointer" class="Check-report color"  onclick="inspectionView(\'' +
                            data + "')\">查看</a>";
                        return butt;
                    }
                }
            ]
        });
    }

    //监测信息列表
    $scope.loadMonitorList = function () {
        $("#qryMonitorGrid").dataTable({
            ajax: {
                url: "/sofn-qry-web/subject/getMonitorListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start =
                        (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;

                    $scope.$apply(function () {
                        $scope.tableTotal = json.data.total;
                    });
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].CHECK_TIME != null && json.data.list[i].CHECK_TIME != "" && json.data.list[i].CHECK_TIME != undefined) {
                            json.data.list[i].CHECK_TIME = new Date(parseInt(json.data.list[i].CHECK_TIME) + 28800000).toISOString().slice(0, 10);
                        }
                    }
                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "POST"
            },
            columns: [
                {
                    title: "序号",
                    width: "5%",
                    data: function (data, type, row, meta) {
                        return meta.row + 1;
                    }
                },
                {
                    data: "MONITOR_SAMPLE_ID",
                    visible: false
                },
                {
                    data: "SAMPLE_NAME",
                    title: "样品名称",
                    width: "10%"
                },
                {
                    data: "SAMPLE_CODE",
                    title: "样品编码",
                    width: "15%"
                },
                /*{
                    data: "PRODUCT_TRACEABILITY_CODE",
                    title: "产品编码",
                    render: function (data, type, row, meta) { // 模板化列显示内容
                        if (data != null) {
                            return '<input readOnly="true"  style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        } else {
                            data = "";
                            return '<input readOnly="true"  style="overflow: auto; overflow-y:hidden" value="' + data + '" type="text"/>';
                        }

                    },
                    width: "10%"
                },*/
                /*{
                    data: "TESTED_DEPARMENT",
                    title: "受检单位",
                    width: "10%"
                },*/
                {
                    data: "SAMPLE_DEPARMENT",
                    title: "抽样单位",
                    width: "10%"
                },
                {
                    data: "CHECK_ORGAN",
                    title: "检测单位",
                    width: "10%"
                },
                {
                    data: "RESULT",
                    title: "判定结果",
                    render: function (data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else if ("2" == data) {
                            return "不作判定";
                        }
                    },
                    width: "10%"
                },
                /*{
                    data: "CHECK_REPORT",
                    title: "状态",
                    render: function (data, type, row) {
                        if (1 == data) {
                            return "已上报";
                        } else if (0 == data) {
                            return "未上报";
                        } else {
                            return "已退回";
                        }
                    },
                    width: "10%"
                },*/

                {
                    data: "CHECK_TIME",
                    title: "抽样时间",
                    width: "10%"
                },
                {
                    data: "ID",
                    title: "操作",
                    render: function (data, type, row) { // 模板化列显示内容
                        return  '<a style="margin-right: 10px;cursor: pointer" class="Check-report"  onclick="viewOrganTask(\'' + data + '\')">检测结果</a>';
                    },
                    width: "6%"
                }
            ]
        });
    }

    //现场巡查
    $scope.loadetlDailyEnforceList = function () {
        $("#qryetlDailyEnforceGrid").dataTable({
            ordering: false, //开启排序
            order: [[9, "asc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-qry-web/subject/getEtlDailyEnforceListByEntityIdcode",
                dataSrc: function (json) {
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start =
                        (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (
                            json.data.list[i].TASK_BEGIN_TIME != null &&
                            json.data.list[i].TASK_BEGIN_TIME != "" &&
                            json.data.list[i].TASK_BEGIN_TIME != undefined
                        ) {
                            json.data.list[i].TASK_BEGIN_TIME = new Date(
                                parseInt(
                                    json.data.list[i].TASK_BEGIN_TIME
                                ) + 28800000
                            )
                                .toISOString()
                                .slice(0, 10);
                            // json.data.list[i].TASK_END_TIME=new Date(parseInt(json.data.list[i].TASK_END_TIME)+28800000).toISOString().slice(0,10);//取消时间段限制 2018年4月24日17:43:34
                            if (json.data.list[i].CREATE_TIME) {
                                json.data.list[i].CREATE_TIME = new Date(
                                    parseInt(json.data.list[i].CREATE_TIME) +
                                    28800000
                                )
                                    .toISOString()
                                    .slice(0, 10);
                            } else {
                                json.data.list[i].CREATE_TIME = "---";
                            }
                        } else if (
                            json.data.list[i].TASK_BEGIN_TIME == null ||
                            json.data.list[i].TASK_BEGIN_TIME == ""
                        ) {
                            //开始时间脏数据处理 2018年6月6日13:53:36
                            json.data.list[i].TASK_BEGIN_TIME = "---";
                            // json.data.list[i].CREATE_TIME = "---";
                        }
                    }
                    $scope.$apply(function () {
                        $scope.tableTotal = json.data.total;
                    });
                    return json.data.list;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.entityIdcode = $scope.subjEntEntityIdcode;
                },
                type: "POST"
            },
            columns: [
                {
                    data: "ID",
                    title: "ID",
                    visible: false
                },
                {
                    data: "ENTERPRISE_ID",
                    title: "ENTERPRISE_ID",
                    visible: false
                },
                {
                    title: "序号",
                    data: function (data, type, row, meta) {
                        return meta.row + 1;
                    },
                    width: "5%",
                    orderable: false
                },
                {
                    data: "TASK_BEGIN_TIME",
                    title: "年度",
                    width: "8%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        return data.substring(0, 4);
                    }
                },
                {
                    data: "TASK_NAME",
                    title: "任务名称",
                    render: function (data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return (
                                    '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            }
                            return (
                                '<div class=""  data-toggle="tooltip"  title="' +
                                data +
                                '">' +
                                showdata +
                                "</div>"
                            );
                        } else {
                            return "--";
                        }
                    },
                    width: "10%"
                },
                {
                    data: "ENTERPRISE_NAME",
                    title: "被执法对象",
                    render: function (data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return (
                                    '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            }
                            return (
                                '<div class=""  data-toggle="tooltip"  title="' +
                                data +
                                '">' +
                                showdata +
                                "</div>"
                            );
                        } else {
                            return "--";
                        }
                    },
                    width: "12%"
                },
                {
                    data: "AREA_ID",
                    title: "区域",
                    width: "12%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        if (
                            data != null &&
                            data != "" &&
                            data != undefined
                        ) {
                            var region = $.fn.Cityname(row.AREA_ID);
                            var regions;
                            if (region) {
                                regions = region.substring(0, data.length);
                                var showdata = regions;
                                if (region.length > 10) {
                                    showdata =
                                        region.substring(0, 10) + "..";
                                    return (
                                        '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                        region +
                                        '">' +
                                        showdata +
                                        "</div>"
                                    );
                                }
                                return (
                                    '<div class=""  data-toggle="tooltip"  title="' +
                                    region +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            } else {
                                return "--";
                            }
                            /*return $.fn.Cityname(row.AREA_ID)*/
                        }
                    }
                },
                {
                    data: "ENTERPRISE_ADDRESS",
                    title: "被执法对象地址",
                    render: function (data, type, row, meta) {
                        if (data) {
                            data = data.substring(0, data.length);
                            var showdata = data;
                            if (data.length > 10) {
                                showdata = data.substring(0, 10) + "..";
                                return (
                                    '<div class=""   style="color:#72ACE3" data-toggle="tooltip"  title="' +
                                    data +
                                    '">' +
                                    showdata +
                                    "</div>"
                                );
                            }
                            return (
                                '<div class=""  data-toggle="tooltip"  title="' +
                                data +
                                '">' +
                                showdata +
                                "</div>"
                            );
                        } else {
                            return "--";
                        }
                    },
                    width: "12%"
                },
                {
                    data: "TASK_BEGIN_TIME",
                    title: "执法时间",
                    width: "10%"
                },
                /*{
                data : "TASK_END_TIME",
                title : "结束时间",
                width : "10%"
            },*/
                {
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width: "10%"
                },
                {
                    data: "ENFORCE_LAW_RESULT_FLAG",
                    title: "巡查结果",
                    width: "5%",
                    render: function (data, type, row) {
                        if (data == "1") {
                            return "合格";
                        } else if (data == "2") {
                            return "不合格";
                        } else if (data == "3") {
                            return "整改";
                        } else if (data == "4") {
                            return "其他";
                        }
                    }
                },
                {
                    data: "CREATE_BY",
                    title: "",
                    visible: false
                },
                {
                    data: "TASK_PERSON_NAME",
                    name: "taskPersonName",
                    title: "执法人员",
                    visible: false
                },
                {
                    data: "TASK_NAME",
                    name: "taskName",
                    title: "执法人员",
                    visible: false
                },
                {
                    data: "ID",
                    title: "操作",
                    width: "6%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        return '<a type="button" data-toggle="modal" data-target="#etlDailyEnforceModal" style="cursor:pointer" onclick="Checkreport(\'' +
                            data + '\')">查看</a>';
                    },
                    orderable: false
                }
            ]
        });
    }

    //行政处罚
    $scope.loadproduceAdminPunishList = function () {
        $("#qryproduceAdminPunishGrid").dataTable({
            ordering: false, //开启排序
            order: [[9, "asc"]], //默认排序项
            scrollX: true, //横向滚动
            ajax: {
                //url: "/sofn-qry-web/subject/getAdministrativePenaltyListByEntityIdcode",
                url: "/sofn-qry-web/subject/getAdministrativePenaltyListByUserIdcode",
                //dataSrc : "rows", // 加载表格的相应数据源
                dataSrc: function (json) {
                    // if(json.code != 200){
                    //     swal('系统提示', '服务器出错', 'error');
                    //     return false;
                    // }
                    json.recordsTotal = json.data.total; //总个数
                    json.recordsFiltered = json.data.total;
                    json.pageNumber = json.data.pageNum; //页码
                    json.pageSize = json.data.pageSize; //每页个数
                    json.start =
                        (json.data.pageNum - 1) * json.data.pageSize; //起始位置
                    json.length = json.data.pageSize;
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].ENFORCE_LAW_TIME != null && json.data.list[i].ENFORCE_LAW_TIME != "" && json.data.list[i].ENFORCE_LAW_TIME != undefined) {
                            json.data.list[i].ENFORCE_LAW_TIME = new Date(
                                parseInt(
                                    json.data.list[i].ENFORCE_LAW_TIME
                                ) + 28800000
                            ).toISOString().slice(0, 10);
                        }
                    }
                    for (var i = 0; i < json.data.list.length; i++) {
                        if (json.data.list[i].CREATE_TIME != null && json.data.list[i].CREATE_TIME != "" && json.data.list[i].CREATE_TIME != undefined) {
                            json.data.list[i].CREATE_TIME = new Date(
                                parseInt(
                                    json.data.list[i].CREATE_TIME
                                ) + 28800000
                            ).toISOString().slice(0, 10);
                        }
                    }
                    $scope.$apply(function () {
                        $scope.tableTotal = json.data.total;
                    });
                    return json.data.list;
                },
                error: function (msg) {
                    console.log(msg);
                    
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.userIdcode = $scope.subjEnt.userIdcode;
                },
                type: "post"
            },
            columns: [
                {
                    //data : "RN",
                    title: "序号",
                    width: "4%",
                    data: function (data, type, row, meta) {
                        return meta.row + 1;
                    },
                    orderable: false
                },
                {
                    data: "ENFORCE_LAW_TIME",
                    title: "年度",
                    width: "4%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        return data.substring(0, 4);
                    }
                },
                {
                    data: "CASE_NAME",
                    title: "案件名称",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 18
                        ) {
                            return (
                                "<a id='CASE_NAME" +
                                row.ID +
                                "' onmouseover=showPopContent('CASE_NAME" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 18) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "PUNISH_CODE",
                    title: "行政处罚决定案号",
                    render: function (data, type, row) {
                        if (
                            data != null &&
                            data != undefined &&
                            data.length > 18
                        ) {
                            return (
                                "<a id='PUNISH_CODE" +
                                row.ID +
                                "' onmouseover=showPopContent('PUNISH_CODE" +
                                row.ID +
                                "','" +
                                data +
                                "')>" +
                                data.substring(0, 18) +
                                "...</a>"
                            );
                        } else {
                            return data;
                        }
                    }
                },
                {
                    data: "ENTERPRISE_ID_NAME",
                    title: "当事主体",
                    width: "15%"
                },
                {
                    data: "ENTITY_TYPE_NAME",
                    title: "主体类型"
                },
                {
                    data: "AREA",
                    title: "区域",
                    width: "12%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        if (
                            data != null &&
                            data != "" &&
                            data != undefined
                        ) {
                            var area = $.fn.Cityname(row.AREA);
                            if (area.length > 18) {
                                return (
                                    "<a id='CASE_NAME" +
                                    row.ID +
                                    "' onmouseover=showPopContent('CASE_NAME" +
                                    row.ID +
                                    "','" +
                                    area +
                                    "')>" +
                                    area.substring(0, 18) +
                                    "...</a>"
                                );
                            } else {
                                return area;
                            }
                        }
                    }
                },
                {
                    data: "ENFORCE_LAW_TIME",
                    title: "执法时间",
                    width: "7%"
                },
                {
                    data: "CREATE_TIME",
                    title: "创建时间",
                    width: "7%"
                },
                {
                    data: "CASE_NAME",
                    name: "caseName",
                    title: "案件名称",
                    visible: false
                }, {
                    data: "PENALTY_SOURCE",
                    name: "penaltySource",
                    title: "行政处罚来源",
                    visible: false
                }, {
                    data: "ENTERPRISE_CODE",
                    name: "enierpriseCode",
                    title: "主体身份码",
                    visible: false
                }, {
                    data: "LEGAL_PERSON",
                    name: "legalPeson",
                    title: "当事人",
                    visible: false
                }, {
                    data: "ENFORCE_LAW_PEOPLE",
                    name: "enforceLawPeople",
                    title: "执法人员",
                    visible: false
                },
                {
                    data: "ID",
                    title: "操作",
                    width: "4%",
                    render: function (data, type, row) {
                        // 模板化列显示内容
                        return (
                            '<a type="button" style="cursor:pointer" data-toggle="modal" data-target="#produceAdminPunishModal" onclick="produceAdminPunishInfo(\'' +
                            data +
                            "')\">查看</a>"
                        ); //转义传值
                    },
                    orderable: false
                }
            ]
        });
    }

    //检查json对象值是否为空
    $scope.checkData = function (o) {
        for (var p in o) {
            if (!o[p]) {
                o[p] = "无";
            }
        }
    }
    //产品信息
    $scope.qryProductGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryProductGrid").parent().show();
        $scope.loadProductList();
    }
    //基地信息
    $scope.qryBaseGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryBaseGrid").parent().show();
        $scope.loadBaseList();
    }
    //批次信息
    $scope.qryBathGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryBathGrid").parent().show();
        $scope.loadBathList();
    }
    //销售信息
    $scope.qrySaleGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qrySaleGrid").parent().show();
        $scope.loadSaleList();
    }
    //收货信息
    $scope.qryCgglGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryCgglGrid").parent().show();
        $scope.loadCgglList();
    }
    //销售打印
    $scope.qrySalePrintGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qrySalePrintGrid").parent().show();
        $scope.loadSalePrintList();
    }
    //库存打印
    $scope.qryStockPrintGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryStockPrintGrid").parent().show();
        $scope.loadStockPrintList();
    }
    //屠宰信息
    $scope.qrySlaughterGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qrySlaughterGrid").parent().show();
        $scope.loadSlaughterList();
    }
    //点击tab改变样式
    $(".tableHead").each(function (i, o) {
        $(o).on("click", function (obj) {
            $(".tableGrid").each(function (i, o) {
                $(o).hide();
            })
            $(".activeTab").removeClass("activeTab");
            $(o).addClass("activeTab");
        })
    })

    //基地巡查
    $scope.qryBaseInspectionGrid = function (o) {

        $($(".tableGrid")[o]).show();
        $("#qryBaseInspectionGrid").parent().show();
        $scope.loadBaseInspectionList();
    }

    //监测信息
    $scope.qryMonitorGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryMonitorGrid").parent().show();
        $scope.loadMonitorList();
    }

    //现场巡查
    $scope.qryetlDailyEnforceGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryetlDailyEnforceGrid").parent().show();
        $scope.loadetlDailyEnforceList();
    }

    //行政处罚
    $scope.qryproduceAdminPunishGrid = function (o) {
        $($(".tableGrid")[o]).show();
        $("#qryproduceAdminPunishGrid").parent().show();
        $scope.loadproduceAdminPunishList();
    }

    //组合批次查询
    window.findCphc = function (hcid) {
        $scope.hcid = hcid
        $("#hcBathList").modal("show");
        $("#qryHcBathGrid").dataTable().api().ajax.reload();
    }
    //销售历史详情
    window.qrySaleDetail = function (id, state) {
        if (state == "T") {
            $scope.state = "退货";
        } else if (state == "H") {
            $scope.state = "换货";
        } else {
            $scope.state = "销售流通";
        }
        $("#saleDetail").modal("show");
        $scope.saleInfo = {};
        if (state != "T" && state != "H") {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/subject/getXsdjAndCustomerById",
                method: "post",
                params: {id: id}
            }).success(function (res) {
                var vo = res.vo;
                $scope.saleInfo.name = vo.name;
                $scope.saleInfo.uname = vo.userName;
                $scope.saleInfo.phone = vo.phone;
                $scope.saleInfo.address = vo.address;
                $scope.saleInfo.customerEntityIdcode = vo.customerEntityIdcode;
                $scope.saleInfo.customerUserIdcode = vo.customerUserIdcode;
                $scope.saleInfo.productXspc = vo.productXspc;

                //产品信息
                $scope.saleInfo.productName = vo.productName;
                $scope.saleInfo.productSort = vo.productSort;
                $scope.saleInfo.harvestUnit = vo.harvestUnit;
                $scope.saleInfo.saleTime = new Date(parseInt(vo.saleTime) + 28800000).toISOString().slice(0, 10);
                $scope.saleInfo.saleAmount = vo.saleAmount;
                $scope.saleInfo.isCirculatesEnd = vo.isCirculatesEnd;
                $scope.saleInfo.traceProof = vo.traceProof;
                $scope.saleInfo.check = "合格";
                if ("Y" == $scope.saleInfo.isCirculatesEnd && $scope.saleInfo.traceProof != null) {
                    $scope.saleInfo.detailTitle = "销售入市详情";
                    $scope.saleInfo.title = "入市追溯码";
                    if (vo.traceProof != "" && vo.traceProof != null) {
                        $scope.saleInfo.code = vo.traceProof;
                    } else {
                        $scope.saleInfo.code = "无";
                    }
                } else {
                    $scope.saleInfo.detailTitle = "销售流通详情";
                    $scope.saleInfo.title = "产品追溯码";
                    if (vo.traceCode != "" && vo.traceCode != null) {
                        $scope.saleInfo.code = vo.traceCode;
                    } else {
                        $scope.saleInfo.code = "无";
                    }
                }
            });
        } else if (state == "T" || state == "H") {
            $http({
                headers: {
                    token: window.localStorage.getItem("token")
                },
                url: "/sofn-qry-web/subject/queryByXsjlId",
                method: "post",
                params: {id: id}
            }).success(function (res) {
                var vo = res.vo;
                $scope.saleInfo.check = "合格";
                if (state == "T") {
                    $scope.saleInfo.detailTitle = "退货详情";
                    $scope.saleInfo.title = "退货原因";
                } else if (state == "H") {
                    $scope.saleInfo.detailTitle = "换货详情";
                    $scope.saleInfo.title = "换货原因";
                }
                //客户信息
                $scope.saleInfo.name = vo.name;
                $scope.saleInfo.uname = vo.userName;
                $scope.saleInfo.phone = vo.phone;
                $scope.saleInfo.address = vo.address;
                $scope.saleInfo.customerEntityIdcode = vo.customerEntityIdcode;
                $scope.saleInfo.customerUserIdcode = vo.customerUserIdcode;
                $scope.saleInfo.productXspc = vo.productXspc;
                //产品信息
                $scope.saleInfo.productName = vo.productName;
                $scope.saleInfo.productSort = vo.productSort;
                $scope.saleInfo.harvestUnit = vo.harverestUnit;
                $scope.saleInfo.saleTime = new Date(parseInt(vo.saleTime) + 28800000).toISOString().slice(0, 10);
                $scope.saleInfo.saleAmount = vo.saleAmount;
                $scope.saleInfo.isCirculatesEnd = vo.isCirculatesEnd;
                $scope.saleInfo.code = vo.xsthyy;
            })
        }
    }


    //附件的下载
    window.downloadFiles = function () {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', "/sofn-qry-web/qryFile/downloadFile");
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'fileUrl');
        input1.attr('value', decodeURI($scope.reCord.punishFiles));
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'fileName');
        input2.attr('value', decodeURI($scope.reCord.punishFilesName));
        $('body').append(form);
        form.append(input1);
        form.append(input2);
        form.submit();
    }

    //查看主体详情
    $scope.subjEntView = function () {
        $state.go("index.content.qry/subject/qrySubjEntView");
    }
    //返回列表页面
    $scope.callBack = function () {
        window.localStorage.removeItem("subjEntId");
        $state.go("index.content.qry/subject/qrySubjEntList");
    }

    //鼠标悬停弹窗显示
    window.showPopContent = function (id, content) {
        $("#" + id).popover({
            animation: true,
            html: false,
            placement: "right",
            trigger: "hover",
            content: content,
            delay: 0,
            container: "body"
        });
        $("#" + id).popover("show");
    };
    //跳转页面-查看基地巡查详情
    window.inspectionView = function (data) {
        window.localStorage.removeItem("baseInspectionId");
        window.localStorage.setItem("baseInspectionId", data);
        window.localStorage.removeItem("inspectionViewEntryType");
        window.localStorage.setItem("inspectionViewEntryType", "2"); //基地巡查详情，从生产经营主体信息进入查看
        $state.go(
            "index.content.qry/supervisionInspection/qryInspectionBaseView"
        );
    };

    //跳转到查看详情页面
    window.subjEntView = function (data) {
        window.localStorage.removeItem("etlDailyEnforceLawID");
        window.localStorage.setItem("etlDailyEnforceLawID", data);
        $state.go("index.content.ales/etl/etlEnterView", {source: "sys"});
    };

    //查看现场巡查详情页面
    window.Checkreport = function (data) {
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/subject/getDailyEnforceLawById",
            method: "post",
            data: {id: data}
        })
            .success(function (data) {
                $scope.reCord = data.data;
                $scope.dailyEnforceLawEnterpriseId =
                    $scope.reCord.enterpriseId;
                if (
                    $scope.reCord.taskBeginTime != null &&
                    $scope.reCord.taskBeginTime != "" &&
                    $scope.reCord.taskBeginTime != undefined
                ) {
                    $scope.beginTime = new Date(
                        parseInt($scope.reCord.taskBeginTime) + 28800000
                    )
                        .toISOString()
                        .slice(0, 10);
                    //      $scope.endTime = new Date(parseInt($scope.reCord.taskEndTime)+28800000).toISOString().slice(0, 10);
                }
                if (
                    $scope.reCord.areaId != null &&
                    $scope.reCord.areaId != "" &&
                    $scope.reCord.areaId != undefined
                ) {
                    $scope.areaId = $.fn.Cityname($scope.reCord.areaId);
                }
                if (
                    $scope.reCord.enforceLawResultFlag != null &&
                    $scope.reCord.enforceLawResultFlag != "" &&
                    $scope.reCord.enforceLawResultFlag != undefined
                ) {
                    if ($scope.reCord.enforceLawResultFlag == "1") {
                        $scope.reCord.enforceLawResultFlag = "合格";
                    }
                    if ($scope.reCord.enforceLawResultFlag == "2") {
                        $scope.reCord.enforceLawResultFlag = "不合格";
                    }
                    if ($scope.reCord.enforceLawResultFlag == "3") {
                        $scope.reCord.enforceLawResultFlag = "整改";
                    }
                    if ($scope.reCord.enforceLawResultFlag == "4") {
                        $scope.reCord.enforceLawResultFlag = "其他";
                    }
                }
                $scope.reCord.address = $scope.reCord.enterpriseAddress;
                $scope.reCord.peson = $scope.reCord.principal;
                if ($scope.reCord.scenePictures != null && $scope.reCord.scenePictures != undefined && $scope.reCord.scenePictures != "") {
                    var array = $scope.reCord.scenePictures.split("*##*");
                    for (var i = 0; i < array.length; i++) {
                        if (i > 0) {
                            $("#scenePictures").append(
                                '<img style="padding-right: 10px; width: 80px;" src="' +
                                "/sofn-sys-web/sysTemplate/downPic?fileUrl=" +
                                array[i] +
                                '" alt=""/>'
                            );
                        } else {
                            $("#scenePictures").html(
                                '<img style="padding-right: 10px; width: 80px;" src="' +
                                "/sofn-sys-web/sysTemplate/downPic?fileUrl=" +
                                array[i] +
                                '" alt=""/>'
                            );
                        }
                    }
                }
                // };
                if ($scope.reCord.principalSignatures != null && $scope.reCord.principalSignatures != undefined && $scope.reCord.principalSignatures != "") {
                    var array = $scope.reCord.principalSignatures.split("*##*");
                    ///  for (var i = 0; i < array.length; i++) {
                    $("#principalSignatures").html(
                        '<img style="padding-right: 10px; width: 80px" src="' +
                        "/sofn-sys-web/sysTemplate/downPic?fileUrl=" +
                        array[0] +
                        '" alt=""/>'
                    );
                }
                // };
            })
            .error(function (msg) {
                console.log(msg);
                
            });
    };

    $scope.punishEnterpriseId = "";
    window.produceAdminPunishInfo = function (data) {
        console.log("传入数据值====" + data);
        $http({
            headers: {
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-qry-web/subject/getPunishiById",
            method: "post",
            params: {id: data}
        })
            .success(function (data) {
                $scope.reCord = data.data;
                console.log($scope.reCord);
                $scope.punishEnterpriseId = $scope.reCord.id;
                console.log(
                    "punishEnterpriseId====>>>>" + $scope.punishEnterpriseId
                );
            })
            .error(function (data) {
               
            });
    };

    window.viewOrganTask=function(obj){
        window.localStorage.setItem("ID",obj);
        // $scope.ID = obj;
        $("#montiorInfoModal").modal("show");
        //清理数据
        var tableName = $('#productList');
        if ($.fn.dataTable.isDataTable(tableName)) {
            tableName.DataTable().clear();
            tableName.DataTable().destroy();
        }
        //加载数据
        $("#productList").dataTable({
            processing : true, // 加载时提示
            serverSide : false, // 分页，是否服务器端处理
            ordering : false,
            searching : false,
            bProcessing: true,
            paging:false,
            dom : '<"top">rt<"bottom"pli><"clear">',
            autoWidth : false,
            ajax : {
                url : "/sofn-qry-web/subject/getMontiorChild",
                dataSrc:function(json){
                    return json.data;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function (params) {
                    params.id = obj;
                },
                type:"post"
            },
            columns : [
                {
                    data : "checkProject",
                    title : "检测项目"
                },
                {
                    data : "checkNum",
                    title : "检测值",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(" " == data){
                            return '<a  value="'+"ND"+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">ND</a>';
                        }else{
                            return '<a  value="'+data+'" type="text" name="wu" class="Sup_contain_section_contain_input" onblur="my123('+meta.row+","+row.judgeNum+')">'+data+'</a>';
                        }
                    },
                },
                {
                    data : "judgeNum",
                    title : "限量值"
                },
                {
                    data : "unit",
                    title : "单位"
                },
                {
                    data : "result",
                    title : "抽检结果",
                    render : function(data, type, row) {
                        if ("-1" == data) {
                            return "暂未检测";
                        } else if ("1" == data) {
                            return "合格";
                        } else if ("0" == data){
                            return "不合格";
                        }else if ("2" == data) {
                            return "不作判定";
                        }
                    }
                },
                {
                    data : "lod",
                    title : "LOD",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(null == data){
                            return '<input type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        }else{
                            return '<input type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                        }
                    }
                },
                {
                    data : "loo",
                    title : "LOQ",
                    render : function(data, type, row,meta) { // 模板化列显示内容
                        if(null == data){
                            return '<input type="text" value="" class="Sup_contain_section_contain_input" )"/>';
                        }else{
                            return '<input type="text" value="'+data+'" class="Sup_contain_section_contain_input" )"/>';
                        }
                    }
                }
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
    }

})