var myapp = angular.module("myapp", []);
myapp.controller("hue", function($scope, $http, $state) {
    var vm = this;
    $scope.currentTable = {};
    $scope.xAxis = [];
    $scope.tableSort = 'asc';
    $scope.xAxisList = {
        SYS_ARGI_PRODUCT: [{value:"type_id", name:'类型'}],
        SYS_ORGANIZATION: [{value:"pro", name:"省"}, {value:"level_name", name:"机构级别"}, {value:"org_type", name:"机构类型"}],
        TTS_SCLTXXCJ_COMPLAINT_V2: [{value:"area_id", name:"区域"}, {value:"complaint_ent_name", name:'投诉企业名称'}, {value:"type", name:'问题类型'}],
        SYS_TEST_STANDARD:[{value:"STANDARD_NAME", name:"标准"}]
    };
    //获取所有表名
    $http({
        headers: {
            token: window.localStorage.getItem("token")
        },
        url: "/sofn-etl-query/etlQuery/getTables",
        method: "post"
    }).then(
        function(res) {
            if (res.data.code != 200) {
                swal("", "获取所有表名出错", "error");
                return;
            }
            $scope.tables = res.data.res;
            $scope.currentTable = res.data.res[0];
            $scope.xAxis = $scope.xAxisList[res.data.res[0].value];
            if($scope.xAxis){
                $scope.tablekey = $scope.xAxis[0] ? $scope.xAxis[0].value : null;
            }else{
                $scope.xAxis = [];
            }
            getDetailColumns(getDetail);
            angular.element('.gui-list .active').trigger('click');
        },
        function(err) {
            swal("", "请求异常", "error");
        }
    );

    //选取表
    $scope.selectedTable = function(table, $event) {
        $($event.currentTarget)
            .addClass("active")
            .siblings()
            .removeClass("active");
        $scope.currentTable = table;
        $scope.xAxis = $scope.xAxisList[table.value];
        if($scope.xAxis){
            $scope.tablekey = $scope.xAxis[0] ? $scope.xAxis[0].value : null;
        }else{
            $scope.xAxis = [];
        }
        getDetailColumns(function() {
            var table = undefined;
            if ($.fn.DataTable.isDataTable( '#resource' ) ) {
                table = $("#resource").DataTable();
                table.destroy();
                $("#resource").empty(); // empty in case the columns change
            }
            getDetail();
        }, function () {
            if ($.fn.DataTable.isDataTable( '#resource' ) ) {
                table = $("#resource").DataTable();
                table.destroy();
            }
            $scope.tableTotal="";
            $("#resource").empty(); // empty in case the columns change
        });
    };

   

    //获取表字段
    function getDetailColumns(callback, errorCallback) {
        $http({
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                token: window.localStorage.getItem("token")
            },
            url: "/sofn-etl-query/etlQuery/getDetail",
            method: "post",
            data: $.param({
                tableName: $scope.currentTable.value,
                start: 0,
                length: 10
            })
        }).then(
            function(res) {
                if (res.data.code != 200) {
                    swal("", "该表没有数据", "error");
                    errorCallback && errorCallback();
                    return;
                }
                var columns = [];
                for (var key in res.data.res[0]) {
                    columns[columns.length] = {
                        title: key,
                        data: key
                    };
                }
                $scope.currentTableColumns = columns;
                callback && callback();
            },
            function(err) {
                swal("", "请求异常", "error");
            }
        );
    }

    //获取表的详细数据
    function getDetail() {
        $("#resource").dataTable({
            scrollX: true, //横向滚动
            ajax: {
                url: "/sofn-etl-query/etlQuery/getDetail",
                dataSrc: function(json) {
                    if (json.code != 200) {
                        swal("", "该表没有数据", "error");
                        errorCallback && errorCallback();
                        return false;
                    }
                    json.recordsTotal = json.resultCount; //总个数
                    json.recordsFiltered = json.resultCount;
                    $scope.$apply(function() {
                        $scope.tableTotal = json.resultCount;
                    });
                    return json.res;
                },
                headers: {
                    token: window.localStorage.getItem("token")
                },
                data: function(params) {
                    params.tableName = $scope.currentTable.value;
                    $scope.chartParams = params; //图表参数赋值
                    $scope.xAxis.length && vm.chart.update(); //刷新图表
                },
                type: "post"
            },
            columns: $scope.currentTableColumns
        });
    }

    /************************************************
     ******* chart *******
     ************************************************/

    //视图展示切换
    $scope.isListView = true;
    $scope.toggleView = function(isListView) {
        $scope.isListView = isListView;
        $scope.viewX = isListView ? "translateX(0)" : "translateX(-50%)";
        $(window).trigger("resize");
    };

    //图表参数配置
    vm.chart = {};
    vm.chartOptions = {
        //接口地址
        url: "/sofn-etl-query/etlQuery/getCount",
        grid: [1, 1], //水平宽度比例
        //业务参数
        data: function() {
            return {
                key: $scope.tablekey,
                tableName: $scope.chartParams.tableName,
                sort: $scope.tableSort,
                value:''
            };
        },
        complete: function(res) {
            if (res.data.code != 200) {
                swal("", "该表没有数据", "error");
                return;
            }
            var json = [
                {
                    name: $scope.currentTable.name,
                    type: "pie",
                    data: res.data.res.map(function(item){
                        item.name = /\d{4,6}/.test(item.name.replace(',', '')) ? $.fn.Cityname(item.name) : item.name;
                        return item;
                    })
                },
                {
                    name: $scope.currentTable.name,
                    type: "bar",
                    data: res.data.res.map(function(item){
                        item.name = /\d{4,6}/.test(item.name.replace(',', '')) ? $.fn.Cityname(item.name) : item.name;
                        return item;
                    })
                }
            ];
            return json;
        }
    };
});
