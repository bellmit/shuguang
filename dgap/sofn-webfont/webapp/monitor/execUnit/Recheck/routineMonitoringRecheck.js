var myapp=angular.module("myapp",[]);
myapp.controller("routineMonitoringRecheck",function($scope,$http,$state){
    $("#ToCheck").click(function(){
        $("#grid").dataTable().api().ajax.reload();
    });

    var orgId = "";

    var token = window.localStorage.getItem("token");

    /**
     * 分页展示数据
     */
    $(function() {

        $.ajax({
            url: "/sofn-ads-web/adsCheckInfo/findSysUserByToken",
            type: "post",
            dataType: "json",
            data: {"token":token},
            success: function (data) {
                orgId = data.user.orgId;
                $("#grid").dataTable().api().ajax.reload();
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        //bootstrap表格
        $("#grid").dataTable({
            dom : '<"top">rt<"bottom"pli><"clear">',
            ajax : {
                headers:{
                    token:window.localStorage.getItem("token")
                },
                url : "/sofn-ads-web/adsMonitorTask/getPageInfoWithParam?time="+new Date(),
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
                    // params.hangye = $('#hangye option:selected').text();
                    var yearparam = $('#year').val();
                    if (yearparam == ""){
                        params.year = "全部";
                    }else {
                        params.year = $('#year').val();
                    }
                    params.status = $('#status option:selected').val();
                    // params.level = $('#level option:selected').val();
                    params.orgId = orgId;
                    params.token = token;
                    params.type = "复检任务";
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
                    data : "rn",
                    title : "序号"
                },
                {
                    data : "taskSource",
                    title : "级别",
                    render : function(data, type, row) {
                        if("DEPTASK"==data){
                            return "部级";
                        }else{
                            return "省级";
                        }
                    }
                },
                {
                    data : "year",
                    title : "年度"
                },
                {
                    data : "checkProject",
                    title : "初检任务名称",
                    render : function(data, type, row) {
                        if(data!=null && data.length>30){
                            var look_a = '<a id="checkProject'+row.supId+'" onmouseover="showPopContent('+"'"+"checkProject"+row.supId+"'"+","+"'"+data+"'"+')" style="margin-right: 10px;cursor: pointer" class="Check-report text-info" onclick="goto2('+"'"+row.supId+"'"+","+"'"+row.industryId+"'"+"," +"'checkProject"+row.supId+"'"+')">'+data.substring(0,29)+"..."+'</a>';
                            return look_a;
                        }else {
                            var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report text-info" onclick="goto2('+"'"+row.supId+"'"+","+"'"+row.industryId+"'"+')">'+data+'</a>';
                            return look_a;
                        }
                    }
                },
                {
                    data : "taskName",
                    title : "复检任务名称",
                    render : function(data, type, row) {
                        if(data!=null && data.length>30){
                            var look_a = '<a id="taskName'+row.id+'" onmouseover="showPopContent('+"'"+"taskName"+row.id+"'"+","+"'"+data+"'"+')" style="margin-right: 10px;cursor: pointer" class="Check-report text-info" onclick="gotoRes('+"'"+row.id+"'"+","+"'"+row.publishStatus+"'"+"," +"'taskName"+row.id+"'"+')">'+data.substring(0,29)+"..."+'</a>';
                            return look_a;
                        }else {
                            var look_a = '<a style="margin-right: 10px;cursor: pointer" class="Check-report text-info" onclick="gotoRes('+"'"+row.id+"'"+","+"'"+row.publishStatus+"'"+')">'+data+'</a>';
                            return look_a;
                        }
                    }
                },
                {
                    data : "startTime",
                    title : "开始时间",
                    render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
                },
                {
                    data : "endTime",
                    title : "结束时间",
                    render : function(data, type, row) {
                        return new Date(data).format('yyyy-MM-dd');
                    }
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
                    title : "任务状态",
                    render : function(data, type, row) {
                        var STATUS = new Array('未发布','已发布','执行中','已结束,已完成','已结束,未完成','已废止');
                        if(data == '5'){
                            return '<span style="line-height:2;" class="label label-danger">'+ STATUS[data] + '</span>';
                        }else if(data =='4'){
                            return '<span style="line-height:2;" class="label label-warning">'+ STATUS[data] + '</span>';
                        }else if(data =='3'){
                            return '<span style="line-height:2;" class="label label-default">'+ STATUS[data] + '</span>';
                        }else if(data =='2'){
                            return '<span style="line-height:2;" class="label label-success">'+ STATUS[data] + '</span>';
                        }else if(data =='1'){
                            return '<span style="line-height:2;" class="label label-primary">'+ STATUS[data] + '</span>';
                        }else {
                            return '<span style="line-height:2;" class="label label-info">'+ STATUS[data] + '</span>';
                        }
                    }
                }
            ],
            sPaginationType : "full_numbers"
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

        //鼠标悬停弹窗显示
        window.showPopContent=function(id,content){
            $("#"+id).popover({
                animation:true,
                html:false,
                placement:'right',
                trigger:'hover',
                content:content,
                delay:0,
                container:'body'
            });
            $("#"+id).popover("show");
        }

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
                            url: "/sofn-ads-web/adsMonitorTask/exportAdsRecheckTaskExcel",
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
                                a.setAttribute('download', '导出任务.xls');
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
        };
        var url = "/sofn-ads-web/adsMonitorTask/getHangye?time="+new Date();
        //行业下拉框
        $.ajax({
            url: url,
            type: "post",
            dataType: "json",
            data: {"phase":1},
            success: function (data) {
                var datas = data.data;
                var Row = '<option>全部</option> ';
                if (datas.length > 0){
                    for (var i = 1; i <=datas.length; i++){
                        var td = datas[i-1];
                        Row =  Row+'<option>'+td.industry+'</option> ';
                    }
                }
                $("#hangye").html(Row);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        var urlYear = "/sofn-ads-web/adsMonitorTask/getYear?time="+new Date();
        //年度下拉框
        $.ajax({
            url: urlYear,
            type: "post",
            dataType: "json",
            data: {"phase":1},
            success: function (data) {
                var datas = data.data;
                var Row = '<option>全部</option> ';
                if (datas.length > 0){
                    for (var i = 1; i <=datas.length; i++){
                        var td = datas[i-1];
                        Row =  Row+'<option>'+td.year+'</option> ';
                    }
                }
                $("#year").html(Row);
            },
            fail: function (data) {
                $.jBox.tip("失败");
            }
        });

        var selectYear = document.getElementById('year');
        selectYear.onblur = function(){
            var year = $('#year').val();
            if (year != ""){
                if("全部"!=year){
                    if(document.getElementById('yli')){
                        var obj = document.getElementById("yli");
                        obj.parentNode.removeChild(obj);
                    }
                    var lihtml = '<li style="margin: 10px;float: left" id="yli">'+'年度：'+year+'<button class="gui-btn" id="year" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                    var lilength =  $(".setslist ul li").length;
                    if(lilength == 0){
                        $(".setslist ul").prepend(lihtml);
                    }else{
                        $(".setslist ul li:last").after(lihtml);
                    }
                }else{
                    if(document.getElementById('yli')){
                        var obj = document.getElementById("yli");
                        obj.parentNode.removeChild(obj)
                    }
                }
            }
        }

        var selectStatus = document.getElementById('status');
        selectStatus.onchange = function(){
            var status = $('#status option:selected').text();
            if("全部"!=status){
                if(document.getElementById('sli')){
                    var obj = document.getElementById("sli");
                    obj.parentNode.removeChild(obj)
                }
                var lihtml = '<li style="margin: 10px;float: left" id="sli">'+'状态：'+status+'<button class="gui-btn" id="status" type="button"><span class="glyphicon glyphicon-remove"></span></button></li>';
                var lilength =  $(".setslist ul li").length;
                if(lilength == 0){
                    $(".setslist ul").prepend(lihtml);
                }else{
                    $(".setslist ul li:last").after(lihtml);
                }
            }else{
                if(document.getElementById('sli')){
                    var obj = document.getElementById("sli");
                    obj.parentNode.removeChild(obj)
                }
            }
        }

        $(".setslist").on("click", "button", function(){
            var $this=$(this);
            $this.parents("li").remove();
            var aa = $(this).attr('id');
            if (aa=="year"){
                document.getElementById('year').value = "全部";
            }else {
                var a = document.getElementById(aa);
                a.options[0].selected = true;
            }
        });

        $(".cq2").on("click",function(){
            $('.setslist ul li').remove();
            document.getElementById('year').value = "全部";
            document.getElementById('status').options[0].selected = true;
        });
    });

    window.gotoRes = function(id,status,rowId){
        $http({
            url:"/sofn-ads-web/adsMonitorTask/queryAndUpdateById",
            method:"POST",
            params:{"id":id, "publishStatus":status}
        }).success(function(data){

        }).error(function(){
        });

        if(rowId!=null&&rowId!=undefined){
            $("#"+rowId).popover("hide");
        }

        $scope.sampleOrganId = orgId;//通过token获取抽样机构代码
        window.localStorage.setItem("adsMonitorTaskId",id);//监测任务id
        window.localStorage.setItem("token",token);//监测任务id
        window.localStorage.setItem("sampleOrganId",$scope.sampleOrganId);//监测任务id
        window.localStorage.setItem("status",status);
        $state.go("index.content.monitor/execUnit/Recheck/resTaskDetail");
    }

    window.goto2 = function(data,type,rowId){
        $scope.sampleOrganId = orgId;//通过token获取抽样机构代码
        window.localStorage.setItem("adsMonitorTaskId",data);//监测任务id
        window.localStorage.setItem("token",token);//监测任务id
        window.localStorage.setItem("sampleOrganId",$scope.sampleOrganId);//监测任务id

        if(rowId!=null&&rowId!=undefined){
            $("#"+rowId).popover("hide");
        }

        if("监督抽查" == type){
            $state.go("index.content.monitor/execUnit/Recheck/AuditMonitor/taskDetailAdu");
        }else{
            $state.go("index.content.monitor/execUnit/Recheck/RoutineMonitor/recheckTaskDetail");
        }
    }

})


