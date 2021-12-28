var myapp = angular.module("myapp", []);
myapp.controller("errorStat", function ($scope, $http, $state) {
    var testScript = document.createElement('script');
    testScript.src = 'assets/js/dgap/common.js';
    testScript.type = 'text/javascript';
    var head = document.getElementsByTagName('head');
    head[0].appendChild(testScript);

    //bootstrap表格
    $("#errorStat").dataTable({
        processing: true, // 加载时提示
        serverSide: true, // 分页，是否服务器端处理
        ordering: false,
        searching: false,
        bProcessing: true,
        //paging:true,
        //bInfo:true,
        //bJQueryUI: true,
        //bPaginate: false,// 分页按钮
        //bFilter: true,// 搜索栏
        //iDisplayLength: 10,// 每页显示行数
        //bSort: false,// 排序
        bAutoWidth: false,
        //sScrollY: "auto",
        dom: '<"top">rt<"bottom"pli><"clear">',
        autoWidth: false,
        ajax: {
            url: "/sofn-dgap-web/wsErrorStat/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for(var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].ERROR_DATE != null && json.data.list[i].ERROR_DATE != "" && json.data.list[i].ERROR_DATE != undefined) {
                        //json.data.list[i].ERROR_DATE=new Date(parseInt(json.data.list[i].ERROR_DATE)).toISOString().slice(0,10);
                        json.data.list[i].ERROR_DATE = formatTime(json.data.list[i].ERROR_DATE);
                        //json.data.list[i].ERROR_DATE=$filter('date')(json.data.list[i].ERROR_DATE, 'yyyy-MM-dd HH:mm:ss');
                    }
                } //js将时间为时间戳格式转成string格式
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
                params.resourceDirectoryId = $("#dirSelect").val();
                params.webServiceName = $("#webServiceName").val();
                params.startTime = $("#startTime").val();
                params.endTime = $("#endTime").val();
                params.methodName = $("#methodName").val();
                params.callerUser = $("#callerUser").val();
                params.errorType = $("#errorType").val();
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
                data: "METHOD_NAME",
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
                data: "ERROR_TYPE",
                title: "出错类型",
                render: function (data) { // 模板化列显示内容
                    if(data=="100000000"){
                        return "系统错误";
                    }else if(data=="100000001"){
                        return "认证错误";
                    }else if(data=="100000005"){
                        return "外部数据错误";
                    }else if(data=="100000006"){
                        return "数据量过大错误";
                    }else {
                        return "";
                    }
                },
            },
            {
                data: "ERROR_DESC",
                title: "出错的详细信息"
            },
            {
                data: "ERROR_DATE",
                title: "出错发生时间"
            }
        ]
    });
    // 清除查询条件
    $scope.cleanError = function () {
        $("select[name='alertType']").val("");
        $("input[name='datebegin']").val("");
        $("input[name='dateend']").val("");
        $("input[name='webServiceName']").val("");
        $("select[name='errorType']").val("");
        $("input[name='callerUser']").val("");
        $("input[name='methodName']").val("");
        $("#errorStat").dataTable().api().ajax.reload();
    };
    // ie兼容
    if (navigator.appVersion.substring(22, 23) == 9) {
        if ($(".myPlaceholder").val() == "") {
            $(".myPlaceholder").val("点击选择查询日期");
        }
    }
    //获取资源目录名称
    $http({
        url: "/sofn-dgap-web/resourceDirectory/all",
        method: "post"
    }).success(function (data) {
        $scope.dirs = data.data;
    }).error(function () {
    });

    $http({
        url: "/sofn-dgap-web/wsErrorStat/listWsErrorTypes",
        data: {},
        method: "post"
    }).success(function (data) {
        $scope.wsErrorTypes = data.data;
    }).error(function () {
    });
//查询
    $scope.ToCheck = function () {
        $("#errorStat").dataTable().api().ajax.reload();
    };
    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.ToCheck();
        }
    };

});
