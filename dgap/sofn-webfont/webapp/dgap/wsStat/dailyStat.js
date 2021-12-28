var myapp = angular.module("myapp", []);
myapp.controller("dailyStat", function ($scope, $http, $state) {
//清除查询条件
    $scope.cleanDaily = function () {
        $("select[name='alertType']").val("");
        $("input[name='webServiceName']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='bussnessName']").val("");
        $("input[name='callerToken']").val("");
        $("#dailyStat").dataTable().api().ajax.reload();
    };
    //bootstrap表格
    $("#dailyStat").dataTable({
        processing: true, // 加载时提示
        serverSide: true, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        bAutoWidth: true,
        //sScrollY : "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        ajax: {
            url: "/sofn-dgap-web/wsDailyStat/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].STAT_DATE != null && json.data.list[i].STAT_DATE != "" && json.data.list[i].STAT_DATE != undefined) {
                        json.data.list[i].STAT_DATE = formatTime(json.data.list[i].STAT_DATE);
                        //json.data.list[i].ERROR_DATE=$filter('date')(json.data.list[i].ERROR_DATE, 'yyyy-MM-dd HH:mm:ss');
                    }
                }
                //js将时间为时间戳格式转成string格式
                function addZero(num) {
                    return num < 10 ? '0' + num : num;
                }

                function formatTime(timeStamp) {
                    var date = new Date(timeStamp);
                    var year = date.getFullYear();
                    var month = addZero(date.getMonth() + 1);
                    var day = addZero(date.getDate());
                    return [year, month, day].join('-');
                }

                return json.data.list;
            },
            data: function (params) {
		        params.token=window.localStorage.getItem("token");
                params.resourceDir = $("#dirSelect").val();
                params.webServiceName = $("#webServiceName").val();
                params.startTime = $("#startTime").val();
                params.endTime = $("#endTime").val();
                params.bussnessName = $("#bussnessName").val();
                params.callerUser = $("#callerToken").val();
            },
            type: "post"
        },
        columns: [
            /*{
             data : "ID",
             checkbox : true,
             title : '<input id="checkall" name="" type="checkbox" value="" onclick="checkAll(this)" />',
             render : function(data, type, row) { // 模板化列显示内容
             return '<input type="checkbox" value="'
             + row.id
             + '" onclick="checkClick(this)" />';
             },
             width : "25px",
             },*/
            {
                data: "RESOURCE_DIR_NAME",
                title: "目录名称",
                render: function (data, type, row) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return  data;
                    }else{
                        return "无"
                    }
                },
            },
            {
                data: "WEB_SERVICE_NAME",
                title: "服务名称",
                render: function (data, type, row) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return  data;
                    }else{
                        return "无"
                    }
                },
            },
            {
                data: "BUSSNESS_NAME",
                title: "业务方法名称"
            },
            {
                data: "CALLER_USER",
                title: "调用方名称",
                render: function (data, type, row) {
                    if(data!=null&&data!=""&&data!= undefined){
                        return  data;
                    }else{
                        return "无"
                    }
                },
            },
            {
                data: "SUCCESS_TIMES",
                title: "成功调用次数"
            },
            {
                data: "FAIL_TIMES",
                title: "失败调用次数"
            },
            {
                data: "AVG_TIMECOST",
                title: "平均调用耗时(毫秒)"
            },
            {
                data: "STAT_DATE",
                title: "统计日期"
            }
        ]
    });

    //获取资源目录名称
    $http({
        url: "/sofn-dgap-web/resourceDirectory/all",
        method: "post"
    }).success(function (data) {
        $scope.dirs = data.data;
    }).error(function () {
    });
    //查询
    $scope.queryResourceMaList = function () {
        $("#dailyStat").dataTable().api().ajax.reload();
    };
    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.queryResourceMaList();
        }
    };

});
