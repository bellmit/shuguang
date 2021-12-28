var myapp = angular.module("myapp", []).controller("ttsScltxxcjProductAdd", function($scope, $http, $state) {
    var isType = {};
    $("#ToCheck").click(function() {
        $("#productList").dataTable().api().ajax.reload();
    });
    $("select[name='status']").attr('value', 'Y');
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        method: 'POST',
        url: '/sofn-tts-web/ttsScltxxcjProduct/getIndustryInfo'
    }).success(function(data) {
        $scope.activities = data.list;
        if (data.list != null) {
            for (var i in data.list) {
                isType[data.list[i].dictValue] = data.list[i].dictName;
            }
        }
    })



    //新增产品管理信息
    $scope.ttsScltxxcjProduct = {};
    $scope.addTtsScltxxcjProduct = function() {
            var industry = $("#pro_industry").val();
            var name = $("input[name='name']").val();
            var status = $("select[name='status']").val();
            var typeName = $("#typeName").val();
            if (industry == null || industry == '' || name == null || name == '' || status == null || status == '' || typeName == null || typeName == '') {
                jBox.tip("标注有*号的栏不能为空或是请选择状态", 'info');
                return;
            }
            $scope.ttsScltxxcjProduct.industry = $("#pro_industry").val();
            $scope.ttsScltxxcjProduct.name = $("input[name='name']").val();
            $scope.ttsScltxxcjProduct.productCode = $("#pro_productCode").val();
            $scope.ttsScltxxcjProduct.type = $("#type").val();
            $scope.ttsScltxxcjProduct.typeName = $("#typeName").val();
            $scope.ttsScltxxcjProduct.model = $("input[name='model']").val();
            $scope.ttsScltxxcjProduct.picture = $("input[name='picture']").val();
            $scope.ttsScltxxcjProduct.status = $("select[name='status']").val();
            $scope.ttsScltxxcjProduct.selfCheck = $("input[name='selfCheck']").val();
            $scope.ttsScltxxcjProduct.authentication = $("input[name='authentication']").val();
            $scope.ttsScltxxcjProduct.ip = $("input[name='ip']").val();
            $scope.ttsScltxxcjProduct.delFlag = $("input[name='delFlag']").val();
            $http({
                url: "/sofn-tts-web/ttsScltxxcjProduct/addTtsScltxxcjProduct?id=" + $.parseJSON(window.localStorage.getItem("entityInfo")).data.id,
                data: $scope.ttsScltxxcjProduct,
                method: "post",
                headers: {
                    token: window.localStorage.getItem("token")
                }
            }).success(function(data) {
                if (data.httpCode == 200) {
                    jBox.tip("保存成功", 'info');
                } else {
                    jBox.tip("保存失败", 'error');
                }
                $state.go("index.content.tts/TtsScltxxcjProduct/TtsScltxxcjProductlist");
            }).error(function() {
                jBox.tip("保存失败", 'error');
            })
        }
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
        var submit = null;
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
                render: function(data, type, row) {
                    // 数据操作选择项
                    var name = row.name;
                    var gbCode = row.gbCode;
                    var productCode = row.productCode;
                    var productName = row.parentName;
                    var pro = { name: name, gbCode: gbCode, productCode: productCode, productName: productName };
                    return "<button class='gui-btn' onclick='findProduct(" + JSON.stringify(pro) + ")'>选定产品</button>"



                }
            }]
        }).on('shown.bs.tab', function(e) {
            $.fn.dataTable.tables({ visible: true, api: true }).columns.adjust();
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

})
