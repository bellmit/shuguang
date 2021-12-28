var myapp = angular.module("myapp", []);
myapp.controller("logView", function ($scope, $http, $state) {
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    var defaultQueryDate = new Date().format("yyyy-MM-dd");
    $("#dateBegin").val(defaultQueryDate);
    $("#dateEnd").val(defaultQueryDate);

    $http({
        url: "/sofn-sys-web/log/getOperateTypes",
        method: "get",
        data: {},
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'token': window.localStorage.getItem("token")
        },
        transformRequest: function (data) {
            return $.param(data);
        }
    }).success(function (json) {
        $scope.operateTypesOptions = json.data;
    });

    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-sys-web/log/queryOperatehistoryLog",
                headers: {token: window.localStorage.getItem("token")},
                dataSrc: function (json) {
                    json.recordsTotal=json.page.recordsTotal;//总个数
                    json.recordsFiltered=json.page.recordsTotal;
                    json.pageNumber=json.page.pageNumber;//页码
                    json.pageSize=json.page.pageSize;//每页个数
                    for(var i=0;i<json.list.length;i++){
                        if(json.list[i].operateTime!=null&&json.list[i].operateTime!=""&&json.list[i].operateTime!=undefined){
                            json.list[i].operateTime = dateformat(new Date(+json.list[i].operateTime));
                        }
                    }
                    return json.list;
                },
                data: function (params) {
                    //分页条件查询，不实现，自己写
                    params.operateType = $("#handType").val();
                    params.operateUsername =  $("#logUser").val()=="操作人"?"":$("#logUser").val();
                    params.operateDesc = $("#logDesc").val()=="请输入操作说明"?"":$("#logDesc").val();
                    params.operateIp = $("#logIP").val()=="请输入操作IP"?"":$("#logIP").val();
                    params.startTime = $("#dateBegin").val()=="起始日期"?"":$("#dateBegin").val();
                    params.endTime = $("#dateEnd").val()=="终止日期"?"":$("#dateEnd").val();
                },
                type: "post"
            },
            columns: [
                {
                    data: "operateType",
                    title: "操作类型"
                },
                {
                    data: "operateDescribe",
                    title: "操作说明"
                },
                {
                    data: "status",
                    title: "结果",
                    render: function (data, type, row) {
                        return data == "Y" ? "成功" : "失败";
                    }
                },
                {
                    data: "operateUsername",
                    title: "操作人"
                },
                {
                    data: "operateTime",
                    title: "操作时间"
                },
                {
                    data: "operateIp",
                    title: "操作IP"
                }

            ]
        });
        //导航展开
        $("#collapseThree").attr("class", "panel-collapse collapse in");
        $("#collapseThree li:nth-of-type(1)").css("background-color", "#64ABDB").addClass("Sup_contain_side_nav_item_after").siblings().css("background-color", "#3695D4").removeClass("Sup_contain_side_nav_item_after");
    });

    /**
     * 查询缓存数据
     */
/*    $scope.queryList = function () {
        $("#grid").dataTable().api().ajax.url("/sofn-sys-web/log/queryOperateLog").load();
        $('#logDesc').attr("disabled",true);
        $('#logIP').attr("disabled",true);
        $('#logUser').attr("disabled",true);
        $('#dateBegin').attr("disabled",true);
        $('#dateEnd').attr("disabled",true);
    };*/

    /**
     * 查询历史记录(归档记录)
     */
    $scope.queryhistoryList = function () {
        $("#grid").dataTable().api().ajax.url("/sofn-sys-web/log/queryOperatehistoryLog").load();
        // $('#logDesc').attr("disabled",false);
        // $('#logIP').attr("disabled",false);
        // $('#logUser').attr("disabled",false);
        // $('#dateBegin').attr("disabled",false);
        // $('#dateEnd').attr("disabled",false);

    }

    $scope.exportReport = function() {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'get');
        form.attr('action', "/sofn-sys-web/log/exportStatsExcel");
        $('body').append(form);

        var startTime = $("#dateBegin").val();
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'startTime');
        input1.attr('value', startTime);

        var endTime = $("#dateEnd").val();
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'endTime');
        input2.attr('value', endTime);

        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'token');
        input3.attr('value', window.localStorage.getItem("token"));

        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.submit();
    }

    $scope.exportLog = function () {
        var form = $("<form>");
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'get');
        form.attr('action', "/sofn-sys-web/log/exportLogsExcel");
        $('body').append(form);

        var startTime = $("#dateBegin").val();
        var input1 = $('<input>');
        input1.attr('type', 'hidden');
        input1.attr('name', 'startTime');
        input1.attr('value', startTime);

        var endTime = $("#dateEnd").val();
        var input2 = $('<input>');
        input2.attr('type', 'hidden');
        input2.attr('name', 'endTime');
        input2.attr('value', endTime);

        var input3 = $('<input>');
        input3.attr('type', 'hidden');
        input3.attr('name', 'token');
        input3.attr('value', window.localStorage.getItem("token"));

        var input4 = $('<input>');
        input4.attr('type', 'hidden');
        input4.attr('name', 'operateType');
        input4.attr('value', $("#handType").val());

        var input5 = $('<input>');
        input5.attr('type', 'hidden');
        input5.attr('name', 'operateUsername');
        input5.attr('value', $("#logUser").val());

        var input6 = $('<input>');
        input6.attr('type', 'hidden');
        input6.attr('name', 'operateDesc');
        input6.attr('value', $("#logDesc").val());

        var input7 = $('<input>');
        input7.attr('type', 'hidden');
        input7.attr('name', 'operateIp');
        input7.attr('value', $("#logIP").val());

        form.append(input1);
        form.append(input2);
        form.append(input3);
        form.append(input4);
        form.append(input5);
        form.append(input6);
        form.append(input7);
        form.submit();
    }
});
function dateformat(date){
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    month = month < 10 ? '0'+month : month;
    day = day < 10 ? '0'+day : day;
    var hour = date.getHours();
    var min = date.getMinutes();
    min = min < 10 ? '0'+min : min;
    var seconds = date.getSeconds();
    seconds = seconds < 10 ? '0'+seconds : seconds;
    return [year, month, day].join('-') + ' ' +  [hour, min, seconds].join(':');
}