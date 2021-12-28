/**
 * Created by Administrator on 2017/11/10/010.
 */
var myapp = angular.module("myapp", []);
myapp.controller("testPesticideResiduesList", function($scope, $http, $compile, $state) {
    $("#addModalForm").bootstrapValidator();
    $scope.addUpdateModal = {
        title:"",
        id:"",
        testItemId:"",
        testItemName:"",
        selectedStandardCode:"",
        standardCodeList:[],
        testObjectId:"",
        testObjectName:"",
        upperBound:"",
        unitMeasure:"",
        remark:""
    }
    $scope.testItemsModal = {
        selectedIndex:-1,
        selectedData:{},
        list:[],
        itemName:"",
        showLoader:false,
        currentPageNum:0,
        navigatepageNums:[],
        isFirstPage:true,
        isLastPage:true,
        firstPage:0,
        prePage:0,
        nextPage:0,
        lastPage:0,
        pages:0
    }
    $scope.testObjectsModal = {
        typeId:"",
        productTypes:[
            {name:"全部", value:""},
            {name:"种植业", value:"01"},
            {name:"畜牧业", value:"02"},
            {name:"水产品", value:"03"},
        ],
        selectedIndex:-1,
        selectedData:{},
        list:[],
        objectName:"",
        showLoader:false,
        currentPageNum:0,
        navigatepageNums:[],
        isFirstPage:true,
        isLastPage:true,
        firstPage:0,
        prePage:0,
        nextPage:0,
        lastPage:0,
        pages:0
    }
    // 菜单权限
    $scope.menuAuthority = getAuthorityByMenuName('采集指标管理');
    // 菜单读写权限值
    $scope.readWriteAuthority = MENU_READWRITE_AUTHORITY;
    // 异步请求错误处理函数
    window.ajaxErrorHandler = function(statusCode) {
        if (statusCode == 401) {
            // swal({
            //     title: "登录超时",
            //     text: "由于您长时间未操作,请重新登录!",
            //     type: "warning",
            //     timer: 2500,
            //     showConfirmButton: true
            // }, function(e){
            //     window.location.href = "/";
            // });
        } else {
            jBox.tip('返回数据不正常，操作失败', 'error');
        }
    }
    // 设置新增修改模态窗口的下拉框需要的标准选项数据
    $http({
        url: "/sofn-sys-web/sysPesticideResidue/getAllStandardCodes",
        method: "post",
        data: {},
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        transformRequest: function (data) {
            return $.param(data);
        }
    }).success(function(json) {
        if (json.httpCode == "200") {
            $scope.addUpdateModal.standardCodeList = json.data;
        } else {
            alert(json.msg);
        }
    }).error(function(json) {
        alert('获取标准号数据时' + json.msg);
    })

    /**
     * 分页展示数据
     */
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysPesticideResidue/queryList",
            headers: {
                token: window.localStorage.getItem("token")
            },
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
                params.standardCode = $scope.queryStandardCode;
                params.testItemName = $scope.queryTestItemName;
                params.testObjectName = $scope.queryTestObjectName;
            },
            type: "post"
        },
        columns: [{
            title: function(data, type, row) {
                return '<input type="checkbox" name="checkAll" id="checkAll">';
            },
            data: "checkOne",
            render: function(data, type, row) {
                if (type === 'display') {
                    return '<input type="checkbox" name="checkOne">';
                }
                return data;
            },
            className: "text-center",
            width: "5%"
        }, {
            title: "序号",
            data: function(data, type, row, meta) {
                return meta.row + 1
            },
            className: "text-center",
            width: "4%"
        }, {
            data: "standardCode",
            title: "标准号",
            className: "text-center",
            width: "8%"
        }, {
            data: "itemName",
            title: "检测项",
            className: "text-center",
            width: "30%"
        }, {
            data: "testObjectId",
            title: "检测对象代码",
            className: "text-center",
            width: "8%"
        }, {
            data: "testObjectName",
            title: "检测对象",
            className: "text-center"
        }, {
            data: "upperBound",
            title: "最大限量",
            className: "text-center",
            width: "5%"
        }, {
            data: "unitMeasure",
            title: "单位",
            className: "text-center",
            width: "5%"
        }, {
            data: "remark",
            title: "说明",
            className: "text-center"
        }, {
            data: "id",
            title: "操作",
            render: function(data, type, row) {
                // 数据操作选择项
                return '<a style="cursor: pointer" class="color mr15" onclick="update(\'' + data + '\')">修改</a>';
            },
            className: "text-center",
            width: "5%"
        }]
    });

    // 点击查询按钮事件处理
    $scope.queryList = function() {
        $("#grid").dataTable().api().ajax.reload();
    }
    /**
     * 公用的获取复选框选中行函数
     * @param tableId  表格标签的id名称（不带#号）
     */
    $scope.getSelectedRowsIds = function(tableId) {
        var rows = $("#" + tableId).DataTable().rows('.selected').data();
        var length = rows.length;
        if (length <= 0) {
            alert("请至少选择一行记录！");
            return false;
        }
        var ids = [];
        for (var i = 0; i < length; i++) {
            ids.push(rows[i].id);
        }

        return ids;
    }
    /**
     * 公用的ajax删除操作函数
     * @param url   删除操作请求地址
     * @param condition   删除条件对象
     * @param successCallback 删除成功后的回调函数
     */
    window.ajaxDelete = function(url, condition, successCallback) {
        $http({
            url: url,
            method: "post",
            data: condition,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                alert(json.data.deleteRows + "条数据删除成功");
                successCallback();
            } else {
                alert(json.msg + "，删除失败");
            }

        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }
    // 删除按钮事件处理
    $scope.delete = function() {
        var idArray = $scope.getSelectedRowsIds("grid");
        if (idArray != false) {
            if (confirm('是否删除已选择数据？')) {
                var condition = {
                    "ids": idArray.toString(),
                    "token": window.localStorage.getItem("token")
                }
                ajaxDelete("/sofn-sys-web/sysPesticideResidue/delete", condition, function() {
                    $("#grid").dataTable().api().ajax.reload();
                });
            }
        }
    }
    // 新增按钮事件处理
    $scope.addModal = function() {
        $('#addModalForm').data('bootstrapValidator').destroy();
        $("#addModalForm").bootstrapValidator();
        $scope.addUpdateModal.title = "新增检测标准限量值";
        $scope.addUpdateModal.id = "";
        $scope.addUpdateModal.selectedStandardCode = "";
        $scope.addUpdateModal.testItemId = "";
        $scope.addUpdateModal.testItemName = "";
        $scope.addUpdateModal.testObjectId = "";
        $scope.addUpdateModal.testObjectName = "";
        $scope.addUpdateModal.upperBound = "";
        $scope.addUpdateModal.unitMeasure = "mg/kg";
        $scope.addUpdateModal.remark = "";
        $("#pr_add_modal").modal("show");
    }

    // 修改单击事件处理
    window.update = function(id) {
        if ($scope.menuAuthority != MENU_READWRITE_AUTHORITY) {
            swal({
                title: "权限不足",
                text: "抱歉你没有修改权限!",
                type: "warning",
                showConfirmButton: true
            });
            return false;
        }
        $('#addModalForm').data('bootstrapValidator').destroy();
        $("#addModalForm").bootstrapValidator();
        $http({
            url: "/sofn-sys-web/sysPesticideResidue/queryOne",
            method: "post",
            data: {"id":id},
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'token': window.localStorage.getItem("token")
            },
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                $scope.addUpdateModal.title = "修改检测标准限量值";
                $scope.addUpdateModal.id = id;
                $scope.addUpdateModal.selectedStandardCode = json.data.standardCode;
                $scope.addUpdateModal.testItemId = json.data.testItemId;
                $scope.addUpdateModal.testItemName = json.data.itemName;
                $scope.addUpdateModal.testObjectId = json.data.testObjectId;
                $scope.addUpdateModal.testObjectName = json.data.testObjectName;
                $scope.addUpdateModal.upperBound = json.data.upperBound;
                $scope.addUpdateModal.unitMeasure = json.data.unitMeasure;
                $scope.addUpdateModal.remark = json.data.remark;
                $("#pr_add_modal").modal("show");
            } else {
                alert(json.msg);
            }
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        });
    }

    // 新增修改模态窗口的取消按钮事件处理
    $scope.addUpdateModalCancel = function() {
        $("#pr_add_modal").modal("hide");
    }
    // 新增修改模态窗口的保存按钮事件处理
    $scope.addUpdateModalSave = function() {
        // 表单验证，验证通过再提交数据
        if (!$("#addModalForm").data('bootstrapValidator').validate().isValid()) {
            return false;
        }
        var ajaxURL = "";
        if ($scope.addUpdateModal.id != "") {
            ajaxURL = "/sofn-sys-web/sysPesticideResidue/update";
        } else {
            ajaxURL = "/sofn-sys-web/sysPesticideResidue/add";
        }
        $http({
            url: ajaxURL,
            method: "post",
            data: { "id": $scope.addUpdateModal.id,
                "testItemId": $scope.addUpdateModal.testItemId,
                "testObjectId": $scope.addUpdateModal.testObjectId,
                "testObjectName": $scope.addUpdateModal.testObjectName,
                "upperBound": $scope.addUpdateModal.upperBound,
                "unitMeasure": $scope.addUpdateModal.unitMeasure,
                "remark": $scope.addUpdateModal.remark,
                "token": window.localStorage.getItem("token")
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            if (json.httpCode == "200") {
                alert("保存成功");
                $("#pr_add_modal").modal("hide");
                $("#grid").dataTable().api().ajax.reload();
            } else {
                alert(json.msg);
                $("#pr_add_modal").modal("hide");
            }
        }).error(function(json, status) {
            ajaxErrorHandler(status);
        })
    }

    /**
     * 重置已选择的选项
     * @param data 已选项数据
     */
    $scope.resetSelected = function(data) {
        data.selectedIndex = -1;
        data.selectedData = {};
    }
    // 打开选择检测项的模态窗口
    $scope.openItemsSelect = function() {
        if ($scope.addUpdateModal.selectedStandardCode == '') {
            alert("请先选择标准号！");
        } else {
            $scope.resetSelected($scope.testItemsModal);
            $scope.loadTestItems(1);
            $("#items_select_modal").modal("show");
        }
    }

    /**
     * 当前选中数据
     * @param selectedStatus 保存选择状态的对象
     * @param index 选项索引
     * @param itemData 选项数据
     */
    $scope.selectItem = function(selectedStatus, index, itemData) {
        if (selectedStatus.selectedIndex == index) {
            $scope.resetSelected(selectedStatus);
        } else {
            selectedStatus.selectedIndex = index;
            selectedStatus.selectedData = itemData;
        }
    };
    /**
     * 分页查询
     *
     * @param url 查询URL
     * @param pageNum 页号
     * @param queryParam 查询参数对象
     * @param result 分页查询结果对象
     */
    $scope.pagingQuery = function(url, pageNum, queryParam, result) {
        queryParam.pageNum = pageNum;
        $http({
            url: url,
            method: "post",
            data: queryParam,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function (json) {
            result.currentPageNum = json.data.pageNum;
            result.navigatepageNums = json.data.navigatepageNums;
            result.isFirstPage = json.data.isFirstPage;
            result.isLastPage = json.data.isLastPage;
            result.list = json.data.list;
            result.firstPage = json.data.firstPage;
            result.lastPage = json.data.lastPage;
            result.prePage = json.data.prePage;
            result.nextPage = json.data.nextPage;
            result.pages = json.data.pages;
        }).error(function (json) {
            alert(json.msg);
        })
    }

    // 分页加载检测项数据
    $scope.loadTestItems = function(pageNum) {
        var url = "/sofn-sys-web/sysPesticideResidue/getTestItems";
        var queryParam = {
            "standardCode": $scope.addUpdateModal.selectedStandardCode,
            "itemName": $scope.testItemsModal.itemName
        };
        $scope.pagingQuery(url, pageNum, queryParam, $scope.testItemsModal);
    }
    // 加载检测项首页数据
    $scope.firstPageTestItems = function() {
        if (!$scope.testItemsModal.isFirstPage) {
            $scope.loadTestItems(1);
        }
    }
    // 加载检测项上一页数据
    $scope.prePageTestItems = function() {
        if (!$scope.testItemsModal.isFirstPage) {
            $scope.loadTestItems($scope.testItemsModal.prePage);
        }
    }
    // 加载检测项下一页数据
    $scope.nextPageTestItems = function() {
        if (!$scope.testItemsModal.isLastPage) {
            $scope.loadTestItems($scope.testItemsModal.nextPage);
        }
    }
    // 加载检测项尾页数据
    $scope.lastPageTestItems = function() {
        if (!$scope.testItemsModal.isLastPage) {
            $scope.loadTestItems($scope.testItemsModal.pages);
        }
    }

    // 检测项模态窗口的查询按钮事件处理
    $scope.queryTestItems = function() {
        $scope.resetSelected($scope.testItemsModal);
        $scope.loadTestItems(1);
    }

    /**
     * 判断指定页号是否为当前页
     * @param pageNum 页号
     * @param currentPageNum 当前页号
     * @returns {boolean}
     */
    $scope.isCurrentPage = function(currentPageNum, pageNum) {
        return currentPageNum == pageNum;
    }
    // 检测项模态窗口取消按钮事件处理
    $scope.itemsModalCancel = function() {
        $scope.resetSelected($scope.testItemsModal);
        $("#items_select_modal").modal("hide");
    }
    // 检测项模态窗口确定按钮事件处理
    $scope.itemsModalOK = function() {
        $scope.addUpdateModal.testItemId = $scope.testItemsModal.selectedData.id;
        $scope.addUpdateModal.testItemName = $scope.testItemsModal.selectedData.itemName;
        $("#items_select_modal").modal("hide");
        $('#addModalForm').data('bootstrapValidator').destroy();
        $("#addModalForm").bootstrapValidator();
    }

    // 打开检查对象选择模态框
    $scope.openObjectsSelect = function() {
        $scope.resetSelected($scope.testObjectsModal);
        $scope.testObjectsModal.typeId = "";
        $scope.testObjectsModal.objectName = "";
        $scope.loadTestObjects(1);
        $("#objects_select_modal").modal("show");
    }
    // 检测对象选择模态框的查询按钮事件处理
    $scope.queryTestObjects = function() {
        $scope.resetSelected($scope.testObjectsModal);
        $scope.loadTestObjects(1);
    }
    // 检测对象选择模态框的取消按钮事件处理
    $scope.objectsModalCancel = function() {
        $scope.resetSelected($scope.testObjectsModal);
        $("#objects_select_modal").modal("hide");
    }
    // 检测对象选择模态框的确定按钮事件处理
    $scope.objectsModalOK = function() {
        $scope.addUpdateModal.testObjectId = $scope.testObjectsModal.selectedData.productCode;
        $scope.addUpdateModal.testObjectName = $scope.testObjectsModal.selectedData.name;
        $("#objects_select_modal").modal("hide");
        $('#addModalForm').data('bootstrapValidator').destroy();
        $("#addModalForm").bootstrapValidator();
    }
    // 分页加载检测对象数据
    $scope.loadTestObjects = function(pageNum) {
        var url = "/sofn-sys-web/sysPesticideResidue/getTestObjects";
        var queryParam = {
            "typeId": $scope.testObjectsModal.typeId,
            "productName": $scope.testObjectsModal.objectName
        };
        $scope.pagingQuery(url, pageNum, queryParam, $scope.testObjectsModal);
    }
    // 加载检测对象首页数据
    $scope.firstPageTestObjects = function() {
        if (!$scope.testObjectsModal.isFirstPage) {
            $scope.loadTestObjects(1);
        }
    }
    // 加载检测对象上一页数据
    $scope.prePageTestObjects = function() {
        if (!$scope.testObjectsModal.isFirstPage) {
            $scope.loadTestObjects($scope.testObjectsModal.prePage);
        }
    }
    // 加载检测对象下一页数据
    $scope.nextPageTestObjects = function() {
        if (!$scope.testObjectsModal.isLastPage) {
            $scope.loadTestObjects($scope.testObjectsModal.nextPage);
        }
    }
    // 加载检测对象尾页数据
    $scope.lastPageTestObjects = function() {
        if (!$scope.testObjectsModal.isLastPage) {
            $scope.loadTestObjects($scope.testObjectsModal.pages);
        }
    }
});