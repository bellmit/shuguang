//    angularjs数据传输
var myapp = angular.module("myapp", []);
var mytype = "";
var myend = "";
myapp.controller("ttsScltxxcjProductUpdate", function($scope, $http, $state) {
    var isType = {};
    $scope.ttsScltxxcjProduct = {};
    $http({
        method: 'POST',
        url: '/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo',
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        $scope.activities = data.list;
        if (data.list != null) {
            for (var i in data.list) {
                isType[data.list[i].dictValue] = data.list[i].dictName;
            }
        }
    })
    $http({
        url: "/sofn-tts-web/ttsScltxxcjProduct/queryById",
        method: "post",
        data: {
            id: window.localStorage.getItem("tts_ttsScltxxcjProduct_id")
        },
        headers: {
            token: window.localStorage.getItem("token")
        }
    }).success(function(data) {
        $("select[name='industry']").val(data.ttsScltxxcjProduct.industry);
        $("input[name='name']").val(data.ttsScltxxcjProduct.name);
        $("#pro_productCode").val(data.ttsScltxxcjProduct.productCode);
        $("input[name='type']").val(data.ttsScltxxcjProduct.type);
        $("#typeName").val(data.ttsScltxxcjProduct.typeName);
        $("#pro_status").val(data.ttsScltxxcjProduct.status);
        //public filed
        $("input[name='delFlag']").val(data.ttsScltxxcjProduct.delFlag);
        $("input[name='createBy']").val(data.ttsScltxxcjProduct.createBy);
        $("input[name='createTime']").val(data.ttsScltxxcjProduct.createTime);
    });
    //
    $scope.updateTtsScltxxcjProduct = function() {
        var industry = $("#pro_industry").val();
        var name = $("input[name='name']").val();
        var status = $("select[name='status']").val();
        var typeName = $("#typeName").val();
        if (industry == null || industry == '' || name == null || name == '' || status == null || status == '' || typeName == null || typeName == '') {
            jBox.tip("标注有*号的栏不能为空或是请选择状态", 'info');
            return;
        }

        $scope.ttsScltxxcjProduct.industry = $("#pro_industry").val();
        $scope.ttsScltxxcjProduct.name = $("#name").val();
        $scope.ttsScltxxcjProduct.type = $("#type").val();
        $scope.ttsScltxxcjProduct.typeName = $("#typeName").val();
        $scope.ttsScltxxcjProduct.productCode = $("#pro_productCode").val();
        $scope.ttsScltxxcjProduct.status = $("#status").val();
        $scope.ttsScltxxcjProduct.delFlag = $("input[name='delFlag']").val();
        $scope.ttsScltxxcjProduct.id = window.localStorage.getItem("tts_ttsScltxxcjProduct_id");
        $http({
            url: "/sofn-tts-web/ttsScltxxcjProduct/updateTtsScltxxcjProduct?id=" + $.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
            data: $scope.ttsScltxxcjProduct,
            method: "post",
            //post传输方式   头信息
            headers: {
                token: window.localStorage.getItem("token")
            }
        }).success(function(data) {
            if (data.httpCode == 200) {
                jBox.tip("修改成功", 'info');
            } else {
                jBox.tip("修改失败", 'error');
            }
            $state.go("index.content.tts/TtsScltxxcjProduct/TtsScltxxcjProductlist");
        }).error(function() {
            jBox.tip("修改失败", 'error');
        })
    };
    //清楚缓存
    $scope.RemoveLocal = function() {
        window.localStorage.removeItem("id");
    };

    /**
     * 重新选择行业时，清空之前所选的产品名称
     */
    window.clearPro = function() {
        $("#name").val("");
        $("#typeName").val("");
    }
        //modal 产品信息
    window.findProductInfo = function() {
        var hangye = $("#pro_industry").val();
        if (hangye == null || hangye == "") {
            jBox.tip("请先选择所属行业", 'info');
            return;
        }
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
                url: "/sofn-tts-web/ttsScltxxcjProduct/getProductPageInfo",
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
                    var entityInfo = $.parseJSON(window.localStorage.getItem("entityInfo"));
                    params.id = entityInfo.data.id;
                    params.industry = $("#pro_industry").val();
                    params.keyWord = $("input[name='keyWord']").val();
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                type: "post"
            },
            columns: [{
                data: "typeId",
                title: "所属行业",
                render: function(data, type, row) {
                    return isType[row.typeId];
                }
            }, {
                data: "name",
                title: "名称"
            }, {
                data: "id",
                title: "操作",
                className:'text-center',
                render: function(data, type, row) {
                    // 数据操作选择项
                    var name = row.name;
                    var gbCode = row.gbCode;
                    var productCode = row.productCode;
                    var productName = row.parentName;
                    var pro = { name: name, gbCode: gbCode, productCode: productCode, productName: productName };
                    return "<button class='gui-btn btn-small' onclick='findProduct(" + JSON.stringify(pro) + ")'>选定产品</button>"
                }
            }]
        });
    }

    /**
     * 将产品的id，名称，行业类型进行插入表单
     */
    window.findProduct = function(product) {
        $("input[name='name']").val(product.name);
        $("#type").val(product.gbCode);
        $("#pro_productCode").val(product.productCode);
        $("#typeName").val(product.productName);
        $("#product_info").modal('hide');
        jBox.tip("选定成功", 'info');
    }
    $("#ToCheck").click(function() {
        $("#productList").dataTable().api().ajax.reload();
    });
});
