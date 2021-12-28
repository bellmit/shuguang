var myapp = angular.module("myapp", []);
myapp.controller("alertConfigLog", function ($scope, $http, $state) {

    $scope.ToCheck = function () {
        $("#alertConfigLog").dataTable().api().ajax.reload();
    };
    $scope.submit = function ($event) {
        if ($event && $event.keyCode == 13) {
            $scope.ToCheck();
        }
    };
    //清除查询条件
    $scope.cleanLog = function () {
        $("select[name='alertConfigId']").val("");
        $("select[name='targetId']").val("").change();
        $("input[name='alertDate']").val("");
        $("input[name='solveDate']").val("");
        $("select[name='solveStatus']").val("").change();
        $scope.a = undefined;
        $scope.webs = [];
        $("#alertConfigLog").dataTable().api().ajax.reload();
    };
    //分页
    $("#alertConfigLog").dataTable({
        ajax: {
            url: "/sofn-dgap-web/alertLog/list",
            //dataSrc : "rows", // 加载表格的相应数据源
            dataSrc: function (json) {
                json.recordsTotal = json.data.total;//总个数
                json.recordsFiltered = json.data.total;
                json.pageNumber = json.data.pageNum;//页码
                json.pageSize = json.data.pageSize;//每页个数
                json.start = (json.data.pageNum - 1) * json.data.pageSize;//起始位置
                json.length = json.data.pageSize;
                for (var i = 0; i < json.data.list.length; i++) {
                    if (json.data.list[i].ALERT_DATE != null && json.data.list[i].ALERT_DATE != "" && json.data.list[i].ALERT_DATE != undefined) {
                        json.data.list[i].ALERT_DATE = formatTime(json.data.list[i].ALERT_DATE);

                    }
                    if (json.data.list[i].SOLVE_DATE != null && json.data.list[i].SOLVE_DATE != "" && json.data.list[i].SOLVE_DATE != undefined) {
                        json.data.list[i].SOLVE_DATE = formatTime(json.data.list[i].SOLVE_DATE);

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
            //查询条件
            data: function (params) {
		        params.token=window.localStorage.getItem("token");
                params.alertConfigId = $("select[name='alertConfigId']").val();
                params.targetId = $("select[name='targetId']").val();
                params.alertDate = $("input[name='alertDate']").val();
                params.solveDate = $("input[name='solveDate']").val();
                params.solveStatus = $("select[name='solveStatus']").val();

            },
            type: "post"
        },

        columns: [
            {
                data: "ID",
                title: "序号",
                data: function(data, type, row, meta) {
                    return meta.row + 1;
                },
                className: "text-center",
                width: "5%"

            },
            {
                data: "RNAME",
                title: "资源目录"

            },
            {
                data: "DNAME",
                title: "webService服务接口"

            },
            {
                data: "ALERT_DATE",
                title: "预警时间"

            },
            {
               data: "SOLVE_DATE",
               title: "预警处理时间",

            },
            {
               data: "SOLVE_STATUS",
               title: "处理状态",
               render: function (data, type, row) {
                    if(data==null||data==""||data==undefined){
                        return '<a data-toggle="modal1" style="margin-right: 30px;cursor: pointer" onclick="checkStatus(\'' + row.ID + '\')">处理</a>' ;
                    }else{
                        return "已处理"
                    }
               }

            },
            {
                data: "DESCRIPTION",
                title: "预警内容"

            }
        ]
    });
//获取资源目录名称

    $http({
        url: "/sofn-dgap-web/alertConfig/getType",
        method: "post"
    }).success(function (data) {
        $scope.type = data.data;
    }).error(function () {
    });
    $scope.getWeb = function (data) {
        $http({
            url: "/sofn-dgap-web/alertConfig/getWeb",
            method: "post",
            data: {name: data}
        }).success(function (data) {
            $scope.webs = data.data;
        })
    };

    //跳转
    window.subjEntView = function (data) {
        window.localStorage.setItem("ID", data);
        $state.go("index.content.dgap/alertConfig/alertConfigLog");
    }
    window.checkStatus = function (id) {
        var submit = function (v, h, f) {
            if (v == true) {
                $.ajax({
                    url: "/sofn-dgap-web/alertLog/checkStatus",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (data) {
                        if (data.httpCode == 200) {
                            jBox.tip("处理成功！");
                        }else {
                            jBox.tip("处理失败！");
                        }
                        $("#alertConfigLog").dataTable().api().ajax.reload();
                    }
                });
            }
            else
                jBox.tip("取消处理", 'info');
            return true;
        };
        // 自定义按钮
        $.jBox.confirm("确认处理吗？", "处理提示", submit, {buttons: {'确认': true, '取消': false}});
    };

});