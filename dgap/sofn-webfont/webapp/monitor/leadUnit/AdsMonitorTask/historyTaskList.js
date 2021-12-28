var myapp=angular.module("myapp",[]);
myapp.controller("historyTaskList",function($scope,$http,$state){
    window.localStorage.setItem("task","his");
    var monitorClass = window.localStorage.getItem("monitorClass");
    // localStorage.removeItem("monitorClass");
    if(monitorClass == "" || monitorClass==null || monitorClass =='' || monitorClass =='undefined ' || monitorClass=="null"){
        $('#allmonitorClass').append(" <label>监测类型:</label>"+
            "<span class='gui-select'>"+
            "<select id='monitorClass' name='monitorClass' >"+
            "<option value=''>全部</option>"+
            "<option value='例行监测'>例行监测</option>"+
            "<option value='专项监测'>专项监测</option>"+
            "<option value='监督抽查'>监督抽查</option>"+
            "<option value='受托检测'>受托检测</option>"+
            "</select> </span>")
    }
    var token = window.localStorage.getItem("token");
    if(monitorClass == "例行监测"){
        $("#jiance").html("例行监测");
    }else if(monitorClass == "专项监测"){
        $("#jiance").html("专项监测");
    }
    $("#jiance").click(function () {
        if(monitorClass == "例行监测"){
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        }else if(monitorClass == "专项监测"){
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor");
        }else{
            $("#jiance").prop("href","#/index/content/monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor");
        }
    })
    $("#newTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/newTaskList")
    });

    $("#bePubTask").click(function(){
        window.localStorage.setItem("monitorClass",monitorClass);//监测类型
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/pubTaskList")
    });

    $("#historyTask").click(function(){
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/historyTaskList")
    });

    $("#supTask").click(function(){
        if(monitorClass == "例行监测"){
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsRoutineMonitor")
        }else if (monitorClass == "专项监测"){
            $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsRoutineMonitor/adsSpecialMonitor")
        }
    });

    $("#seachTask").click(function () {
        $scope.clickEvent();
    });
    $scope.clickEvent = function() {
        $("#grid").dataTable().api().ajax.reload();
    };
    //绑定回车事件
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.clickEvent();
        }
    };

    //年度
    $scope.year = [];
    var myDate = new Date();
    var startDate = myDate.getFullYear()-7;//起始年份
    var endDate = myDate.getFullYear();//结束年份
    for(var i = startDate; i<= endDate;i++){
        $scope.year.push(i);
    }
    //全选
    window.checkAll=function () {
        var val = $("input[name='checkAll']:checked");
        if(val.length > 0){
            //全选
            $("input[name='task']").attr("checked",true);
        }else{
            $("input[name='task']").attr("checked",false);
        }
    };

    $scope.monitorClass = ["例行监测","专项监测","监督抽查","受托检测"];

    $(document).ready(function(){
        $('#monitorClass').change(function(){
            $("#grid").dataTable().api().ajax.reload();
        });
        $('#year').change(function(){
            $("#grid").dataTable().api().ajax.reload();
        });
    });

    /**
     * 导出
     */
    $scope.exportExcel = function () {
        var check = $("input[name='task']:checked");
        if(check.length > 0) {
            var arr = new Array();
            $(check).each(function () {
                var task = $(this)[0];
                var id = $(task).val();
                arr.push(id);
            });
            var submit = function (v, h, f) {
                if (v == true)
                    $http({
                        url: "/sofn-ads-web/adsMonitorTask/exportAdsTaskExcel",
                        method: "post",
                        dataType: "json",
                        params: {
                            "ids": arr.toString(),
                            "token": token
                        },
                        responseType: 'arraybuffer',
                        headers: {"Content-Type": "application/x-www-form-urlencoded"},
                    }).success(function (data) {
                        var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
                        if (blob.size > 0) {
                            var objectUrl = URL.createObjectURL(blob);
                            var a = document.createElement('a');
                            document.body.appendChild(a);
                            a.setAttribute('style', 'display:none');
                            a.setAttribute('href', objectUrl);
                            a.setAttribute('download', '监测历史任务.xls');
                            a.click();
                        } else {
                            jBox.tip("导出失败，没有数据！", "info")
                        }
                    });
                else
                    jBox.tip("取消成功", 'success');
                return true;
            };
            // 自定义按钮
            $.jBox.confirm("导出操作需要花费一点时间，点击确认继续", "操作提示", submit, {buttons: {'确认': true, '取消': false}});
        }else{
            jBox.tip("请选择你需要导出的记录", 'info');
        }
    }

    /**
     * 分页展示数据
     */
    $(function() {
        //bootstrap表格
        $("#grid").dataTable({
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsMonitorTask/getPageInfo",
                dataSrc:function(json){
                    json.recordsTotal=json.data.total;//总个数
                    json.recordsFiltered=json.data.total;
                    json.pageNumber=json.data.pageNum;//页码
                    json.pageSize=json.data.pageSize;//每页个数
                    json.start=(json.data.pageNum-1)*json.data.pageSize;//起始位置
                    json.length=json.data.pageSize;
                    return json.data.list;
                },
                data : function(params) {
                    //分页条件查询，不实现，自己写
                    //params.name = $("input[name='in_name']").val();
                    //params.tel =  $("input[name='in_tel']").val();
                    params.year = $('#year option:selected').val();
                    if(monitorClass == "" || monitorClass==null || monitorClass =='' || monitorClass =='undefined ' || monitorClass=="null"){
                        params.monitorClass = $('#monitorClass option:selected').val();
                    }else {
                        params.monitorClass = monitorClass;
                    }

                    params.is_history = "Y";    //查询历史任务，包括已发布、执行中、已终止
                    params.taskName = $("input[name='taskName']").val();    //任务名称
                    params.token = token;
                },
                type:"post"
            },
            columns : [
                {
                    title : "<input type='checkbox' name='checkAll' onclick='checkAll()'>",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var view = "<input type='checkbox' name='task' value='"+row.id+"'>";
                        return view;
                    }
                },
                {
                    data: "rn",
                    title: "序号"
                },
                {
                    data: "taskName",
                    title: "任务名称",
                    render : function(data, type, row) {
                        var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report" onclick="lookOrganRegion(\''+row.id+'\') ">'+row.taskName+'</a>';
                        return look_a;
                    }
                },
                {
                    data: "year",
                    title: "年度"
                },
                {
                    data: "batch",
                    title: "批次"
                },
                {
                    data: "startTime",
                    title: "开始时间"
                },
                {
                    data: "endTime",
                    title: "结束时间"
                },
                {
                    data: "monitorClass",
                    title: "监测类型"
                },
                {
                    data: "priority",
                    title: "优先级",
                    render: function (data, type, row) {
                        // 数据操作选择项
                        return getPriorityName(data);
                    }
                },
                {
                    data : "publishStatus",
                    title : "状态",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        return getTaskPublishName(data);
                    }
                },
                {
                    data : "ID",
                    title : "操作",
                    render : function(data, type, row) {
                        // 数据操作选择项
                        var tmp = '<span class="btn btn-link btn-sm" onclick="exportWord(\''+row.id+'\',\''+row.taskName+'\')">导出word</span>';
                        return tmp;
                    }
                },
            ],
        });
    });

    //获取发布状态名称
    function getPriorityName(type) {
        var str = "";
        switch (type){
            case 1:
                str = '<span style="line-height:2;" class="label label-danger">特急</span>';
                break;
            case 2:
                str = '<span style="line-height:2;" class="label label-warning">紧急</span>';
                break;
            case 3:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
            default:
                str = '<span style="line-height:2;" class="label label-info">一般</span>';
                break;
        }
        return str;
    }

    //获取发布状态名称
    function getTaskPublishName(type) {
        var str = "";
        switch (type){
            case 0:
                str = '<span style="line-height:2;" class="label label-info">未发布</span>';
                break;
            case 1:
                str = '<span style="line-height:2;" class="label label-primary">已发布</span>';
                break;
            case 2:
                str = '<span style="line-height:2;" class="label label-success">执行中</span>';
                break;
            case 3:
                str = '<span style="line-height:2;" class="label label-default">已完成</span>';
                break;
            case 4:
                str = '<span style="line-height:2;" class="label label-warning">已结束，未完成</span>';
                break;
            case 5:
                str = '<span style="line-height:2;" class="label label-danger">已废止</span>';
                break;
            default:
                str = '<span style="line-height:2;" class="label label-info">已发布</span>';
                break;
        }
        return str;
    }

    /**
     * 导出监测任务word数据
     */
    window.exportWord = function (id,name) {
        $http({
            url:"/sofn-ads-web/adsMonitorTask/exportTaskDoc",
            method:"post",
            dataType:"json",
            params : {"adsMonitorTaskId":id},
            responseType: 'arraybuffer',
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
        }).success(function(data){
            var blob = new Blob([data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
            if(blob.size > 0){
                var taskName = name+".doc";
                var objectUrl = URL.createObjectURL(blob);
                var a = document.createElement('a');
                document.body.appendChild(a);
                a.setAttribute('style', 'display:none');
                a.setAttribute('href', objectUrl);
                a.setAttribute('download', taskName);
                a.click();
            }else{
                jBox.tip("导出失败，没有数据！","error");
            }
        });
    }

    window.check=function(e) {
        //var re = /^[\u4e00-\u9fa5]{1,30}$|^[\dA-Za-z_]{1,60}$/;
        var re = /^[\u4E00-\u9FA5A-Za-z0-9_]{1,40}$/;
        if(e.value != "") {
            if (!re.test(e.value)) {
                alert("最多只能输入40个字符");
                e.value = "";
                e.focus();
            }
        }
    }

    /**
     * 跳转到机构与地域页面
     */
    window.lookOrganRegion=function(data){
        window.localStorage.setItem("adsMonitorTaskId",data);
        $state.go("index.content.monitor/leadUnit/AdsMonitorTask/adsMonitorTaskView", {source: 'history'});
    };
})

