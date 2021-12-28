var myapp = angular.module("myapp", []);
myapp.controller("logView", function ($scope, $http, $state) {
    $("#ToCheck").click(function () {
        $("#grid").dataTable().api().ajax.reload();
    });
    /**
     * 分页展示数据
     */
    $(function () {
        //bootstrap表格
        $("#grid").dataTable({
            ajax: {
                url: "/sofn-dgap-web/log/queryDataOperateLog",
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
                    params.operateType = $("#handType").val(),
                    params.operateUsername =  $("#logUser").val()=="操作人"?"":$("#logUser").val(),
                    params.operateDesc = $("#logDesc").val()=="请输入操作说明"?"":$("#logDesc").val(),
                    params.operateIp = $("#logIP").val()=="请输入操作IP"?"":$("#logIP").val(),
                    params.startTime = $("#dateBegin").val()=="日志时间段"?"":$("#dateBegin").val(),
                    params.endTime = $("#dateEnd").val()=="日志时间段"?"":$("#dateEnd").val()
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
    $scope.queryList = function () {
        $("#grid").dataTable().api().ajax.url("/sofn-dgap-web/log/queryDataOperateLog").load();
        $('#logDesc').attr("disabled",true);
        $('#logIP').attr("disabled",true);
        $('#logUser').attr("disabled",true);
        $('#dateBegin').attr("disabled",true);
        $('#dateEnd').attr("disabled",true);
    };

    /**
     * 查询历史记录(归档记录)
     */
    $scope.queryhistoryList = function () {
        $("#grid").dataTable().api().ajax.url("/sofn-sys-web/log/queryDataOperatehistoryLog").load()
        $('#logDesc').attr("disabled",false);
        $('#logIP').attr("disabled",false);
        $('#logUser').attr("disabled",false);
        $('#dateBegin').attr("disabled",false);
        $('#dateEnd').attr("disabled",false);
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
function isPlaceholer(){
var input = document.createElement("input");
    return "placeholder" in input;
};
function placeholder(input){

var text = input.attr('placeholder'),
defaultValue = input.defaultValue;

if(!defaultValue){

input.val(text).addClass("phcolor");
}

input.focus(function(){

if(input.val() == text){

$(this).val("");
}
});


input.blur(function(){

if(input.val() == ""){

$(this).val(text).addClass("phcolor");
}
});

//输入的字符不为灰色
input.keydown(function(){

$(this).removeClass("phcolor");
});
};

//当浏览器不支持placeholder属性时，调用placeholder函数
if(!isPlaceholer()){

$('input').each(function(){

text = $(this).attr("placeholder");

if($(this).attr("type") == "text"){

placeholder($(this));
}
});
}