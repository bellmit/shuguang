var myapp = angular.module("myapp", []);
myapp.controller("suggestList", function($scope, $http, $state) {
    $scope.queryCondition = {
        regionCode:"",
        suggestionType:"",
        feedbackTimeBeginning:"",
        feedbackTimeEnding:""
    }

    $scope.queryResult = {
        enterpriseName:"",
        area:"",
        time:"",
        type:"",
        title:"",
        content:"",
        attachName:"",
        fileName:"",
        fileUrl:""
    }

    $scope.initRegionSelect = function() {
        $.fn.mycity("provinceSelect","citySelect","countySelect", null); // 初始化区域下拉框选项
    }
    $scope.initRegionSelect();
    var $countySelect = $('#countySelect');
    var $citySelect = $('#citySelect');
    var $provinceSelect = $('#provinceSelect');
    /**
     * 分页展示数据
     */
    //bootstrap表格
    $("#grid").dataTable({
        ajax: {
            url: "/sofn-sys-web/sysSuggestion/queryList",
            headers: {token: window.localStorage.getItem("token")},
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
                if ($countySelect.val() != '') {
                    $scope.queryCondition.regionCode = $countySelect.val();
                } else if ($citySelect.val() != '') {
                    $scope.queryCondition.regionCode = $citySelect.val();
                } else if ($provinceSelect.val() != '') {
                    $scope.queryCondition.regionCode = $provinceSelect.val();
                } else {
                    $scope.queryCondition.regionCode = "";
                }

                params.regionCode = $scope.queryCondition.regionCode;
                params.suggestionType = $scope.queryCondition.suggestionType;
                params.feedbackTimeBeginning = $scope.queryCondition.feedbackTimeBeginning;
                params.feedbackTimeEnding = $scope.queryCondition.feedbackTimeEnding;
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
            width: "5%"
        }, {
            data: "typeDescribe",
            title: "类别",
            className: "text-center",
            width: "12%"
        }, {
            data: "title",
            title: "标题",
            className: "text-center",
            width: "30%"
        }, {
            data: "enterpriseName",
            title: "反馈主体",
            className: "text-center",
            width: "24%"
        }, {
            data: "createTime",
            title: "时间",
            render: function(data, type, row) {
                return new Date(data).format("yyyy-MM-dd");
            },
            className: "text-center",
            width: "12%"
        }, {
            data: "id",
            title: "操作",
            render: function(data, type, row) {
                // 数据操作选择项
                var opTagString = '<a style="cursor: pointer" class="color mr15" onclick="openSuggestion(\'' + data + '\')">查看</a>';
                return opTagString
            },
            className: "text-center",
            width: "18%"
        }]
    });
    // 查询优化建议
    $scope.queryList = function() {
        $("#grid").dataTable().api().ajax.reload();
    }

    // 日历插件选择日期后的回调函数
    window.timeBeginningOnPicked = function(dp) {
        $scope.$evalAsync(function(){
            $scope.queryCondition.feedbackTimeBeginning = dp.cal.getDateStr().trim();
        });
    }
    // 日历插件清空日期后的回调函数
    window.timeBeginningOnCleared = function() {
        $scope.$evalAsync(function(){
            $scope.queryCondition.feedbackTimeBeginning = "";
        });
    }
    window.timeEndingOnPicked = function(dp) {
        $scope.$evalAsync(function(){
            $scope.queryCondition.feedbackTimeEnding = dp.cal.getDateStr().trim();
        });
    }
    window.timeEndingOnCleared = function() {
        $scope.$evalAsync(function(){
            $scope.queryCondition.feedbackTimeEnding = "";
        });
    }

    // 查看优化建议
    window.openSuggestion = function(id) {

        //$("#view_modal").modal("show");
        $http({
            url: "/sofn-sys-web/sysSuggestion/queryOne",
            method: "post",
            data: {"id": id},
            headers: {'Content-Type': 'application/x-www-form-urlencoded',
                      'token': window.localStorage.getItem("token")},
            transformRequest: function (data) {
                return $.param(data);
            }
        }).success(function(json) {
            $scope.queryResult.enterpriseName = json.data.enterpriseName;
            $scope.queryResult.area = $.fn.Cityname(json.data.area);
            $scope.queryResult.type = json.data.typeDescribe;
            $scope.queryResult.time = new Date(json.data.createTime).format("yyyy-MM-dd");
            $scope.queryResult.title = json.data.title;
            $scope.queryResult.content = json.data.content;
            $scope.queryResult.attachName = json.data.attachName;
            $scope.queryResult.fileName = encodeURIComponent(json.data.attachName);
            $scope.queryResult.fileUrl = encodeURIComponent(json.data.attachPath);

            $("#view_modal").modal("show");
        }).error(function(json, status) {
            if (status == 401) {
                // swal({
                //     title: "长时间未操作，请重新登录系统",
                //     type: "warning",
                //     timer: 2500,
                //     showConfirmButton: false
                // }, function(e){
                //     window.location.href = "/";
                // });
            } else {
                jBox.tip('获取数据失败', 'error');
            }
        })
    }
    // 关闭建议查看窗口
    $scope.closeModal = function() {
        $("#view_modal").modal("hide");
    }
});